package pl.put.poznan.sorting_madness.rest;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import pl.put.poznan.sorting_madness.exception.ErrorResponse;
import pl.put.poznan.sorting_madness.exception.WrongAlgorithmException;

@ControllerAdvice
public class RestErrorHandler {

    @ExceptionHandler(WrongAlgorithmException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ErrorResponse handleException(WrongAlgorithmException e) {
        return new ErrorResponse(HttpStatus.BAD_REQUEST.value(), e.getMessage());
    }
}
