package research.criteria;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProjectCriteria {
    public static final LocalDate DEFAULT_START_BEFORE=LocalDate.of(2100, 12, 31);
    public static final LocalDate DEFAULT_START_AFTER=LocalDate.of(1899, 12, 31);

    private String nameLike="";
    private LocalDate startBefore=DEFAULT_START_BEFORE;
    private LocalDate startAfter=DEFAULT_START_AFTER;
    private int minBudget=0;

    private ProjectOrderBy orderBy=ProjectOrderBy.id;
    private OrderType orderType=OrderType.asc;
}
