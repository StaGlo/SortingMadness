package pl.put.poznan.sorting_madness.rest;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import pl.put.poznan.sorting_madness.exception.WrongParameterException;
import pl.put.poznan.sorting_madness.logic.SortingResponse;

import java.util.List;
import java.util.Map;

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

    /** The service for handling sorting operations. */
    private SortingMadnessService sortingMadnessService;

    /**
     * Endpoint for sorting a list of values using a specified algorithm.
     * The algorithm can be specified via a request parameter, defaulting to
     * "BUBBLE_SORT".
     *
     * @param algorithm The sorting algorithm to be used (e.g., "BUBBLE_SORT").
     * @param data      The list of values to be sorted.
     * @return A SortingResponse object containing the sorted data and the time
     *         taken to sort.
     * @throws WrongParameterException If any validation on the input fails.
     */
    @GetMapping("/values")
    public SortingResponse sortValues(
            @RequestParam(defaultValue = "BUBBLE_SORT") String algorithm,
            @RequestBody List<Object> data) throws WrongParameterException {
        log.debug(data.toString());
        log.debug(algorithm);

        return sortingMadnessService.sortValues(algorithm, data);
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
     *         taken to sort.
     * @throws WrongParameterException If any validation on the input fails.
     */
    @GetMapping("/objects")
    public SortingResponse sortObjects(
            @RequestParam(defaultValue = "BUBBLE_SORT") String algorithm,
            @RequestParam String field,
            @RequestBody List<Map<String, Object>> data) throws WrongParameterException {
        log.debug(data.toString());
        log.debug(algorithm);

        return sortingMadnessService.sortObjects(algorithm, data, field);
    }
}
