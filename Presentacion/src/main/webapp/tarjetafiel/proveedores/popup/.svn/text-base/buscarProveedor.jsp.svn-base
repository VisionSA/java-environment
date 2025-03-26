<%@include file="/inc/tags.jsp" %>

<f:view>
<html>
<head>
    <title><h:outputText value="Tarjeta Fiel - Agregar relación con proveedores"/></title>
  	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/basic.css" />
  	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/popup.css" />
</head>

<%@include file="/inc/head.inc" %>

<body onbeforeunload="ShowWait('buscarProveedorForm');">
<center>
<h:form id="buscarProveedorForm">
	<x:panelTabbedPane bgcolor="#dcdcdc">
	<h:panelGroup id="buscarProveedor">
		<h:outputText value="Buscar Proveedor" style="FONT-SIZE: large;" styleClass="texto"/>
		<h:panelGrid id="filtroUno" columns="5">
			<h:outputText value="Codigo:" styleClass="texto"/>
			<h:inputText id="codigoFiltro" value="#{BuscarProveedorBean.codigoFiltroProveedor}" 
               			 styleClass="bordeceldainferior" maxlength="11" size="11" style="width: 100px"
               			 onfocus="encender(this);" onblur="apagar(this);"
               			 onkeypress="return soloEnteros(this,event);"/>
            
            <f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim>
            
            <h:outputText value="Cuit:" styleClass="texto"/>
			<h:inputText id="cuitFiltro" value="#{BuscarProveedorBean.cuitFiltroProveedor}" 
               			 styleClass="bordeceldainferior" maxlength="11" size="11" style="width: 100px"
               			 onfocus="encender(this);" onblur="apagar(this);"
               			 onkeypress="return soloEnteros(this,event);"/>
            
            
            
			<h:outputText value="Razon Social:" styleClass="texto"/>
			<h:inputText id="razonSocialFiltro" value="#{BuscarProveedorBean.razonSocialFiltroProveedor}" 
               			 styleClass="bordeceldatext" maxlength="100" size="100" style="width: 200px"
               			 onfocus="encender(this);" onblur="apagar(this);"/>       		            
               			 
            <f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim>
            
			<h:outputText value="Nombre Fantasia:" styleClass="texto"/>
			<h:inputText id="nFantasiaFiltro" value="#{BuscarProveedorBean.nombreFantasiaFiltroProveedor}" 
               			 styleClass="bordeceldatext" maxlength="100" size="100" style="width: 200px"
               			 onfocus="encender(this);" onblur="apagar(this);"/>
            
            <h:outputText value="Alias:" styleClass="texto"/>
			<h:inputText id="aliasFiltro" value="#{BuscarProveedorBean.aliasFiltroProveedor}" 
               			 styleClass="bordeceldatext" maxlength="100" size="100" style="width: 200px"
               			 onfocus="encender(this);" onblur="apagar(this);"/>       		            			
            
           	<f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim>
			
			<x:commandButton id="btnBuscarProveedor" 
       						 value="Buscar"
       						 action="#{BuscarProveedorBean.filtrarProveedores}" 
       						 title="Busca el proveedor seleccionado"
       						 image="/img/icon/srch_24.gif"/>
       		
       		<f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim>
		</h:panelGrid>		
		
		<h:panelGroup id="listadoPanel">
			<f:verbatim>&nbsp;</f:verbatim>
			<f:verbatim>&nbsp;</f:verbatim>

				<c:if test="${!empty BuscarProveedorBean.proveedores}">
                    <x:dataTable id="listado"
                            styleClass="standardTable"
                            headerClass="dataTable_Header"
                            footerClass="standardTable_Header"
                            rowClasses="standardTable_Row1,standardTable_Row2"
                            columnClasses="dataTable_columns,standardTable_ColumnCentered,standardTable_Column"
                            sortable="false"
                            sortColumn="#{BuscarProveedorBean.nombreColumna}" 
			                sortAscending="#{BuscarProveedorBean.ascendente}" 
                            var="obj" 
                            value="#{BuscarProveedorBean.proveedores}"
                            preserveDataModel="false"
                            rows="10">

                        <x:column defaultSorted="true">
                            <f:facet name="header">
                                <h:outputText id="outCodigo" value="Código" />
                            </f:facet>
                            <h:outputText id="Codigo" value="#{obj.proveedor.idProveedor}" />
                        </x:column>
                   
                        <x:column>
                            <f:facet name="header">
                                <h:outputText id="outCuit" value="Cuit" />
                            </f:facet>
                            <h:outputText id="Cuit" value="#{obj.proveedor.cuit}" />
                        </x:column>

                        <x:column>
                            <f:facet name="header">
                                <h:outputText id="outRazon" value="Razon Social" />
                            </f:facet>
                            <h:outputText id="Razon" value="#{obj.proveedor.razonSocial}" />
                        </x:column>
                        
                        <x:column>
                            <f:facet name="header">
                                <h:outputText id="outGrupo" value="Grupo" />
                            </f:facet>
                            <h:outputText id="Grupo" value="#{obj.proveedor.grupo.descripcion}" />
                        </x:column>
                        <%-- 
                        <x:column>
                            <f:facet name="header">
                                <h:outputText id="outActividad" value="Actividad" />
                            </f:facet>
                            <h:outputText id="Actividad" value="#{obj.proveedor.jurisdiccionActividad.actividad.descripcion}" />
                        </x:column>
                        --%>
                        <h:column>
                            <f:facet name="header">
                            </f:facet>
                            <h:selectBooleanCheckbox value="#{obj.seleccionado}" />
                        </h:column>                         
                	</x:dataTable>

        		<%@include file="/inc/paginator.jsp" %>  
				</c:if>
				
				<c:if test="${empty BuscarProveedorBean.proveedores}">
					<h:outputText value="No existen proveedores." styleClass="texto" style="color:green"/>
				</c:if>

			
			<c:if test="${!empty BuscarProveedorBean.proveedores}">
			<f:verbatim><hr align="center" width="550"></f:verbatim>
			<h:panelGrid columns="8" width="567">
				<f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim>
				<f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim>
				<f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim>
				<f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim>
				<f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim>
				<f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim>
               	<x:commandButton id="buttonAceptar" value="Aceptar" 
               					 action="#{BuscarProveedorBean.aceptarFiltroProveedor}" 
               					 styleClass="botones" actionListener="#{BuscarProveedorBean.recargarYCerrarPopup}"/>
				<x:commandButton id="buttonCancelar" value="Cancelar" 
				                 action="#{BuscarProveedorBean.cancelar}" 
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