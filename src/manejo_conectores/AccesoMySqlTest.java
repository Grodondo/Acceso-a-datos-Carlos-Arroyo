package manejo_conectores;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.SQLException;

import org.junit.jupiter.api.Test;

class AccesoMySqlTest {

	@Test
	void getConnection() throws SQLException {
		AccesoMySql ams = new AccesoMySql();
		ams.getConnection();
	}

	//@Test
	void createTable() throws SQLException {
		AccesoMySql ams = new AccesoMySql();
		ams.createTableTarea();
	}
	
	
	//@Test
	void insertaUsuarios() {
		AccesoMySql ams = new AccesoMySql();
		ams.insertaTarea();
	}
	
}
