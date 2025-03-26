<%@include file="/inc/tags.jsp"%>
<jsp:useBean id="ahora" class="java.util.Date" scope="request" />

<f:view>
	<html lang="es">
	<head>
	<title><h:outputText value="#{TareaBean.tituloLargo}" /></title>
	<s:script language="javascript">
    	function recargar() {
    		document.getElementById('amTareas').submit();
    	}
    </s:script>
	</head>

	<%@include file="/inc/includes.jsp" %>

	<body class="hold-transition skin-blue sidebar-mini" onbeforeunload="ShowWait('amProcesoForm');"
		onload="if('${TareaBean.popup.mostrar}'=='true') {viewDialog.show();} else {window.scrollTo(0,${ScrollBean.hiddenScrollY});}">

<h:form id="mainMenu" style="display: none">
  <jsp:include page="/inc/navigation_test.jsp" />
  <x:commandLink id="cerrarSesion" action="#{LoginBean.salir}"/>
</h:form>

<jsp:include page="/inc/header.jsp" />

<!-- Content Header (Page header) -->
<section class="content-header">
  <h1>
    ${TareaBean.tituloCorto}
    <small>${TareaBean.tituloLargo}</small>
  </h1>
</section>

<section class="content">

<div class="box box-default">
<div class="box-header"><h3></h3></div>



	<center><h:form id="amTareaForm">

		<%-- GRABA EL ESTADO DEL SCROLL(Dibujar solo si el popup no se muestra) --%>
		<h:panelGroup rendered="#{!TareaBean.popup.mostrar}">
			<%@include file="/inc/scroll.inc"%>
		</h:panelGroup>

		<x:panelLayout id="page" layout="#{globalOptions.pageLayout}"
			styleClass="pageLayout" headerClass="pageHeader"
			navigationClass="pageNavigation" bodyClass="pageBody"
			footerClass="pageFooter">

			<f:facet name="body">
				<h:panelGroup id="body">

					<h:panelGrid columns="1" align="center" id="PanelPricipalProceso">
						<%-- INCLUDE PARA LOS ERRORES --%>
						<h:panelGroup id="errores">
							<jsp:include page="/inc/error.jsp" />
						</h:panelGroup>


						<%-- POPUP PARA LOS MENSAJES DE EXITO Y FRACASO --%>
						<s:modalDialog dialogId="viewDialog" dialogVar="viewDialog"
							styleClass="viewDialog" dialogTitle="#{TareaBean.tituloCorto}">
							<h:panelGrid columns="2" width="500">
								<x:graphicImage value="/img/#{TareaBean.popup.nombreIcono}" />
								<h:outputText value=" #{TareaBean.popup.mensaje}"
									styleClass="texto" />
							</h:panelGrid>

							<h:panelGrid columns="3" width="500">
								<x:commandButton action="#{TareaBean.irANuevaTarea}"
									onclick="clickLink('nuevaTarea');dojo.widget.byId('viewDialog').hide();"
									value="Nuevo" styleClass="botones" title="Agregar nueva tarea." />

								<x:commandButton action="#{TareaBean.irAModificarTarea}"
									onclick="clickLink('modificarTarea');dojo.widget.byId('viewDialog').hide();"
									value="Modificar" styleClass="botones"
									title="Seguir modificando la tarea." />

								<x:commandButton action="#{TareaBean.irAListarTarea}"
									onclick="clickLink('listarTarea');dojo.widget.byId('viewDialog').hide();"
									value="Listar" styleClass="botones"
									title="Ir al listado de tareas." />
							</h:panelGrid>
						</s:modalDialog>
						<x:commandLink id="nuevaTarea" action="#{TareaBean.irANuevaTarea}"
							forceId="true" style="display: block;" />
						<x:commandLink id="modificarTarea"
							action="#{TareaBean.irAModificarTarea}" forceId="true"
							style="display: block;" />
						<x:commandLink id="listarTarea"
							action="#{TareaBean.irAListarTarea}" forceId="true"
							style="display: block;" />

						<h:panelGrid id="panelPrincipalUno" columns="2" width="449"
							align="center">
							<h:outputText id="outTitulo" value="Titulo de tarea: "
								styleClass="texto" />
							<h:inputText id="inTitulo" value="#{TareaBean.tarea.titulo}"
								styleClass="bordeceldatext" style=" width : 195px;" 
								onfocus="encender(this);" onblur="apagar(this);" />
							
							<h:outputText id="outTipoTarea" value="Tiipo de tarea: "
								styleClass="texto" />
							<h:selectOneMenu id="lstTipo"
								value="#{TareaBean.tipoTareaSeleccionado}" styleClass="lista"
								valueChangeListener="#{TareaBean.cambioTipoTarea}" onchange="submit();"
								immediate="true" onfocus="encender(this);" binding="#{TareaBean.tipoTareaHtml}"
								onblur="apagar(this);" style=" width : 195px;">
								<f:selectItems value="#{TareaBean.tipoTareas}" />
							</h:selectOneMenu>

							<h:outputText id="outComentario" value="Comentario: "
								styleClass="texto" />
							<h:inputText id="inComentario"
								value="#{TareaBean.tarea.comentario}" 
								styleClass="bordeceldatext" style=" width : 195px;"
								onfocus="encender(this);" onblur="apagar(this);" />

							<h:outputText id="outDuracion" value="Duración de la Tarea: "
								styleClass="texto" />
							
							<h:panelGroup>
							<h:inputText id="inDuracion" value="#{TareaBean.tarea.duracion}"
								size="10" maxlength="3" styleClass="bordeceldainferior"
								style=" width : 33px;" onfocus="encender(this);"
								onblur="apagar(this);"
								onkeypress="return soloEnteros(this,event);" />
							
							<h:outputText id="outHs" value=" Horas." styleClass="texto" />
							
							</h:panelGroup>
							
							<h:panelGroup>
								<h:outputText value="Activa" styleClass="texto" />

								<x:selectBooleanCheckbox id="chkActivo"
									value="#{TareaBean.tarea.activo}" />

							</h:panelGroup>
							
							<f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim>
						</h:panelGrid>
							
						<c:if test="${TareaBean.automatica}">
							<h:panelGrid columns="2" align="center" width="449">
								<h:outputText id="outJobExterno" value="Job Externo: "
									styleClass="texto" />
								<h:inputText id="inJobExterno"
									value="#{TareaBean.tarea.jobExterno}" maxlength="50"
									styleClass="bordeceldatext" style=" width : 195px;"
									onfocus="encender(this);" onblur="apagar(this);" />
							</h:panelGrid>
						</c:if>
						
						<c:if test="${!TareaBean.automatica}">
							<h:panelGrid columns="2" align="center" width="449">
								<h:outputText id="outFormExt" value="Formulario Externo:"
									styleClass="texto" />
								<h:selectOneMenu id="lstFormularioExterno"
									value="#{TareaBean.tipoFormExtSeleccionado}" styleClass="lista"
									immediate="true" onfocus="encender(this);"
									onblur="apagar(this);" style=" width : 195px;">
									<f:selectItems value="#{TareaBean.tipoFormExt}" />
								</h:selectOneMenu>
								
								<h:outputText id="outDocumCont" value="Documento Contractual:"
									styleClass="texto" />
								<h:selectOneMenu id="lstDocumentoContractual"
									value="#{TareaBean.tipoDocCompSeleccionado}" styleClass="lista"
									immediate="true" onfocus="encender(this);"
									onblur="apagar(this);" style=" width : 195px;">
									<f:selectItems value="#{TareaBean.tipoFormExt}" />
								</h:selectOneMenu>
							</h:panelGrid>
						</c:if>

						<f:verbatim>
							<hr align="center" width="700">
						</f:verbatim>
						<h:panelGrid columns="10" width="727" id="panelBotonera">
							<f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim>
							<f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim>
							<f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim>
							<f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim>
							<f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim>
							<f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim>
							<f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim>
							<f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim>
							<x:commandButton id="buttonGrabar" value="Guardar"
								actionListener="#{TareaBean.grabarTareaListener}"
								styleClass="botones" />
							<x:commandButton id="buttonCancelar" value="Cancelar"
								action="#{TareaBean.cancelar}" styleClass="botones" />
						</h:panelGrid>
					</h:panelGrid>
				</h:panelGroup>
			</f:facet>

		</x:panelLayout>
	</h:form></center>


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
    document.getElementById("sideMenu").innerHTML += generateMenu(obj,"mainMenu__idJsp2_menu:A]\#{TareaBean.inicializar}") + `</li>`;
}

</script>

<%@include file="/inc/scripts.jsp" %>


	</body>
	</html>
</f:view>