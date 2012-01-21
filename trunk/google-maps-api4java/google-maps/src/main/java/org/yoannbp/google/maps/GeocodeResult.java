package org.yoannbp.google.maps;

import java.util.List;

public class GeocodeResult {

	private List<String> types;
	private String formattedAddress;
	private List<AddressComponent> adresses;
	private Geometry geometry;

	public GeocodeResult(List<String> types, String formattedAddress,
			List<AddressComponent> adresses, Geometry geometry) {
		super();
		this.types = types;
		this.formattedAddress = formattedAddress;
		this.adresses = adresses;
		this.geometry = geometry;
	}

	public List<String> getTypes() {
		return types;
	}

	public String getFormattedAddress() {
		return formattedAddress;
	}

	public List<AddressComponent> getAdresses() {
		return adresses;
	}

	public Geometry getGeometry() {
		return geometry;
	}

	@Override
	public String toString() {
		return "GeocodingResponse [types=" + types + ", formattedAddress="
				+ formattedAddress + ", adresses=" + adresses + ", geometry="
				+ geometry + "]";
	}
}
