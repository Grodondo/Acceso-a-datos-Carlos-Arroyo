package manejo_ficheros;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.Serializable;

public class Sol_3_2_1 {

	public static void main(String[] args) {
		
		
//		EscribirFichObject("1234ABC", "Seat", 1999);
//		EscribirFichObject("5678DEF", "Renault", 2005);
//		EscribirFichObject("91011GHI", "Ford", 2010);

		LeerFichObject();
	}

	public static void EscribirFichObject(String matricula, String marca, int anioMatriculacion) {
		coche c = new coche(matricula, marca, anioMatriculacion);
        File f = new File("./coche.obj");
        try {
        	boolean exists = f.exists();
            FileOutputStream fos = new FileOutputStream(f, true);
            ObjectOutputStream oos = exists ? new AppendableObjectOutputStream(fos) : new ObjectOutputStream(fos);
            oos.writeObject(c);
            oos.close();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    
	}
	
	static class AppendableObjectOutputStream extends ObjectOutputStream {
	    public AppendableObjectOutputStream(OutputStream out) throws IOException {
	        super(out);
	    }

	    @Override
	    protected void writeStreamHeader() throws IOException {
	        reset();
	    }
	}
	
	public static void LeerFichObject() {
		File f = new File("./coche.obj");
		try {
			
			FileInputStream fis = new FileInputStream(f);
			ObjectInputStream ois = new ObjectInputStream(fis);
			coche c = (coche) ois.readObject();
			
			while(c != null) {
				System.out.println(c.getMatricula() + " " + c.getMarca() + " " + c.getAnioMatriculacion());
				c = (coche) ois.readObject();
			}
			ois.close();
		} catch (IOException ioe) {
			//ioe.printStackTrace();
			System.out.println("Fin de fichero");
		} catch (ClassNotFoundException cnfe) {
			//System.out.println("File f no existe");
			cnfe.printStackTrace();
		}
	}
	

	public static class coche implements Serializable{
		private static final long serialVersionUID = 1L;
		
		private String matricula;
		private String marca;
		private int anioMatriculacion;
		
		public coche(String matricula, String marca, int anioMatriculacion) {
			this.setMatricula(matricula);
			this.setMarca(marca);
			this.setAnioMatriculacion(anioMatriculacion);
		}

		public String getMatricula() {
			return matricula;
		}

		public void setMatricula(String matricula) {
			this.matricula = matricula;
		}

		public String getMarca() {
			return marca;
		}

		public void setMarca(String marca) {
			this.marca = marca;
		}

		public int getAnioMatriculacion() {
			return anioMatriculacion;
		}

		public void setAnioMatriculacion(int anioMatriculacion) {
			this.anioMatriculacion = anioMatriculacion;
		}
		
		
	}
}

