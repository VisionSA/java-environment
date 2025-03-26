package com.bizitglobal.tarjetafiel.operador.dao;

import java.util.List;

import com.bizitglobal.tarjetafiel.operador.negocio.Rol;

public interface RolDao {
	
	public void grabarRol(Rol rol);
	
	public Rol buscarRol(Long id);
	
	public void borrarRol(Long id);
	
	public void borrarRol(Rol rol);
	
	public List listarTodos();
	
	public void actualizarRol(Rol rol);
	
	public void grabarOActualizarRol(Rol rol);
	
}
