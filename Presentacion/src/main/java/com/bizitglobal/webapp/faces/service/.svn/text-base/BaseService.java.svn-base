package com.bizitglobal.webapp.faces.service;

import javax.servlet.ServletContext;

import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.bizitglobal.webapp.faces.service.util.FacesUtils;


public class BaseService {

	private ApplicationContext appContext;


	public BaseService() {
		ServletContext context = FacesUtils.getServletContext();
		this.appContext = WebApplicationContextUtils.getRequiredWebApplicationContext(context);
	}


	public Object lookupService(String serviceBeanName) {
		return appContext.getBean(serviceBeanName);
	}

}
