package com.bizitglobal.tarjetafiel.operador.service;

import java.util.List;

import com.bizitglobal.tarjetafiel.commons.filtros.Filtro;
import com.bizitglobal.tarjetafiel.operador.exeption.PaginaException;
import com.bizitglobal.tarjetafiel.operador.negocio.Pagina;


/**
 *	Define los servicios que tendran disponibles los usuarios.
 */
public interface PaginaService {
	
	/**
	 * Graba un pagina en la base de datos.
	 * @param unPagina, pagina a grabar.
	 */
	public void grabarPagina(Pagina unPagina) throws PaginaException;
	
	/**
	 * Obtiene un objeto pagina dado un id de pagina.
	 * @param id, Id del objeto buscado.
	 * @return Retorna un objeto Pagina.
	 */
	public Pagina leerPagina(Long id) throws PaginaException;
	
	/**
	 * Obtiene todos los paginas almacenados en el base de datos.
	 * @return Retorna una lista de paginas.
	 */
	public List getPaginas() throws PaginaException;
	
	public List getPaginas(Filtro filtro) throws PaginaException;	
	
	/**
	 * Borra un pagina dado su identificador.
	 * @param idPagina, identificador del pagina.
	 */
	public void borrarPagina(Long idPagina) throws PaginaException;

}
