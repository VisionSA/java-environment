<%@include file="/inc/tags.jsp" %>
<jsp:useBean id="ahora" class="java.util.Date" scope="request"/>

<f:view>
<html lang="es">
<head>
   	<title><h:outputText value="#{VerificadorEvaluacionBean.tituloLargo}"/></title>
    <s:script language="javascript">
    	function recargar() {
    		document.getElementById('altaVerificadoresForm').submit();
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

<body  class="hold-transition skin-blue sidebar-mini" onbeforeunload="ShowWait('altaVerificadoresForm');" onload="if('${VerificadorEvaluacionBean.popup.mostrar}'=='true') {viewDialog.show();} else {window.scrollTo(0,${ScrollBean.hiddenScrollY});}">
<h:form id="mainMenu" style="display: none">
  <jsp:include page="/inc/navigation_test.jsp" />
  <x:commandLink id="cerrarSesion" action="#{LoginBean.salir}"/>
</h:form>

<jsp:include page="/inc/header.jsp" />

<!-- Content Header (Page header) -->
<section class="content-header">
  <h1>
    ${VerificadorEvaluacionBean.tituloCorto}
    <small>${VerificadorEvaluacionBean.tituloLargo}</small>
  </h1>
</section>

<section class="content">

<div class="box box-default">
<div class="box-header"><h3></h3></div>


<center>
<h:form id="altaVerificadoresForm">

	<%-- GRABA EL ESTADO DEL SCROLL(Dibujar solo si el popup no se muestra) --%> 
	<h:panelGroup rendered="#{!VerificadorEvaluacionBean.popup.mostrar}" id="panelPopup">
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
            	
            	<h:panelGrid columns="1" align="center" id="PanelPricipalVerificadores" width="900">
				<%-- INCLUDE PARA LOS ERRORES --%>
					<h:panelGroup id="errores">
						<jsp:include page="/inc/error.jsp" />
					</h:panelGroup>

					<%-- POPUP PARA LOS MENSAJES DE EXITO Y FRACASO --%>
					<s:modalDialog dialogId="viewDialog"
								dialogVar="viewDialog"
								styleClass="viewDialog"
								dialogTitle="#{VerificadorEvaluacionBean.tituloCorto}">
						<h:panelGrid columns="2" width="500" id="subPanel">
							<x:graphicImage value="/img/#{VerificadorEvaluacionBean.popup.nombreIcono}" />
							<h:outputText value="#{VerificadorEvaluacionBean.popup.mensaje}" styleClass="texto"/>
						</h:panelGrid>
						<h:panelGrid columns="3" width="500" id="subPanelDos">
							<x:commandButton action="#{VerificadorEvaluacionBean.irANuevoVerificador}" 
								id="cmdIr" onclick="clickLink('nuevoVerificador');dojo.widget.byId('viewDialog').hide();"
								value="Nuevo" styleClass="btn btn-primary btn-flat" title="Agregar nuevo verificador."/>
							<x:commandButton action="#{VerificadorEvaluacionBean.irAModificarVerificador}"
								onclick="clickLink('modificarVerificador');dojo.widget.byId('viewDialog').hide();"
								id="cmdModificar" value="Modificar" styleClass="btn btn-primary btn-flat" title="Seguir modificando."/>
							<x:commandButton action="#{VerificadorEvaluacionBean.irAListarVerificador}" 
								onclick="clickLink('listarVerificador');dojo.widget.byId('viewDialog').hide();"
								id="cmdListar"  value="Listar" styleClass="btn btn-primary btn-flat" title="Ir al listado."/>
						</h:panelGrid>
					</s:modalDialog>
					<x:commandLink id="nuevoVerificador" action="#{VerificadorEvaluacionBean.irANuevoVerificador}" forceId="true" style="display: block;"/>
					<x:commandLink id="modificarVerificador" action="#{VerificadorEvaluacionBean.irAModificarVerificador}" forceId="true" style="display: block;"/>
					<x:commandLink id="listarVerificador" action="#{VerificadorEvaluacionBean.irAListarVerificador}" forceId="true" style="display: block;"/>
			    </h:panelGrid>
			    
			    <h:panelGrid id="uno" columns="1" width="850" align="center">
					<s:fieldset legend="Verificador"  id="fieldVerificador">
					
						<h:panelGrid id="panelPrincipalUno" columns="2" width="350" align="center">
							
							<h:outputText value="Nombre:" styleClass="texto"/>
							<h:inputText id="nombreFiltro" value="#{VerificadorEvaluacionBean.verificador.nombre}"
							styleClass="bordeceldatext" maxlength="100" size="100"
							style="width: 150px" onfocus="encender(this);" onblur="apagar(this);"/>
						    <h:outputText value="Apellido:" styleClass="texto"/>
							<h:inputText id="nombreFiltroDos" value="#{VerificadorEvaluacionBean.verificador.apellido}"
							styleClass="bordeceldatext" maxlength="100" size="100"
							style="width: 150px" onfocus="encender(this);" onblur="apagar(this);"/>
							<h:outputText id="outEstado" value="Estado: " styleClass="texto"/>
							<h:selectOneMenu id="lstEstados" value="#{VerificadorEvaluacionBean.estadoSeleccionado}" 
			        					 styleClass="lista" onfocus="encender(this);" onblur="apagar(this);">
		       						<f:selectItems value="#{VerificadorEvaluacionBean.listaEstados}" id="selectEstado"/>
			        		</h:selectOneMenu>
						</h:panelGrid>
						
						<h:panelGrid columns="1" id="panelTres" width="600" align="center">
							<s:fieldset legend="Domicilio" id="fieldDomicilio" >
								<h:panelGrid columns="4" id="panelDomicilio" width="600" align="center">
										<h:outputText id="txtCalle" value="Calle:" styleClass="texto"/>
										<h:outputText id="outTxtCalle" value="#{VerificadorEvaluacionBean.verificador.domicilio.calleNombre}" styleClass="textoblue"/>
										<h:outputText id="txtNumero" value="Número:" styleClass="texto"/>
										<h:outputText id="outTxtNumero" value="#{VerificadorEvaluacionBean.verificador.domicilio.calleNumero}" styleClass="textoblue"/>
										<h:outputText id="txtBarrio" value="Barrio:" styleClass="texto"/>
										<h:outputText id="outTxtBarrio" value="#{VerificadorEvaluacionBean.verificador.domicilio.barrio.descripcion}" styleClass="textoblue"/>
										<h:outputText id="txtLocalidad" value="Localidad:" styleClass="texto"/>
										<h:outputText id="outTxtLocalidad" value="#{VerificadorEvaluacionBean.verificador.domicilio.localidad.nombre}" styleClass="textoblue"/>
								</h:panelGrid>
								<x:commandLink id="agregarDomicilioLink" action="#{VerificadorEvaluacionBean.agregarDomicilioPopup}">
											<x:graphicImage value="/img/icon/home_24.gif" title="Agregar domicilios." border="0" />
								</x:commandLink>
							</s:fieldset>
							<s:fieldset legend="Email:" id="celda"> 
							   <h:panelGrid columns="4" id="panelEmail" width="600" align="center">
									<h:outputText value="Direccion de Email:" styleClass="texto"/>
									<h:inputText id="direccionMail" value="#{VerificadorEvaluacionBean.verificador.email.email}"
									styleClass="bordeceldatext" maxlength="100" size="100"
									style="width: 150px" onfocus="encender(this);" onblur="apagar(this);"/>
								    <h:outputText value="Descripcion: " styleClass="texto"/>
									<h:inputText id="descripcionEMail" value="#{VerificadorEvaluacionBean.verificador.email.descripcion}"
									styleClass="bordeceldatext" maxlength="100" size="100"
									style="width: 150px" onfocus="encender(this);" onblur="apagar(this);"/>
							   </h:panelGrid>
							</s:fieldset>
							<s:fieldset legend="Partido:" id="fieldPartido">
							    <h:panelGrid columns="5" >
										<h:outputText id="outPais" value="Pais: " styleClass="texto" />
										<h:selectOneMenu id="lstPais"
											value="#{VerificadorEvaluacionBean.idPaisSeleccionado}"
											immediate="true" styleClass="lista" onfocus="encender(this);"
											onblur="apagar(this);"
											binding="#{VerificadorEvaluacionBean.paises}"
											style=" width : 200px;"
											valueChangeListener="#{VerificadorEvaluacionBean.cambiarProvincias}"
											onchange="submit();">
											<f:selectItems
												value="#{VerificadorEvaluacionBean.listaPaises}"
												id="selectPaises" />
										</h:selectOneMenu>
										<h:outputText value="    " />

										<h:outputText id="outProvincia" value="Provincia: "
											styleClass="texto" />
										<h:selectOneMenu id="lstProvincia" 
											value="#{VerificadorEvaluacionBean.provinciaSeleccionada}"
											styleClass="lista" onfocus="encender(this);"
											onblur="apagar(this);" onchange="submit();"
											binding="#{VerificadorEvaluacionBean.provincias}"
											style=" width : 200px;" immediate="true"
											valueChangeListener="#{VerificadorEvaluacionBean.cambiarPartidos}">
											<f:selectItems
												value="#{VerificadorEvaluacionBean.listaProvincias}"
												id="selectProvincias" />
										</h:selectOneMenu>

										<h:outputText id="outPartido" value="Partido: "
											styleClass="texto" />
										<h:selectOneMenu id="lstPartido"
											value="#{VerificadorEvaluacionBean.idPartidoSeleccionado}"
											styleClass="lista" onfocus="encender(this);"
											onblur="apagar(this);"
											binding="#{VerificadorEvaluacionBean.partidos}"
											onchange="submit();" style=" width : 200px;">
											<f:selectItems
												value="#{VerificadorEvaluacionBean.listaPartidos}"
												id="selectPartidos" />
										</h:selectOneMenu>
										<h:outputText value="    " />
										<h:outputText value="    " />
										<h:outputText value="    " />
									</h:panelGrid>
							
							</s:fieldset>
							
							
						<s:fieldset legend="Teléfonos" id="telefonos">
								<h:panelGrid columns="2" id="panelTelefono" width="650" align="center">
									<h:dataTable value="#{VerificadorEvaluacionBean.listTelefonos}"
										id="tablaTelefonoVerif" styleClass="standardTable" headerClass="dataTable_Header"
										footerClass="standardTable_Header" rowClasses="standardTable_Row1,standardTable_Row2"
										columnClasses="standardTable_Column,standardTable_ColumnCentered,standardTable_Column"
										var="verificadorTelefono" style=" width : 600px;">
										
										<h:column>
											<f:facet name="header">
												<h:outputText value="Número" styleClass="texto"  id="outNum"/>
											</f:facet>
											<h:outputText value="#{verificadorTelefono.telefono.nroTlefono}" id="outputNumero"/>
										</h:column>
										
										<h:column>
											<f:facet name="header">
												<h:outputText value="Celular" styleClass="texto"  />
											</f:facet>
											<h:outputText value="#{verificadorTelefono.telefono.esCelular}" id="outputCelular"/>
										</h:column>
										
										<h:column>
											<f:facet name="header">
												<h:outputText value="Fax" styleClass="texto"  />
											</f:facet>
											<h:outputText value="#{verificadorTelefono.telefono.esFax}" id="outputFax"/>
										</h:column>
										
										<h:column>
											<f:facet name="header">
												<h:outputText value="Tipo Telefono" styleClass="texto"  />
											</f:facet>
											<h:outputText value="#{verificadorTelefono.telefono.tipo.descripcion}" id="ouputDescripcion" />
										</h:column>
										
										<h:column>
											<f:facet name="header">
												<h:outputText value="Horarios" styleClass="texto"  />
											</f:facet>
											<h:outputText value="#{verificadorTelefono.telefono.horarios}" id="outputHorarios"/>
										</h:column>
										
										<h:column>
											<f:facet name="header">
												<h:outputText value="Descripción" styleClass="texto"  />
											</f:facet>
											<h:outputText value="#{verificadorTelefono.telefono.descripcion}" id="outputDescripcion"/>
										</h:column>
										
										<h:column>
											<x:commandLink action="#{VerificadorEvaluacionBean.editarTelefono}" id="editarTelefonoLink">
												<f:param id="idTelEdiVeri" name="idTelEdiVeri" value="#{verificadorTelefono.idVerifTelefono}" />
												<x:graphicImage value="/img/editar.gif" title="Editar el Teléfono." border="0" />
											</x:commandLink>
										</h:column>

										<h:column>
											<x:commandLink action="#{VerificadorEvaluacionBean.eliminarTelefono}" id="eliminarTelefonoLink">
												<f:param id="idTelefonoVeri" name="idTelefonoVeri" value="#{verificadorTelefono.idVerifTelefono}"/>
												<x:graphicImage value="/img/cat_act.gif" title="Eliminar un Teléfono." border="0" />
											</x:commandLink>
										</h:column>
										
									</h:dataTable>
									
									
									<x:commandLink id="agregarTelefonoLink" action="#{VerificadorEvaluacionBean.agregarTelefono}">
										<x:graphicImage value="/img/cat_pad.gif" title="Agregar Teléfono." border="0" />
									</x:commandLink>


								</h:panelGrid>
							</s:fieldset>
							
							
							
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
								<x:commandButton id="buttonGrabar" value="Guardar" action="#{VerificadorEvaluacionBean.grabar}" styleClass="btn btn-primary btn-flat"/>
								<x:commandButton id="buttonCancelar" value="Cancelar" action="#{VerificadorEvaluacionBean.cancelar}" styleClass="btn btn-primary btn-flat" />
							</h:panelGrid>
						</h:panelGrid>
						
					</s:fieldset>
					</h:panelGrid>
					<f:verbatim>
						<br>
					</f:verbatim>
			    
           </h:panelGroup>
        </f:facet>

       

    </x:panelLayout>
</h:form>    
</center> 
</div>
</section> 

</div>
<!-- ./Main content -->

<%@include file="/inc/footer.jsp" %>


<script type="text/javascript">

document.getElementById("sideMenu").innerHTML = `<li class="header">MENU</li>`;

for(var i = 1; i < mainMenu__idJsp2_menu.length-1; i++) {
    var obj = mainMenu__idJsp2_menu[i];
    document.getElementById("sideMenu").innerHTML += generateMenu(obj,"mainMenu__idJsp2_menu:A]\#{VerificadorEvaluacionBean.inicializar}") + `</li>`;
}

</script>   
<%@include file="/inc/scripts.jsp" %>
</body>
</html>
</f:view>
