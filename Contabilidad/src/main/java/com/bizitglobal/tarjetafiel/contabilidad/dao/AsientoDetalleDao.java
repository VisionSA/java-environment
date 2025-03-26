package com.bizitglobal.tarjetafiel.contabilidad.dao;

import java.util.List;

import com.bizitglobal.tarjetafiel.commons.filtros.Filtro;
import com.bizitglobal.tarjetafiel.contabilidad.negocio.AsientoDetalle;
import com.bizitglobal.tarjetafiel.contabilidad.negocio.LoteDetalle;


public interface AsientoDetalleDao {
	public void grabar(AsientoDetalle pObject);
	public void actualizar(AsientoDetalle pObject);
	public void grabarAsientoDetalle (AsientoDetalle pObject);
	public AsientoDetalle buscarAsientoDetalle (Long id);
	public void borrar(Long idEjercicio, Long idEmpresa, Long idAsiento, Long renglon);
	public void borrarAsientoDetalle (Long id);
	public void borrarAsientoDetalle(AsientoDetalle pObject);
	public void actualizarAsientoDetalle (AsientoDetalle pObject);
	public List listarTodos(Filtro filtro);
	public AsientoDetalle leerAsientoDetalle(Long id);
	public List listarTodosConsultaEspecial(Filtro filtro);
	public Long getLastIdDeRenglon(Long idEjercicio, Long idEmpresa, Long idAsiento);
	public void borrarLosDetalles(Long idEjercicio, Long idEmpresa, Long idAsiento);
	
}
