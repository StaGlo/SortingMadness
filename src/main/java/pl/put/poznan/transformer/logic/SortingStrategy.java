package pl.put.poznan.transformer.logic;
import java.util.Comparator;
import java.util.List;

// Strategy interface
public interface SortingStrategy<T extends Comparable<T>> {
    List<T> sort(List<T> data, Comparator<T> customComparator);
}
