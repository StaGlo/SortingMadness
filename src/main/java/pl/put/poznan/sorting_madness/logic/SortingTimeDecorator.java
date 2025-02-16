package pl.put.poznan.sorting_madness.logic;

import lombok.extern.slf4j.Slf4j;

import java.util.Comparator;
import java.util.List;
import java.util.Map;

/**
 * A decorator for SortingStrategy that adds timing functionality.
 * It measures the time taken to perform sorting operations.
 */


@Slf4j
public class SortingTimeDecorator extends SortingDecorator {
    /**
     * The original sorting strategy to be decorated with timing functionality.
     */
    private final SortingStrategy originalStrategy;

    /**
     * Constructor that accepts a SortingStrategy object.
     *
     * @param originalStrategy The original sorting strategy to be decorated with timing functionality.
     */
    public SortingTimeDecorator(SortingStrategy originalStrategy) {
        this.originalStrategy = originalStrategy;
    }

    /**
     * Sorts a list of comparable values using the original strategy and measures the time taken to sort.
     *
     * @param <T>              The type of elements in the list, extending Comparable.
     * @param data             The list of values to be sorted.
     * @param customComparator The comparator to be used for sorting.
     * @return A SortingResponse object containing the sorted data and the time taken to sort.
     */
    @Override
    public <T extends Comparable<T>> SortingResponse sortValues(List<T> data, Comparator<T> customComparator, Integer steps) {
        long startTime = System.nanoTime();
        var sortingResult = originalStrategy.sortValues(data, customComparator, steps);
        long endTime = System.nanoTime();
        long duration = endTime - startTime;

        log.info("Sorting took " + duration + " nanoseconds.");
        sortingResult.setTime(duration);
        return sortingResult;
    }

    /**
     * Sorts a list of objects based on a specified field using the original strategy and measures the time taken to sort.
     *
     * @param <T>              The type of elements in the list, extending Comparable.
     * @param data             The list of objects to be sorted.
     * @param customComparator The comparator to be used for sorting.
     * @param field            The field of the objects to sort by.
     * @return A SortingResponse object containing the sorted data and the time taken to sort.
     */
    @Override
    public <T extends Comparable<T>> SortingResponse sortObjects(List<Map<String, Object>> data, Comparator<T> customComparator, String field, Integer steps) {
        long startTime = System.nanoTime();
        var sortingResult = originalStrategy.sortObjects(data, customComparator, field, steps);
        long endTime = System.nanoTime();
        long duration = endTime - startTime;

        log.info("Sorting took " + duration + " nanoseconds.");
        sortingResult.setTime(duration);
        return sortingResult;
    }
}
