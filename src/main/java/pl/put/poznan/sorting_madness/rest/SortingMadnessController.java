package pl.put.poznan.sorting_madness.rest;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import pl.put.poznan.sorting_madness.exception.WrongParameterException;

import java.util.List;


@RestController
@AllArgsConstructor
@Slf4j
@RequestMapping
public class SortingMadnessController {

    private SortingMadnessService sortingMadnessService;

    @GetMapping("/values")
    public List<Comparable<?>> sortValues(
            @RequestParam(defaultValue = "BUBBLE_SORT") String algorithm,
            @RequestParam(defaultValue = "FLOATS") String inputType,
            @RequestBody List<Object> data) throws WrongParameterException {
        log.debug(data.toString());
        log.debug(algorithm);

        return sortingMadnessService.sortValues(algorithm, inputType, data);
    }
}


