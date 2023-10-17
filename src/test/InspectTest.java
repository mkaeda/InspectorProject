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
	public void testInspectEmptyClass() {
		String expectedOutput = 
				EmptyClass.class.getName() + "\r\n" +
				Object.class.getName() + "\r\n";
		
		inspector.inspect(new EmptyClass(), false);
		
		String content	= outBytes.toString();
		assertEquals(expectedOutput, content);
	}
	
	@Test
	public void testInspectMethods() {
		String expectedOutput = 
				Parent.class.getName() + "\r\n" +
				Object.class.getName() + "\r\n" + 
				"public " + String.class.getName() + " getName()\r\n" + 
				"public void setName(" + String.class.getName() + ") throws " + NullPointerException.class.getName() + "\r\n";
		
		inspector.inspect(new Parent(), false);
		
		String content = outBytes.toString();
		assertEquals(expectedOutput, content);
	}
	
	class EmptyClass {}

	class Parent {
		
		String name;
		
		public String getName()
		{
			return name;
		}
		
		public void setName(String newName) throws NullPointerException
		{
			if (newName == null)
			{
				throw new NullPointerException();
			}
			name = newName;
		}
	}

}
