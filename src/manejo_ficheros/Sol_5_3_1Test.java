package manejo_ficheros;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class Sol_5_3_1Test {

	//@Test
	void crearXml() {
		Sol_5_3_1 elf = new Sol_5_3_1();
		elf.crearXml();
	}
	
	@Test
	void aumentarAnioMatriculacion() {
		Sol_5_3_1 elf = new Sol_5_3_1();
		elf.aumentarAnioMatriculacion();
	}

}
