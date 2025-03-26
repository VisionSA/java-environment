package com.bizitglobal.webapp.service.impl;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLDecoder;
import java.util.List;
import java.util.ArrayList;

import org.apache.commons.io.FileUtils;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.util.ImageIOUtil;

import com.bizitglobal.tarjetafiel.commons.filtros.Filtro;
import com.bizitglobal.tarjetafiel.commons.util.AlgoritmoEncriptacionAES;
import com.bizitglobal.tarjetafiel.commons.util.ContextoProperties;
import com.bizitglobal.tarjetafiel.planificadoremail.negocio.Content;
import com.bizitglobal.tarjetafiel.transacciones.negocio.LiqCliente;
import com.bizitglobal.tarjetafiel.transacciones.service.LiqClienteService;
import com.bizitglobal.webapp.faces.util.Validador;
import com.bizitglobal.webapp.service.ArchivoUnionPDFService;
import com.bizitglobal.tarjetafiel.transacciones.negocio.ClienteApp;
import com.bizitglobal.tarjetafiel.transacciones.negocio.DetalleResumen;
import com.bizitglobal.tarjetafiel.transacciones.negocio.MovimientosPeriodoActual;
import com.bizitglobal.tarjetafiel.transacciones.negocio.Notificacion;
import com.bizitglobal.tarjetafiel.transacciones.negocio.Promocion;
import com.bizitglobal.tarjetafiel.transacciones.negocio.UltimoResumenCuenta;
import com.bizitglobal.tarjetafiel.transacciones.negocio.ResumenPDFImage;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfImportedPage;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfWriter;
import com.bizitglobal.tarjetafiel.transacciones.negocio.DisponibleCliente;


public class ArchivoUnionPDFServiceImpl implements ArchivoUnionPDFService {

	LiqClienteService liqClienteServ;

	private final String RELATIVE_WEB_PATH = "/img/fiel/PapelMembretado.jpg";
	private final String TAB_PARAMETERS = "&&";
	private final String TAB_PARAMETER_VALUE = "==";
	private final String NAME_PARAMETER_ID_CLIENT = "idCliente";
	private final String NAME_PARAMETER_ID_LIQ_CLIENT = "idLiqCliente";
	private final String DIR_ARCH = "directorioArchivos";
	private final String NAME_FILTER_ID_CLIENT = "clienteTransaccion";
	private final String NAME_FILTER_ID_LIQ_CLIENT = "idLiqCliente";
	private final String EXT_ARCH = ".pdf";
	private final String DIR_EXCLUDE_PATH_ARCH = "/archivos";
	private final String BACKLASH = "\\";
	private final String SLASH = "/";
	private final String CHARACTERS = "UTF-8";


