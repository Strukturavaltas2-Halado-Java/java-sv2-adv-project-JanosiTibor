package research.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import research.model.Location;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CreateResearchGroupCommand {
    @NotBlank(message = "The name of the research group mustn't be blank!")
    private String name;
    @NotNull(message = "The research group's date of foundation must be valid!")
    private LocalDate founded;
    @Positive(message = "The number of researchers in the research group must be positive!")
    private int countOfResearchers;
    private Location location;
    @PositiveOrZero(message = "The budget of research group mustn't be negative!")
    private int budget;
}
