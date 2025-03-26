<%@include file="/inc/tags.jsp" %>
<jsp:useBean id="ahora" class="java.util.Date" scope="request"/>

<f:view>
<html lang="es">
<head>
	<title><h:outputText value="#{CambioDiaCierreClienteBean.tituloLargo}"/></title>
	<s:script language="javascript">
		function recargar() {
			document.getElementById('cambioDiaCierreCliente').submit();
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

<body class="hold-transition skin-blue sidebar-mini" onbeforeunload="ShowWait('cambioDiaCierreCliente');" onload="if('${CambioDiaCierreClienteBean.popup.mostrar}'=='true') {viewDialog.show();} else {window.scrollTo(0,${ScrollBean.hiddenScrollY});}">

<h:form id="mainMenu" style="display: none">
	<jsp:include page="/inc/navigation_test.jsp" />
	<x:commandLink id="cerrarSesion" action="#{LoginBean.salir}"/>
</h:form>

<jsp:include page="/inc/header.jsp" />

<!-- Content Header (Page header) -->
<section class="content-header">
  <h1>
    ${CambioDiaCierreClienteBean.tituloCorto}
    <small>${CambioDiaCierreClienteBean.tituloLargo}</small>
  </h1>
</section>

<section class="content">
	<div class="box box-default">
		<div class="box-header"><h3></h3>
		</div>

<center>
	

	<h:form id="cambioDiaCierreCliente" enctype="multipart/form-data">

	<%-- GRABA EL ESTADO DEL SCROLL(Dibujar solo si el popup no se muestra) --%>
	<h:panelGroup rendered="#{!CambioDiaCierreClienteBean.popup.mostrar}">
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
								dialogTitle="#{CambioDiaCierreClienteBean.tituloCorto}">
						<h:panelGrid columns="2" width="500">
							<x:graphicImage value="/img/#{CambioDiaCierreClienteBean.popup.nombreIcono}" />
							<h:outputText value="#{CambioDiaCierreClienteBean.popup.mensaje}" styleClass="texto"/>
						</h:panelGrid>
						<h:panelGrid columns="3" width="500">
							<x:commandButton action="#{CambioDiaCierreClienteBean.irAContinuar}" 
								onclick="clickLink('continuar');dojo.widget.byId('viewDialog').hide();"
								value="Continuar" styleClass="botones" title="Continuar en la pantalla."/>
							<f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim>
							<x:commandButton action="#{CambioDiaCierreClienteBean.irASalir}" 
								onclick="clickLink('salir');dojo.widget.byId('viewDialog').hide();"
								value="Salir" styleClass="botones" title="Salir."/>						
						</h:panelGrid>
					</s:modalDialog>
					<x:commandLink id="continuar" action="#{CambioDiaCierreClienteBean.irAContinuar}" forceId="true" style="display: block;"/>					

					<!-- DATOS PERSONALES DEL CLIENTE -->
					<s:layoutingTitlePane id="paraDatosPersonales" label="Datos Personales" containerNodeClass="contentTitlePane" 
							labelNodeClass="labelTitlePane" value="false" >
						<h:panelGrid columns="1" id="panelParaDatosPersonales" align="center" width="850">
							<h:panelGrid columns="4" id="pnlGridPersonales" align="center" width="650">

								<h:outputText id="outApellido" value="Apellido: " styleClass="texto" />
								<x:inputText id="inApellido" value="#{CambioDiaCierreClienteBean.individuoEvaluacion.apellido}"  forceId="true" size="50"
									maxlength="100" styleClass="bordeceldatext" style="width: 150px" onfocus="encender(this);" onblur="apagar(this);"
									disabled="true"/>
									
								<h:outputText id="outNombre" value="Nombres: " styleClass="texto" />
								<x:inputText id="inNombre" value="#{CambioDiaCierreClienteBean.individuoEvaluacion.nombres}" forceId="true" size="50"
									maxlength="100" styleClass="bordeceldatext" style="width: 150px" onfocus="encender(this);" onblur="apagar(this);"
									disabled="true"/>
								
								<h:outputText id="outTipoDNI" value="Tipo Documento: " styleClass="texto" />
								<h:selectOneMenu id="lstTDoc" value="#{CambioDiaCierreClienteBean.idTipoDocumentoSeleccionado}"
									styleClass="lista" immediate="true" onfocus="encender(this);" onblur="apagar(this);" style="width: 150px"
									disabled="true">
									<f:selectItems value="#{CambioDiaCierreClienteBean.listTipoDni}" id="selectItemIdTipoDoc"/>
								</h:selectOneMenu>

								<h:outputText id="outNroDoc" value="Número: " styleClass="texto" />
								<x:inputText id="inNroDoc" forceId="true" value="#{CambioDiaCierreClienteBean.individuoEvaluacion.nroDocumento}" size="20"
									maxlength="20" styleClass="bordeceldatext" style="width: 150px" onfocus="encender(this);" onblur="apagar(this);"
									onkeypress="return soloEnteros(this,event);" disabled="true"/>


			 					<h:outputText value="Fecha Nacimiento: " id="outFechaNac" styleClass="texto"/>
								<x:inputText id="inFechaNac" forceId="true" value="#{CambioDiaCierreClienteBean.fechaNacimiento}" size="20"
									maxlength="20" styleClass="bordeceldainferior" style="width: 150px" onfocus="encender(this);" onblur="apagar(this);"
									disabled="true"/>

								<f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim>
								<f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim>
							
							</h:panelGrid>
						</h:panelGrid>
					</s:layoutingTitlePane>



					
					<!-- DATOS DE LA FACTURACION ACTUAL -->
					<s:layoutingTitlePane id="paraFacturacion" label="Datos Facturacion Actual" containerNodeClass="contentTitlePane" 
							labelNodeClass="labelTitlePane" value="false" >
						<h:panelGrid columns="1" id="panelDiaPago" align="center" width="850">
							<h:panelGrid columns="4" id="pnlGridDiaPago" align="center" width="650">
								<h:outputText value="Dia de Cierre:" id="outDiaPago" styleClass="texto" />
								<x:inputText id="inDiaPago" value="#{CambioDiaCierreClienteBean.diaPagoActual}"  forceId="true" size="50"
									maxlength="100" styleClass="bordeceldatext" style="width: 100px" onfocus="encender(this);" onblur="apagar(this);"
									disabled="true"/>
								
								<h:outputText value="Vencimiento Resumen Actual:" id="outVtoResumen" styleClass="texto" />
								<x:inputText id="inFechaVtoActual" value="#{CambioDiaCierreClienteBean.vencimientoResumenActual}"  forceId="true" size="50"
									maxlength="100" styleClass="bordeceldatext" style="width: 100px" onfocus="encender(this);" onblur="apagar(this);"
									disabled="true"/>

								
							</h:panelGrid>
						</h:panelGrid>
					</s:layoutingTitlePane>

					<!-- DATOS DE LA FACTURACION CAMBIO -->
					<s:layoutingTitlePane id="paraFacturacionCambio" label="Datos Cambio de Facturacion" containerNodeClass="contentTitlePane" 
							labelNodeClass="labelTitlePane" value="false" >
						<h:panelGrid columns="1" id="panelDiaPagoCambio" align="center" width="850">
							<h:panelGrid columns="4" id="pnlGridDiaPagoCambio" align="center" width="650">

							<h:outputText value="Dia de Cierre:" id="outDiaPagoCambio" styleClass="texto" />
							<h:selectOneMenu id="lstDiaPago" value="#{CambioDiaCierreClienteBean.idDiaPagoSeleccionado}" styleClass="lista" 
								immediate="true" onfocus="encender(this);" onblur="apagar(this);" style="width: 200px" disabled="true"
								valueChangeListener="#{CambioDiaCierreClienteBean.cambiarVencimientoResumenCambio}"
								binding="#{CambioDiaCierreClienteBean.diaPagoCambioHtml}"
								onchange="submit();"> 
								<f:selectItems value="#{CambioDiaCierreClienteBean.listDiaPago}" id="selectItemDiaPag" />
							</h:selectOneMenu>


							<h:outputText value="Vencimiento Resumen Actual:" id="outVtoResumenCambio" styleClass="texto" />
							<x:inputText id="inFechaVtoActualCambio" value="#{CambioDiaCierreClienteBean.vencimientoResumenDelCambio}"  forceId="true" size="50"
								maxlength="100" styleClass="bordeceldatext" style="width: 100px" onfocus="encender(this);" onblur="apagar(this);"
								disabled="true"/>

							</h:panelGrid>
						</h:panelGrid>
					</s:layoutingTitlePane>

					<s:layoutingTitlePane id="listadoTareasPendientes" label="Historico de cambios de dia cierre." 
										      containerNodeClass="contentTitlePaneAuto" labelNodeClass="labelTitlePaneAuto" >
						<c:if test="${!empty CambioDiaCierreClienteBean.listHistoricoCambioDiaPago}">
							<f:verbatim>
								<display:table id="listaCodComercio" name="sessionScope.CambioDiaCierreClienteBean.listHistoricoCambioDiaPago"
									defaultsort="1" pagesize="10"
									class="table-bordered table-striped"
									requestURI="/tarjetafiel/transacciones/cambioDiaCierreCliente.jsf"
									export="${lst:contains(requestScope.permisos,'exportacion')}" requestURIcontext="true" style="width: 900px;">

										<display:column property="idCambioDiaPagoHis" title="Nro. Cambio" sortable="true" class="tdA"/>
										<display:column property="diaPagoAnterior.diaPago" title="Dia Cierre" sortable="true" class="tdA"/>
										<display:column property="diaPagoCambio.diaPago" title="Dia Cambio" sortable="true" class="tdA"/>
									    <display:column property="fecha" title="Fecha" sortable="true" class="tdA"/>
										<display:column property="operador.apellido" title="Operador" sortable="true" class="tdA"/>
										

										<display:setProperty name="export.amount" value="list" />
										<display:setProperty name="export.xml" value="true" />
										<display:setProperty name="export.pdf" value="true" />
										<display:setProperty name="export.excel.include_header" value="true" />
										<display:setProperty name="export.banner">
											<div style="width: 900px;" class="exportlinks">Exportar a: {0}</div>
										</display:setProperty>
										<display:setProperty name="basic.show.header" value="true" />
										<display:setProperty name="basic.msg.empty_list" value="No se encontraron elementos." />
										<display:setProperty name="sort.amount" value="list" />
										<display:setProperty name="paging.banner.group_size" value="6" />
										<display:setProperty name="paging.banner.placement" value="bottom" />
										<display:setProperty name="paging.banner.item_name" value="dia pago" />
										<display:setProperty name="paging.banner.items_name" value="dias pago" />
										<!-- 
										<display:setProperty name="paging.banner.some_items_found">
											<div class="pagebanner" align="center" style="width: 900px;">{0} {1} encontrados, mostrando desde el {2} hasta el {3}</div>
										</display:setProperty>
										 -->
										<display:setProperty name="paging.banner.no_items_found">
											<div class="pagebanner">No se encontraron {0}.</div>
										</display:setProperty>						    
										<display:setProperty name="paging.banner.one_item_found">
											<div class="pagebanner">Un {0} encontrado.</div>
										</display:setProperty>						    				
										<display:setProperty name="paging.banner.all_items_found">
											<div class="pagebanner">{0} {1} encontrados, mostrando todos los {2}.</div>
										</display:setProperty>
										<display:setProperty name="paging.banner.full">
											<div class="pagelinks" align="center" style="width: 900px;"><a href={1}><img src="<%=request.getContextPath()%>/img/icon/skipb_16.gif"></a><a href={2}><img src="<%=request.getContextPath()%>/img/icon/rewnd_16.gif"></a>{0}<a href={3}><img src="<%=request.getContextPath()%>/img/icon/fastf_16.gif"></a><a href={4}><img src="<%=request.getContextPath()%>/img/icon/skipf_16.gif"></a></div>
										</display:setProperty>
										<display:setProperty name="paging.banner.first">
											<div class="pagelinks" align="center" style="width: 900px;"><a href={1}><img src="<%=request.getContextPath()%>/img/icon/skipb_16.gif"></a><a href={2}><img src="<%=request.getContextPath()%>/img/icon/rewnd_16.gif"></a>{0}<a href={3}><img src="<%=request.getContextPath()%>/img/icon/fastf_16.gif"></a><a href={4}><img src="<%=request.getContextPath()%>/img/icon/skipf_16.gif"></a></div>
										</display:setProperty>
										<display:setProperty name="paging.banner.last">
											<div class="pagelinks" align="center" style="width: 900px;"><a href={1}><img src="<%=request.getContextPath()%>/img/icon/skipb_16.gif"></a><a href={2}><img src="<%=request.getContextPath()%>/img/icon/rewnd_16.gif"></a>{0}<a href={3}><img src="<%=request.getContextPath()%>/img/icon/fastf_16.gif"></a><a href={4}><img src="<%=request.getContextPath()%>/img/icon/skipf_16.gif"></a></div>
										</display:setProperty>
								</display:table>
							</f:verbatim>
						</c:if>				
					</s:layoutingTitlePane>

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
						<x:commandButton id="buttonGrabar" value="Aceptar Cambio" action="#{CambioDiaCierreClienteBean.grabar}" styleClass="botones" disabled="#{CambioDiaCierreClienteBean.verGrabar}"/>
						<x:commandButton id="buttonCancelar" value="Salir" action="#{CambioDiaCierreClienteBean.cancelar}" styleClass="botones" />
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
    document.getElementById("sideMenu").innerHTML += generateMenu(obj) + `</li>`;
}

</script>

<%@include file="/inc/scripts.jsp" %>
</body>
</html>
</f:view>
