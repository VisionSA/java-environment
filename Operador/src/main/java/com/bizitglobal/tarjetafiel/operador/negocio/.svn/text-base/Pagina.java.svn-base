package com.bizitglobal.tarjetafiel.operador.negocio;

public class Pagina {
	private Long idPagina;
	private String pagina;

	public Pagina() {
		this(null,null);
	}
	
	public Pagina(Long idPagina, String pagina) {
		super();
		this.idPagina = idPagina;
		this.pagina = pagina;
	}

	public Long getIdPagina() {
		return idPagina;
	}
	
	public void setIdPagina(Long idPagina) {
		this.idPagina = idPagina;
	}
	
	public String getPagina() {
		return pagina;
	}
	
	public void setPagina(String pagina) {
		this.pagina = pagina;
	}
	
	public boolean equals(Object obj) {
		boolean result = false;
		if(obj instanceof Pagina) {
			Pagina pagina = (Pagina)obj;
			if(pagina.getIdPagina().equals(this.idPagina)) {
				result = true;
			}
		}
		
		return result;
	}

}
