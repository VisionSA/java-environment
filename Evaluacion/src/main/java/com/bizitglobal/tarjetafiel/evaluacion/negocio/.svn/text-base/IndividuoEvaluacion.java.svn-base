package com.bizitglobal.tarjetafiel.evaluacion.negocio;

import java.sql.Timestamp;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.WordUtils;

import com.bizitglobal.tarjetafiel.commons.interfaces.Negocio;
import com.bizitglobal.tarjetafiel.commons.util.Fecha;
import com.bizitglobal.tarjetafiel.commons.util.Mascara;
import com.bizitglobal.tarjetafiel.general.negocio.Domicilio;
import com.bizitglobal.tarjetafiel.general.negocio.EstadoCivil;
import com.bizitglobal.tarjetafiel.general.negocio.Profesion;
import com.bizitglobal.tarjetafiel.general.negocio.TipoDocumento;
import com.bizitglobal.tarjetafiel.general.negocio.TipoZona;
import com.bizitglobal.tarjetafiel.general.negocio.Vinculo;

public class IndividuoEvaluacion implements Negocio {
	
	private Long idIndividuo = null;
	private String apellido = "";
	private String apellidoMaterno = "";
	private String cbu = "";
	private String cuil = "";
	private Timestamp fechaNacimiento = null;
	private Date  fechaNacimientoFlex= null;
	private Long hijosCargo = new Long(0);
	private ActividadEvaluacion actividad;
	private DiaPago diaPago;
	private Domicilio domicilio;
	private Domicilio domicilioDoc;
	private Educacion educacion;
	private EstadoCivil estadoCivil;
	private Long idOperador = new Long(0);
	private Profesion profesion;
	private TipoDocumento tipoDocumento;
	private Vinculo vinculo;
	private String nombres = "";
	private String nroCuenta = "";
	private String nroDocumento = "";
	private String observacion = "";
	private String sexo = "";
	private Timestamp timestamp = null;
	private Date timestampFlex = null;
	private String tipoCuenta = "";
	private String vehiculoPropio = "";
	private Set domicilios;
	private Set telefonos;
	private Set bancos;
	private Set vehiculos;
	private Set mails;
	private Set tarjetas;
	private Set docAdjuntos;
	private Integer diaPagoFlex;
	private String promotorIndividuo;
	
	
	public static final Mascara sexoStaticList[] = {new Mascara(new String("M"),"Masculino"), new Mascara(new String("F"),"Femenino")};

	public Set getMails() {
		return mails;
	}
	public void setMails(Set mails) {
		this.mails = mails;
	}
	public IndividuoEvaluacion() {
		this(null,null,null,null,null,null,null,new ActividadEvaluacion(),new DiaPago(),new Domicilio(),
				new Domicilio(),new Educacion(),new EstadoCivil(),null,new Profesion(),new TipoDocumento(),new TipoZona(),new Vinculo(),
				null,null, null,null,null,null,null,null,null);
	}
	public IndividuoEvaluacion(Long idIndividuo) {
		this(idIndividuo,null,null,null,null,null,null,new ActividadEvaluacion(),new DiaPago(),new Domicilio(),
				new Domicilio(),new Educacion(),new EstadoCivil(),null,new Profesion(),new TipoDocumento(),new TipoZona(),new Vinculo(),
				null,null, null,null,null,null,null,null,null);
	}
	public IndividuoEvaluacion(Long idIndividuo, String apellido, String apellidoMaterno, String cbu, String cuil, Timestamp fechaNacimiento, 
			Long hijosCargo, ActividadEvaluacion actividadEvaluacion, DiaPago diaPago, Domicilio domicilio, Domicilio domicilioDoc, Educacion educacion, 
			EstadoCivil estadoCivil, Long idOperador, Profesion profesion, TipoDocumento tipoDocumento, TipoZona tipoZona, Vinculo vinculo, 
			ViviendaEstado viviendaEstado, String nombres, String nroCuenta, String nroDocumento, String observacion, String sexo, 
			Timestamp timestamp, String tipoCuenta, String vehiculoPropio) {
		
		super();
		this.idIndividuo = idIndividuo;
		this.apellido = apellido;
		this.apellidoMaterno = apellidoMaterno;
		this.cbu = cbu;
		this.cuil = cuil;
		this.fechaNacimiento = fechaNacimiento;
		this.hijosCargo = hijosCargo;
		this.actividad = actividadEvaluacion;
		this.diaPago = diaPago;
		this.domicilio = domicilio;
		this.domicilioDoc = domicilioDoc;
		this.educacion = educacion;
		this.estadoCivil = estadoCivil;
		this.idOperador = idOperador;
		this.profesion = profesion;
		this.tipoDocumento = tipoDocumento;
		this.vinculo = vinculo;
		this.nombres = nombres;
		this.nroCuenta = nroCuenta;
		this.nroDocumento = nroDocumento;
		this.observacion = observacion;
		this.sexo = sexo;
		this.timestamp = timestamp;
		this.tipoCuenta = tipoCuenta;
		this.vehiculoPropio = vehiculoPropio;
		this.domicilios = new HashSet();
		this.bancos = new HashSet();
		this.vehiculos = new HashSet();
		this.mails = new HashSet();
		this.tarjetas = new HashSet();
	    this.docAdjuntos = new HashSet();
	}

	
	public Long getId() {
		return idIndividuo;
	}

