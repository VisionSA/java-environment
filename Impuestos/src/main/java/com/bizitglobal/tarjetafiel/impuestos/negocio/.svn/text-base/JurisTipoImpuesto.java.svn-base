package com.bizitglobal.tarjetafiel.impuestos.negocio;


public class JurisTipoImpuesto {
	private Long idJurisTipoImpuesto;
	private Jurisdiccion jurisdiccion;
	private TipoImpuesto tipoImpuesto;
	
	public JurisTipoImpuesto() {
		this(null,null,null);
	}
	
	public JurisTipoImpuesto(Long idJurisTipoImpuesto, Jurisdiccion jurisdiccion, TipoImpuesto tipoImpuesto) {
		super();
		this.idJurisTipoImpuesto = idJurisTipoImpuesto;
		this.jurisdiccion = jurisdiccion;
		this.tipoImpuesto = tipoImpuesto;
	}
	
	public TipoImpuesto getTipoImpuesto() {
		return tipoImpuesto;
	}

	public void setTipoImpuesto(TipoImpuesto tipoImpuesto) {
		this.tipoImpuesto = tipoImpuesto;
	}
	
	public Long getIdJurisTipoImpuesto() {
		return idJurisTipoImpuesto;
	}

	public void setIdJurisTipoImpuesto(Long idJurisTipoImpuesto) {
		this.idJurisTipoImpuesto = idJurisTipoImpuesto;
	}

	public Jurisdiccion getJurisdiccion() {
		return jurisdiccion;
	}

	public void setJurisdiccion(Jurisdiccion jurisdiccion) {
		this.jurisdiccion = jurisdiccion;
	}

	public String toString() {
		return "Id: "+idJurisTipoImpuesto+
				"|Tipo Impuesto: "+tipoImpuesto+
				"|Jurisdicción: "+jurisdiccion;
	}
	
	public boolean equals(Object obj) {
		boolean result = false;
		if(obj instanceof JurisTipoImpuesto) {
			JurisTipoImpuesto aux = (JurisTipoImpuesto)obj;
			if(aux.getIdJurisTipoImpuesto().equals(idJurisTipoImpuesto)) {
				result = true;
			}
		}
		
		return result;
	}

}
