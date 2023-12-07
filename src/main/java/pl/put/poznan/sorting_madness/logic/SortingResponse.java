package pl.put.poznan.sorting_madness.logic;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@Getter
@Setter
@Builder
public class SortingResponse {
    List<Comparable<?>> sortedList;
    AlgorithmName algorithmName;
    Long time;
}
