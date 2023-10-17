package test;

import static org.junit.Assert.assertEquals;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.time.LocalDateTime;

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
	public void testInspectEmptyClass() throws IllegalArgumentException, IllegalAccessException {
		String expectedOutput = 
				EmptyClass.class.getName() + "\r\n" +	// Class name
				Object.class.getName() + "\r\n" +		// Superclass name
				" (test.InspectTest)\r\n"+ // Default constructor
				"final " + this.getClass().getName() + " = " + this.toString() + "\r\n"; // default "this" field
		
		inspector.inspect(new EmptyClass(), false);
		
		String content	= outBytes.toString();
		assertEquals(expectedOutput, content);
	}
	
	@Test
	public void testInspectMethods() throws IllegalArgumentException, IllegalAccessException {
		String expectedOutput = 
				Parent.class.getName() + "\r\n" + // Class name
				Object.class.getName() + "\r\n" + // Superclass name
				// Declared methods
				"public " + String.class.getName() + " getName()\r\n" + 
				"public void setName(" + String.class.getName() + ") throws " + NullPointerException.class.getName() + "\r\n" +
				" (test.InspectTest)\r\n" + // Default constructor
				"final " + this.getClass().getName() + " = " + this.toString() + "\r\n"; // default "this" field
		
		inspector.inspect(new Parent(), false);
		
		String content = outBytes.toString();
		assertEquals(expectedOutput, content);
	}
	
	@Test
	public void testInspectConstructors() throws IllegalArgumentException, IllegalAccessException, NullPointerException {
		String expectedOutput = 
				ParentCtors.class.getName() + "\r\n" +
				Object.class.getName() + "\r\n" + 
				"public (" + this.getClass().getName() + ", " + String.class.getName() + ") throws " + NullPointerException.class.getName() + "\r\n" + 
				"protected (" + this.getClass().getName() + ")\r\n" +
				"private (" + this.getClass().getName() + ", "+ String.class.getName() + ", " + int.class.getName() + ")\r\n" +
				"final " + this.getClass().getName() + " = " + this.toString() + "\r\n"; // default "this" field
		
		inspector.inspect(new ParentCtors("amy"), false);
		
		String content = outBytes.toString();
		assertEquals(expectedOutput, content);
	}
	
	@Test
	public void testInspectFields() throws IllegalArgumentException, IllegalAccessException {
		String expectedOutput = 
				ClassWithFields.class.getName() + "\r\n" + // Class
				Object.class.getName() + "\r\n" + // Superclass
				" (test.InspectTest)\r\n" +	 // Default constructor
				"public " + String.class.getName() + " = public\r\n" +
				"private " + String.class.getName() + " = John\r\n" + 
				" " + int.class.getName() + " = 45" + "\r\n" + 
				" " + LocalDateTime.class.getName() + " = null\r\n" + 
				"final " + this.getClass().getName() + " = " + this.toString() + "\r\n"; // default "this" field
		
		inspector.inspect(new ClassWithFields(), false);
		
		String content = outBytes.toString();
		assertEquals(expectedOutput, content);
	}
	
	class EmptyClass {}

	class Parent {		
		public String getName()
		{
			return "name";
		}
		
		public void setName(String newName) throws NullPointerException
		{
			if (newName == null)
			{
				throw new NullPointerException();
			}
			// Method implementation ...
		}
	}
	
	class ParentCtors {
		public ParentCtors(String name) throws NullPointerException
		{
			if (name == null)
			{
				throw new NullPointerException();
			}
			// ctor implementation here
		}
		
		protected ParentCtors()
		{
			// ctor implementation here
		}
		
		@SuppressWarnings("unused")
		private ParentCtors(String name, int age)
		{
			// ctor implementation here
		}
	}
	
	class ClassWithFields
	{
		public String publicString = "public";
		@SuppressWarnings("unused")
		private String name = "John";
		int age = 45;
		LocalDateTime dob;
	}

}
