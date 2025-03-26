package com.bizitglobal.tarjetafiel.operador.dao;

import java.util.List;

import com.bizitglobal.tarjetafiel.commons.filtros.Filtro;
import com.bizitglobal.tarjetafiel.operador.negocio.*;

/**
 * Interface que se encarga de definir las operaciones que se podran efectuar con la base de datos.
 */
public interface PaginaDao {
	
	/**
	 * Graba un pagina en la base de datos.
	 * @param unPagina, Pagina a grabar en la base de datos.
	 */
	public void savePagina(Pagina unPagina);
	
	/**
	 * Obtiene un pagina de la base de datos, dado un id de pagina.
	 * @param id, Id del pagina buscado.
	 * @return Retorna el pagina buscado.
	 */
	public Pagina findPagina(Long id);
	
	/**
	 * Borra un pagina de la base de datos, dado un id de pagina.
	 * @param id, Id del pagina a borrar.
	 */
	public void deletePagina(Long id);
	
	/**
	 * Borra un pagina de la base de datos, dado el mismo pagina.
	 * @param unPagina, Pagina a borrar.
	 */
	public void deletePagina(Pagina unPagina);
	
	
	/**
	 * Actualiza un pagina existente en el base de datos.
	 * @param unPagina, Pagina a actualizar.
	 */
	public void updatePagina(Pagina unPagina);
	
	/**
	 * Obtiene una lista con todos los paginas del sistema.
	 * @return Retorna una lista con todos las paginas.
	 */
	public List listAll();

	public List listAll(Filtro filtro);
}
