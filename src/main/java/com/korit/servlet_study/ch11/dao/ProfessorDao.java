package com.korit.servlet_study.ch11.dao;

import com.korit.servlet_study.ch11.entity.Professor;
import com.korit.servlet_study.ch11.util.DBConnectionMgr;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ProfessorDao {

    public List<Professor> findAllLikeName(String name) {
        DBConnectionMgr mgr = DBConnectionMgr.getInstance();
        List<Professor> professors = new ArrayList<>();

        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            con = mgr.getConnection();
            String sql = """
                    select
                        professor_id,
                        professor_name
                    from
                        professor_tb
                    where
                        professor_name like concat('%', ?, '%')
                    """;
            ps = con.prepareStatement(sql);
            ps.setString(1, name);
            rs = ps.executeQuery();
            while (rs.next()) {
                Professor professor = Professor.builder()
                        .professorId(rs.getInt("professor_id"))
                        .professorName(rs.getString("professor_name"))
                        .build();
                professors.add(professor);
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            mgr.freeConnection(con, ps, rs);
        }

        return professors;
    }
}














