package com.bizitglobal.tarjetafiel.impuestos.dao;

import java.util.List;

import com.bizitglobal.tarjetafiel.commons.filtros.Filtro;
import com.bizitglobal.tarjetafiel.impuestos.negocio.Actividad;

/**@deprecated
 * @author hernan
 *
 */
public interface ActividadDao {
	
	public void grabarActividad(Actividad unaActividad);
	
	public Actividad buscarActividad(Long id);
	
	public void borrarActividad(Long id);
	
	public void borrarActividad(Actividad unaActividad);
	
	public List listarTodos(Filtro unFiltro);
	
	public void actualizarActividad(Actividad unaActividad);
	
}
