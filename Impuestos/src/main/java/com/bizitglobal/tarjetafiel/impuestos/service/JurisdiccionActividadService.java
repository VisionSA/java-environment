package com.bizitglobal.tarjetafiel.impuestos.service;

import java.util.List;

import com.bizitglobal.tarjetafiel.commons.filtros.Filtro;
import com.bizitglobal.tarjetafiel.impuestos.exception.JurisdiccionActividadException;
import com.bizitglobal.tarjetafiel.impuestos.negocio.JurisdiccionActividad;

/**
 * @author Daniel
 * Interface de servicios para las categorias del sistema. Define un conjunto de puntos de entrada
 * para utilizarse en la capa de presentación.
 */
public interface JurisdiccionActividadService {
	
	/**
	 * Graba una categoria en la base de datos.
	 * @param unaJurisdiccionActividad, JurisdiccionActividad a grabar.
	 */
	public void grabarJurisdiccionActividad(JurisdiccionActividad unaJurisdiccionActividad) throws JurisdiccionActividadException;
	
	/**
	 * Obtiene una categoria de la base de datos dado su id.
	 * @param id, Identificador de la categoria buscada.
	 * @return La categoria buscada.
	 */
	public JurisdiccionActividad leerJurisdiccionActividad(Long id) throws JurisdiccionActividadException;
	
	/**
	 * Borra una categoria de la base de datos dado su id.
	 * @param id, Identificador de la categoria.
	 */
	public void borrarJurisdiccionActividad(Long id) throws JurisdiccionActividadException;
	
	/**
	 * Borra una categoria de la base de datos dado el mismo.
	 * @param unaJurisdiccionActividad, JurisdiccionActividad a borrar.
	 */
	public void borrarJurisdiccionActividad(JurisdiccionActividad unaJurisdiccionActividad) throws JurisdiccionActividadException;
	
	/**
	 * Actualiza un proveedor en la base de datos.
	 * @param unProveedor, Proveedor a actualizar.
	 */
	public void actualizarJurisdiccionActividad(JurisdiccionActividad unaJurisdiccionActividad) throws JurisdiccionActividadException;
	
	/**
	 * Obtiene una lista de todos las categorias.
	 * @return Una lista de categorias.
	 */
	public List getJurisdiccionActividads(Filtro unFiltro) throws JurisdiccionActividadException;
	
}
