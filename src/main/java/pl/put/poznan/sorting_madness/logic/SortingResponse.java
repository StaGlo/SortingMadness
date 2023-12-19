package pl.put.poznan.sorting_madness.logic;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * This class represents the response of the sorting algorithm.
 * It contains the sorted list, the name of the algorithm used, and the time it took to sort the list.
 */
@AllArgsConstructor
@Getter
@Setter
@Builder
public class SortingResponse {
    /**
     * The sorted list.
     */
    List<Object> sortedList;
    /**
     * The name of the algorithm used.
     */
    AlgorithmName algorithmName;
    /**
     * The time it took to sort the list.
     */
    Long time;
}
