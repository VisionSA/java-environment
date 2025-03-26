package com.bizitglobal.tarjetafiel.contabilidad.service;

import java.util.Date;
import java.util.List;

import com.bizitglobal.tarjetafiel.commons.filtros.Filtro;
import com.bizitglobal.tarjetafiel.contabilidad.exception.AsientoDetalleException;
import com.bizitglobal.tarjetafiel.contabilidad.negocio.AsientoDetalle;

public interface RenglonLibroMayorService {
		
	
		public List getRenglonesLibroMayor(Long idEmpresa, Long idEjercicion, Date inicio, Date cierre, Long idCuenta, Date inicioEjercicio) throws AsientoDetalleException;
		public List getRenglonesLibroMayorFondos(final Date inicio, final Date cierre,final Long idCuenta,final Date inicioEjercicio)  throws AsientoDetalleException ;
}

