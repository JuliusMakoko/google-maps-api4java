package org.yoannbp.util;

import java.util.ArrayList;
import java.util.List;

public class StringUtil {

	public static String toString(Iterable<?> collection, String delimiter) {
		return toString(collection, null, delimiter);
	}

	public static <T> String toString(Iterable<? extends T> collection,
			Getter<?, T> getter, String delimiter) {

		if (delimiter == null) {
			throw new IllegalArgumentException("delimiter can't be null");
		} else if (collection == null) {
			return "";

		} else {

			StringBuffer sb = new StringBuffer();
			for (T e : collection) {
				sb.append(getter == null ? e : getter.get(e).toString());

				sb.append(delimiter);
			}

			return sb.length() != 0 ? sb.substring(0,
					sb.length() - delimiter.length()) : sb.toString();
		}
	}
	
	public static <T> List<T> filterList(Iterable<? extends T> elements,
			Filter<T> filter) {
		List<T> result = new ArrayList<T>();
		if (elements != null) {

			for (T e : elements) {
				if (filter == null || filter.match(e))
					result.add(e);
			}
		}
		return result;

	}
}
