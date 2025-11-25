package model.entities;

public class Cliente extends Pessoa{
    private Matricula matricula; // 1 -> 1

    public Cliente(Integer id, String nome, String email) {
        super(id, nome, email);
    }

    public Cliente() {
    }

    public Matricula getMatricula() {
        return matricula;
    }

    public void setMatricula(Matricula matricula) {
        this.matricula = matricula;
    }

    @Override
    public String toString() {
        return super.toString()+"Cliente{" +
                "matricula=" + matricula +
                '}';
    }
}
