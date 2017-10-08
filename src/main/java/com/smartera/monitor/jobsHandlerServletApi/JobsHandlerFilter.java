package com.smartera.monitor.jobsHandlerServletApi;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
        
        this.reportCurrentJobInRequest(httpRequest);
        
        chain.doFilter(request,response);

	}
	

	public void destroy() {
		
		
	}
	
	private void reportCurrentJobInRequest(HttpServletRequest request){
//        JobHandler.getInstance().startNewJob().setJobStory(request.getParameter("JOB_STORY"));
		System.out.println("inside jobs handler servlet api");

	}
	

}
