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
import javax.faces.event.ActionEvent;
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
import com.bizitglobal.tarjetafiel.contabilidad.negocio.Balance;
import com.bizitglobal.tarjetafiel.contabilidad.negocio.Ejercicio;
import com.bizitglobal.tarjetafiel.contabilidad.negocio.PlanCuentaDos;
import com.bizitglobal.tarjetafiel.contabilidad.negocio.PlanCuentaSimple;
import com.bizitglobal.webapp.faces.beans.BaseBean;
import com.bizitglobal.webapp.faces.util.Error;
import com.bizitglobal.webapp.faces.util.Session;
import com.bizitglobal.webapp.faces.util.Validador;


@SuppressWarnings({ "rawtypes", "unchecked", "unused" })
public class ReporteContabilidadBalanceBean extends BaseBean {
	private static final Logger log = Logger.getLogger(ReporteContabilidadBalanceBean.class);
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

	List vector;
	List listaImputables;
	List listaSumarisadas;
	List listaNodosSumarizados;
	List listaNodosP;

	List vectorAux;

	HashMap hijosNodo;

	private boolean banderaFechasInicio = false; // truco para lograr que cambie las fechas de los ejercicios.!!!
	private boolean banderaFechasCierre = false;


	public ReporteContabilidadBalanceBean() {
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
		fechaDesde = new Date(fecha.getTime().getTime());
		popupReport = new String("");
		tituloLargo = "";
		tituloCorto = "";
		error.borrar();
	}


