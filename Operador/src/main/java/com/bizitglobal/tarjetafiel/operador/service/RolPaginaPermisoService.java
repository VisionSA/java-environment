package com.bizitglobal.tarjetafiel.operador.service;

import java.util.List;

import com.bizitglobal.tarjetafiel.operador.exeption.RolPaginaPermisoException;
import com.bizitglobal.tarjetafiel.operador.negocio.RolPaginaPermiso;

/**
 *	Define los servicios que tendran disponibles los usuarios.
 */
public interface RolPaginaPermisoService {
	
	public List getPermisosPorPaginaYRol(String unaPagina, Long idRol) throws RolPaginaPermisoException;
	
	public List getPaginasPorRol(Long idRol) throws RolPaginaPermisoException;
	
	public void grabarRolPaginaPermiso(RolPaginaPermiso obj) throws RolPaginaPermisoException;
	
	public void borrarRolPaginaPermiso(Long id) throws RolPaginaPermisoException;

}
