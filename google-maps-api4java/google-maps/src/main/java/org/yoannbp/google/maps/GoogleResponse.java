package org.yoannbp.google.maps;

import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class GoogleResponse<T> implements Iterable<T> {
	private Status status;
	private List<T> results;

	public GoogleResponse(Status status, List<T> results) {
		this.status = status;
		this.results = Collections.unmodifiableList(results);
	}

	@Override
	public Iterator<T> iterator() {
		return results.iterator();
	}

	public boolean isEmpty() {
		return results.isEmpty();
	}

	public List<T> getResults() {
		return results;
	}

	public Status getStatus() {
		return status;
	}

	@Override
	public String toString() {
		return "DistanceResponseMatrix [status=" + status + ", results="
				+ results + "]";
	}

}
