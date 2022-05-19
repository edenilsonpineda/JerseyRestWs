package sv.edu.udb.rest.services.test;

import static org.junit.Assert.*;

import org.junit.Test;

import sv.edu.udb.rest.util.PatternUtils;

public class CheckEmailTest {

	@Test
	public void test() {
		assertTrue("Email correcto", PatternUtils.checkValidEmail("perez.perez@alumno.udb.edu.sv"));
		
		assertFalse("Fecha incorrecta", PatternUtils.checkValidDate("2022-1-1"));
		
		
		assertFalse("Fecha incorrecta, sobrepasa los meses", PatternUtils.checkValidDate("2022-13-13"));
		
		assertTrue("Fecha correcta", PatternUtils.checkValidDate("2022-05-18"));
		
		assertTrue("Fecha incorrecta, no debe contener caracteres en blanco", PatternUtils.checkValidDate(""));
	}

}
