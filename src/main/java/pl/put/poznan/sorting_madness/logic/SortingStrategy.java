package pl.put.poznan.sorting_madness.logic;

import java.util.Comparator;
import java.util.List;
import java.util.Map;

// Strategy interface
public interface SortingStrategy {
    Map<String, Object> sort(List<Comparable<?>> data, Comparator<Comparable<?>> customComparator);
}
