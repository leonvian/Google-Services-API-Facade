package br.com.lvc.googleplaces.model.direction;

import java.io.Serializable;


public class Distancia implements Serializable  {


	private static final long serialVersionUID = 8323238522210876836L;
	private String text;
	private int value;

	public Distancia() {
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}

	@Override
	public String toString() {
		return text;
	}

}