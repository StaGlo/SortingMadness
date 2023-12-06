package pl.put.poznan.sorting_madness.logic;

import java.util.Comparator;
import java.util.List;

// Strategy interface
public interface SortingStrategy {
    List<Comparable<?>> sort(List<Comparable<?>> data, Comparator<Comparable<?>> customComparator);
}
