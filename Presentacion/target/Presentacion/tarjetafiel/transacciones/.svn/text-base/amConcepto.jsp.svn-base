<%@include file="/inc/tags.jsp" %>
<jsp:useBean id="ahora" class="java.util.Date" scope="request"/>

<f:view>
<html>
<head>
	<title><h:outputText value="#{ConceptoBean.tituloLargo}"/></title>
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

<jsp:include page="/inc/includes.jsp"/>

<body class="hold-transition skin-blue sidebar-mini" onbeforeunload="ShowWait('amConceptoForm');" onload="if('${ConceptoBean.popup.mostrar}'=='true') {viewDialog.show();} else {window.scrollTo(0,${ScrollBean.hiddenScrollY});}">

<h:form id="mainMenu" style="display: none">
	<jsp:include page="/inc/navigation_test.jsp" />
	<x:commandLink id="cerrarSesion" action="#{LoginBean.salir}"/>
</h:form>

<jsp:include page="/inc/header.jsp" />

<!-- Content Header (Page header) -->
<section class="content-header">
  <h1>
    ${ConceptoBean.tituloCorto}
    <small>${ConceptoBean.tituloLargo}</small>
  </h1>
</section>

<section class="content">
	<div class="box box-default">
		<div class="box-header"><h3></h3>
		</div>

