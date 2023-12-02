package pl.put.poznan.sorting_madness.logic;

import java.util.Collections;
import java.util.List;
import java.util.Comparator;

/**
 * This is just an example to show that the logic should be outside the REST service.
 */
public class SortingMadness<T extends Comparable<T>> {
    private SortingStrategy<T> sortingStrategy;

    public SortingMadness(){
        this.sortingStrategy=new SelectionSort<>();
    }
    public SortingMadness(SortingStrategy<T> sortingStrategy){
        this.sortingStrategy = sortingStrategy;
    }
    public List<T> performSort(List<T> data, Comparator<T> customComparator) {
        return sortingStrategy.sort(data,customComparator);
    }
    public List<Integer> quickSort(List<Integer> dataList){
        Collections.sort(dataList);
        return dataList;
    }

    public void setStrategy(SortingStrategy<T> sortingStrategy) {
        this.sortingStrategy = sortingStrategy;
    }
}
