package pl.put.poznan.sorting_madness.logic;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// Concrete strategy: Selection Sort
public class SelectionSort implements SortingStrategy {
    @Override
    public Map<String,Object> sort(List<Comparable<?>> data, Comparator<Comparable<?>> customComparator) {
        int n = data.size();

        for (int i = 0; i < n - 1; i++) {
            int minIndex = i;
            for (int j = i + 1; j < n; j++) {
                // Use the custom comparator if provided, otherwise use natural ordering
                if (customComparator != null) {
                    if (customComparator.compare(data.get(j), data.get(minIndex)) < 0) {
                        minIndex = j;
                    }
                } /*else {
                    if (data.get(j).compareTo(data.get(minIndex)) < 0) {
                        minIndex = j;
                    }
                }*/
            }

            // Swap the found minimum element with the element at i
            Comparable<?> temp = data.get(minIndex);
            data.set(minIndex, data.get(i));
            data.set(i, temp);
        }
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("list", data);
        return resultMap;
    }
}