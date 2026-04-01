package model;

import interfaces.Calificable;
import interfaces.Registrable;

import java.util.ArrayList;
import java.util.List;


public class Estudiante extends Persona implements Registrable, Calificable {

    private String  codigo;
    private int     semestre;
    private String  programa;
    private boolean registrado;


    private List<Asignatura> asignaturasMatriculadas;

    public Estudiante(String id, String nombre, String apellido,
                      String correo, String codigo, int semestre, String programa) {
        super(id, nombre, apellido, correo, "");
        this.codigo                 = codigo;
        this.semestre               = semestre;
        this.programa               = programa;
        this.registrado             = false;
        this.asignaturasMatriculadas = new ArrayList<>();
    }


    public Estudiante(String id, String nombre, String apellido, String codigo, int semestre) {
        this(id, nombre, apellido, "", codigo, semestre, "Sin definir");
    }



    @Override
    public String getInfo() {
        return String.format(
                "ESTUDIANTE | Código: %s | Programa: %s | Semestre: %d | Materias: %d",
                codigo, programa, semestre, asignaturasMatriculadas.size());
    }

    @Override
    public String getRol() { return "Estudiante"; }



    @Override
    public void registrar() {
        this.registrado = true;
        System.out.println("Estudiante registrado: " + getNombreCompleto() + " (" + codigo + ")");
    }

    @Override
    public boolean estaRegistrado() { return registrado; }

    @Override
    public String obtenerCodigoRegistro() { return codigo; }





    @Override
    public double calcularNotaFinal() {
        if (asignaturasMatriculadas.isEmpty()) return 0.0;

        double sumaNotas = 0;
        int    conteo    = 0;
        for (Asignatura a : asignaturasMatriculadas) {
            Nota nota = a.obtenerNota(codigo);
            if (nota != null) {
                sumaNotas += nota.calcularFinal();
                conteo++;
            }
        }
        return conteo == 0 ? 0.0 : sumaNotas / conteo;
    }

    @Override
    public boolean estaAprobado() { return calcularNotaFinal() >= 3.0; }

    @Override
    public String obtenerEstado() { return estaAprobado() ? "APROBADO" : "REPROBADO"; }


    public void agregarAsignatura(Asignatura asignatura) {
        asignaturasMatriculadas.add(asignatura);
    }


    public void mostrarHistorial() {
        System.out.println("\n===== HISTORIAL ACADÉMICO =====");
        System.out.println(this);
        System.out.println(getInfo());
        if (asignaturasMatriculadas.isEmpty()) {
            System.out.println("Sin asignaturas matriculadas.");
            return;
        }
        for (Asignatura a : asignaturasMatriculadas) {
            Nota nota = a.obtenerNota(codigo);
            if (nota != null) {
                System.out.printf("  %-30s -> %s%n", a.getNombre(), nota);
            } else {
                System.out.printf("  %-30s -> Sin nota registrada%n", a.getNombre());
            }
        }
        System.out.printf("  Promedio general: %.2f (%s)%n",
                calcularNotaFinal(), obtenerEstado());
        System.out.println("================================\n");
    }





    public String getCodigo()              { return codigo; }
    public int    getSemestre()            { return semestre; }
    public String getPrograma()            { return programa; }
    public void   setSemestre(int s)       { this.semestre = s; }
    public void   setPrograma(String p)    { this.programa = p; }
    public List<Asignatura> getAsignaturas() { return new ArrayList<>(asignaturasMatriculadas); }
}