<center>
	<secure:check/>

	<h:form id="amConceptoForm">

	<%-- GRABA EL ESTADO DEL SCROLL(Dibujar solo si el popup no se muestra) --%>
	<h:panelGroup rendered="#{!ConceptoBean.popup.mostrar}">
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
								dialogTitle="#{ConceptoBean.tituloCorto}">
						<h:panelGrid columns="2" width="500">
							<x:graphicImage value="/img/#{ConceptoBean.popup.nombreIcono}" />
							<h:outputText value="#{ConceptoBean.popup.mensaje}" styleClass="texto"/>
						</h:panelGrid>
						<h:panelGrid columns="3" width="500">
							<x:commandButton action="#{ConceptoBean.irANuevoConcepto}" 
								onclick="clickLink('nuevoConcepto');dojo.widget.byId('viewDialog').hide();"
								value="Nuevo" styleClass="btn btn-primary btn-flat" title="Agregar nueva."/>
							<x:commandButton action="#{ConceptoBean.irAModificarConcepto}" 
								onclick="clickLink('modificarConcepto');dojo.widget.byId('viewDialog').hide();"
								value="Modificar" styleClass="btn btn-primary btn-flat" title="Seguir modificando."/>
							<x:commandButton action="#{ConceptoBean.irAListarConcepto}" 
								onclick="clickLink('listarConcepto');dojo.widget.byId('viewDialog').hide();"
								value="Listar" styleClass="btn btn-primary btn-flat" title="Ir al listado."/>
						</h:panelGrid>
					</s:modalDialog>
					<x:commandLink id="nuevoConcepto" action="#{ConceptoBean.irANuevoConcepto}" forceId="true" style="display: block;"/>
					<x:commandLink id="modificarConcepto" action="#{ConceptoBean.irAModificarConcepto}" forceId="true" style="display: block;"/>
					<x:commandLink id="listarConcepto" action="#{ConceptoBean.irAListarConcepto}" forceId="true" style="display: block;"/>

					<h:panelGrid columns="2" id="decisionFielComercio" align="center" rendered="#{ConceptoBean.mostrarOpciones}">
                        	<h:outputText value="Seleccione que tipo de concepto desea dar de alta: " styleClass="texto" style="color:green" />
						    <f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim>
							<h:selectOneRadio value="#{ConceptoBean.tipoLista}" id="tipoCuent" styleClass="radioB">
								<f:selectItem itemLabel="Concepto Comercio" itemValue="N" />
								<f:selectItem itemLabel="Concepto Fiel" itemValue="S" />
							</h:selectOneRadio>
                            <f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim>
                            <f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim>
                            <h:commandButton action="#{ConceptoBean.aceptarTipoLista}" value="Aceptar" id="decidirTipoLista" styleClass="btn btn-primary btn-flat"/>
                    </h:panelGrid>


                    <s:fieldset id="fielAlta" legend="Concepto" rendered="#{!ConceptoBean.mostrarOpciones}">
					<h:panelGrid id="panelPrincipalUno" columns="7" width="350" align="center">
						<h:outputText value="Descripcion:" styleClass="texto"/>
						<f:verbatim>&nbsp;&nbsp;</f:verbatim>
						<h:inputText id="descripcionFiltro" value="#{ConceptoBean.concepto.descripcion}"
						styleClass="bordeceldatext" maxlength="70" size="17"
						style="width: 220px" onfocus="encender(this);" onblur="apagar(this);"/>
						<f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim>
						<h:outputText value="Código:" styleClass="texto"/>
						<f:verbatim>&nbsp;&nbsp;</f:verbatim>
						<h:inputText id="codigoConceptoFiltro" value="#{ConceptoBean.concepto.codigoConcepto}" onkeypress="return soloEnteros(this,event);"
						styleClass="bordeceldatext" maxlength="70" size="17"
						style="width: 220px" onfocus="encender(this);" onblur="apagar(this);"/>

						<f:verbatim>&nbsp;</f:verbatim>
						<f:verbatim>&nbsp;</f:verbatim>
						<f:verbatim>&nbsp;</f:verbatim>
						<f:verbatim>&nbsp;</f:verbatim>
						<f:verbatim>&nbsp;</f:verbatim>
						<f:verbatim>&nbsp;</f:verbatim>
						<f:verbatim>&nbsp;</f:verbatim>
					

						<h:outputText value="Sucursal:" styleClass="texto"/>
						<f:verbatim>&nbsp;&nbsp;</f:verbatim>
						<h:selectOneMenu id="lstSucursal" value="#{ConceptoBean.idSucursalSeleccionada}" binding="#{ConceptoBean.sucSeleccionada}"
							styleClass="lista" immediate="true" onfocus="encender(this);" disabled="true"
							onblur="apagar(this);">
							<f:selectItems value="#{ConceptoBean.sucursalItems}"/>
						</h:selectOneMenu>
						<f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim>
						<h:outputText value="Clase: "  styleClass="texto"/>
						<f:verbatim>&nbsp;&nbsp;</f:verbatim>
						<h:inputText value="#{ConceptoBean.concepto.clase}" styleClass="bordeceldatext" maxlength="250" size="50"
						style="width: 220px" onfocus="encender(this);" onblur="apagar(this);" title="Esta clase se pasará como parametro a todas las clases de los detalles conceptos."/>
					</h:panelGrid>

					<f:verbatim><br/></f:verbatim>

					<h:panelGrid id="altas" columns="10" align="center">
						<h:outputText value="Calcula Disponible:" styleClass="texto"/>
						<f:verbatim>&nbsp;&nbsp;</f:verbatim>
						<h:selectBooleanCheckbox id="calculadisponibleFiltro" value="#{ConceptoBean.calculaDisponibleAux}"
					    onfocus="encender(this);" onblur="apagar(this);"/>
					    <f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim>
						<h:outputText value="Target:" styleClass="texto" rendered="#{!ConceptoBean.perteneceAFiel}"/>
						<h:selectOneMenu id="lstTargeta" value="#{ConceptoBean.tarSeleccionado}" rendered="#{!ConceptoBean.perteneceAFiel}"
								styleClass="lista" immediate="true" onfocus="encender(this);" binding="#{ConceptoBean.menuTar}"
								onblur="apagar(this);" >
						<f:selectItems value="#{ConceptoBean.listaTar}"/>
						</h:selectOneMenu>
						<f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim>
						<h:outputText value="Es producto Fiel:" styleClass="texto"/>
						<f:verbatim>&nbsp;&nbsp;</f:verbatim>
						<h:selectBooleanCheckbox value="#{ConceptoBean.perteneceAFiel}" id="checkEsdeFiel" disabled="true"/>
					</h:panelGrid>

					<f:verbatim><br/></f:verbatim>

					</s:fieldset>
					
					
					
							<%-- GESTIONAR LOS DETALLES DEL CONCEPTO --%>

								<s:fieldset legend="Detalles del Concepto" id="detalles" rendered="#{!ConceptoBean.mostrarOpciones}">
								<h:panelGrid columns="2" id="panelDetalles" width="650" align="center">
									<h:panelGroup id="panelGroupDetalle">
									<h:dataTable value="#{ConceptoBean.listDetalles}"
										id="tablaDetallesConcepto" styleClass="table-bordered" headerClass="dataTable_Header"
										footerClass="standardTable_Header" rowClasses="standardTable_Row1,standardTable_Row2"
										columnClasses="standardTable_Column,standardTable_ColumnCentered,standardTable_Column"
										var="detalleConcepto" style=" width : 600px;">
										
										<h:column>
											<f:facet name="header">
												<h:outputText value="Descripción" styleClass="texto tdA" />
											</f:facet>
											<h:outputText value="#{detalleConcepto.detalle.nombre}"/>
										</h:column>
										
										<h:column>
											<f:facet name="header">
												<h:outputText value="Activo" styleClass="texto tdA" />
											</f:facet>
											<h:outputText value="#{detalleConcepto.detalle.activo}"/>
										</h:column>
										
										<h:column>
											<f:facet name="header">
												<h:outputText value="Fecha Desde" styleClass="texto tdA" />
											</f:facet>
											<h:outputText value="#{detalleConcepto.detalle.fechavigenciadesde}"/>
										</h:column>
										
										<h:column>
											<f:facet name="header">
												<h:outputText value="Fecha Hasta" styleClass="texto tdA" />
											</f:facet>
											<h:outputText value="#{detalleConcepto.detalle.fechavigenciahasta}" />
										</h:column>
										
										<h:column>
											<x:commandLink action="#{ConceptoBean.editarDetalle}" id="editarDetalleLink">
												<f:param id="idDetalleEdi" name="idDetalleEdi" value="#{detalleConcepto.indice}" />
												<x:graphicImage value="/img/editar.gif" title="Editar el Detalle." border="0" id="botonImagenDos" />
											</x:commandLink>
										</h:column>

										<h:column>
											<x:commandLink action="#{ConceptoBean.eliminarDetalle}" id="eliminarDetalleLink">
												<f:param id="idDetalleElim" name="idDetalleElim" value="#{detalleConcepto.indice}"/>
												<x:graphicImage value="/img/cat_act.gif" title="Eliminar un Detalle." border="0" id="botonImagenTres" />
											</x:commandLink>
										</h:column>
										
									</h:dataTable>
									</h:panelGroup>
									
									<h:panelGroup id="panelGroupBotones">
										<x:commandLink id="agregardetalleLink" action="#{ConceptoBean.agregarDetalle}">
											<x:graphicImage value="/img/cat_pad.gif" title="Agregar Detalle." border="0" id="botonImagenCuatro"/>
										</x:commandLink>
									</h:panelGroup>

								</h:panelGrid>
							</s:fieldset>
					
					
					
					
					
					<f:verbatim>
						<br>
					</f:verbatim>
					<f:verbatim><hr align="center" width="700"></f:verbatim>
					<h:panelGrid columns="10" width="700" id="panelBotonera" rendered="#{!ConceptoBean.mostrarOpciones}">
						<f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim>
						<f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim>
						<f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim>
						<f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim>
						<f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim>
						<f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim>
						<f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim>
						<f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim>
						<x:commandButton id="buttonGrabar" value="Guardar" action="#{ConceptoBean.grabar}" styleClass="btn btn-primary btn-flat"/>
						<x:commandButton id="buttonCancelar" value="Cancelar" action="#{ConceptoBean.cancelar}" styleClass="btn btn-primary btn-flat" />
					</h:panelGrid>
					</h:panelGrid>
				</h:panelGroup>
			</f:facet>

		</x:panelLayout>
	</h:form>

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
    document.getElementById("sideMenu").innerHTML += generateMenu(obj,"mainMenu__idJsp2_menu:A]\#{ConceptoBean.inicializar}") + `</li>`;
}

</script>

<%@include file="/inc/scripts.jsp" %>


</center>
</body>
</html>
</f:view>
