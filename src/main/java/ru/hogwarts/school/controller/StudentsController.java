package ru.hogwarts.school.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.service.StudentService;

import java.util.Collection;
import java.util.List;

@RestController
@RequestMapping("students")
public class StudentsController {
    private final StudentService studentService;

    public StudentsController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping("{id}") // GET http://localhost:8080/students/1
    public ResponseEntity<Student> getStudentInfo(@PathVariable long id) {
        Student student = studentService.findStudent(id);
        if (student == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(student);
    }

    @GetMapping("/all") // GET http://localhost:8080/students
    public ResponseEntity<Collection<Student>> getAll() {
        return ResponseEntity.ok(studentService.getAll());
    }

    @GetMapping("/age/{age}") // GET http://localhost:8080/students/age/10
    public Collection<Student> getAge(@PathVariable long age) {
        return studentService.findSameAge(age);
    }

    @GetMapping() // GET http://localhost:8080/students/int
    public ResponseEntity<?> findStudentByBetweenAge(@RequestParam(required = false) Integer min,
                                                     @RequestParam(required = false) Integer max) {
        if (min > max) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(studentService.findByAgeBetween(min, max));
    }

    @GetMapping("/faculty/{id}") // GET http://localhost:8080/students/faculty/id
    public ResponseEntity<Faculty> findFacultyById(@PathVariable long id) {
        Student student = studentService.findStudent(id);
        if (student == null) {
            return ResponseEntity.notFound().build();
        }
        Faculty faculty = studentService.findFacultyByStudent(id);
        if (faculty == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(faculty);
    }

    @PostMapping // POST http://localhost:8080/students
    public ResponseEntity<Student> createStudent(@RequestBody Student student) {
        return ResponseEntity.ok(student);
    }

    @PutMapping("{id}") // PUT http://localhost:8080/students/1
    public ResponseEntity<Student> editStudent(@RequestBody Student student) {
        Student foundStudent = studentService.editStudent(student);
        if (foundStudent == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        return ResponseEntity.ok(foundStudent);
    }

    @DeleteMapping("{id}")// DELETE http://localhost:8080/students/1
    public void deleteStudent(@PathVariable Long id) {
        studentService.deleteStudent(id);
    }

}
