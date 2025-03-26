package com.bizitglobal.webapp.faces.service;

import javax.servlet.ServletContext;

import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.bizitglobal.tarjetafiel.proveedores.service.ComprobanteService;
import com.bizitglobal.webapp.faces.service.util.FacesUtils;


public class ProveedoresServiceFaces {
	private static final String COMPROBANTE_SERVICE_NAME = "comprobanteService";

	private ApplicationContext appContext;
	private ComprobanteService comprobanteService;


	public ProveedoresServiceFaces() {
		ServletContext context = FacesUtils.getServletContext();
		this.appContext = WebApplicationContextUtils.getRequiredWebApplicationContext(context);
		this.comprobanteService = (ComprobanteService) this.lookupService(COMPROBANTE_SERVICE_NAME);
	}


	public ComprobanteService getComprobanteService() {
		return this.comprobanteService;
	}


	public Object lookupService(String serviceBeanName) {
		return appContext.getBean(serviceBeanName);
	}
}
