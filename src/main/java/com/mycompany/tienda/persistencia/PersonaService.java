/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.tienda.persistencia;

import com.mycompany.tienda.entities.Persona;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author germa_000
 */
public class PersonaService {
    
    ConnectionPostgres cn;
    Connection conex;
    PreparedStatement ps;
    
    public PersonaService(){
        cn = new ConnectionPostgres();
        try {
            conex = cn.connect();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(PersonaService.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(PersonaService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void savePersona(Persona persona){
        String sql = "insert into usuarios (nombre,correo,usuario,pw) values(?,?,?,?)";
        try {
            ps = conex.prepareStatement(sql);
            ps.setString(1, persona.getNombre());
            ps.setString(2, persona.getCorreo());
            ps.setString(3, persona.getUsuario());
            ps.setString(4, persona.getPw());
            ps.executeUpdate();
            System.out.println("Registró al usuario");
        } catch (SQLException ex) {
            Logger.getLogger(PersonaService.class.getName()).log(Level.SEVERE, null, ex);
        }
            
    }
    
    public Boolean userLogin(String usuario,String pw){
        String sql = "select * from usuarios where usuario = ? and pw = ?";
        Persona p = new Persona();
        try {
            ps = conex.prepareStatement(sql);
            ps.setString(1, usuario);
            ps.setString(2, pw);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                p.setNombre(rs.getString(1));
                p.setCorreo(rs.getString(2));
                p.setUsuario(rs.getString(3));
                p.setPw(rs.getString(4));
            }
        } catch (SQLException ex) {
            Logger.getLogger(PersonaService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return p == null;
    }
    
    public Boolean adminLogIn(String usuario,String pw){
        String sql = "select * from admin where usr = ? and psw = ?";
        Persona p = new Persona();
        try {
            ps = conex.prepareStatement(sql);
            ps.setString(1, usuario);
            ps.setString(2, pw);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                p.setUsuario(rs.getString(1));
                p.setPw(rs.getString(2));
            }
        } catch (SQLException ex) {
            Logger.getLogger(PersonaService.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return p == null;
            
    }
    
    
    
}
