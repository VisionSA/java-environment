<%@include file="/inc/tags.jsp"%>
<jsp:useBean id="ahora" class="java.util.Date" scope="request"/>

<f:view>
<html lang="es">
<head>
   	<title><h:outputText value="#{EscritorioBean.tituloLargo}"/></title>
    <s:script language="javascript">
    	function recargar() {
    		document.getElementById('adminTramitesForm').submit();
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

<body class="hold-transition skin-blue sidebar-mini" onbeforeunload="ShowWait('adminTramitesForm');" onload="if('${EscritorioBean.popup.mostrar}'=='true') {viewDialog.show();} else {window.scrollTo(0,${ScrollBean.hiddenScrollY});}">



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
			<h3 class="box-title">Listado de Trámites</h3>
		</div>


<center>
<h:form id="adminTramitesForm">

	<%-- GRABA EL ESTADO DEL SCROLL(Dibujar solo si el popup no se muestra) --%> 
	<h:panelGroup rendered="#{!EscritorioBean.popup.mostrar}">
		<%@include file="/inc/scroll.inc" %>   	      
	</h:panelGroup>

	<f:verbatim>
		<br>
	</f:verbatim>
	
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
				
				
				<%-- POPUP PARA LOS MENSAJES DE EXITO Y FRACASO --%>
				<s:modalDialog dialogId="viewDialog"
							   dialogVar="viewDialog"
							   styleClass="viewDialog"
			                   dialogTitle="#{EscritorioBean.tituloCorto}">
				    	<h:panelGrid columns="2" width="500">
					    	<x:graphicImage value="/img/#{EscritorioBean.popup.nombreIcono}" />
					        <h:outputText value=" #{EscritorioBean.popup.mensaje}" styleClass="texto"/>
				        </h:panelGrid>
					        
				        <h:panelGrid columns="3" width="500">
				        	<x:commandButton action="#{EscritorioBean.irANuevoTipoTarea}" 
				        		 onclick="clickLink('nuevoTipoTarea');dojo.widget.byId('viewDialog').hide();"
				        		 value="Nuevo" styleClass="btn btn-primary btn-flat pull-right" title="Agregar nueva tarea."/>

				        	<x:commandButton action="#{EscritorioBean.irAModificarTipoTarea}" 
				        		 onclick="clickLink('modificarTipoTarea');dojo.widget.byId('viewDialog').hide();"
				         		 value="Modificar" styleClass="btn btn-primary btn-flat pull-right" title="Seguir modificando la tarea."/>
							
							<x:commandButton action="#{EscritorioBean.irAListarTipoTarea}" 
								 onclick="clickLink('listarTipoTarea');dojo.widget.byId('viewDialog').hide();"
								 value="Listar" styleClass="btn btn-primary btn-flat pull-right" title="Ir al listado de tareas."/>
						</h:panelGrid>
				</s:modalDialog>
			    <x:commandLink id="nuevoTipoTarea" action="#{EscritorioBean.irANuevoTipoTarea}" forceId="true" style="display: block;"/>
				<x:commandLink id="modificarTipoTarea" action="#{EscritorioBean.irAModificarTipoTarea}" forceId="true" style="display: block;"/>
				<x:commandLink id="listarTipoTarea" action="#{EscritorioBean.irAListarTipoTarea}" forceId="true" style="display: block;"/>
		
		
		<x:panelTabbedPane id="tabbedPrincipal" serverSideTabSwitch="false" >
	        <x:panelTab id="tab1" label="Escritorio" >
				<h:panelGrid columns="4" id="panelDatos">	
					<h:outputText id="outSupervisor" value="Supervisor: " styleClass="texto"/>
					<h:selectOneMenu id="lstSupervisores" valueChangeListener="#{EscritorioBean.cambioSupervisor}" 
								value="#{EscritorioBean.supervisorSeleccionado}" 
		     					styleClass="lista" immediate="true" onfocus="encender(this);"
		     					onblur="apagar(this);" onchange="setValueId('tab1:lstProcesos','idFoco'); submit();"
		     					binding="#{EscritorioBean.supervisorHtml}" style=" width : 220px;">
		      				<f:selectItems id="itemSuper" value="#{EscritorioBean.supervisores}"/>
		  			</h:selectOneMenu>
					<f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim>
					<h:panelGroup>
			       		<h:outputText id="outNroTramite" value="Nro. Tramite: " styleClass="texto" style="padding-right:10px"/>
			     		<x:inputText id="nroTramite" value="#{EscritorioBean.nroTramite}" 
	                			 size="15" maxlength="10" styleClass="bordeceldatext" 
	                			 onchange="setValueId('tab1:btnBuscarTram','idFoco'); submit();"
	                			 style=" width : 150px;margin-top:8px" 
	                			 onfocus="encender(this);document.getElementById('adminTramitesForm:tab1:nroTramite').select();"
	                			 onblur="apagar(this);"/>
						<x:commandButton id="btnBuscarTram" action="#{EscritorioBean.buscarXnro}" 
				        		 value="Buscar por Nro." styleClass="btn btn-primary btn-flat pull-right" style="margin-left: 10px; margin-bottom:8px"
				        		 title="Busca un tramite en particular segun su numero."/>
					</h:panelGroup>

					<h:outputText id="outProceso" value="Proceso: " styleClass="texto"/>
					<h:selectOneMenu id="lstProcesos" value="#{EscritorioBean.procesoSeleccionado}" 
		     					 	styleClass="lista" immediate="true" onfocus="encender(this);"
		     					 	onchange="setValueId('tab1:Tramite','idFoco'); submit();"
		     					 	onblur="apagar(this);" style=" width : 220px;">
		      				<f:selectItems id="itemProceso" value="#{EscritorioBean.procesos}"/>
		     		</h:selectOneMenu>
					<f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim>
					<h:panelGroup>
			       		<h:outputText id="outNroCU" value="Nro. CU: " styleClass="texto" style="margin-right: 27px;"/>
			     		<x:inputText id="nroCU" value="#{EscritorioBean.nroCU}" 
	                			 size="15" maxlength="12" styleClass="bordeceldatext" 
	                			 onchange="setValueId('tab1:btnBuscarCU','idFoco'); submit();"
	                			 style=" width : 150px;" 
	                			 onfocus="encender(this);document.getElementById('adminTramitesForm:tab1:nroCU').select();"
	                			 onblur="apagar(this);"/>
						<x:commandButton id="btnBuscarCU" action="#{EscritorioBean.buscarXcu}" 
				        		 value="Buscar por Nro. CU" styleClass="btn btn-primary btn-flat pull-right" style="margin-left: 10px;"
				        		 title="Busca un tramite en particular segun su c\u00F3digo \u00FAnico."/>
					</h:panelGroup>
					<h:outputText id="outTramite" value="Parametro de inicio: " styleClass="texto" style="padding-right:10px;margin-top:4px"/>
   		            <h:inputText id="Tramite" value="#{EscritorioBean.paramInicio}" 
                			 size="255" maxlength="255" styleClass="bordeceldatext" 
                			 style=" width : 220px; margin-top:4px" onfocus="encender(this);" onblur="apagar(this);"/>
					<f:verbatim>&nbsp;</f:verbatim>
					<f:verbatim>&nbsp;</f:verbatim>
                </h:panelGrid>	

				<f:verbatim><br></f:verbatim>


				<s:fieldset legend="Rango de Fechas">
					<h:panelGrid id="filtroUno" columns="4" align="center" width="800">
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
						<h:panelGrid id="filtroFecha" columns="1" align="center" width="340" style="margin-left:20px;margin-right:10px">
							<s:fieldset legend="Fecha">
								<h:selectOneRadio value="#{EscritorioBean.selectRadioButton}" id="selectOneRadio" styleClass="radioB">
									<f:selectItem itemValue="1" itemLabel="Inicio" id="Inicio"/>
									<f:selectItem itemValue="2" itemLabel="Inicio Real" id="InicioReal"/>
									<f:selectItem itemValue="3" itemLabel="Fin" id="Fin"/>
									<f:selectItem itemValue="4" itemLabel="Fin Real" id="FinReal"/>
								</h:selectOneRadio>
							</s:fieldset>
						</h:panelGrid>
					</h:panelGrid>
				</s:fieldset>

      			
				<f:verbatim><br></f:verbatim>
			

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
					
					<f:verbatim><br></f:verbatim>
					
			<c:if test="${!empty EscritorioBean.tablaTramites}">
					<x:div style="OVERFLOW-Y: auto; OVERFLOW-X: auto; WIDTH: 875px; HEIGHT: 200px; border: 1px; margin-left: auto; margin-right: auto;">
							<x:dataTable value="#{EscritorioBean.tablaTramites}" id="tablaTramites"
										 styleClass="table-bordered table-striped"
                   		       			 headerClass="standardTable_Header"
                          				 footerClass="standardTable_Header"
                          				 sortable="true"
	                           			 rowClasses="standardTable_Row1,standardTable_Row2"
       		                   			 columnClasses="standardTable_Column,standardTable_ColumnCentered,standardTable_Column"							             
							             var="tablaTramite" style=" width : 855px;">
					    		
								<x:column>
    		                        <f:facet name="header">
            		                    <h:outputText value="Nro." styleClass="texto"/>
                    		        </f:facet>
	                                <h:outputText value="#{tablaTramite.tramite.idTramite}" styleClass="texto" />
    		                    </x:column>
								
		                        <x:column>
    		                        <f:facet name="header">
            		                    <h:outputText value="Proceso" styleClass="texto"/>
                    		        </f:facet>
	                                <h:outputText value="#{tablaTramite.tramite.proceso.titulo}" styleClass="texto" />
    		                    </x:column>
	                        
		                        <x:column>
    		                        <f:facet name="header">
            		                    <h:outputText value="Tramite" styleClass="texto"/>
                    		        </f:facet>
	                                <h:outputText value="#{tablaTramite.nombreTramite}" styleClass="texto" />
    		                    </x:column>
		                        <%-- 
		                        <x:column>
    		                        <f:facet name="header">
            		                    <h:outputText value="Progreso" styleClass="texto" style="font: bold;color: white;"/>
                    		        </f:facet>
	                                <h:outputText value="" styleClass="texto" />
    		                    </x:column>
		                        --%>
            		            <x:column>
                    		        <f:facet name="header">
		                                <h:outputText value="Estado" styleClass="texto"/>
    		                        </f:facet>
               		                <h:outputText value="#{tablaTramite.tramite.estado.descripcion}" styleClass="texto" />
                    		    </x:column>
								
            		            <x:column>
                    		        <f:facet name="header">
		                                <h:outputText value="Inicio" styleClass="texto"/>
    		                        </f:facet>
               		                <h:outputText value="#{tablaTramite.tramite.fechaInicio}" styleClass="texto" />
                    		    </x:column>
								
            		            <x:column>
                    		        <f:facet name="header">
		                                <h:outputText value="Inicio Real" styleClass="texto"/>
    		                        </f:facet>
               		                <h:outputText value="#{tablaTramite.tramite.fechaInicioReal}" styleClass="texto" />
                    		    </x:column>
								
            		            <x:column>
                    		        <f:facet name="header">
		                                <h:outputText value="Fin" styleClass="texto"/>
    		                        </f:facet>
               		                <h:outputText value="#{tablaTramite.tramite.fechaFin}" styleClass="texto" />
                    		    </x:column>
								
            		            <x:column>
                    		        <f:facet name="header">
		                                <h:outputText value="Fin Real" styleClass="texto"/>
    		                        </f:facet>
               		                <h:outputText value="#{tablaTramite.tramite.fechaFinReal}" styleClass="texto" />
                    		    </x:column>
								
								<x:column>
									<x:commandLink id="verTareasLink" action="#{EscritorioBean.mostrarTareas}">
										<f:param id="idTramiteV" name="idTramite" value="#{tablaTramite.tramite.idTramite}"/>
										<x:graphicImage value="/img/icon/OrderView.gif" title="Mostrar tareas." border="0"/>
									</x:commandLink>

									<x:commandLink action="#{EscritorioBean.mostrarTramiteAdmin}" id="verTramiteLink">
										<f:param id="idTramiteM" name="idTramite" value="#{tablaTramite.tramite.idTramite}"/>
										<x:graphicImage value="/img/icon/editar.gif" title="Editar tramite." border="0" />
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
						
						<f:verbatim>
						<br><br>
						</f:verbatim>
						
					<c:if test="${!empty EscritorioBean.tablaDetalleTareas}">
						<x:div style="OVERFLOW-Y: auto; OVERFLOW-X: auto; WIDTH: 875px; HEIGHT: 200px; border: 1px; margin-left: auto; margin-right: auto;">
							<x:dataTable value="#{EscritorioBean.tablaDetalleTareas}" id="tablaDetalleTareas"
                   		       			 headerClass="standardTable_Header" styleClass="standardTable"
                       					 footerClass="standardTable_Header"
                       					 sortable="true"
	                           			 rowClasses="standardTable_Row1,standardTable_Row2"
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
            		                    <h:outputText value="Tarea" styleClass="texto"/>
                    		        </f:facet>
	                                <h:outputText value="#{tablaTarea.tarea.titulo}" styleClass="texto" />
    		                    </x:column>
	                        	
	                        	<x:column>
    		                        <f:facet name="header">
            		                    <h:outputText value="Tramite" styleClass="texto"/>
                    		        </f:facet>
	                                <h:outputText value="#{tablaTarea.nombreTramite}" styleClass="texto" />
    		                    </x:column>
	                        	
	                        	<x:column>
    		                        <f:facet name="header">
            		                    <h:outputText value="Duración" styleClass="texto"/>
                    		        </f:facet>
	                                <h:outputText value="#{tablaTarea.tarea.duracion}" styleClass="texto" />
    		                    </x:column>
	                        	
		                        <x:column>
    		                        <f:facet name="header">
            		                    <h:outputText value="Progreso" styleClass="texto"/>
                    		        </f:facet>
	                                <h:outputText value="#{tablaTarea.detalleTramite.progreso}" styleClass="texto" />
    		                    </x:column>
                        		
            		            <x:column>
                    		        <f:facet name="header">
		                                <h:outputText value="Estado" styleClass="texto"/>
    		                        </f:facet>
               		                <h:outputText value="#{tablaTarea.detalleTramite.estado.descripcion}" styleClass="texto" />
                    		    </x:column>
								
            		            <x:column>
                    		        <f:facet name="header">
		                                <h:outputText value="Operador Responsable" styleClass="texto"/>
    		                        </f:facet>
               		                <h:outputText value="#{tablaTarea.detalleTramite.operadorResponsable.label}" styleClass="texto" />
                    		    </x:column>
								
								<x:column>
									<x:commandLink id="verTareaLink" action="#{EscritorioBean.verTareaAdmin}">
										<f:param id="idDetalle" name="idDetalle" value="#{tablaTarea.detalleTramite.idDetalleTramite}"/>
										<x:graphicImage value="/img/icon/editar.gif" title="Ver Tarea." border="0"/>
									</x:commandLink>
								</x:column>

							</x:dataTable>
						</x:div>
					</c:if>
			</c:if>
					</h:panelGrid>

			</x:panelTab>
			
			<x:panelTab id="tab2" label="Tarea" disabled="true">
			
		    </x:panelTab>
		</x:panelTabbedPane>
		<x:inputHidden id="idFoco" value="#{EscritorioBean.focoHidden}" forceId="true"/>
		<s:focus id="foco" for="#{EscritorioBean.focoHidden}" />
			</h:panelGrid>
           </h:panelGroup>
        </f:facet>

    </x:panelLayout>


    <h:inputText id="FechaDesde" value="#{EscritorioBean.fechaDesde}" style="display: none;">
        <f:convertDateTime type="date" pattern = "dd/MM/yyyy"/>
    </h:inputText>
    <h:inputText id="FechaHasta" value="#{EscritorioBean.fechaHasta}" style="display: none;">
        <f:convertDateTime type="date" pattern = "dd/MM/yyyy"/>
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
    document.getElementById("sideMenu").innerHTML += generateMenu(obj,"mainMenu__idJsp2_menu:A]\#{EscritorioBean.inicializarAdmin}") + `</li>`;
}

