package br.com.lvc.googleplaces.model.geocode;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ResultRequestGeocode implements Serializable  {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5880262411548686612L;

	@JsonProperty("address_components")
	private List<Endereco> enderecos;

	@JsonProperty("formatted_address")
	private String enderecoCompleto;

	@JsonProperty("types")
	private List<String> tipos;

	@JsonProperty("geometry")
	private LocalizacaoInfo localizacaoInfo;


	public ResultRequestGeocode() {
	}


	public List<Endereco> getEnderecos() {
		return enderecos;
	}


	public void setEnderecos(List<Endereco> enderecos) {
		this.enderecos = enderecos;
	}


	public String getEnderecoCompleto() {
		return enderecoCompleto;
	}


	public void setEnderecoCompleto(String enderecoCompleto) {
		this.enderecoCompleto = enderecoCompleto;
	}


	public List<String> getTipos() {
		return tipos;
	}


	public void setTipos(List<String> tipos) {
		this.tipos = tipos;
	}


	public void setLocalizacaoInfo(LocalizacaoInfo localizacaoInfo) {
		this.localizacaoInfo = localizacaoInfo;
	}

	public LocalizacaoInfo getLocalizacaoInfo() {
		return localizacaoInfo;
	}

	/*
  {
   "results" : [
      {
         "address_components" : [
            {
               "long_name" : "1600",
               "short_name" : "1600",
               "types" : [ "street_number" ]
            },
            {
               "long_name" : "Amphitheatre Pkwy",
               "short_name" : "Amphitheatre Pkwy",
               "types" : [ "route" ]
            },
            {
               "long_name" : "Mountain View",
               "short_name" : "Mountain View",
               "types" : [ "locality", "political" ]
            },
            {
               "long_name" : "Santa Clara",
               "short_name" : "Santa Clara",
               "types" : [ "administrative_area_level_2", "political" ]
            },
            {
               "long_name" : "California",
               "short_name" : "CA",
               "types" : [ "administrative_area_level_1", "political" ]
            },
            {
               "long_name" : "United States",
               "short_name" : "US",
               "types" : [ "country", "political" ]
            },
            {
               "long_name" : "94043",
               "short_name" : "94043",
               "types" : [ "postal_code" ]
            }
         ],
         "formatted_address" : "1600 Amphitheatre Pkwy, Mountain View, CA 94043, USA",
         "geometry" : {
            "location" : {
               "lat" : 37.42291810,
               "lng" : -122.08542120
            },
            "location_type" : "ROOFTOP",
            "viewport" : {
               "northeast" : {
                  "lat" : 37.42426708029149,
                  "lng" : -122.0840722197085
               },
               "southwest" : {
                  "lat" : 37.42156911970850,
                  "lng" : -122.0867701802915
               }
            }
         },
         "types" : [ "street_address" ]
      }
   ],
   "status" : "OK"
}
	 */

}
