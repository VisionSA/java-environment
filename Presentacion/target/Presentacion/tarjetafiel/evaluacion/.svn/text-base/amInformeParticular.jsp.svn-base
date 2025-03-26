<%@include file="/inc/tags.jsp"%>
<jsp:useBean id="ahora" class="java.util.Date" scope="request" />

<f:view>
	<html>
	<head>
	<title><h:outputText value="#{InformeParticularBean.tituloLargo}" /></title>
	<s:script language="javascript">
    	function recargar() {
    		document.getElementById('amInformeParticular').submit();
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

	<body onbeforeunload="ShowWait('amInformeParticular');" onload="if('${InformeParticularBean.popup.mostrar}'=='true') {viewDialog.show();} else {window.scrollTo(0,${ScrollBean.hiddenScrollY});}">
	<center>
	<h:form id="amInformeParticular" enctype="multipart/form-data">

		<%-- GRABA EL ESTADO DEL SCROLL --%>
		<h:panelGroup rendered="#{!InformeParticularBean.popup.mostrar}">
			<%@include file="/inc/scroll.inc"%>
		</h:panelGroup>

		<x:panelLayout id="page" layout="#{globalOptions.pageLayout}"
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
						    	<td width="351" height="47" align="center" class="titulo"> ${InformeParticularBean.tituloLargo} <br>
					    	    	<span class="subtitulo"> ${InformeParticularBean.tituloCorto} </span></td>
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
					
					<h:panelGrid columns="1" align="center" id="panlePrincipal" width="900px">
					
						<s:layoutingTitlePane id="solicitud" label="Datos Solicitud"
							containerNodeClass="contentTitlePane" labelNodeClass="labelTitlePane">
							<h:panelGrid columns="4" id="panelUno" align="center" width="650">

								<h:outputText id="outNroSol" value="Nro. Solicitud: " styleClass="texto"/>
								<h:outputText id="outNroSolicitud" value="#{InformeParticularBean.nroSolicitud}" styleClass="textoblue"/>
								
							
								<h:outputText id="outFecha" value="FechaRecepción: " styleClass="texto" />
								<f:verbatim>
									<font style="COLOR: #0000ff;">
										<fmt:formatDate value="${InformeParticularBean.solicitud.fechaRecepcion}" pattern="dd/MM/yyyy" />
									</font>
								</f:verbatim>

							</h:panelGrid>
						</s:layoutingTitlePane>
						
						<f:verbatim><br></f:verbatim>						
			
						<s:layoutingTitlePane id="verificador" label="Datos Verificador"
							containerNodeClass="contentTitlePane" labelNodeClass="labelTitlePane">
														
							<h:panelGrid columns="4" id="panelDos" align="center" width="650">

								<h:outputText id="codigoVerificador" value="Código Verificador: " styleClass="texto" />
								<h:outputText id="outCodVerificador" value="#{InformeParticularBean.informeParticular.verificador.idVerificador}" styleClass="textoblue"/>
								
								<f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim>
								<f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim>
								
								<h:outputText id="apellido" value="Apellidos: " styleClass="texto" />
								<h:outputText id="outApellido" value="#{InformeParticularBean.informeParticular.verificador.apellido} " styleClass="textoblue"/>
								
								<h:outputText id="nombre" value="Nombres: " styleClass="texto" />
								<h:outputText id="outNombres" value="#{InformeParticularBean.informeParticular.verificador.nombre}" styleClass="textoblue"/>
											
							</h:panelGrid>
							
						</s:layoutingTitlePane>
						
						<f:verbatim><br></f:verbatim>
						
						<s:layoutingTitlePane id="individuo" label="Datos Individuo" containerNodeClass="contentTitlePane" labelNodeClass="labelTitlePane">
							
							<h:panelGrid columns="4" id="panelTres" align="center" width="650">

								<h:outputText id="apellidoPaterno" value="Apellido Paterno: " styleClass="texto" />
								<h:inputText id="inApellidoPaterno" value="#{InformeParticularBean.individuo.apellido}" size="50"
									maxlength="100" styleClass="bordeceldatext" style="width: 150px" onfocus="encender(this);" onblur="apagar(this);"/>
									
								<h:outputText id="outAMaterno" value="Apellido Materno: " styleClass="texto" />
								<x:inputText id="inAMaterno" value="#{InformeParticularBean.individuo.apellidoMaterno}" size="50"
									maxlength="100" styleClass="bordeceldatext" style="width: 150px" onfocus="encender(this);" onblur="apagar(this);"
									onchange="javascript:setValueId('inNombre','idFoco');"/>
									
								<h:outputText id="outNombre" value="Nombres: " styleClass="texto" />
								<x:inputText id="inNombre" value="#{InformeParticularBean.individuo.nombres}" size="50"
									maxlength="100" styleClass="bordeceldatext" style="width: 150px" onfocus="encender(this);" onblur="apagar(this);"
									onchange="javascript:setValueId('lstTDoc','idFoco');"/>
								
								<f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim>
								<f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim>
								
								<h:outputText id="outTipoDNI" value="Tipo Documento: " styleClass="texto" />
								<h:selectOneMenu id="lstTDoc" value="#{InformeParticularBean.idTipoDocumentoSeleccionado}"
									styleClass="lista" immediate="true" onfocus="encender(this);" onblur="apagar(this);" style="width: 200px">
									<f:selectItems value="#{InformeParticularBean.listaTipoDocumento}" id="selectItemIdTipoDoc"/>
								</h:selectOneMenu>

								<h:outputText id="outNroDoc" value="Número Documento: " styleClass="texto" />
								<x:inputText id="inNroDoc" forceId="true" value="#{InformeParticularBean.individuo.nroDocumento}" size="20"
									maxlength="20" styleClass="bordeceldainferior" style="width: 150px" onfocus="encender(this);" onblur="apagar(this);"
									onkeypress="return soloEnteros(this,event);"/>

							</h:panelGrid>
							<h:panelGrid columns="1" id="panelSecuandarioTres" align="center" width="850">
								<%-- GESTIONAR DOMICILIOS --%>

								<s:fieldset legend="Domicilio" id="domicilio">
								<h:panelGrid columns="1" id="panelDomicilio" width="650" align="center">

									<h:dataTable value="#{InformeParticularBean.lstDomicilio}"
										id="tablaDomicilio" styleClass="standardTable" headerClass="dataTable_Header"
										footerClass="standardTable_Header" rowClasses="standardTable_Row1,standardTable_Row2"
										columnClasses="standardTable_Column,standardTable_ColumnCentered,standardTable_Column"
										var="domicilio" style=" width : 600px;">
										
										<h:column>
											<f:facet name="header">
												<h:outputText value="Calle" styleClass="texto" style="font: bold;color: white;" />
											</f:facet>
											<h:outputText value="#{domicilio.calleNombre} " style=" width : 190px;" />
										</h:column>
										
										<h:column>
											<f:facet name="header">
												<h:outputText value="Número" styleClass="texto" style="font: bold;color: white;" />
											</f:facet>
											<h:outputText value="#{domicilio.calleNumero}" style=" width : 40px;" />
										</h:column>
										
										<h:column>
											<f:facet name="header">
												<h:outputText value="Barrio" styleClass="texto" style="font: bold;color: white;" />
											</f:facet>
											<h:outputText value="#{domicilio.barrio.descripcion}" style=" width : 40px;" />
										</h:column>
										
										<h:column>
											<f:facet name="header">
												<h:outputText value="Localidad" styleClass="texto" style="font: bold;color: white;" />
											</f:facet>
											<h:outputText value="#{domicilio.localidad.nombre}" style=" width : 40px;" />
										</h:column>
										
										<h:column>
											<x:commandLink action="#{InformeParticularBean.editarDomicilio}" id="editarDomicilioLink">
												<f:param id="idDomiEdi" name="idDomiEdi" value="#{domicilio.idDomicilio}" />
												<x:graphicImage value="/img/editar.gif" title="Editar el domicilio." border="0"/>
											</x:commandLink>
										</h:column>

									</h:dataTable>
									
								</h:panelGrid>
							</s:fieldset>							
							</h:panelGrid>
							
					    </s:layoutingTitlePane>
					
						<f:verbatim><br></f:verbatim>
					
						<s:layoutingTitlePane id="informante" label="Datos Informante" containerNodeClass="contentTitlePane"labelNodeClass="labelTitlePane">
							
							<h:panelGrid columns="4" id="panelCinco" width="650" align="center">
							
								<h:outputText id="outNomApeInf" value="Apellidos y Nombre: " styleClass="texto" />
								<h:inputText id="inNomApeInf" value="#{InformeParticularBean.informeParticular.informante}" styleClass="bordeceldatext" 
									maxlength="50" size="50" style="width: 150" onfocus="encender(this);" onblur="apagar(this);"/>

								<h:outputText id="outNroDocInf" value="Nro. Documento: " styleClass="texto" />
								<h:inputText id="inNroDocInf" value="#{InformeParticularBean.informeParticular.nroDocInform}" styleClass="bordeceldainferior" 
									maxlength="20" size="20" style="width: 150" onfocus="encender(this);" onblur="apagar(this);" onkeypress="return soloEnteros(this,event);"/>
									
							</h:panelGrid>
							
						</s:layoutingTitlePane>
					
						<f:verbatim><br></f:verbatim>
										
						<s:layoutingTitlePane id="observaciones" label="Detalles de Observaciones" containerNodeClass="contentTitlePane" labelNodeClass="labelTitlePane">
												
							<h:panelGrid columns="4" id="panelSeis" align="center" width="650">
							
								<h:outputText value="Corrección Nombre:" id="outCorreccionNombre" styleClass="texto"/>
								<h:selectBooleanCheckbox value="#{InformeParticularBean.correccionNombre}" id="correccionNombre"/>
								
								<h:outputText value="Corrección Domicilio: " id="outCorrecionDomicilio" styleClass="texto"/>
								<h:selectBooleanCheckbox value="#{InformeParticularBean.correccionDomicilio}" id="correcionDomicilio"/>
								
								<h:outputText value="Propietario Vivienda: " id="outPropietarioVivienda" styleClass="texto"/>
								<h:selectOneMenu id="lstPropietarioVivienda" value="#{InformeParticularBean.idPropietarioVivienda}"
									styleClass="lista" immediate="true" onfocus="encender(this);" onblur="apagar(this);" style="width: 200px">
									<f:selectItems value="#{InformeParticularBean.listaPropietarioVivienda}" id="selectItemPropietarioVivienda"/>
								</h:selectOneMenu>
								
								<h:outputText value="Tipo Vivienda: " id="outTipoVivi" styleClass="texto"/>
								<h:selectOneMenu id="lstTipoVivi" value="#{InformeParticularBean.idTipoViviendaSelecciondo}"
									styleClass="lista" immediate="true" onfocus="encender(this);" onblur="apagar(this);" style="width: 200px">
									<f:selectItems value="#{InformeParticularBean.listaTipoVivienda}" id="selctItemTipoVivi"/>
								</h:selectOneMenu>
								
								<h:outputText value="Estado Vivienda: " id="outEstadoVivienda" styleClass="texto"/>
								<h:selectOneMenu id="lstEstadoVivienda" value="#{InformeParticularBean.idEstadoViviendaSeleccionado}"
									styleClass="lista" immediate="true" onfocus="encender(this);" onblur="apagar(this);" style="width: 200px">
									<f:selectItems value="#{InformeParticularBean.listaEstadoVivienda}" id="selctItemEstadoVivienda"/>
								</h:selectOneMenu>
										
								<h:outputText value="Zona: " id="outZona" styleClass="texto"/>
								<h:selectOneMenu id="lstZona" value="#{InformeParticularBean.idZona}"
									styleClass="lista" immediate="true" onfocus="encender(this);" onblur="apagar(this);" style="width: 200px">
									<f:selectItems value="#{InformeParticularBean.listaZona}" id="selctItemZona"/>
								</h:selectOneMenu>
									
							</h:panelGrid>
								
						</s:layoutingTitlePane>
					
						<f:verbatim><br></f:verbatim>
					
						<s:layoutingTitlePane id="otrosDatos" label="Otros Datos" containerNodeClass="contentTitlePane" labelNodeClass="labelTitlePane">
					
							<h:panelGrid columns="4" align="center" id="panelSiete" width="650">
										
								<h:outputText value="Alquiler Mensual: " id="outAlquilerMensual" styleClass="texto"/>
								<h:inputText id="inCuit" value="#{InformeParticularBean.informeParticular.cuotaAlquiler}" maxlength="5"  
									styleClass="bordeceldainferior" style="width: 50px" onfocus="encender(this);" onblur="apagar(this);"/>
																		
								<h:outputText value="Antigüedad en el Domicilio: " id="antiguedadDomil" styleClass="texto"/>
								<h:inputText id="indAntiguedadDomil" value="#{InformeParticularBean.informeParticular.antiguedad}" maxlength="5"  
									styleClass="bordeceldainferior" style="width: 50px" onfocus="encender(this);" onblur="apagar(this);" size="5"/>
								
							</h:panelGrid>
							
					    </s:layoutingTitlePane>
					    
					    <f:verbatim><br></f:verbatim>
					    
					    <s:layoutingTitlePane id="vehiculo" label="Observo Vehiculo" containerNodeClass="contentTitlePane" labelNodeClass="labelTitlePane">
					    
					   		<h:panelGrid columns="4" id="panelOcho" width="650" align="center">
									
								<h:outputText value="Marca: " id="outMarca" styleClass="texto"/>
								<h:inputText id="inMarca" value="#{InformeParticularBean.informeVehiculo.vehiculo.marca}" size="50" maxlength="100" 
									styleClass="bordeceldatext" style="width: 150px" onfocus="encender(this);" onblur="apagar(this);"/>
									
								<h:outputText value="Modelo: " id="outModelo" styleClass="texto"/>
								<h:inputText id="inModelo" value="#{InformeParticularBean.informeVehiculo.vehiculo.modelo}" size="50" maxlength="100" 
									styleClass="bordeceldatext" style="width: 150px" onfocus="encender(this);" onblur="apagar(this);"/>
									
								<h:outputText value="Año: " id="outAnio" styleClass="texto"/>
								<h:inputText id="inAnio" value="#{InformeParticularBean.informeVehiculo.vehiculo.anio}" size="4" maxlength="4" 
									styleClass="bordeceldainferior" style="width: 50px" onfocus="encender(this);" onblur="apagar(this);"/>
									
								<h:outputText value="Patente: " id="outPatente" styleClass="texto"/>
								<h:inputText id="inPatente" value="#{InformeParticularBean.informeVehiculo.vehiculo.patente}" size="7" maxlength="7" 
									styleClass="bordeceldatext" style="width: 150px" onfocus="encender(this);" onblur="apagar(this);"/>
									
									
							</h:panelGrid>
							
						</s:layoutingTitlePane>
					
						<f:verbatim><br></f:verbatim>
						
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
							<x:commandButton id="buttonGrabar" value="Guardar" action="#{InformeParticularBean.guardar}" styleClass="botones"/>
							<x:commandButton id="buttonCancelar" value="Cancelar" action="#{InformeParticularBean.salir}" styleClass="botones"/>
						</h:panelGrid>
					
						<x:inputHidden id="idFoco" value="#{ComprobanteBean.focoHidden}" forceId="true"/>
						<s:focus id="foco" for="#{ComprobanteBean.focoHidden}" />
				    
				    </h:panelGrid>

				</h:panelGroup>
			</f:facet>

			<%@include file="/inc/page_footer.jsp"%>

		</x:panelLayout>
	</h:form></center>
	</body>
	</html>
</f:view>
