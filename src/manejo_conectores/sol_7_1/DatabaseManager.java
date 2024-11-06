package manejo_conectores.sol_7_1;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class DatabaseManager {

	Database db;
	
	public DatabaseManager() {
		db = new Database();
	}
	
	
	public void getTaskBetweenDates(String fecha_inicio, String fecha_fin) {
		
		Connection con = db.createConnection();
		Statement st = null;
		ResultSet rs = null;
		
		//String query = "SELECT * FROM Tareas WHERE fecha_inicio >= '"+ fecha_inicio +"' AND fecha_fin <= '"+ fecha_fin +"'";
		String query = "SELECT * FROM Tareas";
		
		try {
			st = con.createStatement();
			rs = st.executeQuery(query);
			
			while(rs.next()) {
		          int id = rs.getInt("id");
		            String description = rs.getString("descripcion");
		            LocalDate startDate = LocalDate.parse(rs.getString("fecha_inicio"), DateTimeFormatter.ISO_DATE);
		            LocalDate endDate = LocalDate.parse(rs.getString("fecha_final"), DateTimeFormatter.ISO_DATE);
		            boolean isCompleted = rs.getBoolean("finalizada");
		            
		            if(startDate.isAfter(LocalDate.parse(fecha_inicio)) && endDate.isBefore(LocalDate.parse(fecha_fin))) {
		            	  System.out.println("ID: " + id + " Description: " + description + " Start Date: " + startDate
									+ " End Date: " + endDate + " Is Completed: " + isCompleted);	
		            }
			}
				
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				con.close();
				st.close();
			} catch (SQLException e) {
				System.err.println("Coudnt close the Statement - getTaskBetweenDates");
			}
		}
	}
	
	public void modifyTarea (int[] ids) {
		
		Connection con = db.createConnection();
		Statement st = null;
		
		try {
			st = con.createStatement();
			for (int i = 0; i < ids.length; i++) {
				String query = "UPDATE Tareas SET finalizada = TRUE WHERE id = " + ids[i];
				st.executeUpdate(query);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				con.close();
				st.close();
			} catch (SQLException e) {
				System.err.println("Coudnt close the Statement - modifyTarea");
			}
		}
		
	}
	
	/**
	 * 
	 * @param script - File with the script to execute
	 */
	public void CreateTable() {

		File script = new File(db.getRouteCreateTable());

		Connection con = db.createConnection();
		Statement st = null;

		try {
			st = con.createStatement();

			st.executeUpdate(readAllData(script));

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				con.close();
				st.close();
			} catch (SQLException e) {
				System.err.println("Coudnt close the Statement - CreateTable");
			}
		}

	}

	public void InsertData(String nameTable) {

		File script = new File(db.getRouteInsertData());

		Connection con = db.createConnection();
		Statement st = null;

		try {
			st = con.createStatement();
			ArrayList<String> lineas = readLines(script);
			for (int i=0; i<lineas.size(); i++) {
	            String query = "INSERT INTO " + nameTable + " (id, descripcion, fecha_inicio, fecha_final, finalizada) VALUES " + lineas.get(i);
				st.executeUpdate(query);
			}

			//st.executeUpdate(script.toString());

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				con.close();
				st.close();
			} catch (SQLException e) {
				System.err.println("Coudnt close the Statement - CreateDatabase");
			}
		}

	}
	
	private String readAllData(File f) {
		String data = "";
		try (BufferedReader br = new BufferedReader(new FileReader(f))) {
			String linea;
			while ((linea = br.readLine()) != null) {
				data += linea;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return data;
	}

	private ArrayList<String> readLines(File f) {

		ArrayList<String> lineas = new ArrayList<String>();

		try (BufferedReader br = new BufferedReader(new FileReader(f))) {
			String linea;
			while ((linea = br.readLine()) != null) {
				lineas.add(linea);

			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		return lineas;
	}
}
