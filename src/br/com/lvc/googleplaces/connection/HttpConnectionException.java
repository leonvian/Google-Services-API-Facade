package br.com.lvc.googleplaces.connection;


public class HttpConnectionException extends AndroidAppException {


	private static final long serialVersionUID = -8748264590081941429L;

	
	public HttpConnectionException(int message, Throwable exception) {
		super(message, exception);
	}

	public HttpConnectionException(String message, Throwable exception) {
		super(message, exception);
	}
	
	public HttpConnectionException(Throwable exception) {
		super(exception);
	}
	
	public HttpConnectionException(String exception) {
		super(exception,null);
	}


}
