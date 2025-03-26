package com.bizitglobal.tarjetafiel.cobranzas.negocio;

import java.util.Date;

import com.bizitglobal.tarjetafiel.commons.interfaces.Negocio;
import com.bizitglobal.tarjetafiel.general.negocio.Partido;
import com.bizitglobal.tarjetafiel.transacciones.negocio.ClienteLiquidacion;
import com.bizitglobal.tarjetafiel.transacciones.negocio.ClienteTransaccion;
import com.bizitglobal.tarjetafiel.transacciones.negocio.Colaborador;
import com.bizitglobal.tarjetafiel.transacciones.negocio.ConceptoCabecera;
import com.bizitglobal.tarjetafiel.transacciones.negocio.EstadoCobranza;

public class AsignarCobrador extends Accion {
	
	public AsignarCobrador() {
		
	}
	
	/**
	 * Identifica de la tabla de partidos, de a cuerdo al partido del cliente, que cobrador le corresponde y se lo setea.
	 * @param objeto Utilizar para pasar un Partido.
	 * */
	public ResultadoEjecucionAccion ejecutarAccion(Moroso cliente, ConceptoCabecera conceptoCabecera, AccionVersion accV, Object objeto,Colaborador cola) {
		// implementamos el algoritmo de Cambiar estadoMora
		ResultadoEjecucionAccion result = new ResultadoEjecucionAccion();
		
		// agrego un sql a la lista de sqls a Ejecutar.
		
		//creo el objeto EjecucionAccion y se lo seteo al array de objetos.
		EjecucionPlan ejecucionPlan = new EjecucionPlan();
		ejecucionPlan.setFechaEjecucion(new Date());
		ejecucionPlan.setPlan(accV.getEtapaVersion().getPlanVersion().getPlan());
		ejecucionPlan.setEtapa(accV.getEtapaVersion().getEtapa());
		ejecucionPlan.setAccion(accV.getAccion());
		ejecucionPlan.setConfirmoAccion("N");
		ejecucionPlan.setComentario(null);
		ejecucionPlan.setUrlArchivo(null);
		ejecucionPlan.setCobrador(cola);
		ejecucionPlan.setIdUsuario(cliente.getIdCliente());
		
		result.getObjetosAGuardar().add(ejecucionPlan);
		return result;
	}
	
	
	public ResultadoEjecucionAccion ejecutarAccion(Moroso cliente, ConceptoCabecera conceptoCabecera, AccionVersion accV, Partido partido,Colaborador cola) {
		// implementamos el algoritmo de Cambiar estadoMora
		ResultadoEjecucionAccion result = new ResultadoEjecucionAccion();
		
		// agrego un sql a la lista de sqls a Ejecutar.
		
		//creo el objeto EjecucionAccion y se lo seteo al array de objetos.
		EjecucionPlan ejecucionPlan = new EjecucionPlan();
		ejecucionPlan.setFechaEjecucion(new Date());
		ejecucionPlan.setPlan(accV.getEtapaVersion().getPlanVersion().getPlan());
		ejecucionPlan.setEtapa(accV.getEtapaVersion().getEtapa());
		ejecucionPlan.setAccion(accV.getAccion());
		ejecucionPlan.setConfirmoAccion("N");
		ejecucionPlan.setComentario(null);
		ejecucionPlan.setUrlArchivo(null);
		ejecucionPlan.setCobrador(cola);
		ejecucionPlan.setPartido(partido);
		ejecucionPlan.setIdUsuario(cliente.getIdCliente());
		
		result.getObjetosAGuardar().add(ejecucionPlan);
		return result;
	}
	
	
}
