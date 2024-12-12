package BBDD_orientado_objetos.sol_3_3_1;

import java.util.Date;

import org.neodatis.odb.ODB;
import org.neodatis.odb.ODBFactory;
import org.neodatis.odb.Objects;
import org.neodatis.odb.core.query.IQuery;
import org.neodatis.odb.core.query.criteria.ICriterion;
import org.neodatis.odb.core.query.criteria.Where;
import org.neodatis.odb.impl.core.query.criteria.CriteriaQuery;


public class ObjectDatabaseManager {
	
	final String dbName = "tareas.db";
	
	public void mostrarTareaPorFecha(Date fechaInicio, Date fechaFin, boolean incluir) {
		ODB odb = null;
		try {
			odb = ODBFactory.open(dbName);
			ICriterion crit = null;
			if (incluir)
				crit = Where.and().add(Where.ge("fechaInicio", fechaInicio)).add(Where.le("fechaFinal", fechaFin));
			else
				crit = Where.and().add(Where.gt("fechaInicio", fechaInicio)).add(Where.lt("fechaFinal", fechaFin));
			
			IQuery query = new CriteriaQuery(Tarea.class, crit);
			
			Objects<Tarea> tareas = odb.getObjects(query);
			
			if (tareas.size() == 0) {
                System.out.println("No hay tareas entre las fechas: " + fechaInicio + " y " + fechaFin);
                return;
			}
			
			while (tareas.hasNext()) {
				Tarea tarea = (Tarea) tareas.next();
				System.out.println("	" + tarea);
			}
			
		}catch (Exception e) {
            System.out.println("Error al mostrar tareas por fecha: " + e.getMessage());
		}
		finally {
			if (odb != null) {
				odb.close();
			}
		}
	}
	
	public void modificarTarea(int id) {
		ODB odb = null; 
		try {
			odb = ODBFactory.open(dbName);
			
			ICriterion crit = Where.equal("id", id);
			IQuery query = new CriteriaQuery(Tarea.class, crit);
			
			Objects<Tarea> tareas = odb.getObjects(query);
			
			if (tareas.size() == 0) {
	            System.out.println("No hay tareas con ID: " + id);
	            return;
			}
			
			while(tareas.hasNext()) {
                Tarea tarea = (Tarea) tareas.next();
                tarea.setFinalizada(!tarea.getFinalizada());
                odb.store(tarea);
                odb.commit();
                System.out.println("Tarea con ID " + id + " actualizada correctamente.");
			}
			
		}
		catch (Exception e) {
		System.out.println("Error al modificar tarea con id: " + id + "\n" + e.getMessage() + "\n");
		} 
		finally {
			if (odb != null) {
				odb.close();
			}
		}
	}
	
	public void mostrarTareaDes(String des) {
		ODB odb = null; 
		try {
			odb = ODBFactory.open(dbName);
			ICriterion crit = Where.equal("descripcion", des);
			IQuery query = new CriteriaQuery(Tarea.class, crit);
			
			Objects<Tarea> tareas = odb.getObjects(query);
			
			if (tareas.size() == 0) {
				System.out.println("No hay tareas con descripcion: " + des);
			}else {
				while(tareas.hasNext()) {
					Tarea tarea = (Tarea) tareas.next();
					System.out.println("	" + tarea);
				}
				
			}
		} catch (Exception e) {
			System.out.println("Error al mostrar tareas con descripcion: " + des + "\n" + e.getMessage() + "\n");
		} finally {
			if (odb != null) {
				odb.close();
			}
		}
	}
	
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
