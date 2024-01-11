package pl.put.poznan.sorting_madness.sample;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class SampleSortingData {

    private static final List<Object> VALUES = List.of(3, 2, 1);
    private static final List<Map<String, Object>> OBJECTS = List.of(
            Map.of("a", 3, "b", 2, "c", 1),
            Map.of("a", 2, "b", 1, "c", 3),
            Map.of("a", 1, "b", 3, "c", 2)
    );

    public static List<Object> getValues() {
        return VALUES;
    }

    public static <T extends Comparable<T>> List<T> getValuesComparable() {
        return VALUES.stream().map(o -> (T) o).collect(Collectors.toList());
    }

    public static List<Map<String, Object>> getObjectsMaps() {
        return OBJECTS;
    }

    public static List<Object> getObjectsRaw() {
        return OBJECTS.stream().map(o -> (Object) o).collect(Collectors.toList());
    }
}
