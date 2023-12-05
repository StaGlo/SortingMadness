package pl.put.poznan.sorting_madness.rest;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import pl.put.poznan.sorting_madness.exception.WrongAlgorithmException;
import pl.put.poznan.sorting_madness.logic.*;

import java.util.Comparator;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class SortingMadnessService<T extends Comparable<T>> {

    @NonNull
    private SortingMadness<T> sortingMadness;

    public List<T> sortNumbers(String algorithmAsString, List<T> data) throws WrongAlgorithmException {
        AlgorithmName algorithmName;

        try {
            algorithmName = AlgorithmName.valueOf(algorithmAsString);
        } catch (Exception e) {
            var errorMessage = String.format("Wrong algorithm name: %s", algorithmAsString);
            log.error(errorMessage);
            throw new WrongAlgorithmException(errorMessage);
        }

        if (AlgorithmName.BUBBLE_SORT.equals(algorithmName)) {
            sortingMadness.setStrategy(new SortingTimeDecorator<T>(new BubbleSort<>()));
        }
        if (AlgorithmName.SELECTION_SORT.equals(algorithmName)) {
            sortingMadness.setStrategy(new SortingTimeDecorator<T>(new SelectionSort<>()));
        }

        return sortingMadness.performSort(data, Comparator.naturalOrder());
    }

    /*public List<Float> sortNumbers(String algorithmAsString, List<Float> data) throws WrongAlgorithmException {
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

    public List<String> sortStrings(String algorithmAsString, List<String> data) throws WrongAlgorithmException {
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

        return stringSortingMadness.performSort(data, Comparator.naturalOrder());
    }*/


}
