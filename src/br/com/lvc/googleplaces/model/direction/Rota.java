package br.com.lvc.googleplaces.model.direction;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Rota implements Serializable {


	 
	private static final long serialVersionUID = 2923948159659493474L;
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
	
	@JsonProperty("waypoint_order")
	private List<Integer> waypointOrder;

	public Rota() {
	}
	
	public List<Integer> getWaypointOrder() {
		return waypointOrder;
	}
	
	public void setWaypointOrder(List<Integer> waypointOrder) {
		this.waypointOrder = waypointOrder;
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