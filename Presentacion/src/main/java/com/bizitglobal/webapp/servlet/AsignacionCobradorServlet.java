package com.bizitglobal.webapp.servlet;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.jasperreports.engine.JRException;

import org.apache.commons.dbcp.BasicDataSource;
import org.apache.log4j.Logger;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.bizitglobal.tarjetafiel.cobranzas.negocio.EjecucionPlanAsignacionCobrador;
import com.bizitglobal.tarjetafiel.cobranzas.negocio.TareaPendiente;
import com.bizitglobal.tarjetafiel.cobranzas.service.EjecucionPlanService;
import com.bizitglobal.tarjetafiel.cobranzas.service.TareaPendienteService;
import com.bizitglobal.tarjetafiel.commons.util.Fecha;
import com.bizitglobal.tarjetafiel.commons.util.ManejadorArchivos;
import com.bizitglobal.tarjetafiel.commons.util.PropertieFile;
import com.bizitglobal.tarjetafiel.transacciones.negocio.LiqCliente;
import com.bizitglobal.tarjetafiel.transacciones.service.LiqClienteService;
import com.bizitglobal.tarjetafiel.transacciones.service.LiquidacionClientesService;
import com.bizitglobal.webapp.faces.beans.util.MergePDF;
import com.bizitglobal.webapp.faces.util.GeneradorDeInforme;
import com.lowagie.text.pdf.PdfReader;


