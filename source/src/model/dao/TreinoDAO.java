package model.dao;

import java.util.List;
import model.entities.Treino;

public interface TreinoDAO {

    void insert(Treino obj);

    Treino findById(Integer id);

    List<Treino> findAll();

    void update(Treino obj, Integer id);

    void deleteById(Integer id);

    List<Treino> findTreinosByMatricula(Integer id);
}
