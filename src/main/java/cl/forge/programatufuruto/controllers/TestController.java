package cl.forge.programatufuruto.controllers;

import cl.forge.programatufuruto.dao.UsuarioDAO;
import cl.forge.programatufuruto.exceptions.CorreoEnUsoException;
import cl.forge.programatufuruto.exceptions.UsuarioExistenteException;
import cl.forge.programatufuruto.model.Rol;
import cl.forge.programatufuruto.model.Usuario;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


@RestController
public class TestController {

    @RequestMapping(method = RequestMethod.GET, value = "/login")
    public ArrayList<Usuario> obtenerUsuarios() {
        try {
            UsuarioDAO dao = new UsuarioDAO();
            return dao.obtenerUsuarios();

        } catch (SQLException ex) {
            ex.printStackTrace();
            return null;
        }
    }

    @RequestMapping(method = RequestMethod.POST, value = "/login")
    public void setUsuario(@RequestBody Usuario usuario) {
        try {
            UsuarioDAO dao = new UsuarioDAO();
            dao.guardarUsuario(usuario);
        } catch (SQLException ex) {
            ex.printStackTrace();
        } catch (UsuarioExistenteException e) {
            e.printStackTrace();
        } catch (CorreoEnUsoException c) {
            c.printStackTrace();
        }
    }
}

