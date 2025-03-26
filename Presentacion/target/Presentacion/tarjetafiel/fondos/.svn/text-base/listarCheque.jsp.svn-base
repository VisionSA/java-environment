<%@include file="/inc/tags.jsp" %>
<jsp:useBean id="ahora" class="java.util.Date" scope="request"/>

<f:view>
<html lang="es">
<head>
	<title><h:outputText value="Tarjeta Fiel - Listado de Cheques"/></title>
</head>
<jsp:include page="/inc/includes.jsp" />

<body class="hold-transition skin-blue sidebar-mini" onbeforeunload="ShowWait('listadoCheque');" onload="${ChequeBean.popupReport}; verFiltros();">

	<script languaje="javascript">

		function popup(pagina,popW,popH) {
			var w = 0, h = 0;
		   	w = screen.width;
		   	h = screen.height;

			var leftPos = (w-popW)/2, topPos = (h-popH)/2;
		    popupWindow=open(pagina,'','resizable=no,scrollbars=yes,width='+popW+',height='+popH+',top='+topPos+',left='+leftPos);
		    if (popupWindow.opener == null){popupWindow.opener = self;}
		};
		
		function cambiarFiltros(InputText) {
			if (InputText.value == "1") {
				document.getElementById('busquedaHidden').value = '';
				document.getElementById('composicionHidden').value = 'none';
			} else {
				document.getElementById('busquedaHidden').value = 'none';
				document.getElementById('composicionHidden').value = '';
			}
			verFiltros();
		}
		
		function verFiltros() {
			var busqueda = document.getElementById('busquedaHidden').value;
			var composicion = document.getElementById('composicionHidden').value;
			
			document.getElementById('listadoCheque:outNroCheque').style.display = busqueda;
			document.getElementById('listadoCheque:out00').style.display = busqueda;
			document.getElementById('listadoCheque:outNroDesde').style.display = busqueda;
			document.getElementById('listadoCheque:nroDesde').style.display = busqueda;
			document.getElementById('listadoCheque:outNroHasta').style.display = busqueda;
			document.getElementById('listadoCheque:nroHasta').style.display = busqueda;
			
			document.getElementById('listadoCheque:outImporte').style.display = busqueda;
			document.getElementById('listadoCheque:out01').style.display = busqueda;
			document.getElementById('listadoCheque:outImpDesde').style.display = busqueda;
			document.getElementById('listadoCheque:impDesde').style.display = busqueda;
			document.getElementById('listadoCheque:outImpHasta').style.display = busqueda;
			document.getElementById('listadoCheque:impHasta').style.display = busqueda;
			
			document.getElementById('listadoCheque:outFechaEmision').style.display = busqueda;
			document.getElementById('listadoCheque:out02').style.display = busqueda;
			document.getElementById('listadoCheque:outEmiDesde').style.display = busqueda;
			document.getElementById('listadoCheque:GRdesdeEmision').style.display = busqueda;
			document.getElementById('listadoCheque:outEmiHasta').style.display = busqueda;
			document.getElementById('listadoCheque:GRhastaEmision').style.display = busqueda; 
			
			document.getElementById('listadoCheque:outFechaPago').style.display = busqueda;
			document.getElementById('listadoCheque:out03').style.display = busqueda;
			document.getElementById('listadoCheque:outPagDesde').style.display = busqueda;
			document.getElementById('listadoCheque:GRdesdePago').style.display = busqueda;
			document.getElementById('listadoCheque:outPagHasta').style.display = busqueda;
			document.getElementById('listadoCheque:GRhastaPago').style.display = busqueda;
			
			document.getElementById('listadoCheque:outEstFecha').style.display = composicion;
			document.getElementById('listadoCheque:GRfachaEstado').style.display = composicion;
			document.getElementById('listadoCheque:outBenef').style.display = busqueda;
			document.getElementById('listadoCheque:benef').style.display = busqueda;

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
    ${ChequeBean.tituloCorto}
    <small>${ChequeBean.tituloLargo}</small>
  </h1>
</section>

<section class="content">

<div class="box box-default">
<div class="box-header"><h3></h3></div>


<center>


	<h:form id="listadoCheque">
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
							<h:panelGrid columns="3" align="center">
								<s:fieldset legend="Filtrar por:" style=" width : 230px;">
									<h:selectOneRadio value="#{ChequeBean.selectFiltro}" id="selectOneRadio" onchange="cambiarFiltros(this);" styleClass="radioB">
										<f:selectItem itemValue="1" itemLabel="Busqueda" id="Busqueda"/>
										<f:selectItem itemValue="2" itemLabel="Composición" id="Composicion"/>
									</h:selectOneRadio>
								</s:fieldset>
								<f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim>
								<x:commandButton id="btnBuscar" value="Buscar" styleClass="btn btn-primary btn-flat"
									action="#{ChequeBean.listarCheque}" title="Busca los Cheques seleccionados" />
							</h:panelGrid>
							
						<f:verbatim><br></f:verbatim>
							
							
						<s:layoutingTitlePane id="filtroCaja" label=" Filtro Cheque" containerNodeClass="contentTitlePane" 
												labelNodeClass="labelTitlePane" >
							<f:verbatim><br></f:verbatim>
							<h:panelGrid id="filtroUno" columns="6" width="848" align="center">
								
								
								
								<h:outputText value="Tipo:" styleClass="texto" style="margin-right:10px"/>
								<h:selectOneMenu id="lstTipo" value="#{ChequeBean.idTipoSeleccionada}"
									styleClass="lista" immediate="true" onfocus="encender(this);"
									onblur="apagar(this);" style="width: 130px">
									<f:selectItem itemValue="0" itemLabel="Seleccione" id="itemSeleccione"/>
									<f:selectItem itemValue="P" itemLabel="Propio" id="itemPropio"/>
									<f:selectItem itemValue="T" itemLabel="Tercero" id="itemTercero"/>
									<f:selectItem itemValue="A" itemLabel="Acreditación" id="itemAcred"/>
								</h:selectOneMenu>
								
								<h:outputText value="Banco:" styleClass="texto" style="margin-left:20px;margin-right:10px"/>
								<h:selectOneMenu id="lstBanco" value="#{ChequeBean.idBancoSeleccionada}"
									styleClass="lista" immediate="true" onfocus="encender(this);"
									onblur="apagar(this);" style="width: 200px">
									<f:selectItems value="#{ChequeBean.bancoItems}"/>
								</h:selectOneMenu>
								
								<h:outputText value="Estado:" styleClass="texto" style="margin-left:20px;margin-right:10px"/>
								<h:selectOneMenu id="lstEstado" value="#{ChequeBean.idEstadoSeleccionada}"
									styleClass="lista" immediate="true" onfocus="encender(this);"
									onblur="apagar(this);" style="width: 200px">
									<f:selectItems value="#{ChequeBean.estadoItems}"/>
								</h:selectOneMenu>
								
								

								<h:outputText id="outNroCheque" value="Numero de Cheque" styleClass="texto" style="margin-right:10px"/>
								<h:outputText id="out00"  value=" "/>

								<h:outputText id="outNroDesde" value="Desde:" styleClass="texto" style="margin-left:20px;margin-right:10px"/>
								<h:inputText id="nroDesde" value="#{ChequeBean.nroChequeDesde}" align="right" 
									styleClass="bordeceldainferior" maxlength="10" size="10"
									style="width: 100px" onfocus="encender(this);" onblur="apagar(this);"
									onkeypress="return soloEnteros(this,event);"/>

								<h:outputText id="outNroHasta" value="Hasta:" styleClass="texto" style="margin-left:20px;margin-right:10px"/>
								<h:inputText id="nroHasta" value="#{ChequeBean.nroChequeHasta}" align="right"
									styleClass="bordeceldainferior" maxlength="10" size="10"
									style="width: 100px" onfocus="encender(this);" onblur="apagar(this);"
									onkeypress="return soloEnteros(this,event);"/>



								<h:outputText id="outImporte" value="Importe" styleClass="texto" style="margin-right:10px"/>
								<h:outputText id="out01"  value=" "/>

								<h:outputText id="outImpDesde" value="Desde:" styleClass="texto" style="margin-left:20px;margin-right:10px"/>
								<h:inputText id="impDesde" value="#{ChequeBean.importeDesde}" align="right" 
									styleClass="bordeceldainferior" maxlength="10" size="10"
									style="width: 100px" onfocus="encender(this);" onblur="apagar(this);"
									onkeypress="return soloDecimalesPrecisos(this,event,2);"/>

								<h:outputText id="outImpHasta" value="Hasta:" styleClass="texto" style="margin-left:20px;margin-right:10px"/>
								<h:inputText id="impHasta" value="#{ChequeBean.importeHasta}" align="right"
									styleClass="bordeceldainferior" maxlength="10" size="10"
									style="width: 100px" onfocus="encender(this);" onblur="apagar(this);"
									onkeypress="return soloDecimalesPrecisos(this,event,2);"/>

								
								<h:outputText id="outFechaEmision" value="Fecha Emision" styleClass="texto" style="margin-right:10px"/>
								<h:outputText id="out02"  value=" "/>
								
								<h:outputText id="outEmiDesde" value="Desde:" styleClass="texto" style="margin-left:20px;margin-right:10px"/>
								<h:panelGroup id="GRdesdeEmision">
							 		<f:verbatim>
								            <div class="input-group date" style="width:200px">
								                <div class="input-group-addon">
								                    <i class="fa fa-calendar"></i>
								                </div>
								                <input type="text" class="form-control pull-right" id="fDesde">
								            </div>
									</f:verbatim>
								</h:panelGroup>
								
								<h:outputText id="outEmiHasta" value="Hasta:" styleClass="texto" style="margin-left:20px;margin-right:10px"/>
								<h:panelGroup id="GRhastaEmision">
							 		<f:verbatim>
								            <div class="input-group date" style="width:200px">
								                <div class="input-group-addon">
								                    <i class="fa fa-calendar"></i>
								                </div>
								                <input type="text" class="form-control pull-right" id="fHasta">
								            </div>
									</f:verbatim>
								</h:panelGroup>


								<h:outputText id="outFechaPago" value="Fecha Pago" styleClass="texto" style="margin-right:10px"/>
								<h:outputText id="out03"  value=" "/>
								
								<h:outputText id="outPagDesde" value="Desde:" styleClass="texto" style="margin-left:20px;margin-right:10px"/>
								<h:panelGroup id="GRdesdePago">
							 		<f:verbatim>
								            <div class="input-group date" style="width:200px">
								                <div class="input-group-addon">
								                    <i class="fa fa-calendar"></i>
								                </div>
								                <input type="text" class="form-control pull-right" id="fDesdePago">
								            </div>
									</f:verbatim>
								</h:panelGroup>
								
								<h:outputText id="outPagHasta" value="Hasta:" styleClass="texto" style="margin-left:20px;margin-right:10px"/>
								<h:panelGroup id="GRhastaPago">
							 		<f:verbatim>
								            <div class="input-group date" style="width:200px">
								                <div class="input-group-addon">
								                    <i class="fa fa-calendar"></i>
								                </div>
								                <input type="text" class="form-control pull-right" id="fHastaPago">
								            </div>
									</f:verbatim>
								</h:panelGroup>
								
								

								<h:outputText id="outEstFecha" value="Estado a la Fecha:" styleClass="texto" style="display:none;margin-right:10px;margin-top:3px"/>
								<h:panelGroup id="GRfachaEstado" style="display:none;">
							 		<f:verbatim>
								            <div class="input-group date" style="padding-right:20px">
								                <div class="input-group-addon">
								                    <i class="fa fa-calendar"></i>
								                </div>
								                <input type="text" class="form-control pull-right" id="fEstado">
								            </div>
									</f:verbatim>
								</h:panelGroup>
							
								<h:outputText value="Ordenado por:" styleClass="texto" style="margin-right:10px"/>
						 		<h:selectOneMenu id="lstOrden" value="#{ChequeBean.idOrdenSeleccionada}"
									styleClass="lista" immediate="true" onfocus="encender(this);" 
									onblur="apagar(this);" style="width: 200px">
									<f:selectItems value="#{ChequeBean.ordenItems}"/>
								</h:selectOneMenu>
								
								<h:outputText id="outBenef" value="Beneficiario:" styleClass="texto" style="margin-left:20px;margin-right:10px"/>
								<h:inputText id="benef" value="#{ChequeBean.beneficiario}"
									styleClass="bordeceldatext" style="width: 200px" 
									onfocus="encender(this);" onblur="apagar(this);"/>
								
								
							</h:panelGrid>
						</s:layoutingTitlePane>
						</h:panelGrid>
						
						<f:verbatim><br></f:verbatim>

						<h:panelGrid columns="1" align="center" id="paelSecundario" width="850">
						<c:if test="${!empty ChequeBean.chequeList}">
							<f:verbatim>
								<display:table id="listaCheque" name="sessionScope.ChequeBean.chequeList"
									class="table-bordered table-striped"
									requestURI="/tarjetafiel/fondos/listarCheque.jsf"
									requestURIcontext="true" style="width: 900px;">

										<display:column property="tipoFormat" title="Tipo" sortable="true" class="tdA" maxLength="13" style="width: 100px"/>
										<display:column property="numero" title="Numero" sortable="true" class="tdB"/>
										<display:column property="beneficiario" title="Beneficiario" sortable="true" class="tdA"/>
										<display:column property="fechaEmisionFormat" title="Fecha Emision" sortable="true" class="tdB"/>
										<display:column property="fechaPagoFormat" title="Fecha Pago" sortable="true" class="tdB"/>
										<display:column property="importe" title="Importe" sortable="true" class="tdB"/>
										<display:column property="banco.descripcion" title="Banco" sortable="true" class="tdA"/>
										<display:column style="width: 25px;" media="html">
											<a href="javascript:viewUser('${listaCheque.idCheque}','idChequeHidden');javascript:clickLink('listadoCheque:verHistoricoLink')">
												<img src='<%=request.getContextPath()%>/img/icon/editar.gif' title='Historico del Cheque' border='0' />
											</a>
										</display:column>

										<display:setProperty name="basic.show.header" value="true" /> 
										<display:setProperty name="basic.msg.empty_list" value="No se encontraron elementos." />
										<display:setProperty name="sort.amount" value="list" />
										<display:setProperty name="paging.banner.group_size" value="6" />
										<display:setProperty name="paging.banner.placement" value="bottom" />
										<display:setProperty name="paging.banner.item_name" value="Cheque" />
										<display:setProperty name="paging.banner.items_name" value="Cheques" />
										
								</display:table>
							</f:verbatim>

	                        <h:panelGrid id="botoneraPaginadorLote" width="200" columns="7" align="center">
	                        	<h:commandLink id="botonPrimeraPaginaLote" action="#{ChequeBean.primeraPagina}" 
	                        		rendered="#{ChequeBean.pagDeMov.hayAnterior}" styleClass="botones">
	                        		<x:graphicImage value="/img/icon/skipb_16.gif" border="0" 
	                        			onclick="submit();" />
	                        	</h:commandLink>
	                        	<h:commandLink id="botonPaginaAnteriorLote" action="#{ChequeBean.paginaAnterior}" 
	                        		rendered="#{ChequeBean.pagDeMov.hayAnterior}" styleClass="botones">
	                        		<x:graphicImage value="/img/icon/rewnd_16.gif" border="0" 
	                        			onclick="submit();" />
	                        	</h:commandLink>
	                        	<h:outputText value="Página " />
	                        	<h:selectOneMenu  id="lstDePaginas" value="#{ChequeBean.pagDeMov.idPaginaSeleccionada}" binding="#{ChequeBean.pagDeMov.pagSeleccionada}"
     					  						styleClass="lista" onfocus="encender(this);" immediate="true" valueChangeListener="#{ChequeBean.cargarPagina}"
     					 						 onblur="apagar(this);" style=" width : 50px;" onchange="submit();">
     					 						 <f:selectItems value="#{ChequeBean.pagDeMov.comboDePaginas}" id="selectEjerDeSucum" />
								</h:selectOneMenu>	
								<h:outputText value="#{ChequeBean.pagDeMov.estado}" />
	                        	<h:commandLink id="botonPaginaSiguienteLote" action="#{ChequeBean.paginaSiguiente}" 
	                        		rendered="#{ChequeBean.pagDeMov.haySiguiente}" styleClass="botones">
	                        		<x:graphicImage value="/img/icon/fastf_16.gif" border="0" 
	                        			onclick="submit();" />
	                        	</h:commandLink>
	                        	<h:commandLink id="botonUltimaPaginaLote" action="#{ChequeBean.ultimaPagina}" 
	                        		rendered="#{ChequeBean.pagDeMov.haySiguiente}" styleClass="botones">
	                        		<x:graphicImage value="/img/icon/skipf_16.gif" border="0" 
	                        			onclick="submit();"/>
	                        	</h:commandLink>
	                        </h:panelGrid>
							
							<h:panelGrid id="botoneraExportaciones" width="200" columns="7" align="left">
								<h:outputText id="outExportarA" value="Exportar a: " styleClass="texto"/>
								<h:commandLink id="botonExportarPdf" actionListener="#{ChequeBean.exportarAPdf}" 
	                        		rendered="true" styleClass="btn btn-primary btn-flat" value="PDF">
	                        	</h:commandLink>
								<h:commandLink id="botonExportarExcel" actionListener="#{ChequeBean.exportarAExcel}" 
	                        		rendered="true" styleClass="btn btn-primary btn-flat" value="EXCEL" >
	                        	</h:commandLink>
	                        </h:panelGrid>

							<%-- Link oculto para eliminar o editar --%>
							<x:commandLink action="#{ChequeBean.verHistorico}" id="verHistoricoLink" style="display: none;"/>
							<x:commandLink action="#{ChequeBean.eliminarChequera}" id="eliminarChequeraLink" style="display: none;"/>

							<%-- Crear un input hidden para contener el id del elemento seleccionado --%>
							<x:inputHidden id="idChequeHidden" forceId="true" value="#{ChequeBean.idChequeHidden}"/>

	                        </c:if>
                     	  </h:panelGrid>

							<c:if test="${!empty ChequeBean.historicoList}">
								<x:div style="OVERFLOW-Y: auto; OVERFLOW-X: auto; WIDTH: 880px; HEIGHT: 370px; border: 1px; margin-left: auto; margin-right: auto;">
									<x:dataTable value="#{ChequeBean.historicoList}" id="tablaTareasTomadas"
												 styleClass="standardTable"
	                    		       			 headerClass="standardTable_Header"
	                           					 footerClass="standardTable_Header"
			                           			 rowClasses="standardTable_Row1,standardTable_Row2"
			                           			 sortable="true"
	        		                   			 columnClasses="standardTable_Column,standardTable_ColumnCentered,standardTable_Column"							             
									             var="historico" style=" width : 855px;">
							    
										<x:column>
		    		                        <f:facet name="header">
		            		                    <h:outputText value="Fecha Historico" styleClass="texto" />
		                    		        </f:facet>
			                                <h:outputText value="#{historico.timestampFormat}" styleClass="texto" />
		    		                    </x:column>
			                        	<x:column>
		    		                        <f:facet name="header">
		            		                    <h:outputText value="Estado" styleClass="texto" />
		                    		        </f:facet>
			                                <h:outputText value="#{historico.chequeEstado.descripcion}" styleClass="texto" />
		    		                    </x:column>
				                        <x:column>
		    		                        <f:facet name="header">
		            		                    <h:outputText value="numero" styleClass="texto" />
		                    		        </f:facet>
			                                <h:outputText value="#{historico.cheque.numero}" styleClass="texto" />
		    		                    </x:column>
										<x:column>
		                    		        <f:facet name="header">
				                                <h:outputText value="Beneficiario" styleClass="texto" />
		    		                        </f:facet>
	                		                <h:outputText value="#{historico.cheque.beneficiario}" styleClass="texto" />
		                    		    </x:column>
<%--											<x:column>
												<x:commandLink id="verTareaLink" action="#{EscritorioBean.verTarea}">
													<f:param id="idDetalle" name="idDetalle" value="#{historico.detalleTramite.idDetalleTramite}"/>
													<x:graphicImage value="/img/icon/editar.gif" title="Ver Tarea." border="0"/>
												</x:commandLink>
											</x:column>   --%>
									</x:dataTable>
								</x:div>
							</c:if>
						</h:panelGrid>
						<%-- Crear un input hidden para contener el id del elemento seleccionado --%>
						<x:inputHidden id="busquedaHidden" forceId="true" value="#{ChequeBean.busquedaHidden}"/>
						<x:inputHidden id="composicionHidden" forceId="true" value="#{ChequeBean.composicionHidden}"/>
				</h:panelGroup>
			</f:facet>
		
		</x:panelLayout>

		<h:inputText id="FechaDesde" value="#{ChequeBean.fechaEmisionDesde}" style="display: none;">
	        <f:convertDateTime type="both" pattern = "dd/MM/yyyy HH:mm"/>
	    </h:inputText>
	    <h:inputText id="FechaHasta" value="#{ChequeBean.fechaEmisionHasta}" style="display: none;">
	        <f:convertDateTime type="both" pattern = "dd/MM/yyyy HH:mm"/>
	    </h:inputText>

	    <h:inputText id="FechaDesdePago" value="#{ChequeBean.fechaPagoDesde}" style="display: none;">
	        <f:convertDateTime type="both" pattern = "dd/MM/yyyy HH:mm"/>
	    </h:inputText>
	    <h:inputText id="FechaHastaPago" value="#{ChequeBean.fechaPagoHasta}" style="display: none;">
	        <f:convertDateTime type="both" pattern = "dd/MM/yyyy HH:mm"/>
	    </h:inputText>
	    <h:inputText id="FechaEstado" value="#{ChequeBean.fechaEstado}" style="display: none;">
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

for(var i = 1; i < mainMenu__idJsp1_menu.length-1; i++) {
    var obj = mainMenu__idJsp1_menu[i];
    document.getElementById("sideMenu").innerHTML += generateMenu(obj,"mainMenu__idJsp1_menu:A]\#{ChequeBean.irAListarCheque}") + `</li>`;
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

    $("#fDesdePago").datepicker({
      autoclose: true,
      orientation: "bottom"
    });

    $("#fHastaPago").datepicker({
      autoclose: true,
      orientation: "bottom"
    });
    
    $("#fEstado").datepicker({
        autoclose: true,
        orientation: "bottom"
      });


	//Seteo fehcas que trae el bean por defecto, en el datepicker
	var fd = document.getElementById("listadoCheque:FechaDesde").value.split(" ")[0].split("/");
	var year = fd[2];
	var month = fd[1];
	month--;
	var date = fd[0];
	var fAux = new Date(year,month,date);
	$("#fDesde").datepicker("setDate", fAux);
    document.getElementById("listadoCheque:FechaDesde").value = $.datepicker.formatDate('dd/mm/yy', fAux) + " 03:00";
	
	fd = document.getElementById("listadoCheque:FechaHasta").value.split(" ")[0].split("/");
	year = fd[2];
	month = fd[1];
	month--;
	date = fd[0];
	fAux = new Date(year,month,date);
	$("#fHasta").datepicker("setDate", fAux);
	
	fd = document.getElementById("listadoCheque:FechaDesdePago").value.split(" ")[0].split("/");
	year = fd[2];
	month = fd[1];
	month--;
	date = fd[0];
	fAux = new Date(year,month,date);
	$("#fDesdePago").datepicker("setDate", fAux);
	document.getElementById("listadoCheque:FechaDesdePago").value = $.datepicker.formatDate('dd/mm/yy', fAux) + " 03:00";
	
	fd = document.getElementById("listadoCheque:FechaHastaPago").value.split(" ")[0].split("/");
	year = fd[2];
	month = fd[1];
	month--;
	date = fd[0];
	fAux = new Date(year,month,date);
	$("#fHastaPago").datepicker("setDate", fAux);
	document.getElementById("listadoCheque:FechaHastaPago").value = $.datepicker.formatDate('dd/mm/yy', fAux) + " 03:00";
	
	fd = document.getElementById("listadoCheque:FechaEstado").value.split(" ")[0].split("/");
	year = fd[2];
	month = fd[1];
	month--;
	date = fd[0];
	fAux = new Date(year,month,date);
	$("#fEstado").datepicker("setDate", fAux);
	document.getElementById("listadoCheque:FechaEstado").value = $.datepicker.formatDate('dd/mm/yy', fAux) + " 03:00";
	
    

    //Muevo fechas de datepicker a input q pasa datos al bean
    $("#fDesde").change(function() {
        var fd = $(this).datepicker("getDate");
        document.getElementById("listadoCheque:FechaDesde").value = $.datepicker.formatDate('dd/mm/yy', fd) + " 03:00";
    });

    $("#fHasta").change(function() {
        var fh = $(this).datepicker("getDate");
        document.getElementById("listadoCheque:FechaHasta").value = $.datepicker.formatDate('dd/mm/yy', fh) + " 03:00";
    });

    $("#fDesdePago").change(function() {
        var fd = $(this).datepicker("getDate");
        document.getElementById("listadoCheque:FechaDesdePago").value = $.datepicker.formatDate('dd/mm/yy', fd) + " 03:00";
    });

    $("#fHastaPago").change(function() {
        var fh = $(this).datepicker("getDate");
        document.getElementById("listadoCheque:FechaHastaPago").value = $.datepicker.formatDate('dd/mm/yy', fh) + " 03:00";
    });
    
    $("#fEstado").change(function() {
        var fh = $(this).datepicker("getDate");
        document.getElementById("listadoCheque:FechaEstado").value = $.datepicker.formatDate('dd/mm/yy', fh) + " 03:00";
    });


  });
</script>


</body>
</html>
</f:view>
