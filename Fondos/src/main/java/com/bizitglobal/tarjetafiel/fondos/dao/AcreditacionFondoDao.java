package com.bizitglobal.tarjetafiel.fondos.dao;

import java.util.List;

import com.bizitglobal.tarjetafiel.commons.filtros.Filtro;
import com.bizitglobal.tarjetafiel.fondos.negocio.AcreditacionFondo;

public interface AcreditacionFondoDao {

	public void grabarAcreditacionFondo (AcreditacionFondo pObject);
	public AcreditacionFondo buscarAcreditacionFondo (Long id);
	public void borrarAcreditacionFondo (Long id);
	public void borrarAcreditacionFondo (AcreditacionFondo pObject);
	public void actualizarAcreditacionFondo (AcreditacionFondo pObject);
	public List listarTodos(Filtro filtro);
	public List getUltimasAcreditacionesCargadas();
}
