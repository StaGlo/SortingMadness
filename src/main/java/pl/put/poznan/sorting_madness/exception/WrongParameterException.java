package pl.put.poznan.sorting_madness.exception;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class WrongParameterException extends Exception {

    private int errorCode;
    private String errorMessage;

    public WrongParameterException(Throwable cause) {
        super(cause);
    }

    public WrongParameterException(String message, Throwable cause) {
        super(message, cause);
    }

    public WrongParameterException(String message) {
        super(message);
    }

    public WrongParameterException(Throwable cause, int errorCode, String errorMessage) {
        super(cause);
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }
}
