package org.yoannbp.google.maps;

import static junit.framework.Assert.*;

import java.util.Arrays;

import org.apache.log4j.BasicConfigurator;
import org.junit.BeforeClass;
import org.junit.Test;

public class GoogleMapsTestCase {
	@BeforeClass
	public static void setUp() {
		BasicConfigurator.configure();
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

	private void assertOEquals(long expectedValue, String expectedText, O actual) {
		assertEquals(expectedValue, actual.getValue());
		assertEquals(expectedText, actual.getText());
	}
}
