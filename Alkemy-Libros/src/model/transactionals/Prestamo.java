package model.transactionals;

import model.entities.Libro;
import model.entities.Usuario;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Prestamo {

    // Estado: Atributos

    private AsignacionLibro asignacionLibro;
    private Usuario usuarioPrestamo;


    // Comportamiento: Métodos constructores

    public Prestamo() {
    }

    public Prestamo(AsignacionLibro asignacionLibro, Usuario usuarioPrestamo) {
        this.asignacionLibro = asignacionLibro;
        this.usuarioPrestamo = usuarioPrestamo;
    }

    // Comportamiento: Métodos accesores

    public AsignacionLibro getAsignacionLibro() {
        return asignacionLibro;
    }

    public void setAsignacionLibro(AsignacionLibro asignacionLibro) {
        this.asignacionLibro = asignacionLibro;
    }

    public Usuario getUsuarioPrestamo() {
        return usuarioPrestamo;
    }

    public void setUsuarioPrestamo(Usuario usuarioPrestamo) {
        this.usuarioPrestamo = usuarioPrestamo;
    }


    // Comportamiento: Método toString()

    @Override
    public String toString() {
        return "Prestamo{" +
                "asignacionLibro=" + asignacionLibro +
                ", usuarioPrestamo=" + usuarioPrestamo +
                '}';
    }


    // Comportamiento: Métodos transaccionales

    // REGISTRAR PRÉSTAMO

    public void registrarPrestamo(Long numeroIdentificacionUsuario, Long ISBN) {

        List<Libro> listaLibrosPrestamo = new ArrayList<>(); // Lista a settear

        Libro libroPrestar = asignacionLibro.asignarLibroISBN(ISBN);
        Usuario usuario = devolverUsuario(numeroIdentificacionUsuario);

        if (libroPrestar.isDisponibilidad()) {

            System.out.println("Se ha prestado el libro: " + libroPrestar.toString());
            libroPrestar.setDisponibilidad(false);
            listaLibrosPrestamo.add(libroPrestar);

        }

        // Actualizando lista de libros prestados del usuario...

        usuario.setLibrosPrestados(listaLibrosPrestamo);

        usuario.setPrestamo(this);

    }

    public void registrarDevolucion(Long numeroIdentificacionUsuario, Long ISBN) {

        List<Libro> listaLibrosPrestamo = usuarioPrestamo.getLibrosPrestados();

        Libro libroDevolver = this.devolverLibro(ISBN);
        Usuario usuario = this.devolverUsuario(numeroIdentificacionUsuario);

        if (!libroDevolver.isDisponibilidad()) {

            System.out.println("Se ha devuelto el libro: " + libroDevolver.toString());
            libroDevolver.setDisponibilidad(true);
            listaLibrosPrestamo.remove(libroDevolver);

        }

        // Actualizando lista de libros prestados del usuario...

        usuario.setLibrosPrestados(listaLibrosPrestamo);

        if (usuario.getLibrosPrestados().size() == 0) {
            usuario.setPrestamo(null);
        }

    }

    // Método para buscar un usuario y settearle su(s) préstamos

    public Usuario devolverUsuario(Long numeroIdentificacionUsuario) {
        if (Objects.equals(numeroIdentificacionUsuario, usuarioPrestamo.getNumeroIdentificacion())) {
            return usuarioPrestamo;
        } else {
            return null;
        }
    }

    // Método para buscar un libro de la lista de libros

    public Libro devolverLibro(Long ISBN) {

        List<Libro> listaLibrosPrestamo = usuarioPrestamo.getLibrosPrestados();
        Libro libroDevolver = null;


        for (Libro libro : listaLibrosPrestamo) {
            if (Objects.equals(libro.getISBN(), ISBN)) {
                libroDevolver = libro;
            } else {
                return null;
            }
        }

    return libroDevolver;

    }


}