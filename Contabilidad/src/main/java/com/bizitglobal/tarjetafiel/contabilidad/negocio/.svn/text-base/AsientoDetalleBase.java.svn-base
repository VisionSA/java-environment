package com.bizitglobal.tarjetafiel.contabilidad.negocio;

public class AsientoDetalleBase {

	protected Long asiento;
	protected Long numeroImputa; // usado para el debe en el caso de importacion clientes y comercios
	protected double importeDebe;
	protected double importeHaber;
	protected double importe;
	protected String signo;
	protected  String leyenda;	
	
	
	public final static String DETALLE_ASIENTO = "c_id_asiento";
	public final static String DETALLE_NUMERO_IMPUTA = "c_numero_imputa";
	public final static String DETALLE_IMPORTE_DEBE = "c_importe_debe";
	public final static String DETALLE_IMPORTE_HABER = "c_importe_haber";
	public final static String DETALLE_LEYENDA = "c_leyenda";
	
	
	
	public Long getAsiento() {
		return asiento;
	}

	public void setAsiento(Long asiento) {
		this.asiento = asiento;
	}

	public double getImporte() {
		return Math.abs(importeDebe-importeHaber);
	}

	public void setImporte(double importe) {
		this.importe = importe;
	}

	public double getImporteDebe() {
		return importeDebe;
	}

	public void setImporteDebe(double importeDebe) {
		this.importeDebe = importeDebe;
	}

	public double getImporteHaber() {
		return importeHaber;
	}

	public void setImporteHaber(double importeHaber) {
		this.importeHaber = importeHaber;
	}

	public String getLeyenda() {
		return leyenda;
	}

	public void setLeyenda(String leyenda) {
		this.leyenda = leyenda;
	}

	public Long getNumeroImputa() {
		return numeroImputa;
	}

	public void setNumeroImputa(Long numeroImputa) {
		this.numeroImputa = numeroImputa;
	}

	public String getSigno() {
		if (importeHaber>0) return "C";
		else return "D";
	}

	public void setSigno(String signo) {
		this.signo = signo;
	}

	
	
	
}
