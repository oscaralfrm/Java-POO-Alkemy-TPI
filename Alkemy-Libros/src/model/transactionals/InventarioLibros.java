package model.transactionals;

import model.entities.Libro;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class InventarioLibros {

    // Estado: Atributos

    private List<Libro> listaLibros;

    // Comportamiento: Métodos constructores

    public InventarioLibros() {
        this.listaLibros = new ArrayList<>();
    }

    public InventarioLibros(List<Libro> listaLibros) {
        this.listaLibros = listaLibros;
    }

    // Comportamiento: Métodos accesores

    public List<Libro> getListaLibros() {
        return listaLibros;
    }

    public void setListaLibros(List<Libro> listaLibros) {
        this.listaLibros = listaLibros;
    }


    // Comportamiento: Método toString()

    @Override
    public String toString() {
        return "InventarioLibros{" +
                "listaLibros=" + listaLibros +
                '}';
    }


    // Comportamiento: Métodos transaccionales

    // Transaccionales: Métodos de adición y eliminación* de libros

    public void agregarLibroInventario(Libro libro) {

        libro.setDisponibilidad(true);
        this.listaLibros.add(libro);

    }

    // Se procede a modelar el comportamiento de la eliminación de un libro del inventario,
    // aunque no está especificada en los requerimientos específicos.

    public void eliminarLibroInventario(Libro libro) {

        libro.setDisponibilidad(false);
        this.listaLibros.remove(libro);

    }

    // Transaccionales: Métodos de búsqueda y filtro de libros

    // FILTRO : AUTOR

    public List<Libro> librosFiltradosAutor(String autor) {

        // Usamos una expresión lambda (PARADIGMA FUNCIONAL) para devolver de manera
        // más acotada una lista de libros según su autor.

        return this.listaLibros.stream()
                .filter(libro -> libro.getAutor().equalsIgnoreCase(autor))
                .collect(Collectors.toList());

    }


    // FILTRO : GÉNERO

    public List<Libro> librosFiltradosGenero(String genero) {

        // Usamos una expresión lambda (PARADIGMA FUNCIONAL) para devolver de manera
        // más acotada una lista de libros según su género.

        return this.listaLibros.stream()
                .filter(libro -> libro.getGenero().equalsIgnoreCase(genero))
                .collect(Collectors.toList());

    }

    // FILTRO : TÍTULO

    public Libro libroPorTitulo(String titulo) {

        Libro libroPorTitulo = null;

        for (Libro libro : this.listaLibros) {
            if (libro.getTitulo().equalsIgnoreCase(titulo)) {

                libroPorTitulo = libro;
                System.out.println("Se encontró el libro en el " +
                        "inventario, mostrando datos: " + libroPorTitulo.toString());

            } else {
                System.out.println("Lo sentimos. No se encontró el libro: " + titulo);
            }
        }

        return libroPorTitulo;

    }

    // MOSTRAR TODOS LOS DISPONIBLES

    public void mostrarLibrosDisponibles() {

        for (Libro libro : this.listaLibros) {
            if (libro.isDisponibilidad()) {
                System.out.println(libro.toString());
            }
        }

    }

    // DEVOLVER LIBRO POR ISBN

    public Libro libroPorISBN(Long ISBN) {

        Libro libroRetorno = null;

        for (Libro libro : listaLibros) {

            if (Objects.equals(libro.getISBN(), ISBN)) {
                libroRetorno = libro;
                break;
            }

        }

        return libroRetorno;

    }

    // Método mostrar

    public void mostrarLibros(List<Libro> listaLibros) {
        for (Libro libro : listaLibros) {
            System.out.println(libro);
        }
    }



}
