package com.bizitglobal.webapp.faces.beans.operador;

public class AccesoPermisoBean {
	private Integer id;
	private String permiso;
	private String operador;
	private boolean validado;


	/*
	 * public AccesoPermisoBean(Acceso unAcceso) { id = unAcceso.getId(); permiso = unAcceso.getPermiso().getNombre();
	 * 
	 * if(unAcceso.getOperadorOtorga() == null) { operador = "x"; } else { operador = unAcceso.getOperadorOtorga().getNombre(); }
	 * 
	 * validado = (unAcceso.getEstado().equals("V")) ? true:false; }
	 */

	public AccesoPermisoBean() {
		id = null;
		permiso = null;
		operador = null;
		validado = false;
	}


	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}


	public String getOperador() {
		return operador;
	}


	public void setOperador(String operador) {
		this.operador = operador;
	}


	public String getPermiso() {
		return permiso;
	}


	public void setPermiso(String permiso) {
		this.permiso = permiso;
	}


	public boolean isValidado() {
		return validado;
	}


	public void setValidado(boolean validado) {
		this.validado = validado;
	}


	public String toString() {
		return "Id:" + id + "|operador:" + operador + "|permiso:" + permiso + "|validado:" + (validado ? "true" : "false");
	}

}
