package com.bizitglobal.tarjetafiel.fondos.service;

import java.util.Date;
import java.util.List;

import com.bizitglobal.tarjetafiel.fondos.exception.MovimientoConciliableException;


public interface MovimientoConciliableService {
	
	public List listarNoConciliados(Long idPlanCuenta, Date fechaDesde, Date fechaHasta)throws MovimientoConciliableException;
	public double saldoMovContabilidadNoConciliadosHastaFecha(Long idPlanCuenta, Date fechaHasta) throws MovimientoConciliableException;
}
