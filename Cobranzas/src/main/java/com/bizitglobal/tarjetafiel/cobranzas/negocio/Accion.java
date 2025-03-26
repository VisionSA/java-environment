package com.bizitglobal.tarjetafiel.cobranzas.negocio;

import com.bizitglobal.tarjetafiel.general.negocio.Partido;
import com.bizitglobal.tarjetafiel.transacciones.negocio.Colaborador;
import com.bizitglobal.tarjetafiel.transacciones.negocio.ConceptoCabecera;

public class Accion  {
	
	public Integer idAccion;
	public String descripcion;
	public Integer tipoAccion;
	
	
	/**
	 * tipoAccion tipifica las subclases de la siguiente manera.
	 * tipoAccion = 1  =>  clase = EnvioCarta
	 * tipoAccion = 2  =>  clase = CambioEstadoMora
	 * tipoAccion = 3  =>  clase = Teledirecto
	 * tipoAccion = 4  =>  clase = BloqueoPlastico
	 * tipoAccion = 5  =>  clase = LlamadoTelefonicoOficina
	 * tipoAccion = 6  =>  clase = Codesa
	 * tipoAccion = 7  =>  clase = InformeBancoCentral
	 * tipoAccion = 8  =>  clase = AsignarCobrador
	 * tipoAccion = 9  =>  clase = FinalizarEtapaCobradores
	 * tipoAccion = 10 =>  clase = CargoPorMora
	 * */
	public Accion() {
		
	}
	
	
	/**
	 * @param objeto Un objeto que cada accion podra castear al esperado.
	 * */
	public ResultadoEjecucionAccion ejecutarAccion(Moroso cliente, ConceptoCabecera conceptoCabecera, AccionVersion accV, Object objeto, Colaborador cola) {
		return null;
	}
	
	public ResultadoEjecucionAccion ejecutarAccion(Moroso cliente, ConceptoCabecera conceptoCabecera, AccionVersion accV, Partido partido,Colaborador cola) {
		return null;
	}
	
	

	public Integer getIdAccion() {
		return idAccion;
	}

	public void setIdAccion(Integer idAccion) {
		this.idAccion = idAccion;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Integer getTipoAccion() {
		return tipoAccion;
	}

	public void setTipoAccion(Integer tipoAccion) {
		this.tipoAccion = tipoAccion;
	}
	
}
