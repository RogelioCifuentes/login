package cl.forge.programatufuruto;

import cl.forge.programatufuruto.conexion.Conexion;
import cl.forge.programatufuruto.dao.UsuarioDAO;
import cl.forge.programatufuruto.exceptions.CorreoEnUsoException;
import cl.forge.programatufuruto.exceptions.UsuarioExistenteException;
import cl.forge.programatufuruto.model.Rol;
import cl.forge.programatufuruto.model.Usuario;

import java.sql.SQLException;

public class main {

    public static void main(String[] args) {
        System.out.println("hello world");
        Conexion conexion = new Conexion();
        conexion.getConexion();

       Rol rol = new Rol(1, "Admin", "administra");
        Usuario usuario = new Usuario(55,"pedrito", "hola@gmail.com", "1234", new java.util.Date(20191242), rol);

        UsuarioDAO dao = new UsuarioDAO();
        try {
            dao.guardarUsuario(usuario);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (CorreoEnUsoException ex) {
            ex.printStackTrace();
        } catch (UsuarioExistenteException asd) {

        }
    }

    }

