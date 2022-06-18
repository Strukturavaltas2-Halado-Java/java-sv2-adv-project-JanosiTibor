package research.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import research.criteria.ProjectCriteria;
import research.criteria.ResearchGroupCriteria;
import research.dtos.*;
import research.service.ProjectsAndGroupsService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/projects")
@Tag(name="Operations on projects")
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
    @GetMapping
    public List<ProjectDto> getProjects(ProjectCriteria projectCriteria){
        return  projectsAndGroupsService.getProjects(projectCriteria);
    }
    @GetMapping("/{id}")
    public ProjectDto getProjectById( @PathVariable("id") long projectId){
        return projectsAndGroupsService.getProjectById(projectId);
    }

    @PostMapping("/{id}/add-group")
    @ResponseStatus(HttpStatus.CREATED)
    public ProjectDto addPostedGroupToProject(@PathVariable("id") long id, @Valid @RequestBody CreateResearchGroupCommand createResearchGroupCommand){
        return projectsAndGroupsService.addPostedGroupToProject(id,createResearchGroupCommand);
    }

//    @Operation(summary = "Find Location which contains ")
//    @ApiResponse(responseCode = "200", description = "Location has been found")
    @GetMapping("/{id}/add-group")
    @ResponseStatus(HttpStatus.OK)
    @ApiResponse(responseCode = "200", description = "Location has been found")
    public ProjectDto addGroupToProject(@PathVariable("id") long projectId, @RequestParam  @Parameter(name = "groupId", description = "ID of the group", example = "1") long groupId){
        return projectsAndGroupsService.addGroupToProject(projectId,groupId);
    }

    @Operation(summary = "Update Project By ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Project has been found"),
            @ApiResponse(responseCode = "404", description = "No project found with this ID")
    })
    @PutMapping("/update/{id}")
    public ProjectDto updateProject(@PathVariable("id") @Parameter(name = "id", description = "Project ID to update", example = "2") long id, @RequestBody UpdateProjectCommand updateProjectCommand){
        return projectsAndGroupsService.updateProject(id, updateProjectCommand);
    }

    @DeleteMapping("/delete/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteProject(@PathVariable("id") @Parameter(name = "id", description = "Project ID to delete", example = "2") long id){
        projectsAndGroupsService.deleteProject(id);
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
