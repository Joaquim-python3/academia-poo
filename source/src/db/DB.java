package db;

import java.sql.*;

public class DB {
    static Connection conn = null;
    public static Connection getConnection(){
        try{
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/academiasimples", "root", "1234");
        } catch (SQLException e){
            throw new RuntimeException(e);
        }
        return conn;
    }
    public static void closeConnection(){
        if(conn != null){
            try {
                conn.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public static void closeStatement(Statement st){
        if(st != null){
            try {
                conn.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }
    public static void closeResultSet(ResultSet rs){
        if(rs != null){
            try {
                conn.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }
}