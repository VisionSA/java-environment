package com.bizitglobal.tarjetafiel.fondos.negocio;

import java.util.Date;

import com.bizitglobal.tarjetafiel.general.negocio.Banco;

public class MovimientoConciliable extends BaseConciliacion{
	
	private Long idChequeHistorial;
	private String numero;
	private Double importe;
	private Date fecha;      //Asiento
	private Character signo; //D:debito C:credito.
	private String descripcion; // leyenda asiento item
	private String numeroCuenta; //
	private Character tipo;     //cheque
	private String beneficiario;//cheque
	private Banco banco;			
	private Long idBanco;
	private Long idAsiento;//asiento
	
	
	public MovimientoConciliable()
	{
		
	}
	
	public MovimientoConciliable(Banco banco, String beneficiario,
			String descripcion, Date fecha, Double importe, String numero,
			String numeroCuenta, Character signo, Character tipo) {
		super();
		this.banco = banco;
		this.beneficiario = beneficiario;
		this.descripcion = descripcion;
		this.fecha = fecha;
		this.importe = importe;
		this.numero = numero;
		this.numeroCuenta = numeroCuenta;
		this.signo = signo;
		this.tipo = tipo;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public Double getImporte() {
		return importe;
	}

	public void setImporte(Double importe) {
		this.importe = importe;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public Character getSigno() {
		return signo;
	}

	public void setSigno(Character signo) {
		this.signo = signo;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getNumeroCuenta() {
		return numeroCuenta;
	}

	public void setNumeroCuenta(String numeroCuenta) {
		this.numeroCuenta = numeroCuenta;
	}

	public Character getTipo() {
		return tipo;
	}

	public void setTipo(Character tipo) {
		this.tipo = tipo;
	}

	public String getBeneficiario() {
		return beneficiario;
	}

	public void setBeneficiario(String beneficiario) {
		this.beneficiario = beneficiario;
	}

	public Banco getBanco() {
		return banco;
	}

	public void setBanco(Banco banco) {
		this.banco = banco;
	}

	public Long getIdBanco() {
		return idBanco;
	}

	public void setIdBanco(Long idBanco) {
		this.idBanco = idBanco;
	}

	@Override
	public String getDescripcionGen() {
		return descripcion;
	}

	@Override
	public int getDebCred() {
		return 0;
	}

	@Override
	public Date getFechaGeneral() {
		return fecha;
	}

	public Long getIdChequeHistorial() {
		return idChequeHistorial;
	}

	public void setIdChequeHistorial(Long idChequeHistorial) {
		this.idChequeHistorial = idChequeHistorial;
	}

	public Long getIdAsiento() {
		return idAsiento;
	}

	public void setIdAsiento(Long idAsiento) {
		this.idAsiento = idAsiento;
	}
	

}
