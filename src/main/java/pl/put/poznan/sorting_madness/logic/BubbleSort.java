package pl.put.poznan.sorting_madness.logic;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BubbleSort implements SortingStrategy {
    @Override
    public Map<String,Object> sort(List<Comparable<?>> data, java.util.Comparator<Comparable<?>> customComparator) {
        int n = data.size();

        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                // Use the custom comparator if provided, otherwise use natural ordering
                if (customComparator != null) {
                    if (customComparator.compare(data.get(j), data.get(j + 1)) > 0) {
                        // Swap data[j] and data[j+1]
                        Comparable<?> temp = data.get(j);
                        data.set(j, data.get(j + 1));
                        data.set(j + 1, temp);
                    }
                } /*else {
                    if (data.get(j).compareTo(data.get(j + 1)) > 0) {
                        // Swap data[j] and data[j+1]
                        Comparable<?> temp = data.get(j);
                        data.set(j, data.get(j + 1));
                        data.set(j + 1, temp);
                    }
                }*/
            }
        }
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("list", data);
        return resultMap;
    }
}
