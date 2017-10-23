package com.smartera.monitor.jobsHandlerServletApi;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.json.JSONException;
import org.json.JSONObject;

public class HttpJobsFactory {

	public static HashMap<String, Object> paramsFromHttpServletRequest(HttpServletRequest request) throws JSONException{
		
		HashMap<String, Object>	requestImportantParameters	=	getRequestParameters(request);
		HashMap<String, Object> requestImportantHeaders 	=	getRequestHeaders(request);
		
		HashMap<String, Object>	requestDetails				=	new HashMap<String, Object>();
		
		requestDetails.put("headers", 	requestImportantHeaders);
		requestDetails.put("params" ,	requestImportantParameters);
		
        return requestDetails;
	}
	
	private static HashMap<String, Object>	getRequestParameters(HttpServletRequest request){
		
		HashMap<String, Object>	parameters	=	new HashMap<String, Object>();
		Map params = request.getParameterMap();
	    Iterator i = params.keySet().iterator();
	   
	    while ( i.hasNext() )
	      {
	        String key = (String) i.next();
	        Object value = params.get(key);
	        parameters.put(key, value);
	      }
	    
		return parameters;
	}
	
	private static HashMap<String, Object>	getRequestHeaders(HttpServletRequest request){
		HashMap<String, Object>	headers	=	new HashMap<String, Object>();
		Enumeration<String> headerNames = request.getHeaderNames();

        if (headerNames != null) {
                while (headerNames.hasMoreElements()) {
                		headers.put(headerNames.nextElement(), request.getHeader(headerNames.nextElement()));
                }
        }
		return headers;
	}
}
