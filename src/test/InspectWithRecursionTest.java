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
import test.classes.Motorcycle;

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
		System.setOut(sysOut);
	}

	@Test
	public void testRecursiveCallWithSuperclass()
	{
		inspector.inspect(new Car(), true);
		assertEquals(getCarIntrospection(), outBytes.toString());
	}
	
	@Test
	public void testRecursiveCallWithInterfaces() {
		fail("Not yet implemented");
	}
	
	@Test
	public void testRecursiveCallWithObjectField()
	{		
		inspector.inspect(new Biker(new Motorcycle()), true);
		
		assertEquals(getBikerIntrospection(), outBytes.toString());
	}
	
	@Test
	public void testRecursiveCallWithArray() {
		fail("Not yet implemented");
	}
	
	private String getBikerIntrospection()
	{
		return "test.classes.Biker\r\n"
				+ "java.lang.Object\r\n"
				+ getObjectIntrospection()
				+ "public test.classes.Biker(test.classes.Motorcycle)\r\n"
				+ "private test.classes.Motorcycle test.classes.Biker.bike = "
				+ getMotorcycleIntrospection();
				
							
	}
	
	private String getMotorcycleIntrospection()
	{
		return "test.classes.Motorcycle\r\n"
				+ getVehicleIntrospection(2)
				+ "public test.classes.Motorcycle()\r\n";
	}
	
	private String getObjectIntrospection()
	{
		return "java.lang.Object\r\n"
				+ "protected native java.lang.Object java.lang.Object.clone() throws java.lang.CloneNotSupportedException\r\n"
				+ "public boolean java.lang.Object.equals(java.lang.Object)\r\n"
				+ "protected void java.lang.Object.finalize() throws java.lang.Throwable\r\n"
				+ "public final native java.lang.Class java.lang.Object.getClass()\r\n"
				+ "public native int java.lang.Object.hashCode()\r\n"
				+ "public final native void java.lang.Object.notify()\r\n"
				+ "public final native void java.lang.Object.notifyAll()\r\n"
				+ "public java.lang.String java.lang.Object.toString()\r\n"
				+ "public final void java.lang.Object.wait(long) throws java.lang.InterruptedException\r\n"
				+ "public final void java.lang.Object.wait(long,int) throws java.lang.InterruptedException\r\n"
				+ "public final void java.lang.Object.wait() throws java.lang.InterruptedException\r\n"
				+ "private final native void java.lang.Object.wait0(long) throws java.lang.InterruptedException\r\n"
				+ "public java.lang.Object()\r\n";
	}
	
	private String getVehicleIntrospection(int numberOfWheels)
	{
		return "test.classes.Vehicle\r\n"
				+ "java.lang.Object\r\n"
				+ getObjectIntrospection()
				+ "public int test.classes.Vehicle.getNumberOfWheels()\r\n"
				+ "public test.classes.Vehicle(int)\r\n"
				+ "private int test.classes.Vehicle.numberOfWheels = " + numberOfWheels + "\r\n";
	}
	
	private String getCarIntrospection()
	{
		return "test.classes.Car\r\n"
				+ getVehicleIntrospection(4)
				+ "public test.classes.Car()\r\n";
	}
}
