package model.dao;

import db.DB;
import model.dao.impl.ClienteDAOJDBC;
import model.dao.impl.MatriculaDAOJDBC;
import model.dao.impl.TreinoDAOJDBC;

public class DAOFactory {
    // criar um DAOfactory para cada entidade
    public static ClienteDAO criaClienteDAO(){
        return new ClienteDAOJDBC(DB.getConnection());
    }
    public static MatriculaDAO criaMatriculaDAO(){return new MatriculaDAOJDBC(DB.getConnection());}
    public static TreinoDAO criaTreinoDAO(){return new TreinoDAOJDBC(DB.getConnection());}
}