</script>

<%@include file="/inc/scripts.jsp" %>

<script type="text/javascript">
$(function () {

    //Inicializo datepicker
    $("#fDesde").datepicker({
      autoclose: true,
    });

    $("#fHasta").datepicker({
      autoclose: true,
    });


	//Seteo fehcas que trae el bean por defecto, en el datepicker
	var fd = document.getElementById("adminTramitesForm:FechaDesde").value.split(" ")[0].split("/");
	var year = fd[2];
	var month = fd[1];
	month--;
	var date = fd[0];
	var fAux = new Date(year,month,date);
	$("#fDesde").datepicker("setDate", fAux);
    document.getElementById("adminTramitesForm:FechaDesde").value = $.datepicker.formatDate('dd/mm/yy', fAux) + " 03:00";
	
	fd = document.getElementById("adminTramitesForm:FechaHasta").value.split(" ")[0].split("/");
	year = fd[2];
	month = fd[1];
	month--;
	date = fd[0];
	fAux = new Date(year,month,date);
	$("#fHasta").datepicker("setDate", fAux);
	document.getElementById("adminTramitesForm:FechaHasta").value = $.datepicker.formatDate('dd/mm/yy', fAux) + " 03:00";
	

    //Muevo fechas de datepicker a input q pasa datos al bean
    $("#fDesde").change(function() {
        var fd = $(this).datepicker("getDate");
        document.getElementById("adminTramitesForm:FechaDesde").value = $.datepicker.formatDate('dd/mm/yy', fd) + " 03:00";
    });

    $("#fHasta").change(function() {
        var fh = $(this).datepicker("getDate");
		document.getElementById("adminTramitesForm:FechaHasta").value = $.datepicker.formatDate('dd/mm/yy', fh) + " 03:00";
    });



  });
</script>


</body>
</html>
</f:view>
