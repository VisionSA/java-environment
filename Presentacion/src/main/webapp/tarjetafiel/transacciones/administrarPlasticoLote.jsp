<%-- 5248--%>
<%@include file="/inc/tags.jsp"%>
<%-- <jsp:useBean id="ahora" class="java.util.Date" scope="request"/> --%>

<f:view>
	<html lang="es">
<head>
<title><x:outputText
		value="Tarjeta Fiel | Administración de Sobres" /></title>
</head>

<jsp:include page="/inc/includes.jsp" />

<body class="hold-transition skin-blue sidebar-mini" onbeforeunload="ShowWait('AdministracionLotePlastico');"
	onload="if('${AdministrarPlasticoLoteBean.confirmacion.mostrar}'=='true') {confirmDialogConf.show();} else {window.scrollTo(0,${ScrollBean.hiddenScrollY});}
			if('${AdministrarPlasticoLoteBean.popup.mostrar}'=='true') {viewDialog.show();} else {window.scrollTo(0,${ScrollBean.hiddenScrollY});}">

	<h:form id="mainMenu" style="display: none">
	  <jsp:include page="/inc/navigation_test.jsp" />
	  <x:commandLink id="cerrarSesion" action="#{LoginBean.salir}"/>
	</h:form>

	<jsp:include page="/inc/header.jsp" />

	<!-- Content Header (Page header) -->
	<section class="content-header">
	  <h1>
	    Transacción
	    <small>Plásticos</small>
	  </h1>
	</section>

	<section class="content">
	
	    <div class="box box-default">
	    <div class="box-header with-border">
	        <h3 class="box-title">Administración de sobres de plásticos</h3>
	    </div>
	    <br/>		
	<center>
		<secure:check />

		<h:form id="AdministracionLotePlastico">
			<%-- GRABA EL ESTADO DEL SCROLL(Dibujar solo si el popup no se muestra) --%>
			<%-- 			<h:panelGroup rendered="#{!AdministrarPlasticoLoteBean.popup.mostrar}"> --%>
			<%-- 				<%@include file="/inc/scroll.inc"%> --%>
			<%-- 			</h:panelGroup> --%>

			<x:panelGroup
				rendered="#{!AdministrarPlasticoLoteBean.confirmacion.mostrar}">
				<%@include file="/inc/scroll.inc"%>
			</x:panelGroup>

			<x:panelLayout id="page" layout="#{globalOptions.pageLayout}"
				styleClass="pageLayout" headerClass="pageHeader"
				navigationClass="pageNavigation" bodyClass="pageBody"
				footerClass="pageFooter">


				<f:facet name="body">
					<h:panelGroup id="body">

						<x:panelGrid columns="1" align="center"
							id="PanelPricipalTratarPlasticos">
							<%-- INCLUDE PARA LOS ERRORES --%>
							<h:panelGroup id="errores">
								<jsp:include page="/inc/error.jsp" />
							</h:panelGroup>

							<%-- POPUP PARA LOS MENSAJES DE EXITO Y FRACASO --%>
							<s:modalDialog dialogId="viewDialog" dialogVar="viewDialog"
								styleClass="viewDialog"
								dialogTitle="#{AdministrarPlasticoLoteBean.tituloCorto}">
								<x:panelGrid columns="2" width="500">
									<x:graphicImage
										value="/img/#{AdministrarPlasticoLoteBean.popup.nombreIcono}" />
									<x:outputText
										value="#{AdministrarPlasticoLoteBean.popup.mensaje}"
										styleClass="texto" />
								</x:panelGrid>
								<x:panelGrid columns="3" width="500">
									<x:commandButton
										action="#{AdministrarPlasticoLoteBean.irAContinuar}"
										onclick="clickLink('continuar');dojo.widget.byId('viewDialog').hide();"
										value="Continuar" styleClass="btn btn-primary btn-flat"
										title="Continuar en la pantalla." />
									<f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim>
									<f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim>
									<%--<x:commandButton action="#{LiquidarSaldo0Bean.irASalir}" 
										onclick="clickLink('salir');dojo.widget.byId('viewDialog').hide();"
										value="Salir" styleClass="botones" title="Salir."/>--%>
								</x:panelGrid>
							</s:modalDialog>
							<x:commandLink id="continuar"
								action="#{AdministrarPlasticoLoteBean.irAContinuar}"
								forceId="true" style="display: block;" />
						</x:panelGrid>

						<x:panelGrid id="campoTitulo" columns="1" align="center">
							<x:outputText value="Administracion de Sobres"
								style="font-size:36px; text-align: center;"></x:outputText>
						</x:panelGrid>						
						<f:verbatim>&nbsp;</f:verbatim>
						<x:panelGrid id="camposEstado" columns="2" width="400"
							align="center">
							<x:outputText value="Lugar a pasar:" styleClass="texto" />

							<x:selectOneMenu id="lsLugares"
								value="#{AdministrarPlasticoLoteBean.estadoSeleccionado}"
								styleClass="lista" immediate="true" onfocus="encender(this);"
								onblur="apagar(this);setCodOpFocus();" onchange="submit();"
								valueChangeListener="#{AdministrarPlasticoLoteBean.verificarLugar}">
								<f:selectItems
									value="#{AdministrarPlasticoLoteBean.lugaresList}" />
							</x:selectOneMenu>

							<c:if
								test="${AdministrarPlasticoLoteBean.mostrarMensajeAdvertencia}">
								<x:div id="divBlanco"></x:div>
								<x:div id="divImagen" style="align: center">
									<x:graphicImage id="imgAtencion"
										alt="Atención: El cambio a este lugar es IRREVERSIBLE."
										width="16" height="16" url="/img/warning_16x16.gif"
										title="El cambio a este lugar es IRREVERSIBLE" />
									<x:outputText id="mensaje"
										value="El cambio a este lugar es <strong><span style='color:#ff0000;'>IRREVERSIBLE</span></strong>"
										escape="false"></x:outputText>
								</x:div>
							</c:if>

							<x:outputText id="otxtOperacion" value="Codigo Operacion: "
								styleClass="textoACenter" />
							<x:inputText id="txtOperacion" styleClass="bordeceldainferior"
								maxlength="2" size="50" style="width: 75px;margin-top:8px"
								onkeypress="javascript:return changeInputFocus(this, 2, 'AdministracionLotePlastico:txtlote', event)"
								value="#{AdministrarPlasticoLoteBean.codOperacionTxt}" />

							<x:outputText id="otxtLote" value="Lote: "
								styleClass="textoACenter" />
							<x:inputText id="txtlote" styleClass="bordeceldainferior"
								maxlength="6" size="50" style="width: 75px;margin-top:8px"
								onkeypress="javascript:return changeInputFocus(this, 6, 'AdministracionLotePlastico:txtCliente', event)"
								value="#{AdministrarPlasticoLoteBean.codLoteTxt}" />

							<x:outputText id="otxtCliente" value="Nro de Cuenta: " styleClass="textoACenter" />								
							<x:inputText id="txtCliente" styleClass="bordeceldainferior"
								maxlength="6" size="50" style="width: 75px;margin-top:8px"
								onkeypress="javascript:return changeInputFocus(this, 6, 'AdministracionLotePlastico:txtVerificacion', event)"
								value="#{AdministrarPlasticoLoteBean.codCuentaTxt}" />

							<x:outputText id="otxtVerificacion" value="Codigo Verificador: "
								styleClass="textoACenter" />
							<x:inputText id="txtVerificacion" styleClass="bordeceldainferior"
								maxlength="1" size="50" style="width: 75px;margin-top:8px"
								onkeypress="javascript:return changeInputFocus(this, 1, 'AdministracionLotePlastico:btnRenovarPlasticos', event)"
								value="#{AdministrarPlasticoLoteBean.codVerificacionTxt}" />
							
							<f:verbatim>&nbsp;</f:verbatim>
							<f:verbatim>&nbsp;</f:verbatim>
							<f:verbatim>&nbsp;</f:verbatim>

							<x:commandButton id="btnRenovarPlasticos" value="Cambiar lugar"
								title="Cambiar lugar" styleClass="btn btn-primary btn-flat"
								action="#{AdministrarPlasticoLoteBean.cambiarLugar}" />
						</x:panelGrid>

						<x:panelGrid columns="1" width="400" align="center"
							rendered="#{AdministrarPlasticoLoteBean.mostrarHtmlConfLotes}">
							<x:outputText
								value="#{AdministrarPlasticoLoteBean.mensajeConfPlastico}"
								escape="false" />
						</x:panelGrid>


						<s:modalDialog dialogId="confirmDialogConf" dialogVar="confirmDialogConf"
							styleClass="viewDialog"
							dialogTitle="#{RenovacionPlasticosBean.tituloCorto}">
							<x:panelGrid columns="2" width="500">
								<x:graphicImage
									value="/img/#{AdministrarPlasticoLoteBean.confirmacion.nombreIcono}" />
								<x:outputText
									value="#{AdministrarPlasticoLoteBean.confirmacion.mensaje}"
									styleClass="texto" escape="false"/>
							</x:panelGrid>
							<x:panelGrid columns="3" width="500">
						       	<x:commandButton id="btnConfirmar"
										action="#{AdministrarPlasticoLoteBean.confirmarAccion}"
										onclick="clickLink('confirmar');dojo.widget.byId('confirmDialogConf').hide();"
										value="Confirmar" styleClass="btn btn-primary btn-flat" title="Confirmar accion"/>
								<f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim>
								<x:commandButton id="btnCancelar" action="#{AdministrarPlasticoLoteBean.irASalir}"
									onclick="clickLink('cancelar');dojo.widget.byId('confirmDialogConf').hide();" immediate="true"
									value="Cancelar" styleClass="btn btn-primary btn-flat" title="Cancelar." />
							</x:panelGrid>
						</s:modalDialog>
						<x:commandLink id="confirmar" action="#{AdministrarPlasticoLoteBean.confirmarAccion}" forceId="true" style="display: block;"/>
						<x:commandLink id="cancelar" action="#{AdministrarPlasticoLoteBean.irACancelar}" forceId="true" style="display: block;"/>
						<x:commandLink id="desactivar" action="#{AdministrarPlasticoLoteBean.rechazarAccion}" forceId="true" style="display: block;"/>

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

