package cl.forge.programatufuruto.dao;

import cl.forge.programatufuruto.conexion.Conexion;
import cl.forge.programatufuruto.exceptions.RolEnUsoException;
import cl.forge.programatufuruto.model.Rol;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class RolDAO {

    PreparedStatement psInsertar;
    Statement statement;
    Conexion conexion;

    public ArrayList<Rol> obtenerRoles() throws SQLException {

       ArrayList<Rol> roles = new ArrayList<>();

        psInsertar = conexion.getConexion().prepareStatement("SELECT * FROM roles");
        ResultSet rs = psInsertar.executeQuery();

        while(rs.next()){
            Rol rol = new Rol();

            rol.setId_rol(rs.getInt(1));
            rol.setNombre(rs.getString(2));
            rol.setDescripcion(rs.getString(3));

            roles.add(rol);
        }
        return roles;
    }

    public void eliminarRol(Rol rol) throws SQLException, RolEnUsoException {

        psInsertar = conexion.getConexion().prepareStatement("SELECT id_rol FROM usuarios WHERE id_rol = ?");
        psInsertar.setInt(1, rol.getId_rol());
        ResultSet rs = psInsertar.executeQuery();

        if (rs.next()) {
            throw new RolEnUsoException("Rol actualmente en uso");
        } else {
            psInsertar = conexion.getConexion().prepareStatement("DELETE FROM roles WHERE id_rol = ?");
            psInsertar.setInt(1, rol.getId_rol());
            psInsertar.executeUpdate();
            System.out.println("Datos eliminados correctamente.");
        }
    }
}
