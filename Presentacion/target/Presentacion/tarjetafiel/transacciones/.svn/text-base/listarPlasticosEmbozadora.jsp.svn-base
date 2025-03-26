<%@include file="/inc/tags.jsp" %>
<jsp:useBean id="ahora" class="java.util.Date" scope="request"/>

<f:view>
<html lang="es">
<head>
	<title><h:outputText value="#{ListaPlasticosEmbozadoraBean.tituloLargo}"/></title>
	<s:script language="javascript">
		function recargar() {
			document.getElementById('amListaPrecioForm').submit();
		}
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

<body class="hold-transition skin-blue sidebar-mini" onbeforeunload="ShowWait('amListaPlasticosForm');" onload="if('${ListaPlasticosEmbozadoraBean.popup.mostrar}'=='true') {viewDialog.show();} else {window.scrollTo(0,${ScrollBean.hiddenScrollY});};">

<h:form id="mainMenu" style="display: none">
  <jsp:include page="/inc/navigation_test.jsp" />
  <x:commandLink id="cerrarSesion" action="#{LoginBean.salir}"/>
</h:form>

<jsp:include page="/inc/header.jsp" />

<!-- Content Header (Page header) -->
<section class="content-header">
  <h1>
    ${ListaPlasticosEmbozadoraBean.tituloCorto}
    <small>${ListaPlasticosEmbozadoraBean.tituloLargo}</small>
  </h1>
</section>

<section class="content">

<div class="box box-default">
<div class="box-header"><h3></h3></div>	
   

