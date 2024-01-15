package pl.put.poznan.sorting_madness.rest;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import pl.put.poznan.sorting_madness.exception.WrongParameterException;
import pl.put.poznan.sorting_madness.logic.*;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * Controller for handling requests related to sorting operations.
 * It provides endpoints for sorting values and objects using specified sorting
 * algorithms.
 */
@RestController
@AllArgsConstructor
@Slf4j
@RequestMapping
public class SortingMadnessController {

    /**
     * The service for handling sorting operations.
     */
    private SortingMadnessService sortingMadnessService;

    /**
     * Endpoint for sorting a list of values using a specified algorithm.
     * The algorithm can be specified via a request parameter, defaulting to
     * "BUBBLE_SORT".
     *
     * @param algorithm The sorting algorithm to be used (e.g., "BUBBLE_SORT").
     * @param data      The list of values to be sorted.
     * @return A SortingResponse object containing the sorted data and the time
     * taken to sort.
     * @throws WrongParameterException If any validation on the input fails.
     */
    @GetMapping("/values")
    public List<SortingResponse> sortValues(
            @RequestParam(defaultValue = "") String algorithm,
            @RequestParam(defaultValue = "ASCENDING") String order,
            @RequestParam(required = false) String steps,
            @RequestBody List<Object> data) throws WrongParameterException {
        log.debug(data.toString());
        log.debug(algorithm);
        log.debug("Sorting order: " + order);
        return sortingMadnessService.sortValues(algorithm, order, data, steps);
    }

    /**
     * Endpoint for sorting a list of objects based on a specified field using a
     * specified algorithm.
     * The algorithm can be specified via a request parameter, defaulting to
     * "BUBBLE_SORT".
     *
     * @param algorithm The sorting algorithm to be used (e.g., "BUBBLE_SORT").
     * @param field     The field of the objects to sort by.
     * @param data      The list of objects to be sorted.
     * @return A SortingResponse object containing the sorted data and the time
     * taken to sort.
     * @throws WrongParameterException If any validation on the input fails.
     */
    @GetMapping("/objects")
    public List<SortingResponse> sortObjects(
            @RequestParam(defaultValue = "BUBBLE_SORT") String algorithm,
            @RequestParam(defaultValue = "ASCENDING") String order,
            @RequestParam String field,
            @RequestParam(required = false) String steps,
            @RequestBody List<Map<String, Object>> data) throws WrongParameterException {
        log.debug(data.toString());
        log.debug(algorithm);
        log.debug("Sorting order: " + order);

        return sortingMadnessService.sortObjects(algorithm, order, data, field, steps);
    }

    /**
     * Endpoint for retrieving help information about the sorting API.
     *
     * @param endpoint The specific endpoint for which help information is requested (optional).
     * @return A HelpResponse object containing information about the available
     * endpoints and parameters.
     */
    @GetMapping("/help")
    public HelpResponse getHelp(
            @RequestParam(defaultValue = "") String endpoint) {
        HelpResponse helpResponse = new HelpResponse();
        switch (endpoint) {
            case "/values":
                helpResponse.setValues();
                break;
            case "/objects":
                helpResponse.setObjects();
                break;
            case "":
                helpResponse.setEndpoints();
                break;
        }
        return helpResponse;
    }

}
