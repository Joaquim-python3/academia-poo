package model.dao;

import db.DB;
import model.dao.impl.ClienteDAOJDBC;

public class DAOFactory {
    // criar um DAOfactory para cada entidade
    public static ClienteDAO criaClienteDAO(){
        return new ClienteDAOJDBC(DB.getConnection());
    }
}
