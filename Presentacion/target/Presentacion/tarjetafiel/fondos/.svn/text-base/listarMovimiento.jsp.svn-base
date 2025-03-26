<%@include file="/inc/tags.jsp" %>
<jsp:useBean id="ahora" class="java.util.Date" scope="request"/>

<f:view>
<html lang="es">
<head>
	<title><h:outputText value="Tarjeta Fiel - Listado de Movimientos"/></title>
	<s:script language="javascript">
    	function recargar() {
    		document.getElementById('altaComprobantesOPForm').submit();
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
<jsp:include page="/inc/includes.jsp" />
<body class="hold-transition skin-blue sidebar-mini" onbeforeunload="ShowWait('listadoMovimiento');" onload="${MovimientoBean.popupReport}">
	<h:form id="mainMenu" style="display: none">
  <jsp:include page="/inc/navigation_test.jsp" />
  <x:commandLink id="cerrarSesion" action="#{LoginBean.salir}"/>
</h:form>

<jsp:include page="/inc/header.jsp" />

<!-- Content Header (Page header) -->
<section class="content-header">
  <h1>
    ${MovimientoBean.tituloCorto}
    <small>${MovimientoBean.tituloLargo}</small>
  </h1>
</section>

<section class="content">

<div class="box box-default">
<div class="box-header"><h3></h3></div>

<center>
	<secure:check/>

	<h:form id="listadoMovimiento">
		<x:panelLayout id="page" layout="#{globalOptions.pageLayout}"
			styleClass="pageLayout"
			headerClass="pageHeader"
			navigationClass="pageNavigation"
			bodyClass="pageBody"
			footerClass="pageFooter" >


			<f:facet name="body">
				<h:panelGroup id="body">
				
					<h:panelGrid columns="1" align="center">
						<%-- INCLUDE PARA LOS ERRORES --%>
						<h:panelGroup id="errores">
							<jsp:include page="/inc/error.jsp" />
						</h:panelGroup>
						
						<h:panelGrid columns="1" id="uno" width="850" align="center">
						
						<s:layoutingTitlePane id="filtroMovimiento" label=" Filtro de Movimientos" containerNodeClass="contentTitlePane" labelNodeClass="labelTitlePane" >
							<h:panelGrid id="filtroUno" columns="5" width="850" align="center">
								<h:panelGroup>
									<h:outputText value="Id Movimiento:" styleClass="texto"/>
									<h:inputText id="idMovimFiltro" value="#{MovimientoBean.idMovimiento}"
										styleClass="bordeceldainferior" maxlength="10" size="10"
										style="width: 100px; margin-left:10px" onfocus="encender(this);" onblur="apagar(this);"
										onkeypress="return soloEnteros(this,event);"/>
								</h:panelGroup>

								<f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim>
								
								<h:panelGroup>
									<h:outputText value="Desde:" styleClass="texto"/>
							 		<f:verbatim>
						            <div class="input-group date">
						                <div class="input-group-addon">
						                    <i class="fa fa-calendar"></i>
						                </div>
						                <input type="text" class="form-control pull-right" id="fDesde">
						            </div>
									</f:verbatim>
								</h:panelGroup>

					            <f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim>
								
								<h:panelGroup>
									<h:outputText value="Hasta:" styleClass="texto"/>
							 		<f:verbatim>
							        <div class="input-group date">
							            <div class="input-group-addon">
							                <i class="fa fa-calendar"></i>
							            </div>
							            <input type="text" class="form-control pull-right" id="fHasta">
							        </div>
									</f:verbatim>
								</h:panelGroup>
								
								<f:verbatim>&nbsp;</f:verbatim>
								<f:verbatim>&nbsp;</f:verbatim>
								<f:verbatim>&nbsp;</f:verbatim>
								<f:verbatim>&nbsp;</f:verbatim>
								<f:verbatim>&nbsp;</f:verbatim>
								
								<h:panelGroup>
									<h:outputText value="Concepto:" styleClass="texto"/>
									<h:selectOneMenu id="lstConcepto" value="#{MovimientoBean.idConceptoSeleccionada}"
										styleClass="lista" immediate="true" onfocus="encender(this);"
										onblur="apagar(this);" style="width: 200px; margin-left:10px">
										<f:selectItems value="#{MovimientoBean.conceptoItems}"/>
									</h:selectOneMenu>
								</h:panelGroup>
								
								<f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim>
								
								<h:panelGroup>
									<h:outputText value="Caja:" styleClass="texto"/>
									<h:selectOneMenu id="lstCaja" value="#{MovimientoBean.idCajaSeleccionada}"
										styleClass="lista" immediate="true" onfocus="encender(this);"
										onblur="apagar(this);" style="width: 200px; margin-left:10px">
										<f:selectItems value="#{MovimientoBean.cajasItems}"/>
									</h:selectOneMenu>
								</h:panelGroup>
								
								<f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim>
								
								<h:panelGroup>
									<h:outputText value="Codigo Externo:" styleClass="texto"/>
									<h:inputText id="idNroExterno" value="#{MovimientoBean.codigoExterno}"
										styleClass="bordeceldainferior" maxlength="40" size="40"
										style="width: 100px; margin-left:10px" onfocus="encender(this);" onblur="apagar(this);"
										onkeypress="return soloEnteros(this,event);"/>
								</h:panelGroup>
								
							</h:panelGrid>
							
							</s:layoutingTitlePane>
							
							<f:verbatim><hr align="center" width="900"></f:verbatim>
								
							<x:commandButton id="btnBuscar" value="Buscar" onclick="agregarCaja.show();"
								action="#{MovimientoBean.listarMovimiento}" title="Busca los Movimientos seleccionada" 
								styleClass="btn btn-primary btn-flat pull-right"/>
							

						</h:panelGrid>
						
						<h:panelGrid columns="1" align="center" id="paelSecundario" width="850">
						<c:if test="${!empty MovimientoBean.movimientoList}">
							<h:panelGroup>
								<h:outputText value="Impresora:" styleClass="texto"/>
								<h:selectOneMenu id="lstImpresoras" value="#{MovimientoBean.idImpresoraSeleccionada}"
									styleClass="lista" immediate="true" onfocus="encender(this);"
									onblur="apagar(this);" style="width: 200px">
									<f:selectItems value="#{MovimientoBean.impresorasItem}"/>
								</h:selectOneMenu>
							</h:panelGroup>
							<f:verbatim><br></f:verbatim>
							<f:verbatim>
								<display:table id="listaMovimiento" name="sessionScope.MovimientoBean.movimientoList"
									defaultsort="1"
									class="table-bordered table-striped"
									requestURI="/tarjetafiel/fondos/listarMovimiento.jsf"
									export="${lst:contains(requestScope.permisos,'exportacion')}" 
									requestURIcontext="true" style="width: 900px;">

										<display:column property="idMovimiento" title="Id Movimiento" sortable="true" class="tdB"/>
										<display:column property="fechaAsientoFormat" title="Fecha" sortable="true" class="tdB"/>
										<display:column property="concepto.descripcion" title="Concepto" sortable="true" class="tdA"/>
										<display:column property="importe" title="Importe" sortable="true" class="tdB"/>
										<display:column property="caja.descripcion" title="Caja" sortable="true" class="tdA"/>
										<display:column property="codigoExterno" title="Cod. Externo" sortable="true" class="tdA"/>
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
										<display:setProperty name="paging.banner.item_name" value="Movimiento" />
										<display:setProperty name="paging.banner.items_name" value="Movimientos" />
										
								</display:table>
							</f:verbatim>

							<%-- Links oculto para imprimir un movimiento --%>
							<x:commandLink action="#{MovimientoBean.verDetalleMov}" id="verDetalleLink" style="display: none;"/>
							<x:commandLink action="#{MovimientoBean.imprimirTicket}" id="imprimirTicketLink" style="display: none;"/>
							<x:commandLink action="#{MovimientoBean.imprimirMovimientoListar}" id="imprimirLink" style="display: none;"/>

							<x:inputHidden id="idMovimientoHidden" forceId="true" value="#{MovimientoBean.idMovimientoHidden}"/>

	                        <h:panelGrid id="listaMovimientos" width="200" columns="7" align="center">
	                        	<h:commandLink id="botonPrimeraPaginaLote" action="#{MovimientoBean.pagDeMov.primeraPagina}" 
	                        		rendered="#{MovimientoBean.pagDeMov.hayAnterior}" styleClass="botones">
	                        		<x:graphicImage value="/img/icon/skipb_16.gif" border="0" 
	                        			onclick="submit();" />
	                        	</h:commandLink>
	                        	<h:commandLink id="botonPaginaAnteriorLote" action="#{MovimientoBean.pagDeMov.paginaAnterior}" 
	                        		rendered="#{MovimientoBean.pagDeMov.hayAnterior}" styleClass="botones">
	                        		<x:graphicImage value="/img/icon/rewnd_16.gif" border="0" 
	                        			onclick="submit();" />
	                        	</h:commandLink>
	                        	<h:outputText value="Página " />
	                        	<h:selectOneMenu  id="lstDePaginas" value="#{MovimientoBean.pagDeMov.idPaginaSeleccionada}" binding="#{MovimientoBean.pagDeMov.pagSeleccionada}"
     					  						styleClass="lista" onfocus="encender(this);" immediate="true" valueChangeListener="#{MovimientoBean.pagDeMov.cargarPagina}"
     					 						 onblur="apagar(this);" style=" width : 50px;" onchange="submit();">
     					 						 <f:selectItems value="#{MovimientoBean.pagDeMov.comboDePaginas}" id="selectEjerDeSucum" />
								</h:selectOneMenu>	
								<h:outputText value="#{MovimientoBean.pagDeMov.estado}" />
	                        	<h:commandLink id="botonPaginaSiguienteLote" action="#{MovimientoBean.pagDeMov.paginaSiguiente}" 
	                        		rendered="#{MovimientoBean.pagDeMov.haySiguiente}" styleClass="botones">
	                        		<x:graphicImage value="/img/icon/fastf_16.gif" border="0" 
	                        			onclick="submit();" />
	                        	</h:commandLink>
	                        	<h:commandLink id="botonUltimaPaginaLote" action="#{MovimientoBean.pagDeMov.ultimaPagina}" 
	                        		rendered="#{MovimientoBean.pagDeMov.haySiguiente}" styleClass="botones">
	                        		<x:graphicImage value="/img/icon/skipf_16.gif" border="0" 
	                        			onclick="submit();"/>
	                        	</h:commandLink>
	                        </h:panelGrid>
						<c:if test="${!empty MovimientoBean.detalleMovList}">
							<h:outputText value="Detalles del Asiento" style="FONT-SIZE: large;" styleClass="textoblue" />
							<h:panelGrid columns="3" style=" width : 800px;">
								 <h:outputText value="Numero" styleClass="texto" style="COLOR: green;"/>
								 <h:outputText value="Concepto" styleClass="texto" style="COLOR: green;"/>
								 <h:outputText value="Fecha" styleClass="texto" style="COLOR: green;"/>
								 <h:outputText value="#{MovimientoBean.asiento.idAsiento}" styleClass="texto" />
								 <h:outputText value="#{MovimientoBean.asiento.concepto}" styleClass="texto" />
								 <h:outputText value="#{MovimientoBean.asiento.fecha}" styleClass="texto" />
							</h:panelGrid>
							<x:dataTable value="#{MovimientoBean.detalleAsientoList}" id="tablaDetalleAsientos"
											 styleClass="standardTable"
                    		       			 headerClass="standardTable_Header"
                           					 footerClass="standardTable_Header"
		                           			 rowClasses="standardTable_Row1,standardTable_Row2"
		                           			 sortable="true"
        		                   			 columnClasses="standardTable_Column,standardTable_Column,standardTable_Column,
        		                   			 				standardTable_ColumnRight,standardTable_ColumnRight"							             
								             var="item" style=" width : 800px;">
			                        <x:column>
	    		                        <f:facet name="header">
	            		                    <h:outputText value="Reng" styleClass="texto" />
	                    		        </f:facet>
		                                <h:outputText value="#{item.asientoItem.nroRenglon}" styleClass="texto" />
	    		                    </x:column>
	    		                    <x:column>
	    		                        <f:facet name="header">
	            		                    <h:outputText value="Cuenta" styleClass="texto" />
	                    		        </f:facet>
		                                <h:outputText value="#{item.cuenta}" styleClass="texto" />
	    		                    </x:column>
			                        <x:column>
	    		                        <f:facet name="header">
	            		                    <h:outputText value="Leyenda" styleClass="texto" />
	                    		        </f:facet>
		                                <h:outputText value="#{item.asientoItem.leyenda}" styleClass="texto" />
	    		                    </x:column>
	            		    	    <x:column>
	                    		        <f:facet name="header">
			                                <h:outputText value="Debe" styleClass="texto" />
	    		                        </f:facet>
                		                <h:outputText value="#{item.debe}" styleClass="texto" />
	                    		    </x:column>
									<x:column>
	                    		        <f:facet name="header">
			                                <h:outputText value="Haber" styleClass="texto" />
	    		                        </f:facet>
                		                <h:outputText value="#{item.haber}" styleClass="texto" />
	                    		    </x:column>  
							</x:dataTable>
							<h:outputText value="Medios de Pago" style="FONT-SIZE: large;" styleClass="textoblue" />
							<x:dataTable value="#{MovimientoBean.detalleMovList}" id="tablaDetalleMovimientos"
											 styleClass="standardTable"
                    		       			 headerClass="standardTable_Header"
                           					 footerClass="standardTable_Header"
		                           			 rowClasses="standardTable_Row1,standardTable_Row2"
		                           			 sortable="true"
        		                   			 columnClasses="standardTable_Column,standardTable_ColumnRight"							             
								             var="movimMP" style=" width : 800px;">
			                        <x:column>
	    		                        <f:facet name="header">
	            		                    <h:outputText value="Medio" styleClass="texto" />
	                    		        </f:facet>
		                                <h:outputText value="#{movimMP.formaPago.descripcion}" styleClass="texto" />
	    		                    </x:column>
			                        <x:column>
	    		                        <f:facet name="header">
	            		                    <h:outputText value="Monto" styleClass="texto" />
	                    		        </f:facet>
		                                <h:outputText value="#{movimMP.monto}" styleClass="texto" />
	    		                    </x:column>
	            		    <%--    <x:column>
	                    		        <f:facet name="header">
			                                <h:outputText value="Comentario" styleClass="texto" />
	    		                        </f:facet>
                		                <h:outputText value="#{movimMP.detalleTramite.comentario}" styleClass="texto" />
	                    		    </x:column>  
									<x:column>
										<x:commandLink id="tomarTareaLink" action="#{EscritorioBean.tomarTarea}">
											<f:param id="idDetalle" name="idDetalle" value="#{movimMP.detalleTramite.idDetalleTramite}"/>
											<x:graphicImage value="/img/icon/tomar.gif" title="Iniciar la tarea." border="0"/>
										</x:commandLink>
									</x:column>--%>
							</x:dataTable>
						</c:if>
                       </c:if>
                   	  </h:panelGrid>
				</h:panelGrid>

				</h:panelGroup>
			</f:facet>

		</x:panelLayout>

		<h:inputText id="FechaDesde" value="#{MovimientoBean.fechaDesde}" style="display: none;">
        	<f:convertDateTime type="both" pattern = "dd/MM/yyyy HH:mm"/>
   		</h:inputText>
    	<h:inputText id="FechaHasta" value="#{MovimientoBean.fechaHasta}" style="display: none;">
        	<f:convertDateTime type="both" pattern = "dd/MM/yyyy HH:mm"/>
    	</h:inputText>

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
    document.getElementById("sideMenu").innerHTML += generateMenu(obj,"mainMenu__idJsp2_menu:A]\#{MovimientoBean.irAListarMovimiento}") + `</li>`;
}

