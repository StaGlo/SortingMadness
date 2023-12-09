package pl.put.poznan.sorting_madness.logic;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class CountingSort implements SortingStrategy {
    @Override
    public <T extends Comparable<T>> SortingResponse sortValues(List<T> data, Comparator<T> customComparator) {
        int n = data.size();
        int m = 0;
        for (int i = 0; i < n; i++) {
            m = Math.max(m, (Integer) data.get(i));
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

    public <T extends Comparable<T>> SortingResponse sortObjects(List<Map<String, Object>> data, Comparator<T> customComparator, String field) {

        return SortingResponse.builder().sortedList(data.stream().map(o -> (Object) o).collect(Collectors.toList())).build();
    }
}
