package com.bizitglobal.tarjetafiel.cobranzas.negocio;

import java.util.Date;

import com.bizitglobal.tarjetafiel.transacciones.negocio.ClienteTransaccion;
import com.bizitglobal.tarjetafiel.transacciones.negocio.Colaborador;
import com.bizitglobal.tarjetafiel.transacciones.negocio.ConceptoCabecera;
import com.bizitglobal.tarjetafiel.transacciones.negocio.EstadoCobranza;

public class CambioEstadoMora extends Accion {
	
	public CambioEstadoMora() {
		
	}
	
	/**
	 * Identifica de la tabla de estados Cobranza, cual es el proximo id que le corresponde de estado al cliente pasado por parametro, y se lo setea.
	 * */
	public ResultadoEjecucionAccion ejecutarAccion(Moroso cliente, ConceptoCabecera conceptoCabecera, AccionVersion accV, Object objeto,Colaborador cola) {
		// implementamos el algoritmo de Cambiar estadoMora
		ResultadoEjecucionAccion result = new ResultadoEjecucionAccion();
		
		// agrego un sql a la lista de sqls a Ejecutar.
		result.getSqlAEjecutar().add("Update " + ClienteTransaccion.T_CLIENTES + " set " +
				ClienteTransaccion.T_CLIENTES + "." + ClienteTransaccion.C_ID_ESTADO_COBRANZA +
				" = (Select " + EstadoCobranza.T_ESTADOS_COBRANZA + "." + EstadoCobranza.C_ID_PROXIMO_ESTADO + " from " + EstadoCobranza.T_ESTADOS_COBRANZA + " where "+ EstadoCobranza.T_ESTADOS_COBRANZA + "." + EstadoCobranza.C_ID_ESTADO_COBRANZA + " = " + ClienteTransaccion.T_CLIENTES + "." + ClienteTransaccion.C_ID_ESTADO_COBRANZA  +")" +
						", " + ClienteTransaccion.T_CLIENTES + ".C_HABILITADO_CONSUMO = 'D'" +
						", " + ClienteTransaccion.T_CLIENTES + ".C_FECHA_ESTADO_COBRANZA = SYSDATE" +
						", " + ClienteTransaccion.T_CLIENTES + ".C_FECHA_HABILITADO_CONSUMO = SYSDATE" +
						" where " + ClienteTransaccion.T_CLIENTES + "." + ClienteTransaccion.C_ID_CLIENTE + " = " + cliente.getIdCliente());
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
