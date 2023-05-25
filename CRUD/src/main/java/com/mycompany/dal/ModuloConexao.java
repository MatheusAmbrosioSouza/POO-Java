package com.mycompany.dal;
import java.sql.*;
public class ModuloConexao {
    //método para estabelecer conexão com o BD
    public static Connection conector(){
        java.sql.Connection conexao = null;
        //chamar o driver
        String driver = "org.postgresql.Driver";
        //Armazenando infos do banco
        String url = "jdbc:postgresql://localhost:5432/MatheusDB";
        String user = "postgres";
        String password = "157bolado";
        //Estabelecer a conexao com o DB
        try {
            Class.forName(driver);
            conexao = DriverManager.getConnection(url, user, password);
            return conexao;
        } catch (Exception e) {
            return null;
        }
    }
}




