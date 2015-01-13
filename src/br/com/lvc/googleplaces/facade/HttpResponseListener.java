package br.com.lvc.googleplaces.facade;

public interface HttpResponseListener<T> {
	
	public void sucess(T data);
	
	public void error(String erro);

}
