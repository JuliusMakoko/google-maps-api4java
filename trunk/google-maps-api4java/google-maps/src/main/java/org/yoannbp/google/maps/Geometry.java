package org.yoannbp.google.maps;

public class Geometry {
	private Location location;
	private String locationType;
	private Rectangle viewport;
	private Rectangle bounds;

	public Geometry(Location location, String locationType, Rectangle viewport,
			Rectangle bounds) {
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

	public Rectangle getViewport() {
		return viewport;
	}

	public Rectangle getBounds() {
		return bounds;
	}

	@Override
	public String toString() {
		return "Geometry [location=" + location + ", locationType="
				+ locationType + ", viewport=" + viewport + ", bounds="
				+ bounds + "]";
	}
}
