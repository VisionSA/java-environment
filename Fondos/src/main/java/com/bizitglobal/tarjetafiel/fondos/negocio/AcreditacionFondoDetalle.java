package com.bizitglobal.tarjetafiel.fondos.negocio;

import java.util.Date;

public class AcreditacionFondoDetalle implements Comparable{
	
	private Long idAcreditacionDetalle;
	private AcreditacionFondo acreditacionFondo;
	private Long nroTransaccion;
	private Date fechaSolicitud;
	private Integer tipoTrasferencia;//01-Ctas Propias | 02-Sueldos | 03-Proveedores | 04-Aduana | 14-AFIP
	
	private Long bancoDebito;
	private Integer tipoCuentaDebito; //01-CC$ | 02-CA$ | 13-CCu$s | 15-CAu$s
	private String nroCuentaDebito;
	
	private Long bancoCredito;
	private Integer tipoCuentaCredito; //01-CC$ | 02-CA$ | 13-CCu$s | 15-CAu$s
	private String nroCuentaCredito;
	
	private String nombreBeneficiario;
	private Double importeTrasferencia;
	private String moneda;
	private Long numeroReferencia;
	
	private String observacion1;
	private String observacion2;
	private String blancos;
	private String filler;
	
	
	private Long idCheque; //Este campo viene en las observaciones1 delimitado por numerales Ej: #143911#
	private String conciliado;
	private String registroOriginal;
	
	//Para controlar solapamiento
	private Long fechaSolicitudLong;
	private String fechaSolicitudCadena;
	private Long idBanco;
	
	public AcreditacionFondoDetalle()
	{
	}
	
	public AcreditacionFondoDetalle(Long idAcreditacionDetalle,
			AcreditacionFondo acreditacionFondo, Long nroTransaccion,
			Date fechaSolicitud, int tipoTrasferencia, Long bancoDebito,
			int tipoCuentaDebito, String nroCuentaDebito, Long bancoCredito,
			int tipoCuentaCredito, String nroCuentaCredito,
			String nombreBeneficiario, Double importeTrasferencia,
			String moneda, Long numeroReferencia, String observacion1,
			String observacion2, String blancos, String filler) {
		
		this.idAcreditacionDetalle = idAcreditacionDetalle;
		this.acreditacionFondo = acreditacionFondo;
		this.nroTransaccion = nroTransaccion;
		this.fechaSolicitud = fechaSolicitud;
		this.tipoTrasferencia = tipoTrasferencia;
		this.bancoDebito = bancoDebito;
		this.tipoCuentaDebito = tipoCuentaDebito;
		this.nroCuentaDebito = nroCuentaDebito;
		this.bancoCredito = bancoCredito;
		this.tipoCuentaCredito = tipoCuentaCredito;
		this.nroCuentaCredito = nroCuentaCredito;
		this.nombreBeneficiario = nombreBeneficiario;
		this.importeTrasferencia = importeTrasferencia;
		this.moneda = moneda;
		this.numeroReferencia = numeroReferencia;
		this.observacion1 = observacion1;
		this.observacion2 = observacion2;
		this.blancos = blancos;
		this.filler = filler;
	}

	public Long getIdAcreditacionDetalle() {
		return idAcreditacionDetalle;
	}

	public void setIdAcreditacionDetalle(Long idAcreditacionDetalle) {
		this.idAcreditacionDetalle = idAcreditacionDetalle;
	}

	public AcreditacionFondo getAcreditacionFondo() {
		return acreditacionFondo;
	}

	public void setAcreditacionFondo(AcreditacionFondo acreditacionFondo) {
		this.acreditacionFondo = acreditacionFondo;
	}

	public Long getNroTransaccion() {
		return nroTransaccion;
	}

	public void setNroTransaccion(Long nroTransaccion) {
		this.nroTransaccion = nroTransaccion;
	}

	public Date getFechaSolicitud() {
		return fechaSolicitud;
	}

	public void setFechaSolicitud(Date fechaSolicitud) {
		this.fechaSolicitud = fechaSolicitud;
	}

	public int getTipoTrasferencia() {
		return tipoTrasferencia;
	}

	public void setTipoTrasferencia(int tipoTrasferencia) {
		this.tipoTrasferencia = tipoTrasferencia;
	}

	public Long getBancoDebito() {
		return bancoDebito;
	}

