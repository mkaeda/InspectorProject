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
	PrintStream				sysOut 		= System.out;
	ByteArrayOutputStream 	outBytes;

	@Before
	public void setUp() throws Exception
	{
		outBytes 	= new ByteArrayOutputStream();
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
				EmptyClass.class.getName() + "\r\n" +	// Class name
				Object.class.getName() + "\r\n" +		// Superclass name
				" (test.InspectTest)\r\n";				// Default constructor
		
		inspector.inspect(new EmptyClass(), false);
		
		String content	= outBytes.toString();
		assertEquals(expectedOutput, content);
	}
	
	@Test
	public void testInspectMethods() {
		String expectedOutput = 
				Parent.class.getName() + "\r\n" + // Class name
				Object.class.getName() + "\r\n" + // Superclass name
				// Declared methods
				"public " + String.class.getName() + " getName()\r\n" + 
				"public void setName(" + String.class.getName() + ") throws " + NullPointerException.class.getName() + "\r\n" +
				" (test.InspectTest)\r\n"; // Default constructor
		
		inspector.inspect(new Parent(), false);
		
		String content = outBytes.toString();
		assertEquals(expectedOutput, content);
	}
	
	@Test
	public void testInspectConstructors() {
		String expectedOutput = 
				ParentCtors.class.getName() + "\r\n" +
				Object.class.getName() + "\r\n" + 
				"public (" + this.getClass().getName() + ", " + String.class.getName() + ") throws " + NullPointerException.class.getName() + "\r\n" + 
				"protected (" + this.getClass().getName() + ")\r\n" +
				"private (" + this.getClass().getName() + ", "+ String.class.getName() + ", " + int.class.getName() + ")\r\n";
		
		inspector.inspect(new ParentCtors("amy"), false);
		
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
	
	class ParentCtors {
		String name;

		public ParentCtors(String name) throws NullPointerException
		{
			if (name == null)
			{
				throw new NullPointerException();
			}
			this.name = name;
		}
		
		protected ParentCtors()
		{
			// method implementation here
		}
		
		@SuppressWarnings("unused")
		private ParentCtors(String name, int age)
		{
			// method implementation here
		}
	}

}
