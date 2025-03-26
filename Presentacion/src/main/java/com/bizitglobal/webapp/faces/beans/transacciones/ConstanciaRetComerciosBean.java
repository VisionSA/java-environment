package com.bizitglobal.webapp.faces.beans.transacciones;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.faces.component.html.HtmlSelectOneMenu;
import javax.faces.event.ValueChangeEvent;
import javax.faces.model.SelectItem;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import com.bizitglobal.tarjetafiel.commons.util.PropertieFile;
import com.bizitglobal.webapp.faces.beans.BaseBean;
import com.bizitglobal.webapp.faces.beans.util.MergePDF;
import com.bizitglobal.webapp.faces.util.GeneradorDeInforme;
import com.bizitglobal.webapp.faces.util.Session;
import com.lowagie.text.pdf.PdfReader;


@SuppressWarnings({"rawtypes","unchecked"})
public class ConstanciaRetComerciosBean extends BaseBean {
	private static final Logger log = Logger.getLogger(ConstanciaRetComerciosBean.class);
	private String idMesSeleccionado;
	private String idAnioSeleccionado;
	private List mesItems = new ArrayList();
	private List anioItems = new ArrayList();
	private HtmlSelectOneMenu mes = new HtmlSelectOneMenu();
	private HtmlSelectOneMenu anioItem = new HtmlSelectOneMenu();
	private int mesAnteriorAlActual;
	private String[] meses;
	private String labelButton;
	private boolean mostraBoton = false;
	private PropertieFile prop = new PropertieFile(System.getProperty("catalina.home") + "/webapps/contexto.properties");
	Calendar calendar = Calendar.getInstance();
	private static final String NOMBRE_ARCHIVO_UNION_PDF = "combined.pdf";
	private boolean listaVacia = false;


	public ConstanciaRetComerciosBean() {
		error.borrar();
	}


	public String inicializar() {
		log.info("Ejecutando ==> inicilizando el bean de Constancia Retencion.");
		borrar();
		cargarMeses();
		cargarAnios();
		setearPeriodoAnteriorALActual();
		calendar.setTime(new Date());
		cambiarLabelBoton(null);
		return "generacionConstanciaRetencionComercio";
	}


	public String irAContinuar() {
		return inicializar();
	}


	public String irAImprimirTodos() {
		abrirArchivo();
		return null;
	}


	public String irASalir() {
		return "inicio";
	}


	private void setearPeriodoAnteriorALActual() {

		mesAnteriorAlActual = calendar.get(Calendar.MONTH);

		if (mesAnteriorAlActual <= 9) {
			if (mesAnteriorAlActual != 0 && mesAnteriorAlActual != 9) {
				idMesSeleccionado = "0" + String.valueOf(mesAnteriorAlActual);
			}
			else {
				if (mesAnteriorAlActual == 9) {
					idMesSeleccionado = "09";
				}
				else {
					idMesSeleccionado = "12";
				}
			}
			mes.setValue(idMesSeleccionado);
		}
		else {
			idMesSeleccionado = String.valueOf(mesAnteriorAlActual);
			mes.setValue(idMesSeleccionado);
		}
		if (idMesSeleccionado.equals("12")) {
			idAnioSeleccionado = String.valueOf(calendar.get(Calendar.YEAR) - 1);

		} else {
			idAnioSeleccionado = String.valueOf(calendar.get(Calendar.YEAR));
		}
		anioItem.setValue(idAnioSeleccionado);
	}


