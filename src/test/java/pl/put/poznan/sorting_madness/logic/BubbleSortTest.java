package pl.put.poznan.sorting_madness.logic;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Tests for the {@link BubbleSort} class.
 * This class contains various test cases to verify the sorting functionality
 * of the BubbleSort implementation, covering a range of input scenarios.
 */
public class BubbleSortTest {

    private BubbleSort sorter;

    /**
     * Set up the test environment before each test.
     * Initializes the BubbleSort instance.
     */
    @BeforeEach
    void setUp() {
        this.sorter = new BubbleSort();
    }

    /**
     * Clean up after each test.
     * Sets the BubbleSort instance to null.
     */
    @AfterEach
    void tearDown() {
        this.sorter = null;
    }

    /**
     * Test sorting a list of integers in natural order.
     */
    @Test
    void testSortValues_NaturalOrder() {
        List<Integer> data = Arrays.asList(3, 1, 4, 1, 5, 9);
        SortingResponse response = sorter.sortValues(data, Comparator.naturalOrder());

        assertArrayEquals(new Integer[]{1, 1, 3, 4, 5, 9}, response.getSortedList().toArray());
    }

    /**
     * Test sorting a list of integers in reverse order.
     */
    @Test
    void testSortValues_ReverseOrder() {
        List<Integer> data = Arrays.asList(45, -234, 1, 1, 0, 5, 9);
        SortingResponse response = sorter.sortValues(data, Comparator.reverseOrder());

        assertArrayEquals(new Integer[]{45, 9, 5, 1, 1, 0, -234}, response.getSortedList().toArray());
    }

    /**
     * Test sorting a list with a single integer element.
     */
    @Test
    void testSortValues_SingleElement() {
        List<Integer> data = List.of(3);
        SortingResponse response = sorter.sortValues(data, Comparator.naturalOrder());

        assertArrayEquals(new Integer[]{3}, response.getSortedList().toArray());
    }

    /**
     * Test sorting an empty list of integers.
     */
    @Test
    void testSortValues_EmptyList() {
        List<Integer> data = List.of();
        SortingResponse response = sorter.sortValues(data, Comparator.naturalOrder());

        assertArrayEquals(new Integer[]{}, response.getSortedList().toArray());
    }

    /**
     * Test sorting a list of floating-point numbers in natural order.
     */
    @Test
    void testSortValues_FloatingPointNumbers() {
        List<Double> data = Arrays.asList(3.14, 1.0, 4.0, 1.0, 5.0, 9.0);
        SortingResponse response = sorter.sortValues(data, Comparator.naturalOrder());

        assertArrayEquals(new Double[]{1.0, 1.0, 3.14, 4.0, 5.0, 9.0}, response.getSortedList().toArray());
    }

    /**
     * Test sorting a list of maps by an integer field in natural order.
     */
    @Test
    void testSortObjects_NaturalOrderByNumbers() {
        List<Map<String, Object>> data = new ArrayList<>();
        Map<String, Object> data1 = Map.of("age", 30, "name", "John");
        Map<String, Object> data2 = Map.of("age", 25, "name", "Alice");
        Map<String, Object> data3 = Map.of("age", 35, "name", "Bob");
        data.add(data1);
        data.add(data2);
        data.add(data3);

        Comparator<Integer> comparator = Comparator.naturalOrder();
        SortingResponse response = sorter.sortObjects(data, comparator, "age");

        assertEquals(3, response.getSortedList().size());
        assertEquals(data2, response.getSortedList().get(0));
        assertEquals(data1, response.getSortedList().get(1));
        assertEquals(data3, response.getSortedList().get(2));
    }

    /**
     * Test sorting a list of maps by a string field in natural order.
     */
    @Test
    void testSortObjects_NaturalOrderByStrings() {
        List<Map<String, Object>> data = new ArrayList<>();
        Map<String, Object> data1 = Map.of("age", 30, "name", "John");
        Map<String, Object> data2 = Map.of("age", 25, "name", "Alice");
        Map<String, Object> data3 = Map.of("age", 35, "name", "Bob");
        data.add(data1);
        data.add(data2);
        data.add(data3);

        Comparator<String> comparator = Comparator.naturalOrder();
        SortingResponse response = sorter.sortObjects(data, comparator, "name");

        assertEquals(3, response.getSortedList().size());
        assertEquals(data2, response.getSortedList().get(0));
        assertEquals(data3, response.getSortedList().get(1));
        assertEquals(data1, response.getSortedList().get(2));
    }

    /**
     * Test sorting a list of maps in reverse order by a specified numeric field.
     */
    @Test
    void testSortObjects_ReverseOrder() {
        List<Map<String, Object>> data = new ArrayList<>();
        Map<String, Object> data1 = Map.of("age", 30);
        Map<String, Object> data2 = Map.of("age", 25);
        Map<String, Object> data3 = Map.of("age", 35);
        data.add(data1);
        data.add(data2);
        data.add(data3);

        Comparator<Integer> comparator = Comparator.reverseOrder();
        SortingResponse response = sorter.sortObjects(data, comparator, "age");

        assertEquals(3, response.getSortedList().size());
        assertEquals(data3, response.getSortedList().get(0));
        assertEquals(data1, response.getSortedList().get(1));
        assertEquals(data2, response.getSortedList().get(2));
    }

    /**
     * Test sorting a list with a single map element.
     */
    @Test
    void testSortObjects_SingleElement() {
        List<Map<String, Object>> data = new ArrayList<>();
        Map<String, Object> data1 = Map.of("age", 30);
        data.add(data1);

        Comparator<Integer> comparator = Comparator.naturalOrder();
        SortingResponse response = sorter.sortObjects(data, comparator, "age");

        assertEquals(1, response.getSortedList().size());
        assertEquals(data1, response.getSortedList().get(0));
    }

    /**
     * Test sorting an empty list of maps.
     */
    @Test
    void testSortObjects_EmptyList() {
        List<Map<String, Object>> data = new ArrayList<>();

        Comparator<Integer> comparator = Comparator.naturalOrder();
        SortingResponse response = sorter.sortObjects(data, comparator, "age");

        assertEquals(0, response.getSortedList().size());
    }
}
