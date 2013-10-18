package br.com.lvc.googleplaces.utils;

import java.util.ArrayList;
import java.util.List;

import com.google.android.gms.maps.model.LatLng;

public class PolyDecoder {
	
	
	public static List<LatLng> decodePoly(String encoded){

	    List<LatLng> poly = new ArrayList<LatLng>();
	    int index = 0;
	    int length = encoded.length();
	    int latitude = 0;
	    int longitude = 0;

	    while(index < length){
	        int b;
	        int shift = 0;
	        int result = 0;

	        do {
	            b = encoded.charAt(index++) - 63;
	            result |= (b & 0x1f) << shift;
	            shift += 5;
	        } while (b >= 0x20);

	        int destLat = ((result & 1) != 0 ? ~(result >> 1) : (result >> 1));
	        latitude += destLat;

	        shift = 0;
	        result = 0;
	        do {
	            b = encoded.charAt(index++) - 63;
	            result |= (b & 0x1f) << shift;
	            shift += 5;
	        } while (b >= 0x20);

	        int destLong = ((result & 1) != 0 ? ~(result >> 1) : (result >> 1));
	        longitude += destLong;

	        poly.add(new LatLng((latitude / 1E5),(longitude / 1E5) ));
	    }
	    return poly;
	}

}
