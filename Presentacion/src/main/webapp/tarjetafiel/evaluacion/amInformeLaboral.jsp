<%@include file="/inc/tags.jsp" %>
<jsp:useBean id="ahora" class="java.util.Date" scope="request"/>

<f:view>
<html>
<head>
   	<title><h:outputText value="#{InformeLaboralBean.tituloLargo}"/></title>
    <s:script language="javascript">
    	function recargar() {
    		document.getElementById('amInformeLaboralForm').submit();
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

<%@include file="/inc/head.inc" %>

<body  onbeforeunload="ShowWait('amInformeLaboralForm');" onload="if('${InformeLaboralBean.popup.mostrar}'=='true') {viewDialog.show();} else {window.scrollTo(0,${ScrollBean.hiddenScrollY});}">
<center>
<h:form id="amInformeLaboralForm">

	<%-- GRABA EL ESTADO DEL SCROLL(Dibujar solo si el popup no se muestra) --%> 
	<h:panelGroup rendered="#{!InformeLaboralBean.popup.mostrar}">
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
   				<jsp:include page="/inc/navigation_test.jsp" />                
            </f:subview>
        </f:facet>

        <f:facet name="body">
            <h:panelGroup id="body">
            	<f:verbatim>
						<table width="940" border="0" cellspacing="0" cellpadding="0" align="center">
						   	<tr>
						    	<td width="351" height="47" align="center" class="titulo"> ${InformeLaboralBean.tituloLargo} <br>
					    	    	<span class="subtitulo"> ${InformeLaboralBean.tituloCorto} </span></td>
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
					
            	<h:panelGrid columns="1" align="center" id="PanelPricipalInformeAmbiental" width="900">
            		
            		<s:layoutingTitlePane id="solicitud" label="Datos Solicitud"
						containerNodeClass="contentTitlePane" labelNodeClass="labelTitlePane">
						<h:panelGrid columns="4" id="panelUno" align="center" width="650">
							
							<h:outputText value="Nro. de Solicitud: " styleClass="texto" id="outSolic"/>
							<h:outputText value="#{InformeLaboralBean.nroSolicitud}" styleClass="textoblue" id="outNroSolic"/>
							
							<h:outputText value="Fecha Recepción: " styleClass="texto" id="outFecha"/>
							<f:verbatim>
								<font style="COLOR: #0000ff;">
									<fmt:formatDate value="${InformeLaboralBean.fechaRecepcion}" pattern="dd/MM/yyyy" />
								</font>
							</f:verbatim>
							
						</h:panelGrid>
					</s:layoutingTitlePane>
					
					<s:layoutingTitlePane id="verificador" label="Datos Verificador"
						containerNodeClass="contentTitlePane" labelNodeClass="labelTitlePane">
						<h:panelGrid columns="4" id="panelCuatro" align="center" width="650">
							
							<h:outputText value="Codigo Verificador: " styleClass="texto" id="outVerificador"/>
							<h:outputText value="#{InformeLaboralBean.laboral.verificador.idVerificador}" styleClass="textoblue" id="outidVerificador"/>
							
							<f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim>
							<f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim>
							
							<h:outputText value="Apellido: " styleClass="text" id="outApellidoVerificador"/>
							<h:outputText value="#{InformeLaboralBean.laboral.verificador.apellido}" styleClass="textoblue" id="outApellidoVerif"/>
							
							<h:outputText value="Nombre: " styleClass="text" id="outNombreVerificador"/>
							<h:outputText value="#{InformeLaboralBean.laboral.verificador.nombre}" styleClass="textoblue" id="outNombreVerif"/>
														
						</h:panelGrid>
					</s:layoutingTitlePane>
					
					<s:layoutingTitlePane id="individuo" label="Datos Individuo"
						containerNodeClass="contentTitlePane" labelNodeClass="labelTitlePane">
						<h:panelGrid columns="4" id="panelDos" align="center" width="650">
							
							<h:outputText value="Apellido Paterno: " styleClass="texto" id="outApellidoPaterno"/>
							<h:inputText value="#{InformeLaboralBean.laboral.solicitudIndividuo.individuo.apellido}" id="inApellidoPaterno" maxlength="50" size="50" styleClass="bordeceldatext" style="width: 150px" 
							onfocus="encender(this);" onblur="apagar(this);"/>
							
							<h:outputText value="Apellido Materno: " styleClass="texto" id="outApellidoMaterno"/>
							<h:inputText value="#{InformeLaboralBean.laboral.solicitudIndividuo.individuo.apellidoMaterno}" id="inApellidoMaterno" maxlength="50" size="50" styleClass="bordeceldatext" style="width: 150px" 
							onfocus="encender(this);" onblur="apagar(this);"/>
							
							<h:outputText value="Nombres: " styleClass="text" id="outNombres"/>
							<h:inputText value="#{InformeLaboralBean.laboral.solicitudIndividuo.individuo.nombres}" id="inNombres" maxlength="50" size="50" styleClass="bordeceldatext" style="width: 150px" 
							onfocus="encender(this);" onblur="apagar(this);"/>
							
							<f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim>
							<f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim>
							
							<h:outputText value="Tipo Documento: " styleClass="text" id="outTipoDocumento"/>
							<h:selectOneMenu id="lstTDoc" value="#{InformeLaboralBean.idDocumentoSeleccionado}"
								styleClass="lista" immediate="true" onfocus="encender(this);" onblur="apagar(this);" style="width: 200px">
								<f:selectItems value="#{InformeLaboralBean.listaDocumento}" id="selectItemIdTipoDoc"/>
							</h:selectOneMenu>
							
							<h:outputText value="Nro. Documento: " styleClass="text" id="outNroDoc"/>
							<h:inputText value="#{InformeLaboralBean.laboral.solicitudIndividuo.individuo.nroDocumento}" id="inNroDoc" maxlength="20" 
								size="20" styleClass="bordeceldainferior" style="width: 150px" onfocus="encender(this);" onblur="apagar(this);" 
								onkeypress="return soloEnteros(this,event);"/>
						</h:panelGrid>
							
					</s:layoutingTitlePane>
					
					<s:layoutingTitlePane id="empleador" label="Datos del Empleador"
						containerNodeClass="contentTitlePane" labelNodeClass="labelTitlePane">
						<h:panelGrid columns="4" id="panelNueve" align="center" width="650">
							
							<f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim>
							
							<h:outputText value="Empleador: " styleClass="texto" id="outEmpleador"/>
							<h:inputText value="#{InformeLaboralBean.laboral.sucEmpresa.empresa.razonSocial}" id="inNombreEmpresa" maxlength="50" size="50" styleClass="bordeceldatext" style="width: 150px" 
							onfocus="encender(this);" onblur="apagar(this);"/>
							
							<f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim>
							
						</h:panelGrid>
						
						<h:panelGrid columns="1" id="panelDiez" align="center" width="650">
						<s:fieldset legend="Domicilio" id="domicilioEmpresa">
							<h:panelGrid columns="1" id="panelDomicilioEmpresa" width="650" align="center">
								
								<h:dataTable value="#{InformeLaboralBean.listaDomicilioEmpresa}"
									id="tablaDomicilioEmpresa" styleClass="standardTable" headerClass="dataTable_Header"
									footerClass="standardTable_Header" rowClasses="standardTable_Row1,standardTable_Row2"
									columnClasses="standardTable_Column,standardTable_ColumnCentered,standardTable_Column"
									var="empresaDomicilio" style=" width : 600px;">
										
									<h:column>
										<f:facet name="header">
											<h:outputText value="Calle" styleClass="texto" style="font: bold;color: white;" />
										</f:facet>
										<h:outputText value="#{empresaDomicilio.calleNombre} " style=" width : 190px;" />
									</h:column>
										
									<h:column>
										<f:facet name="header">
											<h:outputText value="Número" styleClass="texto" style="font: bold;color: white;" />
										</f:facet>
										<h:outputText value="#{empresaDomicilio.calleNumero}" style=" width : 40px;" />
									</h:column>
										
									<h:column>
										<f:facet name="header">
											<h:outputText value="Barrio" styleClass="texto" style="font: bold;color: white;" />
										</f:facet>
										<h:outputText value="#{empresaDomicilio.barrio.descripcion}" style=" width : 40px;" />
									</h:column>
										
									<h:column>
										<f:facet name="header">
											<h:outputText value="Localidad" styleClass="texto" style="font: bold;color: white;" />
										</f:facet>
										<h:outputText value="#{empresaDomicilio.localidad.nombre}" style=" width : 40px;" />
									</h:column>
										
									<h:column>
										<x:commandLink action="#{InformeLaboralBean.editarDomicilio}" id="editarDomicilioLink">
											<f:param id="idDomiEdi" name="idDomiEdi" value="#{empresaDomicilio.idDomicilio}" />
											<x:graphicImage value="/img/editar.gif" title="Editar el domicilio." border="0" />
										</x:commandLink>
									</h:column>

								</h:dataTable>
								</h:panelGrid>
							</s:fieldset>
							</h:panelGrid>
							
							<h:panelGrid columns="4" id="panelDiezBis" align="center" width="650">
								
								<h:outputText value="Corrección Razón Social: " styleClass="texto" id="correcionRS"/>
								<h:selectBooleanCheckbox value="#{InformeLaboralBean.corrigioRazonSocail}" id="booleanUno"/>
								
								<h:outputText value="Corrección Dirección: " styleClass="texto" id="correcionDireccion"/>
								<h:selectBooleanCheckbox value="#{InformeLaboralBean.corrigioDomicilioEmpresa}" id="booleanDos"/>
								
								<h:outputText value="Observaciones: " styleClass="texto" id="outObservaciones"/>
								<h:inputText value="#{InformeLaboralBean.laboral.observacion}" id="inObservaciones" maxlength="50" size="50" 
									styleClass="bordeceldatext" style="width: 150px" onfocus="encender(this);" onblur="apagar(this);"/>
									
								<h:outputText value="Ocupación: " styleClass="text" id="outOcupacion"/>
								<h:selectOneMenu id="lstOcupacion" value="#{InformeLaboralBean.idOcupacionSeleccionada}"
									styleClass="lista" immediate="true" onfocus="encender(this);" onblur="apagar(this);" style="width: 200px">
									<f:selectItems value="#{InformeLaboralBean.listaOcupacion}" id="selectItemIdOcupacion"/>
								</h:selectOneMenu>	
								
								<h:outputText value="Rubro: " styleClass="text" id="outRubto"/>
								<h:selectOneMenu id="lstRubro" value="#{InformeLaboralBean.idRubroSeleccionado}"
									styleClass="lista" immediate="true" onfocus="encender(this);" onblur="apagar(this);" style="width: 200px">
									<f:selectItems value="#{InformeLaboralBean.listaRubro}" id="selectItemIdRubto"/>
								</h:selectOneMenu>
								
								<h:outputText value="Tamaño: " styleClass="text" id="outTamanio"/>
								<h:selectOneMenu id="lstTamanio" value="#{InformeLaboralBean.idTamanioEmpresaSeleccionado}"
									styleClass="lista" immediate="true" onfocus="encender(this);" onblur="apagar(this);" style="width: 200px">
									<f:selectItems value="#{InformeLaboralBean.listaTamanioEmpresa}" id="selectItemIdTamanio"/>
								</h:selectOneMenu>	
								
							</h:panelGrid>
					
							<h:panelGrid columns="1" id="panelOnce" align="center" width="650">
								
								<s:fieldset legend="Observo">
										<h:dataTable value="#{InformeLaboralBean.listaObservo}"
											id="tUno" styleClass="standardTable" headerClass="dataTable_Header"
											footerClass="standardTable_Header" rowClasses="standardTable_Row1,standardTable_Row2"
											columnClasses="standardTable_Column,standardTable_ColumnCentered,standardTable_Column"
											var="observo" style=" width : 650px;">
													
											<h:column>
												<f:facet name="header">
													<h:outputText value="Observo" styleClass="texto" style="font: bold;color: white;" />
												</f:facet>
												<h:outputText value="#{observo.descripcion}" styleClass="texto"/>
											</h:column>
															
											<h:column>
												<h:selectBooleanCheckbox value="#{observo.seleccionado}" id="estado"/>
											</h:column>
														
										</h:dataTable>
								</s:fieldset>					
							</h:panelGrid>
							
							<h:panelGrid columns="4" id="panelDoce" align="center" width="650">
								
								<h:outputText value="Solo Autónomos / Comerciales: " styleClass="texto"/>
								<h:selectOneMenu id="lstAutonomoComerciales" value="#{InformeLaboralBean.idAutonomo}"
									styleClass="lista" immediate="true" onfocus="encender(this);" onblur="apagar(this);" style="width: 200px">
									<f:selectItems value="#{InformeLaboralBean.listaAutonomo}" id="selectItemIdAutonomoComerciales"/>
								</h:selectOneMenu>
								
								<h:outputText value="Antigüedad: " styleClass="texto" id="outAnt"/>
								<h:panelGroup>
									<h:inputText value="#{InformeLaboralBean.laboral.antiguedad}" id="inAnt" maxlength="50" size="50" styleClass="bordeceldainferior" 
										style="width: 50px" onfocus="encender(this);" onblur="apagar(this);" onkeypress="return soloEnteros(this,event);"/>
									<h:outputText value="Años." styleClass="textoblue" id="outAnios"/>
								</h:panelGroup>
								
								<h:outputText value="Cargo: " styleClass="texto" id="outCargo"/>
								<h:inputText value="#{InformeLaboralBean.laboral.cargo}" id="Cargo" maxlength="50" size="50" styleClass="bordeceldatext" style="width: 150px" 
									onfocus="encender(this);" onblur="apagar(this);"/>
								
								<h:outputText value="Ingreso " styleClass="texto" id="outIngreso"/>
								<h:inputText value="#{InformeLaboralBean.laboral.sueldoNeto}" id="inIngreso" maxlength="50" size="50" styleClass="bordeceldainferior" style="width: 50px" 
									onfocus="encender(this);" onblur="apagar(this);" onkeypress="return soloDecimales(this,event);"/>
								
								<h:outputText value="Informo: " styleClass="texto" id="outInformo"/>
								<h:inputText value="#{InformeLaboralBean.laboral.informante}" id="inInformo" maxlength="50" size="50" styleClass="bordeceldatext" style="width: 150px" 
								onfocus="encender(this);" onblur="apagar(this);"/>
															
								<h:outputText value="Nro. Documento: " styleClass="text" id="outNroDocInformante"/>
								<h:inputText value="#{InformeLaboralBean.laboral.nroDocInform}" id="inNroDocInformante" maxlength="20" size="20" styleClass="bordeceldainferior" style="width: 150px" 
									onfocus="encender(this);" onblur="apagar(this);" onkeypress="return soloEnteros(this,event);"/>
								
							</h:panelGrid>
						</s:layoutingTitlePane>
					</h:panelGrid>
					
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
						<x:commandButton id="buttonGrabar" value="Guardar" action="#{InformeLaboralBean.guardarInformeLaboral}" styleClass="botones"/>
						<x:commandButton id="buttonCancelar" value="Cancelar" action="#{InformeLaboralBean.salir}" styleClass="botones" onclick="close();"/>
					</h:panelGrid>
          		 </h:panelGroup>
	        </f:facet>

        <%@include file="/inc/page_footer.jsp" %>

    </x:panelLayout>
</h:form>    
</center>    
</body>
</html>
</f:view>
