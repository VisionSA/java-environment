<%@include file="/inc/tags.jsp"%>
<jsp:useBean id="ahora" class="java.util.Date" scope="request" />

<f:view>
<html lang="es">
	<head>
	<title><h:outputText value="#{ConciliacionBancariaBean.tituloLargo}" /></title>
	<s:script language="javascript">
		function recargar() {
			document.getElementById('conciliacionForm').submit();
		}
		function popup(pagina,popW,popH) {
			var w = 0, h = 0;
		   	w = screen.width;
		   	h = screen.height;

			var leftPos = (w-popW)/2, topPos = (h-popH)/2;
		    popupWindow=open(pagina,'','resizable=no,autohide=no,scrollbars=ybuses,width='+popW+',height='+popH+',top='+topPos+',left='+leftPos);
		    
		    if (popupWindow.opener == null){popupWindow.opener = self;}
		    
		};
		
		
		var muestro = false;
		var bool = true;


		function mostrarBarra() {
		    document.getElementById('conciliacionForm:barrita').style.display = '';
			return null;
		}


		function ocultarBarra() {
		    document.getElementById('conciliacionForm:barrita').style.display = 'none';
		}

	</s:script>
	</head>
	<jsp:include page="/inc/includes.jsp" />

	<body  class="hold-transition skin-blue sidebar-mini" onbeforeunload="ShowWait('conciliacionForm');  " onload="ocultarBarra();">
	<script language="javascript">
	        function marcar() {
            	if (document.getElementById('conciliacionForm:tablaNoConciliados:boolTodos').checked) {
                for (i=0; document.getElementById('conciliacionForm:tablaNoConciliados:' + i + ':estado')!=null;i++) {
                     document.getElementById('conciliacionForm:tablaNoConciliados:' + i + ':estado').checked = true;
                }
            } else {
            	for (i=0; document.getElementById('conciliacionForm:tablaNoConciliados:' + i + ':estado')!=null;i++) {
                     document.getElementById('conciliacionForm:tablaNoConciliados:' + i + ':estado').checked = false;
                }
              }
             } 
    </script>
    <h:form id="mainMenu" style="display: none">
  <jsp:include page="/inc/navigation_test.jsp" />
  <x:commandLink id="cerrarSesion" action="#{LoginBean.salir}"/>
</h:form>

<jsp:include page="/inc/header.jsp" />

<!-- Content Header (Page header) -->
<section class="content-header">
  <h1>
    ${ConciliacionBancariaBean.tituloCorto}
    <small>${ConciliacionBancariaBean.tituloLargo}</small>
  </h1>
</section>

<section class="content">

