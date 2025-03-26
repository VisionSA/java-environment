package com.bizitglobal.tarjetafiel.operador.negocio;

public class RolPaginaPermiso {
	private Long idRolPaginaPermiso;
	private Rol rol;
	private Pagina pagina;
	private Permiso permiso;
	
	public RolPaginaPermiso() {
		this(null,null,null,null);
	}
	
	public RolPaginaPermiso(Long idRolPagina, Rol rol, Pagina pagina, Permiso permiso) {
		super();
		this.idRolPaginaPermiso = idRolPagina;
		this.rol = rol;
		this.pagina = pagina;
		this.permiso = permiso;
	}

	public Pagina getPagina() {
		return pagina;
	}
	
	public void setPagina(Pagina pagina) {
		this.pagina = pagina;
	}
	
	public Rol getRol() {
		return rol;
	}
	
	public void setRol(Rol rol) {
		this.rol = rol;
	}

	public Permiso getPermiso() {
		return permiso;
	}

	public void setPermiso(Permiso permiso) {
		this.permiso = permiso;
	}

	public Long getIdRolPaginaPermiso() {
		return idRolPaginaPermiso;
	}

	public void setIdRolPaginaPermiso(Long idRolPaginaPermiso) {
		this.idRolPaginaPermiso = idRolPaginaPermiso;
	}

}
