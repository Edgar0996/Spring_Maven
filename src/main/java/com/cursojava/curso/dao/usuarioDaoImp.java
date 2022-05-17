package com.cursojava.curso.dao;

import com.cursojava.curso.models.Usuarios;
import de.mkammerer.argon2.Argon2;
import de.mkammerer.argon2.Argon2Factory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository //Hace referencia para acceder a la base de datos
@Transactional //referencia a como maneja las consultas a la base de datos
public class usuarioDaoImp implements usuariosDao{

    @PersistenceContext //Referencia a un contexto
    EntityManager entityManager; //sirve para la conexion a la bd

    @Override
    public List<Usuarios> getUsuarios() {

        String query = "FROM Usuarios"; //Aqui va el nombre de la clase ya que usamos la clase, y en la clase indicamos las tablas
       return entityManager.createQuery(query).getResultList();


    }

    @Override
    public void eliminar(Long id) {
        Usuarios usuario = entityManager.find(Usuarios.class,id);
        entityManager.remove(usuario);
    }

    @Override
    public void registrar(Usuarios usuarios) {
        entityManager.merge(usuarios);   /* para guardarlo en la base de datos debemos implementar la entidad en el controlador UsuarioController nus@GeneratedValue(strategy = GenerationType.IDENTITY)*/
    }



    /*
    No olvidar que en las consultas apunta a la clase
    y la clase apunta a la tabla de la
    Base de datos
        ----> Usuarios <------
     */

    @Override
    public Usuarios obtenerUsuarioPorCredenciales(Usuarios usuarios) {
        String query = "FROM Usuarios WHERE email = :email ";


        List<Usuarios> lista = entityManager.createQuery(query)
                .setParameter("email", usuarios.getEmail()) //posible error en el tag s -> name:
                .getResultList();

        if (lista.isEmpty()) {
            return null;
        }

        String passwordHashed = lista.get(0).getPassword(); //Obtenemos el primer elemento(password) de  la lista

        //Verificamos la contraseña con Argon2
        Argon2 argon2 = Argon2Factory.create(Argon2Factory.Argon2Types.ARGON2id);
        if (argon2.verify(passwordHashed, usuarios.getPassword())){

            return lista.get(0);
        }

        return null;
        //return  !lista.isEmpty(); //Retorna si la lista esta vacia o encontro un usuario
    }


}
