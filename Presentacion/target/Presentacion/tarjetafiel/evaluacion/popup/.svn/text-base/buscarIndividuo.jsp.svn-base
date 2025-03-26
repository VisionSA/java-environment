<%@include file="/inc/tags.jsp" %>

<f:view>
<html>
<head>
    <title><h:outputText value="Tarjeta Fiel - Búsqueda de Individuos"/></title>
  	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/basic.css" />
  	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/popup.css" />
  	<s:script language="javascript">
    	function recargar() {
    		document.getElementById('amIndividuoForm').submit();
    	};
    </s:script>
</head>

<%@include file="/inc/head.inc" %>

<body onbeforeunload="ShowWait('buscarIndividuoForm');">
<center>
<h:form id="buscarIndividuoForm">

<%-- INCLUDE PARA LOS ERRORES --%>

<x:panelTabbedPane bgcolor="#dcdcdc" align="center">
	<h:panelGroup id="buscarIndividuo">
		
		<h:panelGrid columns="1" id="panelTitulo" width="600" align="center">
			<h:outputText value="Buscar Individuo" style="FONT-SIZE: large;" styleClass="textoblue"/>
		</h:panelGrid>
		
		<h:panelGroup id="verErrores">
			<jsp:include page="/inc/error.jsp" />
		</h:panelGroup>
		
		<h:panelGrid columns="2" id="PrimerPanelBusqueda" width="600" align="center">
			<h:outputText id="outCuilPopap" value="C.U.I.L.: " styleClass="texto" />
			<h:panelGroup>
				<x:inputText id="inNroCuilPopap" forceId="true" value="#{IndividuoEvaluacionBean.cuilBuscador}" size="11"
					maxlength="11" styleClass="bordeceldainferior" style="width: 150px" onfocus="encender(this);" onblur="apagar(this);" 
					onkeypress="return soloEnteros(this,event);"/>
					
				<f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim>
					
				<x:commandButton id="buscarInLink" action="#{IndividuoEvaluacionBean.buscadorDeIndividuo}" value="Buscar" styleClass="botones"/>				
			</h:panelGroup>
		</h:panelGrid>
			
		<h:panelGrid columns="4" id="panelSecuandarioPopap" align="center" width="600">
			
			<h:outputText id="outApellidoPopap" value="Apellido Paterno: " styleClass="texto" />
			<h:outputText id="txtApel" value="#{IndividuoEvaluacionBean.apel}" styleClass="textoblue"/>
			<h:outputText id="outNombrePopap" value="Nombres: " styleClass="texto" />
			<h:outputText id="txtNom" value="#{IndividuoEvaluacionBean.nom}" styleClass="textoblue"/>
				
		</h:panelGrid>
			
		<h:panelGrid columns="2" width="400" align="center">
			<x:commandButton value="Aceptar" disabled="#{!IndividuoEvaluacionBean.habilitarCarga}" action="#{IndividuoEvaluacionBean.recargarYCerrarPopup}" 
				onclick="return confirm('Se va a asociar el individuo seleccionaso a la salicitud. \n ¿Desea continuar? ');"
				styleClass="botones" title="Continuar con el alta del individuo." />
									
			<x:commandButton action="#{IndividuoEvaluacionBean.buscarOtroIndividuo}" value="Cancelar" styleClass="botones" title="Continuar con el alta del individuo."/>
									
		</h:panelGrid>
			
	</h:panelGroup>
		
</x:panelTabbedPane>
	
</h:form>	
</center>
</body>
</html>
</f:view>