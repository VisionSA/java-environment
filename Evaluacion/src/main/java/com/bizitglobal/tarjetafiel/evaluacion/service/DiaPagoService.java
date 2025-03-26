package com.bizitglobal.tarjetafiel.evaluacion.service;
import java.util.List;

import com.bizitglobal.tarjetafiel.commons.filtros.Filtro;
import com.bizitglobal.tarjetafiel.evaluacion.exception.DiaPagoException;
import com.bizitglobal.tarjetafiel.evaluacion.negocio.DiaPago;

/**
* @author BizItGlobal Generator
* Interface de servicios para las Evaluacion del sistema. Define un conjunto de puntos de entrada
* para utilizarse en la capa de presentación.
*/
public interface DiaPagoService {
	/**
	* Graba una DiaPago en la base de datos.
	* @param pObject, objeto a grabar.
	*/
	public void grabarDiaPago (DiaPago pObject) throws DiaPagoException;
	/**
	* Obtiene una DiaPago de la base de datos dado su id.
	* @param id, Identificador del objeto buscado.
	*/
	public DiaPago leerDiaPago (Long id) throws DiaPagoException;
	/**
	* Borra una DiaPago de la base de datos dado su id.
	* @param id, Identificador del objeto a Borrar.
	*/
	public void borrarDiaPago (Long id) throws DiaPagoException;
	/**
	* Borra una DiaPago de la base de datos dado el mismo.
	* @param pObject, Identificador del objeto a Borrar.
	*/
	public void borrarDiaPago (DiaPago pObject) throws DiaPagoException;
	/**
	* Actualiza una DiaPago de la base de datos.
	* @param pObject, Identificador del objeto a Actualizar.
	*/
	public void actualizarDiaPago (DiaPago pObject) throws DiaPagoException;
	/**
	* Obtiene una lista de todas las DiaPago de la base de datos.
	* @return Una lista de objetos.
	*/
	public List getDiaPago(Filtro filtro) throws DiaPagoException;
	
	
	public DiaPago getDiaPagoByIdCliente(Long idCliente) throws DiaPagoException;
	
}

