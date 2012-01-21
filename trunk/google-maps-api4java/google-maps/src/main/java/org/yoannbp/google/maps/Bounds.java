package org.yoannbp.google.maps;

public class Bounds {
	private Location northeast;
	private Location southwest;

	public Bounds(Location northeast, Location southwest) {
		super();
		this.northeast = northeast;
		this.southwest = southwest;
	}

	public Location getNortheast() {
		return northeast;
	}

	public Location getSouthwest() {
		return southwest;
	}

	@Override
	public String toString() {
		return "Bounds [northeast=" + northeast + ", southwest=" + southwest
				+ "]";
	}

}
