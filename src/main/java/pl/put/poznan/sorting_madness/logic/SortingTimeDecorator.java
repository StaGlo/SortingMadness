package pl.put.poznan.sorting_madness.logic;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.put.poznan.sorting_madness.rest.SortingMadnessController;

import java.util.Comparator;
import java.util.List;

public class SortingTimeDecorator<T extends Comparable<T>> implements SortingStrategy<T> {
    private final SortingStrategy<T> originalStrategy;
    private static final Logger logger = LoggerFactory.getLogger(SortingMadnessController.class);

    public SortingTimeDecorator(SortingStrategy<T> originalStrategy) {
        this.originalStrategy = originalStrategy;
    }

    @Override
    public List<T> sort(List<T> data, Comparator<T> customComparator) {
        long startTime = System.nanoTime();

        // Delegate sorting to the original strategy
        List<T> sortedData = originalStrategy.sort(data, customComparator);

        long endTime = System.nanoTime();
        long duration = endTime - startTime;

        logger.info("Sorting took " + duration + " nanoseconds.");

        return sortedData;
    }
}