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
        	reportCurrentJobInRequest(httpRequest);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        chain.doFilter(request,response);

	}
	

	public void destroy() {
		
		
	}
	
	private void reportCurrentJobInRequest(HttpServletRequest request) throws Exception{

        String jobID	=	request.getHeader("request-id");
        System.out.println("current Job ID is: " + jobID);
        String storyID	=	(request.getHeader("JOB_STORY") != null) ? request.getHeader("JOB_STORY") : "NONE";
        
        HashMap<String, Object>	cachedRequestAsHashMap	=	HttpJobsFactory.paramsFromHttpServletRequest(request);
        
        JobHandler.getInstance()
        		  .reportTo("http://monitoringservice:8080/MonitoringService/ReportJobStatus")
        		  .startNewJob(jobID , storyID, cachedRequestAsHashMap);
	}
	

}
