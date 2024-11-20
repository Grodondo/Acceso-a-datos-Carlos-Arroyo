package manejo_conectores.sol_8_1;

import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.sql.SQLException;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


class AccesoErroresTest {

	//@Test
	void causeError1042() throws SQLException {
		AccesoErrores ae = new AccesoErrores();
		
		Assertions.assertTrue(ae.causeError1042() == 1042);
//		AccesoErrores ae = new AccesoErrores();
//		System.out.println(ae.causeError1042());
	}

	@Test
	void causeError1045() throws SQLException {
		AccesoErrores ae = new AccesoErrores();
		
		Assertions.assertTrue(ae.causeError1045() == 1045);
//		AccesoErrores ae = new AccesoErrores();
//		System.out.println(ae.causeError1042());
	}
}
