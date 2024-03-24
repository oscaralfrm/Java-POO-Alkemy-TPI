package model.entities;

public class Libro {

    // Estado: Atributos

    private Long ISBN;
    private String autor;
    private String titulo;
    private String genero;
    private boolean disponibilidad;

    // Comportamiento: Métodos constructores


    public Libro() {
        this.disponibilidad = true;
    }

    // Cuando se crea un libro, éste automáticamente está en true (disponible)

    public Libro(Long ISBN, String autor, String titulo, String genero) {
        this.ISBN = ISBN;
        this.autor = autor;
        this.titulo = titulo;
        this.genero = genero;
        this.disponibilidad = true;
    }

    // Comportamiento: Métodos accesores


    public Long getISBN() {
        return ISBN;
    }

    public void setISBN(Long ISBN) {
        this.ISBN = ISBN;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public boolean isDisponibilidad() {
        return disponibilidad;
    }

    public void setDisponibilidad(boolean disponibilidad) {
        this.disponibilidad = disponibilidad;
    }

    // Comportamiento: Método toString();


    @Override
    public String toString() {
        return "Libro{" +
                "ISBN=" + ISBN +
                ", autor='" + autor + '\'' +
                ", titulo='" + titulo + '\'' +
                ", genero='" + genero + '\'' +
                ", disponibilidad=" + disponibilidad +
                '}';
    }



}
