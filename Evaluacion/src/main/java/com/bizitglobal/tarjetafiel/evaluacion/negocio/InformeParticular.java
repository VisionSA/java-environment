package com.bizitglobal.tarjetafiel.evaluacion.negocio;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Set;

import com.bizitglobal.tarjetafiel.commons.interfaces.Negocio;
import com.bizitglobal.tarjetafiel.general.negocio.PropietarioVivienda;
import com.bizitglobal.tarjetafiel.general.negocio.TipoVivienda;
import com.bizitglobal.tarjetafiel.general.negocio.TipoZona;
import com.bizitglobal.tarjetafiel.general.negocio.Vinculo;

public class InformeParticular implements Negocio {
	
	private Long idInfoParticular = new Long(0);
	private Long antiguedad = new Long(0);
	private String aplica = "";
	private BigDecimal cuotaAlquiler = new BigDecimal(0);
	private String domCorregido = "";
	private Timestamp fechaRecepcion = null;
	private Long idOperador = new Long(0);
	private PropietarioVivienda propietarioVivienda;
	private SolicitudIndividuo solicitudIndividuo;
	private TipoVivienda tipoVivienda;
	private TipoZona tipoZona;
	private Verificador verificador;
	private Vinculo vinculo;
	private ViviendaEstado viviendaEstado;
	private String informante = "";
	private String nombreCorregido = "";
	private String nroDocInform = "";
	private String nroInforme = "";
	private String observacion = "";
	private Timestamp timestamp = null;
	private Set vehiculos;

	public InformeParticular() {
		this(null,null,null,null,null,null,null,null,null,null
				,null,null,null,null,null,null,null,null,null,null,null);
	}
		
	public InformeParticular(Long idInfoParticular) {
		this(idInfoParticular,null,null,null,null,null,null,null,null,null
				,null,null,null,null,null,null,null,null,null,null,null);
	}
	
	
	public InformeParticular(Long idInfoParticular, Long antiguedad, String aplica, BigDecimal cuotaAlquiler, 
			String domCorregido, Timestamp fechaRecepcion, Long idOperador, PropietarioVivienda propietarioVivienda, 
			SolicitudIndividuo solicitudIndividuo, TipoVivienda tipoVivienda, TipoZona tipoZona, 
			Verificador verificador, Vinculo vinculo, ViviendaEstado viviendaEstado, String informante, 
			String nombreCorregido, String nroDocInform, String nroInforme, String observacion, Timestamp timestamp, 
			Set vehiculos) {
		
		super();
		this.idInfoParticular = idInfoParticular;
		this.antiguedad = antiguedad;
		this.aplica = aplica;
		this.cuotaAlquiler = cuotaAlquiler;
		this.domCorregido = domCorregido;
		this.fechaRecepcion = fechaRecepcion;
		this.idOperador = idOperador;
		this.propietarioVivienda = propietarioVivienda;
		this.solicitudIndividuo = solicitudIndividuo;
		this.tipoVivienda = tipoVivienda;
		this.tipoZona = tipoZona;
		this.verificador = verificador;
		this.vinculo = vinculo;
		this.viviendaEstado = viviendaEstado;
		this.informante = informante;
		this.nombreCorregido = nombreCorregido;
		this.nroDocInform = nroDocInform;
		this.nroInforme = nroInforme;
		this.observacion = observacion;
		this.timestamp = timestamp;
		this.vehiculos = vehiculos;
	}

	public Long getId() {
		return idInfoParticular;
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

	public String getAplica() {
		return aplica;
	}

	public void setAplica(String aplica) {
		this.aplica = aplica;
	}

	public BigDecimal getCuotaAlquiler() {
		return cuotaAlquiler;
	}

	public void setCuotaAlquiler(BigDecimal cuotaAlquiler) {
		this.cuotaAlquiler = cuotaAlquiler;
	}

	public String getDomCorregido() {
		return domCorregido;
	}

	public void setDomCorregido(String domCorregido) {
		this.domCorregido = domCorregido;
	}

	public Timestamp getFechaRecepcion() {
		return fechaRecepcion;
	}

	public void setFechaRecepcion(Timestamp fechaRecepcion) {
		this.fechaRecepcion = fechaRecepcion;
	}

	public Long getIdInfoParticular() {
		return idInfoParticular;
	}

	public void setIdInfoParticular(Long idInfoParticular) {
		this.idInfoParticular = idInfoParticular;
	}

	public Long getIdOperador() {
		return idOperador;
	}

	public void setIdOperador(Long idOperador) {
		this.idOperador = idOperador;
	}

	public PropietarioVivienda getPropietarioVivienda() {
		return propietarioVivienda;
	}

	public void setPropietarioVivienda(PropietarioVivienda propietarioVivienda) {
		this.propietarioVivienda = propietarioVivienda;
	}

	public String getInformante() {
		return informante;
	}

	public void setInformante(String informante) {
		this.informante = informante;
	}

	public String getNombreCorregido() {
		return nombreCorregido;
	}

	public void setNombreCorregido(String nombreCorregido) {
		this.nombreCorregido = nombreCorregido;
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

	public SolicitudIndividuo getSolicitudIndividuo() {
		return solicitudIndividuo;
	}

	public void setSolicitudIndividuo(SolicitudIndividuo solicitudIndividuo) {
		this.solicitudIndividuo = solicitudIndividuo;
	}

	public Timestamp getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Timestamp timestamp) {
		this.timestamp = timestamp;
	}

	public TipoVivienda getTipoVivienda() {
		return tipoVivienda;
	}

	public void setTipoVivienda(TipoVivienda tipoVivienda) {
		this.tipoVivienda = tipoVivienda;
	}

	public TipoZona getTipoZona() {
		return tipoZona;
	}

	public void setTipoZona(TipoZona tipoZona) {
		this.tipoZona = tipoZona;
	}

	public Set getVehiculos() {
		return vehiculos;
	}

	public void setVehiculos(Set vehiculos) {
		this.vehiculos = vehiculos;
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

	public ViviendaEstado getViviendaEstado() {
		return viviendaEstado;
	}

	public void setViviendaEstado(ViviendaEstado viviendaEstado) {
		this.viviendaEstado = viviendaEstado;
	}

	public String toString() {
		
		return "Solicitud Individuo: " + solicitudIndividuo.toString() +
				"verificador: " + verificador.toString();
	}
}

