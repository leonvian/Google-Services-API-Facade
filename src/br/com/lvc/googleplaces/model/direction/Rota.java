package br.com.lvc.googleplaces.model.direction;

import java.util.List;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonProperty;

public class Rota {


	private static final String BROKE_LINE = "<BR />";
	private static final String TRACE = " - ";

	@JsonProperty("legs")
	private List<PassoGeral> passosGerais;

	@JsonProperty("summary")
	private String resumo;

	@JsonProperty("warnings")
	private List<String> avisos;

	@JsonProperty("overview_polyline")
	private OverviewPolyline overviewPolyline; 

	public Rota() {

	}

	public List<PassoGeral> getPassosGerais() {
		return passosGerais;
	}

	public void setPassosGerais(List<PassoGeral> passosGerais) {
		this.passosGerais = passosGerais;
	}

	public String getResumo() {
		return resumo;
	}

	public void setResumo(String resumo) {
		this.resumo = resumo;
	}


	public List<String> getAvisos() {
		return avisos;
	}

	public void setAvisos(List<String> avisos) {
		this.avisos = avisos;
	}

	public OverviewPolyline getOverviewPolyline() {
		return overviewPolyline;
	}

	public void setOverviewPolyline(OverviewPolyline overviewPolyline) {
		this.overviewPolyline = overviewPolyline;
	}


	@JsonIgnore
	public boolean isExistWarnings() {
		if(avisos == null || avisos.isEmpty()) 
			return false;
		else
			return true;
	}

	public String overviewWarnings() {
		StringBuilder stringBuilder = new StringBuilder();
		if(avisos != null || !avisos.isEmpty()) {
			for(String aviso : avisos) {
				stringBuilder.append(BROKE_LINE);
				stringBuilder.append(TRACE);
				stringBuilder.append(aviso);
			}
		} else {
			stringBuilder.append(BROKE_LINE);
			stringBuilder.append(TRACE);
			stringBuilder.append(BROKE_LINE);
		}

		String overview = stringBuilder.toString();
		return overview;
	}

	public String overviewRoute(String durationLabel, String distanceLabel) {
		StringBuilder stringBuilder = new StringBuilder();

		for(PassoGeral passoGeral : passosGerais) {
			stringBuilder.append(BROKE_LINE);
			stringBuilder.append(passoGeral.getOverViewStep(durationLabel, distanceLabel));
			stringBuilder.append(BROKE_LINE);
		}

		String overview = stringBuilder.toString();
		return overview;
	}
	
	public String overviewRoute() {
		return overviewRoute(null, null);
	}

	@Override
	public String toString() {
		return overviewRoute();
	}
}