	@SuppressWarnings("unchecked")
	public List<ResumenPDFImage> getResumenPDFImage(long idLiqCliente, long idCliente, String idregistro)
			throws Exception {
		try {

			liqClienteServ.logApp(idregistro, 6);
			String absoluteImageDiskPath = getAppPath() + RELATIVE_WEB_PATH;
			String pathInicial = ContextoProperties.catalinaHome
					+ ContextoProperties.getProperty(DIR_ARCH);
			// busco la liquidacion del cliente
			LiqCliente liqCliente = null;
			if (idLiqCliente <= 0) {
				if (idCliente > 0) {
					liqCliente = liqClienteServ.getUltimaLiquidacion(idCliente);
				}
			} else {
				if (idCliente > 0) {
					Filtro filtro = new Filtro();
					filtro.agregarCampoOperValor(NAME_FILTER_ID_CLIENT,
							Filtro.IGUAL, idCliente);
					filtro.agregarCampoOperValor(NAME_FILTER_ID_LIQ_CLIENT,
							Filtro.IGUAL, idLiqCliente);
					List<LiqCliente> listLiqCliente = (List<LiqCliente>) liqClienteServ
							.getLiqCliente(filtro);
					if (listLiqCliente != null && !listLiqCliente.isEmpty()) {
						liqCliente = listLiqCliente.get(0);
					}
				}
			}
			if (liqCliente != null) {
				String pathArchPDF = liqCliente.getRutaPdf();
				if (pathArchPDF.length() > 9
						&& pathArchPDF.substring(0, 9).equalsIgnoreCase(
								DIR_EXCLUDE_PATH_ARCH)) {
					pathArchPDF = pathArchPDF.substring(9);
				}
				String absolutePathUnionPDF = pathInicial + pathArchPDF
						+ EXT_ARCH;
				System.out.println(absolutePathUnionPDF);
				System.out.println(absolutePathUnionPDF
						.replace(BACKLASH, SLASH));
				System.out.println(absoluteImageDiskPath);
				System.out.println(absoluteImageDiskPath.replace(BACKLASH,
						SLASH));
				return unirPDFResumen(absolutePathUnionPDF.replace(BACKLASH, SLASH),
						absoluteImageDiskPath);
			} else {
				// eror no se encontro la liquidacion del cliente
				throw new Exception(
						"Error no se encontro la liquidacion del cliente");
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return null;
	}


	private List<ResumenPDFImage> unirPDFResumen(String absolutePathUnionPDF,
			String absoluteImageDiskPath) throws IOException, DocumentException {

		try {

			List<ResumenPDFImage> resumenPDFImage = new ArrayList<ResumenPDFImage>();

			PDDocument document = PDDocument.loadNonSeq(new File(absolutePathUnionPDF), null);

			List<PDPage> pdPages = document.getDocumentCatalog().getAllPages();
			int page = 0;
			for (PDPage pdPage : pdPages)
			{

				ByteArrayOutputStream out1 = new ByteArrayOutputStream();
				++page;
				BufferedImage bim = pdPage.convertToImage(BufferedImage.TYPE_BYTE_GRAY, 96);
				ImageIOUtil.writeImage(bim, "png", out1, 100);
				ResumenPDFImage promocion = new ResumenPDFImage();
				promocion.setNumeropagina(page);
				promocion.setResumencliente(out1.toByteArray());
				resumenPDFImage.add(promocion);

			}
			document.close();

			File file = new File(absolutePathUnionPDF);
			byte[] array = FileUtils.readFileToByteArray(file);
			ResumenPDFImage promocion = new ResumenPDFImage();
			promocion.setResumencliente(array);
			resumenPDFImage.add(promocion);

			return resumenPDFImage;

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

		return null;
	}


	@SuppressWarnings("unchecked")
	public byte[] getArchivoUnionPDF(long idLiqCliente, long idCliente)
			throws Exception {
		try {
			String absoluteImageDiskPath = getAppPath() + RELATIVE_WEB_PATH;
			String pathInicial = ContextoProperties.catalinaHome
					+ ContextoProperties.getProperty(DIR_ARCH);
			// busco la liquidacion del cliente
			LiqCliente liqCliente = null;
			if (idLiqCliente <= 0) {
				if (idCliente > 0) {
					liqCliente = liqClienteServ.getUltimaLiquidacion(idCliente);
				}
			} else {
				if (idCliente > 0) {
					Filtro filtro = new Filtro();
					filtro.agregarCampoOperValor(NAME_FILTER_ID_CLIENT,
							Filtro.IGUAL, idCliente);
					filtro.agregarCampoOperValor(NAME_FILTER_ID_LIQ_CLIENT,
							Filtro.IGUAL, idLiqCliente);
					List<LiqCliente> listLiqCliente = (List<LiqCliente>) liqClienteServ
							.getLiqCliente(filtro);
					if (listLiqCliente != null && !listLiqCliente.isEmpty()) {
						liqCliente = listLiqCliente.get(0);
					}
				}
			}
			if (liqCliente != null) {
				String pathArchPDF = liqCliente.getRutaPdf();
				if (pathArchPDF.length() > 9
						&& pathArchPDF.substring(0, 9).equalsIgnoreCase(
								DIR_EXCLUDE_PATH_ARCH)) {
					pathArchPDF = pathArchPDF.substring(9);
				}
				String absolutePathUnionPDF = pathInicial + pathArchPDF
						+ EXT_ARCH;
				System.out.println(absolutePathUnionPDF);
				System.out.println(absolutePathUnionPDF
						.replace(BACKLASH, SLASH));
				System.out.println(absoluteImageDiskPath);
				System.out.println(absoluteImageDiskPath.replace(BACKLASH,
						SLASH));
				// return unirPDF(absolutePathUnionPDF.replace(BACKLASH, SLASH),absoluteImageDiskPath);
				return getArchivoUnionPDFNuevo(absolutePathUnionPDF.replace(BACKLASH, SLASH), absoluteImageDiskPath);
			} else {
				// eror no se encontro la liquidacion del cliente
				throw new Exception(
						"Error no se encontro la liquidacion del cliente");
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return null;
	}
	
	@SuppressWarnings("unchecked")
	public byte[] getPDF(long idLiqCliente)
			throws Exception {
		try {
			String absoluteImageDiskPath = getAppPath() + RELATIVE_WEB_PATH;
			String pathInicial = ContextoProperties.catalinaHome
					+ ContextoProperties.getProperty(DIR_ARCH);
			
			// busco la liquidacion del cliente
			LiqCliente liqCliente = liqClienteServ.leerLiqCliente(idLiqCliente);
			
			if (liqCliente != null) {
				String pathArchPDF = liqCliente.getRutaPdf();
				if (pathArchPDF.length() > 9
						&& pathArchPDF.substring(0, 9).equalsIgnoreCase(
								DIR_EXCLUDE_PATH_ARCH)) {
					pathArchPDF = pathArchPDF.substring(9);
				}
				String absolutePathUnionPDF = pathInicial + pathArchPDF
						+ EXT_ARCH;
				System.out.println(absolutePathUnionPDF);
				System.out.println(absolutePathUnionPDF
						.replace(BACKLASH, SLASH));
				System.out.println(absoluteImageDiskPath);
				System.out.println(absoluteImageDiskPath.replace(BACKLASH,
						SLASH));
				// return unirPDF(absolutePathUnionPDF.replace(BACKLASH, SLASH),absoluteImageDiskPath);
				return getArchivoUnionPDFNuevo(absolutePathUnionPDF.replace(BACKLASH, SLASH), absoluteImageDiskPath);
			} else {
				// eror no se encontro la liquidacion del cliente
				throw new Exception(
						"Error no se encontro la liquidacion del cliente");
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return null;
	}


	public byte[] getArchivoUnionPDFNuevo(String absolutePathUnionPDF,
			String absoluteImageDiskPath) throws IOException, DocumentException {
		File file = new File(absolutePathUnionPDF);
		byte[] array = FileUtils.readFileToByteArray(file);
		return array;
	}


	public byte[] getArchivoEncryptedUnionPDF(String encryptedParameters)
			throws Exception {
		try {
			if (encryptedParameters == null
					|| encryptedParameters.trim().equals("")) {
				throw new Exception("Error los parametros no existen");
			}
			AlgoritmoEncriptacionAES algoritmoEncriptacionAES = new AlgoritmoEncriptacionAES();
			String parameters = algoritmoEncriptacionAES
					.desencriptar(encryptedParameters);
			long idLiqCliente = -1;
			long idCliente = -1;
			String[] parameter = parameters.split(TAB_PARAMETERS);
			for (int i = 0; i < parameter.length; i++) {
				String[] param = parameter[i].split(TAB_PARAMETER_VALUE);
				if (param[0].equalsIgnoreCase(NAME_PARAMETER_ID_CLIENT)) {
					if (Validador.esLong(param[1])) {
						idCliente = Long.parseLong(param[1]);
					}
				} else {
					if (param[0].equalsIgnoreCase(NAME_PARAMETER_ID_LIQ_CLIENT)) {
						if (Validador.esLong(param[1])) {
							idLiqCliente = Long.parseLong(param[1]);
						}
					}
				}

			}
			return getArchivoUnionPDF(idLiqCliente, idCliente);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return null;
	}
	

	private byte[] unirPDF(String absolutePathUnionPDF,
			String absoluteImageDiskPath) throws IOException, DocumentException {
		// Leo el contenido de mi PDF base
		PdfReader reader = new PdfReader(absolutePathUnionPDF);

		Document doc = new Document(PageSize.A4, 0, 0, 0, 0);
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		PdfWriter writer = PdfWriter.getInstance(doc, out);
		doc.open();
		PdfContentByte cb = writer.getDirectContent();

		// Creo una imagen para agregarla y le pongo propiedades de posicion y
		// escala
		Image image = Image.getInstance(absoluteImageDiskPath);
		image.setAbsolutePosition(0, 0);
		image.scalePercent(48);
		// Creo una fuente
		BaseFont bf = BaseFont.createFont(BaseFont.HELVETICA, BaseFont.CP1252,
				BaseFont.NOT_EMBEDDED);
		/*
		 * // Indico que comienzo a escribir texto cb.beginText(); // Indico la fuente a utilizar cb.setFontAndSize(bf, 16); // Le doy Color
		 * cb.setColorFill(BaseColor.RED); // Indico la posicion en la que va a ser colocado el texto cb.setTextMatrix(185, 495); // Escribo el texto
		 * //cb.showText("Tarjeta FIEL"); // Indico que termine de agregar texto cb.endText();
		 */

		float scale = 0.9F;
		for (int i = 1; i <= reader.getNumberOfPages(); i++) {
			if (i > 1) {
				doc.newPage();
			}
			PdfImportedPage page = writer.getImportedPage(reader, i);
			// Agrego una imagen, la cual ya tiene las propiedades de posicion
			cb.addImage(image, true);
			cb.addTemplate(page, scale, 0, 0, scale, 36, 36);

		}
		// Cierro el stamper y se crea el archivo.
		// stamp.close();
		doc.close();
		return out.toByteArray();
	}


	private String getAppPath() throws UnsupportedEncodingException {
		java.net.URL r = this.getClass().getClassLoader().getResource(SLASH);
		String decoded = URLDecoder.decode(r.getFile(), CHARACTERS);
		/*
		 * if (decoded.startsWith(SLASH)) { decoded = decoded.replaceFirst(SLASH, ""); }
		 */
		return new File(new File(new File(decoded).getParent()).getParent())
				.getPath();
	}


	public Boolean registrarApp(String nomdoc, String nrotarjeta, String idregistracion, String nombreapellido, String mail, String resumenmail)
			throws Exception {
		/*
		Long titular = liqClienteServ.buscarTitularApp(nrotarjeta, nomdoc);

		if (titular != 0) {
			Long cliente = liqClienteServ.buscarClienteApp(nrotarjeta, nomdoc);
			liqClienteServ.registrarApp(nomdoc, nrotarjeta, idregistracion, nombreapellido, titular, cliente, mail, resumenmail);
			return true;
		} else {
			return false;
		}
		*/
		return false;
	}


	public Boolean bajaRegistracionApp(String idregistracion) throws Exception {
		//liqClienteServ.bajaRegistracionApp(idregistracion);
		return true;

	}


	public Boolean appResumenMail(String idregistro, String mail) {
		//liqClienteServ.appResumenMail(idregistro, mail);
		return false;

	}


	public Boolean activarPlastico(String codCuenta, String codLote, String codOperacion, String documento, String apellido, String nombre) {
		return liqClienteServ.activarPlastico(codCuenta, codLote, codOperacion, documento, apellido, nombre);

	}


	public Boolean activarPlasticoNuevo(String codCuenta, String codLote, String codOperacion, String documento, String apellido,
			String nombre, Long operador) {

		return liqClienteServ.activarPlasticoNuevo(codCuenta, codLote, codOperacion, documento, apellido, nombre, operador);

	}


	public Boolean activarPlasticoFechaCarga(String codCuenta, String codLote, String codOperacion, String documento, String apellido,
			String nombre, Long operador, String parentesco, String fechaEntrega) {
		return liqClienteServ.activarPlasticoFechaCarga(codCuenta, codLote, codOperacion, documento, apellido, nombre, operador, parentesco,
				fechaEntrega);

	}


	public Boolean activarPlasticoTramite(String codCuenta, String codLote, String codOperacion, String documento, String apellido,
			String nombre, Long operador, String parentesco, String fechaEntrega,
			String lineaCredito, String observacion, String observacionTexto) {

		return liqClienteServ.activarPlasticoTramite(codCuenta, codLote, codOperacion, documento, apellido, nombre, operador, parentesco,
				fechaEntrega, lineaCredito, observacion, observacionTexto);
	}


	public String buscarNotificacionApp(String registo, int tipoNotificacion) throws Exception {
		return "";
				//liqClienteServ.buscarNotificacionApp(registo, tipoNotificacion);
	}


	public String buscarRecomiendaApp() throws Exception {
		return "";
				//liqClienteServ.buscarRecomiendaApp();
	}


	public Boolean buscarRegistracionApp(String idregistracion) throws Exception {
		return true;
				//liqClienteServ.buscarRegistracionApp(idregistracion);
	}


	public DisponibleCliente buscarDiponibleApp(String idregistro) throws Exception {
		DisponibleCliente disponibleCliente = new DisponibleCliente();
		disponibleCliente.setDisponible(0D);
		disponibleCliente.setLimitecredito(0D);
		disponibleCliente.setLimitecreditoAdelanto(0D);
		disponibleCliente.setProximocierre("");
		disponibleCliente.setProximovencimiento("");
		disponibleCliente.setSaldototal(0D);
		return disponibleCliente;
				//liqClienteServ.buscarDiponibleApp(idregistro);
	}


	public UltimoResumenCuenta buscarUltimoResumenCuenta(String idregistro) throws Exception {
		UltimoResumenCuenta ultimoResumen = new UltimoResumenCuenta();
		return ultimoResumen;
				//liqClienteServ.buscarUltimoResumenCuenta(idregistro);
	}


	public List<DetalleResumen> buscarDetalleResumenCuenta(String idregistro, String fecha) throws Exception {
		return new ArrayList<DetalleResumen>();
				//liqClienteServ.buscarDetalleResumenCuenta(idregistro, fecha);
	}


	public List<MovimientosPeriodoActual> movimientosPeriodoActual(String idregistro, String fecha) throws Exception {
		return new ArrayList<MovimientosPeriodoActual>();
				//liqClienteServ.movimientosPeriodoActual(idregistro, fecha);
	}


	public List<Notificacion> buscarNotificacionesCliente(Long idCliente) throws Exception {
		return new ArrayList<Notificacion>();
				//liqClienteServ.buscarNotificacionesCliente(idCliente);
	}


	public String bloqueoPastico(String idregistro, String nrodoc) throws Exception {
		return "";
				//liqClienteServ.bloqueoPastico(idregistro, nrodoc);
	}


	public ClienteApp buscarClienteRegApp(String idregistro) throws Exception {
		String apiKey = "AIzaSyAQQUlFwB3P4Q3Aa53CdugcCk-Cni_NXxc";
		Content content = new Content();
		content.addRegId(idregistro);
		content.createData("Tarjeta Fiel", "Su versión de la aplicación se encuentra fuera de servició. Por favor, actualícela desde Google Play Store.");
		// 1. URL
		URL url = new URL("https://fcm.googleapis.com/fcm/send");

		// 2. Open connection
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();

		// 3. Specify POST method
		conn.setRequestMethod("POST");

		// 4. Set the headers
		conn.setRequestProperty("Content-Type", "application/json");
		conn.setRequestProperty("Authorization", "key=" + apiKey);

		conn.setDoOutput(true);

		// 5. Add JSON data into POST request body

		// `5.1 Use Jackson object mapper to convert Contnet object into JSON
		ObjectMapper mapper = new ObjectMapper();

		// 5.2 Get connection output stream
		DataOutputStream wr = new DataOutputStream(conn.getOutputStream());

		// 5.3 Copy Content "JSON" into
		mapper.writeValue(wr, content);

		// 5.4 Send the request
		wr.flush();

		// 5.5 close
		wr.close();
		
		ClienteApp cliente = new ClienteApp();
		cliente.setApellidoNombre("Actualizar Aplicación.");
		cliente.setSituacionCobranza(1);
		cliente.setSituacionComercial(1);
		cliente.setCodigo("");
		cliente.setIdTitular(0L);
		cliente.setSituacionPlastico("");
		return cliente;
				//liqClienteServ.buscarClienteRegApp(idregistro);
	}


	public Promocion buscarPromocionesApp() throws Exception {
		return liqClienteServ.buscarPromocionesApp();
	}


	public Boolean inserirPromocion(Promocion promocion) throws Exception {
		liqClienteServ.inserirPromocion(promocion);
		return true;
	}


	public LiqClienteService getLiqClienteServ() {
		return liqClienteServ;
	}


	public void setLiqClienteServ(LiqClienteService liqClienteServ) {
		this.liqClienteServ = liqClienteServ;
	}

}
