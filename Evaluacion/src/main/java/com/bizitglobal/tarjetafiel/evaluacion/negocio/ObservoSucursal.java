package com.bizitglobal.tarjetafiel.evaluacion.negocio;

import com.bizitglobal.tarjetafiel.commons.interfaces.Negocio;
import com.bizitglobal.tarjetafiel.general.negocio.SucEmpresa;

public class ObservoSucursal implements Negocio {
	private Long idObsSucursal = new Long(0);
	private ObservoLaboral observoLaboral;
	private SucEmpresa sucEmpresa;

	public ObservoSucursal() {
		this(null,null,null);
	}
	
	public ObservoSucursal(Long idObsSucursal) {
		this(idObsSucursal,null,null);
	}

	public ObservoSucursal(Long idObsSucursal, ObservoLaboral observoLaboral, SucEmpresa sucEmpresa) {
		super();
		this.idObsSucursal = idObsSucursal;
		this.observoLaboral = observoLaboral;
		this.sucEmpresa = sucEmpresa;
	}

	public Long getId() {
		return idObsSucursal;
	}
	
	public Long getIdObsSucursal() {
		return idObsSucursal;
	}
	
	public void setIdObsSucursal(Long idObsSucursal) {
		this.idObsSucursal = idObsSucursal;
	}

	public String getLabel() {
		return "Implementar propiedad getLabel";
	}

	public SucEmpresa getSucEmpresa() {
		return sucEmpresa;
	}
	
	public void setSucEmpresa(SucEmpresa sucEmpresa) {
		this.sucEmpresa = sucEmpresa;
	}
	
	public String toString() {
		
		return "ObservoSucursal: " +
				"id: " + idObsSucursal + 
				", observo laboral: " + observoLaboral.toString()+
				", sucursal empresa: " + sucEmpresa.toString();
	}

	public ObservoLaboral getObservoLaboral() {
		return observoLaboral;
	}

	public void setObservoLaboral(ObservoLaboral observoLaboral) {
		this.observoLaboral = observoLaboral;
	}
}

