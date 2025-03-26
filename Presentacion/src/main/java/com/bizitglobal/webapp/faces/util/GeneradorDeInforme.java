package com.bizitglobal.webapp.faces.util;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.ServletOutputStream;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JRParameter;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.JasperRunManager;
import net.sf.jasperreports.engine.base.JRBaseParameter;
import net.sf.jasperreports.engine.data.JRXmlDataSource;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.engine.export.JRXlsExporter;

import org.apache.commons.dbcp.BasicDataSource;
import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;


@SuppressWarnings({"rawtypes","unchecked"})
public class GeneradorDeInforme {
	private String xmlFile;

	private static final Logger log = Logger.getLogger(GeneradorDeInforme.class);

	public static final String URL_PREFIX = "/report/";
	public static final String REPORT_PREFIX = "com/bizitglobal/webapp/report/";
	public static final String REPORT_SUFFIX = ".jasper";
	public static final String REPORT_DEFINITION_SUFFIX = ".jrxml";
	// public static final String REPORT_DEFINITION_SUFFIX = ".xls";

	private ByteArrayOutputStream byteArrayOutputStream;
	public Connection coneccionBase;


	public void guardarReporte(String cadena, Connection coneccionBase) throws IOException, JRException {
		this.coneccionBase = coneccionBase;
		guardarReporte(cadena);
	}


