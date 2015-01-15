package br.com.lvc.googleplaces.facade;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import android.content.Context;
import android.util.Log;
import br.com.lvc.googleplaces.connection.DataSerializer;
import br.com.lvc.googleplaces.connection.HttpConnectionException;
import br.com.lvc.googleplaces.connection.WebServiceComun;
import br.com.lvc.googleplaces.model.direction.ResultDirectionRequest;
import br.com.lvc.googleplaces.model.geocode.ResultCompleteGeocode;
import br.com.lvc.googleplaces.model.place.ResultPlaceRequest;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.google.android.gms.maps.model.LatLng;


/**
 * 
 * Facade to connect with Google Services API.
 * 
 * https://developers.google.com/maps/documentation/directions/
 * 
 * @author Leonardo Casasanta
 *
 */
public class GoogleServicesFacade /*extends WebServiceComun */ {

	public static final String URL_PLACES_API = "https://maps.googleapis.com/maps/api/place/nearbysearch/json?"; // Lista locais pertinentes pr�ximos a uma localiza��o
	public static final String URL_DIRECTIONS_API = "https://maps.googleapis.com/maps/api/directions/json?"; // Consegue tra�ar rotas entre varios pontos	 
	public static final String URL_GEOCODE_API = "http://maps.googleapis.com/maps/api/geocode/json?"; // Permite saber uma localiza��o com base em um endere�o

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
	public void requestLocationByAddress(Context context, String address, String region, HttpResponseListener<ResultCompleteGeocode> responseListener)  {
		String preparedAddres = prepareDestinationString(address);
		String url = URL_GEOCODE_API.concat("address=" + preparedAddres + "&sensor=true&region=" + region);  
		sendDataGet(context, url, ResultCompleteGeocode.class,responseListener);

	}

	public void requestPlacesNearLocation(Context context, LatLng positionTarget, int radius, String key, HttpResponseListener<ResultPlaceRequest> responseListener)  {
		String latLngString = latLngToString(positionTarget);
		String url = URL_PLACES_API.concat("location=" + latLngString +"&radius="+ radius +"&sensor=true&key=" + key);
		sendDataGet(context, url, ResultPlaceRequest.class, responseListener);
	}

	public void requestWayToGoToDestiny(Context context, String origin, String destination, HttpResponseListener<ResultDirectionRequest> httpResponseListener) { 
		requestWayToGoToDestiny(context,origin,destination, null, null,httpResponseListener);
	}

	public void requestWayToGoToDestiny(Context context, LatLng origin, String destination,HttpResponseListener<ResultDirectionRequest> httpResponseListener )   {
		requestWayToGoToDestiny(context,origin,destination, null, null,httpResponseListener);
	}

	public void requestWayToGoToDestiny(Context context, LatLng origin, LatLng destination,HttpResponseListener<ResultDirectionRequest> httpResponseListener ) {
		requestWayToGoToDestiny(context,origin,destination, null, null,httpResponseListener);
	}

	public void requestWayToGoToDestiny(Context context,String origin, String destination, List<LatLng> latLngs,HttpResponseListener<ResultDirectionRequest> httpResponseListener) {
		requestWayToGoToDestiny(context,origin,destination, latLngs,null,httpResponseListener);
	}

	public void requestWayToGoToDestiny(Context context,String origin, String destination,HashMap<String, String> optionalParameters,HttpResponseListener<ResultDirectionRequest> httpResponseListener)  { 
		requestWayToGoToDestiny(context,origin,destination, null, optionalParameters,httpResponseListener);
	}

	public void requestWayToGoToDestiny(Context context,LatLng origin, String destination,HashMap<String, String> optionalParameters,HttpResponseListener<ResultDirectionRequest> httpResponseListener)  {
		requestWayToGoToDestiny(context,origin,destination, null, optionalParameters,httpResponseListener); 
	}

	public void requestWayToGoToDestiny(Context context,LatLng origin, LatLng destination,HashMap<String, String> optionalParameters,HttpResponseListener<ResultDirectionRequest> httpResponseListener)  {
		requestWayToGoToDestiny(context,origin,destination, null, optionalParameters,httpResponseListener);

	}

