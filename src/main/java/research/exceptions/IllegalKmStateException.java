package research.exceptions;

import org.zalando.problem.AbstractThrowableProblem;
import org.zalando.problem.Status;

import java.net.URI;

public class IllegalKmStateException  extends AbstractThrowableProblem {
    public IllegalKmStateException(int kmCounter) {
        super(URI.create("car/kmCount-error"),
                "Kilometer Count Error",
                Status.NOT_ACCEPTABLE,
                String.format("Not valid km count: "+ kmCounter));
    }
}
