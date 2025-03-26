package com.bizitglobal.tarjetafiel.impuestos.negocio;

import java.math.BigDecimal;

import com.bizitglobal.tarjetafiel.commons.interfaces.Negocio;
import com.bizitglobal.tarjetafiel.general.negocio.Partido;
import com.bizitglobal.tarjetafiel.general.negocio.Provincia;

public class Impuesto implements Negocio{
	private Long idImpuesto;
	private String descripcion;
	private Long porcAlicuota;
	private Character percepcion;
	private Character imponibleGncias;
	private BigDecimal importeMinimo;
	private Partido partido;
	private Provincia provincia;
	private Categoria categoria;
	
	public Impuesto(Long idImpuesto) {
		this(idImpuesto,null,null,null,null,null,new Partido(),new Provincia(),new Categoria());
	}
	
	public Impuesto() {
		this(null,null,null,null,null,null,new Partido(),new Provincia(),new Categoria());
	}
	
	public Impuesto(Long idImpuesto, String descripcion, Long porcAlicuota,
			Character percepcion, Character imponibleGncias, 
			BigDecimal importeMinimo, Partido partido, 
			Provincia provincia, Categoria categoria) {
		super();
		this.idImpuesto = idImpuesto;
		this.descripcion = descripcion;
		this.porcAlicuota = porcAlicuota;
		this.percepcion = percepcion;
		this.imponibleGncias = imponibleGncias;
		this.importeMinimo = importeMinimo;
		this.partido = partido;
		this.provincia = provincia;
		this.categoria = categoria;
	}



	public String getDescripcion() {
		return descripcion;
	}
	
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	
	public Long getIdImpuesto() {
		return idImpuesto;
	}
	
	public void setIdImpuesto(Long idImpuesto) {
		this.idImpuesto = idImpuesto;
	}
	
	public BigDecimal getImporteMinimo() {
		return importeMinimo;
	}
	
	public void setImporteMinimo(BigDecimal importeMinimo) {
		this.importeMinimo = importeMinimo;
	}
	
	public Partido getPartido() {
		return partido;
	}

	public void setPartido(Partido partido) {
		this.partido = partido;
	}

	public Long getPorcAlicuota() {
		return porcAlicuota;
	}
	
	public void setPorcAlicuota(Long porcAlicuota) {
		this.porcAlicuota = porcAlicuota;
	}

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

	public Character getImponibleGncias() {
		return imponibleGncias;
	}

	public void setImponibleGncias(Character imponibleGncias) {
		this.imponibleGncias = imponibleGncias;
	}

	public Character getPercepcion() {
		return percepcion;
	}

	public void setPercepcion(Character percepcion) {
		this.percepcion = percepcion;
	}

	public Provincia getProvincia() {
		return provincia;
	}

	public void setProvincia(Provincia provincia) {
		this.provincia = provincia;
	}

	
	public String toString() {
		return "Id:"+idImpuesto+"|Descripcion:"+descripcion+"|IGanancias:"+imponibleGncias;
	}
	
	public boolean equals(Object unImpuesto) {
		boolean result = false;
		if(unImpuesto instanceof Impuesto) {
			Impuesto aux = (Impuesto)unImpuesto;
			if(aux.getIdImpuesto().equals(idImpuesto)) {
				result = true;
			}
		}
		return result;
	}

	public Long getId() {
		return idImpuesto;
	}

	public String getLabel() {
		return descripcion;
	}
	
}
