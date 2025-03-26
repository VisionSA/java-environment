package com.bizitglobal.tarjetafiel.proveedores.negocio;

import java.math.BigDecimal;

public class AsientoContable {
	private Long idAsiento;
  	private Comprobante comprobante;
  	private BigDecimal importeDebe;
  	private BigDecimal importeHaber;
  	private String leyenda;
  	// Representa una cuenta contable(en la tabla vieja t_plan_cuenta).
  	private Long nroImputa;

  	
  	public AsientoContable() {
  		this(null,null,null,null,null,null);
  	}
  	
	public AsientoContable(Long idAsiento, Comprobante comprobante, BigDecimal importeDebe, 
							BigDecimal importeHaber, String leyenda, Long nroImputa) {
		super();
		this.idAsiento = idAsiento;
		this.comprobante = comprobante;
		this.importeDebe = importeDebe;
		this.importeHaber = importeHaber;
		this.leyenda = leyenda;
		this.nroImputa = nroImputa;
	}


	public Comprobante getComprobante() {
		return comprobante;
	}
	
	public void setComprobante(Comprobante comprobante) {
		this.comprobante = comprobante;
	}
	
	public Long getIdAsiento() {
		return idAsiento;
	}
	
	public void setIdAsiento(Long idAsiento) {
		this.idAsiento = idAsiento;
	}
	
	public String toString() {
		return "Id:"+idAsiento+"|Importe:";
	}
			
	public BigDecimal getImporteDebe() {
		return importeDebe;//.setScale(2, BigDecimal.ROUND_HALF_DOWN);
	}

	public void setImporteDebe(BigDecimal importeDebe) {
		this.importeDebe = importeDebe;
	}

	public BigDecimal getImporteHaber() {
		return importeHaber;//.setScale(2, BigDecimal.ROUND_HALF_DOWN);
	}

	public void setImporteHaber(BigDecimal importeHaber) {
		this.importeHaber = importeHaber;
	}

	public String getLeyenda() {
		return leyenda;
	}

	public void setLeyenda(String leyenda) {
		this.leyenda = leyenda;
	}

	public boolean equals(Object asiento) {
		boolean result = false;
		if(asiento instanceof AsientoContable) {
			AsientoContable aux = (AsientoContable)asiento;
			if(aux.getIdAsiento().equals(idAsiento)) {
				result = true;
			}
		}
		
		return result;
	}

	public Long getNroImputa() {
		return nroImputa;
	}

	public void setNroImputa(Long nroImputa) {
		this.nroImputa = nroImputa;
	}

}
