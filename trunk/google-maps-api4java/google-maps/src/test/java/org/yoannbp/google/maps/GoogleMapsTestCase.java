package org.yoannbp.google.maps;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNotNull;

import java.util.Arrays;

import org.apache.log4j.BasicConfigurator;
import org.junit.BeforeClass;
import org.junit.Test;

public class GoogleMapsTestCase {
	@BeforeClass
	public static void setUp() {
		BasicConfigurator.configure();
	}

	public static void main(String[] args) throws Exception {

		DistanceRequest request = new DistanceRequest(Arrays.asList("Annecy",
				"Annemasse"), Arrays.asList("Cruseilles", "Archamps"));
		request.setAvoidTolls(true);

		GoogleResponse<DistanceResult> response = GoogleMaps
				.getDistance(request);
		System.out.println(response);
		// //
		// "ABQIAAAASFNA2WWNaQhVyBgZ31K3phT2yXp_ZAY8_ufC3CFXhHIE1NvwkxS7gMTFwqfu7VDHtwcOLSaD0WqPrA"
		//
		// GeocodingRequest request = new GeocodingRequest("Chavelot", null,
		// null,
		// null, null);
		//
		// GeocodingRequest request = new GeocodingRequest(null, new Location(
		// 40.714224, -73.961452), null, null, null);

		// GoogleResponse<GeocodeResult> response =
		// getGeocodingResponse(request);
		// System.out.println(response);
	}

	@Test
	public void testGeocoding() throws Exception {
		// http://maps.googleapis.com/maps/api/geocode/json?address=1600+Amphitheatre+Parkway,+Mountain+View,+CA&sensor=true_or_false

		GeocodeRequest request = new GeocodeRequest(
				"1600 Amphitheatre Parkway, Mountain View, CA", null, null,
				null, null);

		GoogleResponse<GeocodeResult> response = GoogleMaps
				.getGeocodingResponse(request);
		assertOkStatus(response);
		assertEquals(1, response.getResults().size());

		// TODO check contents

	}

	@Test
	public void testGeocoding2() throws Exception {
		// http://maps.googleapis.com/maps/api/geocode/json?latlng=40.714224,-73.961452&sensor=true_or_false

		GeocodeRequest request = new GeocodeRequest(null, new Location(
				40.714224, -73.961452), null, null, null);

		GoogleResponse<GeocodeResult> response = GoogleMaps
				.getGeocodingResponse(request);
		assertOkStatus(response);
		assertEquals(11, response.getResults().size());
		// TODO check contents
	}

	@Test
	public void testGeocoding3() throws Exception {
		// http://maps.googleapis.com/maps/api/geocode/json?address=Winnetka&sensor=false

		GeocodeRequest request = new GeocodeRequest("Winnetka", null, null,
				null, null);

		GoogleResponse<GeocodeResult> response = GoogleMaps
				.getGeocodingResponse(request);
		assertOkStatus(response);
		assertEquals(1, response.getResults().size());
		// TODO check contents
	}

	@Test
	public void testGeocoding4() throws Exception {
		// http://maps.googleapis.com/maps/api/geocode/json?address=Winnetka&bounds=34.172684,-118.604794|34.236144,-118.500938&sensor=false

		GeocodeRequest request = new GeocodeRequest("Winnetka", null,
				new Rectangle(new Location(34.172684, -118.604794),
						new Location(34.236144, -118.500938)), null, null);

		GoogleResponse<GeocodeResult> response = GoogleMaps
				.getGeocodingResponse(request);
		assertOkStatus(response);
		assertEquals(1, response.getResults().size());
		// TODO check contents
	}

	@Test
	public void testGeocoding5() throws Exception {
		// http://maps.googleapis.com/maps/api/geocode/json?address=Toledo&sensor=false
		GeocodeRequest request = new GeocodeRequest("Toledo", null, null,
				null, null);

		GoogleResponse<GeocodeResult> response = GoogleMaps
				.getGeocodingResponse(request);
		assertOkStatus(response);
		assertEquals(5, response.getResults().size());
		// TODO check contents
	}

	@Test
	public void testGeocoding6() throws Exception {
		// http://maps.googleapis.com/maps/api/geocode/json?address=Toledo&sensor=false&region=es

		GeocodeRequest request = new GeocodeRequest("Toledo", null, null,
				"es", null);

		GoogleResponse<GeocodeResult> response = GoogleMaps
				.getGeocodingResponse(request);
		assertOkStatus(response);
		assertEquals(1, response.getResults().size());
		// TODO check contents
	}

	@Test
	public void testElevation1() throws Exception {

		ElevationRequest request = new ElevationRequest(new Location(
				39.7391536, -104.9847034));

		GoogleResponse<ElevationResult> response = GoogleMaps
				.getElevationResponse(request);
		assertOkStatus(response);
		assertEquals(1, response.getResults().size());

		ElevationResult result = response.getResults().get(0);
		assertElevationResultEquals(39.7391536, -104.9847034, 1608.6379395,
				4.771976, result);
	}

