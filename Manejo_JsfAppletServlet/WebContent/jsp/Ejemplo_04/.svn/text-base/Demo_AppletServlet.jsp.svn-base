<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>

<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>


<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en" >

<head>
  <title>...::: CONEXION APPLET - SERVLET :::...</title>

 <script language="JavaScript" type="text/javascript"> 
   
    function conexionAppletServletFormaHttpText(){
       var applet = window.document.getElementById( "frmAppletServlet:codigoApplet" );
       
       //alert( "" + applet );
        
       if( applet != null ){
           var mensaje = applet.click_btnProcesarHttpText(); 
           alert( "Mensaje Retorno: " + mensaje );
       }
    }

    function conexionAppletServletFormaHttpTextII(){
       var applet = window.document.getElementById( "frmAppletServlet:codigoApplet" );
       
       //alert( "" + applet );
        
       if( applet != null ){
           applet.click_btnProcesarHttpTextII(); 
       }
    }

    function conexionAppletServletFormaHttpObject(){
       var applet = window.document.getElementById( "frmAppletServlet:codigoApplet" );
       
       //alert( "" + applet );
       
       if( applet != null ){
	       var mensaje = applet.click_btnProcesarHttpObject();  
	       alert( "Mensaje Retorno: " + mensaje );
       }
    }
    
    function conexionAppletServletFormaHttpObjectII(){
       var applet = window.document.getElementById( "frmAppletServlet:codigoApplet" );
       
       //alert( "" + applet );
       
       if( applet != null ){
	       var mensaje = applet.click_btnProcesarHttpObjectII();  
           alert( "Mensaje Retorno: " + mensaje );
       }
    } 
    
    function limpiarDatos(){
       var fomulario = window.document.getElementById( "frmAppletServlet" );
       fomulario.submit();
       /*
       var applet = window.document.getElementById( "frmAppletServlet:codigoApplet" );
       
       //alert( "" + applet );
       
       if( applet != null ){
	       applet.click_btnLimpiar();  
       }
       */
    }   

 </script>

</head>

<body bgcolor="BLACK">

 <f:view>

	<h:form id="frmAppletServlet">

        <t:htmlTag value="br"></t:htmlTag>
        
        <t:htmlTag value="center">
           <t:htmlTag value="h2"> <t:htmlTag value="font color='red'"> <t:outputText value=" Demo #4: Conexion 'JSF-Applet-Servlet' " /> </t:htmlTag> </t:htmlTag>
        </t:htmlTag>

		<t:htmlTag value="br"></t:htmlTag>
		<t:htmlTag value="br"></t:htmlTag>

		<h:panelGrid columns="1" width="100%">

           <t:htmlTag value="center" >

				<t:htmlTag id    = "codigoApplet" 
				           value = "applet
					               type='applet' 
					               code='JavaManApplet.class'
						           width='800' 
						           height='250' 
						           codebase='../../applet' 
						           jreversion='1.6'
						           iepluginurl='http://java.sun.com/update/1.5.0_09/jinstall-1_5-windows-i586.cab#Version=1,5,0,09'
						           nspluginurl='http://java.sun.com/update/1.5.0_09/jinstall-1_5-windows-i586.cab#Version=1,5,0,09' " >
							
                        <t:htmlTag value="param name='URL_AppletServlet' value='http://#{facesContext.externalContext.request.serverName}:#{facesContext.externalContext.request.serverPort}/#{facesContext.externalContext.requestContextPath}/JavaManServlet_01' " />  
												
						<t:htmlTag value="param name='CodigoApplet'   value=#{appletServletMB.codigo}   " />
						<t:htmlTag value="param name='NombreApplet'   value=#{appletServletMB.nombre}   " />
						<t:htmlTag value="param name='ApellidoApplet' value=#{appletServletMB.apellido} " />
	
					<h:outputText value="#{appletServletMB.mensajeErrorApplet}" />
	
				</t:htmlTag>
	
	        </t:htmlTag>
	        
	    </h:panelGrid>
	    
	    <t:htmlTag value="br" ></t:htmlTag>
	    
	    <t:htmlTag value="center" >
	       <h:panelGrid columns="4" width="30%" >
	          <t:column> <h:commandButton id="btnConexion_HttpText"     action="goto" type="button" value=" Modo Envìo: 'HttpText'   "    style="width:200" onclick="conexionAppletServletFormaHttpText()"     /> </t:column>
	          <t:column> <h:commandButton id="btnConexion_HttpObject"   action="goto" type="button" value=" Modo Envìo: 'HttpObject' "    style="width:200" onclick="conexionAppletServletFormaHttpObject()"   /> </t:column>	          
	          <t:column> <h:commandButton id="btnConexion_HttpObjectII" action="goto" type="button" value=" Modo Envìo: 'HttpObject II' " style="width:200" onclick="conexionAppletServletFormaHttpObjectII()" /> </t:column>	          
	          <t:column> <h:commandButton id="btnConexion_HttpTextII"   action="goto" type="button" value=" Generar Imagen "              style="width:200" onclick="conexionAppletServletFormaHttpTextII()"   /> </t:column>
	       </h:panelGrid>
	    </t:htmlTag>
	    	    
   	    <t:htmlTag value="br" ></t:htmlTag>
   	    
	    <t:htmlTag value="center" >
	       <h:panelGrid columns="1" width="15%" >
	          <t:column> <h:commandButton id="btnLimpiar" action="goto" type="button" value=" Limpiar Datos " style="width:200" onclick="limpiarDatos()" /> </t:column>
	       </h:panelGrid>
	    </t:htmlTag>
   	    
   	    <t:htmlTag value="br" ></t:htmlTag>
   	    <t:htmlTag value="br" ></t:htmlTag>
   	    <t:htmlTag value="br" ></t:htmlTag>	    
	    
	    <center> <h3> <strong> <a href="../Menu.jsf" style="color:red; text-decoration:none" > Menu Principal </a> </strong> </h3> </center>  
	
	    <br/>
	    
	</h:form>

</f:view>

</body>

</html>
