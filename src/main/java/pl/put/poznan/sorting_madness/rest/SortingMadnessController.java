package pl.put.poznan.sorting_madness.rest;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import pl.put.poznan.sorting_madness.exception.WrongParameterException;
import pl.put.poznan.sorting_madness.logic.SortingResponse;

import java.util.List;
import java.util.Map;


@RestController
@AllArgsConstructor
@Slf4j
@RequestMapping
public class SortingMadnessController {

    private SortingMadnessService sortingMadnessService;

    @GetMapping("/values")
    public SortingResponse sortValues(
            @RequestParam(defaultValue = "BUBBLE_SORT") String algorithm,
            @RequestBody List<Object> data) throws WrongParameterException {
        log.debug(data.toString());
        log.debug(algorithm);

        return sortingMadnessService.sortValues(algorithm, data);
    }

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


