package com.bizitglobal.tarjetafiel.evaluacion.service;
import com.bizitglobal.tarjetafiel.evaluacion.exception.SolicLogException;
import com.bizitglobal.tarjetafiel.evaluacion.negocio.SolicLog;

/**
* @author BizItGlobal Generator
* Interface de servicios para las Evaluacion del sistema. Define un conjunto de puntos de entrada
* para utilizarse en la capa de presentación.
*/
public interface SolicLogService {
	/**
	* Graba una SolicLog en la base de datos.
	* @param pObject, objeto a grabar.
	*/
	public void grabarSolicLog(SolicLog pObject) throws SolicLogException;
	/**
	* Obtiene una SolicLog de la base de datos dado su id.
	* @param id, Identificador del objeto buscado.
	*/
	public SolicLog leerSolicLog(Long id) throws SolicLogException;
	/**
	* Borra una SolicLog de la base de datos dado su id.
	* @param id, Identificador del objeto a Borrar.
	*/
	public void borrarSolicLog(Long id) throws SolicLogException;
	/**
	* Borra una SolicLog de la base de datos dado el mismo.
	* @param pObject, Identificador del objeto a Borrar.
	*/
	public void borrarSolicLog(SolicLog pObject) throws SolicLogException;
	/**
	* Actualiza una SolicLog de la base de datos.
	* @param pObject, Identificador del objeto a Actualizar.
	*/
	public void actualizarSolicLog(SolicLog pObject) throws SolicLogException;
	/**
	* Obtiene una lista de todas las SolicLog de la base de datos.
	* @return Una lista de objetos.
	*/
//	public List getSolicLog(Filtro filtro) throws SolicLogException;
//	/**
//	 * Obtine un objeto SolicLog de la base de datos.
//	 * @param filtro, se utiliza para filtrar el nro de comprobantes que deceamos que nos traiga.
//	 * @return solcitud, retorna un objeto de la clase SolicLog que recupera de la base de datos.
//	 * @throws SolicLogException
//	 */
//	public SolicLog busNroComprobante(Filtro filtro) throws SolicLogException;
//	
//	public List generarSolicLoges(Operador operador, Long cantidad) throws SolicLogException;
}

