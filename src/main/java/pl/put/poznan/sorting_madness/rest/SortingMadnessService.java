package pl.put.poznan.sorting_madness.rest;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import pl.put.poznan.sorting_madness.exception.WrongAlgorithmException;
import pl.put.poznan.sorting_madness.logic.*;

import java.util.Comparator;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class SortingMadnessService {

    @Qualifier("floatSortingMadness")
    @NonNull
    private final SortingMadness<Float> floatSortingMadness;

    @Qualifier("stringSortingMadness")
    @NonNull
    private SortingMadness<String> stringSortingMadness;

    public List<Float> sortNumbers(String algorithmAsString, List<Float> data) throws WrongAlgorithmException {
        AlgorithmName algorithmName;

        try {
            algorithmName = AlgorithmName.valueOf(algorithmAsString);
        } catch (Exception e) {
            var errorMessage = String.format("Wrong algorithm name: %s", algorithmAsString);
            log.error(errorMessage);
            throw new WrongAlgorithmException(errorMessage);
        }

        if (AlgorithmName.BUBBLE_SORT.equals(algorithmName)) {
            floatSortingMadness.setStrategy(new SortingTimeDecorator<Float>(new BubbleSort<>()));
        }
        if (AlgorithmName.SELECTION_SORT.equals(algorithmName)) {
            floatSortingMadness.setStrategy(new SortingTimeDecorator<Float>(new SelectionSort<>()));
        }

        return floatSortingMadness.performSort(data, Comparator.naturalOrder());
    }
}
