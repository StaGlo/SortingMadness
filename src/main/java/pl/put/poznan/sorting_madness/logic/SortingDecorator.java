package pl.put.poznan.sorting_madness.logic;

import java.util.Comparator;
import java.util.List;
import java.util.Map;

/**
 * This interface represents the strategy pattern.
 * It is used to provide different sorting algorithms.
 */
public abstract class SortingDecorator implements SortingStrategy {

    /**
     * Sorts a list of comparable values using the sorting algorithm.
     * If a custom comparator is provided, it is used for sorting.
     *
     * @param <T>              The type of elements in the list, extending
     *                         Comparable.
     * @param data             The list of values to be sorted.
     * @param customComparator The comparator to be used for sorting if not null.
     * @return A SortingResponse object containing the sorted list.
     */
    public abstract <T extends Comparable<T>> SortingResponse sortValues(List<T> data, Comparator<T> customComparator);

    /**
     * Sorts a list of objects based on a specified field using the sorting
     * algorithm.
     * The sorting is performed according to the comparator provided.
     *
     * @param <T>              The type of the field's values, extending
     *                         Comparable.
     * @param data             The list of objects to be sorted.
     * @param customComparator The comparator to be used for sorting.
     * @param field            The field of the objects to sort by.
     * @return A SortingResponse object containing the sorted list.
     */
    public abstract <T extends  Comparable<T>> SortingResponse sortObjects(List<Map<String, Object>> data, Comparator<T> customComparator, String field);

}
