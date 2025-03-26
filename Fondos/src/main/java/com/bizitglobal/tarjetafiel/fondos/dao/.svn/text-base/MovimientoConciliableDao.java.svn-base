package com.bizitglobal.tarjetafiel.fondos.dao;
import java.util.Date;
import java.util.List;

import com.bizitglobal.tarjetafiel.fondos.negocio.MovimientoConciliable;

public interface MovimientoConciliableDao {
	public MovimientoConciliable buscarMovimientoConciliable(Long idChequeHistorial);
	public List listarNoConciliados(Long idPlanCuenta, Date fechaDesde, Date fechaHasta);
	public double saldoMovContabilidadNoConciliadosHastaFecha(Long idPlanCuenta, Date fechaHasta, Character conciliado);
}

