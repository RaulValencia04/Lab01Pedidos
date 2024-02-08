package Conexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {
    private String url;
    private String usuario;
    private String password;
    private Connection conexion;

    public Conexion() {
        this.url = "jdbc:mysql://localhost:3306/lab01";
        this.usuario = "root";
        this.password = "";
        
    }


    public Connection obtenerConexion() throws SQLException {
        if (conexion == null || conexion.isClosed()) {
            try {
                // Cargar el controlador de la base de datos (Driver)
                Class.forName("com.mysql.jdbc.Driver");

                // Establecer la conexión a la base de datos
                conexion = DriverManager.getConnection(url, usuario, password);

                System.out.println("Conexión establecida con éxito.");
            } catch (ClassNotFoundException | SQLException e) {
                throw new SQLException("Error al establecer la conexión: " + e.getMessage());
            }
        }
        return conexion;
    }

    public void cerrarConexion() throws SQLException {
        if (conexion != null && !conexion.isClosed()) {
            conexion.close();
            System.out.println("Conexión cerrada con éxito.");
        }
    }
}
