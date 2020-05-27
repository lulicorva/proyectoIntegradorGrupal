package com.example.proyectointegradorgrupal.model;

import java.io.Serializable;
import java.util.IdentityHashMap;

public class Favoritos implements Serializable {

    private String nombre;
    private Integer imagen;
    private String id;

    public Favoritos() {
    }

    public Favoritos(String nombre, Integer imagen) {
        this.nombre = nombre;
        this.imagen = imagen;
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Integer getImagen() {
        return imagen;
    }

    public void setImagen(Integer imagen) {
        this.imagen = imagen;
    }
}
