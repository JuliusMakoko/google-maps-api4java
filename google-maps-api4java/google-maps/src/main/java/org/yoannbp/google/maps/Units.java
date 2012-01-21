package org.yoannbp.google.maps;

public enum Units {
	// (default) returns distances in kilometers and meters.
	metric("metric"),

	// returns distances in miles and feet.
	imperial("imperial");

	private String label;

	private Units(String label) {
		this.label = label;
	}

	public String getLabel() {
		return label;
	}
}
