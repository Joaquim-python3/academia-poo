package model.dao.impl;

import db.DB;
import model.dao.DAOFactory;
import model.dao.MatriculaDAO;
import model.dao.TreinoDAO;
import model.entities.Cliente;
import model.entities.Matricula;
import model.exceptions.*;

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
        ResultSet rs = null;
        try{
            st = conn.prepareStatement(
                    "INSERT INTO Matricula (dataInicio, dataFim) VALUES (?, ?)",
                    Statement.RETURN_GENERATED_KEYS
            );

            st.setDate(1, Date.valueOf(newMatricula.getDataInicio()));
            st.setDate(2, Date.valueOf(newMatricula.getDataFim()));

            int linhas = st.executeUpdate();

            // capturar id criado
            if (linhas > 0) {
                rs = st.getGeneratedKeys();
                if (rs.next()) {
                    int id = rs.getInt(1);
                    newMatricula.setId(id);
                }
            } else {
                throw new DBException("Erro inesperado! Nenhuma linha inserida.");
            }

            System.out.println("Matrícula inserida, id: "+newMatricula.getId());
        } catch (SQLException e) {
            throw new DBException("Erro ao inserir matricula: "+e.getMessage());
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

                    TreinoDAO treinoDAO = DAOFactory.criaTreinoDAO();
                    // descomentar linha abaixo para nao mostrar os treinos das matriculas
                    matricula.setTreino(treinoDAO.findTreinosByMatricula(rs.getInt("id")));

                    return matricula;
                }
                return null;
            }catch (SQLException e) {
                throw new DBException("Erro ao buscar matrícula por ID "+e.getMessage());
            } finally {
                DB.closeStatement(st);
                DB.closeResultSet(rs);
            }
    }

    @Override
    public List<Matricula> findAll() {
        PreparedStatement st = null;
        ResultSet rs = null;
        List<Matricula> matriculas = new ArrayList<>();
        try{
            st = conn.prepareStatement("select * from matricula");
            rs = st.executeQuery();

            TreinoDAO treinoDAO = DAOFactory.criaTreinoDAO(); // mais eficiente que o método antigo
            // método antigo: criava um TreinoDAO para cada Matricula

            while(rs.next()){
                int id = rs.getInt("id");
                LocalDate dataInicio = rs.getDate("dataInicio").toLocalDate();
                LocalDate dataFim = rs.getDate("dataFim").toLocalDate();
                Matricula c = new Matricula(id, dataInicio, dataFim);
                c.setTreino(treinoDAO.findTreinosByMatricula(id));
                matriculas.add(c);
            }
        }catch (SQLException e) {
            throw new DBException("Erro ao buscar todas as matrículas: " + e.getMessage());
        }
        finally {
            DB.closeStatement(st);
            DB.closeResultSet(rs);
        }
        return matriculas;
    }

    @Override
    public void update(Matricula matricula, Integer id) {
        PreparedStatement st = null;
        validarMatricula(matricula);
        try{
            st = conn.prepareStatement("UPDATE Matricula SET dataInicio=?, dataFIm=? WHERE id=?");
            st.setDate(1, Date.valueOf(matricula.getDataInicio()));
            st.setDate(2,Date.valueOf(matricula.getDataFim()));
            st.setInt(3, id);

            int linhas = st.executeUpdate();

            if (linhas == 0) {
                throw new NotFoundException("Matricula com id " + id + " não encontrada.");
            }

            System.out.println("Linhas alteradas: "+linhas);

        }
        catch (SQLIntegrityConstraintViolationException e) {
            throw new DbIntegrityException("Violação de integridade ao atualizar Matricula.");
        }
        catch (SQLException e) {
            throw new DBException("Erro SQL ao atualizar Matricula: " + e.getMessage());
        }
        finally {
            DB.closeStatement(st);
        }
    }

    @Override
    public void deleteById(Integer id) {
        PreparedStatement st = null;
        try{
            st = conn.prepareStatement("DELETE FROM Matricula WHERE id=?");
            st.setInt(1,id);
            int linhasAfetadasMatricula = st.executeUpdate();
            System.out.println("Linhas afetadas (matricula) = "+linhasAfetadasMatricula);

        } catch (SQLIntegrityConstraintViolationException e) {
            throw new DbIntegrityException("Não é possível deletar uma matrícula com dependências!");
        } catch (SQLException e){
            throw new DBException(e.getMessage());
        }finally {
            DB.closeStatement(st);
        }
    }

    public void validarMatricula(Matricula matricula){
        if(matricula.getDataFim().equals(matricula.getDataInicio())){
            throw new ValidationException("Datas iguais na criacao na matricula");
        }
    }
}
