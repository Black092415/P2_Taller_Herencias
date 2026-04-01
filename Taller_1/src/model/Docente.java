package model;

import interfaces.Registrable;

import java.util.ArrayList;
import java.util.List;


public class Docente extends Persona implements Registrable {

    public enum TipoDocente { PLANTA, CATEDRA, HORA_CATEDRA }

    private static final int MAX_ASIGNATURAS = 4;

    private String      titulo;
    private TipoDocente tipo;
    private boolean     registrado;

    private List<Asignatura> asignaturasAsignadas;

    public Docente(String id, String nombre, String apellido,
                   String correo, String titulo, TipoDocente tipo) {
        super(id, nombre, apellido, correo, "");
        this.titulo               = titulo;
        this.tipo                 = tipo;
        this.registrado           = false;
        this.asignaturasAsignadas = new ArrayList<>();
    }


    public Docente(String id, String nombre, String apellido, String titulo) {
        this(id, nombre, apellido, "", titulo, TipoDocente.PLANTA);
    }



    @Override
    public String getInfo() {
        return String.format(
                "DOCENTE | Título: %s | Tipo: %s | Asignaturas: %d/%d",
                titulo, tipo, asignaturasAsignadas.size(), MAX_ASIGNATURAS);
    }

    @Override
    public String getRol() { return "Docente"; }



    @Override
    public void registrar() {
        this.registrado = true;
        System.out.println("Docente registrado: " + getNombreCompleto() + " (" + titulo + ")");
    }

    @Override
    public boolean estaRegistrado() { return registrado; }

    @Override
    public String obtenerCodigoRegistro() { return getId(); }


    public boolean asignarAsignatura(Asignatura asignatura) {
        if (asignaturasAsignadas.size() >= MAX_ASIGNATURAS) {
            System.out.println("El docente " + getNombreCompleto() +
                    " ya tiene el máximo de " + MAX_ASIGNATURAS + " asignaturas.");
            return false;
        }
        asignaturasAsignadas.add(asignatura);
        asignatura.setDocente(this);
        return true;
    }




    public void asignarAsignaturas(List<Asignatura> lista) {
        lista.forEach(this::asignarAsignatura);
    }

    public void mostrarAsignaturas() {
        System.out.println("=== Asignaturas de " + getNombreCompleto() + " ===");
        asignaturasAsignadas.forEach(a -> System.out.println("  " + a));
    }



    public String      getTitulo()    { return titulo; }
    public TipoDocente getTipo()      { return tipo; }
    public List<Asignatura> getAsignaturasAsignadas() { return new ArrayList<>(asignaturasAsignadas); }
}
