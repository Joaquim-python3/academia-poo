package model.entities;

import model.exceptions.EmailInvalidoException;

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

    private static final String EMAIL_PATTERN =
            "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                    + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

    public static void validarEmail(String email) throws EmailInvalidoException {
        if (email == null || !email.matches(EMAIL_PATTERN)) {
            throw new EmailInvalidoException("Email inválido!");
        }
    }

    @Override
    public String toString() {
        return super.toString()+"Cliente{" +
                "matricula=" + matricula +
                '}';
    }
}
