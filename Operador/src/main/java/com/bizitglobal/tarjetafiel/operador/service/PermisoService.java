package com.bizitglobal.tarjetafiel.operador.service;

import java.util.List;

import com.bizitglobal.tarjetafiel.operador.exeption.PermisoException;
import com.bizitglobal.tarjetafiel.operador.negocio.Permiso;


/**
 *	Define los servicios que tendran disponibles los usuarios.
 */
public interface PermisoService {
	
	/**
	 * Graba un permiso en la base de datos.
	 * @param unPermiso, permiso a grabar.
	 */
	public void grabarPermiso(Permiso unPermiso) throws PermisoException;
	
	/**
	 * Obtiene un objeto permiso dado un id de permiso.
	 * @param id, Id del objeto buscado.
	 * @return Retorna un objeto Permiso.
	 */
	public Permiso leerPermiso(Long id) throws PermisoException;
	
	/**
	 * Obtiene todos los permisos almacenados en el base de datos.
	 * @return Retorna una lista de permisos.
	 */
	public List getPermisos() throws PermisoException;
	
	/**
	 * Borra un permiso dado su identificador.
	 * @param idPermiso, identificador del permiso.
	 */
	public void borrarPermiso(Long idPermiso) throws PermisoException;

}
