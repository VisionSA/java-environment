package com.bizitglobal.webapp.faces.util;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import javax.faces.FactoryFinder;
import javax.faces.application.Application;
import javax.faces.application.ApplicationFactory;
import javax.faces.component.UIComponent;
import javax.faces.component.UIViewRoot;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.el.ValueBinding;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.bizitglobal.tarjetafiel.operador.negocio.Operador;
import com.bizitglobal.webapp.faces.beans.SessionBean;


@SuppressWarnings({"rawtypes","unchecked"})
public class Session {
	private static final Logger log = Logger.getLogger(Session.class);
	private static final String NOMBRE_OPERADOR_BEAN = "operadorSessionBean";
	private static final String NOMBRE_OPERADOR = "nombreOperador";
	private static final String PERMISOS_SELECCIONADOS_COMPLETOS = "arbolPermisosCompletos";
	private static final String PERMISOS_SELECCIONADOS_ITEMS = "arbolPermisosItems";


	public static void putInSession(String name, Object obj) {
		getSessionMap().put(name, obj);
	}


	public static void removeFromSession(String name) {
		getSessionMap().remove(name);
	}


	public static Object getBean(String name) {
		return getValueBinding(getJsfEl(name)).getValue(FacesContext.getCurrentInstance());
	}


	public static ValueBinding getValueBinding(String el) {
		return getApplication().createValueBinding(el);
	}


	public static Application getApplication() {
		ApplicationFactory appFactory = (ApplicationFactory) FactoryFinder.getFactory(FactoryFinder.APPLICATION_FACTORY);
		return appFactory.getApplication();
	}


	public static Operador getOperador() {
		return (Operador) getSessionMap().get(NOMBRE_OPERADOR_BEAN);
	}


	public static void setOperador(Operador operador) {
		String nombreCompleto = operador.getUsername() + " - " + operador.getNombre() + " " + operador.getApellido();
		getSessionMap().put(NOMBRE_OPERADOR_BEAN, operador);
		getSessionMap().put(NOMBRE_OPERADOR, nombreCompleto);
	}


	public static void removeOperador() {
		getSessionMap().remove(NOMBRE_OPERADOR_BEAN);
		getSessionMap().remove(NOMBRE_OPERADOR);
	}


	public static String getRequestParameter(String name) {
		return (String) FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get(name);
	}


	public static UIComponent getComponent(String id) {
		FacesContext facesContext = FacesContext.getCurrentInstance();
		UIViewRoot root = facesContext.getViewRoot();
		return root.findComponent(id);
	}


	public static void renderResponse() {
		FacesContext.getCurrentInstance().renderResponse();
	}


	private static Map getSessionMap() {
		ExternalContext sessionContext = FacesContext.getCurrentInstance().getExternalContext();
		return sessionContext.getSessionMap();
	}


	public static String getJsfEl(String name) {
		return "#{" + name + "}";
	}


	public static void redirect(String page) {
		FacesContext facesContext = FacesContext.getCurrentInstance();
		try {
			facesContext.getExternalContext().redirect(facesContext.getExternalContext().getRequestContextPath() + page);
			log.info("Redirigiendo -----> " + page);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}


	public static List getPermisosSeleccionadosCompletos() {
		return (List) getSessionMap().get(PERMISOS_SELECCIONADOS_COMPLETOS);
	}


	public static void setPermisosSeleccionadosCompletos(List permisos) {
		getSessionMap().put(PERMISOS_SELECCIONADOS_COMPLETOS, permisos);
	}


	public static void removePermisosSeleccionadosCompletos() {
		getSessionMap().remove(PERMISOS_SELECCIONADOS_COMPLETOS);
	}


	public static List getPermisosSeleccionadosItems() {
		return (List) getSessionMap().get(PERMISOS_SELECCIONADOS_ITEMS);
	}


	public static void setPermisosSeleccionadosItems(List permisos) {
		getSessionMap().put(PERMISOS_SELECCIONADOS_ITEMS, permisos);
	}


	public static void removePermisosSeleccionadosItems() {
		getSessionMap().remove(PERMISOS_SELECCIONADOS_ITEMS);
	}


	public static void reiniciar() {
		if (getBean("SessionBean") != null) {
			SessionBean bean = (SessionBean) getBean("SessionBean");
			bean.setReiniciar(true);
		}
	}


	public static Object getAttribute(String name) {
		FacesContext ctx = FacesContext.getCurrentInstance();
		HttpServletRequest request = (HttpServletRequest) ctx.getExternalContext().getRequest();
		return request.getAttribute(name);
	}


	public static void setAttribute(String name, Object unObj) {
		FacesContext ctx = FacesContext.getCurrentInstance();
		HttpServletRequest request = (HttpServletRequest) ctx.getExternalContext().getRequest();
		request.setAttribute(name, unObj);
	}


	public static HttpServletRequest getRequest() {
		FacesContext ctx = FacesContext.getCurrentInstance();
		HttpServletRequest request = (HttpServletRequest) ctx.getExternalContext().getRequest();
		return request;
	}


	public static HttpServletResponse getResponse() {
		FacesContext ctx = FacesContext.getCurrentInstance();
		HttpServletResponse response = (HttpServletResponse) ctx.getExternalContext().getResponse();
		return response;
	}


	/**
	 * <p>
	 * Skip any remaining request processing lifecycle phases for the current request, including <em>Render Response</em> phase. This is appropriate
	 * if you have completed the response through some means other than JavaServer Faces rendering.
	 * </p>
	 */
	public static void responseComplete() {
		FacesContext.getCurrentInstance().responseComplete();
	}


	public static Vector getCheckboxVector(String name) {
		Vector result = new Vector();
		HttpServletRequest request = getRequest();

		// Obtenemos una lista que contiene todos los parametro de la session.
		Enumeration checks = request.getParameterNames();
		List listaParametros = new ArrayList();
		while (checks.hasMoreElements()) {
			listaParametros.add(checks.nextElement());
		}

		if (!listaParametros.isEmpty()) {
			if (listaParametros.contains(name)) {
				// Obtenemos una lista de valores(campos ocultos del ckeck).
				String[] valores = request.getParameterValues(name);
				for (int i = 0; i < valores.length; i++) {
					String nombreCompleto = name + "[" + valores[i] + "]";
					if (listaParametros.contains(nombreCompleto)) {
						// Agregamos al resultado el id del valor selecionado.
						result.add(valores[i]);
					}
				}
			}
		}

		return result;
	}


	public static String getHomePath() {
		HttpServletRequest request = getRequest();
		int pos = request.getRequestURL().lastIndexOf(request.getServletPath());
		return request.getRequestURL().substring(0, pos);
	}
}
