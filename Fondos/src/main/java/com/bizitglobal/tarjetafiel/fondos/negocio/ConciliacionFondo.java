package com.bizitglobal.tarjetafiel.fondos.negocio;


public class ConciliacionFondo {
	public static final Character TIPO_ID_FONDO='F';
	public static final Character TIPO_ID_EXTRACTO='E';
	
	private Long idConciliacion;
	private ConciliacionFondoCabecera conciliacionFondoCabecera;
	private Character tipo;
	private Long idRegistro;
	private Double importe;
	private Integer signo;
	
	private BaseConciliacion contenedor;
	
	public ConciliacionFondo() {
		super();
		
	}

	public ConciliacionFondo(Character tipo, Long idRegistro, Double importe, int signo) {
		super();
		this.tipo = tipo;
		this.idRegistro = idRegistro;
		this.importe = importe;
		this.signo = signo;
	}


	public Long getIdConciliacion() {
		return idConciliacion;
	}

	public void setIdConciliacion(Long idConciliacion) {
		this.idConciliacion = idConciliacion;
	}

	public ConciliacionFondoCabecera getConciliacionFondoCabecera() {
		return conciliacionFondoCabecera;
	}

	public void setConciliacionFondoCabecera(ConciliacionFondoCabecera conciliacionFondoCabecera) {
		this.conciliacionFondoCabecera = conciliacionFondoCabecera;
	}

	public Character getTipo() {
		return tipo;
	}

	public void setTipo(Character tipo) {
		this.tipo = tipo;
	}

	public Long getIdRegistro() {
		return idRegistro;
	}

	public void setIdRegistro(Long idRegistro) {
		this.idRegistro = idRegistro;
	}

	public Double getImporte() {
		return importe;
	}

	public void setImporte(Double importe) {
		this.importe = importe;
	}

	public int getSigno() {
		return signo;
	}

	public void setSigno(int signo) {
		this.signo = signo;
	}
	
	public void setSigno(Integer signo) {
		this.signo = signo;
	}

	public BaseConciliacion getContenedor() {
		return contenedor;
	}

	public void setContenedor(BaseConciliacion contenedor) {
		this.contenedor = contenedor;
	}

}