	public String getLabel() {
		return apellido;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getApellidoMaterno() {
		return apellidoMaterno;
	}

	public void setApellidoMaterno(String apellidoMaterno) {
		this.apellidoMaterno = apellidoMaterno;
	}

	public String getCbu() {
		return cbu;
	}

	public void setCbu(String cbu) {
		this.cbu = cbu;
	}

	public String getCuil() {
		return cuil;
	}

	public void setCuil(String cuil) {
		this.cuil = cuil;
	}

	public DiaPago getDiaPago() {
		return diaPago;
	}

	public void setDiaPago(DiaPago diaPago) {
		this.diaPago = diaPago;
	}

	public Domicilio getDomicilio() {
		return domicilio;
	}

	public void setDomicilio(Domicilio domicilio) {
		this.domicilio = domicilio;
	}

	public Domicilio getDomicilioDoc() {
		return domicilioDoc;
	}

	public void setDomicilioDoc(Domicilio domicilioDoc) {
		this.domicilioDoc = domicilioDoc;
	}

	public Educacion getEducacion() {
		return educacion;
	}

	public void setEducacion(Educacion educacion) {
		this.educacion = educacion;
	}

	public EstadoCivil getEstadoCivil() {
		return estadoCivil;
	}

	public void setEstadoCivil(EstadoCivil estadoCivil) {
		this.estadoCivil = estadoCivil;
	}

	public Timestamp getFechaNacimiento() {
		return fechaNacimiento;
	}

	public void setFechaNacimiento(Timestamp fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}


	public Long getHijosCargo() {
		return hijosCargo;
	}

	public void setHijosCargo(Long hijosCargo) {
		this.hijosCargo = hijosCargo;
	}

	public Long getIdIndividuo() {
		return idIndividuo;
	}

	public void setIdIndividuo(Long idIndividuo) {
		this.idIndividuo = idIndividuo;
	}

	public Long getIdOperador() {
		return idOperador;
	}

	public void setIdOperador(Long idOperador) {
		this.idOperador = idOperador;
	}

	public String getNombres() {
		return nombres;
	}

	public void setNombres(String nombres) {
		this.nombres = nombres;
	}

	public String getNroCuenta() {
		return nroCuenta;
	}

	public void setNroCuenta(String nroCuenta) {
		this.nroCuenta = nroCuenta;
	}

	public String getNroDocumento() {
		return nroDocumento;
	}

	public void setNroDocumento(String nroDocumento) {
		this.nroDocumento = nroDocumento;
	}

	public String getObservacion() {
		return observacion;
	}

	public void setObservacion(String observacion) {
		this.observacion = observacion;
	}

	public Profesion getProfesion() {
		return profesion;
	}

	public void setProfesion(Profesion profesion) {
		this.profesion = profesion;
	}

	public String getSexo() {
		return sexo;
	}

	public void setSexo(String sexo) {
		this.sexo = sexo;
	}

	public Timestamp getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Timestamp timestamp) {
		this.timestamp = timestamp;
	}

	public String getTipoCuenta() {
		return tipoCuenta;
	}

