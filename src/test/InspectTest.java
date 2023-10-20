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
		String expectedOutput = "test.InspectTest$EmptyClass\r\n"
				+ "{\r\n"
				+ "SuperClass >> Methods >> {\r\n"
				+ "}\r\n"
				+ "Constructors >> {\r\n"
				+ "test.InspectTest$EmptyClass(test.InspectTest)\r\n"
				+ "}\r\n"
				+ "Fields >> {\r\n"
				+ "final test.InspectTest test.InspectTest$EmptyClass.this$0 = test.InspectTest@eafc191\r\n"
				+ "}\r\n"
				+ "}\r\n";
		
		inspector.inspect(new EmptyClass(), false);
		
		String content	= outBytes.toString();
		assertEquals(expectedOutput, content);
	}
	
	@Test
	public void testInspectMethods() throws IllegalArgumentException, IllegalAccessException {
		String expectedOutput = "test.InspectTest$Parent\r\n"
				+ "{\r\n"
				+ "SuperClass >> Methods >> {\r\n"
				+ "public java.lang.String test.InspectTest$Parent.getName()\r\n"
				+ "public void test.InspectTest$Parent.setName(java.lang.String) throws java.lang.NullPointerException\r\n"
				+ "}\r\n"
				+ "Constructors >> {\r\n"
				+ "test.InspectTest$Parent(test.InspectTest)\r\n"
				+ "}\r\n"
				+ "Fields >> {\r\n"
				+ "final test.InspectTest test.InspectTest$Parent.this$0 = test.InspectTest@52feb982\r\n"
				+ "}\r\n"
				+ "}\r\n";
		
		inspector.inspect(new Parent(), false);
		
		String content = outBytes.toString();
		assertEquals(expectedOutput, content);
	}
	
	@Test
	public void testInspectConstructors() throws IllegalArgumentException, IllegalAccessException, NullPointerException {
		String expectedOutput = "test.InspectTest$ParentCtors\r\n"
				+ "{\r\n"
				+ "SuperClass >> Methods >> {\r\n"
				+ "}\r\n"
				+ "Constructors >> {\r\n"
				+ "public test.InspectTest$ParentCtors(test.InspectTest,java.lang.String) throws java.lang.NullPointerException\r\n"
				+ "protected test.InspectTest$ParentCtors(test.InspectTest)\r\n"
				+ "private test.InspectTest$ParentCtors(test.InspectTest,java.lang.String,int)\r\n"
				+ "}\r\n"
				+ "Fields >> {\r\n"
				+ "final test.InspectTest test.InspectTest$ParentCtors.this$0 = test.InspectTest@4493d195\r\n"
				+ "}\r\n"
				+ "}\r\n";
		
		inspector.inspect(new ParentCtors("amy"), false);
		
		String content = outBytes.toString();
		assertEquals(expectedOutput, content);
	}
	
	@Test
	public void testInspectFields() throws IllegalArgumentException, IllegalAccessException {
		String expectedOutput =  "test.InspectTest$ClassWithFields\r\n"
				+ "{\r\n"
				+ "SuperClass >> Methods >> {\r\n"
				+ "}\r\n"
				+ "Constructors >> {\r\n"
				+ "test.InspectTest$ClassWithFields(test.InspectTest)\r\n"
				+ "}\r\n"
				+ "Fields >> {\r\n"
				+ "public java.lang.String test.InspectTest$ClassWithFields.publicString = public\r\n"
				+ "private java.lang.String test.InspectTest$ClassWithFields.name = John\r\n"
				+ "int test.InspectTest$ClassWithFields.age = 45\r\n"
				+ "java.time.LocalDateTime test.InspectTest$ClassWithFields.dob = null\r\n"
				+ "final test.InspectTest test.InspectTest$ClassWithFields.this$0 = test.InspectTest@76b0bfab\r\n"
				+ "}\r\n"
				+ "}\r\n";
		
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
