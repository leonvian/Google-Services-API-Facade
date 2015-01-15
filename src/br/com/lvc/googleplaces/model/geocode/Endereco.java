package br.com.lvc.googleplaces.model.geocode;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Endereco implements Serializable  {

	
	private static final long serialVersionUID = 7531332176742227402L;

	/*
	 * "long_name" : "1600",
               "short_name" : "1600",
               "types" : [ "street_number" ]
	 */

	@JsonProperty("long_name")
	private String nomeLongo;
	
	@JsonProperty("short_name")
	private String nomeCurto;
	
	@JsonProperty("types")
	private List<String> tipos;

	public Endereco() {

	}

	public String getNomeLongo() {
		return nomeLongo;
	}

	public void setNomeLongo(String nomeLongo) {
		this.nomeLongo = nomeLongo;
	}

	public String getNomeCurto() {
		return nomeCurto;
	}

	public void setNomeCurto(String nomeCurto) {
		this.nomeCurto = nomeCurto;
	}

	public List<String> getTipos() {
		return tipos;
	}

	public void setTipos(List<String> tipos) {
		this.tipos = tipos;
	}



}
