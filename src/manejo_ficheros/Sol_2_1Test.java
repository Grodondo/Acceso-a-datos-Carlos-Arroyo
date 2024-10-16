package manejo_ficheros;

import static org.junit.jupiter.api.Assertions.*;

import java.io.File;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

class Sol_2_1Test {

	Sol_2_1 fw = new Sol_2_1();
	Sol_2_2_1 fn = new Sol_2_2_1("io");
	String dir = "C:/X";
	
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
	}

	@Test
	void crearFicheros() {
		fw.crearFicheros();
	}
	
	@Test
	void eliminarFicheros() {
		fw.eliminarFicheros(new File(dir));
	}
	
	@Test
	void mostrarInfo(File f) {
		fw.mostrarInfo(new File(dir));
	}
	
	@Test
	void showSize(File f) {
		fw.showSize(f);
	}
	
	@Test
	void FileName() {
		fn.mostrarDirs(new File("C:\\dev\\p"));
	}
	

}
