package org.quartz.ui.web;

import javax.servlet.ServletContext;

import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

public class QuartzAppContext {
	
	private static QuartzAppContext quartzAppContext;
	
	private ApplicationContext applicationContext;
	
	private ServletContext servletContext;
	
	private QuartzAppContext() {
		// TODO Auto-generated constructor stub
	}
	
	public static QuartzAppContext getInstance(){
		
		if(quartzAppContext == null){
			quartzAppContext = new QuartzAppContext();
		}
		
		return quartzAppContext;
	}
	
	public ApplicationContext getApplicationContext() {
		
		if(applicationContext == null){
			applicationContext = WebApplicationContextUtils.getWebApplicationContext(this.servletContext);
		}
		
		return applicationContext;
	}
	
	public void setServletContext(ServletContext servletContext) {
		this.servletContext = servletContext;
	}
	
	public ServletContext getServletContext() {
		return servletContext;
	}
	
}
