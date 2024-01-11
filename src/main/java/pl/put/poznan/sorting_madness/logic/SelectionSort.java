package pl.put.poznan.sorting_madness.logic;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Implementation of the Selection Sort algorithm.
 * This class provides methods to sort lists of comparable values or objects
 * based on a specified field.
 */
public class SelectionSort implements SortingStrategy {
    
    /**
     * Sorts a list of comparable values using the Selection Sort algorithm.
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
        int stepN;
        if (steps < 0) {
            stepN = data.size();
        } else {
            stepN = Math.min(data.size(),steps + 1);
        }

        for (int i = 0; i < stepN - 1; i++) {
            int minIndex = i;
            for (int j = i + 1; j < n; j++) {
                if (customComparator.compare(data.get(j), data.get(minIndex)) < 0) {
                    minIndex = j;
                }
            }
            var temp = data.get(minIndex);
            data.set(minIndex, data.get(i));
            data.set(i, temp);
        }
        return SortingResponse.builder().sortedList(data.stream().map(o -> (Object) o).collect(Collectors.toList())).build();
    }

    /**
     * Sorts a list of objects based on a specified field using the Selection
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
    public <T extends Comparable<T>> SortingResponse sortObjects(List<Map<String, Object>> data, Comparator<T> customComparator, String field, Integer steps) {
        int n = data.size();
        int stepN;
        if (steps < 0) {
            stepN = data.size();
        } else {
            stepN = Math.min(data.size(),steps + 1);
        }

        for (int i = 0; i < stepN - 1; i++) {
            int minIndex = i;
            for (int j = i + 1; j < n; j++) {
                var value1 = (T) data.get(j).get(field);
                var value2 = (T) data.get(minIndex).get(field);
                if (customComparator.compare(value1, value2) < 0) {
                    minIndex = j;
                }
            }
            var temp = data.get(minIndex);
            data.set(minIndex, data.get(i));
            data.set(i, temp);
        }
        return SortingResponse.builder().sortedList(data.stream().map(o -> (Object) o).collect(Collectors.toList())).build();
    }
}