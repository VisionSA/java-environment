package com.bizitglobal.tarjetafiel.cobranzas.negocio;

import java.util.Date;

import com.bizitglobal.tarjetafiel.transacciones.negocio.Colaborador;
import com.bizitglobal.tarjetafiel.transacciones.negocio.ConceptoCabecera;

public class EnvioCarta extends Accion {

	public EnvioCarta() {
		
	}
	
	public ResultadoEjecucionAccion ejecutarAccion(Moroso cliente, ConceptoCabecera conceptoCabecera, AccionVersion accV, Object objeto,Colaborador cola) {
		ResultadoEjecucionAccion resultado = new ResultadoEjecucionAccion();
		
		EjecucionPlan ejecucionPlan = new EjecucionPlan();
		ejecucionPlan.setFechaEjecucion(new Date());
		ejecucionPlan.setPlan(accV.getEtapaVersion().getPlanVersion().getPlan());
		ejecucionPlan.setEtapa(accV.getEtapaVersion().getEtapa());
		ejecucionPlan.setAccion(accV.getAccion());
		ejecucionPlan.setConfirmoAccion("N");
		ejecucionPlan.setComentario("Primer Envio Carta");
		ejecucionPlan.setIdUsuario(cliente.getIdCliente());		
		resultado.getObjetosAGuardar().add(ejecucionPlan);
		resultado.setImpactaConcepto(false);
		return resultado;
	}

}
