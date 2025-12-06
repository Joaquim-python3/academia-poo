package model.dao;

import java.util.List;
import model.entities.Matricula;

public interface MatriculaDAO {

    void insert(Matricula obj);

    Matricula findById(Integer id);

    List<Matricula> findAll();

    void update(Matricula obj, Integer id);

    void deleteById(Integer id);
}
