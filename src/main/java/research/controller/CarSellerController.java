package research.controller;

import research.service.ProjectsAndGroupsService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/car-sellers")
public class CarSellerController {
    private ProjectsAndGroupsService projectsAndGroupsService;

    public CarSellerController(ProjectsAndGroupsService projectsAndGroupsService) {
        this.projectsAndGroupsService = projectsAndGroupsService;
    }

//    @PostMapping
//    @ResponseStatus(HttpStatus.CREATED)
//    public CarSellerDTO createCarSeller(@RequestBody CreateCarSellerCommand createCarSellerCommand){
//        return projectsAndGroupsService.createCarSeller(createCarSellerCommand);
//    }
//
//    @PostMapping("/{id}/cars")
//    private CarSellerDTO addCarToSeller(@PathVariable long id, @RequestBody CreateCarCommand createCarCommand){
//           return projectsAndGroupsService.addCarToSeller(id,createCarCommand);
//    }
//    @DeleteMapping("/{id}")
//    @ResponseStatus(HttpStatus.NO_CONTENT)
//    public void deleteLocation(@PathVariable("id") long id){
//        projectsAndGroupsService.deleteCarSeller(id);
//    }
}
