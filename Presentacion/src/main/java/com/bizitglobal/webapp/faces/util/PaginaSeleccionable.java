package com.bizitglobal.webapp.faces.util;

import com.bizitglobal.tarjetafiel.operador.negocio.Pagina;


public class PaginaSeleccionable {
	private Pagina pagina;
	private boolean acceso;
	private boolean alta;
	private boolean baja;
	private boolean modificacion;
	private boolean exportacion;


	public PaginaSeleccionable() {
		this(null, false, false, false, false, false);
	}


	public PaginaSeleccionable(Pagina pagina, boolean acceso, boolean alta, boolean baja,
			boolean modificacion, boolean exportacion) {
		super();
		this.pagina = pagina;
		this.acceso = acceso;
		this.alta = alta;
		this.baja = baja;
		this.modificacion = modificacion;
		this.exportacion = exportacion;
	}


	public boolean getAcceso() {
		return acceso;
	}


	public void setAcceso(boolean acceso) {
		this.acceso = acceso;
	}


	public boolean getAlta() {
		return alta;
	}


	public void setAlta(boolean alta) {
		this.alta = alta;
	}


	public boolean getBaja() {
		return baja;
	}


	public void setBaja(boolean baja) {
		this.baja = baja;
	}


	public boolean getExportacion() {
		return exportacion;
	}


	public void setExportacion(boolean exportacion) {
		this.exportacion = exportacion;
	}


	public boolean getModificacion() {
		return modificacion;
	}


	public void setModificacion(boolean modificacion) {
		this.modificacion = modificacion;
	}


	public Pagina getPagina() {
		return pagina;
	}


	public void setPagina(Pagina pagina) {
		this.pagina = pagina;
	}

}
