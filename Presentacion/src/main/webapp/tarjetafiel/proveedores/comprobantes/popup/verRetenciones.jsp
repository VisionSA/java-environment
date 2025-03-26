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
		<h:outputText value="Retenciones" style="FONT-SIZE: large;" styleClass="texto"/>
	
		<h:panelGrid id="listadoPanel" width="600">
			<h:panelGroup>
				<h:outputText value="Total de pagos acumulados en el mes: " styleClass="texto"/>
				<h:outputText value="#{OrdenPagoBean.totalPagosMes}" styleClass="textoblue"/>
			</h:panelGroup>
			<h:panelGroup>
				<h:outputText value="Total de retenciones acumuladas en el mes: " styleClass="texto"/>
				<h:outputText value="#{OrdenPagoBean.totalRetencionesMes}" styleClass="textoblue"/>
			</h:panelGroup>
				<c:if test="${!empty OrdenPagoBean.tablaRetenciones}">
	       		 <x:dataTable id="listado"
   	                    styleClass="standardTable"
       	                headerClass="standardTable_Header"
			            footerClass="standardTable_Header"
        	   	        rowClasses="standardTable_Row1,standardTable_Row2"
       		            columnClasses="standardTable_Column,standardTable_ColumnCentered,standardTable_ColumnRight,
       		            				standardTable_ColumnRight,standardTable_ColumnRight,standardTable_ColumnRight
       		            				standardTable_ColumnRight,standardTable_ColumnRight"
		                var="ret"
 			            value="#{OrdenPagoBean.tablaRetenciones}"
        			    preserveDataModel="false" 
		        	    rendered="true">
		        	    
		        	    <h:column>
    	                	<f:facet name="header" ><h:outputText value="Impuesto" /></f:facet>
        					<h:outputText value="#{ret.categoria.tipoImpuesto.descripcion}" />
            			</h:column>
                   
 	    				<h:column>
    	                	<f:facet name="header" ><h:outputText value="Retencion Aplicada" /></f:facet>
        					<h:outputText value="#{ret.retencion.descripcion}" />
            			</h:column>
            			<h:column>
    						<f:facet name="header"><h:outputText value="AP" /></f:facet>
        	                <h:outputText value="#{ret.retencion.acumulaPagos}" />
            			</h:column>
            			<h:column>
    						<f:facet name="header"><h:outputText value="% Tramo" /></f:facet>
        					<h:outputText value="#{ret.tramo.porcRetencion}" />
						</h:column>
            	        
            	        <h:column>
    						<f:facet name="header"><h:outputText value="Monto Retencion" /></f:facet>
							<h:outputText value="#{ret.totalRetenido}" />
						</h:column>

            	        <h:column>
    						<f:facet name="header"><h:outputText value="% Exclusión" /></f:facet>
        					<h:outputText value="#{ret.exclusion.porcentaje}" />
						</h:column>

            	        <h:column>
    						<f:facet name="header"><h:outputText value="Monto Excluido" /></f:facet>
        					<h:outputText value="#{ret.totalExcluido}" />
						</h:column>
            	          
            			<h:column>
    						<f:facet name="header"><h:outputText value="Total Retenido" /></f:facet>
							<h:outputText value="#{ret.total}" />
						</h:column>
               		</x:dataTable>
               		
				<h:panelGroup>
					<f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim>
					<f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim>
					<f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim>					        					        
					</h:panelGroup>

        		<%@include file="/inc/paginator.jsp" %>  
				</c:if>
			</h:panelGrid>
		
		<f:verbatim><hr align="center" width="550"></f:verbatim>
		<h:panelGrid columns="8" width="567">
			<f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim>
			<f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim>
			<f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim>
			<f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim>
			<f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim>
			<f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim>

			<x:commandButton id="buttonAceptar" value="Aceptar" 
			                 action="#{BuscarProveedorBean.cancelar}" 
			                 styleClass="botones" onclick="window.close();"/>
		</h:panelGrid>
	</h:panelGroup>
	</x:panelTabbedPane>
</h:form>	
</center>
</body>
</html>
</f:view>