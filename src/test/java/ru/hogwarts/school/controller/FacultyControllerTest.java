package ru.hogwarts.school.controller;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class FacultyControllerTest {
    @LocalServerPort
    private int port;
    @Autowired
    private FacultyController facultyController;
    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    void contextLoads() throws Exception {
        Assertions.assertThat(facultyController).isNotNull();
    }

    @Test
    void getFacultiesTest() throws Exception { // тест проходит вне зависимости от URL /faculties/all
        Assertions
                .assertThat(this.restTemplate.getForObject
                        ("http://localhost:" + port + "/faculties/all", String.class))
                .isNotNull();
    }

    @Test
    void postFacultyTest() throws Exception {
        Faculty faculty = new Faculty();
        faculty.setColor("blue");
        faculty.setId(77);
        faculty.setName("DragonFrost");
        faculty.setStudents(null);
        Assertions
                .assertThat(this.restTemplate
                .postForObject("http://localhost:" + port + "/faculties",faculty,String.class));
    }
    @Test
    void updateFacultyTest() throws Exception {
        Faculty faculty = new Faculty(19L,"АТМ","Черный",null);
        long id = this.restTemplate.postForObject
                ("http://localhost:" + port + "/faculties", faculty, Faculty.class).getId();
        Faculty faculty1 = new Faculty(21L,"IT","Белый",null);
        restTemplate.put("http://localhost:" + port + "/faculties", faculty1);
        Assertions
                .assertThat((this.restTemplate.getForObject
                        ("http://localhost:" + port + "/faculties/" + id, String.class)))
                .contains("" + id);

    }
    @Test
    void deleteFacultyTest() throws Exception {
        Faculty faculty = new Faculty(26L,"Экономики","Зеленый",null);
        long id = this.restTemplate.postForObject
                ("http://localhost:" + port + "/faculties", faculty, Faculty.class).getId();
        restTemplate.delete("http://localhost:" + port + "/faculties/" + id);
        Assertions
                .assertThat((this.restTemplate.getForObject
                        ("http://localhost:" + port + "/faculties/" + id, String.class)))
                .toString().contains("500");
    }
}
