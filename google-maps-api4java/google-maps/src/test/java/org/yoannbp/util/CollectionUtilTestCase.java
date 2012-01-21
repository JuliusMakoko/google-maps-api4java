package org.yoannbp.util;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNotNull;
import static junit.framework.Assert.assertNull;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.Test;

public class CollectionUtilTestCase {
	@Test
	public void testToString() throws Exception {

		assertEquals("", CollectionUtil.toString(null, ","));
		assertEquals("", CollectionUtil.toString(Arrays.asList(), ","));
		assertEquals("1,2,3,4",
				CollectionUtil.toString(Arrays.asList(1, 2, 3, 4), ","));
		assertEquals("1", CollectionUtil.toString(Arrays.asList(1), "|"));
		assertEquals("hello|bye",
				CollectionUtil.toString(Arrays.asList("hello", "bye"), "|"));

		assertEquals("", CollectionUtil.toString(null, null, ","));
		assertEquals("", CollectionUtil.toString(Arrays.asList(), null, ","));
		assertEquals("1,2,3,4",
				CollectionUtil.toString(Arrays.asList(1, 2, 3, 4), null, ","));
		assertEquals("1", CollectionUtil.toString(Arrays.asList(1), null, "|"));
		assertEquals("hello|bye", CollectionUtil.toString(
				Arrays.asList("hello", "bye"), null, "|"));
	}

	@Test(expected = IllegalArgumentException.class)
	public void testToString2() throws Exception {
		CollectionUtil.toString(Arrays.asList(1, 2, 3, 4), null);
	}

	@Test
	public void testToString3() throws Exception {

		Getter<String, Integer> getter = new Getter<String, Integer>() {
			@Override
			public String get(Integer o) {
				return o == null ? "" + 0 : "" + (o * 2);
			}
		};

		assertEquals("", CollectionUtil.toString(null, getter, ","));
		assertEquals("",
				CollectionUtil.toString(Arrays.<Integer> asList(), getter, ","));
		assertEquals("2,4,6,8",
				CollectionUtil.toString(Arrays.asList(1, 2, 3, 4), getter, ","));
		assertEquals("2",
				CollectionUtil.toString(Arrays.asList(1), getter, "|"));
		assertEquals("ahello|abye", CollectionUtil.toString(
				Arrays.asList("hello", "bye"), new Getter<String, String>() {
					@Override
					public String get(String o) {
						return "a" + o;
					}
				}, "|"));
	}

	@Test
	public void testFilter() throws Exception {

		Filter<Integer> filter = new Filter<Integer>() {
			@Override
			public boolean match(Integer obj) {
				return obj != null && obj > 2;
			}
		};

		assertListEquals(Collections.<Integer> emptyList(),
				CollectionUtil.filterList(null, filter));
		assertListEquals(Collections.<Integer> emptyList(),
				CollectionUtil.filterList(Arrays.<Integer> asList(), filter));
		assertListEquals(Arrays.asList(3, 4),
				CollectionUtil.filterList(Arrays.asList(1, 2, 3, 4), filter));
		assertListEquals(Collections.<Integer> emptyList(),
				CollectionUtil.filterList(Arrays.asList(1), filter));

		assertListEquals(Collections.<Integer> emptyList(),
				CollectionUtil.<Integer> filterList(null, null));
		assertListEquals(Collections.<Integer> emptyList(),
				CollectionUtil.filterList(Arrays.<Integer> asList(), null));
		assertListEquals(Arrays.asList(1, 2, 3, 4),
				CollectionUtil.filterList(Arrays.asList(1, 2, 3, 4), null));
		assertListEquals(Arrays.asList(1),
				CollectionUtil.filterList(Arrays.asList(1), null));

	}

	public static <T> void assertListEquals(List<T> expected, List<T> actual) {

		if (expected == null) {
			assertNull(actual);
			return;

		} else {

			assertNotNull(actual);
			assertEquals(expected.size(), actual.size());
			int i = 0;
			for (T e : expected) {
				T a = actual.get(i++);

				assertEquals(e, a);
			}
		}

	}

}