for(var i = 1; i < mainMenu__idJsp1_menu.length-1; i++) {
    var obj = mainMenu__idJsp1_menu[i];
    document.getElementById("sideMenu").innerHTML += generateMenu(obj,"mainMenu__idJsp1_menu:A]\#{AdministrarPlasticoLoteBean.inicializar}") + `</li>`;
}

</script>
	<script type="text/javascript">
		document.getElementById('AdministracionLotePlastico:txtOperacion').select();
		document.getElementById('AdministracionLotePlastico:txtOperacion').focus();
	 	//popup('http://localhost:8080/archivos/lotesPlasticos/000130/acuses000130.pdf',700,400);
	 	
	 	function limpiaCampos(){
	 		document.getElementById('AdministracionLotePlastico:txtOperacion').value = '';
	 		document.getElementById('AdministracionLotePlastico:txtlote').value = '';
	 		document.getElementById('AdministracionLotePlastico:txtCliente').value = '';
	 		document.getElementById('AdministracionLotePlastico:txtVerificacion').value = ''; 
	 	}
	 	
	 	function changeInputFocus(input, length, nextInput, event){
	 		if (soloEnteros(input,event)){
		 		if (input.value.length + 1 == length){
						document.getElementById(nextInput).focus();
		 		}
		 		return true;
	 		}
	  		else{
	 			return false;
	 		}
	 	}
	</script>
	<%@include file="/inc/scripts.jsp" %>
</body>
	</html>
</f:view>