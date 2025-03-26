package com.bizitglobal.tarjetafiel.fondos.service;

import java.util.Date;
import java.util.List;

import com.bizitglobal.tarjetafiel.commons.filtros.Filtro;
import com.bizitglobal.tarjetafiel.fondos.exception.ConciliacionFondoCabeceraException;
import com.bizitglobal.tarjetafiel.fondos.negocio.ConciliacionFondoCabecera;
import com.bizitglobal.tarjetafiel.operador.negocio.Operador;

public interface ConciliacionFondoCabeceraService {
	
	/**
	 * Graba una ConciliacionFondoCabecera en la base de datos.
	 * @param ConciliacionFondoCabecera a grabar.
	 */
	public void grabarConciliacionFondoCabecera(ConciliacionFondoCabecera unaConciliacionFondoCabecera)throws ConciliacionFondoCabeceraException ;
	
	/**
	 * Obtiene una ConciliacionFondoCabecera de la base de datos dado su id.
	 * @param id, Identificador de la ConciliacionFondoCabecera buscada.
	 * @return La ConciliacionFondoCabecera buscada.
	 */
	public ConciliacionFondoCabecera leerConciliacionFondoCabecera(Long id) throws ConciliacionFondoCabeceraException;
	
	/**
	 * Borra un ConciliacionFondoCabecera de la base de datos dado su id.
	 * @param id, Identificador de la ConciliacionFondoCabecera.
	 */
	public void borrarConciliacionFondoCabecera(Long id) throws ConciliacionFondoCabeceraException;
	
	/**
	 * Borra una ConciliacionFondoCabecera de la base de datos dado el mismo.
	 * @param ConciliacionFondoCabecera a borrar.
	 */
	public void borrarConciliacionFondoCabecera(ConciliacionFondoCabecera unaConciliacionFondoCabecera) throws ConciliacionFondoCabeceraException;
	
	/**
	 * Actualiza una ConciliacionFondoCabecera en la base de datos.
	 * @param ConciliacionFondoCabecera a actualizar.
	 */
	public void actualizarConciliacionFondoCabecera(ConciliacionFondoCabecera unaConciliacionFondoCabecera) throws ConciliacionFondoCabeceraException;
	
	/**
	 * Obtiene una lista de todas las ConciliacionFondoCabecera.
	 * @return Una lista de ConciliacionFondoCabecera.
	 */
	public List getConciliacionFondoCabeceras() throws ConciliacionFondoCabeceraException;
	
	/**
	 * Obtiene una lista de todas los tipos de ConciliacionFondoCabecera según el filtro.
	 * @return Una lista de ConciliacionFondoCabecera.
	 */
	public List getConciliacionFondoCabeceras(Filtro filtro) throws ConciliacionFondoCabeceraException;
	
	public List getConciliacionFondoCabecerasFlex(Filtro filtro) throws ConciliacionFondoCabeceraException;
	
	public void grabarConciliacionFondoCabeceraFlex(final ConciliacionFondoCabecera unaConciliacionFondoCabecera) throws ConciliacionFondoCabeceraException;
	
	public void confirmarConciliacionFondoCabecera(final List<ConciliacionFondoCabecera> list, final Date fechaConfirmacion, final Operador operadorConfirmo)throws ConciliacionFondoCabeceraException;
	
	public void deshacerConciliacionFondoCabecera(final List<ConciliacionFondoCabecera> list) throws ConciliacionFondoCabeceraException;
	
/*@I3918*/	public List<ConciliacionFondoCabecera> buscarConciliacionCabeceraPorFecha(final int tipoFecha, final Date fechaDesde, final Date fechaHasta, final Long idBancoPropio, final String conciliado,final int firstResult, final int maxResults, final String numero, final Double importe) throws ConciliacionFondoCabeceraException;
/*@F3918*/	
	public List<ConciliacionFondoCabecera> buscarConciliacionCabeceraPaginado(final Filtro filtro,final int firstResult, final int maxResults) throws ConciliacionFondoCabeceraException;
	
	public Long cantidadRegistros(final Filtro filtro) throws ConciliacionFondoCabeceraException;
	
	public void confirmarTodosConciliacionFondoCabecera(final Date fechaConfirmacion, final Operador operadorConfirmo, final Filtro filtro) throws ConciliacionFondoCabeceraException;
	
/*@I3918*/	public Long cantidadRegistrosPorFecha(final int tipoFecha, final Date fechaDesde, final Date fechaHasta, final Long idBancoPropio, final String conciliado, final String numero, final Double importe) throws ConciliacionFondoCabeceraException;
/*@F3918*/}
