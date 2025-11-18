package model.entities;

public class Cliente extends Pessoa{
    private Matricula matricula; // 1 -> 1

    public Cliente(Integer id, String nome, String email) {
        super(id, nome, email);
    }

    @Override
    public double calcularMensalidade() {
        return 0;
    }

    public Matricula getMatricula() {
        return matricula;
    }

    public void setMatricula(Matricula matricula) {
        this.matricula = matricula;
    }
}