	public void guardarReporte(String cadena)
			throws IOException, JRException {
		xmlFile = null;
		log.info("doGet - inicio");

		int i = cadena.indexOf(URL_PREFIX);
		if (i != -1) {

			int j = cadena.indexOf(REPORT_DEFINITION_SUFFIX);
			String reportName = cadena.substring(i + URL_PREFIX.length(), j);
			cadena = cadena.substring(j + 7); // porque trae .jrxml?
			JasperReport report = null;
			Map mapaDeElementos = new HashMap();
			String[] elementos = cadena.split("ƒ");

			for (int h = 0; h < elementos.length; h++) {
				String[] elem = elementos[h].split("=");
				mapaDeElementos.put(elem[0], elem[1]);
			}

			String xmlFile = (String) mapaDeElementos.get("JRXmlDataSource");
			mapaDeElementos.remove("JRXmlDataSource");
			String importarAExcel = (String) mapaDeElementos.get("AExcel");
			mapaDeElementos.remove("AExcel");
			StringBuffer guardarEnArchivo 
				= (mapaDeElementos.get("guardarEn")!=null) ? new StringBuffer((String) mapaDeElementos.get("guardarEn")) : null;
			
			mapaDeElementos.remove("guardarEn");
			boolean importToExcel;
			boolean guardarArchivo;
			try {
				importToExcel = "excel".compareTo(importarAExcel) == 0;
			} catch (NullPointerException e) {
				importToExcel = false;
			}
			if(guardarEnArchivo!=null){
				guardarArchivo = true;
			}
			else{
				guardarArchivo = false;
			}
			/*
			try {
				"".compareTo(guardarEnArchivo.toString());
				guardarArchivo = true;
			} catch (NullPointerException e) {
				guardarArchivo = false;
			}
			*/
			String xpath2 = (String) mapaDeElementos.get("xpath2");

			// String xmlFile = "C://Documents and Settings//fernando//Desktop//balance.xml";

			if (j != -1) {
				Connection con = null;
				try {
					// String ext = reportName.substring(j+1).toUpperCase();
					// reportName = reportName.substring(0,j);
					log.info("El reporte llamado es:" + reportName);
					report = compileReport(reportName);

					// InputStream report = getClass().getClassLoader().getResourceAsStream(REPORT_PREFIX+reportName+REPORT_SUFFIX);
					if (report != null) {
						Map args = new HashMap();
						JRParameter parameter[] = report.getParameters();
						for (int k = 0; k < parameter.length; k++) {
							JRBaseParameter param = (JRBaseParameter) parameter[k];
							// log.info("Parametro getValueClassName() " + k + " : " + param.getValueClassName());
							// log.info("Parametro getName() " + k + " : " + param.getName() + " " +param.isForPrompting() + " && " +
							// param.isSystemDefined());

							// Solo cargo los parametros que no son del sistema del jasper
							if (!param.isSystemDefined()) {
								if (param.isForPrompting()) {

									String arg = param.getName();
									Object obj = null;
									if (param.getValueClassName().equals("net.sf.jasperreports.engine.JasperReport")) {
										JasperReport jasperValue = compileReport(arg);
										obj = jasperValue;
									} else {
										String value = null;
										String value1 = (String) mapaDeElementos.get(arg);
										if (value1 != null) {
											value = value1.trim();
										}
										if (value != null) {
											if (param.getValueClassName().equals("java.lang.String")) {
												log.info("Param: " + arg + ". Es String -> " + value);
												obj = value;
											} else {
												if (param.getValueClassName().equals("java.lang.Long")) {
													Long longValue = new Long(value.trim());
													log.info("Param: " + arg + ". Es Long -> " + longValue);
													obj = longValue;
												} else {
													if (param.getValueClassName().equals("java.util.Date")) {
														SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
														Date value_date = null;
														try {
															if (value != null && !value.equals("")) {
																value_date = sdf.parse(value);
															}
														} catch (Exception e) {

															try {
																sdf.applyPattern("dd/MM/yyyy");
																value_date = sdf.parse(value);
															} catch (Exception e1) {
																sdf.applyPattern("dd-MM-yyyy");
																value_date = sdf.parse(value);
															}

														}

														log.info("Param: " + arg + ". Es Date -> " + value_date);
														obj = value_date;
													} else {
														if (param.getValueClassName().equals("java.lang.Boolean")) {
															Boolean boolValue = new Boolean(value);
															log.info("Param: " + arg + ". Es Boolean -> " + boolValue);
															obj = boolValue;
														} else {
															if (param.getValueClassName().equals("java.lang.Integer")) {
																Integer integerValue = new Integer(value);
																log.info("Param: " + arg + ". Es Integer -> " + integerValue);
																obj = integerValue;
															} else {
																if (param.getValueClassName().equals("java.lang.Double")) {
																	Double doubleValue = new Double(value);
																	log.info("Param: " + arg + ". Es Double -> " + doubleValue);
																	obj = doubleValue;
																} else {
																	if (param.getValueClassName().equals("java.lang.Float")) {
																		Float floatValue = new Float(value);
																		log.info("Param: " + arg + ". Es Float -> " + floatValue);
																		obj = floatValue;
																	} else {
																		if (param.getValueClassName().equals("java.sql.Timestamp")) {
																			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
																			Timestamp timestampValue = new Timestamp(sdf.parse(value).getTime());
																			log.info("Param: " + arg + ". Es Timestamp -> " + timestampValue);
																			obj = timestampValue;
																		} else {
																			if (param.getValueClassName().equals("java.math.BigDecimal")) {
																				BigDecimal bigDecimalValue = new BigDecimal(value);
																				log.info("Param: " + arg + ". Es BigDecimal -> " + bigDecimalValue);
																				obj = bigDecimalValue;
																			} else {
																				if (param.getValueClassName().equals("java.lang.Short")) {
																					Short shortValue = new Short(value);
																					log.info("Param: " + arg + ". Es Short -> " + shortValue);
																					obj = shortValue;
																				} else {
																					if (param.getValueClassName().equals("java.lang.Object")) {
																						Object objectValue = value;
																						log.info("Param: " + arg + ". Es Object -> " + objectValue);
																						obj = objectValue;
																					} else {
																						obj = null;
																					}
																				}
																			}
																		}
																	}
																}
															}
														}
													}
												}
											}
										}
										else {
											// le nuleo el parametro que le pasamos al informe
											obj = null;
										}
									}
									args.put(arg, obj);
								}
							}
						}

						byte[] result = null;

						/*
						 * File xml = ...; JasperPrint jp = JasperFillManager.fillReport( jasperReport, new HashMap(), new JRXmlDataSource(xml,
						 * �/vendedores/vendedor�);
						 */
						InputStream input = null;
						BufferedInputStream buffer = null;
						if (xmlFile != null) {
							if (importToExcel) {
								try {
									String key;
									key = "catalina.home";
									key = System.getProperty(key);
									File xml = new File(key + xmlFile);
									byteArrayOutputStream = new ByteArrayOutputStream();
									JasperPrint print = JasperFillManager.fillReport(report, args, new JRXmlDataSource(xml, xpath2));
									JRXlsExporter exporterXLS = new JRXlsExporter();
									ByteArrayOutputStream xlsReport = new ByteArrayOutputStream();
									exporterXLS.setParameter(JRExporterParameter.JASPER_PRINT, print);
									exporterXLS.setParameter(JRExporterParameter.OUTPUT_STREAM, xlsReport);
									exporterXLS.exportReport();
									System.out.println("Sixe of byte array:" + xlsReport.size());
									byte[] bytes = xlsReport.toByteArray();

								} catch (Exception e) {
									e.printStackTrace();
								}
							} else {
								String key;
								key = "catalina.home";
								key = System.getProperty(key);
								File xml = new File(key + xmlFile);
								result = JasperRunManager.runReportToPdf(report, args, new JRXmlDataSource(xml, xpath2));

							}
						} else {
							try {
								if (coneccionBase == null) {
									ApplicationContext appContext = null;
									ServletContext context = Session.getRequest().getSession().getServletContext();
									appContext = WebApplicationContextUtils.getRequiredWebApplicationContext(context);
									con = ((BasicDataSource) appContext.getBean("dataSource")).getConnection();
								} else {
									con = coneccionBase;
								}

							} catch (SQLException e) {
								e.printStackTrace();
								try {
									con = coneccionBase;
								} catch (Exception exc) {
									e.printStackTrace();
								}
							} catch (Exception exp) { // entra desde cobranzas
								exp.printStackTrace();
								try {
									con = coneccionBase;
								} catch (Exception expi) {
									expi.printStackTrace();
								}
							}

							if (importToExcel) {
								try {
									byteArrayOutputStream = new ByteArrayOutputStream();
									java.io.ByteArrayOutputStream output = new java.io.ByteArrayOutputStream();
									JasperPrint print = JasperFillManager.fillReport(report, args, con);
									JRXlsExporter exporterXLS = new JRXlsExporter();
									ByteArrayOutputStream xlsReport = new ByteArrayOutputStream();
									exporterXLS.setParameter(JRExporterParameter.JASPER_PRINT, print);
									exporterXLS.setParameter(JRExporterParameter.OUTPUT_STREAM, xlsReport);
									System.out.println("Sixe of byte array:" + xlsReport.size());
									exporterXLS.exportReport();
									byte[] bytes = xlsReport.toByteArray();
									if(guardarArchivo){
										guardarEnArchivo.append(".xls");
										FileOutputStream fptr = new FileOutputStream(guardarEnArchivo.toString().trim());
										DataOutputStream f = new DataOutputStream(fptr);
										f.write(bytes);
										fptr.close();
									}
									

								} catch (Exception e) {
									e.printStackTrace();
								}
							} else {
								result = JasperRunManager.runReportToPdf(report, args, con);
								guardarEnArchivo.append(".pdf");
								FileOutputStream fptr = new FileOutputStream(guardarEnArchivo.toString().trim());
								DataOutputStream f = new DataOutputStream(fptr);
								f.write(result);
								fptr.close();		
							}

						}
						log.info("doGet - finalización del informe");
					}
					// else{
					// log.info("doGet - SC_NOT_FOUND or connection is null");
					// response.sendError( HttpServletResponse.SC_NOT_FOUND );
					// }
				} catch (Exception e) {
					log.info("doGet - exception", e);
					e.printStackTrace();
					throw new JRException(e.getMessage());
				} finally {
					if (con != null) {
						try {
							log.info("doGet - cerrando la coneccion");
							con.close();
						} catch (SQLException e1) {
							e1.printStackTrace();
						}
					}

				}
			}
			else
			{
				log.info("doGet - SC_INTERNAL_SERVER_ERROR");
				// response.sendError( HttpServletResponse.SC_INTERNAL_SERVER_ERROR,
				// "Report format not specified: append extension to report name" );
			}
		}
		else
		{
			log.info("doGet - SC_INTERNAL_SERVER_ERROR");
			// response.sendError( HttpServletResponse.SC_INTERNAL_SERVER_ERROR,
			// "Bad URL prefix for servlet: check your web.xml file" );
		}
	}


	private JasperReport compileReport(String reportName) throws JRException {
		JasperReport report = null;
		log.info("doGet - report jrxml url: " + getClass().getClassLoader().getResource(REPORT_PREFIX + reportName + REPORT_DEFINITION_SUFFIX));
		if (null != getClass().getClassLoader().getResource(REPORT_PREFIX + reportName + REPORT_DEFINITION_SUFFIX)) {

			// log.info("doGet - intentando compilar el informe " +
			// getClass().getClassLoader().getResource(REPORT_PREFIX+reportName+REPORT_DEFINITION_SUFFIX).getFile());
			report = JasperCompileManager.compileReport(getClass().getClassLoader().getResourceAsStream(
					REPORT_PREFIX + reportName + REPORT_DEFINITION_SUFFIX));

		}
		// log.info("REPORTE MAESTRO::: " + REPORT_PREFIX+reportName+REPORT_SUFFIX);
		// URL urlReporteMaestro = getClass().getClassLoader().getResource(REPORT_PREFIX+reportName+REPORT_SUFFIX);
		// report = (JasperReport) JRLoader.loadObject(urlReporteMaestro);

		return report;
	}

}
