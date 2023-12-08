package pl.put.poznan.sorting_madness.rest;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import pl.put.poznan.sorting_madness.exception.WrongParameterException;
import pl.put.poznan.sorting_madness.logic.*;

import java.util.Arrays;
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

    public <T extends Comparable<T>> SortingResponse sortValues(String algorithmAsString, List<Object> data)
            throws WrongParameterException {
        validateAlgorithmName(algorithmAsString);
        validateListEmpty(data);
        validateInputSameType(data);
        validateInputComparable(data);

        var algorithmName = AlgorithmName.valueOf(algorithmAsString);
        List<T> convertedData = data.stream().map(o -> (T) o).collect(Collectors.toList());

        if (AlgorithmName.BUBBLE_SORT.equals(algorithmName)) {
            sortingMadness.setStrategy(new SortingTimeDecorator(new BubbleSort()));
            var sortingResult = sortingMadness.performSortValues(convertedData, Comparator.naturalOrder());
            sortingResult.setAlgorithmName(AlgorithmName.BUBBLE_SORT);
            return sortingResult;
        }
        if (AlgorithmName.SELECTION_SORT.equals(algorithmName)) {
            sortingMadness.setStrategy(new SortingTimeDecorator(new SelectionSort()));
            var sortingResult = sortingMadness.performSortValues(convertedData, Comparator.naturalOrder());
            sortingResult.setAlgorithmName(AlgorithmName.SELECTION_SORT);
            return sortingResult;
        }
        return null;
    }

    public <T extends Comparable<T>> SortingResponse sortObjects(String algorithmAsString,
                                                                 List<Map<String, Object>> data, String field) throws WrongParameterException {
        validateAlgorithmName(algorithmAsString);
        validateListEmpty(data);
        checkFieldPresence(data, field);

        var values = data.stream().map(m -> m.get(field)).collect(Collectors.toList());
        validateInputSameType(values);
        validateInputComparable(values);

        var algorithmName = AlgorithmName.valueOf(algorithmAsString);

        if (AlgorithmName.BUBBLE_SORT.equals(algorithmName)) {
            sortingMadness.setStrategy(new SortingTimeDecorator(new BubbleSort()));
            Comparator<T> comparator = Comparator.naturalOrder();
            var sortingResult = sortingMadness.performSortObjects(data, comparator, field);
            sortingResult.setAlgorithmName(AlgorithmName.BUBBLE_SORT);
            return sortingResult;
        }
        if (AlgorithmName.SELECTION_SORT.equals(algorithmName)) {
            sortingMadness.setStrategy(new SortingTimeDecorator(new SelectionSort()));
            Comparator<T> comparator = Comparator.naturalOrder();
            var sortingResult = sortingMadness.performSortObjects(data, comparator, field);
            sortingResult.setAlgorithmName(AlgorithmName.SELECTION_SORT);
            return sortingResult;
        }
        return null;
    }

    private void checkFieldPresence(List<Map<String, Object>> data, String field) throws WrongParameterException {
        boolean allContainField = data.stream().allMatch(map -> map.containsKey(field));
        if (!allContainField) {
            throw new WrongParameterException(String.format("Not all objects contain the field '%s'", field));
        }
    }

    private void validateAlgorithmName(String algorithmAsString) throws WrongParameterException {
        if (Arrays.stream(AlgorithmName.values()).noneMatch(value -> value.name().equals(algorithmAsString))) {
            throw new WrongParameterException(String.format("No such algorithm exists: '%s'", algorithmAsString));
        }
    }

    private void validateInputComparable(List<Object> data) throws WrongParameterException {
        if (!data.stream().allMatch(value -> value instanceof Comparable<?>)) {
            throw new WrongParameterException("The items on the list are not comparable");
        }
    }

    private void validateInputSameType(List<Object> data) throws WrongParameterException {
        var type = data.get(0).getClass();
        if (!data.stream().allMatch(o -> o.getClass().equals(type))) {
            throw new WrongParameterException("Not all items on the list are of the same type");
        }
    }

    private void validateListEmpty(List<?> data) throws WrongParameterException {
        if (data.isEmpty()) {
            throw new WrongParameterException("The list cannot be empty");
        }
    }
}
