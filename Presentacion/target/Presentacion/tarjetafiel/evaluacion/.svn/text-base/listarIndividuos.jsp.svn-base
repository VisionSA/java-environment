<%@include file="/inc/tags.jsp" %>
<jsp:useBean id="ahora" class="java.util.Date" scope="request"/>

<f:view>
<html lang="es">
<head>
   	<title><h:outputText value="#{IndividuoEvaluacionBean.tituloLargo}"/></title>
    <s:script language="javascript">
    	function recargar() {
    		document.getElementById('listarIndividuosForm').submit();
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

<body  class="hold-transition skin-blue sidebar-mini" onbeforeunload="ShowWait('listarIndividuosForm');" onload="if('${IndividuoEvaluacionBean.popup.mostrar}'=='true') {viewDialog.show();} else {window.scrollTo(0,${ScrollBean.hiddenScrollY});}">

	<h:form id="mainMenu" style="display: none">
  <jsp:include page="/inc/navigation_test.jsp" />
  <x:commandLink id="cerrarSesion" action="#{LoginBean.salir}"/>
</h:form>

<jsp:include page="/inc/header.jsp" />

<!-- Content Header (Page header) -->
<section class="content-header">
  <h1>
    ${IndividuoEvaluacionBean.tituloCorto}
    <small>${IndividuoEvaluacionBean.tituloLargo}</small>
  </h1>
</section>

<section class="content">

<div class="box box-default">
<div class="box-header"><h3></h3></div>

<center>

<secure:check/>

<h:form id="listarIndividuosForm">
  	<%-- GRABA EL ESTADO DEL SCROLL(Dibujar solo si el popup no se muestra) --%> 
	<h:panelGroup rendered="#{!IndividuoEvaluacionBean.popup.mostrar}">
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
            	
            	
            	<h:panelGrid columns="1" align="center" id="PanelFiltroIndividuos" width="900" rendered="#{!IndividuoEvaluacionBean.mostrarSolicitudes}">
						<s:layoutingTitlePane id="filtroDeIndividuos" label=" Filtro de Individuos" containerNodeClass="contentTitlePane" labelNodeClass="labelTitlePane" >
							<h:panelGrid id="filtroUno" columns="6" width="600" align="center">
							
								<h:outputText value="Id Individuo:" styleClass="texto"/>
								<h:inputText id="idPromotorFiltro" value="#{IndividuoEvaluacionBean.idIndividuo}"
								styleClass="bordeceldainferior" maxlength="10" size="10"
								style="width: 50px" onfocus="encender(this);" onblur="apagar(this);"
								onkeypress="return soloEnteros(this,event);"/>
								
								<h:outputText value="Apellido Paterno:" styleClass="texto"/>
								<h:inputText id="apellidoFiltro" value="#{IndividuoEvaluacionBean.apellido}"
								styleClass="bordeceldatext" maxlength="50" size="50"
								style="width: 150px" onfocus="encender(this);" onblur="apagar(this);"/>
								
								<f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim>
								<x:commandButton id="btnBuscar" value="Buscar" 
									action="#{IndividuoEvaluacionBean.listarIndividuos}" title="Busca el Individuo especificado." styleClass="botones"/>

							</h:panelGrid>
						</s:layoutingTitlePane>
			    </h:panelGrid>
			    
			    <c:if test="${!empty IndividuoEvaluacionBean.listaIndividuos}">
			    	<h:panelGrid columns="1" align="center" id="panelSecundario" width="900" rendered="#{!IndividuoEvaluacionBean.mostrarSolicitudes}">
					<f:verbatim>
						<display:table id="listaIndividuos" name="sessionScope.IndividuoEvaluacionBean.listaIndividuos"
							defaultsort="1" pagesize="25"
							class="table-bordered table-striped"
							requestURI="/tarjetafiel/evaluacion/listarIndividuos.jsf"
							export="${lst:contains(requestScope.permisos,'exportacion')}" requestURIcontext="true" style="width: 900px;">

								<display:column property="idIndividuo" title="Id Individuo" sortable="true" class="tdB"/>
								<display:column property="apellido" title="Apellido Paterno" sortable="true" class="tdA"/>
								<display:column property="apellidoMaterno" title="Apellido Materno" sortable="true" class="tdA"/>
								<display:column property="nombres" title="Nombres" sortable="true" class="tdA" />
								
								<display:column style="width: 25px;" media="html">
									<c:choose>
										<c:when test="${lst:contains(requestScope.permisos,'edicion')}" >
											<a href="javascript:viewUser('${listaIndividuos.idIndividuo}','idIndividuoHidden');javascript:clickLink('listarIndividuosForm:verSolicitudesIndividuoLink')">
												<img src='<%=request.getContextPath()%>/img/icon/OrderView.gif' title='Ver solicitudes asignadas' border='0' />
											</a>
										</c:when>
										<c:otherwise>
											<a href="#" onclick="return alert('No posee los permisos necesarios.');">
												<img src='<%=request.getContextPath()%>/img/icon/OrderView.gif' title='Ver solicitudes asignadas' border='0'/>
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
							    <display:setProperty name="paging.banner.item_name" value="Individuo" />
							    <display:setProperty name="paging.banner.items_name" value="Individuo" />
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
							<x:commandLink action="#{IndividuoEvaluacionBean.editarIndividuo}" id="editarIndividuoLink" style="display: none;"/>
                            <x:commandLink action="#{IndividuoEvaluacionBean.verSolicitudesIndividuo}" id="verSolicitudesIndividuoLink" style="display: none;"/>
							<%-- Paso 3. Crear un input hidden para contener el id del elemento seleccionado --%>
							<x:inputHidden id="idIndividuoHidden" forceId="true" value="#{IndividuoEvaluacionBean.idIndividuoHidden}"/>
							
						</h:panelGrid>
						</c:if>
			     
                    <h:panelGrid id="panelOcultoSolicitudes" align="center" rendered="#{IndividuoEvaluacionBean.mostrarSolicitudes}" width="900">
                    	<s:fieldset legend="Solicitudes asignadas por Individuo" id="fieldSolic">
                    		<h:panelGrid columns="2" id="panelGrid" width="center">
                    		       <h:panelGrid columns="2" id="datosIndividuo">
                    		            <h:outputText value="Apellido Paterno:" id="outApellPater" styleClass="texto"/>
										<h:outputText value="#{IndividuoEvaluacionBean.individuoEvaluacion.apellido}" id="outApellidoPater" styleClass="textoblue"/>
                                        
                                        <h:outputText value="Apellido Materno:" id="outApellMater" styleClass="texto"/>
										<h:outputText value="#{IndividuoEvaluacionBean.individuoEvaluacion.apellidoMaterno}" id="outApellidoMaterno" styleClass="textoblue"/>
										
										<h:outputText value="Nombres:" id="outNombres" styleClass="texto"/>
										<h:outputText value="#{IndividuoEvaluacionBean.individuoEvaluacion.nombres}" id="outNombresIndi" styleClass="textoblue"/>                    		       
                    		       
                    		            <h:outputText value="Cuil: " id="outCuil" styleClass="texto"/>
										<h:outputText value="#{IndividuoEvaluacionBean.individuoEvaluacion.cuil}" id="outCuitIndi" styleClass="textoblue"/>
                    		      
                    		       </h:panelGrid>
                    		       
                    		       <h:panelGrid align="center">
                    		                <c:if test="${!empty IndividuoEvaluacionBean.listDeSolicitudesPorIndividuo}">
												<h:dataTable value="#{IndividuoEvaluacionBean.listDeSolicitudesPorIndividuo}"
													id="tablaUno" styleClass="standardTable" headerClass="dataTable_Header"
													footerClass="standardTable_Header" rowClasses="standardTable_Row1,standardTable_Row2"
													columnClasses="standardTable_Column,standardTable_ColumnCentered,standardTable_Column"
													var="solicis" style=" width : 300px;">
			
													<h:column>
														<f:facet name="header">
															<h:outputText value="Numero de Solicitud" styleClass="texto"  />
														</f:facet>
														<h:outputText value="#{solicis.numeroSolicitud}"/>
													</h:column>
													
													<h:column>
													    <f:facet name="header">
													        <h:outputText value="Tipo de Individuo" styleClass="texto" />
													    </f:facet>
			                                            <h:outputText value="#{solicis.tipoIndividuo}" />
													</h:column>
													
													<h:column>
														<x:commandLink action="#{IndividuoEvaluacionBean.irAVerSolicitudDetalle}" id="mirarIndividuoLink">
											        		<f:param id="idSolHidden" name="idSolHidden" value="#{solicis.idSolicitudIndividuo}"/>
											        		<f:param id="numSolic" name="numSolic" value="#{solicis.numeroSolicitud }"/>
										            		<x:graphicImage value="/img/icon/OrderView.gif" title="Ver Solicitud" border="0" />
								            			</x:commandLink>
													</h:column>
													
												</h:dataTable>
											</c:if>
											<c:if test="${empty IndividuoEvaluacionBean.listDeSolicitudesPorIndividuo}">
											    <h:outputText value="El individuo no tiene ninguna solicitud asignada" />
											</c:if>
                    		       <h:commandButton id="botonVolver" styleClass="btn btn-primary btn-flat" value="Volver" action="#{IndividuoEvaluacionBean.verListado}"></h:commandButton>
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
    document.getElementById("sideMenu").innerHTML += generateMenu(obj,"mainMenu__idJsp2_menu:A]\#{IndividuoEvaluacionBean.irAListarIndividuos}") + `</li>`;
}

</script>   
<%@include file="/inc/scripts.jsp" %>
</body>
</html>
</f:view>
