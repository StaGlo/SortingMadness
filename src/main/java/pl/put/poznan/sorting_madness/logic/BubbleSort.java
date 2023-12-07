package pl.put.poznan.sorting_madness.logic;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class BubbleSort implements SortingStrategy {
    @Override
    public SortingResponse sortValues(List<Comparable<?>> data, java.util.Comparator<Comparable<?>> customComparator) {
        int n = data.size();

        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (customComparator != null) {
                    if (customComparator.compare(data.get(j), data.get(j + 1)) > 0) {
                        var temp = data.get(j);
                        data.set(j, data.get(j + 1));
                        data.set(j + 1, temp);
                    }
                }
            }
        }
        return SortingResponse.builder().sortedList(data.stream().map(o -> (Object) o).collect(Collectors.toList())).build();
    }

    @Override
    public SortingResponse sortObjects(List<Map<String, Object>> data, Comparator<Comparable<?>> customComparator, String field) {
        int n = data.size();

        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                var value1 = (Comparable<?>) data.get(j).get(field);
                var value2 = (Comparable<?>) data.get(j + 1).get(field);
                if (customComparator.compare(value1, value2) > 0) {
                    var temp = data.get(j);
                    data.set(j, data.get(j + 1));
                    data.set(j + 1, temp);
                }
            }
        }
        return SortingResponse.builder().sortedList(data.stream().map(o -> (Object) o).collect(Collectors.toList())).build();
    }
}
