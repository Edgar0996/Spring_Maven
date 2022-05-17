package com.cursojava.curso.controllers;


import com.cursojava.curso.dao.usuariosDao;
import com.cursojava.curso.models.Usuarios;
import com.cursojava.curso.utils.JWTUtil;
import de.mkammerer.argon2.Argon2;
import de.mkammerer.argon2.Argon2Factory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController //Para indicar que será un controlador
public class UsuarioController {


    @Autowired // Hace que la clase UsuarioDaoImp cree un objeto y se comparta | Inyrccion de dependencias
    private usuariosDao usuarios_Dao;

    @Autowired
    private JWTUtil jwtUtil;

    @RequestMapping(value = "usuario/{id}")
        public Usuarios getUsuario(@PathVariable Long id){

            Usuarios usuario = new Usuarios();
            usuario.setId(id);
            usuario.setNombre("Juan");
            usuario.setApellido("Perez");
            usuario.setEmail("user@gmail.com");
            usuario.setPassword("pasdasd");
            return usuario;

        }
    //BASE de datos
    @RequestMapping(value = "api/usuarios", method = RequestMethod.GET)
    public List <Usuarios>  getUsuario(@RequestHeader(value="Authorization") String token){

        if (!validarToken(token)){
            return null;
        }

        return usuarios_Dao.getUsuarios();

    }




    private boolean validarToken(String token){

        String usuarioId = jwtUtil.getKey(token);
        return usuarioId != null;
    }


    @RequestMapping(value = "api/usuarios", method = RequestMethod.POST)
    public void  registrarUsuario(@RequestBody Usuarios usuarios){
        //Se deve cifrar la contraseña antes de regresar todos los datos usamos Argon2 | agregamos la dependencia en el POM


        Argon2 argon2= Argon2Factory.create(Argon2Factory.Argon2Types.ARGON2id);
        String hash = argon2.hash(1,1024,1, usuarios.getPassword());
        usuarios.setPassword(hash);
        //Tratamos la contraseña y la ciframos a un has
        usuarios_Dao.registrar(usuarios);

    }


    @RequestMapping(value = "usuario3")
    public Usuarios editar(){

        Usuarios usuario = new Usuarios();
        usuario.setNombre("Juan");
        usuario.setApellido("Perez");
        usuario.setEmail("user@gmail.com");
        usuario.setPassword("pasdasd");
        return usuario;

    }
    @RequestMapping(value = "usuari4o")
    public Usuarios agregar(){

        Usuarios usuario = new Usuarios();
        usuario.setNombre("Juan");
        usuario.setApellido("Perez");
        usuario.setEmail("user@gmail.com");
        usuario.setPassword("pasdasd");
        return usuario;

    }
    @RequestMapping(value = "api/usuarios/{id}", method = RequestMethod.DELETE)

    public void eliminar(@RequestHeader(value="Authorization") String token, @PathVariable Long id){
        if (!validarToken(token)){

            return;
        }
        usuarios_Dao.eliminar(id);
    }




}
