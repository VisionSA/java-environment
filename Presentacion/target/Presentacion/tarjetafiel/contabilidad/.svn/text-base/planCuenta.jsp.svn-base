<%@include file="/inc/tags.jsp" %>
<jsp:useBean id="ahora" class="java.util.Date" scope="request"/>

<f:view>
<html lang="es">
<head>
	<title><h:outputText value="#{PlanCuentaBean.tituloLargo}"/></title>
	<s:script language="javascript">
		function recargar() {
			document.getElementById('PlanCuentaForm').submit();
		}
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


<body class="hold-transition skin-blue sidebar-mini">
	<h:form id="mainMenu" style="display: none">
  <jsp:include page="/inc/navigation_test.jsp" />
  <x:commandLink id="cerrarSesion" action="#{LoginBean.salir}"/>
</h:form>

<jsp:include page="/inc/header.jsp" />

<!-- Content Header (Page header) -->
<section class="content-header">
  <h1>
    ${PlanCuentaBean.tituloCorto}
    <small>${PlanCuentaBean.tituloLargo}</small>
  </h1>
</section>

<section class="content">

<div class="box box-default">
<div class="box-header"><h3></h3></div>
<center>
	<secure:check/>

	<h:form id="planCuentaForm">

	<%-- GRABA EL ESTADO DEL SCROLL(Dibujar solo si el popup no se muestra) --%>
	<h:panelGroup rendered="#{!PlanCuentaBean.popup.mostrar}">
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
					
					<h:panelGrid columns="1" align="center" id="PanelPricipal" >
					<%-- INCLUDE PARA LOS ERRORES --%>
					<h:panelGroup id="errores">
						<jsp:include page="/inc/error.jsp" />
					</h:panelGroup>

					<%-- POPUP PARA LOS MENSAJES DE EXITO Y FRACASO --%>
					<s:modalDialog dialogId="viewDialog"
								dialogVar="viewDialog"
								styleClass="viewDialog"
								dialogTitle="#{PlanCuentaBean.tituloCorto}">
					</s:modalDialog>
					
				<h:panelGrid  id="arbolDatos"  columns="2">
				 	<%@include file="/tarjetafiel/contabilidad/pruebatree2ajax4jsf.xhtml" %>
					 <s:fieldset legend="Plan Cuenta" >
				      <h:panelGrid id="planCuenta" columns="1" width="500" align="center"  >
				 	   <h:panelGrid id="panelPrincipalUno" columns="2" width="350"  align="center" style="margin-bottom:20px">
				 	        <h:outputText id="elPadre"  value="Cuenta Padre: "
								styleClass="texto" rendered="#{PlanCuentaBean.altaNuevoPlanCuenta}"/>
							<h:inputText id="NroDeCuentaPadre" value="#{PlanCuentaBean.nroCuentaPadre}"
								size="30" maxlength="35" styleClass="bordeceldainferior"
								style=" width : 179px;margin:4px" onfocus="encender(this);"
								onblur="apagar(this);" disabled="true" rendered="#{PlanCuentaBean.altaNuevoPlanCuenta}"/>
				 	        <h:outputText id="outidPlan"  value="Número de Cuenta: " rendered="#{!PlanCuentaBean.altaNuevoPlanCuenta}"
								styleClass="texto" />
							<h:inputText id="NroDeCuenta" value="#{PlanCuentaBean.planCuenta.idPlanCuenta}"
								size="30" maxlength="35" styleClass="bordeceldainferior"
								style=" width : 179px;margin:4px" onfocus="encender(this);"
								onblur="apagar(this);" disabled="true" rendered="#{!PlanCuentaBean.altaNuevoPlanCuenta}"/>
					 		<h:outputText id="outDescripcion"  value="Descripcion: "
								styleClass="texto" />
							<h:inputText id="Descripcion" value="#{PlanCuentaBean.planCuenta.titulo}"
								size="30" maxlength="35" styleClass="bordeceldainferior"
								style=" width : 179px;margin:4px" onfocus="encender(this);"
								onblur="apagar(this);" disabled="#{PlanCuentaBean.desHabilitaControles}"/>
									
						    </h:panelGrid>
						    
						<h:panelGrid id="uno" columns="2" width="400" align="center" cellspacing="0" cellpadding="0">
                          
                           <s:fieldset legend="Modulo" style="margin:15px">
                              <h:panelGrid id="panelPrincipalDos" columns="2" align="center">
							            <h:selectBooleanCheckbox value="#{PlanCuentaBean.contabilidad}" id="contabilidad" disabled="#{PlanCuentaBean.desHabilitaControles}"/>
										<h:outputText value="Contabilidad" styleClass="texto" id="outputContabilidad" style="padding-left:10px"/>
							            <h:selectBooleanCheckbox value="#{PlanCuentaBean.fondo}" id="fondo" disabled="#{PlanCuentaBean.desHabilitaControles}" 
							            		onchange="submit()" valueChangeListener="#{PlanCuentaBean.cambiaEstadoTiposFondo}" immediate="true"/>
										<h:outputText value="Fondo" styleClass="texto" id="outputFondo" style="padding-left:10px" />
												
					         </h:panelGrid>       
						   </s:fieldset>
						 	
							<s:fieldset legend="Tipo Cuenta" style=" margin:15px">
   							     <h:panelGrid id="panelPrincipalTres" columns="1"  align="center" >
				      		 		<a4j:region  renderRegionOnly="true">
				      		 		<h:selectOneRadio value="#{PlanCuentaBean.tipoDeCuenta}" disabled="#{PlanCuentaBean.desHabilitaControles}" onchange="javascript:document.planCuentaForm.submit()" 
				      		 		valueChangeListener="planCuentaBean.setearAjusteInflacion" styleClass="radioB">
									   <f:selectItem itemLabel="Resultado" itemValue="R"  />
								       <f:selectItem itemLabel="Patrimonio" itemValue="P"/>
								       <a4j:support action="#{PlanCuentaBean.setearAjuste}" event="onchange"  reRender="R,P" ajaxSingle="true"/>
								     </h:selectOneRadio>
								    </a4j:region>
								  </h:panelGrid>
						 	</s:fieldset>
						</h:panelGrid>
						
						<h:panelGrid id="tipoFondo" columns="3"  align="center" width="373" cellspacing="0" cellpadding="0" style="margin:15px">
							<s:fieldset legend="Tipos de Fondo" rendered="#{PlanCuentaBean.mostrarTiposDeFondo}" >
								<a4j:region  renderRegionOnly="true" >
									<h:selectOneRadio valueChangeListener="#{PlanCuentaBean.obtenerMediosDePago}" 
													onchange="submit()" disabled="#{PlanCuentaBean.desHabilitaControles}" 
													converter="javax.faces.Integer" value="#{PlanCuentaBean.tipoDeFondo}"
													styleClass="radioB" >
										<f:selectItems value="#{PlanCuentaBean.tiposDeFondoList}"/> 
									</h:selectOneRadio>
								</a4j:region>
							</s:fieldset>
						</h:panelGrid>
						
						<h:panelGrid id="medioPago" columns="2" width="373" align="center" cellspacing="0" cellpadding="0">
							<s:fieldset legend="Medio de pago" id="mediodepago" 
								rendered="#{PlanCuentaBean.mostrarMediosPago}" style="margin-bottom:20px">
								<a4j:region  renderRegionOnly="true" >
										<h:selectOneMenu id="SeleccionTipo"
										value="#{PlanCuentaBean.medioPagoSeleccionado}"
										styleClass="lista" immediate="true" onfocus="encender(this);"
										onblur="apagar(this);" valueChangeListener="#{PlanCuentaBean.actualizarEstadosPredeterminados}"
										onchange="submit()" disabled="#{PlanCuentaBean.desHabilitaControles}">
											<f:selectItems id="selectMediosPago" value="#{PlanCuentaBean.mediosPagoItem}" />
										</h:selectOneMenu>
								</a4j:region>
							</s:fieldset>
							<s:fieldset legend="Estado Predeterminado" id="estado" 
								rendered="#{PlanCuentaBean.mostrarEstadosPredeterminados}">
								<a4j:region  renderRegionOnly="true">
										<h:selectOneMenu id="SeleccionEstado"
										value="#{PlanCuentaBean.estadosPredSeleccionado}"
										styleClass="lista" immediate="true" onfocus="encender(this);"
										onblur="apagar(this);" disabled="#{PlanCuentaBean.desHabilitaControles}">
											<f:selectItems id="selectEstados" value="#{PlanCuentaBean.estadosPredItem}"/>
										</h:selectOneMenu>
								</a4j:region>
							</s:fieldset>
						</h:panelGrid>	
						
						     <h:panelGrid id="panelAFH" columns="6" width="373"  align="center">
						     <h:selectBooleanCheckbox value="#{PlanCuentaBean.ajusteInflacion}" id="ajuste" disabled="#{PlanCuentaBean.desHabilitaControles}"/>
						     <h:outputText value="Ajuste por Inflacion" styleClass="texto" id="outputajuste"/>
						     
							 <h:selectBooleanCheckbox value="#{PlanCuentaBean.flujoEfectivo}" id="flujo" disabled="#{PlanCuentaBean.desHabilitaControles}"/>
							 <h:outputText value="Flujo Efectivo" styleClass="texto" id="outputflujo"/>
							 
							 <h:selectBooleanCheckbox value="#{PlanCuentaBean.habilitada}" id="habilitada" disabled="#{PlanCuentaBean.desHabilitaControles}"/>
							 <h:outputText value="Habilitada" styleClass="texto" id="outputhabilitada"/>
							 
						   </h:panelGrid>
						   
						   <h:panelGrid id="uso" columns="1" width="373" align="center" cellspacing="0" cellpadding="0" style="margin-top:20px">
                           <s:fieldset legend="Uso">
                              <h:panelGrid id="panelPrincipaluso" columns="2" align="center">
						 			<h:selectOneRadio value="#{PlanCuentaBean.uso}" onchange="submit()" valueChangeListener="planCuentaBean.setearAjusteInflacion" 
						 				disabled="#{!PlanCuentaBean.altaNuevoPlanCuenta}"  styleClass="radioB">
									   <f:selectItem itemLabel="Sumarizada" itemValue="S"  />
								       <f:selectItem itemLabel="Imputable" itemValue="I" />
								    </h:selectOneRadio>
							   </h:panelGrid>
     						<h:panelGrid columns="1"  align="center" >
							 <s:fieldset style="margin-top:20px">
   							     <h:panelGrid id="panelSaldoHAbitual" columns="1" align="center" width="300">
           						 <s:fieldset legend="Saldo Habitual">
				      		 		<h:selectOneRadio valueChangeListener="planCuentaBean.verSiEsDebeOHaber"  styleClass="radioB" value="#{PlanCuentaBean.saldoHabitual}" disabled="#{!PlanCuentaBean.altaNuevoPlanCuenta}" >
									   <f:selectItem itemLabel="Debe" itemValue="D" />
								     	<f:selectItem itemLabel="Haber" itemValue="H" />
								    </h:selectOneRadio>
								  </s:fieldset>     
						            <h:panelGrid id="centroCostoCont" columns="2" align="center" width="300">
						            	<h:selectBooleanCheckbox value="#{PlanCuentaBean.centroCosto}" id="centroCosto" disabled="#{PlanCuentaBean.desHabilitaControles}" />
										<h:outputText value="Incluye Centro de Costo" styleClass="texto" id="ouputCentroCosto"/>
								 	</h:panelGrid>
								 </h:panelGrid>
								  
						 	</s:fieldset>
						 	</h:panelGrid>   
						   </s:fieldset>
						 	
							
							
						</h:panelGrid>
						<h:panelGrid columns="6" width="373" style="margin-top:20px">
						<f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim>
						<f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim>
					    
					    <x:commandButton id="buttonGrabar" value="Guardar" action="#{PlanCuentaBean.grabar}" styleClass="btn btn-primary btn-flat"  disabled="#{PlanCuentaBean.desHabilitaControles}"/>
					    
					    <x:commandButton id="buttonCancelar2" value="Cancelar" action="#{PlanCuentaBean.cancelar2}" styleClass="btn btn-primary btn-flat"  disabled="#{PlanCuentaBean.desHabilitaControles}"/>
					    
					    
					   		
					     </h:panelGrid>  
					
						</h:panelGrid>
					
				</s:fieldset>		
			   </h:panelGrid> 
					<%--t:tree2 value="#{PlanCuentaBean.treeData}" var="node" clientSideToggle="true">
  <f:facet name="company">
    <h:panelGroup>
      <t:graphicImage value="images/tree/yellow-folder-open.png" styleClass="iconImage"/>
      <h:outputText value="#{node.description}" styleClass="label"/>
    </h:panelGroup>
  </f:facet>
    <f:facet name="department">
    <h:panelGroup>
      <t:graphicImage value="images/tree/yellow-folder-open.png" styleClass="iconImage"/>
      <h:outputText value="#{node.description}" styleClass="label"/>
    </h:panelGroup>
  </f:facet>                               
                                           
  <f:facet name="person">
    <h:panelGroup>
      <t:graphicImage value="images/tree/yellow- folder-open.png" styleClass="iconImage"/>
      <h:outputText value="#{node.description}" styleClass="simpleText"/>
    </h:panelGroup>
  </f:facet>

  <f:facet name="thing">
    <h:panelGroup>
      <t:graphicImage value="images/tree/yellow-folder-open.png" styleClass="iconImage"/>
      <h:outputText value="#{ node.description}" styleClass="simpleText"/>
    </h:panelGroup>
  </f:facet>
                                           
