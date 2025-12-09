package model.dao.impl;

import db.DB;
import model.dao.TreinoDAO;
import model.entities.Matricula;
import model.entities.Treino;
import model.exceptions.DBException;
import model.exceptions.DbIntegrityException;
import model.exceptions.NotFoundException;

import java.sql.*;
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
    public void insert(Treino treino) {
        PreparedStatement st = null;
        ResultSet rs = null;
        try{
            st = conn.prepareStatement(
                    "INSERT INTO Treino (nome, horarioInicio, horarioFim, matricula_id) VALUES (?, ?, ?, ?)",
                    Statement.RETURN_GENERATED_KEYS
            );
            st.setString(1, treino.getNome());
            st.setTime(2, Time.valueOf(treino.getHorarioInicio()));
            st.setTime(3, Time.valueOf(treino.getHorarioFim()));
            st.setInt(4, treino.getMatriculaId());

            int linhas = st.executeUpdate();
            System.out.println("Linhas afetadas: " + linhas);

            // capturar id criado
            if (linhas > 0) {
                rs = st.getGeneratedKeys();
                if (rs.next()) {
                    treino.setId(rs.getInt(1));
                }
            } else {
                throw new DBException("Erro inesperado! Nenhuma linha inserida.");
            }
        } catch (SQLException e) {
            throw new DBException("Erro ao inserir treino");
        } finally {
            DB.closeStatement(st);
            DB.closeResultSet(rs);
        }
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
            }else{
                throw new NotFoundException("Nao foi possivel achar o treino pelo id="+id);
            }
        } catch (SQLException e) {
            throw new NotFoundException("Nao foi possível achar o treino pelo o id="+id);
        } finally {
            DB.closeStatement(st);
            DB.closeResultSet(rs);
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

                // ajuste para realizar verificações no banco
                Time timeInicio = rs.getTime("horarioInicio");
                Time timeFim = rs.getTime("horarioFim");

                LocalTime horaInicio = (timeInicio != null) ? timeInicio.toLocalTime() : null;
                LocalTime horaFim = (timeFim != null) ? timeFim.toLocalTime() : null;

                Integer matriculaId = rs.getInt("matricula_id");
                Treino t = new Treino(id, nome, horaInicio, horaFim,matriculaId);
                treinos.add(t);
            }
        }catch (SQLException e) {
            throw new DBException("Erro ao procurar Treinos");
        } finally {
            DB.closeStatement(st);
            DB.closeResultSet(rs);
        }
        return treinos;
    }

    @Override
    public void update(Treino treino, Integer id) {
        PreparedStatement st = null;
        try{
            st = conn.prepareStatement("UPDATE Treino SET nome=?, horarioInicio=?, horarioFim=? WHERE id=?");
            st.setString(1, treino.getNome());
            st.setTime(2, Time.valueOf(treino.getHorarioInicio()));
            st.setTime(3, Time.valueOf(treino.getHorarioFim()));
            st.setInt(4, id);

            int linhasAfetadas = st.executeUpdate();

            if(linhasAfetadas == 0){
                throw new DBException("Nenhum treino encontrado com o ID="+id);
            }
            System.out.println("Linhas Afetadas="+linhasAfetadas);

        } catch (SQLIntegrityConstraintViolationException e) {
            throw new DbIntegrityException("Não foi possível atualizar o treino: restrição de integridade violada.");
        } catch (SQLException e) {
            throw new DBException("Erro ao atualizar treino: " + e.getMessage());
        } finally {
            DB.closeStatement(st);
        }
    }

    @Override
    public void deleteById(Integer id) {
        PreparedStatement st = null;
        try{
            st = conn.prepareStatement("delete from Treino where id=?");
            st.setInt(1,id);
            int linhas = st.executeUpdate();
            if(linhas == 0){
                throw new DbIntegrityException("Nenhum treino encontrado com o id="+id);
            }
            System.out.println("Linhas afetadas: " + linhas);
        } catch (SQLException e) {
            throw new DbIntegrityException("Erro ao deleter Treino");
        } finally {
            DB.closeStatement(st);
        }
    }

    @Override
    public List<Treino> findTreinosByMatricula(Integer id) {
        PreparedStatement st = null;
        ResultSet rs = null;
        List<Treino> treinos = new ArrayList<>();
        try{
            st = conn.prepareStatement("select * from treino where matricula_id=?");
            st.setInt(1,id);
            rs = st.executeQuery();

            while(rs.next()){
                id = rs.getInt("id");
                String nome = rs.getString("nome");
                LocalTime horaInicio = rs.getTime("horarioInicio").toLocalTime();
                LocalTime horaFim = rs.getTime("horarioFim").toLocalTime();
                Integer matriculaId = rs.getInt("matricula_id");
                Treino t = new Treino(id, nome, horaInicio, horaFim,matriculaId);
                treinos.add(t);
            }

            if(treinos.isEmpty()){
                throw new NotFoundException("Nenhum treino encontrado para a matrícula de id = " + id);
            }
        }catch (SQLException e) {
            throw new DBException("Erro ao correlacionar");
        } finally {
            DB.closeStatement(st);
            DB.closeResultSet(rs);
        }
        return treinos;
    }
}
