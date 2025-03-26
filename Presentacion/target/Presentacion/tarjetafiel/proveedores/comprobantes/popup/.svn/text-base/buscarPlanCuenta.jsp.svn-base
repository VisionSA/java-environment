<%@include file="/inc/tags.jsp" %>

<f:view>
<html>
<head>
    <title><h:outputText value="Tarjeta Fiel - Busca Planes de Cuenta"/></title>
  	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/basic.css" />
  	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/popup.css" />    
    <s:script language="javascript">
		function recargar() {
			document.getElementById('asientosForm').submit();
		}
	</s:script>
</head>

<body onbeforeunload="ShowWait('buscarPlanCuentaForm');">
<center>
<h:form id="buscarPlanCuentaForm">
	<x:panelTabbedPane bgcolor="#dcdcdc">
	<h:panelGroup id="buscarPlanCuenta">
		<h:outputText value="Buscar Planes de Cuenta" style="FONT-SIZE: large;" styleClass="texto"/>
		<h:panelGrid id="filtroUno" columns="7">
			<h:outputText value="Nro. Imputacion:" styleClass="texto"/>
			<h:inputText id="nroFiltro" value="#{PlanCuentaBean.nroFiltroPlanCuenta}" 
               			 styleClass="bordeceldainferior" 
               			 maxlength="45" size="30" style="width: 110px"
               			 onfocus="encender(this);" onblur="apagar(this);"
	   					 onkeypress="return soloEnteros(this,event);"/>
               			 
            <f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim>
            
			<h:outputText value="Titulo:" styleClass="texto"/>
			<h:inputText id="tituloFiltro" value="#{PlanCuentaBean.tituloFiltroPlanCuenta}" 
               			 styleClass="bordeceldatext" 
               			 maxlength="45" size="30" style="width: 200px"
               			 onfocus="encender(this);" onblur="apagar(this);"/>       		            
               			 
            <f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim>
               			 
       		<x:commandButton id="btnBuscarPlanCuenta" 
       						 value="Buscar" onclick="agregarRelacionProveedor.show();"
       						 action="#{PlanCuentaBean.filtrarPlanes}" 
       						 title="Busca el plan de cuenta seleccionado"
       						 image="/img/icon/srch_24.gif"/>
		</h:panelGrid>
		
		
		<h:panelGroup id="listadoPanel">
			<f:verbatim>&nbsp;</f:verbatim>
			<f:verbatim>&nbsp;</f:verbatim>

				<c:if test="${!empty PlanCuentaBean.planes}">
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
                            <h:outputText value="#{obj.planCuenta.numeroImputa}" />
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
				</c:if>
				
				<c:if test="${empty PlanCuentaBean.planes}">
					<h:outputText value="No existen planes de cuenta." styleClass="texto"/>
				</c:if>

			
			<c:if test="${!empty PlanCuentaBean.planes}">
			<f:verbatim><hr align="center" width="550"></f:verbatim>
			<h:panelGrid columns="8" width="567">
				<f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim>
				<f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim>
				<f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim>
				<f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim>
				<f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim>
				<f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim>
               	<x:commandButton id="buttonAceptar" value="Aceptar" 
               					 action="#{PlanCuentaBean.aceptarFiltroPlan}" 
               					 styleClass="botones" actionListener="#{PlanCuentaBean.recargarYCerrarPopup}"/>
				<x:commandButton id="buttonCancelar" value="Cancelar" 
				                 action="#{PlanCuentaBean.cancelar}" 
				                 styleClass="botones" onclick="window.close();"/>
			</h:panelGrid>
			</c:if>
			
		</h:panelGroup>
	</h:panelGroup>
	</x:panelTabbedPane>
</h:form>	
</center>
</body>
</html>
</f:view>