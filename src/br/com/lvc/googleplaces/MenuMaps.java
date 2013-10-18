package br.com.lvc.googleplaces;

import android.os.Bundle;
import android.view.View;
import br.com.lvc.utility.screen.BaseActivity;

public class MenuMaps extends BaseActivity {


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.menu_map);
	}

	public void  onClickPlaces(View view) {
		goToNextScreen(MapFragmentPlace.class);
	}

	public void  onClickDirection(View view) {
		goToNextScreen(MapFragmentDirections.class);
	}


}
