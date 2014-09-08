package br.com.lvc.googleplaces.model.place;

import java.io.Serializable;

import br.com.lvc.googleplaces.model.Localizacao;

public class Geometria implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8258303584010024022L;
	private Localizacao location;
	
	public Geometria() {
	
	}

	public Localizacao getLocation() {
		return location;
	}

	public void setLocation(Localizacao location) {
		this.location = location;
	}
	
	

}
