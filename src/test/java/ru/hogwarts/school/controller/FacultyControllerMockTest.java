package ru.hogwarts.school.controller;

import org.json.JSONObject;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.repositories.FacultyRepository;
import ru.hogwarts.school.service.FacultyService;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = FacultyController.class)
public class FacultyControllerMockTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private FacultyRepository facultyRepository;
    @SpyBean // чем отличается от MockBean ?
    private FacultyService facultyService;
    @InjectMocks
    private FacultyController facultyController;

    long ID = 2L;
    String NAME = "Слизарен";
    String COLOR = "Зеленый";
    Faculty faculty = new Faculty(ID, NAME, COLOR, null);

    @Test
    void saveFacultyTest() throws Exception {
        JSONObject facultyObject = new JSONObject();
        facultyObject.put("name", NAME);
        facultyObject.put("color", COLOR);
        facultyObject.put("id", ID);

        when(facultyRepository.save(any())).thenReturn(faculty);
        when(facultyRepository.findById(any(Long.class))).thenReturn(Optional.of(faculty));

        mockMvc.perform(MockMvcRequestBuilders
                        .post("/faculties")
                        .content(facultyObject.toString())
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(ID))
                .andExpect(jsonPath("$.name").value(NAME))
                .andExpect(jsonPath("$.color").value(COLOR));

    }

    @Test
    void updateFacultyTest() throws Exception {
        JSONObject facultyObject = new JSONObject();
        facultyObject.put("name", NAME);
        facultyObject.put("color", COLOR);
        facultyObject.put("id", ID);

        when(facultyRepository.save(any())).thenReturn(faculty);
        when(facultyRepository.findById(any(Long.class))).thenReturn(Optional.of(faculty));

        mockMvc.perform(MockMvcRequestBuilders
                        .put("/faculties/" + ID)
                        .content(facultyObject.toString())
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(ID))
                .andExpect(jsonPath("$.name").value(NAME))
                .andExpect(jsonPath("$.color").value(COLOR));

    }
    @Test
    void getFacultyByIdTest() throws Exception {

        when(facultyRepository.findById(any(Long.class))).thenReturn(Optional.of(faculty));

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/faculties/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(ID))
                .andExpect(jsonPath("$.name").value(NAME))
                .andExpect(jsonPath("$.color").value(COLOR));

    }

    @Test
    void getFacultyByColorOrNameTest() throws Exception { // подскажите почему не работает тест?


        when(facultyRepository.findFacultyByColorOrNameIgnoreCase(any(String.class), (any(String.class))))
                .thenReturn((Collection<Faculty>) faculty);

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/faculties?color=" + COLOR + "&" + "name=" + NAME)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(ID))
                .andExpect(jsonPath("$.name").value(NAME))
                .andExpect(jsonPath("$.color").value(COLOR));
    }

    @Test
    void getAllFacultyTest() throws Exception { // подскажите почему не работает тест?
        List<Faculty> facultyTest = new ArrayList<>
                (List.of(new Faculty(111, "111", "Red", null)
                        , new Faculty(222, "222", "333", null)));

        when(facultyRepository.findAll()).thenReturn(facultyTest);
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/faculties")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()").value(2));


    }

}

