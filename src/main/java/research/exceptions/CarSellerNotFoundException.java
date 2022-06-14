package research.exceptions;

import org.zalando.problem.AbstractThrowableProblem;
import org.zalando.problem.Status;

import java.net.URI;

public class CarSellerNotFoundException extends AbstractThrowableProblem {
    public CarSellerNotFoundException (long id) {
        super(URI.create("car-sellers/not-found"),
                "Not found",
                Status.NOT_FOUND,
                String.format("Car-seller with id: %d not found", id));
    }
}
