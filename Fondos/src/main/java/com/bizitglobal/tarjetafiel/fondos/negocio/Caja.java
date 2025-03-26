package com.bizitglobal.tarjetafiel.fondos.negocio;


import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import com.bizitglobal.tarjetafiel.commons.interfaces.Negocio;
import com.bizitglobal.tarjetafiel.contabilidad.negocio.PlanCuentaDos;
import com.bizitglobal.tarjetafiel.general.negocio.Impresora;
import com.bizitglobal.tarjetafiel.general.negocio.SucursalFiel;
import com.bizitglobal.tarjetafiel.operador.negocio.Operador;

public class Caja  implements Negocio {
	private Long idCaja;
	private String descripcion = "";
	private SucursalFiel sucursal;
	private Character habilitada= new Character('N');
	private Operador operador;
	private Date fechaModificacion;
	private Impresora impresora;
	private Operador operadorDefault;
    private Set cajaMPSet=new HashSet();
    private Set cajaAperturasSet=new HashSet();
	private Long idPlanCuenta;
	private Lugar lugar;
	
    private PlanCuentaDos planCuenta;

	public Set getCajaAperturasSet() {
		return cajaAperturasSet;
	}

	public void setCajaAperturasSet(Set cajaAperturasSet) {
		this.cajaAperturasSet = cajaAperturasSet;
	}

	public Caja() {
	}

	public Caja(Long id) {
		idCaja = id;
	}

	public Long getId() {
		return idCaja;
	}
	public Long getIdCaja() {
		return idCaja;
	}
	public void setIdCaja(Long idCaja) {
		this.idCaja = idCaja;
	}
	
	public String getLabel() {
		return descripcion;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Long getIdPlanCuenta() {
		return idPlanCuenta;
	}

	public void setIdPlanCuenta(Long idPlanCuenta) {
		this.idPlanCuenta = idPlanCuenta;
	}

	public Date getFechaModificacion() {
		return fechaModificacion;
	}

	public void setFechaModificacion(Date fechaModificacion) {
		this.fechaModificacion = fechaModificacion;
	}

	public Character getHabilitada() {
		return habilitada;
	}

	public void setHabilitada(Character habilitada) {
		this.habilitada = habilitada;
	}

	public Operador getOperador() {
		return operador;
	}

	public void setOperador(Operador operador) {
		this.operador = operador;
	}

	public PlanCuentaDos getPlanCuenta() {
		return planCuenta;
	}

	public void setPlanCuenta(PlanCuentaDos planCuenta) {
		this.planCuenta = planCuenta;
	}

	public SucursalFiel getSucursal() {
		return sucursal;
	}

	public void setSucursal(SucursalFiel sucursal) {
		this.sucursal = sucursal;
	}
	
	public Impresora getImpresora() {
		return impresora;
	}

	public void setImpresora(Impresora impresora) {
		this.impresora = impresora;
	}

	public Operador getOperadorDefault() {
		return operadorDefault;
	}

	public void setOperadorDefault(Operador operadorDefault) {
		this.operadorDefault = operadorDefault;
	}

	public Set getCajaMPSet() {
		return cajaMPSet;
	}

	public void setCajaMPSet(Set cajaMPSet) {
		this.cajaMPSet = cajaMPSet;
	}

	public boolean equals(Object obj) {
		boolean result = false;
		if(obj instanceof Caja) {
			Caja aux = (Caja)obj;
			if(aux.getId().equals(idCaja)) {
				result = true;
			}
		}
		return result;
	}
	
	public String toString() {
		return "Id:"+idCaja+"|Descripcion:"+descripcion;
	}
	
	public Lugar getLugar() {
		return lugar;
	}
	
	public void setLugar(Lugar lugar) {
		this.lugar = lugar;
	}
}

