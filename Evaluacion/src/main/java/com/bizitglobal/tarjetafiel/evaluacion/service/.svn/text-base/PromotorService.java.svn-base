package com.bizitglobal.tarjetafiel.evaluacion.service;
import java.util.List;
import java.util.Iterator;
import com.bizitglobal.tarjetafiel.commons.filtros.Filtro;
import com.bizitglobal.tarjetafiel.evaluacion.exception.PromotorException;
import com.bizitglobal.tarjetafiel.evaluacion.negocio.Promotor;

/**
* @author BizItGlobal Generator
* Interface de servicios para las Evaluacion del sistema. Define un conjunto de puntos de entrada
* para utilizarse en la capa de presentación.
*/
public interface PromotorService {
	/**
	* Graba una Promotor en la base de datos.
	* @param pObject, objeto a grabar.
	*/
	public void grabarPromotor(Promotor pObject) throws PromotorException;
	/**
	* Obtiene una Promotor de la base de datos dado su id.
	* @param id, Identificador del objeto buscado.
	*/
	public Promotor leerPromotor(Long id) throws PromotorException;
	/**
	* Borra una Promotor de la base de datos dado su id.
	* @param id, Identificador del objeto a Borrar.
	*/
	public void borrarPromotor(Long id) throws PromotorException;
	/**
	* Borra una Promotor de la base de datos dado el mismo.
	* @param pObject, Identificador del objeto a Borrar.
	*/
	public void borrarPromotor(Promotor pObject) throws PromotorException;
	/**
	* Actualiza una Promotor de la base de datos.
	* @param pObject, Identificador del objeto a Actualizar.
	*/
	public void actualizarPromotor(Promotor pObject) throws PromotorException;
	/**
	* Obtiene una lista de todas las Promotor de la base de datos.
	* @return Una lista de objetos.
	*/
	public List getPromotor(Filtro filtro) throws PromotorException;
}

