package com.bizitglobal.webapp.faces.beans.error;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.bizitglobal.tarjetafiel.commons.util.Convertidores;
import com.bizitglobal.webapp.faces.beans.util.ScrollBean;
import com.bizitglobal.webapp.faces.util.Session;


@SuppressWarnings({"rawtypes","unchecked"})
public class ErrorBean {
	private static final Logger log = Logger.getLogger(ErrorBean.class);
	private List errores;
	private ScrollBean scroll;


	public ErrorBean() {
		this(new ArrayList());
		scroll = (ScrollBean) Session.getBean("ScrollBean");
	}


	public ErrorBean(List errores) {
		super();
		scroll = (ScrollBean) Session.getBean("ScrollBean");
		this.errores = errores;
	}


	public List getErrores() {
		List copia = Convertidores.copiarListaString(errores);
		errores = new ArrayList();
		return copia;
	}


	public void setErrores(List errores) {
		scroll.borrar();
		this.errores = errores;
	}


	public void agregar(String error) {
		log.info("agregando error -> " + error);
		scroll.borrar();
		errores.add(error);
	}


	public void remover(String error) {
		errores.remove(error);
	}


	public int cantidad() {
		return errores.size();
	}


	public boolean hayErrores() {
		return (errores.size() != 0) ? true : false;
	}


	public void borrar() {
		errores = new ArrayList();
	}

}
