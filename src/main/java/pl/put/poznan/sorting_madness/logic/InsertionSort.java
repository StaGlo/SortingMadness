package pl.put.poznan.sorting_madness.logic;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Implementation of the Insertion Sort algorithm.
 * This class provides methods to sort lists of comparable values or objects
 * based on a specified field.
 */
public class InsertionSort implements SortingStrategy {

    /**
     * Sorts a list of comparable values using the Insertion Sort algorithm.
     * If a custom comparator is provided, it is used for sorting.
     *
     * @param <T>              The type of elements in the list, extending
     *                         Comparable.
     * @param data             The list of values to be sorted.
     * @param customComparator The comparator to be used for sorting if not null.
     * @return A SortingResponse object containing the sorted list.
     */
    @Override
    public <T extends Comparable<T>> SortingResponse sortValues(List<T> data, Comparator<T> customComparator) {
        int n = data.size();
        for (int i = 1; i < n; i++) {
            if (customComparator != null) {
                var temp = data.get(i);
                int j = i - 1;
                while (j >= 0 && customComparator.compare(data.get(j), temp) > 0) {
                    data.set(j + 1, data.get(j));
                    j = j - 1;
                }
                data.set(j + 1, temp);
            }
        }
        return SortingResponse.builder().sortedList(data.stream().map(o -> (Object) o).collect(Collectors.toList())).build();
    }

    /**
     * Sorts a list of objects based on a specified field using the Insertion
     * Sort algorithm.
     * The sorting is performed according to the comparator provided.
     *
     * @param <T>              The type of the field's values, extending
     *                         Comparable.
     * @param data             The list of objects to be sorted.
     * @param customComparator The comparator to be used for sorting.
     * @param field            The field of the objects to sort by.
     * @return A SortingResponse object containing the sorted list.
     */
    @Override
    public <T extends Comparable<T>> SortingResponse sortObjects(List<Map<String, Object>> data, Comparator<T> customComparator, String field) {
        int n = data.size();
        for (int i = 1; i < n; i++) {
            if (customComparator != null) {
                var temp = data.get(i);
                int j = i - 1;
                while (j >= 0 && customComparator.compare((T) data.get(j).get(field), (T) temp.get(field)) > 0) {
                    data.set(j + 1, data.get(j));
                    j = j - 1;
                }
                data.set(j + 1, temp);
            }
        }
        return SortingResponse.builder().sortedList(data.stream().map(o -> (Object) o).collect(Collectors.toList())).build();
    }
}
