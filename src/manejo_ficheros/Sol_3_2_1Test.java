package manejo_ficheros;

import org.junit.jupiter.api.Test;

class Sol_3_2_1Test {

	private Sol_3_2_1 elf = new Sol_3_2_1();
	
	@Test
	public void EscribirFichObject() {
        elf.EscribirFichObject("1234ABC", "Seat", 1999);
	}

	
	@Test
	public void LeerFichObject() {
		elf.LeerFichObject();
	}
}
