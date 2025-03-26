package com.bizitglobal.tarjetafiel.evaluacion.service;
import java.util.List;
import com.bizitglobal.tarjetafiel.commons.filtros.Filtro;
import com.bizitglobal.tarjetafiel.evaluacion.exception.EsquemaIndividuoException;
import com.bizitglobal.tarjetafiel.evaluacion.negocio.EsquemaIndividuo;

/**
* @author BizItGlobal Generator
* Interface de servicios para las Evaluacion del sistema. Define un conjunto de puntos de entrada
* para utilizarse en la capa de presentación.
*/
public interface EsquemaIndividuoService {
	/**
	* Graba una EsquemaIndividuo en la base de datos.
	* @param pObject, objeto a grabar.
	*/
	public void grabarEsquemaIndividuo (EsquemaIndividuo pObject) throws EsquemaIndividuoException;
	/**
	* Obtiene una EsquemaIndividuo de la base de datos dado su id.
	* @param id, Identificador del objeto buscado.
	*/
	public EsquemaIndividuo leerEsquemaIndividuo (Long id) throws EsquemaIndividuoException;
	/**
	* Borra una EsquemaIndividuo de la base de datos dado su id.
	* @param id, Identificador del objeto a Borrar.
	*/
	public void borrarEsquemaIndividuo (Long id) throws EsquemaIndividuoException;
	/**
	* Borra una EsquemaIndividuoo de la base de datos dado el mismo.
	* @param pObject, Identificador del objeto a Borrar.
	*/
	public void borrarEsquemaIndividuo(EsquemaIndividuo pObject) throws EsquemaIndividuoException;
	/**
	* Actualiza una EsquemaIndividuo de la base de datos.
	* @param pObject, Identificador del objeto a Actualizar.
	*/
	public void actualizarEsquemaIndividuo (EsquemaIndividuo pObject) throws EsquemaIndividuoException;
	/**
	* Obtiene una lista de todas las EsquemaIndividuo de la base de datos.
	* @return Una lista de objetos.
	*/
	public List getEsquemaIndividuo(Filtro filtro) throws EsquemaIndividuoException;
}

