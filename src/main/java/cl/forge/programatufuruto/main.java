package cl.forge.programatufuruto;

import cl.forge.programatufuruto.conexion.Conexion;
import cl.forge.programatufuruto.dao.RolDAO;
import cl.forge.programatufuruto.dao.UsuarioDAO;
import cl.forge.programatufuruto.exceptions.CorreoEnUsoException;
import cl.forge.programatufuruto.exceptions.UsuarioExistenteException;
import cl.forge.programatufuruto.model.Rol;
import cl.forge.programatufuruto.model.Usuario;

import java.security.NoSuchAlgorithmException;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class main {

    public static void main(String[] args) throws SQLException, CorreoEnUsoException, UsuarioExistenteException, NoSuchAlgorithmException {

        PreparedStatement psInsertar;
        Statement statement;


        System.out.println("hello world");
        Conexion conexion = new Conexion();
        conexion.getConexion();

        Rol rol = new Rol(23, "Admin33", "admin333333istra");

        //SE INGRESA AL PRIMER INTENTO UN ROL
/*
        String SQL = "INSERT INTO roles(id_rol,nombre,descripcion) VALUES(?,?,?)";
        psInsertar = conexion.getConexion().prepareStatement(SQL);
        psInsertar.setInt(1,rol.getId_rol());
        psInsertar.setString(2,rol.getNombre());
        psInsertar.setString(3,rol.getDescripcion());
        psInsertar.executeUpdate();

        LocalDate login = LocalDate.now();
        String login2 = LocalDateTime.now().toString();
        System.out.println(login2);

        System.out.println(login);
        */

        Usuario usuario = new Usuario(57,"qesad", "hla@gmail.com", "1234",rol);
        UsuarioDAO dao = new UsuarioDAO();

        try {
            dao.guardarUsuario(usuario);
        }catch (SQLException e) {
            e.printStackTrace();
        } catch (CorreoEnUsoException ex) {
            ex.printStackTrace();
        } catch (UsuarioExistenteException asd) {
            asd.printStackTrace();
        }
    }
 }


