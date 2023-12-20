package pl.put.poznan.sorting_madness.exception;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * Class used to represent error response.
 */
/**
 * This exception is thrown when an invalid parameter is passed to a method or constructor.
 */
@Getter
@Setter
@ToString
public class WrongParameterException extends Exception {

    /**
     * Status code of the error.
     */
    private int errorCode;

    /**
     * Message describing the error.
     */
    private String errorMessage;

    /**
     * Constructs a new WrongParameterException with the specified cause.
     *
     * @param cause the cause of the exception
     */
    public WrongParameterException(Throwable cause) {
        super(cause);
    }

    /**
     * Constructs a new WrongParameterException with the specified detail message and cause.
     *
     * @param message the detail message
     * @param cause   the cause of the exception
     */
    public WrongParameterException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * Constructs a new WrongParameterException with the specified detail message.
     *
     * @param message the detail message
     */
    public WrongParameterException(String message) {
        super(message);
    }

    /**
     * Constructs a new WrongParameterException with the specified cause, error code, and error message.
     *
     * @param cause       the cause of the exception
     * @param errorCode   the error code
     * @param errorMessage the error message
     */
    public WrongParameterException(Throwable cause, int errorCode, String errorMessage) {
        super(cause);
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }
}
