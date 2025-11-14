package com.korit.servlet_study.ch11.service;

import com.korit.servlet_study.ch11.dao.ProfessorDao;
import com.korit.servlet_study.ch11.entity.Professor;

import java.util.List;

public class ProfessorService {

    public List<Professor> getProfessors(String query) {
        ProfessorDao professorDao =
                new ProfessorDao();
        List<Professor> professors =
                professorDao.findAllLikeName(query);
        return professors;
    }
}
