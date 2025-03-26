package com.bizitglobal.tarjetafiel.cobranzas.negocio;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class AdministradorDePlanes  {
	
	private HashMap<Plan, List<Long>> planXClientes; //un plan, toda la lista de ids de clientes que se le aplican. Aqui no debe ir incluido el plan por defecto.
	private Plan planPorDefecto; // no puede ser null.
	
	private HashMap<Integer, Plan> planXIdPlan; // el id de plan como key, el plan como EL plan en si. Util para obtener el plan de un cliente que ya tiene el id_Plan_mora asociado.
	
	public AdministradorDePlanes() {
        planXIdPlan = new HashMap<Integer, Plan>();
        planXClientes = new HashMap<Plan, List<Long>>();
	}

	public Plan getPlanDelCliente(Long idCliente) {
		Iterator<Plan> iter = planXClientes.keySet().iterator();
		while (iter.hasNext()) {
			Plan key = iter.next();
			if (planXClientes.get(key).contains(idCliente)) {
				return key;
			}
		}
		return planPorDefecto;
	}
	
	public Plan getPlan(Integer idPlan) {
		return planXIdPlan.get(idPlan);
	}

	public HashMap<Plan, List<Long>> getPlanXClientes() {
		return planXClientes;
	}

	public void setPlanXClientes(HashMap<Plan, List<Long>> planXClientes) {
		this.planXClientes = planXClientes;
	}

	public Plan getPlanPorDefecto() {
		return planPorDefecto;
	}

	public void setPlanPorDefecto(Plan planPorDefecto) {
		this.planPorDefecto = planPorDefecto;
	}

	public HashMap<Integer, Plan> getPlanXIdPlan() {
		return planXIdPlan;
	}

	public void setPlanXIdPlan(HashMap<Integer, Plan> planXIdPlan) {
		this.planXIdPlan = planXIdPlan;
	}

	
	
	
}