</script>

<%@include file="/inc/scripts.jsp" %>

<script type="text/javascript">
$(function () {

    //Inicializo datepicker
    $("#fDesde").datepicker({
      autoclose: true,
      orientation: "bottom"
    });

    $("#fHasta").datepicker({
      autoclose: true,
      orientation: "bottom"
    });

    
	//Seteo fehcas que trae el bean por defecto, en el datepicker
	var fd = document.getElementById("listadoMovimiento:FechaDesde").value.split(" ")[0].split("/");
	var year = fd[2];
	var month = fd[1];
	month--;
	var date = fd[0];
	var fAux = new Date(year,month,date);
	$("#fDesde").datepicker("setDate", fAux);
    document.getElementById("listadoMovimiento:FechaDesde").value = $.datepicker.formatDate('dd/mm/yy', fAux) + " 03:00";
	
	fd = document.getElementById("listadoMovimiento:FechaHasta").value.split(" ")[0].split("/");
	year = fd[2];
	month = fd[1];
	month--;
	date = fd[0];
	fAux = new Date(year,month,date);
	$("#fHasta").datepicker("setDate", fAux);
	document.getElementById("listadoMovimiento:FechaHasta").value = $.datepicker.formatDate('dd/mm/yy', fAux) + " 03:00";
	
	

    

    //Muevo fechas de datepicker a input q pasa datos al bean
    $("#fDesde").change(function() {
        var fd = $(this).datepicker("getDate");
        document.getElementById("listadoMovimiento:FechaDesde").value = $.datepicker.formatDate('dd/mm/yy', fd) + " 03:00";
    });

    $("#fHasta").change(function() {
        var fh = $(this).datepicker("getDate");
        document.getElementById("listadoMovimiento:FechaHasta").value = $.datepicker.formatDate('dd/mm/yy', fh) + " 03:00";
    });


  });
</script>

</body>
</html>
</f:view>
