package org.yoannbp.google.maps;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.HttpEntity;
import org.apache.http.HttpException;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.w3c.dom.DOMException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.yoannbp.util.Getter;
import org.yoannbp.util.ParserConfigurationError;
import org.yoannbp.util.StringUtil;
import org.yoannbp.util.XmlUtil;

/**
 * Mashup for <a target="_blank" href=
 * "http://code.google.com/intl/fr-FR/apis/maps/documentation/webservices/index.html"
 * >Google Maps API Web Services</a>
 * 
 * @see <a target="_blank"
 *      href="http://code.google.com/intl/fr-FR/apis/maps/documentation/elevation/"
 *      >The Google Elevation API</a>
 * @see <a target="_blank"
 *      href="http://code.google.com/intl/fr-FR/apis/maps/documentation/geocoding/"
 *      >The Google Geocoding API</a>
 * 
 * @author Yoann Bertrand-Pierron
 * 
 */
public class GoogleMaps {

	private static DocumentBuilder BUILDER;

	static {
		final DocumentBuilderFactory fact = DocumentBuilderFactory
				.newInstance();
		try {
			BUILDER = fact.newDocumentBuilder();
		} catch (ParserConfigurationException e) {
			throw new ParserConfigurationError(e);
		}
	}

	public static HttpClient HTTP_CLIENT = new DefaultHttpClient();

	private static final Log LOGGER = LogFactory.getLog(GoogleMaps.class);

	private static final String ORIGIN_ADRESS = "origin_address";
	private static final String DESTINATION_ADRESS = "destination_address";

	public static DistanceResult getDistance(String origin, String destination,
			Mode mode) throws HttpException, IOException, URISyntaxException,
			ParserConfigurationException, SAXException {

		DistanceRequest request = new DistanceRequest(Arrays.asList(origin),
				Arrays.asList(destination), mode, false, false, Language.fr);
		GoogleResponse<DistanceResult> response = getDistance(request);

		return response == null || response.isEmpty() ? null : response
				.iterator().next();
	}

	public static GoogleResponse<DistanceResult> getDistance(
			DistanceRequest request) throws HttpException, IOException,
			URISyntaxException, ParserConfigurationException, SAXException {

		HttpGet r = buildDistanceRequest(request, false);
		Element doc = executingXmlRequest(r);

		Status status = Status.getFromLabel(XmlUtil.getChildElementByTagName(
				doc, "status").getTextContent());

		List<DistanceResult> distances = new ArrayList<DistanceResult>();

		List<String> origins1 = new ArrayList<String>();
		List<String> destinations1 = new ArrayList<String>();

		// origin_address
		for (Element origin : XmlUtil.getChildElementsByTagName(doc,
				ORIGIN_ADRESS)) {
			origins1.add(origin.getTextContent());
		}
		// destination_address
		for (Element destination : XmlUtil.getChildElementsByTagName(doc,
				DESTINATION_ADRESS)) {
			destinations1.add(destination.getTextContent());
		}

		// Get rows
		int i = 0;
		for (Element row : XmlUtil.getChildElementsByTagName(doc, "row")) {
			int j = 0;
			for (Element element : XmlUtil.getChildElementsByTagName(row,
					"element")) {

				ElementStatus elementStatus = ElementStatus
						.valueFromLabel(XmlUtil.getChildElementByTagName(
								element, "status").getTextContent());
				Pair duration = new Pair(Long.parseLong(getChild(element,
						"duration", "value").getTextContent()), getChild(
						element, "duration", "text").getTextContent());
				;
				Pair distance = new Pair(Long.parseLong(getChild(element,
						"distance", "value").getTextContent()), getChild(
						element, "distance", "text").getTextContent());

				DistanceResult response = new DistanceResult(elementStatus,
						origins1.get(i), destinations1.get(j), duration,
						distance);
				distances.add(response);
				j++;
			}
			i++;
		}

		return new GoogleResponse<DistanceResult>(status, distances);

	}

