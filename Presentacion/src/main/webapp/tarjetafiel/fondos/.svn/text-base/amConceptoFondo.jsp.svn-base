<%@include file="/inc/tags.jsp" %>
<jsp:useBean id="ahora" class="java.util.Date" scope="request"/>

<f:view>
<html lang="es">
<head>
	<title><h:outputText value="#{ConceptoFondoBean.tituloLargo}"/></title>
	<s:script language="javascript">
		function recargar() {
			document.getElementById('amConceptoForm').submit();
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
<jsp:include page="/inc/includes.jsp" />

<body class="hold-transition skin-blue sidebar-mini" onbeforeunload="ShowWait('amConceptoFondoForm');" onload="if('${ConceptoFondoBean.popup.mostrar}'=='true') {viewDialog.show();} else {window.scrollTo(0,${ScrollBean.hiddenScrollY});}">
	<h:form id="mainMenu" style="display: none">
  <jsp:include page="/inc/navigation_test.jsp" />
  <x:commandLink id="cerrarSesion" action="#{LoginBean.salir}"/>
</h:form>

<jsp:include page="/inc/header.jsp" />

<!-- Content Header (Page header) -->
<section class="content-header">
  <h1>
    ${ConceptoFondoBean.tituloCorto}
    <small>${ConceptoFondoBean.tituloLargo}</small>
  </h1>
</section>

<section class="content">

<div class="box box-default">
<div class="box-header"><h3></h3></div>
<center>
	<secure:check/>

	<h:form id="amConceptoFondoForm">

	<%-- GRABA EL ESTADO DEL SCROLL(Dibujar solo si el popup no se muestra) --%>
	<h:panelGroup rendered="#{!ConceptoFondoBean.popup.mostrar}">
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
								dialogTitle="#{ConceptoFondoBean.tituloCorto}">
						<h:panelGrid columns="2" width="500">
							<x:graphicImage value="/img/#{ConceptoFondoBean.popup.nombreIcono}" />
							<h:outputText value="#{ConceptoFondoBean.popup.mensaje}" styleClass="texto"/>
						</h:panelGrid>
						<h:panelGrid columns="3" width="500">
							<x:commandButton action="#{ConceptoFondoBean.irANuevoConcepto}" 
								onclick="clickLink('nuevoConcepto');dojo.widget.byId('viewDialog').hide();"
								value="Nuevo" styleClass="botones" title="Agregar nuevo."/>
							<x:commandButton action="#{ConceptoFondoBean.irAModificarConcepto}" 
								onclick="clickLink('modificarConcepto');dojo.widget.byId('viewDialog').hide();"
								value="Modificar" styleClass="botones" title="Seguir modificando."/>
							<x:commandButton action="#{ConceptoFondoBean.irAListarConcepto}" 
								onclick="clickLink('listarConcepto');dojo.widget.byId('viewDialog').hide();"
								value="Listar" styleClass="botones" title="Ir al listado."/>
						</h:panelGrid>
					</s:modalDialog>
					<x:commandLink id="nuevoConcepto" action="#{ConceptoFondoBean.irANuevoConcepto}" forceId="true" style="display: block;"/>
					<x:commandLink id="modificarConcepto" action="#{ConceptoFondoBean.irAModificarConcepto}" forceId="true" style="display: block;"/>
					<x:commandLink id="listarConcepto" action="#{ConceptoFondoBean.irAListarConcepto}" forceId="true" style="display: block;"/>


                    <s:fieldset id="fielAlta" legend="Concepto de Fondos">
						<h:panelGrid columns="2" id="decisionFielComercio" align="center">
							<h:outputText value="Código:" styleClass="texto"/>
							<h:inputText id="codigoConcepto" value="#{ConceptoFondoBean.concepto.codigoConceptoFondo}" 
							onkeypress="return soloEnteros(this,event);" disabled="#{!ConceptoFondoBean.alta}"
							styleClass="bordeceldatext" maxlength="3" size="8"
							style="width: 40px" onfocus="encender(this);" onblur="apagar(this);"/>
							<h:outputText value="Descripción:" styleClass="texto"/>
							<h:inputText id="descripcion" value="#{ConceptoFondoBean.concepto.descripcion}"
							styleClass="bordeceldatext" maxlength="70" size="17"
							style="width: 220px" onfocus="encender(this);" onblur="apagar(this);"/>
							<h:outputText value="Clase del concepto: " styleClass="texto"/>
							<h:selectOneMenu id="lstClase" value="#{ConceptoFondoBean.idClaseSeleccionada}"
										valueChangeListener="#{ConceptoFondoBean.cambioClase}" onchange="submit();"
										styleClass="lista" immediate="true" onfocus="encender(this);"
										onblur="apagar(this);" style=" width : 300px;" 
										binding="#{ConceptoFondoBean.claseHtml}">
								<f:selectItems id="claseItems" value="#{ConceptoFondoBean.clase.selectItems}"/>
							</h:selectOneMenu>
							<h:outputText value="Target:" styleClass="texto" rendered="#{ConceptoFondoBean.verDetalles}"/>
							<h:selectOneMenu id="lstTargeta" value="#{ConceptoFondoBean.tarSeleccionado}" rendered="#{ConceptoFondoBean.verDetalles}"
									styleClass="lista" immediate="true" onfocus="encender(this);"
									onblur="apagar(this);" >
								<f:selectItems value="#{ConceptoFondoBean.listaTar}"/>
							</h:selectOneMenu>
						</h:panelGrid>
					</s:fieldset>
					
					<s:fieldset legend="Cuenta Unica" id="cuentaUnica" rendered="#{ConceptoFondoBean.verDetalles}">
						<h:panelGrid id="panelCuentaUnica" columns="6" width="350" align="center">
							<h:outputText value="Cuenta: " styleClass="texto"/>
							<h:selectOneMenu id="lstCuentaUnica" value="#{ConceptoFondoBean.detalleUnico.ctacontable}" 
								styleClass="lista" immediate="true" onfocus="encender(this);" 
								onblur="apagar(this);">
								<f:selectItems value="#{ConceptoFondoBean.cuentaUnicaItems}"/>
							</h:selectOneMenu>
							<h:outputText value="Descripción: " styleClass="texto"/>
							<h:inputText id="descripcionCuenta" value="#{ConceptoFondoBean.detalleUnico.nombre}"
										styleClass="bordeceldatext" maxlength="70" size="17"
										style="width: 150px" onfocus="encender(this);" onblur="apagar(this);"/>
							<h:outputText value="Signo: "  styleClass="texto"/>
							<h:selectOneRadio value="#{ConceptoFondoBean.signo}" id="selectOneRadio" disabled="#{ConceptoFondoBean.bloquearSigno}">
								<f:selectItems id="itemsSigno" value="#{ConceptoFondoBean.signoItems}"/>
							</h:selectOneRadio>
						</h:panelGrid>
					</s:fieldset>

				<%-- GESTIONAR LOS DETALLES DEL CONCEPTO  --%> 

					<s:fieldset legend="Compartidas" id="compartidas" rendered="#{ConceptoFondoBean.verDetalles}">
						<h:panelGrid columns="2" id="panelDetalles" width="650" align="center">
							<h:dataTable value="#{ConceptoFondoBean.compartidasList}" 
								id="tablaDetallesConcepto" styleClass="standardTable" headerClass="dataTable_Header"
								footerClass="standardTable_Header" rowClasses="standardTable_Row1,standardTable_Row2"
								columnClasses="standardTable_Column,standardTable_ColumnCentered,standardTable_Column"
								var="detalleConcepto" style=" width : 600px;">
								<h:column>
									<f:facet name="header">
										<h:outputText value="Cuenta" styleClass="texto"  />
									</f:facet>
									<h:selectOneMenu id="lstCuentaCompartida" value="#{detalleConcepto.detalle.ctacontable}" 
										styleClass="lista" immediate="true" onfocus="encender(this);" 
										onblur="apagar(this);">
										<f:selectItems value="#{ConceptoFondoBean.cuentaCompartidaItems}"/>
									</h:selectOneMenu>
								</h:column>
								<h:column>
									<f:facet name="header">
										<h:outputText value="Descripción" styleClass="texto"  />
									</f:facet>
									<h:inputText id="descripcionCuenta" value="#{detalleConcepto.detalle.nombre}"
										styleClass="bordeceldatext" maxlength="70" size="17"
										style="width: 150px" onfocus="encender(this);" onblur="apagar(this);"/>
								</h:column>
								<h:column>
									<x:commandLink action="#{detalleConcepto.borrar}" id="eliminarDetalleLink">
										<f:param id="idDetalleElim" name="idDetalleElim" value="#{detalleConcepto.indice}"/>
										<x:graphicImage value="/img/cat_act.gif" title="Eliminar un Detalle." border="0" id="botonImagenTres" />
									</x:commandLink>
								</h:column>
							</h:dataTable>
							<x:commandButton id="agregardetalleButton" action="#{ConceptoFondoBean.agregarDetalle}" image="/img/cat_pad.gif"/>
						</h:panelGrid>
					</s:fieldset>
					
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
						<x:commandButton id="buttonGrabar" value="Guardar" action="#{ConceptoFondoBean.grabar}" styleClass="botones"/>
						<x:commandButton id="buttonCancelar" value="Cancelar" action="#{ConceptoFondoBean.cancelar}" styleClass="botones" />
					</h:panelGrid>

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
    document.getElementById("sideMenu").innerHTML += generateMenu(obj,"mainMenu__idJsp2_menu:A]\#{ConceptoFondoBean.inicializar}") + `</li>`;
}

</script>

<%@include file="/inc/scripts.jsp" %>

</body>
</html>
</f:view>
