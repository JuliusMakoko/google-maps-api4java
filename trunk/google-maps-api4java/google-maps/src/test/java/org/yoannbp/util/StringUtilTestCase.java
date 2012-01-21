package org.yoannbp.util;

import java.util.Arrays;

import org.junit.Test;

import static junit.framework.Assert.*;

public class StringUtilTestCase {
	@Test
	public void testToString() throws Exception {

		assertEquals("", StringUtil.toString(null, ","));
		assertEquals("", StringUtil.toString(Arrays.asList(), ","));
		assertEquals("1,2,3,4",
				StringUtil.toString(Arrays.asList(1, 2, 3, 4), ","));
		assertEquals("1", StringUtil.toString(Arrays.asList(1), "|"));
		assertEquals("hello|bye",
				StringUtil.toString(Arrays.asList("hello", "bye"), "|"));
	}

	@Test(expected = IllegalArgumentException.class)
	public void testToString2() throws Exception {
		StringUtil.toString(Arrays.asList(1, 2, 3, 4), null);
	}
}
