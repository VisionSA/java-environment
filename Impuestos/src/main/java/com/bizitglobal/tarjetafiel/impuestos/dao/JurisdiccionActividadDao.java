package com.bizitglobal.tarjetafiel.impuestos.dao;

import java.util.List;

import com.bizitglobal.tarjetafiel.commons.filtros.Filtro;
import com.bizitglobal.tarjetafiel.impuestos.negocio.JurisdiccionActividad;

public interface JurisdiccionActividadDao {
	
	public void grabarJurisdiccionActividad(JurisdiccionActividad unJurisdiccionActividad);
	
	public JurisdiccionActividad buscarJurisdiccionActividad(Long id);
	
	public void borrarJurisdiccionActividad(Long id);
	
	public void borrarJurisdiccionActividad(JurisdiccionActividad unJurisdiccionActividad);
	
	public List listarTodos(Filtro unFiltro);
	
	public void actualizarJurisdiccionActividad(JurisdiccionActividad unJurisdiccionActividad);
	
}
