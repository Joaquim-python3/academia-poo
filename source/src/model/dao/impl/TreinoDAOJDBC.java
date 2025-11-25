package model.dao.impl;

import db.DB;
import model.dao.TreinoDAO;
import model.entities.Matricula;
import model.entities.Treino;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;
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
        PreparedStatement st = null;
        ResultSet rs = null;
        List<Treino> treinos = new ArrayList<>();
        try{
            st = conn.prepareStatement("select * from treino");
            rs = st.executeQuery();

            while(rs.next()){

                int id = rs.getInt("id");
                String nome = rs.getString("nome");
                LocalTime horaInicio = rs.getTime("horarioInicio").toLocalTime();
                LocalTime horaFim = rs.getTime("horarioFim").toLocalTime();
                Treino t = new Treino(id, nome, horaInicio, horaFim);
                treinos.add(t);
            }
        }catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            DB.closeStatement(st);
            DB.closeResultSet(rs);
        }
        return treinos;
    }

    @Override
    public void update(Treino obj) {

    }

    @Override
    public void deleteById(Integer id) {

    }
}
