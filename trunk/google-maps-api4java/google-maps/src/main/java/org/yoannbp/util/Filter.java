package org.yoannbp.util;


public interface Filter<T> {

	boolean match(T obj);

}
