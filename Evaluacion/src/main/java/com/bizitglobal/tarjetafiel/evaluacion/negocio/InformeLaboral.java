package com.bizitglobal.tarjetafiel.evaluacion.negocio;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Set;

import com.bizitglobal.tarjetafiel.commons.interfaces.Negocio;
import com.bizitglobal.tarjetafiel.general.negocio.Autonomo;
import com.bizitglobal.tarjetafiel.general.negocio.Ocupacion;
import com.bizitglobal.tarjetafiel.general.negocio.Rubro;
import com.bizitglobal.tarjetafiel.general.negocio.TamEmpresa;
import com.bizitglobal.tarjetafiel.general.negocio.SucEmpresa;
import com.bizitglobal.tarjetafiel.general.negocio.Vinculo;

public class InformeLaboral implements Negocio {
	private Long idInfoLaboral = new Long(0);
	private Long antiguedad = new Long(0);
	private String cargo = "";
	private String domCorregido = "";
	private Timestamp fechaIngreso = null;
	private Timestamp fechaRecepcion = null;
	private Autonomo autonomo;
	private Ocupacion ocupacion;
	private Long idOperador = new Long(0);
	private Rubro rubro;
	private SolicitudIndividuo solicitudIndividuo;
	private TamEmpresa tamanioEmpresa;
	private Verificador verificador;
	private Vinculo vinculo;
	private String informante = "";
	private String nroDocInform = "";
	private String nroInforme = "";
	private String observacion = "";
	private String rsCorregida = "";
	private BigDecimal sueldoNeto = new BigDecimal(0);
	private Timestamp timestamp = null;
	private Set evaObsLaborales;
	private SucEmpresa sucEmpresa;

	public InformeLaboral() {
		this(null,null,null,null,null,null,null,null,null,null
				,null,null,null,null,null,null,null,null,null,null,null);
	}
	
	public InformeLaboral(Long idInfoLaboral) {
		this(idInfoLaboral,null,null,null,null,null,null,null,null,null
				,null,null,null,null,null,null,null,null,null,null,null);
	}
	
	public InformeLaboral(Long idInfoLaboral, Long antiguedad, String cargo, String domCorregido, Timestamp fechaIngreso, 
			Timestamp fechaRecepcion, Ocupacion ocupacion, Long idOperador, Rubro rubro, SolicitudIndividuo solicitudIndividuo, 
			Verificador verificador, Vinculo vinculo, String informante, String nroDocInform, String nroInforme, 
			String observacion, String rsCorregida, BigDecimal sueldoNeto, Timestamp timestamp, Set evaObsLaborales, SucEmpresa sucEmpresa) {
		
		super();
		this.idInfoLaboral = idInfoLaboral;
		this.antiguedad = antiguedad;
		this.cargo = cargo;
		this.domCorregido = domCorregido;
		this.fechaIngreso = fechaIngreso;
		this.fechaRecepcion = fechaRecepcion;
		this.ocupacion = ocupacion;
		this.idOperador = idOperador;
		this.rubro = rubro;
		this.solicitudIndividuo = solicitudIndividuo;
		this.verificador = verificador;
		this.vinculo = vinculo;
		this.informante = informante;
		this.nroDocInform = nroDocInform;
		this.nroInforme = nroInforme;
		this.observacion = observacion;
		this.rsCorregida = rsCorregida;
		this.sueldoNeto = sueldoNeto;
		this.timestamp = timestamp;
		this.evaObsLaborales = evaObsLaborales;
		this.sucEmpresa = sucEmpresa;
	}

	public Long getId() {
		return idInfoLaboral;
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

	public Autonomo getAutonomo() {
		return autonomo;
	}

	public void setAutonomo(Autonomo autonomo) {
		this.autonomo = autonomo;
	}

	public String getCargo() {
		return cargo;
	}

	public void setCargo(String cargo) {
		this.cargo = cargo;
	}

	public String getDomCorregido() {
		return domCorregido;
	}

	public void setDomCorregido(String domCorregido) {
		this.domCorregido = domCorregido;
	}

	public Set getEvaObsLaborales() {
		return evaObsLaborales;
	}

	public void setEvaObsLaborales(Set evaObsLaborales) {
		this.evaObsLaborales = evaObsLaborales;
	}

	public Timestamp getFechaIngreso() {
		return fechaIngreso;
	}

	public void setFechaIngreso(Timestamp fechaIngreso) {
		this.fechaIngreso = fechaIngreso;
	}

	public Timestamp getFechaRecepcion() {
		return fechaRecepcion;
	}

	public void setFechaRecepcion(Timestamp fechaRecepcion) {
		this.fechaRecepcion = fechaRecepcion;
	}

	public Long getIdInfoLaboral() {
		return idInfoLaboral;
	}

	public void setIdInfoLaboral(Long idInfoLaboral) {
		this.idInfoLaboral = idInfoLaboral;
	}

	public Long getIdOperador() {
		return idOperador;
	}

	public void setIdOperador(Long idOperador) {
		this.idOperador = idOperador;
	}

	public String getInformante() {
		return informante;
	}

	public void setInformante(String informante) {
		this.informante = informante;
	}

	public String getNroDocInform() {
		return nroDocInform;
	}

	public void setNroDocInform(String nroDocInform) {
		this.nroDocInform = nroDocInform;
	}

	public String getNroInforme() {
		return nroInforme;
	}

	public void setNroInforme(String nroInforme) {
		this.nroInforme = nroInforme;
	}

	public String getObservacion() {
		return observacion;
	}

	public void setObservacion(String observacion) {
		this.observacion = observacion;
	}

	public Ocupacion getOcupacion() {
		return ocupacion;
	}

	public void setOcupacion(Ocupacion ocupacion) {
		this.ocupacion = ocupacion;
	}

	public String getRsCorregida() {
		return rsCorregida;
	}

	public void setRsCorregida(String rsCorregida) {
		this.rsCorregida = rsCorregida;
	}

	public Rubro getRubro() {
		return rubro;
	}

	public void setRubro(Rubro rubro) {
		this.rubro = rubro;
	}

	public SolicitudIndividuo getSolicitudIndividuo() {
		return solicitudIndividuo;
	}

	public void setSolicitudIndividuo(SolicitudIndividuo solicitudIndividuo) {
		this.solicitudIndividuo = solicitudIndividuo;
	}

	public BigDecimal getSueldoNeto() {
		return sueldoNeto;
	}

	public void setSueldoNeto(BigDecimal sueldoNeto) {
		this.sueldoNeto = sueldoNeto;
	}

	public TamEmpresa getTamanioEmpresa() {
		return tamanioEmpresa;
	}

	public void setTamanioEmpresa(TamEmpresa tamanioEmpresa) {
		this.tamanioEmpresa = tamanioEmpresa;
	}

	public Timestamp getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Timestamp timestamp) {
		this.timestamp = timestamp;
	}

	public Verificador getVerificador() {
		return verificador;
	}

	public void setVerificador(Verificador verificador) {
		this.verificador = verificador;
	}

	public Vinculo getVinculo() {
		return vinculo;
	}

	public void setVinculo(Vinculo vinculo) {
		this.vinculo = vinculo;
	}

	public String toString() {
		
		return "General el metodo toString() de la clase InformeLaboral, cuando sea necesario.";
	}

	public SucEmpresa getSucEmpresa() {
		return sucEmpresa;
	}

	public void setSucEmpresa(SucEmpresa sucEmpresa) {
		this.sucEmpresa = sucEmpresa;
	}
	
	
}

