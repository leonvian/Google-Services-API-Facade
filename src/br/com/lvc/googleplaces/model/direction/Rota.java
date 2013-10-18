package br.com.lvc.googleplaces.model.direction;

import java.util.List;

import org.codehaus.jackson.annotate.JsonProperty;

public class Rota {

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

	
	

}
