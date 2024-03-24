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
        this.asignacionLibro =  new AsignacionLibro();
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

    // Comportamiento: Métodos transaccionales

    // REGISTRAR PRÉSTAMO

    public void registrarPrestamo(Long numeroIdentificacionUsuario, Long ISBN) {

        Libro libroPrestar = asignacionLibro.asignarLibroISBN(ISBN);
        Usuario usuario = this.usuarioPrestamo;

        if (libroPrestar.isDisponibilidad()) {

            libroPrestar.setDisponibilidad(false);
            System.out.println("Se ha prestado el libro: " + libroPrestar.toString());
            usuario.getLibrosPrestados().add(libroPrestar);

        }

        usuario.setPrestamo(this);

    }

    public void registrarDevolucion(Long numeroIdentificacionUsuario, Long ISBN) {

        Libro libroDevolver = this.devolverLibro(ISBN);
        Usuario usuario = this.usuarioPrestamo;

        if (!libroDevolver.isDisponibilidad()) {

            libroDevolver.setDisponibilidad(true);
            System.out.println("Se ha devuelto el libro: " + libroDevolver.toString());
            usuario.getLibrosPrestados().remove(libroDevolver);

        }

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

        List<Libro> listaLibrosPrestamo = this.usuarioPrestamo.getLibrosPrestados();
        Libro libroDevolver = null;


        for (Libro libro : listaLibrosPrestamo) {
            if (Objects.equals(libro.getISBN(), ISBN)) {
                libroDevolver = libro;
                break;
            } else {
                return null;
            }
        }

    return libroDevolver;

    }


}
