package test;

import static org.junit.Assert.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.Arrays;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import main.Inspector;
import test.classes.Biker;
import test.classes.Motorcycle;
import test.classes.Vehicle;

public class InspectWithRecursionTest
{

	Inspector 				inspector 	= new Inspector();
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
//		System.setOut(sysOut);
	}

	@Test
	public void testRecursiveCallWithSuperclass()
	{
		fail("Not yet implemented");
	}
	
	@Test
	public void testRecursiveCallWithInterfaces() {
		fail("Not yet implemented");
	}
	
	@Test
	public void testRecursiveCallWithObjectField()
	{
		StringBuilder sb = new StringBuilder();
		// class
		sb.append("test.classes.Biker\r\n");
		/// superclass
		sb.append("java.lang.Object\r\n");
		// TODO add traversal section for superclass.
		// interface(s)
		sb.append("");
		// TODO add traversal section for superclass.
		// method(s)
		Arrays.stream(Biker.class.getDeclaredMethods())
			.map(Method::toString)
			.forEach(m -> {
				sb.append(m.concat("\r\n"));
			});
		// constructor(s)
		Arrays.stream(Biker.class.getDeclaredConstructors())
			.map(Constructor::toString)
			.forEach(c -> {
				sb.append(c.concat("\r\n"));
		});
		// field(s)
		sb.append("private test.classes.Motorcycle test.classes.Biker.bike = test.classes.Motorcycle\r\n");		
		// >> Motorcycle - superclass
		sb.append("test.classes.Vehicle\r\n");
		// >> Motorcycle - interface(s)
		sb.append("");
		// >> Motorcycle - method(s)
		sb.append("");
		// >> Motorcycle - constructor(s)
		Arrays.stream(Motorcycle.class.getDeclaredConstructors())
			.map(Constructor::toString)
			.forEach(c -> {
				sb.append(c.concat("\r\n"));
		});
		// field(s)
		sb.append("");
		
		inspector.inspect(new Biker(new Motorcycle()), true);
		
		assertEquals(sb.toString(), outBytes.toString());
	}
	
	@Test
	public void testRecursiveCallWithArray() {
		fail("Not yet implemented");
	}
}
