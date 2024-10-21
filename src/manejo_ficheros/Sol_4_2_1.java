package manejo_ficheros;

import java.io.IOException;
import java.io.RandomAccessFile;

public class Sol_4_2_1 {

	public void main(String[] args) {
        int[] ids = {1, 2, 3};
        String[] nombres = {"Ana", "Juan", "Luis"};
        short[] edades = {34, 20, 25};
        EscribeFicheroAleatorio(ids, nombres, edades);
        
    	LeeFicheroAleatorio();
    }
	
	public void ModificaFcihero(int id, short edad) {
		try {
			RandomAccessFile raf = new RandomAccessFile("./prueba.dat", "rw");
			
			long nextpos = 0L + 4 + (15 * 2) + 2;
			for (int i = 0; i < raf.length(); i += nextpos) {
				raf.seek(i);
				int id2 = raf.readInt();
				if (id2 == id) {
					
					raf.skipBytes(30);
					raf.writeShort(edad);
					break;
				}
			}
			
			raf.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void EscribeFicheroAleatorio(int id[], String nombre[], short edad[]) {
		try {
			RandomAccessFile raf = new RandomAccessFile("./prueba.dat", "rw");
			for (int i = 0; i < id.length; i++) {
				this.EscribeRegistro(raf, id[i], nombre[i], edad[i]);
			}
			raf.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void EscribeRegistro(RandomAccessFile raf, int id, String nombre, short edad) throws IOException {
		raf.writeInt(id);
		StringBuffer sBuffer = new StringBuffer(nombre);
		sBuffer.setLength(15);
		raf.writeChars(sBuffer.toString());
		raf.writeShort(edad);
	}
	
	public void LeeFicheroAleatorio() {
		try {
			RandomAccessFile raf = new RandomAccessFile("./prueba.dat", "r");
			LeeRegistro(raf, 0);
			long nextpos = 0L + 4 + (15 * 2) + 2;
			for (int pos=0; pos<raf.length(); pos+=nextpos) {
				LeeRegistro(raf, pos);
			}
			raf.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void LeeRegistro(RandomAccessFile raf, long posicion) throws IOException {
		raf.seek(posicion);
		int id = raf.readInt();
		char[] aNombre = new char[15];
		for (int c = 0; c < 15; c++) {
			char ch = raf.readChar();
			aNombre[c] = ch;
		}
		String nombre = new String(aNombre).trim();
		short edad = raf.readShort();
		System.out.printf("Para el registro %d nombre = %s, edad = %d " + System.lineSeparator(), id, nombre, edad);
	}


}
