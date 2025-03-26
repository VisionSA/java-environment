package com.bizitglobal.tarjetafiel.proveedores.negocio;

public class CondImpuesto {
	private Long idCondImpuestos;
	private String cuit;
	private String denominacion;
	private String impGanancias;
	private String impIva;
	private String monotributo;
	private String integranteSoc;
	private String empleador;
	private String activo;
	private Proveedor proveedor;

	public CondImpuesto() {
		this(null,null,null,null,null,null,null,null,null,null);
	}

	public CondImpuesto(Long idCondImpuestos, String cuit, String denominacion, 
			String impGanancias, String impIva, String monotributo, String integranteSoc, 
			String empleador, String activo, Proveedor proveedor) {
		super();
		this.idCondImpuestos = idCondImpuestos;
		this.cuit = cuit;
		this.denominacion = denominacion;
		this.impGanancias = impGanancias;
		this.impIva = impIva;
		this.monotributo = monotributo;
		this.integranteSoc = integranteSoc;
		this.activo = activo;
		this.empleador = empleador;
		this.proveedor = proveedor;
	}

	public String getCuit() {
		return cuit;
	}
	
	public void setCuit(String cuit) {
		this.cuit = cuit;
	}
	
	public String getDenominacion() {
		return denominacion;
	}
	
	public void setDenominacion(String denominacion) {
		this.denominacion = denominacion;
	}
	
	public String getEmpleador() {
		return empleador;
	}
	
	public void setEmpleador(String empleador) {
		this.empleador = empleador;
	}
	
	public Long getIdCondImpuestos() {
		return idCondImpuestos;
	}
	
	public void setIdCondImpuestos(Long idCondImpuestos) {
		this.idCondImpuestos = idCondImpuestos;
	}
	
	public String getImpGanancias() {
		return impGanancias;
	}
	
	public void setImpGanancias(String impGanancias) {
		this.impGanancias = impGanancias;
	}
	
	public String getImpIva() {
		return impIva;
	}
	
	public void setImpIva(String impIva) {
		this.impIva = impIva;
	}
	
	public String getIntegranteSoc() {
		return integranteSoc;
	}
	
	public void setIntegranteSoc(String integranteSoc) {
		this.integranteSoc = integranteSoc;
	}
	
	public String getMonotributo() {
		return monotributo;
	}
	
	public void setMonotributo(String monotributo) {
		this.monotributo = monotributo;
	}
	
	public Proveedor getProveedor() {
		return proveedor;
	}
	
	public void setProveedor(Proveedor proveedor) {
		this.proveedor = proveedor;
	}

	public String getActivo() {
		return activo;
	}

	public void setActivo(String activo) {
		this.activo = activo;
	}

}
