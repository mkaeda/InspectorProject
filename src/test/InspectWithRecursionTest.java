package test;

import static org.junit.Assert.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import main.Inspector;
import test.classes.Biker;
import test.classes.Car;
import test.classes.IntrospectionUtil;
import test.classes.Motorcycle;
import test.classes.TowTruck;

public class InspectWithRecursionTest
{

	Inspector 				inspector 	= new Inspector();
	PrintStream				sysOut 		= System.out;
	ByteArrayOutputStream 	outBytes;
	
	@Before
	public void setUp() throws Exception
	{
		outBytes 	= new ByteArrayOutputStream();
//		System.setOut(new PrintStream(outBytes));
	}

	@After
	public void tearDown() throws Exception
	{
		System.setOut(sysOut);
	}

	@Test
	public void testRecursiveCallWithAbstractSuperclass()
	{
		inspector.inspect(new Car(), true);
//		assertEquals(IntrospectionUtil.getCarIntrospection(), outBytes.toString());
	}
	
	@Test
	public void testRecursiveCallWithInterfaces()
	{
//		inspector.inspect(new TowTruck(100d), true);
//		assertEquals(IntrospectionUtil.getTowTruckIntrospection(), outBytes.toString());
	}
	
	@Test
	public void testRecursiveCallWithObjectField()
	{		
//		inspector.inspect(new Biker(new Motorcycle()), true);		
//		assertEquals(IntrospectionUtil.getBikerIntrospection(), outBytes.toString());
	}
	
	@Test
	public void testRecursiveCallWithArray() {
//		TowTruck tt = new TowTruck(100d);
//		tt.tow(new Car());
//		tt.tow(new Motorcycle());
//		inspector.inspect(tt, true);
//		assertEquals(IntrospectionUtil.getTowTruckIntrospection2(), outBytes.toString());
	}
}
