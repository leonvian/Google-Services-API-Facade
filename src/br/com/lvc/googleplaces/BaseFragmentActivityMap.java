package br.com.lvc.googleplaces;

import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import br.com.lvc.googleplaces.model.Localizacao;
import br.com.lvc.googleplaces.model.place.Place;
import br.com.lvc.utility.screen.BaseFragmentActivity;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public abstract class BaseFragmentActivityMap extends BaseFragmentActivity implements LocationListener {

	public static final LatLng PRACA_SETE = new LatLng(-19.918969, -43.938768); 

	public static final long MINUTE = 60000;
	public static final long INTERVAL_CAPTURE_IN_MILIS = 2 * MINUTE;
	public static final long INTERVAL_PHYSICAL_SPACE_IN_METERS = 100;

	protected GoogleMap map = null; 

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(layoutID());

		SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
		map = mapFragment.getMap();

		if(enableGPS())
			startCaptureLocation();

	}

	protected boolean enableGPS() {
		return true;
	}

	protected int layoutID() {
		return R.layout.activity_main;
	}

	private void startCaptureLocation() {
		LocationManager	locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
		locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, INTERVAL_CAPTURE_IN_MILIS, INTERVAL_PHYSICAL_SPACE_IN_METERS, this);
		locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, INTERVAL_CAPTURE_IN_MILIS, INTERVAL_PHYSICAL_SPACE_IN_METERS, this);
	}
  
	protected MarkerOptions criarMarkerMe(LatLng position) {
		MarkerOptions markerOptions = getSimpleMarkerOptions(position);
		String titulo = "Eu";
		markerOptions.title(titulo);
		markerOptions.icon(BitmapDescriptorFactory.fromResource(R.drawable.boneco));

		return markerOptions;
	}

	protected MarkerOptions criarMarkerByPlace(Place place) {
		Localizacao location = place.getGeometry().getLocation();
		LatLng position = new LatLng(location.getLat(), location.getLng());
		MarkerOptions markerOptions = getSimpleMarkerOptions(position);
		String titulo = place.getName();
		markerOptions.title(titulo);

		return markerOptions;
	}

	protected MarkerOptions getSimpleMarkerOptions(LatLng position) {
		MarkerOptions markerOptions = new MarkerOptions();
		markerOptions.position(position);
		return markerOptions;
	}

	public abstract void newLocationHasCapture(LatLng location);

	@Override
	public void onLocationChanged(Location location) {
		LatLng latLng = new LatLng(location.getLatitude(), location.getLongitude());
		newLocationHasCapture(latLng);
	}

	@Override
	public void onProviderDisabled(String provider) { }

	@Override
	public void onProviderEnabled(String provider) { }

	@Override
	public void onStatusChanged(String provider, int status, Bundle extras) { }

}