	private static HttpGet buildDistanceRequest(DistanceRequest request,
			boolean isJSon) throws URISyntaxException {

		StringBuffer sb = new StringBuffer();

		sb.append("origins=").append(
				StringUtil.toString(request.getOrigins(), "|"));
		sb.append("&destinations=").append(
				StringUtil.toString(request.getDestinations(), "|"));

		if (request.getMode() != null)
			sb.append("&mode=").append(request.getMode().getLabel());

		if (request.getLanguage() != null)
			sb.append("&language=").append(request.getLanguage().getCode());

		if (request.getUnit() != null)
			sb.append("&units=").append(request.getUnit().getLabel());

		if (request.isAvoidTolls())
			sb.append("&avoid=tolls");

		if (request.isAvoidHighways())
			sb.append("&avoid=highways");

		sb.append("&sensor=false");

		URI uri = new URI("http", "maps.googleapis.com",
				"/maps/api/distancematrix/" + (isJSon ? "json" : "xml"),
				sb.toString(), null);

		return new HttpGet(uri);
	}

	public static GoogleResponse<ElevationResult> getElevationResponse(
			ElevationRequest request) throws ParserConfigurationException,
			SAXException, IOException, HttpException, URISyntaxException {
		HttpGet r = buildElevationRequest(request, false);
		Element doc = executingXmlRequest(r);

		String status = getChild(doc, "status").getTextContent();
		List<ElevationResult> results = new ArrayList<ElevationResult>();

		for (Element e : XmlUtil.getChildElementsByTagName(doc, "result")) {

			Location location = parseLocation(getChild(e, "location"));
			double elevation = Double.parseDouble(getChild(e, "elevation")
					.getTextContent());
			double resolution = Double.parseDouble(getChild(e, "resolution")
					.getTextContent());

			results.add(new ElevationResult(location, elevation, resolution));
		}

		GoogleResponse<ElevationResult> response = new GoogleResponse<ElevationResult>(
				Status.getFromLabel(status), results);
		return response;
	}

	public static GoogleResponse<GeocodeResult> getGeocodingResponse(
			GeocodeRequest request) throws ParserConfigurationException,
			SAXException, IOException, HttpException, URISyntaxException {
		HttpGet r = buildGeocodingRequest(request, false);
		Element doc = executingXmlRequest(r);

		String status = getChild(doc, "status").getTextContent();
		List<GeocodeResult> results = new ArrayList<GeocodeResult>();

		for (Element resultTag : XmlUtil.getChildElementsByTagName(doc,
				"result")) {

			List<String> types = new ArrayList<String>();
			for (Element type : XmlUtil.getChildElementsByTagName(resultTag,
					"type")) {
				types.add(type.getTextContent());
			}

			String formattedAddress = getChild(resultTag, "formatted_address")
					.getTextContent();

			List<AddressComponent> adresses = new ArrayList<AddressComponent>();
			for (Element address : XmlUtil.getChildElementsByTagName(resultTag,
					"address_component")) {

				List<String> types1 = new ArrayList<String>();
				for (Element type : XmlUtil.getChildElementsByTagName(address,
						"type")) {
					types1.add(type.getTextContent());
				}
				String longName = getChild(address, "long_name")
						.getTextContent();
				String shortName = getChild(address, "short_name")
						.getTextContent();

				AddressComponent addressObj = new AddressComponent(longName,
						shortName, types1);

				adresses.add(addressObj);
			}

			Element geometry1 = XmlUtil.getChildElementByTagName(resultTag,
					"geometry");

			Location location = parseLocation(getChild(geometry1, "location"));

			String locationType = getChild(geometry1, "location_type")
					.getTextContent();

			Rectangle viewport = new Rectangle(parseLocation(getChild(
					geometry1, "viewport", "northeast")),
					parseLocation(getChild(geometry1, "viewport", "southwest")));

			Element boundsElement = XmlUtil.getChildElementByTagNameIfExists(
					geometry1, "bounds");
			Rectangle bounds = null;
			if (boundsElement != null) {
				bounds = new Rectangle(parseLocation(getChild(boundsElement,
						"northeast")), parseLocation(getChild(boundsElement,
						"southwest")));
			}

			Geometry geometry = new Geometry(location, locationType, viewport,
					bounds);

			results.add(new GeocodeResult(types, formattedAddress, adresses,
					geometry));
		}
		return new GoogleResponse<GeocodeResult>(Status.getFromLabel(status),
				results);
	}

