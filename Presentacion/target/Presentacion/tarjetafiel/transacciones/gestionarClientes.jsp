<%@include file="/inc/tags.jsp" %>
<jsp:useBean id="ahora" class="java.util.Date" scope="request"/>

<f:view>
<html>
<head>

	<title> <h:outputText value="#{GestionarClientesBean.tituloLargo}"/></title>

	<s:script language="javascript">
		function recargar() {
			document.getElementById('liquidacionParticularClienteForm').submit();
		}
		function popup(pagina,popW,popH) {
			var w = 0, h = 0;
		   	w = screen.width;
		   	h = screen.height;

			var leftPos = (w-popW)/2, topPos = (h-popH)/2;
		    popupWindow=open(pagina,'','resizable=no,autohide=no,scrollbars=yes,width='+popW+',height='+popH+',top='+topPos+',left='+leftPos);
		    
		    if (popupWindow.opener == null){popupWindow.opener = self;}
		    
		}
		
		function marcar(obj) {
		 	var len = document.getElementById('liquidacionParticularClienteForm:filterTbl').rows.length;
		    var val = obj.checked;
		     	
			for (var i = 0; i < len; i++){
				document.getElementById('liquidacionParticularClienteForm:filterTbl:'+i+':seleccionado').checked = val;
			}
			
		}
	</s:script>
</head>

<jsp:include page="/inc/includes.jsp" />

<body class="hold-transition skin-blue sidebar-mini" onbeforeunload="ShowWait('liquidacionParticularClienteForm');" 
		 onload="ocultarPaneles();if('${GestionarClientesBean.popup.mostrar}'=='true' ) {viewDialog.show();} else {window.scrollTo(0,${ScrollBean.hiddenScrollY});}">

<h:form id="mainMenu" style="display: none">
  <jsp:include page="/inc/navigation_test.jsp" />
  <x:commandLink id="cerrarSesion" action="#{LoginBean.salir}"/>
</h:form>

<jsp:include page="/inc/header.jsp" />

<!-- Content Header (Page header) -->
<section class="content-header">
  <h1>
    ${GestionarClientesBean.tituloCorto}
    <small>${GestionarClientesBean.tituloLargo}</small>
  </h1>
</section>

<section class="content">

<div class="box box-default">
<div class="box-header"><h3></h3></div>


	<script languaje="javascript">
		function ocultarPaneles() {
           
			
			document.getElementById('liquidacionParticularClienteForm:panelLiquidacionParticular').style.display = 'none';
			document.getElementById('liquidacionParticularClienteForm:archAdjuntosPre').style.display = 'none';
			document.getElementById('liquidacionParticularClienteForm:panelLiqPartConfirm').style.display = 'none';
	    }

		function mostrarLiqParticular() {
			document.getElementById('liquidacionParticularClienteForm:panelLiquidacionParticular').style.display = '';
			document.getElementById('liquidacionParticularClienteForm:archAdjuntosPre').style.display = '';
			document.getElementById('liquidacionParticularClienteForm:panelLiqPartConfirm').style.display = 'none';
	    }

		function mostrarLiqConfirm() {
			document.getElementById('liquidacionParticularClienteForm:panelLiquidacionParticular').style.display = 'none';
			document.getElementById('liquidacionParticularClienteForm:archAdjuntosPre').style.display = 'none';
			document.getElementById('liquidacionParticularClienteForm:panelLiqPartConfirm').style.display = '';
	    }

		
		function montoFilter(name){
			if (document.getElementById('liquidacionParticularClienteForm:montoMinimo').value!="" && document.getElementById('liquidacionParticularClienteForm:montoMaximo').value!="") {
				var num = parseFloat(document.getElementById('liquidacionParticularClienteForm:montoMinimo').value);
			    var num2 = parseFloat(document.getElementById('liquidacionParticularClienteForm:montoMaximo').value);
			    return parseFloat(name) >= num && parseFloat(name) <= num2; 
		    }
		    if (document.getElementById('liquidacionParticularClienteForm:montoMinimo').value!="") {
		    	var num = parseFloat(document.getElementById('liquidacionParticularClienteForm:montoMinimo').value);
		    	return (parseFloat(name) >= num);
			}
		    if (document.getElementById('liquidacionParticularClienteForm:montoMaximo').value!="") {
		    	var num2 = parseFloat(document.getElementById('liquidacionParticularClienteForm:montoMaximo').value);
		    	return (parseFloat(name) <=num2);
			}
            return true;
	    }
		
		
	</script>


