package com.bizitglobal.tarjetafiel.contabilidad.dao;

import java.util.List;

import com.bizitglobal.tarjetafiel.commons.filtros.Filtro;
import com.bizitglobal.tarjetafiel.contabilidad.negocio.Asiento;

public interface AsientoDao {
	public void grabarAsiento (Asiento pObject);
	public Asiento buscarAsiento (Long id);
	public void borrarAsiento (Long id);
	public void borrarAsiento(Asiento pObject);
	public void actualizarAsiento (Asiento pObject);
	public List listarTodos(Filtro filtro);
	public Asiento leerAsiento(Long id);
    public Long getLastIdDeAsientos(Long idEjercicio, Long idEmpresa);
    public List listarTodosConsultaEspecial(Filtro filtro);
    public Long contarAsientos(Filtro filtro);
}
