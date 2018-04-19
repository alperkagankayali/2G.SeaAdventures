package GameEntities;

import java.util.Random;

//cannot find the import for Location object
public class ObjectRandomLocationManager {

	//Attributes
	private double x;
	private double y;
	private Location loc;

	//for getting random. how big our screen is
	private double rangeMinX = 700;
	private double rangeMinY = 0;

	private double rangeMaxX = 14000;
	private double rangeMaxY = 480;

	//Constructor
	public ObjectRandomLocationManager() {
		//Generate location.
		Location loc = this.generateLocation();
		this.loc = loc;
	}

	//Methods
	public Location generateLocation() {
		Random rand = new Random();

		double x = rangeMinX + (rangeMaxX - rangeMinX) * rand.nextDouble();
		double y = rangeMinY + (rangeMaxY - rangeMinY) * rand.nextDouble();

		this.x = x;
		this.y = y;

		Location newLocation = new Location(x,y);
		return newLocation;
	}

	public double getX() {
		return x;
	}

	public double getY() {
		return y;
	}

	public Location getLoc() {
		return loc;
	}

	private class Location {
		double x;
		double y;

		Location( double x, double y){
			this.x = x;
			this.y = y;
		}

		public double getX() {
			return x;
		}

		public double getY() {
			return y;
		}
	}
}

