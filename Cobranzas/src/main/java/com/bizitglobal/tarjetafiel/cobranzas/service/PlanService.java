package com.bizitglobal.tarjetafiel.cobranzas.service;

import java.util.List;

import com.bizitglobal.tarjetafiel.commons.filtros.Filtro;
import com.bizitglobal.tarjetafiel.cobranzas.exception.PlanException;
import com.bizitglobal.tarjetafiel.cobranzas.negocio.AdministradorDePlanes;
import com.bizitglobal.tarjetafiel.cobranzas.negocio.Plan;
import com.bizitglobal.tarjetafiel.cobranzas.negocio.PlanVersion;

public interface PlanService {
	/**
	* Graba una Plan en la base de datos.
	* @param pObject, objeto a grabar.
	*/
	public List<String> grabarPlan (Plan pObject) throws PlanException;
	/**
	* Obtiene una Plan de la base de datos dado su id.
	* @param id, Identificador del objeto buscado.
	*/
	public Plan leerPlan (Long id) throws PlanException;
	/**
	* Borra una Plan de la base de datos dado su id.
	* @param id, Identificador del objeto a Borrar.
	*/
	public void borrarPlan (Long id) throws PlanException;
	/**
	* Borra una Plan de la base de datos dado el mismo.
	* @param pObject, Identificador del objeto a Borrar.
	*/
	public Object[] borrarPlan (Plan pObject) throws PlanException;
	/**
	* Actualiza una Plan de la base de datos.
	* @param pObject, Identificador del objeto a Actualizar.
	*/
	public void actualizarPlan (Plan pObject) throws PlanException;
	/**
	* Obtiene una lista de todas las Plan de la base de datos.
	* @return Una lista de objetos.
	*/
	public List getPlan(Filtro filtro) throws PlanException;
	
	
	/**
	 * Este metodo devuelve un Plan, preseteado con las etapas, y cada etapa con su accion obligatoria
	 * @return Un nuevo Plan
	 * */
	public Plan crearPlan() throws PlanException;
	
	/**
	 * Devuelve una instancia de AdministradorDePlanes configurado con todos los planes con sus clientes correspondientes, y aparte el plan por defecto.
	 * */
	public AdministradorDePlanes getAdministradorDePlanes() throws PlanException;
	

	/**
	 * De ser posible, guarda una nueva version del plan.
	 * */
	public List<String> guardarNuevaVersion(Plan plan, PlanVersion planVersion) throws PlanException;
	
	public Object[] borrarVersion(final PlanVersion planVersion) throws PlanException;
	
	
	/**
	 * Cambia el estado del plan. de desabilitado a habilitado y viceversa.
	 * */
	public void cambiarEstadoPlan(final Plan plan) throws PlanException;
	
	/**
	 * Cambia el plan que esta marcado por defecto.
	 * */
	public void marcarPlanPorDefecto(final Plan plan) throws PlanException;
	
	/**
	 * Obtiene los planes segun filtro
	 * Este método solo retorna los datos básicos
	 */
	public List getPlanes(Filtro filtro) throws PlanException;
	
	
	public void updatePlan (Plan plan) throws PlanException;
	
	
	public void crearPlanNuevo(Plan plan) throws PlanException;
	
	
	
	

}
