package com.bizitglobal.tarjetafiel.operador.service;

import java.util.List;

import com.bizitglobal.tarjetafiel.operador.exeption.RolException;
import com.bizitglobal.tarjetafiel.operador.negocio.Rol;


/**
 *	Define los servicios que tendran disponibles los usuarios.
 */
public interface RolService {
	
	/**
	 * Graba un rol en la base de datos.
	 * @param unRol, rol a grabar.
	 */
	public void grabarRol(Rol unRol) throws RolException;
	
	/**
	 * Obtiene un objeto rol dado un id de rol.
	 * @param id, Id del objeto buscado.
	 * @return Retorna un objeto Rol.
	 */
	public Rol leerRol(Long id) throws RolException;
	
	/**
	 * Obtiene todos los roles almacenados en el base de datos.
	 * @return Retorna una lista de roles.
	 */
	public List getRoles() throws RolException;
	
	/**
	 * Borra un rol dado su identificador.
	 * @param idRol, identificador del rol.
	 */
	public void borrarRol(Long idRol) throws RolException;
	
	/**
	 * Graba o actualiza un rol en la base de datos segun el caso.
	 * @param unRol, rol a grabar.
	 */
	public void grabarOActualizarRol(Rol unRol) throws RolException;

}
