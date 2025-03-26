package com.bizitglobal.tarjetafiel.proveedores.negocio;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;


public class ComprobantesNoCancelados {
	private static final Logger log = Logger.getLogger(ComprobantesNoCancelados.class);
	private List comprobantes;
	private List cuotasComprobantes;
		
	public ComprobantesNoCancelados() {
		this(new ArrayList(), new ArrayList());
	}

	public ComprobantesNoCancelados(List comprobantes, List cuotasComprobantes) {
		super();
		this.comprobantes = comprobantes;
		this.cuotasComprobantes = cuotasComprobantes;
	}

	public List getComprobantes() {
		return comprobantes;
	}

	public void setComprobantes(List comprobantes) {
		this.comprobantes = comprobantes;
	}

	public List getCuotasComprobantes() {
		return cuotasComprobantes;
	}

	public void setCuotasComprobantes(List cuotasComprobantes) {
		this.cuotasComprobantes = cuotasComprobantes;
	}
	
	public void addComprobante(Comprobante comprobante){
		comprobantes.add(comprobante);
	}
	
	public void addCuotaComprobante(CuotaComprobante cuotaComprobante){
		cuotasComprobantes.add(cuotaComprobante);
	}

}
