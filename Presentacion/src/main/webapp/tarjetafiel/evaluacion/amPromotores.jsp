<%@include file="/inc/tags.jsp" %>
<jsp:useBean id="ahora" class="java.util.Date" scope="request"/>

<f:view>
<html lang="es">
<head>
   	<title><h:outputText value="#{PromotorEvaluacionBean.tituloLargo}"/></title>
    <s:script language="javascript">
    	function recargar() {
    		document.getElementById('altaPromotoresForm').submit();
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

<body class="hold-transition skin-blue sidebar-mini" onbeforeunload="ShowWait('altaPromotoresForm');" onload="if('${PromotorEvaluacionBean.popup.mostrar}'=='true') {viewDialog.show();} else {window.scrollTo(0,${ScrollBean.hiddenScrollY});}">
<h:form id="mainMenu" style="display: none">
  <jsp:include page="/inc/navigation_test.jsp" />
  <x:commandLink id="cerrarSesion" action="#{LoginBean.salir}"/>
</h:form>

<jsp:include page="/inc/header.jsp" />

<!-- Content Header (Page header) -->
<section class="content-header">
  <h1>
    ${PromotorEvaluacionBean.tituloCorto}
    <small>${PromotorEvaluacionBean.tituloLargo}</small>
  </h1>
</section>

<section class="content">

<div class="box box-default">
<div class="box-header"><h3></h3></div>
<center>
<h:form id="altaPromotoresForm">

	<%-- GRABA EL ESTADO DEL SCROLL(Dibujar solo si el popup no se muestra) --%> 
	<h:panelGroup rendered="#{!PromotorEvaluacionBean.popup.mostrar}" id="panelPopup">
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
            	
            	<h:panelGrid columns="1" align="center" id="PanelPricipalPromotores" width="900">
				<%-- INCLUDE PARA LOS ERRORES --%>
					<h:panelGroup id="errores">
						<jsp:include page="/inc/error.jsp" />
					</h:panelGroup>

					<%-- POPUP PARA LOS MENSAJES DE EXITO Y FRACASO --%>
					<s:modalDialog dialogId="viewDialog"
								dialogVar="viewDialog"
								styleClass="viewDialog"
								dialogTitle="#{PromotorEvaluacionBean.tituloCorto}">
						<h:panelGrid columns="2" width="500" id="subPanel">
							<x:graphicImage value="/img/#{PromotorEvaluacionBean.popup.nombreIcono}" />
							<h:outputText value="#{PromotorEvaluacionBean.popup.mensaje}" styleClass="texto"/>
						</h:panelGrid>
						<h:panelGrid columns="3" width="500" id="subPanelDos">
							<x:commandButton action="#{PromotorEvaluacionBean.irANuevoPromotor}" 
								id="cmdIr" onclick="clickLink('nuevoPromotor');dojo.widget.byId('viewDialog').hide();"
								value="Nuevo" styleClass="botones" title="Agregar nuevo."/>
							<x:commandButton action="#{PromotorEvaluacionBean.irAModificarPromotor}"
								onclick="clickLink('modificarPromotor');dojo.widget.byId('viewDialog').hide();"
								id="cmdModificar" value="Modificar" styleClass="botones" title="Seguir modificando."/>
							<x:commandButton action="#{PromotorEvaluacionBean.irAListarPromotor}" 
								onclick="clickLink('listarPromotor');dojo.widget.byId('viewDialog').hide();"
								id="cmdListar"  value="Listar" styleClass="botones" title="Ir al listado."/>
						</h:panelGrid>
					</s:modalDialog>
					<x:commandLink id="nuevoPromotor" action="#{PromotorEvaluacionBean.irANuevoPromotor}" forceId="true" style="display: block;"/>
					<x:commandLink id="modificarPromotor" action="#{PromotorEvaluacionBean.irAModificarPromotor}" forceId="true" style="display: block;"/>
					<x:commandLink id="listarPromotor" action="#{PromotorEvaluacionBean.irAListarPromotor}" forceId="true" style="display: block;"/>
			    </h:panelGrid>
			    
			    <h:panelGrid id="uno" columns="1" width="850" align="center">
					<s:fieldset legend="Promotor"  id="fieldPromotor">
					
						<h:panelGrid id="panelPrincipalUno" columns="2" width="350" align="center">
							
							<h:outputText value="Nombre:" styleClass="texto"/>
							<h:inputText id="nombreFiltro" value="#{PromotorEvaluacionBean.promotor.nombre}"
							styleClass="bordeceldatext" maxlength="100" size="100"
							style="width: 150px" onfocus="encender(this);" onblur="apagar(this);"/>
						    <h:outputText value="Apellido:" styleClass="texto"/>
							<h:inputText id="nombreFiltroDos" value="#{PromotorEvaluacionBean.promotor.apellido}"
							styleClass="bordeceldatext" maxlength="100" size="100"
							style="width: 150px" onfocus="encender(this);" onblur="apagar(this);"/>
							<h:outputText id="outEstado" value="Estado: " styleClass="texto"/>
							<h:selectOneMenu id="lstEstados" value="#{PromotorEvaluacionBean.estadoSeleccionado}" 
			        					 styleClass="lista" onfocus="encender(this);" onblur="apagar(this);">
		       						<f:selectItems value="#{PromotorEvaluacionBean.listaEstados}" id="selectEstado"/>
			        		</h:selectOneMenu>
						</h:panelGrid>
						
						<h:panelGrid columns="1" id="panelTres" width="600" align="center">
							<s:fieldset legend="Domicilio" id="fieldDomicilio">
								<h:panelGrid columns="4" id="panelDomicilio" width="600" align="center">
										<h:outputText id="txtCalle" value="Calle:" styleClass="texto"/>
										<h:outputText id="outTxtCalle" value="#{PromotorEvaluacionBean.promotor.domicilio.calleNombre}" styleClass="textoblue"/>
										<h:outputText id="txtNumero" value="Número:" styleClass="texto"/>
										<h:outputText id="outTxtNumero" value="#{PromotorEvaluacionBean.promotor.domicilio.calleNumero}" styleClass="textoblue"/>
										<h:outputText id="txtBarrio" value="Barrio:" styleClass="texto"/>
										<h:outputText id="outTxtBarrio" value="#{PromotorEvaluacionBean.promotor.domicilio.barrio.descripcion}" styleClass="textoblue"/>
										<h:outputText id="txtLocalidad" value="Localidad:" styleClass="texto"/>
										<h:outputText id="outTxtLocalidad" value="#{PromotorEvaluacionBean.promotor.domicilio.localidad.nombre}" styleClass="textoblue"/>
								</h:panelGrid>
								<x:commandLink id="agregarDomicilioLink" action="#{PromotorEvaluacionBean.agregarDomicilioPopup}">
											<x:graphicImage value="/img/icon/home_24.gif" title="Agregar domicilios." border="0" />
								</x:commandLink>
							</s:fieldset>
							<s:fieldset id="celda"> 
							   <h:panelGrid columns="4" id="panelEmail" width="600" align="center">
							       <h:outputText value="Direccion de Email:" styleClass="texto"/>
									<h:inputText id="direccionMail" value="#{PromotorEvaluacionBean.promotor.email.email}"
									styleClass="bordeceldatext" maxlength="100" size="100"
									style="width: 150px" onfocus="encender(this);" onblur="apagar(this);"/>
								    <h:outputText value="Descripcion: " styleClass="texto"/>
									<h:inputText id="descripcionEMail" value="#{PromotorEvaluacionBean.promotor.email.descripcion}"
									styleClass="bordeceldatext" maxlength="100" size="100"
									style="width: 150px" onfocus="encender(this);" onblur="apagar(this);"/>
							   </h:panelGrid>
							</s:fieldset>
							
							
							
						<s:fieldset legend="Teléfonos" id="telefonos">
								<h:panelGrid columns="2" id="panelTelefono" width="650" align="center">
									<h:dataTable value="#{PromotorEvaluacionBean.listTelefono}"
										id="tablaTelefonoProm" styleClass="table-bordered table-striped" headerClass="dataTable_Header"
										footerClass="standardTable_Header" rowClasses="standardTable_Row1,standardTable_Row2"
										columnClasses="tdA,tdA,tdA,tdA,tdA"
										var="promotorTelefono" style=" width : 600px;">
										
										<h:column>
											<f:facet name="header">
												<h:outputText value="Número" styleClass="texto" />
											</f:facet>
											<h:outputText value="#{promotorTelefono.telefono.nroTlefono}"/>
										</h:column>
										
										<h:column>
											<f:facet name="header">
												<h:outputText value="Celular" styleClass="texto" />
											</f:facet>
											<h:outputText value="#{promotorTelefono.telefono.esCelular}"/>
										</h:column>
										
										<h:column>
											<f:facet name="header">
												<h:outputText value="Fax" styleClass="texto" />
											</f:facet>
											<h:outputText value="#{promotorTelefono.telefono.esFax}"/>
										</h:column>
										
										<h:column>
											<f:facet name="header">
												<h:outputText value="Tipo Telefono" styleClass="texto" />
											</f:facet>
											<h:outputText value="#{promotorTelefono.telefono.tipo.descripcion}" />
										</h:column>
										
										<h:column>
											<f:facet name="header">
												<h:outputText value="Horarios" styleClass="texto" />
											</f:facet>
											<h:outputText value="#{promotorTelefono.telefono.horarios}" />
										</h:column>
										
										<h:column>
											<f:facet name="header">
												<h:outputText value="Descripción" styleClass="texto" />
											</f:facet>
											<h:outputText value="#{promotorTelefonos.telefono.descripcion}" />
										</h:column>
										
										<h:column>
											<x:commandLink action="#{PromotorEvaluacionBean.editarTelefono}" id="editarTelefonoLink">
												<f:param id="idTelEdi" name="idTelEdi" value="#{promotorTelefono.telefono.idTelefono}" />
												<x:graphicImage value="/img/editar.gif" title="Editar el Teléfono." border="0" />
											</x:commandLink>
										</h:column>

										<h:column>
											<x:commandLink action="#{PromotorEvaluacionBean.eliminarTelefono}" id="eliminarTelefonoLink">
												<f:param id="idTelefono" name="idTelefono" value="#{promotorTelefono.telefono.idTelefono}"/>
												<x:graphicImage value="/img/cat_act.gif" title="Eliminar un Teléfono." border="0" />
											</x:commandLink>
										</h:column>
										
									</h:dataTable>
									
									
									<x:commandLink id="agregarTelefonoLink" action="#{PromotorEvaluacionBean.agregarTelefono}">
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
								<x:commandButton id="buttonGrabar" value="Guardar" action="#{PromotorEvaluacionBean.grabar}" styleClass="botones"/>
								<x:commandButton id="buttonCancelar" value="Cancelar" action="#{PromotorEvaluacionBean.cancelar}" styleClass="botones" />
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
    document.getElementById("sideMenu").innerHTML += generateMenu(obj,"mainMenu__idJsp2_menu:A]\#{PromotorEvaluacionBean.inicializar}") + `</li>`;
}

</script>   
<%@include file="/inc/scripts.jsp" %>
</body>
</html>
</f:view>
