package pl.put.poznan.sorting_madness.rest;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import pl.put.poznan.sorting_madness.exception.WrongParameterException;
import pl.put.poznan.sorting_madness.logic.*;
import pl.put.poznan.sorting_madness.util.NameValidator;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class SortingMadnessService {

    @NonNull
    private SortingMadness sortingMadness;

    public SortingResponse sortValues(String algorithmAsString, String inputTypeAsString, List<Object> data)
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

        if (AlgorithmName.BUBBLE_SORT.equals(algorithmName)) {
            sortingMadness.setStrategy(new SortingTimeDecorator(new BubbleSort()));
            var sortingResult = sortingMadness.performSortValues(convertedData, (Comparator<Comparable<?>>) Comparator.naturalOrder());
            sortingResult.setAlgorithmName(AlgorithmName.BUBBLE_SORT);
            return sortingResult;
        }
        if (AlgorithmName.SELECTION_SORT.equals(algorithmName)) {
            sortingMadness.setStrategy(new SortingTimeDecorator(new SelectionSort()));
            var sortingResult = sortingMadness.performSortValues(convertedData, (Comparator<Comparable<?>>) Comparator.naturalOrder());
            sortingResult.setAlgorithmName(AlgorithmName.SELECTION_SORT);
            return sortingResult;
        }
        //TODO error exception
        return null;
    }

    public SortingResponse sortObjects(String algorithmAsString, String inputTypeAsString,
                                       List<Map<String, Object>> data, String field) throws WrongParameterException {
        var algorithmName = NameValidator.validateAlgorithmName(algorithmAsString);
        var inputType = NameValidator.validateInputType(inputTypeAsString);
        // TODO Field does not exist exception

        if (AlgorithmName.BUBBLE_SORT.equals(algorithmName)) {
            sortingMadness.setStrategy(new SortingTimeDecorator(new BubbleSort()));
            var sortingResult = sortingMadness.performSortObjects(data, (Comparator<Comparable<?>>) Comparator.naturalOrder(), field);
            sortingResult.setAlgorithmName(AlgorithmName.BUBBLE_SORT);
            return sortingResult;
        }
        if (AlgorithmName.SELECTION_SORT.equals(algorithmName)) {
            sortingMadness.setStrategy(new SortingTimeDecorator(new SelectionSort()));
            var sortingResult = sortingMadness.performSortObjects(data, (Comparator<Comparable<?>>) Comparator.naturalOrder(), field);
            sortingResult.setAlgorithmName(AlgorithmName.SELECTION_SORT);
            return sortingResult;
        }

        return null;
    }
}
