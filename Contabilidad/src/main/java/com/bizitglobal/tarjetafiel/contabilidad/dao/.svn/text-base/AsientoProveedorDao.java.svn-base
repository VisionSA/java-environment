package com.bizitglobal.tarjetafiel.contabilidad.dao;

import java.util.Date;
import java.util.List;

import com.bizitglobal.tarjetafiel.commons.filtros.Filtro;
import com.bizitglobal.tarjetafiel.contabilidad.negocio.Asiento;
import com.bizitglobal.tarjetafiel.contabilidad.negocio.AsientoProveedor;

public interface AsientoProveedorDao {

	public void actualizarAsientoProveedor (AsientoProveedor pObject);
    public Long getLastIdDeAsientos(Long idEjercicio, Long idEmpresa);
    public List listarTodosImportables(Date inicioEjercicio, Date finEjercicio);
    public List listarDetallesDelImportable(Long id);
}