<div class="box box-default">
<div class="box-header"><h3></h3></div>
     
	<center>
	 
	
	 <h:form id="conciliacionForm"	enctype="multipart/form-data">



		<%-- GRABA EL ESTADO DEL SCROLL(Dibujar solo si el popup no se muestra) --%>
		<h:panelGroup rendered="#{!ConciliacionBancariaBean.popup.mostrar}">
			<%@include file="/inc/scroll.inc"%>
		</h:panelGroup>

		<x:panelLayout id="page" layout="#{globalOptions.pageLayout}"
			styleClass="pageLayout" headerClass="pageHeader"
			navigationClass="pageNavigation" bodyClass="pageBody"
			footerClass="pageFooter">

			
			<f:facet name="body">
				<h:panelGroup id="body">
					<h:panelGrid columns="1" align="center" id="PanelPricipal">
						<%-- INCLUDE PARA LOS ERRORES --%>
						<h:panelGroup id="errores">
							<jsp:include page="/inc/error.jsp" />
						</h:panelGroup>

						<%-- POPUP PARA LOS MENSAJES DE EXITO Y FRACASO --%>
						<s:modalDialog dialogId="viewDialog" dialogVar="viewDialog"
							styleClass="viewDialog" dialogTitle="Conciliaciones">
							<h:panelGrid columns="2" width="500">
								<x:graphicImage value="/img/#{ConciliacionBancariaBean.popup.nombreIcono}" />
								<h:outputText value="#{ConciliacionBancariaBean.popup.mensaje}"
									styleClass="texto" />
							</h:panelGrid>
							<h:panelGrid columns="1" width="500">
								<x:commandButton
									onclick="clickLink('aceptarOK');dojo.widget.byId('viewDialog').hide();"
									value="Listar" styleClass="btn btn-primary btn-flat pull-right" title="Aceptar." />
							</h:panelGrid>
						</s:modalDialog>
						<x:commandLink id="aceptarOK" forceId="true"
							style="display: block;" />
                       
						

						<s:fieldset legend="Acreditaciones Bancarias" id="fieldArchivoPostnet">
							<h:panelGroup id="importacion">
								<h:outputText value="#{ConciliacionBancariaBean.titulo}"
									style="FONT-SIZE: large;" styleClass="texto" />
								<h:outputText value="#{ConciliacionBancariaBean.periodo}"
									style="FONT-SIZE: small;" styleClass="texto" />	
								<h:panelGrid columns="4" id="panelInternoOnce" width="650"
									align="center">
									<h:outputText value="Archivo: " id="outArch" styleClass="texto" />
									<x:inputFileUpload id="fileUpLoad" storage="file"
										styleClass="fileUploadInput" 
										maxlength="1000" required="true"
										value="#{ConciliacionBancariaBean.uploadedFile}">
							
							         </x:inputFileUpload>

									<h:commandButton value="Cancelar" onclick="window.close();"
										styleClass="btn btn-primary btn-flat pull-right" id="btonDesabilitarTDocLink" />
									<h:commandButton value="Ejecutar"
										action="#{ConciliacionBancariaBean.ejecutar2}"
										styleClass="btn btn-primary btn-flat pull-right" id="btonAdjuntarTDocLink"
										onclick="mostrarBarra();" />
									
									<h:panelGrid id="barrita" align="center" style="display:hidden">
										<f:verbatim>
											<script type="text/javascript">
		 				               var bar=createBar(150,15,"white",1,"black","#333366",85,7,3,"");
					                </script>
										</f:verbatim>
									</h:panelGrid>
								
									
								</h:panelGrid>

							</h:panelGroup>

						</s:fieldset>

						


					 <s:fieldset legend="#{ConciliacionBancariaBean.tituloNoConciliados}" id="fieldResConciliacion"
						rendered="#{ConciliacionBancariaBean.mostrarResultConc}">
			
							<h:panelGroup id="grup">
								<h:panelGrid columns="4" width="650" align="center"	id="panelEncabezadoConc" rendered="#{ConciliacionBancariaBean.mostrarEncabezado}">
                                  
							      <f:verbatim> <br id="br"> </f:verbatim>
				                  <h:outputText value="Total Acreditaciones No Encontradas :" styleClass="texto" />
                                  <f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim>
                                  <h:outputText value="#{ConciliacionBancariaBean.conciliacionBancaria.noConciliadas}" style="color:blue" styleClass="texto" />
                                  
                                  <f:verbatim> <br id="br"> </f:verbatim>
								  <h:outputText value="Total Acreditaciones Encontradas :" styleClass="texto" />
                                  <f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim>
                                  <h:outputText value="#{ConciliacionBancariaBean.conciliacionBancaria.conciliados}" style="color:blue" styleClass="texto" />
                                  
                                   <f:verbatim> <br id="br"> </f:verbatim>
				                   <f:verbatim><hr id="linea" align="center" width="600"></f:verbatim> 
                                   <f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim>
								   <f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim>
								  
								  <f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim>
				                  <h:outputText value="Total Acreditaciones Procesadas: :" styleClass="texto" />
                                  <f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim>
                                  <h:outputText value="#{ConciliacionBancariaBean.conciliacionBancaria.noConciliadas + ConciliacionBancariaBean.conciliacionBancaria.conciliados}" style="color:blue" styleClass="texto" />
								  <f:verbatim> <br id="br"> </f:verbatim>
								  <f:verbatim> <br id="br"> </f:verbatim>
                                </h:panelGrid>
                                 
					<x:div style="OVERFLOW-Y: auto; OVERFLOW-X: auto; WIDTH: 850px; HEIGHT: 500px; border: 1px; margin-left: auto; margin-right: auto;"> 
						<h:panelGrid columns="1" width="650" align="center"	id="panelListNoConc" rendered="#{ConciliacionBancariaBean.mostrarTablaNoConc}">
							<c:if test="${empty ConciliacionBancariaBean.listCuponesNoConc}">
								<h:outputText value="Se encontraron todas las acreditaciones." id="outMsg"
									styleClass="texto" style="color:green" />
							</c:if>
							<c:if test="${!empty ConciliacionBancariaBean.listCuponesNoConc}">
								<h:outputText value="Acreditaciones No Econtradas" style="FONT-SIZE: medium;" styleClass="texto" />
								<f:verbatim> <br id="br"> </f:verbatim>
								<f:verbatim>
								<display:table id="listaMovimiento" name="sessionScope.ConciliacionBancariaBean.listCuponesNoConc"
									defaultsort="1"
									class="table-bordered table-striped"
									requestURI="/tarjetafiel/fondos/conciliacionBancaria.jsf"
									requestURIcontext="true" style="width: 900px;">

										<display:column property="nroTransaccion" title="Nro. Transaccion" sortable="true" class="tdB"/>
										<display:column property="fechaSolicitud" title="Fecha Solicitud" sortable="true" class="tdB"/>
										<%-- 
										<display:column property="tipoTrasferencia" title="Tipo Trasferencia" sortable="true" class="tdA"/>
										--%>
										<display:column property="nombreBeneficiario" title="Beneficiario" sortable="true" class="tdB"/>
										<display:column property="importeTrasferencia" title="Importe Trasferencia" sortable="true" class="tdA"/>
										<display:column property="idCheque" title="Cod. Interno Cheque" sortable="true" class="tdA"/>
										<display:column property="conciliado" title="Conciliado" sortable="false" class="tdA"/>
										<%-- 		
										<display:column style="width: 25px;" media="html">
											<a href="javascript:viewUser('${listaMovimiento.idMovimiento}','idMovimientoHidden');javascript:clickLink('listadoMovimiento:imprimirTicketLink')">
												<img src='<%=request.getContextPath()%>/img/icon/print_16.gif' title='Reimprimir Ticket' border='0'
													onclick="return confirm('Se va a imprimir el ticket.\n ¿Desea continuar?');" />
											</a>
										</display:column>
										<display:column style="width: 25px;" media="html">
											<a href="javascript:viewUser('${listaMovimiento.idMovimiento}','idMovimientoHidden');javascript:clickLink('listadoMovimiento:verDetalleLink')">
												<img src='<%=request.getContextPath()%>/img/icon/editar.gif' title='Ver detalles del movimiento' border='0' />
											</a>
										</display:column>
										<display:column style="width: 25px;" media="html">
											<a href="javascript:viewUser('${listaMovimiento.idMovimiento}','idMovimientoHidden');javascript:clickLink('listadoMovimiento:imprimirLink')">
												<img src='<%=request.getContextPath()%>/img/icon/print_16.gif' title='Imprimir detalles del movimiento' border='0' />
											</a>
										</display:column>
										--%>
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
										<display:setProperty name="paging.banner.item_name" value="Acreditacion" />
										<display:setProperty name="paging.banner.items_name" value="Acreditaciones" />
										
								</display:table>
							</f:verbatim>
							<%-- Links oculto para imprimir un movimiento 
							<x:commandLink action="#{MovimientoBean.verDetalleMov}" id="verDetalleLink" style="display: none;"/>
							<x:commandLink action="#{MovimientoBean.imprimirTicket}" id="imprimirTicketLink" style="display: none;"/>
							<x:commandLink action="#{MovimientoBean.imprimirMovimientoListar}" id="imprimirLink" style="display: none;"/>

							<x:inputHidden id="idMovimientoHidden" forceId="true" value="#{MovimientoBean.idMovimientoHidden}"/>
							--%>

	                        <h:panelGrid id="listaMovimientos" width="200" columns="7" align="center">
	                        	<h:commandLink id="botonPrimeraPaginaLote" action="#{ConciliacionBancariaBean.pagDeMov.primeraPagina}" 
	                        		rendered="#{ConciliacionBancariaBean.pagDeMov.hayAnterior}" styleClass="botones">
	                        		<x:graphicImage value="/img/icon/skipb_16.gif" border="0" 
	                        			onclick="submit();" />
	                        	</h:commandLink>
	                        	<h:commandLink id="botonPaginaAnteriorLote" action="#{ConciliacionBancariaBean.pagDeMov.paginaAnterior}" 
	                        		rendered="#{ConciliacionBancariaBean.pagDeMov.hayAnterior}" styleClass="botones">
	                        		<x:graphicImage value="/img/icon/rewnd_16.gif" border="0" 
	                        			onclick="submit();" />
	                        	</h:commandLink>
	                        	<h:outputText value="Página " />
	                        	<h:selectOneMenu  id="lstDePaginas" value="#{ConciliacionBancariaBean.pagDeMov.idPaginaSeleccionada}" binding="#{ConciliacionBancariaBean.pagDeMov.pagSeleccionada}"
     					  						styleClass="lista" onfocus="encender(this);" immediate="true" valueChangeListener="#{ConciliacionBancariaBean.pagDeMov.cargarPagina}"
     					 						 onblur="apagar(this);" style=" width : 50px;" onchange="submit();">
     					 						 <f:selectItems value="#{ConciliacionBancariaBean.pagDeMov.comboDePaginas}" id="selectEjerDeSucum" />
								</h:selectOneMenu>	
								<h:outputText value="#{ConciliacionBancariaBean.pagDeMov.estado}" />
	                        	<h:commandLink id="botonPaginaSiguienteLote" action="#{ConciliacionBancariaBean.pagDeMov.paginaSiguiente}" 
	                        		rendered="#{MovimientoBean.pagDeMov.haySiguiente}" styleClass="botones">
	                        		<x:graphicImage value="/img/icon/fastf_16.gif" border="0" 
	                        			onclick="submit();" />
	                        	</h:commandLink>
	                        	<h:commandLink id="botonUltimaPaginaLote" action="#{ConciliacionBancariaBean.pagDeMov.ultimaPagina}" 
	                        		rendered="#{ConciliacionBancariaBean.pagDeMov.haySiguiente}" styleClass="botones">
	                        		<x:graphicImage value="/img/icon/skipf_16.gif" border="0" 
	                        			onclick="submit();"/>
	                        	</h:commandLink>
	                        </h:panelGrid>

							</c:if>
                                  
                          	</h:panelGrid>
						</x:div> 

						</h:panelGroup>
						</s:fieldset>
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
    document.getElementById("sideMenu").innerHTML += generateMenu(obj,"mainMenu__idJsp2_menu:A]\#{ConciliacionBancariaBean.inicializar}") + `</li>`;
}
</script>

<%@include file="/inc/scripts.jsp" %>

	</body>
	</html>
</f:view>
