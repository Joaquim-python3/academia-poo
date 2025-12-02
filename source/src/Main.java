import model.dao.DAOFactory;
import model.entities.Cliente;
import model.entities.Matricula;
import model.entities.Treino;

import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("selecione alguma opcao");
        System.out.println("1 - listar tudo");
        System.out.println("2 - procurar treino (id)");
        System.out.println("3 - procurar treinos pela matricula (id)");
        System.out.println("4 - procurar matricula (id)");
        int inputValue = sc.nextInt();
        switch (inputValue){
            case 1:
                List<Cliente> clienteList = DAOFactory.criaClienteDAO().findAll();
                for(Cliente c : clienteList){System.out.println(c.toString());}

                List<Matricula> matriculaList = DAOFactory.criaMatriculaDAO().findAll();
                for(Matricula m : matriculaList){System.out.println(m.toString());}

                List<Treino> treinoList = DAOFactory.criaTreinoDAO().findAll();
                for(Treino t : treinoList){System.out.println(t.toString());}
                break;

            case 2:
                int inputTreno = sc.nextInt();
                Treino t1 = DAOFactory.criaTreinoDAO().findById(inputTreno);
                System.out.println(t1.toString());
                break;

            case 3:
                int inputMatricula = sc.nextInt();
                List<Treino> treinosPelaMatricula = DAOFactory.criaTreinoDAO().findTreinosByMatricula(inputMatricula);
                for (Treino t : treinosPelaMatricula){
                    System.out.println(t.toString());
                }
                break;

            case 4:
                inputMatricula = sc.nextInt();
                Matricula m = DAOFactory.criaMatriculaDAO().findById(inputMatricula);
                System.out.println(m.toString());
                break;
        }



    }
}