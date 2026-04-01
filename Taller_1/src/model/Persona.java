package model;

public abstract class Persona {


    private String id;
    private String nombre;
    private String apellido;
    private String correo;
    private String telefono;


    public Persona(String id, String nombre, String apellido, String correo, String telefono) {
        this.id       = id;
        this.nombre   = nombre;
        this.apellido = apellido;
        this.correo   = correo;
        this.telefono = telefono;
    }


    public Persona(String id, String nombre, String apellido) {
        this(id, nombre, apellido, "", "");
    }


    public abstract String getInfo();
    public abstract String getRol();


    public String getNombreCompleto() {
        return nombre + " " + apellido;
    }


    @Override
    public String toString() {
        return String.format("[%s] %s - %s | Correo: %s",
                getRol(), getNombreCompleto(), id, correo);
    }


    public String getId()       { return id; }
    public String getNombre()   { return nombre; }
    public String getApellido() { return apellido; }
    public String getCorreo()   { return correo; }
    public String getTelefono() { return telefono; }

    public void setNombre(String nombre)     { this.nombre = nombre; }
    public void setApellido(String apellido) { this.apellido = apellido; }
    public void setCorreo(String correo)     { this.correo = correo; }
    public void setTelefono(String telefono) { this.telefono = telefono; }
}
