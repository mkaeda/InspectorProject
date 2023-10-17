package test;

import static org.junit.Assert.assertEquals;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import main.Inspector;

public class InspectTest
{
	Inspector 				inspector	= new Inspector();
	ByteArrayOutputStream 	outBytes 	= new ByteArrayOutputStream();
	PrintStream				sysOut 		= System.out;

	@Before
	public void setUp() throws Exception
	{
		System.setOut(new PrintStream(outBytes));
	}

	@After
	public void tearDown() throws Exception
	{
		System.setOut(sysOut);
	}

	@Test
	public void testInspectExtendsObject() {
		String expectedOutput = 
				Parent.class.getName() + "\r\n" +
				Object.class.getName() + "\r\n";
		
		inspector.inspect(new Parent(), false);
		
		String content	= outBytes.toString();
		assertEquals(expectedOutput, content);
	}

	class Parent {
	}
}
