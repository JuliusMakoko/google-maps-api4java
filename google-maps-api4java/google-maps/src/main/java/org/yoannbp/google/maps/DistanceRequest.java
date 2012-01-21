package org.yoannbp.google.maps;

import java.util.Collection;

public class DistanceRequest {

	private Collection<String> origins;
	private Collection<String> destinations;
	private Mode mode;
	/**
	 * @deprecated useless
	 */
	@Deprecated
	private Units unit;
	private boolean avoidTolls;
	private boolean avoidHighways;
	private Language language;

	public DistanceRequest() {
		this(null, null);
	}

	public DistanceRequest(Collection<String> origins,
			Collection<String> destinations) {
		this(origins, destinations, null, false, false, null);
	}

	public DistanceRequest(Collection<String> origins,
			Collection<String> destinations, Mode mode, boolean avoidTolls,
			boolean avoidHighways, Language language) {
		super();
		this.origins = origins;
		this.destinations = destinations;
		this.mode = mode;
		this.avoidTolls = avoidTolls;
		this.avoidHighways = avoidHighways;
		this.language = language;
	}

	public Collection<String> getOrigins() {
		return origins;
	}

	public void setOrigins(Collection<String> origins) {
		this.origins = origins;
	}

	public Collection<String> getDestinations() {
		return destinations;
	}

	public void setDestinations(Collection<String> destinations) {
		this.destinations = destinations;
	}

	public Mode getMode() {
		return mode;
	}

	public void setMode(Mode mode) {
		this.mode = mode;
	}

	public Units getUnit() {
		return unit;
	}

	public void setUnit(Units unit) {
		this.unit = unit;
	}

	public boolean isAvoidTolls() {
		return avoidTolls;
	}

	public void setAvoidTolls(boolean avoidTolls) {
		this.avoidTolls = avoidTolls;
	}

	public boolean isAvoidHighways() {
		return avoidHighways;
	}

	public void setAvoidHighways(boolean avoidHighways) {
		this.avoidHighways = avoidHighways;
	}

	public Language getLanguage() {
		return language;
	}
}
