package com.bizitglobal.webapp.faces.util;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import com.bizitglobal.tarjetafiel.commons.util.Archivo;
import com.bizitglobal.tarjetafiel.commons.util.ContextoProperties;
import com.bizitglobal.tarjetafiel.commons.util.Convertidores;
import com.bizitglobal.tarjetafiel.commons.util.Util;
import com.bizitglobal.tarjetafiel.transacciones.negocio.PlasticoCliente;
import com.bizitglobal.tarjetafiel.transacciones.negocio.PlasticoOperacion;
import com.bizitglobal.webapp.faces.beans.util.MergePDF;
import com.lowagie.text.pdf.PdfReader;


/**
 * @id 4655
 * @author José Casalis. Bizit Global - Año 2012
 */
public class PDFUtil {

	/**
	 * @id: 4655 Method: generarPDFAcusesPlasticos Description: Genera
	 * @param plasticos
	 * @param operacion
	 * @param idLote
	 * @return
	 * @throws Exception
	 */
	public static String generarPDFAcusesPlasticos(Set<PlasticoCliente> plasticos, Long idLote) throws Exception {
		Iterator<PlasticoCliente> plasticosIt = plasticos.iterator();
		StringBuffer directorioPdf = new StringBuffer();
		Long ultimoNroCuenta = 0L;
		Long ultimaOperacion = 0L;
		while (plasticosIt.hasNext()) {
			PlasticoCliente plastico = plasticosIt.next();
			if (plastico.esPlasticoTitular() && !plastico.getClienteTransaccion().getIdCliente().equals(ultimoNroCuenta)) {
				ultimoNroCuenta = plastico.getClienteTransaccion().getIdCliente();
				ultimaOperacion = plastico.getOperacion().getIdPlasticoOperacion();
				directorioPdf = guardarPDF(ultimoNroCuenta, idLote, plastico.getOperacion());

			} else if (!plastico.esPlasticoTitular() && !plastico.getClienteTransaccion().getIdTitular().equals(ultimoNroCuenta)) { // caso adicional
																																	// sin titular
																																	// para embozar

				ultimoNroCuenta = plastico.getClienteTransaccion().getIdTitular();
				ultimaOperacion = plastico.getOperacion().getIdPlasticoOperacion();
				directorioPdf = guardarPDF(ultimoNroCuenta, idLote, plastico.getOperacion());

				// caso adicional sin titular para embozar con diferente codigo de operacion
			} else if (!plastico.esPlasticoTitular() && plastico.getClienteTransaccion().getIdTitular().equals(ultimoNroCuenta)
					&& !plastico.getOperacion().getIdPlasticoOperacion().equals(ultimaOperacion)) {
				ultimaOperacion = plastico.getOperacion().getIdPlasticoOperacion();
				directorioPdf = guardarPDF(ultimoNroCuenta, idLote, plastico.getOperacion());
			}
		}
		String archivoAcuse = unirPDFAcuses(directorioPdf.toString(),
				"acuses" + Convertidores.completarIzquierda(idLote.toString(), new Character('0'), 6) + ".pdf");
		plasticosIt = null;
		System.gc();
		return archivoAcuse;
	}
	
	public static String generarPDFEtiquetasPlasticos(Long idLote) throws Exception {
		StringBuffer directorioPdf = new StringBuffer();

		directorioPdf.append(ContextoProperties.getProperty("directorioEmbozos") + "/"
				+ Convertidores.completarIzquierda(idLote.toString(), new Character('0'), 6));

		String absDir = ContextoProperties.catalinaHome + ContextoProperties.getProperty("directorioArchivos") + directorioPdf;
		
		Archivo.crearEstructuraDirectoriosCompleta(absDir);
		String nombreArchivo = "Etiquetas";
		String page = Session.getRequest().getContextPath() + "/report/EmbozadosEtiquetas.jrxml";
		String p0 = "?guardarEn=" + absDir + "/" + nombreArchivo;
		String p1 = "ƒid_Lote=" + idLote;

		GeneradorDeInforme generador = new GeneradorDeInforme();
		String parametro = page + p0 + p1 ;
		generador.guardarReporte(parametro);
		generador = null;
		System.gc();
		return directorioPdf.toString() + "/" + nombreArchivo + ".pdf";
	}


