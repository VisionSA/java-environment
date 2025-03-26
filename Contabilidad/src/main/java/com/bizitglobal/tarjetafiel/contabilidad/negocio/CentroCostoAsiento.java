package com.bizitglobal.tarjetafiel.contabilidad.negocio;

import com.bizitglobal.tarjetafiel.commons.interfaces.Negocio;

public class CentroCostoAsiento implements Negocio {
	private Long idCentroCostoAsiento;
	private Long idRenglon;
	private Long idAsiento;
	private PlanCuentaDos planCuentaDos;
	private CentroCostos centroCostos;
	private Long idImporte;
	private String isLote;
	

	public CentroCostoAsiento() {
		this(null,null,null,null,null,null, null);
	}
	
	public CentroCostoAsiento(Long idOrigen) {
		this(idOrigen,null, null,null,null,null, null);
	}
	
	public CentroCostoAsiento(Long idCentroCostoAsiento, Long idRenglon, Long idAsiento, PlanCuentaDos planCuentaDos, CentroCostos centroCostos, Long idImporte, String isLote) {
		super();
		this.idCentroCostoAsiento = idCentroCostoAsiento;
		this.idRenglon = idRenglon;
		this.idAsiento = idAsiento;
		this.planCuentaDos = planCuentaDos;
		this.centroCostos = centroCostos;
		this.idImporte = idImporte;
		this.isLote = isLote;
	}
	

	public boolean equals(Object obj) {
		boolean result = false;
		if(obj instanceof CentroCostoAsiento) {
			CentroCostoAsiento aux = (CentroCostoAsiento)obj;
			if(aux.getId().equals(idCentroCostoAsiento)) {
				result = true;
			}
		}
		return result;
	}

	public Long getId() {
		// TODO Auto-generated method stub
		return null;
	}

	public String getLabel() {
		// TODO Auto-generated method stub
		return null;
	}

	public Long getIdAsiento() {
		return idAsiento;
	}

	public void setIdAsiento(Long idAsiento) {
		this.idAsiento = idAsiento;
	}

	public CentroCostos getCentroCostos() {
		return centroCostos;
	}

	public void setCentroCostos(CentroCostos centroCostos) {
		this.centroCostos = centroCostos;
	}

	public Long getIdCentroCostoAsiento() {
		return idCentroCostoAsiento;
	}

	public void setIdCentroCostoAsiento(Long idCentroCostoAsiento) {
		this.idCentroCostoAsiento = idCentroCostoAsiento;
	}

	public Long getIdImporte() {
		return idImporte;
	}

	public void setIdImporte(Long idImporte) {
		this.idImporte = idImporte;
	}

	public PlanCuentaDos getPlanCuentaDos() {
		return planCuentaDos;
	}

	public void setPlanCuentaDos(PlanCuentaDos planCuentaDos) {
		this.planCuentaDos = planCuentaDos;
	}

	public Long getIdRenglon() {
		return idRenglon;
	}

	public void setIdRenglon(Long idRenglon) {
		this.idRenglon = idRenglon;
	}

	public String getIsLote() {
		return isLote;
	}

	public void setIsLote(String isLote) {
		this.isLote = isLote;
	}
}