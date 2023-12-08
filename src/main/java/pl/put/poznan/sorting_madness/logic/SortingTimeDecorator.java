package pl.put.poznan.sorting_madness.logic;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.put.poznan.sorting_madness.rest.SortingMadnessController;

import java.util.Comparator;
import java.util.List;
import java.util.Map;

public class SortingTimeDecorator implements SortingStrategy {
    private final SortingStrategy originalStrategy;
    private static final Logger logger = LoggerFactory.getLogger(SortingMadnessController.class);

    public SortingTimeDecorator(SortingStrategy originalStrategy) {
        this.originalStrategy = originalStrategy;
    }

    @Override
    public <T extends Comparable<T>> SortingResponse sortValues(List<T> data, Comparator<T> customComparator) {
        long startTime = System.nanoTime();

        var sortingResult = originalStrategy.sortValues(data, customComparator);

        long endTime = System.nanoTime();
        long duration = endTime - startTime;

        logger.info("Sorting took " + duration + " nanoseconds.");
        sortingResult.setTime(duration);
        return sortingResult;
    }

    @Override
    public <T extends Comparable<T>> SortingResponse sortObjects(List<Map<String, Object>> data, Comparator<T> customComparator, String field) {
        long startTime = System.nanoTime();

        var sortingResult = originalStrategy.sortObjects(data, customComparator, field);

        long endTime = System.nanoTime();
        long duration = endTime - startTime;

        logger.info("Sorting took " + duration + " nanoseconds.");
        sortingResult.setTime(duration);
        return sortingResult;
    }
}