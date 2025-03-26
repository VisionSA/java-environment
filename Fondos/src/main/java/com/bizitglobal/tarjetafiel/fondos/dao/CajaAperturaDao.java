package com.bizitglobal.tarjetafiel.fondos.dao;
import java.util.List;

import com.bizitglobal.tarjetafiel.commons.filtros.Filtro;
import com.bizitglobal.tarjetafiel.fondos.negocio.CajaApertura;

public interface CajaAperturaDao {
	public void grabarCajaApertura (CajaApertura pObject);
	public CajaApertura buscarCajaApertura (Long id);
	public void borrarCajaApertura (Long id);
	public void borrarCajaApertura (CajaApertura pObject);
	public void actualizarCajaApertura (CajaApertura pObject);
	public List listarTodos(Filtro filtro);
	public String ultimoCierreCajas(boolean esNvaApertura,String cajasAbiertas);
	public String ultimaAperturaCajas();
	public String getCajasSinAbrir();
	public String cajerosAsignadosUltimaAperturaCajas();
/*@I5953*/	public CajaApertura getAperturaVigente(Long idCaja);
/*@F5953*/}

