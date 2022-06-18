package research.exceptions;

import org.zalando.problem.AbstractThrowableProblem;
import org.zalando.problem.Status;
import research.model.Project;
import research.model.ResearchGroup;

import java.net.URI;

public class researchGroupNotValidException extends AbstractThrowableProblem {
    public researchGroupNotValidException(ResearchGroup researchGroup) {
        super(URI.create("research-groups/not-valid"),
                "Not Valid",
                Status.BAD_REQUEST,
               String.format("ResearchGroup (name:%s, founded:%s, countOfResearchers:%d, budget:%d, location:%s) not valid.", researchGroup.getName(),researchGroup.getFounded(),researchGroup.getCountOfResearchers(),researchGroup.getBudget(),researchGroup.getLocation()));
    }
}
