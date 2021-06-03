package r2.llc.communal.util;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import r2.llc.communal.model.exception.ValidateException;

public class ResponseUtil {

    public static <T> ResponseEntity<?> of(Block<T> block) {
        try {
            T data = block.invoke();
            ResponseAPI<T> body = new ResponseAPI<>("OK", data);
            return ResponseEntity.ok(body);
        } catch (ValidateException e) {
            ErrorAPI body = new ErrorAPI(e.getValidateMessage(), e.getHttpStatus().value());
            return ResponseEntity.status(e.getHttpStatus()).body(body);
        } catch (Exception e) {
            ErrorAPI body = new ErrorAPI(e.getMessage(), 500);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(body);
        }
    }
}
