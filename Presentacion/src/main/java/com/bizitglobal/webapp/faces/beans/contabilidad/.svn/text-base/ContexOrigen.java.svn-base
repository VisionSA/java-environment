package com.bizitglobal.webapp.faces.beans.contabilidad;

import java.util.List;

import com.bizitglobal.tarjetafiel.contabilidad.negocio.Importable;
import com.bizitglobal.tarjetafiel.contabilidad.negocio.Lote;
import com.bizitglobal.webapp.faces.beans.contabilidad.ImportacionAsientosBean.WrapperDetalleImportacion;


public class ContexOrigen {
	private OrigenImportable origenImportable = null;


	public List recuperarDetalleLotes(Lote lot, Importable importable) {
		List result = origenImportable.recuperarDetallesDelLote(lot, importable);
		return result;
	}


	public void actualizarAsiento(WrapperDetalleImportacion importable) {
		origenImportable.actualizarAsiento(importable);
	}


	public void impactarOrigenContab(Long idAsiento, Long idObjetoOrigen, Long idNroComprobante) {
		origenImportable.impactarOrigenContab(idAsiento, idObjetoOrigen, idNroComprobante);
	}


	public OrigenImportable getOrigenImportable() {
		return origenImportable;
	}


	public void setOrigenImportable(OrigenImportable origenImportable) {
		this.origenImportable = origenImportable;
	}

}
