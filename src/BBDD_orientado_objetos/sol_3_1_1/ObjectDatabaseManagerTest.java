package BBDD_orientado_objetos.sol_3_1_1;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class ObjectDatabaseManagerTest {

	ObjectDatabaseManager odbm = new ObjectDatabaseManager();
	
	//@Test
	void insertarTarea() {
		Tarea tarea = new Tarea(1, "titulo", "descripcion", new TipoTarea(1, "abreviatura", "descripcion"));
		Tarea tarea2 = new Tarea(2, "titulo2", "descripcion2", new TipoTarea(2, "abreviatura2", "descripcion2"));
		Tarea tarea3 = new Tarea(3, "titulo3", "descripcion3", new TipoTarea(3, "abreviatura3", "descripcion3"));
		
		
        odbm.insertarTarea(tarea);
        odbm.insertarTarea(tarea2);
        odbm.insertarTarea(tarea3);
	}
	
	@Test
	void mostrarTarea() {
		Tarea tarea = odbm.obtenerTareaPorId(1);
		System.out.println(tarea);
	}
	
	@Test
	void mostrarTipoTareas() {
		odbm.mostrarTipoTareas();
	}

}
