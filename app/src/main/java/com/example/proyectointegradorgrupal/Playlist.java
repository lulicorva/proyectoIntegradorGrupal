package com.example.proyectointegradorgrupal;

public class Playlist {

    private String nombre;
    private Integer Imagen;

    public Playlist() {
    }

    public Playlist(String nombre, Integer imagen) {
        this.nombre = nombre;
        Imagen = imagen;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Integer getImagen() {
        return Imagen;
    }

    public void setImagen(Integer imagen) {
        Imagen = imagen;
    }
}
