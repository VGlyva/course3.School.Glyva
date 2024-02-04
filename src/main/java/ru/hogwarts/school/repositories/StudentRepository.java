package ru.hogwarts.school.repositories;

import org.springframework.boot.autoconfigure.data.jpa.JpaRepositoriesAutoConfiguration;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.hogwarts.school.model.Student;

import java.util.Collection;
import java.util.List;

public interface StudentRepository extends JpaRepository<Student, Long> {
    Collection<Student> findStudentByAge(Long age);

    Collection<Student> findByAgeBetween(int min, int max);

    @Query(value = "SELECT COUNT(*) FROM Students", nativeQuery = true)
    Integer getAllStudentsInSchool();

    @Query(value = "SELECT AVG(age) FROM Students", nativeQuery = true)
    Integer getAverageAgeStudents();

    @Query(value = "SELECT * FROM Students ORDER BY id DESC LIMIT 5", nativeQuery = true)
    List<Student> getByLastFiveStudents();
}
