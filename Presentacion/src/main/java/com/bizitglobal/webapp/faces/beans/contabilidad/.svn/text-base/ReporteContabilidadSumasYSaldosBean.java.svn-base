package com.bizitglobal.webapp.faces.beans.contabilidad;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.sql.Timestamp;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import javax.faces.component.html.HtmlSelectOneMenu;
import javax.faces.event.ValueChangeEvent;
import javax.faces.model.SelectItem;
import javax.servlet.http.HttpServletRequest;
import org.apache.log4j.Logger;
//import org.apache.myfaces.custom.tree2.Tree;
//import org.apache.myfaces.custom.tree2.TreeNode;
//import org.apache.myfaces.custom.tree2.TreeNodeBase;
import org.jdom.Attribute;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.output.XMLOutputter;
import zeus.util.TreeNode;
import com.bizitglobal.tarjetafiel.commons.filtros.Filtro;
import com.bizitglobal.tarjetafiel.contabilidad.exception.BalanceException;
import com.bizitglobal.tarjetafiel.contabilidad.exception.EjercicioCreateException;
import com.bizitglobal.tarjetafiel.contabilidad.exception.EjercicioException;
import com.bizitglobal.tarjetafiel.contabilidad.exception.PlanCuentaDosException;

import com.bizitglobal.tarjetafiel.contabilidad.negocio.Ejercicio;
import com.bizitglobal.tarjetafiel.contabilidad.negocio.PlanCuentaDos;
import com.bizitglobal.tarjetafiel.contabilidad.negocio.PlanCuentaSimple;
import com.bizitglobal.tarjetafiel.contabilidad.negocio.SumasYSaldos;
import com.bizitglobal.webapp.faces.beans.BaseBean;
import com.bizitglobal.webapp.faces.util.Error;
import com.bizitglobal.webapp.faces.util.Session;
import com.bizitglobal.webapp.faces.util.Validador;


@SuppressWarnings({"rawtypes","unchecked","unused"})
public class ReporteContabilidadSumasYSaldosBean extends BaseBean {
	private static final Logger log = Logger.getLogger(ReporteContabilidadSumasYSaldosBean.class);
	private final static String PATHDATASOURCEXML = "/webapps";
	private final static String PATH2DATASOURCEXML = "/webapps";
	private Date fechaDesde;
	private Date fechaHasta;
	private Date fechaInicioAux;
	private Date fechaCierreAux;
	private List ejercicios;
	private List ejerciciosSelectItem; // todos los ejercicios como select item;

	// objetos para trabajar con el select item de ejercicios
	private Long idEjercicioSeleccionado;
	private HtmlSelectOneMenu idEjercicioSeleccionadoItem;
	Ejercicio ejerActual;

	FileReader fr = null;
	FileWriter fw = null;
	BufferedReader br = null;
	// map para guardar info de los nodos del arbol plan cuenta
	private Map nodeTreeInfo = new HashMap();
	// map para guardar las tabulaciones q definen el nivel de un nodo
	private Map tab = new HashMap();

	// Propiedad para ejecutar el popup del reporte.
	private String popupReport = new String("");

	TreeNode treeData;

	HashMap hijosNodo;

	private boolean banderaFechasInicio = false; // truco para lograr que cambie las fechas de los ejercicios.!!!
	private boolean banderaFechasCierre = false;


	public ReporteContabilidadSumasYSaldosBean() {
		super();
		fechaDesde = null;
		fechaHasta = null;
	}


	public Date getFechaDesde() {
		return fechaDesde;
	}


	public void setFechaDesde(Date fechaDesde) {
		if (!banderaFechasInicio) {
			this.fechaDesde = fechaDesde;
		} else {
			banderaFechasInicio = false;
		}
	}


	public Date getFechaHasta() {
		return fechaHasta;
	}


	public void setFechaHasta(Date fechaHasta) {
		if (!banderaFechasCierre) {
			this.fechaHasta = fechaHasta;
		} else {
			banderaFechasCierre = false;
		}
	}