	public void requestWayToGoToDestiny(Context context, LatLng origin, String destination, List<LatLng> latLngs, HttpResponseListener<ResultDirectionRequest> responseListener) {
		List<String> wayPoints = toListString(latLngs);
		String url = generateURLDirection(latLngToString(origin), prepareDestinationString(destination),wayPoints, null);
		sendDataGet(context, url, ResultDirectionRequest.class, responseListener);		
	}

	public void requestWayToGoToDestiny(Context context, LatLng origin, LatLng destination, List<LatLng> latLngs, HttpResponseListener<ResultDirectionRequest> httpResponseListener) throws HttpConnectionException {
		List<String> wayPoints = toListString(latLngs);
		String url = generateURLDirection(latLngToString(origin), latLngToString(destination),wayPoints, null);
		sendDataGet(context, url, ResultDirectionRequest.class,httpResponseListener);
	}

	public void requestWayToGoToDestiny(Context context, LatLng origin, String destination, List<LatLng> latLngs, HashMap<String, String> optionalParameters, HttpResponseListener<ResultDirectionRequest> httpListener) {
		List<String> wayPoints = toListString(latLngs);
		String url = generateURLDirection(latLngToString(origin), prepareDestinationString(destination),wayPoints, optionalParameters);
		sendDataGet(context, url, ResultDirectionRequest.class, httpListener);

	}

	public void requestWayToGoToDestiny(Context context, LatLng origin, LatLng destination, List<LatLng> latLngs, HashMap<String, String> optionalParameters,  HttpResponseListener<ResultDirectionRequest> httpListener)   {
		List<String> wayPoints = toListString(latLngs);
		String url = generateURLDirection(latLngToString(origin), latLngToString(destination),wayPoints, optionalParameters);
		sendDataGet(context, url, ResultDirectionRequest.class, httpListener);
	}

	public void requestWayToGoToDestiny(Context context, String origin, String destination, List<LatLng> latLngs, HashMap<String, String> optionalParameters, HttpResponseListener<ResultDirectionRequest> httpResponseListener)   {
		List<String> wayPoints = toListString(latLngs);
		String url = generateURLDirection(prepareDestinationString(origin), prepareDestinationString(destination), wayPoints, optionalParameters);
		sendDataGet(context, url, ResultDirectionRequest.class, httpResponseListener);		
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


	private String generateURLDirection(String positionOrigin, String destination, List<String> wayPoints, HashMap<String, String> optionalParameters) {
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

		if(optionalParameters != null) {
			Set<String> parametersName = optionalParameters.keySet();
			for(String parameterName : parametersName) {
				stringBuilder.append("&");
				stringBuilder.append(parameterName);
				stringBuilder.append("=");
				String parameterKey = optionalParameters.get(parameterName);
				stringBuilder.append(parameterKey);
			}
		}


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


	private <T>void sendDataGet(Context context, String url, final Class<T> target, final HttpResponseListener<T> responseListener) {
		RequestQueue queue =  HttpQueueController.getInstance().getQueue(context);
		Listener<String>  sucessListener = new Response.Listener<String>() {

			@Override
			public void onResponse(String reponse) { 
					T data = DataSerializer.getInstance().toObject(reponse, target);
					responseListener.sucess(data); 
			}
		};

		ErrorListener errorListener = new Response.ErrorListener() {

			@Override
			public void onErrorResponse(VolleyError error) {
				responseListener.error(error.getMessage());
			}
		};

		StringRequestGET stringRequestGET = new StringRequestGET(url, sucessListener, errorListener);
		queue.add(stringRequestGET);

	}

	private class StringRequestGET extends StringRequest {

		public StringRequestGET(String url, Listener<String> listener, ErrorListener errorListener) {
			super(Request.Method.GET, url, listener, errorListener); 
		}

		@Override
		protected Map<String, String> getParams() throws AuthFailureError {
			return super.getParams();
		}

		@Override
		public Map<String, String> getHeaders() throws AuthFailureError {
			Map<String, String> headers = new HashMap<String, String>(); 
			return headers;
		}

	}

}
