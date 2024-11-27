package es.studium.practica;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class GestorConexiones
{
	// Variables para configurar la conexión a la base de datos
		String driver = "com.mysql.cj.jdbc.Driver"; // Driver para MySQL
		String url = "jdbc:mysql://localhost:3306/juegobolin"; // URL de conexión a la base de datos
		String login = "root"; // Usuario de la base de datos
		String password = "Studium2024;"; // Contraseña de la base de datos
		Connection connection = null; // Objeto para la conexión
		Statement statement = null; // Objeto para ejecutar sentencias SQL
		ResultSet rs = null; // Objeto para manejar los resultados de consultas

		// Constructor vacío
		public GestorConexiones() {}

	    // Método para establecer la conexión a la base de datos
	    public void conexion() {
	        try {
	            // Cargar el driver de MySQL
	            Class.forName(driver);

	            // Establecer la conexión con la base de datos
	            connection = DriverManager.getConnection(url, login, password);

	            System.out.println("Conexión exitosa a la base de datos");
	        } catch (ClassNotFoundException e) {
	            // Manejar error si el driver no se encuentra
	            System.out.println("Error: Driver de MySQL no encontrado");
	            e.printStackTrace();
	        } catch (SQLException e) {
	            // Manejar error si falla la conexión
	            System.out.println("Error de conexión a la base de datos: " + e.getMessage());
	            e.printStackTrace();
	        }
	    }

		public boolean altaTiempo(String nombre, double tiempoTotal)
		{
			 boolean altaCorrecta = true; 
		        String sentencia = "INSERT INTO ranking VALUES(null, '" + nombre + "', '" + tiempoTotal + "');"; // Inserción SQL
		        
		        try {
		            statement = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
		            statement.executeUpdate(sentencia); // Ejecutar la inserción
		        } catch(SQLException e) {
		            // Manejar error en la inserción SQL
		            System.out.println("Error en la sentencia SQL: " + e.toString());
		            altaCorrecta = false; // Indicar fallo
		        }

		        return altaCorrecta; // Devolver resultado
		}
	    
		// Método para desconectar de la base de datos
	    public void desconectar() {
	        try {
	            // Cerrar el statement y la conexión
	            statement.close();
	            connection.close();
	        } catch(SQLException e) {
	            // Manejar error al cerrar la conexión
	            System.out.println("Error al cerrar " + e.toString());
	        }
	    }

		public String consultarRanking()
		{
			 String listadoJugadores = "Nombre - Tiempo\n"; // Cabecera del listado
		        String sentencia = "SELECT nombreJugador, tiempoJugador FROM ranking ORDER BY tiempoJugador LIMIT 10"; // Consulta SQL
		        try {
		            statement = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
		            rs = statement.executeQuery(sentencia); // Ejecutar la consulta
		            while(rs.next()) {
		                // Concatenar cada registro al listado
		                listadoJugadores = listadoJugadores + rs.getString("nombreJugador") + " - " + rs.getString("tiempoJugador")+ "\n";
		            }
		        } catch(SQLException e) {
		            // Manejar error en la consulta SQL
		            System.out.println("Error en la sentencia SQL: " + e.toString());
		        }
		        return listadoJugadores; // Devolver el listado
		}
}
