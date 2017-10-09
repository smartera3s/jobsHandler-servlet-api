package com.smartera.monitor.jobsHandlerServletApi;

import java.util.Enumeration;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;

import org.json.JSONException;
import org.json.JSONObject;

public class HttpJobsFactory {

	public static HashMap<String, Object> paramsFromHttpServletRequest(HttpServletRequest request) throws JSONException{
		HashMap<String, Object>	requestImportantParameters	=	new HashMap<String, Object>();
		Enumeration<String> headerNames = request.getHeaderNames();
        if (headerNames != null) {
                while (headerNames.hasMoreElements()) {
                        System.out.println("Header: " + request.getHeader(headerNames.nextElement()));
                }
        }
        

        return null;
	}
	
	public static String	httpRequestCopy(HttpServletRequest request){
		return null;
	}
}
