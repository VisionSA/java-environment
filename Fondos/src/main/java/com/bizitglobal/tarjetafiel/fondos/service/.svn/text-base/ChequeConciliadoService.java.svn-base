package com.bizitglobal.tarjetafiel.fondos.service;

import java.util.List;

import com.bizitglobal.tarjetafiel.commons.filtros.Filtro;
import com.bizitglobal.tarjetafiel.fondos.exception.ChequeConciliadoException;
import com.bizitglobal.tarjetafiel.fondos.negocio.ChequeConciliado;

/*import com.bizitglobal.tarjetafiel.commons.filtros.Filtro;
import com.bizitglobal.tarjetafiel.general.exception.MonedaException;
import com.bizitglobal.tarjetafiel.general.negocio.Moneda;*/

/**
 * @author Daniel
 * Interface de servicios para las formas de pago del sistema. Define un conjunto de puntos de entrada
 * para utilizarse en la capa de presentación.
 */
public interface ChequeConciliadoService {
	
	/**
	 * Graba una ChequeConciliado en la base de datos.
	 * @param unaChequeConciliado, ChequeConciliado a grabar.
	 */
	public void grabarChequeConciliado(ChequeConciliado unaChequeConciliado)throws ChequeConciliadoException ;
	
	/**
	 * Obtiene una ChequeConciliado de la base de datos dado su id.
	 * @param id, Identificador de la ChequeConciliado buscada.
	 * @return La ChequeConciliado buscada.
	 */
	public ChequeConciliado leerChequeConciliado(Long id) throws ChequeConciliadoException;
	
	/**
	 * Borra un ChequeConciliado de la base de datos dado su id.
	 * @param id, Identificador de la ChequeConciliado.
	 */
	public void borrarChequeConciliado(Long id) throws ChequeConciliadoException;
	
	/**
	 * Borra una ChequeConciliado de la base de datos dado el mismo.
	 * @param unaChequeConciliado, ChequeConciliado a borrar.
	 */
	public void borrarChequeConciliado(ChequeConciliado unaChequeConciliado) throws ChequeConciliadoException;
	
	/**
	 * Actualiza una ChequeConciliado en la base de datos.
	 * @param unaChequeConciliado, ChequeConciliado a actualizar.
	 */
	public void actualizarChequeConciliado(ChequeConciliado unaChequeConciliado) throws ChequeConciliadoException;
	
	/**
	 * Obtiene una lista de todas las ChequeConciliados.
	 * @return Una lista de ChequeConciliados.
	 */
	public List getChequeConciliadoes() throws ChequeConciliadoException;
	
	/**
	 * Obtiene una lista de todas los tipos de ChequeConciliados según el filtro.
	 * @return Una lista de ChequeConciliados.
	 */
	public List getChequeConciliados(Filtro filtro) throws ChequeConciliadoException;
	
}
