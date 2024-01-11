package pl.put.poznan.sorting_madness.logic;

import org.springframework.stereotype.Component;

import java.util.Comparator;
import java.util.List;
import java.util.Map;

/**
 * Component responsible for executing sorting operations.
 * Utilizes the Strategy design pattern to facilitate the use of different sorting algorithms.
 */
@Component
public class SortingMadness {

    /**
     * Sorting strategy (algorithm) used for sorting operations.
     */
    private SortingStrategy sortingStrategy;

    /**
     * Comparator used to determine the sorting order.
     */
    private SortingOrder sortingOrder;

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
     * Sorts a list of comparable values using the specified sorting strategy.
     *
     * @param <T>  Type of elements in the list, extending Comparable.
     * @param data The list of values to be sorted.
     * @return A SortingResponse object containing the sorted data.
     */
    public <T extends Comparable<T>> SortingResponse performSortValues(List<T> data, Integer steps) {
        return sortingStrategy.sortValues(data, this.sortingOrder.getComparator(), steps);
    }

    /**
     * Sorts a list of objects based on a specified field using the specified sorting strategy.
     *
     * @param <T>   Type of elements in the list, extending Comparable.
     * @param data  The list of objects to be sorted.
     * @param field The field of the objects to sort by.
     * @return A SortingResponse object containing the sorted data.
     */
    public <T extends Comparable<T>> SortingResponse performSortObjects(List<Map<String, Object>> data, String field, Integer steps) {
        //noinspection unchecked
        return sortingStrategy.sortObjects(data, (Comparator<T>) sortingOrder.getComparator(), field, steps);
    }

    /**
     * Sets the sorting strategy to be used for sorting operations.
     *
     * @param sortingStrategy The sorting strategy to set.
     */
    public void setStrategy(SortingStrategy sortingStrategy) {
        this.sortingStrategy = sortingStrategy;
    }

    /**
     * Sets the sorting order to be used for sorting operations.
     *
     * @param sortingOrder The sorting order to set.
     */
    public void setOrder(SortingOrder sortingOrder) {
        this.sortingOrder = sortingOrder;
    }

}
