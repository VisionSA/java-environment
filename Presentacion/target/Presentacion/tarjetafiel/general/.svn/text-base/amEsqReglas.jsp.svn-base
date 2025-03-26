<%@include file="/inc/tags.jsp" %>
<jsp:useBean id="ahora" class="java.util.Date" scope="request"/>

<f:view>
<html lang="es">
<head>
	<title><h:outputText value="#{EsquemaReglaBean.tituloLargo}"/></title>
	<s:script language="javascript">
		function recargar() {
			document.getElementById('amEsqReglasForm').submit();
		};
		function popup(pagina,popW,popH) {
			var w = 0, h = 0;
		   	w = screen.width;
		   	h = screen.height;

			var leftPos = (w-popW)/2, topPos = (h-popH)/2;

		    popupWindow=open(pagina,'','resizable=no,scrollbars=yes,width='+popW+',height='+popH+',top='+topPos+',left='+leftPos);
		    if (popupWindow.opener == null){popupWindow.opener = self;}
		};
	</s:script>
</head>

<jsp:include page="/inc/includes.jsp" />>

	<body class="hold-transition skin-blue sidebar-mini" onbeforeunload="ShowWait('amEsqReglasForm');"
		onload="if('${EsquemaReglaBean.popup.mostrar}'=='true') {viewDialog.show();} else {window.scrollTo(0,${ScrollBean.hiddenScrollY});}">
	<h:form id="mainMenu" style="display: none">
  <jsp:include page="/inc/navigation_test.jsp" />
  <x:commandLink id="cerrarSesion" action="#{LoginBean.salir}"/>
</h:form>

<jsp:include page="/inc/header.jsp" />

<!-- Content Header (Page header) -->
<section class="content-header">
  <h1>
    ${EsquemaReglaBean.tituloCorto}
    <small>${EsquemaReglaBean.tituloLargo}</small>
  </h1>
</section>

<section class="content">

