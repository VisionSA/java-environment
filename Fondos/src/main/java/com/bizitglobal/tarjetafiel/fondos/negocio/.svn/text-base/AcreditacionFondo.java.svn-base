package com.bizitglobal.tarjetafiel.fondos.negocio;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;


public class AcreditacionFondo {
	
	private Long idAcreditacion;
	private String tipoRegistroInicial;
	private String nroCliente;
	private Date fechaProceso;
	private String nombreArchivo;
	private String tipoRegistroFinal;
	private String blancosFinal;
	private Long totalTranferenciasFinal;
	private BigDecimal importeTotal;
	private Set acreditacionesFondoDetalle;
	private SortedSet acreditacionesDetalleOrdenado;
	
	private String conciliado;
	private String registroOriginal;
	private String registroOriginalFinal;
	private String fechaProcesoCadena;
	private Long idBanco;
	
	
	public AcreditacionFondo()
	{
		
	}
	
	public AcreditacionFondo(Long idAcreditacion, String tipoRegistroInicial,
			String nroCliente, Date fechaProceso, String nombreArchivo,
			String tipoRegistroFinal, String blancosFinal,
			Long totalTranferenciasFinal, Set acreditacionesFondoDetalle) {
		this.idAcreditacion = idAcreditacion;
		this.tipoRegistroInicial = tipoRegistroInicial;
		this.nroCliente = nroCliente;
		this.fechaProceso = fechaProceso;
		this.nombreArchivo = nombreArchivo;
		this.tipoRegistroFinal = tipoRegistroFinal;
		this.blancosFinal = blancosFinal;
		this.totalTranferenciasFinal = totalTranferenciasFinal;
		this.acreditacionesFondoDetalle = acreditacionesFondoDetalle;
	}


	public SortedSet getAcreditacionesDetalleOrdenado() {
		this.acreditacionesDetalleOrdenado = this.acreditacionesDetalleOrdenado==null ? new TreeSet() : this.acreditacionesDetalleOrdenado;
		return this.acreditacionesDetalleOrdenado;
	}

	public void setAcreditacionesDetalleOrdenado(SortedSet acreditacionesDetalleOrdenado) {
		this.acreditacionesDetalleOrdenado = acreditacionesDetalleOrdenado;
	}

	public Long getIdAcreditacion() {
		return idAcreditacion;
	}


	public void setIdAcreditacion(Long idAcreditacion) {
		this.idAcreditacion = idAcreditacion;
	}


	public String getTipoRegistroInicial() {
		return tipoRegistroInicial;
	}


	public void setTipoRegistroInicial(String tipoRegistroInicial) {
		this.tipoRegistroInicial = tipoRegistroInicial;
	}

	public Date getFechaProceso() {
		return fechaProceso;
	}


	public void setFechaProceso(Date fechaProceso) {
		this.fechaProceso = fechaProceso;
	}

	public String getTipoRegistroFinal() {
		return tipoRegistroFinal;
	}


	public void setTipoRegistroFinal(String tipoRegistroFinal) {
		this.tipoRegistroFinal = tipoRegistroFinal;
	}


	public String getBlancosFinal() {
		return blancosFinal;
	}


	public void setBlancosFinal(String blancosFinal) {
		this.blancosFinal = blancosFinal;
	}


	public Long getTotalTranferenciasFinal() {
		return totalTranferenciasFinal;
	}


	public void setTotalTranferenciasFinal(Long totalTranferenciasFinal) {
		this.totalTranferenciasFinal = totalTranferenciasFinal;
	}

	public String getNroCliente() {
		return nroCliente;
	}
	
	public void setNroCliente(String nroCliente) {
		this.nroCliente = nroCliente;
	}

	public String getNombreArchivo() {
		return nombreArchivo;
	}

	public void setNombreArchivo(String nombreArchivo) {
		this.nombreArchivo = nombreArchivo;
	}


	
	public Set getAcreditacionesFondoDetalle() {
		return this.acreditacionesFondoDetalle==null ? new HashSet() : this.acreditacionesFondoDetalle;
	}

	public void setAcreditacionesFondoDetalle(Set acreditacionesFondoDetalle) {
		this.acreditacionesFondoDetalle = acreditacionesFondoDetalle;
	}

	public BigDecimal getImporteTotal() {
		return importeTotal;
	}

	public void setImporteTotal(BigDecimal importeTotal) {
		this.importeTotal = importeTotal;
	}

	public String getConciliado() {
		return conciliado;
	}

	public void setConciliado(String conciliado) {
		this.conciliado = conciliado;
	}

	public String getRegistroOriginal() {
		return registroOriginal;
	}

	public void setRegistroOriginal(String registroOriginal) {
		this.registroOriginal = registroOriginal;
	}

	public String getRegistroOriginalFinal() {
		return registroOriginalFinal;
	}

	public void setRegistroOriginalFinal(String registroOriginalFinal) {
		this.registroOriginalFinal = registroOriginalFinal;
	}

	public String getFechaProcesoCadena() {
		return fechaProcesoCadena;
	}

	public void setFechaProcesoCadena(String fechaProcesoCadena) {
		this.fechaProcesoCadena = fechaProcesoCadena;
	}

	public Long getIdBanco() {
		return idBanco;
	}

	public void setIdBanco(Long idBanco) {
		this.idBanco = idBanco;
	}

	
}
