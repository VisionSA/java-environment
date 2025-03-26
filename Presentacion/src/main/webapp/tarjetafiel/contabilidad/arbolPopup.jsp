<%@include file="/inc/tags.jsp" %>

<f:view>
<html>
<head>
	<meta HTTP-EQUIV="Content-Type" CONTENT="text/html;CHARSET=iso-8859-1">
    <title><h:outputText value="Tarjeta Fiel - Agregar domicilio"/></title>
  	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/basic.css" />
</head>

<%@include file="/inc/head.inc" %>

<body onbeforeunload="ShowWait('seleccionarNodoForm');">
<center>
<h:form id="seleccionarNodoForm">
	<x:panelTabbedPane bgcolor="#dcdcdc">
	<h:panelGroup>
	   <h:panelGrid columns="1"> 
	     <h:outputText value="Nodo Origen: #{PlanCuentaBean.nodoOrigen}" style="FONT-SIZE: large;" styleClass="texto"/>
		 <h:outputText value="Seleccione   el nodo padre" style="FONT-SIZE: large;" styleClass="texto"/>
	   </h:panelGrid>	
	<%-- INCLUDE PARA LOS ERRORES --%> 	
		<h:panelGroup id="errores">
			<jsp:include page="/inc/error.jsp" />
		</h:panelGroup>            		
		
		<f:verbatim>&nbsp;</f:verbatim>
		<f:verbatim>&nbsp;</f:verbatim>					
		<f:verbatim>&nbsp;</f:verbatim>
		<f:verbatim>&nbsp;</f:verbatim>
					

              		<%@include file="/tarjetafiel/contabilidad/pruebatree2ajax4jsf.xhtml" %>
		
					<f:verbatim><hr align="center" width="600"></f:verbatim>
					<h:panelGrid columns="7" width="560">
						<f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim>
						<f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim>
						<f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim>
						<f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim>
						<f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim>
	                	<x:commandButton id="buttonAceptarDomicilioPopup" value="Aceptar" 
	                					 action="#{PlanCuentaBean.agregarNodoDestinoPopup}" 
	                					 styleClass="botones" 
	                					 actionListener="#{PlanCuentaBean.recargarYCerrarPopup}"/>
						<x:commandButton id="buttonVolverDomicilioPopup" value="Cancelar" 
										 action="#{PlanCuentaBean.cancelarPopup}" 
										 styleClass="botones" onclick="window.close();"/>
					</h:panelGrid>					
	</h:panelGroup>
	</x:panelTabbedPane>
</h:form>
</center>
</body>
</html>
</f:view>
