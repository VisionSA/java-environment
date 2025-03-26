package com.bizitglobal.webapp.faces.beans.workflow.wrappers;

import java.text.Format;
import java.text.SimpleDateFormat;

import com.bizitglobal.workflow.negocio.DetalleTramiteHis;
import com.bizitglobal.workflow.negocio.Tarea;


/**
 * @author Hernan Esta clase representa un tramite para ser mostrado
 */
public class DetalleHisWrapper {
	private DetalleTramiteHis detalleHis;
	private Tarea tarea;


	public DetalleHisWrapper() {
		super();
		this.detalleHis = new DetalleTramiteHis();
		this.tarea = new Tarea();
	}


	public DetalleHisWrapper(DetalleTramiteHis detalleHis) {
		super();
		this.detalleHis = detalleHis;
		this.tarea = new Tarea();
	}


	public DetalleTramiteHis getDetalleHis() {
		return detalleHis;
	}


	public void setDetalleHis(DetalleTramiteHis detalleHis) {
		this.detalleHis = detalleHis;
	}


	public String getTimestamp() {
		Format dateFormat = new SimpleDateFormat("dd/MM/yyyy-HH:mm");
		return dateFormat.format(detalleHis.getTimestamp());
	}


	public Tarea getTarea() {
		return tarea;
	}


	public void setTarea(Tarea tarea) {
		this.tarea = tarea;
	}


	public boolean equals(Object obj) {
		if (obj instanceof DetalleHisWrapper == false)
			return false;
		DetalleHisWrapper detalleHisWrapper = (DetalleHisWrapper) obj;
		return detalleHisWrapper.getDetalleHis().equals(detalleHis);
	}
}
