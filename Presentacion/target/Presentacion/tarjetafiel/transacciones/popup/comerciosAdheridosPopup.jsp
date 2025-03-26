<%@include file="/inc/tags.jsp" %>
<jsp:useBean id="ahora" class="java.util.Date" scope="request"/>

<f:view>
<html>
<head>
	<title><h:outputText value="#{LiquidacionComerciosBean.tituloLargo}"/></title>
	
</head>

<%@include file="/inc/head.inc" %>





<body >

<center>
	

	<h:form id="comerciosAdheForm">


		<x:panelLayout id="page" layout="#{globalOptions.pageLayout}"
			styleClass="pageLayout"
			headerClass="pageHeader"
			navigationClass="pageNavigation"
			bodyClass="pageBody"
			footerClass="pageFooter" >

			<f:facet name="body">
				<h:panelGroup id="body">
					
					<h:panelGrid columns="1" align="center" id="PanelPricipal" width="700px">
					

					<%-- POPUP PARA LOS MENSAJES DE EXITO Y FRACASO --%>
					<%-- POPUP PARA LOS MENSAJES DE EXITO Y FRACASO --%>
					
					<x:commandLink id="aceptarOK" forceId="true" style="display: block;"/>
					    
					     
					     <c:if test="${empty LiquidacionComerciosBean.listaComerciosAdheridos}">
					        <h:outputText value="No existe ningún comercio adherido a esta lista de precios." styleClass="bordeceldainferior" style="border:none; background:none;color:green;"/>
					     </c:if>
					     
					     <c:if test="${!empty LiquidacionComerciosBean.listaComerciosAdheridos}">
					        
					     <h:panelGrid id="panelDeTabla" columns="1" align="center">
					                            <h:dataTable id="listado" styleClass="standardTable"
						                            headerClass="dataTable_Header"
						                            footerClass="standardTable_Header"
						                            rowClasses="standardTable_Row1,standardTable_Row2"
						                            columnClasses="standardTable_ColumnCentered,standardTable_ColumnCentered,standardTable_ColumnCentered"
						                            var="obj"
						                            value="#{LiquidacionComerciosBean.listaComerciosAdheridos}">
						                             
						                        <x:column>
						                            <f:facet name="header">
						                                <h:outputText value="Empresa" />
						                            </f:facet>
						                            <h:outputText value="#{obj.sucEmpresa.empresa.razonSocial}" />
						                        </x:column>
						
						                        <x:column>
						                            <f:facet name="header">
						                                <h:outputText value="Sucursal" />
						                            </f:facet>
						                            <h:outputText value="#{obj.sucEmpresa.descripcion}" />
						                        </x:column>
						                        
						                        <x:column>
						                            <f:facet name="header">
						                                <h:outputText value="Código" />
						                            </f:facet>
						                            <h:outputText value="#{obj.codigoPosnet}" />
						                        </x:column>
						                        
						                       
						                        
							                 </h:dataTable>
					     </h:panelGrid>
					     
					     
					     </c:if>
					     
					      <h:panelGrid id="boto" columns="10">
					             <f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim>
					             <f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim>
					             <f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim>
					             <f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim>
					             <f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim>
					             <f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim>
					             <f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim>
					             <f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim>
					             <f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim>
					             <h:commandButton onclick="window.close()" value="Volver" id="salir" styleClass="botones" />
					      </h:panelGrid>
					
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
