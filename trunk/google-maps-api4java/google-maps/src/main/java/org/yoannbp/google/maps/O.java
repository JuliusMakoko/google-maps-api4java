package org.yoannbp.google.maps;

public class O {
	private long value;
	private String text;

	public O(long value, String text) {
		super();
		this.value = value;
		this.text = text;
	}

	public String getText() {
		return text;
	}

	public long getValue() {
		return value;
	}

	@Override
	public String toString() {
		return "O [value=" + value + ", text=" + text + "]";
	}
}
