package manejo_conectores.Sol_11_1;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class DatabaseManager implements AutoCloseable {

	private Database db;
	private Connection con = null;
	
	public DatabaseManager() {
		db = new Database();
		Connection con = db.createConnection();
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
				rs.close();
			} catch (SQLException e) {
				System.err.println("Coudnt close the Statement - getTaskBetweenDates");
			}
		}
	}
	
	public void modifyTarea (int[] ids) throws SQLException {
		boolean autoCommit = true;
		Connection con = db.createConnection();
		Statement st = null;
		
		try {
			st = con.createStatement();
			autoCommit = con.getAutoCommit();
			con.setAutoCommit(false);
			for (int i = 0; i < ids.length; i++) {
				String query = "UPDATE Tareas SET finalizada = TRUE WHERE id = " + ids[i];
				st.executeUpdate(query);
			}
			con.commit();
			con.setAutoCommit(autoCommit);
		} catch (SQLException e) {
			con.rollback();
			throw e;
			//e.printStackTrace();
		} finally {
			try {
				con.close();
				st.close();
			} catch (SQLException e) {
				System.err.println("Coudnt close the Statement - modifyTarea");
			}
		}
		
	}
	
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
	
	public void modifyFinalizada (int ids[], boolean finalizada) {
		boolean autoCommit = true;
		Connection con = db.createConnection();
		CallableStatement cstm = null;
		
        try {
        	cstm = con.prepareCall("{call modificar_finalizadas(?, ?)}");
        	autoCommit = con.getAutoCommit();
        	con.setAutoCommit(false);
        	
        	for(int i=0; i<ids.length; i++) {
        		cstm.setInt(1, ids[i]);
        		cstm.setBoolean(2, finalizada);
        		
        		cstm.execute();
        	}
        	con.commit();
		} 
        catch (SQLException e) {
			System.out.println("Error en la lectura de la base de datos, rollback done");
			try {
		        if (con != null) {
		            con.rollback();
		        }
		    } catch (SQLException rollbackEx) {
		        rollbackEx.printStackTrace();
		    }
        }
        finally {
        	try {
        		con.setAutoCommit(autoCommit);
        		
        		if (cstm != null && !cstm.isClosed()) {
            		cstm.close();
        		}
			} catch (SQLException e) {
				e.printStackTrace();
			}
        }
  
	}

	public void InsertData(String nameTable) throws SQLException {
		boolean autoCommit = true;
		
		File script = new File(db.getRouteInsertData());

		Connection con = db.createConnection();
		Statement st = null;

		try {
			st = con.createStatement();
			autoCommit = con.getAutoCommit();
			con.setAutoCommit(false);
			
			ArrayList<String> lineas = readLines(script);
			for (int i=0; i<lineas.size(); i++) {
	            String query = "INSERT INTO " + nameTable + " (id, descripcion, fecha_inicio, fecha_final, finalizada) VALUES " + lineas.get(i);
				st.executeUpdate(query);
			}
			con.commit();
			con.setAutoCommit(autoCommit);
			//st.executeUpdate(script.toString());

		} catch (SQLException e) {
			con.rollback();
			throw e;
			//e.printStackTrace();
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


	@Override
	public void close() throws Exception {
		try {
			if (con != null && !con.isClosed()) {
                con.close();
                System.out.println("Conexión cerrada");
            } 
		}catch (SQLException e) {
        	System.out.println("Se ha producido un error al ceerar la conexión");
        }
		
	}
}
