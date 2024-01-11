package pl.put.poznan.sorting_madness.logic;

import java.util.Comparator;
import java.util.List;
import java.util.Map;

/**
 * The {@code SortingStrategy} interface defines the methods for sorting collections.
 * Implementations of this interface provide specific sorting algorithms (like Bubble Sort or Quick Sort
 */
public interface SortingStrategy {

    /**
     * Sorts a list of data based on the natural ordering or a custom comparator.
     *
     * @param <T>              the type of elements in the list, which extends {@link Comparable}
     * @param data             the list of data to be sorted
     * @param customComparator the comparator to determine the order of the list.
     * @return {@link SortingResponse} containing the sorted list
     */
    <T extends Comparable<T>> SortingResponse sortValues(List<T> data, Comparator<T> customComparator, Integer steps);

    /**
     * Sorts a list of maps based on a specified field. The field's values are compared using
     * either their natural ordering or a custom comparator.
     *
     * @param <T>              the type of the field values in the maps, which extends {@link Comparable}
     * @param data             the list of maps to be sorted
     * @param customComparator the comparator to determine the order of sorting.
     * @return {@link SortingResponse} containing the sorted list of maps
     */
    <T extends Comparable<T>> SortingResponse sortObjects(List<Map<String, Object>> data, Comparator<T> customComparator, String field, Integer steps);
}
