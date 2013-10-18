package br.com.lvc.googleplaces.model.geocode;

import org.codehaus.jackson.annotate.JsonProperty;

import br.com.lvc.googleplaces.model.Localizacao;

public class LocalizacaoComplementar {

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
	

}
