package com.bizitglobal.tarjetafiel.contabilidad.dao;

import java.util.List;

import com.bizitglobal.tarjetafiel.commons.filtros.Filtro;
import com.bizitglobal.tarjetafiel.contabilidad.negocio.Lote;

public interface LoteDao {
	public void grabarLote (Lote pObject);
	public Lote buscarLote (Long id);
	public void borrarLote (Long id);
	public void borrarLote(Lote pObject);
	public void actualizarLote (Lote pObject);
	public List listarTodos(Filtro filtro);
	public Lote leerLote(Long id);
	public void moverLote(Long idEjercicio, Long idEmpresa, Long idAsiento);
	public void moverDetallesDelLote(Long idEjercicio, Long idEmpresa, Long idAsiento);
	public Long getLastIdDeAsiento();
    public Long getLastIdDeLote();
    public List listarTodosConsultaEspecial(Filtro filtro);
    public void grabarOrigenContab(Long nroAsiento, Long idProveedor, Long idComprobante);
    public Long contarLotes(Filtro filtro);
}
