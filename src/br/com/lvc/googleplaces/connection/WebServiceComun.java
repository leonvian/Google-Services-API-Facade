package br.com.lvc.googleplaces.connection;

import org.apache.http.HttpResponse;

import android.util.Log;

public abstract class WebServiceComun {

	protected SynchronousHttpConnection synchronousHttpConnection = new SynchronousHttpConnection();

	public  void sendDataPOST(String url,String passable) throws  HttpConnectionException {
		String json = passable;
		Log.i("Dados enviados", json);

		String response = synchronousHttpConnection.post(url, json);
		Log.i("Resposta Servidor", response);
	}

	public  <T> T sendDataGet(String url, Class<T> targetClass) throws HttpConnectionException {
		String response = synchronousHttpConnection.get(url);
		Log.i("Resposta Servidor", response);
		T t = DataSerializer.getInstance().toObject(response, targetClass);

		return t;
	}
 

	public  <T> T sendDataPOST(String url,String passable, Class<T> targetClass) throws  HttpConnectionException {
		String json = passable;
		Log.i("Dados enviados", json);

		String response = synchronousHttpConnection.post(url, json);
		Log.i("Resposta Servidor", response);

		T t = DataSerializer.getInstance().toObject(response, targetClass);

		return t;
	} 
 

	public  <T> T sendDataGetLonger(String url, Class<T> targetClass) throws  HttpConnectionException {
		String response = synchronousHttpConnection.executeGetHTTPLonger(url);
		T t = DataSerializer.getInstance().toObject(response, targetClass);

		return t;
	}
 
	public  HttpResponse sendDataPOSTHttpResponseAsReturn(String url,String passable) throws  HttpConnectionException { 
		HttpResponse response = synchronousHttpConnection.postHttpResponseAsReturn(url, passable);
	    return response;
	}

	public  HttpResponse sendDataGetHttpResponseAsReturn(String url) throws HttpConnectionException {
		HttpResponse response = synchronousHttpConnection.getHttpResponseAsReturn(url);
		return response;
	}
 

	public  HttpResponse sendDataGetLongerHttpResponseAsReturn(String url) throws  HttpConnectionException {
		HttpResponse response = synchronousHttpConnection.executeGetHTTPLongerHttpResponseAsReturn(url);
		return response;
	}
}