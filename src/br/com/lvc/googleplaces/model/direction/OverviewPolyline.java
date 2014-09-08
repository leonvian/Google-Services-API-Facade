package br.com.lvc.googleplaces.model.direction;

import java.io.Serializable;
import java.util.List;

import br.com.lvc.googleplaces.utils.PolyDecoder;

import com.google.android.gms.maps.model.LatLng;

public class OverviewPolyline implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3660035078215303743L;
	private String points;

	public OverviewPolyline() {
	}
	
	public String getPoints() {
		return points;
	}
	
	public void setPoints(String points) {
		this.points = points;
	}
	
	public List<LatLng> pointToPositions() {
		List<LatLng> positions = PolyDecoder.decodePoly(points);
		return positions;
	}

}
