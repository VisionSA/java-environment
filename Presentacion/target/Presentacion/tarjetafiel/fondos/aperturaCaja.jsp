<%@include file="/inc/tags.jsp" %>
<jsp:useBean id="ahora" class="java.util.Date" scope="request"/>

<f:view>
<html lang="es">
<head>
	<title><h:outputText value="#{AperturaCajaBean.tituloLargo}"/></title>
	<s:script language="javascript">
		function recargar() {
			document.getElementById('amAperturaCajaForm').submit();
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

<jsp:include page="/inc/includes.jsp"/>

<body class="hold-transition skin-blue sidebar-mini" onbeforeunload="ShowWait('amCajaForm');" onload="if('${AperturaCajaBean.popup.mostrar}'=='true') {viewDialog.show();} else {window.scrollTo(0,${ScrollBean.hiddenScrollY});}">

<h:form id="mainMenu" style="display: none">
	<jsp:include page="/inc/navigation_test.jsp" />
	<x:commandLink id="cerrarSesion" action="#{LoginBean.salir}"/>
</h:form>

<jsp:include page="/inc/header.jsp" />

<!-- Content Header (Page header) -->
<section class="content-header">
  <h1>
    ${AperturaCajaBean.tituloCorto}
    <small>${AperturaCajaBean.tituloLargo}</small>
  </h1>
</section>

<section class="content">
	<div class="box box-default">
		<div class="box-header"><h3></h3>
		</div>

<center>

	<h:form id="amCajaForm">

	<%-- GRABA EL ESTADO DEL SCROLL(Dibujar solo si el popup no se muestra) --%>
	<h:panelGroup rendered="#{!ApertCajaBean.popup.mostrar}">
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
								dialogTitle="#{AperturaCajaBean.tituloCorto}">
						<h:panelGrid columns="2" width="500">
							<x:graphicImage value="/img/#{AperturaCajaBean.popup.nombreIcono}" />
							<h:outputText value="#{AperturaCajaBean.popup.mensaje}" styleClass="texto"/>
						</h:panelGrid>
						<h:panelGrid columns="1" align="center" width="500">
							<x:commandButton action="#{AperturaCajaBean.irANuevaApertura}" 
								onclick="clickLink('nuevaApertura');dojo.widget.byId('viewDialog').hide();"
								value="Nueva Apertura" styleClass="botones" title="Efectuar nueva apertura."/>
					   </h:panelGrid>
					</s:modalDialog>
					<x:commandLink id="nuevaApertura" action="#{AperturaCajaBean.irANuevaApertura}" forceId="true" style="display: block;"/>
						
					<h:panelGrid columns="1" align="center" width="850" id="uno">
					    <h:panelGrid columns="2" rendered="#{AperturaCajaBean.mostrarCajas}">
					        <h:outputText value="Caja" styleClass="texto"/>
					    	<h:selectOneMenu id="lstCaja" value="#{AperturaCajaBean.idCajaSeleccionada}"
								styleClass="lista" immediate="true" onfocus="encender(this);" 
								onblur="apagar(this);" style="width: 200px"
								binding="#{AperturaCajaBean.cajaHtml}"
								valueChangeListener="#{AperturaCajaBean.cambiarCaja}"
								onchange="submit();">
						 
								<f:selectItems value="#{AperturaCajaBean.cajaItems}"/>
							</h:selectOneMenu>
					    </h:panelGrid>
					    <h:outputText value="Todas las cajas ya han sido abiertas."  rendered="#{!AperturaCajaBean.mostrarCajas}"
								styleClass="texto" style="color:green"  id="outMsg"/>
						<s:fieldset legend="Seleccione Operador" rendered="#{AperturaCajaBean.mostrarPanel}">
						<h:panelGrid id="panelPrincipalUno" columns="2" width="600" align="center">
							<h:outputText value="Operador" styleClass="texto"/>
							<h:selectOneMenu id="lstOperador" value="#{AperturaCajaBean.idOperadorSeleccionado}"
								styleClass="lista" immediate="true" onfocus="encender(this);"
								onblur="apagar(this);" style="width: 200px">
								<f:selectItems value="#{AperturaCajaBean.operadorItems}"/>
							</h:selectOneMenu>	
							<h:outputText value="Saldo Anterior" styleClass="texto"/>
							<h:outputText id="nombreFiltro" value="#{AperturaCajaBean.cajaApertura.saldoInicial}"/>
							<h:outputText value="Saldo Anterior" styleClass="texto" rendered="false"/>
							<h:outputText id="saldoAnt" value="#{AperturaCajaBean.saldoInicial}" rendered="false"/>
						    
						</h:panelGrid>
						<f:verbatim>&nbsp;</f:verbatim>
						   <s:fieldset legend="Detalle Saldo Anterior">
							<h:panelGrid id="panel2" columns="1" style="height: 150" >
										<h:panelGrid columns="2" id="panelFormaPago" >
						                <h:outputText value="Medios de Pago Habilitados" style="FONT-SIZE: large;" styleClass="texto"/>
			                        
										<f:verbatim>&nbsp;</f:verbatim>
											 <c:if test="${!empty AperturaCajaBean.tablaDeFormaDePago}">			
											<h:dataTable value="#{AperturaCajaBean.tablaDeFormaDePago}" id="tablaFormaPago"
														 styleClass="table-bordered table-striped"
							                             headerClass="standardTable_Header" 
							                             footerClass="standardTable_Header"
							                             rowClasses="standardTable_Row1,standardTable_Row2"
							                             columnClasses="tdA,tdB dinero"							
														 var="forma" style=" width : 700px;">
						                       							
					                            
						                        <h:column>
						                            <f:facet name="header">
						                                <h:outputText value="Forma Pago" styleClass="texto" />
						                            </f:facet>
						                            <h:outputText value="#{forma.formaPago.descripcion}" />
						                        </h:column>
						                        
						                         <h:column>
						                            <f:facet name="header">
						                                <h:outputText value="Monto" styleClass="texto" />
						                            </f:facet>
						                            <h:outputText value="#{forma.monto}" />
						                        </h:column>
						                        
						                        
						                   </h:dataTable>
					                        </c:if>
                    			          	<c:if test="${empty AperturaCajaBean.tablaDeFormaDePago}">
								         	<h:outputText value="No se encontraron Medios de Pago." styleClass="texto" style="color:green"/>
								        	</c:if>
					            	</h:panelGrid>     
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
						<x:commandButton id="buttonGrabar" value="Guardar" action="#{AperturaCajaBean.grabar}" styleClass="btn btn-primary btn-flat"  rendered="#{AperturaCajaBean.mostrarCajas}"/>
						<x:commandButton id="buttonCancelar" value="Cancelar" action="#{AperturaCajaBean.cancelar}" styleClass="btn btn-primary btn-flat" />
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
    document.getElementById("sideMenu").innerHTML += generateMenu(obj,"mainMenu__idJsp2_menu:A]\#{AperturaCajaBean.inicializar}") + `</li>`;
}

</script>

<%@include file="/inc/scripts.jsp" %>

</body>
</html>
</f:view>
