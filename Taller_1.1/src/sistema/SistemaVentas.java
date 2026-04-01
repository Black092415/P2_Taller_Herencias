package sistema;

import interfaz.IComisionable;
import modelo.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


public class SistemaVentas {

    private String nombreEmpresa;
    private List<Empleado> empleados;
    private List<Propiedad> propiedades;
    private List<Venta> ventas;
    private int contadorVentas;

    public SistemaVentas(String nombreEmpresa) {
        this.nombreEmpresa  = nombreEmpresa;
        this.empleados      = new ArrayList<>();
        this.propiedades    = new ArrayList<>();
        this.ventas         = new ArrayList<>();
        this.contadorVentas = 1;
    }



    public void registrarEmpleado(Empleado empleado) {
        if (empleado == null) throw new IllegalArgumentException("Empleado nulo.");
        empleados.add(empleado);
        System.out.println("✔ Empleado registrado: " + empleado.getNombre());
    }

    public void registrarPropiedad(Propiedad propiedad) {
        if (propiedad == null) throw new IllegalArgumentException("Propiedad nula.");
        propiedades.add(propiedad);
        System.out.println("✔ Propiedad registrada: " + propiedad);
    }




    public void registrarVenta(String idEmpleado, String idPropiedad) {
        registrarVenta(idEmpleado, idPropiedad, LocalDate.now());
    }


    public void registrarVenta(String idEmpleado, String idPropiedad, LocalDate fecha) {
        Empleado empleado  = buscarEmpleado(idEmpleado);
        Propiedad propiedad = buscarPropiedad(idPropiedad);

        if (empleado == null)  throw new IllegalArgumentException("Empleado no encontrado: " + idEmpleado);
        if (propiedad == null) throw new IllegalArgumentException("Propiedad no encontrada: " + idPropiedad);

        String idVenta = String.format("V%03d", contadorVentas++);
        Venta venta = new Venta(idVenta, propiedad, fecha);
        empleado.agregarVenta(venta);
        ventas.add(venta);

        System.out.printf("✔ Venta %s registrada: %s vendió %s ($%,.0f)%n",
                idVenta, empleado.getNombre(), propiedad.getTipo(), propiedad.getPrecio());
    }


    public void calcularComisionesMensuales() {
        int mes  = LocalDate.now().getMonthValue();
        int anio = LocalDate.now().getYear();
        System.out.printf("%n>>> Comisiones %d/%d — %s%n", mes, anio, nombreEmpresa);
        System.out.println("─".repeat(55));

        for (Empleado e : empleados) {
            IComisionable comisionable = (IComisionable) e; // polimorfismo
            double comision = comisionable.calcularComision();
            System.out.printf("  %-20s | %s | Comisión: $%,.2f%n",
                    e.getNombre(), e.getTipoEmpleado(), comision);
        }
        System.out.println("─".repeat(55));
    }


    public String generarReporteGlobal() {
        StringBuilder sb = new StringBuilder();
        sb.append("\n").append("█".repeat(55)).append("\n");
        sb.append("  REPORTE GLOBAL — ").append(nombreEmpresa).append("\n");
        sb.append("█".repeat(55)).append("\n\n");

        for (Empleado e : empleados) {
            IComisionable comisionable = (IComisionable) e;
            sb.append(comisionable.generarReporte()).append("\n");
        }
        return sb.toString();
    }



    private Empleado buscarEmpleado(String id) {
        return empleados.stream()
                .filter(e -> e.getId().equals(id))
                .findFirst().orElse(null);
    }

    private Propiedad buscarPropiedad(String id) {
        return propiedades.stream()
                .filter(p -> p.getId().equals(id))
                .findFirst().orElse(null);
    }


    public List<Empleado>  getEmpleados()    { return empleados; }
    public List<Propiedad> getPropiedades()  { return propiedades; }
    public List<Venta>     getVentas()       { return ventas; }
}