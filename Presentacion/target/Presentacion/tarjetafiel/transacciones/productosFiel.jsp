<%@include file="/inc/tags.jsp" %>
<jsp:useBean id="ahora" class="java.util.Date" scope="request"/>

<f:view>
<html>
<head>
	<title><h:outputText value="#{productosFielBean.tituloLargo}"/></title>
	<s:script language="javascript">
		function recargar() {
			document.getElementById('productosFielBeanForm').submit();
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

<jsp:include page="/inc/includes.jsp"/>

<body class="skin-blue layout-top-nav" onbeforeunload="ShowWait('productosFielBeanForm');">



<!-- wrapper -->
<div class="wrapper">

<header class="main-header">

    <!-- Header Navbar: style can be found in header.less -->
    <nav class="navbar navbar-static-top">
      <!-- Sidebar toggle button-->

          <!-- Logo -->
    <a href="/Presentacion/inicio.jsf" class="logo">
      <!-- mini logo for sidebar mini 50x50 pixels -->
      <span class="logo-mini"><b>T</b>F</span>
      <!-- logo for regular state and mobile devices -->
      <span class="logo-lg"><b>Tarjeta</b> FIEL</span>
    </a>

      <div class="navbar-custom-menu">
        <ul class="nav navbar-nav">
          
          <!-- User Account -->
          <li class="dropdown">
            <a href="#" class="dropdown-toggle" data-toggle="dropdown">
              <span class="hidden-xs"><%=request.getSession().getAttribute("nombreOperador")%></span>
            </a>
          <ul class="dropdown-menu">
              <!-- Menu Footer-->
              <li class="user-footer">
                <div class="row">
                  <div class="col-xs-4">
                  </div>
                  <div class="col-xs-4">
                      <a class="btn btn-default btn-flat" onclick="callLink('mainMenu:cerrarSesion')" >Salir</a>
                  </div>
                  <div class="col-xs-4">
                  </div>
                </div>
              </li>
            </ul>
          </li>

        </ul>
      </div>
    </nav>

</header>


  <!-- Main content -->
<div class="content-wrapper">

<section class="content-header">
  <h1>
    ${ProductosFielBean.tituloCorto}
    <small>${ProductosFielBean.tituloLargo}</small>
  </h1>
</section>

<section class="content">
	<div class="box box-default">
		<div class="box-header"><h3></h3>
		</div>

<center>
	<secure:check/>

	<h:form id="productosFielBeanForm">

	<%-- GRABA EL ESTADO DEL SCROLL(Dibujar solo si el popup no se muestra) --%>
	<h:panelGroup rendered="#{!ProductosFielBean.popup.mostrar}">
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
	                    <h:panelGrid id="principalPanel" columns="1" align="center" >
	                         <x:panelTabbedPane id="tabbedTablas"  align="center" serverSideTabSwitch="false" width="800" style="height: 400">
			                 <%-- DECLARACION DEL TAB --%>
								 <x:panelTab id="tabConceptosFiel" label="Conceptos" >
										<%-- PANEL DE CONCEPTOS --%>
										<h:panelGrid id="panelListadoC" columns="1" width="750" align="center">
											<s:fieldset id="fielConceptos"  legend="Conceptos Fiel" >
												<h:panelGrid id="panelListadoIntC" columns="3" width="750" align="center">
													<h:dataTable id="tblDeConceptosExistentes1" value="#{ProductosFielBean.conceptoFielList1}" width="232" var="concep1">
				                            	 		<h:column>
					                            			<x:commandLink id="columnaConceptos1" action="#{concep1.editar}" value="#{concep1.concepto.descripcion}"></x:commandLink>
														</h:column>
				                          			</h:dataTable>
													<h:dataTable id="tblDeConceptosExistentes2" value="#{ProductosFielBean.conceptoFielList2}" width="232" var="concep2">
				                            	 		<h:column>
					                            			<x:commandLink id="columnaConceptos2" action="#{concep2.editar}" value="#{concep2.concepto.descripcion}"></x:commandLink>
														</h:column>
				                          			</h:dataTable>
				                          			<h:dataTable id="tblDeConceptosExistentes3" value="#{ProductosFielBean.conceptoFielList3}" width="232" var="concep3">
				                            	 		<h:column>
					                            			<x:commandLink id="columnaConceptos3" action="#{concep3.editar}" value="#{concep3.concepto.descripcion}"></x:commandLink>
														</h:column>
				                          			</h:dataTable>
				                          		</h:panelGrid>
		                          			</s:fieldset>
										</h:panelGrid>
										<f:verbatim>&nbsp;</f:verbatim>
										<h:panelGrid id="panelConceptoEditado" style="height: 200;" columns="1" width="750" align="center" rendered="#{ProductosFielBean.conceptoSeleccionado}">
										<h:outputText value="#{ProductosFielBean.conceptoEditado.descripcion}" style="FONT-SIZE: large;" styleClass="texto"/>
										<h:panelGrid columns="3" id="versionesExistentes" align="center" width="700" >
				                        	<h:outputText value="Versiones Anteriores:" styleClass="texto"/>
				                        	<h:outputText value="Version Actual:" styleClass="texto"/>
				                        	<h:outputText value="Versiones Futuras:" styleClass="texto"/>
				                        	
				                        	<c:choose>
										        <c:when test="${!empty ProductosFielBean.conceptoEditado.versionesAnteriores}">
						                            <h:dataTable id="tblVersionesAnt" value="#{ProductosFielBean.conceptoEditado.versionesAnteriores}" var="verAnt">
						                            	 <h:column>
							                            	<x:commandLink id="verVersionesAnt" action="#{ProductosFielBean.editarVersionAnterior}" value="Versión: #{verAnt.version} - Fecha Inicio: #{verAnt.diaMesAnioVigencia}" >
																<f:param id="idVerAnterior" name="idVerAnterior" value="#{verAnt.version}"/>
															</x:commandLink>
														</h:column>
						                            </h:dataTable>
				                             	</c:when>
										        <c:otherwise>
										        	<h:outputText value="No existen versiones anteriores." styleClass="texto" style="color:green"/>
										        </c:otherwise>
										    </c:choose>
											<c:choose>
										        <c:when test="${null != ProductosFielBean.conceptoEditado.versionVigente}">
										            <x:commandLink id="verNuevosLink" action="#{ProductosFielBean.editarVersionVigente}" value="Versión: #{ProductosFielBean.conceptoEditado.versionVigente.version} - Fecha Inicio: #{ProductosFielBean.conceptoEditado.versionVigente.diaMesAnioVigencia}" >
														<f:param id="idVerActual" name="idVerActual" value="1"/>
													</x:commandLink>
										        </c:when>
										        <c:otherwise>
										            <h:outputText value="No existe una version actual." styleClass="texto" style="color:green"/>
										        </c:otherwise>
										    </c:choose>
											<c:choose>
										        <c:when test="${!empty ProductosFielBean.conceptoEditado.versionesFuturas}">
						                            <h:dataTable id="tblVersionesFut" value="#{ProductosFielBean.conceptoEditado.versionesFuturas}" var="verFut">
						                                <h:column>
							                            	<x:commandLink id="verVersionesFut" action="#{ProductosFielBean.editarVersionFutura}" value="Versión: #{verFut.version} - Fecha Inicio: #{verFut.diaMesAnioVigencia}" >
																<f:param id="idVerFutura" name="idVerFutura" value="#{verFut.version}"/>
															</x:commandLink>
														</h:column>
						                            </h:dataTable>
						                        </c:when>
										        <c:otherwise>
										            <h:outputText value="No existen versiones futuras." styleClass="texto" style="color:green"/>
										        </c:otherwise>
										    </c:choose>
				                        </h:panelGrid>
										<f:verbatim>&nbsp;</f:verbatim>
										
										<h:panelGrid columns="2" id="filtrosPFGenerales" >
											<h:panelGrid columns="3" id="filtrosPF" >
													<h:outputText id="outReglaPF"  value="Regla De Selección:" styleClass="texto"/>
													<h:selectOneMenu id="lstDeEstadosRegla" value="#{ProductosFielBean.idReglaSeleccionada}" binding="#{ProductosFielBean.reglaHtml}"
														styleClass="lista" immediate="true" onfocus="encender(this);" valueChangeListener="#{ProductosFielBean.buscarDetallesXConcepto}"
														onblur="apagar(this);" onchange="submit();">
														<f:selectItems id="selectItemsReglaPF" value="#{ProductosFielBean.reglasItems}"/>
													</h:selectOneMenu>
													<f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim>
											</h:panelGrid>
											<h:panelGrid columns="1" id="filtrosPFTabla" width="300" align="center">
											        <h:panelGrid id="panleyFecha" columns="5" align="center">
											            <h:outputText id="leyendaFechaVig" value="En vigencia desde:" styleClass="texto"/>
												        <x:inputCalendar id="fechaVigenciaCal" monthYearRowClass="yearMonthHeader" disabled="#{!ProductosFielBean.altaFiltros}"
															weekRowClass="weekHeader" popupButtonStyleClass="standard_bold" 
															currentDayCellClass="currentDayCell" value="#{ProductosFielBean.conceptoFielVersionEditado.fechaVigencia}" renderAsPopup="true"
															styleClass="bordeceldainferior" style="width: 90px"
															popupDateFormat="dd/MM/yyyy" popupWeekString="#{example_messages['popup_week_string']}"
															helpText="DD/MM/YYYY" 
														forceId="true"/>
														<f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim>
														 <h:outputText id="leyendaVersionEd" value="Versión:" styleClass="texto"/>
														 <h:outputText id="leyendaVersionNum" value="#{ProductosFielBean.conceptoFielVersionEditado.version}" styleClass="textoblue"/>
													</h:panelGrid>
										        	<c:if test="${!empty ProductosFielBean.detallesXConcepto}">
														<h:dataTable id="tablaConFiltros" value="#{ProductosFielBean.detallesXConcepto}" var="detalle" width="400" align="center">
							                                <h:column>
								                            		<f:facet name="header">
																		<h:outputText value="Filtro" styleClass="texto" style="font: bold;color: white;" />
																	</f:facet>
																	<h:outputText value="#{detalle.detalleReglaXConcepto.detalleReglaPF.descripcion}"/>
															</h:column>
															<h:column>
								                            		<f:facet name="header">
																		<h:outputText value="Lista Precio" styleClass="texto" style="font: bold;color: white;" />
																	</f:facet>
																	<x:commandLink id="verNuevosLink" action="#{detalle.irAgregarListaPrecio}" value="#{detalle.label}">
																		
												                    </x:commandLink>
															</h:column>
							                            </h:dataTable>
						                            </c:if>
						                            <h:panelGrid id="panlistaPordefecto" columns="1" align="center">
											            <h:outputText id="leyendaListaDefecto" value="En caso de no cumplir ningun filtro, se aplicará la siguiente lista de precios:" styleClass="texto" style="color:green" />
													    <x:commandLink id="verLisDefectoLink" action="#{ProductosFielBean.agregarListaPrecioDefecto}" value="#{ProductosFielBean.listaPrecioDefecto}"/>
													</h:panelGrid>
											</h:panelGrid>
										</h:panelGrid>
										<f:verbatim>&nbsp;</f:verbatim>
										<h:panelGrid columns="10" id="botoneras" rendered="#{ProductosFielBean.hayReglas}">
											<f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim>
											<f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim>
											<f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim>
											<f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim>
											<f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim>
											<f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim>
											<x:commandButton id="buttonGrabarNuevaVersion" rendered="#{!ProductosFielBean.altaFiltros}" value="Guardar nueva versión" action="#{ProductosFielBean.aceptarNuevaVersion}" styleClass="botones" />
											<x:commandButton id="buttonGrabar" value="Guardar" rendered="#{ProductosFielBean.verGuardar}" action="#{ProductosFielBean.grabar}" styleClass="botones"/>
											<x:commandButton id="buttonBorrarVersion" value="Borrar Versión" rendered="#{ProductosFielBean.verEdicion}" action="#{ProductosFielBean.borrarVersion}" styleClass="botones"/>
											<x:commandButton id="buttonCancelar" value="Cancelar" action="#{ProductosFielBean.cancelar}"  styleClass="botones"/>
										</h:panelGrid>
										<h:panelGrid columns="4" id="aceptarFechaParaNV" rendered="#{ProductosFielBean.verAltaNuevaVersion}">
											<h:outputText id="leyendaParaFechaNV" value="Ingrese la fecha en la que entrará en vigencia la versión:" styleClass="texto" style="color:green"/>
										    <x:inputCalendar id="aPartirDe" monthYearRowClass="yearMonthHeader" weekRowClass="weekHeader" popupButtonStyleClass="standard_bold"
										          currentDayCellClass="currentDayCell" styleClass="bordeceldainferior" style="width: 90px" renderAsPopup="true"
										          popupDateFormat="dd/MM/yyyy" popupWeekString="#{example_messages['popup_week_string']}" helpText="DD/MM/YYYY" 
										          forceId="true" value="#{ProductosFielBean.fechaComienzoNuevaVersion}"
										    />
										    <x:commandButton id="buttonAceptaNue" value="Aceptar" action="#{ProductosFielBean.grabarNuevaVersion}" styleClass="botones"/>
										    <x:commandButton id="buttonCancelaNue" action="#{ProductosFielBean.cancelarNuevaVersion}" value="Cancelar" styleClass="botones"/>
										</h:panelGrid>
									</h:panelGrid>
										
					      		 </x:panelTab>	
					         
                            	 <x:panelTab id="tabListasPrecios" label="Listas de Precios">
										<%-- PANEL DE LISTAS PRECIOS FIEL --%>							
				            			<h:panelGrid id="panelListadoL" columns="1" width="750" align="center">
											<s:fieldset id="fielListas"  legend="Listas de Precio Fiel" >
												<h:panelGrid id="panelListadoIntL" columns="3" width="750" align="center">
													<h:dataTable id="tblDeListasPExistentes1" value="#{ProductosFielBean.listasFielList1}" width="232" var="listas1">
				                            	 		<h:column>
					                            			<x:commandLink id="columnaListas1" action="#{listas1.editar}" value="#{listas1.listaPrecio.descripcion}"></x:commandLink>
														</h:column>
				                          			</h:dataTable>
													<h:dataTable id="tblDeListasExistentes2" value="#{ProductosFielBean.listasFielList2}" width="232" var="listas2">
				                            	 		<h:column>
					                            			<x:commandLink id="columnaListas2" action="#{listas2.editar}" value="#{listas2.listaPrecio.descripcion}"></x:commandLink>
														</h:column>
				                          			</h:dataTable>
				                          			<h:dataTable id="tblDeListasExistentes3" value="#{ProductosFielBean.listasFielList3}" width="232" var="listas3">
				                            	 		<h:column>
					                            			<x:commandLink id="columnaListas3" action="#{listas3.editar}" value="#{listas3.listaPrecio.descripcion}"></x:commandLink>
														</h:column>
				                          			</h:dataTable>
				                          		</h:panelGrid>
		                          			</s:fieldset>
										</h:panelGrid>
										<h:panelGrid id="panelListasEditado" columns="1" width="750" align="center">
										    <h:outputText value="La lista de precios #{ProductosFielBean.listaInvestigada}  es utilizada en los conceptos:" style="FONT-SIZE: large;" rendered="#{ProductosFielBean.hayInvestigada}" id="antVerInv" styleClass="texto"/>
						                    <f:verbatim>&nbsp;</f:verbatim>
						                    <h:outputText value="Versiones Anteriores" style="FONT-SIZE: medium;" id="antVer" styleClass="texto"/>
						                    
						                        <c:if test="${!empty ProductosFielBean.conceptosViejos}">
						                    	<h:dataTable value="#{ProductosFielBean.conceptosViejos}" id="conceptosViejos" var="tar" style="width :400px; margin-left: 25px;">
													<h:column>
														<h:outputText value="#{tar.concepto}"/>
													</h:column>
												</h:dataTable>
												</c:if>
						                        <c:if test="${empty ProductosFielBean.conceptosViejos}">
												<h:outputText value="No existen versiones pasadas de conceptos que utilizen esta Lista de Precio." styleClass="texto" style="color:green"/>
												</c:if>
										</h:panelGrid>
										<h:panelGrid id="panelListasEditado2" columns="1" width="750" align="center">
						                        <h:outputText value="Versiones Actuales" style="FONT-SIZE: medium;" id="actVer" styleClass="texto"/>
						                        <c:if test="${!empty ProductosFielBean.conceptosActuales}">
						                        <h:dataTable value="#{ProductosFielBean.conceptosActuales}"	id="conceptosActuales" var="tarDos" style=" width :400px; margin-left: 25px;">
													<h:column>
														<h:outputText value="#{tarDos.concepto}"/>
													</h:column>
													
			                    				</h:dataTable>
			                    				</c:if>
			                    				<c:if test="${empty ProductosFielBean.conceptosActuales}">
												<h:outputText value="No existen versiones actuales de conceptos que utilizen esta Lista de Precio." styleClass="texto" style="color:green"/>
												</c:if>
										</h:panelGrid>
										<h:panelGrid id="panelListasEditado3" columns="1" width="750" align="center">		
						                         <h:outputText value="Versiones Futuras" style="FONT-SIZE: medium;" id="futVer" styleClass="texto"/>
			                					<c:if test="${!empty ProductosFielBean.conceptosFuturos}">
						                        <h:dataTable value="#{ProductosFielBean.conceptosFuturos}" id="conceptosFuturos" var="tarTres" style=" width :400px; margin-left: 25px;">
													<h:column>
														<h:outputText value="#{tarTres.concepto}"/>
													</h:column>
			                    				</h:dataTable>
			                    				</c:if>
			                    				<c:if test="${empty ProductosFielBean.conceptosFuturos}">
												<h:outputText value="No existen versiones futuras de conceptos que utilizen esta Lista de Precio." styleClass="texto" style="color:green"/>
												</c:if>
										
										
										
										</h:panelGrid>
					      		 </x:panelTab>
                            								
							 </x:panelTabbedPane>
				        </h:panelGrid>
				        
						<f:verbatim>&nbsp;</f:verbatim>
					
					</h:panelGrid>
				</h:panelGroup>
			</f:facet>


		</x:panelLayout>
	</h:form>
</center>



<%@include file="/inc/footer_popup.jsp" %>


<script type="text/javascript">



function callLink(link){
    document.getElementById(link).click();
 }

function executeJsfAction(target,action){
	var dummyForm = document.forms[target];
    dummyForm.elements['jscook_action'].value = action;
    dummyForm.submit();
}

var jq = jQuery.noConflict();


</script>





</body>
</html>
</f:view>
