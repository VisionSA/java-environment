package com.bizitglobal.webapp.faces.beans.contabilidad;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.log4j.Logger;
import org.jdom.Attribute;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.output.XMLOutputter;
import zeus.util.TreeNode;
import com.bizitglobal.tarjetafiel.commons.filtros.Filtro;
import com.bizitglobal.tarjetafiel.contabilidad.exception.PlanCuentaDosException;
import com.bizitglobal.tarjetafiel.contabilidad.negocio.PlanCuentaDos;
import com.bizitglobal.webapp.faces.beans.BaseBean;
import com.bizitglobal.webapp.faces.util.Session;


@SuppressWarnings({"rawtypes","unchecked","unused"})
public class ReporteContabilidadPlanCuentasBean extends BaseBean {
	private static final Logger log = Logger.getLogger(ReporteContabilidadPlanCuentasBean.class);
	private final static String PATHDATASOURCEXML = "/webapps";
	private final static String PATH2DATASOURCEXML = "/webapps";
	public final static String TAB0 = "";
	public final static String TAB1 = "	";
	public final static String TAB2 = "			";
	public final static String TAB3 = "					";
	public final static String TAB4 = "							";
	public final static String TAB5 = "									";
	public final static String TAB6 = "											";
	public final static String TAB7 = "													";

	// variable usada para limpiar el popup en la pantalla de inicio
	private String blanco = "";

	// private final static int CANTIDAD_NIVELES=6;

	FileReader fr = null;
	FileWriter fw = null;
	BufferedReader br = null;

	// map para guardar las tabulaciones q definen el nivel de un nodo
	private Map tab = new HashMap();

	// Propiedad para ejecutar el popup del reporte.
	private String popupReport = new String("");

	TreeNode treeData;

	HashMap hijosNodo;


	public ReporteContabilidadPlanCuentasBean() {
		// super();
	}


	public String getPopupReport() {
		return popupReport;
	}


	public void setPopupReport(String popupReport) {
		this.popupReport = popupReport;
	}


	public void borrar() {
		borrarBaseBean();
		tituloLargo = "";
		tituloCorto = "";
	}


	public String inicializar() {
		borrar();
		tituloLargo = "CONTABILIDAD";
		tituloCorto = "Reporte Balance";
		tab.put("0", this.TAB0);
		tab.put("1", this.TAB1);
		tab.put("2", this.TAB2);
		tab.put("3", this.TAB3);
		tab.put("4", this.TAB4);
		tab.put("5", this.TAB5);
		tab.put("6", this.TAB6);
		tab.put("7", this.TAB7);
		generar();
		return "inicio";
	}


