package modelo;

public abstract class Propiedad {

    private String id;
    private String descripcion;
    private double precio;


    public Propiedad(String id, String descripcion, double precio) {
        if (precio <= 0) {
            throw new IllegalArgumentException("El precio debe ser mayor a 0.");
        }
        this.id = id;
        this.descripcion = descripcion;
        this.precio = precio;
    }


    public Propiedad(String id, double precio) {
        this(id, "Sin descripción", precio);
    }


    public abstract double getPorcentajeComision();

    public abstract String getTipo();

    public double calcularComisionBruta() {
        return precio * getPorcentajeComision();
    }


    public String getId()          { return id; }
    public String getDescripcion() { return descripcion; }
    public double getPrecio()      { return precio; }


    @Override
    public String toString() {
        return String.format("[%s] %s | Tipo: %s | Precio: $%,.0f | Comisión base: %.1f%%",
                id, descripcion, getTipo(), precio, getPorcentajeComision() * 100);
    }
}
