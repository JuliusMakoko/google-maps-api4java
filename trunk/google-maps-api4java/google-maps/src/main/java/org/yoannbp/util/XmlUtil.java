package org.yoannbp.util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public abstract class XmlUtil {

	@SuppressWarnings("unchecked")
	public static <T extends Node> List<T> getChildren(Class<?> tagClass,
			Element element) {
		NodeList elements = element.getChildNodes();
		if (elements.getLength() == 0)
			return Collections.emptyList();
		else {

			List<T> list = new ArrayList<T>();
			for (int i = 0; i < elements.getLength(); i++) {
				Node e = elements.item(i);
				if (tagClass.isAssignableFrom(e.getClass()))
					list.add((T) e);
			}
			return list;
		}

	}

	public static <T extends Element> List<T> getChildElements(Element element) {
		return getChildren(Element.class, element);
	}

	/**
	 * Returns the child elements whose tag name is equal to the specified
	 * parameter. It's <b>not</b> a recursive search
	 * 
	 * @param element
	 * @param tagName
	 * @return
	 * @throws SAXException
	 */
	public static List<Element> getChildElementsByTagName(Element element,
			final String tagName) {
		return StringUtil.filterList(getChildElements(element),
				new Filter<Element>() {
					@Override
					public boolean match(Element obj) {
						return obj.getNodeName().equals(tagName);
					}
				});
	}

	/**
	 * Returns the child elements whose tag name is equal to the specified
	 * parameter. It's <b>not</b> a recursive search
	 * 
	 * @param element
	 * @param tagName
	 * @return
	 * @throws SAXException
	 */
	public static Element getChildElementByTagName(Element element,
			String tagName) throws SAXException {

		List<Element> elements = getChildElementsByTagName(element, tagName);
		if (elements.size() != 1) {
			throw new SAXException(
					"You are looking for one element with tag name equals to '"
							+ tagName + "' but parser found " + elements.size()
							+ " elements");
		} else {
			return (Element) elements.get(0);
		}
	}

	public static Element getChildElementByTagNameIfExists(Element element,
			String tagName) throws SAXException {

		List<Element> elements = getChildElementsByTagName(element, tagName);
		if (elements.isEmpty()) {
			return null;
		} else if (elements.size() == 1) {
			return (Element) elements.get(0);
		} else {
			throw new SAXException(
					"You are looking for one element with tag name equals to '"
							+ tagName + "' but parser found " + elements.size()
							+ " elements");
		}
	}

}
