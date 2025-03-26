<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t" %>
<%@ taglib uri="http://java.sun.com/jsf/html"       prefix="h" %>
<%@ taglib uri="http://java.sun.com/jsf/core"       prefix="f" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en" >

<head>
   <title> ...::: Applet Servlet Communication :::...  </title>
</head>

  <% 
     /* REQUEST OBJECTs: (http://www.exforsys.com/tutorials/jsp/jsp-request-object.html)
        ---------------
		getCookies() 
		getHeader(String name) 
		getHeaderNames() 
		getAttribute(String name) 
		getAttributeNames() 
		getMethod() 
		getParameter(String name) 
		getParameterNames() 
		getParameterValues(String name) 
		getQueryString() 
		getRequestURI() 
		getServletPath()      
      */

     System.out.println( " " );      
     System.out.println( "REQUEST OBJECTs: " );    
     System.out.println( "---------------- " );
     System.out.println( "HeaderNames: "  + request.getHeaderNames() );
     System.out.println( "Method: "       + request.getMethod() );
     System.out.println( "RequestURI: "   + request.getRequestURI() ); 
     System.out.println( "ServletPath: "  + request.getServletPath() );
     System.out.println( "ContextPath: "  + request.getContextPath() );   
     System.out.println( "ContentType: "  + request.getContentType() ); 
     System.out.println( "QueryString: "  + request.getQueryString() ); 
     System.out.println( "PathInfo: "     + request.getPathInfo() );
     System.out.println( "Protocol: "     + request.getProtocol() ); 
     System.out.println( "ServerName: "   + request.getServerName() );     
     System.out.println( "ServerPort: "   + request.getServerPort() );
     System.out.println( " " );  
  %>

  <body bgcolor="black">

   <f:view>

	<h:form id="frmAppletServlet" >

        <t:htmlTag value="br"></t:htmlTag>
        
        <t:htmlTag value="center">
           <t:htmlTag value="h2"> <t:htmlTag value="font color='red'"> <t:outputText value=" Demo #3B: Conexion 'JSF-Applet-Servlet' " /> </t:htmlTag> </t:htmlTag>
        </t:htmlTag>

		<t:htmlTag value="br"></t:htmlTag>
		<t:htmlTag value="br"></t:htmlTag>

		<h:panelGrid columns="1" width="100%" >

           <t:htmlTag value="center" >

				<t:htmlTag value="applet
				            type='applet' 
				            code='RichiApplet.class'
					        width='500' 
					        height='200' 
					        codebase='../../applet' 
					        jreversion='1.5'
					        iepluginurl='http://java.sun.com/update/1.5.0_09/jinstall-1_5-windows-i586.cab#Version=1,5,0,09'
					        nspluginurl='http://java.sun.com/update/1.5.0_09/jinstall-1_5-windows-i586.cab#Version=1,5,0,09' " >
									
						<t:htmlTag value="param name='URL_NombreAppletServlet' value='http://#{facesContext.externalContext.request.serverName}:#{facesContext.externalContext.request.serverPort}/#{facesContext.externalContext.request.contextPath}/JavaManServlet_02' " />  
						<t:htmlTag value="param name='nombre'                  value='#{appletServletMB.nombre}' "></t:htmlTag>            
						<t:htmlTag value="param name='URL_ServletContext'      value='#{facesContext.externalContext.request.contextPath}' " />
						<t:htmlTag value="param name='URL_ContextPath'         value='#{facesContext.externalContext.request.contextPath}' " />
		
					    <h:outputText value="Error al Cargar el APPLET...!!!" />
	
				</t:htmlTag>
	
	        </t:htmlTag>
	        
	    </h:panelGrid>
	    
	    <t:htmlTag value="br"></t:htmlTag>
	    <t:htmlTag value="br"></t:htmlTag>
	    <t:htmlTag value="br"></t:htmlTag>	    
	    <t:htmlTag value="br"></t:htmlTag>	    
	    
	 <center> <h3> <strong> <a href="../Menu.jsf" style="color:red; text-decoration:none" > Menu Principal </a> </strong> </h3> </center>  
	
	 <br/>
	    
	</h:form>

</f:view>

</body>

</html>
