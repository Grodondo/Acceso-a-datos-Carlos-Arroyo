package manejo_ficheros;

import java.io.File;
import java.io.FilenameFilter;
import java.util.ArrayList;

public class Sol_2_2_1 implements FilenameFilter{

	String cadena;
	
	public Sol_2_2_1(String cadena) {
		this.cadena = cadena;
	}
	
	@Override
	public boolean accept(File dir, String word) {
		return (dir.isDirectory() && dir.getName().contains(word));
	}
	
	
	public void mostrarDirs(File dir) {
		
		ArrayList<String> listaDir = new ArrayList<String>();
		
		if(dir.exists()) {
			String[] elements = dir.list(new Sol_2_2_1(this.cadena));
			
			for(String e : elements) {
				if (e.contains(this.cadena)) listaDir.add(e);
			}
			
			if(listaDir.size() == 0) System.out.println("No existe");
			else {
				for(String d : listaDir) {
					System.out.print(d + " ");
				}
			}
		}
		
	}

	
	
}
