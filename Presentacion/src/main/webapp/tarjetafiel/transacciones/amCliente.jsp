<%@include file="/inc/tags.jsp" %>
<jsp:useBean id="ahora" class="java.util.Date" scope="request"/>

<f:view>
<html lang="es">
<head>
	<title><h:outputText value="#{ClienteBean.tituloLargo}"/></title>
	<s:script language="javascript">
		function recargar() {
			document.getElementById('amClienteForm').submit();
		}
		
		function popup(pagina,popW,popH) {
			var w = 0, h = 0;
		   	w = screen.width;
		   	h = screen.height;

			var leftPos = (w-popW)/2, topPos = (h-popH)/2;
		    popupWindow=open(pagina,'','resizable=no,autohide=no,scrollbars=yes,width='+popW+',height='+popH+',top='+topPos+',left='+leftPos);
		    
		    if (popupWindow.opener == null){popupWindow.opener = self;}
		    
		};
		
		
		
	</s:script>
</head>

<jsp:include page="/inc/includes.jsp" />

<body class="hold-transition skin-blue sidebar-mini" onbeforeunload="ShowWait('amClienteForm');" onload="if('${ClienteBean.popup.mostrar}'=='true') {viewDialog.show();} else {window.scrollTo(0,${ScrollBean.hiddenScrollY});}">

<h:form id="mainMenu" style="display: none">
  <jsp:include page="/inc/navigation_test.jsp" />
  <x:commandLink id="cerrarSesion" action="#{LoginBean.salir}"/>
</h:form>

<jsp:include page="/inc/header.jsp" />

<!-- Content Header (Page header) -->
<section class="content-header">
  <h1>
    ${ClienteBean.tituloCorto}
    <small>${ClienteBean.tituloLargo}</small>
  </h1>
</section>

<section class="content">

<div class="box box-default">
<div class="box-header"><h3></h3></div>

