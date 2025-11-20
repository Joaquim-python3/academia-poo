package model.entities;

import java.time.LocalTime;
import java.util.List;

public class Treino {
    private int id;
    private String nome;
    private LocalTime horarioInicio;
    private LocalTime horarioFim;

    public Treino() {}

    public Treino(int id, String nome, LocalTime horarioInicio, LocalTime horarioFim) {
        this.id = id;
        this.nome = nome;
        this.horarioInicio = horarioInicio;
        this.horarioFim = horarioFim;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public LocalTime getHorarioInicio() {
        return horarioInicio;
    }

    public void setHorarioInicio(LocalTime horarioInicio) {
        this.horarioInicio = horarioInicio;
    }

    public LocalTime getHorarioFim() {
        return horarioFim;
    }

    public void setHorarioFim(LocalTime horarioFim) {
        this.horarioFim = horarioFim;
    }

    @Override
    public String toString() {
        return "Treino{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", horarioInicio=" + horarioInicio +
                ", horarioFim=" + horarioFim +
                '}';
    }
}
