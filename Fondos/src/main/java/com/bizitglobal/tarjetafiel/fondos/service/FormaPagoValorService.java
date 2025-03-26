package com.bizitglobal.tarjetafiel.fondos.service;

import java.util.List;

import com.bizitglobal.tarjetafiel.commons.filtros.Filtro;
import com.bizitglobal.tarjetafiel.fondos.exception.FormaPagoValorException;
import com.bizitglobal.tarjetafiel.fondos.negocio.FormaPagoValor;

/*import com.bizitglobal.tarjetafiel.commons.filtros.Filtro;
import com.bizitglobal.tarjetafiel.general.exception.MonedaException;
import com.bizitglobal.tarjetafiel.general.negocio.Moneda;*/

/**
 * @author Daniel
 * Interface de servicios para las formas de pago del sistema. Define un conjunto de puntos de entrada
 * para utilizarse en la capa de presentación.
 */
public interface FormaPagoValorService {
	
	/**
	 * Graba una FormaPagoValor en la base de datos.
	 * @param unaFormaPagoValor, FormaPagoValor a grabar.
	 */
	public void grabarFormaPagoValor(FormaPagoValor unaFormaPagoValor)throws FormaPagoValorException ;
	
	/**
	 * Obtiene una FormaPagoValor de la base de datos dado su id.
	 * @param id, Identificador de la FormaPagoValor buscada.
	 * @return La FormaPagoValor buscada.
	 */
	public FormaPagoValor leerFormaPagoValor(Long id) throws FormaPagoValorException;
	
	/**
	 * Borra un FormaPagoValor de la base de datos dado su id.
	 * @param id, Identificador de la FormaPagoValor.
	 */
	public void borrarFormaPagoValor(Long id) throws FormaPagoValorException;
	
	/**
	 * Borra una FormaPagoValor de la base de datos dado el mismo.
	 * @param unaFormaPagoValor, FormaPagoValor a borrar.
	 */
	public void borrarFormaPagoValor(FormaPagoValor unaFormaPagoValor) throws FormaPagoValorException;
	
	/**
	 * Actualiza una FormaPagoValor en la base de datos.
	 * @param unaFormaPagoValor, FormaPagoValor a actualizar.
	 */
	public void actualizarFormaPagoValor(FormaPagoValor unaFormaPagoValor) throws FormaPagoValorException;
	
	/**
	 * Obtiene una lista de todas las FormaPagoValors.
	 * @return Una lista de FormaPagoValors.
	 */
	public List getFormaPagoValores() throws FormaPagoValorException;
	
	/**
	 * Obtiene una lista de todas los tipos de FormaPagoValors según el filtro.
	 * @return Una lista de FormaPagoValors.
	 */
	public List getFormaPagoValors(Filtro filtro) throws FormaPagoValorException;
	
}
