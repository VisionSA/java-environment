<%@include file="/inc/tags.jsp" %>
<jsp:useBean id="ahora" class="java.util.Date" scope="request"/>

<f:view>
<html lang="es">
<head>
   	<title><h:outputText value="#{FormularioBean.tituloLargo}"/></title>
    <s:script language="javascript">
    	function recargar() {
    		document.getElementById('amFormTareaForm').submit();
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

<jsp:include page="/inc/includes.jsp"/>

<body class="hold-transition skin-blue sidebar-mini" onbeforeunload="ShowWait('amFormTareaForm');" onload="if('${FormularioBean.popup.mostrar}'=='true') {viewDialog.show();} else {window.scrollTo(0,${ScrollBean.hiddenScrollY});} ; ${FormularioBean.popupReport}">

<h:form id="mainMenu" style="display: none">
	<jsp:include page="/inc/navigation_test.jsp" />
	<x:commandLink id="cerrarSesion" action="#{LoginBean.salir}"/>
</h:form>

<jsp:include page="/inc/header.jsp" />

<!-- Content Header (Page header) -->
<section class="content-header">
  <h1>
    ${FormularioBean.tituloCorto}
  </h1>
</section>

<section class="content">
	<div class="box box-default">
		<div class="box-header with-border">
			<h3 class="box-title">Administración de Trámites</h3>
		</div>

<center>
<h:form id="amFormTareaForm">

	<%-- GRABA EL ESTADO DEL SCROLL(Dibujar solo si el popup no se muestra) --%> 
	<h:panelGroup rendered="#{!FormularioBean.popup.mostrar}">
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
            	            	
            	<h:panelGrid columns="1" align="center" id="PanelPricipalFormTarea">
				<%-- INCLUDE PARA LOS ERRORES --%> 	
				<h:panelGroup id="errores">
					<jsp:include page="/inc/error.jsp" />
				</h:panelGroup>
				
				
				<%-- POPUP PARA LOS MENSAJES DE EXITO Y FRACASO --%>
				<s:modalDialog dialogId="viewDialog"
							   dialogVar="viewDialog"
							   styleClass="viewDialog"
			                   dialogTitle="#{FormularioBean.tituloCorto}">
				    	<h:panelGrid columns="2" width="500">
					    	<x:graphicImage value="/img/#{FormularioBean.popup.nombreIcono}" />
					        <h:outputText value=" #{FormularioBean.popup.mensaje}" styleClass="texto"/>
				        </h:panelGrid>
					        
				        <h:panelGrid columns="3" width="500">
				        	<f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim>
				        	<x:commandButton action="#{FormularioBean.irANuevoProceso}" rendered="false"
				        		 onclick="clickLink('nuevoProc');dojo.widget.byId('viewDialog').hide();"
				        		 value="Nuevo" styleClass="botones" title="Agregar nuevo proceso."/>

				        	<x:commandButton action="#{FormularioBean.irAEscritorioRefresc}" 
				        		 onclick="clickLink('escritorio');dojo.widget.byId('viewDialog').hide();"
				         		 value="Aceptar" styleClass="botones" title="Seguir modificando el proceso."/>
							
							<f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim>
							<x:commandButton action="#{FormularioBean.irAListarProceso}" rendered="false"
								 onclick="clickLink('listarProc');dojo.widget.byId('viewDialog').hide();"
								 value="Listar" styleClass="botones" title="Ir al listado de procesos."/>
						</h:panelGrid>
				</s:modalDialog>
			    <x:commandLink id="nuevoProc" action="#{FormularioBean.irANuevoProceso}" forceId="true" style="display: block;"/>
				<x:commandLink id="escritorio" action="#{FormularioBean.irAEscritorio}" forceId="true" style="display: block;"/>
				<x:commandLink id="listarProc" action="#{FormularioBean.irAListarProceso}" forceId="true" style="display: block;"/>
		
		<x:panelTabbedPane id="tabbedPrincipal" serverSideTabSwitch="false" selectedIndex="1">
			<x:panelTab id="tab1" label="Escritorio" disabled="true">
			
		    </x:panelTab>
		
	        <x:panelTab id="tab2" label="Tarea">
				<h:panelGrid columns="2" align="right" style="margin:10px">
				
					<x:commandLink id="backLink" action="#{FormularioBean.cancelar}" styleClass="align:right" style="margin-right:10px;margin-top:8px">
						<f:verbatim>						
							<i title="Volver." border="0" class="fa fa-2x fa-arrow-left" style="color:lightseagreen;margin-right:5px"></i>
						</f:verbatim>
					</x:commandLink>
					<x:commandLink id="saveLink" action="#{FormularioBean.grabar}" styleClass="align:right">
						<f:verbatim>
							<i title="Graba la Tarea." border="0" align="right" class="fa fa-2x fa-save"></i>
						</f:verbatim>
					</x:commandLink>
				</h:panelGrid>
				
				<f:verbatim><br/><br/><br/></f:verbatim>

				<h:panelGrid styleClass="box box-primary" style="background:#fcfcfc" columns="1">
					
				<s:layoutingTitlePane id="panelFormTramite"
								label=" Tramite "
								containerNodeClass="contentTitlePane"
								labelNodeClass="labelTitlePane">
								<h:panelGrid columns="8">
									
									<f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim>

									<h:panelGroup>
										<h:outputText id="outProceso" value="Proceso: " styleClass="text"/>
										<h:outputText id="outTextProceso" styleClass="text-blue"
											value="#{FormularioBean.tramite.proceso.titulo}"/>
									</h:panelGroup>
									
									<f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim>
									
									<h:panelGroup>
										<h:outputText id="outputTramite" value="Trámite: "
											styleClass="text" />
										<h:outputText id="outTramite"
											value=" #{FormularioBean.nombreTramite}" styleClass="text-blue"
										/>
									</h:panelGroup>

									<f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim>

									<h:panelGroup>
										<h:outputText id="outputFInicio" value="Fecha Inicio: "
											styleClass="text" />
										<f:verbatim>
										<font class="text-blue">
											<fmt:formatDate value="${FormularioBean.tramite.fechaInicio}"
												pattern="dd/MM/yyyy" />
										</font>
										</f:verbatim>
									</h:panelGroup>

									<f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim>

									<h:panelGroup>
										<h:outputText id="outputFInicioR" value="Fecha Inicio Real: "
											styleClass="text" />
										<f:verbatim>
										<font class="text-blue">
											<fmt:formatDate value="${FormularioBean.tramite.fechaInicioReal}" 
												pattern="dd/MM/yyyy" />
										</font>
										</f:verbatim>
									</h:panelGroup>

									<f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim>

									<h:panelGroup>
										<h:outputText id="outputVersion" value="Versión: "
											styleClass="text" />
										<h:outputText id="outVersion"
											value=" #{FormularioBean.tramite.proceso.version}" styleClass="text-blue"/>
									</h:panelGroup>

									<f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim>

									<h:panelGroup>
										<h:outputText id="outputSupervisor" value="Supervisor: "
											styleClass="text" />
										<h:outputText id="outSupervisor"
											value="#{FormularioBean.tramite.operadorSup.label}" styleClass="text-blue" />
									</h:panelGroup>

									<f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim>
									
									<h:panelGroup>
										<h:outputText id="outputFFin" value="Fecha Fin: "
											styleClass="text" />
										<f:verbatim>
										<font class="text-blue">
											<fmt:formatDate value="${FormularioBean.tramite.fechaFin}" 
												pattern="dd/MM/yyyy" />
										</font>
										</f:verbatim>
									</h:panelGroup>

									<f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim>

									<h:panelGroup>
										<h:outputText id="outputFFinR" value="Fecha Fin Real: "
											styleClass="text" />
										<f:verbatim>
										<font class="text-blue">
											<fmt:formatDate value="${FormularioBean.tramite.fechaFinReal}" 
												pattern="dd/MM/yyyy" />
										</font>
										</f:verbatim>
									</h:panelGroup>

									<f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim>

									<h:panelGroup>
										<h:outputText id="outputRevision" value="Revisión: "
											styleClass="text" />
										<h:outputText id="outRevision"
											value="#{FormularioBean.tramite.proceso.revision}" styleClass="text-blue"/>
									</h:panelGroup>

									<f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim>

									<h:panelGroup>
										<h:outputText id="outputEstado" value="Estado: "
											styleClass="text" />
										<h:outputText id="outEstado"
											value="#{FormularioBean.tramite.estado.descripcion}" styleClass="text-blue"/>
									</h:panelGroup>
									
									<f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim>
									<h:panelGroup>
										<h:outputText id="outputNroTram" value="Nro. Tramite: "
											styleClass="text" />
										<h:outputText id="outNroTram"
											value="#{FormularioBean.tramite.idTramite}" style="COLOR: green;"/>
									</h:panelGroup>
									<f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim>
									<f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim>
																		
								</h:panelGrid>								
								
									<h:panelGroup>
										<f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim>
										<h:outputText id="outputComentario" value="Comentario: "
											styleClass="text" />
										<h:outputText id="outComentario" value="#{FormularioBean.tramite.proceso.comentario}" 
												styleClass="text-blue"/>
									</h:panelGroup>
								
							</s:layoutingTitlePane>

						<f:verbatim>&nbsp;</f:verbatim>
						</h:panelGrid>
						
						<f:verbatim><br/></f:verbatim>
				
						<c:if test="${empty FormularioBean.tablaHistorico}">
							<h:panelGrid styleClass="box box-primary" style="background:#fcfcfc" columns="1">
							<s:layoutingTitlePane id="panelFormTarea"
								label=" Tarea "
								containerNodeClass="contentTitlePane"
								labelNodeClass="labelTitlePane">
								<h:panelGrid columns="8">
									
									<f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim>

									<h:panelGroup>
										<h:outputText id="outputTarea" value="Título Tarea: " 
											styleClass="text"/>
										<h:outputText id="outTextTarea" value="#{FormularioBean.tarea.titulo}"
											styleClass="text-blue"/>
									</h:panelGroup>
									
									<f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim>
									
									<h:panelGroup>
										<h:outputText id="outputReap" value="Responsable: "
											styleClass="text" />
										<h:outputText id="outresp"
											value=" #{FormularioBean.detalleTramite.operadorResponsable.apellido}" styleClass="text-blue"
											rendered="#{!FormularioBean.vistaSuper || FormularioBean.automatica}"/>
										<h:selectOneMenu id="lstOper" rendered="#{FormularioBean.vistaSuper && !FormularioBean.automatica}"
											value="#{FormularioBean.operadorSeleccionado}" styleClass="lista"
											immediate="true" onfocus="encender(this);" onblur="apagar(this);">
											<f:selectItems value="#{FormularioBean.operadorItems}" />
										</h:selectOneMenu>
									</h:panelGroup>

									<f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim>

									<h:panelGroup>
										<h:outputText id="outputFInicioT" value="Fecha Inicio: "
											styleClass="text" />
										<f:verbatim>
										<font class="text-blue">
											<fmt:formatDate value="${FormularioBean.detalleTramite.fechaInicio}" 
												pattern="dd/MM/yyyy" />
										</font>
										</f:verbatim>
									</h:panelGroup>

									<f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim>

									<h:panelGroup>
										<h:outputText id="outputFInicioRT" value="Fecha Inicio Real: "
											styleClass="text" />
										<f:verbatim>
										<font class="text-blue">
											<fmt:formatDate value="${FormularioBean.detalleTramite.fechaInicioReal}" 
												pattern="dd/MM/yyyy" />
										</font>
										</f:verbatim>
									</h:panelGroup>

									<f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim>

									<h:panelGroup>
										<h:outputText id="outputEstadoT" value="Estado: "
											styleClass="text" />
										<h:outputText id="outEstadoT" value="#{FormularioBean.detalleTramite.estado.descripcion}" 
											styleClass="text-blue"/>
									</h:panelGroup>

									<f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim>

									<h:panelGroup>
										<h:outputText id="outputProgresoT" value="Progreso: "
											styleClass="text" />
										<h:inputText id="progreso" value="#{FormularioBean.detalleTramite.progreso}" 
			                			 	 size="3" maxlength="3" styleClass="bordeceldainferior text-blue" style="width: 40px; margin: 8px"
            			    			 	 onfocus="encender(this);" onblur="apagar(this);" />
										<h:outputText id="outX100" value=" %"
											styleClass="text-blue" />
									</h:panelGroup>

									<f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim>
									
									<h:panelGroup>
										<h:outputText id="outputFFinT" value="Fecha Fin: "
											styleClass="text" />
										<f:verbatim>
										<font class="text-blue">
											<fmt:formatDate value="${FormularioBean.detalleTramite.fechaFin}" 
												pattern="dd/MM/yyyy" />
										</font>
										</f:verbatim>
									</h:panelGroup>

									<f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim>

									<h:panelGroup>
										<h:outputText id="outputFFinRT" value="Fecha Fin Real: "
											styleClass="text" />
										<f:verbatim>
										<font class="text-blue">
											<fmt:formatDate value="${FormularioBean.detalleTramite.fechaFinReal}" 
												pattern="dd/MM/yyyy" />
										</font>
										</f:verbatim>
									</h:panelGroup>

								</h:panelGrid>
								
								
									<h:panelGrid columns="7">
										<f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim>
										<x:div style="OVERFLOW-Y: auto; OVERFLOW-X: auto; WIDTH: 200; HEIGHT: 70px; border: 1px; margin-left: auto; margin-right: auto;">
											<h:outputText id="outputDescTares" value="Descripción: " 
												styleClass="text"/>
											<h:outputText id="outTextDescTarea" value="#{FormularioBean.tarea.comentario}"
												styleClass="text-blue"/>
										</x:div>
										<f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim>
										<h:outputText id="outputComentarioT" value="Comentario: "
											styleClass="text" />
										<x:inputTextarea id="impCome" style="width : 240px; height : 70px; margin-left:8px;" 
											value="#{FormularioBean.detalleTramite.comentario}" styleClass="background:colorSuave"
					                		onfocus="encender(this);" onblur="apagar(this);"/>
					                	<f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim>
					                	<h:panelGroup>
					                	<c:if test="${FormularioBean.tarea.formExterno.idFormExterno != 0}">
					                		<h:outputText id="outFormExt" value="Formulario: " styleClass="text" />
					                		<h:outputText id="outForm" value="#{FormularioBean.tarea.formExterno.label}" styleClass="text-blue" />
					                		<f:verbatim>&nbsp;&nbsp;&nbsp;</f:verbatim>
											<x:commandButton id="irForm" value="Ir" action="#{FormularioBean.irFormExterno}" styleClass="botones"/>
											<f:verbatim><br></f:verbatim>
										</c:if>
										<c:if test="${FormularioBean.tarea.docContractual.idFormExterno != 0}">
					                		<h:outputText id="outDocCont" value="Documento: " styleClass="text" />
					                		<h:outputText id="outDoc" value="#{FormularioBean.tarea.docContractual.label}" styleClass="text-blue"/>
					                		<f:verbatim>&nbsp;&nbsp;&nbsp;</f:verbatim>
											<x:commandButton id="irDoc" value="Ir" action="#{FormularioBean.irDocCont}" styleClass="botones"/>
										</c:if>
					                	</h:panelGroup>
									</h:panelGrid>
																
							</s:layoutingTitlePane>
							<f:verbatim>&nbsp;</f:verbatim>
							</h:panelGrid>
							
							<f:verbatim><br/></f:verbatim>
							
							<h:panelGrid styleClass="box box-primary" style="background:#fcfcfc" columns="1">
							<s:layoutingTitlePane id="panelFormTareaDinamico"
								label=" Formulario Dinamico "
								containerNodeClass="contentTitlePane"
								labelNodeClass="labelTitlePane">
								
								<h:panelGrid columns="3" id="superPanel" style="vertical-align:top;">
										<x:dataList value="#{FormularioBean.atributosListUno}" var="pwlUno">
											<h:panelGrid columns="3" id="tablauno">
											<h:outputText id="tituloUno" value="#{pwlUno.nombre}" style="vertical-align:top; margin: 4px;"/>

											<h:inputText  id="inputCadenaUno" value="#{pwlUno.valorTexto}" size="40" maxlength="#{pwlUno.atributo.longitud}" styleClass="bordeceldatext"
												style=" width : 200px; vertical-align:top;margin: 4px;" onfocus="encender(this);" onblur="apagar(this);" rendered="#{pwlUno.boolCadena}" 
												disabled="#{pwlUno.soloLectura}" title="#{pwlUno.atributo.comentario}"/>
											
											<h:inputText  id="inputImagenUno" value="#{pwlUno.valorImagen}" size="40" maxlength="#{pwlUno.atributo.longitud}" styleClass="bordeceldatext"
												style=" width : 200px; vertical-align:top;margin: 4px;" onfocus="encender(this);" onblur="apagar(this);" rendered="#{pwlUno.boolImagen}" 
												disabled="#{pwlUno.soloLectura}" title="#{pwlUno.atributo.comentario}"/>
											
											<h:inputTextarea id="inputTextoUno" value="#{pwlUno.valorTexto}" style="width : 300px; height : 70px;margin: 4px;" 
												onfocus="encender(this);" onblur="apagar(this);" rendered="#{pwlUno.boolTexto}" disabled="#{pwlUno.soloLectura}"
												title="#{pwlUno.atributo.comentario}"/>
												
											<h:inputText id="inputEnteroUno" value="#{pwlUno.valorNroEntero}" size="10" maxlength="#{pwlUno.atributo.longitud}" styleClass="bordeceldainferior" 
												style=" width : 200px; vertical-align:top;margin: 4px;" onfocus="encender(this);" onblur="apagar(this);" title="#{pwlUno.atributo.comentario}"
												onkeypress="return soloEnteros(this,event);" rendered="#{pwlUno.boolEntero}" disabled="#{pwlUno.soloLectura}"/>
											
											<h:inputText id="inputDecimalUno" value="#{pwlUno.valorNro}" size="3" maxlength="#{pwlUno.atributo.longitud}" styleClass="bordeceldainferior" 
												style=" width : 200px; vertical-align:top;margin: 4px;" onfocus="encender(this);" onblur="apagar(this);" title="#{pwlUno.atributo.comentario}"
												onkeypress="return soloDecimales(this,event);" rendered="#{pwlUno.boolDecimal}" disabled="#{pwlUno.soloLectura}"/>
											
											<h:inputText id="inputCuitUno" value="#{pwlUno.valorCuit}" size="10" maxlength="#{pwlUno.atributo.longitud}" styleClass="bordeceldainferior" 
												style=" width : 200px; vertical-align:top;margin: 4px;" onfocus="encender(this);" onblur="apagar(this);" title="#{pwlUno.atributo.comentario}"
												onkeypress="return soloEnteros(this,event);" rendered="#{pwlUno.boolCuit}" disabled="#{pwlUno.soloLectura}"/>
											
											<h:selectOneMenu id="lsitaDesplegableUno" value="#{pwlUno.valorTexto}" immediate="true" title="#{pwlUno.atributo.comentario}"
												onfocus="encender(this);" onblur="apagar(this);" style=" width : 200px;vertical-align:top;margin: 4px;" 
												rendered="#{pwlUno.boolLista}" disabled="#{pwlUno.soloLectura}" styleClass="lista" >
												<f:selectItems value="#{pwlUno.selectItems}"/>
											</h:selectOneMenu>
											
											<h:selectBooleanCheckbox id="chechkBoxUno" value="#{pwlUno.valorBoleano}"  styleClass="lista" style="margin: 4px;"
												immediate="true" onfocus="encender(this);" onblur="apagar(this);" title="#{pwlUno.atributo.comentario}"
												rendered="#{pwlUno.boolVerificacion}" disabled="#{pwlUno.soloLectura}"/>
											
											<x:inputCalendar id="fechaEmicionUno" monthYearRowClass="yearMonthHeader" title="#{pwlUno.atributo.comentario}"
												popupButtonStyleClass="standard_bold" currentDayCellClass="currentDayCell" value="#{pwlUno.valorFecha}"
												renderAsPopup="true" styleClass="bordeceldainferior" style="width: 200px; vertical-align:top;margin: 4px;" 
												popupTodayString="#{example_messages['popup_today_string']}" popupDateFormat="dd/MM/yyyy" 
												popupWeekString="#{example_messages['popup_week_string']}" helpText="DD/MM/YYYY" forceId="true"
												rendered="#{pwlUno.boolFecha}" disabled="#{pwlUno.soloLectura}"/>
												
											<h:panelGroup id="elementouno">
												<x:commandLink id="btnSetValorUno" action="#{FormularioBean.restaurarValor}" 
														rendered="#{pwlUno.atributo.valor}" disabled="#{pwlUno.soloLectura}">
													<f:param id="idAtributo" name="idAtributo" value="#{pwlUno.idAtributo}"/>
													<f:verbatim>
														<i class="fa fa-fw fa-refresh" title="Setea con el valor de base" border="0" style="color:lightseagreen"></i>
													</f:verbatim>
												</x:commandLink>
												<f:verbatim>&nbsp;</f:verbatim>
											</h:panelGroup>

											
										</h:panelGrid>
								</x:dataList>
								<f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim>
								<x:dataList value="#{FormularioBean.atributosListDos}" var="pwlDos" forceIdIndex="true">
										<h:panelGrid columns="3" id="tablados">
											<h:outputText id="tituloDos" value="#{pwlDos.nombre}" rendered="#{pwlDos.boolNombre}"style="vertical-align:top;margin: 4px;"/>
											
											<h:inputText  id="inputCadenaDos" value="#{pwlDos.valorTexto}" size="40" maxlength="#{pwlDos.atributo.longitud}" styleClass="bordeceldatext"
												style=" width : 200px;margin: 4px;" onfocus="encender(this);" onblur="apagar(this);" disabled="#{pwlDos.soloLectura}"
												rendered="#{pwlDos.boolCadena}" title="#{pwlDos.atributo.comentario}"/>
											
											<h:inputText  id="inputImagenDos" value="#{pwlDos.valorImagen}" size="40" maxlength="#{pwlDos.atributo.longitud}" styleClass="bordeceldatext"
												style=" width : 200px;margin: 4px;" onfocus="encender(this);" onblur="apagar(this);" disabled="#{pwlDos.soloLectura}"
												rendered="#{pwlDos.boolImagen}" title="#{pwlDos.atributo.comentario}"/>
											
											<h:inputTextarea id="inputTextoDos" value="#{pwlDos.valorTexto}" style="width : 300px; height : 70px;margin: 4px;" 
												onfocus="encender(this);" onblur="apagar(this);" rendered="#{pwlDos.boolTexto}" disabled="#{pwlDos.soloLectura}"
												title="#{pwlDos.atributo.comentario}"/>
												
											<h:inputText id="inputEnteroDos" value="#{pwlDos.valorNroEntero}" size="10" maxlength="#{pwlDos.atributo.longitud}" styleClass="bordeceldainferior" 
												style=" width : 200px;vertical-align:top;margin: 4px;" onfocus="encender(this);" onblur="apagar(this);" disabled="#{pwlDos.soloLectura}"
												onkeypress="return soloEnteros(this,event);" rendered="#{pwlDos.boolEntero}" title="#{pwlDos.atributo.comentario}"/>
											
											<h:inputText id="inputDecimalDos" value="#{pwlDos.valorNro}" size="10" maxlength="#{pwlDos.atributo.longitud}" styleClass="bordeceldainferior" 
												style=" width : 200px;vertical-align:top;margin: 4px;" onfocus="encender(this);" onblur="apagar(this);" disabled="#{pwlDos.soloLectura}"
												onkeypress="return soloDecimales(this,event);" rendered="#{pwlDos.boolDecimal}" title="#{pwlDos.atributo.comentario}"/>
											
											<h:inputText id="inputCuitDos" value="#{pwlDos.valorCuit}" size="10" maxlength="#{pwlDos.atributo.longitud}" styleClass="bordeceldainferior" 
												style=" width : 200px; vertical-align:top;margin: 4px;" onfocus="encender(this);" onblur="apagar(this);" disabled="#{pwlDos.soloLectura}"
												onkeypress="return soloEnteros(this,event);" rendered="#{pwlDos.boolCuit}" title="#{pwlDos.atributo.comentario}"/>
												
											<h:selectOneMenu id="lsitaDesplegableDos" value="#{pwlDos.valorTexto}" immediate="true" title="#{pwlDos.atributo.comentario}"
												onfocus="encender(this);" onblur="apagar(this);" style=" width : 200px;vertical-align:top;margin: 4px;" 
												rendered="#{pwlDos.boolLista}" disabled="#{pwlDos.soloLectura}" styleClass="lista" >
												<f:selectItems value="#{pwlDos.selectItems}"/>
											</h:selectOneMenu>
											
											<h:selectBooleanCheckbox id="chechkBoxDos" value="#{pwlDos.valorBoleano}" styleClass="lista" title="#{pwlDos.atributo.comentario}"
												immediate="true" onfocus="encender(this);" onblur="apagar(this);" disabled="#{pwlDos.soloLectura}"
												rendered="#{pwlDos.boolVerificacion}"  style="vertical-align:top;margin: 4px;"/>
											
											<x:inputCalendar id="fechaEmicionDos" monthYearRowClass="yearMonthHeader" disabled="#{pwlDos.soloLectura}"
												popupButtonStyleClass="standard_bold" currentDayCellClass="currentDayCell" value="#{pwlDos.valorFecha}"
												renderAsPopup="true" styleClass="bordeceldainferior" style="width: 200px; vertical-align:top;" 
												popupTodayString="#{example_messages['popup_today_string']}" popupDateFormat="dd/MM/yyyy" 
												popupWeekString="#{example_messages['popup_week_string']}" helpText="DD/MM/YYYY" forceId="true"
												rendered="#{pwlDos.boolFecha}" title="#{pwlDos.atributo.comentario}"/>
											
											<h:outputText id="tituloDs" value="#{pwlDos.auxiliar}" rendered="#{!pwlDos.boolNombre}" escape="false" style="margin: 4px;"/>
											
											<h:panelGroup id="elementodos">
												<x:commandLink id="btnSetValorDos" action="#{FormularioBean.restaurarValor}" 
														rendered="#{pwlDos.atributo.valor}" disabled="#{pwlDos.soloLectura}">
													<f:param id="idAtributo" name="idAtributo" value="#{pwlDos.idAtributo}"/>
													<f:verbatim>
														<i class="fa fa-fw fa-refresh" title="Setea con el valor de base" border="0" style="color:lightseagreen"></i>
													</f:verbatim>
												</x:commandLink>
												<f:verbatim>&nbsp;</f:verbatim>
											</h:panelGroup>

											 
										</h:panelGrid>							
								</x:dataList>
							</h:panelGrid>
					</s:layoutingTitlePane>
					<f:verbatim>&nbsp;</f:verbatim>
					</h:panelGrid>
					<f:verbatim><br/></f:verbatim>
				
				<h:panelGrid columns="10" width="727" id="panelBotonera">
					<f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim>
					<f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim>
					<f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim>
					<f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim>
					<f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim>
					<f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim>
					<f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim>						
					<x:commandButton id="finTareaGrabar" value="Finalizar Tarea" action="#{FormularioBean.finalizar}" 
								styleClass="btn btn-primary btn-flat" onclick="return confirm('¿Seguro desea finalizar la tarea?');"/>
                	<x:commandButton id="buttonGrabar" value="Email al Supervisor" action="" styleClass="btn btn-primary btn-flat"
                				disabled="true"/>
					<x:commandButton id="buttonCancelar" value="Rechazar Tarea" action="#{FormularioBean.rechazar}" 
								disabled="#{FormularioBean.vistaSuper}" styleClass="btn btn-primary btn-flat" style=" width : 120px;"/>
				</h:panelGrid>
			</c:if>

				<c:if test="${!empty FormularioBean.tablaHistorico}">
				<f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim>
				<f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim>
					<h:dataTable value="#{FormularioBean.tablaHistorico}" id="tablaDetalleTareas"
	                    		       			 headerClass="standardTable_Header" styleClass="standardTable"
	                           					 footerClass="standardTable_Header"
			                           			 rowClasses="standardTable_Row1,standardTable_Row2"
	        		                   			 columnClasses="standardTable_Column,standardTable_ColumnCentered,standardTable_Column"							             
									             var="tablaTarea" style=" width : 900px;">
							    
				                        <h:column>
		    		                        <f:facet name="header">
		            		                    <h:outputText value="Fecha" styleClass="texto" />
		                    		        </f:facet>
			                                <h:outputText value="#{tablaTarea.timestamp}" styleClass="texto" />
		    		                    </h:column>
			                        	
			                        	<h:column>
		    		                        <f:facet name="header">
		            		                    <h:outputText value="Operador" styleClass="texto" />
		                    		        </f:facet>
			                                <h:outputText value="#{tablaTarea.detalleHis.operador.label}" styleClass="texto" />
		    		                    </h:column>
			                        	
			                        	<h:column>
		    		                        <f:facet name="header">
		            		                    <h:outputText value="Tarea" styleClass="texto" />
		                    		        </f:facet>
			                                <h:outputText value="#{tablaTarea.tarea.titulo}" styleClass="texto" />
		    		                    </h:column>
			                        		
				                        <h:column>
		    		                        <f:facet name="header">
		            		                    <h:outputText value="Progreso" styleClass="texto" />
		                    		        </f:facet>
			                                <h:outputText value="#{tablaTarea.detalleHis.progreso}" styleClass="texto" />
		    		                    </h:column>
		                        		
		            		            <h:column>
		                    		        <f:facet name="header">
				                                <h:outputText value="Estado" styleClass="texto" />
		    		                        </f:facet>
	                		                <h:outputText value="#{tablaTarea.detalleHis.estado.descripcion}" styleClass="texto" />
		                    		    </h:column>
										
										<h:column>
		    		                        <f:facet name="header">
		            		                    <h:outputText value="Fecha Inicio" styleClass="texto" />
		                    		        </f:facet>
			                                <h:outputText value="#{tablaTarea.detalleHis.fechaInicio}" styleClass="texto" />
		    		                    </h:column>
		    		                    
		    		                    <h:column>
		    		                        <f:facet name="header">
		            		                    <h:outputText value="Fecha Inicio Real" styleClass="texto" />
		                    		        </f:facet>
			                                <h:outputText value="#{tablaTarea.detalleHis.fechaInicioReal}" styleClass="texto" />
		    		                    </h:column>
		    		                    
		    		                    <h:column>
		    		                        <f:facet name="header">
		            		                    <h:outputText value="Fecha Fin" styleClass="texto" />
		                    		        </f:facet>
			                                <h:outputText value="#{tablaTarea.detalleHis.fechaFin}" styleClass="texto" />
		    		                    </h:column>
		    		                    
		    		                    <h:column>
		    		                        <f:facet name="header">
		            		                    <h:outputText value="Fecha Fin Real" styleClass="texto" />
		                    		        </f:facet>
			                                <h:outputText value="#{tablaTarea.detalleHis.fechaFinReal}" styleClass="texto" />
		    		                    </h:column>
										
		            		            <h:column>
		                    		        <f:facet name="header">
				                                <h:outputText value="Operador Responsable" styleClass="texto" />
		    		                        </f:facet>
	                		                <h:outputText value="#{tablaTarea.detalleHis.operadorResponsable.label}" styleClass="texto" />
		                    		    </h:column>
	
									</h:dataTable>
				</c:if>
				
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
    document.getElementById("sideMenu").innerHTML += generateMenu(obj) + `</li>`;
}

</script>

<%@include file="/inc/scripts.jsp" %>  
</body>
</html>
</f:view>
