package com.bizitglobal.tarjetafiel.commons.interfaces;

import com.bizitglobal.tarjetafiel.commons.filtros.Filtro;
import com.bizitglobal.tarjetafiel.commons.paginacion2.Page;

/**
 * @author hernan
 * Esta interfaz define un metodo generico para la ejecucion de las consultas paginadas.
 */
public interface Paginacion {
	
	/**
	 * La redefinicion deberia apuntar al metodo nativo que soporta esta implementacion.
	 * @param filtro
	 * @param pageNumber
	 * @param pageSize
	 * @return una pagina cargada con todos los regstros
	 * @throws Exception
	 */
	public Page getPage(final Filtro filtro, final int pageNumber, final int pageSize) throws Exception ;
	
}
