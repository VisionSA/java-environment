<%@include file="/inc/tags.jsp" %>

<f:view>
<html>
<head>
	<meta HTTP-EQUIV="Content-Type" CONTENT="text/html;CHARSET=iso-8859-1">
    <title><h:outputText value="Tarjeta Fiel - Agregar Medio de Pago"/></title>
  	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/basic.css" />
</head>

<%@include file="/inc/head.inc" %>

<body onbeforeunload="ShowWait('agregarMedioPagoForm');">
<center>
<h:form id="agregarMedioPagoForm">
	<x:panelTabbedPane bgcolor="#dcdcdc">
	<h:panelGroup>
		<h:outputText value="Agregar Medio de  Pago" style="FONT-SIZE: large;" styleClass="texto"/>
		
		<%-- INCLUDE PARA LOS ERRORES --%> 	
		<h:panelGroup id="errores">
			<jsp:include page="/inc/error.jsp" />
		</h:panelGroup>            		
		
		<f:verbatim>&nbsp;</f:verbatim>
		<f:verbatim>&nbsp;</f:verbatim>					
		<f:verbatim>&nbsp;</f:verbatim>
		<f:verbatim>&nbsp;</f:verbatim>
		<h:panelGrid columns="2" id="panelPrincipal" width="600" align="center">
                	<h:outputText value="Medio de Pago:" styleClass="texto"/>
							<h:selectOneMenu id="lstImpresora" value="#{CajaBean.idImpresoraSeleccionada}"
								styleClass="lista" immediate="true" onfocus="encender(this);"
								onblur="apagar(this);" style="width: 200px">
								<f:selectItems value="#{CajaBean.impresoraItems}"/>
							</h:selectOneMenu>
							<h:outputText value=""  id="relleno" />
							<h:panelGrid id="secun" columns="3">
			                     <h:outputText value="Numero de Cuenta : " styleClass="texto"/>
								 <h:inputText value="#{CajaBean.idPlanCuentaABuscar}" onkeypress="return soloEnteros(this, event);" maxlength="4" id="inputCuentaBusquedaLibro" title="Introduzca el nro. de  cuenta buscada." style=" width : 47px;"/>
								 <x:commandLink id="buscarCuentaLink"  action="#{CajaBean.buscarCuentaPopup}">
									<x:graphicImage value="/img/icon/srch_24.gif" title="Buscar Cuenta." border="0"/>								
								 </x:commandLink>
						    </h:panelGrid>	
						    <h:outputText value=""  id="relleno2" />
						    <h:panelGrid columns="2">		
						      <h:outputText id="outhabilitada" value="Habilitada:" styleClass="texto"/>
						      <h:selectBooleanCheckbox id="boolChecHabilitada" value="#{CajaBean.habilitada}" />
						    </h:panelGrid>	
									
		</h:panelGrid>
        <f:verbatim><hr align="center" width="600"></f:verbatim>
		<h:panelGrid columns="7" width="560">
			<f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim>
			<f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim>
			<f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim>
			<f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim>
			<f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim>
	        <x:commandButton id="buttonAceptarDomicilioPopup" value="Aceptar" 
	        	action="#{TelefonoBean.agregarTelefono}" styleClass="botones" 
	            actionListener="#{TelefonoBean.recargarYCerrarPopup}"/>
			<x:commandButton id="buttonVolverDomicilioPopup" value="Cancelar" 
				action="#{TelefonoBean.cancelar}" styleClass="botones" onclick="window.close();"/>
		</h:panelGrid>					
	</h:panelGroup>
	</x:panelTabbedPane>
</h:form>
</center>
</body>
</html>
</f:view>
