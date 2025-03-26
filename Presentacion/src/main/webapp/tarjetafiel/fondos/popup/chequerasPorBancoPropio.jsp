<%@include file="/inc/tags.jsp" %>
<jsp:useBean id="ahora" class="java.util.Date" scope="request"/>

<f:view>
<html>
<head>
	<title><h:outputText value="Chequeras"/></title>
	
</head>

<%@include file="/inc/head.inc" %>





<body >

<center>
	

	<h:form id="chequerasBcoPropioForm">


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
					    
					     
					     <c:if test="${empty BancoPropioBean.listaChequeras}">
					        <h:outputText value="No existe ningúna chequera asociada a la cuenta bancaria" styleClass="bordeceldainferior" style="border:none; background:none;color:green;"/>
					     </c:if>
					     
					     <c:if test="${!empty BancoPropioBean.listaChequeras}">
					        
					     <h:panelGrid id="panelDeTabla" columns="1" align="center">
					                            <h:dataTable id="listado" styleClass="standardTable"
						                            headerClass="dataTable_Header"
						                            footerClass="standardTable_Header"
						                            rowClasses="standardTable_Row1,standardTable_Row2"
						                            columnClasses="standardTable_ColumnCentered,standardTable_ColumnCentered,standardTable_ColumnCentered"
						                            var="obj"
						                            value="#{BancoPropioBean.listaChequeras}">
						                             
 										        <x:column>
						                            <f:facet name="header">
						                                <h:outputText value="Id Chequera" />
						                            </f:facet>
						                            <h:outputText value="#{obj.idChequera}" />
						                        </x:column>
						
						                        
						                         <x:column>
						                            <f:facet name="header">
						                                <h:outputText value="Nro de Inicio" />
						                            </f:facet>
						                            <h:outputText value="#{obj.nroInicio}" />
						                        </x:column>
						                       
						                        <x:column>
						                            <f:facet name="header">
						                                <h:outputText value="Nro de Fin" />
						                            </f:facet>
						                            <h:outputText value="#{obj.nroFin}" />
						                        </x:column>
						                        
						                        <x:column>
						                            <f:facet name="header">
						                                <h:outputText value="Proximo a Usar" />
						                            </f:facet>
						                            <h:outputText value="#{obj.proximoAUsar}" />
						                        </x:column>
						                        
						                         <x:column>
						                            <f:facet name="header">
						                                <h:outputText value="Habilitada" />
						                            </f:facet>
						                            <h:outputText value="#{obj.habilitado}" />
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
