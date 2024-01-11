package pl.put.poznan.sorting_madness.rest;

import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import pl.put.poznan.sorting_madness.exception.WrongParameterException;
import pl.put.poznan.sorting_madness.logic.AlgorithmName;
import pl.put.poznan.sorting_madness.logic.SortingMadness;
import pl.put.poznan.sorting_madness.logic.SortingResponse;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest(classes = {SortingMadnessService.class, SortingMadness.class})
class SortingMadnessServiceTest {

    @Autowired
    private SortingMadnessService sortingMadnessService;

    @MockBean
    private SortingMadness sortingMadness;

    @Test
    void testSortEmptyList() {
        //given
        List<Object> valuesBody = Collections.emptyList();
        List<Map<String, Object>> objectsBody = Collections.emptyList();

        //then
        assertThrows(WrongParameterException.class, () -> sortingMadnessService.sortValues("BUBBLE_SORT", "ASCENDING", valuesBody, null));
        assertThrows(WrongParameterException.class, () -> sortingMadnessService.sortObjects("BUBBLE_SORT", "ASCENDING", objectsBody, "a", null));
    }

    @Test
    void testSortDifferentTypes() {
        //given
        List<Object> valuesBody = List.of(3, "2", 1);
        List<Map<String, Object>> objectsBody = List.of(
                Map.of("a", 1),
                Map.of("a", "2"),
                Map.of("a", 3)
        );

        //then
        assertThrows(WrongParameterException.class, () -> sortingMadnessService.sortValues("BUBBLE_SORT", "ASCENDING", valuesBody, null));
        assertThrows(WrongParameterException.class, () -> sortingMadnessService.sortObjects("BUBBLE_SORT", "ASCENDING", objectsBody, "a", null));
    }

    @Test
    void testSortNotComparable() {
        //given
        List<Object> valuesBody = List.of(new Object(), new Object());
        List<Map<String, Object>> objectsBody = List.of(
                Map.of("a", new Object()),
                Map.of("a", new Object())
        );

        //then
        assertThrows(WrongParameterException.class, () -> sortingMadnessService.sortValues("BUBBLE_SORT", "ASCENDING", valuesBody, null));
        assertThrows(WrongParameterException.class, () -> sortingMadnessService.sortObjects("BUBBLE_SORT", "ASCENDING", objectsBody, "a", null));
    }

    @Test
    void testSortValuesInvalidAlgorithm() {
        //given
        List<Object> valuesBody = List.of(3, 2, 1);
        List<Map<String, Object>> objectsBody = List.of(
                Map.of("a", 3, "b", 2, "c", 1),
                Map.of("a", 2, "b", 1, "c", 3),
                Map.of("a", 1, "b", 3, "c", 2)
        );

        //then
        assertThrows(WrongParameterException.class, () -> sortingMadnessService.sortValues("INVALID_ALGORITHM", "ASCENDING", valuesBody, null));
        assertThrows(WrongParameterException.class, () -> sortingMadnessService.sortObjects("INVALID_ALGORITHM", "ASCENDING", objectsBody, "a", null));
    }

    @Test
    void testSortCountingSortInvalidInput() {
        //given
        List<Object> valuesBody = List.of(3.0, 2.2, 1.1);
        List<Map<String, Object>> objectsBody = List.of(
                Map.of("a", 3.0, "b", 2.2, "c", 1.1),
                Map.of("a", 2.2, "b", 1.1, "c", 3.0),
                Map.of("a", 1.1, "b", 3.0, "c", 2.2)
        );

        //then
        assertThrows(WrongParameterException.class, () -> sortingMadnessService.sortValues("COUNTING_SORT", "ASCENDING", valuesBody, null));
        assertThrows(WrongParameterException.class, () -> sortingMadnessService.sortObjects("COUNTING_SORT", "ASCENDING", objectsBody, "a", null));
    }

