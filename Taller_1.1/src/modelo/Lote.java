package modelo;

public class Lote extends Propiedad {

    private static final double COMISION_BASE = 0.015;
    private static final double BONO_VOLUMEN  = 0.003;
    private double areaM2;

    public Lote(String id, String descripcion, double precio, double areaM2) {
        super(id, descripcion, precio);
        this.areaM2 = areaM2;
    }

    public Lote(String id, double precio) {
        super(id, precio);
        this.areaM2 = 0;
    }

    @Override
    public double getPorcentajeComision() { return COMISION_BASE; }

    public double getPorcentajeConBono()  { return COMISION_BASE + BONO_VOLUMEN; }

    @Override
    public String getTipo() { return "Lote"; }

    public double getAreaM2() { return areaM2; }
}
