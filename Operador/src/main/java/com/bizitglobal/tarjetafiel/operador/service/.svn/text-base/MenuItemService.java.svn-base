package com.bizitglobal.tarjetafiel.operador.service;

import java.util.List;

import com.bizitglobal.tarjetafiel.operador.exeption.MenuItemException;
import com.bizitglobal.tarjetafiel.operador.negocio.MenuItem;


/**
 *	Define los servicios que tendran disponibles los usuarios.
 */
public interface MenuItemService {
	
	/**
	 * Graba un menuItem en la base de datos.
	 * @param unMenuItem, menuItem a grabar.
	 */
	public void grabarMenuItem(MenuItem unMenuItem) throws MenuItemException;
	
	/**
	 * Obtiene un objeto menuItem dado un id de menuItem.
	 * @param id, Id del objeto buscado.
	 * @return Retorna un objeto MenuItem.
	 */
	public MenuItem leerMenuItem(Long id) throws MenuItemException;
	
	/**
	 * Obtiene todos los menuItems almacenados en el base de datos.
	 * @return Retorna una lista de menuItems.
	 */
	public List getMenuItems() throws MenuItemException;
	
	/**
	 * Borra un menuItem dado su identificador.
	 * @param idMenuItem, identificador del menuItem.
	 */
	public void borrarMenuItem(Long idMenuItem) throws MenuItemException;

}
