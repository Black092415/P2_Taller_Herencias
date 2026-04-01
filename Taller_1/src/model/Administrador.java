package model;

import interfaces.Registrable;

import java.util.Arrays;
import java.util.List;


public class Administrador extends Persona implements Registrable {

    private String       rol;
    private List<String> permisos;
    private boolean      registrado;

    public Administrador(String id, String nombre, String apellido,
                         String correo, String rol, String... permisos) {
        super(id, nombre, apellido, correo, "");
        this.rol       = rol;
        this.permisos  = Arrays.asList(permisos);
        this.registrado = false;
    }

    @Override
    public String getInfo() {
        return "ADMINISTRADOR | Rol: " + rol + " | Permisos: " + permisos;
    }

    @Override
    public String getRol() { return "Administrador"; }

    @Override
    public void registrar() {
        this.registrado = true;
        System.out.println("Administrador registrado: " + getNombreCompleto());
    }

    @Override
    public boolean estaRegistrado() { return registrado; }

    @Override
    public String obtenerCodigoRegistro() { return getId(); }

    public boolean tienePermiso(String permiso) {
        return permisos.contains(permiso);
    }

    public String getRolAdmin()        { return rol; }
    public List<String> getPermisos()  { return permisos; }
}
