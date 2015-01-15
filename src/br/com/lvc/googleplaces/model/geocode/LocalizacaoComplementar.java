package br.com.lvc.googleplaces.model.geocode;

import java.io.Serializable;

import br.com.lvc.googleplaces.model.Localizacao;

import com.fasterxml.jackson.annotation.JsonProperty;

public class LocalizacaoComplementar implements Serializable  {

 
	private static final long serialVersionUID = -4065080334252012333L;

/*
  * "" : {
               "northeast" : {
                  "lat" : 37.42426708029149,
                  "lng" : -122.0840722197085
               },
               "southwest" : {
                  "lat" : 37.42156911970850,
                  "lng" : -122.0867701802915
               }
            }
  */
	
	@JsonProperty("northeast")
	private Localizacao localizacaoNorte;
	
	@JsonProperty("southwest")
	private Localizacao localizacaoSul;
	
	public LocalizacaoComplementar() {
	}

	public Localizacao getLocalizacaoNorte() {
		return localizacaoNorte;
	}

	public void setLocalizacaoNorte(Localizacao localizacaoNorte) {
		this.localizacaoNorte = localizacaoNorte;
	}

	public Localizacao getLocalizacaoSul() {
		return localizacaoSul;
	}

	public void setLocalizacaoSul(Localizacao localizacaoSul) {
		this.localizacaoSul = localizacaoSul;
	}
	
	
	

}
