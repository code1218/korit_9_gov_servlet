package com.korit.servlet_study.ch11.service;

import com.korit.servlet_study.ch11.dao.DepartmentDao;
import com.korit.servlet_study.ch11.entity.Department;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class DepartmentService {
    private final DepartmentDao departmentDao;

    public List<Department> getDepartments() {
        return departmentDao.findAll();
    }

}
