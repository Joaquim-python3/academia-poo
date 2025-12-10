package model.entities;

import model.exceptions.HoraInvalidaException;

import java.time.LocalTime;
import java.util.Date;
import java.util.List;

public class Treino {
    private int id;
    private String nome;
    private LocalTime horarioInicio;
    private LocalTime horarioFim;
    private Integer matriculaId;

    public Treino() {}

    public Treino(int id, String nome, LocalTime horarioInicio, LocalTime horarioFim, Integer matriculaId) {
        this.id = id;
        this.nome = nome;
        this.horarioInicio = horarioInicio;
        this.horarioFim = horarioFim;
        this.matriculaId = matriculaId;
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

    public Integer getMatriculaId() {
        return matriculaId;
    }

    public void setMatriculaId(Integer matriculaId) {
        this.matriculaId = matriculaId;
    }

    public static void validarHoras(LocalTime inicio, LocalTime fim) throws HoraInvalidaException {
        if (inicio.isAfter(fim)) {
            throw new HoraInvalidaException("A hora inicial não pode ser maior do que a hora final!");
        }
    }


    @Override
    public String toString() {
        return "Treino{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", horarioInicio=" + horarioInicio +
                ", horarioFim=" + horarioFim +
                ", matriculaId= "+ matriculaId+
                '}';
    }
}
