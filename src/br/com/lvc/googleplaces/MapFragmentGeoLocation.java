package br.com.lvc.googleplaces;

import java.util.List;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import br.com.lvc.googleplaces.connection.WebServicePlace;
import br.com.lvc.googleplaces.model.Localizacao;
import br.com.lvc.googleplaces.model.geocode.Endereco;
import br.com.lvc.googleplaces.model.geocode.LocalizacaoInfo;
import br.com.lvc.googleplaces.model.geocode.ResultCompleteGeocode;
import br.com.lvc.googleplaces.model.geocode.ResultRequestGeocode;
import br.com.lvc.utility.exceptions.AndroidAppException;
import br.com.lvc.utility.taskcontrol.SimpleTask;
import br.com.lvc.utility.taskcontrol.TaskResult;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.model.LatLng;

public class MapFragmentGeoLocation extends BaseFragmentActivityMap {

	private EditText editTextPesquisar;

	@Override
	protected int layoutID() {
		return R.layout.teste_layout;
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		editTextPesquisar = (EditText)findViewById(R.id.edit_text_pesquisar);
		editTextPesquisar.setText("MG Santa Luzia Esplanada Irm‹os Kennedy");
	}

	@Override
	public void newLocationHasCapture(LatLng location) {

	} 

	public void onClickPesquisar(View view) {


		SimpleTask simpleTask = new SimpleTask() {

			ResultCompleteGeocode resultCompleteGeocode = null;

			@Override
			public TaskResult executeTask() throws AndroidAppException {
				TaskResult taskResult = new TaskResult();
				String address = editTextPesquisar.getText().toString();
				resultCompleteGeocode =  WebServicePlace.getInstance().requestLocationByAddress(address);
				return taskResult;
			}

			@Override
			public void processAfterTask(TaskResult taskResult) {
				 List<ResultRequestGeocode> results = resultCompleteGeocode.getResults();
				 for(ResultRequestGeocode reGeocode : results) {
					 LocalizacaoInfo localizacaoInfo = reGeocode.getLocalizacaoInfo();
					 Localizacao localizacao = localizacaoInfo.getLocalizacao();
					 LatLng latLng = localizacao.gerarLatLng();
					 map.addMarker(criarMarkerMe(latLng));
					 map.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, 15));	
					 
					 
					  List<Endereco> enderecos = reGeocode.getEnderecos();
					  Log.i("ENDERECO", "Quantidade de enderecos recuperados: " + enderecos.size());
					  for(Endereco endereco : enderecos) {
						  
					  }
				 }

			}

			@Override
			public void processAfterFailTask(AndroidAppException e) {
				// TODO Auto-generated method stub

			}

		};

		executeTask(simpleTask);
	}


}
