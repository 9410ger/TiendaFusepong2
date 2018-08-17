/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.tienda.controladores;


import com.mycompany.tienda.entities.Persona;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import com.mycompany.tienda.persistencia.PersonaService;

/**
 *
 * @author 9410ger
 */
@RestController
@RequestMapping("/api")
public class PersonaController {
    
    PersonaService ps = new PersonaService();
    
    @RequestMapping(value = "/persona", method = RequestMethod.POST)
    @ResponseStatus(value = HttpStatus.OK)
    public void savePersona(@RequestBody Persona persona){
        //Servicio registrar persona
        ps.savePersona(persona);
    }
    
    @RequestMapping(value = "/persona/{usuario}/{pw}", method = RequestMethod.GET)
    @ResponseBody
    public Boolean userLogin(@PathVariable String usuario,@PathVariable String pw){
        //Servicio ingreso de persona por usuario y contraseña
        return ps.userLogin(usuario, pw);
    }
    
    @RequestMapping(value = "/admin/{usr}/{psw}", method = RequestMethod.GET)
    @ResponseBody
    public Boolean adminLogIn(@PathVariable String usr,@PathVariable String psw){
        //Servicio ingreso de administrador
        return ps.adminLogIn(usr, psw);
    }
    
}
