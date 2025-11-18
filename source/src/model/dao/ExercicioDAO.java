package model.dao;

import java.util.List;
import model.entities.Exercicio;

public interface ExercicioDAO {

    void insert(Exercicio obj);

    Exercicio findById(Integer id);

    List<Exercicio> findAll();

    List<Exercicio> findByTreino(Integer treinoId);

    void update(Exercicio obj);

    void deleteById(Integer id);
}
