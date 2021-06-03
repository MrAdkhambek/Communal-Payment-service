package r2.llc.communal.model.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.http.HttpStatus;


@Data
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class ValidateException extends RuntimeException {
    private final String validateMessage;
    private final HttpStatus httpStatus;
}
