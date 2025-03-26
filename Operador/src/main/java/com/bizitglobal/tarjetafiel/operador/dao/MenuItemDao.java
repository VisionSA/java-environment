package com.bizitglobal.tarjetafiel.operador.dao;

import java.util.List;

import com.bizitglobal.tarjetafiel.operador.negocio.*;

/**
 * Interface que se encarga de definir las operaciones que se podran efectuar con la base de datos.
 */
public interface MenuItemDao {
	
	/**
	 * Graba un MenuItem en la base de datos.
	 * @param unMenuItem, MenuItem a grabar en la base de datos.
	 */
	public void saveMenuItem(MenuItem unMenuItem);
	
	/**
	 * Obtiene un MenuItem de la base de datos, dado un id de MenuItem.
	 * @param id, Id del MenuItem buscado.
	 * @return Retorna el MenuItem buscado.
	 */
	public MenuItem findMenuItem(Long id);
	
	/**
	 * Borra un MenuItem de la base de datos, dado un id de MenuItem.
	 * @param id, Id del MenuItem a borrar.
	 */
	public void deleteMenuItem(Long id);
	
	/**
	 * Borra un MenuItem de la base de datos, dado el mismo MenuItem.
	 * @param unMenuItem, MenuItem a borrar.
	 */
	public void deleteMenuItem(MenuItem unMenuItem);
	
	
	/**
	 * Actualiza un MenuItem existente en el base de datos.
	 * @param unMenuItem, MenuItem a actualizar.
	 */
	public void updateMenuItem(MenuItem unMenuItem);
	
	/**
	 * Obtiene una lista con todos los MenuItems del sistema.
	 * @return Retorna una lista con todos los MenuItems.
	 */
	public List listAll();
	
}
