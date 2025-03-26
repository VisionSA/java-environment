<%@include file="/inc/tags.jsp" %>
<jsp:useBean id="ahora" class="java.util.Date" scope="request"/>

<f:view>
<html lang="es">
<head>
	<title><h:outputText value="#{ColaboradorBean.tituloLargo}"/></title>
	<s:script language="javascript">
		function recargar() {
			document.getElementById('amColaboradorForm').submit();
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

<body class="hold-transition skin-blue sidebar-mini" onbeforeunload="ShowWait('amColaboradorForm');" onload="if('${ColaboradorBean.popup.mostrar}'=='true') {viewDialog.show();} else {window.scrollTo(0,${ScrollBean.hiddenScrollY});}; modificarFechaCierre();">
    <s:script language="javascript">
         function modificarFechaCierre()        
  		 {
  		    if (document.getElementById('amColaboradorForm:activoFiltroChex').checked == true) {
  		        document.getElementById('amColaboradorForm:panelOculto').style.display = '';
  		 	} else {
  		 	    document.getElementById('amColaboradorForm:panelOculto').style.display = 'none';
  		 	}
  		 	return null;
  		 }
      </s:script>

<h:form id="mainMenu" style="display: none">
  <jsp:include page="/inc/navigation_test.jsp" />
  <x:commandLink id="cerrarSesion" action="#{LoginBean.salir}"/>
</h:form>

<jsp:include page="/inc/header.jsp" />

<!-- Content Header (Page header) -->
<section class="content-header">
  <h1>
    Transacción
    <small>Altas</small>
  </h1>
</section>

<section class="content">

		<div class="box box-default">
		<div class="box-header with-border">
			<h3 class="box-title">Alta Colaborador</h3>
		</div>
		<br/>     

<center>

	<h:form id="amColaboradorForm">

	<%-- GRABA EL ESTADO DEL SCROLL(Dibujar solo si el popup no se muestra) --%>
	<h:panelGroup rendered="#{!ColaboradorBean.popup.mostrar}">
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
								dialogTitle="#{ColaboradorBean.tituloCorto}">
						<h:panelGrid columns="2" width="500">
							<x:graphicImage value="/img/#{ColaboradorBean.popup.nombreIcono}" />
							<h:outputText value="#{ColaboradorBean.popup.mensaje}" styleClass="texto"/>
						</h:panelGrid>
						<h:panelGrid columns="3" width="500">
							<x:commandButton action="#{ColaboradorBean.irANuevoColaborador}" 
								onclick="clickLink('nuevoColaborador');dojo.widget.byId('viewDialog').hide();"
								value="Nuevo" styleClass="botones" title="Agregar nuevo Colaborador."/>
							<x:commandButton action="#{ColaboradorBean.irAModificarColaborador}" 
								onclick="clickLink('modificarColaborador');dojo.widget.byId('viewDialog').hide();"
								value="Modificar" styleClass="botones" title="Seguir modificando."/>
							<x:commandButton action="#{ColaboradorBean.irAListarColaborador}" 
								onclick="clickLink('listarColaborador');dojo.widget.byId('viewDialog').hide();"
								value="Listar" styleClass="botones" title="Ir al listado."/>
						</h:panelGrid>
					</s:modalDialog>
					<x:commandLink id="nuevoColaborador" action="#{ColaboradorBean.irANuevoColaborador}" forceId="true" style="display: block;"/>
					<x:commandLink id="modificarColaborador" action="#{ColaboradorBean.irAModificarColaborador}" forceId="true" style="display: block;"/>
					<x:commandLink id="listarColaborador" action="#{ColaboradorBean.irAListarColaborador}" forceId="true" style="display: block;"/>

					<h:panelGrid id="panelPrincipalUno" columns="1" width="500">
						<h:panelGrid id="documento" columns="6">
						    <h:outputText id="outTipoDNI" value="Tipo Documento: " styleClass="texto" />
							<h:selectOneMenu id="lstTDoc" value="#{ColaboradorBean.colaborador.individuo.tipoDocumento.idTipoDocumento}" disabled="#{ColaboradorBean.individuoCargado}"
								styleClass="lista" immediate="true" onfocus="encender(this);" onblur="apagar(this);" style="width: 200px">
								<f:selectItems value="#{ColaboradorBean.listTipoDni}" id="selectItemIdTipoDoc"/>
							</h:selectOneMenu>

							<h:outputText id="outNroDoc" value="Número: " styleClass="texto" />
							<x:inputText id="inNroDoc" forceId="true" value="#{ColaboradorBean.colaborador.individuo.nroDocumento}" disabled="#{ColaboradorBean.individuoCargado}" size="20"
								maxlength="20" styleClass="bordeceldainferior" style="width: 150px" onfocus="encender(this);" onblur="apagar(this);"
								onkeypress="return soloEnteros(this,event);"/>
								<h:commandButton id="botonNuevoIndividuo" value="Nueva Carga" styleClass="btn btn-primary btn-flat" action="#{ColaboradorBean.habilitarNuevaCarga}"/>
						    <h:commandButton id="botonCargarIndividuo" value="Habilitar Carga" styleClass="btn btn-primary btn-flat" action="#{ColaboradorBean.habilitarCarga}" disabled="#{ColaboradorBean.individuoCargado}"/>
						</h:panelGrid>
						<s:fieldset id="datosPersonales" legend="Datos Personales">
                               <h:panelGrid id="datPersonPanel" columns="4" align="center">
                                     	<h:outputText id="outApellido" value="Apellido Paterno: " styleClass="texto" />
										<x:inputText id="inApellido" value="#{ColaboradorBean.colaborador.individuo.apellido}" disabled="#{!ColaboradorBean.individuoCargado}" forceId="true" size="50"
											maxlength="100" styleClass="bordeceldatext" style="width: 150px" onfocus="encender(this);"
											onblur="apagar(this);"/>
											
										<h:outputText id="outAMaterno" value="Apellido Materno: " styleClass="texto" />
										<x:inputText id="inAMaterno" value="#{ColaboradorBean.colaborador.individuo.apellidoMaterno}" forceId="true" size="50" disabled="#{!ColaboradorBean.individuoCargado}"
											maxlength="100" styleClass="bordeceldatext" style="width: 150px" onfocus="encender(this);" onblur="apagar(this);"/>
											
										<h:outputText id="outNombre" value="Nombres: " styleClass="texto" />
										<x:inputText id="inNombre" value="#{ColaboradorBean.colaborador.individuo.nombres}" disabled="#{!ColaboradorBean.individuoCargado}" forceId="true" size="50"
											maxlength="100" styleClass="bordeceldatext" style="width: 150px" onfocus="encender(this);" onblur="apagar(this);"/>
		                                <h:outputText id="outNrocuit" value="Cuit: " styleClass="texto" />
										<x:inputText id="inNrocuit" forceId="true" value="#{ColaboradorBean.colaborador.individuo.cuil}" disabled="#{!ColaboradorBean.individuoCargado}" size="20"
											maxlength="11" styleClass="bordeceldainferior" style="width: 150px" onfocus="encender(this);" onblur="apagar(this);"
											onkeypress="return soloEnteros(this,event);"/>
		                                <h:outputText id="outSexo" value="Sexo: " styleClass="texto" />
										<h:selectOneMenu id="lstsexo" value="#{ColaboradorBean.idTipoSexoSeleccionado}" disabled="#{!ColaboradorBean.individuoCargado}"
											styleClass="lista" immediate="true" onfocus="encender(this);"
											onblur="apagar(this);" style="width: 200px">
											<f:selectItems value="#{ColaboradorBean.listTipoSexo}" id="selectItemIdTipoSexo"/>
										</h:selectOneMenu>
					 					
					 					<h:outputText value="Fecha Nacimiento: " id="outFechaNac" styleClass="texto"/>
											<f:verbatim>
									                <div class="input-group date" style="width: 200px" >
									                    <div class="input-group-addon">
									                        <i class="fa fa-calendar"></i>
									                    </div>
									                    <input type="text" class="form-control pull-right" id="fDesde" placeholder="DD/MM/AAAA" autocomplete="off">
									                </div>
											</f:verbatim>
		                               						
 						       </h:panelGrid>
 						       <h:panelGrid id="su" columns="14" align="center"> 
 						            <h:outputText value="Sucursal:" styleClass="texto"/>
									<h:selectOneMenu id="lstSucursal" value="#{ColaboradorBean.idSucursalSeleccionada}" disabled="true"
										styleClass="lista" immediate="true" onfocus="encender(this);"
										onblur="apagar(this);" >
										<f:selectItems value="#{ColaboradorBean.sucursalItems}"/>
									</h:selectOneMenu>
									<h:outputText value="Estado Colaborador:" styleClass="texto"/>
									<h:selectOneMenu id="lstestadoColaborador" value="#{ColaboradorBean.colaborador.estado}" disabled="#{!ColaboradorBean.individuoCargado}"
										styleClass="lista" immediate="true" onfocus="encender(this);"
										onblur="apagar(this);" >
										<f:selectItems value="#{ColaboradorBean.listEstadoColaborador}"/>
									</h:selectOneMenu>
					                <h:outputText value="Promotor" styleClass="texto"/>
					                <h:selectBooleanCheckbox value="#{ColaboradorBean.esPromotor}" id="booProm" disabled="#{!ColaboradorBean.individuoCargado}" />
					                <h:outputText value="Verificador" styleClass="texto"/>
					                <h:selectBooleanCheckbox value="#{ColaboradorBean.esVerificador}" id="activoFiltroChex" onclick="modificarFechaCierre();" disabled="#{!ColaboradorBean.individuoCargado}"/>
                                    <h:outputText value="Cobrador" styleClass="texto"/>
					                <h:selectBooleanCheckbox value="#{ColaboradorBean.esCobrador}" id="booCobr"  disabled="#{!ColaboradorBean.individuoCargado}"/>
					                <h:outputText value="Abogado" styleClass="texto"/>
					                <h:selectBooleanCheckbox value="#{ColaboradorBean.esAbogado}" id="booAbog"  disabled="#{!ColaboradorBean.individuoCargado}"/>
									<h:outputText value="Cajero" styleClass="texto"/>
					                <h:selectBooleanCheckbox value="#{ColaboradorBean.esCajero}" id="booCaje"  disabled="#{!ColaboradorBean.individuoCargado}"/>
 						       </h:panelGrid>
 						       <h:panelGrid columns="1" id="panelOculto">
 						                <s:fieldset legend="Partido del verificador:" id="panelOcu" >
							             <h:panelGrid columns="6">
							             <h:outputText id="outPais" value="País: " styleClass="texto"/>
										 	<h:selectOneMenu id="lstPais" value="#{ColaboradorBean.idPaisSeleccionado}" immediate="true"
							        					     styleClass="lista" onfocus="encender(this);" onblur="apagar(this);"
							        					     binding="#{ColaboradorBean.paises}" style=" width : 200px;"
							        					     valueChangeListener="#{ColaboradorBean.cambiarProvincias}" onchange="submit();">
						       					<f:selectItems value="#{ColaboradorBean.listaPaises}" id="selectPaises"/>
							        		</h:selectOneMenu>
					              			
					              			
							                <h:outputText id="outProvincia" value="Provincia: " styleClass="texto"/>
											<h:selectOneMenu id="lstProvincia" value="#{ColaboradorBean.provinciaSeleccionada}"
							        					     styleClass="lista" onfocus="encender(this);" onblur="apagar(this);"
							        					     onchange="submit();" binding="#{ColaboradorBean.provincias}" style=" width : 200px;"
							        					     valueChangeListener="#{ColaboradorBean.cambiarPartidos}">
						       					<f:selectItems value="#{ColaboradorBean.listaProvincias}" id="selectProvincias"/>
							        		</h:selectOneMenu>
							        		
							        		
							                <h:outputText id="outPartido" value="Partido: " styleClass="texto"/>
											<h:selectOneMenu id="lstPartido" value="#{ColaboradorBean.idPartidoSeleccionado}" 
							        					     styleClass="lista" onfocus="encender(this);" onblur="apagar(this);"
							        					     binding="#{ColaboradorBean.partidos}" onchange="submit();" style=" width : 200px;">
						       					<f:selectItems value="#{ColaboradorBean.listaPartidos}" id="selectPartidos"/>
							        		</h:selectOneMenu>
											<h:outputText value="    " />
                		
		                
							    </h:panelGrid>
							
							</s:fieldset>
							</h:panelGrid>
 						       <h:panelGrid id="panSopro" columns="6" align="center">
 						       			<h:outputText value="Fecha de Alta " id="outAlta" styleClass="texto" rendered="#{ColaboradorBean.verFechaAlta}"/>
 						       			<f:verbatim>
										<font style="COLOR: blue;">
											<fmt:formatDate value="${ColaboradorBean.fechaAlta}"
												pattern="dd/MM/yyyy" />
										</font>
										</f:verbatim>
 						       			<h:outputText value="Fecha de Baja" id="outgbaja" styleClass="texto" rendered="#{ColaboradorBean.verFechaBaja}"/>
 						       			<f:verbatim>
										<font style="COLOR: blue;">
											<fmt:formatDate value="${ColaboradorBean.fechaBaja}"
												pattern="dd/MM/yyyy" />
										</font>
										</f:verbatim>
 						       			<h:outputText id="outNroLegajo" value="Número de Legajo: " styleClass="texto" />
										<x:inputText id="inLegadjo" value="#{ColaboradorBean.colaborador.nroLegajo}" forceId="true" size="50" disabled="#{!ColaboradorBean.individuoCargado}"
											maxlength="100" styleClass="bordeceldatext" style="width: 150px" onfocus="encender(this);" onblur="apagar(this);" onkeypress="return soloEnteros(this,event);"/>
 						       			
 						       			
 						       </h:panelGrid>
						</s:fieldset>
						<s:fieldset id="datosContacto" legend="Contacto">
						       <s:fieldset legend="Domicilio" id="fieldDomicilio">
								<h:panelGrid columns="4" id="panelDomicilio" width="600" align="center">
										<h:outputText id="txtCalle" value="Calle:" styleClass="texto"/>
										<h:outputText id="outTxtCalle" value="#{ColaboradorBean.colaborador.individuo.domicilio.calleNombre}" styleClass="textoblue"/>
										<h:outputText id="txtNumero" value="Número:" styleClass="texto"/>
										<h:outputText id="outTxtNumero" value="#{ColaboradorBean.colaborador.individuo.domicilio.calleNumero}" styleClass="textoblue"/>
										<h:outputText id="txtBarrio" value="Barrio:" styleClass="texto"/>
										<h:outputText id="outTxtBarrio" value="#{ColaboradorBean.colaborador.individuo.domicilio.barrio.descripcion}" styleClass="textoblue"/>
										<h:outputText id="txtLocalidad" value="Localidad:" styleClass="texto"/>
										<h:outputText id="outTxtLocalidad" value="#{ColaboradorBean.colaborador.individuo.domicilio.localidad.nombre}" styleClass="textoblue"/>
								</h:panelGrid>
								<x:commandLink id="agregarDomicilioLink" action="#{ColaboradorBean.agregarDomicilioPopup}" disabled="#{!ColaboradorBean.individuoCargado}">
											<x:graphicImage value="/img/icon/home_24.gif" title="Agregar domicilios." border="0" />
								</x:commandLink>
							</s:fieldset>
							<s:fieldset id="celda" legend="E-mail"> 
							   <h:panelGrid columns="4" id="panelEmail" width="600" align="center">
							       <h:outputText value="Direccion de Email:" styleClass="texto"/>
									<h:inputText id="direccionMail" value="#{ColaboradorBean.email}" disabled="#{!ColaboradorBean.individuoCargado}"
									styleClass="bordeceldatext" maxlength="100" size="100"
									style="width: 150px" onfocus="encender(this);" onblur="apagar(this);"/>
								    <h:outputText value="Descripción: " styleClass="texto"/>
									<h:inputText id="descripcionEMail" value="#{ColaboradorBean.descripcion}" disabled="#{!ColaboradorBean.individuoCargado}"
									styleClass="bordeceldatext" maxlength="100" size="100"
									style="width: 150px" onfocus="encender(this);" onblur="apagar(this);"/>
							   </h:panelGrid>
							</s:fieldset>
							<s:fieldset legend="Teléfonos" id="teledonos">
								<h:panelGrid columns="2" id="panelTelefono" width="650" align="center">
									<h:panelGroup id="panelGroupTelefono">
									<h:dataTable value="#{ColaboradorBean.listTelefono}"
										id="tablaTelefono" styleClass="standardTable" headerClass="dataTable_Header"
										footerClass="standardTable_Header" rowClasses="standardTable_Row1,standardTable_Row2"
										columnClasses="standardTable_Column,standardTable_ColumnCentered,standardTable_Column"
										var="individuoTelefono" style=" width : 600px;">
										
										<h:column>
											<f:facet name="header">
												<h:outputText value="Número" styleClass="texto"  />
											</f:facet>
											<h:outputText value="#{individuoTelefono.telefono.nroTlefono}"/>
										</h:column>
										
										<h:column>
											<f:facet name="header">
												<h:outputText value="Celular" styleClass="texto"  />
											</f:facet>
											<h:outputText value="#{individuoTelefono.telefono.esCelular}"/>
										</h:column>
										
										<h:column>
											<f:facet name="header">
												<h:outputText value="Fax" styleClass="texto"  />
											</f:facet>
											<h:outputText value="#{individuoTelefono.telefono.esFax}"/>
										</h:column>
										
										<h:column>
											<f:facet name="header">
												<h:outputText value="Tipo Telefono" styleClass="texto"  />
											</f:facet>
											<h:outputText value="#{individuoTelefono.telefono.tipo.descripcion}" />
										</h:column>
										
										<h:column>
											<f:facet name="header">
												<h:outputText value="Horarios" styleClass="texto"  />
											</f:facet>
											<h:outputText value="#{individuoTelefono.telefono.horarios}" />
										</h:column>
										
										<h:column>
											<f:facet name="header">
												<h:outputText value="Descripción" styleClass="texto"  />
											</f:facet>
											<h:outputText value="#{individuoTelefono.telefono.descripcion}" />
										</h:column>
										
										<h:column>
											<x:commandLink action="#{ColaboradorBean.editarTelefono}" id="editarTelefonoLink" disabled="#{!ColaboradorBean.individuoCargado}">
												<f:param id="idTelEdi" name="idTelEdi" value="#{individuoTelefono.telefono.idTelefono}" />
												<x:graphicImage value="/img/editar.gif" title="Editar el Teléfono." border="0" id="botonImagenDos" />
											</x:commandLink>
										</h:column>

										<h:column>
											<x:commandLink action="#{ColaboradorBean.eliminarTelefono}" id="eliminarTelefonoLink" disabled="#{!ColaboradorBean.individuoCargado}">
												<f:param id="idTelefono" name="idTelefono" value="#{individuoTelefono.telefono.idTelefono}"/>
												<x:graphicImage value="/img/cat_act.gif" title="Eliminar un Teléfono." border="0" id="botonImagenTres" />
											</x:commandLink>
										</h:column>
										
									</h:dataTable>
									</h:panelGroup>
									
									<h:panelGroup id="panelGroupBotones">
										<x:commandLink id="agregarTelefonoLink" action="#{ColaboradorBean.agregarTelefono}" disabled="#{!ColaboradorBean.individuoCargado}">
											<x:graphicImage value="/img/cat_pad.gif" title="Agregar Teléfono." border="0" id="botonImagenCuatro"/>
										</x:commandLink>
									</h:panelGroup>

								</h:panelGrid>
							</s:fieldset>
							
							
							
							
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
						<x:commandButton id="buttonGrabar" value="Guardar" action="#{ColaboradorBean.grabar}" styleClass="btn btn-primary btn-flat" disabled="#{!ColaboradorBean.individuoCargado}"/>
						<x:commandButton id="buttonCancelar" value="Cancelar" action="#{ColaboradorBean.cancelar}" styleClass="btn btn-primary btn-flat" />
					</h:panelGrid>
					</h:panelGrid>
				</h:panelGroup>
			</f:facet>
		</x:panelLayout>
		<h:inputText id="FechaDesde" value="#{ColaboradorBean.fechaNacimiento}" style="display: none;">
        <f:convertDateTime type="both" pattern = "dd/MM/yyyy HH:mm"/>
    </h:inputText>
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

for(var i = 1; i < mainMenu__idJsp3_menu.length-1; i++) {
    var obj = mainMenu__idJsp3_menu[i];
    document.getElementById("sideMenu").innerHTML += generateMenu(obj,"mainMenu__idJsp3_menu:A]\#{ColaboradorBean.inicializar}") + `</li>`;
}

</script>
<%@include file="/inc/scripts.jsp" %>
<script type="text/javascript">
$(function () {

    //Inicializo datepicker
    $("#fDesde").datepicker({
      autoclose: true,
      orientation: "bottom"
    });


    //Muevo fechas de datepicker a input q pasa datos al bean
    $("#fDesde").change(function() {
        var fd = $(this).datepicker("getDate");
        //la hora va en gtm 0. al pasar el server que está en gtm -3, le restará 3 hs.
        document.getElementById("amColaboradorForm:FechaDesde").value = $.datepicker.formatDate('dd/mm/yy', fd) + " 03:00"; 
    });

  });

</script>

</body>
</html>
</f:view>