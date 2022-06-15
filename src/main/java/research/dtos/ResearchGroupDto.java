package research.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import research.model.Location;
import research.model.Project;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.ManyToMany;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ResearchGroupDto {
    private Long id;
    private String name;
    private LocalDate founded;
    private int countOfResearchers;
    private Location location;
    private int budget;
    private Set<ProjectWithoutGroupsDto> projectSet = new HashSet<>();
}
