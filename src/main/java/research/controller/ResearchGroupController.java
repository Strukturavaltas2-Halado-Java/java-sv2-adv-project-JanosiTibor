package research.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import research.criteria.ResearchGroupCriteria;
import research.dtos.*;
import research.service.ProjectsAndGroupsService;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("api/research-groups")
public class ResearchGroupController {
    private ProjectsAndGroupsService projectsAndGroupsService;

    public ResearchGroupController(ProjectsAndGroupsService projectsAndGroupsService) {
        this.projectsAndGroupsService = projectsAndGroupsService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Tag(name="01. Create research group")
    public ResearchGroupDto createResearchGroup(@Valid @RequestBody CreateResearchGroupCommand createResearchGroupCommand){
        return projectsAndGroupsService.createResearchGroup(createResearchGroupCommand);
    }
    @GetMapping
//    public List<ResearchGroupDto> getResearchGroups(@RequestParam(required = false) ResearchGroupCriteria researchGroupCriteria){
    @Tag(name="02. Read all or filtered research groups")
    public List<ResearchGroupDto> getResearchGroups(ResearchGroupCriteria researchGroupCriteria){
        return  projectsAndGroupsService.getResearchGroups(researchGroupCriteria);
    }

    @Operation(summary = "Update research group by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Research group has been updated"),
            @ApiResponse(responseCode = "404", description = "No research group found with this ID")
    })
    @ResponseStatus(HttpStatus.OK)
    @PutMapping("/update/{id}")
    @Tag(name="05. Update research group")
    public ResearchGroupDto updateResearchGroupById(@PathVariable("id") @Parameter(name = "id", description = "Research group ID to update", example = "2") long id, @RequestBody UpdateResearchGroupCommand updateResearchGroupCommand){
        return projectsAndGroupsService.updateResearchGroupById(id, updateResearchGroupCommand);
    }

    @DeleteMapping("/delete/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Tag(name="06. Delete research group")
    public void deleteResearchGroup(@PathVariable("id") @Parameter(name = "id", description = "Research group ID to delete", example = "2") long id){
        projectsAndGroupsService.deleteResearchGroup(id);
    }
    @GetMapping("/{id}")
    @Tag(name="04. Read research group by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Research group has been found"),
            @ApiResponse(responseCode = "404", description = "No research group found with this ID")
    })
    public ResearchGroupDto getResearchGroupById(@PathVariable("id") long id){
        return projectsAndGroupsService.getResearchGroupById(id);
    }

}
