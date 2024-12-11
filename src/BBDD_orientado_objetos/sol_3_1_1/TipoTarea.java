package BBDD_orientado_objetos.sol_3_1_1;

public class TipoTarea {
	private int id;
	private String abreviatura;
	private String descripcion;
	
	public TipoTarea(int id, String abreviatura, String descripcion) {
		this.id = id;
		this.abreviatura = abreviatura;
		this.descripcion = descripcion;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getAbreviatura() {
		return abreviatura;
	}

	public void setAbreviatura(String abreviatura) {
		this.abreviatura = abreviatura;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	
	  @Override
	    public String toString() {
	        return "TipoTarea{id=" + id + ", abreviatura='" + abreviatura + "', descripcion='" + descripcion + "'}";
	    }
}
