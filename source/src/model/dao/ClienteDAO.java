package model.dao;

import model.entities.Cliente;
import model.entities.Matricula;

import java.util.List;

public interface ClienteDAO {
    void insert(Cliente cliente);
    Cliente findById(Integer id);
    List<Cliente> findAll();
    void update(Cliente cliente, Integer id);
    void deleteById(Integer id);
}
