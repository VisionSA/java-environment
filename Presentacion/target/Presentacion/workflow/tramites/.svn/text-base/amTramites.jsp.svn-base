<%@include file="/inc/tags.jsp"%>
<jsp:useBean id="ahora" class="java.util.Date" scope="request" />

<f:view>
	<html lang="es">
	<head>
	<title><h:outputText value="Tarjeta Fiel | Administración de Trámites" /></title>
	<s:script language="javascript">
    	function recargar() {
    		document.getElementById('amTramitesForm').submit();
    	}
    </s:script>
	</head>

	<jsp:include page="/inc/includes.jsp" />

	<body class="hold-transition skin-blue sidebar-mini" onbeforeunload="ShowWait('amTramitesForm');"
		onload="if('${TramitesBean.popup.mostrar}'=='true') {viewDialog.show();} else {window.scrollTo(0,${ScrollBean.hiddenScrollY});}">
	
		<h:form id="mainMenu" style="display: none">
		  <jsp:include page="/inc/navigation_test.jsp" />
		  <x:commandLink id="cerrarSesion" action="#{LoginBean.salir}"/>
		</h:form>

		<jsp:include page="/inc/header.jsp" />

		<!-- Content Header (Page header) -->
		<section class="content-header">
		  <h1>
		    ${TramitesBean.tituloCorto}
		    <small>${TramitesBean.tituloLargo}</small>
		  </h1>
		</section>

		<section class="content">

		<div class="box box-default">
		<div class="box-header with-border">
			<h3 class="box-title">${TramitesBean.proceso.titulo}</h3>
		</div>
		<br/>

	<center><h:form id="amTramitesForm">

		<%-- GRABA EL ESTADO DEL SCROLL(Dibujar solo si el popup no se muestra) --%>
		<h:panelGroup rendered="#{!TramitesBean.popup.mostrar}">
			<%@include file="/inc/scroll.inc"%>
		</h:panelGroup>

		<x:panelLayout id="page" layout="#{globalOptions.pageLayout}"
			styleClass="pageLayout" headerClass="pageHeader"
			navigationClass="pageNavigation" bodyClass="pageBody"
			footerClass="pageFooter">


			<f:facet name="body">
				<h:panelGroup id="body">
				
				<%-- POPUP PARA LOS MENSAJES DE EXITO Y FRACASO --%>
				<s:modalDialog dialogId="viewDialog"
							   dialogVar="viewDialog"
							   styleClass="viewDialog"
			                   dialogTitle="#{TramitesBean.tituloCorto}">
				    	<h:panelGrid columns="2" width="500">
					    	<x:graphicImage value="/img/#{TramitesBean.popup.nombreIcono}" />
					        <h:outputText value=" #{TramitesBean.popup.mensaje}" styleClass="texto"/>
				        </h:panelGrid>
					        
				        <h:panelGrid columns="3" width="500">
				        	<x:commandButton action="#{TramitesBean.irANuevoTramite}" 
				        		 onclick="clickLink('nuevoTram');dojo.widget.byId('viewDialog').hide();"
				        		 value="Nuevo Tramite" styleClass="btn btn-primary btn-flat" title="Iniciar un nuevo tramite."/>

				        	<x:commandButton action="#{TramitesBean.irAEscritorio}" 
				        		 onclick="clickLink('irEscritorio');dojo.widget.byId('viewDialog').hide();"
				         		 value="Escritorio" styleClass="btn btn-primary btn-flat" title="Ir al escritorio."/>
							
						 	<x:commandButton action="#{TramitesBean.irALInicio}" 
								 onclick="clickLink('irInicio');dojo.widget.byId('viewDialog').hide();"
								 value="Inicio" styleClass="btn btn-primary btn-flat" title="Ir al inicio."/> 
						</h:panelGrid>
				</s:modalDialog>
			    <x:commandLink id="nuevoTram" action="#{TramitesBean.irANuevoTramite}" forceId="true" style="display: block;"/>
				<x:commandLink id="irEscritorio" action="#{TramitesBean.irAEscritorio}" forceId="true" style="display: block;"/>
 				<x:commandLink id="irInicio" action="#{TramitesBean.irALInicio}" forceId="true" style="display: block;"/>
				
					<h:panelGrid columns="1" align="center" id="PanelPricipalProceso">
						<%-- INCLUDE PARA LOS ERRORES --%>
						<h:panelGroup id="errores">
							<jsp:include page="/inc/error.jsp" />
						</h:panelGroup>

						<c:if test="${TramitesBean.opcionA}">
							<h:panelGrid styleClass="box box-primary" style="background:#fcfcfc" columns="1">

							<f:verbatim>&nbsp;</f:verbatim>
							
							<s:layoutingTitlePane id="panleProceso"
								containerNodeClass="contentTitlePane">
								<h:panelGrid columns="7">
									
									<f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim>
									<f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim>
									<h:panelGroup>
										<h:outputText id="outputCreacion" value="Fecha creación: " />
										<h:outputText id="outcreacion"
											value="#{TramitesBean.proceso.timestamp}"
											styleClass="text-light-blue"/>
									</h:panelGroup>
									
									<f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim>

									<h:panelGroup>
										<h:outputText id="outputVersion" value="Versión: "
											styleClass="text" />
										<h:outputText id="outVersion"
											value=" #{TramitesBean.proceso.version}"
											styleClass="text-light-blue" />
									</h:panelGroup>

									<f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim>


									<h:panelGroup>
										<h:outputText id="outputRevision" value="Revisión: "
											styleClass="text" />
										<h:outputText id="outRevision"
											value="#{TramitesBean.proceso.revision}"
											styleClass="text-light-blue" />
									</h:panelGroup>
									
									<f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim>
									<f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim>

									<h:panelGroup>
										<h:outputText id="outputComentario" value="Comentario: "
											styleClass="text" />
										<h:outputText id="outComentario"
											value="#{TramitesBean.proceso.comentario}"
											styleClass="text-light-blue" />
									</h:panelGroup>

									<f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim>
									<h:panelGroup>
										<h:outputText id="outputCURequerido" value="Cód Único:"
											styleClass="text" rendered="#{TramitesBean.usarCU}"/>
										<h:inputText id="inputCURequerido" value="#{TramitesBean.cu}" size="11" maxlength="11" 
													 styleClass="bordeceldatext" style=" width : 200px; margin-left: 5px;" onfocus="encender(this);" 
													 onblur="apagar(this);" rendered="#{TramitesBean.usarCU}"/>
										<c:if test="${!TramitesBean.usarCU}">
											<f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim>									
										</c:if>
									</h:panelGroup> 
									<f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim>
									<f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim>
									
									<f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim>
									<f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim>

									<h:panelGroup>
										<h:outputText id="outputSupervisor" value="Supervisor: "
											styleClass="text" />
										<h:outputText id="outSupervisor"
											value="#{TramitesBean.supervisor}"
											styleClass="text-light-blue" />
									</h:panelGroup>
									
									<f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim>
									<f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim>
									<f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim>
									<f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim>
									
									<f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim>
									<f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim>
									<f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim>
									<f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim>
									<f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim>
									<f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim>
									<f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim>
									
								</h:panelGrid>
							</s:layoutingTitlePane>
							</h:panelGrid>


							<f:verbatim>
								<br>
							</f:verbatim>

							<h:panelGrid columns="3" id="superPanel" style="vertical-align:top;">
								<x:dataList value="#{TramitesBean.parametroWrapperList}" var="pwlUno">
										<h:panelGrid columns="3">
											<h:outputText id="tituloUno" value="#{pwlUno.nombreParam}" style="vertical-align:top;"/>

											<f:verbatim>&nbsp;&nbsp;</f:verbatim>
											
											<h:inputText  id="inputCadenaUno" value="#{pwlUno.valorTexto}" size="40" maxlength="#{pwlUno.atributo.longitud}" styleClass="bordeceldatext"
												style=" width : 200px; vertical-align:top;" onfocus="encender(this);" onblur="apagar(this);" rendered="#{pwlUno.boolCadena}" />
											
											<h:inputTextarea id="inputTextoUno" value="#{pwlUno.valorTexto}" style="width : 300px; height : 70px;" 
												onfocus="encender(this);" onblur="apagar(this);" rendered="#{pwlUno.boolTexto}"/>
												
											<h:inputText id="inputEnteroUno" value="#{pwlUno.valorNroEntero}" size="10" maxlength="#{pwlUno.atributo.longitud}" styleClass="bordeceldainferior" 
												style=" width : 200px; vertical-align:top;" onfocus="encender(this);" onblur="apagar(this);"
												onkeypress="return soloEnteros(this,event);" rendered="#{pwlUno.boolEntero}"/>
											
											<h:inputText id="inputDecimalUno" value="#{pwlUno.valorNro}" size="3" maxlength="#{pwlUno.atributo.longitud}" styleClass="bordeceldainferior" 
												style=" width : 200px; vertical-align:top;" onfocus="encender(this);" onblur="apagar(this);" 
												onkeypress="return soloDecimales(this,event);" rendered="#{pwlUno.boolDecimal}"/>
												
											<h:inputText id="inputCuitUno" value="#{pwlUno.valorCuit}" size="10" maxlength="#{pwlUno.atributo.longitud}" styleClass="bordeceldainferior" 
												style=" width : 200px; vertical-align:top;" onfocus="encender(this);" onblur="apagar(this);"
												onkeypress="return soloEnteros(this,event);" rendered="#{pwlUno.boolCuit}"/>
											
											<h:selectOneMenu id="lsitaDesplegableUno" value="#{pwlUno.valorTexto}" immediate="true" 
												onfocus="encender(this);" onblur="apagar(this);" style=" width : 200px;vertical-align:top;" 
												rendered="#{pwlUno.boolLista}" styleClass="lista" >
												<f:selectItems value="#{pwlUno.selectItems}"/>
											</h:selectOneMenu>
											
											<h:selectBooleanCheckbox id="chechkBoxUno" value="#{pwlUno.valorBoleano}"  styleClass="lista" 
												immediate="true" onfocus="encender(this);" onblur="apagar(this);" 
												rendered="#{pwlUno.boolVerificacion}"/>
											
											<x:inputCalendar id="fechaEmicionUno" monthYearRowClass="yearMonthHeader" 
												popupButtonStyleClass="standard_bold" currentDayCellClass="currentDayCell" value="#{pwlUno.valorFecha}"
												renderAsPopup="true" styleClass="bordeceldainferior" style="width: 200px; vertical-align:top;" 
												popupTodayString="#{example_messages['popup_today_string']}" popupDateFormat="dd/MM/yyyy" 
												popupWeekString="#{example_messages['popup_week_string']}" helpText="DD/MM/YYYY" forceId="true"
												rendered="#{pwlUno.boolFecha}" />
							
										</h:panelGrid>
								</x:dataList>

								<f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim>
								
								<x:dataList value="#{TramitesBean.parametroWrapperListDos}" var="pwlDos">
										<h:panelGrid columns="3">
											<h:outputText id="tituloDos" value="#{pwlDos.nombreParam}" rendered="#{pwlDos.boolNombre}"style="vertical-align:top;"/>

											<f:verbatim>&nbsp;&nbsp;</f:verbatim>
											
											<h:inputText  id="inputCadenaDos" value="#{pwlDos.valorTexto}" size="40" maxlength="#{pwlDos.atributo.longitud}" styleClass="bordeceldatext"
												style=" width : 200px;" onfocus="encender(this);" onblur="apagar(this);" 
												rendered="#{pwlDos.boolCadena}" />
											
											<h:inputTextarea id="inputTextoDos" value="#{pwlDos.valorTexto}" style="width : 300px; height : 70px;" 
												onfocus="encender(this);" onblur="apagar(this);" rendered="#{pwlDos.boolTexto}" />
												
											<h:inputText id="inputEnteroDos" value="#{pwlDos.valorNroEntero}" size="10" maxlength="#{pwlDos.atributo.longitud}" styleClass="bordeceldainferior" 
												style=" width : 200px;vertical-align:top;" onfocus="encender(this);" onblur="apagar(this);" 
												onkeypress="return soloEnteros(this,event);" rendered="#{pwlDos.boolEntero}" />
											
											<h:inputText id="inputDecimalDos" value="#{pwlDos.valorNro}" size="10" maxlength="#{pwlDos.atributo.longitud}" styleClass="bordeceldainferior" 
												style=" width : 200px;vertical-align:top;" onfocus="encender(this);" onblur="apagar(this);" 
												onkeypress="return soloDecimales(this,event);" rendered="#{pwlDos.boolDecimal}" />
											
											<h:inputText id="inputCuitDos" value="#{pwlDos.valorCuit}" size="10" maxlength="#{pwlDos.atributo.longitud}" styleClass="bordeceldainferior" 
												style=" width : 200px; vertical-align:top;" onfocus="encender(this);" onblur="apagar(this);"
												onkeypress="return soloEnteros(this,event);" rendered="#{pwlDos.boolCuit}"/>
											
											<h:selectOneMenu id="lsitaDesplegableDos" value="#{pwlDos.valorTexto}" immediate="true" 
												onfocus="encender(this);" onblur="apagar(this);" style=" width : 200px;vertical-align:top;" 
												rendered="#{pwlDos.boolLista}" styleClass="lista" >
												<f:selectItems value="#{pwlDos.selectItems}"/>
											</h:selectOneMenu>
											
											<h:selectBooleanCheckbox id="chechkBoxDos" value="#{pwlDos.valorBoleano}" styleClass="lista" 
												immediate="true" onfocus="encender(this);" onblur="apagar(this);" 
												rendered="#{pwlDos.boolVerificacion}"  style="vertical-align:top;"/>
											
											<x:inputCalendar id="fechaEmicionDos" monthYearRowClass="yearMonthHeader"
												popupButtonStyleClass="standard_bold" currentDayCellClass="currentDayCell" value="#{pwlDos.valorFecha}"
												renderAsPopup="true" styleClass="bordeceldainferior" style="width: 200px; vertical-align:top;" 
												popupTodayString="#{example_messages['popup_today_string']}" popupDateFormat="dd/MM/yyyy" 
												popupWeekString="#{example_messages['popup_week_string']}" helpText="DD/MM/YYYY" forceId="true"
												rendered="#{pwlDos.boolFecha}"/>
											
											<h:outputText id="tituloDs" value="#{pwlDos.auxiliar}" rendered="#{!pwlDos.boolNombre}" escape="false"/>
											
										</h:panelGrid>							
								</x:dataList>
							</h:panelGrid>
						</c:if>

						<f:verbatim>
							<hr align="center" width="700">
						</f:verbatim>
						<h:panelGrid columns="10" width="727" id="panelBotonera">
							<f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim>
							<f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim>
							<f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim>
							<f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim>
							<f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim>
							<f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim>
							<f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim>
							<f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim>
							<x:commandButton id="buttonTermiar" value="Iniciar Tramite" 
								action="#{TramitesBean.grabarTramite}" styleClass="btn btn-primary btn-flat" />
							<x:commandButton id="buttonCancelar" value="Cancelar"
								action="#{TramitesBean.cancelar}" styleClass="btn btn-primary btn-flat" />
						</h:panelGrid>
					</h:panelGrid>
				</h:panelGroup>
			</f:facet>

			

		</x:panelLayout>
	</h:form></center>

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
    document.getElementById("sideMenu").innerHTML += generateMenu(obj,"mainMenu__idJsp2_menu:L]\#{TramitesBean.inicializar};${TramitesBean.proceso.titulo}") + `</li>`;
}

</script>

<%@include file="/inc/scripts.jsp" %>
	
	</body>
	</html>
</f:view>
