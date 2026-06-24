package com.danielvelasco.apirest.apirest.Entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

//Se le añade la anotación Entity para indicar a Spring que es una tabla de nuestra BD
@Entity
public class Producto {
    
    
    @Id //Se la añade esta anotación para que sepa que es un ID
    @GeneratedValue(strategy = GenerationType.IDENTITY) //Se la añade esta anotación para que se incremente automaticamente el ID
    //Creamos los atributos de esta clase Entidad, para este caso sería los campos de la tabla de nuestra BD
    private long id; //Utilizamos long por si en algun momento son demasiados ID
    private String nombre;
    private double precio;

    //Y generamos los Getters y los Setters
    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }
    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public double getPrecio() {
        return precio;
    }
    public void setPrecio(double precio) {
        this.precio = precio;
    } 

    
}
