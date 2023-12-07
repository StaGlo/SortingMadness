package pl.put.poznan.sorting_madness.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import pl.put.poznan.sorting_madness.exception.WrongParameterException;

import java.util.List;
import java.util.Map;


@RestController
@AllArgsConstructor
@Slf4j
@RequestMapping
public class SortingMadnessController {

    private SortingMadnessService sortingMadnessService;

    @GetMapping("/values")
    public String  sortValues(
            @RequestParam(defaultValue = "BUBBLE_SORT") String algorithm,
            @RequestParam(defaultValue = "FLOATS") String inputType,
            @RequestBody List<Object> data) throws WrongParameterException {
        log.debug(data.toString());
        log.debug(algorithm);

        Map<String,Object> responseMap = sortingMadnessService.sortValues(algorithm, inputType, data);

        ObjectMapper objectMapper = new ObjectMapper();
        try {
            return objectMapper.writeValueAsString(responseMap);
        } catch (Exception e) {
            // Handle exception (e.g., log it or return an error message and code 500)
            e.printStackTrace();
            return "Error converting to JSON";
        }
    }
}