<center>

	<h:form id="liquidacionParticularClienteForm" enctype="multipart/form-data">

	<%-- GRABA EL ESTADO DEL SCROLL(Dibujar solo si el popup no se muestra) --%>
	<h:panelGroup rendered="#{!GestionarClientesBean.popup.mostrar}">
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
			                   dialogTitle="#{GestionarClientesBean.tituloCorto}">
					    	<h:panelGrid columns="2" width="500">
						    	<x:graphicImage value="/img/#{GestionarClientesBean.popup.nombreIcono}" />
						        <h:outputText value=" #{GestionarClientesBean.popup.mensaje}" styleClass="texto"/>
					        	<f:verbatim>&nbsp;&nbsp;</f:verbatim>
					        	<h:panelGrid columns="2" width="300">
									<x:commandButton action="#{GestionarClientesBean.irASalir}" 
					        		 	onclick="clickLink('listoBloqueo');dojo.widget.byId('viewDialog').hide();window.close();"
					        		 	value="Aceptar" styleClass="btn btn-primary btn-flat" />
				        		</h:panelGrid>					        		 
							</h:panelGrid>
						</s:modalDialog>
						<x:commandLink id="listoBloqueo" action="#{GestionarClientesBean.irASalir}" forceId="true" style="display: block;"/>	 	
	                   	<h:panelGrid id="datosCliente" columns="2" width="700" align="center" style="border-bottom: #D9D7BB 1px solid;">
	                          <h:outputText id="cliente" value="#{GestionarClientesBean.cliente.individuo.apellido} , #{GestionarClientesBean.cliente.individuo.nombres}" styleClass="text-light-blue" style="FONT-SIZE: large;"></h:outputText>
                              <f:verbatim>&nbsp;</f:verbatim>
                              <h:outputText id="cuil" value="Cuil:  #{GestionarClientesBean.cliente.individuo.cuil}          IdCliente: #{GestionarClientesBean.cliente.idCliente} " styleClass="text-light-blue" style="FONT-SIZE: large;"></h:outputText>
                              <f:verbatim>&nbsp;</f:verbatim>
                              <h:commandButton id="idvolverListado" value="Volver al Listado" action="#{GestionarClientesBean.volverAlListado}" styleClass="btn btn-primary btn-flat"></h:commandButton> 
	                    	  <h:commandButton id="idBloqCuenta" value="#{GestionarClientesBean.valueBotonBloquearDesbloquearCuenta}" action="#{GestionarClientesBean.bloquearDesbloquearCuenta}" styleClass="btn btn-primary btn-flat"></h:commandButton>	  
	                    </h:panelGrid>
	                    <h:panelGrid id="principal" columns="2" width="700px;" align="left">
	                    	<h:panelGrid id="izquierdo" columns="1" width="150px;">   
	                            <f:verbatim>&nbsp;</f:verbatim>
	                            <f:verbatim>
                                   <c:choose>
											<c:when
												test="${lst:containsPage('liquidacionParticular','alta')}">
												<h:commandLink id="opcionDelCliente1"  onclick="mostrarLiqParticular(); return;" value="Liquidación Particular" onmouseup="return false;">
								                </h:commandLink>
											</c:when>
											<c:otherwise>
                                                <h:commandLink id="opcionDelCliente1"  onclick="return alert('No posee los permisos necesarios.');" value="Liquidación Particular" onmouseup="return false;">
								                </h:commandLink>
											</c:otherwise>
										</c:choose>
                                </f:verbatim>
	                             
                                <h:commandLink  id="opcionDelCliente4" onclick="mostrarLiqConfirm();return;" value="Liquidaciones Anteriores">
								</h:commandLink>
	                        </h:panelGrid> 
	                        <h:panelGrid id="derecho" columns="1" align="center" width="550px;" style="border-left: #D9D7BB 1px solid;">
                                  
                                  <h:panelGrid columns="1" id="archAdjuntosPre" align="center" style="display:hidden; height: 100px; overflow-x:hidden; overflow-y: auto;">
												   
									<c:if test="${!empty GestionarClientesBean.liquidacionesParticularesRealizadas}">   
									<h:dataTable value="#{GestionarClientesBean.liquidacionesParticularesRealizadas}"
										id="tabla" styleClass="standarDePartdTable" headerClass="dataTable_Header"
										footerClass="standardTable_Header" rowClasses="standardTable_Row1,standardTable_Row2"
										columnClasses="standardTable_Column,standardTable_ColumnCentered,standardTable_Column"
										var="var" style=" width :300px;">
										
										
										<h:column>
											<f:facet name="header">
												<h:outputText value="Liquidacion" id="cab" styleClass="texto" />
											</f:facet>
                                            <h:commandLink value="#{var.liqCliente.rutaPdf}" id="urlDeArchiv" action="#{var.abrirArchivo}">
											</h:commandLink>
											
										</h:column>
									  	<h:column>
											<f:facet name="header">
												<h:outputText value="Fecha Preliquidación" id="cab2" styleClass="texto" />
											</f:facet>
											<h:outputText id="fechaParaliq" value="#{var.liqCliente.liquidacionClientes.fechaPreliq}"/>
										</h:column>
										
										
										<h:column>
											<x:commandLink action="#{var.eliminarPreliquidacion}" id="eliminarPreliquidacionLink">
												
												<x:graphicImage value="/img/cat_act.gif" title="Eliminar una Liquidacion Particular." border="0" id="botonImagenTresTar" />
											</x:commandLink>
										</h:column>
                                        
										
									</h:dataTable>
                                 </c:if>
								 </h:panelGrid>
                                
                
                                
                                  <h:panelGrid id="panelLiquidacionParticular"  align="center" style="display:hidden; height: 100px; overflow-x:hidden; overflow-y: auto;" >
	                                   <c:if test="${empty GestionarClientesBean.liquidacionesParticularesRealizadas}">
                                          <c:if test="${!empty GestionarClientesBean.listaItemsPendientes}">
                                           <h:panelGrid columns="5">
                                                <h:outputText id="porValorMinimo" value="Montos Mayores a: "/>
                                                <h:inputText id="montoMinimo" style="width:60px;"></h:inputText>
                                                <h:outputText id="porValorMaximo" value="Montos Menores a: "/>
                                                <h:inputText id="montoMaximo" style="width:60px;"></h:inputText>
                                                <h:commandLink value="Buscar" onclick="dojo.widget.byId('liquidacionParticularClienteForm:filterTbl').setFilter('importe', montoFilter);return;" />
                                           </h:panelGrid>
										   
										   <h:panelGrid columns="2" width="500">
										   		<h:commandLink value="Listar todos" onclick="dojo.widget.byId('liquidacionParticularClienteForm:filterTbl').clearFilters(); return;" />
										        <h:panelGrid columns="2" width="150" align="right">
											        <h:outputText value="Marcar Todo" />
	                                                <h:selectBooleanCheckbox value="" onclick="marcar(this);"/>
                                                </h:panelGrid>
                                           </h:panelGrid>
                                            
										    <s:filterTable id="filterTbl" var="tar" value="#{GestionarClientesBean.listaItemsPendientes}"
                                                styleClass="table-bordered table-striped" 
                                                headClass="standardTable_Header">
										        <s:sortableColumn field="detalle" text="Detalle" align="left" >
										            <h:outputText value="#{tar.itemLiquidacion.detalleTransaccion}" styleClass="tdA"/>
										        </s:sortableColumn>
										        <s:sortableColumn field="fecha" text="Fecha" align="left">
										            <h:outputText value="#{tar.itemLiquidacion.fecha}" styleClass="tdA"/>
										        </s:sortableColumn>
										        <s:sortableColumn field="cuota" text="Cuota" align="right">
										            <h:outputText value="#{tar.itemLiquidacion.cuota} / #{tar.itemLiquidacion.cantCuotas}" styleClass="tdB"/>
										        </s:sortableColumn>
                                                <s:sortableColumn field="importe" dataType="Number" align="right" text="Importe">
										            <h:outputText value="#{tar.itemLiquidacion.importe}" />
										        </s:sortableColumn>
                                                <s:sortableColumn field="seleccion" text="Sel." align="center">
										            <h:selectBooleanCheckbox value="#{tar.seleccionado}" id="seleccionado" />
										        </s:sortableColumn> 
										    </s:filterTable>
                                            <h:panelGrid columns="1" width="500" align="right">
                                                  <h:commandButton id="btnLiquidar" value="Realizar Liquidación" action="#{GestionarClientesBean.liquidarItems}" styleClass="btn btn-primary btn-flat"></h:commandButton>
                                            </h:panelGrid>
										</c:if>
				                        <c:if test="${empty GestionarClientesBean.listaItemsPendientes}">
										<h:outputText id="ult" value="No existen movimientos que el cliente pueda liquidar por adelantado." styleClass="text-light-blue" />
										</c:if>
 									</c:if>
                                   </h:panelGrid>

	                              <h:panelGrid id="panelLiqPartConfirm"  align="center" style="display:hidden;">
	                                   <c:if test="${!empty GestionarClientesBean.liquidacionesParticularesConfirmadas}">   
											<h:dataTable value="#{GestionarClientesBean.liquidacionesParticularesConfirmadas}"
												id="tablaDeConfirm" styleClass="table-bordered table-striped" headerClass="dataTable_Header"
												footerClass="standardTable_Header" rowClasses="standardTable_Row1,standardTable_Row2"
												columnClasses="tdA,tdA,tdA"
												var="var" style=" width :300px;">
												
												
												<h:column>
													<f:facet name="header">
														<h:outputText value="Liquidacion" id="cabco" styleClass="texto" />
													</f:facet>
		                                            <h:commandLink value="#{var.liqCliente.rutaPdf}" id="urlDeArchivco" action="#{var.abrirArchivo}">
													</h:commandLink>
													
												</h:column>
											  	<h:column>
													<f:facet name="header">
														<h:outputText value="Fecha Liquidación" id="cab2co" styleClass="texto" />
													</f:facet>
													<h:outputText id="fechaParaliqco" value="#{var.liqCliente.fechaCierre}"/>
												</h:column>
												
												
												
												
											</h:dataTable>
		                                 </c:if>
 										<c:if test="${empty GestionarClientesBean.liquidacionesParticularesConfirmadas}">
										<h:outputText id="ultCli" value="No existen liquidaciones previas para este cliente." styleClass="text-light-blue" />
										</c:if>
                                  </h:panelGrid>
	                    
	                        </h:panelGrid>
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
    document.getElementById("sideMenu").innerHTML += generateMenu(obj,"mainMenu__idJsp2_menu:A]\#{ClienteBean.irAListarCliente}") + `</li>`;
}

</script>

<%@include file="/inc/scripts.jsp" %>


</body>
</html>
</f:view>
