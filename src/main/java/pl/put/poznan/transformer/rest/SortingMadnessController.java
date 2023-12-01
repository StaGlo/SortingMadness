package pl.put.poznan.transformer.rest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;
import pl.put.poznan.transformer.logic.SortingMadness;

import java.util.Arrays;
import java.util.List;


@RestController
@RequestMapping("/")
public class SortingMadnessController {

    private static final Logger logger = LoggerFactory.getLogger(SortingMadnessController.class);

//    @RequestMapping(method = RequestMethod.GET, produces = "application/json")
//    public String get(@PathVariable String text,
//                              @RequestParam(value="transforms", defaultValue="upper,escape") String[] transforms) {
//
//        // log the parameters
//        logger.debug(text);
//        logger.debug(Arrays.toString(transforms));
//
//        // perform the transformation, you should run your logic here, below is just a silly example
//        SortingMadness transformer = new SortingMadness("transofrms");
//        return transformer.transform(text);
//    }

    @RequestMapping(method = RequestMethod.POST, produces = "application/json")
    public List<Integer> post(@RequestParam(defaultValue = "ascending") String order, @RequestBody List<Integer> dataList) {

        // log the parameters
        logger.debug(dataList.toString());
        logger.debug(order);

        // Perform any other processing as needed
        SortingMadness sortingMadness = new SortingMadness(order);
        return sortingMadness.quickSort(dataList);
    }



}


