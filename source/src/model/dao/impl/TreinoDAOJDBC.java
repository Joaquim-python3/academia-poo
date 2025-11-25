package model.dao.impl;

import model.dao.TreinoDAO;
import model.entities.Treino;

import java.sql.Connection;
import java.util.List;

public class TreinoDAOJDBC implements TreinoDAO {

    private Connection conn;

    public TreinoDAOJDBC(Connection conn) {
        this.conn = conn;
    }
    @Override
    public void insert(Treino obj) {

    }

    @Override
    public Treino findById(Integer id) {
        return null;
    }

    @Override
    public List<Treino> findAll() {
        return List.of();
    }

    @Override
    public void update(Treino obj) {

    }

    @Override
    public void deleteById(Integer id) {

    }
}
