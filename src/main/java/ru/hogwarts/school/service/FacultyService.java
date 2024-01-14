package ru.hogwarts.school.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.repositories.FacultyRepository;

import java.util.Collection;
import java.util.stream.Collectors;

@Service
public class FacultyService {
    @Autowired
    private final FacultyRepository facultyRepository;

    public FacultyService(FacultyRepository facultyRepository) {
        this.facultyRepository = facultyRepository;
    }

    public Faculty createFaculty(Faculty student) {
        return facultyRepository.save(student);
    }

    public Faculty findFaculty(long id) {
        return facultyRepository.findById(id).get();
    }

    public Faculty editFaculty(Faculty faculty) {
        return facultyRepository.save(faculty);
    }

    public ResponseEntity deleteFaculty(long id) {
        facultyRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }
    public Collection<Faculty> getAllFaculties() {
        return facultyRepository.findAll();
    }
    public Collection<Faculty> findSameColor(String color) {
        return getAllFaculties().stream()
                .filter(e -> e.getColor().equals(color))
                .collect(Collectors.toList());
    }
}
