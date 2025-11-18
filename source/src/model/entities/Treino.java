package model.entities;

import java.util.List;

public class Treino {
    private Integer id;
    private String nome;
    private List<Exercicio> exercicios; // 1 → N

    public Treino() {}

    public Treino(Integer id, String nome, List<Exercicio> exercicios) {
        this.id = id;
        this.nome = nome;
        this.exercicios = exercicios;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public List<Exercicio> getExercicios() {
        return exercicios;
    }

    public void setExercicios(List<Exercicio> exercicios) {
        this.exercicios = exercicios;
    }
}
