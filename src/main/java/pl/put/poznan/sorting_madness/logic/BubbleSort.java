package pl.put.poznan.sorting_madness.logic;

import java.util.List;

public class BubbleSort<T extends Comparable<T>> implements SortingStrategy<T> {
    @Override
    public List<T> sort(List<T> data, java.util.Comparator<T> customComparator) {
        int n = data.size();

        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                // Use the custom comparator if provided, otherwise use natural ordering
                if (customComparator != null) {
                    if (customComparator.compare(data.get(j), data.get(j + 1)) > 0) {
                        // Swap data[j] and data[j+1]
                        T temp = data.get(j);
                        data.set(j, data.get(j + 1));
                        data.set(j + 1, temp);
                    }
                } else {
                    if (data.get(j).compareTo(data.get(j + 1)) > 0) {
                        // Swap data[j] and data[j+1]
                        T temp = data.get(j);
                        data.set(j, data.get(j + 1));
                        data.set(j + 1, temp);
                    }
                }
            }
        }

        return data;
    }
}
