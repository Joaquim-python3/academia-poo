package model.dao.impl;

import db.DB;
import model.dao.DAOFactory;
import model.dao.MatriculaDAO;
import model.entities.Cliente;
import model.entities.Matricula;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class MatriculaDAOJDBC implements MatriculaDAO {

    private Connection conn;

    public MatriculaDAOJDBC(Connection conn) {
        this.conn = conn;
    }
    @Override
    public void insert(Matricula newMatricula) {
        PreparedStatement st = null;
        try{
            st = conn.prepareStatement(
                    "INSERT INTO Matricula (dataInicio, dataFim) VALUES (?, ?)",
                    Statement.RETURN_GENERATED_KEYS
            );

            st.setDate(1, java.sql.Date.valueOf(newMatricula.getDataInicio()));
            st.setDate(2, java.sql.Date.valueOf(newMatricula.getDataFim()));

            int linhas = st.executeUpdate();
            System.out.println("Linhas afetadas: " + linhas);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            DB.closeStatement(st);
        }
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
                    matricula.setDataInicio(rs.getDate("dataInicio").toLocalDate());
                    matricula.setDataFim(rs.getDate("dataFim").toLocalDate());
                    // descomentar linha abaixo para nao mostrar os treinos das matriculas
                    matricula.setTreino(DAOFactory.criaTreinoDAO().findTreinosByMatricula(rs.getInt("id")));
                }
            }catch (SQLException e) {
                throw new RuntimeException(e);
            } finally {
                DB.closeStatement(st);
                DB.closeResultSet(rs);
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
                LocalDate dataInicio = rs.getDate("dataInicio").toLocalDate();
                LocalDate dataFim = rs.getDate("dataFim").toLocalDate();
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
    public void update(Matricula obj, Integer id) {
        PreparedStatement st = null;
        try{
            st = conn.prepareStatement("UPDATE Matricula SET dataInicio=?, dataFIm=? WHERE id=?");
            st.setDate(1, Date.valueOf(obj.getDataInicio()));
            st.setDate(2,Date.valueOf(obj.getDataFim()));
            st.setInt(3, id);

            st.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            DB.closeStatement(st);
        }
    }

    @Override
    public void deleteById(Integer id) {
        PreparedStatement st = null;
        try{
//            st = conn.prepareStatement("DELETE FROM CLIENTE WHERE matricula_id=?");
//            st.setInt(1,id);
//            int linhasAfetadas = st.executeUpdate();
//            System.out.println("Linhas afetadas (cliente) = "+linhasAfetadas);
//
//            st = conn.prepareStatement("DELETE FROM TREINO WHERE matricula_id=?");
//            st.setInt(1,id);
//            linhasAfetadas = st.executeUpdate();
//            System.out.println("Linhas afetadas (treino) = "+linhasAfetadas);

            st = conn.prepareStatement("DELETE FROM Matricula WHERE id=?");
            st.setInt(1,id);
            int linhasAfetadasMatricula = st.executeUpdate();
            System.out.println("Linhas afetadas (matricula) = "+linhasAfetadasMatricula);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            DB.closeStatement(st);
        }
    }
}
