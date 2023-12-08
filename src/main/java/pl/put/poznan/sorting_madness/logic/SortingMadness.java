package pl.put.poznan.sorting_madness.logic;

import org.springframework.stereotype.Component;

import java.util.Comparator;
import java.util.List;
import java.util.Map;

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

    public <T extends Comparable<T>> SortingResponse performSortValues(List<T> data, Comparator<T> customComparator) {
        return sortingStrategy.sortValues(data, customComparator);
    }

    public <T extends Comparable<T>> SortingResponse performSortObjects(List<Map<String, Object>> data, Comparator<T> customComparator, String field) {
        return sortingStrategy.sortObjects(data, customComparator, field);
    }

    public void setStrategy(SortingStrategy sortingStrategy) {
        this.sortingStrategy = sortingStrategy;
    }
}
