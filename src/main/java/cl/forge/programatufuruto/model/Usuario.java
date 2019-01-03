package cl.forge.programatufuruto.model;

import java.util.Date;

public class Usuario {

    private int id_usuario;
    private String nombre_usuario;
    private String email;
    private String password;
    private Date ultimo_login;
    private int id_rol;

    public Usuario(int id_usuario, String nombre_usuario, String email, String password, Date ultimo_login, int id_rol) {
        this.id_usuario = id_usuario;
        this.nombre_usuario = nombre_usuario;
        this.email = email;
        this.password = password;
        this.ultimo_login = ultimo_login;
        this.id_rol = id_rol;
    }

    public Usuario() {
        this.id_usuario = 55;
        this.nombre_usuario = "";
        this.email = "";
        this.password = "";
        this.ultimo_login = null;
        this.id_rol = 3;
    }


    public int getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(int id_usuario) {
        this.id_usuario = id_usuario;
    }

    public String getNombre_usuario() {
        return nombre_usuario;
    }

    public void setNombre_usuario(String nombre_usuario) {
        this.nombre_usuario = nombre_usuario;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getUltimo_login() {
        return ultimo_login;
    }

    public void setUltimo_login(Date ultimo_login) {
        this.ultimo_login = ultimo_login;
    }

    public int getId_rol() {
        return id_rol;
    }

    public void setId_rol(int id_rol) {
        this.id_rol = id_rol;
    }

    @Override
    public String toString(){

        return this.id_usuario+ " - " +
                this.nombre_usuario + " - " +
                this.password + " - " +
                this.email + " - " +
                this.ultimo_login + " - "+
                this.id_rol;

    }
}
