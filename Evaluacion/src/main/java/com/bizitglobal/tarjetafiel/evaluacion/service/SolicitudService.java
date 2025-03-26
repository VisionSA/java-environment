package com.bizitglobal.tarjetafiel.evaluacion.service;
import java.util.List;
import java.util.Iterator;
import com.bizitglobal.tarjetafiel.commons.filtros.Filtro;
import com.bizitglobal.tarjetafiel.evaluacion.exception.SolicitudException;
import com.bizitglobal.tarjetafiel.evaluacion.negocio.Solicitud;
import com.bizitglobal.tarjetafiel.operador.negocio.Operador;

/**
* @author BizItGlobal Generator
* Interface de servicios para las Evaluacion del sistema. Define un conjunto de puntos de entrada
* para utilizarse en la capa de presentación.
*/
public interface SolicitudService {
	/**
	* Graba una Solicitud en la base de datos.
	* @param pObject, objeto a grabar.
	*/
	public void grabarSolicitud(Solicitud pObject) throws SolicitudException;
	/**
	* Obtiene una Solicitud de la base de datos dado su id.
	* @param id, Identificador del objeto buscado.
	*/
	public Solicitud leerSolicitud(Long id) throws SolicitudException;
	/**
	* Borra una Solicitud de la base de datos dado su id.
	* @param id, Identificador del objeto a Borrar.
	*/
	public void borrarSolicitud(Long id) throws SolicitudException;
	/**
	* Borra una Solicitud de la base de datos dado el mismo.
	* @param pObject, Identificador del objeto a Borrar.
	*/
	public void borrarSolicitud(Solicitud pObject) throws SolicitudException;
	/**
	* Actualiza una Solicitud de la base de datos.
	* @param pObject, Identificador del objeto a Actualizar.
	*/
	public void actualizarSolicitud(Solicitud pObject) throws SolicitudException;
	/**
	* Obtiene una lista de todas las Solicitud de la base de datos.
	* @return Una lista de objetos.
	*/
	public List getSolicitud(Filtro filtro) throws SolicitudException;
	/**
	 * Obtine un objeto Solicitud de la base de datos.
	 * @param filtro, se utiliza para filtrar el nro de comprobantes que deceamos que nos traiga.
	 * @return solcitud, retorna un objeto de la clase solicitud que recupera de la base de datos.
	 * @throws SolicitudException
	 */
	public Solicitud busNroComprobante(Filtro filtro) throws SolicitudException;
	
	public List generarSolicitudes(Operador operador, Long cantidad) throws SolicitudException;
}

