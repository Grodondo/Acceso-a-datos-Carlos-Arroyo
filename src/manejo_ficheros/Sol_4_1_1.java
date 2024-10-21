package manejo_ficheros;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

public class Sol_4_1_1 extends Sol_3_2_1{

	public static void mostrarInfoCoche(String matricula) {
	
		 File f = new File("./coche.obj");
		 
		 try {
             FileInputStream fis = new FileInputStream(f);
             ObjectInputStream ois = new ObjectInputStream(fis);
             coche c = (coche) ois.readObject();
             
             while(c != null) {
                 if(c.getMatricula().equals(matricula)) {
                     System.out.println(c.getMatricula() + " " + c.getMarca() + " " + c.getAnioMatriculacion());
                     break;
                 }
                 c = (coche) ois.readObject();
             }
             
             ois.close();
             
         } catch (IOException ioe) {
             ioe.printStackTrace();
         } catch (ClassNotFoundException e) {
        	 e.printStackTrace();
         }
	
	}
}
