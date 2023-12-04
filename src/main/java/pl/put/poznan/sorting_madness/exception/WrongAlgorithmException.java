package pl.put.poznan.sorting_madness.exception;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class WrongAlgorithmException extends Exception {

    private int errorCode;
    private String errorMessage;

    public WrongAlgorithmException(Throwable cause) {
        super(cause);
    }

    public WrongAlgorithmException(String message, Throwable cause) {
        super(message, cause);
    }

    public WrongAlgorithmException(String message) {
        super(message);
    }

    public WrongAlgorithmException(Throwable cause, int errorCode, String errorMessage) {
        super(cause);
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }
}
