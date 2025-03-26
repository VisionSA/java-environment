package com.bizitglobal.tarjetafiel.fondos.negocio;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.bizitglobal.tarjetafiel.operador.negocio.Operador;

public class ConciliacionFondoCabecera  {
	
	private Long idCabeceraConciliacion; //idGrupo
	private String comentario;
	private Date fechaGeneracion; //fecha de la conciliacion automatica o upload.
	private Date fechaConfirmacion;
	private Operador operadorConfirmo;
	private Character conciliado; // [S | N | R:revertido]
	private Date fechaReversion;
	private Operador operadorReversion;
	private BancoPropio bancoPropio;
	
	private Set conciliacionFondos;
	private List conciliacionFondosFlex; 
	
/*@I3918*/	//Atributos solo válidos para búsquedas
	private String numeroOperacion; //Número de la operación que concilia esta cabecera
	private Double importeTotalConciliado; //Importe total de la conciliación
/*@F3918*/	
	public static final Character CONCILIADO_SI = 'S'; 
	public static final Character CONCILIADO_NO = 'N';
	public static final Character CONCILIADO_REVERTIDO = 'R';
	
	public static final int FECHA_CONCILIACION = 1;
	public static final int FECHA_MOVIMIENTO = 2;
	public static final int FECHA_EXTRACTO = 3;
	public static final int SIN_FECHA = 0;
	
	public ConciliacionFondoCabecera(){
		
	}
	
	public ConciliacionFondoCabecera(String comentario, Date fechaConfirmacion,
			Date fechaGeneracion, Set setConciliacionFondo,
			Operador operadorConfirmo, Character conciliado,
			BancoPropio bancoPropio) {
		super();
		this.comentario = comentario;
		this.fechaConfirmacion = fechaConfirmacion;
		this.fechaGeneracion = fechaGeneracion;
		this.conciliacionFondos = setConciliacionFondo;
		this.operadorConfirmo = operadorConfirmo;
		this.conciliado = conciliado;
		this.bancoPropio = bancoPropio;
	}
	
	public Long getIdCabeceraConciliacion() {
		return idCabeceraConciliacion;
	}

	public void setIdCabeceraConciliacion(Long idCabeceraConciliacion) {
		this.idCabeceraConciliacion = idCabeceraConciliacion;
	}

	public String getComentario() {
		return comentario;
	}

	public void setComentario(String comentario) {
		this.comentario = comentario;
	}

	public Date getFechaGeneracion() {
		return fechaGeneracion;
	}

	public void setFechaGeneracion(Date fechaGeneracion) {
		this.fechaGeneracion = fechaGeneracion;
	}

	public Date getFechaConfirmacion() {
		return fechaConfirmacion;
	}

	public void setFechaConfirmacion(Date fechaConfirmacion) {
		this.fechaConfirmacion = fechaConfirmacion;
	}

	public Operador getOperadorConfirmo() {
		return operadorConfirmo;
	}

	public void setOperadorConfirmo(Operador operadorConfirmo) {
		this.operadorConfirmo = operadorConfirmo;
	}

	public Set getConciliacionFondos() {
		this.conciliacionFondos = conciliacionFondos==null ? new HashSet() : conciliacionFondos;
		return conciliacionFondos;
	}

	public void setConciliacionFondos(Set conciliacionFondos) {
		this.conciliacionFondos = conciliacionFondos;
	}

	public Character getConciliado() {
		return conciliado;
	}

	public void setConciliado(Character conciliado) {
		this.conciliado = conciliado;
	}

	public Date getFechaReversion() {
		return fechaReversion;
	}

	public void setFechaReversion(Date fechaReversion) {
		this.fechaReversion = fechaReversion;
	}

	public Operador getOperadorReversion() {
		return operadorReversion;
	}

	public void setOperadorReversion(Operador operadorReversion) {
		this.operadorReversion = operadorReversion;
	}

	public BancoPropio getBancoPropio() {
		return bancoPropio;
	}

	public void setBancoPropio(BancoPropio bancoPropio) {
		this.bancoPropio = bancoPropio;
	}

	public List getConciliacionFondosFlex() {
		return conciliacionFondosFlex;
	}

	public void setConciliacionFondosFlex(List conciliacionFondosFlex) {
		this.conciliacionFondosFlex = conciliacionFondosFlex;
	}
/*@I3918*/
	public String getNumeroOperacion() {
		return numeroOperacion;
	}

	public void setNumeroOperacion(String numeroOperacion) {
		this.numeroOperacion = numeroOperacion;
	}

	public Double getImporteTotalConciliado() {
		return importeTotalConciliado;
	}

	public void setImporteTotalConciliado(Double importeTotalConciliado) {
		this.importeTotalConciliado = importeTotalConciliado;
	}
/*@F3918*/
	
}
