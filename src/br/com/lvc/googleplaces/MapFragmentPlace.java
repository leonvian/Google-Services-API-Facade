package br.com.lvc.googleplaces;

import java.util.List;

import br.com.lvc.googleplaces.connection.WebServicePlace;
import br.com.lvc.googleplaces.model.place.Place;
import br.com.lvc.googleplaces.model.place.ResultPlaceRequest;
import br.com.lvc.utility.exceptions.AndroidAppException;
import br.com.lvc.utility.taskcontrol.SimpleTask;
import br.com.lvc.utility.taskcontrol.TaskResult;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapFragmentPlace extends BaseFragmentActivityMap {
	
	@Override
	public void newLocationHasCapture(LatLng latLng) {
		putPlacesInGoogleMaps(latLng);
	}

	public void putPlacesInGoogleMaps(final LatLng position) {

		SimpleTask simpleTask = new SimpleTask() { 
			
			ResultPlaceRequest resultadoPlaceRequest = null;

			@Override
			public TaskResult executeTask() throws AndroidAppException {
				TaskResult taskResult = new TaskResult();
				resultadoPlaceRequest = WebServicePlace.getInstance().requestPlacesNearMe(position, 500);
				return taskResult;
			}

			@Override
			public void processAfterTask(TaskResult taskResult) {
			   marcarLocaisDoGooglePlacesNoMapa();
			   meMarcarNoMapa();
			}
			
			private void meMarcarNoMapa() {
				map.addMarker(criarMarkerMe(position));
				map.moveCamera(CameraUpdateFactory.newLatLngZoom(position, 15));
			}
			
			private void marcarLocaisDoGooglePlacesNoMapa() {
				List<Place> places = resultadoPlaceRequest.getResults();
				for(Place place : places) {
					MarkerOptions markerOptions = criarMarkerByPlace(place);
					map.addMarker(markerOptions);
				}
			}

			@Override
			public void processAfterFailTask(AndroidAppException e) { 
				e.printStackTrace();
			}
		};

		executeTask(simpleTask);
	}

}
