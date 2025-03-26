package com.bizitglobal.tarjetafiel.commons.util;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class PropertieFile {
	private String configurationFile; 
	private Properties properties;
	
	public PropertieFile(String confFile){
		try {
                    //if(confFile.substring(confFile.indexOf(".")+1).equalsIgnoreCase("properties")){
//			        if (true) {
			            configurationFile = confFile;
                        FileInputStream f = new FileInputStream(configurationFile);
                        properties = new Properties();
                        properties.load(f);
                        f.close();
//                    }
                } catch (Exception ex) {
	    	  ex.printStackTrace();	
	      }
	}
	/**
         * 
         * @param propName
         * @return propValue or null if the property is not found
         * @throws IOException in case of the properties file is't load
         */
	public String getProperties(String propName) throws IOException {
		String propValue;
                propValue=properties.getProperty(propName);
                return propValue;
	}

}