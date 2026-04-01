package main;

import model.*;
import service.SistemaAcademico;


public class Main {

  public static void main(String[] args) {

    System.out.println("============================================================");
    System.out.println("   SISTEMA DE GESTIÓN ACADÉMICA - UNICORSALUD               ");
    System.out.println("============================================================\n");


    SistemaAcademico sistema = new SistemaAcademico("UNICORSALUD 2026-1");


    System.out.println("\n--- Registrando personas ---");

    Administrador admin = new Administrador(
            "ADM001", "Carlos", "Pérez", "cperez@uni.edu",
            "Coordinador", "CRUD_ESTUDIANTES", "CRUD_DOCENTES", "CRUD_ASIGNATURAS");

    Docente docente1 = new Docente(
            "DOC001", "Ana", "Gómez", "agomez@uni.edu",
            "Magíster en Informática", Docente.TipoDocente.PLANTA);

    Docente docente2 = new Docente(
            "DOC002", "Luis", "Martínez", "Especialista");  // constructor sobrecargado

    // Sobrecarga de constructor: Estudiante con todos los datos
    Estudiante est1 = new Estudiante(
            "EST001", "María", "Torres", "mtorres@uni.edu",
            "2021115001", 5, "Ingeniería de Sistemas");

    // Sobrecarga de constructor: Estudiante con datos mínimos
    Estudiante est2 = new Estudiante(
            "EST002", "Juan", "López", "2021115002", 3);

    Estudiante est3 = new Estudiante(
            "EST003", "Sofía", "Vargas", "svargas@uni.edu",
            "2021115003", 4, "Ingeniería de Sistemas");


    sistema.registrarPersona(admin);
    sistema.registrarPersona(docente1);
    sistema.registrarPersona(docente2);
    sistema.registrarPersona(est1);
    sistema.registrarPersona(est2);
    sistema.registrarPersona(est3);


    System.out.println("\n--- Creando asignaturas ---");

    Asignatura progOO  = new Asignatura("PRG301", "Programación Orientada a Objetos", 3, "2026-1");
    Asignatura bdatos  = new Asignatura("BDA201", "Bases de Datos", 4);             // constructor sobrecargado
    Asignatura calcDif = new Asignatura("CAL101", "Cálculo Diferencial", 3, "2026-1");

    sistema.crearAsignatura(progOO);
    sistema.crearAsignatura(bdatos);
    sistema.crearAsignatura(calcDif);


    System.out.println("\n--- Asignando docentes ---");
    sistema.asignarDocente("DOC001", "PRG301");
    sistema.asignarDocente("DOC001", "BDA201");
    sistema.asignarDocente("DOC002", "CAL101");


    System.out.println("\n--- Matriculando estudiantes ---");
    sistema.matricularEstudiante("EST001", "PRG301");
    sistema.matricularEstudiante("EST001", "BDA201");
    sistema.matricularEstudiante("EST002", "PRG301");
    sistema.matricularEstudiante("EST002", "CAL101");
    sistema.matricularEstudiante("EST003", "PRG301");
    sistema.matricularEstudiante("EST003", "BDA201");


    System.out.println("\n--- Prueba de regla: matrícula duplicada ---");
    sistema.matricularEstudiante("EST001", "PRG301");  // debe lanzar error


    System.out.println("\n--- Registrando notas ---");


    sistema.registrarNota("EST001", "PRG301", 4.2, 3.8, 4.5);  // Final: 4.17
    sistema.registrarNota("EST001", "BDA201", 2.5, 3.0, 3.5);  // Final: 3.05
    sistema.registrarNota("EST002", "PRG301", 2.0, 1.5, 2.5);  // Final: 2.05 - REPROBADO
    sistema.registrarNota("EST002", "CAL101", 4.8, 4.5, 5.0);  // Final: 4.79
    sistema.registrarNota("EST003", "PRG301", 3.5, 3.8, 4.0);  // Final: 3.79
    sistema.registrarNota("EST003", "BDA201", 4.0, 4.2, 4.5);  // Final: 4.24


    System.out.println("\n--- Prueba de regla: nota fuera de rango ---");
    sistema.registrarNota("EST001", "CAL101", 6.0, 3.0, 4.0);  // debe lanzar error


    sistema.mostrarTodasLasPersonas();
    sistema.mostrarAsignaturas();


    sistema.mostrarHistorialEstudiante("EST001");
    sistema.mostrarHistorialEstudiante("EST002");
    sistema.mostrarHistorialEstudiante("EST003");


    System.out.println("===== DEMOSTRACIÓN DE POLIMORFISMO =====");
    Persona[] personas = { admin, docente1, docente2, est1, est2 };
    for (Persona p : personas) {
      // Llamada polimórfica: cada clase ejecuta SU versión de getInfo()
      System.out.println(p.getRol() + ": " + p.getInfo());
    }

    System.out.println("\n============================================================");
    System.out.println("   SISTEMA FINALIZADO                                        ");
    System.out.println("============================================================");
  }
}