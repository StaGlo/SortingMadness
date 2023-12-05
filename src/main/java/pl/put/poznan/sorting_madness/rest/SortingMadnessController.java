package pl.put.poznan.sorting_madness.rest;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import pl.put.poznan.sorting_madness.exception.WrongAlgorithmException;

import java.util.List;


@RestController
@AllArgsConstructor
@Slf4j
@RequestMapping
public class SortingMadnessController {

    private SortingMadnessService<Float> floatSortingMadnessService;
    private SortingMadnessService<String> stringSortingMadnessService;

    @GetMapping("/floats")
    public List<Float> sortNumbers(
            @RequestParam(defaultValue = "BUBBLE_SORT") String algorithm,
            @RequestBody List<Float> data) throws WrongAlgorithmException {
        log.debug(data.toString());
        log.debug(algorithm);

        return floatSortingMadnessService.sortNumbers(algorithm, data);
    }

    @GetMapping("/strings")
    public List<String> sortStrings(
            @RequestParam(defaultValue = "BUBBLE_SORT") String algorithm,
            @RequestBody List<String> data) throws WrongAlgorithmException {
        log.debug(data.toString());
        log.debug(algorithm);

        return stringSortingMadnessService.sortNumbers(algorithm, data);
    }
}


