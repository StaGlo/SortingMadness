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

    private SortingMadnessService sortingMadnessService;

    @GetMapping(produces = "application/json")
    public List<Float> sortNumbers(
            @RequestParam(defaultValue = "BUBBLE_SORT") String algorithm,
            @RequestBody List<Float> data) throws WrongAlgorithmException {
        log.debug(data.toString());
        log.debug(algorithm);

        return sortingMadnessService.sortNumbers(algorithm, data);
    }
}


