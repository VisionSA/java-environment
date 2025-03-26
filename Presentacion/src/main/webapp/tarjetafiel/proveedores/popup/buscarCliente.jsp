<%@include file="/inc/tags.jsp" %>

<f:view>
<html>
<head>
    <title><h:outputText value="Tarjeta Fiel - Agregar relación con clientes"/></title>
  	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/basic.css" />
  	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/popup.css" />
</head>

<%@include file="/inc/head.inc" %>

<body onbeforeunload="ShowWait('buscarClienteForm');">
<center>
<h:form id="buscarClienteForm">
	<x:panelTabbedPane bgcolor="#dcdcdc">
	<h:panelGroup id="buscarClientePanel">
		<h:outputText value="Buscar Cliente" style="FONT-SIZE: large;" styleClass="texto"/>
		<h:panelGrid id="filtroUnoPanel" columns="7">
			<h:outputText value="Nombre:" styleClass="texto"/>
			<h:inputText id="nombre" value="#{BuscarClienteBean.nombre}" 
               			 styleClass="bordeceldatext" maxlength="50" size="50" style="width: 110px"
               			 onfocus="encender(this);" onblur="apagar(this);"/>
               			 
            <f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim>
            
			<h:outputText value="Apellido:" styleClass="texto"/>
			<h:inputText id="apellido" value="#{BuscarClienteBean.apellido}" 
               			 styleClass="bordeceldatext" maxlength="50" size="50" style="width: 200px"
               			 onfocus="encender(this);" onblur="apagar(this);"/>       		            
               			 
            <f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim>
               			 
       		<x:commandButton id="btnBuscarCliente" 
       						 value="Buscar" 
       						 action="#{BuscarClienteBean.filtrarClientes}" 
       						 title="Filtrar clientes"
       						 image="/img/icon/srch_24.gif"/>

			<h:outputText value="Dni:" styleClass="texto"/>
			<h:inputText id="documento" value="#{BuscarClienteBean.documento}" 
               			 styleClass="bordeceldainferior" maxlength="8" size="8" style="width: 90px"
               			 onfocus="encender(this);" onblur="apagar(this);"
               			 onkeypress="return soloEnteros(this,event);"/>       		            		

            <f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim>

			<h:outputText value="Cuil:" styleClass="texto"/>
			<h:inputText id="cuil" value="#{BuscarClienteBean.cuil}" 
               			 styleClass="bordeceldainferior" maxlength="11" size="11" style="width: 110px"
               			 onfocus="encender(this);" onblur="apagar(this);"
               			 onkeypress="return soloEnteros(this,event);"/>
            <f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim>
            <f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim>
		</h:panelGrid>		
		
		<h:panelGroup id="listadoClientePanel">
			<f:verbatim>&nbsp;</f:verbatim>
			<f:verbatim>&nbsp;</f:verbatim>

				<c:if test="${!empty BuscarClienteBean.clientes}">
                    <x:dataTable id="listado"
                            styleClass="standardTable"
                            headerClass="dataTable_Header"
                            footerClass="standardTable_Header"
                            rowClasses="standardTable_Row1,standardTable_Row2"
                            columnClasses="dataTable_columns,standardTable_ColumnCentered,standardTable_Column"
                            sortable="false"
                            sortColumn="#{BuscarClienteBean.nombreColumna}" 
			                sortAscending="#{BuscarClienteBean.ascendente}" 
                            var="obj" 
                            value="#{BuscarClienteBean.clientes}"
                            preserveDataModel="false"
                            rows="10">
                   
                        <x:column defaultSorted="true">
                            <f:facet name="header">
                                <h:outputText value="Dni" />
                            </f:facet>
                            <h:outputText value="#{obj.cliente.nroDocumento}" />
                        </x:column>

                        <x:column>
                            <f:facet name="header">
                                <h:outputText value="Nombre" />
                            </f:facet>
                            <h:outputText value="#{obj.cliente.nombre1}" />
                        </x:column>
                        
                        <x:column>
                            <f:facet name="header">
                                <h:outputText value="Apellido" />
                            </f:facet>
                            <h:outputText value="#{obj.cliente.apellido}" />
                        </x:column>
                        
                        <x:column>
                            <f:facet name="header">
                                <h:outputText value="Cuil" />
                            </f:facet>
                            <h:outputText value="#{obj.cliente.cuil}" />
                        </x:column>
                        
                        <h:column>
                            <f:facet name="header">
                            </f:facet>
                            <h:selectBooleanCheckbox value="#{obj.seleccionado}" />
                        </h:column>                         
                	</x:dataTable>

        		<%@include file="/inc/paginator.jsp" %>
				</c:if>
				
				<c:if test="${empty BuscarClienteBean.clientes}">
					<h:outputText value="No existen clientes." styleClass="texto" style="color:green"/>
				</c:if>

			
			<c:if test="${!empty BuscarClienteBean.clientes}">
			<f:verbatim><hr align="center" width="550"></f:verbatim>
			<h:panelGrid columns="8" width="567">
				<f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim>
				<f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim>
				<f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim>
				<f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim>
				<f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim>
				<f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim>
               	<x:commandButton id="buttonAceptarCliente" value="Aceptar" 
               					 action="#{BuscarClienteBean.aceptarFiltroCliente}" 
               					 styleClass="botones" actionListener="#{BuscarClienteBean.recargarYCerrarPopup}"/>
				<x:commandButton id="buttonCancelarCliente" value="Cancelar" 
				                 action="#{BuscarClienteBean.cancelar}" 
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