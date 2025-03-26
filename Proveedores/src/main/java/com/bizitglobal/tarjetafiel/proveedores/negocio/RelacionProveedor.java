package com.bizitglobal.tarjetafiel.proveedores.negocio;


public class RelacionProveedor {
	private Long idRelacion;
	private Long cuitPadre;
	private Long cuitHijo;
	private String observacion;
	private Proveedor proveedor;
	
	public RelacionProveedor() {
		this(null,null,null,null,null);
	}
	
	public RelacionProveedor(Long idRelacion, Long cuitPadre, Long cuitHijo, 
			String observacion, Proveedor proveedor) {
		super();
		this.idRelacion = idRelacion;
		this.cuitPadre = cuitPadre;
		this.cuitHijo = cuitHijo;
		this.observacion = observacion;
		this.proveedor = proveedor;
	}

	public Long getCuitHijo() {
		return cuitHijo;
	}
	
	public void setCuitHijo(Long cuitHijo) {
		this.cuitHijo = cuitHijo;
	}
	
	public Long getCuitPadre() {
		return cuitPadre;
	}
	
	public void setCuitPadre(Long cuitPadre) {
		this.cuitPadre = cuitPadre;
	}
	
	public Long getIdRelacion() {
		return idRelacion;
	}
	
	public void setIdRelacion(Long idRelacion) {
		this.idRelacion = idRelacion;
	}
	
	public String getObservacion() {
		return observacion;
	}
	
	public void setObservacion(String observacion) {
		this.observacion = observacion;
	}
	
	public Proveedor getProveedor() {
		return proveedor;
	}
	
	public void setProveedor(Proveedor proveedor) {
		this.proveedor = proveedor;
	}

}
