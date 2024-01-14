package ru.hogwarts.school.service;

import org.springframework.stereotype.Service;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;

import java.util.Collection;
import java.util.HashMap;
import java.util.stream.Collectors;

@Service
public class FacultyService {
    private final HashMap<Long, Faculty> faculties = new HashMap<>();
    private long lastId = 0;

    public Faculty createFaculty(Faculty faculty) {
        faculty.setId(lastId++);
        faculties.put(lastId, faculty);
        return faculty;
    }

    public Faculty findFaculty(long id) {
        return faculties.get(id);
    }

    public Faculty editFaculty(long id,Faculty faculty) {
        if (!faculties.containsKey(id)) {
            return  null;
        }
        faculties.put(faculty.getId(), faculty);
        return faculty;
    }

    public Faculty deleteFaculty(long id) {
        return faculties.remove(id);
    }
    public Collection<Faculty> getAllFaculties() {
        return faculties.values();
    }
    public Collection<Faculty> findSameColor(String color) {
        return getAllFaculties().stream()
                .filter(e -> e.getColor().equals(color))
                .collect(Collectors.toList());
    }
}
