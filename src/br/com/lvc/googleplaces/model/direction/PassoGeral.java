package br.com.lvc.googleplaces.model.direction;

import java.util.List;

import org.codehaus.jackson.annotate.JsonProperty;

import br.com.lvc.googleplaces.model.Localizacao;

public class PassoGeral {

	
	/*
	 * end_address: "Avenida Brasil, 1334 - Regi‹o da Nossa Senhora da Boa, Belo Horizonte - Minas Gerais, 30140-003, Brazil"
	 */

	@JsonProperty("distance")
	private Distancia distancia;
	
	@JsonProperty("duration")
	private Duracao duracao;
	
	@JsonProperty("end_address")
	private String enderecoFinal;
	
	@JsonProperty("start_address")
	private String enderecoInicial; 
	
	@JsonProperty("end_location")
	private Localizacao posicaoFinal;
	
	@JsonProperty("start_location")
	private Localizacao posicaoInicial; 
	
	@JsonProperty("steps")
	private List<PassoDetalhado> passosDetalhados;
	
	public PassoGeral() {
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

	public String getEnderecoFinal() {
		return enderecoFinal;
	}

	public void setEnderecoFinal(String enderecoFinal) {
		this.enderecoFinal = enderecoFinal;
	}

	public String getEnderecoInicial() {
		return enderecoInicial;
	}

	public void setEnderecoInicial(String enderecoInicial) {
		this.enderecoInicial = enderecoInicial;
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

	public List<PassoDetalhado> getPassosDetalhados() {
		return passosDetalhados;
	}

	public void setPassosDetalhados(List<PassoDetalhado> passosDetalhados) {
		this.passosDetalhados = passosDetalhados;
	}
	
	
	
	 
}
