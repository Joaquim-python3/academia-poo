package model.entities;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Matricula {
    private Integer id;
    private LocalDate dataInicio;
    private LocalDate dataFim;
    private List<Treino> treino = new ArrayList<>(); // 1 -> N

    public Matricula() {}

    public Matricula(Integer id, LocalDate dataInicio, LocalDate dataFim) {
        this.id = id;
        this.dataInicio = dataInicio;
        this.dataFim = dataFim;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public LocalDate getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(LocalDate dataInicio) {
        this.dataInicio = dataInicio;
    }

    public LocalDate getDataFim() {
        return dataFim;
    }

    public void setDataFim(LocalDate dataFim) {
        this.dataFim = dataFim;
    }

    public List<Treino> getTreino() {
        return treino;
    }

    public void setTreino(List<Treino> treino) {
        this.treino = treino;
    }

    @Override
    public String toString() {
        return "Matricula{" +
                "id=" + id +
                ", dataInicio=" + dataInicio +
                ", dataFim=" + dataFim +
                ", treino=" + treino +
                '}';
    }
}
