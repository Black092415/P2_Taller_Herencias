package modelo;

import java.time.LocalDate;
import java.util.List;


public class VendedorJunior extends Empleado {

    private static final double MULTIPLICADOR = 1.0;

    public VendedorJunior(String id, String nombre, String cedula) {
        super(id, nombre, cedula);
    }

    public VendedorJunior(String id, String nombre) {
        super(id, nombre);
    }

    @Override
    public double getMultiplicador() { return MULTIPLICADOR; }

    @Override
    public String getTipoEmpleado() { return "Vendedor Junior"; }


    @Override
    public double calcularComision() {
        return calcularComisionPeriodo(
                LocalDate.now().getMonthValue(),
                LocalDate.now().getYear());
    }


    public double calcularComision(int mes, int anio) {
        return calcularComisionPeriodo(mes, anio);
    }

    private double calcularComisionPeriodo(int mes, int anio) {
        List<Venta> ventasPeriodo = getVentasPorPeriodo(mes, anio);
        boolean aplicaBono = ventasPeriodo.size() >= UMBRAL_BONO_VOLUMEN;
        double total = 0;

        for (Venta v : ventasPeriodo) {
            Propiedad p = v.getPropiedad();
            double porcentaje = aplicaBono
                    ? getPorcentajeConBono(p)
                    : p.getPorcentajeComision();
            total += p.getPrecio() * porcentaje;
        }
        return total * MULTIPLICADOR;
    }

    private double getPorcentajeConBono(Propiedad p) {
        if (p instanceof Casa)        return ((Casa) p).getPorcentajeConBono();
        if (p instanceof Apartamento) return ((Apartamento) p).getPorcentajeConBono();
        if (p instanceof Lote)        return ((Lote) p).getPorcentajeConBono();
        return p.getPorcentajeComision();
    }

    @Override
    public String generarReporte() {
        int mes  = LocalDate.now().getMonthValue();
        int anio = LocalDate.now().getYear();
        List<Venta> ventasPeriodo = getVentasPorPeriodo(mes, anio);
        boolean aplicaBono = ventasPeriodo.size() >= UMBRAL_BONO_VOLUMEN;

        StringBuilder sb = new StringBuilder();
        sb.append("=".repeat(55)).append("\n");
        sb.append("  REPORTE DE COMISIONES — ").append(getTipoEmpleado()).append("\n");
        sb.append("  Empleado  : ").append(getNombre()).append("\n");
        sb.append("  Período   : ").append(mes).append("/").append(anio).append("\n");
        sb.append("  Bono vol. : ").append(aplicaBono ? "SÍ (+bono aplicado)" : "NO").append("\n");
        sb.append("-".repeat(55)).append("\n");
        for (Venta v : ventasPeriodo) {
            sb.append("  ").append(v).append("\n");
        }
        sb.append("-".repeat(55)).append("\n");
        sb.append(String.format("  TOTAL COMISIÓN: $%,.2f%n", calcularComision(mes, anio)));
        sb.append("=".repeat(55)).append("\n");
        return sb.toString();
    }
}