	public String generar() {

		inicializarArbol();
		// //////////armamos el xmldatasource
		String key;
		key = "catalina.home";
		key = System.getProperty(key);

		Enumeration balancesAux = treeData.nodes();
		TreeNode raiz = null;

		// aca empezamos a armar el xml que sera usado como datasource en el reporte
		Element root = new Element("planCuentas");
		Element nodo;
		Element denominancion;
		Element I_S;
		Element CC;
		Enumeration planCuentas = treeData.values();

		while (planCuentas.hasMoreElements()) {
			PlanCuentaDos planCuenta = (PlanCuentaDos) planCuentas.nextElement();
			nodo = new Element("nodo");
			nodo.setAttribute(new Attribute("nroImputa", planCuenta.getId().toString()));
			denominancion = new Element("denominacion");
			denominancion.setText(tab.get(String.valueOf(planCuenta.getNivel())) + planCuenta.getTitulo());
			I_S = new Element("I-S");
			I_S.setText(planCuenta.getUso());
			CC = new Element("CC");
			CC.setText(planCuenta.getCentroCosto());
			nodo.addContent(denominancion);
			nodo.addContent(I_S);
			nodo.addContent(CC);
			root.addContent(nodo);
		}

		Document doc = new Document(root);// Creamos el documento

		try {
			XMLOutputter out = new XMLOutputter();
			XMLOutputter outDos = new XMLOutputter();
			File fiUno = new File(key + PATHDATASOURCEXML);
			if (fiUno.exists()) {
				FileOutputStream file = new FileOutputStream(key + PATHDATASOURCEXML + "/reportePlanCuentas.xml");
				out.output(doc, file);
				file.flush();
				file.close();
			}
			File fiDos = new File(key + PATH2DATASOURCEXML);
			if (fiDos.exists()) {
				FileOutputStream file2 = new FileOutputStream(key + PATH2DATASOURCEXML + "/reportePlanCuentas.xml");
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
		String url = PATHDATASOURCEXML + "/reportePlanCuentas.xml";

		HttpServletRequest request = Session.getRequest();

		error.borrar();
		popupReport = new String("");
		Integer inte = new Integer(1);
		Format dateFormat = new SimpleDateFormat("yyyy-MM-dd");

		String p1 = "?URLImagen=" + Session.getHomePath() + "/img/fiel/logo_fiel.jpg";
		String p2 = "&JRXmlDataSource=" + url;
		String p3 = "&xpath2=/planCuentas/nodo";
		String page = request.getContextPath() + "/report/ContabilidadPlanCuentas.jrxml";
		popupReport = "popup('" + page + p1 + p2 + p3 + "',1000,600)";

		log.info(popupReport);
		return null;
	}


	private void inicializarArbol() {
		// traemos los saldos de las hojas para el ejercicio seleccionado y lo guardamos en map

		hijosNodo = new HashMap();
		List listaHijos = new ArrayList();
		try {
			List nodos = contabilidadService.getPlanCuentaDosService().getPlanCuenta(new Filtro());
			Iterator it = nodos.iterator();
			PlanCuentaDos p = new PlanCuentaDos();
			while (it.hasNext()) {
				PlanCuentaDos element = (PlanCuentaDos) it.next();
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
			Filtro filtro = new Filtro();
			filtro.agregarCampoOperValor("idPadre", Filtro.IGUAL, new Long(PlanCuentaBean.IDNODORAIZ));
			List nodosRaizList;
			treeData = null;
			nodosRaizList = contabilidadService.getPlanCuentaDosService().getPlanCuenta(filtro);
			Iterator iter = nodosRaizList.iterator();
			while (iter.hasNext()) {
				PlanCuentaDos element = (PlanCuentaDos) iter.next();

				treeData = asignarHijo(element);
			}

		} catch (PlanCuentaDosException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}


	/* ESTE METODO ES RECURSIVO PARA CREAR LOS NODOS */
	public TreeNode asignarHijo(PlanCuentaDos padre) {
		List hijosP = new ArrayList();
		// hijosP = getHijos(padre);
		hijosP = (List) hijosNodo.get(padre.getId());
		Iterator iter = hijosP.iterator();
		// TreeNode nodePadre=null;
		int level = 1;
		// if(padre.getIdPadre().equals(PlanCuentaBean.IDNODORAIZ))
		// { //nivelactual= 0;

		padre.setNivel(0);
		TreeNode nodePadre = new TreeNode(padre);
		// }
		TreeNode nodeHijo;
		while (iter.hasNext()) {
			PlanCuentaDos hijo = (PlanCuentaDos) iter.next();
			hijo.setNivel(1);
			nodeHijo = new TreeNode(hijo);
			nodePadre.addChild(nodeHijo);
			// nodePadre.getChildren().add(nodeHijo);
			asignarHijo(nodeHijo, hijo, level);
		}
		return nodePadre;
	}


	public TreeNode asignarHijo(TreeNode nodePadre, PlanCuentaDos padre, int level) {

		List hijosP = new ArrayList();

		// hijosP = getHijos(padre);
		hijosP = (List) hijosNodo.get(padre.getId());
		if (hijosP != null) {
			Iterator iter = hijosP.iterator();
			TreeNode nodeHijo;
			while (iter.hasNext()) {
				PlanCuentaDos hijo = (PlanCuentaDos) iter.next();
				hijo.setNivel(level + 1);
				nodeHijo = new TreeNode(hijo);
				nodePadre.addChild(nodeHijo);
				asignarHijo(nodeHijo, hijo, ((PlanCuentaDos) nodeHijo.getValue()).getNivel());
			}
		}

		return nodePadre;
	}


	public List getHijos(PlanCuentaDos padre) {

		List aux = new ArrayList();
		List hijos = new ArrayList();

		Filtro filtro = new Filtro();
		filtro.agregarCampoOperValor("idPadre", Filtro.IGUAL, padre.getIdPlanCuenta());
		try {
			aux = contabilidadService.getPlanCuentaDosService().getPlanCuenta(filtro);
			Iterator iter;
			iter = aux.iterator();
			while (iter.hasNext()) {
				PlanCuentaDos planCuenta = (PlanCuentaDos) iter.next();
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


	public boolean validar() {
		// TODO Auto-generated method stub
		return false;
	}


	public String getBlanco() {
		popupReport = "";
		return "";
	}


	public void setBlanco(String blanco) {
		this.blanco = blanco;
	}

}
