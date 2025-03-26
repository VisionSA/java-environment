package com.bizitglobal.webapp.service;

/**
 * @id 6247
 * @author Carlos Ibañez. Tarjeta Fiel - Año 2012 * Esta clase es para enviar el paquete de autorización eco al transaccionador
 */
public interface AutorizacionEcoService {

public String enviarMensajeTransaccionadoEco(long transaccion, String numero_tarjeta, long importe) throws Exception;
	
	
	public String comprarAquiCobro(long transaccion, String numero_tarjeta, long importe,String codigo_comercio, String nombre_comercio,
			String mes_anio,long cantidad_cuotas,String documento) throws Exception ;
	
	
	public String devolucionAquiCobro(long transaccion, String numero_tarjeta,long cantidad_cuotas, long importe,String codigo_comercio,
			String nombre_comercio) throws Exception;
	
	public String consultaConsumoAquiCobro( String  codigo_comercio,  String  transaccion) throws Exception;

}
