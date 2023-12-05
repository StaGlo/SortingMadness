package pl.put.poznan.sorting_madness.logic;

import org.springframework.stereotype.Component;

import java.util.Comparator;
import java.util.List;

/**
 * This is just an example to show that the logic should be outside the REST service.
 */

@Component
public class SortingMadness<T extends Comparable<T>> {
    private SortingStrategy<T> sortingStrategy;

    public SortingMadness() {
        this.sortingStrategy = new SelectionSort<>();
    }

    public SortingMadness(SortingStrategy<T> sortingStrategy) {
        this.sortingStrategy = sortingStrategy;
    }

    public List<T> performSort(List<T> data, Comparator<T> customComparator) {
        return sortingStrategy.sort(data, customComparator);
    }

    public void setStrategy(SortingStrategy<T> sortingStrategy) {
        this.sortingStrategy = sortingStrategy;
    }
}
