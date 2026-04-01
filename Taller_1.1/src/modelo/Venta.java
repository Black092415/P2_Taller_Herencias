package modelo;

import java.time.LocalDate;


public class Venta {

    private String idVenta;
    private Propiedad propiedad;
    private LocalDate fecha;
    private int mes;
    private int anio;

    public Venta(String idVenta, Propiedad propiedad, LocalDate fecha) {
        this.idVenta    = idVenta;
        this.propiedad  = propiedad;
        this.fecha      = fecha;
        this.mes        = fecha.getMonthValue();
        this.anio       = fecha.getYear();
    }


    public Venta(String idVenta, Propiedad propiedad) {
        this(idVenta, propiedad, LocalDate.now());
    }

    public String     getIdVenta()   { return idVenta; }
    public Propiedad  getPropiedad() { return propiedad; }
    public LocalDate  getFecha()     { return fecha; }
    public int        getMes()       { return mes; }
    public int        getAnio()      { return anio; }

    @Override
    public String toString() {
        return String.format("Venta[%s] | %s | Fecha: %s", idVenta, propiedad, fecha);
    }
}