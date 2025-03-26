package com.bizitglobal.webapp.faces.beans.impuestos;

import java.sql.Timestamp;
import java.util.Date;

import javax.faces.event.ActionEvent;

import org.apache.log4j.Logger;

import com.bizitglobal.tarjetafiel.impuestos.negocio.Exclusion;
import com.bizitglobal.tarjetafiel.impuestos.negocio.TipoImpuesto;
import com.bizitglobal.webapp.faces.beans.BaseBean;
import com.bizitglobal.webapp.faces.util.Error;
import com.bizitglobal.webapp.faces.util.Session;


@SuppressWarnings({"unchecked"})
public class ExclusionBean extends BaseBean {
	private static final Logger log = Logger.getLogger(ExclusionBean.class);
	private Exclusion exclusion;
	private Date fechaDesde;
	private Date fechaHasta;
	private Date fechaEmision;


	public ExclusionBean() {
		exclusion = new Exclusion();
		exclusion.setIdExclusion(new Long("" + exclusion.hashCode()));
		fechaDesde = null;
		fechaEmision = null;
		fechaHasta = null;
	}


	public Exclusion getExclusion() {
		return exclusion;
	}


	public void setExclusion(Exclusion exclusion) {
		this.exclusion = exclusion;
	}


	/*
	 * ACCIONES PARA EXCLUSION BEAN
	 */
	public String agregarExclusion() {
		log.info("Agregando exclusion al bean de individuos!!!");
		if (validar()) {
			exclusion.setIdExclusion(new Long(hashCode()));
			exclusion.setFechaDesde(new Timestamp(fechaDesde.getTime()));
			exclusion.setFechaHasta(new Timestamp(fechaHasta.getTime()));
			exclusion.setFechaEmcion(new Timestamp(fechaEmision.getTime()));
			IndividuoBean bean = (IndividuoBean) Session.getBean("IndividuoBean");
			bean.getExclusiones().add(exclusion);
			borrar();
		}
		return null;
	}


	public String cancelar() {
		return null;
	}


	public void recargarYCerrarPopup(ActionEvent event) {
		log.info("Ejecutando recargar y close!!!");
		if (validar()) {
			ejecutarJavaScript("window.opener.recargar();window.close();");
		}
	}


	public void borrar() {
		exclusion = new Exclusion();
		exclusion.setIdExclusion(new Long("" + exclusion.hashCode()));
		fechaDesde = null;
		fechaHasta = null;
		fechaEmision = null;
	}


	public String inicializar() {
		// TODO Auto-generated method stub
		return null;
	}


	public String inicializar(TipoImpuesto tipoImpuesto) {
		log.info("<<<<<<<<<<<<< Inicializando Exclusion >>>>>>>>>>>>>>");
		borrar();
		exclusion.setTipoImpuesto(tipoImpuesto);
		return null;
	}


	public boolean validar() {
		error.borrar();

		if (exclusion.getDescripcion() == null || exclusion.getDescripcion() == "") {
			error.agregar(Error.EXCLUSION_DESCRIPCION_REQUERIDA);
		}

		if (getFechaDesde() == null) {
			error.agregar(Error.EXCLUSION_FECHA_DESDE_REQUERIDA);
		}

		if (getFechaHasta() == null) {
			error.agregar(Error.EXCLUSION_FECHA_HASTA_REQUERIDA);
		}

		if ((getFechaDesde() != null && getFechaHasta() != null) && (getFechaDesde().after(getFechaHasta()))) {
			error.agregar(Error.EXCLUSION_FECHA_RANGO_REQUERIDA);
		}

		if (exclusion.getPorcentaje() == null) {
			error.agregar(Error.EXCLUSION_PORCENTAJE_REQUERIDA);
		} else {
			if (exclusion.getPorcentaje().compareTo(new Float(100)) > 0) {
				error.agregar(Error.EXCLUSION_PORCENTAJE_EXEDIDO);
			}
		}

		if (getFechaEmision() == null) {
			error.agregar(Error.EXCLUSION_FECHA_EMISION_REQUERIDA);
		}

		log.info("Cantidad de errores: " + error.cantidad());

		return (error.cantidad() == 0) ? true : false;
	}


	public Date getFechaDesde() {
		return fechaDesde;
	}


	public void setFechaDesde(Date fechaDesde) {
		this.fechaDesde = fechaDesde;
	}


	public Date getFechaHasta() {
		return fechaHasta;
	}


	public void setFechaHasta(Date fechaHasta) {
		this.fechaHasta = fechaHasta;
	}


	public Date getFechaEmision() {
		return fechaEmision;
	}


	public void setFechaEmision(Date fechaEmision) {
		this.fechaEmision = fechaEmision;
	}

}
