<%@include file="/inc/tags.jsp" %>

<f:view>
<html>
<head>
	<meta HTTP-EQUIV="Content-Type" CONTENT="text/html;CHARSET=iso-8859-1">
    <title><h:outputText value="Tarjeta Fiel - Reglas"/></title>
  	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/basic.css" />
</head>

<%@include file="/inc/head.inc" %>

<body onbeforeunload="ShowWait('agregarReglaForm');">
<center>
<secure:check/>
<h:form id="agregarReglaForm">
		<x:panelTabbedPane bgcolor="#dcdcdc">
			<h:panelGroup>
				<h:outputText value="Reglas" style="FONT-SIZE: large;"
					styleClass="texto" />

				<%-- INCLUDE PARA LOS ERRORES --%>
				<h:panelGroup id="errores">
					<jsp:include page="/inc/error.jsp" />
				</h:panelGroup>

				<f:verbatim>&nbsp;</f:verbatim>
				<f:verbatim>&nbsp;</f:verbatim>
				<f:verbatim>&nbsp;</f:verbatim>
				<f:verbatim>&nbsp;</f:verbatim>
				<h:panelGrid columns="1" id="panelPrincipal" width="725"
					align="center">

					<h:outputText value="Descripcion de la Regla" id="outDesc" styleClass="texto"
						style="COLOR: #000000; font: bold;" />
					<h:inputText id="inDescripcion"
						value="#{EsquemaReglaBean.regla.descripcion}"
						styleClass="bordeceldatext"
						style="align: center; width : 700px;" onfocus="encender(this);"
						onblur="apagar(this);" />
					<h:outputText value="Requerde que debe formular la pregunta de forma tal que 
						la respuesta sea 'SI' ó 'NO'. Siendo 'SI' la respuesta para que pase la regla." 
						id="outMensaje" styleClass="texto"
						style="COLOR: #000000; font: bold;" />

				</h:panelGrid>
				<f:verbatim>
					<hr align="center" width="600">
				</f:verbatim>
				<h:panelGrid columns="7" width="560">
					<f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim>
					<f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim>
					<f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim>
					<f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim>
					<f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim>
					<x:commandButton id="buttonAceptarReglaPopup" value="Aceptar"
						action="#{EsquemaReglaBean.grabarRegla}" styleClass="botones"
						actionListener="#{EsquemaReglaBean.recargarYCerrarPopup}" />
					<x:commandButton id="buttonVolverReglaPopup" value="Cancelar"
						action="#{EsquemaReglaBean.cancelarRegla}" styleClass="botones"
						onclick="window.close();" />
				</h:panelGrid>
			</h:panelGroup>
		</x:panelTabbedPane>
	</h:form>
</center>
</body>
</html>
</f:view>
