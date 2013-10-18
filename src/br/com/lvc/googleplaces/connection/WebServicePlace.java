package br.com.lvc.googleplaces.connection;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import android.util.Log;
import br.com.lvc.googleplaces.Key;
import br.com.lvc.googleplaces.model.direction.ResultDirectionRequest;
import br.com.lvc.googleplaces.model.geocode.ResultCompleteGeocode;
import br.com.lvc.googleplaces.model.place.ResultPlaceRequest;
import br.com.lvc.utility.connection.HttpConnectionException;
import br.com.lvc.utility.connection.WebServiceComun;

import com.google.android.gms.maps.model.LatLng;

public class WebServicePlace extends WebServiceComun {
	
	//String URL = 
	//
	public static final String URL_PLACES_API = "https://maps.googleapis.com/maps/api/place/nearbysearch/json?"; // Lista locais pertinentes próximos a uma localização
	public static final String URL_DIRECTIONS_API = "https://maps.googleapis.com/maps/api/directions/json?"; // Consegue traçar rotas entre varios pontos

	//	address=1600+Amphitheatre+Parkway,+Mountain+View,+CA&sensor=true_or_false
	public static final String URL_GEOCODE_API = "http://maps.googleapis.com/maps/api/geocode/json?"; // Permite saber uma localização com base em um endereço
	
	private static WebServicePlace instance;
	
	public static WebServicePlace getInstance() {
		if(instance == null)
			instance = new WebServicePlace();
		return instance;
	}
	
	public ResultCompleteGeocode requestLocationByAddress(String address) throws HttpConnectionException {
		String preparedAddres = prepareDestinationString(address);
		String url = URL_GEOCODE_API.concat("address=" + preparedAddres + "&sensor=true&region=br");
		ResultCompleteGeocode result = sendDataGet(url, ResultCompleteGeocode.class);
		return result;
	}
	 
	public ResultPlaceRequest requestPlacesNearMe(LatLng positionTarget, int radius) throws HttpConnectionException {
		String latLngString = latLngToString(positionTarget);
		String url = URL_PLACES_API.concat("location=" + latLngString +"&radius="+ radius +"&sensor=true&key=" + Key.WEB_APP_KEY);
		ResultPlaceRequest result = sendDataGet(url, ResultPlaceRequest.class);
		return result;
	}
	 
	
	public ResultDirectionRequest requestWayToGoDestiny(LatLng origin, String destination) throws HttpConnectionException {
		//praca+sete+belo+horizonte,MG
		String destinationPrepareted = prepareDestinationString(destination);
		String url = generateURLDirection(origin, destinationPrepareted);
		ResultDirectionRequest result = sendDataGet(url, ResultDirectionRequest.class);
		
		return result;
	}
	
	private String prepareDestinationString(String destination) {
		String destionationResult = destination.trim().replace(" ", "+");
		return destionationResult;
	}
	
	
	private String generateURLDirection(LatLng postionOrigin, String destination) {
		StringBuilder stringBuilder = new StringBuilder();
		
		stringBuilder.append("origin=" + latLngToString(postionOrigin));

		stringBuilder.append("&");
		stringBuilder.append("destination=" + destination);

		stringBuilder.append("&");
		stringBuilder.append("sensor=false");
	 
		stringBuilder.append("&");
		String pipe = encodeUTF8("|");
		stringBuilder.append("waypoints=optimize:true"+pipe+"Concórdia+Belo+Horizonte,MG"+pipe+"Aparecida+Belo+Horizonte,MG"); 
		 

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
