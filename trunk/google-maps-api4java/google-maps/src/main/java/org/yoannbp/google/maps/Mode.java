package org.yoannbp.google.maps;

public enum Mode {

	driving("driving",
			"(default) indicates standard driving directions using the road network"),

	walking(
			"walking",
			"requests walking directions via pedestrian paths & sidewalks (where available)"),

	bicycling(
			"bicycling",
			"requests bicycling directions via bicycle paths & preferred streets (currently only available in the US and some Canadian cities)");

	private String label;
	private String description;

	private Mode(String label, String description) {
		this.label = label;
		this.description = description;
	}

	public String getLabel() {
		return label;
	}

	public String getDescription() {
		return description;
	}

}
