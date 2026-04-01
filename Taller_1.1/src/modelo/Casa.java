package modelo;

public class Casa extends Propiedad {

    private static final double COMISION_BASE    = 0.035;
    private static final double BONO_VOLUMEN     = 0.005;
    private int habitaciones;

    public Casa(String id, String descripcion, double precio, int habitaciones) {
        super(id, descripcion, precio);
        this.habitaciones = habitaciones;
    }


    public Casa(String id, double precio) {
        super(id, precio);
        this.habitaciones = 0;
    }

    @Override
    public double getPorcentajeComision() { return COMISION_BASE; }

    public double getPorcentajeConBono()  { return COMISION_BASE + BONO_VOLUMEN; }

    @Override
    public String getTipo() { return "Casa"; }

    public int getHabitaciones() { return habitaciones; }
}
