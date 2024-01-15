package pl.put.poznan.sorting_madness.logic;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Getter
public class HelpResponse {
    private final String overview;
    private List<String> endpoint;
    private Map<String, String> parameters;

    public HelpResponse() {
        this.overview = "The sort-api provides endpoints for sorting values and objects using different algorithms.";
    }

    public void setValues() {
        this.endpoint = List.of("/values");
        this.parameters = new HashMap<>();
        this.parameters.put("algorithm", "The sorting algorithm to be used. Options: BUBBLE_SORT, SELECTION_SORT, INSERTION_SORT, QUICK_SORT, MERGE_SORT. (required)");
        this.parameters.put("order", "The sorting order. Options: ASCENDING, DESCENDING (default: ASCENDING). (optional)");
        this.parameters.put("steps", "The number of sorting steps to be performed. (optional)");
        this.parameters.put("data", "The list of values to be sorted. (required) Request Body");
    }

    public void setObjects() {
        this.endpoint = List.of("/objects");
        this.parameters = new HashMap<>();
        this.parameters.put("algorithm", "The sorting algorithm to be used. Options: BUBBLE_SORT, SELECTION_SORT, INSERTION_SORT, QUICK_SORT, MERGE_SORT. (required)");
        this.parameters.put("field", "The field of the objects to sort by. (required)");
        this.parameters.put("order", "The sorting order. Options: ASCENDING, DESCENDING (default: ASCENDING). (optional)");
        this.parameters.put("steps", "The number of sorting steps to be performed. (optional)");
        this.parameters.put("data", "The list of objects to be sorted. (required) Request Body");
    }

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

