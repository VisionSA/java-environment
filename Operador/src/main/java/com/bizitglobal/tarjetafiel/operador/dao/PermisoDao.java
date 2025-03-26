package com.bizitglobal.tarjetafiel.operador.dao;

import java.util.List;

import com.bizitglobal.tarjetafiel.operador.negocio.*;

/**
 * Interface que se encarga de definir las operaciones que se podran efectuar con la base de datos.
 */
public interface PermisoDao {
	
	/**
	 * Graba un permiso en la base de datos.
	 * @param unPermiso, Permiso a grabar en la base de datos.
	 */
	public void savePermiso(Permiso unPermiso);
	
	/**
	 * Obtiene un permiso de la base de datos, dado un id de permiso.
	 * @param id, Id del permiso buscado.
	 * @return Retorna el permiso buscado.
	 */
	public Permiso findPermiso(Long id);
	
	/**
	 * Borra un permiso de la base de datos, dado un id de permiso.
	 * @param id, Id del permiso a borrar.
	 */
	public void deletePermiso(Long id);
	
	/**
	 * Borra un permiso de la base de datos, dado el mismo permiso.
	 * @param unPermiso, Permiso a borrar.
	 */
	public void deletePermiso(Permiso unPermiso);
	
	
	/**
	 * Actualiza un permiso existente en el base de datos.
	 * @param unPermiso, Permiso a actualizar.
	 */
	public void updatePermiso(Permiso unPermiso);
	
	/**
	 * Obtiene una lista con todos los permisos del sistema.
	 * @return Retorna una lista con todos los permisos.
	 */
	public List listAll();
	
}
