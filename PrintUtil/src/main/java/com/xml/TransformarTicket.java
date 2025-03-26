/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.xml;


import java.io.ByteArrayOutputStream;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMResult;
import javax.xml.transform.dom.DOMSource;
import org.apache.xml.serializer.OutputPropertiesFactory;
import org.apache.xml.serializer.Serializer;
import org.apache.xml.serializer.SerializerFactory;
import org.dom4j.Document;
import org.dom4j.io.DOMWriter;

/**
 *
 * @author sporta
 */
public class TransformarTicket {

    public TransformarTicket() {
    }

    public String transformarTicket(Document ticket, String pathXsl) throws Exception{
        
    
        TransformerFactory tFactory = TransformerFactory.newInstance();

        if(tFactory.getFeature(DOMSource.FEATURE) && tFactory.getFeature(DOMResult.FEATURE))
        {
              //Instantiate a DocumentBuilderFactory.
              DocumentBuilderFactory dFactory = DocumentBuilderFactory.newInstance();

              // And setNamespaceAware, which is required when parsing xsl files
              dFactory.setNamespaceAware(true);

              //Use the DocumentBuilderFactory to create a DocumentBuilder.
              DocumentBuilder dBuilder = dFactory.newDocumentBuilder();

              //Use the DocumentBuilder to parse the XSL stylesheet.
              org.w3c.dom.Document xslDoc = dBuilder.parse(pathXsl);

              // Use the DOM Document to define a DOMSource object.
              DOMSource xslDomSource = new DOMSource(xslDoc);

              // Set the systemId: note this is actually a URL, not a local filename
              xslDomSource.setSystemId(pathXsl);

              // Process the stylesheet DOMSource and generate a Transformer.
              Transformer transformer = tFactory.newTransformer(xslDomSource);

              //Use the DocumentBuilder to parse the XML input.
              
              org.w3c.dom.Document xmlDoc = new DOMWriter().write(ticket);

              // Use the DOM Document to define a DOMSource object.
              DOMSource xmlDomSource = new DOMSource(xmlDoc);

              // Set the base URI for the DOMSource so any relative URIs it contains can
              // be resolved.
              //xmlDomSource.setSystemId("birds.xml");

              // Create an empty DOMResult for the Result.
              DOMResult domResult = new DOMResult();

              // Perform the transformation, placing the output in the DOMResult.
              transformer.transform(xmlDomSource, domResult);

              ByteArrayOutputStream byteOutputStream = new ByteArrayOutputStream();
                //Instantiate an Xalan XML serializer and use it to serialize the output DOM to System.out
                // using the default output format, except for indent="yes"
              java.util.Properties xmlProps = OutputPropertiesFactory.getDefaultMethodProperties("text");
              xmlProps.setProperty("indent", "no");
              xmlProps.setProperty("standalone", "no");
              Serializer serializer = SerializerFactory.getSerializer(xmlProps);
              serializer.setOutputStream(byteOutputStream);
              serializer.asDOMSerializer().serialize(domResult.getNode());

              return new String(byteOutputStream.toByteArray());
        }
        else
        {
            throw new org.xml.sax.SAXNotSupportedException("DOM node processing not supported!");
        }
        
    }

}
