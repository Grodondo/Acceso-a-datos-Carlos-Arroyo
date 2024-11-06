CREATE TABLE IF NOT EXISTS tareas (
    id INT PRIMARY KEY,
    descripcion VARCHAR(255),
    fecha_inicio DATE,
    fecha_final DATE,
    finalizada BOOLEAN
);