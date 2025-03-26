package com.bizitglobal.webapp.faces.beans.transacciones;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;

import javax.faces.event.ActionEvent;

import org.apache.log4j.Logger;

import com.bizitglobal.webapp.faces.beans.BaseBean;
import com.bizitglobal.webapp.faces.util.PosnetParser;
import com.bizitglobal.webapp.faces.util.SocketCliente;
import com.bizitglobal.tarjetafiel.commons.filtros.Filtro;
import com.bizitglobal.tarjetafiel.commons.util.PropertieFile;
import com.bizitglobal.tarjetafiel.transacciones.negocio.Token;


@SuppressWarnings({"rawtypes","unchecked"})
public class TransaccionadorBean extends BaseBean {
	private static final Logger log = Logger.getLogger(TransaccionadorBean.class);
	private Integer cantToken;
	private Long tienpo;
	private List tokenList;
	private StringBuffer resultados;
	private boolean boolResult;
	private String ipTransaccionador = "";
	private int contador = 0;

	// Propiedad para ejecutar el popup del reporte.
	private String popupReport = new String("");


	public TransaccionadorBean() {
		super();
	}


	public List getTokenList() {
		return tokenList;
	}


	public void setTokenList(List tokenList) {
		this.tokenList = tokenList;
	}


	public Integer getCantToken() {
		return cantToken;
	}


	public void setCantToken(Integer cantToken) {
		this.cantToken = cantToken;
	}


	public Long getTienpo() {
		return tienpo;
	}


	public void setTienpo(Long tienpo) {
		this.tienpo = tienpo;
	}


	public String getPopupReport() {
		return popupReport;
	}


	public void setPopupReport(String popupReport) {
		this.popupReport = popupReport;
	}


	public String getResultados() {
		return resultados.toString();
	}


	public void setResultados(String resultados) {
	}


	public boolean getBoolResult() {
		return boolResult;
	}


	public void borrar() {

		Calendar fecha = Calendar.getInstance();
		boolResult = false;
		resultados = new StringBuffer("");
		cantToken = new Integer(1);
		tienpo = new Long(0);
		tokenList = new ArrayList();
		popupReport = new String("");
	}


	public String inicializar() {
		borrar();
		popupReport = new String("");
		tituloLargo = "Transacciones";
		tituloCorto = "Transaccionador";

		Iterator iterator = transaccionesService.getTokenDao().listarTodos(new Filtro()).iterator();
		// Si se descomenta el if, se Agrega solo uno para las pruebas mias (waldemar)

		while (iterator.hasNext()) {
			Token t = (Token) iterator.next();
			// if (t.getIdToken().compareTo(new Long(6))==0) {
			tokenList.add(new TokenSelect(t));
			// }
		}
		String key;
		key = "catalina.home";
		key = System.getProperty(key);
		log.info(key + "/webapps/contexto.properties");
		PropertieFile prop = new PropertieFile(key + "/webapps/contexto.properties");
		try {
			ipTransaccionador = prop.getProperties("ipTransaccionador");
		} catch (Exception e) {
			e.printStackTrace();
		}

		return "transaccionador";
	}


	public boolean validar() {
		error.borrar();

		if (error.cantidad() != 0)
			return false;

		return true;
	}


	public void generar(ActionEvent event) {
		boolean flag = false;
		popupReport = "";
		String page = "http://localhost:8080/TransacWeb/transac/TransacRecive";
		String p1;
		String p2;
		Iterator iterator = tokenList.iterator();
		while (iterator.hasNext()) {
			TokenSelect tokenSelect = (TokenSelect) iterator.next();
			if (tokenSelect.seleccionado) {
				if (flag)
					popupReport = popupReport + ",";
				else
					popupReport = "new Array(";
				p1 = "?token=" + tokenSelect.getToken().getToken();
				p2 = "&origen=" + tokenSelect.getParse().getOrigen();
				popupReport = popupReport + "'" + page + p1 + p2 + "'";
				flag = true;
			}
		}
		if (flag)
			popupReport = popupReport + ")";
		log.info(popupReport);
	}


