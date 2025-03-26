package com.bizitglobal.tarjetafiel.cobranzas.negocio;

import java.util.Date;

import com.bizitglobal.tarjetafiel.transacciones.negocio.Colaborador;
import com.bizitglobal.tarjetafiel.transacciones.negocio.ConceptoCabecera;

public class CargoPorMora extends Accion {
	
	public CargoPorMora() {
		
	}
	
	/**
	 * Crea una ejecucion plan que agrega un item a la cta corriente del cliente con un importe fijo por defecto.
	 */
	public ResultadoEjecucionAccion ejecutarAccion(Moroso cliente, ConceptoCabecera conceptoCabecera, AccionVersion accV, Object objeto,Colaborador cola) {
		// Implementamos el algoritmo de agregar un cargo por fijo por Moroso
		
		
		ResultadoEjecucionAccion result = new ResultadoEjecucionAccion();		
		
		
		//Creo el objeto EjecucionAccion y se lo seteo al array de objetos.
		EjecucionPlan ejecucionPlan = new EjecucionPlan();
		
		
		ejecucionPlan.setFechaEjecucion(new Date());
		ejecucionPlan.setPlan(accV.getEtapaVersion().getPlanVersion().getPlan());
		ejecucionPlan.setEtapa(accV.getEtapaVersion().getEtapa());
		ejecucionPlan.setAccion(accV.getAccion());
		ejecucionPlan.setConfirmoAccion("S"); // Ya que no tiene que ser confirmada - No pasa a tareas pendiente
		ejecucionPlan.setComentario(accV.getAccion().getDescripcion());
		ejecucionPlan.setUrlArchivo(null);
		ejecucionPlan.setCobrador(null);
		ejecucionPlan.setIdUsuario(cliente.getIdCliente());
		
		
		result.getObjetosAGuardar().add(ejecucionPlan);
		
		result.setImpactaConcepto(true);
		return result;
	}
	
}
