package cl.forge.programatufuruto.dao;

import cl.forge.programatufuruto.conexion.Conexion;
import cl.forge.programatufuruto.exceptions.CorreoEnUsoException;
import cl.forge.programatufuruto.exceptions.UsuarioExistenteException;
import cl.forge.programatufuruto.model.Rol;
import cl.forge.programatufuruto.model.Usuario;


import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;


public class UsuarioDAO {

    private PreparedStatement psInsertar;
    private Statement statement;
    private Conexion conexion;

    public UsuarioDAO(){
        this.conexion = new Conexion();
    }

    public PreparedStatement getPsInsertar() {
        return psInsertar;
    }

    public void setPsInsertar(PreparedStatement psInsertar) {
        this.psInsertar = psInsertar;
    }

    public Statement getStatement() {
        return statement;
    }

    public void setStatement(Statement statement) {
        this.statement = statement;
    }

    public Conexion getConexion(Conexion conexion) {
        return this.conexion;
    }

    public void setConexion(Conexion conexion) {
        this.conexion = conexion;
    }

    public String encriptar(String cadena) throws NoSuchAlgorithmException{

        //Encriptacion MD5

        MessageDigest md = MessageDigest.getInstance("MD5");
        byte[] hashInBytes = md.digest(cadena.getBytes(StandardCharsets.UTF_8));

        StringBuilder sb = new StringBuilder();
        for (byte b : hashInBytes) {
            sb.append(String.format("%02x", b));
        }
        return sb.toString();
    }

    public boolean existeUsuario( Usuario usuario ) throws SQLException {
        //BUSQUEDA DE NOMBRE EN BASE DE DATOS
        psInsertar = conexion.getConexion().prepareStatement("SELECT nombre_usuario FROM usuarios WHERE nombre_usuario=?");
        psInsertar.setString(1, usuario.getNombre_usuario());
        ResultSet rs = psInsertar.executeQuery();
        return rs.next();
    }

    public boolean existeUsuarioPorEmail( Usuario usuario ) throws SQLException{
        //BUSQUEDA DE EMAIL EN BASE DE DATOS
        psInsertar = conexion.getConexion().prepareStatement("SELECT * FROM usuarios WHERE email=?");
        psInsertar.setString(1, usuario.getEmail());
        ResultSet rs = psInsertar.executeQuery();
        return rs.next();
    }

