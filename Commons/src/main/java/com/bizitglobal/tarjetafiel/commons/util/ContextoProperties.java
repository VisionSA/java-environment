package com.bizitglobal.tarjetafiel.commons.util;

import java.io.IOException;

/**
 * @id 4655
 * @author  José Casalis.
 * Bizit Global - Año 2012
 */
public class ContextoProperties {
	
	public static String catalinaHome = System.getProperty("catalina.home");
	public static PropertieFile contextoProperty = new PropertieFile(catalinaHome + "/webapps/contexto.properties");
	
	/**
	 * Constructor for class ContextoProperties
	 */
	public ContextoProperties(){
		super();
	}
	
	public static String getProperty(String nombrePropiedad) throws IOException{
		return contextoProperty.getProperties(nombrePropiedad);
	}
}
