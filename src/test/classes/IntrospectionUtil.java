package test.classes;

public class IntrospectionUtil
{
	public static String getBikerIntrospection()
	{
		return "test.classes.Biker\r\n"
				+ "{\r\nSuperClass >> "
				+ getObjectIntrospection()
				+ "Methods >> {\r\n"
				+ "}\r\n"
				+ "Constructors >> {\r\n"
				+ "public test.classes.Biker(test.classes.Motorcycle)\r\n"
				+ "}\r\n"
				+ "Fields >> {\r\n"
				+ "private test.classes.Motorcycle test.classes.Biker.bike = {"
				+ getMotorcycleIntrospection()
				+ "}\r\n"
				+ "}\r\n"
				+ "}\r\n";			
	}
	
	public static String getMotorcycleIntrospection()
	{
		return "test.classes.Motorcycle\r\n"
				+ "{\r\n"
				+ "SuperClass >> "
				+ getVehicleIntrospection(2)
				+ "Methods >> {\r\n"
				+ "}\r\n"
				+ "Constructors >> {\r\n"
				+ "public test.classes.Motorcycle()\r\n"
				+ "}\r\n"
				+ "Fields >> {\r\n"
				+ "}\r\n"
				+ "}\r\n";
	}
	
	public static String getObjectIntrospection()
	{
		return "java.lang.Object\r\n"
				+ "{\r\n"
				+ "Methods >> {\r\n"
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
				+ "}\r\n"
				+ "Constructors >> {\r\n"
				+ "public java.lang.Object()\r\n"
				+ "}\r\n"
				+ "Fields >> {\r\n"
				+ "}\r\n"
				+ "}\r\n";
	}
	
	public static String getVehicleIntrospection(int numberOfWheels)
	{
		return "test.classes.Vehicle\r\n"
				+ "{\r\n"
				+ "SuperClass >> "
				+ getObjectIntrospection()
				+ "Methods >> {\r\n"
				+ "public int test.classes.Vehicle.getNumberOfWheels()\r\n"
				+ "}\r\n"
				+ "Constructors >> {\r\n"
				+ "public test.classes.Vehicle(int)\r\n"
				+ "}\r\n"
				+ "Fields >> {\r\n"
				+ "private int test.classes.Vehicle.numberOfWheels = " + numberOfWheels + "\r\n"
				+ "}\r\n"
				+ "}\r\n";
	}
	
	public static String getCarIntrospection()
	{
		return "test.classes.Car\r\n"
				+ "{\r\n"
				+ "SuperClass >> "
				+ getVehicleIntrospection(4)
				+ "Methods >> {\r\n"
				+ "}\r\n"
				+ "Constructors >> {\r\n"
				+ "public test.classes.Car()\r\n"
				+ "}\r\n"
				+ "Fields >> {\r\n"
				+ "}\r\n"
				+ "}\r\n";
	}
	
	
	public static String getTruckIntrospection()
	{
		return "test.classes.Truck\r\n"
				+ "{\r\nSuperClass >> "
				+ getCarIntrospection()
				+ "Methods >> {\r\n"
				+ "}\r\n"
				+ "Constructors >> {\r\n"
				+ "public test.classes.Truck()\r\n"
				+ "}\r\n"
				+ "Fields >> {\r\n"
				+ "}\r\n"
				+ "}\r\n";
	}
	
	public static String getCanTowIntrospection()
	{
		return "interface test.classes.CanTowCar {\r\n"
				+ "test.classes.CanTowCar\r\n"
				+ "{\r\n"
				+ "Methods >> {\r\n"
				+ "public abstract void test.classes.CanTowCar.remove(test.classes.Vehicle)\r\n"
				+ "public abstract void test.classes.CanTowCar.tow(test.classes.Vehicle)\r\n"
				+ "}\r\n"
				+ "Constructors >> {\r\n"
				+ "}\r\n"
				+ "Fields >> {\r\n"
				+ "}\r\n"
				+ "}\r\n"
				+ "}\r\n";
	}
	
	public static String getTowTruckIntrospection()
	{
		return "test.classes.TowTruck\r\n"
				+ "{\r\n"
				+ "SuperClass >> "
				+ getTruckIntrospection()
				+ "Interface >> "
				+ getCanTowIntrospection()
				+ "Methods >> {\r\n"
				+ "public void test.classes.TowTruck.remove(test.classes.Vehicle)\r\n"
				+ "public void test.classes.TowTruck.tow(test.classes.Vehicle)\r\n"
				+ "private void test.classes.TowTruck.unchainVehicle()\r\n"
				+ "}\r\n"
				+ "Constructors >> {\r\n"
				+ "public test.classes.TowTruck(double)\r\n"
				+ "}\r\n"
				+ "Fields >> {\r\n"
				+ "private double test.classes.TowTruck.maxLoad = 100.0\r\n"
				+ "private java.util.List test.classes.TowTruck.vehiclesclass java.lang.Object[0] = {\r\n"
				+ "}\r\n"
				+ "}\r\n"
				+ "}\r\n";	
	}
	
	public static String getTowTruckIntrospection2()
	{
		return "test.classes.TowTruck\r\n"
				+ "{\r\n"
				+ "SuperClass >> "
				+ getTruckIntrospection()
				+ "Interface >> "
				+ getCanTowIntrospection()
				+ "Methods >> {\r\n"
				+ "public void test.classes.TowTruck.remove(test.classes.Vehicle)\r\n"
				+ "public void test.classes.TowTruck.tow(test.classes.Vehicle)\r\n"
				+ "private void test.classes.TowTruck.unchainVehicle()\r\n"
				+ "}\r\n"
				+ "Constructors >> {\r\n"
				+ "public test.classes.TowTruck(double)\r\n"
				+ "}\r\n"
				+ "Fields >> {\r\n"
				+ "private double test.classes.TowTruck.maxLoad = 100.0\r\n"
				+ "private java.util.List test.classes.TowTruck.vehiclesclass java.lang.Object[2] = {\r\n"
				+ getCarIntrospection()
				+ getMotorcycleIntrospection()
				+ "}\r\n"
				+ "}\r\n"
				+ "}\r\n";
	}
}
