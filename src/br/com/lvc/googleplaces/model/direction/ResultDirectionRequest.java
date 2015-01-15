package br.com.lvc.googleplaces.model.direction;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ResultDirectionRequest {

	
	@JsonProperty("routes")
	private List<Rota> rotas;
	private String status;
	
	public ResultDirectionRequest() {
	}
	
	
	public List<Rota> getRotas() {
		return rotas;
	}
	
	public void setRotas(List<Rota> rotas) {
		this.rotas = rotas;
	}

	
	public String getStatus() {
		return status;
	}
	
	public void setStatus(String status) {
		this.status = status;
	}

}
