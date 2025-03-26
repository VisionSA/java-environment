package com.bizitglobal.tarjetafiel.fondos.dao;
import java.util.List;

import com.bizitglobal.tarjetafiel.commons.filtros.Filtro;
import com.bizitglobal.tarjetafiel.fondos.negocio.Cheque;
import com.bizitglobal.tarjetafiel.fondos.negocio.ChequeLugar;
import com.bizitglobal.tarjetafiel.fondos.negocio.Lugar;

public interface ChequeLugarDao {
	public void grabarChequeLugar (ChequeLugar pObject);
	public ChequeLugar buscarChequeLugar (Long id);
	public void borrarChequeLugar (Long id);
	public void borrarChequeLugar (ChequeLugar pObject);
	public void actualizarChequeLugar (ChequeLugar pObject);
	public List listarTodos(Filtro filtro);
	public List<Cheque> buscarChequesEnLugar(Lugar lugar);
}

