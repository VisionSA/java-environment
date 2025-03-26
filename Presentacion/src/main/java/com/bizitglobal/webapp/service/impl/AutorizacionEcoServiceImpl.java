package com.bizitglobal.webapp.service.impl;

import java.io.File;
import java.io.IOException;
import java.net.Socket;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.apache.log4j.Logger;

import com.bizitglobal.tarjetafiel.commons.util.Convertidores;
import com.bizitglobal.tarjetafiel.commons.util.PropertieFile;
import com.bizitglobal.tarjetafiel.transacciones.service.NotaCreditoDebitoService;
import com.bizitglobal.webapp.service.AutorizacionEcoService;


/**
 * * @id 6247
 * 
 * @author Carlos Ibañez. Tarjeta Fiel - Año 2012 Esta clase es para enviar el paquete de autorización eco al transaccionador
 */
public class AutorizacionEcoServiceImpl implements AutorizacionEcoService {
	
	NotaCreditoDebitoService notaCreditoDebitoService;

	private Logger logger = Logger.getLogger(AutorizacionEcoServiceImpl.class);


	@Override
	public String enviarMensajeTransaccionadoEco(long transaccion, String numero_tarjeta, long importe) throws Exception {

		try {
			PropertieFile prop = new PropertieFile(System.getProperty("catalina.home") + File.separator + "webapps" + File.separator
					+ "contexto.properties");
			String ipTran = "";
			String port = "";

			try {
				ipTran = prop.getProperties("ipTransaccionador");
				port = prop.getProperties("puertoTransaccionador");

			} catch (IOException e1) {
				e1.printStackTrace();
			}
			String comercio = "0000000004689";
			// en el numero de lote se guardar el numero de transaccion que envie ECO
			String numeroLote = Convertidores.completarIzquierda(transaccion + "", new Character('0'), 11);
			String numeroCupon = "0000";
			String tipoRegistro = "861";
			String codigoVerificacion = "0000";
			Format dateFormat = new SimpleDateFormat("ddMMyy");
			Calendar fecha = Calendar.getInstance();
			String fecha_real = dateFormat.format(fecha.getTime());
			String cuotas = "01";
			String valor = Convertidores.completarIzquierda(importe + "", new Character('0'), 15);
			String importeSinDescuento = "000000000000000";
			String codigoAutorizacion = "00000000";
			String codigoMoneda = "032";
			String horaReal = "000000";
			String utilizaPin = "0";
			String tracker2 = "0000000000000000000000000000000000000000";
			String numeroCuponOriginal = "0000";
			String fechaOperacionOriginal = "0000";
			String indicadorCaptura = "0";
			String anulacion = "0";
			String codigoRespuestaOffline = "000";
			String tipoPlanCuotas = "0";
			String tipoCuota = "0";
			String formaDeIngresoTarjeta = "0";
			String tipoTerminal = "0";
			String privado = "0000";
			String datos1 = "00000000000600P00000000000000000";
			

			String token = "111" + "0200" + comercio + numeroLote + numeroCupon + tipoRegistro + numero_tarjeta + codigoVerificacion + fecha_real
					+ cuotas + valor + importeSinDescuento + codigoAutorizacion + codigoMoneda + horaReal + utilizaPin + tracker2 +
					numeroCuponOriginal + fechaOperacionOriginal + indicadorCaptura +
					anulacion + codigoRespuestaOffline + tipoPlanCuotas + tipoCuota + formaDeIngresoTarjeta + tipoTerminal + privado + datos1;
			String respuesta;
			Socket socket = new Socket(ipTran, Integer.parseInt(port));
			socket.getOutputStream().write(token.getBytes());
			socket.getOutputStream().flush();

			byte[] bytes = new byte[3];
			socket.getInputStream().read(bytes);
			bytes = new byte[4];
			socket.getInputStream().read(bytes);

			int longitud = Integer.valueOf(new String(bytes));

			bytes = new byte[longitud];

			socket.getInputStream().read(bytes);

			respuesta = new String(bytes);

			StringBuffer outputXML = new StringBuffer();
			// outputXML.append("<?xml version='1.0' standalone='yes'?>");
			outputXML.append("<Operacion>");

			outputXML.append("<Transaccion>");
			outputXML.append(transaccion);
			outputXML.append("</Transaccion>");

			outputXML.append("<Tarjeta>");
			outputXML.append(numero_tarjeta);
			outputXML.append("</Tarjeta>");

			outputXML.append("<Monto>");
			outputXML.append(importe);
			outputXML.append("</Monto>");

			outputXML.append("<Resultado>");
			if (respuesta.substring(0, 2).equals("00"))
			{
				outputXML.append("OK");
			} else {
				outputXML.append("ERROR");
			}
			outputXML.append("</Resultado>");

			outputXML.append("<Autorizacion>");
			outputXML.append(respuesta.substring(2, 8));
			outputXML.append("</Autorizacion>");

			outputXML.append("<Descripcion>");
			// outputXML.append(respuesta.substring(8, respuesta.length()));
			// outputXML.append(respuesta.substring(8, 8+10));
			if (respuesta.substring(0, 2).equals("00"))
			{
				outputXML.append("APROBADA");
			} else {
				outputXML.append("RECHAZADA");
			}
			outputXML.append("</Descripcion>");

			outputXML.append("</Operacion>");
			return (outputXML.toString());

		} catch (Exception e) {
			logger.error(e);
			throw new Exception("Error al conectar con el transaccionador");
		}
	}
	
