package com.bizitglobal.tarjetafiel.fondos.dao;
import java.util.List;

import com.bizitglobal.tarjetafiel.commons.filtros.Filtro;
import com.bizitglobal.tarjetafiel.fondos.negocio.CajaArqueo;

public interface CajaArqueoDao {
	public void grabarCajaArqueo (CajaArqueo pObject);
	public CajaArqueo buscarCajaArqueo (Long id);
	public void borrarCajaArqueo (Long id);
	public void borrarCajaArqueo (CajaArqueo pObject);
	public void actualizarCajaArqueo (CajaArqueo pObject);
	public List listarTodos(Filtro filtro);
	public List buscarTotalesMovimientos(Long caja, Long idApertura);
}

