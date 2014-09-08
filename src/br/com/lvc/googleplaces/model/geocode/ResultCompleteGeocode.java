package br.com.lvc.googleplaces.model.geocode;

import java.io.Serializable;
import java.util.List;

public class ResultCompleteGeocode implements Serializable  {

	 
	private static final long serialVersionUID = 1401587268774606206L;
	private List<ResultRequestGeocode> results;
	 private String status;
	 
	 public ResultCompleteGeocode() {
	}

	public List<ResultRequestGeocode> getResults() {
		return results;
	}

	public void setResults(List<ResultRequestGeocode> results) {
		this.results = results;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	 
	 
	 
}
