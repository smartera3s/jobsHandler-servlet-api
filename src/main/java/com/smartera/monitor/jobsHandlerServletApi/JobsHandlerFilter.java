package com.smartera.monitor.jobsHandlerServletApi;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONException;

import com.smartera.monitor.jobsHandler.Job;
import com.smartera.monitor.jobsHandler.JobHandler;

public class JobsHandlerFilter implements Filter {

	public void init(FilterConfig filterConfig) throws ServletException {
		
		
	}

	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		
		if (!(request instanceof HttpServletRequest) || !(response instanceof HttpServletResponse)) {
            throw new ServletException("RequestTracingFilter only supports HTTP requests");
        }
		
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        
        try {
			this.reportCurrentJobInRequest(httpRequest);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        chain.doFilter(request,response);

	}
	

	public void destroy() {
		
		
	}
	
	private void reportCurrentJobInRequest(HttpServletRequest request) throws JSONException{
		
        HashMap<String, Object>	requestParameters	=	HttpJobsFactory.paramsFromHttpServletRequest(request);

        JobHandler.getInstance().startNewJob((String) requestParameters.get("request_id") ,(String)requestParameters.get("Job_Story"));
	}
	

}
