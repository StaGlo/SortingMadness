package pl.put.poznan.sorting_madness.rest;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import pl.put.poznan.sorting_madness.exception.WrongParameterException;
import pl.put.poznan.sorting_madness.logic.*;
import pl.put.poznan.sorting_madness.util.NameValidator;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class SortingMadnessService {

    @NonNull
    private SortingMadness sortingMadness;

    public Map<String, Object> sortValues(String algorithmAsString, String inputTypeAsString, List<Object> data)
            throws WrongParameterException {
        var algorithmName = NameValidator.validateAlgorithmName(algorithmAsString);
        var inputType = NameValidator.validateInputType(inputTypeAsString);

        List<Comparable<?>> convertedData = null;
        switch (inputType) {
            case DOUBLES:
                convertedData = data.stream().map(o -> (Double) o).collect(Collectors.toList());
                break;
            case STRINGS:
                convertedData = data.stream().map(o -> (String) o).collect(Collectors.toList());
                break;
            case INTEGERS:
                convertedData = data.stream().map(o -> (Integer) o).collect(Collectors.toList());
                break;
        }
        Map<String, Object> resultMap = new HashMap<>();
        if (AlgorithmName.BUBBLE_SORT.equals(algorithmName)) {
            sortingMadness.setStrategy(new SortingTimeDecorator(new BubbleSort()));
            Map<String, Object> localMap = sortingMadness.performSort(convertedData, (Comparator<Comparable<?>>) Comparator.naturalOrder());
            resultMap.put("sorted_list",localMap.get("list"));
            resultMap.put("bubble_sort_time",localMap.get("time"));
        }
        if (AlgorithmName.SELECTION_SORT.equals(algorithmName)) {
            sortingMadness.setStrategy(new SortingTimeDecorator(new SelectionSort()));
            Map<String, Object> localMap = sortingMadness.performSort(convertedData, (Comparator<Comparable<?>>) Comparator.naturalOrder());
            resultMap.put("sorted_list",localMap.get("list"));
            resultMap.put("selection_sort_time",localMap.get("time"));
        }
        //TODO error exception
        resultMap.remove("time");
        return resultMap;
    }
}
