package br.com.lvc.googleplaces;

import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapPuro extends FragmentActivity implements LocationListener {
	
	public static final long MINUTE = 60000;
	public static final long INTERVAL_CAPTURE_IN_MILIS = 2 * MINUTE;
	public static final long INTERVAL_PHYSICAL_SPACE_IN_METERS = 100;
	
	private static final LatLng PRACA_TIRADENTES = new LatLng(-19.929762, -43.931773);
	private GoogleMap map;
	 
	
	@Override
	protected void onCreate(Bundle arg0) {
		super.onCreate(arg0);
		setContentView(R.layout.activity_main);
		
		SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
		map = mapFragment.getMap();
		map.moveCamera(CameraUpdateFactory.newLatLngZoom(PRACA_TIRADENTES, 15));
		map.setMapType(GoogleMap.MAP_TYPE_NORMAL);
		startCaptureLocation();
	}
	
	private void startCaptureLocation() {
		LocationManager	locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
		locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, INTERVAL_CAPTURE_IN_MILIS, INTERVAL_PHYSICAL_SPACE_IN_METERS, this);
		locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, INTERVAL_CAPTURE_IN_MILIS, INTERVAL_PHYSICAL_SPACE_IN_METERS, this);
	}
	
	@Override
	protected void onDestroy() {
		super.onDestroy();
		LocationManager	locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
		locationManager.removeUpdates(this);
	}

	@Override
	public void onLocationChanged(Location location) {
		MarkerOptions markerOptions = new MarkerOptions();
		LatLng latLng = new LatLng(location.getLatitude(), location.getLongitude());
		markerOptions.position(latLng);
		markerOptions.title("Eu");
		markerOptions.icon(BitmapDescriptorFactory.fromResource(R.drawable.boneco));
		
		map.addMarker(markerOptions);
		map.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, 15));
		
	}

	@Override
	public void onProviderDisabled(String provider) {
	}

	@Override
	public void onProviderEnabled(String provider) {
	}

	@Override
	public void onStatusChanged(String provider, int status, Bundle extras) {
	}
}