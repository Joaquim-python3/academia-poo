import model.dao.DAOFactory;
import model.entities.Cliente;
import model.entities.Matricula;
import model.entities.Treino;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
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
        System.out.println("5 - procurar cliente (id)");
        System.out.println("6 - criar matricula");
        System.out.println("7 - criar cliente");
        System.out.println("8 - deleter cliente (id)");
        System.out.println("9 - atualizar cliente (id)");
        int inputValue = sc.nextInt();
        sc.nextLine(); // limpar buffer (ler o enter)
        switch (inputValue){
            case 1:
                List<Cliente> clienteList = DAOFactory.criaClienteDAO().findAll();
                for(Cliente c : clienteList){System.out.println(c.toString());}
//
//                List<Matricula> matriculaList = DAOFactory.criaMatriculaDAO().findAll();
//                for(Matricula m : matriculaList){System.out.println(m.toString());}

//                List<Treino> treinoList = DAOFactory.criaTreinoDAO().findAll();
//                for(Treino t : treinoList){System.out.println(t.toString());}
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
            case 5:
                int inputCliente = sc.nextInt();
                Cliente cliente = DAOFactory.criaClienteDAO().findById(inputCliente);
                System.out.println(cliente.toString());
                break;
            case 6:
                String inputDataInicio = sc.next();  // 2025-12-04
                String inputDataFim = sc.next();     // 2025-12-29

                Matricula newMatricula = new Matricula();
                newMatricula.setDataInicio(LocalDate.parse(inputDataInicio));
                newMatricula.setDataFim(LocalDate.parse(inputDataFim));

                DAOFactory.criaMatriculaDAO().insert(newMatricula);

                System.out.println(newMatricula.toString());

                break;

            case 7:
                Cliente newCliente = new Cliente();
                String nome = sc.nextLine();
                String email = sc.nextLine();
                int matricula_id = sc.nextInt();

                newCliente.setNome(nome);
                newCliente.setEmail(email);
                newCliente.setMatricula(DAOFactory.criaMatriculaDAO().findById(matricula_id)); // precisa necessariamente já ter uma matrícula

                DAOFactory.criaClienteDAO().insert(newCliente);
                System.out.println(newCliente.toString());

                break;

            case 8:
                int inputDelete = sc.nextInt();
                DAOFactory.criaClienteDAO().deleteById(inputDelete);

                for (Cliente c: DAOFactory.criaClienteDAO().findAll()) {
                    System.out.println(c.toString());
                }

                break;

            case 9:
                Cliente updateCliente = new Cliente();
                int id = sc.nextInt();
                sc.nextLine();
                nome = sc.nextLine();
                email = sc.nextLine();

                updateCliente.setNome(nome);
                updateCliente.setEmail(email);

                DAOFactory.criaClienteDAO().update(updateCliente, id);
                break;
        }

    sc.close();

    }
}