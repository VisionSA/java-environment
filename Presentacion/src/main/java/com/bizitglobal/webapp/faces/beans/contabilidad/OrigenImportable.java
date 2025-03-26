package com.bizitglobal.webapp.faces.beans.contabilidad;

import java.util.List;

import com.bizitglobal.tarjetafiel.contabilidad.negocio.Importable;
import com.bizitglobal.tarjetafiel.contabilidad.negocio.Lote;
import com.bizitglobal.webapp.faces.beans.contabilidad.ImportacionAsientosBean.WrapperDetalleImportacion;


public interface OrigenImportable {

	public List recuperarDetallesDelLote(Lote lot, Importable importable);
	public void actualizarAsiento(WrapperDetalleImportacion importable);
	public void impactarOrigenContab(Long idAsiento, Long idObjetoOrigen, Long idNroComprobante);

}
