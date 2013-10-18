package br.com.lvc.googleplaces.model.direction;

import java.util.List;

import br.com.lvc.googleplaces.utils.PolyDecoder;

import com.google.android.gms.maps.model.LatLng;

public class OverviewPolyline {

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
