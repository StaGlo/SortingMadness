package pl.put.poznan.sorting_madness.rest;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import pl.put.poznan.sorting_madness.exception.ErrorResponse;
import pl.put.poznan.sorting_madness.exception.WrongParameterException;

/**
 * Global exception handler for REST controllers.
 * <p>
 * This class uses {@link ControllerAdvice} to handle exceptions across
 * the entire application in one global handling component.
 * </p>
 */
@ControllerAdvice
public class RestErrorHandler {

    /**
     * Handles {@link WrongParameterException} by returning a response with
     * a BAD_REQUEST status and error details.
     *
     * @param e The {@link WrongParameterException} thrown in the application.
     * @return An {@link ErrorResponse} object containing the error details.
     */
    @ExceptionHandler(WrongParameterException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ErrorResponse handleException(WrongParameterException e) {
        return new ErrorResponse(HttpStatus.BAD_REQUEST.value(), e.getMessage());
    }
}
