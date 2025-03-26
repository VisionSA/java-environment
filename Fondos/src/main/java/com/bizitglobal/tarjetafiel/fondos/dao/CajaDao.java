package com.bizitglobal.tarjetafiel.fondos.dao;
import java.util.List;

import com.bizitglobal.tarjetafiel.fondos.negocio.Caja;
import com.bizitglobal.tarjetafiel.general.negocio.Moneda;
import com.bizitglobal.tarjetafiel.commons.filtros.Filtro;

public interface CajaDao {
	public void grabarCaja (Caja pObject);
	public Caja buscarCaja (Long id);
	public void borrarCaja (Long id);
	public void borrarCaja (Caja pObject);
	public void actualizarCaja (Caja pObject);
	public List listarTodos(Filtro filtro);
}

