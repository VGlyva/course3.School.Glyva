package ru.hogwarts.school.service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.repositories.StudentRepository;

import java.util.*;


@Service
public class StudentService {
    Logger logger = LoggerFactory.getLogger(StudentService.class);
    @Autowired
    private final StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public Student createStudent(Student student) {
        logger.info("Was invoked method for create student");
        return studentRepository.save(student);
    }

    public Student findStudent(long id) {
        logger.info("The method of searching for a student by id was called");
        return studentRepository.findById(id).get();
    }

    public Student editStudent(Student student) {
        logger.info("Was invoked method for edit student");
        return studentRepository.save(student);
    }

    public ResponseEntity<?> deleteStudent(long id) {
        studentRepository.deleteById(id);
        logger.info("Was invoked method for delete student");
        return ResponseEntity.ok().build();
    }

    public Collection<Student> getAll() {
        logger.info("Was invoked method for called to display all students");
        return studentRepository.findAll();
    }

    public Collection<Student> findSameAge(long age) {
        logger.info("A method was called to display all students by age");
        return studentRepository.findStudentByAge(age);
    }

    public Collection<Student> findByAgeBetween(int min, int max) {
        logger.info("A method was called to display all students by age between");
        return studentRepository.findByAgeBetween(min, max);
    }

    public Faculty findFacultyByStudent(long id) {
        logger.info("The method of searching for a faculty by student id was called");
        return studentRepository.getReferenceById(id).getFaculty();
    }
    public Integer getAllStudentsInSchool() {
        logger.info("The method for calling all students of the school was called");
        return studentRepository.getAllStudentsInSchool();
    }
    public Integer getAverageAgeStudents() {
        logger.info("The method of calling the average age of students was called");
        return studentRepository.getAverageAgeStudents();
    }
    public List<Student> getByLastFiveStudents() {
        logger.info("The method for calling the last five students was called");
        return studentRepository.getByLastFiveStudents();
    }

}
