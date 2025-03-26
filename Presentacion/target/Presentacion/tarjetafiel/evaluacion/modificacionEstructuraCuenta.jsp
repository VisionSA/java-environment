<%@include file="/inc/tags.jsp"%>
<jsp:useBean id="ahora" class="java.util.Date" scope="request" />

<f:view>
	<html>
	<head>
	<title><h:outputText value="#{ModificacionEstructuraCuentaBean.tituloLargo}" /></title>
	<s:script language="javascript">
    	function recargar() {
    		document.getElementById('modificacionEstructuraForm').submit();
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






	<body onbeforeunload="ShowWait('modificacionEstructuraForm');" 
	        onload="if('${ModificacionEstructuraCuentaBean.popup.mostrar}'=='true') {viewDialog.show();} 
	                   else {window.scrollTo(0,${ScrollBean.hiddenScrollY});}">


	<center>
	<h:form id="modificacionEstructuraForm" enctype="multipart/form-data">

		<%-- GRABA EL ESTADO DEL SCROLL --%>
		<h:panelGroup rendered="#{!ModificacionEstructuraCuentaBean.popup.mostrar}">
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
						    	<td width="351" height="47" align="center" class="titulo"> ${ModificacionEstructuraCuentaBean.tituloLargo} <br>
					    	    	<span class="subtitulo"> ${ModificacionEstructuraCuentaBean.tituloCorto} </span></td>
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
					<s:modalDialog dialogId="viewDialog" dialogVar="viewDialog" styleClass="viewDialog" dialogTitle="#{ModificacionEstructuraCuentaBean.tituloCorto}">
					    	
					    	<h:panelGrid columns="3" width="500">
					    		<f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim>
						    	<x:graphicImage value="/img/#{ModificacionEstructuraCuentaBean.popup.nombreIcono}" />
						        <h:outputText value=" #{ModificacionEstructuraCuentaBean.popup.mensaje}" styleClass="texto"/>
					        </h:panelGrid>	  
					        
						        
					        <h:panelGrid columns="3" width="500">
					        	<f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim>
					        	<x:commandButton action="#{ModificacionEstructuraCuentaBean.irAContinuar}" onclick="clickLink('continuarCarga');dojo.widget.byId('viewDialog').hide();"
					         		 value="Continuar" styleClass="botones" title="Continuar con el alta del individuo."/>
								<f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim>
								
							</h:panelGrid>
					</s:modalDialog>
					
					
					
                	
				    <x:commandLink id="continuarCarga" action="#{ModificacionEstructuraCuentaBean.irAContinuar}" forceId="true" style="display: block;"/>
                    <x:commandLink id="traerIndividuo" action="#{ModificacionEstructuraCuentaBean.traerIndividuo}" forceId="true" style="display: block;"/>
                    <x:commandLink id="buscarOtroIndividuo" action="#{ModificacionEstructuraCuentaBean.buscarOtroIndividuo}" forceId="true" style="display: block;"/>
                	
					<h:panelGrid columns="1" align="center" id="panlePrincipal" width="900px">

					<%-- Datos Solicitud  --%>
						<s:layoutingTitlePane id="solicitud" label="#{ModificacionEstructuraCuentaBean.tituloPanleUno}"
							containerNodeClass="contentTitlePane" labelNodeClass="labelTitlePane" rendered="false">
							<h:panelGrid columns="4" id="panelSecundarioUno" align="center" width="650">
								<h:outputText id="outNroSolDiv" value="Nro. Solicitud y Nro. DIV: " />
								<x:inputText id="inNroSolDiv" value="#{ModificacionEstructuraCuentaBean.nroSolicitud}" forceId="true" 
									maxlength="8" styleClass="bordeceldainferior" style="width: 150px" onfocus="encender(this);"
									onblur="apagar(this);" onkeypress="return soloEnteros(this,event);" disabled="true"
									onchange="javascript:setValueId('inApellido','idFoco');"/>
							
								<h:outputText id="outPromotor" value="Promotor: " styleClass="texto" />
								<h:outputText id="outPromotorNro" value="#{ModificacionEstructuraCuentaBean.nombrePromotor}" styleClass="textoblue"/>

								<h:outputText id="outCaja" value="Caja: " styleClass="taxto" />
								<h:outputText id="caja" value="#{ModificacionEstructuraCuentaBean.solicitud.caja}" styleClass="textoblue"/>

								<h:outputText id="outFolio" value="Folio: " styleClass="taxto" />
								<h:outputText id="folio" value="#{ModificacionEstructuraCuentaBean.solicitud.folio}" styleClass="textoblue"/>
								
								<h:panelGroup>
									<h:outputText id="outFechaAlta" value="Fecha Alta: " styleClass="texto" />
									<f:verbatim>&nbsp;&nbsp;</f:verbatim>
									<c:if test="${ModificacionEstructuraCuentaBean.solicitud.fechaRecepcion != null}">
										<f:verbatim>
											<font style="COLOR: #0000ff;">
											<fmt:formatDate value="${ModificacionEstructuraCuentaBean.solicitud.fechaRecepcion}" pattern="dd/MM/yyyy" />
											</font>
										</f:verbatim>
									</c:if>
								</h:panelGroup>
								<f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim>
								
								<c:if test="${ModificacionEstructuraCuentaBean.origen != 4}">
									<x:commandButton id="buttonGrabar1" value="Guardar" action="#{ModificacionEstructuraCuentaBean.guardarSolicitud}"
									styleClass="botones" disabled="#{!ModificacionEstructuraCuentaBean.opcionA}"/> 
								</c:if>
								<c:if test="${ModificacionEstructuraCuentaBean.origen == 4}">
									<f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim>
								</c:if>
								<x:commandButton id="buttonCancelar1" value="Salir" action="#{ModificacionEstructuraCuentaBean.salirSolicitud}" 
									styleClass="botones"/>

							</h:panelGrid>
						</s:layoutingTitlePane>
						
						<f:verbatim><br></f:verbatim>

						<%-- Observaciones Generales Solicitud  --%>

						<s:layoutingTitlePane id="observacionesGenerales" label="Observaciones Generales de la solicitud" rendered="#{ModificacionEstructuraCuentaBean.panelD}"
							containerNodeClass="contentTitlePane" labelNodeClass="labelTitlePane">
							<h:panelGrid columns="2" id="panelDeObservacionesUno" align="center" width="650">

								<h:outputText id="outUnoObs" value="Observación de la solicitud:" />
								<x:inputTextarea id="inUnoObs" value="#{ModificacionEstructuraCuentaBean.observacion}" 
								    styleClass="background:colorSuave" style="width : 400px; height : 50px;" onfocus="encender(this);"
									onblur="apagar(this);" onchange="setValueId('inDosObs','idFoco');"/>
									
								<h:outputText id="outCuatroObs" value="Observación del verificador :" />
								<x:inputTextarea id="inCuatroObs" value="#{ModificacionEstructuraCuentaBean.nota_cred}" 
								    styleClass="background:colorSuave" style="width : 400px; height : 50px;" onfocus="encender(this);"
									onblur="apagar(this);" onchange="setValueId('inDosObs','idFoco');"/>
							
								<h:outputText id="outDosObs" value="Confirmación telefonica Laboral:" styleClass="texto" rendered="false"/>
								<x:inputTextarea id="inDosObs" forceId="true" value="#{ModificacionEstructuraCuentaBean.confTelLaboral}"
									styleClass="background:colorSuave" style="width : 400px; height : 50px;" onfocus="encender(this);" 
									onblur="apagar(this);" onchange="setValueId('inTresObs','idFoco');" rendered="false"/>	

								<h:outputText id="outTresObs" value="Confirmación telefonica Particular:" styleClass="texto" rendered="false"/>
								<x:inputTextarea id="inTresObs" forceId="true" value="#{ModificacionEstructuraCuentaBean.confTelParticular}"
									styleClass="background:colorSuave" style="width : 400px; height : 50px;" onfocus="encender(this);" 
									onblur="apagar(this);" onchange="setValueId('inApellido','idFoco');" rendered="false"/>	

							</h:panelGrid>
						</s:layoutingTitlePane>
						
						
			<c:if test="${ModificacionEstructuraCuentaBean.opcionA}">
						<%-- Getión Datos Personales --%>

						<s:layoutingTitlePane id="datosPersonales" label="#{ModificacionEstructuraCuentaBean.tituloPanleDos}"
							containerNodeClass="contentTitlePane" labelNodeClass="labelTitlePane" rendered="#{ModificacionEstructuraCuentaBean.panelH}">
							
							<h:panelGrid columns="4" id="panelSecuandarioDos" align="center" width="650">
							
								<h:outputText id="outCuil" value="C.U.I.L.: " styleClass="texto" />
								<h:panelGroup id="panelRepetido">
									<x:inputText id="inNroCuil" forceId="true" value="#{ModificacionEstructuraCuentaBean.cuil}" size="11" disabled="true"
										maxlength="11" styleClass="bordeceldainferior" style=" width : 180px;" onfocus="encender(this);" onblur="apagar(this);"
										onkeypress="return soloEnteros(this,event);" onchange="javascript:setValueId('inMail','idFoco');"/>
									
									<f:verbatim>&nbsp;&nbsp;&nbsp;</f:verbatim>
																																
											<x:commandButton id="btnExisteIndividuo" value="Buscar Individuo" action="#{ModificacionEstructuraCuentaBean.buscarSiIndividuoExiste}" 
												title="Buscar Individuo" image="/img/icon/reload_page.png" alt="Buscar Individuo." rendered="#{ModificacionEstructuraCuentaBean.buscarIndividuo}" />
											
											<f:verbatim>&nbsp;&nbsp;</f:verbatim>
											
									
									<x:commandButton id="btnBuscar" value="Buscar" onclick="agregarTipoDomicilio.show();" rendered="false"
										action="" title="Busca el Promotor especificado." image="/img/icon/srch_24.gif"/>
								</h:panelGroup>
								<f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim>
								<f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim>

								<h:outputText id="outApellido" value="Apellido Paterno: " styleClass="texto" />
								<x:inputText id="inApellido" value="#{ModificacionEstructuraCuentaBean.individuoEvaluacion.apellido}" disabled="#{ModificacionEstructuraCuentaBean.habilitada}"  forceId="true" size="50"
									maxlength="100" styleClass="bordeceldatext" style="width: 150px" onfocus="encender(this);" onblur="apagar(this);"
									onchange="javascript:setValueId('inAMaterno','idFoco');"/>
									
								<h:outputText id="outAMaterno" value="Apellido Materno: " styleClass="texto" />
								<x:inputText id="inAMaterno" value="#{ModificacionEstructuraCuentaBean.individuoEvaluacion.apellidoMaterno}" forceId="true" size="50"
									maxlength="100" styleClass="bordeceldatext" style="width: 150px" onfocus="encender(this);" onblur="apagar(this);"
									onchange="javascript:setValueId('inNombre','idFoco');"/>
									 
								<h:outputText id="outNombre" value="Nombres: " styleClass="texto" />
								<x:inputText id="inNombre" value="#{ModificacionEstructuraCuentaBean.individuoEvaluacion.nombres}" disabled="#{ModificacionEstructuraCuentaBean.habilitada}" forceId="true" size="50"
									maxlength="100" styleClass="bordeceldatext" style="width: 150px" onfocus="encender(this);" onblur="apagar(this);"
									onchange="javascript:setValueId('lstTDoc','idFoco');"/>
								
								<f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim>
								<f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim>
								
								<h:outputText id="outTipoDNI" value="Tipo Documento: " styleClass="texto" />
								<h:selectOneMenu id="lstTDoc" value="#{ModificacionEstructuraCuentaBean.individuoEvaluacion.tipoDocumento.idTipoDocumento}"
									styleClass="lista" immediate="true" onfocus="encender(this);" onblur="apagar(this);" style="width: 200px"
									onchange="javascript:setValueId('inNroDoc','idFoco');">
									<f:selectItems value="#{ModificacionEstructuraCuentaBean.listTipoDni}" id="selectItemIdTipoDoc"/>
								</h:selectOneMenu>

								<h:outputText id="outNroDoc" value="Número: " styleClass="texto" />
								<x:inputText id="inNroDoc" forceId="true" value="#{ModificacionEstructuraCuentaBean.individuoEvaluacion.nroDocumento}" disabled="#{ModificacionEstructuraCuentaBean.habilitada}" size="20"
									maxlength="20" styleClass="bordeceldainferior" style="width: 150px" onfocus="encender(this);" onblur="apagar(this);"
									onkeypress="return soloEnteros(this,event);" onchange="javascript:setValueId('lstsexo','idFoco');"/>

								<h:outputText id="outSexo" value="Sexo: " styleClass="texto" />
								<h:selectOneMenu id="lstsexo" value="#{ModificacionEstructuraCuentaBean.idTipoSexoSeleccionado}"
									styleClass="lista" immediate="true" onfocus="encender(this);"
									onblur="apagar(this);" style="width: 200px" onchange="javascript:setValueId('fechaNac','idFoco');">
									<f:selectItems value="#{ModificacionEstructuraCuentaBean.listTipoSexo}" id="selectItemIdTipoSexo"/>
								</h:selectOneMenu>
			 					
			 					<h:outputText value="Fecha Nacimiento: " id="outFechaNac" styleClass="texto"/>
								<x:inputCalendar id="fechaNac" monthYearRowClass="yearMonthHeader" weekRowClass="weekHeader" 
			                		currentDayCellClass="currentDayCell" popupButtonStyleClass="standard_bold"
			                		value="#{ModificacionEstructuraCuentaBean.fechaNacimiento}" renderAsPopup="true"
					                styleClass="bordeceldainferior" style="width: 90px"
					                popupTodayString="#{example_messages['popup_today_string']}"
			        		        popupDateFormat="dd/MM/yyyy" popupWeekString="#{example_messages['popup_week_string']}"
					                helpText="DD/MM/YYYY" forceId="true" onchange="javascript:setValueId('inMail','idFoco');"/>
			 					
								<h:outputText id="outMail" value="Casilla Email: " styleClass="texto" />
								<x:inputText id="inMail" forceId="true" value="#{ModificacionEstructuraCuentaBean.email}" size="50"
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
														<h:outputText id="outTxtCalle" value="#{ModificacionEstructuraCuentaBean.individuoEvaluacion.domicilio.calleNombre}" styleClass="textoblue" />
														<h:outputText id="txtNumero" value="Número:" styleClass="texto"/>
														<h:outputText id="outTxtNumero" value="#{ModificacionEstructuraCuentaBean.individuoEvaluacion.domicilio.calleNumero}" styleClass="textoblue"/>
														 
														<h:outputText id="txtBarrio" value="Barrio:" styleClass="texto"/>
														<h:outputText id="outTxtBarrio" value="#{ModificacionEstructuraCuentaBean.individuoEvaluacion.domicilio.barrio.descripcion}" styleClass="textoblue"/>
														
														<h:outputText id="txtLocalidad" value="Localidad:" styleClass="texto"/>
														<h:outputText id="outTxtLocalidad" value="#{ModificacionEstructuraCuentaBean.individuoEvaluacion.domicilio.localidad.nombre}" styleClass="textoblue"/>
														
												</h:panelGrid>
												<x:commandLink id="agregarDomicilioLink" action="#{ModificacionEstructuraCuentaBean.agregarDomicilioPopup}" >
															<x:graphicImage value="/img/icon/home_24.gif" title="Agregar-Modificar domicilio." border="0" id="botonImagenUno"/>
												</x:commandLink>
												<x:commandLink id="eliminarDomicilioLink" action="#{ModificacionEstructuraCuentaBean.eliminarDomicilio}" >
															<x:graphicImage value="/img/quitar.png" title="Borra domicilio incorrecto." border="0" id="botonImagenElim"/>
												</x:commandLink>
											</h:panelGrid>
										</s:fieldset>							
							<%-- GESTIONAR TELEFONOS --%>

								<s:fieldset legend="Teléfonos" id="teledonos">
								<h:panelGrid columns="2" id="panelTelefono" width="650" align="center">
									<h:panelGroup id="panelGroupTelefono">
									<h:dataTable value="#{ModificacionEstructuraCuentaBean.listTelefono}"
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
											<x:commandLink action="#{ModificacionEstructuraCuentaBean.editarTelefono}" id="editarTelefonoLink" disabled="#{ModificacionEstructuraCuentaBean.botonTelefono}">
												<f:param id="idTelEdi" name="idTelEdi" value="#{individuoTelefono.telefono.idTelefono}" />
												<x:graphicImage value="/img/editar.gif" title="Editar el Teléfono." border="0" id="botonImagenDos" />
											</x:commandLink>
										</h:column>

										<h:column>
											<x:commandLink action="#{ModificacionEstructuraCuentaBean.eliminarTelefono}" id="eliminarTelefonoLink" disabled="#{ModificacionEstructuraCuentaBean.botonTelefono}">
												<f:param id="idTelefono" name="idTelefono" value="#{individuoTelefono.telefono.idTelefono}"/>
												<x:graphicImage value="/img/cat_act.gif" title="Eliminar un Teléfono." border="0" id="botonImagenTres" />
											</x:commandLink>
										</h:column>
										
									</h:dataTable>
									</h:panelGroup>
									
									<h:panelGroup id="panelGroupBotones">
										<x:commandLink id="agregarTelefonoLink" action="#{ModificacionEstructuraCuentaBean.agregarTelefono}" disabled="#{ModificacionEstructuraCuentaBean.botonTelefono}">
											<x:graphicImage value="/img/cat_pad.gif" title="Agregar Teléfono." border="0" id="botonImagenCuatro"/>
										</x:commandLink>
									</h:panelGroup>

								</h:panelGrid>
							</s:fieldset>
							
						</h:panelGrid>
					</s:layoutingTitlePane>
					
					<f:verbatim><br></f:verbatim>
					

					<%-- Informacion Familiar --%>
					<s:layoutingTitlePane id="informeFamiliar" label="#{ModificacionEstructuraCuentaBean.tituloPanleCinco}"
							containerNodeClass="contentTitlePane"labelNodeClass="labelTitlePane" 
							rendered="#{ModificacionEstructuraCuentaBean.panelI}"> 
							
							<f:verbatim><br></f:verbatim>
							
							<h:panelGrid columns="1" id="panelDatosF" width="850" align="center">
							
							<s:fieldset legend="Datos Familiares" id="datosFamiliares">
							<h:panelGrid columns="4" id="panelDatosFamiliares" width="650" align="center">
							
								<h:outputText id="outEstadoCivil" value="Estado Civil: " styleClass="texto" />
								<h:selectOneMenu id="lstestadoCivil" value="#{ModificacionEstructuraCuentaBean.idEstadoCivilSeleccionado}"
									styleClass="lista" immediate="true" onfocus="encender(this);" onblur="apagar(this);" style="width: 200px"
									onchange="javascript:setValueId('inCantHijos','idFoco');">
									<f:selectItems value="#{ModificacionEstructuraCuentaBean.listEstadoCivil}" id="selectItemIdEstadoCivil"/>
								</h:selectOneMenu>

								<h:outputText id="outHijo" value="Hijos a Cargo: "
									styleClass="texto" />
								<h:inputText id="inCantHijos" value="#{ModificacionEstructuraCuentaBean.individuoEvaluacion.hijosCargo}" styleClass="bordeceldainferior" 
									maxlength="2" style="width: 50" onfocus="encender(this);" onblur="apagar(this);" onkeypress="return soloEnteros(this,event);"
									onchange="javascript:setValueId('lstEducacion','idFoco');"/>
							</h:panelGrid>
							</s:fieldset>
																
<%--@I4991--%>							<s:fieldset legend="Estudios" id="datosEstudio" >
<%--@F4991--%>							<h:panelGrid columns="4" id="panelEstudios" width="650" align="center">
								<h:outputText id="outEducacion" value="Educación: " styleClass="texto" />
<%--@I4991--%>							<h:selectOneMenu id="lstEducacion" value="#{ModificacionEstructuraCuentaBean.idEducSeleccionado}"
									styleClass="lista" immediate="true" onfocus="encender(this);" onblur="apagar(this);" style="width: 200px"
									valueChangeListener="#{ModificacionEstructuraCuentaBean.cambiarProfesion}" binding="#{ModificacionEstructuraCuentaBean.educacionHtml}"
									onchange="javascript:setValueId('lstEducacion','idFoco'); submit();">
<%--@F4991--%>									<f:selectItems value="#{ModificacionEstructuraCuentaBean.listEducacion}" id="selctItemIdEducSeleccionada"/>
								</h:selectOneMenu>

								<h:outputText id="outProfesion" value="Profesión: " styleClass="texto" />
<%--@I4991--%>								<h:selectOneMenu id="lstPorfesion" value="#{ModificacionEstructuraCuentaBean.idProfesionSeleccionado}"
									styleClass="lista" immediate="true" onfocus="encender(this);" onblur="apagar(this);" style="width: 200px;" binding="#{ModificacionEstructuraCuentaBean.profesionHtml}"
								    disabled="#{ModificacionEstructuraCuentaBean.deshabilitarProfesion}">
<%--@F4991--%>									<f:selectItems  id="selectoUno" value="#{ModificacionEstructuraCuentaBean.listProfesion}"/>
								</h:selectOneMenu>
							</h:panelGrid>
							</s:fieldset>
							
<%--@I4991--%>							<c:if test="${ModificacionEstructuraCuentaBean.solicitudIndividuo.tipoIndividuo.idTipoIndividuo != 1}">
<%--@F4991--%>								<s:fieldset legend="Vinculo" id="vinculo">
								<h:panelGrid columns="4" id="panelvinculo" width="650" align="center">
									<h:outputText id="outVinculo"
										value="Vinculo con el Solicitante: " styleClass="texto" />
<%--@I4991--%>									<h:selectOneMenu id="lstVinculo" value="#{ModificacionEstructuraCuentaBean.idVinculoSeleccionado}" 
										styleClass="lista" immediate="true" onfocus="encender(this);" onblur="apagar(this);" style="width: 200px"
										onchange="javascript:setValueId('lstDomEnv','idFoco');">
<%--@F4991--%>										<f:selectItems value="#{ModificacionEstructuraCuentaBean.listVinculo}" id="selctItemIdVinvulo"/>
									</h:selectOneMenu>
									
									<f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim>
									<f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim>
								</h:panelGrid>
								</s:fieldset>
							</c:if>
							</h:panelGrid>
					</s:layoutingTitlePane>

					<c:if test="${ModificacionEstructuraCuentaBean.panelA}">
						<f:verbatim><br></f:verbatim>
					</c:if>
					
					<s:layoutingTitlePane id="datosActividad" label="#{ModificacionEstructuraCuentaBean.tituloPanleSiete}" containerNodeClass="contentTitlePane" labelNodeClass="labelTitlePane" rendered="#{ModificacionEstructuraCuentaBean.panelJ}">
						<h:panelGrid columns="1" align="center" id="panelIternoCuatro" width="850">
						<%-- Datos Empresa --%>
								<s:fieldset legend="Datos Empresa" id="datosEmpresa">
									<h:panelGrid columns="4" align="center" id="panelIternoLaboral" width="650">
										
										<h:outputText value="C.U.I.T.: " id="outCuit" styleClass="texto" />
										<h:panelGroup id="uno">
											<h:inputText id="inCuit" value="#{ModificacionEstructuraCuentaBean.nroCuit}" maxlength="11" styleClass="bordeceldainferior" style="width: 150px" onfocus="encender(this);" onblur="apagar(this);" onkeypress="return soloEnteros(this,event);" />
											
											<f:verbatim>&nbsp;&nbsp;&nbsp;</f:verbatim>
																				
											<x:commandButton id="btnIngresarCuit" value="Buscar Empresa" action="#{ModificacionEstructuraCuentaBean.buscarEmpresa}" 
											title="Buscar Empresas" image="/img/icon/reload_page.png" disabled="#{ModificacionEstructuraCuentaBean.botonEmpresa}" alt="Buscar Empresa." />
											
											<f:verbatim>&nbsp;&nbsp;</f:verbatim>
											
											<x:commandButton id="btnNuevaEmpresa" value="Agregar Empresa" action="#{ModificacionEstructuraCuentaBean.agregarEmpresa}" title="Agregar Empresa" image="/img/cat_pad.gif" disabled="#{ModificacionEstructuraCuentaBean.botonEmpresa}" alt="Agregar Empresa." />										
											
											<f:verbatim>&nbsp;&nbsp;</f:verbatim>
											
											<x:commandButton id="btnVerEmpresa" title="Mostrar cliente" action="#{ModificacionEstructuraCuentaBean.popUpListaTclientes}" disabled="#{ModificacionEstructuraCuentaBean.botonEmpresa}" image="/img/icon/view.gif" value="Ver Cliente" />
										</h:panelGroup>
										
										<h:outputText value="Sucursal: " id="outSuc" styleClass="texto" />
										<h:selectOneMenu id="lstSuc" value="#{ModificacionEstructuraCuentaBean.idSucursalSeleccionado}" 
												styleClass="lista" immediate="true" onfocus="encender(this);" onblur="apagar(this);" style="width: 200px" 
												disabled="#{ModificacionEstructuraCuentaBean.habilitarSucursal}" binding="#{ModificacionEstructuraCuentaBean.sucursalHtml}" 
												valueChangeListener="#{ModificacionEstructuraCuentaBean.cambioSucursal}" onchange="javascript:setValueId('lstOcupacion','idFoco'); submit();">
											<f:selectItems value="#{ModificacionEstructuraCuentaBean.listSucEmp}" id="itemSuc" />
										</h:selectOneMenu>
										
										<h:outputText value="Cuit: " id="outCuitEmpre" styleClass="texto" />
										<h:outputText value="#{ModificacionEstructuraCuentaBean.empresa.cuit}" id="outCuitLabel" styleClass="textoblue" />
												
										<h:outputText value="Razón Social: " id="outRS" styleClass="texto" />
										<h:outputText value="#{ModificacionEstructuraCuentaBean.empresa.razonSocial}" id="outRazonSocial" styleClass="textoblue" />
<%--
										<h:outputText value="Rubro: " id="outR" styleClass="texto" />
										<h:outputText value="#{ModificacionEstructuraCuentaBean.empresa.rubro.descripcion}" id="outRubro" styleClass="textoblue" />
									--%>
									
										<h:outputText value="Dirección: " id="outD" styleClass="texto" />
										<h:outputText value="#{ModificacionEstructuraCuentaBean.direccionSucursal}" id="outDireccion" styleClass="textoblue" />
										
										<h:outputText value="Teléfono: " id="outT" styleClass="texto" />
										<h:outputText value="#{ModificacionEstructuraCuentaBean.telefono}" id="outTelefono" styleClass="textoblue" />
										
									</h:panelGrid>
							
								</s:fieldset>
								
								</h:panelGrid>
								<%-- Datos Laboraless--%>
								
								<h:panelGrid columns="1" id="panleLaboralesUno" width="850" align="center">
								
								<s:fieldset legend="Datos Laborales" id="datosLaborales">
									<h:panelGrid columns="4" id="panleLaborales" width="650" align="center"> 
									
										<h:outputText value="Ocupación: " id="outOcupacion" styleClass="tecto" />
										<h:selectOneMenu id="lstOcupacion" value="#{ModificacionEstructuraCuentaBean.idOcupacion}" styleClass="lista" immediate="true" onfocus="encender(this);" onblur="apagar(this);" style="width: 200px">
											<f:selectItems value="#{ModificacionEstructuraCuentaBean.listOcupacion}" id="selctItemIdOcupacionSeleccionada" />
										</h:selectOneMenu>
										
										<h:outputText value="Cargo: " id="outCargo" styleClass="texto" />
										<h:inputText id="inCargo" value="#{ModificacionEstructuraCuentaBean.cargo}" size="50" maxlength="50" styleClass="bordeceldatext" style="width: 150px" onfocus="encender(this);" onblur="apagar(this);" />
											
										<h:outputText value="Fecha Ingreso: " id="outFIngreso" styleClass="texto" />
<%--@I4991--%>										<x:inputCalendar id="fechaIng" monthYearRowClass="yearMonthHeader" weekRowClass="weekHeader" currentDayCellClass="currentDayCell" popupButtonStyleClass="standard_bold" value="#{ModificacionEstructuraCuentaBean.ingreso}" renderAsPopup="true" styleClass="bordeceldainferior" style="width: 90px" onchange="javascript:setValueId('inAntiguedad','idFoco');" popupTodayString="#{example_messages['popup_today_string']}" immediate="true" popupDateFormat="dd/MM/yyyy" popupWeekString="#{example_messages['popup_week_string']}" forceId="true" valueChangeListener="#{ModificacionEstructuraCuentaBean.calcularAntiguedad}" binding="#{ModificacionEstructuraCuentaBean.fechaIngreso}" />
<%--@F4991--%>										
										<h:outputText value="Antigüedad: " id="outAnt" styleClass="texto" />
										<h:outputText id="inAntiguedad" value="#{ModificacionEstructuraCuentaBean.antiguedad}" styleClass="textoblue" />
										
										<h:outputText value="Sueldo: " id="outSueldo" styleClass="texto" />
										<h:inputText id="inSueldo" value="#{ModificacionEstructuraCuentaBean.sueldoNeto }" size="10" maxlength="10" styleClass="bordeceldainferior" style="width: 90px" onfocus="encender(this);" onblur="apagar(this);" onkeypress="return soloDecimales(this,event);" />
										 
										<f:verbatim>&nbsp;&nbsp;&nbsp;</f:verbatim>
										<f:verbatim>&nbsp;&nbsp;&nbsp;</f:verbatim>
										
										<h:outputText value="Empleo Anterior: " id="outEmplAnt" styleClass="texto" />
										<h:inputText id="inEmplAnt" value="#{ModificacionEstructuraCuentaBean.empleoAnterior}" size="50" maxlength="100" styleClass="bordeceldatext" style="width: 150px" onfocus="encender(this);" onblur="apagar(this);" />
										
										<h:outputText value="Teléfono: " id="outTelefonoLaboral" styleClass="texto" />
										<h:inputText id="inTelefonoLaboral" value="#{ModificacionEstructuraCuentaBean.telEmpleoAnt}" size="15" maxlength="50" styleClass="bordeceldainferior" style="width: 150px" onfocus="encender(this);" onblur="apagar(this);" />
									</h:panelGrid>
									
									<h:panelGrid columns="4" id="panelReferencia" width="650" align="center"> 
										<h:outputText value="Referencia Comercial: " id="outRefComer" styleClass="texto" />
										<h:inputText id="inRefCom" value="#{ModificacionEstructuraCuentaBean.referencias}" size="50" maxlength="200" styleClass="bordeceldatext" style="width: 150px" onfocus="encender(this);" onblur="apagar(this);" />
											
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
										<h:inputText id="inDesc" value="#{ModificacionEstructuraCuentaBean.otrosIngresosDesc}" size="50" maxlength="200" styleClass="bordeceldatext" style="width: 150px" onfocus="encender(this);" onblur="apagar(this);" />
										
										<h:outputText value="Monto: " id="outMonto" styleClass="texto" />
										<h:inputText id="inMonto" value="#{ModificacionEstructuraCuentaBean.otrosIngresosMonto}" size="6" maxlength="5" styleClass="bordeceldainferior" style="width: 150px" onfocus="encender(this);" onblur="apagar(this);" onkeypress="return soloDecimales(this,event);" />
										
									</h:panelGrid>
								</s:fieldset>
							</h:panelGrid>
					</s:layoutingTitlePane>
					
					<f:verbatim><br></f:verbatim>
					<%-- Datos para la Factura --%>
					
					
					<c:if test="${ModificacionEstructuraCuentaBean.panelB}">
						<f:verbatim><br></f:verbatim>
					</c:if>
					
					<s:layoutingTitlePane id="datosPropiedad" label="#{ModificacionEstructuraCuentaBean.tituloPanleOcho}"
							containerNodeClass="contentTitlePane" labelNodeClass="labelTitlePane" 
							rendered="#{ModificacionEstructuraCuentaBean.panelB}"> 
						<h:panelGrid columns="1" id="panelInternoCinco" width="850" align="center">
						
							<s:fieldset legend="Domicilio Inmueble" id="datosInmuebles">
								<h:panelGrid columns="2" id="panelDomicilioInmueble" width="650" align="center">
									<h:panelGroup id="panelGroupTable">
									<h:dataTable value="#{ModificacionEstructuraCuentaBean.listDomicilioInmueble}"
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
											<x:commandLink action="#{ModificacionEstructuraCuentaBean.editarDomicilioInmueble}" id="editarDomicilioInmueblesLink" disabled="#{ModificacionEstructuraCuentaBean.botonDomicilio}" onclick="javascript:setValueId('datosInmuebles','idFoco');">
												<f:param id="idDomiEdi" name="idDomiEdi" value="#{domicilioInmueble.domicilio.idDomicilio}" />
												<x:graphicImage value="/img/editar.gif" title="Editar el domicilio Inmueble." border="0" id="botnoImagenCinco"/>
											</x:commandLink>
										</h:column>

										<h:column>
											<x:commandLink action="#{ModificacionEstructuraCuentaBean.eliminarDomicilioInmueble}" id="eliminarDomicilioInmueblrsLink" disabled="#{ModificacionEstructuraCuentaBean.botonDomicilio}">
												<f:param id="idDomicilio" name="idDomicilio" value="#{domicilioInmueble.domicilio.idDomicilio}"/>
												<x:graphicImage value="/img/cat_act.gif" title="Eliminar un domicilio Inmueble." border="0" id="botonImagenSeis" />
											</x:commandLink>
										</h:column>
										
									</h:dataTable>
									</h:panelGroup>
									
									<h:panelGroup id="panelGroupagregarDom"> 
										<x:commandLink id="agregarDomicilioInmueblesLink" action="#{ModificacionEstructuraCuentaBean.agregarDomicilioInmueble}" disabled="#{ModificacionEstructuraCuentaBean.botonDomicilio}">
											<x:graphicImage value="/img/icon/home_24.gif" title="Agregar domicilios inmueble." border="0" id="botonImagenSiete"/>
										</x:commandLink>
									</h:panelGroup>

								</h:panelGrid>
							</s:fieldset>
							
							<s:fieldset legend="Vehiculo" id="vehiculo">
								<h:panelGrid columns="4" id="panelVahiculo" width="650" align="center">
									
									<h:outputText value="Marca: " id="outMarca" styleClass="texto"/>
									<h:inputText id="inMarca" value="#{ModificacionEstructuraCuentaBean.individuoVehiculo.vehiculo.marca}" size="50" maxlength="100" 
										styleClass="bordeceldatext" style="width: 150px" onfocus="encender(this);" onblur="apagar(this);"/>
									
									<h:outputText value="Modelo: " id="outModelo" styleClass="texto"/>
									<h:inputText id="inModelo" value="#{ModificacionEstructuraCuentaBean.individuoVehiculo.vehiculo.modelo}" size="50" maxlength="100" 
										styleClass="bordeceldatext" style="width: 150px" onfocus="encender(this);" onblur="apagar(this);"/>
									
									<h:outputText value="Año: " id="outAnio" styleClass="texto"/>
									<h:inputText id="inAnio" value="#{ModificacionEstructuraCuentaBean.individuoVehiculo.vehiculo.anio}" size="4" maxlength="4" 
										styleClass="bordeceldainferior" style="width: 50px" onfocus="encender(this);" onblur="apagar(this);"/>
									
									<h:outputText value="Patente: " id="outPatente" styleClass="texto"/>
									<h:inputText id="inPatente" value="#{ModificacionEstructuraCuentaBean.individuoVehiculo.vehiculo.patente}" size="7" maxlength="7" 
										styleClass="bordeceldatext" style="width: 150px" onfocus="encender(this);" onblur="apagar(this);"/>
										
									<h:outputText value="Es Propio: " id="outPropio" styleClass="texto"/>
									<h:selectBooleanCheckbox value="#{ModificacionEstructuraCuentaBean.vehiculoPropio}" id="vehiculoPropio" immediate="true"/>
									
									<f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim>
									<f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim>
									
								</h:panelGrid>
							</s:fieldset>
						</h:panelGrid>
					</s:layoutingTitlePane>
					
					<c:if test="${ModificacionEstructuraCuentaBean.panelC}">
						<f:verbatim><br></f:verbatim>
					</c:if>
					
					<s:layoutingTitlePane id="datosFinancieros" label="#{ModificacionEstructuraCuentaBean.tituloPanleNueve}"
							containerNodeClass="contentTitlePane" labelNodeClass="labelTitlePane"
						 	rendered="#{ModificacionEstructuraCuentaBean.panelC}">
						<h:panelGrid columns="1" id="panelInternoSeis" width="850" align="center">
						
							<s:fieldset legend="Cuenta" id="cuenta">
								<h:panelGrid columns="4" id="internoTres" align="center" width="650">
									<h:outputText value="Banco: " id="outBanco" styleClass="texto" />
									<h:selectOneMenu id="lstBanco" value="#{ModificacionEstructuraCuentaBean.idBcoSeleccionado}" styleClass="lista" immediate="true" onfocus="encender(this);" onblur="apagar(this);" style="width: 200px" disabled="#{ModificacionEstructuraCuentaBean.mostrarCbu}" binding="#{ModificacionEstructuraCuentaBean.bancoSeleccionado}" onchange="javascript:setValueId('lstCuenta','idFoco'); submit();">
										<f:selectItems value="#{ModificacionEstructuraCuentaBean.listBancos}" id="selctItemIdBcoSeleccionado" />
									</h:selectOneMenu>
									<f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim>
									<f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim>
									<h:outputText value="Cuenta: " id="outCuenta" styleClass="texto" />
									<h:selectOneMenu id="lstCuenta" value="#{ModificacionEstructuraCuentaBean.idTipoCuentaSeleccionado}" styleClass="lista" immediate="true" onfocus="encender(this);" onblur="apagar(this);" style="width: 200px" disabled="#{ModificacionEstructuraCuentaBean.mostrarCbu}" onchange="javascript:setValueId('inNroCuenta','idFoco');">
										<f:selectItems value="#{ModificacionEstructuraCuentaBean.listTipoCtas}" id="selctItemIdDiaPagoSeleccionado" />
									</h:selectOneMenu>
								<h:outputText value="Nro. Cuenta: " id="outNroCuenta" styleClass="texto" />
								<h:inputText id="inNroCuenta" value="#{ModificacionEstructuraCuentaBean.individuoEvaluacion.nroCuenta}" styleClass="bordeceldainferior" maxlength="20" style="width: 150" onfocus="encender(this);" onblur="apagar(this);" onkeypress="return soloEnteros(this,event);" size="20" disabled="#{ModificacionEstructuraCuentaBean.mostrarCbu}" onchange="javascript:setValueId('cbu','idFoco');" />
								<h:outputText value="CBU: " styleClass="texto" style="COLOR: #000000; font: bold;" id="outCBU" />
								<h:selectBooleanCheckbox id="cbu" value="#{ModificacionEstructuraCuentaBean.esCBU}" onclick="javascript:setValueId('inNroCBU','idFoco'); submit();" />
								<h:outputText value="Nro. CBU: " id="outNroCBU" styleClass="texto" />
								<x:inputText id="inNroCBU" value="#{ModificacionEstructuraCuentaBean.individuoEvaluacion.cbu}" size="22" maxlength="22" styleClass="bordeceldainferior" style="width: 150px" onfocus="encender(this);" onblur="apagar(this);" disabled="#{!ModificacionEstructuraCuentaBean.mostrarCbu}" onchange="javascript:setValueId('inCuit','idFoco');" onkeypress="return soloEnteros(this,event);" />
								</h:panelGrid>
							</s:fieldset>

							<s:fieldset legend="Vinculo Banco" id="vinculoBco">
								<h:panelGrid columns="4" id="panelBancos" width="650" align="center">
									<h:outputText value="Banco: " id="outBco" styleClass="texto"/>
									<h:outputText value="#{ModificacionEstructuraCuentaBean.nombreBanco}" id="inBco" styleClass="textoblue"
										binding="#{ModificacionEstructuraCuentaBean.textBanco}"/>
									<h:outputText value="Vinculación: " id="outVinc" styleClass="texto"/>
									<h:inputText id="inVinculo" value="#{ModificacionEstructuraCuentaBean.bancos.vinculacion}" size="50" maxlength="100" 
										styleClass="bordeceldatext" style="width: 150px" onfocus="encender(this);" 
										onblur="apagar(this);"/>
								</h:panelGrid>
							</s:fieldset>
							
							<s:fieldset legend="Tarjeta" id="tarjeta">
								<h:panelGrid columns="4" id="panelTarjetas" width="650" align="center">
									<h:outputText value="Tarjeta: " id="outTarjeta" styleClass="texto"/>
									<h:inputText id="inTarj" value="#{ModificacionEstructuraCuentaBean.tarjeta.descripcion}" size="50" maxlength="50" 
										styleClass="bordeceldatext" style="width: 150px" onfocus="encender(this);" 
										onblur="apagar(this);"/>
									<h:outputText value="Número: " id="outNroTarj" styleClass="texto"/>
									<h:inputText id="inNroTarj" value="#{ModificacionEstructuraCuentaBean.tarjeta.nro}" size="20" maxlength="16" 
										styleClass="bordeceldainferior" style="width: 150px" onfocus="encender(this);" 
										onblur="apagar(this);" onkeypress="return soloEnteros(this,event);"/>
									<h:outputText value="Banco: " id="outBancos" styleClass="texto"/>
									<h:selectOneMenu id="lstBancos" value="#{ModificacionEstructuraCuentaBean.idBancoSeleccionado}"
											styleClass="lista" immediate="true" onfocus="encender(this);"
											onblur="apagar(this);" style="width: 200px" disabled=" ">
											<f:selectItems value="#{ModificacionEstructuraCuentaBean.listBancos}" id="selctItemIdBancoSeleccionado"/>
									</h:selectOneMenu>
									<f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim>
									<f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim>
								</h:panelGrid>
							</s:fieldset>
						</h:panelGrid>
						
					</s:layoutingTitlePane>
					
<%--@I4991--%>					<h:panelGrid columns="10" id="panelGuardar" width="950" align="rigth">
						<f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim>
						<f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim>
						<f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim>
						<f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim>
						<f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim>
						<f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim>
						<f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim>
						<f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim>
						<f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim>
						<c:if test="${ModificacionEstructuraCuentaBean.origen != 4}">
							<x:commandButton id="buttonGrabar" value="Guardar" action="#{ModificacionEstructuraCuentaBean.guardarSolicitud}"
							styleClass="botones" disabled="#{!ModificacionEstructuraCuentaBean.opcionA}"/> 
						</c:if>
						<c:if test="${ModificacionEstructuraCuentaBean.origen == 4}">
							<f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim>
						</c:if>
					</h:panelGrid>
<%--@F4991--%>					
					<c:if test="${ModificacionEstructuraCuentaBean.panelD}">
						<f:verbatim><br></f:verbatim>
					</c:if>
					
					<f:verbatim><br></f:verbatim>
					
					<c:if test="${ModificacionEstructuraCuentaBean.panelF}">
						<f:verbatim><br></f:verbatim>
					</c:if>
					
					<s:layoutingTitlePane id="datosAltas" label="#{ModificacionEstructuraCuentaBean.tituloPanleDoce}"
							containerNodeClass="contentTitlePane" labelNodeClass="labelTitlePane"
						 	rendered="#{ModificacionEstructuraCuentaBean.panelF}">
						
						<h:panelGrid columns="1" width="650" align="center" id="panelAltaGA">
						<s:fieldset legend="Garante" id="garante">
							<h:panelGrid columns="1" width="650" align="center" id="panelInternoAltaGarante">
			
								<h:commandButton value="Alta Garante" action="#{ModificacionEstructuraCuentaBean.altaGarante}" styleClass="botones" id="btnAltaGaranteLink"/>
								
								<c:if test="${!empty ModificacionEstructuraCuentaBean.listGarante}">
									<h:dataTable value="#{ModificacionEstructuraCuentaBean.listGarante}"
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
											<h:outputText value="#{garante.individuoEvaluacion.apellido}"/>
										</h:column>
										
										<h:column>
										    <f:facet name="header">
										        <h:outputText value="Nombre" styleClass="texto" style="font: bold;color: white" />
										    </f:facet>
                                            <h:outputText value="#{garante.individuoEvaluacion.nombres}" />
										</h:column>
										
										<h:column>
										    <f:facet name="header">
										        <h:outputText value="Accion" styleClass="texto" style="font: bold;color: white" />
										    </f:facet>
                                            <h:outputText value="#{garante.accion}" />
										</h:column>
<%--@I4991--%>										<h:column >
										    <h:commandLink action="#{ModificacionEstructuraCuentaBean.editarGarante}" id="editarGaranteLink" rendered="#{garante.mostrarBotonEditar}">
												<f:param id="idGaranteEdi" name="idGaranteEdi" value="#{garante.individuoEvaluacion.idIndividuo}" />
												<x:graphicImage value="/img/editar.gif" title="Editar Garante." border="0"/>
											</h:commandLink>
										</h:column>
										
										<h:column>
											<x:commandLink action="#{ModificacionEstructuraCuentaBean.eliminarGarante}" id="eliminarGaranteLink">
												<f:param id="idGaranteElim" name="idGaranteElim" value="#{garante.individuoEvaluacion.idIndividuo}"/>
												<x:graphicImage value="#{garante.imgEliminarWrapper}" title="#{garante.msgEliminarGarante}" border="0"/>
											</x:commandLink>
										</h:column>										
<%--@F4991--%>										
									</h:dataTable>
								</c:if>
			
							</h:panelGrid>

						</s:fieldset>
					
						<s:fieldset legend="Adicional" id="adicional">
							<h:panelGrid columns="1" width="650" align="center" id="panelAltaAdicional">
			
								<h:commandButton value="Alta Adicional" action="#{ModificacionEstructuraCuentaBean.altaAdicional}" styleClass="botones" id="btnAltaAdicionalLink"/>
							    	<c:if test="${!empty ModificacionEstructuraCuentaBean.listAdicional}">
									<h:dataTable value="#{ModificacionEstructuraCuentaBean.listAdicional}" headerClass="dataTable_Header"
										footerClass="standardTable_Header" rowClasses="standardTable_Row1,standardTable_Row2"
										columnClasses="standardTable_Column,standardTable_ColumnCentered,standardTable_Column"
										id="tablaDos" styleClass="standardTable"
										var="adicional" style=" width : 300px;">

										<h:column>
											<f:facet name="header">
												<h:outputText value="Apellido" styleClass="texto" style="font: bold;color: white;" />
											</f:facet>
											<h:outputText value="#{adicional.individuoEvaluacion.apellido}"/>
										</h:column>
										
										<h:column>
											<f:facet name="header">
												<h:outputText value="Nombre" styleClass="texto" style="font: bold;color: white;" />
											</f:facet>
											<h:outputText value="#{adicional.individuoEvaluacion.nombres}"/>
										</h:column>

										<h:column>
										    <f:facet name="header">
										        <h:outputText value="Accion" styleClass="texto" style="font: bold;color: white" />
										    </f:facet>
                                            <h:outputText value="#{adicional.accion}" />
										</h:column>

										<h:column>
<%--@I4991--%>										<x:commandLink action="#{ModificacionEstructuraCuentaBean.editarAdicional}" id="editarAdicionalLink" rendered="#{adicional.mostrarBotonEditar}">
<%--@F4991--%>												<f:param id="idAdicionalEdi" name="idAdicionalEdi" value="#{adicional.individuoEvaluacion.idIndividuo}" />
												<x:graphicImage value="/img/editar.gif" title="Editar el Adicional." border="0"/>
											</x:commandLink>
										</h:column>
										<h:column>
											<x:commandLink action="#{ModificacionEstructuraCuentaBean.eliminarAdicional}" id="eliminarAdicionalLink">
												<f:param id="idAdicionalElim" name="idAdicionalElim" value="#{adicional.individuoEvaluacion.idIndividuo}"/>
<%--@I4991--%>												<x:graphicImage value="#{adicional.imgEliminarWrapper}" title="#{adicional.msgEliminarAdicional}" border="0" />
<%--@F4991--%>											</x:commandLink>
										</h:column>										
									</h:dataTable>								
								</c:if>
			
						</h:panelGrid>
						</s:fieldset>
					
						</h:panelGrid>
					</s:layoutingTitlePane>

					<c:if test="${ModificacionEstructuraCuentaBean.panelE}">
						<f:verbatim><br></f:verbatim>
					</c:if>
					
					<s:layoutingTitlePane id="datosGenAsigInf" label="#{ModificacionEstructuraCuentaBean.tituloPanleTrece}"
							containerNodeClass="contentTitlePane" labelNodeClass="labelTitlePane"  rendered="#{ModificacionEstructuraCuentaBean.panelE}">

							<h:panelGrid columns="3" width="650" align="center" id="gridUno">
								
								<h:outputText value="Informe Ambiental Particular" id="outInfAmb" styleClass="texto"/>
								<h:selectOneMenu id="lstIAP" value="#{ModificacionEstructuraCuentaBean.idInformeParticular}"
									styleClass="lista" immediate="true" onfocus="encender(this);" onblur="apagar(this);" style="width: 200px"
									disabled="#{ModificacionEstructuraCuentaBean.particular}">
									<f:selectItems value="#{ModificacionEstructuraCuentaBean.listaVerificadores}" id="selectItemParticular"/>
								</h:selectOneMenu>
								<h:commandButton value="Asignar" action="#{ModificacionEstructuraCuentaBean.gestionarInformeParticular}" 
									styleClass="botones" id="btnGestInfParticularLink" disabled="#{ModificacionEstructuraCuentaBean.particular}"/>
							
								<h:outputText value="Informe Ambiental Laboral" id="outInfAmbLab" styleClass="texto"/>
								<h:selectOneMenu id="lstIAL" value="#{ModificacionEstructuraCuentaBean.idInformeLaboral}"
									styleClass="lista" immediate="true" onfocus="encender(this);" onblur="apagar(this);" style="width: 200px"
									disabled="#{ModificacionEstructuraCuentaBean.laboral}">
									<f:selectItems value="#{ModificacionEstructuraCuentaBean.listaVerificadores}" id="selectItemLaboral"/>
								</h:selectOneMenu>
								<h:commandButton value="Asignar" action="#{ModificacionEstructuraCuentaBean.gestionarInformeLaboral}" styleClass="botones" id="btnGestInfoLaboralLink" 
									disabled="#{ModificacionEstructuraCuentaBean.laboral}" />

							</h:panelGrid>
											
					</s:layoutingTitlePane>	
					<c:if test="${ModificacionEstructuraCuentaBean.panelE}">
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
<%--@I4991--%>						<f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim>
						<x:commandButton id="buttonCancelar" value="Salir" action="#{ModificacionEstructuraCuentaBean.salirSolicitud}" styleClass="botones"/>
<%--@F4991--%>					</h:panelGrid>
					
					<x:inputHidden id="idFoco" value="#{ModificacionEstructuraCuentaBean.focoHidden}" forceId="true"/>
					<s:focus id="foco" for="#{ModificacionEstructuraCuentaBean.focoHidden}" />
				</h:panelGrid>
							
				</h:panelGroup>
			</f:facet>

			<%@include file="/inc/page_footer.jsp"%>

		</x:panelLayout>
	</h:form></center>
	</body>
	</html>
</f:view>
