package com.bizitglobal.webapp.faces.util;

import java.math.BigDecimal;
import java.util.Calendar;


public class PosnetParser {
	private String tokens;


	public PosnetParser(String tokens) {
		// this.tokens = tokens.substring(7);
		// this.header = tokens.substring(0, 7);
		this.tokens = tokens;
	}


	/*
	 * (non-Javadoc)
	 * 
	 * @see com.visionis.transaccionador.util.Parseable#getNumeroComercio()
	 */
	public Integer getNumeroComercio() {
		// return new Integer(1);
		return new Integer(tokens.substring(0, 13));
	}


	/*
	 * (non-Javadoc)
	 * 
	 * @see com.visionis.transaccionador.util.Parseable#getNumeroLote()
	 */
	public String getNumeroLote() {
		return tokens.substring(13, (13 + 11));
	}


	/*
	 * (non-Javadoc)
	 * 
	 * @see com.visionis.transaccionador.util.Parseable#getNumeroCupon()
	 */
	public String getNumeroCupon() {
		return tokens.substring(24, (24 + 4));
	}


	/*
	 * (non-Javadoc)
	 * 
	 * @see com.visionis.transaccionador.util.Parseable#getTipoRegistro()
	 */
	public String getTipoRegistro() {
		return tokens.substring(28, (28 + 3));
	}


	/*
	 * (non-Javadoc)
	 * 
	 * @see com.visionis.transaccionador.util.Parseable#getNumeroTarjeta()
	 */
	public String getNumeroTarjeta() {
		return tokens.substring(31, (31 + 20));
	}


	public String getCodigoVerificacion() {
		return tokens.substring(47, (47 + 4));
	}


	/*
	 * (non-Javadoc)
	 * 
	 * @see com.visionis.transaccionador.util.Parseable#getFechaReal()
	 */
	public String getFechaReal() {
		return tokens.substring(51, (51 + 6));
	}


	/*
	 * (non-Javadoc)
	 * 
	 * @see com.visionis.transaccionador.util.Parseable#getCantidadCuotas()
	 */
	public String getCantidadCuotas() {
		return tokens.substring(57, (57 + 2));
	}


	/*
	 * (non-Javadoc)
	 * 
	 * @see com.visionis.transaccionador.util.Parseable#getImporte()
	 */
	public BigDecimal getImporte() {
		// return new BigDecimal(1);
		return new BigDecimal(tokens.substring(59, (59 + 15)));
	}


	/*
	 * (non-Javadoc)
	 * 
	 * @see com.visionis.transaccionador.util.Parseable#getImporteSinDescuento()
	 */
	public BigDecimal getImporteSinDescuento() {
		return new BigDecimal(1);
		// return new BigDecimal(tokens.substring(74, (74+15)));
	}


	/*
	 * (non-Javadoc)
	 * 
	 * @see com.visionis.transaccionador.util.Parseable#getCodigoAutorizacion()
	 */
	public String getCodigoAutorizacion() {
		return tokens.substring(89, (89 + 8));
	}


	/*
	 * (non-Javadoc)
	 * 
	 * @see com.visionis.transaccionador.util.Parseable#getCodigoMoneda()
	 */
	public String getCodigoMoneda() {
		return tokens.substring(97, (97 + 3));
	}


	/*
	 * (non-Javadoc)
	 * 
	 * @see com.visionis.transaccionador.util.Parseable#getHoraReal()
	 */
	public String getHoraReal() {
		return tokens.substring(100, (100 + 6));
	}


	/*
	 * (non-Javadoc)
	 * 
	 * @see com.visionis.transaccionador.util.Parseable#getUtilizaPin()
	 */
	public String getUtilizaPin() {
		return tokens.substring(106, (106 + 1));
	}


	/*
	 * (non-Javadoc)
	 * 
	 * @see com.visionis.transaccionador.util.Parseable#getTracker2()
	 */
	public String getTracker2() {
		return tokens.substring(107, (107 + 40));
	}


	/*
	 * (non-Javadoc)
	 * 
	 * @see com.visionis.transaccionador.util.Parseable#getNumeroCuponOriginal()
	 */
	public String getNumeroCuponOriginal() {
		return tokens.substring(147, (147 + 4));
	}


	/*
	 * (non-Javadoc)
	 * 
	 * @see com.visionis.transaccionador.util.Parseable#getFechaOperacionOriginal()
	 */
	public String getFechaOperacionOriginal() {
		return tokens.substring(151, (151 + 4));
	}


	/*
	 * (non-Javadoc)
	 * 
	 * @see com.visionis.transaccionador.util.Parseable#getIndicadorCaptura()
	 */
	public String getIndicadorCaptura() {
		return tokens.substring(155, (155 + 1));
	}


	/*
	 * (non-Javadoc)
	 * 
	 * @see com.visionis.transaccionador.util.Parseable#getAnulacion()
	 */
	public String getAnulacion() {
		return tokens.substring(156, (156 + 1));
	}