	private void cargarMeses() {
		mesItems.add(new SelectItem("01", "Enero"));
		mesItems.add(new SelectItem("02", "Febrero"));
		mesItems.add(new SelectItem("03", "Marzo"));
		mesItems.add(new SelectItem("04", "Abril"));
		mesItems.add(new SelectItem("05", "Mayo"));
		mesItems.add(new SelectItem("06", "Junio"));
		mesItems.add(new SelectItem("07", "Julio"));
		mesItems.add(new SelectItem("08", "Agosto"));
		mesItems.add(new SelectItem("09", "Septiembre"));
		mesItems.add(new SelectItem("10", "Octubre"));
		mesItems.add(new SelectItem("11", "Noviembre"));
		mesItems.add(new SelectItem("12", "Diciembre"));
		meses = new String[13];
		meses[1] = "Enero";
		meses[2] = "Febrero";
		meses[3] = "Marzo";
		meses[4] = "Abril";
		meses[5] = "Mayo";
		meses[6] = "Junio";
		meses[7] = "Julio";
		meses[8] = "Agosto";
		meses[9] = "Septiembre";
		meses[10] = "Octubre";
		meses[11] = "Noviembre";
		meses[12] = "Diciembre";
	}


	private void cargarAnios() {
		int hasta = calendar.get(Calendar.YEAR) - 15;
		for (int i = 0; i <= 15; i++) {
			anioItems.add(new SelectItem(String.valueOf(hasta + i), String.valueOf(hasta + i)));
		}

	}


