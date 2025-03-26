package com.bizitglobal.tarjetafiel.cobranzas.negocio;

import java.util.Date;

import com.bizitglobal.tarjetafiel.general.negocio.Partido;
import com.bizitglobal.tarjetafiel.transacciones.negocio.Colaborador;
import com.bizitglobal.tarjetafiel.transacciones.negocio.ConceptoCabecera;

public class LiquidacionJudicial extends Accion {
	
	public ResultadoEjecucionAccion ejecutarAccion(Moroso cliente,
			ConceptoCabecera conceptoCabecera, AccionVersion accV,
			Partido partido, Colaborador cola) {
		// implementamos el algoritmo de Cambiar estadoMora
		ResultadoEjecucionAccion result = new ResultadoEjecucionAccion();
		
				
		// agrego un sql a la lista de sqls a Ejecutar.

		// creo el objeto EjecucionAccion y se lo seteo al array de objetos.
		EjecucionPlan ejecucionPlan = new EjecucionPlan();
		ejecucionPlan.setFechaEjecucion(new Date());
		ejecucionPlan.setPlan(accV.getEtapaVersion().getPlanVersion().getPlan());
		ejecucionPlan.setEtapa(accV.getEtapaVersion().getEtapa());
		ejecucionPlan.setAccion(accV.getAccion());
		ejecucionPlan.setConfirmoAccion("N");
		ejecucionPlan.setComentario("Liquidacion Judicial");
		ejecucionPlan.setUrlArchivo(null);
		ejecucionPlan.setCobrador(null);
		ejecucionPlan.setPartido(partido);
		ejecucionPlan.setIdUsuario(cliente.getIdCliente());
		ejecucionPlan.setIdAbogado(partido.getIdAbogado());
		
		result.getObjetosAGuardar().add(ejecucionPlan);
		return result;
	}

}
