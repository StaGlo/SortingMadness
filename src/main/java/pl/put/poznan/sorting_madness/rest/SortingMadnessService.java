package pl.put.poznan.sorting_madness.rest;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import pl.put.poznan.sorting_madness.exception.WrongParameterException;
import pl.put.poznan.sorting_madness.logic.*;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Service class for performing sorting operations using various algorithms.
 * This class provides methods to sort lists of values or objects based on specified criteria.
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class SortingMadnessService {

    /**
     * The instance of SortingMadness used by the SortingMadnessService.
     */
    @NonNull
    private SortingMadness sortingMadness;

    /**
     * Sorts a list of values using the specified sorting algorithm.
     * Validates the algorithm name and data before performing the sorting operation.
     *
     * @param <T>               The type of elements in the list, extending Comparable.
     * @param algorithmAsString The name of the sorting algorithm to be used.
     * @param data              The list of values to be sorted.
     * @return A SortingResponse object containing the sorted data and the time taken to sort.
     * @throws WrongParameterException If the algorithm name is invalid, the data list is empty, items are not of the same type, or items are not comparable.
     */
    public <T extends Comparable<T>> List<SortingResponse> sortValues(String algorithmAsString, List<Object> data)
            throws WrongParameterException {
        validateListEmpty(data);
        validateInputSameType(data);
        validateInputComparable(data);

        var algorithmList = algorithmAsString.split(",");

        List<SortingResponse> finalResult = new ArrayList<>();
        for(String algorithmListElement: algorithmList) {
            validateAlgorithmName(algorithmListElement);
            var algorithmName = AlgorithmName.valueOf(algorithmListElement);
            if (algorithmName.equals(AlgorithmName.COUNTING_SORT)) {
                validateCountingSortInput(data);
            }

            List<T> convertedData = data.stream().map(o -> (T) o).collect(Collectors.toList());

            switch (algorithmName) {
                case BUBBLE_SORT:
                    sortingMadness.setStrategy(new SortingTimeDecorator(new BubbleSort()));
                    break;
                case SELECTION_SORT:
                    sortingMadness.setStrategy(new SortingTimeDecorator(new SelectionSort()));
                    break;
                case INSERTION_SORT:
                    sortingMadness.setStrategy(new SortingTimeDecorator(new InsertionSort()));
                    break;
                case QUICK_SORT:
                    sortingMadness.setStrategy(new SortingTimeDecorator(new QuickSort()));
                    break;
                case MERGE_SORT:
                    sortingMadness.setStrategy(new SortingTimeDecorator(new MergeSort()));
                    break;
                case COUNTING_SORT:
                    sortingMadness.setStrategy(new SortingTimeDecorator(new CountingSort()));
                    break;
            }
            var sortingResult = sortingMadness.performSortValues(convertedData, Comparator.naturalOrder());
            sortingResult.setAlgorithmName(algorithmName);
            finalResult.add(sortingResult);
        }
        return finalResult;
    }

    /**
     * Sorts a list of objects based on a specified field using the specified algorithm.
     * Validates the algorithm name, data, and field before performing the sorting.
     *
     * @param <T>               The type of elements in the list, extending Comparable.
     * @param algorithmAsString The name of the sorting algorithm to be used.
     * @param data              The list of objects to be sorted.
     * @param field             The field of the objects to sort by.
     * @return A SortingResponse object containing the sorted data and the time taken to sort.
     * @throws WrongParameterException If the algorithm name is invalid, the data list is empty, field is not present in all objects, items are not of the same type, or items are not comparable.
     */
    public <T extends Comparable<T>> List<SortingResponse> sortObjects(String algorithmAsString,
                                                                        List<Map<String, Object>> data, String field) throws WrongParameterException {
        validateListEmpty(data);
        checkFieldPresence(data, field);

        var values = data.stream().map(m -> m.get(field)).collect(Collectors.toList());
        validateInputSameType(values);
        validateInputComparable(values);

        var algorithmList = algorithmAsString.split(",");
        List<SortingResponse> finalResult = new ArrayList<>();

        for(String algorithmListElement: algorithmList) {
            validateAlgorithmName(algorithmListElement);
            var algorithmName = AlgorithmName.valueOf(algorithmListElement);
            if (algorithmName.equals(AlgorithmName.COUNTING_SORT)) {
                validateCountingSortInput(values);
            }
            Comparator<T> comparator = Comparator.naturalOrder();

            switch (algorithmName) {
                case BUBBLE_SORT:
                    sortingMadness.setStrategy(new SortingTimeDecorator(new BubbleSort()));
                    break;
                case SELECTION_SORT:
                    sortingMadness.setStrategy(new SortingTimeDecorator(new SelectionSort()));
                    break;
                case INSERTION_SORT:
                    sortingMadness.setStrategy(new SortingTimeDecorator(new InsertionSort()));
                    break;
                case QUICK_SORT:
                    sortingMadness.setStrategy(new SortingTimeDecorator(new QuickSort()));
                    break;
                case MERGE_SORT:
                    sortingMadness.setStrategy(new SortingTimeDecorator(new MergeSort()));
                    break;
                case COUNTING_SORT:
                    sortingMadness.setStrategy(new SortingTimeDecorator(new CountingSort()));
                    break;
            }
            var sortingResult = sortingMadness.performSortObjects(data, comparator, field);
            sortingResult.setAlgorithmName(algorithmName);
            finalResult.add(sortingResult);
        }
        return finalResult;
    }

    /**
     * Validates if all objects in the list contain the specified field.
     *
     * @param data  The list of maps representing the objects.
     * @param field The field to check for in each object.
     * @throws WrongParameterException If not all objects contain the specified field.
     */
    private void checkFieldPresence(List<Map<String, Object>> data, String field) throws WrongParameterException {
        boolean allContainField = data.stream().allMatch(map -> map.containsKey(field));
        if (!allContainField) {
            throw new WrongParameterException(String.format("Not all objects contain the field '%s'", field));
        }
    }

    /**
     * Validates the algorithm name against the available sorting algorithms.
     *
     * @param algorithmAsString The name of the algorithm to validate.
     * @throws WrongParameterException If the algorithm name does not match any available algorithms.
     */
    private void validateAlgorithmName(String algorithmAsString) throws WrongParameterException {
        if ("".equals(algorithmAsString)) {
            throw new WrongParameterException("The algorithm name cannot be empty");
        }
        if (Arrays.stream(AlgorithmName.values()).noneMatch(value -> value.name().equals(algorithmAsString))) {
            throw new WrongParameterException(String.format("No such algorithm exists: '%s'", algorithmAsString));
        }
    }

    /**
     * Validates if all elements in the list are comparable.
     *
     * @param data The list of objects to be validated.
     * @throws WrongParameterException If any element in the list is not comparable.
     */
    private void validateInputComparable(List<Object> data) throws WrongParameterException {
        if (!data.stream().allMatch(value -> value instanceof Comparable<?>)) {
            throw new WrongParameterException("The items on the list are not comparable");
        }
    }

    /**
     * Validates if all elements in the list are of the same type.
     *
     * @param data The list of objects to be validated.
     * @throws WrongParameterException If elements in the list are of different types.
     */
    private void validateInputSameType(List<Object> data) throws WrongParameterException {
        var type = data.get(0).getClass();
        if (!data.stream().allMatch(o -> o.getClass().equals(type))) {
            throw new WrongParameterException("Not all items on the list are of the same type");
        }
    }

    /**
     * Validates if the provided list is not empty.
     *
     * @param data The list to be checked.
     * @throws WrongParameterException If the list is empty.
     */
    private void validateListEmpty(List<?> data) throws WrongParameterException {
        if (data.isEmpty()) {
            throw new WrongParameterException("The list cannot be empty");
        }
    }

    private void validateCountingSortInput(List<Object> data) throws WrongParameterException {
        if (!data.stream().allMatch(o -> (o instanceof Integer) && ((Integer) o > 0))) {
            throw new WrongParameterException("Counting sort is only applicable to positive integers");
        }
    }
}
