package research;

import research.service.ProjectsAndGroupsService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.reactive.server.EntityExchangeResult;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.zalando.problem.Problem;
import org.zalando.problem.Status;
import org.zalando.problem.violations.ConstraintViolationProblem;

import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Sql(statements = {"DELETE FROM car_kilometer_state_list","DELETE FROM cars", "DELETE FROM car_sellers"})
class ProjectControllerWebClientIT {
    @Autowired
    WebTestClient webTestClient;

    @Autowired
    ProjectsAndGroupsService projectsAndGroupsService;

    CarDTO carDTO;

    @BeforeEach
    void init(){
        webTestClient
                .post()
                .uri("/api/cars")
                .bodyValue(new CreateCarCommand("Toyota","Corolla",25, Condition.POOR,295000))
                .exchange();
        carDTO=webTestClient
                .post()
                .uri("/api/cars")
                .bodyValue(new CreateCarCommand("Toyota","Corolla",15,Condition.EXCELLENT,95000))
                .exchange()
                .expectBody(CarDTO.class)
                .returnResult()
                .getResponseBody();
        webTestClient
                .post()
                .uri("/api/cars")
                .bodyValue(new CreateCarCommand("BMW","X3",19,Condition.NORMAL,350000))
                .exchange();
        webTestClient
                .post()
                .uri("/api/cars")
                .bodyValue(new CreateCarCommand("Toyota","Auris",5,Condition.EXCELLENT,55892))
                .exchange();
    }

    @Test
    void testCreateCar(){
        webTestClient
                .post()
                .uri("/api/cars")
                .bodyValue(new CreateCarCommand("Toyota","Corolla",25,Condition.POOR,295000))
                .exchange()
                .expectStatus().isCreated()
                .expectBody(CarDTO.class)
                .value(c->assertThat(c.getBrand()).isEqualTo("Toyota"))
        ;
    }
    @Test
    void testCreateCarOtherStyle(){
        EntityExchangeResult<CarDTO> result=webTestClient
                .post()
                .uri("/api/cars")
                .bodyValue(new CreateCarCommand("Toyota","Corolla",25,Condition.POOR,295000))
                .exchange()
                .expectBody(CarDTO.class)
                .returnResult();
        assertThat(result.getResponseBody().getType()).isEqualTo("Corolla");
    }
    @Test
    void testGetCarsWithBrand(){
        webTestClient
                .get()
                .uri(uriBuilder -> uriBuilder.path("api/cars").queryParam("brand","toyota").build())
                .exchange()
                .expectBodyList(CarDTO.class)
                .hasSize(3)
                .contains(new CarDTO(4L,"Toyota","Auris",5,Condition.EXCELLENT, List.of(new KilometerStateDTO(55892, LocalDate.now()))));
    }
    @Test
    void testGetBrands(){
        List<String> result = webTestClient
                .get()
                .uri("/api/cars/brands")
                .exchange()
                .expectBody(List.class)
                .returnResult()
                .getResponseBody();

        assertThat(result)
                .hasSize(2)
                .containsOnly("Toyota","BMW");
    }
    @Test
    void testCarNotFound(){
        Problem p=webTestClient.get()
                .uri(uriBuilder -> uriBuilder.path("/api/cars/{id}").build(2002))
                .exchange()
                .expectBody(Problem.class)
                .returnResult()
                .getResponseBody();
        assertEquals("Car with id: 2002 not found",p.getDetail());
        assertEquals(Status.NOT_FOUND,p.getStatus());
        assertEquals("Not found",p.getTitle());
        assertEquals("car/not-found",p.getType().getPath());
    }
    @Test
    void testAddKilometerState(){
        CarDTO result=webTestClient.post()
                .uri(uriBuilder -> uriBuilder.path("/api/cars/{id}/kilometerstates").build(carDTO.getId()))
                .bodyValue(new AddKilometerStatesCommand(102500))
                .exchange()
                .expectBody(CarDTO.class)
                .returnResult()
                .getResponseBody();

        assertThat(result.getKilometerStateList())
                .hasSize(2)
                .extracting(KilometerStateDTO::getKmCounter)
                .containsOnly(95000,102500);
    }
    @Test
    void testAddWrongKilometerState(){
        Problem result=webTestClient.post()
                .uri(uriBuilder -> uriBuilder.path("/api/cars/{id}/kilometerstates").build(carDTO.getId()))
                .bodyValue(new AddKilometerStatesCommand(2500))
                .exchange()
                .expectBody(Problem.class)
                .returnResult()
                .getResponseBody();

        assertEquals("Not valid km count: 2500",result.getDetail());
        assertEquals(Status.NOT_ACCEPTABLE,result.getStatus());
        assertEquals("Kilometer Count Error",result.getTitle());
        assertEquals("car/kmCount-error",result.getType().getPath());
    }
    @Test
    void testAddNegativeKilometerState(){
        ConstraintViolationProblem result=webTestClient.post()
                .uri(uriBuilder -> uriBuilder.path("/api/cars/{id}/kilometerstates").build(carDTO.getId()))
                .bodyValue(new AddKilometerStatesCommand(-2500))
                .exchange()
                .expectBody(ConstraintViolationProblem.class)
                .returnResult()
                .getResponseBody();

        assertEquals(Status.BAD_REQUEST,result.getStatus());
        assertEquals("The km counter mustn't be negative!",result.getViolations().get(0).getMessage());
    }
}