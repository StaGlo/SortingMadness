package pl.put.poznan.sorting_madness.logic;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.put.poznan.sorting_madness.rest.SortingMadnessController;

import java.util.Comparator;
import java.util.List;

public class SortingTimeDecorator implements SortingStrategy {
    private final SortingStrategy originalStrategy;
    private static final Logger logger = LoggerFactory.getLogger(SortingMadnessController.class);

    public SortingTimeDecorator(SortingStrategy originalStrategy) {
        this.originalStrategy = originalStrategy;
    }

    @Override
    public List<Comparable<?>> sort(List<Comparable<?>> data, Comparator<Comparable<?>> customComparator) {
        long startTime = System.nanoTime();

        var sortedData = originalStrategy.sort(data, customComparator);

        long endTime = System.nanoTime();
        long duration = endTime - startTime;

        logger.info("Sorting took " + duration + " nanoseconds.");

        return sortedData;
    }
}