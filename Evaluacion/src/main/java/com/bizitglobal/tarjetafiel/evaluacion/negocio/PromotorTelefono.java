package com.bizitglobal.tarjetafiel.evaluacion.negocio;

import com.bizitglobal.tarjetafiel.commons.interfaces.Negocio;
import com.bizitglobal.tarjetafiel.general.negocio.Telefono;

public class PromotorTelefono implements Negocio {
	
	private Long idPromotorTelefono = new Long(0);
	private Promotor promotor;
	private Telefono telefono;

	public PromotorTelefono() {
		this(null,null,null);
	}
	
	public PromotorTelefono(Long idPromotorTelefono) {
		this(idPromotorTelefono,null,null);
	}

	public PromotorTelefono(Long idPromotorTelefono, Promotor promotor, Telefono telefono) {
		super();
		this.idPromotorTelefono = idPromotorTelefono;
		this.promotor = promotor;
		this.telefono = telefono;
	}

	public Long getId() {
		return idPromotorTelefono;
	}
	
	public String getLabel() {
		return "Implementar propiedad getLabel";
	}
	
	public Telefono getTelefono() {
		return telefono;
	}
	
	public void setTelefono(Telefono telefono) {
		this.telefono = telefono;
	}
	
	public Long getIdPromotorTelefono() {
		return idPromotorTelefono;
	}

	public void setIdPromotorTelefono(Long idPromotorTelefono) {
		this.idPromotorTelefono = idPromotorTelefono;
	}

	public Promotor getPromotor() {
		return promotor;
	}

	public void setPromotor(Promotor promotor) {
		this.promotor = promotor;
	}

	public String toString() {
		
		return "Promotor Telefonos: " + 
				"id: " + idPromotorTelefono +
				", promotor: " + promotor.toString() +
				", telefono: " + telefono.toString(); 
	}
}

