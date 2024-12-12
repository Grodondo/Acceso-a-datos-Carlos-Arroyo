package BBDD_orientado_objetos.sol_3_1_1;

import org.neodatis.odb.ODB;
import org.neodatis.odb.ODBFactory;
import org.neodatis.odb.Objects;

public class ObjectDatabaseManager {
	
	final String dbName = "tareas.db";
	
	public void insertarTarea(Tarea tarea) {
		ODB odb = null;
		try {
			odb = ODBFactory.open(dbName);
			odb.store(tarea);
			
		} catch (Exception e) {
			System.out.println("Fallo al insertar tarea: " + e.getMessage());
		}
		finally {
			if (odb != null) {
				odb.close();
			}
		}
	}
	
    public Tarea obtenerTareaPorId(int id) {
    	ODB odb = null;
        try {
        	odb = ODBFactory.open(dbName);
            Objects<Tarea> tareas = odb.getObjects(Tarea.class);
            for (Tarea tarea : tareas) {
                if (tarea.getId() == id) {
                    return tarea;
                }
            }
        }
        catch (Exception e) {
            System.out.println("Obtener tarea por id: " + e.getMessage());
        }
        finally {
			if (odb != null) {
				odb.close();
			}
        }
        return null;
    }
	
	public void mostrarTipoTareas() {
        ODB odb = ODBFactory.open(dbName);
        odb.getObjects(TipoTarea.class).forEach(tarea -> System.out.println(((TipoTarea) tarea)));
        odb.close();
    }
	
	public void mostrarTareas() {
		ODB odb = ODBFactory.open(dbName);
		odb.getObjects(Tarea.class).forEach(tarea -> System.out.println(((Tarea) tarea)));
		odb.close();
	}

}
