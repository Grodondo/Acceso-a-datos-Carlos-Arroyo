package BBDD_orientado_objetos.sol_3_1_1;

public class Tarea {
	
    private int id;
    private String titulo;
    private String descripcion;
    private TipoTarea tipoTarea;

    public Tarea() {}

    public Tarea(int id, String titulo, String descripcion, TipoTarea tipoTarea) {
        this.id = id;
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.tipoTarea = tipoTarea;
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

    @Override
    public String toString() {
        return "Tarea{id=" + id + ", titulo='" + titulo + "', descripcion='" + descripcion + "', tipoTarea=" + tipoTarea + "}";
    }
}

