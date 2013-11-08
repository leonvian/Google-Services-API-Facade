package br.com.lvc.googleplaces.facade;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import android.util.Log;
import br.com.lvc.googleplaces.connection.HttpConnectionException;
import br.com.lvc.googleplaces.connection.WebServiceComun;
import br.com.lvc.googleplaces.model.direction.ResultDirectionRequest;
import br.com.lvc.googleplaces.model.geocode.ResultCompleteGeocode;
import br.com.lvc.googleplaces.model.place.ResultPlaceRequest;

import com.google.android.gms.maps.model.LatLng;

public class GoogleServicesFacade extends WebServiceComun {

	public static final String URL_PLACES_API = "https://maps.googleapis.com/maps/api/place/nearbysearch/json?"; // Lista locais pertinentes próximos a uma localização
	public static final String URL_DIRECTIONS_API = "https://maps.googleapis.com/maps/api/directions/json?"; // Consegue traçar rotas entre varios pontos	 
	public static final String URL_GEOCODE_API = "http://maps.googleapis.com/maps/api/geocode/json?"; // Permite saber uma localização com base em um endereço

	private static GoogleServicesFacade instance;

	public static GoogleServicesFacade getInstance() {
		if(instance == null)
			instance = new GoogleServicesFacade();
		return instance;
	}

	/**
	 * 
	 * @param address
	 * @param region = br to Brazil
	 * @return
	 * @throws HttpConnectionException
	 */
	public ResultCompleteGeocode requestLocationByAddress(String address, String region) throws HttpConnectionException {
		String preparedAddres = prepareDestinationString(address);
		String url = URL_GEOCODE_API.concat("address=" + preparedAddres + "&sensor=true&region=" + region);
		ResultCompleteGeocode result = sendDataGet(url, ResultCompleteGeocode.class);
		return result;
	}

	public ResultPlaceRequest requestPlacesNearLocation(LatLng positionTarget, int radius, String key) throws HttpConnectionException {
		String latLngString = latLngToString(positionTarget);
		String url = URL_PLACES_API.concat("location=" + latLngString +"&radius="+ radius +"&sensor=true&key=" + key);
		ResultPlaceRequest result = sendDataGet(url, ResultPlaceRequest.class);
		return result;
	}


	public ResultDirectionRequest requestWayToGoToDestiny(LatLng origin, String destination, List<LatLng> latLngs) throws HttpConnectionException {
		List<String> wayPoints = toListString(latLngs);
		String url = generateURLDirection(latLngToString(origin), prepareDestinationString(destination),wayPoints);
		ResultDirectionRequest result = sendDataGet(url, ResultDirectionRequest.class);

		return result;
	}

	public ResultDirectionRequest requestWayToGoToDestiny(LatLng origin, LatLng destination, List<LatLng> latLngs) throws HttpConnectionException {
		List<String> wayPoints = toListString(latLngs);
		String url = generateURLDirection(latLngToString(origin), latLngToString(destination),wayPoints);
		ResultDirectionRequest result = sendDataGet(url, ResultDirectionRequest.class);

		return result;
	}

	public ResultDirectionRequest requestWayToGoToDestiny(String origin, String destination) throws HttpConnectionException { 
		ResultDirectionRequest result = requestWayToGoToDestiny(origin,destination, null);
		return result;
	}
	
	public ResultDirectionRequest requestWayToGoToDestiny(LatLng origin, String destination ) throws HttpConnectionException {
		ResultDirectionRequest result = requestWayToGoToDestiny(origin,destination, null);
		return result;
	}

	public ResultDirectionRequest requestWayToGoToDestiny(LatLng origin, LatLng destination ) throws HttpConnectionException {
		ResultDirectionRequest result = requestWayToGoToDestiny(origin,destination, null);
		return result;
	}

	public ResultDirectionRequest requestWayToGoToDestiny(String origin, String destination, List<LatLng> latLngs) throws HttpConnectionException {
		List<String> wayPoints = toListString(latLngs);
		String url = generateURLDirection(prepareDestinationString(origin), prepareDestinationString(destination), wayPoints);
		ResultDirectionRequest result = sendDataGet(url, ResultDirectionRequest.class);

		return result;
	}

	private List<String> toListString(List<LatLng> latLngs) {
		List<String> wayPoints = new ArrayList<String>();
		if(latLngs == null)
			return wayPoints;
		
		for(LatLng latLng : latLngs) {
			String wayPoint = latLngToString(latLng);
			wayPoints.add(wayPoint);
		}
		return wayPoints;
	}

	private String prepareDestinationString(String destination) {
		String destionationResult = destination.trim().replace(" ", "+");
		return destionationResult;
	}


	private String generateURLDirection(String positionOrigin, String destination, List<String> wayPoints) {
		StringBuilder stringBuilder = new StringBuilder();

		stringBuilder.append("origin=" + positionOrigin);

		stringBuilder.append("&");
		stringBuilder.append("destination=" + destination);

		stringBuilder.append("&");
		stringBuilder.append("sensor=true");

		if(wayPoints != null && !wayPoints.isEmpty()) {
			stringBuilder.append("&");
			String pipe = encodeUTF8("|");
			stringBuilder.append("waypoints=optimize:true");
			for(String wayPoint : wayPoints) {
				stringBuilder.append(pipe + wayPoint);	
			}
		}

		stringBuilder.append("&");
		stringBuilder.append("unit=metric");

		String url =  stringBuilder.toString();
		String novaURL = URL_DIRECTIONS_API.concat(url);
		Log.i("URL FINAL", "url: " + novaURL);
		return novaURL;
	}

	private String latLngToString(LatLng latLng) {
		String lat = String.valueOf(latLng.latitude);
		String lng = String.valueOf(latLng.longitude);

		return lat.concat(",").concat(lng);
	}

	private String encodeUTF8(String url) {

		String result = null;

		try { 
			result = URLEncoder.encode(url, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return result;
	}

}
