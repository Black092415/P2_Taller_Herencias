package service;

import exceptions.CupoExcedidoException;
import exceptions.MatriculaDuplicadaException;
import exceptions.NotaInvalidaException;
import model.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;




public class SistemaAcademico {

    private String          nombre;
    private List<Persona>   personas;      // Polimorfismo: guarda Estudiantes, Docentes y Admins
    private List<Asignatura> asignaturas;

    public SistemaAcademico(String nombre) {
        this.nombre      = nombre;
        this.personas    = new ArrayList<>();
        this.asignaturas = new ArrayList<>();
        System.out.println("Sistema Académico '" + nombre + "' iniciado.");
    }



    public void registrarPersona(Persona persona) {
        // Polimorfismo: se llama registrar() según el tipo real en tiempo de ejecución
        if (persona instanceof model.Estudiante e) {
            e.registrar();
        } else if (persona instanceof model.Docente d) {
            d.registrar();
        } else if (persona instanceof model.Administrador a) {
            a.registrar();
        }
        personas.add(persona);
    }



    public void crearAsignatura(Asignatura asignatura) {
        asignaturas.add(asignatura);
        System.out.println("Asignatura creada: " + asignatura.getNombre());
    }

    public boolean asignarDocente(String idDocente, String codigoAsignatura) {
        Optional<Persona> personaOpt = buscarPersonaPorId(idDocente);
        Optional<Asignatura> asigOpt = buscarAsignaturaPorCodigo(codigoAsignatura);

        if (personaOpt.isEmpty() || !(personaOpt.get() instanceof Docente docente)) {
            System.out.println("Docente no encontrado: " + idDocente);
            return false;
        }
        if (asigOpt.isEmpty()) {
            System.out.println("Asignatura no encontrada: " + codigoAsignatura);
            return false;
        }
        return docente.asignarAsignatura(asigOpt.get());
    }



    public boolean matricularEstudiante(String codigoEstudiante, String codigoAsignatura) {
        Optional<Persona> personaOpt = buscarPersonaPorId(codigoEstudiante);
        Optional<Asignatura> asigOpt = buscarAsignaturaPorCodigo(codigoAsignatura);

        if (personaOpt.isEmpty() || !(personaOpt.get() instanceof Estudiante estudiante)) {
            System.out.println("Estudiante no encontrado: " + codigoEstudiante);
            return false;
        }
        if (asigOpt.isEmpty()) {
            System.out.println("Asignatura no encontrada: " + codigoAsignatura);
            return false;
        }
        Asignatura asignatura = asigOpt.get();
        try {
            asignatura.matricularEstudiante(estudiante);
            estudiante.agregarAsignatura(asignatura);
            System.out.printf("Estudiante '%s' matriculado en '%s'%n",
                    estudiante.getNombreCompleto(), asignatura.getNombre());
            return true;
        } catch (CupoExcedidoException | MatriculaDuplicadaException ex) {
            System.out.println("Error al matricular: " + ex.getMessage());
            return false;
        }
    }



    public boolean registrarNota(String codigoEstudiante, String codigoAsignatura,
                                 double c1, double c2, double c3) {
        Optional<Asignatura> asigOpt = buscarAsignaturaPorCodigo(codigoAsignatura);
        if (asigOpt.isEmpty()) {
            System.out.println("Asignatura no encontrada: " + codigoAsignatura);
            return false;
        }
        try {
            Nota nota = new Nota(codigoEstudiante, codigoAsignatura, c1, c2, c3);
            asigOpt.get().registrarNota(nota);
            System.out.println("Nota registrada: " + nota);
            return true;
        } catch (NotaInvalidaException ex) {
            System.out.println("Error al registrar nota: " + ex.getMessage());
            return false;
        }
    }



    public void mostrarHistorialEstudiante(String codigoEstudiante) {
        buscarPersonaPorId(codigoEstudiante).ifPresentOrElse(
                p -> { if (p instanceof Estudiante e) e.mostrarHistorial(); },
                () -> System.out.println("Estudiante no encontrado.")
        );
    }

    public void mostrarTodasLasPersonas() {
        System.out.println("\n===== PERSONAS REGISTRADAS =====");
        // Polimorfismo: toString() se ejecuta según tipo real
        personas.forEach(p -> System.out.println("  " + p + "\n  " + p.getInfo()));
        System.out.println("================================\n");
    }

    public void mostrarAsignaturas() {
        System.out.println("\n===== ASIGNATURAS =====");
        asignaturas.forEach(a -> System.out.println("  " + a));
        System.out.println("=======================\n");
    }



    private Optional<Persona> buscarPersonaPorId(String id) {
        return personas.stream().filter(p -> p.getId().equals(id)).findFirst();
    }

    private Optional<Asignatura> buscarAsignaturaPorCodigo(String codigo) {
        return asignaturas.stream().filter(a -> a.getCodigo().equals(codigo)).findFirst();
    }

    public String getNombre() { return nombre; }
}