	/*
	 * (non-Javadoc)
	 * 
	 * @see com.visionis.transaccionador.util.Parseable#codigoRespuestaOffline()
	 */
	public String getCodigoRespuestaOffline() {
		return tokens.substring(157, (157 + 3));
	}


	/*
	 * (non-Javadoc)
	 * 
	 * @see com.visionis.transaccionador.util.Parseable#getTipoPlanCuotas()
	 */
	public String getTipoPlanCuotas() {
		return tokens.substring(160, (160 + 1));
	}


	/*
	 * (non-Javadoc)
	 * 
	 * @see com.visionis.transaccionador.util.Parseable#getTipoCuota()
	 */
	public String getTipoCuota() {
		return tokens.substring(161, (161 + 1));
	}


	/*
	 * (non-Javadoc)
	 * 
	 * @see com.visionis.transaccionador.util.Parseable#getFormaDeIngresoTarjeta()
	 */
	public String getFormaDeIngresoTarjeta() {
		return tokens.substring(162, (162 + 1));
	}


	/*
	 * (non-Javadoc)
	 * 
	 * @see com.visionis.transaccionador.util.Parseable#getTipoTerminal()
	 */
	public String getTipoTerminal() {
		return tokens.substring(163, (163 + 1));
	}


	/*
	 * (non-Javadoc)
	 * 
	 * @see com.visionis.transaccionador.util.Parseable#getPrivate()
	 */
	public String getPrivate() {
		return tokens.substring(164, (164 + 4));
	}


	/*
	 * (non-Javadoc)
	 * 
	 * @see com.visionis.transaccionador.util.Parseable#getNumeroOrigen()
	 */
	public String getNumeroOrigen() {
		// TODO Auto-generated method stub
		return null;
	}


	/*
	 * (non-Javadoc)
	 * 
	 * @see com.visionis.transaccionador.util.Parseable#getTokensCompleto()
	 */
	public String getTokensCompleto() {
		// return "0010208" + tokens;
		return "1110208" + tokens;
		// return "1120168" + tokens;
	}


	/*
	 * (non-Javadoc)
	 * 
	 * @see com.visionis.transaccionador.util.Parseable#getTokensCompleto()
	 */
	public String getTokensCompletoFiel() {
		return "1110200" + tokens;
	}


	/*
	 * (non-Javadoc)
	 * 
	 * @see com.visionis.transaccionador.util.Parseable#getPin()
	 */
	public String getPin() {
		// return getNumeroTarjeta().substring(16, 20);
		return getCodigoAutorizacion();
	}


	/*
	 * (non-Javadoc)
	 * 
	 * @see com.visionis.transaccionador.util.Parseable#getOrigen()
	 */
	public int getOrigen() {
		return 1;
	}


	// al implementar, borrar esto
	public void replaceNroLote() {
		// Momentaneamente, estamos simulando que cada dia del año es un lote. Los lotes van del 1 al 365
		String result = null;
		int dia = Calendar.getInstance().get(Calendar.DAY_OF_YEAR);
		Convertidores conv = new Convertidores();
		try {
			result = conv.completarALaIzquierda(dia, 11, "000");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// Cuando tomemos el codigo de posnet verdadero, el codigo debe ser el siguiente:
		tokens.replace(tokens.substring(13, (13 + 11)), result);
	}


	// al implementar, borrar esto.
	public void replaceNumeroCupon() {
		// Momentaneamente, estamos simulando que cada dia del año es un lote. Los lotes van del 1 al 365
		String cade = "0123456789";
		String resultado = "";
		for (int i = 0; i < 4; i++) {
			resultado += String.valueOf(cade.charAt(Double.valueOf((Math.random() * 10)).intValue()));

		}

		// //Cuando tomemos el codigo de posnet verdadero, el codigo debe ser el siguiente:
		// return tokens.substring(24, (24+4));
		tokens = tokens.substring(0, 24) + resultado + tokens.substring(28, tokens.length());

	}


	// al implementar, borrar esto.
	public void replaceFechaRealYOperacion() {
		// Este es el codigo correcto
		// return tokens.substring(51,(51+6));
		// Este otro es para poder simular enlas pruebas el dia de la fecha. No olvidar de corregir!!!!
		String aux, mes = "", dia = "";
		Calendar calendar = Calendar.getInstance();

		if (calendar.get(Calendar.MONTH) + 1 < 10) {
			mes = "0" + String.valueOf(calendar.get(Calendar.MONTH) + 1);
		} else {
			mes = String.valueOf(calendar.get(Calendar.MONTH) + 1);
		}
		if (calendar.get(Calendar.DAY_OF_MONTH) < 10) {
			dia = "0" + String.valueOf(calendar.get(Calendar.DAY_OF_MONTH));
		} else {
			dia = String.valueOf(calendar.get(Calendar.DAY_OF_MONTH));
		}
		aux = String.valueOf(calendar.get(Calendar.YEAR)).substring(2) + mes + dia;
		// return aux;
		tokens = tokens.substring(0, 51) + aux + tokens.substring(57, 151) + mes + dia + tokens.substring(155, tokens.length());

	}

}
