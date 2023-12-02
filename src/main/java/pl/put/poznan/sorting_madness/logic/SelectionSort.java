package pl.put.poznan.sorting_madness.logic;

import java.util.Comparator;
import java.util.List;

// Concrete strategy: Selection Sort
public class SelectionSort <T extends Comparable<T>> implements SortingStrategy<T> {
    @Override
    public List<T> sort(List<T> data, Comparator<T> customComparator) {
        int n = data.size();

        for (int i = 0; i < n - 1; i++) {
            int minIndex = i;
            for (int j = i + 1; j < n; j++) {
                // Use the custom comparator if provided, otherwise use natural ordering
                if (customComparator != null) {
                    if (customComparator.compare(data.get(j), data.get(minIndex)) < 0) {
                        minIndex = j;
                    }
                } else {
                    if (data.get(j).compareTo(data.get(minIndex)) < 0) {
                        minIndex = j;
                    }
                }
            }

            // Swap the found minimum element with the element at i
            T temp = data.get(minIndex);
            data.set(minIndex, data.get(i));
            data.set(i, temp);
        }
        return data;
    }
}