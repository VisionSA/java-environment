<%@include file="/inc/tags.jsp" %>

<f:view>
<html>
<head>
	<meta HTTP-EQUIV="Content-Type" CONTENT="text/html;CHARSET=iso-8859-1">
    <title><h:outputText value="Tarjeta Fiel - Agregar Telefono"/></title>
  	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/basic.css" />
</head>

<%@include file="/inc/head.inc" %>

<body onbeforeunload="ShowWait('agregarDomicilioForm');">
<center>
<h:form id="agregarDomicilioForm">
	<x:panelTabbedPane bgcolor="#dcdcdc">
	<h:panelGroup>
		<h:outputText value="Agregar Telefono" style="FONT-SIZE: large;" styleClass="texto"/>
		
		<%-- INCLUDE PARA LOS ERRORES --%> 	
		<h:panelGroup id="errores">
			<jsp:include page="/inc/error.jsp" />
		</h:panelGroup>            		
		
		<f:verbatim>&nbsp;</f:verbatim>
		<f:verbatim>&nbsp;</f:verbatim>					
		<f:verbatim>&nbsp;</f:verbatim>
		<f:verbatim>&nbsp;</f:verbatim>
		<h:panelGrid columns="4" id="panelPrincipal" width="600" align="center">

			<h:outputText value="Codigo de Area" id="outCodarea" styleClass="texto" style="COLOR: #000000; font: bold;"/>
			<h:outputText value="Número" id="outNro" styleClass="texto" style="COLOR: #000000; font: bold;"/>
			<h:outputText value="Interno" id="outInterno" styleClass="texto" style="COLOR: #000000; font: bold;"/>
			<h:outputText value="Teléfono" id="outSalida" styleClass="texto" style="COLOR: #000000; font: bold;"/>

			<h:inputText id="inCodarea" value="#{TelefonoBean.telefono.codArea}" styleClass="bordeceldainferior" 
				maxlength="6" style="width: 60; align: center;" onfocus="encender(this);" onblur="apagar(this);"
				onkeypress="return soloEnteros(this,event);"/>

			<h:inputText id="inNro" value="#{TelefonoBean.telefono.nroTlefono}" styleClass="bordeceldainferior" 
				maxlength="15" style="width: 100; align: center;" onfocus="encender(this);" onblur="apagar(this);"
				onkeypress="return soloEnteros(this,event);"/>

			<h:inputText id="inInterno" value="#{TelefonoBean.telefono.nroInterno}" styleClass="bordeceldainferior" 
				maxlength="5" style="width: 50; align: center;" onfocus="encender(this);" onblur="apagar(this);"
				onkeypress="return soloEnteros(this,event);"/>

			<h:selectOneRadio value="#{TelefonoBean.selectRadioButton}" id="selectOneRadio">
				<f:selectItem itemValue="1" itemLabel="Celular" id="celular"/>
				<f:selectItem itemValue="2" itemLabel="Fax" id="fax"/>
				<f:selectItem itemValue="3" itemLabel="Fijo" id="fio"/>
			</h:selectOneRadio>

			<h:outputText value="Tipo Teléfono" id="outTipo" styleClass="texto" style="COLOR: #000000; font: bold;"/>
			<h:outputText value="Horarios" id="outHorario" styleClass="texto" style="COLOR: #000000; font: bold;"/>
			<h:outputText value="Descripción" id="outObservo" styleClass="texto" style="COLOR: #000000; font: bold;"/>
			<f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim>

			<h:selectOneMenu id="listTipoTel" value="#{TelefonoBean.idTipoTel}" styleClass="lista" 
				immediate="true" onfocus="encender(this);" onblur="apagar(this);" style="width: 150px">
				<f:selectItems value="#{TelefonoBean.listTipoTel}" />
			</h:selectOneMenu>

			<h:inputText id="inHorario" value="#{TelefonoBean.telefono.horarios}" styleClass="bordeceldatext" size="20"
				maxlength="50" style="width: 100" onfocus="encender(this);" onblur="apagar(this);"/>

			<h:inputText id="inObs" value="#{TelefonoBean.telefono.descripcion}" styleClass="bordeceldatext" size="50"
				maxlength="200" style="width: 100" onfocus="encender(this);" onblur="apagar(this);"/>
				
			<f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim>
						
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
