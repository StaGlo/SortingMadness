package pl.put.poznan.sorting_madness.logic;

import java.util.Comparator;

public enum SortingOrder {

    ASCENDING {
        @Override
        public <T extends Comparable<T>> Comparator<T> getComparator() {
            return Comparator.naturalOrder();
        }
    },
    DESCENDING {
        @Override
        public <T extends Comparable<T>> Comparator<T> getComparator() {
            return Comparator.reverseOrder();
        }
    };

    public abstract <T extends Comparable<T>> Comparator<T> getComparator();
}
