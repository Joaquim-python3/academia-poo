import model.dao.DAOFactory;
import model.entities.Cliente;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Cliente> clienteList = DAOFactory.criaClienteDAO().findAll();
        for(Cliente c : clienteList){
            System.out.println(c.toString());
        }
    }
}