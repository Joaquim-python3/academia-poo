package model.dao.impl;

import db.DB;
import model.dao.DAOFactory;
import model.dao.MatriculaDAO;
import model.entities.Cliente;
import model.entities.Matricula;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
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
        Matricula matricula = new Matricula();
        PreparedStatement st = null;
        ResultSet rs = null;
            try{
                st = conn.prepareStatement("select * from matricula where id=?");
                st.setInt(1,id);
                rs = st.executeQuery();
                if(rs.next()){
                    matricula.setId(rs.getInt("id"));
                    matricula.setDataInicio(rs.getDate("dataInicio"));
                    matricula.setDataFim(rs.getDate("dataFim"));
                    matricula.setTreino(DAOFactory.criaTreinoDAO().findTreinosByMatricula(rs.getInt("id")));
                }
            }catch (SQLException e) {
                throw new RuntimeException(e);
            }
        return matricula;
    }

    @Override
    public List<Matricula> findAll() {
        PreparedStatement st = null;
        ResultSet rs = null;
        List<Matricula> matriculas = new ArrayList<>();
        try{
            st = conn.prepareStatement("select * from matricula");
            rs = st.executeQuery();

            while(rs.next()){
                int id = rs.getInt("id");
                Date dataInicio = rs.getDate("dataInicio");
                Date dataFim = rs.getDate("dataFim");
                Matricula c = new Matricula(id, dataInicio, dataFim);
                c.setTreino(DAOFactory.criaTreinoDAO().findTreinosByMatricula(id));
                matriculas.add(c);
            }
        }catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            DB.closeStatement(st);
            DB.closeResultSet(rs);
        }
        return matriculas;
    }

    @Override
    public void update(Matricula obj) {

    }

    @Override
    public void deleteById(Integer id) {

    }
}
