package br.com.lvc.googleplaces.model.place;

import org.codehaus.jackson.annotate.JsonProperty;

public class Foto {
 
	/*
	 * height: 645
html_attributions: [0]
photo_reference: "CnRrAAAAyjq3Md88NjzHuEDEX8amt0asJiIUXAI3O2NIGTup6EQ3zjJ4ZOlbE7DZfoTOCx-qsWSZa5r5bZVV7zDIVfVl5WV_sCZ3r__MN3i_P6BXYVWYIsWzwn99OdRRm28qXdKXfq1-I9Zl9hhANCkevSUNXhIQ3K5TcTVf7hfI4y8mHfHRGBoU4a3iLcsVdqvf8LQamVF6KTCEIXY"
width: 1024
	 */
	
	private int height;
	private int width;
	@JsonProperty("photo_reference")
	private String foto;
	
	public Foto() {
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public String getFoto() {
		return foto;
	}

	public void setFoto(String foto) {
		this.foto = foto;
	}
	
	
	
}
