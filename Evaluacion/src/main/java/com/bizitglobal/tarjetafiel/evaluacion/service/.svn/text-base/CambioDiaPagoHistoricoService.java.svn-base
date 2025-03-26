package com.bizitglobal.tarjetafiel.evaluacion.service;
import java.util.List;
import java.util.Iterator;
import com.bizitglobal.tarjetafiel.commons.filtros.Filtro;
import com.bizitglobal.tarjetafiel.evaluacion.exception.CambioDiaPagoHistoricoException;
import com.bizitglobal.tarjetafiel.evaluacion.negocio.CambioDiaPagoHistorico;

/**
* @author BizItGlobal Generator
* Interface de servicios para las Evaluacion del sistema. Define un conjunto de puntos de entrada
* para utilizarse en la capa de presentación.
*/
public interface CambioDiaPagoHistoricoService {
	/**
	* Graba una CambioDiaPagoHistorico en la base de datos.
	* @param pObject, objeto a grabar.
	*/
	public void grabarCambioDiaPagoHistorico (CambioDiaPagoHistorico pObject) throws CambioDiaPagoHistoricoException;
	/**
	* Obtiene una CambioDiaPagoHistorico de la base de datos dado su id.
	* @param id, Identificador del objeto buscado.
	*/
	public CambioDiaPagoHistorico leerCambioDiaPagoHistorico (Long id) throws CambioDiaPagoHistoricoException;
	/**
	* Borra una CambioDiaPagoHistorico de la base de datos dado su id.
	* @param id, Identificador del objeto a Borrar.
	*/
	public void borrarCambioDiaPagoHistorico (Long id) throws CambioDiaPagoHistoricoException;
	/**
	* Borra una CambioDiaPagoHistorico de la base de datos dado el mismo.
	* @param pObject, Identificador del objeto a Borrar.
	*/
	public void borrarCambioDiaPagoHistorico (CambioDiaPagoHistorico pObject) throws CambioDiaPagoHistoricoException;
	/**
	* Obtiene una lista de todas las CambioDiaPagoHistorico de la base de datos.
	* @return Una lista de objetos.
	*/
	public List getCambioDiaPagoHistorico(Filtro filtro) throws CambioDiaPagoHistoricoException;
	
	/**
	 * Obtiene el id del dia de pago, del ultimo cambio del cliente.
	 * @param idCliente
	 * @return
	 * @throws CambioDiaPagoHistoricoException
	 */
	public Long buscarIdDiaPagoUltimaCambio(Long idCliente)throws CambioDiaPagoHistoricoException;
}

