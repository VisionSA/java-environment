package com.bizitglobal.tarjetafiel.fondos.dao;
import java.util.Date;
import java.util.List;

import com.bizitglobal.tarjetafiel.commons.filtros.Filtro;
import com.bizitglobal.tarjetafiel.fondos.negocio.AsientoFondos;

public interface AsientoFondosDao {
	public void grabarAsiento (AsientoFondos pObject);
	public AsientoFondos buscarAsiento (Long id);
	public void borrarAsiento (Long id);
	public void borrarAsiento (AsientoFondos pObject);
	public void actualizarAsiento (AsientoFondos pObject);
	public List listarTodos(Filtro filtro);
	public double saldoContableRealAFecha(Long idPlanCuenta, Date fechaHasta);
}

