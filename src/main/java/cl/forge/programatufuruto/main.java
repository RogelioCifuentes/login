package cl.forge.programatufuruto;

import cl.forge.programatufuruto.conexion.Conexion;
import cl.forge.programatufuruto.dao.RolDAO;
import cl.forge.programatufuruto.dao.UsuarioDAO;
import cl.forge.programatufuruto.exceptions.CorreoEnUsoException;
import cl.forge.programatufuruto.exceptions.UsuarioExistenteException;
import cl.forge.programatufuruto.model.Rol;
import cl.forge.programatufuruto.model.Usuario;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class main {

    public static void main(String[] args) throws SQLException,CorreoEnUsoException,UsuarioExistenteException {
        System.out.println("hello world");
        Conexion conexion = new Conexion();
        conexion.getConexion();

        Rol rol = new Rol(10, "Admin", "administra");

        //SE INGRESA AL PRIMER INTENTO UN ROL
        /*
        String SQL = "INSERT INTO roles(id_rol,nombre,descripcion) VALUES(?,?,?)";
        PreparedStatement psInsertar = conexion.getConexion().prepareStatement(SQL);
        psInsertar.setInt(1,rol.getId_rol());
        psInsertar.setString(2,rol.getNombre());
        psInsertar.setString(3,rol.getDescripcion());
        psInsertar.executeUpdate();
*/

        Usuario usuario = new Usuario(55,"qwesad", "hola@gmail.com", "1234", null, 3);

        System.out.println(usuario);
        UsuarioDAO dao = new UsuarioDAO();

        dao.guardarUsuario(usuario);

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

