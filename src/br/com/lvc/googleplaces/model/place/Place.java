package br.com.lvc.googleplaces.model.place;

import java.util.List;

public class Place {
 
	
	/*
	 * icon: "http://maps.gstatic.com/mapfiles/place_api/icons/cafe-71.png"
id: "c71365287e7606bd21a3311d21fda087830b7813"
name: "Pancakes on the Rocks"
	 */
	
	private double rating;
	private String icon;
	private String id;
	private String name;
	private String reference;
	private List<String> types;
	private String vicinity; // Vizinhança
	private Geometria geometry;
	private List<Foto> photos;
	
	public Place() {
	
	}

	public double getRating() {
		return rating;
	}

	public void setRating(double rating) {
		this.rating = rating;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getReference() {
		return reference;
	}

	public void setReference(String reference) {
		this.reference = reference;
	}

	public List<String> getTypes() {
		return types;
	}

	public void setTypes(List<String> types) {
		this.types = types;
	}

	public String getVicinity() {
		return vicinity;
	}

	public void setVicinity(String vicinity) {
		this.vicinity = vicinity;
	}

	public Geometria getGeometry() {
		return geometry;
	}

	public void setGeometry(Geometria geometry) {
		this.geometry = geometry;
	}

	public List<Foto> getPhotos() {
		return photos;
	}

	public void setPhotos(List<Foto> photos) {
		this.photos = photos;
	}
	
}