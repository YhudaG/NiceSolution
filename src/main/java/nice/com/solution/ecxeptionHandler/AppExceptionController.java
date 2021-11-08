package nice.com.solution.ecxeptionHandler;

import nice.com.solution.model.ApiError;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class AppExceptionController extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = AppException.class)
    public ResponseEntity<Object> exception(AppException exception) {
        ApiError apiError = exception.getApiError();
        return new ResponseEntity<>(apiError.getMessage(), apiError.getStatus());
    }

    @ExceptionHandler(value = Exception.class)
    public ResponseEntity<Object> exception(Exception exception) {
        return new ResponseEntity<>("Unexpected error", HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
