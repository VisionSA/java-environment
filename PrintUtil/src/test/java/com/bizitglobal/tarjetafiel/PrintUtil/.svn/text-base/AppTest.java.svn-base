package com.bizitglobal.tarjetafiel.PrintUtil;

import com.util.ControladorArchivo;
import com.xml.ArmarXmlTicket;
import com.xml.TransformarTicket;
import java.net.URL;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import org.apache.log4j.Logger;
import org.dom4j.Document;
import org.dom4j.io.SAXReader;

/**
 * Unit test for simple App.
 */
public class AppTest 
    extends TestCase
{

    Logger logger = Logger.getLogger(AppTest.class);

    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public AppTest( String testName )
    {
        super( testName );
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite()
    {
        return new TestSuite( AppTest.class );
    }

    /**
     * Rigourous Test :-)
     */
    public void testApp()
    {
        assertTrue( true );
         try {

            ArmarXmlTicket armarXmlTicket = new ArmarXmlTicket();
            
            
            Document document = new SAXReader().read("src\\test\\java\\com\\bizitglobal\\tarjetafiel\\PrintUtil\\cierreX.xsl");
            armarXmlTicket.setDatosTicket(document);
            String string = new TransformarTicket().transformarTicket(armarXmlTicket.getDatosTicket(),"D:\\repositorios\\PrintUtil\\src\\test\\java\\com\\bizitglobal\\tarjetafiel\\PrintUtil\\cierre.xsl");
            ControladorArchivo controladorArchivo = new ControladorArchivo();

            System.out.print(controladorArchivo.centrarPartTicket(string));

        }catch(Exception ex){
            ex.printStackTrace();
        }
    }
}
