package pl.put.poznan.sorting_madness.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 * Class used to represent error response.
 */
@Getter
@Setter
@AllArgsConstructor
public class ErrorResponse {

    /**
     * Status code of error.
     */
    private Integer status;

    /**
     * Message of error.
     */
    private String message;
}
