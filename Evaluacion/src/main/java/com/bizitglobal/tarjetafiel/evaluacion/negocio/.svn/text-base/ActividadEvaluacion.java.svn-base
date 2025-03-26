package com.bizitglobal.tarjetafiel.evaluacion.negocio;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Date;
import java.util.Set;

import com.bizitglobal.tarjetafiel.commons.interfaces.Negocio;
import com.bizitglobal.tarjetafiel.commons.util.Fecha;
import com.bizitglobal.tarjetafiel.commons.util.Mascara;
import com.bizitglobal.tarjetafiel.general.negocio.Ocupacion;
import com.bizitglobal.tarjetafiel.general.negocio.SucEmpresa;

public class ActividadEvaluacion implements Negocio {
	private Long idActividad = null;
	private Long antiguedad = null;
	private String cargo = "";
	private String empleoAnterior = "";	
	private Timestamp fechaIngreso = null;
	private Date fechaIngresoFlex = null;
	private Ocupacion ocupacion;
	private SucEmpresa sucEmpresa;
	private String otrosIngresosDesc = "";
	private BigDecimal otrosIngresosMonto = new BigDecimal(0);
	private String referencias = "";
	private BigDecimal sueldoNeto = new BigDecimal(0);
	private String telEmpleoAnt = "";
	private Set evaIndividuos;
	private String tipo;
	private String cuil = null;
	private Character habilitadoCuit;
	
	public static final Mascara tipoStaticList[] = {new Mascara(new String("A"),"Autorizado"), new Mascara(new String("R"),"Responsable"),new Mascara(new String("E"),"Empleado"),new Mascara(new String("X"),"Autorizado/Responsable")};
	
	public ActividadEvaluacion() {
		this(null,null,null,null,null,null,null,null,null,null,null,null,null,"E");
	}
	
	public ActividadEvaluacion(Long idActividad) {
		this(idActividad,null,null,null,null,null,null,null,null,null,null,null,null,null);
	}
	
	public ActividadEvaluacion(Long idActividad, Long antiguedad, String cargo, String empleoAnterior, Timestamp fechaIngreso, Ocupacion ocupacion, SucEmpresa sucEmpresa, String otrosIngresosDesc, BigDecimal otrosIngresosMonto, String referencias, BigDecimal sueldoNeto, String telEmpleoAnt, Set evaIndividuos, String tipo) {
		super();
		this.idActividad = idActividad;
		this.antiguedad = antiguedad;
		this.cargo = cargo;
		this.empleoAnterior = empleoAnterior;
		this.fechaIngreso = fechaIngreso;
		this.ocupacion = ocupacion;
		this.sucEmpresa = sucEmpresa;
		this.otrosIngresosDesc = otrosIngresosDesc;
		this.otrosIngresosMonto = otrosIngresosMonto;
		this.referencias = referencias;
		this.sueldoNeto = sueldoNeto;
		this.telEmpleoAnt = telEmpleoAnt;
		this.evaIndividuos = evaIndividuos;
		this.tipo = tipo;
	}

	public Long getId() {
		return idActividad;
	}
	
	public String getLabel() {
		return "Implementar propiedad getLabel";
	}
	
	
	public Long getAntiguedad() {
		return antiguedad;
	}

	public void setAntiguedad(Long antiguedad) {
		this.antiguedad = antiguedad;
	}

	public String getCargo() {
		return cargo;
	}

	public void setCargo(String cargo) {
		this.cargo = cargo;
	}

	public String getEmpleoAnterior() {
		return empleoAnterior;
	}

	public void setEmpleoAnterior(String empleoAnterior) {
		this.empleoAnterior = empleoAnterior;
	}

	public Set getEvaIndividuos() {
		return evaIndividuos;
	}

	public void setEvaIndividuos(Set evaIndividuos) {
		this.evaIndividuos = evaIndividuos;
	}

	public Timestamp getFechaIngreso() {
		return fechaIngreso;
	}

	public void setFechaIngreso(Timestamp fechaIngreso) {
		this.fechaIngreso = fechaIngreso;
	}

	public Long getIdActividad() {
		return idActividad;
	}

	public void setIdActividad(Long idActividad) {
		this.idActividad = idActividad;
	}

	public Ocupacion getOcupacion() {
		return ocupacion;
	}

	public void setOcupacion(Ocupacion ocupacion) {
		this.ocupacion = ocupacion;
	}

	public String getOtrosIngresosDesc() {
		return otrosIngresosDesc;
	}

	public void setOtrosIngresosDesc(String otrosIngresosDesc) {
		this.otrosIngresosDesc = otrosIngresosDesc;
	}

	public BigDecimal getOtrosIngresosMonto() {
		return otrosIngresosMonto;
	}

	public void setOtrosIngresosMonto(BigDecimal otrosIngresosMonto) {
		this.otrosIngresosMonto = otrosIngresosMonto;
	}

	public String getReferencias() {
		return referencias;
	}

	public void setReferencias(String referencias) {
		this.referencias = referencias;
	}

	public SucEmpresa getSucEmpresa() {
		return sucEmpresa;
	}

	public void setSucEmpresa(SucEmpresa sucEmpresa) {
		this.sucEmpresa = sucEmpresa;
	}

	public BigDecimal getSueldoNeto() {
		return sueldoNeto;
	}

	public void setSueldoNeto(BigDecimal sueldoNeto) {
		this.sueldoNeto = sueldoNeto;
	}

	public String getTelEmpleoAnt() {
		return telEmpleoAnt;
	}

	public void setTelEmpleoAnt(String telEmpleoAnt) {
		this.telEmpleoAnt = telEmpleoAnt;
	}

	public String toString() {
		
		return  "ActividadEvaluacion " +
				"idActividad " + idActividad+
				", antiguedad: " + antiguedad +
				", cargo " + cargo +
				", empleoAnterior: " + empleoAnterior +
				", fechaIngreso: " + fechaIngreso +
				//", ocupacion: " + ocupacion == null ? "" : ocupacion.toString() +
				//", sucEmpresa: " + sucEmpresa == null ? "" : sucEmpresa.toString() +
				", otrosIngresosDesc: " + otrosIngresosDesc +
				", otrosIngresosMonto: " + otrosIngresosMonto +
				", referencias: " + referencias +
				", sueldoNeto: " + sueldoNeto + 
				", telEmpleoAnt: " + telEmpleoAnt; 
				
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getCuil() {
		return cuil;
	}

	public void setCuil(String cuil) {
		this.cuil = cuil;
	}
	
	public Date getFechaIngresoFlex() {
		if(fechaIngreso!=null){
			fechaIngresoFlex = new Date(fechaIngreso.getTime());
		}
		return fechaIngresoFlex;
	}
	
	public void setFechaIngresoFlex(Date fechaIngresoFlex) {
		this.fechaIngresoFlex = fechaIngresoFlex;
		if(fechaIngresoFlex != null){
			fechaIngreso = new Timestamp(fechaIngresoFlex.getTime());
		}
	}
	
	public Character getHabilitadoCuit() {
		return habilitadoCuit;
	}
	
	public void setHabilitadoCuit(Character habilitadoCuit) {
		this.habilitadoCuit = habilitadoCuit;
	}
}

