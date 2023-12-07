package pl.put.poznan.sorting_madness.logic;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

// Concrete strategy: Selection Sort
public class SelectionSort implements SortingStrategy {
    @Override
    public SortingResponse sortValues(List<Comparable<?>> data, Comparator<Comparable<?>> customComparator) {
        int n = data.size();

        for (int i = 0; i < n - 1; i++) {
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

    @Override
    public SortingResponse sortObjects(List<Map<String, Object>> data, Comparator<Comparable<?>> customComparator, String field) {
        int n = data.size();

        for (int i = 0; i < n - 1; i++) {
            int minIndex = i;
            for (int j = i + 1; j < n; j++) {
                var value1 = (Comparable<?>) data.get(j).get(field);
                var value2 = (Comparable<?>) data.get(minIndex).get(field);
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