	public String getPopupReport() {
		return popupReport;
	}


	public void setPopupReport(String popupReport) {
		this.popupReport = popupReport;
	}


	public void borrar() {
		fechaHasta = new Timestamp(new java.util.Date().getTime());
		Calendar fecha = Calendar.getInstance();
		Date date = new Date(fechaHasta.getTime());
		fecha.setTime(date);
		fecha.add(Calendar.YEAR, -1);
		fechaDesde = new Timestamp(fecha.getTime().getTime());
		popupReport = new String("");
		tituloLargo = "";
		tituloCorto = "";
		error.borrar();
	}


	public String inicializar() {
		borrar();
		tituloLargo = "CONTABILIDAD";
		tituloCorto = "Reporte Sumas y Saldos";
		tab.put("0", ReporteContabilidadPlanCuentasBean.TAB0);
		tab.put("1", ReporteContabilidadPlanCuentasBean.TAB1);
		tab.put("2", ReporteContabilidadPlanCuentasBean.TAB2);
		tab.put("3", ReporteContabilidadPlanCuentasBean.TAB3);
		tab.put("4", ReporteContabilidadPlanCuentasBean.TAB4);
		tab.put("5", ReporteContabilidadPlanCuentasBean.TAB5);
		tab.put("6", ReporteContabilidadPlanCuentasBean.TAB6);
		tab.put("7", ReporteContabilidadPlanCuentasBean.TAB7);

		ejercicios = new ArrayList();
		ejerciciosSelectItem = new ArrayList();
		Filtro filtro = new Filtro();
		try {
			ejercicios = contabilidadService.getEjercicioService().getEjercicio(filtro);
			Iterator iterDeEjerciciosAuxiliares = ejercicios.iterator();
			while (iterDeEjerciciosAuxiliares.hasNext()) {
				Ejercicio ejer = (Ejercicio) iterDeEjerciciosAuxiliares.next();
				ejer.getIdEjercicio();
				ejer.getFechaCierre();
				ejer.getFechaInicio();
				ejer.getFechaPeriodo();
				ejer.getEstado();
				ejer.getSucursalFiel();
			}
		} catch (EjercicioException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Iterator i = ejercicios.iterator();
		ejerciciosSelectItem.add(new SelectItem(new Long(0), "Seleccione un ejercicio"));
		while (i.hasNext()) {
			Ejercicio ej = (Ejercicio) i.next();
			ejerciciosSelectItem.add(new SelectItem(new Long(ej.getIdEjercicio().intValue()), "Ejercicio: " + ej.getIdEjercicio()
					+ " | Fecha Inicio: " + ej.getFechaInicio() + " | Fecha Cierre: " + ej.getFechaCierre()));
			if (ej.getActual().compareTo("S") == 0) {
				idEjercicioSeleccionado = new Long(ej.getIdEjercicio().longValue());
				idEjercicioSeleccionadoItem = new HtmlSelectOneMenu();
				idEjercicioSeleccionadoItem.setValue(idEjercicioSeleccionado);
				ejerActual = ej;
			}
		}
		if (ejerActual != null) {
			fechaInicioAux = ejerActual.getFechaInicio();
			fechaCierreAux = ejerActual.getFechaCierre();
			fechaDesde = new Date(fechaInicioAux.getTime());
			fechaHasta = new Date(fechaCierreAux.getTime());
		}

		return "sumasYSaldos";
	}


	public boolean validar() {
		error.borrar();
		return false;
	}


	public boolean validarFecha() {
		error.borrar();

		if (Validador.esNulo(getFechaDesde()) || getFechaDesde().equals(new Date(0))
				|| Validador.esNulo(getFechaHasta()) || getFechaHasta().equals(new Date(0))) {
			error.agregar(Error.COMPROBANTE_FECHA_REQUERIDA);
		} else {
			if (getFechaDesde().after(getFechaHasta())) {
				error.agregar("La fecha desde no puede ser mayor a la fecha hasta");
			}
		}
		return (error.cantidad() == 0) ? true : false;
	}


	public boolean validarFechaHasta() {
		error.borrar();

		if (Validador.esNulo(getFechaHasta()) || getFechaHasta().equals(new Date(0))) {
			error.agregar(Error.COMPROBANTE_FECHA_HASTA_REQUERIDA);
		}
		return (error.cantidad() == 0) ? true : false;
	}


	public String generar() {

		if (ejerActual != null || validarFecha()) {

			inicializarArbol();
			// //////////armamos el xmldatasource
			String key;
			key = "catalina.home";
			key = System.getProperty(key);

			Enumeration sumasYSaldosAux = treeData.nodes();
			TreeNode raiz = null;
			// buscamos el nodo raiz
			while (sumasYSaldosAux.hasMoreElements()) {
				raiz = (TreeNode) sumasYSaldosAux.nextElement();
				break;
			}

			// hacemos una instancia de SaldoNodo que calcula recursivamente los saldo de cada nodo
			SaldosNodoSumasYSaldos saldosNodo = new SaldosNodoSumasYSaldos(raiz);
			saldosNodo.getSaldo();

			// aca empezamos a armar el xml que sera usado como datasource en el reporte
			Element root = new Element("sumasYSaldos");

			Element nodo;
			Element denominancion;
			Element debeInicial;
			Element haberInicial;
			Element debePeriodo;
			Element haberPeriodo;
			Enumeration sumasYSaldos = treeData.values();
			while (sumasYSaldos.hasMoreElements()) {
				SumasYSaldos sumYSal = (SumasYSaldos) sumasYSaldos.nextElement();
				// if(!(balance.getId().toString().equals(PlanCuentaBean.IDNODORAIZ))){
				if (sumYSal.isMostrar()) {
					nodo = new Element("nodo");
					nodo.setAttribute(new Attribute("nroImputa", sumYSal.getNroImputa()));
					denominancion = new Element("denominacion");
					denominancion.setText(sumYSal.getTitulo());
					debeInicial = new Element("debeInicial");
					debeInicial.setText(sumYSal.getDebeInicial().toString());
					haberInicial = new Element("haberInicial");
					haberInicial.setText(sumYSal.getHaberInicial().toString());
					debePeriodo = new Element("debePeriodo");
					debePeriodo.setText(sumYSal.getDebePeriodo().toString());
					haberPeriodo = new Element("haberPeriodo");
					haberPeriodo.setText(sumYSal.getHaberPeriodo().toString());
					nodo.addContent(denominancion);
					nodo.addContent(debeInicial);
					nodo.addContent(haberInicial);
					nodo.addContent(debePeriodo);
					nodo.addContent(haberPeriodo);
					root.addContent(nodo);
				}
				// }
			}

			Document doc = new Document(root);// Creamos el documento
			String usuario = Session.getOperador().getUsername();
			try {
				XMLOutputter out = new XMLOutputter();
				XMLOutputter outDos = new XMLOutputter();
				File fiUno = new File(key + PATHDATASOURCEXML);
				if (fiUno.exists()) {
					FileOutputStream file = new FileOutputStream(key + PATHDATASOURCEXML + "/reporteSumasYSaldos" + usuario + ".xml");
					out.output(doc, file);
					file.flush();
					file.close();
				}
				File fiDos = new File(key + PATH2DATASOURCEXML);
				if (fiDos.exists()) {
					FileOutputStream file2 = new FileOutputStream(key + PATH2DATASOURCEXML + "/reporteSumasYSaldos" + usuario + ".xml");
					outDos.output(doc, file2);
					file2.flush();
					file2.close();
				}

				out.output(doc, System.out);
			} catch (Exception e) {
				e.printStackTrace();
			}

			// ///////////////

			// cambiar esto dpues!!!!!!!
			String url = PATHDATASOURCEXML + "/reporteSumasYSaldos" + usuario + ".xml";

			HttpServletRequest request = Session.getRequest();

			error.borrar();
			popupReport = new String("");
			Integer inte = new Integer(1);
			Format dateFormat = new SimpleDateFormat("yyyy-MM-dd");

			String p1 = "?ejercicio=" + (Long) idEjercicioSeleccionadoItem.getValue();
			String p2 = "&fecha_desde=" + dateFormat.format(fechaDesde);
			String p3 = "&fecha_hasta=" + dateFormat.format(fechaHasta);
			String p4 = "&id_sucursal" + inte;
			String p5 = "&URLImagen=" + Session.getHomePath() + "/img/fiel/logo_fiel.jpg";
			String p6 = "&JRXmlDataSource=" + url;
			String p7 = "&xpath2=/sumasYSaldos/nodo";

			String page = request.getContextPath() + "/report/ContabilidadSumasYSaldos.jrxml";
			popupReport = "popup('" + page + p1 + p2 + p3 + p4 + p5 + p6 + p7 + "',1000,600)";

			log.info(popupReport);
		} else {
			error.agregar("Error en el rango de fechas.");
			System.out.println("Error en el rango de fechas.");
			return null;
		}
		return null;
	}


	public String generarAExcel() {

		if (ejerActual != null || validarFecha()) {

			inicializarArbol();
			// //////////armamos el xmldatasource
			String key;
			key = "catalina.home";
			key = System.getProperty(key);

			Enumeration sumasYSaldosAux = treeData.nodes();
			TreeNode raiz = null;
			// buscamos el nodo raiz
			while (sumasYSaldosAux.hasMoreElements()) {
				raiz = (TreeNode) sumasYSaldosAux.nextElement();
				break;
			}

			// hacemos una instancia de SaldoNodo que calcula recursivamente los saldo de cada nodo
			SaldosNodoSumasYSaldos saldosNodo = new SaldosNodoSumasYSaldos(raiz);
			saldosNodo.getSaldo();

			// aca empezamos a armar el xml que sera usado como datasource en el reporte
			Element root = new Element("sumasYSaldos");

			Element nodo;
			Element denominancion;
			Element debeInicial;
			Element haberInicial;
			Element debePeriodo;
			Element haberPeriodo;
			Enumeration sumasYSaldos = treeData.values();
			while (sumasYSaldos.hasMoreElements()) {
				SumasYSaldos sumYSal = (SumasYSaldos) sumasYSaldos.nextElement();
				// if(!(balance.getId().toString().equals(PlanCuentaBean.IDNODORAIZ))){
				if (sumYSal.isMostrar()) {
					nodo = new Element("nodo");
					nodo.setAttribute(new Attribute("nroImputa", sumYSal.getNroImputa()));
					denominancion = new Element("denominacion");
					denominancion.setText(tab.get(String.valueOf(sumYSal.getNivel())) + sumYSal.getTitulo());
					debeInicial = new Element("debeInicial");
					debeInicial.setText(sumYSal.getDebeInicial().toString());
					haberInicial = new Element("haberInicial");
					haberInicial.setText(sumYSal.getHaberInicial().toString());
					debePeriodo = new Element("debePeriodo");
					debePeriodo.setText(sumYSal.getDebePeriodo().toString());
					haberPeriodo = new Element("haberPeriodo");
					haberPeriodo.setText(sumYSal.getHaberPeriodo().toString());
					nodo.addContent(denominancion);
					nodo.addContent(debeInicial);
					nodo.addContent(haberInicial);
					nodo.addContent(debePeriodo);
					nodo.addContent(haberPeriodo);
					root.addContent(nodo);
				}
				// }
			}

			Document doc = new Document(root);// Creamos el documento
			String usuario = Session.getOperador().getUsername();
			try {
				XMLOutputter out = new XMLOutputter();
				XMLOutputter outDos = new XMLOutputter();
				File fiUno = new File(key + PATHDATASOURCEXML);
				if (fiUno.exists()) {
					FileOutputStream file = new FileOutputStream(key + PATHDATASOURCEXML + "/reporteSumasYSaldos" + usuario + ".xml");
					out.output(doc, file);
					file.flush();
					file.close();
				}
				File fiDos = new File(key + PATH2DATASOURCEXML);
				if (fiDos.exists()) {
					FileOutputStream file2 = new FileOutputStream(key + PATH2DATASOURCEXML + "/reporteSumasYSaldos" + usuario + ".xml");
					outDos.output(doc, file2);
					file2.flush();
					file2.close();
				}

				out.output(doc, System.out);
			} catch (Exception e) {
				e.printStackTrace();
			}

			// ///////////////

			// cambiar esto dpues!!!!!!!
			String url = PATHDATASOURCEXML + "/reporteSumasYSaldos" + usuario + ".xml";

			HttpServletRequest request = Session.getRequest();

			error.borrar();
			popupReport = new String("");
			Integer inte = new Integer(1);
			Format dateFormat = new SimpleDateFormat("yyyy-MM-dd");

			String p1 = "?ejercicio=" + (Long) idEjercicioSeleccionadoItem.getValue();
			String p2 = "&fecha_desde=" + dateFormat.format(fechaDesde);
			String p3 = "&fecha_hasta=" + dateFormat.format(fechaHasta);
			String p4 = "&id_sucursal" + inte;
			String p5 = "&URLImagen=" + Session.getHomePath() + "/img/fiel/logo_fiel.jpg";
			String p6 = "&JRXmlDataSource=" + url;
			String p7 = "&xpath2=/sumasYSaldos/nodo";
			String p8 = "&AExcel=excel";
			String page = request.getContextPath() + "/report/ContabilidadSumasYSaldosExcel.jrxml";
			popupReport = "popup('" + page + p1 + p2 + p3 + p4 + p5 + p6 + p7 + p8 + "',1000,600)";

			log.info(popupReport);
		} else {
			error.agregar("Error en el rango de fechas.");
			System.out.println("Error en el rango de fechas.");
			return null;
		}
		return null;
	}


	public List getEjercicios() {
		return ejercicios;
	}


	public void setEjercicios(List ejercicios) {
		this.ejercicios = ejercicios;
	}


	public List getEjerciciosSelectItem() {
		return ejerciciosSelectItem;
	}


	public void setEjerciciosSelectItem(List ejerciciosSelectItem) {
		this.ejerciciosSelectItem = ejerciciosSelectItem;
	}


	public Long getIdEjercicioSeleccionado() {
		return idEjercicioSeleccionado;
	}


	public void setIdEjercicioSeleccionado(Long idEjercicioSeleccionado) {
		this.idEjercicioSeleccionado = idEjercicioSeleccionado;
	}


	public HtmlSelectOneMenu getIdEjercicioSeleccionadoItem() {
		return idEjercicioSeleccionadoItem;
	}


	public void setIdEjercicioSeleccionadoItem(
			HtmlSelectOneMenu idEjercicioSeleccionadoItem) {
		this.idEjercicioSeleccionadoItem = idEjercicioSeleccionadoItem;
	}


	private void inicializarArbol() {

		nodeTreeInfo = new HashMap();

		// traemos los saldos de las hojas para el ejercicio seleccionado y lo guardamos en map
		try {

			Integer idEjercicico = new Integer(((Long) idEjercicioSeleccionadoItem.getValue()).toString());
			Ejercicio ejer = null;
			try {
				ejer = new Ejercicio(idEjercicico, new Integer(1), fechaInicioAux, new Date(), new Date(), "E", "", "");
			} catch (EjercicioCreateException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			List sumasYSaldos = contabilidadService.getBalanceService().getBalanceConsultaManualSumasYSaldos(ejer, fechaDesde, fechaHasta);

			Iterator iter = sumasYSaldos.iterator();
			while (iter.hasNext()) {
				SumasYSaldos element = (SumasYSaldos) iter.next();
				nodeTreeInfo.put(element.getNroImputa(), element);
			}

		} catch (BalanceException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		hijosNodo = new HashMap();
		List listaHijos = new ArrayList();
		try {
			List nodos = contabilidadService.getPlanCuentaDosService().getPlanCuentaSimple(new Filtro());
			Iterator it = nodos.iterator();
			PlanCuentaSimple p = new PlanCuentaSimple();
			while (it.hasNext()) {
				PlanCuentaSimple element = (PlanCuentaSimple) it.next();
				if (hijosNodo.get(element.getIdPadre()) == null) {
					listaHijos = new ArrayList();
					listaHijos.add(element);
					hijosNodo.put(element.getIdPadre(), listaHijos);
				}
				else
				{
					listaHijos = (List) hijosNodo.get(element.getIdPadre());
					listaHijos.add(element);
					hijosNodo.put(element.getIdPadre(), listaHijos);
				}
			}
			/*
			 * List lista1 = (List)hijosNodo.get(new Long(2)); Iterator iter = lista1.iterator(); while (iter.hasNext()) { PlanCuentaDos element =
			 * (PlanCuentaDos) iter.next(); System.out.println(element.getTitulo()); }
			 */

		} catch (PlanCuentaDosException e1) {

			e1.printStackTrace();
		}
		Filtro filtro = new Filtro();
		filtro.agregarCampoOperValor("idPadre", Filtro.IGUAL, new Long(PlanCuentaBean.IDNODORAIZ));
		List nodosRaizList;
		treeData = null;
		try {
			nodosRaizList = contabilidadService.getPlanCuentaDosService().getPlanCuentaSimple(filtro);
			Iterator it = nodosRaizList.iterator();
			while (it.hasNext()) {
				PlanCuentaSimple element = (PlanCuentaSimple) it.next();
				treeData = asignarHijo(element);

			}
		} catch (PlanCuentaDosException e) {
			e.printStackTrace();
		}

	}


	/* ESTE METODO ES RECURSIVO PARA CREAR LOS NODOS */
	public TreeNode asignarHijo(PlanCuentaSimple padre) {
		List hijosP = new ArrayList();
		// hijosP = getHijos(padre);
		hijosP = (List) hijosNodo.get(padre.getId());
		Iterator iter = hijosP.iterator();
		// TreeNode nodePadre=null;
		int level = 1;
		// if(padre.getIdPadre().equals(PlanCuentaBean.IDNODORAIZ))
		// { //nivelactual= 0;
		TreeNode nodePadre = new TreeNode(new SumasYSaldos(String.valueOf(padre.getIdPlanCuenta()), padre.getTitulo(), new Double(0), new Double(0),
				new Double(0), new Double(0), 0, false));
		// }
		TreeNode nodeHijo;
		String key = "";
		while (iter.hasNext()) {
			PlanCuentaSimple hijo = (PlanCuentaSimple) iter.next();
			key = String.valueOf(hijo.getIdPlanCuenta().longValue());// clave para determinar si el nodo es una hoja y esta dentro de los resultados
																		// de los filtros aplic al ejerc seleccionado
			if (nodeTreeInfo.get(key) == null)
				nodeHijo = new TreeNode(new SumasYSaldos(String.valueOf(hijo.getIdPlanCuenta()), hijo.getTitulo(), new Double(0), new Double(0),
						new Double(0), new Double(0), 1, false));
			else
				nodeHijo = new TreeNode(new SumasYSaldos(String.valueOf(hijo.getIdPlanCuenta()), hijo.getTitulo(),
						((SumasYSaldos) nodeTreeInfo.get(key)).getDebeInicial(), ((SumasYSaldos) nodeTreeInfo.get(key)).getHaberInicial(),
						((SumasYSaldos) nodeTreeInfo.get(key)).getHaberPeriodo(), ((SumasYSaldos) nodeTreeInfo.get(key)).getDebePeriodo(), 1, true));
			nodePadre.addChild(nodeHijo);
			// nodePadre.getChildren().add(nodeHijo);
			asignarHijo(nodeHijo, hijo, level);
		}
		return nodePadre;
	}


	public TreeNode asignarHijo(TreeNode nodePadre, PlanCuentaSimple padre, int level) {

		List hijosP = new ArrayList();

		hijosP = (List) hijosNodo.get(padre.getId());
		if (hijosP != null) {
			Iterator iter = hijosP.iterator();
			TreeNode nodeHijo;
			String key = "";

			while (iter.hasNext()) {
				PlanCuentaSimple hijo = (PlanCuentaSimple) iter.next();
				key = String.valueOf(hijo.getIdPlanCuenta().longValue());
				if (nodeTreeInfo.get(key) == null)
					nodeHijo = new TreeNode(new SumasYSaldos(String.valueOf(hijo.getIdPlanCuenta()), hijo.getTitulo(), new Double(0), new Double(0),
							new Double(0), new Double(0), level + 1, false));
				else
					nodeHijo = new TreeNode(new SumasYSaldos(String.valueOf(hijo.getIdPlanCuenta()), hijo.getTitulo(),
							((SumasYSaldos) nodeTreeInfo.get(key)).getDebeInicial(), ((SumasYSaldos) nodeTreeInfo.get(key)).getHaberInicial(),
							((SumasYSaldos) nodeTreeInfo.get(key)).getHaberPeriodo(), ((SumasYSaldos) nodeTreeInfo.get(key)).getDebePeriodo(),
							level + 1, true));
				nodePadre.addChild(nodeHijo);
				// nodePadre.getChildren().add(nodeHijo);

				asignarHijo(nodeHijo, hijo, ((SumasYSaldos) nodeHijo.getValue()).getNivel());
			}
		}

		return nodePadre;
	}


	public List getHijos(PlanCuentaSimple padre) {
		List aux = new ArrayList();
		List hijos = new ArrayList();

		Filtro filtro = new Filtro();
		filtro.agregarCampoOperValor("idPadre", Filtro.IGUAL, padre.getIdPlanCuenta());
		try {
			aux = contabilidadService.getPlanCuentaDosService().getPlanCuentaSimple(filtro);
			Iterator iter;
			iter = aux.iterator();
			while (iter.hasNext()) {
				PlanCuentaSimple planCuenta = (PlanCuentaSimple) iter.next();
				// if (planCuenta.getEstado().equals("H"))
				hijos.add(planCuenta);
			}
		} catch (PlanCuentaDosException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return hijos;
		// List lista = (List)hijosNodo.get((padre.getId()));
		// return lista;//(List)hijosNodo.get((padre.getId()));

	}


	public List getHijos(Long idParent) {
		List aux = new ArrayList();
		List hijos = new ArrayList();

		Iterator it;
		Filtro filtro = new Filtro();
		filtro.agregarCampoOperValor("idPadre", Filtro.IGUAL, idParent);
		try {
			aux = contabilidadService.getPlanCuentaDosService().getPlanCuenta(filtro);
			Iterator iter;
			iter = aux.iterator();
			while (iter.hasNext()) {
				PlanCuentaDos planCuenta = (PlanCuentaDos) iter.next();
				if (planCuenta.getEstado().equals("H"))
					// TODO crear uso en la bd y filtrar por uso
					// if(planCuenta.getUso()!=null && planCuenta.getUso().compareTo())
					hijos.add(planCuenta);
			}
		} catch (PlanCuentaDosException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return hijos;
	}


	public void acomodarFechas(ValueChangeEvent e) {
		popupReport = "";
		idEjercicioSeleccionado = (Long) idEjercicioSeleccionadoItem.getValue();
		Iterator iter = ejercicios.iterator();
		while (iter.hasNext()) {
			Ejercicio ej = (Ejercicio) iter.next();
			if (ej.getIdEjercicio().intValue() == idEjercicioSeleccionado.intValue()) {
				log.info("El ejercicion actual es el " + ej.getIdEjercicio());
				ejerActual = ej;
				break;
			}
		}
		if (ejerActual != null) {
			banderaFechasInicio = true;
			banderaFechasCierre = true;
			fechaInicioAux = ejerActual.getFechaInicio();
			fechaCierreAux = ejerActual.getFechaCierre();
			fechaDesde = new Date(fechaInicioAux.getTime());
			fechaHasta = new Date(fechaCierreAux.getTime());
		} else {
			log.info("el ejercicio es nulo");
		}
	}

}
