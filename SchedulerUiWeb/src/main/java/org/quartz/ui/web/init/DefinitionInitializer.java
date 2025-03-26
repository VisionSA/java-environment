package org.quartz.ui.web.init;
 
import org.quartz.ee.servlet.QuartzInitializerServlet;
import org.quartz.ui.web.model.DefinitionManager;
import org.quartz.ui.web.Util;
import org.apache.commons.betwixt.io.BeanReader;
import org.apache.commons.logging.Log;
import org.apache.log4j.Logger;
import org.xml.sax.SAXException;
 
import javax.servlet.ServletConfig;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.ServletException;
import javax.servlet.ServletContext;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.beans.IntrospectionException;
 
/**
 *  Definition extends QuartInitializerServlet by calling its super methods, but also
 *  loading JobDefinitions into application context
 * @since Oct 2, 2003
 * @version $Revision: 1.7 $
 * @author Matthew Payne
 */
 
public class DefinitionInitializer implements ServletContextListener {
 
    public static String DEFAULT_DEFINITION_FILE = "/JobDefinitions.xml";
    
    private static Logger log = Logger.getLogger(DefinitionInitializer.class);
    
    private ServletContext context;
 

	@Override
	public void contextDestroyed(ServletContextEvent cfg) {
		 context.setAttribute("Util.JOB_DEFINITIONS_PROP", null);
	}
	
	@Override
	public void contextInitialized(ServletContextEvent cfg) {
		  context = cfg.getServletContext();
		  String definitionPath = context.getInitParameter("definition-file");
		 
		  BeanReader beanReader = new BeanReader();
		 
		  // Configure the reader
		  beanReader.getXMLIntrospector().setAttributesForPrimitives(false);
		  
		  if (definitionPath != null && definitionPath != "") {
		   // Now we parse the xml
		   try {
			// Register beans so that betwixt knows what the xml is to be converted to
		 
			beanReader.registerBeanClass("JobDefinitions", DefinitionManager.class);
						File defFile = new File(context.getRealPath(definitionPath));
		 	
						if (!defFile.exists())  {
							 
							 log.info("Alternate user definitions file, not specfic or does not exist.  Default resource /JobDefinitions.xml will be tried.");
									     
						     //defFile = new File(context.getRealPath("/WEB-INF/JobDefinitions.xml"));
							 log.info("Attempting to read definitions from file " + this.getClass().getResource(DEFAULT_DEFINITION_FILE).getFile());
						     
						     URL url = this.getClass().getResource(DEFAULT_DEFINITION_FILE);
						     
						     if (url == null) {
						    	 log.info("resource " + DEFAULT_DEFINITION_FILE + " not found");
						     }
						     
						     defFile = new File(url.getFile());
	
						}  else {
							log.info("Reading definitions from " + definitionPath);
						}
						
			
						DefinitionManager defs = (DefinitionManager) beanReader.parse(defFile);
	
			if (defs!=null) {
			 context.setAttribute(Util.JOB_DEFINITIONS_PROP,  defs);
			 log.info(defs.getDefinitions().size() + " Definition(s) loaded from config file");
			} else {
				log.info("no definitions found");
		     
			}
		 
		   } catch (IntrospectionException e) {
			   log.error("error reading definitions", e);
		    
		   } catch (IOException e) {
			   log.error("IO error reading definitions", e);
			   
		   } catch (SAXException e) {
			   log.error("error reading definitions", e);
			      }
		  } else {
			  log.error("Error definition-file init parameter not specified");
		  }
		
	}
 
}