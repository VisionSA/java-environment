package com.bizitglobal.webapp.faces.service.util;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.faces.context.FacesContext;


public class FacesUtils {

	public static ServletContext getServletContext() {
		return (ServletContext) FacesContext.getCurrentInstance().getExternalContext().getContext();
	}


	private static HttpServletRequest getServletRequest() {
		return (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
	}

}