	@Override
	public String comprarAquiCobro(long transaccion, String numero_tarjeta, long importe,String codigo_comercio, String nombre_comercio,
			String mes_anio,long cantidad_cuotas,String documento) throws Exception {

		try {
			PropertieFile prop = new PropertieFile(System.getProperty("catalina.home") + File.separator + "webapps" + File.separator
					+ "contexto.properties");
			String ipTran = "";
			String port = "";

			try {
				ipTran = prop.getProperties("ipTransaccionador");
				port = prop.getProperties("puertoTransaccionador");

			} catch (IOException e1) {
				e1.printStackTrace();
			}
			

			
			//String comercio = "0000000004689";
			
			String comercio = Convertidores.completarIzquierda(codigo_comercio.trim() , new Character('0'), 13);
			// en el numero de lote se guardar el numero de transaccion que envie ECO
			String numeroLote = Convertidores.completarIzquierda(transaccion + "", new Character('0'), 11);			
			String numeroCupon = Convertidores.completarIzquierda(mes_anio, new Character('0'), 4);
			String tipoRegistro = "861";
			String codigoVerificacion = "0000";
			Format dateFormat = new SimpleDateFormat("ddMMyy");
			Calendar fecha = Calendar.getInstance();
			String fecha_real = dateFormat.format(fecha.getTime());
			String cuotas =Convertidores.completarIzquierda(cantidad_cuotas + "", new Character('0'), 2); 
			String valor = Convertidores.completarIzquierda(importe + "", new Character('0'), 15);
			//Por ejemplo, "Chaitanya".substring(2,5)volvería "ait
			String importeSinDescuento;
			int length_comercio = nombre_comercio.length();
			if (length_comercio > 15) {
				 importeSinDescuento = Convertidores.completarDerecha(nombre_comercio.substring(0,15).trim() , new Character(' '), 15);
				
			} else {
				 importeSinDescuento = Convertidores.completarDerecha(nombre_comercio.trim() , new Character(' '), 15);
			}
			
			
			String codigoAutorizacion = Convertidores.completarIzquierda(documento.trim() , new Character('0'), 8);
			String codigoMoneda = "032";
			String horaReal = "000000";
			String utilizaPin = "0";
			String tracker2 = "0000000000000000000000000000000000000000";
			String numeroCuponOriginal = "0000";
			String fechaOperacionOriginal = "0000";
			String indicadorCaptura = "0";
			String anulacion = "0";
			String codigoRespuestaOffline = "000";
			String tipoPlanCuotas = "0";
			String tipoCuota = "0";
			String formaDeIngresoTarjeta = "0";
			String tipoTerminal = "0";
			String privado = "0000";
			//String datos1 = "00000000000600P00000000000000000";
			  String datos1 = "00000000000400C00000000000000000";
			
			String fechaConsumo = null;
			String buscaCodAutorizacion = null;
			
			String posnetAquiCobro =	notaCreditoDebitoService.obtenerComercioAquiCobro(comercio);
			
			if (posnetAquiCobro == null ) {
				
				StringBuffer outputXML = new StringBuffer();
				// outputXML.append("<?xml version='1.0' standalone='yes'?>");
				outputXML.append("<Operacion>");
				
				outputXML.append("<Comercio>");
				outputXML.append(codigo_comercio);
				outputXML.append("</Comercio>");

				outputXML.append("<Transaccion>");
				outputXML.append(transaccion);
				outputXML.append("</Transaccion>");
								
				outputXML.append("<Resultado>");				
				outputXML.append("ERROR");				
				outputXML.append("</Resultado>");
				
				outputXML.append("<Descripcion>");
				// outputXML.append(respuesta.substring(8, respuesta.length()));
				// outputXML.append(respuesta.substring(8, 8+10));
				outputXML.append("Comercio erroneo "+ codigo_comercio);
				
				outputXML.append("</Descripcion>");
				
				outputXML.append("<Estado>");				
				outputXML.append("RECHAZADA");				
				outputXML.append("</Estado>");

				outputXML.append("</Operacion>");
				return (outputXML.toString());
				
			} else {
				
			
			
			String compraExiste =	notaCreditoDebitoService.consultaTransaccionAquiCobro(comercio,numeroLote);
			
			if (compraExiste == null || compraExiste.equals("noexistecompra")) {
				
			String token = "111" + "0200" + comercio + numeroLote + numeroCupon + tipoRegistro + numero_tarjeta + codigoVerificacion + fecha_real
					+ cuotas + valor + importeSinDescuento + codigoAutorizacion + codigoMoneda + horaReal + utilizaPin + tracker2 +
					numeroCuponOriginal + fechaOperacionOriginal + indicadorCaptura +
					anulacion + codigoRespuestaOffline + tipoPlanCuotas + tipoCuota + formaDeIngresoTarjeta + tipoTerminal + privado + datos1;
			String respuesta;
			Socket socket = new Socket(ipTran, Integer.parseInt(port));
			socket.getOutputStream().write(token.getBytes());
			socket.getOutputStream().flush();

			byte[] bytes = new byte[3];
			socket.getInputStream().read(bytes);
			bytes = new byte[4];
			socket.getInputStream().read(bytes);

			int longitud = Integer.valueOf(new String(bytes));

			bytes = new byte[longitud];

			socket.getInputStream().read(bytes);

			respuesta = new String(bytes);
			
			
			
			if (respuesta.substring(0, 2).equals("00")) {
				fechaConsumo = 	notaCreditoDebitoService.fechaConsumoAquiCobroSysdate();
			}

			StringBuffer outputXML = new StringBuffer();
			// outputXML.append("<?xml version='1.0' standalone='yes'?>");
			outputXML.append("<Operacion>");
			
			outputXML.append("<Comercio>");
			outputXML.append(codigo_comercio);
			outputXML.append("</Comercio>");

			outputXML.append("<Transaccion>");
			outputXML.append(transaccion);
			outputXML.append("</Transaccion>");

			outputXML.append("<Tarjeta>");
			outputXML.append(numero_tarjeta);
			outputXML.append("</Tarjeta>");
			
			outputXML.append("<Cuotas>");
			outputXML.append(cantidad_cuotas);
			outputXML.append("</Cuotas>");

			outputXML.append("<Monto>");
			outputXML.append(importe);
			outputXML.append("</Monto>");
			
			if (respuesta.substring(0, 2).equals("00")) {
				outputXML.append("<Fecha>");
				outputXML.append(fechaConsumo);
				outputXML.append("</Fecha>");				
			}

			outputXML.append("<Resultado>");
			if (respuesta.substring(0, 2).equals("00"))
			{
				outputXML.append("OK");
			} else {
				outputXML.append("ERROR");
			}
			outputXML.append("</Resultado>");
			
			
			
			if (respuesta.substring(0, 2).equals("00"))
			{
				outputXML.append("<Autorizacion>");
				outputXML.append(respuesta.substring(2, 8));				
				outputXML.append("</Autorizacion>");			
			} 	

			outputXML.append("<Estado>");
			// outputXML.append(respuesta.substring(8, respuesta.length()));
			// outputXML.append(respuesta.substring(8, 8+10));
			if (respuesta.substring(0, 2).equals("00"))
			{
				outputXML.append("APROBADA");
			} else if (respuesta.substring(0, 2).equals("51")) 
			{
				outputXML.append("SALDO INSUFICIENTE, CONTACTAR TARJETA FIEL TEL:264-4293400");
			} else {
				System.out.println(" respuesta "+ respuesta );
				System.out.println(" respuesta.substring(0, 2) "+ respuesta.substring(0, 2));
				
				outputXML.append("DATOS INVALIDOS");
			}
			outputXML.append("</Estado>");

			outputXML.append("</Operacion>");
			return (outputXML.toString());
			
			} else {
				
				 buscaCodAutorizacion = 	notaCreditoDebitoService.autorizacionConsumoAquiCobro(comercio,numeroLote);
				 fechaConsumo = 	notaCreditoDebitoService.fechaConsumoAquiCobro(comercio,numeroLote,buscaCodAutorizacion);
				
				
				StringBuffer outputXML = new StringBuffer();
				// outputXML.append("<?xml version='1.0' standalone='yes'?>");
				outputXML.append("<Operacion>");
				
				outputXML.append("<Comercio>");
				outputXML.append(codigo_comercio);
				outputXML.append("</Comercio>");

				outputXML.append("<Transaccion>");
				outputXML.append(transaccion);
				outputXML.append("</Transaccion>");

				outputXML.append("<Tarjeta>");
				outputXML.append(numero_tarjeta);
				outputXML.append("</Tarjeta>");
				
				outputXML.append("<Cuotas>");
				outputXML.append(cantidad_cuotas);
				outputXML.append("</Cuotas>");

				outputXML.append("<Monto>");
				outputXML.append(importe);
				outputXML.append("</Monto>");				
								
				outputXML.append("<Autorizacion>");
				outputXML.append(buscaCodAutorizacion);				
				outputXML.append("</Autorizacion>");
				
				outputXML.append("<Resultado>");				
				outputXML.append("ERROR");				
				outputXML.append("</Resultado>");
				
				outputXML.append("<Descripcion>");
				// outputXML.append(respuesta.substring(8, respuesta.length()));
				// outputXML.append(respuesta.substring(8, 8+10));
				outputXML.append("Compra realizada en fecha "+ fechaConsumo);
				
				outputXML.append("</Descripcion>");
				
				outputXML.append("<Estado>");				
				outputXML.append("RECHAZADA");				
				outputXML.append("</Estado>");

				outputXML.append("</Operacion>");
				return (outputXML.toString());
				
			}
			}

		} catch (Exception e) {
			logger.error(e);
			throw new Exception("Error al conectar con el transaccionador");
		}
	}

	
	@Override
	public String devolucionAquiCobro(long transaccion, String numero_tarjeta,long cantidad_cuotas, long importe,String codigo_comercio,
			String nombre_comercio) throws Exception {

		try {
			//String comercio = "0000000004689";
			
			PropertieFile prop = new PropertieFile(System.getProperty("catalina.home") + File.separator + "webapps" + File.separator
					+ "contexto.properties");
			String ipTran = "";
			String port = "";

			try {
				ipTran = prop.getProperties("ipTransaccionador");
				port = prop.getProperties("puertoTransaccionador");

			} catch (IOException e1) {
				e1.printStackTrace();
			}
			
						
		//	00202150200200030122817591400000000050500000000460320000504906768055221400021100 02        00000000018709000          02708812281459140115049061576923811=2112                239999540008020000000000018728121459140000000045
		String 	version  = 	"002";
		String	longitudPaquete =	"0215";
		String  mensaje =	"0200";
		String	codigoProcesamiento =	"200030";
		String	fechaTransaccion =	"1228175914";
		//String	monto =	"000000000505";
		String monto = Convertidores.completarIzquierda(importe+"" , new Character('0'), 12);
		String numeroLote = Convertidores.completarIzquierda(transaccion + "", new Character('0'), 11);
		String	cupon = 	"0000000046";
		String	moneda =	"032";
		//String	tarjeta = 	"00005049067680552214";
		String tarjeta = Convertidores.completarIzquierda(numero_tarjeta.trim() , new Character('0'), 20);
		String	codSeguridad  =	"000";
		String	fechaVenc =	"2110";
		String	plan =	"0"; 
		String blanco1 = " ";
		//String	cuotas =	"01";
		String cuotas =Convertidores.completarIzquierda(cantidad_cuotas + "", new Character('0'), 2); 
		String	codRespuesta = "  ";	  
		String	codigoAutorizacion = "      ";	      
		String	RRN = 	"000000000187";
		//String	nroComercio =	"09000";  
		String nroComercio = Convertidores.completarIzquierda(codigo_comercio.trim() , new Character('0'), 13);
		String blanco2 = "  ";
		String	trace=	"027088";
		String	fechaLocal =	"1228145914";
		String	formaCaptura =	"011";
		//String	track2 =	"5049067680552214=2110"; 
		String comercioTrack2= null;
		int length_comercio = nombre_comercio.trim().length();
		if (length_comercio > 15) {
			comercioTrack2 = nombre_comercio.trim().substring(0,15).trim();
			
		} else {
			comercioTrack2 = nombre_comercio.trim();
					//Convertidores.completarDerecha(nombre_comercio.trim() , new Character(' '), 15);
		}
		
		 // String	track2 =	"Garbarino            ";
		  String	track2 =	Convertidores.completarDerecha(comercioTrack2.trim() , new Character(' '), 21);
		  
		String blanco3 = "                ";
		String	terminal =	"23999954";
		String	lote =	"0008";
		String	tipoMensOriginal =	"0200";
		//String	RRNOrig =	"000000000187";
		String	RRNOrig = Convertidores.completarIzquierda(transaccion + "", new Character('0'), 12);
		String	fechaOriginal  =	"2812145914";
		String	cuponOriginal = "0000000045";

		String token = version  + 	longitudPaquete +   mensaje + 	codigoProcesamiento + 	fechaTransaccion +	monto +	cupon + moneda +
			tarjeta +		codSeguridad  +		fechaVenc + plan + blanco1+ cuotas+ 	codRespuesta +	codigoAutorizacion +      
			RRN + nroComercio+ blanco2 +	trace + 	fechaLocal+		formaCaptura + track2+	blanco3 +terminal +	lote + tipoMensOriginal +	RRNOrig +
			fechaOriginal +			cuponOriginal;
		
		String fechaConsumo = null;
		String buscaCodAutorizacion = null;
		
		
		String posnetAquiCobro =	notaCreditoDebitoService.obtenerComercioAquiCobro(nroComercio);
		
		if (posnetAquiCobro == null ) {
			
			StringBuffer outputXML = new StringBuffer();
			// outputXML.append("<?xml version='1.0' standalone='yes'?>");
			outputXML.append("<Operacion>");
			
			outputXML.append("<Comercio>");
			outputXML.append(codigo_comercio);
			outputXML.append("</Comercio>");

			outputXML.append("<Transaccion>");
			outputXML.append(transaccion);
			outputXML.append("</Transaccion>");
							
			outputXML.append("<Resultado>");				
			outputXML.append("ERROR");				
			outputXML.append("</Resultado>");
			
			outputXML.append("<Descripcion>");
			// outputXML.append(respuesta.substring(8, respuesta.length()));
			// outputXML.append(respuesta.substring(8, 8+10));
			outputXML.append("Comercio erroneo "+ codigo_comercio);
			
			outputXML.append("</Descripcion>");
			
			outputXML.append("<Estado>");				
			outputXML.append("RECHAZADA");				
			outputXML.append("</Estado>");

			outputXML.append("</Operacion>");
			return (outputXML.toString());
			
		} else {
		
		
		String compraExiste =	notaCreditoDebitoService.consultaTransaccionAquiCobro(nroComercio,numeroLote);
		
		if (compraExiste == null || compraExiste.equals("noexistecompra")) {
		
		System.out.println(" token "+ token);
		
//		String token = "111" + "0200" + comercio + numeroLote + numeroCupon + tipoRegistro + numero_tarjeta + codigoVerificacion + fecha_real
//				+ cuotas + valor + importeSinDescuento + codigoAutorizacion + codigoMoneda + horaReal + utilizaPin + tracker2 +
//				numeroCuponOriginal + fechaOperacionOriginal + indicadorCaptura +
//				anulacion + codigoRespuestaOffline + tipoPlanCuotas + tipoCuota + formaDeIngresoTarjeta + tipoTerminal + privado + datos1;
		String respuesta;
		Socket socket = new Socket(ipTran, Integer.parseInt(port));
		socket.getOutputStream().write(token.getBytes());
		socket.getOutputStream().flush();

		byte[] bytes = new byte[3];
		socket.getInputStream().read(bytes);
		bytes = new byte[4];
		socket.getInputStream().read(bytes);

		int longitud = Integer.valueOf(new String(bytes));

		bytes = new byte[longitud];

		socket.getInputStream().read(bytes);

		respuesta = new String(bytes);
		
		
		StringBuffer outputXML = new StringBuffer();
		// outputXML.append("<?xml version='1.0' standalone='yes'?>");
		outputXML.append("<Operacion>");
		
		outputXML.append("<Comercio>");
		outputXML.append(codigo_comercio);
		outputXML.append("</Comercio>");
		
		outputXML.append("<Transaccion>");
		outputXML.append(transaccion);
		outputXML.append("</Transaccion>");

		outputXML.append("<Tarjeta>");
		outputXML.append(numero_tarjeta);
		outputXML.append("</Tarjeta>");
		
		outputXML.append("<Cuotas>");
		outputXML.append(cantidad_cuotas);
		outputXML.append("</Cuotas>");

		outputXML.append("<Monto>");
		outputXML.append(importe);
		outputXML.append("</Monto>");
		
		outputXML.append("<Resultado>");
		
		if (respuesta.substring(0, 1).equals("0"))
		{
			outputXML.append("OK");
		} else {
			outputXML.append("ERROR");
		}
		outputXML.append("</Resultado>");
		
		if (respuesta.substring(0, 1).equals("0"))
		{
			outputXML.append("<Autorizacion>");
			outputXML.append(new Long(respuesta.substring(10, 18))+"");				
			outputXML.append("</Autorizacion>");			
		}
		
		outputXML.append("<Estado>");
		
		if (respuesta.substring(0, 1).equals("0"))
		{
			outputXML.append("APROBADA");
		} else {
			outputXML.append("RECHAZADA");
		}
		outputXML.append("</Estado>");
			
		outputXML.append("</Operacion>");
		return (outputXML.toString());			
			
		} else {
			
			StringBuffer outputXML = new StringBuffer();
			// outputXML.append("<?xml version='1.0' standalone='yes'?>");
			outputXML.append("<Operacion>");
			
			outputXML.append("<Comercio>");
			outputXML.append(codigo_comercio);
			outputXML.append("</Comercio>");
			
			outputXML.append("<Transaccion>");
			outputXML.append(transaccion);
			outputXML.append("</Transaccion>");

			outputXML.append("<Tarjeta>");
			outputXML.append(numero_tarjeta);
			outputXML.append("</Tarjeta>");
			
			outputXML.append("<Cuotas>");
			outputXML.append(cantidad_cuotas);
			outputXML.append("</Cuotas>");

			outputXML.append("<Monto>");
			outputXML.append(importe);
			outputXML.append("</Monto>");
			
			outputXML.append("<Resultado>");
				outputXML.append("ERROR");			
			outputXML.append("</Resultado>");		
			
				outputXML.append("<Descripcion>");
				outputXML.append("Transaccion ya realizada Anteriormente");				
				outputXML.append("</Descripcion>");	
			
			outputXML.append("<Estado>");
				outputXML.append("RECHAZADA");			
			outputXML.append("</Estado>");
			
			outputXML.append("</Operacion>");
			return (outputXML.toString());
			
		}
		}
//			String comercio = Convertidores.completarIzquierda(codigo_comercio.trim() , new Character('0'), 13);
//			// en el numero de lote se guardar el numero de transaccion que envie ECO
//			String numeroLote = Convertidores.completarIzquierda(transaccion + "", new Character('0'), 11);
//			String codigoAutorizacion = codigo_autorizacion;
//			
//			Long estadoImpacto = notaCreditoDebitoService.verificaNotaCreditoConsumoAquiCobro(comercio,   numeroLote,   codigoAutorizacion);
//			String respuesta = null;
//			
//			if (estadoImpacto == null ) {				
//				respuesta = "Transaccion no existe";
//			} else 	if (estadoImpacto == 1) {
//				respuesta =	notaCreditoDebitoService.notaCreditoConsumoAquiCobro(  comercio,  numeroLote  ,  codigoAutorizacion );
//			} else if (estadoImpacto != 1 ) {
//				respuesta = "Transaccion revertiva anteriormente";
//			} else {
//				respuesta = "Estado Impacto tiene otro valor";
//			}
//			
//			
//			StringBuffer outputXML = new StringBuffer();			
//			outputXML.append("<Operacion>");
//			
//			outputXML.append("<Comercio>");
//			outputXML.append(codigo_comercio);
//			outputXML.append("</Comercio>");
//			
//			outputXML.append("<Transaccion>");
//			outputXML.append(transaccion);
//			outputXML.append("</Transaccion>");			
//			
//			if (respuesta.equals("Transaccion Revertida")  )
//			{
//				outputXML.append("<Resultado>");
//				outputXML.append("OK");
//				outputXML.append("</Resultado>");
//			} 
//			
//			if (respuesta.equals("Transaccion no existe") || respuesta.equals("Tranaccion Erronea") || respuesta.equals("Transaccion revertiva anteriormente")  )
//			{
//				outputXML.append("<Resultado>");
//				outputXML.append("ERROR");
//				outputXML.append("</Resultado>");
//			} 
//			
//			outputXML.append("<Descripcion>");
//			outputXML.append(respuesta);
//			outputXML.append("</Descripcion>");
//			
//			if (respuesta.equals("Transaccion Revertida") || respuesta.equals("Transaccion revertiva anteriormente"))
//			{
//				outputXML.append("<Fecha>");
//				String fechaDevolucion = notaCreditoDebitoService.fechaDevolucionAquiCobro(comercio,   numeroLote);
//				outputXML.append(fechaDevolucion);
//				outputXML.append("</Fecha>");
//			} 
//
//			outputXML.append("</Operacion>");
//			return (outputXML.toString());

		} catch (Exception e) {
			logger.error(e);
			throw new Exception("Error en devolucionAquiCobro");
		}
	}
	
