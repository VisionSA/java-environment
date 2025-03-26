<%@include file="/inc/tags.jsp" %>
<jsp:useBean id="ahora" class="java.util.Date" scope="request"/>
<%--
Id: 4945 
Author: Hernan Guillen
--%>
<f:view>
<html>
<head>
	<title><h:outputText value="#{NotaDebitoJudicialBean.tituloLargo}"/></title>
	<s:script language="javascript">
		function recargar() {
			document.getElementById('amFormaPagoForm').submit();
		}
	</s:script>
</head>

<jsp:include page="/inc/includes.jsp" />

<body class="hold-transition skin-blue sidebar-mini" onbeforeunload="ShowWait('NotaDebitoJudicialBeanManual');" onload="if('${NotaDebitoJudicialBean.popup.mostrar}'=='true') {viewDialog.show();} else {window.scrollTo(0,${ScrollBean.hiddenScrollY});}">

<h:form id="mainMenu" style="display: none">
  <jsp:include page="/inc/navigation_test.jsp" />
  <x:commandLink id="cerrarSesion" action="#{LoginBean.salir}"/>
</h:form>

<jsp:include page="/inc/header.jsp" />

<!-- Content Header (Page header) -->
<section class="content-header">
  <h1>
    ${NotaDebitoJudicialBean.tituloCorto}
    <small>${NotaDebitoJudicialBean.tituloLargo}</small>
  </h1>
</section>

<section class="content">

<div class="box box-default">
<div class="box-header"><h3></h3></div>

<center>
	<secure:check/>

	<h:form id="NotaDebitoJudicialBeanManual">

	<%-- GRABA EL ESTADO DEL SCROLL(Dibujar solo si el popup no se muestra) --%>
	<h:panelGroup rendered="#{!NotaDebitoJudicialBean.popup.mostrar}">
	<%@include file="/inc/scroll.inc" %>
	</h:panelGroup>

		<x:panelLayout id="page" layout="#{globalOptions.pageLayout}"
			styleClass="pageLayout"
			headerClass="pageHeader"
			navigationClass="pageNavigation"
			bodyClass="pageBody"
			footerClass="pageFooter" >


			<f:facet name="body">
				<h:panelGroup id="body">

					<h:panelGrid columns="1" align="center" id="PanelPricipal">
					<%-- INCLUDE PARA LOS ERRORES --%>
					<h:panelGroup id="errores">
						<jsp:include page="/inc/error.jsp" />
					</h:panelGroup>

					<%-- POPUP PARA LOS MENSAJES DE EXITO Y FRACASO --%>
					<s:modalDialog dialogId="viewDialog"
								dialogVar="viewDialog"
								styleClass="viewDialog"
								dialogTitle="#{NotaDebitoJudicialBean.tituloCorto}">
						<h:panelGrid columns="2" width="500">
							<x:graphicImage value="/img/#{NotaDebitoJudicialBean.popup.nombreIcono}" />
							<h:outputText value="#{NotaDebitoJudicialBean.popup.mensaje}" styleClass="texto"/>
						</h:panelGrid>
						<h:panelGrid columns="2" width="500">
							<x:commandButton action="#{NotaDebitoJudicialBean.irANuevaNotaDebito}" 
								onclick="clickLink('nuevaNotaDebito');dojo.widget.byId('viewDialog').hide();"
								value="Nuevo" styleClass="btn btn-primary btn-flat" title="Nueva nota de débito."/>
							<x:commandButton action="#{NotaDebitoJudicialBean.irASalir}" 
								onclick="clickLink('salir');dojo.widget.byId('viewDialog').hide();"
								value="Salir" styleClass="btn btn-primary btn-flat" title="Regresa al inicio."/>
						</h:panelGrid>
					</s:modalDialog>
					<x:commandLink id="nuevaNotaDebito" action="#{NotaDebitoJudicialBean.irANuevaNotaDebito}" forceId="true" style="display: block;"/>
					<x:commandLink id="salir" action="#{NotaDebitoJudicialBean.irASalir}" forceId="true" style="display: block;"/>

					<c:if test="${!NotaDebitoJudicialBean.verDetalles}">
						<h:panelGrid id="panelPrincipalUno" columns="3" width="350">
							<h:outputText value="Ingrese cuenta:" styleClass="texto"/>
							<h:inputText id="nroCuenta" value="#{NotaDebitoJudicialBean.nroCuenta}"
							styleClass="bordeceldatext" maxlength="6" size="6"
							style="width: 90px" onfocus="encender(this);" onblur="apagar(this);"
							onkeypress="return soloEnteros(this,event);"/>
							<x:commandButton id="buttonBuscar" value="Buscar" action="#{NotaDebitoJudicialBean.buscar}" styleClass="btn btn-primary btn-flat"/>
						</h:panelGrid>
					</c:if>
					<c:if test="${NotaDebitoJudicialBean.verDetalles}">
						<h:panelGrid id="panelPrincipalDos" columns="2" width="350">
							<h:outputText value="Cuenta:" styleClass="texto"/>
							<h:outputText value="#{NotaDebitoJudicialBean.cliente.idCliente}" styleClass="texto"/>
							
							<h:outputText value="Nombre:" styleClass="texto"/>
							<h:outputText value="#{NotaDebitoJudicialBean.cliente.nombreCliente}" styleClass="texto"/>
							
							<h:outputText value="Saldo:" styleClass="texto"/>
							<h:outputText value="#{NotaDebitoJudicialBean.saldo}" styleClass="texto"/>
							
							<h:outputText value="Monto:" styleClass="texto"/>
							<h:inputText id="montoDebito" value="#{NotaDebitoJudicialBean.monto}"
							styleClass="bordeceldatext" style="width: 90px" 
							onfocus="encender(this);" onblur="apagar(this);"
							onkeypress="return soloDecimales(this,event);"/>
						</h:panelGrid>
						<f:verbatim>
							<br>
						</f:verbatim>
						<f:verbatim><hr align="center" width="700"></f:verbatim>
						<h:panelGrid columns="10" width="727" id="panelBotonera">
							<f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim>
							<f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim>
							<f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim>
							<f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim>
							<f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim>
							<f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim>
							<f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim>
							<f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim>
							<x:commandButton id="buttonGrabar" value="Crear Nota" action="#{NotaDebitoJudicialBean.crear}" styleClass="btn btn-primary btn-flat"
									onclick="return confirm('Se va a realizar una nota de debito judicial por $' + document.getElementById('NotaDebitoJudicialBeanManual:montoDebito').value + '. \n¿Desea continuar?');"/>
							<x:commandButton id="buttonCancelar" value="Cancelar" action="#{NotaDebitoJudicialBean.cancelar}" styleClass="btn btn-primary btn-flat" />
						</h:panelGrid>
					</c:if>
					</h:panelGrid>
				</h:panelGroup>
			</f:facet>

		</x:panelLayout>
	</h:form>
</center>



<div class="box-header"></div>

</div>
</section> 

</div>
<!-- ./Main content -->

<%@include file="/inc/footer.jsp" %>


<script type="text/javascript">

document.getElementById("sideMenu").innerHTML = `<li class="header">MENU</li>`;

for(var i = 1; i < mainMenu__idJsp2_menu.length-1; i++) {
    var obj = mainMenu__idJsp2_menu[i];
    document.getElementById("sideMenu").innerHTML += generateMenu(obj,"mainMenu__idJsp2_menu:A]\#{NotaDebitoJudicialBean.inicializar}") + `</li>`;
}

</script>

<%@include file="/inc/scripts.jsp" %>



</body>

</html>
</f:view>
