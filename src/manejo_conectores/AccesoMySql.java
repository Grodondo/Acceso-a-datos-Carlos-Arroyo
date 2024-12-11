package manejo_conectores;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class AccesoMySql {

	public void createTableTarea() {
		Connection con = null;
		Statement st = null;
		String sentenciaSql = null;

		try {
			String url = "jdbc:mysql://localhost:3306/dannys_dinner";

			con = DriverManager.getConnection(url, "root", "");
			st = con.createStatement();					
			
			sentenciaSql = "CREATE TABLE tarea ( \n" + "id INTEGER NOT NULL, \n" 
					+ "descripcion VARCHAR(100) NOT NULL, \n"
					+ "fecha_inicio DATE DEFAULT GETDATE(), \n" 
					+ "fecha_final DATE NOT NULL, \n"
					+ "finalizada BOOLEAN DEFAULT false, \n"
					+ "CONSTRAINT pk_tarea PRIMARY KEY (id));";
			st.executeUpdate(sentenciaSql);

			System.out.println("Tabla creada exitosamente");
		} catch (SQLException ex) {
			System.out.println("Error" + ex.getMessage());
		} finally {
			if (st != null) {
				try {
					st.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}

		}

	}

	public void insertaTarea() {
		Connection con = null;
		Statement statement = null;
		String sentenciaSQL = null;
		try {
			String url = "jdbc:mysql://localhost:3306/dannys_dinner";
			
			con = DriverManager.getConnection(url, "root", "");
			statement = con.createStatement();
			sentenciaSQL = "INSERT INTO TAREA VALUES (1, 'Comprar manzanas', '2024-02-15', '2024-05-02', true)";
			statement.executeUpdate(sentenciaSQL);
			sentenciaSQL = "INSERT INTO TAREA VALUES (2, 'Estudiar examen', '2024-05-02', '2024-05-18', true)";
			statement.executeUpdate(sentenciaSQL);
			sentenciaSQL = "INSERT INTO TAREA VALUES (3, 'Comprar billete para Sidney', '2024-02-15', '2024-05-20', true)";
			statement.executeUpdate(sentenciaSQL);
		} catch (SQLException ex) {
			System.out.println("Error en el método insertaUsuarios: \n" + ex.getMessage());
		} finally {
			try {
				if (statement != null && !statement.isClosed()) {
					statement.close();
					System.out.println("Sentencia sql finalizada");
				}
			} catch (SQLException ex) {
				System.out.println("Se ha producido un error al cerrar la sentencia: \n" + ex.getStackTrace());
			}
			try {
				if (con != null && !con.isClosed()) {
					con.close();
					System.out.println("Conexión cerrada");
				}
			} catch (SQLException ex) {
				System.out.println("Se ha producido un error al cerrar la conexión: \n" + ex.getStackTrace());
			}
		}
	}

	public void getConnection() {
		Connection con = null;
		try {
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/dannys_dinner", "root", "");
			System.out.println("Conexión exitosa");

			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery("SELECT * FROM usuario;");

			while (rs.next()) {
				String id = rs.getString("id");
				String fecha = rs.getString("nombre");
				System.out.println(id + " " + fecha);
			}

			st.close();

			System.out.println("Query completada");

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (con != null) {
				try {
					con.close();
				} catch (SQLException e) {
				}
			}
		}

	}

}
