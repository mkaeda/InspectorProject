package test;

import static org.junit.Assert.assertEquals;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import main.Inspector;

public class InspectWithInterfaces
{
	Inspector inspector = new Inspector();
	ByteArrayOutputStream outBytes = new ByteArrayOutputStream();
	PrintStream sysOut = System.out;

	@Before
	public void setUp() throws Exception {
		System.setOut(new PrintStream(outBytes));
	}

	@After
	public void tearDown() throws Exception {
		System.setOut(sysOut);
	}

	@Test
	public void testInspectHasEmptyInterfaces() throws IllegalArgumentException, IllegalAccessException {
		String expectedOutput = 
				Parent.class.getName() + "\r\n" + // Class
				Object.class.getName() + "\r\n"	+ // Superclass
				Interface2.class.getName() + "\r\n" + // Interface 1
				Interface1.class.getName() + "\r\n" + // Interface 2
				" (test.InspectWithInterfaces)\r\n" + // Default constructor
				"final " + this.getClass().getName() + " = " + this.toString() + "\r\n"; // default "this" field

		inspector.inspect(new Parent(), false);

		String content = outBytes.toString();
		assertEquals(expectedOutput, content);
	}

	interface Interface1 {
	}

	interface Interface2 {
	}

	class Parent implements Interface2, Interface1 {
	}

}
