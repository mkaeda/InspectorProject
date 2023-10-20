package test.classes;

public class IntrospectionUtil
{

	public static String getBikerIntrospection()
	{
		return "test.classes.Biker\r\n"
				+ "java.lang.Object\r\n"
				+ getObjectIntrospection()
				+ "public test.classes.Biker(test.classes.Motorcycle)\r\n"
				+ "private test.classes.Motorcycle test.classes.Biker.bike = {"
				+ getMotorcycleIntrospection()
				+ "}\r\n";			
	}
	
	public static String getMotorcycleIntrospection()
	{
		return "test.classes.Motorcycle\r\n"
				+ getVehicleIntrospection(2)
				+ "public test.classes.Motorcycle()\r\n";
	}
	
	public static String getObjectIntrospection()
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
	
	public static String getVehicleIntrospection(int numberOfWheels)
	{
		return "test.classes.Vehicle\r\n"
				+ "java.lang.Object\r\n"
				+ getObjectIntrospection()
				+ "public int test.classes.Vehicle.getNumberOfWheels()\r\n"
				+ "public test.classes.Vehicle(int)\r\n"
				+ "private int test.classes.Vehicle.numberOfWheels = " + numberOfWheels + "\r\n";
	}
	
	public static String getCarIntrospection()
	{
		return "test.classes.Car\r\n"
				+ getVehicleIntrospection(4)
				+ "public test.classes.Car()\r\n";
	}
	
	
	public static String getTruckIntrospection()
	{
		return "test.classes.Truck\r\n"
				+ "test.classes.Car\r\n"
				+ getCarIntrospection()
				+ "public test.classes.Truck()\r\n";
	}
	
	public static String getCanTowIntrospection()
	{
		return "interface test.classes.CanTowCar\r\n"
				+ "public void test.classes.TowTruck.remove(test.classes.Vehicle)\r\n"
				+ "public void test.classes.TowTruck.tow(test.classes.Vehicle)\r\n";
	}
	
	public static String getTowTruckIntrospection()
	{
		return "test.classes.TowTruck\r\n"
				+ "test.classes.Truck\r\n"
				+ getTruckIntrospection()
				+ getCanTowIntrospection()
				+ "private void test.classes.TowTruck.unchainVehicle()\r\n"
				+ "public test.classes.TowTruck(double)\r\n"
				+ "private double test.classes.TowTruck.maxLoad = 100.0\r\n"
				+ "private java.util.List test.classes.TowTruck.vehiclesclass java.lang.Object[0] = {\r\n"
				+ "}\r\n";
	}
	
	public static String getTowTruckIntrospection2()
	{
		return "test.classes.TowTruck\r\n"
				+ "test.classes.Truck\r\n"
				+ getTruckIntrospection()
				+ getCanTowIntrospection()
				+ "private void test.classes.TowTruck.unchainVehicle()\r\n"
						+ "public test.classes.TowTruck(double)\r\n"
						+ "private double test.classes.TowTruck.maxLoad = 100.0\r\n"
				+ "private java.util.List test.classes.TowTruck.vehiclesclass java.lang.Object[2] = {\r\n"
				+ getCarIntrospection()
				+ getMotorcycleIntrospection()
				+ "}\r\n";
	}
}
