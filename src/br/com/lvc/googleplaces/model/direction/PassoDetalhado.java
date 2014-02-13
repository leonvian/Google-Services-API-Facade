package br.com.lvc.googleplaces.model.direction;

import org.codehaus.jackson.annotate.JsonProperty;

import android.text.Html;
import br.com.lvc.googleplaces.model.Localizacao;

public class PassoDetalhado  {
	
	@JsonProperty("distance")
	private Distancia distancia;
	
	@JsonProperty("duration")
	private Duracao duracao;

	@JsonProperty("travel_mode")
	 private String tipoViagem;
	
	@JsonProperty("end_location")
	private Localizacao posicaoFinal;
	
	@JsonProperty("html_instructions")
	private String instrucoes;
	
	@JsonProperty("start_location")
	private Localizacao posicaoInicial; 
	
	private OverviewPolyline polyline;
	
	@JsonProperty("maneuver") // Ex: turn-right
	private String dicaDirecao;
	
	public PassoDetalhado() {
	
	}
	
	public String getInstrucoes() {
		return instrucoes;
	}
	
	public void setInstrucoes(String instrucoes) {
		this.instrucoes = instrucoes;
	}

	public String getTipoViagem() {
		return tipoViagem;
	}

	public void setTipoViagem(String tipoViagem) {
		this.tipoViagem = tipoViagem;
	}

	public Distancia getDistancia() {
		return distancia;
	}

	public void setDistancia(Distancia distancia) {
		this.distancia = distancia;
	}

	public Duracao getDuracao() {
		return duracao;
	}

	public void setDuracao(Duracao duracao) {
		this.duracao = duracao;
	}

	public Localizacao getPosicaoFinal() {
		return posicaoFinal;
	}

	public void setPosicaoFinal(Localizacao posicaoFinal) {
		this.posicaoFinal = posicaoFinal;
	}

	public Localizacao getPosicaoInicial() {
		return posicaoInicial;
	}

	public void setPosicaoInicial(Localizacao posicaoInicial) {
		this.posicaoInicial = posicaoInicial;
	}

	public OverviewPolyline getPolyline() {
		return polyline;
	}

	public void setPolyline(OverviewPolyline polyline) {
		this.polyline = polyline;
	}
	
	public String getDicaDirecao() {
		return dicaDirecao;
	}
	
	public void setDicaDirecao(String dicaDirecao) {
		this.dicaDirecao = dicaDirecao;
	}

	@Override
	public String toString() {
		return instrucoes;
	}
}