	public void setBancoDebito(Long bancoDebito) {
		this.bancoDebito = bancoDebito;
	}

	public int getTipoCuentaDebito() {
		return tipoCuentaDebito;
	}

	public void setTipoCuentaDebito(int tipoCuentaDebito) {
		this.tipoCuentaDebito = tipoCuentaDebito;
	}

	public String getNroCuentaDebito() {
		return nroCuentaDebito;
	}

	public void setNroCuentaDebito(String nroCuentaDebito) {
		this.nroCuentaDebito = nroCuentaDebito;
	}

	public Long getBancoCredito() {
		return bancoCredito;
	}

	public void setBancoCredito(Long bancoCredito) {
		this.bancoCredito = bancoCredito;
	}

	public int getTipoCuentaCredito() {
		return tipoCuentaCredito;
	}

	public void setTipoCuentaCredito(int tipoCuentaCredito) {
		this.tipoCuentaCredito = tipoCuentaCredito;
	}

	public String getNroCuentaCredito() {
		return nroCuentaCredito;
	}

	public void setNroCuentaCredito(String nroCuentaCredito) {
		this.nroCuentaCredito = nroCuentaCredito;
	}

	public String getNombreBeneficiario() {
		return nombreBeneficiario;
	}

	public void setNombreBeneficiario(String nombreBeneficiario) {
		this.nombreBeneficiario = nombreBeneficiario;
	}

	public Double getImporteTrasferencia() {
		return importeTrasferencia;
	}

	public void setImporteTrasferencia(Double importeTrasferencia) {
		this.importeTrasferencia = importeTrasferencia;
	}

	public String getMoneda() {
		return moneda;
	}

	public void setMoneda(String moneda) {
		this.moneda = moneda;
	}

	public Long getNumeroReferencia() {
		return numeroReferencia;
	}

	public void setNumeroReferencia(Long numeroReferencia) {
		this.numeroReferencia = numeroReferencia;
	}

	public String getObservacion1() {
		return observacion1;
	}

	public void setObservacion1(String observacion1) {
		this.observacion1 = observacion1;
	}

	public String getObservacion2() {
		return observacion2;
	}

	public void setObservacion2(String observacion2) {
		this.observacion2 = observacion2;
	}

	public String getBlancos() {
		return blancos;
	}

	public void setBlancos(String blancos) {
		this.blancos = blancos;
	}

	public String getFiller() {
		return filler;
	}

	public void setFiller(String filler) {
		this.filler = filler;
	}

	public Long getIdCheque() {
		return idCheque;
	}

	public void setIdCheque(Long idCheque) {
		this.idCheque = idCheque;
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
	
	

	public Long getFechaSolicitudLong() {
		return fechaSolicitudLong;
	}

	public void setFechaSolicitudLong(Long fechaSolicitudLong) {
		this.fechaSolicitudLong = fechaSolicitudLong;
	}

	public Long getIdBanco() {
		return idBanco;
	}

	public void setIdBanco(Long idBanco) {
		this.idBanco = idBanco;
	}

	public String getFechaSolicitudCadena() {
		return fechaSolicitudCadena;
	}

	public void setFechaSolicitudCadena(String fechaSolicitudCadena) {
		this.fechaSolicitudCadena = fechaSolicitudCadena;
	}

	@Override
	public int compareTo(Object o) {
		AcreditacionFondoDetalle acreditacionFondoDetalle = (AcreditacionFondoDetalle)o;
		Long result = this.getIdCheque() - acreditacionFondoDetalle.getIdCheque(); 
		return result.intValue()== 0L ? -1 : result.intValue();
	}

	@Override
	public boolean equals(Object obj) {
		boolean result = false;
		if(obj instanceof Cheque) {
			Cheque aux = (Cheque)obj;
			if(aux.getIdCheque().equals(idCheque) 
					&& aux.getBancoPropio().getBanco().getIdBanco().equals(this.bancoDebito)
					&& aux.getBancoPropio().getNumeroCuenta().trim().equalsIgnoreCase(this.nroCuentaDebito.trim())
					) {
				result = true;
			}
		}
		else if(obj instanceof AcreditacionFondoDetalle)
		{
			AcreditacionFondoDetalle aux = (AcreditacionFondoDetalle)obj;
			if(aux.getIdCheque().equals(idCheque)){
				result = true;
			}
		}
		return result;
	}
	
	
	
	
	
	
}
