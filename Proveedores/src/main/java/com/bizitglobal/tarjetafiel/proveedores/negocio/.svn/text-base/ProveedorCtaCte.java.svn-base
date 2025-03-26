package com.bizitglobal.tarjetafiel.proveedores.negocio;

import java.sql.Timestamp;
/*@I5562*/import java.text.NumberFormat;
import java.text.DecimalFormat;
/*@F5562*/import java.math.BigDecimal;


public class ProveedorCtaCte {
	private Long idComprobante;	
	private Long idProveedor;	
	private String datosProveedor;
	private Timestamp fechaEmision;	
	private String fecha;
	private String numeroCte;
	private String tipoCte;
	private BigDecimal debe;
	private BigDecimal haber;
	private BigDecimal saldo;
	private BigDecimal saldoAnterior;

	public final static String ID_COMPROBANTE = "c_id_comprobante";	
	public final static String ID_PROVEEDOR = "c_id_proveedor";
	public final static String DATOS_PROVEEDOR = "datosprov";
	public final static String FECHA_EMISION = "c_fecha_emision";
	public final static String FECHA = "fecha";
	public final static String NUMERO_CTE = "numerocte";
	public final static String TIPO_CTE = "tipo";
	public final static String DEBE = "impdebe";
	public final static String HABER = "imphaber";
	public final static String SALDO = "saldo";
	public final static String SALDO_ANTERIOR = "saldoant";
	
	public ProveedorCtaCte(){
		this(null,null,null,null,null,null,null,null,null,null,null);
	}
	
	public ProveedorCtaCte( Long idComprobante, Long idProveedor, String datosProveedor, Timestamp fechaEmision,
			String fecha, String numeroCte, String tipoCte, BigDecimal debe, BigDecimal haber, BigDecimal saldo,
			BigDecimal saldoAnterior){
		super();
		this.idComprobante = idComprobante;		
		this.idProveedor = idProveedor;
		this.datosProveedor = datosProveedor;
		this.fechaEmision = fechaEmision;
		this.fecha = fecha;
		this.numeroCte = numeroCte;
		this.tipoCte = tipoCte;
		this.debe = debe;
		this.haber = haber;
		this.saldo = saldo;
		this.saldoAnterior = saldoAnterior;
	}

	public String getDatosProveedor() {
		return datosProveedor;
	}

	public void setDatosProveedor(String datosProveedor) {
		this.datosProveedor = datosProveedor;
	}

/*@I5562*/	public String getDebe() {
		return bigDecimalToString(debe);
/*@F5562*/	}

	public void setDebe(BigDecimal debe) {
		this.debe = debe;
	}

	public String getFecha() {
		return fecha;
	}

	public void setFecha(String fecha) {
		this.fecha = fecha;
	}

	public Timestamp getFechaEmision() {
		return fechaEmision;
	}

	public void setFechaEmision(Timestamp fechaEmision) {
		this.fechaEmision = fechaEmision;
	}

/*@I5562*/	public String getHaber() {
		return bigDecimalToString(this.haber);
/*@F5562*/	}

	public void setHaber(BigDecimal haber) {
		this.haber = haber;
	}

	public Long getIdComprobante() {
		return idComprobante;
	}

	public void setIdComprobante(Long idComprobante) {
		this.idComprobante = idComprobante;
	}

	public Long getIdProveedor() {
		return idProveedor;
	}

	public void setIdProveedor(Long idProveedor) {
		this.idProveedor = idProveedor;
	}

	public String getNumeroCte() {
		return numeroCte;
	}

	public void setNumeroCte(String numeroCte) {
		this.numeroCte = numeroCte;
	}

/*@I5562*/	public String getSaldo() {
		return bigDecimalToString(saldo);
/*@F5562*/	}

	public void setSaldo(BigDecimal saldo) {
		this.saldo = saldo;
	}

	public BigDecimal getSaldoAnterior() {
		return saldoAnterior;
	}

	public void setSaldoAnterior(BigDecimal saldoAnterior) {
		this.saldoAnterior = saldoAnterior;
	}

	public String getTipoCte() {
		return tipoCte;
	}

	public void setTipoCte(String tipoCte) {
		this.tipoCte = tipoCte;
	}
	
	public boolean equals(Object obj) {
		if (obj instanceof ProveedorCtaCte == false)
			return false;
		ProveedorCtaCte proveedorCtaCte = (ProveedorCtaCte)obj;
		return proveedorCtaCte.getIdComprobante().equals(idComprobante);
	}
	
/*@I5562*/	private String bigDecimalToString(BigDecimal big){
		double datoDoubleD = 0;
		 //se verifica que sean correctos los argumentos recibidos
		 if(big != null)
		 datoDoubleD = big.doubleValue();
		 /**
		 * Los # indican valores no obligatorios
		 * Los 0 indican que si no hay valor se pondrá un cero
		 */
/*@I5562*/		 NumberFormat formatter = new DecimalFormat("#,##0.00");
/*@F5562*/		 return formatter.format(datoDoubleD);
	 }
/*@F5562*/	
}