package com.bizitglobal.tarjetafiel.impuestos.service;
import java.util.List;

import com.bizitglobal.tarjetafiel.commons.filtros.Filtro;
import com.bizitglobal.tarjetafiel.impuestos.exception.TipoImpuestoException;
import com.bizitglobal.tarjetafiel.impuestos.negocio.TipoImpuesto;

/**
* @author BizItGlobal Generator
* Interface de servicios para las Impuesto del sistema. Define un conjunto de puntos de entrada
* para utilizarse en la capa de presentación.
*/
public interface TipoImpuestoService {
	/**
	* Graba una TipoImpuesto en la base de datos.
	* @param pObject, objeto a grabar.
	*/
	public void grabarTipoImpuesto (TipoImpuesto pObject) throws TipoImpuestoException;
	/**
	* Obtiene una TipoImpuesto de la base de datos dado su id.
	* @param id, Identificador del objeto buscado.
	*/
	public TipoImpuesto leerTipoImpuesto (Long id) throws TipoImpuestoException;
	/**
	* Borra una TipoImpuesto de la base de datos dado su id.
	* @param id, Identificador del objeto a Borrar.
	*/
	public void borrarTipoImpuesto (Long id) throws TipoImpuestoException;
	/**
	* Borra una TipoImpuesto de la base de datos dado el mismo.
	* @param pObject, Identificador del objeto a Borrar.
	*/
	public void borrarTipoImpuesto (TipoImpuesto pObject) throws TipoImpuestoException;
	/**
	* Actualiza una TipoImpuesto de la base de datos.
	* @param pObject, Identificador del objeto a Actualizar.
	*/
	public void actualizarTipoImpuesto (TipoImpuesto pObject) throws TipoImpuestoException;
	/**
	* Obtiene una lista de todas las TipoImpuesto de la base de datos.
	* @return Una lista de objetos.
	*/
	public List getTipoImpuesto(Filtro filtro) throws TipoImpuestoException;
}

