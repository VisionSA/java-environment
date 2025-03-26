package com.bizitglobal.webapp.faces.service.parametro;

import javax.servlet.ServletContext;

import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.bizitglobal.tarjetafiel.tparametros.service.ParametroService;
import com.bizitglobal.webapp.faces.service.util.FacesUtils;


public class ABMParametroServiceFaces {
	private static final String PARAMETRO_SERVICE_NAME = "parametroService";

	private ApplicationContext appContext;
	private ParametroService parametroService;


	public ABMParametroServiceFaces() {
		ServletContext context = FacesUtils.getServletContext();
		this.appContext = WebApplicationContextUtils.getRequiredWebApplicationContext(context);
		this.parametroService = (ParametroService) this.lookupService(PARAMETRO_SERVICE_NAME);
	}


	public ParametroService getParametroService() {
		return this.parametroService;
	}


	public Object lookupService(String serviceBeanName) {
		return appContext.getBean(serviceBeanName);
	}
}
