package com.bizitglobal.webapp.faces.beans.contabilidad;

import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import org.apache.log4j.Logger;
import org.apache.myfaces.renderkit.html.util.AddResource;
import org.apache.myfaces.renderkit.html.util.AddResourceFactory;

import com.bizitglobal.tarjetafiel.contabilidad.negocio.PlanCuentaDos;
import com.bizitglobal.webapp.faces.beans.BaseBean;
import com.bizitglobal.webapp.faces.util.Session;


public class ArbolBean extends BaseBean {
	private static final Logger log = Logger.getLogger(ArbolBean.class);

	// Domicilio que contiene las propiedades para el bean.
	private PlanCuentaDos planCuentaDos;


	public ArbolBean() {
		planCuentaDos = new PlanCuentaDos();
		// Borrar el bean de errores para poder validar desde cero.
		error.borrar();
	}


	/*
	 * Acciones para el bean de domicilios.
	 */

	public String agregarNodoDestinoPopup() {
		log.info("Agregando el domicilio al origen !!!");

		PlanCuentaBean planCuentaBean = (PlanCuentaBean) Session.getBean("PlanCuentaBean");
		// planCuenta.
		// / planCuentaBean.setNodoDestino("aca poner nodo seleccinado");
		log.info("");

		String path = FacesContext.getCurrentInstance().getExternalContext()
				.getRequestContextPath();
		path += "/tarjetafiel/contabilidad/arbolPopup.jsf";
		ejecutarJavaScript("popup('" + path + "',750,400), 'titlebar=no';");
		return null;

	}


	public String cancelar() {
		borrar();
		return null;
	}


	public void recargarYCerrarPopup(ActionEvent event) {
		if (validar()) {
			FacesContext facesContext = FacesContext.getCurrentInstance();
			String javaScriptText = "window.opener.recargar();window.close();";
			AddResource addResource = AddResourceFactory.getInstance(facesContext);
			addResource.addInlineScriptAtPosition(facesContext, AddResource.HEADER_BEGIN, javaScriptText);
		}
	}


	public boolean validar() {
		error.borrar();

		return (error.cantidad() == 0) ? true : false;
	}


	public String inicializar() {
		return null;
	}


	public String inicializar(PlanCuentaDos cuenta) {
		log.info("Ejecutando ==> inicializa");
		borrar();
		planCuentaDos = cuenta;
		error.borrar();

		return null;
	}


	public void borrar() {
		planCuentaDos = new PlanCuentaDos();

	}

}
