package com.bizitglobal.webapp.faces.util;

public interface Parseable {

	/**
	 * Retorna el tokens completo enviado desde el origen de datos.
	 * 
	 * @return el tokens completo.
	 */
	public String getTokensCompleto();


	/**
	 * Retorna el numero de comercio al cual corresponde la transaccion.
	 * 
	 * @return el numero de comercio.
	 */
	public String getNumeroComercio();


	/**
	 * Retorna el numero de la entidad de origen, la que genero la transaccion.
	 * 
	 * @return el origen de datos.
	 */
	public String getNumeroOrigen();


	/**
	 * Retorna el numero de lote que viene en el tokens.
	 * 
	 * @return el numero de lote.
	 */
	public String getNumeroLote();


	/**
	 * Retorna el numero del cupon de la operacion.
	 * 
	 * @return numero de cupon.
	 */
	public String getNumeroCupon();


	/**
	 * Retorn el numero de registro, osea el tipo de operacion.
	 * 
	 * @return el numero de registro
	 */
	public String getTipoRegistro();


	/**
	 * Retorna el numero de plastico o de tarjeta.
	 * 
	 * @return el numero de tarjeta.
	 */
	public String getNumeroTarjeta();


	/**
	 * Retorna el codigo de verificacion de la tarjeta.
	 * 
	 * @return el codigo de verificacion de la tarjeta.
	 */
	public String getCodigoVerificacion();


	/**
	 * Retorna la fecha real de la operacion.
	 * 
	 * @return la fecha real.
	 */
	public String getFechaReal();


	/**
	 * Retorna la cantidad de cuotas que corresponden a la transaccion.
	 * 
	 * @return la cantidad de cuotas.
	 */
	public String getCantidadCuotas();


	/**
	 * Retorna el importe de la operacion completado con ceros a la izquierda.
	 * 
	 * @return el importe.
	 */
	public String getImporte();


	/**
	 * Retorna el importe sin descuento completado con ceros a la izquierda.
	 * 
	 * @return el importe sin descuento.
	 */
	public String getImporteSinDescuento();


	/**
	 * Retorna el codigo de autorización.
	 * 
	 * @return el codigo autorización.
	 */
	public String getCodigoAutorizacion();


	/**
	 * Retorna el codigo de la moneda utilizada en la transaccion.
	 * 
	 * @return el codigo de moneda.
	 */
	public String getCodigoMoneda();


	/**
	 * Retorna la hora real de la operacion.
	 * 
	 * @return la hora real.
	 */
	public String getHoraReal();


	/**
	 * Retorna la utilizacion del pin.
	 * 
	 * @return la utilizacion del pin.
	 */
	public String getUtilizaPin();


	/**
	 * Retorna el pin asociado a la tarjeta, se toma los ultimos 4 digitos de el numero de tarjeta.
	 * 
	 * @return el pin.
	 */
	public String getPin();


	/**
	 * Retorna el numero del cupon original, solo se utiliza en caso de que sea una devolucion.
	 * 
	 * @return el numero de cupon original.
	 */
	public String getNumeroCuponOriginal();


	/**
	 * Retorna la fecha de la operacion original, solo se utiliza en caso de que sea una devolucion.
	 * 
	 * @return la fecha de la operacion original.
	 */
	public String getFechaOperacionOriginal();


	/**
	 * Retorna el indicador de forma de captura.
	 * 
	 * @return el indicador de captura.
	 */
	public String getIndicadorCaptura();


	/**
	 * Retorna el campo anulacion, "V" operacion anulada, "O" operacion normal.
	 * 
	 * @return el campo anulacion.
	 */
	public String getAnulacion();


	/**
	 * Retorna el tipo de plan cuota.
	 * 
	 * @return el tipo de plan.
	 */
	public String getTipoPlanCuotas();


	/**
	 * Retorna el tipo de cuenta asociado a la tarjeta de credito.
	 * 
	 * @return el tipo de cuenta.
	 */
	public String getTipoCuota();


	/**
	 * Retorna la forma de ingreso de la tarjeta.
	 * 
	 * @return la forma de ingreso.
	 */
	public String getFormaDeIngresoTarjeta();


	/**
	 * Retorna el tipo de terminal desde donde se origino la transaccion.
	 * 
	 * @return el tipo de terminal.
	 */
	public String getTipoTerminal();


	/**
	 * Sin descripcion!!!
	 * 
	 * @return campo private.
	 */
	public String getPrivate();


	/**
	 * Sin descripcion!!!
	 * 
	 * @return campo tracker2
	 */
	public String getTracker2();


	/**
	 * Retorna el codigo de respuesta offline.
	 * 
	 * @return el codigo de respuesta.
	 */
	public String getCodigoRespuestaOffline();


	/**
	 * Retorna el origen desde donde se obtiene los datos.
	 * 
	 * @return el origen de datos.
	 */
	public int getOrigen();

}