	public void setTipoCuenta(String tipoCuenta) {
		this.tipoCuenta = tipoCuenta;
	}

	public TipoDocumento getTipoDocumento() {
		return tipoDocumento;
	}

	public void setTipoDocumento(TipoDocumento tipoDocumento) {
		this.tipoDocumento = tipoDocumento;
	}

	public String getVehiculoPropio() {
		return vehiculoPropio;
	}

	public void setVehiculoPropio(String vehiculoPropio) {
		this.vehiculoPropio = vehiculoPropio;
	}

	public Vinculo getVinculo() {
		return vinculo;
	}

	public void setVinculo(Vinculo vinculo) {
		this.vinculo = vinculo;
	}

	public String toString() {
		
		return " INDIVIDUO " +
			"id: " + idIndividuo + 
			", Apellido: " + apellido +
			", Apellido Materno: " + apellidoMaterno +
			", Nombres: " + nombres + 
			", Tipo DNI: " + tipoDocumento.getIdTipoDocumento() +
			", Nro DNI: " + nroDocumento +
			", Sexo: " + sexo +
			", Fecha Nacimiento: " + fechaNacimiento +
			", c.u.i.t / c.u.i.l.: " + cuil + 
			", nroCBU: " + cbu +
			", hijos a cargo: " + hijosCargo +
			", nro de cta.: " + nroCuenta + 
			", tipo de cta.: " + tipoCuenta;
	}

	/**
	 * @return the actividad
	 */
	public ActividadEvaluacion getActividad() {
		return actividad;
	}

	/**
	 * @param actividad the actividad to set
	 */
	public void setActividad(ActividadEvaluacion actividad) {
		this.actividad = actividad;
	}
	
	public Set getDomicilios() {
		return domicilios;
	}
	
	public void setDomicilios(Set domicilios) {
		this.domicilios = domicilios;
	}
	public Set getTelefonos() {
		return telefonos;
	}
	public void setTelefonos(Set telefonos) {
		this.telefonos = telefonos;
	}
	
	public Set getBancos() {
		return bancos;
	}
	public void setBancos(Set bancos) {
		this.bancos = bancos;
	}
	public Set getVehiculos() {
		return vehiculos;
	}
	public void setVehiculos(Set vehiculos) {
		this.vehiculos = vehiculos;
	}
	public Set getTarjetas() {
		return tarjetas;
	}
	public void setTarjetas(Set tarjetas) {
		this.tarjetas = tarjetas;
	}
	public Set getDocAdjuntos() {
		return docAdjuntos;
	}
	public void setDocAdjuntos(Set docAdjuntos) {
		this.docAdjuntos = docAdjuntos;
	}
	public Date getTimestampFlex() {
		if(timestamp!=null){
			timestampFlex = new Date(timestamp.getTime());
		}
		return timestampFlex;
	}
	public void setTimestampFlex(Date timestampFlex) {
		this.timestampFlex = timestampFlex;
		if(timestampFlex!=null){
			timestamp = new Timestamp(timestampFlex.getTime());
		}
	}
	
	public Date getFechaNacimientoFlex() {
		if(fechaNacimiento!=null){
			fechaNacimientoFlex = new Date(fechaNacimiento.getTime());
		}
		return fechaNacimientoFlex;
	}
	
	public void setFechaNacimientoFlex(Date fecha){
		fechaNacimientoFlex = fecha;
		if(fecha != null)
			this.fechaNacimiento = new Timestamp(fecha.getTime());

	}
	
	public Integer getDiaPagoFlex() {
		return diaPagoFlex;
	}
	
	public void setDiaPagoFlex(Integer diaPagoFlex) {
		this.diaPagoFlex = diaPagoFlex;
	}
	
	/**
	 * Requiere: nombre != null y apellido != null
	 * @return Apellido, Nombres
	 */
	public String getNombreCompleto() {		
		return StringUtils.upperCase(this.apellido).trim() + ", " + WordUtils.capitalizeFully(this.nombres).trim();
	}
	public String getPromotorIndividuo() {
		return promotorIndividuo;
	}
	public void setPromotorIndividuo(String promotorIndividuo) {
		this.promotorIndividuo = promotorIndividuo;
	}

}

