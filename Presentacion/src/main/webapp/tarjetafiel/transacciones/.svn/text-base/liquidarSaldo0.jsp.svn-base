<%@include file="/inc/tags.jsp" %>
<%-- <jsp:useBean id="ahora" class="java.util.Date" scope="request"/> --%>

<f:view>
<html lang="es">
<head>
	<title><h:outputText value="Tarjeta Fiel - Liquidacion cuentas con saldo 0"/></title>
	<s:script language="javascript">
		function validar(input) {
			valor = input.value;
			if (isNaN(valor)){
				valor = valor.substring(0, valor.length -1);
			}
			input.value = valor;
		}
	</s:script>
</head>

<jsp:include page="/inc/includes.jsp"/>

<body class="hold-transition skin-blue sidebar-mini" onbeforeunload="ShowWait('LiquidarSaldo0');" 
		onload="if('${LiquidarSaldo0Bean.popup.mostrar}'=='true') {viewDialog.show();} else {window.scrollTo(0,${ScrollBean.hiddenScrollY});}">
		<h:form id="mainMenu" style="display: none">
	<jsp:include page="/inc/navigation_test.jsp" />
	<x:commandLink id="cerrarSesion" action="#{LoginBean.salir}"/>
</h:form>

<jsp:include page="/inc/header.jsp" />

<!-- Content Header (Page header) -->
<section class="content-header">
  <h1>
    ${LiquidarSaldo0Bean.tituloCorto}
    <small>${LiquidarSaldo0Bean.tituloLargo}</small>
  </h1>
</section>

<section class="content">
	<div class="box box-default">
		<div class="box-header"><h3></h3>
		</div>

	<center>
		<secure:check/>
	
		<h:form id="LiquidarSaldo0">
			<%-- GRABA EL ESTADO DEL SCROLL(Dibujar solo si el popup no se muestra) --%>
			<h:panelGroup rendered="#{!LiquidarSaldo0Bean.popup.mostrar}">
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
										dialogTitle="#{LiquidarSaldo0Bean.tituloCorto}">
								<h:panelGrid columns="2" width="500">
									<x:graphicImage value="/img/#{LiquidarSaldo0Bean.popup.nombreIcono}" />
									<h:outputText value="#{LiquidarSaldo0Bean.popup.mensaje}" styleClass="texto"/>
								</h:panelGrid>
								<h:panelGrid columns="3" width="500">
									<x:commandButton action="#{LiquidarSaldo0Bean.irAContinuar}" 
										onclick="clickLink('continuar');dojo.widget.byId('viewDialog').hide();"
										value="Continuar" styleClass="btn btn-primary btn-flat" title="Continuar en la pantalla."/>
									<f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim>
									<%--<x:commandButton action="#{LiquidarSaldo0Bean.irASalir}" 
										onclick="clickLink('salir');dojo.widget.byId('viewDialog').hide();"
										value="Salir" styleClass="botones" title="Salir."/>--%>						
								</h:panelGrid>
							</s:modalDialog>
							<x:commandLink id="continuar" action="#{LiquidarSaldo0Bean.irAContinuar}" forceId="true" style="display: block;"/>
							
							<h:panelGrid id="ingresarDatos" columns="5" align="center">
								<h:panelGrid id="titulares" columns="1" >
									<h:outputText id="txtIngDatos" value="Cuenta a liquidar: " styleClass="textoACenter"/>
								</h:panelGrid>							
								<f:verbatim>&nbsp;&nbsp;</f:verbatim>
								<h:inputText id="txtCuenta" value="#{LiquidarSaldo0Bean.cuentaLiquidar}"
										styleClass="bordeceldainferior" maxlength="8" size="8" style="width: 80px"
										onkeyup="validar(this);" />

								<f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim>
									
								<x:commandButton id="btnConsultarCuenta" 
		       						 value="Consultar Cuenta" 
		       						 action="#{LiquidarSaldo0Bean.buscarCuenta}" 
		       						 title="Consulta los items de la cuenta"
		       						 styleClass="btn btn-primary btn-flat"/>
		       						 
						</h:panelGrid>
						<c:if test="${LiquidarSaldo0Bean.verDetalles}">
							<h:panelGrid id="cliente" columns="2" >
									<h:outputText id="txtLeyendaCliente" value="Cliente: " styleClass="textoACenter"/>
									<h:outputText id="txtCliente" value="#{LiquidarSaldo0Bean.cliente.nombreCliente}" styleClass="textoACenter"/>
									
									<h:outputText id="txtLeyendaCuenta" value="Estado Cuenta: " styleClass="textoACenter"/>
									<h:outputText id="txtEstadoCuenta" value="#{LiquidarSaldo0Bean.cliente.estadoCobranza.descripcion}" styleClass="textoACenter"/>
							</h:panelGrid>
							
							<h:panelGrid>
								<s:filterTable id="filterTbl" var="tar" value="#{LiquidarSaldo0Bean.listaItemsLiquidaciones}"
		                                                styleClass="standardTable" 
		                                                headClass="standardTable_Header">
												        <s:sortableColumn
												        
												        
												        
												         field="detalle" text="Detalle" align="left" >
												            <h:outputText value="#{tar.detalleTransaccion}" />
												        </s:sortableColumn>
												        <s:sortableColumn field="fecha" text="Fecha" align="left">
												            <h:outputText value="#{tar.fecha}" />
												        </s:sortableColumn>
												        <s:sortableColumn field="cuota" text="Cuota" align="right">
												            <h:outputText value="#{tar.cuota} / #{tar.cantCuotas}" />
												        </s:sortableColumn>
		                                                <s:sortableColumn field="importe" dataType="Number" align="right" text="Importe">
												            <h:outputText value="#{tar.importe}" />
												        </s:sortableColumn>
												    </s:filterTable>
											
								</h:panelGrid>
								
								<h:panelGrid align="right">
									<x:commandButton id="btnLiquidar" 
				       						 value="Liquidar" 
				       						 action="#{LiquidarSaldo0Bean.liquidar}" 
				       						 title="Realiza la liquidación de la cuenta"
				       						 disabled="#{!LiquidarSaldo0Bean.permitirLiquidar}"/>
				       			</h:panelGrid>
			       		</c:if>
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
    document.getElementById("sideMenu").innerHTML += generateMenu(obj,"mainMenu__idJsp2_menu:A]\#{LiquidarSaldo0Bean.inicializar}") + `</li>`;
}

</script>

<%@include file="/inc/scripts.jsp" %>
</body>
</html>
</f:view>