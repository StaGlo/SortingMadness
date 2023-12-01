package pl.put.poznan.transformer.logic;

import java.util.Collections;
import java.util.List;

/**
 * This is just an example to show that the logic should be outside the REST service.
 */
public class SortingMadness {

    private final String order;

    public SortingMadness(String order){
        this.order = order;
    }
    public List<Integer> quickSort(List<Integer> dataList){
        Collections.sort(dataList);
        return dataList;
    }
    public String transform(String text){
        // of course, normally it would do something based on the transforms
        return text.toUpperCase();
    }
}
