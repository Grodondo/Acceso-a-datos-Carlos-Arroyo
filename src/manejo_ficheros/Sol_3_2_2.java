package manejo_ficheros;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.Serializable;

public class Sol_3_2_2 {

	public static void main(String[] args) {
		
		
		EscribirFichObject("1234ABC", "Seat", 1999);
		EscribirFichObject("5678DEF", "Renault", 2005);
		EscribirFichObject("91011GHI", "Ford", 2010);

		LeerFichObject();
	}

	public static void EscribirFichObject(String matricula, String marca, int anioMatriculacion) {
		coche c = new coche(matricula, marca, anioMatriculacion);
        File f = new File("./coche.dat");
        try {
        	boolean exists = f.exists();
            FileOutputStream fos = new FileOutputStream(f, true);
            DataOutputStream dos = new DataOutputStream(fos);
            String str = c.getMatricula() + " " + c.getMarca() + " " + c.getAnioMatriculacion();
            dos.writeUTF(str);

            dos.close();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    
	}
	

	
	public static void LeerFichObject() {
		File f = new File("./coche.dat");
		try {
			
			FileInputStream fis = new FileInputStream(f);
			DataInputStream dis = new DataInputStream(fis);
			
			while(dis.available() > 0) {
				System.out.println(dis.readUTF());
			}
				
			dis.close();
			
		} catch (IOException ioe) {
			ioe.printStackTrace();
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

