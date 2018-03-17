import java.util.Random;

import javax.tools.JavaFileManager.Location;

//cannot find the import for Location object

public class ObjectRandomLocationManager {

//Attributes
private double x;
private double y;
private Location loc;

//for getting random. how big our screen is
private double rangeMinX;
private double rangeMinY;

private double rangeMaxX;
private double rangeMaxY;

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

}

