package ru.hogwarts.school.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.repositories.FacultyRepository;

import java.util.Collection;
import java.util.Comparator;


@Service
public class FacultyService {

    Logger logger = LoggerFactory.getLogger(FacultyService.class);
    @Autowired
    private final FacultyRepository facultyRepository;

    public FacultyService(FacultyRepository facultyRepository) {
        this.facultyRepository = facultyRepository;
    }

    public Faculty createFaculty(Faculty student) {
        logger.info("Was invoked method for create faculty");
        return facultyRepository.save(student);
    }

    public Faculty findFaculty(long id) {
        logger.info("Search for an faculty by student ID");
        return facultyRepository.findById(id).get();
    }

    public Faculty editFaculty(Faculty faculty) {
        logger.info("Was invoked method for create faculty");
        return facultyRepository.save(faculty);
    }

    public ResponseEntity<?> deleteFaculty(long id) {
        facultyRepository.deleteById(id);
        logger.info("Was invoked method for delete student");
        return ResponseEntity.ok().build();
    }

    public Collection<Faculty> getAllFaculties() {
        logger.info("Was invoked method for called to display all faculties");
        return facultyRepository.findAll();
    }


    public Collection<Faculty> findFacultyByColorOrName(String color, String name) {
        logger.info("A method was called to display all faculties by color or name");
        return facultyRepository.findFacultyByColorOrNameIgnoreCase(color, name);
    }

    public String getByFacultyLongName() {
        return facultyRepository.findAll().stream()
                .max(Comparator.comparingInt(f -> f.getName().length()))
                .get().getName();
    }
}
