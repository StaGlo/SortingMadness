package pl.put.poznan.sorting_madness.logic;

import org.springframework.stereotype.Component;

import java.util.Comparator;
import java.util.List;

/**
 * This is just an example to show that the logic should be outside the REST service.
 */

@Component
public class SortingMadness {
    private SortingStrategy sortingStrategy;

    public SortingMadness() {
        this.sortingStrategy = new SelectionSort();
    }

    public SortingMadness(SortingStrategy sortingStrategy) {
        this.sortingStrategy = sortingStrategy;
    }

    public List<Comparable<?>> performSort(List<Comparable<?>> data, Comparator<Comparable<?>> customComparator) {
        return sortingStrategy.sort(data, customComparator);
    }

    public void setStrategy(SortingStrategy sortingStrategy) {
        this.sortingStrategy = sortingStrategy;
    }
}
