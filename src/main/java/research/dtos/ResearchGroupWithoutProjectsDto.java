package research.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import research.model.Location;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ResearchGroupWithoutProjectsDto {
    private Long id;
    private String name;
    private LocalDate founded;
    private int countOfResearchers;
    private Location location;
    private int budget;
}
