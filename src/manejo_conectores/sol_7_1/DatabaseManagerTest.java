package manejo_conectores.sol_7_1;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class DatabaseManagerTest {

	DatabaseManager dbm = new DatabaseManager();
	

	@Test
	void testGetTaskBetweenDates() {
		dbm.getTaskBetweenDates("2024-09-01", "2029-01-31");
	}

	//@Test
	void testModifyTarea() {
		dbm.modifyTarea(new int[] {1,6,7});
	}

	//@Test
	void testCreateTable() {
		dbm.CreateTable();
	}

	//@Test
	void testInsertData() {
		dbm.InsertData("tareas");
	}

}
