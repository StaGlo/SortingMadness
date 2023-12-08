package pl.put.poznan.sorting_madness.logic;

import org.springframework.stereotype.Component;

import java.util.Comparator;
import java.util.List;
import java.util.Map;

/**
 * This component is responsible for executing sorting operations.
 * It utilizes the Strategy design pattern to allow different sorting algorithms to be used.
 */
@Component
public class SortingMadness {

    /**
     * The sorting strategy (algorithm) to be used for sorting operations.
     */
    private SortingStrategy sortingStrategy;

    /**
     * Default constructor that initializes the sorting strategy to SelectionSort.
     */
    public SortingMadness() {
        this.sortingStrategy = new SelectionSort();
    }

    /**
     * Constructor to set a specific sorting strategy.
     *
     * @param sortingStrategy The sorting strategy to be used.
     */
    public SortingMadness(SortingStrategy sortingStrategy) {
        this.sortingStrategy = sortingStrategy;
    }

    /**
     * Performs sorting on a list of comparable values using the set sorting strategy.
     *
     * @param <T>              The type of elements in the list, extending Comparable.
     * @param data             The list of values to be sorted.
     * @param customComparator The comparator to be used for sorting.
     * @return                 A SortingResponse object containing the sorted data and the time taken to sort.
     */
    public <T extends Comparable<T>> SortingResponse performSortValues(List<T> data, Comparator<T> customComparator) {
        return sortingStrategy.sortValues(data, customComparator);
    }

    /**
     * Performs sorting on a list of objects based on a specified field using the set sorting strategy.
     *
     * @param <T>              The type of elements in the list, extending Comparable.
     * @param data             The list of objects to be sorted.
     * @param customComparator The comparator to be used for sorting.
     * @param field            The field of the objects to sort by.
     * @return                 A SortingResponse object containing the sorted data and the time taken to sort.
     */
    public <T extends Comparable<T>> SortingResponse performSortObjects(List<Map<String, Object>> data, Comparator<T> customComparator, String field) {
        return sortingStrategy.sortObjects(data, customComparator, field);
    }

    /**
     * Sets the sorting strategy to be used for sorting operations.
     *
     * @param sortingStrategy The sorting strategy to set.
     */
    public void setStrategy(SortingStrategy sortingStrategy) {
        this.sortingStrategy = sortingStrategy;
    }
}
