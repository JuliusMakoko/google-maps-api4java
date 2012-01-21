package org.yoannbp.google.maps;

public enum Status {
	OK("OK", "indicating the API request was successful"), INVALID_REQUEST(
			"INVALID_REQUEST", "indicating the API request was malformed"), OVER_QUERY_LIMIT(
			"OVER_QUERY_LIMIT", "indicating the requestor has exceeded quota"), REQUEST_DENIED(
			"REQUEST_DENIED",
			"indicating the API did not complete the request, likely because the requestor failed to include a valid sensor parameter"), UNKNOWN_ERROR(
			"UNKNOWN_ERROR", "indicating an unknown error");

	private String label;
	private String description;

	private Status(String label, String description) {
		this.label = label;
		this.description = description;
	}

	public String getLabel() {
		return label;
	}

	public String getDescription() {
		return description;
	}

	public static Status getFromLabel(String label) {
		for (Status status : Status.values()) {
			if (status.label.equals(label)) {
				return status;
			}
		}
		throw new IllegalArgumentException("Unable to find status from label: "
				+ label);
	}
}
