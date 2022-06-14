package research.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import research.model.Location;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.PositiveOrZero;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CreateResearchGroupCommand {
    @NotBlank(message = "The name of the research group mustn't be blank!")
    private String name;
    private Location location;
    @PositiveOrZero(message = "The budget of research group mustn't be negative!")
    private int budget;
}
