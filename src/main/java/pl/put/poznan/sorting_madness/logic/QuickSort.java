package pl.put.poznan.sorting_madness.logic;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class QuickSort implements SortingStrategy {
    @Override
    public <T extends Comparable<T>> SortingResponse sortValues(List<T> data, Comparator<T> customComparator) {
        int n = data.size();
        qSortVal(data, 0, n - 1, customComparator);
        return SortingResponse.builder().sortedList(data.stream().map(o -> (Object) o).collect(Collectors.toList())).build();
    }

    public <T extends Comparable<T>> SortingResponse sortObjects(List<Map<String, Object>> data, Comparator<T> customComparator, String field) {
        int n = data.size();
        qSortObj(data, 0, n - 1, customComparator, field);
        return SortingResponse.builder().sortedList(data.stream().map(o -> (Object) o).collect(Collectors.toList())).build();
    }

    static <T extends Comparable<T>> void qSortVal(List<T> data, int low, int high, Comparator<T> customComparator)
    {
        if (low < high) {
            int pi = partitionVal(data, low, high, customComparator);
            qSortVal(data, low, pi - 1, customComparator);
            qSortVal(data, pi + 1, high, customComparator);
        }
    }

    static <T extends Comparable<T>> void qSortObj(List<Map<String, Object>> data, int low, int high, Comparator<T> customComparator, String field)
    {
        if (low < high) {
            int pi = partitionObj(data, low, high, customComparator, field);
            qSortObj(data, low, pi - 1, customComparator, field);
            qSortObj(data, pi + 1, high, customComparator, field);
        }
    }

    static <T extends Comparable<T>> int  partitionVal(List<T> data, int low, int high, Comparator<T> customComparator)
    {
        var pivot = data.get(high);
        int i = (low - 1);
        for (int j = low; j <= high - 1; j++) {
            if (customComparator.compare(data.get(j), pivot) < 0) {
                i++;
                var temp = data.get(i);
                data.set(i, data.get(j));
                data.set(j, temp);
            }
        }
        var temp = data.get(i + 1);
        data.set(i + 1, data.get(high));
        data.set(high, temp);
        return (i + 1);
    }

    static <T extends Comparable<T>> int  partitionObj(List<Map<String, Object>> data, int low, int high, Comparator<T> customComparator, String field)
    {
        var pivot = data.get(high);
        int i = (low - 1);
        for (int j = low; j <= high - 1; j++) {
            if (customComparator.compare((T) data.get(j).get(field), (T) pivot.get(field)) < 0) {
                i++;
                var temp = data.get(i);
                data.set(i, data.get(j));
                data.set(j, temp);
            }
        }
        var temp = data.get(i + 1);
        data.set(i + 1, data.get(high));
        data.set(high, temp);
        return (i + 1);
    }
}
