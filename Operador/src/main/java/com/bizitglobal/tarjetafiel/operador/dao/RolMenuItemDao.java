package com.bizitglobal.tarjetafiel.operador.dao;

import java.util.List;

import com.bizitglobal.tarjetafiel.operador.negocio.*;

/**
 * Interface que se encarga de definir las operaciones que se podran efectuar con la base de datos.
 */
public interface RolMenuItemDao {
	
	/**
	 * Graba un rolMenuItem en la base de datos.
	 * @param unRolMenuItem, RolMenuItem a grabar en la base de datos.
	 */
	public void saveRolMenuItem(RolMenuItem unRolMenuItem);
	
	/**
	 * Obtiene un rolMenuItem de la base de datos, dado un id de rolMenuItem.
	 * @param id, Id del rolMenuItem buscado.
	 * @return Retorna el rolMenuItem buscado.
	 */
	public RolMenuItem findRolMenuItem(Long id);
	
	/**
	 * Borra un rolMenuItem de la base de datos, dado un id de rolMenuItem.
	 * @param id, Id del rolMenuItem a borrar.
	 */
	public void deleteRolMenuItem(Long id);
	
	/**
	 * Borra un rolMenuItem de la base de datos, dado el mismo rolMenuItem.
	 * @param unRolMenuItem, RolMenuItem a borrar.
	 */
	public void deleteRolMenuItem(RolMenuItem unRolMenuItem);
	
	
	/**
	 * Actualiza un rolMenuItem existente en el base de datos.
	 * @param unRolMenuItem, RolMenuItem a actualizar.
	 */
	public void updateRolMenuItem(RolMenuItem unRolMenuItem);
	
	/**
	 * Obtiene una lista con todos los rolMenuItems del sistema.
	 * @return Retorna una lista con todos los rolMenuItems.
	 */
	public List listAll(Long idRol);
	
	/**
	 * Obtiene una lista con todos los rolMenuItems del sistema.
	 * @return Retorna una lista con todos los rolMenuItems.
	 */
	public List listAll(Long idRol,Long idMenu);	
	
}
