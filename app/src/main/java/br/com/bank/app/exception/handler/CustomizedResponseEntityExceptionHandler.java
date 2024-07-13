package br.com.bank.app.exception.handler;

import br.com.bank.app.exception.request.AgeLessThanMinimumException;
import br.com.bank.app.exception.ExceptionResponse;
import br.com.bank.app.exception.request.NameIsBlankRequestException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Date;

@RestController
@ControllerAdvice
public class CustomizedResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(Exception.class)
    public final ResponseEntity<ExceptionResponse> handleAllExceptions(Exception exception, WebRequest webRequest){
        ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(), exception.getMessage(), webRequest.getDescription(false));
        return new ResponseEntity<>(exceptionResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(AgeLessThanMinimumException.class)
    public final ResponseEntity<ExceptionResponse> handleAgeLessThanMinimumException(Exception exception, WebRequest webRequest){
        ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(), exception.getMessage(), webRequest.getDescription(false));
        return new ResponseEntity<>(exceptionResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NameIsBlankRequestException.class)
    public final ResponseEntity<ExceptionResponse> handleNameIsBlankRequestException(Exception exception, WebRequest webRequest){
        ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(), exception.getMessage(), webRequest.getDescription(false));
        return new ResponseEntity<>(exceptionResponse, HttpStatus.BAD_REQUEST);
    }

}
