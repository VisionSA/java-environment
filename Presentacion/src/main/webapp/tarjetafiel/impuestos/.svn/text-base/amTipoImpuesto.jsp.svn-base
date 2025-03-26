<%@include file="/inc/tags.jsp" %>
<jsp:useBean id="ahora" class="java.util.Date" scope="request"/>

<f:view>
<html lang="es">
<head>
	<title><h:outputText value="#{TipoImpuestoBean.tituloLargo}"/></title>
	<s:script language="javascript">
		function recargar() {
			document.getElementById('amTipoImpuestoForm').submit();
		}
		function marcar(obj) {
		 	var len = document.getElementById('amTipoImpuestoForm:tUno').rows.length;
		    var val = obj.checked;
		     	
			for (var i = 0; i < len; i++){
				document.getElementById('amTipoImpuestoForm:tUno:'+i+':estado').checked = val;
			}
		}
	</s:script>
</head>

<jsp:include page="/inc/includes.jsp" />

<body class="hold-transition skin-blue sidebar-mini" onbeforeunload="ShowWait('amTipoImpuestoForm');" onload="if('${TipoImpuestoBean.popup.mostrar}'=='true') {viewDialog.show();} else {window.scrollTo(0,${ScrollBean.hiddenScrollY});}">

<h:form id="mainMenu" style="display: none">
  <jsp:include page="/inc/navigation_test.jsp" />
  <x:commandLink id="cerrarSesion" action="#{LoginBean.salir}"/>
</h:form>

<jsp:include page="/inc/header.jsp" />

<!-- Content Header (Page header) -->
<section class="content-header">
  <h1>
    ${TipoImpuestoBean.tituloCorto}
    <small>${TipoImpuestoBean.tituloLargo}</small>
  </h1>
</section>

<section class="content">

<div class="box box-default">
<div class="box-header"><h3></h3></div>

