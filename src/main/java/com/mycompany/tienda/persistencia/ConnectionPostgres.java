/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.tienda.persistencia;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Connection;

/**
 *
 * @author fs20165
 */
public class ConnectionPostgres {
    
    private final String url = "jdbc:postgresql://localhost:5432/tienda2";
    private final String user = "postgres";
    private final String password = "9410ger";
    
    /*private final String url = "jdbc:postgresql://ec2-23-23-216-40.compute-1.amazonaws.com:5432/d2lk7qf8nbgpq6?sslmode=require";
    private final String user = "wswjdoemjhhwqx";
    private final String password = "7547516f77e59d901929e95b2d4e496421de4346c20fba32d0fe4c9984bb488d";*/
    
     public Connection connect() throws ClassNotFoundException, IllegalAccessException {
        Connection conn = null;
       
        try {
            Class.forName("org.postgresql.Driver");
            conn = DriverManager.getConnection(url, user, password);
            System.out.println("Connected to the PostgreSQL server successfully.");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
 
        return conn;
    }
    
}
