package model.dao.impl;

import model.dao.ClienteDAO;
import model.entities.Cliente;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ClienteDAOJDBC implements ClienteDAO {
    private Connection conn;

    public ClienteDAOJDBC(Connection conn) {
        this.conn = conn;
    }

    @Override
    public void insert(Cliente cliente) {

    }

    @Override
    public Cliente findById(Integer id) {
        return null;
    }

    @Override
    public List<Cliente> findAll() {
        PreparedStatement st = null;
        ResultSet rs = null;
        List<Cliente> clientes = new ArrayList<>();

        return clientes;
    }

    @Override
    public void update(Cliente cliente) {

    }

    @Override
    public void deleteById(Integer id) {

    }
}
