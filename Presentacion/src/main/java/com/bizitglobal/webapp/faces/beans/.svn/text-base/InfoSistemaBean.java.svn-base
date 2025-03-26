package com.bizitglobal.webapp.faces.beans;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Properties;
import java.util.Set;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.apache.log4j.Logger;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import com.bizitglobal.tarjetafiel.commons.filtros.Filtro;
import com.bizitglobal.tarjetafiel.general.exception.KettleConfigException;
import com.bizitglobal.tarjetafiel.general.exception.ParametroSistemaException;
import com.bizitglobal.tarjetafiel.general.negocio.KettleConfig;
import com.bizitglobal.tarjetafiel.general.negocio.ParametroSistema;
import com.bizitglobal.webapp.faces.beans.util.ScrollBean;
import com.bizitglobal.webapp.faces.util.Session;


/**
 * @id 4503
 * @author Facundo Bustos. Bizit Global - Año 2011
 */
public class InfoSistemaBean extends BaseBean {
	private static final Logger log = Logger.getLogger(InfoSistemaBean.class);
	private static final String CATALINA_HOME = System.getProperty("catalina.home");
	private static final String HAVA_HOME = System.getProperty("java.home");

	private static final String[] clavesRigidas =
	{ "separator",
			"driverClassName",
			"url",
			"username",
			"password",
			"initialSize",
			"maxActive",
			"minIdle",
			"maxIdle",
			"maxWait",
			"testWhileIdle",
			"validationQuery",
			"defaultAutoCommit" };

	private static final String[] clavesNoVisibles = { "password" };

	private Set<String> setClavesRigidas;
	private Set<String> setClavesNoVisibles;

	// Wrap CLASE INTERNA
	private List<Wrap> datosConexion; // LISTA DE DATOS DE CONEXION A MOSTRAR
	private List<Wrap> datosArchivos; // LISTAD DE DATOS DE DIRECTORIOS DE ARCHIVOS A MOSTRAR

	private List<Wrap> vars; // LISTA DE VARIABLES DE ENTORNO A MOSTAR
	private List<Wrap> configFlex; // LISTA DE CONFIGURACIONES DE FLEX A MOSTRAR

	private List<Wrap> version;

	private List<ParametroSistema> dbParametros;
	private List<KettleConfig> kettle;


	public InfoSistemaBean() {
		super();
		borrar();

		setClavesRigidas = new HashSet<String>();
		for (int i = 0; i < clavesRigidas.length; i++) {
			setClavesRigidas.add(clavesRigidas[i]);
		}

		setClavesNoVisibles = new HashSet<String>();
		for (int i = 0; i < clavesNoVisibles.length; i++) {
			setClavesNoVisibles.add(clavesNoVisibles[i]);
		}

	}


	public List<Wrap> getDatosConexion() {
		return datosConexion;
	}


	public void setDatosConexion(List<Wrap> datosConexion) {
		this.datosConexion = datosConexion;
	}


	public List<Wrap> getDatosArchivos() {
		return datosArchivos;
	}


	public void setDatosArchivos(List<Wrap> datosArchivos) {
		this.datosArchivos = datosArchivos;
	}


	public List<Wrap> getVars() {
		return vars;
	}


	public void setVars(List<Wrap> vars) {
		this.vars = vars;
	}


	public List<Wrap> getConfigFlex() {
		return configFlex;
	}


	public void setConfigFlex(List<Wrap> configFlex) {
		this.configFlex = configFlex;
	}


	public List<Wrap> getVersion() {
		return version;
	}


	public void setVersion(List<Wrap> version) {
		this.version = version;
	}


	public List<ParametroSistema> getDbParametros() {
		return dbParametros;
	}


	public void setDbParametros(List<ParametroSistema> dbParametros) {
		this.dbParametros = dbParametros;
	}


	public List<KettleConfig> getKettle() {
		return kettle;
	}


	public void setKettle(List<KettleConfig> kettle) {
		this.kettle = kettle;
	}


	/************************************************************************
	 * ACCIONES DEL BEAN DE INFOSISTEMA
	 ************************************************************************/

	public String inicializar() {
		log.info("entrando a inicializar() del InfoSistemaBean");
		borrarBaseBean();
		borrar();
		if (Session.getBean("ScrollBean") != null)
		{
			ScrollBean bean = (ScrollBean) Session.getBean("ScrollBean");
			bean.setHiddenScrollY(new Integer(0));
		}

		leerParametros(); // CARGO LOS PARAMETROS DE LA BASE DE DATOS
		leerVarEntorno(); // CARGO LAS VARIABLES DE ENTORNO A MOSTRAR

		leerConfigFlex(); // CARGO LAS CONFIGURACIONES FLEX A MOSTRAR
		leerVersion(); // CARGO LOS DATOS DE VERSION A MOSTRAR

		leerKettle();

		Properties p;
		try {
			p = obtenerProperties(); // INICILIZO CON EL CONTEXTO.PROPERTIES
			leerDatosConexion(p); // CARGO LOS DATOS DE CONEXION A MOSTRAR
			leerDatosArchivos(p); // CARGO LOS DATOS DEL SISTEME DE DIRECTORIOS A MOSTRAR
			log.info("inicializar() termina exitosamente");

		} catch (IOException e) {
			error.agregar("No se pudo leer el archivo contexto.properties");
			e.printStackTrace();
		}

		return "infoSistema";
	}


