package org.yoannbp.google.maps;

public enum ElementStatus {
	OK("OK", "indicates the response contains a valid result"), NOT_FOUND(
			"NOT_FOUND",
			"indicates that the origin and/or destination of this pairing could not be geocoded"), ZERO_RESULTS(
			"ZERO_RESULTS",
			"indicates no route could be found between the origin and destination");

	private String label;
	private String description;

	private ElementStatus(String label, String description) {
		this.label = label;
		this.description = description;
	}

	public String getLabel() {
		return label;
	}

	public String getDescription() {
		return description;
	}

	public static ElementStatus valueFromLabel(String label) {
		for (ElementStatus status : ElementStatus.values()) {
			if (status.getLabel().equals(label)) {
				return status;
			}
		}

		throw new IllegalArgumentException(
				"Unable to find element status from label: " + label);
	}

}
