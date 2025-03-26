<%@include file="/inc/tags.jsp"%>
<%-- <jsp:useBean id="ahora" class="java.util.Date" scope="request"/> --%>

<f:view>
<html lang="es">
<head>
<title><x:outputText
		value="Tarjeta Fiel | Confirmacion de Plasticos" /></title>
</head>

<jsp:include page="/inc/includes.jsp" />

<body class="hold-transition skin-blue sidebar-mini" onbeforeunload="ShowWait('EmbozadoLotePlastico');"
	onload="if('${EmbozadoLotesPlasticosBean.popup.mostrar}'=='true') {viewDialog.show();} else {window.scrollTo(0,${ScrollBean.hiddenScrollY});}">
	
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
        <h3 class="box-title">Confirmación de Plásticos</h3>
    </div>
    <br/>

	<center>
		<%--<secure:check /> --%>

		<h:form id="EmbozadoLotePlastico">
			<%-- GRABA EL ESTADO DEL SCROLL(Dibujar solo si el popup no se muestra) --%>
			<h:panelGroup rendered="#{!EmbozadoLotesPlasticosBean.popup.mostrar}">
				<%@include file="/inc/scroll.inc"%>
			</h:panelGroup>

			<x:panelLayout id="page" layout="#{globalOptions.pageLayout}"
				styleClass="pageLayout" headerClass="pageHeader"
				navigationClass="pageNavigation" bodyClass="pageBody"
				footerClass="pageFooter">

				<f:facet name="body">
					<h:panelGroup id="body">

						<x:panelGrid columns="1" align="center" id="PanelPricipalTratarPlasticos">
							<%-- INCLUDE PARA LOS ERRORES --%>
							<h:panelGroup id="errores">
								<jsp:include page="/inc/error.jsp" />
							</h:panelGroup>

							<%-- POPUP PARA LOS MENSAJES DE EXITO Y FRACASO --%>
							<%--<s:modalDialog dialogId="viewDialog" dialogVar="viewDialog"
								styleClass="viewDialog"
								dialogTitle="#{EmbozadoLotesPlasticosBean.tituloCorto}">
								<x:panelGrid columns="2" width="500">
									<x:graphicImage
										value="/img/#{EmbozadoLotesPlasticosBean.popup.nombreIcono}" />
									<x:outputText
										value="#{EmbozadoLotesPlasticosBean.popup.mensaje}"
										styleClass="texto" />
								</x:panelGrid>
								<x:panelGrid columns="3" width="500">
									<x:commandButton
										action="#{EmbozadoLotesPlasticosBean.irAContinuar}"
										onclick="clickLink('continuar');dojo.widget.byId('viewDialog').hide();"
										value="Continuar" styleClass="botones"
										title="Continuar en la pantalla." />
									<f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim>
									<f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim>
									<%--<x:commandButton action="#{LiquidarSaldo0Bean.irASalir}" 
										onclick="clickLink('salir');dojo.widget.byId('viewDialog').hide();"
										value="Salir" styleClass="botones" title="Salir."/>
								</x:panelGrid>
							</s:modalDialog>--%>
							<x:commandLink id="continuar"
								action="#{EmbozadoLotesPlasticosBean.irAContinuar}"
								forceId="true" style="display: block;" />
						</x:panelGrid>
						
						<x:panelGrid id="campoTitulo" columns="1" align="center">
							<x:outputText value="Confirmación de plásticos - Lote #{EmbozadoLotesPlasticosBean.nroLoteFormateado}" style="font-size:36px; text-align: center;"></x:outputText>
						</x:panelGrid>
						<f:verbatim>&nbsp;</f:verbatim>
						<x:panelGrid id="camposEstado" columns="2" width="400" align="center">
										<h:outputText value="Lugar a pasar:" styleClass="texto"/>								
										<h:selectOneMenu id="lsLugares" value="#{EmbozadoLotesPlasticosBean.estadoSeleccionado}"
											styleClass="lista" immediate="true" onfocus="encender(this);"
											onblur="apagar(this);setCodOpFocus();" onchange="setCodBarrasFocus();">
											<f:selectItems value="#{EmbozadoLotesPlasticosBean.lugaresList}"/>
										</h:selectOneMenu>

										<x:outputText id="otxtOperacion" value="Código Operación: "
											styleClass="textoACenter" />
										<x:inputText id="txtOperacion"
											styleClass="bordeceldainferior" maxlength="2" size="50"
											style="width: 60px;margin-top:8px" onkeypress="javascript:return changeInputFocus(this, 2, 'EmbozadoLotePlastico:txtlote', event)"
											value="#{EmbozadoLotesPlasticosBean.codOperacionTxt}"/>

										<x:outputText id="otxtLote" value="Lote: "
											styleClass="textoACenter" />
										<x:inputText id="txtlote" 
											styleClass="bordeceldainferior" maxlength="6" size="50"
											style="width: 60px;margin-top:8px" 
											onkeypress="javascript:return changeInputFocus(this, 6, 'EmbozadoLotePlastico:txtCliente', event)"
											value="#{EmbozadoLotesPlasticosBean.codLoteTxt}"/>
						
										<x:outputText id="otxtCliente" value="Nro de Cuenta: " styleClass="textoACenter" />										
										<x:inputText id="txtCliente"
											styleClass="bordeceldainferior" maxlength="6" size="50"
											style="width: 60px;margin-top:8px" 
											onkeypress="javascript:return changeInputFocus(this, 6, 'EmbozadoLotePlastico:txtVerificacion', event)"
											value="#{EmbozadoLotesPlasticosBean.codCuentaTxt}"/>

											<x:outputText id="otxtVerificacion"
												value="Código Verificador: " styleClass="textoACenter" />
											<x:inputText id="txtVerificacion"
												styleClass="bordeceldainferior" maxlength="1" size="50"
												style="width: 60px;margin-top:8px" 
												onkeypress="javascript:return changeInputFocus(this, 1, 'EmbozadoLotePlastico:btnRenovarPlasticos', event)"
												value="#{EmbozadoLotesPlasticosBean.codVerificacionTxt}"/>
											<f:verbatim>&nbsp;</f:verbatim>
											<f:verbatim>&nbsp;</f:verbatim>	
											<f:verbatim>&nbsp;</f:verbatim>
											<x:commandButton id="btnRenovarPlasticos" 
					       						 value="Confirmar plástico"
					       						 title="Confirma Plástico" styleClass="btn btn-primary btn-flat"
					       						 action="#{EmbozadoLotesPlasticosBean.confirmarPlastico}"/>
									</x:panelGrid>
									
									<x:panelGrid columns="1" width="400" align="center" rendered="#{EmbozadoLotesPlasticosBean.mostrarHtmlConfLotes}">
										<x:outputText value="#{EmbozadoLotesPlasticosBean.mensajeConfPlastico}" escape="false"/>
									</x:panelGrid>
									
									<f:verbatim>
										<hr WIDTH="600" ALIGN="center">
									</f:verbatim>
									<x:panelGrid align="center" width="400" >
										<x:outputText value="Regenerar archivos de embozo" style="font-size:25px; text-align: center;" />
										<x:panelGrid id="areaRegenerar" columns="3" align="center" width="600">
											<x:outputText id="titPlastico" value="Número de plástico a regenerar"></x:outputText>
											<x:inputText id="nroPlastico" maxlength="16" 
												onkeypress="javascript:return changeInputFocus(this, 16, 'EmbozadoLotePlastico:btnRegenerar', event)"
												value="#{EmbozadoLotesPlasticosBean.nroPlasticoRegenerar}"></x:inputText>
											<x:commandButton id="btnRegenerar" 
								       						 value="Regenerar Archivo"
								       						 title="Regenerar Archivo" styleClass="btn btn-primary btn-flat pull-right"
								       						 action="#{EmbozadoLotesPlasticosBean.regenerarArchivoEmbozo}"/>
						       			</x:panelGrid>
					       			</x:panelGrid>
									<f:verbatim>&nbsp;</f:verbatim>
									<x:panelGrid id="areaVolver" columns="1" align="center" width="600">
										<x:panelGrid id="subArea" align="right">
											<x:commandButton id="btnVolver" 
							       						 value="Volver"
							       						 title="Volver" styleClass="btn btn-primary btn-flat"
							       						 action="#{EmbozadoLotesPlasticosBean.volverPaginaLotes}"/>
						       			</x:panelGrid>
					       			</x:panelGrid>
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
    document.getElementById("sideMenu").innerHTML += generateMenu(obj,"mainMenu__idJsp1_menu:A]\#{EmbozadoLotesPlasticosBean.inicializar}") + `</li>`;
}

</script>
<%@include file="/inc/scripts.jsp" %>
	<script type="text/javascript">
		document.getElementById('EmbozadoLotePlastico:txtOperacion').select();
		document.getElementById('EmbozadoLotePlastico:txtOperacion').focus();
	 	//popup('http://localhost:8080/archivos/lotesPlasticos/000130/acuses000130.pdf',700,400);
	 	
	 	function limpiaCampos(){
	 		document.getElementById('EmbozadoLotePlastico:txtlote').value = '';
	 		document.getElementById('EmbozadoLotePlastico:txtCliente').value = '';
	 		document.getElementById('EmbozadoLotePlastico:txtVerificacion').value = ''; 
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
</body>
</html>
</f:view>