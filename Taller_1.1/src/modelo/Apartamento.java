package modelo;

public class Apartamento extends Propiedad {

    private static final double COMISION_BASE = 0.028;
    private static final double BONO_VOLUMEN  = 0.004;
    private int piso;

    public Apartamento(String id, String descripcion, double precio, int piso) {
        super(id, descripcion, precio);
        this.piso = piso;
    }

    public Apartamento(String id, double precio) {
        super(id, precio);
        this.piso = 0;
    }

    @Override
    public double getPorcentajeComision() { return COMISION_BASE; }

    public double getPorcentajeConBono()  { return COMISION_BASE + BONO_VOLUMEN; }

    @Override
    public String getTipo() { return "Apartamento"; }

    public int getPiso() { return piso; }
}
