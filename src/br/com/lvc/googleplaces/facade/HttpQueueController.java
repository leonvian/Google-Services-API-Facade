package br.com.lvc.googleplaces.facade;

import android.content.Context;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;


public class HttpQueueController {

	private RequestQueue queue = null;
	private static HttpQueueController instance;

	public static HttpQueueController getInstance() {
		if(instance == null)
			instance = new HttpQueueController();
		return instance;
	}

	public RequestQueue getQueue(Context context) { 
		if(queue == null)
			queue = Volley.newRequestQueue(context);

		return queue;
	}

}