public class AsignacionCobradorServlet extends HttpServlet implements
		javax.servlet.Servlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private static final Logger log = Logger
			.getLogger(AsignacionCobradorServlet.class);

	ApplicationContext appContext = null;
	EjecucionPlanService ejecucionPlanService;
	TareaPendienteService tareaPendienteService;
	LiquidacionClientesService liquidacionClientesService;
	LiqClienteService liqClienteService;

	private static int PROCESO_ENVIO_CARTA = 1;
	private static int PROCESO_FICHA_COBRADORES = 2;
	private static int PROCESO_COBROS_COBRADORES = 3;
	private static int PROCESO_LLAMADO_TELEFONICO = 4;
	private static int PROCESO_TELEDIRECTO = 5;
	private static int PROCESO_ASIGNAR_ABOGADOS = 6;
	private static int PROCESO_LIQUIDACION_JUDICIAL = 7;


	public void init(ServletConfig conf) throws ServletException {
		super.init(conf);
	}


	public void doPost(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		service(req, res);
	}


	/**
	 * req debe traer los atributos, diaTarea, mesTarea, anioTarea, que es la fecha de la respectiva tarea pendiente que se quiere confirmar.
	 * Asimismo, debe traer el atributo idTarea (que indica que se confirma, 8=asignacion de cobradores, etc.)
	 * */
	@SuppressWarnings("unchecked")
	public void service(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		res.setContentType("text/html");

		// Instanciamos los servicios
		appContext = WebApplicationContextUtils
				.getRequiredWebApplicationContext(getServletContext());

		ejecucionPlanService = (EjecucionPlanService) appContext
				.getBean("ejecucionPlanService");
		tareaPendienteService = (TareaPendienteService) appContext
				.getBean("tareaPendienteService");

		liquidacionClientesService = (LiquidacionClientesService) appContext
				.getBean("liquidacionClientesService");

		liqClienteService = (LiqClienteService) appContext
				.getBean("liqClienteService");

		// siempre deberiamo enviar el parametro peticion, para ver que rama se
		// ejecutara.
		int peticion = Integer.valueOf(req.getParameter("peticion").toString())
				.intValue();

		if (peticion == PROCESO_ENVIO_CARTA
				|| peticion == PROCESO_FICHA_COBRADORES
				|| peticion == PROCESO_LLAMADO_TELEFONICO
				|| peticion == PROCESO_TELEDIRECTO
				|| peticion == PROCESO_ASIGNAR_ABOGADOS
				|| peticion == PROCESO_LIQUIDACION_JUDICIAL) {

			int idAccion = Integer.valueOf(
					req.getParameter("idAccion").toString()).intValue();

			int dia = Integer.valueOf(req.getParameter("diaTarea").toString())
					.intValue();
			int mes = Integer.valueOf(req.getParameter("mesTarea").toString())
					.intValue();
			int anio = Integer
					.valueOf(req.getParameter("anioTarea").toString())
					.intValue();
			Calendar cal = Calendar.getInstance();
			cal.set(Calendar.DATE, dia);
			cal.set(Calendar.MONTH, mes);
			cal.set(Calendar.YEAR, anio);

			new Fecha(cal); // Esto es importante ya que la clase fecha usa un
							// método estatico que devuelve la fecha en un
							// formato especial

			TareaPendiente tp = new TareaPendiente();
			tp.setFecha(cal.getTime());
			tp.setIdAccion(idAccion);

			try {

				List<EjecucionPlanAsignacionCobrador> asignacionesARealizar = null;

				StringBuffer absolutePathDirectory = crearDirectorioAbsolutoPDF(
						cal, peticion, idAccion);

				StringBuffer relativePathDirectory = crearDirectorioRelativoPDF(absolutePathDirectory);

				log.info("Los Reportes se guardaran en: "
						+ relativePathDirectory);

				asignacionesARealizar = tareaPendienteService
						.getListaDetallesTareasByParam(cal.getTime(), new Long(
								tp.getIdAccion()), "N");

				Iterator<EjecucionPlanAsignacionCobrador> iter = asignacionesARealizar
						.iterator();

				switch (peticion) {
				case 1: // ENVIO CARTA
					StringBuffer urlImagenIconoFiel = new StringBuffer(
							getHomePath(req) + "/img/fiel/logo_fiel.jpg");
					
					while (iter.hasNext()) {
						EjecucionPlanAsignacionCobrador ejec = (EjecucionPlanAsignacionCobrador) iter
								.next();
						
						boolean generarCarta = true;
						// Id Accion:
						// 1- Carta Moroso
						// 11- Aviso Carta Urgente
						// 12- Aviso Carta Documentado
						// 13- Aviso Carta Prejudicial
						
						if(idAccion == 11){ // Aviso Carta Urgente, se genera si o si.
							generarCarta = true;
						}
						
						if(generarCarta){
							try {
								generarPDF(getParametrosEnvioCarta(ejec,
										absolutePathDirectory,
										relativePathDirectory, idAccion,
										urlImagenIconoFiel));
	
								StringBuffer urlArchivoPDF = getURLPDFGenerado(
										relativePathDirectory, ejec, idAccion);
	
								// Cambio el estado a confirmado = 'S' y le seteo la
								// url
								ejecucionPlanService.cambiarEstadoYAddFile(
										ejec.getIdEjecucionPlan(), "S",
										urlArchivoPDF);
	
							} catch (Exception e) {
								log.error("No se pudo generar el Reporte para el cliente: "
										+ ejec.getIdUsuario());
							}
						}
					}

					break;
					
				case 2: // PETICION_FICHAS_COBRANZA

					while (iter.hasNext()) {
						EjecucionPlanAsignacionCobrador ejec = (EjecucionPlanAsignacionCobrador) iter
								.next();

						try {
							generarPDF(getParametrosFichaCobrador(ejec,
									absolutePathDirectory,
									relativePathDirectory));

							StringBuffer urlArchivoPDF = getURLPDFGenerado(
									relativePathDirectory, ejec, idAccion);

							// Cambio el estado a confirmado = 'S' y le seteo la
							// url
							ejecucionPlanService.cambiarEstadoYAddFile(
									ejec.getIdEjecucionPlan(), "S",
									urlArchivoPDF);

						} catch (Exception e) {
							log.error("No se pudo generar el Reporte para el cliente: "
									+ ejec.getNumeroCliente());
						}
					}

					break;
					
				case 4: // LISTADO LLAMADOS TELEFONICOS

					generarPDF(getParametrosLlamadasTelefonicas(cal,
							absolutePathDirectory, relativePathDirectory));

					while (iter.hasNext()) {
						EjecucionPlanAsignacionCobrador ejec = (EjecucionPlanAsignacionCobrador) iter
								.next();

						try {

							// Cambio el estado a confirmado = 'S' y le seteo la
							// url
							ejecucionPlanService.cambiarEstadoYAddFile(
									ejec.getIdEjecucionPlan(), "S", null);

						} catch (Exception e) {
							log.error("No se pudo cambiar el estado para el cliente: "
									+ ejec.getNumeroCliente());
						}
					}

					break;
					
				case 5: // PROCESO TELEDIRECTO
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

					StringBuffer nombreArchivosTelCelulares = new StringBuffer(
							"teledirecto_celulares_"
									+ sdf.format(cal.getTime()) + ".txt");
					StringBuffer nombreArchivosTelFijos = new StringBuffer(
							"teledirecto_fijos_" + sdf.format(cal.getTime())
									+ ".txt");

					ManejadorArchivos.crearArchivo(absolutePathDirectory,
							nombreArchivosTelFijos);
					ManejadorArchivos.crearArchivo(absolutePathDirectory,
							nombreArchivosTelCelulares);

					StringBuffer contentsCelulares = new StringBuffer();
					StringBuffer contentsFijos = new StringBuffer();

					while (iter.hasNext()) {
						EjecucionPlanAsignacionCobrador ejec = (EjecucionPlanAsignacionCobrador) iter
								.next();

						Iterator<String> iCelus = ejec.getListaTelCelulares()
								.iterator();
						while (iCelus.hasNext()) {
							contentsCelulares.append((String) iCelus.next()
									+ ";" + ejec.idUsuario + ";"
									+ ejec.getApellidoCliente() + " "
									+ ejec.getNombreCliente() + "\n");
						}

						Iterator<String> iFijos = ejec.getListaTelFijos()
								.iterator();
						while (iFijos.hasNext()) {
							contentsFijos.append((String) iFijos.next() + ";"
									+ ejec.idUsuario + ";"
									+ ejec.getApellidoCliente() + " "
									+ ejec.getNombreCliente() + "\n");
						}

						ejecucionPlanService.cambiarEstadoYAddFile(
								ejec.getIdEjecucionPlan(), "S", null);

					}

					ManejadorArchivos.escribirArchivo(absolutePathDirectory,
							nombreArchivosTelCelulares, contentsCelulares);

					ManejadorArchivos.escribirArchivo(absolutePathDirectory,
							nombreArchivosTelFijos, contentsFijos);

					break;
					
				case 6: // PROCESO ASIGNAR ABOGADOS

					log.info("doGet - report jrxml url: "
							+ getClass().getClassLoader().getResource(
									GeneradorDeInforme.REPORT_PREFIX
											+ "Cobranzas_Detalles_Cliente"
											+ GeneradorDeInforme.REPORT_SUFFIX));

					StringBuffer subReportURL = new StringBuffer(getClass()
							.getClassLoader().getResource(
									GeneradorDeInforme.REPORT_PREFIX
											+ "Cobranzas_Detalles_Cliente"
											+ GeneradorDeInforme.REPORT_SUFFIX)
							+ "");

					while (iter.hasNext()) {
						EjecucionPlanAsignacionCobrador ejec = (EjecucionPlanAsignacionCobrador) iter
								.next();

						try {
							generarPDF(getParametrosFichaAbogado(ejec,
									absolutePathDirectory,
									relativePathDirectory, subReportURL));

							StringBuffer urlArchivoPDF = getURLPDFGenerado(
									relativePathDirectory, ejec, idAccion);

							// Cambio el estado a confirmado = 'S' y le seteo la
							// url
							ejecucionPlanService.cambiarEstadoYAddFile(
									ejec.getIdEjecucionPlan(), "S",
									urlArchivoPDF);

						} catch (Exception e) {
							log.error("No se pudo generar el Reporte para el cliente: "
									+ ejec.getNumeroCliente());
						}
					}
					break;
					
				case 7: // PROCESO LIQUIDACION JUDICIAL

					while (iter.hasNext()) {
						EjecucionPlanAsignacionCobrador ejec = (EjecucionPlanAsignacionCobrador) iter
								.next();

						try {

							LiqCliente liqCliente = liquidacionClientesService.generarLiquidacionJudicialCliente(ejec.getIdUsuario());

							StringBuffer urlArchivoPDF = null;

							if (liqCliente != null) {
								generarPDF(getParametrosLiquidacionJudicial(ejec,
										absolutePathDirectory,
										relativePathDirectory, liqCliente));

								urlArchivoPDF = getURLPDFGenerado(
										relativePathDirectory, ejec, idAccion);

								liqCliente.setRutaPdf(urlArchivoPDF.toString().replaceAll(".pdf", ""));

								liqClienteService.actualizarLiqCliente(liqCliente);

							}

							// Cambio el estado a confirmado = 'S' y le seteo la
							// url
							ejecucionPlanService.cambiarEstadoYAddFile(
									ejec.getIdEjecucionPlan(), "S",
									urlArchivoPDF);

						} catch (Exception e) {
							log.error("No se pudo generar el Reporte para el cliente: "
									+ ejec.getNumeroCliente());
						}
					}
					break;
				}

				// Unir planilla Cobradores
				if (peticion == PROCESO_ENVIO_CARTA
						|| peticion == PROCESO_FICHA_COBRADORES
						|| peticion == PROCESO_ASIGNAR_ABOGADOS) {
					unirPDFsGenerados(absolutePathDirectory);

				}

				log.info("Tarea finalizada de forma exitosa");

			} catch (Exception e) {
				e.printStackTrace();
				throw new ServletException("Error al procesar los datos");
			}

		} else if (peticion == PROCESO_COBROS_COBRADORES) { // Planilla Cobros
															// de cobradores
			int diaDesde = 0;
			int mesDesde = 0;
			int anioDesde = -1;
			int diaHasta = 0;
			int mesHasta = -1;
			int anioHasta = 0;
			Long idCobrador = null;

			if (req.getParameter("idCobrador") != null) {
				idCobrador = Long.valueOf(req.getParameter("idCobrador")
						.toString());
			}

			if (req.getParameter("diaDesde") != null) {
				diaDesde = Integer.valueOf(
						req.getParameter("diaDesde").toString()).intValue();
			}

			if (req.getParameter("mesDesde") != null) {
				mesDesde = Integer.valueOf(
						req.getParameter("mesDesde").toString()).intValue();
			}

			if (req.getParameter("anioDesde") != null) {
				anioDesde = Integer.valueOf(
						req.getParameter("anioDesde").toString()).intValue();
			}

			Calendar fechaDesde = null;

			if (diaDesde != 0 && mesDesde != -1 && anioDesde != 0) {
				fechaDesde = Calendar.getInstance();
				fechaDesde.set(Calendar.DATE, diaDesde);
				fechaDesde.set(Calendar.MONTH, mesDesde);
				fechaDesde.set(Calendar.YEAR, anioDesde);
			}

			if (req.getParameter("diaHasta") != null) {
				diaHasta = Integer.valueOf(
						req.getParameter("diaHasta").toString()).intValue();
			}

			if (req.getParameter("mesHasta") != null) {
				mesHasta = Integer.valueOf(
						req.getParameter("mesHasta").toString()).intValue();
			}

			if (req.getParameter("anioHasta") != null) {
				anioHasta = Integer.valueOf(
						req.getParameter("anioHasta").toString()).intValue();
			}

			Calendar fechaHasta = null;

			if (diaHasta != 0 && mesHasta != -1 && anioHasta != 0) {
				fechaHasta = Calendar.getInstance();
				fechaHasta.set(Calendar.DATE, diaHasta);
				fechaHasta.set(Calendar.MONTH, mesHasta);
				fechaHasta.set(Calendar.YEAR, anioHasta);
			}

			if (fechaDesde != null && fechaHasta != null) {
				StringBuffer absolutePathDirectory;
				try {
					absolutePathDirectory = crearDirectorioAbsolutoPDF(
							fechaHasta, peticion, 0);

					StringBuffer relativePathDirectory = crearDirectorioRelativoPDF(absolutePathDirectory);

					log.info("Los Reportes se guardaran en: "
							+ relativePathDirectory);

					generarPDF(getParametrosCobrosCobradores(fechaDesde,
							fechaHasta, idCobrador, absolutePathDirectory,
							relativePathDirectory));

				} catch (Exception e) {
					e.printStackTrace();
					throw new ServletException("Error al procesar los datos");
				}
			} else {
				throw new ServletException("Error al procesar los datos");
			}
		}
	}


	private StringBuffer getParametrosLiquidacionJudicial(
			EjecucionPlanAsignacionCobrador ejec,
			StringBuffer absolutePathDirectory,
			StringBuffer relativePathDirectory, LiqCliente liqCliente) {

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat sdf1 = new SimpleDateFormat("dd/MM/yyyy");

		StringBuffer resultado = new StringBuffer();

		String nombreFC = absolutePathDirectory + "/"
				+ ejec.getApellidoCliente().trim() + "_"
				+ ejec.getNombreCliente().trim() + "_" + ejec.getIdUsuario();

		resultado.append("Presentacion/report/LiquidacionJudicialClientes.jrxml");

		resultado.append("?guardarEn= " + nombreFC);

		// resultado.append("ƒid_cliente= " + ejec.getIdUsuario());
		// resultado.append("ƒid_ejec_plan= " + ejec.getIdEjecucionPlan());
		// if (ejec.getUltimoPagoEfectuado() != null) {
		// resultado.append("ƒfecha_ultimo_pago= "
		// + sdf.format(ejec.getUltimoPagoEfectuado()));
		// }
		// resultado.append("ƒdomicilio_cliente= " + ejec.getDomicilioCliente());
		// resultado.append("ƒlocalidad_cliente= " + ejec.getLocalidadCliente());
		// resultado.append("ƒtelefonos_cliente= " + ejec.getTelefonosCliente());

		// resultado.append("ƒadi1= ");
		// resultado.append("ƒadi2= ");
		// resultado.append("ƒadi3= ");
		resultado.append("ƒliquidacion_numero=" + liqCliente.getIdLiqCliente());
		resultado.append("ƒcuenta_nro=" + ejec.getIdUsuario());

		resultado.append("ƒnombre=" + ejec.getApellidoCliente().trim() + " " + ejec.getNombreCliente().trim());
		resultado.append("ƒdireccion= " + ejec.getDomicilioCliente());
		resultado.append("ƒdescripcion= " + ejec.getLocalidadCliente());
		// resultado.append("ƒcp= ");
		// resultado.append("ƒciudad= ");

		resultado.append("ƒdesc1=" + "Int.Compensatorios" + "ƒtna1=" + liqCliente.getIntComp());
		resultado.append("ƒdesc2=" + "Int.Punitorio" + "ƒtna2=" + liqCliente.getIntPunitorios());

		resultado.append("ƒcredito= " + new BigDecimal(liqCliente.getLineaDeCredito()).setScale(BigDecimal.ROUND_HALF_DOWN, 2));

		resultado.append("ƒcredito= " + new BigDecimal(liqCliente.getSaldoTotal()).setScale(BigDecimal.ROUND_HALF_DOWN, 2));

		resultado.append("ƒdisponible= " + new BigDecimal(liqCliente.getDisponible()).setScale(BigDecimal.ROUND_HALF_DOWN, 2));

		// resultado.append("ƒ1cuota= ");
		// resultado.append("ƒ2cuota= ");
		// resultado.append("ƒ3cuota= ");

		resultado.append("ƒf_1_ven= " + sdf.format(liqCliente.getFechaLiq()));

		resultado.append("ƒtotal_resumen= " + liqCliente.getMontoTotal());

		resultado.append("ƒfechaEspecial= " + sdf1.format(liqCliente.getFechaCierre()));

		resultado.append("fechaResumenAnterior= " + sdf1.format(liqCliente.getFechaCierreAnterior()));
		// resultado.append("ƒcodBarr= ");
		// resultado.append("ƒcodBarrDos= ");
		// resultado.append("ƒcodBarrTres= ");
		//
		// resultado.append("ƒmensaje1= ");
		// resultado.append("ƒmensaje2= ");
		// resultado.append("ƒmensaje3= ");

		resultado.append("ƒf_2_ven= " + sdf.format(liqCliente.getFechaProximoVto()));

		resultado.append("ƒf_3_ven= " + sdf.format(liqCliente.getFechaVto3()));

		return resultado;
	}


	private StringBuffer getParametrosFichaAbogado(
			EjecucionPlanAsignacionCobrador ejec,
			StringBuffer absolutePathDirectory,
			StringBuffer relativePathDirectory, StringBuffer subReportURL) {

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

		StringBuffer resultado = new StringBuffer();

		String nombreFC = absolutePathDirectory + "/"
				+ ejec.getApellidoCliente().trim() + "_"
				+ ejec.getNombreCliente().trim() + "_" + ejec.getIdUsuario();

		resultado.append("Presentacion/report/Cobranzas_Ficha_Abogado.jrxml");

		resultado.append("?guardarEn= " + nombreFC);

		resultado.append("ƒid_cliente= " + ejec.getIdUsuario());
		resultado.append("ƒid_ejec_plan= " + ejec.getIdEjecucionPlan());
		if (ejec.getUltimoPagoEfectuado() != null) {
			resultado.append("ƒfecha_ultimo_pago= "
					+ sdf.format(ejec.getUltimoPagoEfectuado()));
		}
		resultado.append("ƒsubreport_url= " + subReportURL);
		resultado.append("ƒdomicilio_cliente= " + ejec.getDomicilioCliente());
		resultado.append("ƒlocalidad_cliente= " + ejec.getLocalidadCliente());
		resultado.append("ƒtelefonos_cliente= " + ejec.getTelefonosCliente());

		return resultado;
	}


	private StringBuffer getParametrosLlamadasTelefonicas(Calendar fecha,
			StringBuffer absolutePathDirectory,
			StringBuffer relativePathDirectory) {

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

		StringBuffer resultado = new StringBuffer();

		String nombreFC = absolutePathDirectory + "/"
				+ "LISTADO_TELEFONOS_MOROSOS";
		resultado.append("Presentacion/report/LLam_telef.jrxml");

		resultado.append("?guardarEn= " + nombreFC);

		resultado.append("ƒfecha_ejec= " + sdf.format(fecha.getTime()));

		return resultado;
	}


	private StringBuffer getParametrosCobrosCobradores(Calendar fechaDesde,
			Calendar fechaHasta, Long idCobrador,
			StringBuffer absolutePathDirectory,
			StringBuffer relativePathDirectory) {
		StringBuffer resultado = new StringBuffer();

		String nombreFC = absolutePathDirectory + "/"
				+ "PLANILLA_COBROS_COBRADORES";

		if (idCobrador == 0) {
			resultado
					.append("Presentacion/report/Cobranzas_cobros_cuentas.jrxml");
		} else {
			resultado.append("Presentacion/report/CobranzasCobrosXCob.jrxml");
		}

		resultado.append("?guardarEn= " + nombreFC);

		if (idCobrador != 0) {
			resultado.append("ƒid_cobrador= " + idCobrador);
		}

		resultado.append("ƒfecha_desde= " + fechaDesde.get(Calendar.YEAR) + "-"
				+ (fechaDesde.get(Calendar.MONTH) + 1) + "-"
				+ fechaDesde.get(Calendar.DATE));

		resultado.append("ƒfecha_hasta= " + fechaHasta.get(Calendar.YEAR) + "-"
				+ (fechaHasta.get(Calendar.MONTH) + 1) + "-"
				+ fechaHasta.get(Calendar.DATE));

		return resultado;
	}


	private StringBuffer getURLPDFGenerado(StringBuffer relativePathDirectory,
			EjecucionPlanAsignacionCobrador ejec, int idAccion) {

		StringBuffer urlPDFGenerado = new StringBuffer(relativePathDirectory
				+ "/");
		//
		// switch (idAccion) {
		// case 1:
		// urlPDFGenerado.append(ejec.getApellidoCliente().trim() + "_"
		// + ejec.getNombreCliente().trim() + "_"
		// + ejec.getIdUsuario() + ".pdf");
		// break;
		//
		// case 8:
		//
		// urlPDFGenerado.append(ejec.getApellidoCobrador().trim() + "_"
		// + ejec.getNombreCobrador().trim() + "_"
		// + ejec.getIdUsuario() + ".pdf");
		// break;
		//
		// case 11:
		// urlPDFGenerado.append(ejec.getApellidoCliente().trim() + "_"
		// + ejec.getNombreCliente().trim() + "_"
		// + ejec.getIdUsuario() + ".pdf");
		// break;
		// case 12:
		// urlPDFGenerado.append(ejec.getApellidoCliente().trim() + "_"
		// + ejec.getNombreCliente().trim() + "_"
		// + ejec.getIdUsuario() + ".pdf");
		// break;
		// case 13:
		// urlPDFGenerado.append(ejec.getApellidoCliente().trim() + "_"
		// + ejec.getNombreCliente().trim() + "_"
		// + ejec.getIdUsuario() + ".pdf");
		// break;
		// case 15:
		//
		// urlPDFGenerado.append(ejec.getApellidoCliente().trim() + "_"
		// + ejec.getNombreCliente().trim() + "_"
		// + ejec.getIdUsuario() + ".pdf");
		// break;
		//
		// case 15:
		//
		//
		// break;
		// }
		/* @I5520 */
		switch (idAccion) {
		case 8:
			return urlPDFGenerado.append(ejec.getApellidoCobrador().trim() + "_"
					+ ejec.getNombreCobrador().trim() + "_"
					+ ejec.getIdUsuario() + ".pdf");

		default:
			return urlPDFGenerado.append(ejec.getApellidoCliente().trim() + "_"
					+ ejec.getNombreCliente().trim() + "_"
					+ ejec.getIdUsuario() + ".pdf");
		}

	}


	private StringBuffer getParametrosFichaCobrador(
			EjecucionPlanAsignacionCobrador ejecucionPlanAsignacionCobrador,
			StringBuffer absolutePathDirectory,
			StringBuffer relativePathDirectory) {

		StringBuffer resultado = new StringBuffer();

		String nombreFC = absolutePathDirectory + "/"
				+ ejecucionPlanAsignacionCobrador.getApellidoCobrador().trim()
				+ "_"
				+ ejecucionPlanAsignacionCobrador.getNombreCobrador().trim()
				+ "_" + ejecucionPlanAsignacionCobrador.getIdUsuario();

		resultado.append("Presentacion/report/Cobranzas_Ficha_Cobrador.jrxml");

		resultado.append("?guardarEn= " + nombreFC);

		resultado.append("ƒcobrador= "
				+ ejecucionPlanAsignacionCobrador.getApellidoCobrador() + ", "
				+ ejecucionPlanAsignacionCobrador.getNombreCobrador());

		resultado.append("ƒnumero_cuenta= "
				+ ejecucionPlanAsignacionCobrador.getIdUsuario());

		resultado.append("ƒcliente= "
				+ ejecucionPlanAsignacionCobrador.getApellidoCliente() + ", "
				+ ejecucionPlanAsignacionCobrador.getNombreCliente());

		resultado.append("ƒtipo_documento= "
				+ ejecucionPlanAsignacionCobrador.getTipoDocumento());

		resultado.append("ƒnumero_documento= "
				+ ejecucionPlanAsignacionCobrador.getNroDocumento());

		resultado
				.append("ƒfecha_nacimiento= "
						+ (ejecucionPlanAsignacionCobrador.getFechaNacimiento() != null ? ejecucionPlanAsignacionCobrador
								.getFechaNacimiento() : ""));

		resultado.append("ƒdomicilio_particular= "
				+ ejecucionPlanAsignacionCobrador.getDomicilioCliente());

		resultado.append("ƒtelefono_particular= "
				+ ejecucionPlanAsignacionCobrador.getTelefonosCliente());

		resultado.append("ƒempresa= "
				+ ejecucionPlanAsignacionCobrador.getDescripcionEmpresa());

		resultado.append("ƒdomicilio_empresa= "
				+ ejecucionPlanAsignacionCobrador.getDomicilioEmpresa());

		resultado.append("ƒtelefono_empresa= "
				+ ejecucionPlanAsignacionCobrador.getTelefonosEmpresa());

		resultado.append("ƒcodeudor= "
				+ ejecucionPlanAsignacionCobrador.getNombreApellidoGarante());

		resultado.append("ƒdomicilio_codeudor= "
				+ ejecucionPlanAsignacionCobrador.getDomicilioGarante());

		resultado.append("ƒtelefono_codeudor= "
				+ ejecucionPlanAsignacionCobrador.getTelefonosGarante());

		resultado
				.append("ƒultimo_pago= "
						+ (ejecucionPlanAsignacionCobrador
								.getUltimoPagoEfectuado() != null ? ejecucionPlanAsignacionCobrador
								.getUltimoPagoEfectuado() : ""));

		/*
		 * NO BORRAR LAS SIGUIENTES LINEAS COMENTADAS, YA QUE EN UN FUTURO PUEDEN PEDIR LO DE AGENDA
		 */

		// Calendar cal = Calendar.getInstance();

		// resultado.append("ƒobservacion_crediticia= ");
		//
		// resultado.append("ƒobservacion_cobranza= ");

		// resultado.append("ƒfecha1_agenda= " + cal.get(Calendar.YEAR) + "-"
		// + (cal.get(Calendar.MONTH) + 1) + "-" + cal.get(Calendar.DATE));
		//
		//
		// resultado.append("ƒfecha2_agenda= " + cal.get(Calendar.YEAR) + "-"
		// + (cal.get(Calendar.MONTH) + 1) + "-" + cal.get(Calendar.DATE));
		//
		// resultado.append("ƒcomentario1_agenda= ");
		//
		// resultado.append("ƒcomentario2_agenda= ");

		return resultado;
	}


	private StringBuffer getParametrosEnvioCarta(
			EjecucionPlanAsignacionCobrador ejecucionPlanAsignacionCobrador,
			StringBuffer absolutePathDirectory,
			StringBuffer relativePathDirectory, int idAccion,
			StringBuffer urlImagenIconoFiel) {

		StringBuffer resultado = new StringBuffer();

		StringBuffer nombreFC = new StringBuffer(absolutePathDirectory + "/"
				+ ejecucionPlanAsignacionCobrador.getApellidoCliente().trim()
				+ "_"
				+ ejecucionPlanAsignacionCobrador.getNombreCliente().trim()
				+ "_" + ejecucionPlanAsignacionCobrador.getIdUsuario());

		switch (idAccion) {
		case 1:
			resultado.append("Presentacion/report/Carta_Moroso_1.jrxml");
			break;
		case 11:
			resultado.append("Presentacion/report/Aviso_Carta_Urgente.jrxml");
			break;
		case 12:
			resultado.append("Presentacion/report/Aviso_Carta_Documentado.jrxml");
			break;
		case 13:
			resultado.append("Presentacion/report/Aviso_Carta_Prejudicial.jrxml");
			break;
		}

		resultado.append("?guardarEn= " + nombreFC);

		resultado.append("ƒnumero_cuenta= "
				+ ejecucionPlanAsignacionCobrador.getIdUsuario());

		resultado.append("ƒcliente= "
				+ ejecucionPlanAsignacionCobrador.getApellidoCliente() + ", "
				+ ejecucionPlanAsignacionCobrador.getNombreCliente());

		resultado.append("ƒdomicilio= "
				+ ejecucionPlanAsignacionCobrador.getDomicilioCliente());

		resultado.append("ƒlocalidad= "
				+ ejecucionPlanAsignacionCobrador.getLocalidadCliente());

		resultado.append("ƒfecha_hoy= " + Fecha.getFechaFormatoEspanol());

		resultado.append("ƒURLImagenLogo= " + urlImagenIconoFiel);

		return resultado;
	}


	private StringBuffer crearDirectorioAbsolutoPDF(Calendar cal, int peticion,
			int idAccion) throws Exception {

		StringBuffer resultado = new StringBuffer();

		SimpleDateFormat formatoFecha = new SimpleDateFormat("dd-MM-yyyy");

		File directorio = null;

		PropertieFile prop = new PropertieFile(
				System.getProperty("catalina.home") + "/" + "webapps" + "/"
						+ "contexto.properties");

		resultado.append(System.getProperty("catalina.home"));
		resultado.append(prop.getProperties("directorioArchivos"));

		switch (peticion) {
		case 1: // Directorio Envio Cartas
			switch (idAccion) {
			case 1: // AVISO MOROSO
				resultado.append(prop.getProperties("cartaClientesMorosos"));
				break;
			case 11: // AVISO URGENTE
				resultado.append(prop.getProperties("cartaAvisoUrgente"));
				break;
			case 12: // AVISO DOCUMENTADO
				resultado.append(prop.getProperties("cartaAvisoDocumentado"));
				break;
			case 13: // AVISO PREJUDICIAL
				resultado.append(prop.getProperties("cartaAvisoPrejudicial"));
				break;
			}

			break;
		case 2: // Directorio Fichas de cobradores
			resultado.append(prop.getProperties("fichasDeCobradores"));
			break;

		case 3: // Fichas Cobros Cobradores
			resultado.append(prop.getProperties("cobrosDeCobradores"));
			break;

		case 4: // Listado Telefónico clientes morosos
			resultado.append(prop.getProperties("listadoTelefonos"));
			break;
		case 5: // Teledirecto
			resultado.append(prop.getProperties("teledirecto"));
			break;
		case 6: // Teledirecto
			resultado.append(prop.getProperties("fichasAbogados"));
			break;
		case 7: // Teledirecto
			resultado.append(prop.getProperties("directorioLiquidacionesJudiciales"));
			break;
		}

		resultado.append("/");
		resultado.append(formatoFecha.format(cal.getTime()));

		resultado = new StringBuffer(resultado.toString().replace("\\", "/"));

		directorio = new File(resultado.toString());

		if (!directorio.exists()) {
			directorio.mkdirs();
		}

		return resultado;
	}


	private StringBuffer crearDirectorioRelativoPDF(StringBuffer absolutePath) {
		return new StringBuffer(absolutePath.toString().replaceAll(".*webapps",
				""));
	}


	public void generarPDF(StringBuffer parametros) throws BeansException,
			IOException, JRException, SQLException {

		GeneradorDeInforme gen = new GeneradorDeInforme();

		log.info("la cadena que se envia es: " + parametros);
		gen.guardarReporte(parametros.toString(), ((BasicDataSource) appContext
				.getBean("dataSource")).getConnection());

	}


	public void unirPDFsGenerados(StringBuffer absolutePathDirectory)
			throws Exception {

		List<PdfReader> pdfs = new ArrayList<PdfReader>();
		String nombre;
		File directorio = null;
		directorio = new File(absolutePathDirectory.toString());
		if (!directorio.exists()) {
			throw new Exception("El directorio no existe.");
		}
		File[] archivos = directorio.listFiles();
		Arrays.sort(archivos);
		for (File file : archivos) {
			nombre = file.getAbsolutePath();
			pdfs.add(new PdfReader(nombre));
		}
		String outputFile = directorio.getAbsolutePath() + "/" + "ZZ_UNION_DOC"
				+ directorio.getName() + ".pdf";
		MergePDF.concatPDFs(pdfs, outputFile);

	}


	private String getHomePath(HttpServletRequest request) {
		int pos = request.getRequestURL().lastIndexOf(request.getServletPath());
		return request.getRequestURL().substring(0, pos);
	}

}