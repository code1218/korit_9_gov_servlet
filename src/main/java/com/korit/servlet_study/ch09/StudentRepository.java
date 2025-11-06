package com.korit.servlet_study.ch09;


import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class StudentRepository {
    private List<Student> students = new ArrayList<>();
    private int autoId = 20250001;

    public void insert(Student student) {
        student.setId(autoId++);
        students.add(student);
        System.out.println(students);
    }

    public List<Student> findAllBySearchNameValue(String searchNameValue) {
        if (Objects.isNull(searchNameValue)) {
            return students;
        }
        return students.stream()
                .filter(student -> student.getName().contains(searchNameValue))
                .toList();
    }
}
