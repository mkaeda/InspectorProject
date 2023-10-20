package test.classes;

public abstract class Vehicle
{
	private int numberOfWheels;
	
	public Vehicle(int numberOfWheels)
	{
		this.numberOfWheels = numberOfWheels;
	}
	
	public int getNumberOfWheels()
	{
		return numberOfWheels;
	}
}