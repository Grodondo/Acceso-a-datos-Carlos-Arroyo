package manejo_ficheros;

import org.junit.jupiter.api.Test;

class Sol_4_2_1Test {

	Sol_4_2_1 elf = new Sol_4_2_1();
	
	//@Test
	void EscribeFicheroAleatorio() {
		elf.EscribeFicheroAleatorio(new int[] {1, 2, 3}, new String[] {"Ana", "Juan", "Luis"}, new short[] {34, 20, 25});
	}
	
	@Test
	void LeeFicheroAleatorio() {
		elf.LeeFicheroAleatorio();
	}
	
	@Test
	void ModificaFichero() {
		elf.ModificarFichero(2, (short) 220);
	}

}
