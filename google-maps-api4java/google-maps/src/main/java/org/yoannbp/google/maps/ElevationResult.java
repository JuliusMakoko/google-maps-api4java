package org.yoannbp.google.maps;


public class ElevationResult {

	private Location location;
	private double elevation;
	private double resolution;

	public ElevationResult(Location location, double elevation,
			double resolution) {
		super();
		this.location = location;
		this.elevation = elevation;
		this.resolution = resolution;
	}

	public Location getLocation() {
		return location;
	}

	public double getElevation() {
		return elevation;
	}

	public double getResolution() {
		return resolution;
	}

	@Override
	public String toString() {
		return "ElevationResult [location=" + location + ", elevation="
				+ elevation + ", resolution=" + resolution + "]";
	}

}
