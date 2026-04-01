package modelo;

import modelo.*;
import sistema.SistemaVentas;

import java.time.LocalDate;


public class Main {

    public static void main(String[] args) {

        System.out.println("╔══════════════════════════════════════════╗");
        System.out.println("║  SISTEMA DE VENTAS Y COMISIONES          ║");
        System.out.println("║  Inmobiliaria Unicorsalud Realty          ║");
        System.out.println("╚══════════════════════════════════════════╝\n");


        SistemaVentas sistema = new SistemaVentas("Unicorsalud Realty");


        VendedorJunior juan   = new VendedorJunior("E01", "Juan Pérez",   "10234567");
        VendedorSenior maria  = new VendedorSenior("E02", "María López",  "20345678", 8);
        VendedorJunior carlos = new VendedorJunior("E03", "Carlos Ruiz");  // sobrecarga
        VendedorSenior ana    = new VendedorSenior("E04", "Ana Torres", 5); // sobrecarga

        sistema.registrarEmpleado(juan);
        sistema.registrarEmpleado(maria);
        sistema.registrarEmpleado(carlos);
        sistema.registrarEmpleado(ana);


        Casa        c1 = new Casa("P01", "Casa Barranquilla Norte",   350_000_000, 4);
        Casa        c2 = new Casa("P02", "Casa Soledad",              280_000_000, 3);
        Apartamento a1 = new Apartamento("P03", "Apto El Prado P12",  200_000_000, 12);
        Apartamento a2 = new Apartamento("P04", "Apto Manga P5",      180_000_000, 5);
        Lote        l1 = new Lote("P05", "Lote Villa Campestre",      120_000_000, 500);
        Lote        l2 = new Lote("P06", "Lote Galapa Industrial",     95_000_000, 800);
        Lote        l3 = new Lote("P07", "Lote Malambo",               60_000_000, 300);
        Apartamento a3 = new Apartamento("P08", "Apto Ciudad Jardín",  95_000_000, 3);

        sistema.registrarPropiedad(c1);
        sistema.registrarPropiedad(c2);
        sistema.registrarPropiedad(a1);
        sistema.registrarPropiedad(a2);
        sistema.registrarPropiedad(l1);
        sistema.registrarPropiedad(l2);
        sistema.registrarPropiedad(l3);
        sistema.registrarPropiedad(a3);


        System.out.println("\n--- Registrando ventas ---");


        sistema.registrarVenta("E01", "P01");


        sistema.registrarVenta("E02", "P02");
        sistema.registrarVenta("E02", "P03");
        sistema.registrarVenta("E02", "P05");


        sistema.registrarVenta("E03", "P04");
        sistema.registrarVenta("E03", "P06");
        sistema.registrarVenta("E03", "P07");


        sistema.registrarVenta("E04", "P08");
        sistema.registrarVenta("E04", "P02"); // segunda propiedad


        sistema.calcularComisionesMensuales();


        System.out.println(sistema.generarReporteGlobal());


        System.out.println("--- Información de empleados (toString sobrescrito) ---");
        for (Empleado e : sistema.getEmpleados()) {
            System.out.println("  " + e); // polimorfismo → cada subclase su toString
        }

        System.out.println("\n--- Información de propiedades (toString sobrescrito) ---");
        for (Propiedad p : sistema.getPropiedades()) {
            System.out.println("  " + p); // polimorfismo → Casa/Apto/Lote toString
        }

        System.out.println("\n✔ Sistema finalizado correctamente.");
    }
}