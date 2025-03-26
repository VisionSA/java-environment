package com.bizitglobal.tarjetafiel.fondos.service;

import java.util.Date;
import java.util.List;

import com.bizitglobal.tarjetafiel.commons.filtros.Filtro;
import com.bizitglobal.tarjetafiel.contabilidad.exception.AsientoException;
import com.bizitglobal.tarjetafiel.fondos.exception.AsientoFondosException;
import com.bizitglobal.tarjetafiel.fondos.negocio.AsientoFondos;
import com.bizitglobal.tarjetafiel.general.negocio.ConceptoGen;
import com.bizitglobal.tarjetafiel.general.negocio.FormaPago;
import com.bizitglobal.tarjetafiel.operador.negocio.Operador;

/*import com.bizitglobal.tarjetafiel.commons.filtros.Filtro;
import com.bizitglobal.tarjetafiel.general.exception.MonedaException;
import com.bizitglobal.tarjetafiel.general.negocio.Moneda;*/

/**
 * @author Daniel
 * Interface de servicios para las formas de pago del sistema. Define un conjunto de puntos de entrada
 * para utilizarse en la capa de presentación.
 */
public interface AsientoFondosService {
	
	/**
	 * Graba una Asiento en la base de datos.
	 * @param unaAsiento, Asiento a grabar.
	 */
	public void grabarAsiento(AsientoFondos unaAsiento)throws AsientoFondosException ;
	
	/**
	 * Obtiene una Asiento de la base de datos dado su id.
	 * @param id, Identificador de la Asiento buscada.
	 * @return La Asiento buscada.
	 */
	public AsientoFondos leerAsiento(Long id) throws AsientoFondosException;
	
	/**
	 * Borra un Asiento de la base de datos dado su id.
	 * @param id, Identificador de la Asiento.
	 */
	public void borrarAsiento(Long id) throws AsientoFondosException;
	
	/**
	 * Borra una Asiento de la base de datos dado el mismo.
	 * @param unaAsiento, Asiento a borrar.
	 */
	public void borrarAsiento(AsientoFondos unaAsiento) throws AsientoFondosException;
	
	/**
	 * Actualiza una Asiento en la base de datos.
	 * @param unaAsiento, Asiento a actualizar.
	 */
	public void actualizarAsiento(AsientoFondos unaAsiento) throws AsientoFondosException;
	
	/**
	 * Obtiene una lista de todas las Asientos.
	 * @return Una lista de Asientos.
	 */
	public List getAsientoes() throws AsientoFondosException;
	
	/**
	 * Obtiene una lista de todas los tipos de Asientos según el filtro.
	 * @return Una lista de Asientos.
	 */
	public List getAsientos(Filtro filtro) throws AsientoFondosException;
	
	/**
	 * @param fechaAsiento
	 * */
	public AsientoFondos construirAsientoPagoLiquidacionComercio(Date fechaAsiento, Operador operador, int codigoComercio, int nroLiquidacion, Double importe, FormaPago formaPago);

	public double saldoContableRealAFecha(Long idPlanCuenta, Date fechaHasta) throws AsientoException;
}
