package br.com.lvc.googleplaces.model.geocode;

import java.io.Serializable;

import br.com.lvc.googleplaces.model.Localizacao;

import com.fasterxml.jackson.annotation.JsonProperty;

public class LocalizacaoInfo implements Serializable  {
	
	 
	private static final long serialVersionUID = -439437324601153968L;

	@JsonProperty("location_type")
	private String tipoLocalizaoca;
	
	@JsonProperty("location")
	private Localizacao localizacao;
	
	@JsonProperty("viewport")
	private LocalizacaoComplementar localizacaoComplementar;
	
	public LocalizacaoInfo() {
	
	}

	public String getTipoLocalizaoca() {
		return tipoLocalizaoca;
	}

	public void setTipoLocalizaoca(String tipoLocalizaoca) {
		this.tipoLocalizaoca = tipoLocalizaoca;
	}

	public Localizacao getLocalizacao() {
		return localizacao;
	}

	public void setLocalizacao(Localizacao localizacao) {
		this.localizacao = localizacao;
	}

	public LocalizacaoComplementar getLocalizacaoComplementar() {
		return localizacaoComplementar;
	}

	public void setLocalizacaoComplementar(
			LocalizacaoComplementar localizacaoComplementar) {
		this.localizacaoComplementar = localizacaoComplementar;
	}
	
	
	
}
