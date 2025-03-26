<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>

<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>


<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en" >

<head>
  <title> ...::: CONEXION JSP - APPLET - SERVLET :::... </title>
</head>

<body bgcolor="black" >

 <f:view>

	<h:form id="frmAppletServlet" >

		<t:htmlTag value="br"></t:htmlTag>
		<t:htmlTag value="br"></t:htmlTag>
		
        <t:htmlTag value="center">
           <t:htmlTag value="h2"> <t:htmlTag value="font color='red'"> <t:outputText value=" DEMOS CONEXIONES: JSP-APPLET-SERVLET " /> </t:htmlTag> </t:htmlTag>
        </t:htmlTag>
		
		<t:htmlTag value="br"></t:htmlTag>
		<t:htmlTag value="br"></t:htmlTag>
		<t:htmlTag value="br"></t:htmlTag>						

       <t:htmlTag value="center" >		      
	      <h:panelGrid columns="1" width="100%" >

                <h:column> 
                   <h:commandLink value="Demo #1: Conexion 'JSP-Applet'" style="color:white; text-decoration:none" action="DemoLink_01" /> 
                </h:column> 
                
                <h:column> 
                   <h:commandLink value="Demo #2: Conexion 'JSP-Applet-Applet'" style="color:white; text-decoration:none" action="DemoLink_02" /> 
                </h:column> 
                
                <h:column> 
                   <h:commandLink value="Demo #3A: Conexion 'JSP-Applet-Servlet'" style="color:white; text-decoration:none" action="DemoLink_03A" /> 
                </h:column> 
                 
                <h:column> 
                   <h:commandLink value="Demo #3B: Conexion 'JSF-Applet-Servlet'" style="color:white; text-decoration:none" action="DemoLink_03B" /> 
                </h:column> 
                
                <h:column> 
                   <h:commandLink value="Demo #4: Conexion 'JSP-Applet-Servlet'" style="color:white; text-decoration:none" action="DemoLink_04" /> 
                </h:column>  
                
                <h:column> 
                   <h:commandLink value="Objetos Request JSP" style="color:white; text-decoration:none" action="ObjetoRequestJSP" /> 
                </h:column>  
                
                <h:column> 
                   <h:commandLink value="Objetos Request JSF" style="color:white; text-decoration:none" action="ObjetoRequestJSF" /> 
                </h:column>                   
       
          </h:panelGrid>
       </t:htmlTag>

		<t:htmlTag value="br"></t:htmlTag>
		<t:htmlTag value="br"></t:htmlTag>
	    
	</h:form>

</f:view>

</body>

</html>