	public String inicializar() {
		borrar();
		tituloLargo = "CONTABILIDAD";
		tituloCorto = "Reporte Balance";
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
			fechaDesde = fechaInicioAux;
			fechaHasta = fechaCierreAux;
		}
		return "balance";
	}


	public boolean validar() {
		error.borrar();
		return false;
	}


	public boolean validarFecha() {
		error.borrar();
		log.info("Validar fecha");
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


	public String generarArcXml(ActionEvent event) {

		try {
			List balances = contabilidadService.getBalanceService().getBalanceConsultaManual(new Ejercicio(), new Date(), new Date());

			Iterator iter = balances.iterator();
			while (iter.hasNext()) {
				Balance element = (Balance) iter.next();
				nodeTreeInfo.put(element.getNroImputa(), element);
				System.out.println(element.getTitulo());
			}

		} catch (BalanceException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		// armarArbol();
		inicializarArbol();

		/*
		 * List planCuentas; try { //Filtro fil=new Filtro(); planCuentas = contabilidadService.getPlanCuentaDosService().getPlanCuenta(new Filtro());
		 * 
		 * Iterator iter=planCuentas.iterator(); while (iter.hasNext()) { PlanCuentaDos element = (PlanCuentaDos) iter.next();
		 * nodeTreeInfo.put(element.getIdPlanCuenta(),element); //String valor =
		 * ((PlanCuentaDos)nodeTreeInfo.get(element.getIdPlanCuenta())).getTitulo(); //System.out.println("valor "+ valor); }
		 * System.out.println("titulo  "+ ((PlanCuentaDos)nodeTreeInfo.get(new Long (1))).getTitulo());
		 * 
		 * 
		 * TreeNode raiz = new TreeNode(nodeTreeInfo.get(new Long(0))); Filtro f = new Filtro(); f.agregarCampoOperValor("idPadre",
		 * Filtro.MAYOR_IGUAL, "-1"); f.agregarCampoOperValor("idPadre", Filtro.MENOR_IGUAL, "3"); f.agregarOrderBy("idPadre desc");
		 * 
		 * 
		 * List hijos= new ArrayList(); System.out.println(f.getHQL()); boolean flag = true; Long idPadreAnterior=new Long(-2); List planCuentas2=
		 * contabilidadService.getPlanCuentaDosService().getPlanCuenta(f); TreeNode nodoHijo; TreeNode nodoPadre=null; //TreeNode aux[]= null; Vector
		 * aux= new Vector(); TreeNode auxiliar= null;
		 * 
		 * int i=0;
		 * 
		 * Iterator iterator = planCuentas2.iterator(); while (iterator.hasNext()) { PlanCuentaDos element = (PlanCuentaDos) iterator.next();
		 * if((element.getIdPadre().longValue()!=-1)){//si no es el nodo raiz if(flag){ idPadreAnterior= element.getIdPadre(); flag =false; }
		 * //if(element.getIdPadre().longValue()!=0) { /////si no es hijo del nodo raiz if(idPadreAnterior.longValue()
		 * ==element.getIdPadre().longValue()) hijos.add(nodeTreeInfo.get(element.getId())); else { //creo un padre con el id del hijo nodoPadre = new
		 * TreeNode(nodeTreeInfo.get(element.getIdPadre())); Iterator it= hijos.iterator(); while (it.hasNext()) { PlanCuentaDos elem =
		 * (PlanCuentaDos) it.next(); nodoHijo = new TreeNode(elem); nodoPadre.addChild(nodoHijo); } hijos.clear();
		 * hijos.add(nodeTreeInfo.get(element.getIdPadre())); idPadreAnterior=element.getIdPadre(); } } else aux.add(auxiliar);
		 * 
		 * }
		 * 
		 * }
		 * 
		 * // for(int j =0; j<aux.size();j++) // raiz.addChild(aux.get(j)); //asignamos el nodo como hijo de raiz
		 * 
		 * Enumeration todo= raiz.nodes(); while (todo.hasMoreElements()) { String element = todo.nextElement().toString();
		 * System.out.println(element);
		 * 
		 * }
		 * 
		 * 
		 * 
		 * 
		 * /////armar hojas////////////
		 * 
		 * 
		 * //////armar ascencencia
		 * 
		 * ////////// sumar horas///////////
		 * 
		 * 
		 * } catch (PlanCuentaDosException e) { // TODO Auto-generated catch block e.printStackTrace(); }
		 */

		return null;
	}


	public String generar() {

		if (ejerActual != null || validarFecha()) {

			inicializarArbol();
			// //////////armamos el xmldatasource
			String key;
			key = "catalina.home";
			key = System.getProperty(key);

			Enumeration balancesAux = treeData.nodes();
			TreeNode raiz = null;
			// buscamos el nodo raiz
			while (balancesAux.hasMoreElements()) {
				raiz = (TreeNode) balancesAux.nextElement();
				break;
			}

			// hacemos una instancia de SaldoNodo que calcula recursivamente los saldo de cada nodo
			SaldosNodo saldosNodo = new SaldosNodo(raiz);
			saldosNodo.getSaldo();

			// aca empezamos a armar el xml que sera usado como datasource en el reporte
			Element root = new Element("balance");

			Element nodo;
			Element denominancion;
			Element saldoInicial;
			Element saldoPeriodo;
			Element saldo;
			Enumeration balances = treeData.values();
			while (balances.hasMoreElements()) {
				Balance balance = (Balance) balances.nextElement();
				// if(!(balance.getId().toString().equals(PlanCuentaBean.IDNODORAIZ))){
				if (balance.isMostrar()) {
					nodo = new Element("nodo");
					nodo.setAttribute(new Attribute("nroImputa", balance.getNroImputa()));
					denominancion = new Element("denominacion");
					denominancion.setText(tab.get(String.valueOf(balance.getNivel())) + balance.getTitulo());
					saldoInicial = new Element("saldoInicial");
					saldoInicial.setText(balance.getSaldoInicial().toString());
					saldoPeriodo = new Element("saldoPeriodo");
					saldoPeriodo.setText(balance.getSaldoPeriodo().toString());
					saldo = new Element("saldo");
					saldo.setText(String.valueOf(balance.getSaldoInicial().doubleValue() + balance.getSaldoPeriodo().doubleValue()));
					nodo.addContent(denominancion);
					nodo.addContent(saldoInicial);
					nodo.addContent(saldoPeriodo);
					nodo.addContent(saldo);
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
					FileOutputStream file = new FileOutputStream(key + PATHDATASOURCEXML + "/reporteBalance" + usuario + ".xml");
					out.output(doc, file);
					file.flush();
					file.close();
				}
				File fiDos = new File(key + PATH2DATASOURCEXML);
				if (fiDos.exists()) {
					FileOutputStream file2 = new FileOutputStream(key + PATH2DATASOURCEXML + "/reporteBalance" + usuario + ".xml");
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
			String url = PATHDATASOURCEXML + "/reporteBalance" + usuario + ".xml";

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
			String p7 = "&xpath2=/balance/nodo";

			String page = request.getContextPath() + "/report/ContabilidadBalance.jrxml";
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

			Enumeration balancesAux = treeData.nodes();
			TreeNode raiz = null;
			// buscamos el nodo raiz
			while (balancesAux.hasMoreElements()) {
				raiz = (TreeNode) balancesAux.nextElement();
				break;
			}

			// hacemos una instancia de SaldoNodo que calcula recursivamente los saldo de cada nodo
			SaldosNodo saldosNodo = new SaldosNodo(raiz);
			saldosNodo.getSaldo();

			// aca empezamos a armar el xml que sera usado como datasource en el reporte
			Element root = new Element("balance");

			Element nodo;
			Element denominancion;
			Element saldoInicial;
			Element saldoPeriodo;
			Element saldo;
			Enumeration balances = treeData.values();
			while (balances.hasMoreElements()) {
				Balance balance = (Balance) balances.nextElement();
				// if(!(balance.getId().toString().equals(PlanCuentaBean.IDNODORAIZ))){
				if (balance.isMostrar()) {
					nodo = new Element("nodo");
					nodo.setAttribute(new Attribute("nroImputa", balance.getNroImputa()));
					denominancion = new Element("denominacion");
					denominancion.setText(tab.get(String.valueOf(balance.getNivel())) + balance.getTitulo());
					saldoInicial = new Element("saldoInicial");
					saldoInicial.setText(balance.getSaldoInicial().toString());
					saldoPeriodo = new Element("saldoPeriodo");
					saldoPeriodo.setText(balance.getSaldoPeriodo().toString());
					saldo = new Element("saldo");
					saldo.setText(String.valueOf(balance.getSaldoInicial().doubleValue() + balance.getSaldoPeriodo().doubleValue()));
					nodo.addContent(denominancion);
					nodo.addContent(saldoInicial);
					nodo.addContent(saldoPeriodo);
					nodo.addContent(saldo);
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
					FileOutputStream file = new FileOutputStream(key + PATHDATASOURCEXML + "/reporteBalance" + usuario + ".xml");
					out.output(doc, file);
					file.flush();
					file.close();
				}
				File fiDos = new File(key + PATH2DATASOURCEXML);
				if (fiDos.exists()) {
					FileOutputStream file2 = new FileOutputStream(key + PATH2DATASOURCEXML + "/reporteBalance" + usuario + ".xml");
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
			String url = PATHDATASOURCEXML + "/reporteBalance" + usuario + ".xml";

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
			String p7 = "&xpath2=/balance/nodo";
			String p8 = "&AExcel=excel";

			String page = request.getContextPath() + "/report/ContabilidadBalanceExcel.jrxml";
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

	public class WrapperBalance {

		private String nroImputa;
		private String denominacion;
		private String saldoInicial;
		private String saldoPeriodo;
		private String saldo;


		public WrapperBalance(String nroImputa, String denominacion, String saldoInicial, String saldoPeriodo, String saldo) {

			this.nroImputa = nroImputa;
			this.denominacion = denominacion;
			this.saldoInicial = saldoInicial;
			this.saldoPeriodo = saldoPeriodo;
			this.saldo = saldo;

		}


		public String getDenominacion() {
			return denominacion;
		}


		public void setDenominacion(String denominacion) {
			this.denominacion = denominacion;
		}


		public String getNroImputa() {
			return nroImputa;
		}


		public void setNroImputa(String nroImputa) {
			this.nroImputa = nroImputa;
		}


		public String getSaldo() {
			return saldo;
		}


		public void setSaldo(String saldo) {
			this.saldo = saldo;
		}


		public String getSaldoInicial() {
			return saldoInicial;
		}


		public void setSaldoInicial(String saldoInicial) {
			this.saldoInicial = saldoInicial;
		}


		public String getSaldoPeriodo() {
			return saldoPeriodo;
		}


		public void setSaldoPeriodo(String saldoPeriodo) {
			this.saldoPeriodo = saldoPeriodo;
		}

	}


	private void inicializarArbol() {
		// traemos los saldos de las hojas para el ejercicio seleccionado y lo guardamos en map

		nodeTreeInfo = new HashMap();

		try {
			Integer idEjercicico = new Integer(((Long) idEjercicioSeleccionadoItem.getValue()).toString());
			Ejercicio ejer = null;
			try {
				ejer = new Ejercicio(idEjercicico, new Integer(1), fechaInicioAux, new Date(), new Date(), "E", "", "");
			} catch (EjercicioCreateException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			List balances = contabilidadService.getBalanceService().getBalanceConsultaManual(ejer, fechaDesde, fechaHasta);

			Iterator iter = balances.iterator();
			while (iter.hasNext()) {
				Balance element = (Balance) iter.next();
				// element.set
				element.setMostrar(true);
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

		// Balance
		TreeNode nodePadre = new TreeNode(new Balance(String.valueOf(padre.getIdPlanCuenta()), padre.getTitulo(), new Double(0), new Double(0), 0,
				false));
		// }
		TreeNode nodeHijo;
		String key = "";
		while (iter.hasNext()) {
			PlanCuentaSimple hijo = (PlanCuentaSimple) iter.next();
			key = String.valueOf(hijo.getIdPlanCuenta().longValue());// clave para determinar si el nodo es una hoja y esta dentro de los resultados
																		// de los filtros aplic al ejerc seleccionado
			if (nodeTreeInfo.get(key) == null)
				nodeHijo = new TreeNode(new Balance(String.valueOf(hijo.getIdPlanCuenta()), hijo.getTitulo(), new Double(0), new Double(0), 1, false));
			else
				nodeHijo = new TreeNode(new Balance(String.valueOf(hijo.getIdPlanCuenta()), hijo.getTitulo(),
						((Balance) nodeTreeInfo.get(key)).getSaldoInicial(), ((Balance) nodeTreeInfo.get(key)).getSaldoPeriodo(), 1, true));
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
					nodeHijo = new TreeNode(new Balance(String.valueOf(hijo.getIdPlanCuenta()), hijo.getTitulo(), new Double(0), new Double(0),
							level + 1, false));
				else
					nodeHijo = new TreeNode(
							new Balance(String.valueOf(hijo.getIdPlanCuenta()), hijo.getTitulo(),
									((Balance) nodeTreeInfo.get(key)).getSaldoInicial(), ((Balance) nodeTreeInfo.get(key)).getSaldoPeriodo(),
									level + 1, true));
				nodePadre.addChild(nodeHijo);
				// nodePadre.getChildren().add(nodeHijo);

				asignarHijo(nodeHijo, hijo, ((Balance) nodeHijo.getValue()).getNivel());
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
		popupReport = " ";
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

	/*
	 * public void armarArbol() { listaImputables = new ArrayList(); listaSumarisadas = new ArrayList(); listaNodosSumarizados = new ArrayList();
	 * vector = new ArrayList(); nodoRaiz = new TreeNodeBase("node","",false); try { listaImputables =
	 * contabilidadService.getPlanCuentaDosService().getPlanCuenta(new Filtro("uso", Filtro.LIKE, "I")); listaSumarisadas =
	 * contabilidadService.getPlanCuentaDosService().getPlanCuenta(new Filtro("uso", Filtro.LIKE, "S")); } catch (PlanCuentaDosException e) { // TODO
	 * Auto-generated catch block e.printStackTrace(); } Iterator iter = listaImputables.iterator(); while (iter.hasNext()) { PlanCuentaDos plan =
	 * (PlanCuentaDos)iter.next(); boolean flag = false; for (int i =0; i<vector.size(); i++) { ContenedorDeNodo contenedor =
	 * (ContenedorDeNodo)vector.get(i); if (contenedor.getIdPadre()==plan.getIdPadre().intValue()) { contenedor.agregarUnHijo(plan); flag = true;
	 * break; } } if (!flag) { ContenedorDeNodo cont = new ContenedorDeNodo(plan.getIdPadre().intValue()); cont.agregarUnHijo(plan); vector.add(cont);
	 * } } //Hasta aca tenemos todo lo imputable. vectorAux = new ArrayList();
	 * 
	 * armarEstructura(); listaNodosP = new ArrayList(); while (true) { boolean seguimosIterando = true; if (vector.size()!=1) { seguimosIterando =
	 * false; } if (!seguimosIterando) { log.info("Llamaremos a FINALIZAR ARBOL!"); finalizarArbol(); } else { break; } } for (int h=0;
	 * h<vector.size();h++) { ContenedorDeNodo con = (ContenedorDeNodo)vector.get(h); nodoRaiz = con.getNodo(); } log.info("El nodo raiz tiene " +
	 * nodoRaiz.getChildCount() + " hijos"); List hijos = nodoRaiz.getChildren(); Iterator iterador = hijos.iterator(); while (iterador.hasNext()) {
	 * org.apache.myfaces.custom.tree2.TreeNode no = (org.apache.myfaces.custom.tree2.TreeNode)iterador.next();
	 * 
	 * log.info(no.getDescription()); List listHijos = no.getChildren(); Iterator iteraHijos = listHijos.iterator(); while (iteraHijos.hasNext()) {
	 * org.apache.myfaces.custom.tree2.TreeNode noHi = (org.apache.myfaces.custom.tree2.TreeNode)iteraHijos.next();
	 * 
	 * log.info("		" + noHi.getDescription()); } } log.info("fin"); }
	 * 
	 * public void armarEstructura() { for (int i =0; i<vector.size(); i++) { ContenedorDeNodo cont = (ContenedorDeNodo)vector.get(i); Iterator ite =
	 * listaSumarisadas.iterator(); while (ite.hasNext()) { PlanCuentaDos pl = (PlanCuentaDos)ite.next();
	 * 
	 * if (pl.getIdPlanCuenta().intValue()==cont.getIdPadre()) { log.info("Agrupe los elementos del bloque " + i); cont.agruparConPadre(pl); break; }
	 * } } log.info("metodo estructura"); List listaABorrar; listaABorrar = new ArrayList(); for (int i=0;i<vector.size(); i++) { ContenedorDeNodo
	 * cont = (ContenedorDeNodo)vector.get(i); int j; for (j=0; j<vector.size();j++) { if (j==i) { j++; } log.info("El vector tiene " + vector.size()
	 * + " elementos"); if (j!=vector.size()) { ContenedorDeNodo contDos = (ContenedorDeNodo)vector.get(j); log.info("...." +
	 * cont.getNodo().getIdentifier()); log.info("...." + contDos.getIdPadre()); if
	 * (cont.getNodo().getIdentifier().compareTo(String.valueOf(contDos.getIdPadre()))==0) { cont.getNodo().getChildren().add(contDos.getNodo());
	 * listaABorrar.add(contDos); } }
	 * 
	 * } } log.info("el vector tenia " + vector.size() + " nodos"); vector.removeAll(listaABorrar); log.info("el vector ahora tiene " + vector.size()
	 * + " nodos");
	 * 
	 * }
	 * 
	 * 
	 * public void finalizarArbol() { listaNodosP = new ArrayList(); List listaABorrar = new ArrayList(); for (int i =0; i<vector.size(); i++) {
	 * ContenedorDeNodo cont = (ContenedorDeNodo)vector.get(i); Iterator ite = listaSumarisadas.iterator(); log.info("Grupo " + i);
	 * log.info("el padre debe ser una cuentasumarizada"); log.info("es la cuenta " + cont.getIdPadre());
	 * log.info("************************************"); while (ite.hasNext()) { PlanCuentaDos pl = (PlanCuentaDos)ite.next();
	 * 
	 * if (pl.getIdPlanCuenta().intValue()==cont.getIdPadre()) { log.info("Agrupe los elementos del bloque " + i); boolean yaExiste = false; for (int
	 * j=0; j<listaNodosP.size(); j++) { org.apache.myfaces.custom.tree2.TreeNode nod = (org.apache.myfaces.custom.tree2.TreeNode)listaNodosP.get(j);
	 * if (nod.getIdentifier().compareTo(String.valueOf(cont.getIdPadre()))==0) { // nod.getChildren().add(cont.getNodo()); // listaABorrar.add(cont);
	 * yaExiste = true; break; } } if (!yaExiste) { org.apache.myfaces.custom.tree2.TreeNode nodePadre = new TreeNodeBase("nodo", pl.getTitulo(),
	 * String.valueOf(pl.getIdPlanCuenta()), false); nodePadre.setDescription(pl.getTitulo());
	 * nodePadre.setIdentifier(String.valueOf(pl.getIdPlanCuenta())); cont.setIdPadre(pl.getIdPadre().intValue());
	 * nodePadre.getChildren().add(cont.getNodo()); listaNodosP.add(nodePadre); cont.setNodo(nodePadre); }
	 * 
	 * break; } } } for (int i=0;i<vector.size(); i++) { ContenedorDeNodo cont = (ContenedorDeNodo)vector.get(i); int j; for (j=0;
	 * j<vector.size();j++) { if (j==i) { j++; } log.info("El vector tiene " + vector.size() + " elementos"); if (j!=vector.size()) { ContenedorDeNodo
	 * contDos = (ContenedorDeNodo)vector.get(j); log.info("...." + cont.getNodo().getIdentifier()); log.info("...." + contDos.getIdPadre()); if
	 * (cont.getNodo().getIdentifier().compareTo(String.valueOf(contDos.getIdPadre()))==0) { cont.getNodo().getChildren().add(contDos.getNodo());
	 * listaABorrar.add(contDos); } }
	 * 
	 * } }
	 * 
	 * log.info("metodo finalizar borrara los repetidos"); log.info("el vector tenia " + vector.size() + " nodos"); vector.removeAll(listaABorrar);
	 * log.info("el vector ahora tiene " + vector.size() + " nodos");
	 * 
	 * 
	 * 
	 * }
	 * 
	 * public class ContenedorDeNodo {
	 * 
	 * private int idPadre; private List listaDeHijos; private List listaDeNodos; private org.apache.myfaces.custom.tree2.TreeNode nodo;
	 * 
	 * public ContenedorDeNodo(int idPadre) { this.idPadre = idPadre; }
	 * 
	 * public void agruparConPadre(PlanCuentaDos plan) { nodo = new TreeNodeBase("nodo", plan.getTitulo(), String.valueOf(plan.getIdPlanCuenta()),
	 * false); nodo.setDescription(plan.getTitulo()); nodo.setIdentifier(String.valueOf(plan.getIdPlanCuenta())); Iterator iter =
	 * listaDeHijos.iterator(); idPadre = plan.getIdPadre().intValue(); while (iter.hasNext()) { PlanCuentaDos planC = (PlanCuentaDos)iter.next();
	 * org.apache.myfaces.custom.tree2.TreeNode nodeHijo = new TreeNodeBase("nodo", "", true); nodeHijo.setDescription(planC.getTitulo());
	 * nodeHijo.setIdentifier(String.valueOf(planC.getIdPlanCuenta())); nodo.getChildren().add(nodeHijo);
	 * 
	 * } log.info("*************************************"); log.info("Soy el nodo " + nodo.getDescription()); log.info("Con id " +
	 * nodo.getIdentifier()); log.info("Mis hijos son " + nodo.getChildren().size());
	 * 
	 * log.info("Sus nombres son:"); Iterator ite = nodo.getChildren().iterator(); while (ite.hasNext()) { org.apache.myfaces.custom.tree2.TreeNode
	 * hijo = (org.apache.myfaces.custom.tree2.TreeNode)ite.next(); log.info(hijo.getDescription()); }
	 * log.info("*************************************");
	 * 
	 * }
	 * 
	 * 
	 * public void asignarPadre(PlanCuentaDos plan) { // if (listaDeHijos!=null&&listaDeHijos.size()>0) { // nodo = new TreeNodeBase("nodo",
	 * plan.getTitulo(), String.valueOf(plan.getIdPlanCuenta()), false); // nodo.setDescription(plan.getTitulo()); //
	 * nodo.setIdentifier(String.valueOf(plan.getIdPlanCuenta())); // Iterator iter = listaDeHijos.iterator(); // while (iter.hasNext()) { //
	 * PlanCuentaDos planC = (PlanCuentaDos)iter.next(); // TreeNode nodeHijo = new TreeNodeBase("nodo", "", true); //
	 * nodeHijo.setDescription(planC.getTitulo()); // nodeHijo.setIdentifier(String.valueOf(planC.getIdPlanCuenta())); //
	 * nodo.getChildren().add(nodeHijo); // idPadre = plan.getIdPadre().intValue(); // // } // log.info("*************************************"); //
	 * log.info("Soy el nodo " + nodo.getDescription()); // log.info("Mis hijos son " + nodo.getChildren().size()); // log.info("Sus nombres son:");
	 * // Iterator ite = nodo.getChildren().iterator(); // while (ite.hasNext()) { // TreeNode hijo = (TreeNode)ite.next(); //
	 * log.info(hijo.getDescription()); // } // log.info("*************************************"); // listaDeHijos.clear(); // } else { boolean existe
	 * = false; Iterator iter = listaNodosSumarizados.iterator(); while (iter.hasNext()) { org.apache.myfaces.custom.tree2.TreeNode no =
	 * (org.apache.myfaces.custom.tree2.TreeNode)iter.next(); if (no.getIdentifier().compareTo(plan.getIdPlanCuenta().toString())==0) {
	 * no.getChildren().add(nodo); existe = true; break; } } if (!existe) {
	 * 
	 * org.apache.myfaces.custom.tree2.TreeNode nodePadre = new TreeNodeBase("nodo", plan.getTitulo(), String.valueOf(plan.getIdPlanCuenta()), false);
	 * nodePadre.setDescription(plan.getTitulo()); nodePadre.setIdentifier(String.valueOf(plan.getIdPlanCuenta())); idPadre =
	 * plan.getIdPadre().intValue(); nodePadre.getChildren().add(nodo); listaNodosSumarizados.add(nodePadre); this.nodo = nodePadre;
	 * log.info("*************************************"); log.info("Soy el nodo " + nodePadre.getDescription()); log.info("Mis hijos son " +
	 * nodePadre.getChildren().size()); log.info("Sus nombres son:"); Iterator ite = nodePadre.getChildren().iterator(); while (ite.hasNext()) {
	 * org.apache.myfaces.custom.tree2.TreeNode hijo = (org.apache.myfaces.custom.tree2.TreeNode)ite.next(); // log.info(hijo.getDescription()); }
	 * log.info("*************************************"); } // }
	 * 
	 * 
	 * }
	 * 
	 * public void agregarUnHijo(PlanCuentaDos plan) { if (listaDeHijos == null) { listaDeHijos = new ArrayList(); } listaDeHijos.add(plan); }
	 * 
	 * public int getIdPadre() { return idPadre; }
	 * 
	 * public void setIdPadre(int idPadre) { this.idPadre = idPadre; }
	 * 
	 * public org.apache.myfaces.custom.tree2.TreeNode getNodo() { return nodo; }
	 * 
	 * public void setNodo(org.apache.myfaces.custom.tree2.TreeNode nodo) { this.nodo = nodo; }
	 * 
	 * public List getListaDeNodos() { return listaDeNodos; }
	 * 
	 * public void setListaDeNodos(List listaDeNodos) { this.listaDeNodos = listaDeNodos; }
	 * 
	 * }
	 */

}
