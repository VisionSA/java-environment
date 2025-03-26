package com.bizitglobal.tarjetafiel.fondos.dao;
import java.util.List;

import com.bizitglobal.tarjetafiel.commons.filtros.Filtro;
import com.bizitglobal.tarjetafiel.fondos.negocio.ExtractoBancario;
import com.bizitglobal.tarjetafiel.fondos.negocio.ExtractoBancarioTipoMovimiento;

public interface ExtractoBancarioTipoMovimientoDao {
	public void grabarExtractoBancarioTipoMovimiento (ExtractoBancarioTipoMovimiento pObject);
	public ExtractoBancarioTipoMovimiento buscarExtractoBancarioTipoMovimiento (Long id);
	public void borrarExtractoBancarioTipoMovimiento (Long id);
	public void borrarExtractoBancarioTipoMovimiento (ExtractoBancarioTipoMovimiento pObject);
	public void actualizarExtractoBancarioTipoMovimiento (ExtractoBancarioTipoMovimiento pObject);
	public List listarTodos(Filtro filtro);

}

