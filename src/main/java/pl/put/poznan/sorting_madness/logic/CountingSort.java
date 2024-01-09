package pl.put.poznan.sorting_madness.logic;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Implementation of the Counting Sort algorithm.
 * This class provides methods to sort lists of comparable values or objects
 * based on a specified field.
 */
public class CountingSort implements SortingStrategy {

    /**
     * Sorts a list of comparable values using the Counting Sort algorithm.
     * If a custom comparator is provided, it is used for sorting.
     *
     * @param <T>              The type of elements in the list, extending
     *                         Comparable.
     * @param data             The list of values to be sorted.
     * @param customComparator The comparator to be used for sorting if not null.
     * @return A SortingResponse object containing the sorted list.
     */
    @Override
    public <T extends Comparable<T>> SortingResponse sortValues(List<T> data, Comparator<T> customComparator, Integer steps) {
        int n = data.size();
        int m = 0;
        for (T datum : data) {
            m = Math.max(m, (Integer) datum);
        }
        int[] count = new int[m + 1];

        for (int i = 0; i < n; i++) {
            count[(Integer) data.get(i)]++;
        }

        for (int i = 1; i <= m; i++) {
            count[i] += count[i - 1];
        }

        List<T> output = new ArrayList<>(data.subList(0, data.size()));

        for (int i = n - 1; i >= 0; i--) {
            output.set(count[(Integer) data.get(i)] - 1, data.get(i));
            count[(Integer) data.get(i)]--;
        }

        return SortingResponse.builder().sortedList(output.stream().map(o -> (Object) o).collect(Collectors.toList())).build();
    }

    /**
     * Sorts a list of objects based on a specified field using the Counting
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
    public <T extends Comparable<T>> SortingResponse sortObjects(List<Map<String, Object>> data,
                                                                 Comparator<T> customComparator, String field, Integer steps) {
        int n = data.size();
        int m = 0;
        for (Map<String, Object> datum : data) {
            m = Math.max(m, (Integer) datum.get(field));
        }
        int[] count = new int[m + 1];

        for (int i = 0; i < n; i++) {
            count[(Integer) data.get(i).get(field)]++;
        }

        for (int i = 1; i <= m; i++) {
            count[i] += count[i - 1];
        }

        List<Map<String, Object>> output = new ArrayList<>(data.subList(0, data.size()));

        for (int i = n - 1; i >= 0; i--) {
            output.set(count[(Integer) data.get(i).get(field)] - 1, data.get(i));
            count[(Integer) data.get(i).get(field)]--;
        }

        return SortingResponse.builder().sortedList(output.stream().map(o -> (Object) o).collect(Collectors.toList())).build();
    }
}