	private Document loadXml(String filename) {
		try {
			DocumentBuilderFactory docBuilderFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder docBuilder = docBuilderFactory.newDocumentBuilder();

			Document config = docBuilder.parse(filename);

			// Normalize the document.
			config.getDocumentElement().normalize();

			return config;

		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return null;
	}


	private Wrap getTag(Document config, String tag) {

		/* To get the tag */
		try {
			NodeList _nodeModo = config.getElementsByTagName(tag);
			Element eModo = (Element) _nodeModo.item(0);

			NodeList nModo = eModo.getChildNodes();
			return new Wrap(tag, ((Node) nModo.item(0)).getNodeValue());

		} catch (Exception e) {

			e.printStackTrace();
			return null;
		}
	}


	private void leerConfigFlex() {
		configFlex = new ArrayList<Wrap>();

		// Document d1 = loadXml("D:/Facundo Bustos/Proyectos/entorno/tarjetafiel/Presentacion/target/Presentacion/config/caja-config.xml");
		Document d1 = loadXml(CATALINA_HOME + "/webapps/Presentacion/config/caja-config.xml");

		if (d1 != null) {
			configFlex.add(getTag(d1, "modo"));
		}

		// Document d2 = loadXml("D:/Facundo Bustos/Proyectos/entorno/tarjetafiel/Presentacion/target/Presentacion/config/trans-config.xml");
		Document d2 = loadXml(CATALINA_HOME + "/webapps/Presentacion/config/trans-config.xml");

		if (d2 != null) {
			configFlex.add(getTag(d2, "ip"));
			configFlex.add(getTag(d2, "port"));
		}
	}


	private void leerVersion() {
		version = new ArrayList<Wrap>();

		// Document d = loadXml("D:/Facundo Bustos/Proyectos/entorno/tarjetafiel/Presentacion/target/Presentacion/config/version.xml");
		Document d = loadXml(CATALINA_HOME + "/webapps/Presentacion/config/version.xml");

		if (d != null) {
			version.add(getTag(d, "fecha"));
			version.add(getTag(d, "despliegue"));
			version.add(getTag(d, "descripcion"));

		}
	}


	private void leerVarEntorno() {
		this.vars = new ArrayList<Wrap>();
		vars.add(new Wrap("catalina_home", CATALINA_HOME));
		vars.add(new Wrap("java_home", HAVA_HOME));

		// vars.add(new VarEntorno("java_opts" , System.getProperty("-XX:PermSize")));

	}


	private Properties obtenerProperties() throws IOException {

		Properties p;
		FileInputStream f;
		f = new FileInputStream(CATALINA_HOME + "/webapps/contexto.properties");
		p = new Properties();
		p.load(f);
		f.close();
		return p;

	}


	private void leerDatosConexion(Properties p) {
		datosConexion = new ArrayList<Wrap>();
		for (Object key : p.keySet()) {
			if (setClavesRigidas.contains(key) && !setClavesNoVisibles.contains(key)) {

				Wrap w;
				if (key.equals("url")) {
					String valor = p.getProperty((String) key);
					int i = valor.indexOf('@');
					w = new Wrap((String) key, valor.substring(i + 1));

				}
				else {
					w = new Wrap((String) key, p.getProperty((String) key));

				}
				datosConexion.add(w);
			}

		}
	}


	private void leerDatosArchivos(Properties p) {
		datosArchivos = new ArrayList<Wrap>();
		for (Object key : p.keySet()) {
			if (!setClavesRigidas.contains(key) && !setClavesNoVisibles.contains(key)) {
				Wrap w = new Wrap((String) key, p.getProperty((String) key));
				datosArchivos.add(w);

			}

		}
	}


	private void leerParametros() {
		try {
			dbParametros = generalService.getParametroSistemaService().getParametroSistema(new Filtro());

		} catch (ParametroSistemaException e) {
			e.printStackTrace();
		}

	}


	private void leerKettle() {
		try {
			// VER ACA
			Filtro f = new Filtro();
			f.agregarCampoOperValor("esVisible", Filtro.LIKE, "si");
			kettle = generalService.getKettleConfigService().getKettleConfig(f);

		} catch (KettleConfigException e) {
			e.printStackTrace();
		}

	}


	public void borrar() {
		error.borrar();
		// alta = true;
		tituloLargo = "TARJETA FIEL";
		tituloCorto = "Informacion del Sistema";
		popup.borrar();
	}


	public boolean validar() {
		error.borrar();
		return (error.cantidad() == 0) ? true : false;
	}

	// DEFINICION CLASE INTERNA
	public class Wrap {
		private String clave;
		private String valor;


		public Wrap(String clave, String valor) {
			this.clave = clave;
			this.valor = valor;
		}


		public String getClave() {
			return clave;
		}


		public void setClave(String clave) {
			this.clave = clave;
		}


		public String getValor() {
			return valor;
		}


		public void setValor(String valor) {
			this.valor = valor;
		}
	}


	public static void main(String[] args) {
		System.out.println(System.getProperty("catalina.home"));
		System.out.println(System.getProperty("java.home"));
		System.out.println(System.getProperty("java.XX"));
	}
}
