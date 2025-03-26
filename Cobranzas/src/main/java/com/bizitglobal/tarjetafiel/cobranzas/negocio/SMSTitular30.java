package com.bizitglobal.tarjetafiel.cobranzas.negocio;

import java.util.Date;

import com.bizitglobal.tarjetafiel.transacciones.negocio.ClienteTransaccion;
import com.bizitglobal.tarjetafiel.transacciones.negocio.Colaborador;
import com.bizitglobal.tarjetafiel.transacciones.negocio.ConceptoCabecera;
import com.bizitglobal.tarjetafiel.transacciones.negocio.EstadoCobranza;

public class SMSTitular30 extends Accion {
	
	public SMSTitular30() {
		
	}
	
	/**
	 * Identifica de la tabla de estados Cobranza, cual es el proximo id que le corresponde de estado al cliente pasado por parametro, y se lo setea.
	 * */
	public ResultadoEjecucionAccion ejecutarAccion(Moroso cliente, ConceptoCabecera conceptoCabecera, AccionVersion accV, Object objeto,Colaborador cola) {
		// implementamos el algoritmo de Cambiar estadoMora
		ResultadoEjecucionAccion result = new ResultadoEjecucionAccion();
		
				//creo el objeto EjecucionAccion y se lo seteo al array de objetos.
		EjecucionPlan ejecucionPlan = new EjecucionPlan();
		ejecucionPlan.setFechaEjecucion(new Date());
		ejecucionPlan.setPlan(accV.getEtapaVersion().getPlanVersion().getPlan());
		ejecucionPlan.setEtapa(accV.getEtapaVersion().getEtapa());
		ejecucionPlan.setAccion(accV.getAccion());
		ejecucionPlan.setConfirmoAccion("S");
		ejecucionPlan.setComentario(null);
		ejecucionPlan.setUrlArchivo(null);
		ejecucionPlan.setCobrador(null);
		ejecucionPlan.setIdUsuario(cliente.getIdCliente());
		
		result.getObjetosAGuardar().add(ejecucionPlan);
		return result;
	}
	
	
}
