<%@include file="/inc/tags.jsp" %>
<jsp:useBean id="ahora" class="java.util.Date" scope="request"/>

<f:view>
<html lang="es">
<head>
   	<title><h:outputText value="#{EscritorioBean.tituloLargo}"/></title>
   	<meta http-equiv="Content-Type" content="text/html" charset=utf-8" />
    <s:script language="javascript">
    	function recargar() {
    		document.getElementById('escritorioForm').submit();
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

<jsp:include page="/inc/includes.jsp" />


<body class="hold-transition skin-blue sidebar-mini" onbeforeunload="ShowWait('escritorioForm');" onload="if('${EscritorioBean.popup.mostrar}'=='true') {viewDialog.show();} else {window.scrollTo(0,${ScrollBean.hiddenScrollY});}">


<h:form id="mainMenu" style="display: none">
	<jsp:include page="/inc/navigation_test.jsp" />
	<x:commandLink id="cerrarSesion" action="#{LoginBean.salir}"/>
</h:form>

<jsp:include page="/inc/header.jsp" />

<!-- Content Header (Page header) -->
<section class="content-header">
  <h1>
    ${EscritorioBean.tituloCorto}
  </h1>
</section>

<section class="content">
	<div class="box box-default">
		<div class="box-header with-border">
			<h3 class="box-title">Administración de Trámites</h3>
		</div>


<center>
	
<h:form id="escritorioForm">

	<%-- GRABA EL ESTADO DEL SCROLL(Dibujar solo si el popup no se muestra) --%> 
	<h:panelGroup rendered="#{!EscritorioBean.popup.mostrar}">
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
            	
            	
            	<h:panelGrid columns="1" align="center" id="PanelPricipalEscritorio">
				<%-- INCLUDE PARA LOS ERRORES --%> 	
				<h:panelGroup id="errores">
					<jsp:include page="/inc/error.jsp" />
				</h:panelGroup>

		<h:panelGrid columns="1" align="right">
			<x:commandLink id="backLink" action="#{EscritorioBean.volver}" styleClass="align:right">
				<f:verbatim>
					<i title="Refrescar escritorio" border="0" class="fa fa-2x fa-refresh" style="margin-top:10px;margin-bottom:10px"></i>
				</f:verbatim>
			</x:commandLink>
		</h:panelGrid>
		<x:panelTabbedPane id="tabbedPrincipal" serverSideTabSwitch="false" >
	        <x:panelTab id="tab1" label="Escritorio" >
             		<h:panelGrid id="panelPrincipalUno" columns="2" align="top" columnClasses="vertical-align:top">
						<x:inputCalendar monthYearRowClass="yearMonthHeader" weekRowClass="weekHeader" rendered="false"
									currentDayCellClass="currentDayCell" value="#{EscritorioBean.fechaSelec}"/>
						<h:panelGroup>
						<f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim>
						<s:layoutingTitlePane id="listadoAvisos" label=" Avisos" 
									containerNodeClass="contentTitlePane" labelNodeClass="labelTitlePane; aling='top'" >
							<x:dataTable id="avisosList" 
											var="aviso"
											value=""
											width="400" style=" width : 663px;">
									<h:column >
										<h:outputText value="#{aviso}" style="font-size: 12px;" styleClass="text-blue"/>
						    		</h:column>	
							</x:dataTable>
						</s:layoutingTitlePane>
						</h:panelGroup>
						
					</h:panelGrid>
						
				<x:panelTabbedPane id="tabbedTablas" serverSideTabSwitch="false" selectedIndex="#{EscritorioBean.idTab}">
					<c:if test="${!empty EscritorioBean.tablaTareasPendientes}">
						<x:panelTab id="tabPendientes" label="Tareas Pendientes" >
							<s:layoutingTitlePane id="listadoTareasPendientes" label="Listado de tareas pendientes" 
										      containerNodeClass="box-body no-padding" labelNodeClass="box-header" >
									<h:panelGrid columns="1" id="panelTareasPendientes">
									<x:div style="OVERFLOW-Y: auto; OVERFLOW-X: auto; WIDTH: 880px; HEIGHT: 370px; border: 1px; margin-left: auto; margin-right: auto;">
									<x:dataTable value="#{EscritorioBean.tablaTareasPendientes}" id="tablaTareasPendientes"
												 styleClass="table table-bordered table-striped"
	                    		       			 headerClass="standardTable_Header"
	                           					 footerClass="standardTable_Header"
			                           			 rowClasses="standardTable_Row1,standardTable_Row2"
			                           			 sortable="true"
	        		                   			 columnClasses="standardTable_Column,standardTable_ColumnCentered,standardTable_Column"							             
									             var="tablaTPendiente" style=" width : 855px;">
				                        <x:column>
		    		                        <f:facet name="header">
		            		                    <h:outputText value="Tarea" styleClass="texto"/>
		                    		        </f:facet>
			                                <h:outputText value="#{tablaTPendiente.tarea.titulo}" styleClass="texto" />
		    		                    </x:column>
				                        <x:column>
		    		                        <f:facet name="header">
		            		                    <h:outputText value="Proceso" styleClass="texto"/>
		                    		        </f:facet>
			                                <h:outputText value="#{tablaTPendiente.detalleTramite.tramite.proceso.titulo}" styleClass="texto" />
		    		                    </x:column>
				                        <x:column>
		    		                        <f:facet name="header">
		            		                    <h:outputText value="Tramite" styleClass="texto"/>
		                    		        </f:facet>
			                                <h:outputText value="#{tablaTPendiente.nombreTramite}" styleClass="texto" />
		    		                    </x:column>
		    		                     <x:column>
		    		                        <f:facet name="header">
		            		                    <h:outputText value="Operador" styleClass="texto"/>
		                    		        </f:facet>
			                                <h:outputText value="#{tablaTPendiente.operadorInciaTramite}" styleClass="texto" />
		    		                    </x:column>
		            		            <x:column>
		                    		        <f:facet name="header">
				                                <h:outputText value="Fecha Creación" styleClass="texto"/>
		    		                        </f:facet>
	                		                <h:outputText value="#{tablaTPendiente.detalleTramite.fechaInicio}" styleClass="texto" />
		                    		    </x:column>
		            		    <%--    <x:column>
		                    		        <f:facet name="header">
				                                <h:outputText value="Comentario" styleClass="texto" />
		    		                        </f:facet>
	                		                <h:outputText value="#{tablaTPendiente.detalleTramite.comentario}" styleClass="texto" />
		                    		    </x:column>  --%>
										<x:column>
											<x:commandLink id="tomarTareaLink" action="#{EscritorioBean.tomarTarea}">
												<f:param id="idDetalle" name="idDetalle" value="#{tablaTPendiente.detalleTramite.idDetalleTramite}"/>
												<x:graphicImage value="/img/icon/tomar.gif" title="Iniciar la tarea." border="0"/>
											</x:commandLink>
										</x:column>
									</x:dataTable>
									</x:div>
									</h:panelGrid>
								</s:layoutingTitlePane>
							</x:panelTab>
						</c:if>
					
					<c:if test="${!empty EscritorioBean.tablaTareasTomadas}">
						<x:panelTab id="tabTomadas" label="Tareas Tomadas" >
							<x:div style="OVERFLOW-Y: auto; OVERFLOW-X: auto; WIDTH: 880px; HEIGHT: 370px; border: 1px; margin-left: auto; margin-right: auto;">
									<x:dataTable value="#{EscritorioBean.tablaTareasTomadas}" id="tablaTareasTomadas"
												 styleClass="table table-bordered table-striped"
	                    		       			 headerClass="standardTable_Header"
	                           					 footerClass="standardTable_Header"
			                           			 rowClasses="standardTable_Row1,standardTable_Row2"
			                           			 sortable="true"
	        		                   			 columnClasses="standardTable_Column,standardTable_ColumnCentered,standardTable_Column"							             
									             var="tablaTarea" style=" width : 855px;">
							    
				                        <x:column>
		    		                        <f:facet name="header">
		            		                    <h:outputText value="Inició" styleClass="texto"/>
		                    		        </f:facet>
			                                <h:outputText value="#{tablaTarea.detalleTramite.fechaInicioReal}" styleClass="texto" />
		    		                    </x:column>
			                        	
			                        	<x:column>
		    		                        <f:facet name="header">
		            		                    <h:outputText value="Tarea" styleClass="texto" />
		                    		        </f:facet>
			                                <h:outputText value="#{tablaTarea.tarea.titulo}" styleClass="texto" />
		    		                    </x:column>
			                        	
										<x:column>
		    		                        <f:facet name="header">
		            		                    <h:outputText value="Proceso" styleClass="texto" />
		                    		        </f:facet>
			                                <h:outputText value="#{tablaTarea.detalleTramite.tramite.proceso.titulo}" styleClass="texto" />
		    		                    </x:column>

			                        	<x:column>
		    		                        <f:facet name="header">
		            		                    <h:outputText value="Tramite" styleClass="texto" />
		                    		        </f:facet>
			                                <h:outputText value="#{tablaTarea.nombreTramite}" styleClass="texto" />
		    		                    </x:column>
			                        <%-- 			                        	
				                        <x:column>
		    		                        <f:facet name="header">
		            		                    <h:outputText value="Progreso" styleClass="texto" />
		                    		        </f:facet>
			                                <h:outputText value="#{tablaTarea.detalleTramite.progreso}" styleClass="texto" />
		    		                    </x:column>
		                        		--%>
		            		            <x:column>
		                    		        <f:facet name="header">
				                                <h:outputText value="Estado" styleClass="texto" />
		    		                        </f:facet>
	                		                <h:outputText value="#{tablaTarea.detalleTramite.estado.descripcion}" styleClass="texto" />
		                    		    </x:column>
										
		            		            <x:column>
		                    		        <f:facet name="header">
				                                <h:outputText value="Supervisor" styleClass="texto" />
		    		                        </f:facet>
	                		                <h:outputText value="#{tablaTarea.detalleTramite.tramite.operadorSup.apellido}" styleClass="texto" />
		                    		    </x:column>
										
										<x:column>
											<x:commandLink id="verTareaLink" action="#{EscritorioBean.verTarea}">
												<f:param id="idDetalle" name="idDetalle" value="#{tablaTarea.detalleTramite.idDetalleTramite}"/>
												<x:graphicImage value="/img/icon/editar.gif" title="Ver Tarea." border="0"/>
											</x:commandLink>
										</x:column>
	
									</x:dataTable>
							</x:div>
<%--								<s:filterTable id="filterTbl" 
										styleClass="standardTable" tbodyClass="standardTable"
                   		       			headClass="standardTable_Header"
										var="tablaTarea" value="#{EscritorioBean.tablaTareasTomadas}"  >
								        <s:sortableColumn field="id" text="Titulo">
								            <h:outputText value="#{tablaTarea.tarea.titulo}" />
								        </s:sortableColumn>
								        <s:sortableColumn field="manufacturer" text="nombreTramite">
								            <h:outputText value="#{tablaTarea.nombreTramite}" />
								        </s:sortableColumn>
								        <s:sortableColumn field="model" text="estado">
								            <h:outputText value="#{tablaTarea.detalleTramite.estado.descripcion}" />
								        </s:sortableColumn>
								        <s:sortableColumn field="linkl" text="">
								        <x:commandLink id="verTareaLink1" action="#{EscritorioBean.verTarea}">
											<f:param id="idDetalle1" name="idDetalle" value="#{tablaTarea.detalleTramite.idDetalleTramite}"/>
											<x:graphicImage value="/img/cat_pad.gif" title="Ver Tarea." border="0"/>
										</x:commandLink>
										</s:sortableColumn>
								    </s:filterTable>  --%>
						</x:panelTab>
					</c:if>
					<c:if test="${!empty EscritorioBean.tablaTramites || EscritorioBean.verLeyendaTramite==true}">
			        <x:panelTab id="tabSuper" label="Tramites Supervisados" >
						<h:panelGrid id="panelTramites" columns="1" >
							<h:panelGroup>
								<x:commandLink id="verNuevosLink" action="#{EscritorioBean.buscarTramites}" value="Nuevos" >
									<f:param id="idEstadoN" name="idEstado" value="1"/>
									<x:graphicImage value="/img/icon/status_new.gif" title="Ver Nuevos." border="0" style="margin:5px"/>
								</x:commandLink>
								<f:verbatim>&nbsp;&nbsp;&nbsp;</f:verbatim>
								<x:commandLink id="verIniciadosLink" action="#{EscritorioBean.buscarTramites}" value="Iniciados" >
									<f:param id="idEstadoI" name="idEstado" value="2"/>
									<x:graphicImage value="/img/icon/status_new.gif" title="Ver Iniciados." border="0" style="margin:5px"/>
								</x:commandLink>
								<f:verbatim>&nbsp;&nbsp;&nbsp;</f:verbatim>
								<x:commandLink id="verEnProcesoLink" action="#{EscritorioBean.buscarTramites}" value="En Proceso" >
									<f:param id="idEstadoP" name="idEstado" value="3"/>
									<x:graphicImage value="/img/icon/status_in_progress.gif" title="Ver En Proceso." border="0" style="margin:5px"/>
								</x:commandLink>
								<f:verbatim>&nbsp;&nbsp;&nbsp;</f:verbatim>
								<x:commandLink id="verCanceladosLink" action="#{EscritorioBean.buscarTramites}" value="Cancelados" >
									<f:param id="idEstadoC" name="idEstado" value="4"/>
									<x:graphicImage value="/img/icon/status_stopped.gif" title="Ver Cancelados." border="0" style="margin:5px"/>
								</x:commandLink>
								<f:verbatim>&nbsp;&nbsp;&nbsp;</f:verbatim>
								<x:commandLink id="verFinalizadosLink" action="#{EscritorioBean.buscarTramites}" value="Finalizados" >
									<f:param id="idEstadoF" name="idEstado" value="6"/>
									<x:graphicImage value="/img/icon/status_finished.gif" title="Ver Finalizados." border="0" style="margin:5px"/>
								</x:commandLink>
								<f:verbatim>&nbsp;&nbsp;&nbsp;</f:verbatim>
								<x:commandLink id="verTodosLink" action="#{EscritorioBean.buscarTramites}" value="Todos">
									<f:param id="idEstadoT" name="idEstado" value="0"/>
								</x:commandLink>
							</h:panelGroup>
							
							<c:if test="${EscritorioBean.verLeyendaTramite!=false}">
								<h:outputText id="listVacia" value="Lista vacia" styleClass="texto" />
							</c:if>
							<c:if test="${EscritorioBean.verLeyendaTramite!=true}">
							<x:div id="divTramite" style="OVERFLOW-Y: auto; OVERFLOW-X: auto; WIDTH: 875px; HEIGHT: 370px; border: 1px; margin-left: auto; margin-right: auto;">
									<x:dataTable value="#{EscritorioBean.tablaTramites}" id="tablaTramites"
												 styleClass="table table-bordered table-striped"
	                    		       			 headerClass="standardTable_Header"
	                           					 footerClass="standardTable_Header"
			                           			 rowClasses="standardTable_Row1,standardTable_Row2"
			                           			 sortable="true"
	        		                   			 columnClasses="standardTable_Column,standardTable_ColumnCentered,standardTable_Column"							             
									             var="tablaTramite" style=" width : 855px;">
							    
				                        <x:column>
		    		                        <f:facet name="header">
		            		                    <h:outputText value="Nro." styleClass="texto" />
		                    		        </f:facet>
			                                <h:outputText value="#{tablaTramite.tramite.idTramite}" styleClass="texto" />
		    		                    </x:column>
				                        
				                        <x:column>
		    		                        <f:facet name="header">
		            		                    <h:outputText value="Proceso" styleClass="texto" />
		                    		        </f:facet>
			                                <h:outputText id="outputProceso" value="#{tablaTramite.tramite.proceso.titulo}" styleClass="texto" />
		    		                    </x:column>
			                        
				                        <x:column>
		    		                        <f:facet name="header">
		            		                    <h:outputText value="Tramite" styleClass="texto" />
		                    		        </f:facet>
			                                <h:outputText id="outputTramite" value="#{tablaTramite.nombreTramite}" styleClass="texto" />
		    		                    </x:column>
				                        <%-- 
				                        <x:column>
		    		                        <f:facet name="header">
		            		                    <h:outputText value="Progreso" styleClass="texto" />
		                    		        </f:facet>
			                                <h:outputText id="outputProgreso" value="" styleClass="texto" />
		    		                    </x:column>
								        --%>
		            		            <x:column>
		                    		        <f:facet name="header">
				                                <h:outputText value="Estado" styleClass="texto" />
		    		                        </f:facet>
	                		                <h:outputText id="outputEstado" value="#{tablaTramite.tramite.estado.descripcion}" styleClass="texto" />
		                    		    </x:column>
										
		            		            <x:column>
		                    		        <f:facet name="header">
				                                <h:outputText value="Inicio" styleClass="texto" />
		    		                        </f:facet>
	                		                <h:outputText id="outputIni" value="#{tablaTramite.tramite.fechaInicio}" styleClass="texto" />
		                    		    </x:column>
										
		            		            <x:column>
		                    		        <f:facet name="header">
				                                <h:outputText value="Inicio Real" styleClass="texto" />
		    		                        </f:facet>
	                		                <h:outputText id="outputIniReal" value="#{tablaTramite.tramite.fechaInicioReal}" styleClass="texto" />
		                    		    </x:column>
										
		            		            <x:column>
		                    		        <f:facet name="header">
				                                <h:outputText value="Fin" styleClass="texto" />
		    		                        </f:facet>
	                		                <h:outputText id="outputFin" value="#{tablaTramite.tramite.fechaFin}" styleClass="texto" />
		                    		    </x:column>
										
		            		            <x:column>
		                    		        <f:facet name="header">
				                                <h:outputText value="Fin Real" styleClass="texto" />
		    		                        </f:facet>
	                		                <h:outputText id="outputFinReal" value="#{tablaTramite.tramite.fechaFinReal}" styleClass="texto" />
		                    		    </x:column>
										
										<x:column>
											<x:commandLink id="verTareasLink" action="#{EscritorioBean.mostrarTareas}">
												<f:param id="idTramiteV" name="idTramite" value="#{tablaTramite.tramite.idTramite}"/>
												<x:graphicImage value="/img/icon/OrderView.gif" title="Mostrar tareas." border="0"/>
											</x:commandLink>

											<x:commandLink action="#{EscritorioBean.mostrarTramite}" id="verTramiteLink">
												<f:param id="idTramiteM" name="idTramite" value="#{tablaTramite.tramite.idTramite}"/>
												<x:graphicImage value="/img/icon/editar.gif" title="Historico del tramite." border="0" />
											</x:commandLink>
										
											<x:commandLink action="#{EscritorioBean.cancelarTramite}" id="cancelarTramiteLink" >
												<f:param id="idTramiteC" name="idTramite" value="#{tablaTramite.tramite.idTramite}"/>
												<x:graphicImage value="/img/borrar.gif" title="Cancelar el tramite." border="0" 
												rendered="#{tablaTramite.verCancelarTramite}"
												onclick="return confirm('¿Confirma la cancelacion del tramite?');" />
											</x:commandLink>
										</x:column>
									</x:dataTable>
								</x:div>
								</c:if>
								<%-- 
								<f:verbatim>
								<br>
								</f:verbatim> --%>
								
							
							<c:if test="${!empty EscritorioBean.tablaDetalleTareas}">
								<h:panelGroup>
									<h:outputText value="Tramite: " styleClass="texto"/>
									<h:outputText value="#{EscritorioBean.tituloTramite}" styleClass="text-blue" style="FONT-WEIGHT: bold;"/>
								</h:panelGroup>
								<x:div id="divDetTarea" style="OVERFLOW-Y: auto; OVERFLOW-X: auto; WIDTH: 875px; HEIGHT: 250px; border: 1px; margin-left: auto; margin-right: auto;">
									<x:dataTable value="#{EscritorioBean.tablaDetalleTareas}" id="tablaDetalleTareas"
	                    		       			 headerClass="standardTable_Header" styleClass="table table-bordered table-striped"
	                           					 footerClass="standardTable_Header"
			                           			 rowClasses="standardTable_Row1,standardTable_Row2"
			                           			 sortable="true"
	        		                   			 columnClasses="standardTable_Column,standardTable_ColumnCentered,standardTable_Column"							             
									             var="tablaTarea" style=" width : 855px;">
							    
				                        <x:column>
		    		                        <f:facet name="header">
		            		                    <h:outputText value="Inició" styleClass="texto" />
		                    		        </f:facet>
			                                <h:outputText value="#{tablaTarea.detalleTramite.fechaInicioReal}" styleClass="texto" />
		    		                    </x:column>
			                        	
			                        	<x:column>
		    		                        <f:facet name="header">
		            		                    <h:outputText value="Tarea" styleClass="texto" style="font: bold;color: black;"/>
		                    		        </f:facet>
			                                <h:outputText value="#{tablaTarea.tarea.titulo}" styleClass="texto" />
		    		                    </x:column>
			                    <%--     	
			                        	<x:column>
		    		                        <f:facet name="header">
		            		                    <h:outputText value="Tramite" styleClass="texto" />
		                    		        </f:facet>
			                                <h:outputText value="#{tablaTarea.nombreTramite}" styleClass="texto" />
		    		                    </x:column>
			                    --%>    	
			                        	<x:column>
		    		                        <f:facet name="header">
		            		                    <h:outputText value="Duración" styleClass="texto" />
		                    		        </f:facet>
			                                <h:outputText value="#{tablaTarea.tarea.duracion}" styleClass="texto" />
		    		                    </x:column>
			                        	
				                        <x:column>
		    		                        <f:facet name="header">
		            		                    <h:outputText value="Progreso" styleClass="texto" />
		                    		        </f:facet>
			                                <h:outputText value="#{tablaTarea.detalleTramite.progreso}" styleClass="texto" />
		    		                    </x:column>
		                        		
		            		            <x:column>
		                    		        <f:facet name="header">
				                                <h:outputText value="Estado" styleClass="texto" />
		    		                        </f:facet>
	                		                <h:outputText value="#{tablaTarea.detalleTramite.estado.descripcion}" styleClass="texto" />
		                    		    </x:column>
										
		            		            <x:column>
		                    		        <f:facet name="header">
				                                <h:outputText value="Operador Responsable" styleClass="texto" />
		    		                        </f:facet>
	                		                <h:outputText value="#{tablaTarea.detalleTramite.operadorResponsable.label}" styleClass="texto" />
		                    		    </x:column>
										
										<x:column>
											<x:commandLink id="verTareaLink" action="#{EscritorioBean.verTareaSuper}">
												<f:param id="idDetalle" name="idDetalle" value="#{tablaTarea.detalleTramite.idDetalleTramite}"/>
												<x:graphicImage value="/img/icon/editar.gif" title="Ver Tarea." border="0"/>
											</x:commandLink>
										</x:column>
	
									</x:dataTable>
								</x:div>
							</c:if>
							</h:panelGrid>
						</x:panelTab>
					</c:if>
				</x:panelTabbedPane>
			</x:panelTab>
			
			<x:panelTab id="tab2" label="Tarea" disabled="true">
			
		    </x:panelTab>
		</x:panelTabbedPane>
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
    document.getElementById("sideMenu").innerHTML += generateMenu(obj,"mainMenu__idJsp2_menu:A]\#{EscritorioBean.inicializar}") + `</li>`;
}

</script>

<%@include file="/inc/scripts.jsp" %>

</body>
</html>
</f:view>
