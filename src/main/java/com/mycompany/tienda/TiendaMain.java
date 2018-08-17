/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.tienda;




import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jmx.JmxAutoConfiguration;
/**
 *
 * @author fs20165
 */
@SpringBootApplication(exclude = {JmxAutoConfiguration.class})
public class TiendaMain {
    
    public static void main(String[] args){
        SpringApplication.run(TiendaMain.class, args);
    }
    
}
