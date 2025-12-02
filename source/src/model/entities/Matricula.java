package model.entities;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Matricula {
    private Integer id;
    private Date dataInicio;
    private Date dataFim;
    private List<Treino> treino = new ArrayList<>(); // 1 -> N

    public Matricula() {}

    public Matricula(Integer id, Date dataInicio, Date dataFim) {
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

    public Date getDataInicio() {return dataInicio;}

    public void setDataInicio(Date dataInicio) {
        this.dataInicio = dataInicio;
    }

    public Date getDataFim() {
        return dataFim;
    }

    public void setDataFim(Date dataFim) {
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
