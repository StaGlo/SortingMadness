package pl.put.poznan.sorting_madness.logic;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class InsertionSort implements SortingStrategy {
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
