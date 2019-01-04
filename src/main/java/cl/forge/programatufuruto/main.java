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


        Rol rol1 = new Rol(1, "Admin", "Administra");
        Rol rol2 = new Rol(2, "Moderador", "Modera");
        Rol rol3 = new Rol(3, "Usuario", "Se registra y navega");


        Usuario usuario = new Usuario("JUn lopezz", "asdadasd@gatito.zorrito", "1asd234",rol3);
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