</t:tree2>  --%>
					
					
					<f:verbatim>
						<br>
					</f:verbatim>
					<f:verbatim><hr align="center" width="700"></f:verbatim>
					<h:panelGrid  columns="10" width="727" id="panelBotonera" >
						<x:commandLink id="agregarNodoDestinoMoverLink" action="#{PlanCuentaBean.moverNodo}" 
							disabled="#{PlanCuentaBean.accionDeshabilitada}">
							<x:graphicImage value="/img/icon/move_16.png" title="Mover Plan Cuenta" border="0" />
						</x:commandLink>
						<x:commandLink id="agregarNodoDestinoCopiarLink" action="#{PlanCuentaBean.copiarNodo}" 
							disabled="#{PlanCuentaBean.accionDeshabilitada}">
							<x:graphicImage value="/img/icon/page_copy_16.png" title="Copiar Plan Cuenta" border="0" />
						</x:commandLink>
						<%--<x:commandLink id="borrarNodoLink" action="#{PlanCuentaBean.borrarPlanCuenta}" >
							<x:graphicImage value="/img/borrar.gif" title="Borrar PlanCuenta." border="0" />
						</x:commandLink>--%>
						<x:commandLink id="NuevoNodoLink" action="#{PlanCuentaBean.nuevoPlanCuenta}" 
							disabled="#{PlanCuentaBean.accionDeshabilitada}">
							<x:graphicImage value="/img/icon/new_16.png" title="Nuevo Plan Cuenta" border="0" />
						</x:commandLink>
						<x:commandLink id="EditarNodoLink" action="#{PlanCuentaBean.modificarPlanCuenta}" 
							disabled="#{PlanCuentaBean.accionDeshabilitada}">
							<x:graphicImage value="/img/icon/edit_16.gif" title="Modificar Plan Cuenta." border="0" />
						</x:commandLink>
						<f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim>
					   	<f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim>
						<f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim>
					    
					    <x:commandButton id="buttonCancelar" value="Cancelar" action="#{PlanCuentaBean.cancelar}" styleClass="btn btn-primary btn-flat pull-right" />
				
					</h:panelGrid>
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
    document.getElementById("sideMenu").innerHTML += generateMenu(obj,"mainMenu__idJsp2_menu:L]null;Plan Cuenta:A]\#{PlanCuentaBean.inicializar}") + `</li>`;
}

</script>
<%@include file="/inc/scripts.jsp" %>
</body>
</html>
</f:view>
