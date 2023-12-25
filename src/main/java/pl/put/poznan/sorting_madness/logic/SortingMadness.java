package pl.put.poznan.sorting_madness.logic;

import org.springframework.stereotype.Component;

import java.util.Comparator;
import java.util.List;
import java.util.Map;

/**
 * This component is responsible for executing sorting operations.
 * It utilizes the Strategy design pattern to allow different sorting algorithms to be used.
 */
@Component
public class SortingMadness {

    /**
     * The sorting strategy (algorithm) to be used for sorting operations.
     */
    private SortingStrategy sortingStrategy;
    private Comparator sortingOrder;

    /**
     * Default constructor that initializes the sorting strategy to SelectionSort.
     */
    public SortingMadness() {
        this.sortingStrategy = new SelectionSort();
    }

    /**
     * Constructor to set a specific sorting strategy.
     *
     * @param sortingStrategy The sorting strategy to be used.
     */
    public SortingMadness(SortingStrategy sortingStrategy) {
        this.sortingStrategy = sortingStrategy;
    }

    public <T extends Comparable<T>> SortingResponse performSortValues(List<T> data) {
        return sortingStrategy.sortValues(data, this.sortingOrder);
    }

    public <T extends Comparable<T>> SortingResponse performSortObjects(List<Map<String, Object>> data, String field) {
        return sortingStrategy.sortObjects(data, this.sortingOrder, field);
    }

    /**
     * Sets the sorting strategy to be used for sorting operations.
     *
     * @param sortingStrategy The sorting strategy to set.
     */
    public void setStrategy(SortingStrategy sortingStrategy) {
        this.sortingStrategy = sortingStrategy;
    }

    public void setOrder(SortingOrder sortingOrder) {
        if (sortingOrder == SortingOrder.ASCENDING) {
            this.sortingOrder = Comparator.naturalOrder();
        } else if (sortingOrder == SortingOrder.DESCENDING) {
            this.sortingOrder = Comparator.reverseOrder();
        }
    }
}
