<%@include file="/inc/tags.jsp" %>
<jsp:useBean id="ahora" class="java.util.Date" scope="request" />

<f:view>
<html>
<head>
    <title><h:outputText value="Tarjeta Fiel - Gestión de Individuos"/></title>
    <s:script language="javascript">
		function recargar() {
			document.getElementById('amIndivForm').submit();
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

<%@include file="/inc/head.inc" %>

<body onbeforeunload="ShowWait('amIndivForm');"
		 onload="if('${IndividuoPopupBean.popup.mostrar}'=='true') {viewDialog.show();} 
                  else {window.scrollTo(0,${ScrollBean.hiddenScrollY});}">
     
      
<center>
	

	<h:form id="amIndivForm" enctype="multipart/form-data">

	<%-- GRABA EL ESTADO DEL SCROLL(Dibujar solo si el popup no se muestra) --%>
	<h:panelGroup rendered="#{!IndividuoPopupBean.popup.mostrar}">
	<%@include file="/inc/scroll.inc" %>
	</h:panelGroup>

		<x:panelLayout id="page" layout="#{globalOptions.pageLayout}"
			styleClass="pageLayout"
			headerClass="pageHeader"
			navigationClass="pageNavigation"
			bodyClass="pageBody"
			footerClass="pageFooter" >

			<f:facet name="header">
				<f:subview id="header">
					<jsp:include page="/inc/page_header.jsp" />
				</f:subview>
			</f:facet>

			<f:facet name="body">
				<h:panelGrid columns="1" align="center" id="PanelPricipal" >
					<%-- POPUP PARA LOS MENSAJES DE EXITO Y FRACASO --%>
					<s:modalDialog dialogId="viewDialog" dialogVar="viewDialog" styleClass="viewDialog" dialogTitle="#{IndividuoEvaluacionBean.tituloCorto}">
					    	
					    	<h:panelGrid columns="3" width="500">
					    		<f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim>
						    	<x:graphicImage value="/img/#{IndividuoPopupBean.popup.nombreIcono}" />
						        <h:outputText value=" #{IndividuoPopupBean.popup.mensaje}" styleClass="texto"/>
					        </h:panelGrid>
						        
					        <h:panelGrid columns="3" width="500">
					        	<f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim>
					        	<x:commandButton action="#{IndividuoPopupBean.irAContinuar}" onclick="clickLink('continuarCarga');dojo.widget.byId('viewDialog').hide();"
					         		 value="Continuar" styleClass="botones" title="Continuar con el alta del individuo."/>
								<f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim>
								
							</h:panelGrid>
					</s:modalDialog>
				    <x:commandLink id="continuarCarga" action="#{IndividuoPopupBean.irAContinuar}" forceId="true" style="display: block;"/>
                	
					<h:panelGrid id="titu" align="center">
	                 	<h:outputText value="INDIVIDUOS" style="FONT-SIZE: large;" styleClass="texto"/>			
				     </h:panelGrid>
					<%-- INCLUDE PARA LOS ERRORES --%>
					<h:panelGroup id="errores">
						<jsp:include page="/inc/error.jsp" />
					</h:panelGroup>
                    <h:panelGrid id="popParaIndi" width="700" columns="1" align="center">
                    	<h:panelGrid columns="1" id="individ" align="center">
			    			<h:panelGrid columns="4" id="panelSecuandarioDos" align="center" width="650">
								<h:outputText id="outCuil" value="C.U.I.L.: " styleClass="texto" />
								<x:outputText id="outNroCuil" value="#{IndividuoPopupBean.individuoEvaluacion.cuil}"
									styleClass="texto"/>
								<h:commandButton action="#{IndividuoPopupBean.darAltaNuevoIndividuo}" styleClass="botones" value="Nuevo Individuo" id="nuevoIndividuo" rendered="false"/>
                                <h:commandButton action="#{IndividuoPopupBean.habilitarCarga}" styleClass="botones" value="Habilitar Carga" id="habilitacion" rendered="false"/>
								<c:if test="${IndividuoPopupBean.origen==3}">
							    <x:commandButton id="buttonGrabar_1" value="Guardar" action="#{IndividuoPopupBean.grabarIndividuo}"
										styleClass="botones" /> 
								</c:if>
								<c:if test="${IndividuoPopupBean.origen!=3}">
								    <x:commandButton id="buttonGrabar_2" value="Guardar" action="#{IndividuoPopupBean.guardar}"
											styleClass="botones" /> 
								</c:if>	
								<x:commandButton id="buttonCancelar_1" value="Salir" action="#{IndividuoPopupBean.salir}" 
										styleClass="botones"/>
                                <f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim>
							</h:panelGrid>
							<h:panelGrid columns="4" id="panelSecuandarioConDAtos" align="center" width="650" rendered="#{IndividuoPopupBean.verDatos}">
								<h:outputText id="outApellido" value="Apellido Paterno: " styleClass="texto" />
								<x:inputText id="inApellido" value="#{IndividuoPopupBean.individuoEvaluacion.apellido}"  forceId="true" size="50"
									maxlength="100" styleClass="bordeceldatext" style="width: 150px" onfocus="encender(this);" onblur="apagar(this);"
									disabled="#{!IndividuoPopupBean.editDatos}"/>
									
								<h:outputText id="outAMaterno" value="Apellido Materno: " styleClass="texto" />
								<x:inputText id="inAMaterno" value="#{IndividuoPopupBean.individuoEvaluacion.apellidoMaterno}" forceId="true" size="50"
									maxlength="100" styleClass="bordeceldatext" style="width: 150px" onfocus="encender(this);" onblur="apagar(this);"
									disabled="#{!IndividuoPopupBean.editDatos}"/>
									
								<h:outputText id="outNombre" value="Nombres: " styleClass="texto" />
								<x:inputText id="inNombre" value="#{IndividuoPopupBean.individuoEvaluacion.nombres}" forceId="true" size="50"
									maxlength="100" styleClass="bordeceldatext" style="width: 150px" onfocus="encender(this);" onblur="apagar(this);"
									disabled="#{!IndividuoPopupBean.editDatos}"/>
								
								<f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim>
								<f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim>
								
								<h:outputText id="outTipoDNI" value="Tipo Documento: " styleClass="texto" />
								<h:selectOneMenu id="lstTDoc" value="#{IndividuoPopupBean.individuoEvaluacion.tipoDocumento.idTipoDocumento}"
									styleClass="lista" immediate="true" onfocus="encender(this);" onblur="apagar(this);" style="width: 200px"
									disabled="#{!IndividuoPopupBean.editDatos}">
									<f:selectItems value="#{IndividuoPopupBean.listTipoDni}" id="selectItemIdTipoDoc"/>
								</h:selectOneMenu>

								<h:outputText id="outNroDoc" value="Número: " styleClass="texto" />
								<x:inputText id="inNroDoc" forceId="true" value="#{IndividuoPopupBean.individuoEvaluacion.nroDocumento}" size="20"
									maxlength="20" styleClass="bordeceldainferior" style="width: 150px" onfocus="encender(this);" onblur="apagar(this);"
									onkeypress="return soloEnteros(this,event);" disabled="#{!IndividuoPopupBean.editDatos}"/>

								<h:outputText id="outSexo" value="Sexo: " styleClass="texto" />
								<h:selectOneMenu id="lstsexo" value="#{IndividuoPopupBean.idTipoSexoSeleccionado}"
									styleClass="lista" immediate="true" onfocus="encender(this);" disabled="#{!IndividuoPopupBean.editDatos}"
									onblur="apagar(this);" style="width: 200px" onchange="javascript:setValueId('fechaNac','idFoco');">
									<f:selectItems value="#{IndividuoPopupBean.listTipoSexo}" id="selectItemIdTipoSexo"/>
								</h:selectOneMenu>
			 					
			 					<h:outputText value="Fecha Nacimiento: " id="outFechaNac" styleClass="texto"/>
								<x:inputCalendar id="fechaNac" monthYearRowClass="yearMonthHeader" weekRowClass="weekHeader" 
			                		currentDayCellClass="currentDayCell" popupButtonStyleClass="standard_bold"
			                		value="#{IndividuoPopupBean.fechaNacimiento}" renderAsPopup="true"
					                styleClass="bordeceldainferior" style="width: 90px"
					                popupTodayString="#{example_messages['popup_today_string']}"
			        		        popupDateFormat="dd/MM/yyyy" popupWeekString="#{example_messages['popup_week_string']}"
					                helpText="DD/MM/YYYY" forceId="true"
					                disabled="#{!IndividuoPopupBean.editDatos}"/>
			 					
								<h:outputText id="outMail" value="Casilla Email: " styleClass="texto" />
								<x:inputText id="inMail" forceId="true" value="#{IndividuoPopupBean.email}" size="50"
									maxlength="50" styleClass="bordeceldatext" style="width: 150px" onfocus="encender(this);" onblur="apagar(this);"
									disabled="#{!IndividuoPopupBean.editEmail}"/>
								
								<f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim>
								<f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim>
							</h:panelGrid>
						</h:panelGrid>
							<%-- GESTIONAR DOMICILIOS --%>
						<h:panelGrid columns="1" id="panelSecuandarioTres" align="center" width="850" rendered="#{IndividuoPopupBean.verDomicilio}">
							<s:fieldset legend="Domicilio" id="fieldDomicilio">
							<h:outputText id="outCuatroObs" value="Observación del verificador :" />
								<x:inputTextarea id="inCuatroObs" value="#{IndividuoPopupBean.nota_cred}" 
								    styleClass="background:colorSuave" style="width : 400px; height : 50px;" onfocus="encender(this);"
									onblur="apagar(this);" onchange="setValueId('inDosObs','idFoco');"/>
							
	                            <h:panelGrid columns="3" align="center" id="domic">
									<h:panelGrid columns="4" id="panelDomicilio" width="600" align="center">
											<h:outputText id="txtCalle" value="Calle:" styleClass="texto"/>
											<h:outputText id="outTxtCalle" value="#{IndividuoPopupBean.individuoEvaluacion.domicilio.calleNombre}" styleClass="textoblue" />
											<h:outputText id="txtNumero" value="Número:" styleClass="texto"/>
											<h:outputText id="outTxtNumero" value="#{IndividuoPopupBean.individuoEvaluacion.domicilio.calleNumero}" styleClass="textoblue"/>
											<h:outputText id="txtBarrio" value="Barrio:" styleClass="texto"/>
											<h:outputText id="outTxtBarrio" value="#{IndividuoPopupBean.individuoEvaluacion.domicilio.barrio.descripcion}" styleClass="textoblue"/>
											<h:outputText id="txtLocalidad" value="Localidad:" styleClass="texto"/>
											<h:outputText id="outTxtLocalidad" value="#{IndividuoPopupBean.individuoEvaluacion.domicilio.localidad.nombre}" styleClass="textoblue"/>
									</h:panelGrid>
									<x:commandLink id="agregarDomicilioLink" action="#{IndividuoPopupBean.agregarDomicilioPopup}" disabled="#{!IndividuoPopupBean.editDomicilio}">
												<x:graphicImage value="/img/icon/home_24.gif" title="Agregar-Modificar domicilio." border="0" id="botonImagenUno"/>
									</x:commandLink>
									<x:commandLink id="eliminarDomicilioLink" action="#{IndividuoPopupBean.eliminarDomicilio}" >
												<x:graphicImage value="/img/quitar.png" title="Borra domicilio incorrecto." border="0" id="botonImagenElim"/>
									</x:commandLink>
								</h:panelGrid>
							</s:fieldset>							
						</h:panelGrid>

						<%-- GESTIONAR TELEFONOS --%>

						<s:fieldset legend="Teléfonos" id="teledonos">
							<h:panelGrid columns="2" id="panelTelefono" width="650" align="center">
								<h:panelGroup id="panelGroupTelefono">
									<h:dataTable value="#{IndividuoPopupBean.listTelefono}"
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
											<x:commandLink action="#{IndividuoPopupBean.editarTelefono}" id="editarTelefonoLink" disabled="#{!IndividuoPopupBean.editTelefono}">
												<f:param id="idTelEdi" name="idTelEdi" value="#{individuoTelefono.telefono.idTelefono}" />
												<x:graphicImage value="/img/editar.gif" title="Editar el Teléfono." border="0" id="botonImagenDos" />
											</x:commandLink>
										</h:column>
										<h:column>
											<x:commandLink action="#{IndividuoPopupBean.eliminarTelefono}" id="eliminarTelefonoLink" disabled="#{!IndividuoPopupBean.editTelefono}">
												<f:param id="idTelefono" name="idTelefono" value="#{individuoTelefono.telefono.idTelefono}"/>
												<x:graphicImage value="/img/cat_act.gif" title="Eliminar un Teléfono." border="0" id="botonImagenTres" />
											</x:commandLink>
										</h:column>
									</h:dataTable>
								</h:panelGroup>
								<h:panelGroup id="panelGroupBotones">
									<x:commandLink id="agregarTelefonoLink" action="#{IndividuoPopupBean.agregarTelefono}" disabled="#{!IndividuoPopupBean.editTelefono}">
										<x:graphicImage value="/img/cat_pad.gif" title="Agregar Teléfono." border="0" id="botonImagenCuatro"/>
									</x:commandLink>
								</h:panelGroup>
							</h:panelGrid>
						</s:fieldset>

					<%-- Informacion Familiar --%>
						<s:fieldset id="informeFamiliar" legend="Información Familiar y Educación">
							<f:verbatim> <br> </f:verbatim>
							<h:panelGrid columns="1" id="panelDatosF" width="850" align="center">
								<s:fieldset legend="Datos Familiares" id="datosFamiliares">
									<h:panelGrid columns="4" id="panelDatosFamiliares" width="650" align="center">
										<h:outputText id="outEstadoCivil" value="Estado Civil: " styleClass="texto" />
										<h:selectOneMenu id="lstestadoCivil" value="#{IndividuoPopupBean.idEstadoCivilSeleccionado}"
											styleClass="lista" immediate="true" onfocus="encender(this);"
											onblur="apagar(this);" style="width: 200px" disabled="#{!IndividuoPopupBean.editFamilia}"
											onchange="javascript:setValueId('inCantHijos','idFoco');">
											<f:selectItems value="#{IndividuoPopupBean.listEstadoCivil}" id="selectItemIdEstadoCivil" />
										</h:selectOneMenu>
										<h:outputText id="outHijo" value="Hijos a Cargo: " styleClass="texto" />
										<h:inputText id="inCantHijos" value="#{IndividuoPopupBean.individuoEvaluacion.hijosCargo}"
											styleClass="bordeceldainferior" maxlength="2"
											style="width: 50" onfocus="encender(this);" disabled="#{!IndividuoPopupBean.editFamilia}"
											onblur="apagar(this);" onkeypress="return soloEnteros(this,event);"
											onchange="javascript:setValueId('lstEducacion','idFoco');" />
									</h:panelGrid>
								</s:fieldset>
					 			<s:fieldset id="datosEstudio" legend="Estudios">
									<h:panelGrid columns="4" id="panelEstudios" width="650" align="center">
										<h:outputText id="outEducacion" value="Educación: "	styleClass="texto" />
										<h:selectOneMenu id="lstEducacion" value="#{IndividuoPopupBean.idEducacionSeleccionado}"
											styleClass="lista" immediate="true" onfocus="encender(this);"
											onblur="apagar(this);" style="width: 200px" disabled="#{!IndividuoPopupBean.editFamilia}"
											valueChangeListener="#{IndividuoPopupBean.cambiarProfesion}"
											binding="#{IndividuoPopupBean.educacionHtml}"
											onchange="javascript:setValueId('lstEducacion','idFoco'); submit();">
											<f:selectItems value="#{IndividuoPopupBean.listEducacion}" id="selctItemIdEducSeleccionada" />
										</h:selectOneMenu>
										<h:outputText id="outProfesion" value="Profesión: "	styleClass="texto" />
										<h:selectOneMenu id="lstPorfesion" 
											value="#{IndividuoPopupBean.idProfesionSeleccionado}"
											styleClass="lista" immediate="true" onfocus="encender(this);"
											onblur="apagar(this);" style="width: 200px;"
											disabled="#{IndividuoPopupBean.habilitarProfesion}">
											<f:selectItems id="selectoUno" value="#{IndividuoPopupBean.listProfesion}" />
										</h:selectOneMenu>
									</h:panelGrid>
								</s:fieldset>
					<%--			<c:if test="${IndividuoPopupBean.origen != 1}">
									<s:fieldset legend="Vinculo" id="vinculo">
										<h:panelGrid columns="4" id="panelvinculo" width="650"
											align="center">
											<h:outputText id="outVinculo"
												value="Vinculo con el Solicitante: " styleClass="texto" />
											<h:selectOneMenu id="lstVinculo"
												value="#{IndividuoPopupBean.individuoEvaluacion.vinculo.idVinculo}"
												styleClass="lista" immediate="true"
												onfocus="encender(this);" onblur="apagar(this);"
												style="width: 200px"
												onchange="javascript:setValueId('lstDomEnv','idFoco');">
												<f:selectItems value="#{IndividuoPopupBean.listVinculo}"
													id="selctItemIdVinvulo" />
											</h:selectOneMenu>
											<f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim>
											<f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim>
										</h:panelGrid>
									</s:fieldset>
								</c:if> --%>
							</h:panelGrid>
						</s:fieldset>

						<s:layoutingTitlePane id="datosActividad"
							label="Datos de su Actividad"
							containerNodeClass="contentTitlePane"
							labelNodeClass="labelTitlePane" >
<%--							rendered="#{IndividuoPopupBean.panelJ}"> --%>
							<h:panelGrid columns="1" align="center" id="panelIternoCuatro"
								width="850">
								<%-- Datos Empresa --%>
								<s:fieldset legend="Datos Empresa" id="datosEmpresa" rendered="#{!IndividuoPopupBean.vengoDeComercio}">
									<h:panelGrid columns="4" align="center" id="panelIternoLaboral"
										width="650">
										<h:outputText value="C.U.I.T.: " id="outCuit" styleClass="texto" />
										<h:panelGroup id="uno">
											<h:inputText id="inCuit" disabled="#{!IndividuoPopupBean.editActividad}"
												value="#{IndividuoPopupBean.nroCuit}" maxlength="11"
												styleClass="bordeceldainferior" style="width: 150px"
												onfocus="encender(this);" onblur="apagar(this);"
												onkeypress="return soloEnteros(this,event);" />
											<f:verbatim>&nbsp;&nbsp;&nbsp;</f:verbatim>
											<x:commandButton id="btnIngresarCuit" value="Buscar Empresa"
												action="#{IndividuoPopupBean.buscarEmpresa}"
												title="Buscar Empresas" image="/img/icon/reload_page.png"
												alt="Buscar Empresa."  disabled="#{!IndividuoPopupBean.editActividad}"/>
											<%-- 	disabled="#{IndividuoPopupBean.botonEmpresa}" --%> 
											<f:verbatim>&nbsp;&nbsp;</f:verbatim>
											<x:commandButton id="btnNuevaEmpresa" value="Agregar Empresa"
												action="#{IndividuoPopupBean.agregarEmpresa}"
												title="Agregar Empresa" image="/img/cat_pad.gif"
												alt="Agregar Empresa." disabled="#{!IndividuoPopupBean.editActividad}"/>
											<f:verbatim>&nbsp;&nbsp;</f:verbatim>
											<x:commandButton id="btnVerEmpresa" title="Mostrar cliente"
												action="#{IndividuoPopupBean.popUpListaTclientes}"
												image="/img/icon/view.gif" value="Ver Cliente" />
										</h:panelGroup>
										<h:outputText value="Sucursal: " id="outSuc" styleClass="texto" />
										<h:selectOneMenu id="lstSuc"
											value="#{IndividuoPopupBean.idSucursalSeleccionado}"
											styleClass="lista" immediate="true" onfocus="encender(this);"
											onblur="apagar(this);" style="width: 200px"
											disabled="#{IndividuoPopupBean.habilitarSucursal}"
											binding="#{IndividuoPopupBean.sucursalHtml}"
											valueChangeListener="#{IndividuoPopupBean.cambioSucursal}"
											onchange="javascript:setValueId('lstOcupacion','idFoco'); submit();">
											<f:selectItems value="#{IndividuoPopupBean.listSucEmp}" id="itemSuc" />
										</h:selectOneMenu>
										<h:outputText value="Razón Social: " id="outRS" styleClass="texto" />
										<h:outputText id="outRazonSocial"
											value="#{IndividuoPopupBean.empresa.razonSocial}"
											styleClass="textoblue" />
										<h:outputText value="Dirección: " id="outD" styleClass="texto" />
										<h:outputText id="outDireccion"
											value="#{IndividuoPopupBean.direccionSucursal}"
											styleClass="textoblue" />
										<h:outputText value="Teléfono: " id="outT" styleClass="texto" />
										<h:outputText value="#{IndividuoPopupBean.telefono}"
											id="outTelefono" styleClass="textoblue" />
										<f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim>
										<f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim>
									</h:panelGrid>
								</s:fieldset>
							</h:panelGrid>
							<%-- Datos Laboraless --%>
							<h:panelGrid columns="1" id="panleLaboralesUno" width="850"
								align="center">
								<s:fieldset legend="Datos Laborales" id="datosLaborales">
									<h:panelGrid columns="4" id="panleLaborales" width="650"
										align="center">
										<h:outputText value="Ocupación: " id="outOcupacion"
											styleClass="tecto" />
										<h:selectOneMenu id="lstOcupacion" disabled="#{!IndividuoPopupBean.editActividad}"
											value="#{IndividuoPopupBean.idOcupacion}"
											styleClass="lista" immediate="true" onfocus="encender(this);"
											onblur="apagar(this);" style="width: 200px">
											<f:selectItems id="selctItemIdOcupacionSeleccionada" 
												value="#{IndividuoPopupBean.listOcupacion}"/>
										</h:selectOneMenu>
										<h:outputText value="Cargo: " id="outCargo" styleClass="texto" />
										<h:inputText id="inCargo"
											value="#{IndividuoPopupBean.cargo}" size="50"
											maxlength="50" styleClass="bordeceldatext"
											style="width: 150px" onfocus="encender(this);"
											onblur="apagar(this);" disabled="#{!IndividuoPopupBean.editActividad}"/>
										<h:outputText value="Fecha Ingreso: " id="outFIngreso" styleClass="texto" rendered="#{!IndividuoPopupBean.vengoDeComercio}"/>
										<x:inputCalendar id="fechaIng" disabled="#{!IndividuoPopupBean.editActividad}"  rendered="#{!IndividuoPopupBean.vengoDeComercio}"
											monthYearRowClass="yearMonthHeader" weekRowClass="weekHeader"
											currentDayCellClass="currentDayCell"
											popupButtonStyleClass="standard_bold"
											value="#{IndividuoPopupBean.ingreso}"
											renderAsPopup="true" styleClass="bordeceldainferior"
											style="width: 90px"
											onchange="javascript:setValueId('inAntiguedad','idFoco'); submit();"
											popupTodayString="#{example_messages['popup_today_string']}"
											immediate="true" popupDateFormat="dd/MM/yyyy"
											popupWeekString="#{example_messages['popup_week_string']}"
											forceId="true"
											valueChangeListener="#{IndividuoPopupBean.calcularAntiguedad}"
											binding="#{IndividuoPopupBean.fechaIngreso}" />
										<h:outputText value="Antigüedad: " id="outAnt"  rendered="#{!IndividuoPopupBean.vengoDeComercio}"
											styleClass="texto" />
										<h:outputText id="inAntiguedad"
											value="#{IndividuoPopupBean.antiguedad}"
											styleClass="textoblue" />
										<h:outputText value="Sueldo: " id="outSueldo"  rendered="#{!IndividuoPopupBean.vengoDeComercio}"
											styleClass="texto" />
										<h:inputText id="inSueldo" disabled="#{!IndividuoPopupBean.editActividad}"  rendered="#{!IndividuoPopupBean.vengoDeComercio}"
											value="#{IndividuoPopupBean.sueldoNeto }" size="5"
											maxlength="5" styleClass="bordeceldainferior"
											style="width: 50px" onfocus="encender(this);"
											onblur="apagar(this);"
											onkeypress="return soloDecimales(this,event);" />
										<f:verbatim>&nbsp;&nbsp;&nbsp;</f:verbatim>
										<f:verbatim>&nbsp;&nbsp;&nbsp;</f:verbatim>
										<h:outputText value="Empleo Anterior: " id="outEmplAnt"  rendered="#{!IndividuoPopupBean.vengoDeComercio}"
											styleClass="texto" />
										<h:inputText id="inEmplAnt" disabled="#{!IndividuoPopupBean.editActividad}"
											value="#{IndividuoPopupBean.empleoAnterior}" size="50"  rendered="#{!IndividuoPopupBean.vengoDeComercio}"
											maxlength="100" styleClass="bordeceldatext"
											style="width: 150px" onfocus="encender(this);"
											onblur="apagar(this);" />
										<h:outputText value="Teléfono: " id="outTelefonoLaboral"  rendered="#{!IndividuoPopupBean.vengoDeComercio}"
											styleClass="texto" />
										<h:inputText id="inTelefonoLaboral" disabled="#{!IndividuoPopupBean.editActividad}"
											value="#{IndividuoPopupBean.telEmpleoAnt}" size="15"  rendered="#{!IndividuoPopupBean.vengoDeComercio}"
											maxlength="50" styleClass="bordeceldainferior"
											style="width: 150px" onfocus="encender(this);"
											onblur="apagar(this);" />
									</h:panelGrid>
									<h:panelGrid columns="4" id="panelReferencia" width="650"  rendered="#{!IndividuoPopupBean.vengoDeComercio}"
										align="center">
										<h:outputText value="Referencia Comercial: " id="outRefComer"
											styleClass="texto" />
										<h:inputText id="inRefCom" disabled="#{!IndividuoPopupBean.editActividad}"
											value="#{IndividuoPopupBean.referencias}" size="50"
											maxlength="200" styleClass="bordeceldatext"
											style="width: 150px" onfocus="encender(this);"
											onblur="apagar(this);" />
										<f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim>
										<f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim>
									</h:panelGrid>
								</s:fieldset>
							</h:panelGrid>
							<h:panelGrid columns="1" id="panelIternoOtrosIngresosUno" 
								width="850" align="center"  rendered="#{!IndividuoPopupBean.vengoDeComercio}">
								<s:fieldset legend="Otros Ingresos" id="ingresos">
									<h:panelGrid columns="4" id="panelIternoOtrosIngresos"
										width="650" align="center">
										<h:outputText value="Descripción: " id="outOI"
											styleClass="texto" />
										<h:inputText id="inDesc" disabled="#{!IndividuoPopupBean.editActividad}"
											value="#{IndividuoPopupBean.otrosIngresosDesc}"
											size="50" maxlength="200" styleClass="bordeceldatext"
											style="width: 150px" onfocus="encender(this);"
											onblur="apagar(this);" />
										<h:outputText value="Monto: " id="outMonto" styleClass="texto" />
										<h:inputText id="inMonto" disabled="#{!IndividuoPopupBean.editActividad}"
											value="#{IndividuoPopupBean.otrosIngresosMonto}"
											size="6" maxlength="5" styleClass="bordeceldainferior"
											style="width: 150px" onfocus="encender(this);"
											onblur="apagar(this);"
											onkeypress="return soloDecimales(this,event);" />
									</h:panelGrid>
								</s:fieldset>
							</h:panelGrid>
						</s:layoutingTitlePane>

					<f:verbatim><br></f:verbatim>
					<%-- Datos para la Factura --%>
					<%-- Datos para la Factura --%>
					<s:layoutingTitlePane id="paraFacturacion" label="Datos para la Facturación" containerNodeClass="contentTitlePane" 
							labelNodeClass="labelTitlePane" value="false"  rendered="#{!IndividuoPopupBean.vengoDeComercio}">
						<h:panelGrid columns="1" id="panelParaFacturacion" align="center" width="850">
							<h:panelGrid columns="5" id="interno" align="center" width="650">
								<h:outputText value="Domicilio Envio:" id="outDomicilioEnvio" styleClass="texto" />
								<h:selectOneMenu id="lstDomEnv" value="#{IndividuoPopupBean.idDomicilioPagoSeleccionado}" styleClass="lista" 
										immediate="true" onfocus="encender(this);" onblur="apagar(this);" style="width: 200px" 
										 disabled="#{!IndividuoPopupBean.editFacturacion}">
									<f:selectItems value="#{IndividuoPopupBean.listDomicilioPago}" id="selctItemListaDomPag" />
								</h:selectOneMenu>
								
								<h:outputText value="Dia Seleccionado: #{IndividuoPopupBean.diaPagoSeleccionadoInfo}" id="outDiaPagoSeleccionado" styleClass="texto"  />
							</h:panelGrid>
 

						</h:panelGrid>
					</s:layoutingTitlePane>
			
					<s:layoutingTitlePane id="datosFinancieros" label="Datos Financieros" 
							containerNodeClass="contentTitlePane" labelNodeClass="labelTitlePane"
							rendered="false">

						<h:panelGrid columns="1" id="panelInternoSeis" width="850" align="center">
							<s:fieldset legend="Cuenta" id="cuenta">
								<h:panelGrid columns="4" id="internoTres" align="center" width="650">
									<h:outputText value="Banco: " id="outBanco" styleClass="texto" />
									<h:selectOneMenu id="lstBanco" value="#{IndividuoPopupBean.idBcoSeleccionado}" styleClass="lista"
										immediate="true" onfocus="encender(this);" onblur="apagar(this);" style="width: 200px" 
										disabled="#{IndividuoPopupBean.mostrarCbu}" 
										onchange="javascript:setValueId('lstCuenta','idFoco'); submit();">
										<f:selectItems value="#{IndividuoPopupBean.listBancos}" id="selctItemIdBcoSeleccionado" />
									</h:selectOneMenu>
									<f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim>
									<f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim>
									<h:outputText value="Cuenta: " id="outCuenta" styleClass="texto" />
									<h:selectOneMenu id="lstCuenta" value="#{IndividuoPopupBean.idTipoCuentaSeleccionado}" styleClass="lista" 
										immediate="true" onfocus="encender(this);" onblur="apagar(this);" style="width: 200px" 
										disabled="#{IndividuoPopupBean.mostrarCbu}" onchange="javascript:setValueId('inNroCuenta','idFoco');">
										<f:selectItems value="#{IndividuoPopupBean.listTipoCtas}" id="selctItemIdDiaPagoSeleccionado" />
									</h:selectOneMenu>
									<h:outputText value="Nro. Cuenta: " id="outNroCuenta" styleClass="texto" />
									<h:inputText id="inNroCuenta" value="#{IndividuoPopupBean.individuoEvaluacion.nroCuenta}" styleClass="bordeceldainferior" 
										maxlength="20" style="width: 150" onfocus="encender(this);" onblur="apagar(this);" onkeypress="return soloEnteros(this,event);" 
										size="20" disabled="#{IndividuoPopupBean.mostrarCbu}" onchange="javascript:setValueId('cbu','idFoco');" />
									<h:outputText value="CBU: " styleClass="texto" style="COLOR: #000000; font: bold;" id="outCBU" />
									<h:selectBooleanCheckbox id="cbu" value="#{IndividuoPopupBean.esCBU}" 
										onclick="javascript:setValueId('inNroCBU','idFoco'); submit();"  disabled="#{!IndividuoPopupBean.editFinanciero}"/>
									<h:outputText value="Nro. CBU: " id="outNroCBU" styleClass="texto" />
									<x:inputText id="inNroCBU" value="#{IndividuoPopupBean.individuoEvaluacion.cbu}" size="22" maxlength="22" 
										styleClass="bordeceldainferior" style="width: 150px" onfocus="encender(this);" onblur="apagar(this);" 
										disabled="#{!IndividuoPopupBean.mostrarCbu}" onchange="javascript:setValueId('inCuit','idFoco');" 
										onkeypress="return soloEnteros(this,event);" />
									
								</h:panelGrid>
							</s:fieldset>

							<s:fieldset legend="Vinculo Banco" id="vinculoBco">
								<h:panelGrid columns="4" id="panelBancos" width="650" align="center">
									<h:outputText value="Banco: " id="outBco" styleClass="texto"/>
									<h:outputText value="" id="inBco" styleClass="textoblue"/>
									<h:outputText value="Vinculación: " id="outVinc" styleClass="texto"/>
									<h:inputText id="inVinculo" value="#{IndividuoPopupBean.bancos.vinculacion}" size="50" maxlength="100" 
										styleClass="bordeceldatext" style="width: 150px" onfocus="encender(this);" 
										onblur="apagar(this);"  disabled="#{!IndividuoPopupBean.editFinanciero}"/>
								</h:panelGrid>
							</s:fieldset>
							
							<s:fieldset legend="Tarjeta" id="tarjeta">
								<h:panelGrid columns="4" id="panelTarjetas" width="650" align="center">
									<h:outputText value="Tarjeta: " id="outTarjeta" styleClass="texto"/>
									<h:inputText id="inTarj" value="#{IndividuoPopupBean.tarjeta.descripcion}" size="50" maxlength="50" 
										styleClass="bordeceldatext" style="width: 150px" onfocus="encender(this);" 
										onblur="apagar(this);" disabled="#{!IndividuoPopupBean.editFinanciero}"/>
									<h:outputText value="Número: " id="outNroTarj" styleClass="texto"/>
									<h:inputText id="inNroTarj" value="#{IndividuoPopupBean.tarjeta.nro}" size="20" maxlength="16" 
										styleClass="bordeceldainferior" style="width: 150px" onfocus="encender(this);" 
										onblur="apagar(this);" onkeypress="return soloEnteros(this,event);"
										disabled="#{!IndividuoPopupBean.editFinanciero}"/>
									<h:outputText value="Banco: " id="outBancos" styleClass="texto"/>
									<h:selectOneMenu id="lstBancos" value="#{IndividuoPopupBean.idBancoSeleccionado}"
											styleClass="lista" immediate="true" onfocus="encender(this);" disabled="#{!IndividuoPopupBean.editFinanciero}"
											onblur="apagar(this);" style="width: 200px">
											<f:selectItems value="#{IndividuoPopupBean.listBancos}" id="selctItemIdBancoSeleccionado"/>
									</h:selectOneMenu>
									<f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim>
									<f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim>
								</h:panelGrid>
							</s:fieldset>
						</h:panelGrid>
						
					</s:layoutingTitlePane>
					
					<s:layoutingTitlePane id="datosPropiedad" label="Datos Propiedades"
							containerNodeClass="contentTitlePane" labelNodeClass="labelTitlePane"
							rendered="false"> 
						<h:panelGrid columns="1" id="panelInternoCinco" width="850" align="center">
						
							<s:fieldset legend="Domicilio Inmueble" id="datosInmuebles">
								<h:panelGrid columns="2" id="panelDomicilioInmueble" width="650" align="center">
									<h:panelGroup id="panelGroupTable">
									<h:dataTable value="#{IndividuoPopupBean.listDomicilioInmueble}"
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
											<x:commandLink action="#{IndividuoPopupBean.editarDomicilioInmueble}" id="editarDomicilioInmueblesLink"
														onclick="javascript:setValueId('datosInmuebles','idFoco');" disabled="#{!IndividuoPopupBean.editPropiedades}">
												<f:param id="idDomiEdi" name="idDomiEdi" value="#{domicilioInmueble.domicilio.idDomicilio}" />
												<x:graphicImage value="/img/editar.gif" title="Editar el domicilio Inmueble." border="0" id="botnoImagenCinco"/>
											</x:commandLink>
										</h:column>

										<h:column>
											<x:commandLink action="#{IndividuoPopupBean.eliminarDomicilioInmueble}" id="eliminarDomicilioInmueblrsLink"
													disabled="#{!IndividuoPopupBean.editPropiedades}">
												<f:param id="idDomicilio" name="idDomicilio" value="#{domicilioInmueble.domicilio.idDomicilio}"/>
												<x:graphicImage value="/img/cat_act.gif" title="Eliminar un domicilio Inmueble." border="0" id="botonImagenSeis" />
											</x:commandLink>
										</h:column>
										
									</h:dataTable>
									</h:panelGroup>
									
									<h:panelGroup id="panelGroupagregarDom"> 
										<x:commandLink id="agregarDomicilioInmueblesLink" action="#{IndividuoPopupBean.agregarDomicilioInmueble}" 
													disabled="#{!IndividuoPopupBean.editPropiedades}">
											<x:graphicImage value="/img/icon/home_24.gif" title="Agregar domicilios inmueble." border="0" id="botonImagenSiete"/>
										</x:commandLink>
									</h:panelGroup>

								</h:panelGrid>
							</s:fieldset>
							
							<s:fieldset legend="Vehiculo" id="vehiculo">
								<h:panelGrid columns="4" id="panelVahiculo" width="650" align="center">
									
									<h:outputText value="Marca: " id="outMarca" styleClass="texto"/>
									<h:inputText id="inMarca" value="#{IndividuoPopupBean.individuoVehiculo.vehiculo.marca}" size="50" maxlength="100" 
										styleClass="bordeceldatext" style="width: 150px" onfocus="encender(this);" onblur="apagar(this);"
										disabled="#{!IndividuoPopupBean.editPropiedades}"/>
									
									<h:outputText value="Modelo: " id="outModelo" styleClass="texto"/>
									<h:inputText id="inModelo" value="#{IndividuoPopupBean.individuoVehiculo.vehiculo.modelo}" size="50" maxlength="100" 
										styleClass="bordeceldatext" style="width: 150px" onfocus="encender(this);" onblur="apagar(this);"
										disabled="#{!IndividuoPopupBean.editPropiedades}"/>
									
									<h:outputText value="Año: " id="outAnio" styleClass="texto"/>
									<h:inputText id="inAnio" value="#{IndividuoPopupBean.individuoVehiculo.vehiculo.anio}" size="4" maxlength="4" 
										styleClass="bordeceldainferior" style="width: 50px" onfocus="encender(this);" onblur="apagar(this);"
										disabled="#{!IndividuoPopupBean.editPropiedades}"/>
									
									<h:outputText value="Patente: " id="outPatente" styleClass="texto"/>
									<h:inputText id="inPatente" value="#{IndividuoPopupBean.individuoVehiculo.vehiculo.patente}" size="7" maxlength="7" 
										styleClass="bordeceldatext" style="width: 150px" onfocus="encender(this);" onblur="apagar(this);"
										disabled="#{!IndividuoPopupBean.editPropiedades}"/>
										
									<h:outputText value="Es Propio: " id="outPropio" styleClass="texto"/>
									<h:selectBooleanCheckbox value="#{IndividuoPopupBean.vehiculoPropio}" id="vehiculoPropio" immediate="true" 
										disabled="#{!IndividuoPopupBean.editPropiedades}"/>
									
									<f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim>
									<f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim>
									
								</h:panelGrid>
							</s:fieldset>
						</h:panelGrid>
					</s:layoutingTitlePane>
	
					<s:layoutingTitlePane id="datosObservaciones" label="Observaciones"  rendered="#{!IndividuoPopupBean.vengoDeComercio}"
							containerNodeClass="contentTitlePane" labelNodeClass="labelTitlePane">
						<h:panelGrid columns="1" id="panelInternoDiez" width="650" align="center">
							<h:outputText value="Observación: " id="outObservaciones" styleClass="texto"/>
							<x:inputTextarea id="inObservaciones" value="#{IndividuoPopupBean.individuoEvaluacion.observacion}" 
								styleClass="bordeceldatext" style="width : 650px; height : 50px;" onfocus="encender(this);" 
								onblur="apagar(this);"/>
						</h:panelGrid>
					</s:layoutingTitlePane>

					<f:verbatim><br></f:verbatim>
					
					<s:layoutingTitlePane id="datosDocDig" label="Documentos Digitales"  rendered="#{!IndividuoPopupBean.vengoDeComercio}"
							containerNodeClass="contentTitlePane" labelNodeClass="labelTitlePane">
						<h:panelGrid columns="4" id="panelInternoOnce" width="650" align="center">
						
							<h:outputText value="Tipo Documentos: " id="outTDoc" styleClass="texto"/>
							<h:selectOneMenu id="lstTDocs" value="#{IndividuoPopupBean.idTipoDocSeleccionado}"
								styleClass="lista" immediate="true" onfocus="encender(this);"
								onblur="apagar(this);" style="width: 200px" disabled="#{!IndividuoPopupBean.editDigitales}">
								<f:selectItems value="#{IndividuoPopupBean.listTipoDigital}" id="selectItemLstDocs"/>
							</h:selectOneMenu>
							
							<h:outputText value="Archivo: " id="outArch" styleClass="texto"/>

							<x:inputFileUpload id="fileUpLoad" storage="file" styleClass="fileUploadInput" maxlength="1000"
								value="#{IndividuoPopupBean.uploadedFile}" onfocus="encender(this);" onblur="apagar(this);" 
								disabled="#{!IndividuoPopupBean.editDigitales}"/>
														
							<h:outputText value="Descripción: " id="outDesc" styleClass="texto"/>
							<h:inputText id="inDescrip" value="#{IndividuoPopupBean.docDigital.descripcion}" maxlength="200" 
								styleClass="bordeceldatext" style="width: 150px" onfocus="encender(this);" 
								onblur="apagar(this);" disabled="#{!IndividuoPopupBean.editDigitales}"/>
							<f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim>
							
							<h:commandButton value="Adjuntar" action="#{IndividuoPopupBean.saveFile}" 
								styleClass="botones" id="btonAdjuntarTDocLink" disabled="#{IndividuoPopupBean.botonAdjuntar}" 
								onclick="javascript:setValueId('lstTDocs','idFoco');"/>
						</h:panelGrid>
						<h:panelGrid columns="1" width="650" align="center" id="panelListDocAdj">
						<s:fieldset legend="Lista Documentos Adjuntos" id="listaDocAdj">
							<h:panelGrid columns="1" id="panelAdjuntos" width="650" align="center">
							<h:dataTable value="#{IndividuoPopupBean.listTipoDocumentos}"
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
										<h:commandLink value="#{listDocAdj.nombreArchivo}" action="#{IndividuoPopupBean.abrirArchivo}">
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
									    <x:commandLink action="#{IndividuoPopupBean.borrarArchivo}" disabled="#{!IndividuoPopupBean.editDigitales}">
										      <f:param id="idArchi" name="idArchi" value="#{listDocAdj.idWrappers}"/>
											  <x:graphicImage value="/img/borrar.gif" title="Eliminar archivo." border="0" id="botonImagenocho"/>
										</x:commandLink>
									</h:column>	
									</h:dataTable>
																
							</h:panelGrid>
						</s:fieldset>
						</h:panelGrid>
					</s:layoutingTitlePane>

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
							<c:if test="${IndividuoPopupBean.origen==3}">
							    <x:commandButton id="buttonGrabar" value="Guardar" action="#{IndividuoPopupBean.grabarIndividuo}"
										styleClass="botones" /> 
							</c:if>
							<c:if test="${IndividuoPopupBean.origen!=3}">
							    <x:commandButton id="buttonGrabar" value="Guardar" action="#{IndividuoPopupBean.guardar}"
										styleClass="botones" /> 
							</c:if>			
							<x:commandButton id="buttonCancelar" value="Salir" action="#{IndividuoPopupBean.salir}" 
								styleClass="botones"/>
						</h:panelGrid>

					</h:panelGrid>
					<x:inputHidden id="idFoco" value="#{IndividuoPopupBean.focoHidden}" forceId="true"/>
					<s:focus id="foco" for="#{IndividuoPopupBean.focoHidden}" />
				</h:panelGrid>
			</f:facet>
			<%@include file="/inc/page_footer.jsp" %>
			</x:panelLayout>
	</h:form>
</center>
</body>
</html>
</f:view>
