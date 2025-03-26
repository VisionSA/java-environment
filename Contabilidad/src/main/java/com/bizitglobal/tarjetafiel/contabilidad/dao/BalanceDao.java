package com.bizitglobal.tarjetafiel.contabilidad.dao;

import java.util.Date;
import java.util.List;

import com.bizitglobal.tarjetafiel.contabilidad.negocio.Ejercicio;


public interface BalanceDao {
	public List listarBalanceHojas(Ejercicio ejercicio, Date fechaDesde, Date fechaHasta);
	public List listarBalanceHojasSumasYSaldos(Ejercicio ejercicio, Date fechaDesde, Date fechaHasta);
}
