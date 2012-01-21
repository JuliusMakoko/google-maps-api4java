package org.yoannbp.google.maps;

public class GeocodingRequest {
	// (required) — The address that you want to geocode.*
	private String address;
	// (required) — The textual latitude/longitude value for which you wish to
	// obtain the closest, human-readable address.*

	private Location latlng;

	// (optional) — The bounding box of the viewport within which to bias
	// geocode results more prominently. (For more information see Viewport
	// Biasing below.)
	private Rectangle bounds;

	// (optional) — The region code, specified as a ccTLD ("top-level domain")
	// two-character value. (For more information see Region Biasing below.)
	private String region;

	// (optional) — The language in which to return results. See the supported
	// list of domain languages. Note that we often update supported languages
	// so this list may not be exhaustive. If language is not supplied, the
	// geocoder will attempt to use the native language of the domain from which
	// the request is sent wherever possible.
	private Language language;

	// // (required)
	// private boolean sensor;

	public GeocodingRequest(String address, Location latlng, Rectangle bounds,
			String region, Language language) {
		super();
		this.address = address;
		this.latlng = latlng;
		this.bounds = bounds;
		this.region = region;
		this.language = language;

	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Location getLatlng() {
		return latlng;
	}

	public void setLatlng(Location latlng) {
		this.latlng = latlng;
	}

	public Rectangle getBounds() {
		return bounds;
	}

	public void setBounds(Rectangle bounds) {
		this.bounds = bounds;
	}

	public String getRegion() {
		return region;
	}

	public void setRegion(String region) {
		this.region = region;
	}

	public Language getLanguage() {
		return language;
	}

	public void setLanguage(Language language) {
		this.language = language;
	}

	@Override
	public String toString() {
		return "GeocodingRequest [address=" + address + ", latlng=" + latlng
				+ ", bounds=" + bounds + ", region=" + region + ", language="
				+ language + "]";
	}

}
