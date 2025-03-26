<%@include file="/inc/tags.jsp" %>
<jsp:useBean id="ahora" class="java.util.Date" scope="request"/>

<f:view>
<html lang="es">
<head>
   	<title><h:outputText value="#{SolicitudesBean.tituloLargo}"/></title>
    <s:script language="javascript">
    	function recargar() {
    		document.getElementById('listarSolicitudesForm').submit();
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

<body class="hold-transition skin-blue sidebar-mini" onbeforeunload="ShowWait('listarSolicitudesForm');" onload="if('${SolicitudesBean.popup.mostrar}'=='true') {viewDialog.show();} else {window.scrollTo(0,${ScrollBean.hiddenScrollY});}">
	<h:form id="mainMenu" style="display: none">
  <jsp:include page="/inc/navigation_test.jsp" />
  <x:commandLink id="cerrarSesion" action="#{LoginBean.salir}"/>
</h:form>

<jsp:include page="/inc/header.jsp" />

<!-- Content Header (Page header) -->
<section class="content-header">
  <h1>
    ${SolicitudesBean.tituloCorto}
    <small>${SolicitudesBean.tituloLargo}</small>
  </h1>
</section>

<section class="content">

<div class="box box-default">
<div class="box-header"><h3></h3></div>
<center>


<h:form id="listarSolicitudesForm">

	<%-- GRABA EL ESTADO DEL SCROLL(Dibujar solo si el popup no se muestra) --%> 
	<h:panelGroup rendered="#{!SolicitudesBean.popup.mostrar}">
		<%@include file="/inc/scroll.inc" %>   	      
	</h:panelGroup>
	
    <x:panelLayout id="page" layout="#{globalOptions.pageLayout}"
            styleClass="pageLayout"
            headerClass="pageHeader"
            navigationClass="pageNavigation"
            bodyClass="pageBody"
            footerClass="pageFooter" >


        <f:facet name="body" >
            <h:panelGroup id="body">
            	
            	<h:panelGrid columns="1" align="center" id="PanelPricipalPopup" width="900">
				<%-- INCLUDE PARA LOS ERRORES --%>
					<h:panelGroup id="errores">
						<jsp:include page="/inc/error.jsp" />
					</h:panelGroup>
				</h:panelGrid>
            	
            	
            	<h:panelGrid columns="1" align="center" id="PanelFiltroSolicitudes" width="900" rendered="#{!SolicitudesBean.mostrarIndividuos}">
						<s:layoutingTitlePane id="filtroDeSolicitudes" label=" Filtro de Solicitudes" containerNodeClass="contentTitlePane" labelNodeClass="labelTitlePane" >
							<h:panelGrid id="filtroUno" columns="6" width="600" align="center">
							
								<h:outputText value="Nro. de Solicitud:" styleClass="texto"/>
								<h:inputText id="idSolicitudNumero" value="#{SolicitudesBean.nroSolicitud}" styleClass="bordeceldainferior" maxlength="10" size="10"
									style="width: 50px" onfocus="encender(this);" onblur="apagar(this);" onkeypress="return soloEnteros(this,event);"/>
								
								<h:outputText value="Promotor:" styleClass="texto"/>
								<h:inputText id="promotorFiltro" value="#{SolicitudesBean.promotor}" styleClass="bordeceldatext" maxlength="50" size="50"
									style="width: 150px" onfocus="encender(this);" onblur="apagar(this);"/>
																
								<f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim>
								<x:commandButton id="btnBuscar" value="Buscar" action="#{SolicitudesBean.listarSolicitudes}" 
									title="Busca las solicitudes especificadas." styleClass="btn btn-primary btn-flat"/>

							</h:panelGrid>
						</s:layoutingTitlePane>
			    </h:panelGrid>
			    
			    <h:panelGrid columns="1" align="center" id="panelSecundario" width="850" rendered="#{!SolicitudesBean.mostrarIndividuos}">
						<c:if test="${!empty SolicitudesBean.listaSolicitudes}">
							<f:verbatim>
								<display:table id="listaSolicitudes" name="sessionScope.SolicitudesBean.listaSolicitudes"
									defaultsort="1" pagesize="25"
									class="table-bordered table-striped"
									requestURI="/tarjetafiel/evaluacion/lstSolicitudes.jsf"
									export="${lst:contains(requestScope.permisos,'exportacion')}" requestURIcontext="true" style="width: 900px;">

										<display:column property="numero" title="Nro. Solicitud" sortable="true" class="tdB"/>
										<display:column property="promotor" title="Promotor" sortable="true" class="tdA"/>
										<display:column property="solicitud.estados.descripcion" title="Estado" sortable="true" class="tdB"/>
										<display:column property="solicitud.estaImpreso" title="Impresa" sortable="true" class="tdB" />
										
										<display:column style="width: 25px;" media="html">
											<c:choose>
												<c:when test="${lst:contains(requestScope.permisos,'edicion')}" >
													<a href="javascript:viewUser('${listaSolicitudes.numero}','idSolicitudHidden');javascript:clickLink('listarSolicitudesForm:verIndividuosLink')">
														<img src='<%=request.getContextPath()%>/img/icon/OrderView.gif' title='Ver individuos relacionados' border='0' />
													</a>
												</c:when>
												<c:otherwise>
													<a href="#" onclick="return alert('No posee los permisos necesarios.');">
														<img src='<%=request.getContextPath()%>/img/icon/OrderView.gif' title='Ver individuos relacionados' border='0'/>
													</a>
												</c:otherwise>
											</c:choose>
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
										<display:setProperty name="paging.banner.some_items_found">
											<div class="pagebanner" align="center" style="width: 900px;">{0} {1} encontrados, mostrando desde el {2} hasta el {3}</div>
										</display:setProperty>
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

							<%-- Link oculto para eliminar o editar --%>
                  
                            <x:commandLink action="#{SolicitudesBean.verIndividuos}" id="verIndividuosLink" style="display: none;"/>
							<%-- Paso 3. Crear un input hidden para contener el id del elemento seleccionado --%>
							<x:inputHidden id="idSolicitudHidden" forceId="true" value="#{SolicitudesBean.idSolicitudHidden}"/>
							

						</c:if>
						
					</h:panelGrid>
			     
                    <h:panelGrid id="panelOcultoIndividuos" align="center" rendered="#{SolicitudesBean.mostrarIndividuos}" width="900">
                    	<s:fieldset legend="Individuos relacionados a la solicitud" id="fieldIndi">
                    		<h:panelGrid columns="2" id="panelGrid" width="center">
                    		       <h:panelGrid columns="2" id="datosSolicitud">
                    		            <h:outputText value="Nro. Solicitud:" id="outNroSolic" styleClass="texto"/>
										<h:outputText value="#{SolicitudesBean.solicitud.nroSolicitud}#{SolicitudesBean.solicitud.DV}" id="outNumeroSolicitud" styleClass="textoblue"/>
                                        
                                        <h:outputText value="Promotor:" id="outPromot" styleClass="texto"/>
										<h:outputText value="#{SolicitudesBean.solicitud.promotor.apellido}" id="outPromotors" styleClass="textoblue"/>
										
                    		       </h:panelGrid>
                    		       
                    		       <h:panelGrid align="center">
                    		                <c:if test="${!empty SolicitudesBean.listDeIndividuosAsignados}">
												<h:dataTable value="#{SolicitudesBean.listDeIndividuosAsignados}"
													id="tablaUno" styleClass="table-bordered table-striped" headerClass="dataTable_Header"
													footerClass="standardTable_Header" rowClasses="standardTable_Row1,standardTable_Row2"
													columnClasses="standardTable_Column,standardTable_ColumnCentered,standardTable_Column"
													var="solicis" style=" width : 500px;">
			
													<h:column>
														<f:facet name="header">
															<h:outputText value="Apellido Paterno" styleClass="texto" />
														</f:facet>
														<h:outputText value="#{solicis.apellido}"/>
													</h:column>
													
													<h:column>
													    <f:facet name="header">
													        <h:outputText value="Apellido Materno" styleClass="texto" />
													    </f:facet>
			                                            <h:outputText value="#{solicis.apellidoMaterno}" />
													</h:column>
													
													<h:column>
														<f:facet name="header">
															<h:outputText value="Nombres" styleClass="texto" />
														</f:facet>
														<h:outputText value="#{solicis.nombres}"/>
													</h:column>
													
													<h:column>
													    <f:facet name="header">
													        <h:outputText value="Rol en la solicitud" styleClass="texto" />
													    </f:facet>
			                                            <h:outputText value="#{solicis.tipoIndividuo}" />
													</h:column>
													
													<h:column>
														<x:commandLink action="#{SolicitudesBean.irAVerIndividuo}" id="mirarIndividuoLink">
											        		<f:param id="idIndHidden" name="idIndHidden" value="#{solicis.idIndividuo}"/>
											        		<f:param id="tipoIndividuo" name="tipoIndividuo" value="#{solicis.tipoIndividuo}" />
										            		<x:graphicImage value="/img/icon/OrderView.gif" title="Ver Individuo" border="0" />
								            			</x:commandLink>
													</h:column>
													
												</h:dataTable>
											</c:if>
											<c:if test="${empty SolicitudesBean.listDeIndividuosAsignados}">
											    <h:outputText value="La Solicitud no tiene individuos asignados" />
											</c:if>
                    		       <h:commandButton id="botonVolver" styleClass="btn btn-primary btn-flat" value="Volver" action="#{SolicitudesBean.verListado}">
                    		       
                    		       </h:commandButton>
                    		       </h:panelGrid>
                                   
                    	    </h:panelGrid>
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
    document.getElementById("sideMenu").innerHTML += generateMenu(obj,"mainMenu__idJsp2_menu:A]\#{SolicitudesBean.irAListarSolicitudes}") + `</li>`;
}

</script>   
<%@include file="/inc/scripts.jsp" %>
</body>
</html>
</f:view>
