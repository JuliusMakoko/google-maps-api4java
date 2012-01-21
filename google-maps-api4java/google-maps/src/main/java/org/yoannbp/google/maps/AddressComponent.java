package org.yoannbp.google.maps;

import java.util.List;

public class AddressComponent {

	private String longName;
	private String shortName;
	private List<String> types;

	public AddressComponent(String longName, String shortName,
			List<String> types) {
		super();
		this.longName = longName;
		this.shortName = shortName;
		this.types = types;
	}

	public String getLongName() {
		return longName;
	}

	public void setLongName(String longName) {
		this.longName = longName;
	}

	public String getShortName() {
		return shortName;
	}

	public void setShortName(String shortName) {
		this.shortName = shortName;
	}

	public List<String> getTypes() {
		return types;
	}

	public void setTypes(List<String> types) {
		this.types = types;
	}

	@Override
	public String toString() {
		return "AddressComponent [longName=" + longName + ", shortName="
				+ shortName + ", types=" + types + "]";
	}
}
