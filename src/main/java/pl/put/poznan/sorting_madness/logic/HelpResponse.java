package pl.put.poznan.sorting_madness.logic;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;
import java.util.List;

/**
 * The {@code HelpResponse} class represents the response format for providing
 * help information about the sorting API.
 *
 * This class includes methods to set specific help information for different
 * endpoints of the sorting API, such as "/values", "/objects", and "/help".
 * The information includes details about endpoints, parameters, and usage.
 */
@Slf4j
@Getter
public class HelpResponse {
    /**
     * A general message about the purpose of the sorting API.
     */
    private final String message;

    /**
     * The endpoint for which help information is provided.
     */
    private List<String> endpoint;

    /**
     * The parameters associated with the provided endpoint.
     */
    private List<String> parameters;

    /**
     * Constructs a {@code HelpResponse} with a default message about the sorting API.
     */
    public HelpResponse() {
        this.message = "The sort-api provides endpoints for sorting values and objects using different algorithms.";
    }

    /**
     * Sets help information for the "/values" endpoint.
     */
    public void setValues() {
        this.endpoint = List.of("/values");
        this.parameters = Arrays.asList(
                "PARAMETERS:",
                "algorithm (required) - The sorting algorithm to be used Options: BUBBLE_SORT, SELECTION_SORT, INSERTION_SORT, QUICK_SORT, MERGE_SORT.",
                "order (optional) - The sorting order Options: ASCENDING, DESCENDING (default: ASCENDING).",
                "steps (optional) - The number of sorting steps to be performed.",
                "REQUEST BODY",
                "data (required) - The list of values to be sorted."
        );
    }

    /**
     * Sets help information for the "/objects" endpoint.
     */
    public void setObjects() {
        this.endpoint = List.of("/objects");
        this.parameters = Arrays.asList(
                "PARAMETERS:",
                "algorithm (required) - The sorting algorithm to be used Options: BUBBLE_SORT, SELECTION_SORT, INSERTION_SORT, QUICK_SORT, MERGE_SORT.",
                "field (required) - The field of the objects to sort by.",
                "order (optional) - The sorting order Options: ASCENDING, DESCENDING (default: ASCENDING).",
                "steps (optional) - The number of sorting steps to be performed.",
                "REQUEST BODY",
                "data (required) - The list of objects to be sorted."
        );
    }

    /**
     * Sets help information for the general "/help" endpoint.
     */
    public void setEndpoints() {
        log.debug("set endpoints");
        this.endpoint = Arrays.asList(
                "/objects - Sorts a list of values given in the request body using a specified algorithm.",
                "/objects - Sort a list of objects based on a specified field using a specified algorithm.",
                "/help - Retrieve help information about the sorting API."
        );
        this.parameters = List.of(
                "to get information about specific endpoint:",
                "/help?endpoint=</endpoint>");
    }
}
