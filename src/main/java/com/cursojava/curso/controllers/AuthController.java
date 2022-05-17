package com.cursojava.curso.controllers;


import com.cursojava.curso.dao.usuariosDao;
import com.cursojava.curso.models.Usuarios;
import com.cursojava.curso.utils.JWTUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {

    @Autowired// Hace que la clase UsuarioDaoImp cree un objeto y se comparta | Inyeccion de dependencias
    private usuariosDao usuarios_Dao;

    @Autowired
    private JWTUtil jwtUtil;


    @RequestMapping(value = "api/login", method = RequestMethod.POST)
    public String login(@RequestBody Usuarios usuarios){

        Usuarios usuarioLogueado = usuarios_Dao.obtenerUsuarioPorCredenciales(usuarios);

       if (usuarioLogueado != null) {

           String tokenjwt = jwtUtil.create(String.valueOf(usuarioLogueado.getId()), usuarioLogueado.getEmail());
           return tokenjwt;

       }
       return "Fail";
    }

}


