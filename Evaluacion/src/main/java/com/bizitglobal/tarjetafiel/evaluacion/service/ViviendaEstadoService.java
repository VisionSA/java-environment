package com.bizitglobal.tarjetafiel.evaluacion.service;
import java.util.List;
import java.util.Iterator;
import com.bizitglobal.tarjetafiel.commons.filtros.Filtro;
import com.bizitglobal.tarjetafiel.evaluacion.exception.ViviendaEstadoException;
import com.bizitglobal.tarjetafiel.evaluacion.negocio.ViviendaEstado;

/**
* @author BizItGlobal Generator
* Interface de servicios para las Evaluacion del sistema. Define un conjunto de puntos de entrada
* para utilizarse en la capa de presentación.
*/
public interface ViviendaEstadoService {
	/**
	* Graba una ViviendaEstado en la base de datos.
	* @param pObject, objeto a grabar.
	*/
	public void grabarViviendaEstado(ViviendaEstado pObject) throws ViviendaEstadoException;
	/**
	* Obtiene una ViviendaEstado de la base de datos dado su id.
	* @param id, Identificador del objeto buscado.
	*/
	public ViviendaEstado leerViviendaEstado(Long id) throws ViviendaEstadoException;
	/**
	* Borra una ViviendaEstado de la base de datos dado su id.
	* @param id, Identificador del objeto a Borrar.
	*/
	public void borrarViviendaEstado(Long id) throws ViviendaEstadoException;
	/**
	* Borra una ViviendaEstado de la base de datos dado el mismo.
	* @param pObject, Identificador del objeto a Borrar.
	*/
	public void borrarViviendaEstado(ViviendaEstado pObject) throws ViviendaEstadoException;
	/**
	* Actualiza una ViviendaEstado de la base de datos.
	* @param pObject, Identificador del objeto a Actualizar.
	*/
	public void actualizarViviendaEstado(ViviendaEstado pObject) throws ViviendaEstadoException;
	/**
	* Obtiene una lista de todas las ViviendaEstado de la base de datos.
	* @return Una lista de objetos.
	*/
	public List getViviendaEstado(Filtro filtro) throws ViviendaEstadoException;
}