	@Override
	public String consultaConsumoAquiCobro( String  codigo_comercio,  String  transaccion) throws Exception
	 {
		
	try {
		String comercio = Convertidores.completarIzquierda(codigo_comercio.trim() , new Character('0'), 13);
		// en el numero de lote se guardar el numero de transaccion que envie ECO
		String numeroLote = Convertidores.completarIzquierda(transaccion + "", new Character('0'), 11);
		
		String posnetAquiCobro =	notaCreditoDebitoService.obtenerComercioAquiCobro(comercio);
		
		if (posnetAquiCobro == null ) {
			
			StringBuffer outputXML = new StringBuffer();
			// outputXML.append("<?xml version='1.0' standalone='yes'?>");
			outputXML.append("<Operacion>");
			
			outputXML.append("<Comercio>");
			outputXML.append(codigo_comercio);
			outputXML.append("</Comercio>");

			outputXML.append("<Transaccion>");
			outputXML.append(transaccion);
			outputXML.append("</Transaccion>");
							
			outputXML.append("<Resultado>");				
			outputXML.append("ERROR");				
			outputXML.append("</Resultado>");
			
			outputXML.append("<Descripcion>");
			// outputXML.append(respuesta.substring(8, respuesta.length()));
			// outputXML.append(respuesta.substring(8, 8+10));
			outputXML.append("Comercio erroneo "+ codigo_comercio);
			
			outputXML.append("</Descripcion>");
			
			outputXML.append("<Estado>");				
			outputXML.append("RECHAZADA");				
			outputXML.append("</Estado>");

			outputXML.append("</Operacion>");
			return (outputXML.toString());
			
		} else {
		
		
		String respuesta =	notaCreditoDebitoService.consultaConsumoAquiCobro(comercio,numeroLote);
		String buscaCodAutorizacion;
		String fechaConsumo= "Sin Fecha";
		
		if (respuesta == null || respuesta.equals("noexistecompra")){
			respuesta = "Compra Inexistente";
		}
		
		StringBuffer outputXML = new StringBuffer();
		// outputXML.append("<?xml version='1.0' standalone='yes'?>");
		outputXML.append("<Operacion>");
		
		outputXML.append("<Comercio>");
		outputXML.append(codigo_comercio);
		outputXML.append("</Comercio>");
		
		outputXML.append("<Transaccion>");
		outputXML.append(transaccion);
		outputXML.append("</Transaccion>");
		
		if (respuesta == null || respuesta.equals("Compra Inexistente")){
			outputXML.append("<Resultado>");
			outputXML.append("ERROR");
			outputXML.append("</Resultado>");
			
		} else {
		 buscaCodAutorizacion = 	notaCreditoDebitoService.autorizacionConsumoAquiCobro(comercio,numeroLote);
		 
		 Long estadoImpacto = notaCreditoDebitoService.verificaNotaCreditoConsumoAquiCobro(comercio,   numeroLote,   buscaCodAutorizacion);
		 
		 if (estadoImpacto == null ) {
			 
			 respuesta = "Compra Conciliada";
		 } else {
			 //fechaConsumo = notaCreditoDebitoService.fechaDevolucionAquiCobro(comercio,   numeroLote);
			// fechaConsumo = 	notaCreditoDebitoService.fechaConsumoAquiCobro(comercio,numeroLote,buscaCodAutorizacion);
			 respuesta = "Compra Revertida";
		 }
		 fechaConsumo = 	notaCreditoDebitoService.fechaConsumoAquiCobro(comercio,numeroLote,buscaCodAutorizacion);	 
		 	outputXML.append("<Autorizacion>");
			outputXML.append(buscaCodAutorizacion);
			outputXML.append("</Autorizacion>");
			
			outputXML.append("<Fecha>");
			outputXML.append(fechaConsumo);
			outputXML.append("</Fecha>");
			
			outputXML.append("<Resultado>");
			outputXML.append("OK");
			outputXML.append("</Resultado>");
		}
		
		outputXML.append("<Descripcion>");
		//outputXML.append(respuesta.substring(2, 8));
		outputXML.append(respuesta);
		outputXML.append("</Descripcion>");
		


		outputXML.append("</Operacion>");
		return (outputXML.toString());
		
		}

	} catch (Exception e) {
		logger.error(e);
		throw new Exception("Error en consultaConsumoAquiCobro");
	}
		
		
	 }



	public Boolean registrarApp(long nomdoc, String nrotarjeta, String idregistracion, String password) throws Exception {
		return true;
	}


	public Boolean buscarRegistracionApp(String nrotarjeta) throws Exception {
		return true;
	}

	public NotaCreditoDebitoService getNotaCreditoDebitoService() {
		return notaCreditoDebitoService;
	}

	public void setNotaCreditoDebitoService(
			NotaCreditoDebitoService notaCreditoDebitoService) {
		this.notaCreditoDebitoService = notaCreditoDebitoService;
	}

}