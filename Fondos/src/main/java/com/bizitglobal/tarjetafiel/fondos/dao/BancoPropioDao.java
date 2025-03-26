package com.bizitglobal.tarjetafiel.fondos.dao;
import java.util.List;

import com.bizitglobal.tarjetafiel.commons.filtros.Filtro;
import com.bizitglobal.tarjetafiel.fondos.negocio.BancoPropio;

public interface BancoPropioDao {
	public void grabarBancoPropio (BancoPropio pObject);
	public BancoPropio buscarBancoPropio (Long id);
	public void borrarBancoPropio (Long id);
	public void borrarBancoPropio (BancoPropio pObject);
	public void actualizarBancoPropio (BancoPropio pObject);
	public List listarTodos(Filtro filtro);
}

