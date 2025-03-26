package com.bizitglobal.tarjetafiel.proveedores.service;

import java.util.List;

import com.bizitglobal.tarjetafiel.proveedores.exception.DocumentoAdjuntoException;
import com.bizitglobal.tarjetafiel.proveedores.negocio.DocumentoAdjunto;

/**
 * @author WAldemar
 * Interface de servicios para el DocumentoAdjunto del sistema. Define un conjunto de puntos de entrada
 * para utilizarse en la capa de presentación.
 */
public interface DocumentoAdjuntoService {
	
	/**
	 * Graba un DocumentoAdjunto en la base de datos.
	 * @param unDocumentoAdjunto, DocumentoAdjunto a grabar.
	 */
	public void grabarDocumentoAdjunto(DocumentoAdjunto unDocumentoAdjunto) throws DocumentoAdjuntoException;
	
	/**
	 * Obtiene un DocumentoAdjunto de la base de datos dado su id.
	 * @param id, Identificador del DocumentoAdjunto buscado.
	 * @return El DocumentoAdjunto buscada.
	 */
	public DocumentoAdjunto leerDocumentoAdjunto(Long id) throws DocumentoAdjuntoException;
	
	/**
	 * Borra una DocumentoAdjunto de la base de datos dado su id.
	 * @param id, Identificador del DocumentoAdjunto.
	 */
	public void borrarDocumentoAdjunto(Long id) throws DocumentoAdjuntoException;
	
	/**
	 * Borra una DocumentoAdjunto de la base de datos dado el mismo.
	 * @param unDocumentoAdjunto, DocumentoAdjunto a borrar.
	 */
	public void borrarDocumentoAdjunto(DocumentoAdjunto unDocumentoAdjunto) throws DocumentoAdjuntoException;
	
	/**
	 * Actualiza un DocumentoAdjunto en la base de datos.
	 * @param unDocumentoAdjunto, DocumentoAdjunto a actualizar.
	 */
	public void actualizarDocumentoAdjunto(DocumentoAdjunto unDocumentoAdjunto) throws DocumentoAdjuntoException;
	
	/**
	 * Obtiene una lista de todos las DocumentoAdjunto.
	 * @return Una lista de DocumentoAdjunto.
	 */

	
}
