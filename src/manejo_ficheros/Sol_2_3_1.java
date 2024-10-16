package manejo_ficheros;

import java.io.File;
import java.io.IOException;

/**
 * Esta clase contiene metodos para trabajar con ficheros y directorios
 * 
 * @version 1.0, 03/10/2024
 * @author Carlos Arroyo
 */
public class Sol_2_3_1 {

	/**
	 * Constructor por defecto
	 */
	Sol_2_3_1() {
	
	}
	
	public static void main(String[] args) throws FileNoReference, DirNoReference {
		
		File file = new File("C:/dev/Trash/fichero1.txt");
		File dir = new File("C:/dev/Trash");

		crearFicheroTxt2(dir, "fichero1");
		//cambiarNombreFichero(file, "C:/dev/Trash", "fichero2.txt");
		
	}
	
	/**
	 * Este metodo crea un directorio 
	 * @param nombre Nombre del nuevo fichero
	 */
	
	public static void crearDir(String nombre) {
		File dir = new File(nombre);
		dir.mkdir();
	}

	/**
	 * Este metodo crea un fichero txt en el directorio especificado
	 * @param dir    Directorio donde se creará el fichero
	 * @param nombre Nombre del nuevo fichero
	 * @return Devuelve el fichero creado o null si este no se crea.
	 */
	public static File crearFicheroTxt(String dir, String nombre) {
		File result = null;
        File file = new File(dir + "//" + nombre + ".txt");
        try {
        	if(file.exists()) {
        		System.out.println("El fichero ya existe");
        	}else {
	            file.createNewFile();
	            result = file;
        	}
        } catch (Exception e) {
        	System.out.println("Error al crear el fichero");
        }
        
        return result;
    }
	
	
	/**
	 * Este metodo crea un fichero txt en el directorio especificado
	 * @param dir Directorio donde se creará el fichero
	 * @param nombre Nombre del nuevo fichero
	 * @throws DirNoReference Cuando el directorio no sea un directorio o no exista.
	 * @return Devuelve el fichero creado o null si este no se crea.
	 */
	public static File crearFicheroTxt2(File dir, String nombre) throws DirNoReference{
		
		if(!dir.isDirectory()) {
			throw new DirNoReference("El fichero " + dir.getAbsolutePath() + " no existe o no es un fichero");
		}
		
		File result = null;
        File file = new File(dir.getAbsolutePath() + "//" + nombre + ".txt");
        try {
        	if(file.exists()) {
        		System.out.println("El fichero ya existe");
        	}else {
                file.createNewFile();
                result = file;
        	}
        } catch (Exception e) {
            System.out.println("Error al crear el fichero");
        }
        
        return result;
	}
	
	
	/**
	 * Este metodo renombra un fichero y lo mueve a un nuevo directorio
	 * @param fichero Fichero a renombrar
	 * @param newDir  Directorio donde se moverá el fichero
	 * @param newName Nuevo nombre del fichero
	 * @throws FileNoReference Cuando el fichero no sea un fichero o no exista.
	 */
	public static void cambiarNombreFichero(File fichero, String newDir, String newName) throws FileNoReference {
		
		if(!fichero.isFile()) {
			throw new FileNoReference("El fichero " + fichero.getAbsolutePath() + " no existe o no es un fichero");
		}
		
        File newFile = new File(newDir + "/" + newName + (fichero.getAbsolutePath().endsWith("txt") ? "" : ".txt"));
        boolean isRenamed = fichero.renameTo(newFile);
        
        if(isRenamed) {
            System.out.println("Fichero renombrado a " + newFile.getAbsolutePath());
        } else {
            System.out.println("Error al renombrar el fichero");
        }
    }
			
	
}

/**
 * Esta clase sirve para generar una excepcion cuando no exista un archivo
 * @author drago
 * @version v1.0
 * @exception FileNoReference
 * @see IOException
 */
class FileNoReference extends IOException{

	private static final long serialVersionUID = 1L;
	
	public FileNoReference(String message) {
		super(message);
	}
	
}


class DirNoReference extends IOException {

	private static final long serialVersionUID = 1L;

	public DirNoReference(String message){
		super(message);
    }
	
}
