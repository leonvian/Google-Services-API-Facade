package br.com.lvc.googleplaces.model.place;

import java.io.Serializable;
import java.util.List;

public class ResultPlaceRequest implements Serializable {


	/**
	 * 
	 */
	private static final long serialVersionUID = -602267970595049296L;
	private List<Place> results;

	public ResultPlaceRequest() {

	}

	public List<Place> getResults() {
		return results;
	}

	public void setResults(List<Place> results) {
		this.results = results;
	}
}
