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
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.repositories.StudentRepository;
import ru.hogwarts.school.service.StudentService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = StudentsController.class)
public class StudentControllerMockTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private StudentRepository studentRepository;
    @SpyBean
    private StudentService studentService;
    @InjectMocks
    private StudentsController studentsController;

    long ID = 1;
    String NAME = "Лорд Воландеморт";
    int AGE = 97;
    Student student = new Student(ID, NAME, AGE);

    @Test
    void saveStudentTest() throws Exception {
        JSONObject studentObject = new JSONObject();
        studentObject.put("name", NAME);
        studentObject.put("age", AGE);
        studentObject.put("id", ID);

        when(studentRepository.save(any())).thenReturn(student);
        when(studentRepository.findById(any(Long.class))).thenReturn(Optional.of(student));

        mockMvc.perform(MockMvcRequestBuilders
                        .post("/students")
                        .content(studentObject.toString())
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(ID))
                .andExpect(jsonPath("$.name").value(NAME))
                .andExpect(jsonPath("$.age").value(AGE));

    }

    @Test
    void getStudentByIdStudentTest() throws Exception {
        when(studentRepository.findById(any(Long.class))).thenReturn(Optional.of(student));

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/students/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(ID))
                .andExpect(jsonPath("$.name").value(NAME))
                .andExpect(jsonPath("$.age").value(AGE));

    }
    @Test
    void getStudentByAgeBetweenTest() throws Exception {
        List<Student> studentList = new ArrayList<>(List.of
                (new Student
                                (13L, "Cаша", 14)
                        , new Student
                                (14L, "Ольга", 15)));
        when(studentRepository.findByAgeBetween(any(Integer.class), any(Integer.class)))
                .thenReturn(studentList);
        mockMvc.perform(MockMvcRequestBuilders
                .get("/students?min=1&max=20")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()").value(2));

    }

    @Test
    void updateStudentTest() throws Exception {
        JSONObject studentObject = new JSONObject();
        studentObject.put("name", NAME);
        studentObject.put("age", AGE);
        studentObject.put("id", ID);

        when(studentRepository.save(any())).thenReturn(student);
        when(studentRepository.findById(any(Long.class))).thenReturn(Optional.of(student));

        mockMvc.perform(MockMvcRequestBuilders
                        .put("/students/" + ID)
                        .content(studentObject.toString())
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(ID))
                .andExpect(jsonPath("$.name").value(NAME))
                .andExpect(jsonPath("$.age").value(AGE));

    }
}
