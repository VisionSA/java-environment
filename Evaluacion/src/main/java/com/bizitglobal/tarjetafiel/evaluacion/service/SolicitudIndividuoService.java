package com.bizitglobal.tarjetafiel.evaluacion.service;
import java.util.List;
import com.bizitglobal.tarjetafiel.commons.filtros.Filtro;
import com.bizitglobal.tarjetafiel.evaluacion.exception.SolicitudIndividuoException;
import com.bizitglobal.tarjetafiel.evaluacion.negocio.Solicitud;
import com.bizitglobal.tarjetafiel.evaluacion.negocio.SolicitudIndividuo;

/**
* @author BizItGlobal Generator
* Interface de servicios para las Evaluacion del sistema. Define un conjunto de puntos de entrada
* para utilizarse en la capa de presentación.
*/
public interface SolicitudIndividuoService {
	/**
	* Graba una SolicitudIndividuo en la base de datos.
	* @param pObject, objeto a grabar.
	*/
	public void grabarSolicitudIndividuo(SolicitudIndividuo pObject) throws SolicitudIndividuoException;
	/**
	* Obtiene una SolicitudIndividuo de la base de datos dado su id.
	* @param id, Identificador del objeto buscado.
	*/
	public SolicitudIndividuo leerSolicitudIndividuo(Long id) throws SolicitudIndividuoException;
	/**
	* Borra una SolicitudIndividuo de la base de datos dado su id.
	* @param id, Identificador del objeto a Borrar.
	*/
	public void borrarSolicitudIndividuo(Long id) throws SolicitudIndividuoException;
	/**
	* Borra una SolicitudIndividuo de la base de datos dado el mismo.
	* @param pObject, Identificador del objeto a Borrar.
	*/
	public void borrarSolicitudIndividuo(SolicitudIndividuo pObject) throws SolicitudIndividuoException;
	/**
	* Actualiza una SolicitudIndividuo de la base de datos.
	* @param pObject, Identificador del objeto a Actualizar.
	*/
	public void actualizarSolicitudIndividuo(SolicitudIndividuo pObject) throws SolicitudIndividuoException;
	/**
	* Obtiene una lista de todas las SolicitudIndividuo de la base de datos.
	* @return Una lista de objetos.
	*/
	public List getSolicitudIndividuo(Filtro filtro) throws SolicitudIndividuoException;
	/**
	 * Valida si ya fueron cargados adicionales existentes a la solicitud.
	 * @param Solicitud
	 * @return 
	 * @throws SolicitudIndividuoException
	 */
	public boolean isCargarAdicionales(Solicitud solicitud) throws SolicitudIndividuoException;
	
	/**
	 * Valida si ya fueron cargados garantes existentes a la solicitud.
	 * @param solicitud
	 * @return
	 * @throws SolicitudIndividuoException
	 */
	public boolean isCargarGarantes(Solicitud solicitud) throws SolicitudIndividuoException;
	
	
}

