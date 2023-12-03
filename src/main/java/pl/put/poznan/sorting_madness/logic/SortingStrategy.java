package pl.put.poznan.sorting_madness.logic;
import java.util.Comparator;
import java.util.List;

// Strategy interface
public interface SortingStrategy<T extends Comparable<T>> {
    List<T> sort(List<T> data, Comparator<T> customComparator);
}
