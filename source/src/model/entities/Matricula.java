package model.entities;

import java.time.LocalDate;

public class Matricula {
    private Integer id;
    private LocalDate dataInicio;
    private LocalDate dataFim;
    private Treino treino; // 1 → 1

    public Matricula() {}

    public Matricula(Integer id, LocalDate dataInicio, LocalDate dataFim, Treino treino) {
        this.id = id;
        this.dataInicio = dataInicio;
        this.dataFim = dataFim;
        this.treino = treino;
    }

    // getters e setters
}
