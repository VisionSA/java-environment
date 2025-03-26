<%@include file="/inc/tags.jsp"%>

<f:view>
	<html>
	<head>
	<meta HTTP-EQUIV="Content-Type" CONTENT="text/html;CHARSET=iso-8859-1">
	<title><h:outputText value="Tarjeta Fiel - AmPlanCuenta" /></title>
	<link rel="stylesheet" type="text/css"
		href="<%=request.getContextPath()%>/css/basic.css" />
	</head>

	<%@include file="/inc/head.inc"%>

	<body onbeforeunload="ShowWait('amPlanCuentaForm');">
	<center><h:form id="amPlanCuentaForm">
		<x:panelTabbedPane bgcolor="#dcdcdc">
			<h:panelGroup>
	    	 <h:panelGrid columns="1">
					<h:outputText
						value="Alta Plan Cuenta: #{PlanCuentaBean.nodeClicked}"
						style="FONT-SIZE: large;" styleClass="texto" />
			 </h:panelGrid>
				<%-- INCLUDE PARA LOS ERRORES --%>
				<h:panelGroup id="errores">
					<jsp:include page="/inc/error.jsp" />
				</h:panelGroup>

				<f:verbatim>&nbsp;</f:verbatim>
				<f:verbatim>&nbsp;</f:verbatim>
				<f:verbatim>&nbsp;</f:verbatim>
				<f:verbatim>&nbsp;</f:verbatim>
				<s:fieldset legend="Plan Cuenta">
				 <h:panelGrid id="planCuenta" columns="1" width="650" align="center">
						<h:panelGrid id="panelPrincipalUno" columns="2" width="350"  align="center">
							<h:outputText id="outDescripcion" value="Descripcion: "
								styleClass="texto" />
							<h:inputText id="Descripcion" value="#{PlanCuenta.titulo}"
								size="10" maxlength="10" styleClass="bordeceldainferior"
								style=" width : 139px;" onfocus="encender(this);"
								onblur="apagar(this);" />
						 </h:panelGrid>
						<h:panelGrid id="uno" columns="2" width="250" align="center">
                           <s:fieldset legend="Modulo">
                              <h:panelGrid id="panelPrincipalDos" columns="2" width="150"  align="center">
						 				<h:outputText value="Contabilidad:" styleClass="texto" id="outputContabilidad"/>
							            <h:selectBooleanCheckbox value="#{PlanCuentaBean.contabilidad}" id="contabilidad"/>
							            <h:outputText value="Fondo" styleClass="texto" id="outputFondo"/>
							            <h:selectBooleanCheckbox value="#{PlanCuentaBean.fondo" id="fondo"/>
					         </h:panelGrid>       
						   </s:fieldset>
						 	
							<s:fieldset legend="Tipo Cuenta">
   							     <h:panelGrid id="panelPrincipalTres" columns="1" width="150"  align="center">
				      		 		<h:selectOneRadio value="#{PlanCuentaBean.selectedItemsModulo}">
									   <f:selectItem itemLabel="Resultado" itemValue="Resultado" />
								     	<f:selectItem itemLabel="Patrimonio" itemValue="Patrimonio" />
								    </h:selectOneRadio>
								  </h:panelGrid>
						 	</s:fieldset>
							
						</h:panelGrid>
						     <h:panelGrid id="panelAFH" columns="2" width="180"  align="center">
						     <h:outputText value="Ajuste por Retencion RT6:" styleClass="texto" id="outputajuste"/>
						     <h:selectBooleanCheckbox value="#{PlanCuentaBean.ajusteInflacion}" id="ajuste"/>
							 <h:outputText value="Flujo Efectivo:" styleClass="texto" id="outputflujo"/>
							 <h:selectBooleanCheckbox value="#{PlanCuentaBean.flujoEfectivo}" id="flujo"/>
							 <h:outputText value="Habilitada: " styleClass="texto" id="outputhabilitada"/>
							 <h:selectBooleanCheckbox value="#{PlanCuentaBean.habilitada}" id="habilitada"/>
						   </h:panelGrid>
						   
						   <h:panelGrid id="uso" columns="2" width="250" align="center">
                           <s:fieldset legend="Uso">
                              <h:panelGrid id="panelPrincipaluso" columns="2" width="150"  align="center">
						 			<h:selectOneRadio value="#{PlanCuentaBean.selectedItemsModulo}">
									   <f:selectItem itemLabel="Sumarizada" itemValue="Sumarizada" />
								     	<f:selectItem itemLabel="Imputable" itemValue="Imputable" />
								    </h:selectOneRadio>
								     
							 <s:fieldset>
   							     <h:panelGrid id="panelSaldoHAbitual" columns="1" width="150"  align="center">
           						 <s:fieldset legend="Saldo Habitual">
				      		 		<h:selectOneRadio value="#{PlanCuentaBean.selectedItemsModulo}">
									   <f:selectItem itemLabel="Debe" itemValue="Debe" />
								     	<f:selectItem itemLabel="Haber" itemValue="Haber" />
								    </h:selectOneRadio>
								  </s:fieldset>  
								      <h:panelGrid id="panelcentroCosto" columns="2" width="150"  align="center">
           						         <h:outputText value="Centro de Costo: " styleClass="texto" id="ouputCentroCosto"/>
							            <h:selectBooleanCheckbox value="#{PlanCuentaBean.centroCosto}" id="centroCosto"/>
							          </h:panelGrid>
								        
						   
								 </h:panelGrid>
								  
						 	</s:fieldset>
						 	</h:panelGrid>   
						   </s:fieldset>
						 	
							
							
						</h:panelGrid>
						
			             			
						</h:panelGrid>
				</s:fieldset>		
			                     	
						
				<f:verbatim>
					<hr align="center" width="600">
				</f:verbatim>
				<h:panelGrid columns="7" width="560">
					<f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim>
					<f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim>
					<f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim>
					<f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim>
					<f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim>
					<x:commandButton id="buttonAceptarDomicilioPopup" value="Aceptar"
						action="#{PlanCuentaBean.agregarNodoDestinoPopup}"
						styleClass="botones"
						actionListener="#{PlanCuentaBean.recargarYCerrarPopup}" />
					<x:commandButton id="buttonVolverDomicilioPopup" value="Cancelar"
						action="#{PlanCuentaBean.cancelarPopup}" styleClass="botones"
						onclick="window.close();" />
				</h:panelGrid>
			</h:panelGroup>
		</x:panelTabbedPane>
	</h:form></center>
	</body>
	</html>
</f:view>
