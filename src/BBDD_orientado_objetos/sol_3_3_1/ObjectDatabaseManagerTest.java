package BBDD_orientado_objetos.sol_3_3_1;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.Test;

class ObjectDatabaseManagerTest {

	ObjectDatabaseManager odbm = new ObjectDatabaseManager();
	
	@Test
	void mostrarTareaDes() {
		String des = "descripcion";
		System.out.println("Mostrando tarea con descripcion: " + des);
		
		odbm.mostrarTareaDes(des);
	}
	
	//@Test
	void insertarTarea() {
		List<Tarea> tareas = new ArrayList<Tarea>();
		tareas.add(new Tarea(1, "titulo", "descripcion", new TipoTarea(1, "abreviatura", "descripcion"), "2004-01-01"));
		tareas.add(new Tarea(2, "titulo2", "descripcion2", new TipoTarea(2, "abreviatura2", "descripcion2"), "2019-07-01"));
		tareas.add(new Tarea(3, "titulo3", "descripcion3", new TipoTarea(3, "abreviatura3", "descripcion3"), "2020-07-24"));
		
		
		for (Tarea tarea : tareas) {
			odbm.insertarTarea(tarea);
		}
	}
	
	@Test
	void mostrarTarea() {
		Tarea tarea = odbm.obtenerTareaPorId(1);
		System.out.println(tarea);
	}
	
	//@Test
	void mostrarTipoTareas() {
		odbm.mostrarTipoTareas();
	}
	
	//@Test
	void mostrarTareas() {
		odbm.mostrarTareas();
	}

}
