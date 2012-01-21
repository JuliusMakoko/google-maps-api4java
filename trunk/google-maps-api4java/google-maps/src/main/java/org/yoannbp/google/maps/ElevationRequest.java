package org.yoannbp.google.maps;

import java.util.Arrays;
import java.util.List;

public class ElevationRequest {
	private List<Location> locations;
	private List<Location> pathes;
	private int samples;

	public ElevationRequest(List<Location> locations) {
		this(locations, null, 0);
	}

	public ElevationRequest(Location... locations) {
		this(Arrays.asList(locations));
	}

	public ElevationRequest(List<Location> pathes, int samples) {
		this(null, pathes, samples);
	}

	private ElevationRequest(List<Location> locations, List<Location> pathes,
			int samples) {
		super();
		this.locations = locations;
		this.pathes = pathes;
		this.samples = samples;
	}

	public List<Location> getLocations() {
		return locations;
	}

	public void setLocations(List<Location> locations) {
		this.locations = locations;
	}

	public List<Location> getPathes() {
		return pathes;
	}

	public void setPathes(List<Location> pathes) {
		this.pathes = pathes;
	}

	public int getSamples() {
		return samples;
	}

	public void setSamples(int samples) {
		this.samples = samples;
	}

	@Override
	public String toString() {
		return "ElevationRequest [locations=" + locations + ", pathes="
				+ pathes + ", samples=" + samples + "]";
	}

}
