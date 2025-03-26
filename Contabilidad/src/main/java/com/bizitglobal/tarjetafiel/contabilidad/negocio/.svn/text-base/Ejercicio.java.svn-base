package com.bizitglobal.tarjetafiel.contabilidad.negocio;


import java.util.Date;

import com.bizitglobal.tarjetafiel.commons.interfaces.Negocio;
import com.bizitglobal.tarjetafiel.contabilidad.exception.EjercicioCreateException;
import com.bizitglobal.tarjetafiel.general.negocio.SucursalFiel;


public class Ejercicio  implements Negocio {
	
	private Integer idEjercicio;
	private Integer sucursalFiel;
	private Date fechaInicio;
	private Date fechaCierre;
	private Date fechaPeriodo;
	private String estado;
	private String observaciones;
	private String actual;
	
	
	public Ejercicio() {
		
	}
	
	public Ejercicio(Integer idEjercicio, Integer sucursalFiel, Date fechaInicio,Date fechaCierre,Date fechaPeriodo,String estado, String observaciones, String actual) throws EjercicioCreateException {
		if (estado.length()!=1) {
			throw new EjercicioCreateException("El estado debe ser un solo caracter");
		}
		if (sucursalFiel==null) {
			throw new EjercicioCreateException("Debe ingresar una sucursal al menos");
		}
		if (fechaInicio.after(fechaPeriodo)) {
			throw new EjercicioCreateException("La fecha de inicio no puede ser posterior a la fecha de Período");
		}
		if (fechaCierre.before(fechaPeriodo)) {
			throw new EjercicioCreateException("La fecha de Cierre no puede ser anterior a la fecha de Período");
		}
		if (observaciones.length()>50) {
			throw new EjercicioCreateException("Las observaciones no pueden superar los 50 caracteres");
		}
		this.idEjercicio = idEjercicio;
		this.sucursalFiel = sucursalFiel;
		this.fechaInicio = fechaInicio;
		this.fechaCierre = fechaCierre;
		this.fechaPeriodo = fechaPeriodo;
		this.estado = estado;
		this.observaciones = observaciones;
		this.actual= actual;
	}

	public Long getId() {
		return new Long(idEjercicio.intValue());
	}

	public String getLabel() {
		// TODO Auto-generated method stub
		return null;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public Date getFechaCierre() {
		return fechaCierre;
	}

	public void setFechaCierre(Date fechaCierre) {
		this.fechaCierre = fechaCierre;
	}

	public Date getFechaInicio() {
		return fechaInicio;
	}

	public void setFechaInicio(Date fechaInicio) {
		this.fechaInicio = fechaInicio;
	}

	public Date getFechaPeriodo() {
		return fechaPeriodo;
	}

	public void setFechaPeriodo(Date fechaPeriodo) {
		this.fechaPeriodo = fechaPeriodo;
	}

	public Integer getIdEjercicio() {
		return idEjercicio;
	}

	public void setIdEjercicio(Integer idEjercicio) {
		this.idEjercicio = idEjercicio;
	}

	public String getObservaciones() {
		return observaciones;
	}

	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}

	public Integer getSucursalFiel() {
		return sucursalFiel;
	}

	public void setSucursalFiel(Integer sucursalFiel) {
		this.sucursalFiel = sucursalFiel;
	}
	
	public String getActual() {
		return actual;
	}

	public void setActual(String actual) {
		this.actual = actual;
	}

}
