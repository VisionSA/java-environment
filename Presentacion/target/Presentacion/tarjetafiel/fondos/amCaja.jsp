<%@include file="/inc/tags.jsp" %>
<jsp:useBean id="ahora" class="java.util.Date" scope="request"/>

<f:view>
<html lang="es">
<head>
	<title><h:outputText value="#{CajaBean.tituloLargo}"/></title>
	<s:script language="javascript">
		function recargar() {
			document.getElementById('amCajaForm').submit();
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

<body class="hold-transition skin-blue sidebar-mini" onbeforeunload="ShowWait('amCajaForm');" onload="if('${CajaBean.popup.mostrar}'=='true') {viewDialog.show();} else {window.scrollTo(0,${ScrollBean.hiddenScrollY});}">

<h:form id="mainMenu" style="display: none">
  <jsp:include page="/inc/navigation_test.jsp" />
  <x:commandLink id="cerrarSesion" action="#{LoginBean.salir}"/>
</h:form>

<jsp:include page="/inc/header.jsp" />

<!-- Content Header (Page header) -->
<section class="content-header">
  <h1>
    ${CajaBean.tituloCorto}
    <small>${CajaBean.tituloLargo}</small>
  </h1>
</section>

<section class="content">

<div class="box box-default">
<div class="box-header"><h3></h3></div>

<center>
	<secure:check/>

	<h:form id="amCajaForm">

	<%-- GRABA EL ESTADO DEL SCROLL(Dibujar solo si el popup no se muestra) --%>
	<h:panelGroup rendered="#{!CajaBean.popup.mostrar}">
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
								dialogTitle="#{CajaBean.tituloCorto}">
						<h:panelGrid columns="2" width="500">
							<x:graphicImage value="/img/#{CajaBean.popup.nombreIcono}" />
							<h:outputText value="#{CajaBean.popup.mensaje}" styleClass="texto"/>
						</h:panelGrid>
						<h:panelGrid columns="3" width="500">
							<x:commandButton action="#{CajaBean.irANuevoCaja}" 
								onclick="clickLink('nuevoCaja');dojo.widget.byId('viewDialog').hide();"
								value="Nuevo" styleClass="botones" title="Agregar nueva."/>
							<x:commandButton action="#{CajaBean.irAModificarCaja}" 
								onclick="clickLink('modificarCaja');dojo.widget.byId('viewDialog').hide();"
								value="Modificar" styleClass="botones" title="Seguir modificando."/>
							<x:commandButton action="#{CajaBean.irAListarCaja}" 
								onclick="clickLink('listarCaja');dojo.widget.byId('viewDialog').hide();"
								value="Listar" styleClass="botones" title="Ir al listado."/>
						</h:panelGrid>
					</s:modalDialog>
					<x:commandLink id="nuevoCaja" action="#{CajaBean.irANuevoCaja}" forceId="true" style="display: block;"/>
					<x:commandLink id="modificarCaja" action="#{CajaBean.irAModificarCaja}" forceId="true" style="display: block;"/>
					<x:commandLink id="listarCaja" action="#{CajaBean.irAListarCaja}" forceId="true" style="display: block;"/>
						
					<h:panelGrid columns="1" align="center" width="850" id="uno">
						<s:fieldset legend="Caja">
						<h:panelGrid id="panelPrincipalUno" columns="2" width="600" align="center">
							
							<h:outputText value="Sucursal Fiel" styleClass="texto"/>
							<h:selectOneMenu id="lstSucursal" value="#{CajaBean.idSucursalSeleccionada}"
								styleClass="lista" immediate="true" onfocus="encender(this);"
								onblur="apagar(this);" style="width: 200px">
								<f:selectItems value="#{CajaBean.sucursalItems}"/>
							</h:selectOneMenu>
							
							<h:outputText value="Descripcion Caja" styleClass="texto"/>
							<h:inputText id="nombreFiltro" value="#{CajaBean.caja.descripcion}"
								styleClass="bordeceldatext" maxlength="30" size="100"
								style="width: 200px" onfocus="encender(this);" onblur="apagar(this);"/>
							
						  	<h:outputText value="Impresora" styleClass="texto"/>
							<h:selectOneMenu id="lstImpresora" value="#{CajaBean.idImpresoraSeleccionada}"
								styleClass="lista" immediate="true" onfocus="encender(this);"
								onblur="apagar(this);" style="width: 200px">
								<f:selectItems value="#{CajaBean.impresoraItems}"/>
							</h:selectOneMenu>
							<h:outputText value="Operador por Defecto" styleClass="texto"/>
							<h:selectOneMenu id="lstOperador" value="#{CajaBean.idOperadorSeleccionado}"
								styleClass="lista" immediate="true" onfocus="encender(this);"
								onblur="apagar(this);" style="width: 200px">
								<f:selectItems value="#{CajaBean.operadorItems}"/>
							</h:selectOneMenu>	
							<h:outputText value=""  id="relleno" rendered="false"/>
							<h:panelGrid id="secun" columns="3" rendered="false">
			                     <h:outputText value="Numero de Cuenta : " styleClass="texto"/>
								 <h:inputText value="#{CajaBean.idPlanCuentaABuscar}" onkeypress="return soloEnteros(this, event);" maxlength="4" id="inputCuentaBusquedaLibro" title="Introduzca el nro. de  cuenta buscada." style=" width : 47px;"/>
								 <x:commandLink id="buscarCuentaLink"  action="#{CajaBean.buscarCuentaPopup}">
									<x:graphicImage value="/img/icon/srch_24.gif" title="Buscar Cuenta." border="0"/>								
								 </x:commandLink>
						    </h:panelGrid>	
						    <h:outputText value=""  id="relleno2" />
						    <h:panelGrid columns="2">		
						      <h:outputText id="outhabilitada" value="Habilitada:" styleClass="texto"/>
						      <h:selectBooleanCheckbox id="boolChecHabilitada" value="#{CajaBean.habilitada}" />
						    </h:panelGrid>	
						    
						</h:panelGrid>
						   <s:fieldset legend="Gestionar Medio de Pago">
							<h:panelGrid id="panel2" columns="1" style="height: 150" >
										<h:panelGrid columns="2" id="panelFormaPago" >
						                <h:outputText value="Forma de Pago" style="FONT-SIZE: large;" styleClass="texto"/>
			                        
										<f:verbatim>&nbsp;</f:verbatim>
											 <c:if test="${!empty CajaBean.tablaDeFormaDePago}">			
											 
											
											
											 
											<h:dataTable value="#{CajaBean.tablaDeFormaDePago}" id="tablaFormaPago"
														 styleClass="standardTable"
							                             headerClass="standardTable_Header" 
							                             footerClass="standardTable_Header"
							                             rowClasses="standardTable_Row1,standardTable_Row2"
							                             columnClasses="standardTable_Column,standardTable_ColumnCentered,standardTable_Column"							
														 var="forma" style=" width : 700px;">
						                       							
					                            
						                        <h:column>
						                            <f:facet name="header">
						                                <h:outputText value="Descripcion" styleClass="texto" style="font: bold;color: white;"/>
						                            </f:facet>
						                            <h:outputText value="#{forma.cajaMP.formaPago.descripcion}" />
						                        </h:column>
						                        
						                        <h:column>
						                            <f:facet name="header">
						                                <h:outputText value="Nro de Cuenta" styleClass="texto" style="font: bold;color: white;"/>
						                            </f:facet>
						                            <h:outputText value="#{forma.cajaMP.planCuentaDos.idPlanCuenta}" />
						                        </h:column>
						                         <h:column>
						                            <f:facet name="header">
						                                <h:outputText value="Habilitada" styleClass="texto" style="font: bold;color: white;"/>
						                            </f:facet>
						                            <h:outputText value="#{forma.cajaMP.habilitado}" />
						                        </h:column>
						                        
						                        
						                        
						                       
						                        
						                        <h:column>
													<x:commandLink action="#{CajaBean.eliminarFormaPago}" id="eliminarFPLink"  >
														<f:param id="idFormapago" name="idFormapago" value="#{forma.indice}"/>
														<x:graphicImage value="/img/cat_act.gif" title="Eliminar la forma de pago." border="0"/>
													</x:commandLink>
												</h:column>
											</h:dataTable>
					                        </c:if>
                    			          	<c:if test="${empty CajaBean.tablaDeFormaDePago}">
								         	<h:outputText value="No existen Formas de Pago." styleClass="texto" style="color:green"/>
								        	</c:if>
					                       
					
											
										</h:panelGrid>
				                         <h:panelGrid id="agreFomaPago" columns="5" align="right" width="700">
			                               <f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim>
			                               <f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim>
			                               <f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim>
			                               <f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim>
			                               <h:commandButton id="botonCargarFormaPago" value="Agregar" styleClass="botones" action="#{CajaBean.irAgregarFormaDePago}"/>
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
						<x:commandButton id="buttonGrabar" value="Guardar" action="#{CajaBean.grabar}" styleClass="botones"/>
						<x:commandButton id="buttonCancelar" value="Cancelar" action="#{CajaBean.cancelar}" styleClass="botones" />
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
    document.getElementById("sideMenu").innerHTML += generateMenu(obj,"mainMenu__idJsp2_menu:A]\#{CajaBean.inicializar}") + `</li>`;
}

</script>

<%@include file="/inc/scripts.jsp" %>

</body>
</html>
</f:view>
