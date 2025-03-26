<%@include file="/inc/tags.jsp"%>
<jsp:useBean id="ahora" class="java.util.Date" scope="request" />

<f:view>
	<html>
	<head>
	<title><h:outputText value="#{IndividuoEvaluacionBean.tituloLargo}" /></title>
	<s:script language="javascript">
    	function recargar() {
    		document.getElementById('amIndividuoForm').submit();
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

	<%@include file="/inc/head.inc"%>






	<body onbeforeunload="ShowWait('amIndividuoForm');" 
	        onload="if('${IndividuoEvaluacionBean.popup.mostrar}'=='true') {viewDialog.show();} 
	                   else {window.scrollTo(0,${ScrollBean.hiddenScrollY});}">


	<center>
	<h:form id="amIndividuoForm" enctype="multipart/form-data">

		<%-- GRABA EL ESTADO DEL SCROLL --%>
		<h:panelGroup rendered="#{!IndividuoEvaluacionBean.popup.mostrar}">
			<%@include file="/inc/scroll.inc"%>
		</h:panelGroup>

		<x:panelLayout id="page" layout="#{globalOptions.pageLayout}" align="center"
			styleClass="pageLayout" headerClass="pageHeader"
			navigationClass="pageNavigation" bodyClass="pageBody"
			footerClass="pageFooter">

			<f:facet name="header">
				<f:subview id="header">
					<jsp:include page="/inc/page_header.jsp" />
					<%--<jsp:include page="/inc/navigation_test.jsp" />--%>
				</f:subview>
			</f:facet>

			<f:facet name="body">
				<h:panelGroup id="body">
					<f:verbatim>
						<table width="940" border="0" cellspacing="0" cellpadding="0" align="center">
						   	<tr>
						    	<td width="351" height="47" align="center" class="titulo"> ${IndividuoEvaluacionBean.tituloLargo} <br>
					    	    	<span class="subtitulo"> ${IndividuoEvaluacionBean.tituloCorto} </span></td>
					        	<td width="589" align="right" valign="top" class="fecha" style="color: #FFFFFF;">
					        		<fmt:formatDate value="${ahora}" timeZone="GMT-3" type="both" pattern="EEEE dd 'de' MMMM 'de' yyyy"/> &nbsp;&nbsp;
						        </td>
					        </tr>
					        <tr>
					        	<td height="10" colspan="4"></td>
					        </tr>
						</table>
					</f:verbatim>

					<%-- INCLUDE PARA LOS ERRORES --%>
					<h:panelGroup id="verErrores">
						<jsp:include page="/inc/error.jsp" />
					</h:panelGroup>
																		
					<%-- POPUP PARA LOS MENSAJES DE EXITO Y FRACASO --%>
					<s:modalDialog dialogId="viewDialog" dialogVar="viewDialog" styleClass="viewDialog" dialogTitle="#{IndividuoEvaluacionBean.tituloCorto}">
					    	
					    	<h:panelGrid columns="3" width="500">
					    		<f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim>
						    	<x:graphicImage value="/img/#{IndividuoEvaluacionBean.popup.nombreIcono}" />
						        <h:outputText value=" #{IndividuoEvaluacionBean.popup.mensaje}" styleClass="texto"/>
					        </h:panelGrid>
						        
					        <h:panelGrid columns="3" width="500">
					        	<f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim>
					        	<x:commandButton action="#{IndividuoEvaluacionBean.irAContinuar}" onclick="clickLink('continuarCarga');dojo.widget.byId('viewDialog').hide();"
					         		 value="Continuar" styleClass="botones" title="Continuar con el alta del individuo."/>
								<f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim>
								
							</h:panelGrid>
					</s:modalDialog>
					
					
					
                	
				    <x:commandLink id="continuarCarga" action="#{IndividuoEvaluacionBean.irAContinuar}" forceId="true" style="display: block;"/>
                    <x:commandLink id="traerIndividuo" action="#{IndividuoEvaluacionBean.traerIndividuo}" forceId="true" style="display: block;"/>
                    <x:commandLink id="buscarOtroIndividuo" action="#{IndividuoEvaluacionBean.buscarOtroIndividuo}" forceId="true" style="display: block;"/>
                	
					<h:panelGrid columns="1" align="center" id="panlePrincipal" width="900px">
					
						<s:layoutingTitlePane id="solicitud" label="#{IndividuoEvaluacionBean.tituloPanleUno}"
							containerNodeClass="contentTitlePane" labelNodeClass="labelTitlePane">
							<h:panelGrid columns="4" id="panelSecundarioUno" align="center" width="650">
								<h:outputText id="outNroSolDiv" value="Nro. Solicitud y Nro. DIV: " />
								<x:inputText id="inNroSolDiv" value="#{IndividuoEvaluacionBean.nroSolicitud}" forceId="true" 
									maxlength="8" styleClass="bordeceldainferior" style="width: 150px" onfocus="encender(this);"
									onblur="apagar(this);" onkeypress="return soloEnteros(this,event);" disabled="true"
									onchange="javascript:setValueId('inApellido','idFoco');"/>
							
								<h:outputText id="outPromotor" value="Promotor: " styleClass="texto" />
								<h:outputText id="outPromotorNro" value="#{IndividuoEvaluacionBean.nombrePromotor}" styleClass="textoblue"/>

								<h:outputText id="outCaja" value="Caja: " styleClass="taxto" />
								<h:outputText id="caja" value="#{IndividuoEvaluacionBean.solicitud.caja}" styleClass="textoblue"/>

								<h:outputText id="outFolio" value="Folio: " styleClass="taxto" />
								<h:outputText id="folio" value="#{IndividuoEvaluacionBean.solicitud.folio}" styleClass="textoblue"/>
								
								<h:panelGroup>
									<h:outputText id="outFechaAlta" value="Fecha Alta: " styleClass="texto" />
									<f:verbatim>&nbsp;&nbsp;</f:verbatim>
									<c:if test="${IndividuoEvaluacionBean.solicitud.fechaRecepcion != null}">
										<f:verbatim>
											<font style="COLOR: #0000ff;">
											<fmt:formatDate value="${IndividuoEvaluacionBean.solicitud.fechaRecepcion}" pattern="dd/MM/yyyy" />
											</font>
										</f:verbatim>
									</c:if>
								</h:panelGroup>
								<f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim>
								
								<c:if test="${IndividuoEvaluacionBean.origen != 4}">
									<x:commandButton id="buttonGrabar1" value="Guardar" action="#{IndividuoEvaluacionBean.guardarSolicitud}"
									styleClass="botones" disabled="#{!IndividuoEvaluacionBean.opcionA}"/> 
								</c:if>
								<c:if test="${IndividuoEvaluacionBean.origen == 4}">
									<f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim>
								</c:if>
								<x:commandButton id="buttonCancelar1" value="Salir" action="#{IndividuoEvaluacionBean.salirSolicitud}" 
									styleClass="botones"/>

							</h:panelGrid>
						</s:layoutingTitlePane>
						
						<f:verbatim><br></f:verbatim>
						
						<s:layoutingTitlePane id="observacionesGenerales" label="Observaciones Generales de la solicitud" rendered="#{IndividuoEvaluacionBean.panelD}"
							containerNodeClass="contentTitlePane" labelNodeClass="labelTitlePane">
							<h:panelGrid columns="2" id="panelDeObservacionesUno" align="center" width="650">

								<h:outputText id="outUnoObs" value="Observación de la solicitud:" />
								<x:inputTextarea id="inUnoObs" value="#{IndividuoEvaluacionBean.observacion}" 
								    styleClass="background:colorSuave" style="width : 400px; height : 50px;" onfocus="encender(this);"
									onblur="apagar(this);" onchange="setValueId('inDosObs','idFoco');"/>
							
								<h:outputText id="outDosObs" value="Confirmación telefonica Laboral:" styleClass="texto" rendered="false"/>
								<x:inputTextarea id="inDosObs" forceId="true" value="#{IndividuoEvaluacionBean.confTelLaboral}"
									styleClass="background:colorSuave" style="width : 400px; height : 50px;" onfocus="encender(this);" 
									onblur="apagar(this);" onchange="setValueId('inTresObs','idFoco');" rendered="false"/>	

								<h:outputText id="outTresObs" value="Confirmación telefonica Particular:" styleClass="texto" rendered="false"/>
								<x:inputTextarea id="inTresObs" forceId="true" value="#{IndividuoEvaluacionBean.confTelParticular}"
									styleClass="background:colorSuave" style="width : 400px; height : 50px;" onfocus="encender(this);" 
									onblur="apagar(this);" onchange="setValueId('inApellido','idFoco');" rendered="false"/>	

							</h:panelGrid>
						</s:layoutingTitlePane>
						
						
			<c:if test="${IndividuoEvaluacionBean.opcionA}">
						<%-- Getión Datos Personales --%>
						<s:layoutingTitlePane id="datosPersonales" label="#{IndividuoEvaluacionBean.tituloPanleDos}"
							containerNodeClass="contentTitlePane" labelNodeClass="labelTitlePane" rendered="#{IndividuoEvaluacionBean.panelH}">
							
							<h:panelGrid columns="4" id="panelSecuandarioDos" align="center" width="650">
							
								<h:outputText id="outCuil" value="C.U.I.L.: " styleClass="texto" />
								<h:panelGroup id="panelRepetido">
									<x:inputText id="inNroCuil" forceId="true" value="#{IndividuoEvaluacionBean.cuil}" size="11" disabled="true"
										maxlength="11" styleClass="bordeceldainferior" style=" width : 180px;" onfocus="encender(this);" onblur="apagar(this);"
										onkeypress="return soloEnteros(this,event);" onchange="javascript:setValueId('inMail','idFoco');"/>
									
									<f:verbatim>&nbsp;&nbsp;&nbsp;</f:verbatim>
																				
											<x:commandButton id="btnExisteIndividuo" value="Buscar Individuo" action="#{IndividuoEvaluacionBean.buscarSiIndividuoExiste}" 
												title="Buscar Individuo" image="/img/icon/reload_page.png" alt="Buscar Individuo."/>
											
											<f:verbatim>&nbsp;&nbsp;</f:verbatim>
											
									
									<x:commandButton id="btnBuscar" value="Buscar" onclick="agregarTipoDomicilio.show();" rendered="false"
										action="" title="Busca el Promotor especificado." image="/img/icon/srch_24.gif"/>
								</h:panelGroup>
								<f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim>
								<f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim>

								<h:outputText id="outApellido" value="Apellido Paterno: " styleClass="texto" />
								<x:inputText id="inApellido" value="#{IndividuoEvaluacionBean.individuoEvaluacion.apellido}" disabled="#{IndividuoEvaluacionBean.habilitada}"  forceId="true" size="50"
									maxlength="100" styleClass="bordeceldatext" style="width: 150px" onfocus="encender(this);" onblur="apagar(this);"
									onchange="javascript:setValueId('inAMaterno','idFoco');"/>
									
								<h:outputText id="outAMaterno" value="Apellido Materno: " styleClass="texto" />
								<x:inputText id="inAMaterno" value="#{IndividuoEvaluacionBean.individuoEvaluacion.apellidoMaterno}" forceId="true" size="50"
									maxlength="100" styleClass="bordeceldatext" style="width: 150px" onfocus="encender(this);" onblur="apagar(this);"
									onchange="javascript:setValueId('inNombre','idFoco');"/>
									
								<h:outputText id="outNombre" value="Nombres: " styleClass="texto" />
								<x:inputText id="inNombre" value="#{IndividuoEvaluacionBean.individuoEvaluacion.nombres}" disabled="#{IndividuoEvaluacionBean.habilitada}" forceId="true" size="50"
									maxlength="100" styleClass="bordeceldatext" style="width: 150px" onfocus="encender(this);" onblur="apagar(this);"
									onchange="javascript:setValueId('lstTDoc','idFoco');"/>
								
								<f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim>
								<f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim>
								
								<h:outputText id="outTipoDNI" value="Tipo Documento: " styleClass="texto" />
								<h:selectOneMenu id="lstTDoc" value="#{IndividuoEvaluacionBean.individuoEvaluacion.tipoDocumento.idTipoDocumento}"
									styleClass="lista" immediate="true" onfocus="encender(this);" onblur="apagar(this);" style="width: 200px"
									onchange="javascript:setValueId('inNroDoc','idFoco');">
									<f:selectItems value="#{IndividuoEvaluacionBean.listTipoDni}" id="selectItemIdTipoDoc"/>
								</h:selectOneMenu>

								<h:outputText id="outNroDoc" value="Número: " styleClass="texto" />
								<x:inputText id="inNroDoc" forceId="true" value="#{IndividuoEvaluacionBean.individuoEvaluacion.nroDocumento}" disabled="#{IndividuoEvaluacionBean.habilitada}" size="20"
									maxlength="20" styleClass="bordeceldainferior" style="width: 150px" onfocus="encender(this);" onblur="apagar(this);"
									onkeypress="return soloEnteros(this,event);" onchange="javascript:setValueId('lstsexo','idFoco');"/>

								<h:outputText id="outSexo" value="Sexo: " styleClass="texto" />
								<h:selectOneMenu id="lstsexo" value="#{IndividuoEvaluacionBean.idTipoSexoSeleccionado}"
									styleClass="lista" immediate="true" onfocus="encender(this);"
									onblur="apagar(this);" style="width: 200px" onchange="javascript:setValueId('fechaNac','idFoco');">
									<f:selectItems value="#{IndividuoEvaluacionBean.listTipoSexo}" id="selectItemIdTipoSexo"/>
								</h:selectOneMenu>
			 					
			 					<h:outputText value="Fecha Nacimiento: " id="outFechaNac" styleClass="texto"/>
								<x:inputCalendar id="fechaNac" monthYearRowClass="yearMonthHeader" weekRowClass="weekHeader" 
			                		currentDayCellClass="currentDayCell" popupButtonStyleClass="standard_bold"
			                		value="#{IndividuoEvaluacionBean.fechaNacimiento}" renderAsPopup="true"
					                styleClass="bordeceldainferior" style="width: 90px"
					                popupTodayString="#{example_messages['popup_today_string']}"
			        		        popupDateFormat="dd/MM/yyyy" popupWeekString="#{example_messages['popup_week_string']}"
					                helpText="DD/MM/YYYY" forceId="true" onchange="javascript:setValueId('inMail','idFoco');"/>
			 					
								<h:outputText id="outMail" value="Casilla Email: " styleClass="texto" />
								<x:inputText id="inMail" forceId="true" value="#{IndividuoEvaluacionBean.email}" size="50"
									maxlength="50" styleClass="bordeceldatext" style="width: 150px" onfocus="encender(this);" onblur="apagar(this);"
									onchange="javascript:setValueId('agregarDomicilioLink','idFoco');"/>
								
								<f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim>
								<f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim>
							</h:panelGrid>
							
							<h:panelGrid columns="1" id="panelSecuandarioTres" align="center" width="850">

								<%-- GESTIONAR DOMICILIOS --%>

								<s:fieldset legend="Domicilio" id="fieldDomicilio">
				                            <h:panelGrid columns="3" align="center" id="domic">
												<h:panelGrid columns="4" id="panelDomicilio" width="600" align="center">
														<h:outputText id="txtCalle" value="Calle:" styleClass="texto"/>
														<h:outputText id="outTxtCalle" value="#{IndividuoEvaluacionBean.individuoEvaluacion.domicilio.calleNombre}" styleClass="textoblue" />
														<h:outputText id="txtNumero" value="Número:" styleClass="texto"/>
														<h:outputText id="outTxtNumero" value="#{IndividuoEvaluacionBean.individuoEvaluacion.domicilio.calleNumero}" styleClass="textoblue"/>
														<h:outputText id="txtBarrio" value="Barrio:" styleClass="texto"/>
														<h:outputText id="outTxtBarrio" value="#{IndividuoEvaluacionBean.individuoEvaluacion.domicilio.barrio.descripcion}" styleClass="textoblue"/>
														<h:outputText id="txtLocalidad" value="Localidad:" styleClass="texto"/>
														<h:outputText id="outTxtLocalidad" value="#{IndividuoEvaluacionBean.individuoEvaluacion.domicilio.localidad.nombre}" styleClass="textoblue"/>
												</h:panelGrid>
												<x:commandLink id="agregarDomicilioLink" action="#{IndividuoEvaluacionBean.agregarDomicilioPopup}" >
															<x:graphicImage value="/img/icon/home_24.gif" title="Agregar-Modificar domicilio." border="0" id="botonImagenUno"/>
												</x:commandLink>
												<x:commandLink id="eliminarDomicilioLink" action="#{IndividuoEvaluacionBean.eliminarDomicilio}" >
															<x:graphicImage value="/img/quitar.png" title="Borra domicilio incorrecto." border="0" id="botonImagenElim"/>
												</x:commandLink>
											</h:panelGrid>
										</s:fieldset>							
							<%-- GESTIONAR TELEFONOS --%>

								<s:fieldset legend="Teléfonos" id="teledonos">
								<h:panelGrid columns="2" id="panelTelefono" width="650" align="center">
									<h:panelGroup id="panelGroupTelefono">
									<h:dataTable value="#{IndividuoEvaluacionBean.listTelefono}"
										id="tablaTelefono" styleClass="standardTable" headerClass="dataTable_Header"
										footerClass="standardTable_Header" rowClasses="standardTable_Row1,standardTable_Row2"
										columnClasses="standardTable_Column,standardTable_ColumnCentered,standardTable_Column"
										var="individuoTelefono" style=" width : 600px;">
										
										<h:column>
											<f:facet name="header">
												<h:outputText value="Número" styleClass="texto" style="font: bold;color: white;" />
											</f:facet>

											<h:outputText value="#{individuoTelefono.telefono.codArea}"/>
											<h:outputText value=" - "/>
											<h:outputText value="#{individuoTelefono.telefono.nroTlefono}"/>
											<h:outputText value=" int. "/>
											<h:outputText value="#{individuoTelefono.telefono.nroInterno}"/>
										</h:column>
										
										<h:column>
											<f:facet name="header">
												<h:outputText value="Celular" styleClass="texto" style="font: bold;color: white;" />
											</f:facet>
											<h:outputText value="#{individuoTelefono.telefono.esCelular}"/>
										</h:column>
										
										<h:column>
											<f:facet name="header">
												<h:outputText value="Fax" styleClass="texto" style="font: bold;color: white;" />
											</f:facet>
											<h:outputText value="#{individuoTelefono.telefono.esFax}"/>
										</h:column>
										
										<h:column>
											<f:facet name="header">
												<h:outputText value="Tipo Telefono" styleClass="texto" style="font: bold;color: white;" />
											</f:facet>
											<h:outputText value="#{individuoTelefono.telefono.tipo.descripcion}" />
										</h:column>
										
										<h:column>
											<f:facet name="header">
												<h:outputText value="Horarios" styleClass="texto" style="font: bold;color: white;" />
											</f:facet>
											<h:outputText value="#{individuoTelefono.telefono.horarios}" />
										</h:column>
										
										<h:column>
											<f:facet name="header">
												<h:outputText value="Descripción" styleClass="texto" style="font: bold;color: white;" />
											</f:facet>
											<h:outputText value="#{individuoTelefono.telefono.descripcion}" />
										</h:column>
										
										<h:column>
											<x:commandLink action="#{IndividuoEvaluacionBean.editarTelefono}" id="editarTelefonoLink" disabled="#{IndividuoEvaluacionBean.botonTelefono}">
												<f:param id="idTelEdi" name="idTelEdi" value="#{individuoTelefono.telefono.idTelefono}" />
												<x:graphicImage value="/img/editar.gif" title="Editar el Teléfono." border="0" id="botonImagenDos" />
											</x:commandLink>
										</h:column>

										<h:column>
											<x:commandLink action="#{IndividuoEvaluacionBean.eliminarTelefono}" id="eliminarTelefonoLink" disabled="#{IndividuoEvaluacionBean.botonTelefono}">
												<f:param id="idTelefono" name="idTelefono" value="#{individuoTelefono.telefono.idTelefono}"/>
												<x:graphicImage value="/img/cat_act.gif" title="Eliminar un Teléfono." border="0" id="botonImagenTres" />
											</x:commandLink>
										</h:column>
										
									</h:dataTable>
									</h:panelGroup>
									
									<h:panelGroup id="panelGroupBotones">
										<x:commandLink id="agregarTelefonoLink" action="#{IndividuoEvaluacionBean.agregarTelefono}" disabled="#{IndividuoEvaluacionBean.botonTelefono}">
											<x:graphicImage value="/img/cat_pad.gif" title="Agregar Teléfono." border="0" id="botonImagenCuatro"/>
										</x:commandLink>
									</h:panelGroup>

								</h:panelGrid>
							</s:fieldset>
							
						</h:panelGrid>
					</s:layoutingTitlePane>
					
					<f:verbatim><br></f:verbatim>
					
					<%-- Informacion Familiar --%>
					<s:layoutingTitlePane id="informeFamiliar" label="#{IndividuoEvaluacionBean.tituloPanleCinco}"
							containerNodeClass="contentTitlePane"labelNodeClass="labelTitlePane" 
							rendered="#{IndividuoEvaluacionBean.panelI}"> 
							
							<f:verbatim><br></f:verbatim>
							
							<h:panelGrid columns="1" id="panelDatosF" width="850" align="center">
							
							<s:fieldset legend="Datos Familiares" id="datosFamiliares">
							<h:panelGrid columns="4" id="panelDatosFamiliares" width="650" align="center">
							
								<h:outputText id="outEstadoCivil" value="Estado Civil: " styleClass="texto" />
								<h:selectOneMenu id="lstestadoCivil" value="#{IndividuoEvaluacionBean.idEstadoCivilSeleccionado}"
									styleClass="lista" immediate="true" onfocus="encender(this);" onblur="apagar(this);" style="width: 200px"
									onchange="javascript:setValueId('inCantHijos','idFoco');">
									<f:selectItems value="#{IndividuoEvaluacionBean.listEstadoCivil}" id="selectItemIdEstadoCivil"/>
								</h:selectOneMenu>

								<h:outputText id="outHijo" value="Hijos a Cargo: "
									styleClass="texto" />
								<h:inputText id="inCantHijos" value="#{IndividuoEvaluacionBean.individuoEvaluacion.hijosCargo}" styleClass="bordeceldainferior" 
									maxlength="2" style="width: 50" onfocus="encender(this);" onblur="apagar(this);" onkeypress="return soloEnteros(this,event);"
									onchange="javascript:setValueId('lstEducacion','idFoco');"/>
							</h:panelGrid>
							</s:fieldset>
																
							<s:fieldset legend="Estudios" id="datosEstudio" rendered="#{IndividuoEvaluacionBean.mostrarEducacion}">
							<h:panelGrid columns="4" id="panelEstudios" width="650" align="center">
								<h:outputText id="outEducacion" value="Educación: " styleClass="texto" />
								<h:selectOneMenu id="lstEducacion" value="#{IndividuoEvaluacionBean.individuoEvaluacion.educacion.idEducacion}"
									styleClass="lista" immediate="true" onfocus="encender(this);" onblur="apagar(this);" style="width: 200px"
									valueChangeListener="#{IndividuoEvaluacionBean.cambiarProfesion}" binding="#{IndividuoEvaluacionBean.educacionHtml}"
									onchange="javascript:setValueId('lstEducacion','idFoco'); submit();">
									<f:selectItems value="#{IndividuoEvaluacionBean.listEducacion}" id="selctItemIdEducSeleccionada"/>
								</h:selectOneMenu>

								<h:outputText id="outProfesion" value="Profesión: " styleClass="texto" />
								<h:selectOneMenu id="lstPorfesion" value="#{IndividuoEvaluacionBean.individuoEvaluacion.profesion.idProfesion}"
									styleClass="lista" immediate="true" onfocus="encender(this);" onblur="apagar(this);" style="width: 200px;" binding="#{IndividuoEvaluacionBean.profesionHtml}"
								    disabled="#{IndividuoEvaluacionBean.habilitarProfesion}">
									<f:selectItems  id="selectoUno" value="#{IndividuoEvaluacionBean.listProfesion}"/>
								</h:selectOneMenu>
							</h:panelGrid>
							</s:fieldset>
							
							<c:if test="${IndividuoEvaluacionBean.origen != 1}">
								<s:fieldset legend="Vinculo" id="vinculo">
								<h:panelGrid columns="4" id="panelvinculo" width="650" align="center">
									<h:outputText id="outVinculo"
										value="Vinculo con el Solicitante: " styleClass="texto" />
									<h:selectOneMenu id="lstVinculo" value="#{IndividuoEvaluacionBean.individuoEvaluacion.vinculo.idVinculo}" 
										styleClass="lista" immediate="true" onfocus="encender(this);" onblur="apagar(this);" style="width: 200px"
										onchange="javascript:setValueId('lstDomEnv','idFoco');">
										<f:selectItems value="#{IndividuoEvaluacionBean.listVinculo}" id="selctItemIdVinvulo"/>
									</h:selectOneMenu>
									
									<f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim>
									<f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim>
								</h:panelGrid>
								</s:fieldset>
							</c:if>
							</h:panelGrid>
					</s:layoutingTitlePane>
					
					<c:if test="${IndividuoEvaluacionBean.panelA}">
						<f:verbatim><br></f:verbatim>
					</c:if>
					
					<s:layoutingTitlePane id="datosActividad" label="#{IndividuoEvaluacionBean.tituloPanleSiete}" containerNodeClass="contentTitlePane" labelNodeClass="labelTitlePane" rendered="#{IndividuoEvaluacionBean.panelJ}">
						<h:panelGrid columns="1" align="center" id="panelIternoCuatro" width="850">
						<%-- Datos Empresa --%>
								<s:fieldset legend="Datos Empresa" id="datosEmpresa">
									<h:panelGrid columns="4" align="center" id="panelIternoLaboral" width="650">
										
										<h:outputText value="C.U.I.T.: " id="outCuit" styleClass="texto" />
										<h:panelGroup id="uno">
											<h:inputText id="inCuit" value="#{IndividuoEvaluacionBean.nroCuit}" maxlength="11" styleClass="bordeceldainferior" style="width: 150px" onfocus="encender(this);" onblur="apagar(this);" onkeypress="return soloEnteros(this,event);" />
											
											<f:verbatim>&nbsp;&nbsp;&nbsp;</f:verbatim>
																				
											<x:commandButton id="btnIngresarCuit" value="Buscar Empresa" action="#{IndividuoEvaluacionBean.buscarEmpresa}" 
											title="Buscar Empresas" image="/img/icon/reload_page.png" disabled="#{IndividuoEvaluacionBean.botonEmpresa}" alt="Buscar Empresa." />
											
											<f:verbatim>&nbsp;&nbsp;</f:verbatim>
											
											<x:commandButton id="btnNuevaEmpresa" value="Agregar Empresa" action="#{IndividuoEvaluacionBean.agregarEmpresa}" title="Agregar Empresa" image="/img/cat_pad.gif" disabled="#{IndividuoEvaluacionBean.botonEmpresa}" alt="Agregar Empresa." />										
											
											<f:verbatim>&nbsp;&nbsp;</f:verbatim>
											
											<x:commandButton id="btnVerEmpresa" title="Mostrar cliente" action="#{IndividuoEvaluacionBean.popUpListaTclientes}" disabled="#{IndividuoEvaluacionBean.botonEmpresa}" image="/img/icon/view.gif" value="Ver Cliente" />
										</h:panelGroup>
										
										<h:outputText value="Sucursal: " id="outSuc" styleClass="texto" />
										<h:selectOneMenu id="lstSuc" value="#{IndividuoEvaluacionBean.idSucursalSeleccionado}" 
												styleClass="lista" immediate="true" onfocus="encender(this);" onblur="apagar(this);" style="width: 200px" 
												disabled="#{IndividuoEvaluacionBean.habilitarSucursal}" binding="#{IndividuoEvaluacionBean.sucursalHtml}" 
												valueChangeListener="#{IndividuoEvaluacionBean.cambioSucursal}" onchange="javascript:setValueId('lstOcupacion','idFoco'); submit();">
											<f:selectItems value="#{IndividuoEvaluacionBean.listSucEmp}" id="itemSuc" />
										</h:selectOneMenu>
										
										<h:outputText value="Cuit: " id="outCuitEmpre" styleClass="texto" />
										<h:outputText value="#{IndividuoEvaluacionBean.empresa.cuit}" id="outCuitLabel" styleClass="textoblue" />
												
										<h:outputText value="Razón Social: " id="outRS" styleClass="texto" />
										<h:outputText value="#{IndividuoEvaluacionBean.empresa.razonSocial}" id="outRazonSocial" styleClass="textoblue" />
<%--
										<h:outputText value="Rubro: " id="outR" styleClass="texto" />
										<h:outputText value="#{IndividuoEvaluacionBean.empresa.rubro.descripcion}" id="outRubro" styleClass="textoblue" />
									--%>
									
										<h:outputText value="Dirección: " id="outD" styleClass="texto" />
										<h:outputText value="#{IndividuoEvaluacionBean.direccionSucursal}" id="outDireccion" styleClass="textoblue" />
										
										<h:outputText value="Teléfono: " id="outT" styleClass="texto" />
										<h:outputText value="#{IndividuoEvaluacionBean.telefono}" id="outTelefono" styleClass="textoblue" />
										
									</h:panelGrid>
							
								</s:fieldset>
								
								</h:panelGrid>
								<%-- Datos Laboraless--%>
								
								<h:panelGrid columns="1" id="panleLaboralesUno" width="850" align="center">
								
								<s:fieldset legend="Datos Laborales" id="datosLaborales">
									<h:panelGrid columns="4" id="panleLaborales" width="650" align="center"> 
									
										<h:outputText value="Ocupación: " id="outOcupacion" styleClass="tecto" />
										<h:selectOneMenu id="lstOcupacion" value="#{IndividuoEvaluacionBean.idOcupacion}" styleClass="lista" immediate="true" onfocus="encender(this);" onblur="apagar(this);" style="width: 200px">
											<f:selectItems value="#{IndividuoEvaluacionBean.listOcupacion}" id="selctItemIdOcupacionSeleccionada" />
										</h:selectOneMenu>
										
										<h:outputText value="Cargo: " id="outCargo" styleClass="texto" />
										<h:inputText id="inCargo" value="#{IndividuoEvaluacionBean.cargo}" size="50" maxlength="50" styleClass="bordeceldatext" style="width: 150px" onfocus="encender(this);" onblur="apagar(this);" />
											
										<h:outputText value="Fecha Ingreso: " id="outFIngreso" styleClass="texto" />
										<x:inputCalendar id="fechaIng" monthYearRowClass="yearMonthHeader" weekRowClass="weekHeader" currentDayCellClass="currentDayCell" popupButtonStyleClass="standard_bold" value="#{IndividuoEvaluacionBean.ingreso}" renderAsPopup="true" styleClass="bordeceldainferior" style="width: 90px" onchange="javascript:setValueId('inAntiguedad','idFoco'); submit();" popupTodayString="#{example_messages['popup_today_string']}" immediate="true" popupDateFormat="dd/MM/yyyy" popupWeekString="#{example_messages['popup_week_string']}" forceId="true" valueChangeListener="#{IndividuoEvaluacionBean.calcularAntiguedad}" binding="#{IndividuoEvaluacionBean.fechaIngreso}" />
										
										<h:outputText value="Antigüedad: " id="outAnt" styleClass="texto" />
										<h:outputText id="inAntiguedad" value="#{IndividuoEvaluacionBean.antiguedad}" styleClass="textoblue" />
										
										<h:outputText value="Sueldo: " id="outSueldo" styleClass="texto" />
										<h:inputText id="inSueldo" value="#{IndividuoEvaluacionBean.sueldoNeto }" size="10" maxlength="10" styleClass="bordeceldainferior" style="width: 90px" onfocus="encender(this);" onblur="apagar(this);" onkeypress="return soloDecimales(this,event);" />
										 
										<f:verbatim>&nbsp;&nbsp;&nbsp;</f:verbatim>
										<f:verbatim>&nbsp;&nbsp;&nbsp;</f:verbatim>
										
										<h:outputText value="Empleo Anterior: " id="outEmplAnt" styleClass="texto" />
										<h:inputText id="inEmplAnt" value="#{IndividuoEvaluacionBean.empleoAnterior}" size="50" maxlength="100" styleClass="bordeceldatext" style="width: 150px" onfocus="encender(this);" onblur="apagar(this);" />
										
										<h:outputText value="Teléfono: " id="outTelefonoLaboral" styleClass="texto" />
										<h:inputText id="inTelefonoLaboral" value="#{IndividuoEvaluacionBean.telEmpleoAnt}" size="15" maxlength="50" styleClass="bordeceldainferior" style="width: 150px" onfocus="encender(this);" onblur="apagar(this);" />
									</h:panelGrid>
									
									<h:panelGrid columns="4" id="panelReferencia" width="650" align="center"> 
										<h:outputText value="Referencia Comercial: " id="outRefComer" styleClass="texto" />
										<h:inputText id="inRefCom" value="#{IndividuoEvaluacionBean.referencias}" size="50" maxlength="200" styleClass="bordeceldatext" style="width: 150px" onfocus="encender(this);" onblur="apagar(this);" />
											
										<f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim>
										<f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim>
										
									</h:panelGrid>
								</s:fieldset>
								</h:panelGrid>
								
								<h:panelGrid columns="1" id="panelIternoOtrosIngresosUno" width="850" align="center">
								<%-- Otros Ingresos--%>
								<s:fieldset legend="Otros Ingresos" id="ingresos">
									<h:panelGrid columns="4" id="panelIternoOtrosIngresos" width="650" align="center">
										
										<h:outputText value="Descripción: " id="outOI" styleClass="texto" />
										<h:inputText id="inDesc" value="#{IndividuoEvaluacionBean.otrosIngresosDesc}" size="50" maxlength="200" styleClass="bordeceldatext" style="width: 150px" onfocus="encender(this);" onblur="apagar(this);" />
										
										<h:outputText value="Monto: " id="outMonto" styleClass="texto" />
										<h:inputText id="inMonto" value="#{IndividuoEvaluacionBean.otrosIngresosMonto}" size="6" maxlength="5" styleClass="bordeceldainferior" style="width: 150px" onfocus="encender(this);" onblur="apagar(this);" onkeypress="return soloDecimales(this,event);" />
										
									</h:panelGrid>
								</s:fieldset>
							</h:panelGrid>
					</s:layoutingTitlePane>
					
					<f:verbatim><br></f:verbatim>
					<%-- Datos para la Factura --%>
					<s:layoutingTitlePane id="paraFacturacion" label="#{IndividuoEvaluacionBean.tituloPanleSeis}" containerNodeClass="contentTitlePane" labelNodeClass="labelTitlePane" value="false" rendered="#{IndividuoEvaluacionBean.panelA}">
						<h:panelGrid columns="1" id="panelParaFacturacion" align="center" width="850">
						
							<h:panelGrid columns="4" id="interno" align="center" width="650">
							
								<h:outputText value="Domicilio Envio:" id="outDomicilioEnvio" styleClass="texto" />
								<h:selectOneMenu id="lstDomEnv" value="#{IndividuoEvaluacionBean.idDomicilioPagoSeleccionado}" styleClass="lista" 
										immediate="true" onfocus="encender(this);" onblur="apagar(this);" style="width: 200px" 
										binding="#{IndividuoEvaluacionBean.diasPagos}" valueChangeListener="#{IndividuoEvaluacionBean.cambiarDiasDePago}" 
										onchange="setValueId('lstDiaPago','idFoco'); submit();">
									<f:selectItems value="#{IndividuoEvaluacionBean.listDomicilioPago}" id="selctItemListaDomPag" />
								</h:selectOneMenu>
								
								<h:outputText value="Día de Cierre: " id="outDiaPago" styleClass="texto" />
								<h:selectOneMenu id="lstDiaPago" value="#{IndividuoEvaluacionBean.idDiaPagoSeleccionado}" styleClass="lista" immediate="true" onfocus="encender(this);" onblur="apagar(this);" style="width: 200px" onchange="javascript:setValueId('lstBanco','idFoco');">
									<f:selectItems value="#{IndividuoEvaluacionBean.listDiaPago}" id="selectItemDiaPag" />
								</h:selectOneMenu>
								
							</h:panelGrid>
						</h:panelGrid>
					</s:layoutingTitlePane>
					
					
					<c:if test="${IndividuoEvaluacionBean.panelB}">
						<f:verbatim><br></f:verbatim>
					</c:if>
					
					<s:layoutingTitlePane id="datosPropiedad" label="#{IndividuoEvaluacionBean.tituloPanleOcho}"
							containerNodeClass="contentTitlePane" labelNodeClass="labelTitlePane" 
							rendered="#{IndividuoEvaluacionBean.panelB}"> 
						<h:panelGrid columns="1" id="panelInternoCinco" width="850" align="center">
						
							<s:fieldset legend="Domicilio Inmueble" id="datosInmuebles">
								<h:panelGrid columns="2" id="panelDomicilioInmueble" width="650" align="center">
									<h:panelGroup id="panelGroupTable">
									<h:dataTable value="#{IndividuoEvaluacionBean.listDomicilioInmueble}"
										id="tablaDomicilioInmuebles" styleClass="standardTable" headerClass="dataTable_Header"
										footerClass="standardTable_Header" rowClasses="standardTable_Row1,standardTable_Row2"
										columnClasses="standardTable_Column,standardTable_ColumnCentered,standardTable_Column"
										var="domicilioInmueble" style=" width : 570px;">
										
										<h:column>
											<f:facet name="header">
												<h:outputText value="Calle" styleClass="texto" style="font: bold;color: white;" id="inmueblreCalle"/>
											</f:facet>
											<h:outputText value="#{domicilioInmueble.domicilio.calleNombre} " style=" width : 190px;" />
										</h:column>
										
										<h:column>
											<f:facet name="header">
												<h:outputText value="Número" styleClass="texto" style="font: bold;color: white;" id="inmueblreNro"/>
											</f:facet>
											<h:outputText value="#{domicilioInmueble.domicilio.calleNumero}" style=" width : 40px;" />
										</h:column>
										
										<h:column>
											<f:facet name="header">
												<h:outputText value="Barrio" styleClass="texto" style="font: bold;color: white;" id="inmueblreBarrio"/>
											</f:facet>
											<h:outputText value="#{domicilioInmueble.domicilio.barrio.descripcion}" style=" width : 40px;" />
										</h:column>
										
										<h:column>
											<f:facet name="header">
												<h:outputText value="Localidad" styleClass="texto" style="font: bold;color: white;" id="inmueblreLocalidad"/>
											</f:facet>
											<h:outputText value="#{domicilioInmueble.domicilio.localidad.nombre}" style=" width : 40px;" />
										</h:column>
										
										<h:column>
											<x:commandLink action="#{IndividuoEvaluacionBean.editarDomicilioInmueble}" id="editarDomicilioInmueblesLink" disabled="#{IndividuoEvaluacionBean.botonDomicilio}" onclick="javascript:setValueId('datosInmuebles','idFoco');">
												<f:param id="idDomiEdi" name="idDomiEdi" value="#{domicilioInmueble.domicilio.idDomicilio}" />
												<x:graphicImage value="/img/editar.gif" title="Editar el domicilio Inmueble." border="0" id="botnoImagenCinco"/>
											</x:commandLink>
										</h:column>

										<h:column>
											<x:commandLink action="#{IndividuoEvaluacionBean.eliminarDomicilioInmueble}" id="eliminarDomicilioInmueblrsLink" disabled="#{IndividuoEvaluacionBean.botonDomicilio}">
												<f:param id="idDomicilio" name="idDomicilio" value="#{domicilioInmueble.domicilio.idDomicilio}"/>
												<x:graphicImage value="/img/cat_act.gif" title="Eliminar un domicilio Inmueble." border="0" id="botonImagenSeis" />
											</x:commandLink>
										</h:column>
										
									</h:dataTable>
									</h:panelGroup>
									
									<h:panelGroup id="panelGroupagregarDom"> 
										<x:commandLink id="agregarDomicilioInmueblesLink" action="#{IndividuoEvaluacionBean.agregarDomicilioInmueble}" disabled="#{IndividuoEvaluacionBean.botonDomicilio}">
											<x:graphicImage value="/img/icon/home_24.gif" title="Agregar domicilios inmueble." border="0" id="botonImagenSiete"/>
										</x:commandLink>
									</h:panelGroup>

								</h:panelGrid>
							</s:fieldset>
							
							<s:fieldset legend="Vehiculo" id="vehiculo">
								<h:panelGrid columns="4" id="panelVahiculo" width="650" align="center">
									
									<h:outputText value="Marca: " id="outMarca" styleClass="texto"/>
									<h:inputText id="inMarca" value="#{IndividuoEvaluacionBean.individuoVehiculo.vehiculo.marca}" size="50" maxlength="100" 
										styleClass="bordeceldatext" style="width: 150px" onfocus="encender(this);" onblur="apagar(this);"/>
									
									<h:outputText value="Modelo: " id="outModelo" styleClass="texto"/>
									<h:inputText id="inModelo" value="#{IndividuoEvaluacionBean.individuoVehiculo.vehiculo.modelo}" size="50" maxlength="100" 
										styleClass="bordeceldatext" style="width: 150px" onfocus="encender(this);" onblur="apagar(this);"/>
									
									<h:outputText value="Año: " id="outAnio" styleClass="texto"/>
									<h:inputText id="inAnio" value="#{IndividuoEvaluacionBean.individuoVehiculo.vehiculo.anio}" size="4" maxlength="4" 
										styleClass="bordeceldainferior" style="width: 50px" onfocus="encender(this);" onblur="apagar(this);"/>
									
									<h:outputText value="Patente: " id="outPatente" styleClass="texto"/>
									<h:inputText id="inPatente" value="#{IndividuoEvaluacionBean.individuoVehiculo.vehiculo.patente}" size="7" maxlength="7" 
										styleClass="bordeceldatext" style="width: 150px" onfocus="encender(this);" onblur="apagar(this);"/>
										
									<h:outputText value="Es Propio: " id="outPropio" styleClass="texto"/>
									<h:selectBooleanCheckbox value="#{IndividuoEvaluacionBean.vehiculoPropio}" id="vehiculoPropio" immediate="true"/>
									
									<f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim>
									<f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim>
									
								</h:panelGrid>
							</s:fieldset>
						</h:panelGrid>
					</s:layoutingTitlePane>
					
					<c:if test="${IndividuoEvaluacionBean.panelC}">
						<f:verbatim><br></f:verbatim>
					</c:if>
					
					<s:layoutingTitlePane id="datosFinancieros" label="#{IndividuoEvaluacionBean.tituloPanleNueve}"
							containerNodeClass="contentTitlePane" labelNodeClass="labelTitlePane"
						 	rendered="#{IndividuoEvaluacionBean.panelC}">
						<h:panelGrid columns="1" id="panelInternoSeis" width="850" align="center">
						
							<s:fieldset legend="Cuenta" id="cuenta">
								<h:panelGrid columns="4" id="internoTres" align="center" width="650">
									<h:outputText value="Banco: " id="outBanco" styleClass="texto" />
									<h:selectOneMenu id="lstBanco" value="#{IndividuoEvaluacionBean.idBcoSeleccionado}" styleClass="lista" immediate="true" onfocus="encender(this);" onblur="apagar(this);" style="width: 200px" disabled="#{IndividuoEvaluacionBean.mostrarCbu}" binding="#{IndividuoEvaluacionBean.bancoSeleccionado}" onchange="javascript:setValueId('lstCuenta','idFoco'); submit();">
										<f:selectItems value="#{IndividuoEvaluacionBean.listBancos}" id="selctItemIdBcoSeleccionado" />
									</h:selectOneMenu>
									<f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim>
									<f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim>
									<h:outputText value="Cuenta: " id="outCuenta" styleClass="texto" />
									<h:selectOneMenu id="lstCuenta" value="#{IndividuoEvaluacionBean.idTipoCuentaSeleccionado}" styleClass="lista" immediate="true" onfocus="encender(this);" onblur="apagar(this);" style="width: 200px" disabled="#{IndividuoEvaluacionBean.mostrarCbu}" onchange="javascript:setValueId('inNroCuenta','idFoco');">
										<f:selectItems value="#{IndividuoEvaluacionBean.listTipoCtas}" id="selctItemIdDiaPagoSeleccionado" />
									</h:selectOneMenu>
								<h:outputText value="Nro. Cuenta: " id="outNroCuenta" styleClass="texto" />
								<h:inputText id="inNroCuenta" value="#{IndividuoEvaluacionBean.individuoEvaluacion.nroCuenta}" styleClass="bordeceldainferior" maxlength="20" style="width: 150" onfocus="encender(this);" onblur="apagar(this);" onkeypress="return soloEnteros(this,event);" size="20" disabled="#{IndividuoEvaluacionBean.mostrarCbu}" onchange="javascript:setValueId('cbu','idFoco');" />
								<h:outputText value="CBU: " styleClass="texto" style="COLOR: #000000; font: bold;" id="outCBU" />
								<h:selectBooleanCheckbox id="cbu" value="#{IndividuoEvaluacionBean.esCBU}" onclick="javascript:setValueId('inNroCBU','idFoco'); submit();" />
								<h:outputText value="Nro. CBU: " id="outNroCBU" styleClass="texto" />
								<x:inputText id="inNroCBU" value="#{IndividuoEvaluacionBean.individuoEvaluacion.cbu}" size="22" maxlength="22" styleClass="bordeceldainferior" style="width: 150px" onfocus="encender(this);" onblur="apagar(this);" disabled="#{!IndividuoEvaluacionBean.mostrarCbu}" onchange="javascript:setValueId('inCuit','idFoco');" onkeypress="return soloEnteros(this,event);" />
								</h:panelGrid>
							</s:fieldset>

							<s:fieldset legend="Vinculo Banco" id="vinculoBco">
								<h:panelGrid columns="4" id="panelBancos" width="650" align="center">
									<h:outputText value="Banco: " id="outBco" styleClass="texto"/>
									<h:outputText value="#{IndividuoEvaluacionBean.nombreBanco}" id="inBco" styleClass="textoblue"
										binding="#{IndividuoEvaluacionBean.textBanco}"/>
									<h:outputText value="Vinculación: " id="outVinc" styleClass="texto"/>
									<h:inputText id="inVinculo" value="#{IndividuoEvaluacionBean.bancos.vinculacion}" size="50" maxlength="100" 
										styleClass="bordeceldatext" style="width: 150px" onfocus="encender(this);" 
										onblur="apagar(this);"/>
								</h:panelGrid>
							</s:fieldset>
							
							<s:fieldset legend="Tarjeta" id="tarjeta">
								<h:panelGrid columns="4" id="panelTarjetas" width="650" align="center">
									<h:outputText value="Tarjeta: " id="outTarjeta" styleClass="texto"/>
									<h:inputText id="inTarj" value="#{IndividuoEvaluacionBean.tarjeta.descripcion}" size="50" maxlength="50" 
										styleClass="bordeceldatext" style="width: 150px" onfocus="encender(this);" 
										onblur="apagar(this);"/>
									<h:outputText value="Número: " id="outNroTarj" styleClass="texto"/>
									<h:inputText id="inNroTarj" value="#{IndividuoEvaluacionBean.tarjeta.nro}" size="20" maxlength="16" 
										styleClass="bordeceldainferior" style="width: 150px" onfocus="encender(this);" 
										onblur="apagar(this);" onkeypress="return soloEnteros(this,event);"/>
									<h:outputText value="Banco: " id="outBancos" styleClass="texto"/>
									<h:selectOneMenu id="lstBancos" value="#{IndividuoEvaluacionBean.idBancoSeleccionado}"
											styleClass="lista" immediate="true" onfocus="encender(this);"
											onblur="apagar(this);" style="width: 200px" disabled=" ">
											<f:selectItems value="#{IndividuoEvaluacionBean.listBancos}" id="selctItemIdBancoSeleccionado"/>
									</h:selectOneMenu>
									<f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim>
									<f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim>
								</h:panelGrid>
							</s:fieldset>
						</h:panelGrid>
						
					</s:layoutingTitlePane>
					
					<c:if test="${IndividuoEvaluacionBean.panelD}">
						<f:verbatim><br></f:verbatim>
					</c:if>
					
					<s:layoutingTitlePane id="datosObservaciones" label="#{IndividuoEvaluacionBean.tituloPanleDiez}"
							containerNodeClass="contentTitlePane" labelNodeClass="labelTitlePane">
						<h:panelGrid columns="2" id="panelInternoDiez" width="650" align="center">
							
							<h:outputText value="Observación: " id="outObservaciones" styleClass="texto"/>
							<x:inputTextarea id="inObservaciones" value="#{IndividuoEvaluacionBean.individuoEvaluacion.observacion}" 
								styleClass="bordeceldatext" style="width : 400px; height : 50px;" onfocus="encender(this);" 
								onblur="apagar(this);"/>
							
							<h:outputText value="Observación para informe ambiental: " rendered="false" id="outOPIA" styleClass="texto"/>
							<h:inputText id="inOPIA" value="#{IndividuoEvaluacionBean.informeParticular.observacion}" maxlength="500" 
								styleClass="bordeceldatext" style="width: 400px" rendered="false" onfocus="encender(this);" 
								onblur="apagar(this);"/>
							
							<h:outputText value="Observación para llamado Telefónico: " id="outOPLT" rendered="false" styleClass="texto"/>
							<h:inputText id="inOPLT" value="#{IndividuoEvaluacionBean.confirmacionTelefonica.observacion}" maxlength="500" 
								styleClass="bordeceldatext" style="width: 400px" rendered="false" onfocus="encender(this);" 
								onblur="apagar(this);"/>
							
						</h:panelGrid>
					</s:layoutingTitlePane>
					
					<f:verbatim><br></f:verbatim>
					
					<s:layoutingTitlePane id="datosDocDig" label="#{IndividuoEvaluacionBean.tituloPanleOnce}"
							containerNodeClass="contentTitlePane" labelNodeClass="labelTitlePane"
						 	rendered="#{IndividuoEvaluacionBean.panelK}">
						<h:panelGrid columns="4" id="panelInternoOnce" width="650" align="center">
						
							<h:outputText value="Tipo Documentos: " id="outTDoc" styleClass="texto"/>
							<h:selectOneMenu id="lstTDocs" value="#{IndividuoEvaluacionBean.idTipoDocSeleccionado}"
								styleClass="lista" immediate="true" onfocus="encender(this);"
								onblur="apagar(this);" style="width: 200px" disabled=" ">
								<f:selectItems value="#{IndividuoEvaluacionBean.listTipoDigital}" id="selectItemLstDocs"/>
							</h:selectOneMenu>
							
							<h:outputText value="Archivo: " id="outArch" styleClass="texto"/>

							<x:inputFileUpload id="fileUpLoad" storage="file" styleClass="fileUploadInput" maxlength="1000"
								value="#{IndividuoEvaluacionBean.uploadedFile}" onfocus="encender(this);" onblur="apagar(this);" disabled="#{IndividuoEvaluacionBean.botonAdjuntar}"/>
														
							<h:outputText value="Descripción: " id="outDesc" styleClass="texto"/>
							<h:inputText id="inDescrip" value="#{IndividuoEvaluacionBean.docDigital.descripcion}" maxlength="200" 
								styleClass="bordeceldatext" style="width: 150px" onfocus="encender(this);" 
								onblur="apagar(this);"/>
							<f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim>
							
							<h:commandButton value="Adjuntar" action="#{IndividuoEvaluacionBean.saveFile}" 
								styleClass="botones" id="btonAdjuntarTDocLink" disabled="#{IndividuoEvaluacionBean.botonAdjuntar}" onclick="javascript:setValueId('lstTDocs','idFoco');"/>
						</h:panelGrid>
						<h:panelGrid columns="1" width="650" align="center" id="panelListDocAdj">
						<s:fieldset legend="Lista Documentos Adjuntos" id="listaDocAdj">
							<h:panelGrid columns="1" id="panelAdjuntos" width="650" align="center">
							<h:dataTable value="#{IndividuoEvaluacionBean.listTipoDocumentos}"
								id="tablaDocAdj" styleClass="standardTable" headerClass="dataTable_Header"
								footerClass="standardTable_Header" rowClasses="standardTable_Row1,standardTable_Row2"
								columnClasses="standardTable_Column,standardTable_ColumnCentered,standardTable_Column"
								var="listDocAdj" style=" width : 570px;">
										
									<h:column>
										<f:facet name="header">
											<h:outputText value="Tipo Documento" styleClass="texto" style="font: bold;color: white;" />
										</f:facet>
										<h:outputText value="#{listDocAdj.tipoDocumento}" style=" width : 150px;" styleClass="texto"/>
									</h:column>
										
									<h:column>
										<f:facet name="header">
											<h:outputText value="Archivo" styleClass="texto" style="font: bold;color: white;"/>
										</f:facet>
										<h:commandLink value="#{listDocAdj.nombreArchivo}" action="#{IndividuoEvaluacionBean.abrirArchivo}">
										      <f:param id="idArchiAbrir" name="idArchiAbrir" value="#{listDocAdj.idWrappers}"/>
										</h:commandLink>
									</h:column>
										
									<h:column>
										<f:facet name="header">
											<h:outputText value="Descripción" styleClass="texto" style="font: bold;color: white;"/>
										</f:facet>
											<h:outputText value="#{listDocAdj.descripcion}" styleClass="texto" style="width: 150px" />
									</h:column>
										
									<h:column>
										<f:facet name="header">
											<h:outputText value="Fecha" styleClass="texto" style="font: bold;color: white;"/>
										</f:facet>
											<h:outputText value="#{listDocAdj.timestamp}" styleClass="texto" style="width: 150px" />
									</h:column>

									<h:column>	
									    <h:commandLink action="#{IndividuoEvaluacionBean.borrarArchivo}">
										      <f:param id="idArchi" name="idArchi" value="#{listDocAdj.idWrappers}"/>
											  <x:graphicImage value="/img/borrar.gif" title="Eliminar archivo." border="0" id="botonImagenocho"/>
										</h:commandLink>
									</h:column>	
									</h:dataTable>
																
							</h:panelGrid>
						</s:fieldset>
						</h:panelGrid>
					</s:layoutingTitlePane>
					
					<c:if test="${IndividuoEvaluacionBean.panelF}">
						<f:verbatim><br></f:verbatim>
					</c:if>
					
					<s:layoutingTitlePane id="datosAltas" label="#{IndividuoEvaluacionBean.tituloPanleDoce}"
							containerNodeClass="contentTitlePane" labelNodeClass="labelTitlePane"
						 	rendered="#{IndividuoEvaluacionBean.panelF}">
						
						<h:panelGrid columns="1" width="650" align="center" id="panelAltaGA">
						<s:fieldset legend="Garante" id="garante">
							<h:panelGrid columns="1" width="650" align="center" id="panelInternoAltaGarante">
			
								<h:commandButton value="Alta Garante" action="#{IndividuoEvaluacionBean.altaGarante}" styleClass="botones" id="btnAltaGaranteLink"/>
								
								<c:if test="${!empty IndividuoEvaluacionBean.listGarante}">
									<h:dataTable value="#{IndividuoEvaluacionBean.listGarante}"
										id="tablaUno" styleClass="standardTable" 
										headerClass="dataTable_Header"
										footerClass="standardTable_Header" 
										rowClasses="standardTable_Row1,standardTable_Row2"
										columnClasses="standardTable_Column,standardTable_ColumnCentered,standardTable_Column"
										var="garante" style=" width : 300px;">

										<h:column>
											<f:facet name="header">
												<h:outputText value="Apellido" styleClass="texto" style="font: bold;color: white;" />
											</f:facet>
											<h:outputText value="#{garante.apellido}"/>
										</h:column>
										
										<h:column>
										    <f:facet name="header">
										        <h:outputText value="Nombre" styleClass="texto" style="font: bold;color: white" />
										    </f:facet>
                                            <h:outputText value="#{garante.nombres}" />
										</h:column>
										
										<h:column>
										    <h:commandButton action="#{IndividuoEvaluacionBean.editarGarante}" id="editarGaranteLink"
										     image="/img/editar.gif" title="Editar el Garante.">
												<f:param id="idGaranteEdi" name="idGaranteEdi" value="#{garante.idIndividuo}" />
											</h:commandButton>
										</h:column>

										<h:column>
											<h:commandButton action="#{IndividuoEvaluacionBean.eliminarGarante}" image="/img/cat_act.gif" 
												id="eliminarGaranteLink" onclick="return confirm('¿Confirma la eliminación del Garante?');">
												<f:param id="idGaranteElim" name="idGaranteElim" value="#{garante.idIndividuo}"/>
											</h:commandButton>
										</h:column>
										
									</h:dataTable>
								</c:if>
			
							</h:panelGrid>
						</s:fieldset>
					
						<s:fieldset legend="Adicional" id="adicional">
							<h:panelGrid columns="1" width="650" align="center" id="panelAltaAdicional">
			
								<h:commandButton value="Alta Adicional" action="#{IndividuoEvaluacionBean.altaAdicional}" styleClass="botones" id="btnAltaAdicionalLink"/>
		
							    	<c:if test="${!empty IndividuoEvaluacionBean.listAdicional}">
									<h:dataTable value="#{IndividuoEvaluacionBean.listAdicional}" headerClass="dataTable_Header"
										footerClass="standardTable_Header" rowClasses="standardTable_Row1,standardTable_Row2"
										columnClasses="standardTable_Column,standardTable_ColumnCentered,standardTable_Column"
										id="tablaDos" styleClass="standardTable"
										var="adicional" style=" width : 300px;">

										<h:column>
											<f:facet name="header">
												<h:outputText value="Apellido" styleClass="texto" style="font: bold;color: white;" />
											</f:facet>
											<h:outputText value="#{adicional.apellido}"/>
										</h:column>
										
										<h:column>
											<f:facet name="header">
												<h:outputText value="Nombre" styleClass="texto" style="font: bold;color: white;" />
											</f:facet>
											<h:outputText value="#{adicional.nombres}"/>
										</h:column>
										
										<h:column>
											<x:commandLink action="#{IndividuoEvaluacionBean.editarAdicional}" id="editarAdicionalLink" >
												<f:param id="idAdicionalEdi" name="idAdicionalEdi" value="#{adicional.idIndividuo}" />
												<x:graphicImage value="/img/editar.gif" title="Editar el Adicional." border="0"/>
											</x:commandLink>
										</h:column>

										<h:column>
											<x:commandLink action="#{IndividuoEvaluacionBean.eliminarAdicional}" id="eliminarAdicionalLink">
												<f:param id="idAdicionalElim" name="idAdicionalElim" value="#{adicional.idIndividuo}"/>
												<x:graphicImage value="/img/cat_act.gif" title="Eliminar el adicional." border="0"
													onclick="return confirm('¿Confirma la eliminación del Adicional?');"/>
											</x:commandLink>
										</h:column>										
									</h:dataTable>
								</c:if>
			
						</h:panelGrid>
						</s:fieldset>
					
						</h:panelGrid>
					</s:layoutingTitlePane>

					<c:if test="${IndividuoEvaluacionBean.panelE}">
						<f:verbatim><br></f:verbatim>
					</c:if>
					
					<s:layoutingTitlePane id="datosGenAsigInf" label="#{IndividuoEvaluacionBean.tituloPanleTrece}"
							containerNodeClass="contentTitlePane" labelNodeClass="labelTitlePane"  rendered="#{IndividuoEvaluacionBean.panelE}">

							<h:panelGrid columns="3" width="650" align="center" id="gridUno">
								
								<h:outputText value="Informe Ambiental Particular" id="outInfAmb" styleClass="texto"/>
								<h:selectOneMenu id="lstIAP" value="#{IndividuoEvaluacionBean.idInformeParticular}"
									styleClass="lista" immediate="true" onfocus="encender(this);" onblur="apagar(this);" style="width: 200px"
									disabled="#{IndividuoEvaluacionBean.particular}">
									<f:selectItems value="#{IndividuoEvaluacionBean.listaVerificadores}" id="selectItemParticular"/>
								</h:selectOneMenu>
								<h:commandButton value="Asignar" action="#{IndividuoEvaluacionBean.gestionarInformeParticular}" 
									styleClass="botones" id="btnGestInfParticularLink" disabled="#{IndividuoEvaluacionBean.particular}"/>
							
								<h:outputText value="Informe Ambiental Laboral" id="outInfAmbLab" styleClass="texto"/>
								<h:selectOneMenu id="lstIAL" value="#{IndividuoEvaluacionBean.idInformeLaboral}"
									styleClass="lista" immediate="true" onfocus="encender(this);" onblur="apagar(this);" style="width: 200px"
									disabled="#{IndividuoEvaluacionBean.laboral}">
									<f:selectItems value="#{IndividuoEvaluacionBean.listaVerificadores}" id="selectItemLaboral"/>
								</h:selectOneMenu>
								<h:commandButton value="Asignar" action="#{IndividuoEvaluacionBean.gestionarInformeLaboral}" styleClass="botones" id="btnGestInfoLaboralLink" 
									disabled="#{IndividuoEvaluacionBean.laboral}" />

							</h:panelGrid>
											
					</s:layoutingTitlePane>	
					<c:if test="${IndividuoEvaluacionBean.panelE}">
						<f:verbatim><br></f:verbatim>
					</c:if>

				</c:if>
					<f:verbatim>
					<br>
					</f:verbatim>				
					
					<f:verbatim><hr align="center" width="900"></f:verbatim>
					
					<h:panelGrid columns="10" id="panelFinal" width="900" align="rigth">
						
						<f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim>
						<f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim>
						<f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim>
						<f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim>
						<f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim>
						<f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim>
						<f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim>
						<f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim>
						<c:if test="${IndividuoEvaluacionBean.origen != 4}">
							<x:commandButton id="buttonGrabar" value="Guardar" action="#{IndividuoEvaluacionBean.guardarSolicitud}"
							styleClass="botones" disabled="#{!IndividuoEvaluacionBean.opcionA}"/> 
						</c:if>
						<c:if test="${IndividuoEvaluacionBean.origen == 4}">
							<f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim>
						</c:if>
						<x:commandButton id="buttonCancelar" value="Salir" action="#{IndividuoEvaluacionBean.salirSolicitud}" 
							styleClass="botones"/>
					</h:panelGrid>
					
					<x:inputHidden id="idFoco" value="#{IndividuoEvaluacionBean.focoHidden}" forceId="true"/>
					<s:focus id="foco" for="#{IndividuoEvaluacionBean.focoHidden}" />
				</h:panelGrid>
							
				</h:panelGroup>
			</f:facet>

			<%@include file="/inc/page_footer.jsp"%>

		</x:panelLayout>
	</h:form></center>
	</body>
	</html>
</f:view>
