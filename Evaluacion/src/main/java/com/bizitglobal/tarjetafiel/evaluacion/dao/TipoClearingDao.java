package com.bizitglobal.tarjetafiel.evaluacion.dao;
import java.util.List;
import com.bizitglobal.tarjetafiel.evaluacion.negocio.TipoClearing;
import com.bizitglobal.tarjetafiel.commons.filtros.Filtro;

public interface TipoClearingDao {
	public void grabarEvaTiposClearings (TipoClearing pObject);
	public TipoClearing buscarEvaTiposClearings (Long id);
	public void borrarEvaTiposClearings (Long id);
	public void borrarEvaTiposClearings (TipoClearing pObject);
	public void actualizarEvaTiposClearings (TipoClearing pObject);
	public List listarTodos(Filtro filtro);
}

