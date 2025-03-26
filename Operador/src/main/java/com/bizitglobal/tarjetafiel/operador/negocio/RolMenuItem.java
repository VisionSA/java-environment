package com.bizitglobal.tarjetafiel.operador.negocio;

public class RolMenuItem {
	private Long idRolMenuItem;
	private Rol rol;
	private MenuItem menuItem;
	
	public RolMenuItem() {
		this(null,null,null);
	}
	
	public RolMenuItem(Long id, Rol rol, MenuItem menuItem) {
		super();
		this.idRolMenuItem = id;
		this.rol = rol;
		this.menuItem = menuItem;
	}

	public Long getIdRolMenuItem() {
		return idRolMenuItem;
	}

	public void setIdRolMenuItem(Long id) {
		this.idRolMenuItem = id;
	}

	public MenuItem getMenuItem() {
		return menuItem;
	}

	public void setMenuItem(MenuItem menuItem) {
		this.menuItem = menuItem;
	}

	public Rol getRol() {
		return rol;
	}

	public void setRol(Rol rol) {
		this.rol = rol;
	}
	
}
