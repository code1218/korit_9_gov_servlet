package com.korit.servlet_study.ch11.service;

import com.korit.servlet_study.ch11.dao.StudentDao;
import com.korit.servlet_study.ch11.dto.StudentDto;
import com.korit.servlet_study.ch11.entity.Student;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class StudentService {
    private final StudentDao studentDao;

    public Student save(StudentDto studentDto) {
        Student student = studentDto.toEntity();
        studentDao.insert(student);
        return student;
    }

}
