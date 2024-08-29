package hello.exception.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.REQUEST_TIMEOUT, reason = "error.bad")
public class BadRequestException extends RuntimeException{

}
