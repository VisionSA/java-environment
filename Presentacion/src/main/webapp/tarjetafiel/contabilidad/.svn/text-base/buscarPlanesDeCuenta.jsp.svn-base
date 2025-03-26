<%@include file="/inc/tags.jsp" %>

<f:view>
<html>
<head>
    <title><h:outputText value="Tarjeta Fiel - Busca Planes de Cuenta"/></title>
  	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/basic.css" />
  	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/popup.css" />    
</head>
<%@include file="/inc/head.inc"%>

<body onbeforeunload="ShowWait('buscarPlanCuentaForm');">
<center>
<h:form id="buscarPlanCuentaForm" >
	
	
	<x:panelTabbedPane bgcolor="#dcdcdc">
	<h:panelGroup id="buscarPlanCuenta">
		<h:outputText value="Buscar Planes de Cuenta" style="FONT-SIZE: large;" styleClass="texto"/>
			<h:panelGrid columns="1" align="center" id="PanelPricipal" >
					<%-- INCLUDE PARA LOS ERRORES --%>
					<h:panelGroup id="errores">
						<jsp:include page="/inc/error.jsp" />
					</h:panelGroup>
			</h:panelGrid>
		<s:fieldset legend="Método de Búsqueda" id="fieldDeMetodos">
			<h:selectOneRadio value="#{PlanCuentaBean.metodoDeBusqueda}" onchange="submit()" valueChangeListener="#{PlanCuentaBean.cambiarMetodoDeBusqueda}">
					<f:selectItem itemLabel="Búsqueda por esquema de títulos" itemValue="T"  />
					<f:selectItem itemLabel="Búsqueda por esquema de árbol" itemValue="A" />
			</h:selectOneRadio>
		</s:fieldset>
	    <h:panelGrid id="filtroUno" columns="7" rendered="#{!PlanCuentaBean.isBusquedaDeArbol}">
			<h:outputText value="Número de Cuenta:" styleClass="texto"/>
			<h:inputText id="nroCuenta" value="#{PlanCuentaBean.nroCuentaBuscada}" 
               			 styleClass="bordeceldainferior" 
               			 maxlength="45" size="30" style="width: 110px"
               			 onfocus="encender(this);" onblur="apagar(this);"
	   					 onkeypress="return soloEnteros(this,event);"/>

            <f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim>
            
			<h:outputText value="Título del Plan de Cuenta:" styleClass="texto"/>
			<h:inputText id="tituloFiltro" value="#{PlanCuentaBean.tituloPlanCuentaBuscado}" 
               			 styleClass="bordeceldatext" 
               			 maxlength="45" size="30" style="width: 200px"
               			 onfocus="encender(this);" onblur="apagar(this);"/>       		            
               			 
            <f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim>
               			 
       		<x:commandButton id="btnBuscarPlanCuenta" 
       						 value="Buscar" 
       						 action="#{PlanCuentaBean.filtrarPlanes}" 
       						 title="Busca el plan de cuenta seleccionado"
       						 image="/img/icon/srch_24.gif"/>
		</h:panelGrid>
		
		
		<h:panelGroup id="listadoPanel">
			<f:verbatim>&nbsp;</f:verbatim>
			<f:verbatim>&nbsp;</f:verbatim>

			
				    <h:panelGrid id="panelDeTitulos" rendered="#{!PlanCuentaBean.isBusquedaDeArbol}">
                    <x:dataTable id="listado"
                            styleClass="standardTable"
                            headerClass="dataTable_Header"
                            footerClass="standardTable_Header"
                            rowClasses="standardTable_Row1,standardTable_Row2"
                            columnClasses="dataTable_columns,standardTable_ColumnCentered,standardTable_Column"
                            sortable="true"
                            var="obj" 
                            value="#{PlanCuentaBean.planes}"
                            preserveDataModel="false"
                            rows="10">
                   
                        <x:column>
                            <f:facet name="header">
                                <h:outputText value="Codigo" />
                            </f:facet>
                            <h:outputText value="#{obj.planCuenta.idPlanCuenta}" />
                        </x:column>

                        <x:column>
                            <f:facet name="header">
                                <h:outputText value="Titulo" />
                            </f:facet>
                            <h:outputText value="#{obj.planCuenta.titulo}" />
                        </x:column>
                        
                        <x:column>
                            <f:facet name="header">
                                <h:outputText value="Saldo" />
                            </f:facet>
                            <h:outputText value="#{obj.planCuenta.saldo}" />
                        </x:column>
                        
                        <h:column>
                            <f:facet name="header">
                            </f:facet>
                            <h:selectBooleanCheckbox value="#{obj.seleccionado}" />
                        </h:column>                         
                	</x:dataTable>

        		<%@include file="/inc/paginator.jsp" %>
				</h:panelGrid>
				
				
			    <h:panelGrid id="panelArboles" rendered="#{PlanCuentaBean.isBusquedaDeArbol}">
						<h:panelGrid id="panelIdentificatorio" columns="2" align="center">
						     <h:outputText value="Plan Cuenta Seleccionado: " styleClass="text"/>
						     <h:outputText value="#{PlanCuentaBean.planSeleccionado}" styleClass="textoblue" />
						</h:panelGrid>
						<%@include file="/tarjetafiel/contabilidad/pruebatree2ajax4jsf.xhtml" %>
						
	            </h:panelGrid>
                
			
			
			<h:panelGrid id="botonera" columns="8" width="567">
				<f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim>
				<f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim>
				<f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim>
				<f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim>
				<f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim>
				<f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim>
               	<x:commandButton id="buttonAceptar" value="Aceptar" 
               					 action="#{PlanCuentaBean.aceptarFiltroPlan}" 
               					 styleClass="botones"  actionListener="#{PlanCuentaBean.recargarYCerrarPopup}"/>
				<x:commandButton id="buttonCancelar" value="Cancelar" 
				                 action="#{PlanCuentaBean.cancelarFiltroPlan}" 
				                 styleClass="botones" onclick="window.close();"/>
			</h:panelGrid>
			
			
		</h:panelGroup>
	</h:panelGroup>
	</x:panelTabbedPane>
	
	
</h:form>	
</center>
</body>
</html>
</f:view>