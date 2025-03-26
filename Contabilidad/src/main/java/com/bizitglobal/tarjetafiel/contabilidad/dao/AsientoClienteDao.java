package com.bizitglobal.tarjetafiel.contabilidad.dao;

import java.util.Date;
import java.util.List;

import com.bizitglobal.tarjetafiel.contabilidad.negocio.AsientoCliente;

public interface AsientoClienteDao {

	public void actualizarAsientoCliente (AsientoCliente pObject);
    public Long getLastIdDeAsientos(Long idEjercicio, Long idEmpresa);
    public List listarTodosImportables(Date inicioEjercicio, Date finEjercicio);
    public List listarDetallesDelImportable(Long id);
}
