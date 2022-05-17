package com.cursojava.curso.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;


@Entity //hacemos refertencia a que es una entidad a la base de datos
@Table(name = "usuarios") //Nombre de la tabla en la base de datos
public class Usuarios {

    //Usaremos Hibernate
    @Id // Indicamos que es la clave primaria | Con @Colum indicamos la columna en la tabla
    @GeneratedValue(strategy = GenerationType.IDENTITY) // funciona para que el id se agregue automaticamente
    @Getter @Setter @Column(name = "id") //Usamos la libreria lombok de maven para los getter y setter la dependencia se agrega en el POM
    private Long id;

    @Getter @Setter @Column(name = "nombre")
    private String nombre;

    @Getter @Setter  @Column(name = "apellido")
    private String apellido;

    @Getter @Setter @Column(name = "email")
    private String email;

    @Getter @Setter @Column(name = "telefono")
    private String telefono;

    @Getter @Setter @Column(name = "password")
    private String password;


}



