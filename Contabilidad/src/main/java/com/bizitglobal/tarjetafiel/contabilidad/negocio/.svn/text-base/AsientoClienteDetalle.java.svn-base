package com.bizitglobal.tarjetafiel.contabilidad.negocio;


public class AsientoClienteDetalle extends AsientoDetalleBase {
	
	private Long comprobante;
	private Long numeroImputaHaber;
	
	public final static String TRA_ASIENTO_CTACTE_CLIENTE = "t_vis_tra_ctacte_clientes";
	public final static String DETALLE_COMPROBANTE = "c_id_ctacte_cliente";
	public final static String DETALLE_IMPORTE = "c_importe";
	public final static String DETALLE_NUMERO_IMPUTA_DEBE = "c_ctacontabledebe";
	public final static String DETALLE_NUMERO_IMPUTA_HABER = "c_ctacontablehaber";
	
	public AsientoClienteDetalle() {
		
	}
	
	public Long getComprobante() {
		return comprobante;
	}


	public void setComprobante(Long comprobante) {
		this.comprobante = comprobante;
	}

	public Long getNumeroImputaHaber() {
		return numeroImputaHaber;
	}

	public void setNumeroImputaHaber(Long numeroImputaHaber) {
		this.numeroImputaHaber = numeroImputaHaber;
	}

	public double getImporte() {
		return Math.abs(this.importe);
	}
	
}
