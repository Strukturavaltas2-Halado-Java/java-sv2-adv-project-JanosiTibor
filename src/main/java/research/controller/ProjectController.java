package research.controller;

import research.criteria.CarCriteria;
import research.dtos.CreateProjectCommand;
import research.dtos.CreateResearchGroupCommand;
import research.dtos.ProjectDto;
import research.service.ProjectsAndGroupsService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/projects")
public class ProjectController {
    private ProjectsAndGroupsService projectsAndGroupsService;

    public ProjectController(ProjectsAndGroupsService projectsAndGroupsService) {
        this.projectsAndGroupsService = projectsAndGroupsService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ProjectDto createProject(@Valid @RequestBody CreateProjectCommand createCarCommand){
        return projectsAndGroupsService.createProject(createCarCommand);
    }

    @PostMapping("/{id}/add-group")
    @ResponseStatus(HttpStatus.CREATED)
    public ProjectDto addPostedGroupToProject(@PathVariable("id") long id, @Valid @RequestBody CreateResearchGroupCommand createResearchGroupCommand){
        return projectsAndGroupsService.addPostedGroupToProject(id,createResearchGroupCommand);
    }

//    @GetMapping
//    public List<CarDTO> getCars(CarCriteria carCriteria){
//        return projectsAndGroupsService.getCars(carCriteria);
//    }
//
//
//    @GetMapping("/brands")
//    public List<String> getBrands(){
//        return projectsAndGroupsService.getBrands();
//    }
//
//    @PostMapping("/{id}/kilometerstates")
//    @ResponseStatus(HttpStatus.CREATED)
//    public CarDTO addKilometerStatesToCar(@PathVariable("id") long id, @Valid @RequestBody AddKilometerStatesCommand addKilometerStatesCommand){
//        return projectsAndGroupsService.addKilometerStatesToCar(id,addKilometerStatesCommand);
//    }
//
//    @GetMapping("/{id}")
//    public CarDTO getCar(@PathVariable("id") long id){
//        return projectsAndGroupsService.getCar(id);
//    }
//
//    @DeleteMapping("/{id}")
//    @ResponseStatus(HttpStatus.NO_CONTENT)
//    public void deleteLocation(@PathVariable("id") long id){
//        projectsAndGroupsService.deleteCar(id);
//    }
}
