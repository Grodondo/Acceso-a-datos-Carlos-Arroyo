package manejo_conectores.sol_8_1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class AccesoErrores {
	
	public int causeError1042() throws SQLException {
		int errorCode = -1;
		
		Connection con = null;
		try {
			con = DriverManager.getConnection("jdbc:mysql://0.0.0.0:3306/dannys_dinner", "root", "");
			
		}
		catch (Exception e) {
//			System.out.println("Error Code: " + ((SQLException)e).getErrorCode());
//			System.err.println("Message: " + e.getMessage());
			errorCode = ((SQLException)e).getSQLState().equals("08S01") ? 1042 : -1;
			System.out.println(e.getMessage());
			
		} finally {
			if (con != null) {
				try {
					con.close();
				} catch (SQLException e) {
				}
			}
		}
		
		System.out.println("Error Code: " + errorCode);
		return errorCode;
	}
	
	public int causeError1045() {
		int errorCode = -1;
		
		Connection con = null;
		try {
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/dannys_dinner", "juan", "");
			
		}
		catch (SQLException e) {
//			System.out.println("Error Code: " + ((SQLException)e).getErrorCode());
//			System.err.println("Message: " + e.getMessage());
			//errorCode = ((SQLException)e).getSQLState().equals("08S01") ? 1042 : -1;
			errorCode = e.getErrorCode();
			System.out.println(e.getMessage());
			
		} finally {
			if (con != null) {
				try {
					con.close();
				} catch (SQLException e) {
				}
			}
		}
		
		System.out.println("Error Code: " + errorCode);
		return errorCode;

	}
	
}
