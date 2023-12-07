package pl.put.poznan.sorting_madness.logic;

import java.util.Comparator;
import java.util.List;
import java.util.Map;

// Strategy interface
public interface SortingStrategy {
    SortingResponse sortValues(List<Comparable<?>> data, Comparator<Comparable<?>> customComparator);

    SortingResponse sortObjects(List<Map<String, Object>> data, Comparator<Comparable<?>> customComparator, String field);
}
