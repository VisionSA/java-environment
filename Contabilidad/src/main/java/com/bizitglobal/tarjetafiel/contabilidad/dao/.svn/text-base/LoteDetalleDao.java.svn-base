package com.bizitglobal.tarjetafiel.contabilidad.dao;

import java.util.List;

import com.bizitglobal.tarjetafiel.commons.filtros.Filtro;
import com.bizitglobal.tarjetafiel.contabilidad.negocio.LoteDetalle;


public interface LoteDetalleDao {
	public void grabar(LoteDetalle pObject);
//	public LoteDetalle buscarLoteDetalle (Long id);
	public void borrar(Long idEjercicio, Long idEmpresa, Long idAsiento, Long renglon);
//	public void borrarLoteDetalle(LoteDetalle pObject);
	public void actualizar(LoteDetalle pObject);
	public List listarTodos(Filtro filtro);
//	public LoteDetalle leerLoteDetalle(Long id);
	public List listarTodosConsultaEspecial(Filtro filtro);
	public Long getLastIdDeRenglon(Long idEjercicio, Long idEmpresa, Long idAsiento);
    public long getBalance(Long idEjercicio, Long idEmpresa, Long idAsiento, String comparacion);
    /*
     *devuelve la suma del debe menos el haber para poder conocer si el asiento esta balanceado.
     * */
    public long getBalance(Long idEjercicio, Long idEmpresa, Long idAsiento);
    public void borrarLosDetalles(Long idEjercicio, Long idEmpresa, Long idAsiento);

}
