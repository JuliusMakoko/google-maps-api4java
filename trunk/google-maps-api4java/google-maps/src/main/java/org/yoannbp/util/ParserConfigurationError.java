package org.yoannbp.util;

import javax.xml.parsers.ParserConfigurationException;

@SuppressWarnings("serial")
public final class ParserConfigurationError extends RuntimeException {

	public ParserConfigurationError(final ParserConfigurationException e) {
		super(e);
	}

}
