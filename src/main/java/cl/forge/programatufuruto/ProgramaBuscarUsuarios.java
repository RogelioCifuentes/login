package cl.forge.programatufuruto;

import cl.forge.programatufuruto.dao.UsuarioDAO;
import cl.forge.programatufuruto.model.Usuario;


import java.sql.SQLException;
import java.util.ArrayList;

public class ProgramaBuscarUsuarios {

    public static void main(String[] args) {

        try {
            UsuarioDAO dao = new UsuarioDAO();
            ArrayList<Usuario> lista = dao.obtenerUsuarios();
            imprimirUsuarios(lista);

        } catch (SQLException e) {
            e.printStackTrace();
        }


    }

    public static void imprimirUsuarios(ArrayList<Usuario> usuarios){
        for(Usuario usuario : usuarios){
            System.out.println(usuario);
        }
    }
}
