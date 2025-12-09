package model.dao.impl;

import db.DB;
import model.dao.ClienteDAO;
import model.dao.DAOFactory;
import model.entities.Cliente;
import model.entities.Matricula;
import model.entities.Treino;
import model.exceptions.DBException;
import model.exceptions.NotFoundException;
import model.exceptions.ValidationException;

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
        ResultSet rs = null;
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

            // capturar id criado
            if (linhas > 0) {
                rs = st.getGeneratedKeys();
                if (rs.next()) {
                    newCliente.setId(rs.getInt(1));
                }
            } else {
                throw new DBException("Erro inesperado! Nenhuma linha inserida.");
            }
        } catch (SQLException e) {
            throw new DBException("Erro ao inserir cliente. "+e.getMessage());
        } catch (NullPointerException e){
            throw new DBException("Erro ao procurar matrícula. \n"+e.getMessage());
        }finally {
            DB.closeStatement(st);
            DB.closeResultSet(rs);
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
            if(rs.next()){
                id = rs.getInt("id");
                String nome = rs.getString("nome");
                String email = rs.getString("email");
                cliente = new Cliente(id, nome, email);
                cliente.setMatricula(DAOFactory.criaMatriculaDAO().findById(rs.getInt("matricula_id")));

                return cliente;
            }
            // excecao para caso nao ache o cliente
            throw new NotFoundException("Cliente não encontrado para o ID = " + id);

        }catch (SQLException e) {
            throw new DBException("Erro ao buscar cliente. "+e.getMessage());
        } finally {
            DB.closeStatement(st);
            DB.closeResultSet(rs);
        }
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
            throw new DBException("Erro ao buscar lista de clientes:. "+e.getMessage());
        } finally {
            DB.closeStatement(st);
            DB.closeResultSet(rs);
        }

        return clientes;
    }

    @Override
    public void update(Cliente cliente, Integer id) {
        PreparedStatement st = null;
        validarCliente(cliente);
        try{
            st = conn.prepareStatement("UPDATE CLIENTE SET nome=?, email=? WHERE id=?");
            st.setString(1, cliente.getNome());
            st.setString(2, cliente.getEmail());
            st.setInt(3, id);

            int linhas = st.executeUpdate();
            if(linhas == 0){
                throw new NotFoundException("Nenhum cliente encontrado com o id="+id);
            }

            System.out.println("Linhas afetadas: " + linhas);

        } catch (SQLException e) {
            throw new DBException("Erro ao atualizar cliente. "+e.getMessage());
        } finally {
            DB.closeStatement(st);
        }
    }

    @Override
    public void deleteById(Integer id) {
        PreparedStatement st = null;
        try{
            st = conn.prepareStatement("DELETE FROM Cliente WHERE id=?");
            st.setInt(1, id);

            int linhas = st.executeUpdate();

            if (linhas == 0) {
                throw new NotFoundException("Nenhum cliente encontrado com o id=" + id);
            }
            System.out.println("Linhas afetadas: " + linhas);
        } catch (SQLException e) {
            throw new DBException("Erro ao deletar cliente."+e.getMessage());
        } finally {
            DB.closeStatement(st);
        }
    }

    // metodo auxiliar para ser usado no update
    public void validarCliente(Cliente cliente){

        String nome = cliente.getNome();
        String email = cliente.getEmail();

        if(nome == null || nome.trim().isEmpty() || email == null || email.trim().isEmpty()){
            throw new ValidationException("Valores vazios nao podem ser adicionados ao banco");
        }
        if(!nome.matches("^[A-Za-zÀ-ÿ ]+$")){
            throw new ValidationException("Insira um nome válido");
        }
        if(!email.contains("@")){
            throw new ValidationException("Email deve conter @");
        }
        if(!(email.endsWith(".com") || email.endsWith(".com.br"))){
            throw new ValidationException("Email invalido");
        }
    }
}
