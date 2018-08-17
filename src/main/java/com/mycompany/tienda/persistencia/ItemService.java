/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.tienda.persistencia;

import com.mycompany.tienda.entities.Item;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author germa_000
 */
public class ItemService {
    
    ConnectionPostgres cn;
    Connection conex;
    
    public ItemService(){
        cn = new ConnectionPostgres();
        try {
            conex = cn.connect();
        } catch (ClassNotFoundException | IllegalAccessException ex) {
            Logger.getLogger(ItemService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void saveItem(Item item){
        String sql = "insert into items (nombre,descripcion,valor,iva,envio,foto,cantidad) values(?,?,?,?,?,?,?)";
        //FileInputStream fis = new FileInputStream(imagen);
        try {
            PreparedStatement ps = conex.prepareStatement(sql);
            ps.setString(1, item.getNombre());
            ps.setString(2, item.getDescripcion());
            ps.setInt(3, item.getValor());
            ps.setFloat(4, item.getIva());
            ps.setString(5, item.getEnvio());
            ps.setString(6,item.getFoto());
            ps.setInt(7, item.getCantidad());
            ps.executeUpdate();
            System.out.println("Guardo el item");
        } catch (SQLException ex) {
            Logger.getLogger(ItemService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public Item getItemByName(String itemName){
        String sql = "select * from items where nombre = ? ";
        Item it = new Item();
        try {
            PreparedStatement ps = conex.prepareStatement(sql);
            ps.setString(1, itemName);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                it.setNombre(rs.getString(2));
                it.setDescripcion(rs.getString(3));
                it.setValor(rs.getInt(4));
                it.setIva(rs.getFloat(5));
                it.setEnvio(rs.getString(6));
                it.setFoto(rs.getString(7));
                it.setCantidad(rs.getInt(8));
            }
        } catch (SQLException ex) {
            Logger.getLogger(ItemService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return it;
    }
    
    public List<Item> getAllItems(){
        List<Item> items = new ArrayList<>();
        String sql = "select * from items";
        try {
            PreparedStatement ps = conex.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                Item it = new Item();
                it.setId(rs.getInt(1));
                it.setNombre(rs.getString(2));
                it.setDescripcion(rs.getString(3));
                it.setValor(rs.getInt(4));
                it.setIva(rs.getFloat(5));
                it.setEnvio(rs.getString(6));
                it.setFoto(rs.getString(7));
                it.setCantidad(rs.getInt(8));
                items.add(it);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ItemService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return items;
    }
    
    public Item getItemById(int itemId){
        Item it = new Item();
        String sql = "select * from items where id = ?";
        PreparedStatement ps;
        try {
            ps = conex.prepareStatement(sql);
            ps.setInt(1, itemId);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                it.setNombre(rs.getString(2));
                it.setDescripcion(rs.getString(3));
                it.setValor(rs.getInt(4));
                it.setIva(rs.getFloat(5));
                it.setEnvio(rs.getString(6));
                it.setFoto(rs.getString(7));
                it.setCantidad(rs.getInt(8));
            }
        } catch (SQLException ex) {
            Logger.getLogger(ItemService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return it;
        
    }
    
    public void deleteItemById(int itemId){
        String sql = "delete from items where id = ?";
        try {
            PreparedStatement ps = conex.prepareStatement(sql);
            ps.setInt(1, itemId);
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ItemService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void updateItem(Item itemUpdated){
        Item oldItem = getItemById(itemUpdated.getId());
        if(!oldItem.getNombre().equals(itemUpdated.getNombre())){
            updateSql("nombre", itemUpdated.getId(), itemUpdated.getNombre());
        }if(!oldItem.getDescripcion().equals(itemUpdated.getDescripcion())){
            updateSql("descripcion", itemUpdated.getId(), itemUpdated.getDescripcion());
        }if(oldItem.getValor() != itemUpdated.getValor()){
            updateSql("valor", itemUpdated.getId(), itemUpdated.getValor());
        }if(oldItem.getIva() != itemUpdated.getIva()){
            updateSql("iva", itemUpdated.getId(), itemUpdated.getIva());
        }if(oldItem.getFoto() != itemUpdated.getFoto()){
            updateSql("foto", itemUpdated.getId(), itemUpdated.getFoto());
        }if(oldItem.getCantidad() != itemUpdated.getCantidad()){
            updateSql("cantidad", itemUpdated.getId(), itemUpdated.getCantidad());
        }
    }
    
    public void saveCompra(Item item){
        String sql = "insert into itemsComprados(nombre,descripcion,valor,iva,envio) values(?,?,?,?,?)";
        try {
            PreparedStatement ps = conex.prepareStatement(sql);
            ps.setString(1, item.getNombre());
            ps.setString(2, item.getDescripcion());
            ps.setInt(3, item.getValor());
            ps.setFloat(4, item.getIva());
            ps.setString(5, item.getEnvio());
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ItemService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void updateSql(String column,int itemId,Object value){
        String sql = "update items set "+column+" = ?"+" where id =?";
        try {
            PreparedStatement ps = conex.prepareStatement(sql);
            ps.setObject(1, value);
            ps.setInt(2, itemId);
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ItemService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public List<Item> soldItems(){
        List<Item> soldItems = new ArrayList<>();
        String sql =  "select * from itemscomprados";
        try {
            PreparedStatement ps = conex.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                Item it = new Item();
                it.setId(rs.getInt(1));
                it.setNombre(rs.getString(2));
                it.setDescripcion(rs.getString(3));
                it.setValor(rs.getInt(4));
                it.setIva(rs.getFloat(5));
                it.setEnvio(rs.getString(6));
                soldItems.add(it);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(ItemService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return soldItems;
    }
    
    
    
}