	public String generarTransac() {
		boolResult = false;
		long tiempoInicial = System.currentTimeMillis();

		resultados = new StringBuffer("Iniciando la prueba . . . \n");

		try {

			// proceso que simule la creacion del archivo de posnet.
			File archivo = new File("archivoPosnet" + contador++);
			String key;
			key = "catalina.home";
			key = System.getProperty(key);
			File file = new File(key + "/archivoPosnet" + contador + ".txt");
			FileOutputStream fos = new FileOutputStream(file);
			String cabecera = "FIEL                000     000                080804";
			byte cade[] = new byte[200];
			int j = 0;
			for (int i = 0; i < cabecera.length(); i++) {
				cade[i] = (byte) cabecera.charAt(i);
				j++;
			}
			fos.write(cade, 0, j);
			fos.write('\n');

			for (int i = 0; i < cantToken.intValue(); i++) {
				Iterator iterator = tokenList.iterator();
				while (iterator.hasNext()) {
					TokenSelect tokenSelect = (TokenSelect) iterator.next();
					if (tokenSelect.seleccionado) {
						try {
							// lo siguiente no va, es solo para armar el token con fecha real, y nro. de lote y cupon reales, que se propage a la
							// transaccion y a la simulacion de archivo posnet
							// esto mas adelante no es preocupacion nuestra, deberiamos tomar el token como viene e impactarlo, terminando ahi nuestra
							// responsabilidad.
							PosnetParser posnetParser = new PosnetParser(tokenSelect.getToken().getToken());

							// llamamos a tres metodos auxiliares que nos reemplazan los datos criticos...

							System.out.println("el ip del transaccionador es: " + ipTransaccionador);
							System.out.println("La longitud del token es " + posnetParser.getTokensCompleto().length());
							posnetParser.replaceFechaRealYOperacion();
							System.out.println("La longitud del token es " + posnetParser.getTokensCompleto().length());
							posnetParser.replaceNroLote();
							System.out.println("La longitud del token es " + posnetParser.getTokensCompleto().length());
							posnetParser.replaceNumeroCupon();
							System.out.println("La longitud del token es " + posnetParser.getTokensCompleto().length());
							System.out.println(posnetParser.getTokensCompleto());
							// Fin metodos de simulacion necesarios para generar el archivo de posnet.
							System.out.println("el ip del transaccionador es: " + ipTransaccionador);
							Thread cliente = new Thread(new SocketCliente(tokenSelect.getToken().getIdToken().intValue(), new Socket(
									ipTransaccionador, 5000), posnetParser.getTokensCompleto(), resultados));
							byte cadena[] = new byte[208];
							int z = 0;
							for (int h = 0; h < posnetParser.getTokensCompleto().length(); h++) {
								cadena[h] = (byte) posnetParser.getTokensCompleto().charAt(h);
								z++;
							}
							fos.write(cadena, 0, z);
							fos.write('\n');
							cliente.start();
							Thread.currentThread().sleep(tienpo.longValue());
							// System.out.println("Tiempo ente hilos -> " + (System.currentTimeMillis() - tiempoInicial));
						} catch (UnknownHostException e) {
							e.printStackTrace();
						} catch (IOException e) {
							e.printStackTrace();
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
				}
			}
			fos.flush();
			fos.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

		long total = System.currentTimeMillis() - tiempoInicial;
		// resultados.append("Fin de la prueba . . . \nTiempo total: " + total + " ms \n");
		return "";
	}


	public String verResult() {
		boolResult = !boolResult;
		return "";
	}

	public class TokenSelect {
		private Token token;
		private PosnetParser parse;
		private boolean seleccionado;


		public TokenSelect(Token token) {
			super();
			this.token = token;
			parse = new PosnetParser(token.getToken());
		}


		public Token getToken() {
			return token;
		}


		public void setToken(Token token) {
			this.token = token;
		}


		public PosnetParser getParse() {
			return parse;
		}


		public void setParse(PosnetParser parse) {
			this.parse = parse;
		}


		public boolean isSeleccionado() {
			return seleccionado;
		}


		public void setSeleccionado(boolean seleccionado) {
			this.seleccionado = seleccionado;
		}

	}
}
