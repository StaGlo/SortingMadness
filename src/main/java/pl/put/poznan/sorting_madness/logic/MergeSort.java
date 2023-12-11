package pl.put.poznan.sorting_madness.logic;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class MergeSort implements SortingStrategy {
    @Override
    public <T extends Comparable<T>> SortingResponse sortValues(List<T> data, Comparator<T> customComparator) {
        int n = data.size();
        mSortVal(data, 0, n - 1, customComparator);
        return SortingResponse.builder().sortedList(data.stream().map(o -> (Object) o).collect(Collectors.toList())).build();
    }

    public <T extends Comparable<T>> SortingResponse sortObjects(List<Map<String, Object>> data, Comparator<T> customComparator, String field) {
        int n = data.size();
        mSortObj(data, 0, n - 1, customComparator, field);
        return SortingResponse.builder().sortedList(data.stream().map(o -> (Object) o).collect(Collectors.toList())).build();
    }

    private <T extends Comparable<T>> void mSortVal(List<T> data, int low, int high, Comparator<T> customComparator)
    {
        if (low < high) {
            int mid = low + (high - low) / 2;
            mSortVal(data, low, mid, customComparator);
            mSortVal(data, mid + 1, high, customComparator);
            mergeVal(data, low, mid, high, customComparator);
        }
    }

    private  <T extends Comparable<T>> void mSortObj(List<Map<String, Object>> data, int low, int high, Comparator<T> customComparator, String field)
    {
        if (low < high) {
            int mid = low + (high - low) / 2;
            mSortObj(data, low, mid, customComparator, field);
            mSortObj(data, mid + 1, high, customComparator, field);
            mergeObj(data, low, mid, high, customComparator, field);
        }
    }

    private <T extends Comparable<T>> void mergeVal(List<T> data, int low, int mid, int high, Comparator<T> customComparator)
    {
        int n1 = mid - low + 1;
        int n2 = high - mid;

        List<T> left = new ArrayList<>(data.subList(low, mid + 1));
        List<T> right = new ArrayList<>(data.subList(mid + 1, high + 1));

        int i = 0, j = 0;

        int k = low;
        while (i < n1 && j < n2) {
            if (customComparator.compare(left.get(i), right.get(j)) <= 0) {
                data.set(k, left.get(i));
                i++;
            }
            else {
                data.set(k, right.get(j));
                j++;
            }
            k++;
        }

        while (i < n1) {
            data.set(k, left.get(i));
            i++;
            k++;
        }

        while (j < n2) {
            data.set(k, right.get(j));
            j++;
            k++;
        }
    }

    private <T extends Comparable<T>> void mergeObj(List<Map<String, Object>> data, int low, int mid, int high, Comparator<T> customComparator, String field)
    {
        int n1 = mid - low + 1;
        int n2 = high - mid;

        List<Map<String, Object>> left = new ArrayList<>(data.subList(low, mid + 1));
        List<Map<String, Object>> right = new ArrayList<>(data.subList(mid + 1, high + 1));

        int i = 0, j = 0;

        int k = low;
        while (i < n1 && j < n2) {
            if (customComparator.compare((T) left.get(i).get(field), (T) right.get(j).get(field)) <= 0) {
                data.set(k, left.get(i));
                i++;
            }
            else {
                data.set(k, right.get(j));
                j++;
            }
            k++;
        }

        while (i < n1) {
            data.set(k, left.get(i));
            i++;
            k++;
        }

        while (j < n2) {
            data.set(k, right.get(j));
            j++;
            k++;
        }
    }
}
