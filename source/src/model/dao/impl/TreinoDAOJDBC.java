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
        Treino treino = new Treino();
        PreparedStatement st = null;
        ResultSet rs = null;
        try{
            st = conn.prepareStatement("select * from treino where id=?");
            st.setInt(1,id);
            rs = st.executeQuery();
            if(rs.next()){
                treino.setId(rs.getInt("id"));
                treino.setNome(rs.getString("nome"));
                treino.setHorarioInicio(rs.getTime("horarioInicio").toLocalTime());
                treino.setHorarioFim(rs.getTime("horarioFim").toLocalTime());
                treino.setMatriculaId(rs.getInt("matricula_id"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return treino;
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
                Integer matriculaId = rs.getInt("matricula_id");
                Treino t = new Treino(id, nome, horaInicio, horaFim,matriculaId);
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
