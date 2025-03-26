<%@include file="/inc/tags.jsp" %>

<f:view>
<html>
<head>
	<meta HTTP-EQUIV="Content-Type" CONTENT="text/html;CHARSET=iso-8859-1">
    <title><h:outputText value="Tarjeta Fiel - Agregar tramos"/></title>
  	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/basic.css" />
</head>

<%@include file="/inc/head.inc" %>

<body onbeforeunload="ShowWait('agregarTramos');">
<center>
<h:form id="agregarTramos">
	<x:panelTabbedPane bgcolor="#dcdcdc">
	<h:panelGroup>
		<h:outputText value="Agregar Tramos" style="FONT-SIZE: large;" styleClass="texto"/>
		
		<%-- INCLUDE PARA LOS ERRORES --%> 	
		<h:panelGroup id="errores">
			<jsp:include page="/inc/error.jsp" />
		</h:panelGroup>            		
		
		<f:verbatim>&nbsp;</f:verbatim>
		<f:verbatim>&nbsp;</f:verbatim>					
		<f:verbatim>&nbsp;</f:verbatim>
		<f:verbatim>&nbsp;</f:verbatim>
					
		<h:panelGroup>
              		<h:panelGrid columns="3">
						<h:outputText id="outMontoDesde" value="Monto Desde: " styleClass="texto"/>
		                <h:inputText id="inMontoDesde" value="#{TramoRetencionBean.tramo.montoDesde}" 
	                			 	 size="20" maxlength="20" styleClass="bordeceldainferior" style=" width : 60px;"
	                			 	 onfocus="encender(this);" onblur="apagar(this);" onkeypress="return soloDecimales(this,event);"/>
	                	<f:verbatim>&nbsp;</f:verbatim>
	                	
						<h:outputText id="outMontoHasta" value="Monto Hasta: " styleClass="texto"/>
		                <h:inputText id="inMontoHasta" value="#{TramoRetencionBean.tramo.montoHasta}" 
	                			 	 size="20" maxlength="20" styleClass="bordeceldainferior" style=" width : 60px;"
	                			 	 onfocus="encender(this);" onblur="apagar(this);" onkeypress="return soloDecimales(this,event);"/>
	                	<f:verbatim>&nbsp;</f:verbatim>
	                	
						<h:outputText id="outMontoMinimo" value="Monto Mínimo: " styleClass="texto"/>
		                <h:inputText id="inMontoMinimo" value="#{TramoRetencionBean.tramo.montoMinimo}" 
	                			 	 size="20" maxlength="20" styleClass="bordeceldainferior" style=" width : 60px;"
	                			 	 onfocus="encender(this);" onblur="apagar(this);" onkeypress="return soloDecimales(this,event);"/>
	                	<f:verbatim>&nbsp;</f:verbatim>	                	
	                	
						<h:outputText id="outPorcentaje" value="Porcentaje Retención: " styleClass="texto"/>
						<h:panelGroup>
			                <h:inputText id="inPordentaje" value="#{TramoRetencionBean.tramo.porcRetencion}" 
		                			 	 size="5" maxlength="5" styleClass="bordeceldainferior" style=" width : 40px;"
		                			 	 onfocus="encender(this);" onblur="apagar(this);" onkeypress="return soloDecimales(this,event);"/>
		                	<h:outputText id="outPorCien" value="%" styleClass="texto"/>
	                	</h:panelGroup>
	                	<f:verbatim>&nbsp;</f:verbatim>
	                	
						<h:outputText id="outExcedente" value="Sobre Exedente: " styleClass="texto"/>
		                <h:inputText id="inExcedente" value="#{TramoRetencionBean.tramo.sobreExedente}" 
	                			 	 size="13" maxlength="13" styleClass="bordeceldainferior" style=" width : 60px;"
	                			 	 onfocus="encender(this);" onblur="apagar(this);" onkeypress="return soloDecimales(this,event);"/>
	                	<f:verbatim>&nbsp;</f:verbatim>	                		                		                	
	                	
              		</h:panelGrid>
              		
					<f:verbatim><hr align="center" width="600"></f:verbatim>
					<h:panelGrid columns="7" width="560">
						<f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim>
						<f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim>
						<f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim>
						<f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim>
						<f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim>
	                	<x:commandButton id="buttonAceptarTramoPopup" value="Aceptar" 
	                					 action="#{TramoRetencionBean.agregarTramo}" 
	                					 styleClass="botones" 
	                					 actionListener="#{TramoRetencionBean.recargarYCerrarPopup}"/>
						<x:commandButton id="buttonVolverTramoPopup" value="Cancelar" 
										 action="#{TramoRetencionBean.cancelar}" 
										 styleClass="botones" onclick="window.close();"/>
					</h:panelGrid>					
		</h:panelGroup>
	</h:panelGroup>
	</x:panelTabbedPane>
</h:form>
</center>
</body>
</html>
</f:view>