	private static StringBuffer guardarPDF(Long idTitular, Long idLote, PlasticoOperacion operacion) throws Exception {
		StringBuffer relDir = new StringBuffer();
		relDir.append(ContextoProperties.getProperty("directorioEmbozos") + "/"
				+ Convertidores.completarIzquierda(idLote.toString(), new Character('0'), 6) + "/temp");

		String absDir = ContextoProperties.catalinaHome + ContextoProperties.getProperty("directorioArchivos") + relDir;
		Archivo.crearEstructuraDirectoriosCompleta(absDir);
		/* @I5796 */String nombreArchivo = Convertidores.completarIzquierda(idTitular.toString(), new Character('0'), 6) + "_"
				+ operacion.getIdPlasticoOperacion();
		/* @F5796 */String page = Session.getRequest().getContextPath() + "/report/EmbozadoraPlasticosAdicional.jrxml";
		String p0 = "?guardarEn=" + absDir + "/" + nombreArchivo;
		// String p1 = "ƒid_plastico_cliente='"+idCliente+"'";
		String p1 = "ƒidCodOperacion=" + operacion.getIdPlasticoOperacion();
		String p2 = "ƒURLImagen=" + Session.getHomePath() + "/img/fiel/logo_fiel.jpg";
		String p3 = "ƒid_titular=" + idTitular;
		String p5 = "ƒid_Lote=" + idLote;
		// armamos el codigo de barra
		String codigoOperacion = Convertidores.completarIzquierda(operacion.getIdPlasticoOperacion().toString(), new Character('0'), 2);
		String idLoteString = Convertidores.completarIzquierda(idLote.toString(), new Character('0'), 6);
		String idCuentaString = Convertidores.completarIzquierda(idTitular.toString(), new Character('0'), 6);
		String verif = Util.generarDigitoVerificadorCB(codigoOperacion + idLoteString + idCuentaString);

		String p4 = "ƒcodigoBarra=" + "*" + codigoOperacion + idLoteString + idCuentaString + verif + "*";

		GeneradorDeInforme generador = new GeneradorDeInforme();
		String parametro = page + p0 + p1 + p2 + p3 + p4 + p5;
		generador.guardarReporte(parametro);
		// Limpiamos objetos para optimizar memoria
		parametro = nombreArchivo = page = p0 = p1 = p2 = p3 = null;
		generador = null;
		System.gc();
		return relDir;
	}


	/**
	 * @id: Method: unirPDFAcuses Description: Une todos los pdf de un directorio dado
	 * @param relDir
	 * @param lote
	 * @throws Exception
	 */
	public static String unirPDFAcuses(String relDir, String nombreArchivo) throws Exception {
		List<PdfReader> pdfs = new ArrayList<PdfReader>();
		String nombre;
		String key = "catalina.home";
		key = System.getProperty(key);
		String base = ContextoProperties.catalinaHome + ContextoProperties.getProperty("directorioArchivos");
		File fileDirectorio = null;
		fileDirectorio = new File(base + relDir);

		if (!fileDirectorio.exists()) {
			if (!fileDirectorio.mkdir())
				throw new Exception("Error,al intentar unir pdf");
		}
		File[] archivos = fileDirectorio.listFiles();
		Arrays.sort(archivos);
		for (File file : archivos) {
			nombre = file.getAbsolutePath();
			pdfs.add(new PdfReader(nombre));
		}

		String name = nombreArchivo;
		String path = fileDirectorio.getAbsolutePath().replace("temp", "");
		String outputFile = path + name;
		MergePDF.concatPDFs(pdfs, outputFile);
		String rutaArchivo = relDir.replace("temp", "") + name;
		rutaArchivo = rutaArchivo.replace("\\", "/").replace("//", "/");
		Archivo.borrarDirectorio(fileDirectorio.getAbsolutePath());

		pdfs = null;
		archivos = null;
		fileDirectorio = null;
		return rutaArchivo;
	}

}
