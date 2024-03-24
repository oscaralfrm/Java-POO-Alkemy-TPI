import model.entities.Libro;
import model.entities.Usuario;
import model.transactionals.AsignacionLibro;
import model.transactionals.InventarioLibros;
import model.transactionals.Prestamo;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

public class Main {

    // Para simplificar el sistema de préstamos, se considera que sólo puede haber un ejemplar de cada libro.
    // No se especifica en los requerimientos funcionales.
    // Si un Libro tiene disponibilidad true, podrá ser asignado en un préstamo.
    // Caso contrario, éste se considera parte de un préstamo y hasta que no se devuelva no se podrá asignar de nuevo.
    // Usaremos atributos identificadores (Long ISBN, Long numeroIdentificacion) para realizar las transacciones correspondientes.
    // A su vez, se considerará a una devolución como un préstamo en sentido contrario.

    public static void main(String[] args) {

        // Objeto Scanner
        Scanner scanner = new Scanner(System.in);

        // Inventario de Libros y Asignación de Libros
        InventarioLibros inventarioLibros = new InventarioLibros();
        AsignacionLibro asignacionLibro = new AsignacionLibro(inventarioLibros);

        // Lista de Usuarios
        List<Usuario> listaUsuarios = new ArrayList<>();

        int opc = -1;

        while (opc != 0) {

            System.out.println("-- Bienvenid@ al Sistema Gestor de Préstamos de Libros --");
            System.out.println("Ingrese opciones con los números del teclado. SALIR CON 0.");
            System.out.println("-- 1.- REGISTRAR NUEVO USUARIO --");
            System.out.println("-- 2.- REGISTRAR NUEVO LIBRO // SE AGREGARÁ AL INVENTARIO --");
            System.out.println("-- 3.- BUSCAR LIBRO EN EL INVENTARIO POR AUTOR --");
            System.out.println("-- 4.- BUSCAR LIBRO EN EL INVENTARIO POR TÍTULO --");
            System.out.println("-- 5.- BUSCAR LIBRO EN EL INVENTARIO POR GÉNERO --");
            System.out.println("-- 6.- REGISTRAR PRÉSTAMO DE UN LIBRO A UN USUARIO --"); // Del Usuario, ingresar el numeroIdentificacion y del libro el ISBN.
            System.out.println("-- 7.- REGISTRAR DEVOLUCIÓN DE UN LIBRO DE UN USUARIO--"); // Del Usuario, ingresar el numeroIdentificacion y del libro el ISBN.
            System.out.println("-- 8.- MOSTRAR LIBROS PRESTADOS DE UN USUARIO --"); // Del Usuario, ingresar el numeroIdentificacion
            System.out.println("-- 9.- MOSTRAR LIBROS DISPONIBLES EN EL INVENTARIO --");
            System.out.println("-- 0.- SALIR DEL SISTEMA --");
            System.out.print("Ingrese una opción: ");
            opc = scanner.nextInt();


            switch(opc) {

                case 1:

                    Usuario usuario = new Usuario();

                    System.out.println("-- CREANDO NUEVO USUARIO: -- ");

                    // Long numeroIdentificacion
                    System.out.print("- INGRESE EL NÚMERO DE IDENTIFICACIÓN DEL USUARIO: ");
                    Long numeroIdentificacionNuevoUsuario = scanner.nextLong();
                    usuario.setNumeroIdentificacion(numeroIdentificacionNuevoUsuario);

                    // String nombre
                    System.out.print("- INGRESE EL NOMBRE DEL USUARIO: ");
                    String nombreNuevoUsuario = scanner.next();
                    usuario.setNombre(nombreNuevoUsuario);

                    System.out.println("USUARIO CREADO: - ID: " + usuario.getNumeroIdentificacion() + " | - NOMBRE: " + usuario.getNombre());

                    listaUsuarios.add(usuario);

                    System.out.println("-- USUARIOS DEL SISTEMA --");

                    for (Usuario usuarioSistema : listaUsuarios) {
                        System.out.println(usuarioSistema.toString());
                    }

                    break;

                case 2:

                    Libro libro = new Libro();

                    System.out.println("-- CREANDO NUEVO LIBRO: -- ");

                    // Long ISBN
                    System.out.print("- INGRESE EL ISBN DEL LIBRO: ");
                    Long ISBNRegistro = scanner.nextLong();
                    libro.setISBN(ISBNRegistro);

                    // String autor
                    System.out.print("- INGRESE EL AUTOR DEL LIBRO: ");
                    String autor = scanner.next();
                    libro.setAutor(autor);

                    // String titulo
                    System.out.print("- INGRESE EL TITULO DEL LIBRO: ");
                    String titulo = scanner.next();
                    libro.setTitulo(titulo);

                    // String genero
                    System.out.print("- INGRESE EL GÉNERO DEL LIBRO: ");
                    String genero = scanner.next();
                    libro.setGenero(genero);

                    System.out.println("LIBRO CREADO: - ISBN: " + libro.getISBN() +
                            " | - AUTOR: " + libro.getAutor() +
                            " | - TÍTULO: " + libro.getTitulo() +
                            " | - GÉNERO: " + libro.getGenero() +
                            " | - DISPONIBILIDAD: " + libro.isDisponibilidad());


                    System.out.println("-- AÑADIENDO LIBRO AL INVENTARIO... --");

                    inventarioLibros.agregarLibroInventario(libro);

                    break;

                case 3:

                    // String autor
                    System.out.print("- INGRESE EL AUTOR DEL LIBRO A BUSCAR Y MOSTRAR SUS DATOS: ");
                    String autorLibro = scanner.next();

                    System.out.println("-- MOSTRANDO LISTA DE LIBROS POR AUTOR --");

                    inventarioLibros.mostrarLibros(inventarioLibros.librosFiltradosAutor(autorLibro));

                    break;

                case 4:

                    // String titulo
                    System.out.print("- INGRESE EL TITULO DEL LIBRO A BUSCAR Y MOSTRAR SUS DATOS: ");
                    String tituloLibro = scanner.next();

                    System.out.println("- MOSTRANDO LIBRO: " + inventarioLibros.libroPorTitulo(tituloLibro));

                    break;

                case 5:

                    // String genero
                    System.out.print("- INGRESE EL GÉNERO DE LOS LIBROS A BUSCAR Y MOSTRAR SUS DATOS: ");
                    String generoLibro = scanner.next();

                    System.out.println("-- MOSTRANDO LISTA DE LIBROS POR GÉNERO --");
                    inventarioLibros.mostrarLibros(inventarioLibros.librosFiltradosGenero(generoLibro));

                    break;


                case 6:

                    System.out.println("-- REGISTRANDO UN PRÉSTAMO --");

                    // Long numeroIdentificacionPrestamo
                    System.out.print("- INGRESE EL NÚMERO DE IDENTIFICACIÓN DEL USUARIO A REALIZAR EL PRÉSTAMO: ");
                    Long numeroIdentificacionPrestamo = scanner.nextLong();

                    // Long ISBN
                    System.out.print("- INGRESE EL ISBN DEL LIBRO A PRESTAR: ");
                    Long ISBNPrestamo = scanner.nextLong();

                    Usuario usuarioPrestamo = conseguirUsuario(numeroIdentificacionPrestamo,
                            listaUsuarios);

                    Prestamo prestamo = new Prestamo(asignacionLibro, usuarioPrestamo);

                    prestamo.registrarPrestamo(numeroIdentificacionPrestamo, ISBNPrestamo);

                    System.out.println("- PRÉSTAMO: NOMBRE: "
                            + usuarioPrestamo.getNombre() + " | LIBROS PRESTADOS: "
                            + usuarioPrestamo.getLibrosPrestados());

                    System.out.println("-- PRÉSTAMO REGISTRADO --");

                    break;


                case 7:

                    System.out.println("-- REGISTRANDO UNA DEVOLUCIÓN --");

                    // Long numeroIdentificacionPrestamo
                    System.out.print("- INGRESE EL NÚMERO DE IDENTIFICACIÓN DEL USUARIO A REALIZAR LA DEVOLUCIÓN: ");
                    Long numeroIdentificacionDevolucion = scanner.nextLong();

                    // Long ISBN
                    System.out.print("- INGRESE EL ISBN DEL LIBRO A DEVOLVER: ");
                    Long ISBNDevolucion = scanner.nextLong();

                    Usuario usuarioDevolucion = conseguirUsuario(numeroIdentificacionDevolucion,
                            listaUsuarios);

                    Prestamo devolucion = new Prestamo(asignacionLibro, usuarioDevolucion);

                    devolucion.registrarDevolucion(numeroIdentificacionDevolucion, ISBNDevolucion);

                    System.out.println("- DEVOLUCIÓN: NOMBRE: " + usuarioDevolucion.getNombre()
                            + " | LIBROS PRESTADOS ACTUALMENTE: "
                            + usuarioDevolucion.getLibrosPrestados());

                    System.out.println("-- DEVOLUCIÓN REGISTRADA --");

                    break;


                case 8:

                    System.out.println("-- MOSTRAR LIBROS PRESTADOS DE UN USUARIO --");

                    // Long numeroIdentificacionLibrosPrestadosUsuario
                    System.out.print("- INGRESE EL NÚMERO DE IDENTIFICACIÓN DEL USUARIO PARA MOSTRAR SUS LIBROS PRESTADOS: ");
                    Long numeroIdentificacionLibrosPrestadosUsuario = scanner.nextLong();

                    Usuario usuarioMostrarLibrosPrestados = conseguirUsuario(numeroIdentificacionLibrosPrestadosUsuario,
                            listaUsuarios);

                    usuarioMostrarLibrosPrestados.mostrarLibrosPrestadosUsuario();

                    break;


                case 9:

                    System.out.println("-- MOSTRANDO LISTA DE LIBROS DISPONIBLES --");

                    inventarioLibros.mostrarLibrosDisponibles();

                    break;


                default: case 0:
                    System.out.println("¡Nos vemos!");
                    return;
            }


        }



    }

    public static Usuario conseguirUsuario (Long idUsuario, List<Usuario> listaDeUsuarios) {
        Usuario usuario = null;

        for (Usuario usuarioMain : listaDeUsuarios) {
            if (Objects.equals(usuarioMain.getNumeroIdentificacion(), idUsuario)) {
                usuario = usuarioMain;
            }
        }

        return usuario;

    }


}
