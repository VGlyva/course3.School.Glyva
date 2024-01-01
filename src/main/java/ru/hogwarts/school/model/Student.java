package ru.hogwarts.school.model;

import java.util.Objects;

public class Student {
    private Long id;
    private String name;
    private String color;

    public Student(Long id, String name, String color) {
        this.id = id;
        this.name = name;
        this.color = color;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Student student = (Student) o;
        return Objects.equals(id, student.id) && Objects.equals(name, student.name) && Objects.equals(color, student.color);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, color);
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", color='" + color + '\'' +
                '}';
    }
}
