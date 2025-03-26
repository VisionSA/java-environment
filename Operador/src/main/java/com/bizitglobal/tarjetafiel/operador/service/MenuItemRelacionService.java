package com.bizitglobal.tarjetafiel.operador.service;

import java.util.List;

import com.bizitglobal.tarjetafiel.operador.exeption.MenuItemRelacionException;
import com.bizitglobal.tarjetafiel.operador.negocio.MenuItemRelacion;


/**
 *	Define los servicios que tendran disponibles los usuarios.
 */
public interface MenuItemRelacionService {
	
	/**
	 * Graba un MenuItemRelacion en la base de datos.
	 * @param unMenuItemRelacion, MenuItemRelacion a grabar.
	 */
	public void grabarMenuItemRelacion(MenuItemRelacion unMenuItemRelacion) throws MenuItemRelacionException;
	
	/**
	 * Obtiene un objeto MenuItemRelacion dado un id de MenuItemRelacion.
	 * @param id, Id del objeto buscado.
	 * @return Retorna un objeto MenuItemRelacion.
	 */
	public MenuItemRelacion leerMenuItemRelacion(Long id) throws MenuItemRelacionException;
	
	/**
	 * Obtiene todos los MenuItemRelaciones almacenados en el base de datos.
	 * @return Retorna una lista de MenuItemRelaciones.
	 */
	public List getMenuItemRelaciones() throws MenuItemRelacionException;
	
	/**
	 * Borra un MenuItemRelacion dado su identificador.
	 * @param idMenuItemRelacion, identificador del MenuItemRelacion.
	 */
	public void borrarMenuItemRelacion(Long idMenuItemRelacion) throws MenuItemRelacionException;

}
