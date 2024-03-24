package model.entities;

import model.transactionals.Prestamo;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Usuario {

    // Estado: Atributos

    private Long numeroIdentificacion;
    private String nombre;
    private Prestamo prestamo;
    private List<Libro> librosPrestados;


    // Comportamiento: Métodos constructores

    // Se crea un usuario estando con sus atributos prestamo en null
    // y librosPrestados sólo se crea en memoria la instancia de la colección.
    // Estos datos serán cargados después.

    public Usuario() {
    }

    public Usuario(Long numeroIdentificacion, String nombre) {
        this.numeroIdentificacion = numeroIdentificacion;
        this.nombre = nombre;
        this.prestamo = null;
        this.librosPrestados = new ArrayList<>();
    }

    // Comportamiento: Métodos accesores

    public Long getNumeroIdentificacion() {
        return numeroIdentificacion;
    }

    public void setNumeroIdentificacion(Long numeroIdentificacion) {
        this.numeroIdentificacion = numeroIdentificacion;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Prestamo getPrestamo() {
        return prestamo;
    }

    public void setPrestamo(Prestamo prestamo) {
        this.prestamo = prestamo;
    }

    public List<Libro> getLibrosPrestados() {
        return librosPrestados;
    }

    public void setLibrosPrestados(List<Libro> librosPrestados) {
        this.librosPrestados = librosPrestados;
    }


    // Comportamiento: Método toString()


    @Override
    public String toString() {
        return "Usuario{" +
                "numeroIdentificacion=" + numeroIdentificacion +
                ", nombre='" + nombre + '\'' +
                ", prestamo=" + prestamo +
                ", librosPrestados=" + librosPrestados +
                '}';
    }


}
