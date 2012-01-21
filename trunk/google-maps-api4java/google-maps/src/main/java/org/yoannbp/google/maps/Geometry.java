package org.yoannbp.google.maps;

public class Geometry {
	private Location location;
	private String locationType;
	private Bounds viewport;
	private Bounds bounds;

	public Geometry(Location location, String locationType, Bounds viewport,
			Bounds bounds) {
		super();
		this.location = location;
		this.locationType = locationType;
		this.viewport = viewport;
		this.bounds = bounds;
	}

	public Location getLocation() {
		return location;
	}

	public String getLocationType() {
		return locationType;
	}

	public Bounds getViewport() {
		return viewport;
	}

	public Bounds getBounds() {
		return bounds;
	}

	@Override
	public String toString() {
		return "Geometry [location=" + location + ", locationType="
				+ locationType + ", viewport=" + viewport + ", bounds="
				+ bounds + "]";
	}
}
