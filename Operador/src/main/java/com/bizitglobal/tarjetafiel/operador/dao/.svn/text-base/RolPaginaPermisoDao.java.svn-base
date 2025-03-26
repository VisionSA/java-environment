package com.bizitglobal.tarjetafiel.operador.dao;

import java.util.List;

import com.bizitglobal.tarjetafiel.operador.negocio.*;

/**
 * Interface que se encarga de definir las operaciones que se podran efectuar con la base de datos.
 */
public interface RolPaginaPermisoDao {
	
	/**
	 * Graba un rolPagina en la base de datos.
	 * @param unRolPagina, RolPagina a grabar en la base de datos.
	 */
	public void saveRolPaginaPermiso(RolPaginaPermiso unRolPaginaPermiso);
	
	/**
	 * Obtiene un rolPagina de la base de datos, dado un id de rolPagina.
	 * @param id, Id del rolPagina buscado.
	 * @return Retorna el rolPagina buscado.
	 */
	public RolPaginaPermiso findRolPaginaPermiso(Long id);
	
	/**
	 * Borra un rolPagina de la base de datos, dado un id de rolPagina.
	 * @param id, Id del rolPagina a borrar.
	 */
	public void deleteRolPaginaPermiso(Long id);
	
	/**
	 * Borra un rolPagina de la base de datos, dado el mismo rolPagina.
	 * @param unRolPagina, RolPagina a borrar.
	 */
	public void deleteRolPaginaPermiso(RolPaginaPermiso unRolPaginaPermiso);
	
	
	/**
	 * Actualiza un rolPagina existente en el base de datos.
	 * @param unRolPagina, RolPagina a actualizar.
	 */
	public void updateRolPaginaPermiso(RolPaginaPermiso unRolPaginaPermiso);
	
	/**
	 * Obtiene una lista con todos los rolPaginas del sistema.
	 * @return Retorna una lista con todos los rolPaginas.
	 */
	public List listAll(String pagina, Long idRol);
	
	public List listAll(Long idRol);	
	
}
