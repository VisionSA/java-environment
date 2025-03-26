package com.bizitglobal.tarjetafiel.operador.service;

import java.util.List;

import com.bizitglobal.tarjetafiel.operador.exeption.RolMenuItemException;
import com.bizitglobal.tarjetafiel.operador.negocio.RolMenuItem;


/**
 *	Define los servicios que tendran disponibles los usuarios.
 */
public interface RolMenuItemService {
	
	/**
	 * Graba un rolMenuItem en la base de datos.
	 * @param unRolMenuItem, rolMenuItem a grabar.
	 */
	public void grabarRolMenuItem(RolMenuItem unRolMenuItem) throws RolMenuItemException;
	
	/**
	 * Obtiene un objeto rolMenuItem dado un id de rolMenuItem.
	 * @param id, Id del objeto buscado.
	 * @return Retorna un objeto RolMenuItem.
	 */
	public RolMenuItem leerRolMenuItem(Long id) throws RolMenuItemException;
	
	/**
	 * Obtiene todos los rolMenuItems almacenados en el base de datos.
	 * @return Retorna una lista de rolMenuItems.
	 */
	public List getRolMenuItems(Long idRol) throws RolMenuItemException;
	
	public List getRolMenuItems(Long idRol,Long idMenu) throws RolMenuItemException;	
	
	/**
	 * Borra un rolMenuItem dado su identificador.
	 * @param idRolMenuItem, identificador del rolMenuItem.
	 */
	public void borrarRolMenuItem(Long idRolMenuItem) throws RolMenuItemException;
	
	public void borrarRolMenuItem(RolMenuItem rolMenuItem) throws RolMenuItemException;	

}
