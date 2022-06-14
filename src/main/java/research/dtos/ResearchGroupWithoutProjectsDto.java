package research.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import research.model.Location;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ResearchGroupWithoutProjectsDto {
    private Long id;
    private String name;
    private Location location;
    private int budget;
}
