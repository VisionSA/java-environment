package com.bizitglobal.tarjetafiel.proveedores.service;

import java.util.List;

import com.bizitglobal.tarjetafiel.commons.filtros.Filtro;
import com.bizitglobal.tarjetafiel.proveedores.exception.GrupoException;
import com.bizitglobal.tarjetafiel.proveedores.negocio.Grupo;

/**
 * @author Daniel
 * Interface de servicios para los grupos del sistema. Define un conjunto de puntos de entrada
 * para utilizarse en la capa de presentación.
 */
public interface GrupoService {
	
	/**
	 * Graba un grupo en la base de datos.
	 * @param unGrupo, Grupo a grabar.
	 */
	public void grabarGrupo(Grupo unGrupo) throws GrupoException;
	
	/**
	 * Obtiene un grupo de la base de datos dado su id.
	 * @param id, Identificador del grupo buscado.
	 * @return El grupo buscado.
	 */
	public Grupo leerGrupo(Long id) throws GrupoException;
	
	/**
	 * Borra un grupo de la base de datos dado su id.
	 * @param id, Identificador del grupo.
	 */
	public void borrarGrupo(Long id) throws GrupoException;
	
	/**
	 * Borra un grupo de la base de datos dado el mismo.
	 * @param unGrupo, Grupo a borrar.
	 */
	public void borrarGrupo(Grupo unGrupo) throws GrupoException;
	
	/**
	 * Actualiza un grupo en la base de datos.
	 * @param unGrupo, grupo a actualizar.
	 */
	public void actualizarGrupo(Grupo unGrupo) throws GrupoException;
	
	/**
	 * Obtiene una lista de todos los grupos.
	 * @return Una lista de grupos.
	 */
	public List getGrupos(Filtro filtro) throws GrupoException;
	
}
