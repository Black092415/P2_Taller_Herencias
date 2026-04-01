package modelo;

import interfaz.IComisionable;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


public abstract class Empleado implements IComisionable {

    private String id;
    private String nombre;
    private String cedula;
    private List<Venta> ventas;


    protected static final int UMBRAL_BONO_VOLUMEN = 3;

    public Empleado(String id, String nombre, String cedula) {
        this.id      = id;
        this.nombre  = nombre;
        this.cedula  = cedula;
        this.ventas  = new ArrayList<>();
    }


    public Empleado(String id, String nombre) {
        this(id, nombre, "N/A");
    }


    public void agregarVenta(Venta venta) {
        if (venta == null) throw new IllegalArgumentException("La venta no puede ser nula.");
        ventas.add(venta);
    }


    public List<Venta> getVentasPorPeriodo(int mes, int anio) {
        return ventas.stream()
                .filter(v -> v.getMes() == mes && v.getAnio() == anio)
                .collect(Collectors.toList());
    }


    public int getTotalVentas() { return ventas.size(); }


    public abstract double getMultiplicador();


    public abstract String getTipoEmpleado();


    public String getId()      { return id; }
    public String getNombre()  { return nombre; }
    public String getCedula()  { return cedula; }
    public List<Venta> getVentas() { return ventas; }


    @Override
    public String toString() {
        return String.format("%s | ID: %s | Cédula: %s | Tipo: %s",
                nombre, id, cedula, getTipoEmpleado());
    }
}
