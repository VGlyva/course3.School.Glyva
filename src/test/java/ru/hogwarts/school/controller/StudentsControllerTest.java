package ru.hogwarts.school.controller;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import ru.hogwarts.school.model.Student;

import static org.assertj.core.api.Assertions.assertThat;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class StudentsControllerTest {

    @LocalServerPort
    private int port;
    @Autowired
    private StudentsController studentsController;
    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    void contextLoads() throws Exception {
        assertThat(studentsController).isNotNull();
    }

    @Test
    void getStudentsTest() throws Exception { // тест проходит вне зависимости от URL /students/all
        assertThat(this.restTemplate.getForObject(
                "http://localhost:" + port + "/students/all", String.class)).isNotNull();
    }

    @Test
    void postStudentTest() throws Exception {
        Student student = new Student();
        student.setId(8);
        student.setAge(50);
        student.setName("HarryTest");
        student.setFaculty(null);
        assertThat(this.restTemplate
                .postForObject("http://localhost:" + port + "/students", student, String.class));
    }

    @Test
    void updateStudentTest() throws Exception {
        Student student = new Student(99L, "Арнольд", 17);
        long id = this.restTemplate.postForObject
                ("http://localhost:" + port + "/students", student, Student.class).getId();
        Student student1 = new Student(101L, "Гарольд", 65);
        restTemplate.put("http://localhost:" + port + "/students", student1);
        Assertions
                .assertThat((this.restTemplate.getForObject
                        ("http://localhost:" + port + "/students/" + id, String.class)))
                .contains("" + id);

    }
    @Test
    void deleteStudentTest() throws Exception {
        Student student = new Student(111L,"Рональд",15);
        long id = this.restTemplate.postForObject
                ("http://localhost:" + port + "/students", student, Student.class).getId();
        restTemplate.delete("http://localhost:" + port + "/students/" + id);
        Assertions
                .assertThat((this.restTemplate.getForObject
                        ("http://localhost:" + port + "/students/" + id, String.class)))
                .toString().contains("500");
    }
}
