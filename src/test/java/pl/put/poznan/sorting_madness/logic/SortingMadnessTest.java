package pl.put.poznan.sorting_madness.logic;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import pl.put.poznan.sorting_madness.sample.SampleSortingData;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@SpringBootTest(classes = {SortingMadness.class})
class SortingMadnessTest {

    private List<Object> sortingDataValues;
    private List<? extends Comparable> sortingDataValuesComparable;
    private List<Map<String, Object>> sortingDataObjectsMaps;
    private List<Object> sortingDataObjectsRaw;
    private SortingStrategy sortingStrategy;

    @Autowired
    private SortingMadness sortingMadness;

    @BeforeEach
    private void setUp() {
        this.sortingDataValues = SampleSortingData.getValues();
        this.sortingDataValuesComparable = SampleSortingData.getValuesComparable();
        this.sortingDataObjectsMaps = SampleSortingData.getObjectsMaps();
        this.sortingDataObjectsRaw = SampleSortingData.getObjectsRaw();
    }

    private void initSoringMadness(Class<? extends SortingStrategy> strategy) {
        sortingStrategy = mock(strategy);
        sortingMadness = new SortingMadness(sortingStrategy);
        sortingMadness.setOrder(SortingOrder.ASCENDING);
    }

    @Test
    void testBubbleSort() {
        //given
        initSoringMadness(BubbleSort.class);
        var responseValues = new SortingResponse(sortingDataValues, AlgorithmName.BUBBLE_SORT, 0L);
        var responseObjects = new SortingResponse(sortingDataObjectsRaw, AlgorithmName.BUBBLE_SORT, 0L);

        //when
        when(sortingStrategy.sortValues(any(), any(), any())).thenReturn(responseValues);
        when(sortingStrategy.sortObjects(any(), any(), any(), any())).thenReturn(responseObjects);

        //then
        var sortingResponseValues = sortingMadness.performSortValues(sortingDataValuesComparable, 10000);
        var sortingResponseObjects = sortingMadness.performSortObjects(sortingDataObjectsMaps, "a", 10000);
        assertEquals(AlgorithmName.BUBBLE_SORT, sortingResponseValues.getAlgorithmName());
        assertEquals(AlgorithmName.BUBBLE_SORT, sortingResponseObjects.getAlgorithmName());
        verify(sortingStrategy, times(1)).sortValues(any(), any(), any());
    }

    @Test
    void testSelectionSort() {
        //given
        initSoringMadness(SelectionSort.class);
        var responseValues = new SortingResponse(sortingDataValues, AlgorithmName.SELECTION_SORT, 0L);
        var responseObjects = new SortingResponse(sortingDataObjectsRaw, AlgorithmName.SELECTION_SORT, 0L);

        //when
        when(sortingStrategy.sortValues(any(), any(), any())).thenReturn(responseValues);
        when(sortingStrategy.sortObjects(any(), any(), any(), any())).thenReturn(responseObjects);

        //then
        var sortingResponseValues = sortingMadness.performSortValues(sortingDataValuesComparable, 10000);
        var sortingResponseObjects = sortingMadness.performSortObjects(sortingDataObjectsMaps, "a", 10000);
        assertEquals(AlgorithmName.SELECTION_SORT, sortingResponseValues.getAlgorithmName());
        assertEquals(AlgorithmName.SELECTION_SORT, sortingResponseObjects.getAlgorithmName());
        verify(sortingStrategy, times(1)).sortValues(any(), any(), any());
    }