    @SneakyThrows
    @Test
    void testSortValuesSuccess() {
        //given
        List<Object> body = List.of(3, 2, 1);
        var response = new SortingResponse(body, AlgorithmName.BUBBLE_SORT, 1L);

        //when
        when(sortingMadness.performSortValues(any(), any())).thenReturn(response);

        //then
        assertEquals(List.of(response), sortingMadnessService.sortValues("BUBBLE_SORT", "ASCENDING", body, null));
    }

    @SneakyThrows
    @Test
    void testSortValuesSuccessDescending() {
        //given
        List<Object> body = List.of(3, 2, 1);
        var response = new SortingResponse(body, AlgorithmName.BUBBLE_SORT, 1L);

        //when
        when(sortingMadness.performSortValues(any(), any())).thenReturn(response);

        //then
        assertEquals(List.of(response), sortingMadnessService.sortValues("BUBBLE_SORT", "DESCENDING", body, null));
    }

    @SneakyThrows
    @Test
    void testSortObjectsSuccess() {
        //given
        List<Map<String, Object>> body = List.of(
                Map.of("a", 3, "b", 2, "c", 1),
                Map.of("a", 2, "b", 1, "c", 3),
                Map.of("a", 1, "b", 3, "c", 2)
        );
        var listAsObjects = body.stream().map(o -> (Object) o).collect(Collectors.toList());
        var response = new SortingResponse(listAsObjects, AlgorithmName.BUBBLE_SORT, 1L);

        //when
        when(sortingMadness.performSortObjects(any(), any(), any())).thenReturn(response);

        //then
        assertEquals(List.of(response), sortingMadnessService.sortObjects("BUBBLE_SORT", "ASCENDING", body, "a", null));
    }

    @SneakyThrows
    @Test
    void testSortObjectsSuccessDescending() {
        //given
        List<Map<String, Object>> body = List.of(
                Map.of("a", 3, "b", 2, "c", 1),
                Map.of("a", 2, "b", 1, "c", 3),
                Map.of("a", 1, "b", 3, "c", 2)
        );
        var listAsObjects = body.stream().map(o -> (Object) o).collect(Collectors.toList());
        var response = new SortingResponse(listAsObjects, AlgorithmName.BUBBLE_SORT, 1L);

        //when
        when(sortingMadness.performSortObjects(any(), any(), any())).thenReturn(response);

        //then
        assertEquals(List.of(response), sortingMadnessService.sortObjects("BUBBLE_SORT", "DESCENDING", body, "a", null));
    }

    @SneakyThrows
    @Test
    void testSortValuesInvalidSortingOrder() {
        //given
        List<Object> valuesBody = List.of(3, 2, 1);
        List<Map<String, Object>> objectsBody = List.of(
                Map.of("a", 3, "b", 2, "c", 1),
                Map.of("a", 2, "b", 1, "c", 3),
                Map.of("a", 1, "b", 3, "c", 2)
        );

        //then
        assertThrows(WrongParameterException.class, () -> sortingMadnessService.sortValues("BUBBLE_SORT", "INVALID_ORDER", valuesBody, null));
        assertThrows(WrongParameterException.class, () -> sortingMadnessService.sortObjects("BUBBLE_SORT", "INVALID_ORDER", objectsBody, "a", null));
    }


    @SneakyThrows
    @Test
    void testEmptyOrder() {
        //given
        List<Object> valuesBody = List.of(3, 2, 1);
        List<Map<String, Object>> objectsBody = List.of(
                Map.of("a", 3, "b", 2, "c", 1),
                Map.of("a", 2, "b", 1, "c", 3),
                Map.of("a", 1, "b", 3, "c", 2)
        );

        //then
        assertThrows(WrongParameterException.class, () -> sortingMadnessService.sortValues("BUBBLE_SORT", "", valuesBody, null));
        assertThrows(WrongParameterException.class, () -> sortingMadnessService.sortObjects("BUBBLE_SORT", "", objectsBody, "a", null));
    }
}