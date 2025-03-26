package com.bizitglobal.tarjetafiel.cobranzas.negocio;

import java.util.Date;

import com.bizitglobal.tarjetafiel.commons.interfaces.Negocio;
import com.bizitglobal.tarjetafiel.general.negocio.Partido;
import com.bizitglobal.tarjetafiel.transacciones.negocio.ClienteLiquidacion;
import com.bizitglobal.tarjetafiel.transacciones.negocio.ClienteTransaccion;
import com.bizitglobal.tarjetafiel.transacciones.negocio.Colaborador;
import com.bizitglobal.tarjetafiel.transacciones.negocio.ConceptoCabecera;
import com.bizitglobal.tarjetafiel.transacciones.negocio.EstadoCobranza;

public class FinalizarEtapaCobradores extends Accion {
	
	public FinalizarEtapaCobradores() {
		
	}
	
	/**
	 * Identifica de la tabla de partidos, de a cuerdo al partido del cliente, que cobrador le corresponde y se lo setea.
	 * @param objeto Utilizar para pasar un Colaborador.
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
		ejecucionPlan.setConfirmoAccion("S");
		ejecucionPlan.setComentario("FEC - 180 dias mora");
		ejecucionPlan.setUrlArchivo(null);
		ejecucionPlan.setCobrador(null);
		ejecucionPlan.setIdUsuario(cliente.getIdCliente());
		ejecucionPlan.setIdParent((Long)objeto);
		
		result.getObjetosAGuardar().add(ejecucionPlan);
		return result;
	}
	
	
}
