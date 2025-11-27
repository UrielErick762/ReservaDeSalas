package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexao {
    public static Connection getConexaoMySQL() throws ClassNotFoundException, SQLException {
        //MySQL
        Class.forName("com.mysql.cj.jdbc.Driver");
        String URL = "jdbc:mysql://localhost:3306/ReservasdeSalas";
        String USER = "root";
        String PASSWORD = "";
        
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    public static Connection getConexaoPostgresql() throws ClassNotFoundException, SQLException {
        //Postgresql
        Class.forName("org.postgresql.Driver");
        String URL = "jdbc:postgresql://localhost:3306/ReservasdeSalas";
        String USER = "admin";
        String PASSWORD = "admin";
        // Estabelecendo a conexão
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
 }
