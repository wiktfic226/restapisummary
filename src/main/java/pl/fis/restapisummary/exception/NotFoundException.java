package pl.fis.restapisummary.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.client.HttpStatusCodeException;

public class NotFoundException extends HttpStatusCodeException {
    public NotFoundException(HttpStatus statusCode, String message) {
        super(statusCode, message);
    }
}
