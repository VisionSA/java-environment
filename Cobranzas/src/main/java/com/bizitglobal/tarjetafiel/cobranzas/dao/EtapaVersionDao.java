package com.bizitglobal.tarjetafiel.cobranzas.dao;

import java.util.List;

import com.bizitglobal.tarjetafiel.cobranzas.negocio.EtapaVersion;
import com.bizitglobal.tarjetafiel.cobranzas.negocio.PlanVersion;
import com.bizitglobal.tarjetafiel.commons.filtros.Filtro;

public interface EtapaVersionDao {
	public void grabarEtapaVersion (EtapaVersion pObject);
	public EtapaVersion buscarEtapaVersion (Long id);
	public void borrarEtapaVersion (Long id);
	public void borrarEtapaVersion (EtapaVersion pObject);
	public void actualizarEtapaVersion(EtapaVersion pObject);
	public List listarTodos(Filtro filtro);
	public List getEtapasVersionByFiltro(PlanVersion planVersion);
}