<center>
	<secure:check/>

	<h:form id="amTipoImpuestoForm">

	<%-- GRABA EL ESTADO DEL SCROLL(Dibujar solo si el popup no se muestra) --%>
	<h:panelGroup rendered="#{!TipoImpuestoBean.popup.mostrar}">
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
								dialogTitle="#{TipoImpuestoBean.tituloCorto}">
						<h:panelGrid columns="2" width="500">
							<x:graphicImage value="/img/#{TipoImpuestoBean.popup.nombreIcono}" />
							<h:outputText value="#{TipoImpuestoBean.popup.mensaje}" styleClass="texto"/>
						</h:panelGrid>
						<h:panelGrid columns="3" width="500">
							<x:commandButton action="#{TipoImpuestoBean.irANuevoTipoImpuesto}" 
								onclick="clickLink('nuevoTipoImpuesto');dojo.widget.byId('viewDialog').hide();"
								value="Nuevo" styleClass="btn btn-primary btn-flat pull-right" title="Agregar nueva."/>
							<x:commandButton action="#{TipoImpuestoBean.irAModificarTipoImpuesto}" 
								onclick="clickLink('modificarTipoImpuesto');dojo.widget.byId('viewDialog').hide();"
								value="Modificar" styleClass="btn btn-primary btn-flat pull-right" title="Seguir modificando."/>
							<x:commandButton action="#{TipoImpuestoBean.irAListarTipoImpuesto}" 
								onclick="clickLink('listarTipoImpuesto');dojo.widget.byId('viewDialog').hide();"
								value="Listar" styleClass="btn btn-primary btn-flat pull-right" title="Ir al listado."/>
						</h:panelGrid>
					</s:modalDialog>
					<x:commandLink id="nuevoTipoImpuesto" action="#{TipoImpuestoBean.irANuevoTipoImpuesto}" forceId="true" style="display: block;"/>
					<x:commandLink id="modificarTipoImpuesto" action="#{TipoImpuestoBean.irAModificarTipoImpuesto}" forceId="true" style="display: block;"/>
					<x:commandLink id="listarTipoImpuesto" action="#{TipoImpuestoBean.irAListarTipoImpuesto}" forceId="true" style="display: block;"/>
					
					<h:panelGrid columns="1" id="uno" width="850" align="center">
						<h:panelGrid id="panelPrincipalUno" columns="4" width="600" align="center">
							<h:outputText value="Descripción:" styleClass="texto"/>
							<h:inputText id="descripcionFiltro" value="#{TipoImpuestoBean.tipoImpuesto.descripcion}"
							styleClass="bordeceldatext" maxlength="20" size="20"
							style="width: 150px" onfocus="encender(this);" onblur="apagar(this);"/>
							<x:commandButton rendered="#{!TipoImpuestoBean.verTablaJurisdiccion}" action="#{TipoImpuestoBean.editarLasJurisdiccionesDelTipoImpuesto}" 
									onclick="return confirm('Los datos editados se guardarán.');" value="Ver/editar jurisdicciones" styleClass="btn btn-primary btn-flat pull-right"/>
							<x:panelGrid id="panelDeLasJuris" rendered="#{TipoImpuestoBean.verTablaJurisdiccion}">
							<s:fieldset legend="Jurisdicciones" id="fieldDeJuris"  >
								<x:div style="OVERFLOW-Y: auto; OVERFLOW-X: auto; WIDTH: 250; HEIGHT: 200px; border: 1px; margin-left: auto; margin-right: auto;">
								<h:dataTable value="#{TipoImpuestoBean.tablaJurisdiccion}"
									id="tUno" styleClass="standardTable" headerClass="dataTable_Header"
									footerClass="standardTable_Header" rowClasses="standardTable_Row1,standardTable_Row2"
									columnClasses="standardTable_Column,standardTable_ColumnCentered,standardTable_Column"
									var="listJuris" style=" width : 200px;">
										
									<h:column>
										<f:facet name="header">
											<h:outputText value="Jurisdicción" styleClass="texto"  />
										</f:facet>
										<h:outputText value="#{listJuris.jurisdiccion.descripcion}" style=" width : 175px;" styleClass="texto"/>
									</h:column>
											
									<h:column>
										<f:facet name="header">
											<h:panelGroup>
												<f:facet name="header">
													<h:outputText value="Todos" id="todos" styleClass="texto" />
												</f:facet>
												<h:selectBooleanCheckbox value="#{TipoImpuestoBean.todos}" id="boolTodos"  onclick="marcar(this);"/>
											</h:panelGroup>
										</f:facet>
										<h:selectBooleanCheckbox value="#{listJuris.estado}" style="width: 25px" id="estado"/>
									</h:column>
											
								</h:dataTable>
								</x:div>
								<h:panelGrid id="panelSoporte" align="center">
								<x:commandButton action="#{TipoImpuestoBean.grabarLasJurisdicciones}" id="botonSumbit" value="Aceptar" styleClass="btn btn-primary btn-flat pull-right"/>
								</h:panelGrid>
							</s:fieldset>	
							</x:panelGrid>
							</h:panelGrid>
							<s:fieldset legend="Listado de Categorías" rendered="#{!TipoImpuestoBean.verTablaJurisdiccion}">
							<h:panelGrid id="panelcontabla" width="650" columns="2" align="center" rendered="#{!TipoImpuestoBean.verTablaJurisdiccion}">
							<h:panelGroup>
								<h:dataTable value="#{TipoImpuestoBean.listCategorias}" align="center"  title="Categorias Asociadas"
										id="tablaTelefono" styleClass="standardTable" headerClass="dataTable_Header"
										footerClass="standardTable_Header" rowClasses="standardTable_Row1,standardTable_Row2"
										columnClasses="standardTable_ColumnCentered,standardTable_ColumnCentered,"
										var="objetoCategoria" style=" width : 600px;">
										<h:column>
											<f:facet name="header">
												<h:outputText value="Código" styleClass="texto"  />
											</f:facet>
											<h:inputText value="#{objetoCategoria.categoria.codCategoria}" disabled="#{!objetoCategoria.editable}" maxlength="2"/>
										</h:column>
										<h:column>
											<f:facet name="header">
												<h:outputText value="Descripción" styleClass="texto"  />
											</f:facet>
											<h:inputText value="#{objetoCategoria.categoria.descripcion}" disabled="#{!objetoCategoria.editable}"/>
										</h:column>
										<h:column>
											<x:commandLink action="#{TipoImpuestoBean.editarCategoria}" id="editarCategoriaLink">
												<f:param id="idCatEdi" name="idCatEdi" value="#{objetoCategoria.indiceTabla}" />
												<x:graphicImage value="/img/editar.gif" title="Edita la Categoria." border="0" id="botonImagenDosA" />
											</x:commandLink>
										</h:column>
										<h:column>
											<x:commandLink id="eliminarCategoriaLink"  action="#{TipoImpuestoBean.eliminarCategoria}" >
												<x:graphicImage value="/img/cat_act.gif" title="Eliminar una Categoria." border="0" id="botonImagenTresA" />
												<f:param id="idCatEli" name="idCatEli" value="#{objetoCategoria.indiceTabla}"/>
											</x:commandLink>
										</h:column>
								</h:dataTable>
							</h:panelGroup>
									
							<h:panelGroup id="panelGroupBotones">
								<x:commandLink id="agregarCategoriaLink" action="#{TipoImpuestoBean.agregarCategoria}"  >
									<x:graphicImage value="/img/cat_pad.gif" title="Agregar Categoria." border="0" id="botonImagenCuatroA"/>
								</x:commandLink>
							</h:panelGroup>	
						</h:panelGrid>
	                </s:fieldset>

		            <s:fieldset legend="#{TipoImpuestoBean.tituloDelPanelActividades}" rendered="#{!TipoImpuestoBean.verTablaJurisdiccion}">	
						<h:panelGrid id="panelcontablaActividades" width="650" columns="2" align="center" rendered="#{!TipoImpuestoBean.verTablaJurisdiccion}">
							<h:panelGroup>
								<x:dataTable value="#{TipoImpuestoBean.listActividades}" align="center" title="Actividades"
											id="tablaActividades" styleClass="standardTable" headerClass="dataTable_Header"
											footerClass="standardTable_Header" rowClasses="standardTable_Row1,standardTable_Row2"
											columnClasses="standardTable_ColumnCentered,standardTable_ColumnCentered"
											var="objetoActividad" style=" width : 600px;" sortable="true">
									<x:column>
										<f:facet name="header">
											<h:outputText value="Descripcion Actividad" styleClass="texto"  />
										</f:facet>
										<h:inputText value="#{objetoActividad.jurisActividad.descActividad}" disabled="#{!objetoActividad.editable}"/>
									</x:column>
									<x:column>
	                                    <f:facet name="header">
	                                         <h:outputText value="Jurisdicción" styleClass="texto" />
	                                    </f:facet>
	                                    <h:selectOneMenu id="lstJurisActi" value="#{objetoActividad.jurisSeleccionada}" 
			       					             styleClass="lista" onfocus="encender(this);" immediate="true" disabled="#{!objetoActividad.editable}"
			       					             onblur="apagar(this);" style=" width : 180px;">
			       					         <f:selectItems value="#{objetoActividad.listaDeJurisdicciones}" id="selectjuris" />
			       				        </h:selectOneMenu>	
	                                </x:column>
									<x:column defaultSorted="true">
	                                    <f:facet name="header">
	                                         <h:outputText value="Aplicable" styleClass="texto" />
	                                    </f:facet>
	                                    <h:selectOneMenu id="lstApliActi" value="#{objetoActividad.aplicableSeleccionada}" 
			       					             styleClass="lista" onfocus="encender(this);" immediate="true" disabled="#{!objetoActividad.editable}"
			       					             onblur="apagar(this);" style=" width : 180px;">
			       					         <f:selectItems value="#{objetoActividad.listaAplicables}" id="selectjuris" />
			       				        </h:selectOneMenu>	
	                                </x:column>
									<x:column>
										<x:commandLink action="#{TipoImpuestoBean.editarActividad}" id="editarActividaLinka">
											<f:param id="idActEdi" name="idActEdi" value="#{objetoActividad.indiceTablaActividad}" />
											<x:graphicImage value="/img/editar.gif" title="Edita la Categoria." border="0" id="botonImagenDos" />
										</x:commandLink>
									</x:column>
									<x:column>
										<x:commandLink action="#{TipoImpuestoBean.eliminarActividad}" id="eliminarActividaLinka" >
											<f:param id="idActEli" name="idActEli" value="#{objetoActividad.indiceTablaActividad}"/>
											<x:graphicImage value="/img/cat_act.gif" title="Eliminar una Actividad." border="0" id="botonImagenTres" />
										</x:commandLink>
									</x:column>
								</x:dataTable>
							</h:panelGroup>
										
							<h:panelGroup id="panelGroupBotonesDos">
								<x:commandLink id="agregarActividadLink" action="#{TipoImpuestoBean.agregarJurisActividad}">
									<x:graphicImage value="/img/cat_pad.gif" title="Agregar Categoria." border="0" id="botonImagenCuatro"/>
								</x:commandLink>
							</h:panelGroup>	
						</h:panelGrid>
					</s:fieldset>
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
						<x:commandButton id="buttonGrabar" value="Guardar" action="#{TipoImpuestoBean.grabar}" rendered="#{!TipoImpuestoBean.verTablaJurisdiccion}" styleClass="btn btn-primary btn-flat pull-right"/>
						<x:commandButton id="buttonCancelar" value="Cancelar" action="#{TipoImpuestoBean.cancelar}" styleClass="btn btn-primary btn-flat pull-right" />
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
    document.getElementById("sideMenu").innerHTML += generateMenu(obj,"mainMenu__idJsp2_menu:A]\#{TipoImpuestoBean.inicializar}") + `</li>`;
}

</script>
<%@include file="/inc/scripts.jsp" %>
</body>
</html>
</f:view>