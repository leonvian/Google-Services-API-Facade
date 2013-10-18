package br.com.lvc.googleplaces.model.place;

import java.util.List;

public class ResultPlaceRequest {


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
