package com.example.proyectointegradorgrupal.model;

import java.io.Serializable;

public class Favoritos implements Serializable {

    private String nombre;
    private Integer imagen;

    public Favoritos() {
    }

    public Favoritos(String nombre, Integer imagen) {
        this.nombre = nombre;
        this.imagen = imagen;
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
