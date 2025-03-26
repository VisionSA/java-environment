package com.bizitglobal.tarjetafiel.cobranzas.dao;

import java.util.List;

import com.bizitglobal.tarjetafiel.cobranzas.negocio.Plan;
import com.bizitglobal.tarjetafiel.commons.filtros.Filtro;

public interface PlanDao {
	public void grabarPlan (Plan pObject);
	public Plan buscarPlan (Long id);
	public void borrarPlan (Long id);
	public void borrarPlan (Plan pObject);
	public void actualizarPlan(Plan pObject);
	public List listarTodos(Filtro filtro);
	/**
	 * @return El unico plan por defecto que se encuentra habilitado.
	 * */
	public Plan getPlanPorDefecto();
	
	/**
	 * @param plan A partir del plan, obtiene la version actual y ejecuta su query.
	 * @return Una lista de Long con los id de clientes que recupera para el plan en cuestion
	 * */
	public List<Long> ejecutarQuery(Plan plan);
	
	public void borrarPlanesPorDefecto();
	
	public void cambiarEstadoPlan(Plan p);
	
	public void marcarPlanPorDefecto(Plan p);
	
	public void desmarcarPlanPorDefecto();
	
	public List getPlanes(Filtro filtro);
	
	public void updatePlan (Plan plan);
	
}
