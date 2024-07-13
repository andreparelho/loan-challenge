package br.com.bank.app.exception.request;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class AgeLessThanMinimumException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public AgeLessThanMinimumException(String message) {
        super(message);
    }
}
