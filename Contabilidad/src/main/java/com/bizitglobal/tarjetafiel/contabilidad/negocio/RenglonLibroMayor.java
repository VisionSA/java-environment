package com.bizitglobal.tarjetafiel.contabilidad.negocio;

import java.math.BigDecimal;
import java.util.Date;


public class RenglonLibroMayor {
	
	public final static String RENGLON = "t_cont_asientos_d";
	public static String EJERCICIO = "c_ejercicio";
	public static String EMPRESA = "c_empresa";
	public static String CUENTA = "c_numero_imputa";
	public static String FECHA_CONTAB = "c_fecha_contab";
	public static String SIGNO = "c_signo";
	public static String IMPORTE = "c_importe";
	public static String LEYENDA = "c_leyenda";
	public static String FECHA_CARGA = "c_fecha_carga";
	public static String ID_ASIENTO = "c_asiento";
	public static String SALDO_ACUMULADO = "c_saldo_acumulado";
	
	//para asiento de fondos
	public static String RENGLON_FONDOS = "t_vis_fon_asientos_item";
	public static String CABECERA_FONDOS = "t_vis_fon_asientos";
	public static String FECHA = "c_fecha";
	public static String FECHA_CONTABILIZADO = "c_fecha_contabilizado";
	public static String ID_ASIENTO_FONDOS = "c_id_asiento";
	//public static String LEYENDA_FONDOS = "tm.c_leyenda";
	//public static String IMPORTE_FONDOS = "asiItm.c_importe";
	//public static String SIGNO_FONDOS = "asiItm.c_signo";
	public static String CUENTA_FONDOS = "c_id_plan_cuenta";
	
	
	private Date fechaContab;
	private Date fechaCarga;
	private Long asiento;
	private String leyenda;
	private BigDecimal debe;
	private BigDecimal haber;
	private BigDecimal saldo;
	private BigDecimal importe;
	private String importeCadena;
	private String signo;
	private BigDecimal saldoAcumulado;
	
    public RenglonLibroMayor() {
    	
    }
    
    public void calcularDebeHaber(BigDecimal saldoAnterior) {
    	recuperarImporte();
    	if (signo.compareTo("D")==0) {
    		debe = importe;
    		haber= new BigDecimal(0);
    	}
    	if (signo.compareTo("C")==0) {
    		haber = importe;
    		debe = new BigDecimal(0);
    	}
    	saldo = new BigDecimal(debe.doubleValue() - haber.doubleValue());
        this.saldoAcumulado = new BigDecimal(saldoAnterior.longValue() + saldo.longValue());
        this.saldoAcumulado = saldoAcumulado.setScale(2,BigDecimal.ROUND_HALF_DOWN);
    }
    
    public void recuperarImporte() {
    	BigDecimal num = new BigDecimal(importeCadena);
    	importe = num;
    }
    
	public Long getAsiento() {
		return asiento;
	}

	public void setAsiento(Long asiento) {
		this.asiento = asiento;
	}

	public BigDecimal getDebe() {
		return debe;
	}

	public void setDebe(BigDecimal debe) {
		this.debe = debe;
	}

	public Date getFechaContab() {
		return fechaContab;
	}

	public void setFechaContab(Date fechaContab) {
		this.fechaContab = fechaContab;
	}

	public BigDecimal getHaber() {
		return haber;
	}

	public void setHaber(BigDecimal haber) {
		this.haber = haber;
	}

	public String getLeyenda() {
		return leyenda;
	}

	public void setLeyenda(String leyenda) {
		this.leyenda = leyenda;
	}

	public BigDecimal getSaldo() {
		return saldo;
	}

	public void setSaldo(BigDecimal saldo) {
		this.saldo = saldo;
	}

	public BigDecimal getImporte() {
		return importe;
	}

	public void setImporte(BigDecimal importe) {
		this.importe = importe;
	}

	public String getSigno() {
		return signo;
	}

	public void setSigno(String signo) {
		this.signo = signo;
	}

	public Date getFechaCarga() {
		return fechaCarga;
	}

	public void setFechaCarga(Date fechaCarga) {
		this.fechaCarga = fechaCarga;
	}

	public String getImporteCadena() {
		return importeCadena;
	}

	public void setImporteCadena(String importeCadena) {
		this.importeCadena = importeCadena;
	}

	public BigDecimal getSaldoAcumulado() {
		return saldoAcumulado;
	}

	public void setSaldoAcumulado(BigDecimal saldoAcumulado) {
		this.saldoAcumulado = saldoAcumulado;
	}

        
}
