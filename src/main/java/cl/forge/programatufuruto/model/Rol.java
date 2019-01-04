package cl.forge.programatufuruto.model;

public class Rol {

    private int id_rol;
    private String nombre;
    private String descripcion;


    public Rol() {
        this.id_rol = 0;
        this.nombre = "";
        this.descripcion = "";
    }

    public Rol(int id_rol) {
        this.id_rol = id_rol;
        this.nombre = "";
        this.descripcion = "";
    }

    public Rol(int id_rol, String nombre, String descripcion) {
        this.id_rol = id_rol;
        this.nombre = nombre;
        this.descripcion = descripcion;
    }

    public int getId_rol() {
        return id_rol;
    }

    public void setId_rol(int id_rol) {
        this.id_rol = id_rol;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    @Override
    public String toString(){
        return this.id_rol + " - "+ this.nombre +  " - " + this.descripcion;
    }
}
