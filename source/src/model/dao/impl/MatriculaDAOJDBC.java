package model.dao.impl;

import model.dao.MatriculaDAO;
import model.entities.Matricula;

import java.sql.Connection;
import java.util.List;

public class MatriculaDAOJDBC implements MatriculaDAO {

    private Connection conn;

    public MatriculaDAOJDBC(Connection conn) {
        this.conn = conn;
    }
    @Override
    public void insert(Matricula obj) {

    }

    @Override
    public Matricula findById(Integer id) {
        return null;
    }

    @Override
    public List<Matricula> findAll() {
        return List.of();
    }

    @Override
    public void update(Matricula obj) {

    }

    @Override
    public void deleteById(Integer id) {

    }
}
