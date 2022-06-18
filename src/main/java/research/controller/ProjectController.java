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
public class ProjectController {
    private ProjectsAndGroupsService projectsAndGroupsService;

    public ProjectController(ProjectsAndGroupsService projectsAndGroupsService) {
        this.projectsAndGroupsService = projectsAndGroupsService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Tag(name="11. Create project")
    public ProjectDto createProject(@Valid @RequestBody CreateProjectCommand createCarCommand){
        return projectsAndGroupsService.createProject(createCarCommand);
    }
    @GetMapping
    @Tag(name="12. Read all or filtered projects")
    public List<ProjectDto> getProjects(ProjectCriteria projectCriteria){
        return  projectsAndGroupsService.getProjects(projectCriteria);
    }
    @GetMapping("/{id}")
    @Tag(name="13. Read project  by id")
    public ProjectDto getProjectById( @PathVariable("id") long projectId){
        return projectsAndGroupsService.getProjectById(projectId);
    }

    @Operation(summary = "Update Project By ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Project has been found"),
            @ApiResponse(responseCode = "404", description = "No project found with this ID")
    })
    @PutMapping("/update/{id}")
    @Tag(name="14. Update project")
    public ProjectDto updateProject(@PathVariable("id") @Parameter(name = "id", description = "Project ID to update", example = "2") long id, @RequestBody UpdateProjectCommand updateProjectCommand){
        return projectsAndGroupsService.updateProject(id, updateProjectCommand);
    }

    @PostMapping("/{id}/add-group")
    @ResponseStatus(HttpStatus.CREATED)
    @Tag(name="15. Post research group to project")
    public ProjectDto addPostedGroupToProject(@PathVariable("id") long id, @Valid @RequestBody CreateResearchGroupCommand createResearchGroupCommand){
        return projectsAndGroupsService.addPostedGroupToProject(id,createResearchGroupCommand);
    }

    @GetMapping("/{id}/add-group")
    @ResponseStatus(HttpStatus.OK)
    @ApiResponse(responseCode = "200", description = "Research group has been added")
    @Tag(name="16. Link research group to project")
    public ProjectDto addGroupToProject(@PathVariable("id") long projectId, @RequestParam  @Parameter(name = "groupId", description = "ID of the group", example = "1") long groupId){
        return projectsAndGroupsService.addGroupToProject(projectId,groupId);
    }

    @GetMapping("/{id}/delete-group")
    @ResponseStatus(HttpStatus.OK)
    @ApiResponse(responseCode = "200", description = "Research group has been added")
    @Tag(name="16. Unlink research group from project")
    public ProjectDto deleteGroupFromProject(@PathVariable("id") long projectId, @RequestParam  @Parameter(name = "groupId", description = "ID of the group", example = "1") long groupId){
        return projectsAndGroupsService.deleteGroupFromProject(projectId,groupId);
    }



    @DeleteMapping("/delete/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Tag(name="18. Delete project")
    public void deleteProject(@PathVariable("id") @Parameter(name = "id", description = "Project ID to delete", example = "2") long id){
        projectsAndGroupsService.deleteProject(id);
    }
}
