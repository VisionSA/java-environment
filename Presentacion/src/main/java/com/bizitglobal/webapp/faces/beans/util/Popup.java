package com.bizitglobal.webapp.faces.beans.util;

public class Popup {
	// El root de las imagenes es la carpeta /img/
	public static final String ICONO_OK = "icon/about_32.gif";
	public static final String ICONO_FALLA = "alarma.gif";
	public static final String ICONO_ERROR = "";
	public static final String ICONO_CONFIRMACION = "icon/confirm.png";

	private String nombreIcono;
	private String mensaje;
	private boolean mostrar;


	public Popup() {
		this(null, null, false);
	}


	public Popup(String nombreIcono, String mensaje, boolean mostrar) {
		super();
		this.nombreIcono = nombreIcono;
		this.mensaje = mensaje;
		this.mostrar = mostrar;
	}


	public String getMensaje() {
		return mensaje;
	}


	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}


	public String getNombreIcono() {
		return nombreIcono;
	}


	public void setNombreIcono(String nombreIcono) {
		this.nombreIcono = nombreIcono;
	}


	public boolean getMostrar() {
		return mostrar;
	}


	public void setMostrar(boolean mostrar) {
		this.mostrar = mostrar;
	}


	public void setPopup(String nombreIcono, String mensaje) {
		this.nombreIcono = nombreIcono;
		this.mensaje = mensaje;
		this.mostrar = true;
	}


	public void borrar() {
		this.nombreIcono = null;
		this.mensaje = null;
		this.mostrar = false;
	}

}
