package research.controller;

import org.springframework.http.HttpStatus;
import research.dtos.CreateResearchGroupCommand;
import research.dtos.ResearchGroupDto;
import research.service.ProjectsAndGroupsService;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("api/research-groups")
public class ResearchGroupController {
    private ProjectsAndGroupsService projectsAndGroupsService;

    public ResearchGroupController(ProjectsAndGroupsService projectsAndGroupsService) {
        this.projectsAndGroupsService = projectsAndGroupsService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResearchGroupDto createResearchGroup(@Valid @RequestBody CreateResearchGroupCommand createResearchGroupCommand){
        return projectsAndGroupsService.createResearchGroup(createResearchGroupCommand);
    }
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
