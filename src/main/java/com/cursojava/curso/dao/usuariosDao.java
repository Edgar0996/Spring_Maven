package com.cursojava.curso.dao;

import com.cursojava.curso.models.Usuarios;

import java.util.List;



public interface usuariosDao {

        List<Usuarios> getUsuarios();


        void eliminar(Long id);

        void registrar(Usuarios usuarios);

        Usuarios obtenerUsuarioPorCredenciales(Usuarios usuarios);

}
