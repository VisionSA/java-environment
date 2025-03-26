package com.bizitglobal.tarjetafiel.cobranzas.service;

import java.util.List;

import com.bizitglobal.tarjetafiel.commons.filtros.Filtro;
import com.bizitglobal.tarjetafiel.cobranzas.exception.EjecucionPlanException;
import com.bizitglobal.tarjetafiel.cobranzas.negocio.EjecucionPlan;
import com.bizitglobal.tarjetafiel.cobranzas.negocio.Moroso;
import com.bizitglobal.tarjetafiel.cobranzas.negocio.Plan;
import com.bizitglobal.tarjetafiel.transacciones.negocio.Abogado;
import com.bizitglobal.tarjetafiel.transacciones.negocio.ClienteLiquidacion;

public interface EjecucionPlanService {
	/**
	* Graba una EjecucionPlan en la base de datos.
	* @param pObject, objeto a grabar.
	*/
	public void grabarEjecucionPlan (EjecucionPlan pObject) throws EjecucionPlanException;
	/**
	* Obtiene una EjecucionPlan de la base de datos dado su id.
	* @param id, Identificador del objeto buscado.
	*/
	public EjecucionPlan leerEjecucionPlan (Long id) throws EjecucionPlanException;
	/**
	* Borra una EjecucionPlan de la base de datos dado su id.
	* @param id, Identificador del objeto a Borrar.
	*/
	public void borrarEjecucionPlan (Long id) throws EjecucionPlanException;
	/**
	* Borra una EjecucionPlan de la base de datos dado el mismo.
	* @param pObject, Identificador del objeto a Borrar.
	*/
	public void borrarEjecucionPlan (EjecucionPlan pObject) throws EjecucionPlanException;
	/**
	* Actualiza una EjecucionPlan de la base de datos.
	* @param pObject, Identificador del objeto a Actualizar.
	*/
	public void actualizarEjecucionPlan (EjecucionPlan pObject) throws EjecucionPlanException;
	/**
	* Obtiene una lista de todas las EjecucionPlan de la base de datos.
	* @return Una lista de objetos.
	*/
	public List getEjecucionPlan(Filtro filtro) throws EjecucionPlanException;
	
	/**
	 * Lista los clientes que entran en mora. (se identifican porque tienen el c_id_plan_mora en  null.
	 * @param montoDeGracia Un monto de tolerancia, para que no recupere clientes que adeudan centavos nada mas, poniendolos  en mora.
	 * @return Una lista de objetos Long, con el id de cada cliente al cual se lo debe iniciar en la mora,
	 * seteandole el id_plan_mora y la fecha de entrada en mora.
	 * */
/*@I5597*/	public List<ClienteLiquidacion> listarIdClientesQueEntranEnMora(final Double montoDeGracia) throws EjecucionPlanException;
/*@F5597*/	
	/**
	 * Realiza un update del cliente poniendole el id del plan de mora, 
	 * la fecha del dia como inicio para el id de cliente pasado
	 * */
	public void asociarPlanACliente(final Long idCliente, final Plan plan);
	
	
	/**
	 * Devuelve los clientes en mora
	 * */
	public List<Moroso> getClientesEnMora();
	
	
	/**
	* Graba un Objeto en la base de datos.
	* @param pObject, objeto a grabar.
	*/
	public void grabarObjeto (Object pObject) throws EjecucionPlanException;
	
	
	/**
	* Ejecuta la query que le espasada por parametro.
	* @param query, query a ejecutar.
	*/
	public void ejecutarQuery(String query) throws EjecucionPlanException;
	
	/** llama al store procedure que recopila los pagos realizados por clientes, indicando si corresponden a un cobrador o no.
	 * @param mesInicial El mes desde el cual se quiere sacar el resumen
	 * @param mesFinal El mes hasta, el cual se quiere el resumen 
	 * @param anioInicial El anio, desde el cual se quiere el resumen
	 * @param anioFinal El anio hasta el cual se quiere el resumen
	 * */
	public Boolean procesarPagosRealizados(int mesInicial, int mesFinal, int anioInicial, int anioFinal)throws EjecucionPlanException;
	
	public void cambiarEstadoYAddFile(Long idEjecucionPlan, String confirmoAccion,
			StringBuffer pathRelativoPDFCobradores) throws EjecucionPlanException ;
	
	
	public Long cambiarCobrador(Long idCobrador,Long idEjecPlan) throws EjecucionPlanException;
	
	public void cambiarCobradoresTareasPendientes(Long idCobIN, Long idCobOUT, Long idPartido) throws EjecucionPlanException;
	
	public void cambiarAbogadosTareasPendientes(Long idAbogIN, Long idAbogOUT, Long idPartido) throws EjecucionPlanException;
	
	public Abogado buscarAbogadoTarea(Long idEjecucionPlan) throws EjecucionPlanException;
	
	public void cambiarAbogadoClienteTarea(Long idEjecucionPlan, Long idAbogIN) throws EjecucionPlanException;
	
	public void insertarHistoricoMora(Long idCliente);
	
	public Long getIdEjecPlanCobradorAsignado(Long idCliente);
	
	void insertarErrorEjecucion(Long idCliente, Long idAccion,
			String descripcion);
	
	
}
