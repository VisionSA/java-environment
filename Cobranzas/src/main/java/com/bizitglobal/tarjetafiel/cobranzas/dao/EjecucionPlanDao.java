package com.bizitglobal.tarjetafiel.cobranzas.dao;

import java.util.List;

import com.bizitglobal.tarjetafiel.cobranzas.negocio.EjecucionPlan;
import com.bizitglobal.tarjetafiel.cobranzas.negocio.Moroso;
import com.bizitglobal.tarjetafiel.cobranzas.negocio.Plan;
import com.bizitglobal.tarjetafiel.commons.filtros.Filtro;
import com.bizitglobal.tarjetafiel.transacciones.negocio.Abogado;
import com.bizitglobal.tarjetafiel.transacciones.negocio.ClienteLiquidacion;
import com.bizitglobal.tarjetafiel.transacciones.negocio.ClienteTransaccion;

public interface EjecucionPlanDao {
	public void grabarEjecucionPlan (EjecucionPlan pObject);
	public EjecucionPlan buscarEjecucionPlan (Long id);
	public void borrarEjecucionPlan (Long id);
	public void borrarEjecucionPlan (EjecucionPlan pObject);
	public void actualizarEjecucionPlan(EjecucionPlan pObject);
	public List listarTodos(Filtro filtro);
/*@I5597*/	public List<ClienteLiquidacion> listarIdClientesQueEntranEnMora(final Double montoDeGracia);
/*@F5597*/	
	public Boolean procesarPagosRealizados(int mesInicial, int mesFinal, int anioInicial, int anioFinal);
	
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
	public void grabarObjeto(Object pObject);
	
	/**
	* Ejecuta una query en la base de datos.
	* @param query, query a ejecutar.
	*/
	public void ejecutarQuery(String query);
	
	public void cambiarEstadoYAddFile(Long idEjecucionPlan, String confirmoAccion,
			StringBuffer pathRelativoPDFCobradores);
	
	public Long cambiarCobrador(Long idCobrador,Long idEjecPlan);
	
	public void cambiarCobradoresTareasPendientes(Long idCobIN, Long idCobOUT, Long idPartido);
	
	public void cambiarAbogadosTareasPendientes(Long idAbogIN, Long idAbogOUT,
			Long idPartido);
	
	public Abogado buscarAbogadoTarea(Long idEjecucionPlan);
	
	public void cambiarAbogadoClienteTarea(Long idEjecucionPlan, Long idAbogIN);
	
	
	public void insertarHistoricoMora(Long idCliente);
	
	public Long getIdEjecPlanCobradorAsignado(Long idCliente);
	
	void insertarErrorEjecucion(Long idCliente, Long idAccion,
			String descripcion);
	
}
