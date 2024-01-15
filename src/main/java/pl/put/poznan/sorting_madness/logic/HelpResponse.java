package pl.put.poznan.sorting_madness.logic;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
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
    private final String overview;
    /**
     * The endpoint for which help information is provided.
     */
    private List<String> endpoint;
    /**
     * The parameters associated with the provided endpoint.
     */
    private Map<String, String> parameters;

    /**
     * Constructs a {@code HelpResponse} with a default message about the sorting API.
     */
    public HelpResponse() {
        this.overview = "The sort-api provides endpoints for sorting values and objects using different algorithms.";
    }

    /**
     * Sets help information for the "/values" endpoint.
     */
    public void setValues() {
        this.endpoint = List.of("/values");
        this.parameters = new HashMap<>();
        this.parameters.put("algorithm", "The sorting algorithm to be used. Options: BUBBLE_SORT, SELECTION_SORT, INSERTION_SORT, QUICK_SORT, MERGE_SORT. (required)");
        this.parameters.put("order", "The sorting order. Options: ASCENDING, DESCENDING (default: ASCENDING). (optional)");
        this.parameters.put("steps", "The number of sorting steps to be performed. (optional)");
        this.parameters.put("data", "The list of values to be sorted. (required) Request Body");
    }

    /**
     * Sets help information for the "/objects" endpoint.
     */
    public void setObjects() {
        this.endpoint = List.of("/objects");
        this.parameters = new HashMap<>();
        this.parameters.put("algorithm", "The sorting algorithm to be used. Options: BUBBLE_SORT, SELECTION_SORT, INSERTION_SORT, QUICK_SORT, MERGE_SORT. (required)");
        this.parameters.put("field", "The field of the objects to sort by. (required)");
        this.parameters.put("order", "The sorting order. Options: ASCENDING, DESCENDING (default: ASCENDING). (optional)");
        this.parameters.put("steps", "The number of sorting steps to be performed. (optional)");
        this.parameters.put("data", "The list of objects to be sorted. (required) Request Body");
    }

    /**
     * Sets help information for the general "/help" endpoint.
     */
    public void setEndpoints() {
        this.endpoint = Arrays.asList(
                "/values - Sorts a list of values given in the request body using a specified algorithm.",
                "/objects - Sort a list of objects based on a specified field using a specified algorithm.",
                "/help - Retrieve help information about the sorting API."
        );
        this.parameters = new HashMap<>();
        this.parameters.put("more information","to get information about specific endpoint use: /help?endpoint=</endpoint>");
    }
}

