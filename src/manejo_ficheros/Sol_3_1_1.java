package manejo_ficheros;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Sol_3_1_1 {

	public Sol_3_1_1() {

	}
	
	public void escribeFichero(File fichero, String cadena, Boolean asterisco) {
        try {
			FileWriter fic = new FileWriter(fichero); 
			char[] cad = cadena.toCharArray();

			for (int i = 0; i < cad.length; i++)
				fic.write(cad[i]); 
			if (asterisco)
				fic.write('*');
			fic.close(); 
        } catch (FileNotFoundException nfe) {
            nfe.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
	
	static public void escribeNombresFichero(File fichero, String[] nombres) {
		try {
			BufferedWriter bufW = new BufferedWriter(new FileWriter(fichero));

			for (String nombre : nombres) {
				bufW.write(nombre);
				bufW.newLine();
			}

			bufW.close();
		} catch (FileNotFoundException fn) {
			System.out.println("No se encuentra el fichero");
		} catch (IOException io) {
			System.out.println("Error de E/S ");
		}
	}
	
	public void leeFichero() {
		try {
			FileReader fr = new FileReader("");
			int c = 0;
			do {
				c = fr.read();
				if (c != -1)
					System.out.println((char) c + " " + c);
			} while (c != -1);

			fr.close();
		} catch (FileNotFoundException nfe) {
			nfe.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
