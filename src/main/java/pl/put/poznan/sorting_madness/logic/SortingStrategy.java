package pl.put.poznan.sorting_madness.logic;

import java.util.Comparator;
import java.util.List;
import java.util.Map;

// Strategy interface
public interface SortingStrategy {
    <T extends Comparable<T>> SortingResponse sortValues(List<T> data, Comparator<T> customComparator);

    <T extends Comparable<T>> SortingResponse sortObjects(List<Map<String, Object>> data, Comparator<T> customComparator, String field);
}
