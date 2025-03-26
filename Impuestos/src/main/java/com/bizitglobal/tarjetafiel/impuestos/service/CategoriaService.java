package com.bizitglobal.tarjetafiel.impuestos.service;

import java.util.List;

import com.bizitglobal.tarjetafiel.commons.filtros.Filtro;
import com.bizitglobal.tarjetafiel.impuestos.exception.CategoriaException;
import com.bizitglobal.tarjetafiel.impuestos.negocio.Categoria;

/**
 * @author Daniel
 * Interface de servicios para las categorias del sistema. Define un conjunto de puntos de entrada
 * para utilizarse en la capa de presentación.
 */
public interface CategoriaService {
	
	/**
	 * Graba una categoria en la base de datos.
	 * @param unaCategoria, Categoria a grabar.
	 */
	public void grabarCategoria(Categoria unaCategoria) throws CategoriaException;
	
	/**
	 * Obtiene una categoria de la base de datos dado su id.
	 * @param id, Identificador de la categoria buscada.
	 * @return La categoria buscada.
	 */
	public Categoria leerCategoria(Long id) throws CategoriaException;
	
	/**
	 * Borra una categoria de la base de datos dado su id.
	 * @param id, Identificador de la categoria.
	 */
	public void borrarCategoria(Long id) throws CategoriaException;
	
	/**
	 * Borra una categoria de la base de datos dado el mismo.
	 * @param unaCategoria, Categoria a borrar.
	 */
	public void borrarCategoria(Categoria unaCategoria) throws CategoriaException;
	
	/**
	 * Actualiza un proveedor en la base de datos.
	 * @param unProveedor, Proveedor a actualizar.
	 */
	public void actualizarCategoria(Categoria unaCategoria) throws CategoriaException;
	
	/**
	 * Obtiene una lista de todos las categorias.
	 * @return Una lista de categorias.
	 */
	public List getCategorias(Filtro unFiltro) throws CategoriaException;
	
}