	private static HttpGet buildElevationRequest(ElevationRequest request,
			boolean jSonOutput) throws URISyntaxException {
		if (request.getLocations() != null && request.getPathes() != null) {
			throw new IllegalArgumentException();
		}

		StringBuffer sb = new StringBuffer();
		if (request.getLocations() != null) {
			sb.append("locations=").append(
					StringUtil.toString(request.getLocations(),
							INTERNAL_STRING_GETTER, "|"));
		} else {
			sb.append("path=").append(
					StringUtil.toString(request.getPathes(),
							INTERNAL_STRING_GETTER, "|"));
			if (request.getSamples() > 0)
				sb.append("&samples=").append(request.getSamples());
		}

		sb.append("&sensor=false");

		// http://maps.googleapis.com/maps/api/geocode/output?parameters
		URI uri = new URI("http", "maps.googleapis.com", "/maps/api/elevation/"
				+ (jSonOutput ? "json" : "xml"), sb.toString(), null);

		return new HttpGet(uri);
	}

	private static HttpGet buildGeocodingRequest(GeocodeRequest request,
			boolean isJson) throws URISyntaxException {

		StringBuffer sb = new StringBuffer();

		if (request.getAddress() != null)
			sb.append("address=").append(request.getAddress());

		if (request.getBounds() != null
				&& request.getBounds().getNortheast() != null
				&& request.getBounds().getSouthwest() != null)
			sb.append("&bounds=")
					.append(request.getBounds().getSouthwest()
							.toInternalString())
					.append("|")
					.append(request.getBounds().getNortheast()
							.toInternalString());

		if (request.getLatlng() != null)
			sb.append("&latlng=")
					.append(request.getLatlng().toInternalString());

		if (request.getRegion() != null)
			sb.append("&region=").append(request.getRegion());

		if (request.getLanguage() != null)
			sb.append("&language=").append(request.getLanguage().getCode());

		sb.append("&sensor=false");

		// http://maps.googleapis.com/maps/api/geocode/output?parameters
		URI uri = new URI("http", "maps.googleapis.com", "/maps/api/geocode/"
				+ (isJson ? "json" : "xml"), sb.toString(), null);

		return new HttpGet(uri);
	}

	private static Element getChild(Element element, String... tagNames)
			throws SAXException {
		if (tagNames == null || tagNames.length == 0)
			return null;
		else {
			Element e = element;
			for (String tagName : tagNames)
				e = XmlUtil.getChildElementByTagName(e, tagName);
			return e;
		}
	}

	private static Element executingXmlRequest(HttpGet request)
			throws ParserConfigurationException, SAXException, IOException,
			HttpException {

		HttpResponse response = HTTP_CLIENT.execute(request);
		HttpEntity entity = response.getEntity();

		byte[] bytes = (entity != null) ? EntityUtils.toByteArray(entity)
				: new byte[0];

		String charset = EntityUtils.getContentCharSet(entity);
		if (charset == null) {
			charset = HTTP.DEFAULT_CONTENT_CHARSET;
		}

		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("request: " + request.getURI());
			LOGGER.debug("response: " + new String(bytes, charset));
		}

		ByteArrayInputStream is = new ByteArrayInputStream(bytes);
		InputSource src = new InputSource(new InputStreamReader(is, charset));
		Document document = BUILDER.parse(src);
		// is.close();
		return document.getDocumentElement();

		// return XmlUtil.getRootElement(content);
	}

	public static Location parseLocation(Element element) throws DOMException,
			SAXException {
		double latitude = Double.parseDouble(getChild(element, "lat")
				.getTextContent());
		double longitude = Double.parseDouble(getChild(element, "lng")
				.getTextContent());
		return new Location(latitude, longitude);
	}

	private static Getter<String, Location> INTERNAL_STRING_GETTER = new Getter<String, Location>() {
		@Override
		public String get(Location value) {
			return value.toInternalString();
		}
	};
}
