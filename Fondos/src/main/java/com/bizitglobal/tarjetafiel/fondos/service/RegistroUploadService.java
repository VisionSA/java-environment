package com.bizitglobal.tarjetafiel.fondos.service;

import java.util.List;

import com.bizitglobal.tarjetafiel.commons.filtros.Filtro;
import com.bizitglobal.tarjetafiel.fondos.exception.RegistroUploadException;
import com.bizitglobal.tarjetafiel.fondos.negocio.RegistroUpload;

/*import com.bizitglobal.tarjetafiel.commons.filtros.Filtro;
import com.bizitglobal.tarjetafiel.general.exception.MonedaException;
import com.bizitglobal.tarjetafiel.general.negocio.Moneda;*/

/**
 * @author Daniel
 * Interface de servicios para las formas de pago del sistema. Define un conjunto de puntos de entrada
 * para utilizarse en la capa de presentación.
 */
public interface RegistroUploadService {
	
	/**
	 * Graba una RegistroUpload en la base de datos.
	 * @param unaRegistroUpload, RegistroUpload a grabar.
	 */
	public void grabarRegistroUpload(RegistroUpload unaRegistroUpload)throws RegistroUploadException ;
	
	/**
	 * Obtiene una RegistroUpload de la base de datos dado su id.
	 * @param id, Identificador de la RegistroUpload buscada.
	 * @return La RegistroUpload buscada.
	 */
	public RegistroUpload leerRegistroUpload(Long id) throws RegistroUploadException;
	
	/**
	 * Borra un RegistroUpload de la base de datos dado su id.
	 * @param id, Identificador de la RegistroUpload.
	 */
	public void borrarRegistroUpload(Long id) throws RegistroUploadException;
	
	/**
	 * Borra una RegistroUpload de la base de datos dado el mismo.
	 * @param unaRegistroUpload, RegistroUpload a borrar.
	 */
	public void borrarRegistroUpload(RegistroUpload unaRegistroUpload) throws RegistroUploadException;
	
	/**
	 * Actualiza una RegistroUpload en la base de datos.
	 * @param unaRegistroUpload, RegistroUpload a actualizar.
	 */
	public void actualizarRegistroUpload(RegistroUpload unaRegistroUpload) throws RegistroUploadException;
	
	/**
	 * Obtiene una lista de todas las RegistroUploads.
	 * @return Una lista de RegistroUploads.
	 */
	public List getRegistroUploades() throws RegistroUploadException;
	
	/**
	 * Obtiene una lista de todas los tipos de RegistroUploads según el filtro.
	 * @return Una lista de RegistroUploads.
	 */
	public List getRegistroUploads(Filtro filtro) throws RegistroUploadException;
	
}