	public String cambiarLabelBoton(ValueChangeEvent event) {
		try {
			listaVacia = false;
			idMesSeleccionado = (String) mes.getValue();
			idAnioSeleccionado = (String) anioItem.getValue();
			Integer cant = transaccionesService.getRetencionAComercioService().getCantDeConstanciasAGenerar(idMesSeleccionado, idAnioSeleccionado);
			if (cant.intValue() == 0) {
				labelButton = "Generar";
			} else {
				labelButton = "Imprimir";
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}


	public boolean validar() {
		error.borrar();
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new Date());
		if (calendar.get(Calendar.YEAR) < Integer.parseInt(idAnioSeleccionado)) {
			error.agregar("El año ingresado de menor o igual al corriente año ");
		} else {
			if (Integer.parseInt(idMesSeleccionado) - 1 >= mesAnteriorAlActual && calendar.get(Calendar.YEAR) == Integer.parseInt(idAnioSeleccionado)) {
				error.agregar("El mes seleccionado debe ser menor al mes actual ");
			}
		}
		return (error.cantidad() == 0) ? true : false;
	}


	public void borrar() {
		error.borrar();
		popup.borrar();
		tituloLargo = "TARJETA FIEL";
		tituloCorto = "Generar Constancia de Retencion/Percepcion Comercios";
		mesItems.clear();
	}


	public String ejecutarAccion() {
		if (validar()) {
			error.borrar();
			if (labelButton.equals("Generar")) {
				List comerciosParaConstancia;
				HttpServletRequest request = Session.getRequest();
				try {
					String rutaConstanciaRetencion = crearDirectoMes();
					String home = System.getProperty("catalina.home");
					try {
						comerciosParaConstancia = transaccionesService.getRetencionAComercioService().listadoComerciosParaConstanciaRetencion(
								idMesSeleccionado, idAnioSeleccionado, null);
						Iterator it = comerciosParaConstancia.iterator();
						Long codComercio, comercio, constancia;
						listaVacia = (it.hasNext()) ? false : true;
						while (it.hasNext()) {
							Object[] data = (Object[]) it.next();
							comercio = (Long) data[0];
							codComercio = (Long) data[1];
							constancia = (Long) data[2];

							String nombreArchivo = idAnioSeleccionado + "-" + idMesSeleccionado + "-" + constancia + "-" + codComercio;
							String p0 = "?guardarEn=" + home + "/" + rutaConstanciaRetencion + "/" + nombreArchivo;
							String p1 = "ƒURLImagen= " + Session.getHomePath() + "/img/fiel/logo_fiel.jpg";
							String p2 = "ƒfecha_label=" + meses[Integer.parseInt(idMesSeleccionado)] + " de " + idAnioSeleccionado;
							String p3 = "ƒmes=" + idMesSeleccionado;
							String p4 = "ƒanio=" + idAnioSeleccionado;
							String p5 = "ƒcomercio=" + comercio;
							String page = request.getContextPath() + "/report/constanciaRetencionComercio.jrxml";
							GeneradorDeInforme gen = new GeneradorDeInforme();
							gen.guardarReporte(page + p0 + p1 + p2 + p3 + p4 + p5);
							try {
								
								String bucket = "constanciaRetenciones/"+idAnioSeleccionado+idMesSeleccionado;
								String fileAmazonContancia = home + "/" + rutaConstanciaRetencion + "/" + nombreArchivo;
								String fileAmazonContanciaPdf = fileAmazonContancia.trim()+".pdf";	
								Map<String, String> headers = new HashMap<String, String>();
						    	   // headers.put("User-Agent", "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_14_4) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/79.0.3945.88 Safari/537.36");
						    	  //  HttpPostMultipart multipart = new HttpPostMultipart("http://localhost/index", "utf-8", headers);
						    	    //HttpPostMultipart multipart = new HttpPostMultipart("http://192.168.0.10:8080/storage/uploadFile", "utf-8", headers);
//						    	    HttpPostMultipart multipart = new HttpPostMultipart("http://192.168.0.76:8080/s3_bucket/storage/uploadFile", "utf-8", headers);
						    	    HttpPostMultipart multipart = new HttpPostMultipart("http://192.168.0.66:8080/s3_bucket/storage/uploadFile", "utf-8", headers);
						    	    // Add form field
//						    	    multipart.addFormField("username", "test_name");
//						    	    multipart.addFormField("password", "test_psw");    	    
						    	    multipart.addFormField("bucket", bucket);
						    	    // Add file
						    	    multipart.addFilePart("file", new File(fileAmazonContanciaPdf));
						    	    // Print result
//						    	    String response = multipart.finish();
//						    	    System.out.println(response); 
						    	    log.info("archivo pdf subido al Amazon: " + fileAmazonContanciaPdf);
								} catch (Exception e) {
									log.info("Error en subir archivo al Amazon: " + home + "/" + rutaConstanciaRetencion + "/" + nombreArchivo+".pdf");
									// TODO Auto-generated catch block
									e.printStackTrace();
								}

							transaccionesService.getRetencionAComercioService().actualizarURL(constancia,
									rutaConstanciaRetencion + "/" + nombreArchivo);
						}
						if (!listaVacia) {
							String ruta = "/../archivos/constanciaRetenciones/" + idAnioSeleccionado + idMesSeleccionado;
							unirPDFConstancias();
							popup.setPopup(popup.ICONO_OK, "La generacion de los certificados finalizo con exito.");
						}

					} catch (Exception e) {
						popup.setPopup(popup.ICONO_FALLA, "Ocurrio un error mientras se generaban los certificados.");
						error.agregar("Ocurrio.");
						e.printStackTrace();
					}
				} catch (Exception e3) {
					error.agregar("No se ha podido crear el directorio para los certificados.");
					e3.printStackTrace();
				}
			} else {
				abrirArchivo();
			}
		}

		return null;
	}


	public String abrirArchivo() {
		try {
			// String ruta=
			// File.pathSeparator+".."+File.pathSeparator+"archivos"+File.pathSeparator+"constanciaRetenciones"+File.pathSeparator+idAnioSeleccionado+idMesSeleccionado+File.pathSeparator+"prueba.pdf";
			String ruta = "/../archivos/constanciaRetenciones/" + idAnioSeleccionado + idMesSeleccionado + "/" + NOMBRE_ARCHIVO_UNION_PDF;
			ejecutarJavaScript("popup('" + ruta + "',1000,700), 'titlebar=no';");
		} catch (Exception e) {
			log.info("Error al intentar leer el archivo");
			e.printStackTrace();
		}
		return "";
	}


	public void unirPDFConstancias() {
		try {
			List<PdfReader> pdfs = new ArrayList<PdfReader>();
			String nombre;
			String key = "catalina.home";
			key = System.getProperty(key);
			PropertieFile prop = new PropertieFile(key + "/webapps/contexto.properties");
			String linea = null;
			File directorio = null;
			directorio = new File(key + "/" + prop.getProperties("directorioArchivos") + prop.getProperties("directorioConstanciaRetenciones") + "/"
					+ idAnioSeleccionado + idMesSeleccionado);
			if (!directorio.exists()) {
				if (!directorio.mkdir())
					throw new Exception("Error al generar certificado.");
			}
			File[] archivos = directorio.listFiles();
			Arrays.sort(archivos);
			// Arrays.sort(archivos, new Comparator<File>(){
			// public int compare(File f1, File f2)
			// {
			// return String.valueOf(f1.getName()).compareTo(f2.getName());
			// } });

			for (File file : archivos) {
				nombre = file.getAbsolutePath();
				pdfs.add(new PdfReader(nombre));
			}
			String outputFile = directorio.getAbsolutePath() + "/" + NOMBRE_ARCHIVO_UNION_PDF;
			MergePDF.concatPDFs(pdfs, outputFile);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}


	/**
	 * Crea el directorio de las liquidaciones en la posición especificada por los atributos del contexto.properties, de la siguiente forma:
	 * directorioArchivos/directorioLiquidacionesClientes/carpeta con dd-mm-yyyy
	 * */
	private String crearDirectoMes() throws Exception {
		File directorio = new File(System.getProperty("catalina.home") + "/" + prop.getProperties("directorioArchivos") + "/"
				+ prop.getProperties("directorioConstanciaRetenciones"));
		if (!directorio.exists()) {
			if (!directorio.mkdir())
				throw new Exception("No se ha podido crear el directorio para almacenar los Certificados.");
		}
		String nombreConst = prop.getProperties("directorioArchivos") + prop.getProperties("directorioConstanciaRetenciones") + "/"
				+ idAnioSeleccionado + idMesSeleccionado;
		File directorioInterno = new File(System.getProperty("catalina.home") + "/" + nombreConst);
		if (!directorioInterno.exists()) {
			if (!directorioInterno.mkdir())
				throw new Exception("No se ha podido crear el directorio para almacenar los Certificados.");
		}
		return nombreConst;
	}


	public String cancelar() {
		borrar();
		return "inicio";
	}


	public String getIdMesSeleccionado() {
		return idMesSeleccionado;
	}


	public void setIdMesSeleccionado(String idMesSeleccionado) {
		this.idMesSeleccionado = idMesSeleccionado;
	}


	public List getMesItems() {
		return mesItems;
	}


	public void setMesItems(List mesItems) {
		this.mesItems = mesItems;
	}


	public HtmlSelectOneMenu getMes() {
		return mes;
	}


	public void setMes(HtmlSelectOneMenu mes) {
		this.mes = mes;
	}


	public String getLabelButton() {
		return labelButton;
	}


	public void setLabelButton(String labelButton) {
		this.labelButton = labelButton;
	}


	public boolean isMostraBoton() {
		return mostraBoton;
	}


	public void setMostraBoton(boolean mostraBoton) {
		this.mostraBoton = mostraBoton;
	}


	public HtmlSelectOneMenu getAnioItem() {
		return anioItem;
	}


	public void setAnioItem(HtmlSelectOneMenu anioItem) {
		this.anioItem = anioItem;
	}


	public String getIdAnioSeleccionado() {
		return idAnioSeleccionado;
	}


	public void setIdAnioSeleccionado(String idAnioSeleccionado) {
		this.idAnioSeleccionado = idAnioSeleccionado;
	}


	public List getAnioItems() {
		return anioItems;
	}


	public void setAnioItems(List anioItems) {
		this.anioItems = anioItems;
	}


	public boolean isListaVacia() {
		return listaVacia;
	}


	public void setListaVacia(boolean listaVacia) {
		this.listaVacia = listaVacia;
	}

}
