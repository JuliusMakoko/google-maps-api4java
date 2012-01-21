package org.yoannbp.google.maps;

public class DistanceResult {
	private String origin;
	private String destination;

	private ElementStatus status;
	private O duration;
	private O distance;

	public DistanceResult(ElementStatus status, String origin,
			String destination, O duration, O distance) {
		super();
		this.origin = origin;
		this.destination = destination;
		this.status = status;
		this.duration = duration;
		this.distance = distance;
	}

	public String getOrigin() {
		return origin;
	}

	public String getDestination() {
		return destination;
	}

	public ElementStatus getStatus() {
		return status;
	}

	public O getDuration() {
		return duration;
	}

	public O getDistance() {
		return distance;
	}

	@Override
	public String toString() {
		return "DistanceResponse [origin=" + origin + ", destination="
				+ destination + ", status=" + status + ", duration=" + duration
				+ ", distance=" + distance + "]";
	}

}
