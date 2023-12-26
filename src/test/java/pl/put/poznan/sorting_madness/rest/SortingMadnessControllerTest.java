package pl.put.poznan.sorting_madness.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import pl.put.poznan.sorting_madness.logic.AlgorithmName;
import pl.put.poznan.sorting_madness.logic.SortingResponse;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(SortingMadnessController.class)
@ContextConfiguration(classes = {SortingMadnessController.class, SortingMadnessService.class})
class SortingMadnessControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private SortingMadnessService sortingMadnessService;

    @SneakyThrows
    @Test
    void testSortValues() {
        //given
        List<Object> body = List.of(3, 2, 1);
        var mapper = new ObjectMapper();
        var jsonContent = mapper.writeValueAsString(body);
        var response = new SortingResponse(body, AlgorithmName.BUBBLE_SORT, 1L);

        //when
        when(sortingMadnessService.sortValues(any(String.class), any(), any(List.class))).thenReturn(List.of(response));

        //then
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/values")
                        .param("algorithm", "BUBBLE_SORT")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonContent))
                .andExpect(status().isOk());
    }

    @SneakyThrows
    @Test
    void testSortObjects() {
        //given
        List<Map<String, Object>> body = List.of(
                Map.of("a", 3, "b", 2, "c", 1),
                Map.of("a", 2, "b", 1, "c", 3),
                Map.of("a", 1, "b", 3, "c", 2)
        );
        var mapper = new ObjectMapper();
        var jsonContent = mapper.writeValueAsString(body);
        var listAsObjects = body.stream().map(o -> (Object) o).collect(Collectors.toList());
        var response = new SortingResponse(listAsObjects, AlgorithmName.BUBBLE_SORT, 1L);

        //when
        when(sortingMadnessService.sortObjects(any(String.class), any(), any(List.class), any(String.class))).thenReturn(List.of(response));

        //then
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/objects")
                        .param("algorithm", "BUBBLE_SORT")
                        .param("field", "a")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonContent))
                .andExpect(status().isOk());
    }
}