package com.bizitglobal.webapp.servlet;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
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
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
import net.sf.jasperreports.engine.export.JRXlsExporter;


import org.apache.commons.dbcp.BasicDataSource;
import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;


public class ReportServlet extends HttpServlet {
	private String xmlFile;
	private static final long serialVersionUID = -5605017166013496429L;

	private static final Logger log = Logger.getLogger(ReportServlet.class);

	public static final String URL_PREFIX = "/report/";
	public static final String REPORT_PREFIX = "com/bizitglobal/webapp/report/";
	public static final String REPORT_SUFFIX = ".jasper";
	public static final String REPORT_DEFINITION_SUFFIX = ".jrxml";
	// public static final String REPORT_DEFINITION_SUFFIX = ".xls";

	private ByteArrayOutputStream byteArrayOutputStream;


	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		xmlFile = null;
		log.info("doGet - inicio");
		String uri = request.getRequestURI();
		log.info("URI= " + uri.toString());

		int i = uri.indexOf(URL_PREFIX);
		if (i != -1) {

			int j = uri.indexOf(REPORT_DEFINITION_SUFFIX);
			String reportName = uri.substring(i + URL_PREFIX.length(), j);

			JasperReport report = null;

			String xmlFile = request.getParameter("JRXmlDataSource");
			String importarAExcel = request.getParameter("AExcel");
			String guardarEnArchivo = request.getParameter("GuardarEn");
			boolean importToExcel;
			boolean guardarArchivo;
			try {
				importToExcel = "excel".compareTo(importarAExcel) == 0;
			} catch (NullPointerException e) {
				importToExcel = false;
			}
			try {
				"".compareTo(guardarEnArchivo);
				guardarArchivo = true;
			} catch (NullPointerException e) {
				guardarArchivo = false;
			}
			String xpath2 = request.getParameter("xpath2");

			// String xmlFile = "C://Documents and Settings//fernando//Desktop//balance.xml";

			if (j != -1) {
				Connection con = null;
				try {
					// String ext = reportName.substring(j+1).toUpperCase();
					// reportName = reportName.substring(0,j);
					report = compileReport(reportName);

					// InputStream report = getClass().getClassLoader().getResourceAsStream(REPORT_PREFIX+reportName+REPORT_SUFFIX);
					log.info("doGet - report: " + report);
					log.info("doGet - iniciando lista de parametros");
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
										log.info("Param: " + arg + ". Es SubReport -> " + jasperValue);
										obj = jasperValue;
									} else {
										final String value = request.getParameter(arg);
										if (value != null) {
											if (param.getValueClassName().equals("java.lang.String")) {
												log.info("Param: " + arg + ". Es String -> " + value);
												obj = value;
											} else {
												if (param.getValueClassName().equals("java.lang.Long")) {
													Long longValue = new Long(value);
													log.info("Param: " + arg + ". Es Long -> " + longValue);
													obj = longValue;
												} else {
													if (param.getValueClassName().equals("java.util.Date")) {
														SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
														Date value_date = sdf.parse(value);
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
									}
									args.put(arg, obj);
								}
							}
						}

						log.info("doGet - argc " + args.size());

						byte[] result = null;

						log.info("doGet - construyendo el reporte");
						// log.info("doGet - formato pdf");
						// log.info("doGet - args: " + args.keySet().toString());
						// log.info("doGet - args values: " + args.values().toString());
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

									byte[] bytes = xlsReport.toByteArray();
//									response.setContentType("application/vnd.ms-excel");
									response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
//									response.setHeader("Content-Disposition", "attachment; filename=" + reportName + ".xls");
									response.setHeader("Content-Disposition", "attachment; filename=" + reportName + ".xlsx");
									response.setContentLength(bytes.length);
									ServletOutputStream ouputStream = response.getOutputStream();
									ouputStream.write(bytes, 0, bytes.length);
									ouputStream.flush();
									ouputStream.close();
								} catch (Exception e) {
									e.printStackTrace();
								}
							} else {
								String key;
								key = "catalina.home";
								key = System.getProperty(key);
								File xml = new File(key + xmlFile);
								result = JasperRunManager.runReportToPdf(report, args, new JRXmlDataSource(xml, xpath2));
								// lo siguiente agregue yo walde. si no anda sacarlo
								response.setContentType("application/pdf");
								response.setContentLength(result.length);
								response.getOutputStream().write(result);
							}
						} else {
							try {
								log.info("doGet - iniciando conexion ad BD");
								ApplicationContext appContext = null;
								ServletContext context = getServletContext();
								appContext = WebApplicationContextUtils.getRequiredWebApplicationContext(context);
								con = ((BasicDataSource) appContext.getBean("dataSource")).getConnection();
								log.info("doGet - terminando conexion ad BD");
							} catch (SQLException e) {
								e.printStackTrace();
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
									exporterXLS.exportReport();

									byte[] bytes = xlsReport.toByteArray();
//									response.setContentType("application/vnd.ms-excel");
									response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
//									response.setHeader("Content-Disposition", "attachment; filename=" + reportName + ".xls");
									response.setHeader("Content-Disposition", "attachment; filename=" + reportName + ".xlsx");
									response.setContentLength(bytes.length);
									ServletOutputStream ouputStream = response.getOutputStream();
									ouputStream.write(bytes, 0, bytes.length);
									ouputStream.flush();
									ouputStream.close();
								} catch (Exception e) {
									e.printStackTrace();
								}
							} else {
								result = JasperRunManager.runReportToPdf(report, args, con);
								response.setContentType("application/pdf");
								log.info("doGet - finalización del informe");
								response.setContentLength(result.length);
								response.getOutputStream().write(result);
							}

						}
						log.info("doGet - finalización del informe");
					} else {
						log.info("doGet - SC_NOT_FOUND or connection is null");
						response.sendError(HttpServletResponse.SC_NOT_FOUND);
					}
				} catch (Exception e) {
					log.info("doGet - exception", e);
					e.printStackTrace();
					response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, e.getMessage());
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
				response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR,
						"Report format not specified: append extension to report name");
			}
		}
		else
		{
			log.info("doGet - SC_INTERNAL_SERVER_ERROR");
			response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR,
					"Bad URL prefix for servlet: check your web.xml file");
		}
	}


	private JasperReport compileReport(String reportName) throws JRException {
		JasperReport report = null;
		log.info("doGet - report name solicitado: " + reportName);
		// log.info("doGet - report file: " + REPORT_PREFIX+reportName+REPORT_SUFFIX);
		log.info("doGet - report jrxml url: " + getClass().getClassLoader().getResource(REPORT_PREFIX + reportName + REPORT_DEFINITION_SUFFIX));
		// log.info("doGet - comprobación de fichero .jasper");
		if (null != getClass().getClassLoader().getResource(REPORT_PREFIX + reportName + REPORT_DEFINITION_SUFFIX)) {

			log.info("doGet - intentando compilar el informe "
					+ getClass().getClassLoader().getResource(REPORT_PREFIX + reportName + REPORT_DEFINITION_SUFFIX).getFile());
			report = JasperCompileManager.compileReport(getClass().getClassLoader().getResourceAsStream(
					REPORT_PREFIX + reportName + REPORT_DEFINITION_SUFFIX));

			// String f =
			// JasperCompileManager.compileReportToFile(getClass().getClassLoader().getResource(REPORT_PREFIX+reportName+REPORT_DEFINITION_SUFFIX).getFile());
			// log.debug("doGet - Compilación OK, se ha generado el fichero: " + f);
		}
		return report;
	}
}
