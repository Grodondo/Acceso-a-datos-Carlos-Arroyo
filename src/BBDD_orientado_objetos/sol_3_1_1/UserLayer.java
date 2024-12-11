package BBDD_orientado_objetos.sol_3_1_1;

public class UserLayer {

	
	ObjectDatabaseManager odbm = new ObjectDatabaseManager();
	
	public void insertarTarea(Tarea tarea) {
		odbm.insertarTarea(tarea);
	}
	
	
}
