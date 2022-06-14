package research.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CreateProjectCommand {
    @NotBlank(message = "The name of the project mustn't be blank!")
    private String name;
    @NotNull(message = "The starting date of the project must be valid!")
    private LocalDate startDate;
    @PositiveOrZero(message = "The budget of project mustn't be negative!")
    private int budget;
}