<center>
	<secure:check/>

	<h:form  id="amListaPlasticosForm">

	<%-- GRABA EL ESTADO DEL SCROLL(Dibujar solo si el popup no se muestra) --%>
	<h:panelGroup rendered="#{!ListaPlasticosEmbozadoraBean.popup.mostrar}">
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
					<h:panelGrid columns="1" align="center" id="PanelPricipal" width="900">
						<%-- INCLUDE PARA LOS ERRORES --%>
						<h:panelGroup id="errores">
							<jsp:include page="/inc/error.jsp" />
						</h:panelGroup>

					<%-- POPUP PARA LOS MENSAJES DE EXITO Y FRACASO --%> 
					<s:modalDialog dialogId="viewDialog"
								dialogVar="viewDialog"
								styleClass="viewDialog"
								dialogTitle="#{ListaPlasticosEmbozadoraBean.tituloCorto}">
						<h:panelGrid columns="2" width="500">
							<x:graphicImage value="/img/#{ListaPlasticosEmbozadoraBean.popup.nombreIcono}" />
							<h:outputText value="#{ListaPlasticosEmbozadoraBean.popup.mensaje}" styleClass="texto"/>
						</h:panelGrid>
						<h:panelGrid columns="2" width="500">
							<x:commandButton action="#{ListaPlasticosEmbozadoraBean.irContinuar}" 
								onclick="clickLink('lnContinuar');dojo.widget.byId('viewDialog').hide();"
								value="Continuar Embozando" styleClass="btn btn-primary btn-flat" title="Ir a generar un nuevo embozo."/>
							<x:commandButton action="#{ListaPlasticosEmbozadoraBean.irEmbozarLote}" 
								onclick="clickLink('lnEmbozar');dojo.widget.byId('viewDialog').hide();" 
								value="Ir a Embozar Lotes " styleClass="btn btn-primary btn-flat" title="Ir a embozar lotes generados."
								rendered="#{ListaPlasticosEmbozadoraBean.mostrarBotonConfirmar}"/>

						</h:panelGrid>
					</s:modalDialog>
					<x:commandLink id="lnContinuar" action="#{ListaPlasticosEmbozadoraBean.irContinuar}" forceId="true" style="display: block;"/>
					<x:commandLink id="lnEmbozar" action="#{ListaPlasticosEmbozadoraBean.irEmbozarLote}" forceId="true" style="display: block;"/>
									
						<h:panelGrid id="tablaDetallesFiel" columns="1" align="center">
                             <c:if test="${!empty ListaPlasticosEmbozadoraBean.listaCuentas}">			
							 	<s:fieldset legend="Cuentas con Plasticos Pendientes de Embozar" id="fieldDomicilios">
							    <h:panelGrid columns="2" id="tablaDeDetallesFiel" width="850" align="center">
									<h:dataTable value="#{ListaPlasticosEmbozadoraBean.listaCuentas}"
										id="tableDeDetallesFiel" styleClass="table-bordered table-striped" headerClass="dataTable_Header"
										footerClass="standardTable_Header" rowClasses="standardTable_Row1,standardTable_Row2"
										columnClasses="standardTable_Column,standardTable_Column,standardTable_Column"
										var="item" style=" width : 840px;">
										
										<h:column>
											<f:facet name="header">
												<x:commandLink id="selecTodos" value="S/D" action="#{ListaPlasticosEmbozadoraBean.seleccionarTodos}" forceId="true" style="display: block;font: bold;color: white;"/>
											</f:facet>
											<h:selectBooleanCheckbox id="idCentral"  
												onclick ="checkearSeleccionado(this)" 
												value="#{item.embozar}" immediate="true"/>
										</h:column>
										<h:column >
											<f:facet  name="header" >
												<h:outputText value="Cuenta" styleClass="texto"  />
											</f:facet>
											<h:outputText value="#{item.idCuenta}"/>
										</h:column>
										<h:column>
											<f:facet name="header">
												<h:outputText value="Nombre del Titular de la Cuenta" styleClass="texto"  />
											</f:facet>
											<h:outputText value="#{item.nombreTitular}"/>
										</h:column>
										<h:column>
											<f:facet name="header">
												<h:outputText value="Operacion" styleClass="texto"  />
											</f:facet>
											<h:outputText value="#{item.operacion.descripcion}"/>
										</h:column>
										<h:column>
											<f:facet name="header">
												<h:outputText value="Plasticos a Embozar" styleClass="texto"  />
											</f:facet>
											<x:dataList id="plas" value="#{item.infoPlasticosAsociados}" 
											      var="infoPlastico" first="0" 
											      dir="LTR" >
													    <x:outputText  value="#{infoPlastico}"/>
													    <f:verbatim>
													    	<br>
													    </f:verbatim>
											</x:dataList>
										</h:column>
										
					    	</h:dataTable>
						</h:panelGrid>
				</s:fieldset>
				
											
						    <f:verbatim><hr align="center" width="700"></f:verbatim>
							<h:panelGrid columns="1" width="727" id="panelBotonera" align="center" >
								<h:panelGrid columns="10" id="botoneras">
									<f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim>
									<f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim>
									<f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim>
									<f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim>
									<f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim>
									<f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim>
									<f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim>
									<h:outputText value="      " id="espacio" />
									<x:commandButton id="buttonProcesarEmbozado" value="Procesar Embozado"   
									action="#{ListaPlasticosEmbozadoraBean.procesarEmbozado}" styleClass="btn btn-primary btn-flat"/>
									<f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim>
								</h:panelGrid>							
							</h:panelGrid>
												
					        </c:if>
                    		<c:if test="${empty ListaPlasticosEmbozadoraBean.listaCuentas}">
								 <h:outputText value="No existen plasticos pendiente de embozar." rendered="#{!ListaPlasticosEmbozadoraBean.aborto}" styleClass="texto" id="detallesInexistentes" style="color:green"/>
							</c:if>	
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
    document.getElementById("sideMenu").innerHTML += generateMenu(obj,"mainMenu__idJsp2_menu:A]\#{ListaPlasticosEmbozadoraBean.inicializar}") + `</li>`;
}

</script>
<%@include file="/inc/scripts.jsp" %>
</body>
</html>
</f:view>
