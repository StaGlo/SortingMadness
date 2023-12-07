package pl.put.poznan.sorting_madness.logic;

import java.util.Comparator;
import java.util.List;

// Strategy interface
public interface SortingStrategy {
    SortingResponse sort(List<Comparable<?>> data, Comparator<Comparable<?>> customComparator);
}
