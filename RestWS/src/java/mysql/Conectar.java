/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mysql;

import com.mysql.jdbc.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author rodolfo
 */
public class Conectar {
    private static Connection conn;
    private static final String driver = "com.mysql.jdbc.Driver";
    private static final String user = "root";
    private static final String password = "ubuntu1604";
    private static final String url = "jdbc:mysql://35.196.93.7:3306/centro_salud";
    private String estado = "desconectado";
    
    public Conectar()
    {
        conn = null;
        try
        {
            Class.forName(driver);
            conn = (Connection) DriverManager.getConnection(url, user, password);
            if(conn != null)
            {
                estado = "conectado";
                System.out.println("Conexion con MySQL establecida");
            }
        }
        catch (ClassNotFoundException | SQLException e)
        {
            System.out.println("Error al conectar: " + e.getMessage());
        }
    }
    
    public Connection getConexion()
    {
        return conn;
    }
    
    public String getEstado()
    {
        return this.estado;
    }
    
    public void desconectar()
    {
        conn = null;
        if(conn == null)
        {
            System.out.println("Conexion terminada");
        }
    }
    
}
