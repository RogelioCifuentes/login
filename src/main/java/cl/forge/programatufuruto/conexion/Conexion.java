package cl.forge.programatufuruto.conexion;

import java.sql.Connection;

import java.sql.DriverManager;

public class Conexion {

    private static Connection conexion;

    private static final String driver = "com.mysql.jdbc.Driver";

    private static final String usuario = "root";

    private static final String password = "1234";

    private static final String url = "jdbc:mysql://localhost:3306/login";


    public Conexion(){

        conexion = null;

        try{

            Class.forName(driver);

            conexion = DriverManager.getConnection(url,usuario,password);

            if(conexion != null){

                System.out.println("Conexion exitosa.");

            }

        }catch(Exception e){

            System.out.println("Error "+ e);

        }

    }        //Retorna la conexion

    public Connection getConexion(){

        return conexion;

    }        //Metodo que desconecta de la DB

    public void desconectar(){

        conexion = null;

        if( conexion == null ){

            System.out.println("Desconectado.");

        }

    }

}