	// http://maps.googleapis.com/maps/api/elevation/json?locations=39.7391536,-104.9847034|36.455556,-116.866667&sensor=true_or_false
	@Test
	public void testElevation2() throws Exception {

		ElevationRequest request = new ElevationRequest(new Location(
				39.7391536, -104.9847034), new Location(36.455556, -116.866667));

		GoogleResponse<ElevationResult> response = GoogleMaps
				.getElevationResponse(request);
		assertOkStatus(response);
		assertEquals(2, response.getResults().size());

		ElevationResult result = response.getResults().get(0);
		assertElevationResultEquals(39.7391536, -104.9847034, 1608.6379395,
				4.771976, result);

		result = response.getResults().get(1);
		assertElevationResultEquals(36.4555560, -116.8666670, -50.7890358,
				19.087904, result);
	}

	@Test
	public void testElevation3() throws Exception {
		// http://maps.googleapis.com/maps/api/elevation/json
		// ?path=36.578581,-118.291994|36.23998,-116.83171&samples=3&sensor=true_or_false

		ElevationRequest request = new ElevationRequest(Arrays.asList(
				new Location(36.578581, -118.291994), new Location(36.23998,
						-116.83171)), 3);

		GoogleResponse<ElevationResult> response = GoogleMaps
				.getElevationResponse(request);
		assertOkStatus(response);
		assertEquals(3, response.getResults().size());

		ElevationResult result = response.getResults().get(0);
		assertElevationResultEquals(36.5785810, -118.2919940, 4411.9418945,
				19.0879040, result);

		result = response.getResults().get(1);
		assertElevationResultEquals(36.4115029, -117.5602608, 1381.8616943,
				19.0879040, result);

		result = response.getResults().get(2);
		assertElevationResultEquals(36.2399800, -116.8317100, -84.6169968,
				19.0879040, result);
	}

	@Test
	public void testDistance1() throws Exception {
		// http://maps.googleapis.com/maps/api/distancematrix/json
		// ?origins=Vancouver+BC|Seattle&destinations=San+Francisco|Victoria+BC&mode=bicycling&language=fr-FR&sensor=false

		DistanceRequest request = new DistanceRequest(Arrays.asList(
				"Vancouver BC", "Seattle"), Arrays.asList("San Francisco",
				"Victoria BC"), Mode.bicycling, false, false, Language.fr);

		GoogleResponse<DistanceResult> response = GoogleMaps
				.getDistance(request);
		assertOkStatus(response);
		assertEquals(4, response.getResults().size());

		DistanceResult result = response.getResults().get(0);
		assertDistanceResultEquals("Vancouver, BC, Canada",
				"San Francisco, Californie, États-Unis", 343543,
				"3 jours 23 heures", 1742196, "1 742 km", result);

		result = response.getResults().get(1);
		assertDistanceResultEquals("Vancouver, BC, Canada",
				"Victoria, BC, Canada", 25064, "6 heures 58 minutes", 136003,
				"136 km", result);

		result = response.getResults().get(2);
		assertDistanceResultEquals("Seattle, État de Washington, États-Unis",
				"San Francisco, Californie, États-Unis", 288327,
				"3 jours 8 heures", 1481687, "1 482 km", result);

		result = response.getResults().get(3);
		assertDistanceResultEquals("Seattle, État de Washington, États-Unis",
				"Victoria, BC, Canada", 44912, "12 heures 29 minutes", 255165,
				"255 km", result);

	}

	private static void assertOkStatus(GoogleResponse<?> actual) {
		assertNotNull("Response is null", actual);
		assertNotNull("Status is null", actual.getStatus());
		assertEquals(actual.getStatus().getDescription(), Status.OK,
				actual.getStatus());
	}

	private static void assertLocationEquals(double a, double b, Location actual) {
		assertNotNull("Location is null", actual);
		assertEquals(a, actual.getLatitude());
		assertEquals(b, actual.getLongitude());
	}

	private static void assertElevationResultEquals(double lat, double lng,
			double elevation, double resolution, ElevationResult actual) {
		assertNotNull("ElevationResult is null", actual);
		assertLocationEquals(lat, lng, actual.getLocation());
		assertEquals(elevation, actual.getElevation());
		assertEquals(resolution, actual.getResolution());
	}

	private void assertDistanceResultEquals(String origin, String destination,
			long duration, String durationText, long distance,
			String distanceText, DistanceResult actual) {
		assertNotNull("DistanceResult is null", actual);
		assertEquals(origin, actual.getOrigin());
		assertEquals(destination, actual.getDestination());
		assertOEquals(duration, durationText, actual.getDuration());
		assertOEquals(distance, distanceText, actual.getDistance());
	}

	private void assertOEquals(long expectedValue, String expectedText,
			Pair actual) {
		assertEquals(expectedValue, actual.getValue());
		assertEquals(expectedText, actual.getText());
	}
}
