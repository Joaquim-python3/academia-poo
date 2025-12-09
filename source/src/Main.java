import model.dao.DAOFactory;
import model.entities.Cliente;
import model.entities.Matricula;
import model.entities.Treino;
import model.exceptions.ValidationException;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("Selecione uma opção");
        System.out.println("1 - Listar clientes");
        System.out.println("2 - Procurar cliente (id)");
        System.out.println("3 - Criar cliente");
        System.out.println("4 - Atualizar cliente (id)");
        System.out.println("5 - Deletar cliente (id)");

        System.out.println("6 - Procurar matrícula (id)");
        System.out.println("7 - Criar matrícula");
        System.out.println("8 - Atualizar matrícula (id)");
        System.out.println("9 - Deletar matrícula (id)");

        System.out.println("10 - Procurar treino (id)");
        System.out.println("11 - Procurar treinos por matrícula (id)");
        System.out.println("12 - Criar treino");
        System.out.println("13 - Atualizar treino (id)");
        System.out.println("14 - Deletar treino (id)");

        try {
            int inputValue = sc.nextInt();
            sc.nextLine(); // limpar buffer

            switch (inputValue) {

                // CLIENTE

                case 1:
                    List<Cliente> clienteList = DAOFactory.criaClienteDAO().findAll();
                    for (Cliente c : clienteList) System.out.println(c);
                    break;

                case 2:
                    System.out.println("Digite o id do cliente:");
                    int inputCliente = sc.nextInt();
                    Cliente cliente = DAOFactory.criaClienteDAO().findById(inputCliente);
                    System.out.println(cliente);
                    break;

                case 3:
                    Cliente newCliente = new Cliente();
                    System.out.println("Digite o nome:");
                    String nome = sc.nextLine();
                    System.out.println("Digite o email:");

                    String email = sc.nextLine();
                    System.out.println("Digite a matricula (ja deve existir uma matricula):");
                    int matricula_id = sc.nextInt();

                    newCliente.setNome(nome);
                    newCliente.setEmail(email);
                    newCliente.setMatricula(DAOFactory.criaMatriculaDAO().findById(matricula_id));

                    DAOFactory.criaClienteDAO().insert(newCliente);
                    System.out.println(newCliente);
                    break;

                case 4:
                    Cliente updateCliente = new Cliente();
                    System.out.println("Digite o id do cliente para ser atualizado:");
                    int id = sc.nextInt();
                    sc.nextLine();
                    System.out.println("Digite o novo nome:");
                    nome = sc.nextLine();
                    System.out.println("Digite o novo email:");
                    email = sc.nextLine();

                    updateCliente.setNome(nome);
                    updateCliente.setEmail(email);

                    DAOFactory.criaClienteDAO().update(updateCliente, id);
                    break;

                case 5:
                    System.out.println("Digite o id do cliente para deletar:");
                    int inputDelete = sc.nextInt();
                    DAOFactory.criaClienteDAO().deleteById(inputDelete);
                    break;

                // MATRÍCULA

                case 6:
                    System.out.println("Digite o id da matricula:");
                    int inputMatricula = sc.nextInt();
                    Matricula m = DAOFactory.criaMatriculaDAO().findById(inputMatricula);
                    System.out.println(m);
                    break;

                case 7:
                    System.out.println("Digite a data de inicio (yyyy-MM-dd):");
                    String inputDataInicio = sc.next();
                    System.out.println("Digite a data de fim (yyyy-MM-dd):");
                    String inputDataFim = sc.next();

                    Matricula newMatricula = new Matricula();
                    newMatricula.setDataInicio(LocalDate.parse(inputDataInicio));
                    newMatricula.setDataFim(LocalDate.parse(inputDataFim));

                    DAOFactory.criaMatriculaDAO().insert(newMatricula);
                    System.out.println(newMatricula);
                    break;

                case 8:
                    Matricula updateMatricula = new Matricula();
                    System.out.println("Digite o id da matricula para ser atualizado");
                    id = sc.nextInt();
                    System.out.println("Digite a data de inicio (yyyy-MM-dd):");
                    inputDataInicio = sc.next();
                    System.out.println("Digite a data de fim (yyyy-MM-dd):");
                    inputDataFim = sc.next();

                    try{
                        updateMatricula.setDataInicio(LocalDate.parse(inputDataInicio));
                        updateMatricula.setDataFim(LocalDate.parse(inputDataFim));
                    } catch (DateTimeParseException e){
                        throw new ValidationException("Formato errado passado");
                    }

                    DAOFactory.criaMatriculaDAO().update(updateMatricula, id);
                    break;

                case 9:
                    System.out.println("Digite o id da matricula para deletar:");
                    int inputIdMatricula = sc.nextInt();
                    DAOFactory.criaMatriculaDAO().deleteById(inputIdMatricula);
                    break;

                // TREINO

                case 10:
                    System.out.println("Digite o id do treino:");
                    int inputTreino = sc.nextInt();
                    Treino t1 = DAOFactory.criaTreinoDAO().findById(inputTreino);
                    System.out.println(t1);
                    break;

                case 11:
                    System.out.println("Digite o id da matricula para procurar os treinos:");
                    inputMatricula = sc.nextInt();
                    List<Treino> treinosPelaMatricula = DAOFactory.criaTreinoDAO().findTreinosByMatricula(inputMatricula);

                    for (Treino t : treinosPelaMatricula) System.out.println(t);
                    break;

                case 12:
                    Treino treino = new Treino();
                    System.out.println("Digite o id da matricula relacionada ao treino:");
                    matricula_id = sc.nextInt();
                    System.out.println("Digite o nome do treino:");
                    sc.nextLine();
                    nome = sc.nextLine();
                    System.out.println("Digite a hora de inicio do treino (HH:mm):");
                    String inputHoraInicio = sc.nextLine();
                    System.out.println("Digite a hora de fim do treino (HH:mm):");
                    String inputHoraFim = sc.nextLine();

                    treino.setNome(nome);
                    try{
                        treino.setHorarioInicio(LocalTime.parse(inputHoraInicio));
                        treino.setHorarioFim(LocalTime.parse(inputHoraFim));
                    } catch (DateTimeParseException e){
                        throw new ValidationException("Formato errado passado");
                    }

                    treino.setMatriculaId(matricula_id);

                    DAOFactory.criaTreinoDAO().insert(treino);
                    break;

                case 13:
                    Treino updateTreino = new Treino();
                    System.out.println("Digite o id do treino para ser atualizado:");
                    id = sc.nextInt();
                    sc.nextLine();

                    System.out.println("Digite o nome do treino:");
                    nome = sc.nextLine();
                    System.out.println("Digite a hora de inicio do treino (HH:mm):");
                    inputHoraInicio = sc.nextLine();
                    System.out.println("Digite a hora de fim do treino (HH:mm):");
                    inputHoraFim = sc.nextLine();

                    updateTreino.setNome(nome);
                    updateTreino.setHorarioInicio(LocalTime.parse(inputHoraInicio));
                    updateTreino.setHorarioFim(LocalTime.parse(inputHoraFim));

                    DAOFactory.criaTreinoDAO().update(updateTreino, id);
                    break;

                case 14:
                    System.out.println("Digite o id do treino para ser deletado:");

                    int inputIdTreino = sc.nextInt();
                    DAOFactory.criaTreinoDAO().deleteById(inputIdTreino);
                    break;
            }
        }catch (InputMismatchException e){
            System.out.println("Valor inválido digitado!");
        }

        sc.close();
    }
}
