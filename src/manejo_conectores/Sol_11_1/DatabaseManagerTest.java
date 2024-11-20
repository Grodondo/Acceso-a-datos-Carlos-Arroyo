package manejo_conectores.Sol_11_1;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.SQLException;

import org.junit.jupiter.api.Test;

class DatabaseManagerTest {

	DatabaseManager dbm = new DatabaseManager();
	

	//@Test
	void testGetTaskBetweenDates() {
		dbm.getTaskBetweenDates("2024-09-01", "2029-01-31");
	}

	//@Test
	void testModifyTarea() {
		try {
			dbm.modifyTarea(new int[] {1,6,7});
		} catch (SQLException e) {
			System.out.println("Error en la lectura de la base de datos, rollback done");
			//e.printStackTrace();
		}
	}

	//@Test
	void testCreateTable() {
		dbm.CreateTable();
	}

	//@Test
	void testInsertData() {
		try {
			dbm.InsertData("tareas");
		} catch (SQLException e) {
			System.out.println("Error en la escritura de en la tabla, rollback done");
			//e.printStackTrace();
		}
	}
	
	@Test
	void modifyFinalizada() {
		System.out.println("Modificando finalizadas");
		dbm.modifyFinalizada(new int[] { 1, 6, 7 }, false);
	}

}
