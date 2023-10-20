package test.classes;

import java.util.ArrayList;
import java.util.List;

public class TowTruck extends Truck implements CanTowCar
{
	private double 			maxLoad;
	private List<Vehicle> 	vehicles;
	
	public TowTruck(double maxLoad)
	{
		super();
		this.maxLoad 	= maxLoad;
		this.vehicles	= new ArrayList<>();
	}
	
	@Override
	public void tow(Vehicle vehicle)
	{
		// do something...
		vehicles.add(vehicle);
	}
	
	@Override
	public void remove(Vehicle vehicle)
	{
		// do something...
		unchainVehicle();
		// do something...
	}
	
	private void unchainVehicle()
	{
		// do something...
	}
}