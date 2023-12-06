package pl.put.poznan.sorting_madness.rest;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import pl.put.poznan.sorting_madness.exception.ErrorResponse;
import pl.put.poznan.sorting_madness.exception.WrongParameterException;

@ControllerAdvice
public class RestErrorHandler {

    @ExceptionHandler(WrongParameterException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ErrorResponse handleException(WrongParameterException e) {
        return new ErrorResponse(HttpStatus.BAD_REQUEST.value(), e.getMessage());
    }
}
