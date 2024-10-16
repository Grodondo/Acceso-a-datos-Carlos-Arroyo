package manejo_ficheros;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Sol_3_1_2 {
	public static void main(String[] args) {
		
		File fichero = new File("C:\\dev\\Trash\\fichero1.txt");
		File f2 = new File("C:\\Users\\drago\\Documents\\github-recovery-codes.txt");
		
		//escribeFichero(fichero, "Hola mundo");
		leeFicheroB(f2);
		leeFicheroF(f2);
		
	}
	
	static public void leeFicheroF(File fichero) {
		try {
			FileReader fr = new FileReader(fichero);
			long tiempoInicio = System.currentTimeMillis();
			while (fr.read() != -1) {
				System.out.print((char) fr.read());
			}
			System.out.println("Tiempo de lectura fr: " + (System.currentTimeMillis() - tiempoInicio));
		
			fr.close();
		}catch (FileNotFoundException fn) {
            System.out.println("No se encuentra el fichero");
        } catch (IOException io) {
            System.out.println("Error de E/S ");
        }
	}
	
	static public void leeFicheroB(File fichero) {
		try {
			BufferedReader br = new BufferedReader(new FileReader(fichero));
			 
			long tiempoInicio = System.currentTimeMillis();
			
			while (br.readLine() != null) {
				System.out.println(br.readLine());
			}
			System.out.println("Tiempo de lectura br: " + (System.currentTimeMillis() - tiempoInicio));

			
			br.close();
		} catch (FileNotFoundException fn) {
			System.out.println("No se encuentra el fichero");
		} catch (IOException io) {
			System.out.println("Error de E/S ");
		}
	}
	
//	static public void escribeFichero(File fichero, String cadena) {
//		try {
//			BufferedWriter bufW = new BufferedWriter(new FileWriter(fichero));
//			
//			bufW.write(cadena); 
//			bufW.newLine(); 
//			
//			bufW.close();
//		} catch (FileNotFoundException fn) {
//			System.out.println("No se encuentra el fichero");
//		} catch (IOException io) {
//			System.out.println("Error de E/S ");
//		}
//	}
	
	
	
}
