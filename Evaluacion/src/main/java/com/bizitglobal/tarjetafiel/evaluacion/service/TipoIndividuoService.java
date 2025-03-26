package com.bizitglobal.tarjetafiel.evaluacion.service;
import java.util.List;
import java.util.Iterator;
import com.bizitglobal.tarjetafiel.commons.filtros.Filtro;
import com.bizitglobal.tarjetafiel.evaluacion.exception.TipoIndividuoException;
import com.bizitglobal.tarjetafiel.evaluacion.negocio.TipoIndividuo;

/**
* @author BizItGlobal Generator
* Interface de servicios para las Evaluacion del sistema. Define un conjunto de puntos de entrada
* para utilizarse en la capa de presentación.
*/
public interface TipoIndividuoService {
	/**
	* Graba una TipoIndividuo en la base de datos.
	* @param pObject, objeto a grabar.
	*/
	public void grabarTipoIndividuo(TipoIndividuo pObject) throws TipoIndividuoException;
	/**
	* Obtiene una TipoIndividuo de la base de datos dado su id.
	* @param id, Identificador del objeto buscado.
	*/
	public TipoIndividuo leerTipoIndividuo(Long id) throws TipoIndividuoException;
	/**
	* Borra una TipoIndividuo de la base de datos dado su id.
	* @param id, Identificador del objeto a Borrar.
	*/
	public void borrarTipoIndividuo(Long id) throws TipoIndividuoException;
	/**
	* Borra una TipoIndividuo de la base de datos dado el mismo.
	* @param pObject, Identificador del objeto a Borrar.
	*/
	public void borrarTipoIndividuo(TipoIndividuo pObject) throws TipoIndividuoException;
	/**
	* Actualiza una TipoIndividuo de la base de datos.
	* @param pObject, Identificador del objeto a Actualizar.
	*/
	public void actualizarTipoIndividuo(TipoIndividuo pObject) throws TipoIndividuoException;
	/**
	* Obtiene una lista de todas las TipoIndividuo de la base de datos.
	* @return Una lista de objetos.
	*/
	public List getTipoIndividuo(Filtro filtro) throws TipoIndividuoException;
}

