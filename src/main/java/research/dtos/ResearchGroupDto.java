package research.dtos;

import research.model.Location;
import research.model.Project;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.ManyToMany;
import java.util.HashSet;
import java.util.Set;

public class ResearchGroupDto {
    private Long id;
    private String name;
    private Location location;
    private int budget;
    private Set<ProjectWithoutGroupsDto> projectSet = new HashSet<>();
}
