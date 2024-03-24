package model.transactionals;

import model.entities.Libro;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class AsignacionLibro {

    // Nota: Ésta es una clase que sigue el patrón de modelado
    // de Transacción-Transacción Subsiguiente.
    // Fue modelada para que podamos comunicar nuestra Transacción Préstamo con el InventarioLibros.
    // Más información: https://www.studocu.com/es-ar/document/universidad-tecnologica-nacional/analisis-de-sistemas-integradora/apunte-patrones-y-software-uml-2/25259598


    // Estado: Atributos

    private InventarioLibros inventarioLibros;

    // Comportamiento: Métodos constructores

    public AsignacionLibro() {
    }

    public AsignacionLibro(InventarioLibros inventarioLibros) {
        this.inventarioLibros = inventarioLibros;
    }

    // Comportamiento: Métodos accesores

    public InventarioLibros getInventarioLibros() {
        return inventarioLibros;
    }

    public void setInventarioLibros(InventarioLibros inventarioLibros) {
        this.inventarioLibros = inventarioLibros;
    }

    // Comportamiento: Método toString()

    @Override
    public String toString() {
        return "AsignacionLibro{" +
                "inventarioLibros=" + inventarioLibros +
                '}';
    }


    // Comportamiento: Métodos transaccionales

    // MÉTODO TRANSACCIONAL DE ASIGNACIÓN DEL LIBRO

    // ASIGNACIÓN POR ISBN - ES LA QUE PLANTEAMOS EN EL MAIN COMO MÉTODO DE RESOLUCIÓN

    public Libro asignarLibroISBN(Long ISBN) {

        return this.inventarioLibros.libroPorISBN(ISBN);

    }


}
