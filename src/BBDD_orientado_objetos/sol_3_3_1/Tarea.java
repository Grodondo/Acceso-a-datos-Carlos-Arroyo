package BBDD_orientado_objetos.sol_3_3_1;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Tarea {
	
    private int id;
    private String titulo;
    private String descripcion;
    private Date fechaInicio;
    private Date fechaFinal;
    private TipoTarea tipoTarea;
    private boolean finalizada;

    public Tarea() {}

    public Tarea(int id, String titulo, String descripcion, TipoTarea tipoTarea, String fechaFinal) {
        this.id = id;
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.tipoTarea = tipoTarea;
        this.finalizada = false;
        this.fechaInicio = new Date();
        setFechaFinal(fechaFinal);
    }

    // Getters y setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public TipoTarea getTipoTarea() {
        return tipoTarea;
    }

    public void setTipoTarea(TipoTarea tipoTarea) {
        this.tipoTarea = tipoTarea;
    }
    
	public boolean getFinalizada() {
		return this.finalizada;
	}
	
	public void setFinalizada(boolean finalizada) {
		this.finalizada = finalizada;
	}
	
    public Date getFechaInicio() {
		return fechaInicio;
	}

	public Date getFechaFinal() {
		return fechaFinal;
	}

	public void setFechaFinal(String fechaFinal) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		dateFormat.setLenient(false); // Ensure strict parsing
		
		// Parse the input string into a Date object
		Date parsedDate = null;
		try {
			parsedDate = dateFormat.parse(fechaFinal);
		} catch (ParseException e) {
			System.out.println("Invalid date format. Please use the format 'yyyy-MM-dd'");
			e.printStackTrace();
		}
		this.fechaFinal = parsedDate;
	}

	@Override
    public String toString() {
        return "Tarea{id=" + id + ", titulo='" + titulo + "', descripcion='" + descripcion + "', tipoTarea=" + tipoTarea + "}";
    }
}

