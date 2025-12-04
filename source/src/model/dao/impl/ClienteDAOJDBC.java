package model.dao.impl;

import db.DB;
import model.dao.ClienteDAO;
import model.dao.DAOFactory;
import model.entities.Cliente;
import model.entities.Matricula;
import model.entities.Treino;

import java.sql.*;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ClienteDAOJDBC implements ClienteDAO {
    private Connection conn;

    public ClienteDAOJDBC(Connection conn) {
        this.conn = conn;
    }

    @Override
    public void insert(Cliente newCliente) {
        PreparedStatement st = null;
        try{
            st = conn.prepareStatement(
                    "INSERT INTO Cliente (nome, email, matricula_id) VALUES (?,?,?)",
                    Statement.RETURN_GENERATED_KEYS
            );

            st.setString(1, newCliente.getNome());
            st.setString(2, newCliente.getEmail());
            st.setInt(3, newCliente.getMatricula().getId()); // precisa necessariamente já ter uma matrícula

            int linhas = st.executeUpdate();
            System.out.println("Linhas afetadas: " + linhas);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            DB.closeStatement(st);
        }
    }

    @Override
    public Cliente findById(Integer id) {
        PreparedStatement st = null;
        ResultSet rs = null;
        Cliente cliente = new Cliente();
        try{
            st = conn.prepareStatement("select * from cliente where id=?");
            st.setInt(1,id);
            rs = st.executeQuery();
            while(rs.next()){
                id = rs.getInt("id");
                String nome = rs.getString("nome");
                String email = rs.getString("email");
                cliente = new Cliente(id, nome, email);
                cliente.setMatricula(DAOFactory.criaMatriculaDAO().findById(rs.getInt("matricula_id")));

            }
        }catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            DB.closeStatement(st);
            DB.closeResultSet(rs);
        }

        return cliente;
    }

    @Override
    public List<Cliente> findAll() {
        PreparedStatement st = null;
        ResultSet rs = null;
        List<Cliente> clientes = new ArrayList<>();
        try{
            st = conn.prepareStatement("select * from cliente");
            rs = st.executeQuery();

            while(rs.next()){

                int id = rs.getInt("id");
                String nome = rs.getString("nome");
                String email = rs.getString("email");
                Cliente c = new Cliente(id, nome, email);
                c.setMatricula(DAOFactory.criaMatriculaDAO().findById(rs.getInt("matricula_id")));
                clientes.add(c);
            }
        }catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            DB.closeStatement(st);
            DB.closeResultSet(rs);
        }

        return clientes;
    }

    @Override
    public void update(Cliente cliente) {

    }

    @Override
    public void deleteById(Integer id) {

    }
}
