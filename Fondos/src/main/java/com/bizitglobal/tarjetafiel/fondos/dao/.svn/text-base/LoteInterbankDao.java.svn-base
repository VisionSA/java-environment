package com.bizitglobal.tarjetafiel.fondos.dao;
import java.util.Date;
import java.util.List;

import com.bizitglobal.tarjetafiel.commons.filtros.Filtro;
import com.bizitglobal.tarjetafiel.fondos.negocio.LoteInterbank;

public interface LoteInterbankDao {
	public void grabarLoteInterbank (LoteInterbank pObject);
	public LoteInterbank buscarLoteInterbank (Long id);
	public void borrarLoteInterbank (Long id);
	public void borrarLoteInterbank (LoteInterbank pObject);
	public void actualizarLoteInterbank (LoteInterbank pObject);
	public List listarTodos(Filtro filtro);
	public List listarTodos(Date fechaGenerado,Date fechaSolicitud, Long idBanco);
	public List generarlistaInterbank(Long id_lote_interbank);
}