    public void guardarUsuario(Usuario u) throws CorreoEnUsoException, UsuarioExistenteException, SQLException {
        try {
        //VERIFICAR SI NOMBRE EXISTE, SI NO, SETEARLO
        if (existeUsuario(u)) {
            throw new UsuarioExistenteException("Usuario ya existe");          //LLAMA AL METODO existeUsuario, que verifica en la DB si el nombre ya esta en uso.
        } else if (existeUsuarioPorEmail(u)) {
            throw new CorreoEnUsoException("Email ya utilizado");             //LLAMA AL METODO existeUsuariPorEmail, que verifica en la DB si el email ya esta en uso.
        } else {


                final String SQL = "INSERT INTO usuarios(nombre_usuario,email,password,ultimo_login,id_rol) VALUES (?,?,?,?,?)";
                psInsertar = conexion.getConexion().prepareStatement(SQL);

                psInsertar.setString(1, u.getNombre_usuario());
                psInsertar.setString(2, u.getEmail());
                psInsertar.setString(3, encriptar(u.getPassword()));   //Se ingresa contraseña encriptada
                psInsertar.setString(4, u.getUltimo_login());    //TRASPASA LA FECHA ACTUAL DEL SISTEMA, DE JAVA A SQL.
                psInsertar.setInt(5, u.getId_rol().getId_rol());
                psInsertar.executeUpdate();
                System.out.println("Usuario ingresado correctamente");
        }

        } catch (SQLException ex) {
            ex.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }

    //ACTUALIZA FECHA EN LA BASE DE DATOS
    public void actualizarFecha(Usuario usuario) throws SQLException{

        psInsertar = conexion.getConexion().prepareStatement("UPDATE usuarios SET ultimo_login = ? WHERE nombre_usuario = ?");
        String horaActual = LocalDateTime.now().toString();
        psInsertar.setString(1, horaActual);  //TRASPASA LA FECHA ACTUAL DEL SISTEMA, DE JAVA A SQL.
        psInsertar.setString(2,usuario.getNombre_usuario());
    }

    //VERIFICA SI EL NOMBRE USUARIO Y EMAIL YA EXISTEN EN DATABASE, SINO, LANZA EXCEPCIONES ESPECIFICAS.
    public boolean login(Usuario usuario) throws SQLException{

        try {
            psInsertar = conexion.getConexion().prepareStatement("SELECT * FROM usuario WHERE nombre_usuario = ? AND password = ? ");
            psInsertar.setString(1, usuario.getNombre_usuario());
            psInsertar.setString(2,encriptar(usuario.getPassword()));
            ResultSet rs = psInsertar.executeQuery();

            if(rs.next()){
                usuario.setUltimo_login(usuario.getUltimo_login());              //Actualiza el objeto Usuario
                UsuarioDAO usuariodao = new UsuarioDAO();
                usuariodao.actualizarFecha(usuario);                            //Actualiza la base de datos
            }else{
                return false;
            }

        }catch(NoSuchAlgorithmException ex){
            ex.printStackTrace();
        }
        System.out.println("Login exitoso.");
        return true;
    }

    // RETORNA LISTA DE USUARIOS
    public ArrayList<Usuario> obtenerUsuarios() throws SQLException{

        ArrayList<Usuario> usuarios = new ArrayList<>();

        psInsertar = conexion.getConexion().prepareStatement("SELECT * FROM usuarios");
        ResultSet rs = psInsertar.executeQuery();

        while(rs.next()){
            Usuario usuario = new Usuario();

            usuario.setId_usuario(rs.getInt(1));
            usuario.setNombre_usuario(rs.getString(2));
            usuario.setEmail(rs.getString(3));
            usuario.setPassword(rs.getString(4));
            usuario.setUltimo_login(rs.getString(5));
            usuario.setId_rol( new Rol(rs.getInt(6)) );
            usuarios.add(usuario);
        }
        return usuarios;
    }

    //BUSCAR USUARIOS POR NOMBRE_USUARIO
    public Usuario buscarPorNombreUsuario(String nombre) throws SQLException{

        Usuario usuario = new Usuario();
        psInsertar = conexion.getConexion().prepareStatement("SELECT * FROM usuarios WHERE nombre_usuario = ?");
        psInsertar.setString(1,nombre);
        ResultSet rs = psInsertar.executeQuery();

        if(rs.next()){
            usuario.setId_usuario(rs.getInt(1));
            usuario.setNombre_usuario(rs.getString(2));
            usuario.setEmail(rs.getString(3));
            usuario.setPassword(rs.getString(4));
            usuario.setUltimo_login(rs.getString(5));
            usuario.setId_rol((Rol)rs.getObject(6));
        }
        return usuario;
    }

    //BUSCAR USUARIOS POR EMAIL
    public Usuario buscarPorEmail(String email) throws SQLException{

        Usuario usuario = new Usuario();
        psInsertar = conexion.getConexion().prepareStatement("SELECT * FROM usuarios WHERE email = ?");
        psInsertar.setString(1,email);
        ResultSet rs = psInsertar.executeQuery();

        if(rs.next()){
            usuario.setId_usuario(rs.getInt(1));
            usuario.setNombre_usuario(rs.getString(2));
            usuario.setEmail(rs.getString(3));
            usuario.setPassword(rs.getString(4));
            usuario.setUltimo_login(rs.getString(5));
            usuario.setId_rol((Rol)rs.getObject(6));
        }
        return usuario;
    }

    //ACTUALIZAR ROL DE USUARIO
    public void actualizarRol(Usuario usuario, Rol rol) throws SQLException{
        psInsertar = conexion.getConexion().prepareStatement("UPDATE usuarios SET id_rol VALUES (?) WHERE nombre_usuario = ?");
        psInsertar.setObject(1,rol);
        psInsertar.setString(2,usuario.getNombre_usuario());
        psInsertar.executeUpdate();
    }

}
