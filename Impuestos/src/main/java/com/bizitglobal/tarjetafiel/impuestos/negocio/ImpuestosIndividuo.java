package com.bizitglobal.tarjetafiel.impuestos.negocio;

public class ImpuestosIndividuo {
	private Long idImpIndividuo;
	private Individuo individuo;
	private Categoria categoria;
		
	public ImpuestosIndividuo() {
		this(null,null,null);
	}
	
	public ImpuestosIndividuo(Long idImpIndividuo, Individuo individuo, Categoria categoria) {
		super();
		this.idImpIndividuo = idImpIndividuo;
		this.individuo = individuo;
		this.categoria = categoria;
	}

	public Categoria getCategoria() {
		return categoria;
	}
	
	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}
	
	public Long getIdImpIndividuo() {
		return idImpIndividuo;
	}
	
	public void setIdImpIndividuo(Long idImpIndividuo) {
		this.idImpIndividuo = idImpIndividuo;
	}
	
	public Individuo getIndividuo() {
		return individuo;
	}
	
	public void setIndividuo(Individuo individuo) {
		this.individuo = individuo;
	}

	public String toString() {
		return "Id:"+idImpIndividuo+"|Individuo:"+individuo.getCuit()+"|Categoria:"+categoria.getDescripcion();
	}
	
	public boolean equals(Object impuestoIndividuo) {
		boolean result = false;
		if(impuestoIndividuo instanceof ImpuestosIndividuo) {
			ImpuestosIndividuo aux = (ImpuestosIndividuo)impuestoIndividuo;
			if(aux.getIdImpIndividuo().equals(idImpIndividuo)) {
				result = true;
			}
		}
		
		return result;
	}

}
