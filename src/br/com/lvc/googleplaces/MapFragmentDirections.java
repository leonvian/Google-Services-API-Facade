package br.com.lvc.googleplaces;

import java.util.List;

import android.graphics.Color;
import android.util.Log;
import br.com.lvc.googleplaces.connection.WebServicePlace;
import br.com.lvc.googleplaces.model.direction.PassoDetalhado;
import br.com.lvc.googleplaces.model.direction.PassoGeral;
import br.com.lvc.googleplaces.model.direction.ResultDirectionRequest;
import br.com.lvc.googleplaces.model.direction.Rota;
import br.com.lvc.utility.exceptions.AndroidAppException;
import br.com.lvc.utility.taskcontrol.SimpleTask;
import br.com.lvc.utility.taskcontrol.TaskResult;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolylineOptions;

public class MapFragmentDirections extends BaseFragmentActivityMap {
	
	private static final LatLng PRACA_SETE = new LatLng(-19.918969, -43.938768); // Coração de Belo Horizonte
	private static final LatLng PRACA_TIRADENTES = new LatLng(-19.929762, -43.931773);
	private static final LatLng CONTORNO = new LatLng(-19.922621, -43.919113);
 
	@Override
	protected int layoutID() {
		return R.layout.teste_layout;
	}
	
	@Override
	public void newLocationHasCapture(LatLng location) {
		tracarRota(location);
	}

	public void tracarRota(final LatLng latLng) {
		
		SimpleTask simpleTask = new SimpleTask() {
			
			ResultDirectionRequest resultadoRequisicao = null;

			@Override
			public TaskResult executeTask() throws AndroidAppException {
				TaskResult taskResult = new TaskResult();
				resultadoRequisicao = WebServicePlace.getInstance().requestWayToGoDestiny(latLng, "Praça sete");
				return taskResult;
			}
			
			@Override
			public void processAfterTask(TaskResult taskResult) {
				List<Rota> rotas = resultadoRequisicao.getRotas();
				
				for(Rota rota : rotas) {
					
					
					List<PassoGeral> passosGerais = rota.getPassosGerais();

					Log.i("PASSO GERAL = Legs", "Quantidade de passos gerais: " + passosGerais.size());
					for(PassoGeral passoGeral : passosGerais) {
						LatLng posicaoFinal =  passoGeral.getPosicaoFinal().gerarLatLng();

						map.addMarker(criarMarkerInicial(passoGeral));
						map.addMarker(criarMarkerFinal(passoGeral));
						map.moveCamera(CameraUpdateFactory.newLatLngZoom(posicaoFinal, 15));	

					 	List<PassoDetalhado> passosDetalhados = passoGeral.getPassosDetalhados();
						for(PassoDetalhado passoDetalhado : passosDetalhados) {
							
							map.addMarker(criarMarkerMicroPassoInicial(passoDetalhado));
							map.addMarker(criarMarkerMicroPassoFinal(passoDetalhado));
						}  
						
					}

					List<LatLng> latLngs  = rota.getOverviewPolyline().pointToPositions();
					PolylineOptions polylineOptions = criarPolyLineOptions();
					polylineOptions.addAll(latLngs);

					map.addPolyline(polylineOptions);
				}				
			}
			
			private PolylineOptions criarPolyLineOptions() {
				PolylineOptions polylineOptions = new PolylineOptions();
				polylineOptions.width(3);
				polylineOptions.color(Color.BLACK);
				
				return polylineOptions;
			}

			 
			@Override
			public void processAfterFailTask(AndroidAppException e) { 
				
			}
		};

		executeTask(simpleTask);
	}
	 
	
	protected MarkerOptions criarMarkerMicroPassoFinal(PassoDetalhado passoDetalhado) {
		MarkerOptions markerOptions = criarMarkerMicroPasso(passoDetalhado, false);
		return markerOptions;
	}

	protected MarkerOptions criarMarkerMicroPassoInicial(PassoDetalhado passoDetalhado) {
		MarkerOptions markerOptions = criarMarkerMicroPasso(passoDetalhado, true);
		return markerOptions;
	}

	private MarkerOptions criarMarkerMicroPasso(PassoDetalhado passoDetalhado, boolean inicial) {
		LatLng position = null;
		if(inicial)
			position = passoDetalhado.getPosicaoInicial().gerarLatLng();
		else
			position = passoDetalhado.getPosicaoFinal().gerarLatLng();
		
		MarkerOptions markerOptions = getSimpleMarkerOptions(position);
		String titulo = passoDetalhado.getDicaDirecao();
		markerOptions.title(titulo);
		
		String snippet = getString(R.string.dur).concat(passoDetalhado.getDuracao().getText()).concat(" ").concat(getString(R.string.dist).concat(passoDetalhado.getDistancia().getText()));
		
		markerOptions.snippet(snippet);
		markerOptions.icon(BitmapDescriptorFactory.fromResource(R.drawable.marker_micro));

		return markerOptions;
	}
	
	protected MarkerOptions criarMarkerInicial(PassoGeral passoGeral) {
		MarkerOptions markerOptions = criarMarkerMacroPasso(passoGeral, true);
		return markerOptions;
	}

	protected MarkerOptions criarMarkerFinal(PassoGeral passoGeral) {
		MarkerOptions markerOptions = criarMarkerMacroPasso(passoGeral, false);

		return markerOptions;
	}
	
	private MarkerOptions criarMarkerMacroPasso(PassoGeral passoGeral, boolean inicial) {
		LatLng position = null;
		String endereco = null;
		BitmapDescriptor bitmapDescriptor = null;
				
		if(inicial) {
			endereco = passoGeral.getEnderecoInicial();
			position = passoGeral.getPosicaoInicial().gerarLatLng();
			bitmapDescriptor = BitmapDescriptorFactory.fromResource(R.drawable.os_new);
		} else {
			endereco = passoGeral.getEnderecoFinal();
			position = passoGeral.getPosicaoFinal().gerarLatLng();
			bitmapDescriptor = BitmapDescriptorFactory.fromResource(R.drawable.os_incomplete);
		}
		
		
		MarkerOptions markerOptions = getSimpleMarkerOptions(position);
		markerOptions.title(endereco);
		
		markerOptions.icon(bitmapDescriptor);

		return markerOptions;
	}

}