<div class="box box-default">
<div class="box-header"><h3></h3></div>
	<center>
		<secure:check /> <h:form id="amEsqReglasForm">

		<%-- GRABA EL ESTADO DEL SCROLL(Dibujar solo si el popup no se muestra) --%>
		<h:panelGroup rendered="#{!EsquemaReglaBean.popup.mostrar}">
			<%@include file="/inc/scroll.inc"%>
		</h:panelGroup>

		<x:panelLayout id="page" layout="#{globalOptions.pageLayout}"
			styleClass="pageLayout" headerClass="pageHeader"
			navigationClass="pageNavigation" bodyClass="pageBody"
			footerClass="pageFooter">

			
			<f:facet name="body">
				<h:panelGroup id="body">
					
					<h:panelGrid columns="1" align="center" id="PanelPricipal">
						<%-- INCLUDE PARA LOS ERRORES --%>
						<h:panelGroup id="errores">
							<jsp:include page="/inc/error.jsp" />
						</h:panelGroup>

						<%-- POPUP PARA LOS MENSAJES DE EXITO Y FRACASO --%>
						<s:modalDialog dialogId="viewDialog" dialogVar="viewDialog"
							styleClass="viewDialog"
							dialogTitle="#{EsquemaReglaBean.tituloCorto}">
							<h:panelGrid columns="2" width="500">
								<x:graphicImage
									value="/img/#{EsquemaReglaBean.popup.nombreIcono}" />
								<h:outputText value="#{EsquemaReglaBean.popup.mensaje}"
									styleClass="texto" />
							</h:panelGrid>
							<h:panelGrid columns="2" width="500">
								<x:commandButton
									action="#{EsquemaReglaBean.irASeguirModificando}"
									onclick="clickLink('seguirModificando');dojo.widget.byId('viewDialog').hide();"
									value="Seguir modificando" styleClass="btn btn-primary btn-flat pull-right"
									title="Seguir modificando." />
								<x:commandButton action="#{EsquemaReglaBean.irASalir}"
									onclick="clickLink('salir');dojo.widget.byId('viewDialog').hide();"
									value="Salir" styleClass="btn btn-primary btn-flat pull-right" title="Salir." />
							</h:panelGrid>
						</s:modalDialog>
						<x:commandLink id="seguirModificando"
							action="#{EsquemaReglaBean.irASeguirModificando}" forceId="true"
							style="display: block;" />
						<x:commandLink id="salir" action="#{EsquemaReglaBean.irASalir}"
							forceId="true" style="display: block;" />

						<h:panelGrid id="uno" columns="1" width="850" align="center">
							<s:fieldset legend="Esquema">
								<h:panelGrid id="panelPrincipalUno" columns="5" width="600"
									align="center">

									<h:outputText value="Esquema" styleClass="texto" />
									<h:selectOneMenu id="lstEsq"
										value="#{EsquemaReglaBean.idEsquemaSeleccionado}"
										styleClass="lista" immediate="true" onfocus="encender(this);"
										onblur="apagar(this);" style="width: 200px"
										binding="#{EsquemaReglaBean.esquema}"
										valueChangeListener="#{EsquemaReglaBean.cambiarEsquema}"
										onchange="submit();">
										<f:selectItems id="itemEsq"
											value="#{EsquemaReglaBean.esquemaItems}" />
									</h:selectOneMenu>
									<f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim>
									<f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim>
									<f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim>

									<h:outputText value="Reglas" styleClass="texto" />
									<h:selectOneMenu id="lstReglas"
										value="#{EsquemaReglaBean.idReglaSeleccionada}"
										styleClass="lista" immediate="true" onfocus="encender(this);"
										onblur="apagar(this);" style="width: 600px">
										<f:selectItems id="itemReglas"
											value="#{EsquemaReglaBean.reglasItems}" />
									</h:selectOneMenu>
									<x:commandLink id="AddReglaLink"
										action="#{EsquemaReglaBean.addRegla}">
										<x:graphicImage value="/img/cat_pad.gif"
											title="Agregar reglas al esquema." border="0" />
									</x:commandLink>
									<x:commandLink id="EditReglaLink"
										action="#{EsquemaReglaBean.editarRegla}">
										<x:graphicImage value="/img/editar.gif" title="Editar regla."
											border="0" />
									</x:commandLink>
									<x:commandLink id="NuevaReglaLink"
										action="#{EsquemaReglaBean.nuevaRegla}">
										<x:graphicImage value="/img/icon/docs_16.png"
											title="Crear nueva regla." border="0" />
									</x:commandLink>
								</h:panelGrid>
							</s:fieldset>
							<h:dataTable value="#{EsquemaReglaBean.tablaEsqRegra}"
								id="tablaReglas" styleClass="standardTable"
								headerClass="standardTable_Header"
								footerClass="standardTable_Header"
								rowClasses="standardTable_Row1,standardTable_Row2"
								columnClasses="standardTable_ColumnRight,standardTable_Column,
                           			 		standardTable_ColumnCentered,standardTable_ColumnCentered,standardTable_Column"
								var="regla" style=" width : 900px;" align="center">

								<h:column>
									<f:facet name="header">
										<h:outputText value="Cod Regla" styleClass="texto"
											/>
									</f:facet>
									<h:outputText value="#{regla.esqRegla.regla.idRegla}"
										styleClass="texto" />
								</h:column>

								<h:column>
									<f:facet name="header">
										<h:outputText value="Descripción" styleClass="texto"
											 />
									</f:facet>
									<h:outputText value="#{regla.esqRegla.regla.descripcion}"
										styleClass="texto" />
								</h:column>

								<h:column>
									<f:facet name="header">
										<h:outputText value="Activa" styleClass="texto"
											/>
									</f:facet>
									<h:selectBooleanCheckbox value="#{regla.activa}" />
								</h:column>

								<h:column>
									<f:facet name="header">
										<h:outputText value="Manual" styleClass="texto"
											/>
									</f:facet>
									<h:selectBooleanCheckbox value="#{regla.manual}" />
								</h:column>

								<h:column>
									<f:facet name="header">
										<h:outputText value="Ponderacion" styleClass="texto"
											/>
									</f:facet>
									<h:selectOneRadio id="radioPonder"
										value="#{regla.esqRegla.ponderacion}">
										<f:selectItems id="itemPonder"
											value="#{EsquemaReglaBean.ponderacionItems}" />
									</h:selectOneRadio>
								</h:column>
							</h:dataTable>
						</h:panelGrid>
						<f:verbatim>
							<br>
						</f:verbatim>
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
								action="#{EsquemaReglaBean.grabar}" styleClass="btn btn-primary btn-flat pull-right" />
							<x:commandButton id="buttonCancelar" value="Cancelar"
								action="#{EsquemaReglaBean.cancelar}" styleClass="btn btn-primary btn-flat pull-right" />
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
    document.getElementById("sideMenu").innerHTML += generateMenu(obj,"mainMenu__idJsp2_menu:A]\#{EsquemaReglaBean.inicializar}") + `</li>`;
}

</script>
<%@include file="/inc/scripts.jsp" %>
	</body>
	</html>
</f:view>
