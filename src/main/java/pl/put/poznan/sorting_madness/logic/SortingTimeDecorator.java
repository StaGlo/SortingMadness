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
    public Map<String, Object> sort(List<Comparable<?>> data, Comparator<Comparable<?>> customComparator) {
        long startTime = System.nanoTime();

        Map<String,Object> resultMap = originalStrategy.sort(data, customComparator);

        long endTime = System.nanoTime();
        long duration = endTime - startTime;

        logger.info("Sorting took " + duration + " nanoseconds.");
        resultMap.put("time",duration);
        return resultMap;
    }
}