package com.bizitglobal.tarjetafiel.impuestos.service;
import java.util.List;

import com.bizitglobal.tarjetafiel.commons.filtros.Filtro;
import com.bizitglobal.tarjetafiel.impuestos.exception.JurisTipoImpuestoException;
import com.bizitglobal.tarjetafiel.impuestos.negocio.JurisTipoImpuesto;


/**
* @author BizItGlobal Generator
* Interface de servicios para las Impuesto del sistema. Define un conjunto de puntos de entrada
* para utilizarse en la capa de presentación.
*/
public interface JurisTipoImpuestoService {
	/**
	* Graba una TipoImpuesto en la base de datos.
	* @param pObject, objeto a grabar.
	*/
	public void grabarJurisTipoImpuesto (JurisTipoImpuesto pObject) throws JurisTipoImpuestoException;
	/**
	* Obtiene una TipoImpuesto de la base de datos dado su id.
	* @param id, Identificador del objeto buscado.
	*/
	public JurisTipoImpuesto leerJurisTipoImpuesto (Long id) throws JurisTipoImpuestoException;
	/**
	* Borra una TipoImpuesto de la base de datos dado su id.
	* @param id, Identificador del objeto a Borrar.
	*/
	public void borrarJurisTipoImpuesto (Long id) throws JurisTipoImpuestoException;
	/**
	* Borra una TipoImpuesto de la base de datos dado el mismo.
	* @param pObject, Identificador del objeto a Borrar.
	*/
	public void borrarJurisTipoImpuesto (JurisTipoImpuesto pObject) throws JurisTipoImpuestoException;
	/**
	* Actualiza una TipoImpuesto de la base de datos.
	* @param pObject, Identificador del objeto a Actualizar.
	*/
	public void actualizarJurisTipoImpuesto (JurisTipoImpuesto pObject) throws JurisTipoImpuestoException;
	/**
	* Obtiene una lista de todas las TipoImpuesto de la base de datos.
	* @return Una lista de objetos.
	*/
	public List getJurisTipoImpuesto(Filtro filtro) throws JurisTipoImpuestoException;
}

