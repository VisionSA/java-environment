package com.bizitglobal.webapp.faces.beans.workflow.wrappers;

import com.bizitglobal.workflow.negocio.DetalleTramite;
import com.bizitglobal.workflow.negocio.Tarea;


/**
 * @author Hernan Esta clase representa un tramite para ser mostrado
 */
public class TareaDetalleTabla {
	private DetalleTramite detalleTramite;
	private Tarea tarea;
	private String nombreTramite;
	private String operadorInciaTramite;


	public TareaDetalleTabla() {
		super();
		this.detalleTramite = new DetalleTramite();
		this.tarea = new Tarea();
		this.nombreTramite = "";
	}


	public TareaDetalleTabla(DetalleTramite detalleTramite) {
		super();
		this.detalleTramite = detalleTramite;
		this.tarea = new Tarea();
		this.nombreTramite = "";
	}


	public DetalleTramite getDetalleTramite() {
		return detalleTramite;
	}


	public void setDetalleTramite(DetalleTramite detalleTramite) {
		this.detalleTramite = detalleTramite;
	}


	public Tarea getTarea() {
		return tarea;
	}


	public void setTarea(Tarea tarea) {
		this.tarea = tarea;
	}


	public String getNombreTramite() {
		return nombreTramite;
	}


	public void setNombreTramite(String nombreTramite) {
		this.nombreTramite = nombreTramite;
	}


	public boolean equals(Object obj) {
		if (obj instanceof TareaDetalleTabla == false)
			return false;
		TareaDetalleTabla tareaDetalleTabla = (TareaDetalleTabla) obj;
		return tareaDetalleTabla.getDetalleTramite().equals(detalleTramite);
	}


	public String getOperadorInciaTramite() {
		return operadorInciaTramite;
	}


	public void setOperadorInciaTramite(String operadorInciaTramite) {
		this.operadorInciaTramite = operadorInciaTramite;
	}
}