<center>
	<secure:check/>

	<h:form id="amClienteForm">

	<%-- GRABA EL ESTADO DEL SCROLL(Dibujar solo si el popup no se muestra) --%>
	<h:panelGroup rendered="#{!ClienteBean.popup.mostrar}">
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
								dialogTitle="#{ClienteBean.tituloCorto}">
						<h:panelGrid columns="2" width="500">
							<x:graphicImage value="/img/#{ClienteBean.popup.nombreIcono}" />
							<h:outputText value="#{ClienteBean.popup.mensaje}" styleClass="texto"/>
						</h:panelGrid>
						<h:panelGrid columns="3" width="500">
							<x:commandButton action="#{ClienteBean.irANuevoCliente}" 
								onclick="clickLink('nuevoCliente');dojo.widget.byId('viewDialog').hide();"
								value="Nuevo" styleClass="btn btn-primary btn-flat" title="Agregar nueva."/>
							<x:commandButton action="#{ClienteBean.irAModificarCliente}" 
								onclick="clickLink('modificarCliente');dojo.widget.byId('viewDialog').hide();"
								value="Modificar" styleClass="btn btn-primary btn-flat" title="Seguir modificando."/>
							<x:commandButton action="#{ClienteBean.irAListarCliente}" 
								onclick="clickLink('listarCliente');dojo.widget.byId('viewDialog').hide();"
								value="Listar" styleClass="btn btn-primary btn-flat" title="Ir al listado."/>
						</h:panelGrid>
					</s:modalDialog>
					<x:commandLink id="nuevoCliente" action="#{ClienteBean.irANuevoCliente}" forceId="true" style="display: block;"/>
					<x:commandLink id="modificarCliente" action="#{ClienteBean.irAModificarCliente}" forceId="true" style="display: block;"/>
					<x:commandLink id="listarCliente" action="#{ClienteBean.irAListarCliente}" forceId="true" style="display: block;"/>

                     <h:panelGrid id="documento" columns="1" align="center" rendered="#{!ClienteBean.clienteCargado}">
						<h:commandButton id="botonModificarIndividuo" value="Cargar Datos del Individuo" styleClass="btn btn-primary btn-flat" action="#{ClienteBean.cargarDatosIndividuo}"/>
					 </h:panelGrid>
					 <h:panelGrid id="empresainexistente" columns="3" align="center" rendered="#{ClienteBean.mensajeParaAlta}">
						      <h:outputText value="El individuo ingresado no corresponde a ningun cliente existente. ¿Desea darlo de alta?." styleClass="texto" style="color:green"/>
						      <h:commandButton action="#{ClienteBean.darAltaCliente}" value="Si" id="si" styleClass="btn btn-primary btn-flat" />
						      <h:commandButton action="#{ClienteBean.cancelarAltaCliente}" value="No" id="no" styleClass="btn btn-primary btn-flat" />
					</h:panelGrid>
						      
						<f:verbatim>
							<br>
					    </f:verbatim>

					<h:panelGrid id="panelPrincipalUno" columns="1" width="900"  rendered="#{ClienteBean.clienteCargado}">
					    <s:fieldset legend="Datos Personales" id="personalesDelCliente">
					         <h:panelGrid columns="2" id="datosPartPanel">
					             <h:outputText value="Cuil: #{ClienteBean.cliente.individuo.cuil}" />
					             <h:outputText value="Apellido y Nombres: #{ClienteBean.cliente.individuo.apellido}, #{ClienteBean.cliente.individuo.nombres}"/>
					         </h:panelGrid>
					    </s:fieldset>
						<s:fieldset legend="Cliente" id="fieldPrincipalUno">
						    <f:verbatim>&nbsp;</f:verbatim>
						    <h:outputText value="Consumo" style="FONT-SIZE: large;" styleClass="texto"/>
						    <h:panelGrid id="consum" columns="6">
						         <h:outputText value="Consumo: " styleClass="texto" id="consumoHabilitado" style="width: 220px"/>
							     <h:selectOneMenu id="lstEstCons" value="#{ClienteBean.cliente.estadoCliente.habilitadoConsumo}"
									styleClass="lista" immediate="true" onfocus="encender(this);"
									onblur="apagar(this);" style="width: 220px" >
									<f:selectItems value="#{ClienteBean.estadoConsumoItems}"/>
						         </h:selectOneMenu>
						         <h:outputText value="Fecha Habilitación " styleClass="texto" id="fechaConsumo" style="width: 220px"/>
								 <h:outputText value="#{ClienteBean.fechaConsumo}" styleClass="textoblue" id="fechaConsumoDato"/>
								 <h:outputText value="Operador Responsable " styleClass="texto" id="operadorConsumo"/>
								 <h:outputText id="idoperadorhabilitadoconsumoFiltro" value="#{ClienteBean.cliente.idOperadorHabilitadoconsumo}" styleClass="textoblue" />
						    </h:panelGrid>
						    <f:verbatim>&nbsp;</f:verbatim>
						    <h:outputText value="Cliente" style="FONT-SIZE: large;" styleClass="texto"/>
						    <h:panelGrid id="client" columns="6">
						         <h:outputText value="Cliente: " styleClass="texto" id="clienteHabilitado" style="width: 220px"/>
							     <h:selectOneMenu id="lstEstClie" value="#{ClienteBean.cliente.estadoCliente}"
									styleClass="lista" immediate="true" onfocus="encender(this);"
									onblur="apagar(this);" style="width: 220px">
									<f:selectItems value="#{ClienteBean.estadoClienteItems}"/>
						         </h:selectOneMenu>
						         <h:outputText value="Fecha Habilitación " styleClass="texto" id="fechaCliente" style="width: 220px"/>
								 <h:outputText value="#{ClienteBean.fechaCliente}" styleClass="textoblue" id="fechaClienteDato"/>
								 <h:outputText value="Operador Responsable " styleClass="texto" id="operadorCliente"/>
								 <h:outputText id="idoperadorhabilitadoclienteFiltro" value="#{ClienteBean.cliente.idOperadorCliente}" styleClass="textoblue" />
						    </h:panelGrid>
						    <f:verbatim>&nbsp;</f:verbatim>
						    <h:outputText value="Cobranza" style="FONT-SIZE: large;" styleClass="texto"/>
						    <h:panelGrid id="cobran" columns="6">
						         <h:outputText value="Cobranza: " styleClass="texto" id="estadoCobranza" style="width: 220px"/>
								 <h:selectOneMenu id="lstEstCobranzas" value="#{ClienteBean.cliente.estadoCobranza}"
									styleClass="lista" immediate="true" onfocus="encender(this);"
									onblur="apagar(this);" style="width: 220px" >
									<f:selectItems value="#{ClienteBean.estadoCobranzaItems}"/>
						         </h:selectOneMenu>
                                 <h:outputText value="Fecha Estado Cobranza: " styleClass="texto" id="fechaCobranza" style="width: 220px"/>
								 <h:outputText value="#{ClienteBean.fechaCobranza}" styleClass="textoblue" id="fechaCobranzaDato"/>
								 <h:outputText value="Operador Responsable " styleClass="texto" id="operadorCobranza"/>
								 <h:outputText id="idoperadorhabilitadocobranzaFiltro" value="#{ClienteBean.cliente.idOperadorCobranza}" styleClass="textoblue" />
						    </h:panelGrid>
							<h:panelGrid id="panelSecundario" columns="6" width="600" align="center">
								
								<h:outputText value="Ciclo: " styleClass="texto" id="ciclo"/>
								<h:inputText id="cicloFiltro" value="#{ClienteBean.cliente.ciclo}" styleClass="bordeceldainferior" maxlength="200" size="200"
									style="width: 100px" onfocus="encender(this);" onblur="apagar(this);" onkeypress="return soloEnteros(this,event);"/>
								<h:outputText value="Limite Credito: " styleClass="texto" id="limiteCredito"/>
								<h:inputText id="limitecreditoFiltro" value="#{ClienteBean.cliente.limiteCredito}" styleClass="bordeceldainferior" 
									maxlength="10" size="10" style="width: 100px" onfocus="encender(this);" onblur="apagar(this);" onkeypress="return soloDecimales(this,event);"/>
									
								<h:outputText value="Saldo Linea: " styleClass="texto" id="saldoLine"/>
								<h:inputText id="saldolineaFiltro" value="#{ClienteBean.cliente.saldoLinea}" styleClass="bordeceldainferior" 
									maxlength="10" size="10" style="width: 100px" onfocus="encender(this);" onblur="apagar(this);" onkeypress="return soloDecimales(this,event);"/>
								</h:panelGrid>	
								
								
								
								
								
								
						                		
								
															
								
								
								
								
								
						    <h:panelGrid columns="1" id="titularOAdicional">
								
								<h:outputText value="El cliente es titular de esta tarjeta." styleClass="texto" style="color:green" rendered="#{ClienteBean.esTitular}"/>
								<h:outputText value="El cliente es adicional. El titular es #{ClienteBean.nombreTitular}" styleClass="texto" style="color:green" rendered="#{!ClienteBean.esTitular}"/>
								<h:panelGrid columns="2">
									<h:outputText value="Titular: " styleClass="texto" id="titular"/>
									<h:inputText id="idtitularFiltro" value="#{ClienteBean.cliente.idTitular}" styleClass="bordeceldainferior" maxlength="10" size="10"	
										style="width: 100px" onfocus="encender(this);" onblur="apagar(this);" onkeypress="return soloEnteros(this,event);"/>
									<h:outputText value="Adicional: " styleClass="texto" id="adicional"/>
									<h:inputText id="adicionalFiltro" value="#{ClienteBean.cliente.adicional}" styleClass="bordeceldainferior" maxlength="200" size="200"
										style="width: 100px" onfocus="encender(this);" onblur="apagar(this);" onkeypress="return soloEnteros(this,event);"/>	
								</h:panelGrid>
									
								
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
						<x:commandButton id="buttonGrabar" value="Guardar" action="#{ClienteBean.grabar}" styleClass="btn btn-primary btn-flat"/>
						<x:commandButton id="buttonCancelar" value="Cancelar" action="#{ClienteBean.cancelar}" styleClass="btn btn-primary btn-flat" />
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
    document.getElementById("sideMenu").innerHTML += generateMenu(obj,"mainMenu__idJsp2menu:A]\#{ClienteBean.inicializar}") + `</li>`;
}

</script>
<%@include file="/inc/scripts.jsp" %>
</body>
</html>
</f:view>

