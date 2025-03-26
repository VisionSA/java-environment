package com.bizitglobal.tarjetafiel.proveedores.dao;

import java.util.List;

import com.bizitglobal.tarjetafiel.commons.filtros.Filtro;
import com.bizitglobal.tarjetafiel.proveedores.negocio.DocumentoAdjunto;

public interface DocumentoAdjuntoDao {
	
	public void grabarDocumentoAdjunto(DocumentoAdjunto unDocumentoAdjunto);
	
	public DocumentoAdjunto buscarDocumentoAdjunto(Long id);
	
	public void borrarDocumentoAdjunto(Long id);
	
	public void borrarDocumentoAdjunto(DocumentoAdjunto unDocumentoAdjunto);
	
	public List listarTodos(Filtro filtro);
	
	public void actualizarDocumentoAdjunto(DocumentoAdjunto unDocumentoAdjunto);
	
	public Long maxIdDocumentoAdjunto();
	
}