    @Test
    void testInsertionSort() {
        //given
        initSoringMadness(InsertionSort.class);
        var responseValues = new SortingResponse(sortingDataValues, AlgorithmName.INSERTION_SORT, 0L);
        var responseObjects = new SortingResponse(sortingDataObjectsRaw, AlgorithmName.INSERTION_SORT, 0L);

        //when
        when(sortingStrategy.sortValues(any(), any(), any())).thenReturn(responseValues);
        when(sortingStrategy.sortObjects(any(), any(), any(), any())).thenReturn(responseObjects);

        //then
        var sortingResponseValues = sortingMadness.performSortValues(sortingDataValuesComparable, 10000);
        var sortingResponseObjects = sortingMadness.performSortObjects(sortingDataObjectsMaps, "a", 10000);
        assertEquals(AlgorithmName.INSERTION_SORT, sortingResponseValues.getAlgorithmName());
        assertEquals(AlgorithmName.INSERTION_SORT, sortingResponseObjects.getAlgorithmName());
        verify(sortingStrategy, times(1)).sortValues(any(), any(), any());
    }

    @Test
    void testQuickSort() {
        //given
        initSoringMadness(QuickSort.class);
        var responseValues = new SortingResponse(sortingDataValues, AlgorithmName.QUICK_SORT, 0L);
        var responseObjects = new SortingResponse(sortingDataObjectsRaw, AlgorithmName.QUICK_SORT, 0L);

        //when
        when(sortingStrategy.sortValues(any(), any(), any())).thenReturn(responseValues);
        when(sortingStrategy.sortObjects(any(), any(), any(), any())).thenReturn(responseObjects);

        //then
        var sortingResponseValues = sortingMadness.performSortValues(sortingDataValuesComparable, 10000);
        var sortingResponseObjects = sortingMadness.performSortObjects(sortingDataObjectsMaps, "a", 10000);
        assertEquals(AlgorithmName.QUICK_SORT, sortingResponseValues.getAlgorithmName());
        assertEquals(AlgorithmName.QUICK_SORT, sortingResponseObjects.getAlgorithmName());
        verify(sortingStrategy, times(1)).sortValues(any(), any(), any());
    }

    @Test
    void testMergeSort() {
        //given
        initSoringMadness(MergeSort.class);
        var responseValues = new SortingResponse(sortingDataValues, AlgorithmName.MERGE_SORT, 0L);
        var responseObjects = new SortingResponse(sortingDataObjectsRaw, AlgorithmName.MERGE_SORT, 0L);

        //when
        when(sortingStrategy.sortValues(any(), any(), any())).thenReturn(responseValues);
        when(sortingStrategy.sortObjects(any(), any(), any(), any())).thenReturn(responseObjects);

        //then
        var sortingResponseValues = sortingMadness.performSortValues(sortingDataValuesComparable, 10000);
        var sortingResponseObjects = sortingMadness.performSortObjects(sortingDataObjectsMaps, "a", 10000);
        assertEquals(AlgorithmName.MERGE_SORT, sortingResponseValues.getAlgorithmName());
        assertEquals(AlgorithmName.MERGE_SORT, sortingResponseObjects.getAlgorithmName());
        verify(sortingStrategy, times(1)).sortValues(any(), any(), any());
    }

    @Test
    void testCountingSort() {
        //given
        initSoringMadness(MergeSort.class);
        var responseValues = new SortingResponse(sortingDataValues, AlgorithmName.COUNTING_SORT, 0L);
        var responseObjects = new SortingResponse(sortingDataObjectsRaw, AlgorithmName.COUNTING_SORT, 0L);

        //when
        when(sortingStrategy.sortValues(any(), any(), any())).thenReturn(responseValues);
        when(sortingStrategy.sortObjects(any(), any(), any(), any())).thenReturn(responseObjects);

        //then
        var sortingResponseValues = sortingMadness.performSortValues(sortingDataValuesComparable, 10000);
        var sortingResponseObjects = sortingMadness.performSortObjects(sortingDataObjectsMaps, "a", 10000);
        assertEquals(AlgorithmName.COUNTING_SORT, sortingResponseValues.getAlgorithmName());
        assertEquals(AlgorithmName.COUNTING_SORT, sortingResponseObjects.getAlgorithmName());
        verify(sortingStrategy, times(1)).sortValues(any(), any(), any());
    }
}