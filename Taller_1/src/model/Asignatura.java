package model;

import exceptions.CupoExcedidoException;
import exceptions.MatriculaDuplicadaException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class Asignatura {

    private static final int CUPO_MAXIMO = 35;

    private String codigo;
    private String nombre;
    private int creditos;
    private String periodo;
    private Docente docente;


    private List<Estudiante>       estudiantesMatriculados;
    private Map<String, Nota>      notasPorEstudiante;   // key: codigoEstudiante

    public Asignatura(String codigo, String nombre, int creditos, String periodo) {
        this.codigo                 = codigo;
        this.nombre                 = nombre;
        this.creditos               = creditos;
        this.periodo                = periodo;
        this.estudiantesMatriculados = new ArrayList<>();
        this.notasPorEstudiante      = new HashMap<>();
    }


    public Asignatura(String codigo, String nombre, int creditos) {
        this(codigo, nombre, creditos, "2026-1");
    }

    public void matricularEstudiante(Estudiante estudiante)
            throws CupoExcedidoException, MatriculaDuplicadaException {
        if (estudiantesMatriculados.size() >= CUPO_MAXIMO) {
            throw new CupoExcedidoException(
                    "Cupo máximo alcanzado (" + CUPO_MAXIMO + ") en " + nombre);
        }
        boolean yaMatriculado = estudiantesMatriculados.stream()
                .anyMatch(e -> e.getId().equals(estudiante.getId()));
        if (yaMatriculado) {
            throw new MatriculaDuplicadaException(
                    estudiante.getNombreCompleto() + " ya está matriculado en " + nombre);
        }
        estudiantesMatriculados.add(estudiante);
    }

    public void registrarNota(Nota nota) {
        notasPorEstudiante.put(nota.getCodigoEstudiante(), nota);
    }

    public Nota obtenerNota(String codigoEstudiante) {
        return notasPorEstudiante.get(codigoEstudiante);
    }

    public int getCupoDisponible() {
        return CUPO_MAXIMO - estudiantesMatriculados.size();
    }

    public void mostrarEstudiantes() {
        System.out.println("=== Estudiantes en " + nombre + " ===");
        estudiantesMatriculados.forEach(e -> System.out.println("  " + e.getNombreCompleto()));
    }

    public void mostrarNotas() {
        System.out.println("=== Notas de " + nombre + " ===");
        notasPorEstudiante.values().forEach(System.out::println);
    }


    @Override
    public String toString() {
        return String.format("Asignatura [%s] %s | %d créditos | Periodo: %s | Matriculados: %d/%d",
                codigo, nombre, creditos, periodo,
                estudiantesMatriculados.size(), CUPO_MAXIMO);
    }




    public String getCodigo()                  { return codigo; }
    public String getNombre()                  { return nombre; }
    public int getCreditos()                   { return creditos; }
    public String getPeriodo()                 { return periodo; }
    public Docente getDocente()                { return docente; }
    public void setDocente(Docente docente)    { this.docente = docente; }
    public List<Estudiante> getEstudiantes()   { return new ArrayList<>(estudiantesMatriculados); }
    public Map<String, Nota> getNotas()        { return new HashMap<>(notasPorEstudiante); }
}
