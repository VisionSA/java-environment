package com.bizitglobal.tarjetafiel.fondos.service;

import java.util.List;

import com.bizitglobal.tarjetafiel.commons.filtros.Filtro;
import com.bizitglobal.tarjetafiel.fondos.exception.ExtractoBancarioException;
import com.bizitglobal.tarjetafiel.fondos.negocio.ExtractoBancario;

/*import com.bizitglobal.tarjetafiel.commons.filtros.Filtro;
import com.bizitglobal.tarjetafiel.general.exception.MonedaException;
import com.bizitglobal.tarjetafiel.general.negocio.Moneda;*/

/**
 * @author Daniel
 * Interface de servicios para las formas de pago del sistema. Define un conjunto de puntos de entrada
 * para utilizarse en la capa de presentación.
 */
public interface ExtractoBancarioService {
	
	/**
	 * Graba una ExtractoBancario en la base de datos.
	 * @param unaExtractoBancario, ExtractoBancario a grabar.
	 */
	public void grabarExtractoBancario(ExtractoBancario unaExtractoBancario)throws ExtractoBancarioException ;
	
	/**
	 * Obtiene una ExtractoBancario de la base de datos dado su id.
	 * @param id, Identificador de la ExtractoBancario buscada.
	 * @return La ExtractoBancario buscada.
	 */
	public ExtractoBancario leerExtractoBancario(Long id) throws ExtractoBancarioException;
	
	/**
	 * Borra un ExtractoBancario de la base de datos dado su id.
	 * @param id, Identificador de la ExtractoBancario.
	 */
	public void borrarExtractoBancario(Long id) throws ExtractoBancarioException;
	
	/**
	 * Borra una ExtractoBancario de la base de datos dado el mismo.
	 * @param unaExtractoBancario, ExtractoBancario a borrar.
	 */
	public void borrarExtractoBancario(ExtractoBancario unaExtractoBancario) throws ExtractoBancarioException;
	
	/**
	 * Actualiza una ExtractoBancario en la base de datos.
	 * @param unaExtractoBancario, ExtractoBancario a actualizar.
	 */
	public void actualizarExtractoBancario(ExtractoBancario unaExtractoBancario) throws ExtractoBancarioException;
	
	/**
	 * Obtiene una lista de todas las ExtractoBancarios.
	 * @return Una lista de ExtractoBancarios.
	 */
	public List getExtractoBancarioes() throws ExtractoBancarioException;
	
	/**
	 * Obtiene una lista de todas los tipos de ExtractoBancarios según el filtro.
	 * @return Una lista de ExtractoBancarios.
	 */
	public List getExtractoBancarios(Filtro filtro) throws ExtractoBancarioException;
	
	
	/**
	 * Graba una ExtractoBancario en la base de datos.
	 * @param archivo en forma de array de bytes, ExtractoBancario a grabar.
	 */
	public String grabarExtractoBancarioDesdeArchivo(byte[] bytes)throws ExtractoBancarioException ;
	
}
