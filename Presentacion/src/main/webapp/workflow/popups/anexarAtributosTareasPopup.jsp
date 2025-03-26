<%@include file="/inc/tags.jsp"%>

<f:view>
	<html>
	<head>
	<meta HTTP-EQUIV="Content-Type" CONTENT="text/html;CHARSET=iso-8859-1">
	<title><h:outputText value="Tarjeta Fiel - Anexar atributos a Tareas" /></title>
	<link rel="stylesheet" type="text/css"
		href="<%=request.getContextPath()%>/css/basic.css" />
	</head>

	<%@include file="/inc/head.inc"%>

	<body onbeforeunload="ShowWait('agregarAtributoTareaForm');">
	<center><h:form id="agregarAtributoTareaForm">
		<x:panelTabbedPane bgcolor="#dcdcdc">
			<h:panelGrid columns="1" align="center">
				<h:outputText value="Anexar atributos a las Tareas" style="FONT-SIZE: large;" styleClass="textoblue" />
				
				<%-- INCLUDE PARA LOS ERRORES --%>
				<h:panelGroup id="errores">
					<jsp:include page="/inc/error.jsp" />
				</h:panelGroup>
			</h:panelGrid>
			<h:panelGrid columns="1" align="center">
				<c:if test="${!empty ProcesoBean.atributosSeleccionable}">
                    <x:dataTable id="listadoAtributos"
                            styleClass="standardTable"
                            width="300"
                            headerClass="dataTable_Header"
                            footerClass="standardTable_Header"
                            rowClasses="standardTable_Row1,standardTable_Row2"
                            columnClasses="dataTable_columns,standardTable_ColumnCentered,standardTable_ColumnCentered,standardTable_ColumnCentered
                            		,standardTable_ColumnCentered,standardTable_ColumnCentered,standardTable_ColumnCentered,standardTable_ColumnCentered
                            		,standardTable_ColumnCentered,standardTable_ColumnCentered"
                            sortable="true"
                            var="atributo" 
                            value="#{ProcesoBean.atributosSeleccionable}"
                            preserveDataModel="false" style=" width : 300px;"
                            align="center">
                        
                        <x:column>
                            <f:facet name="header">
                                <h:outputText value="Atributo" />
                            </f:facet>
                            <h:outputText value="#{atributo.nombre}" />
                        </x:column>

                        <x:column>
                            <f:facet name="header">
                            	<h:outputText value="Anexado" />
                            </f:facet>
                            <x:selectBooleanCheckbox value="#{atributo.seleccionado}" style="vertical-align: center;"
                            	title="Se anexa el atributo a la tarea" onchange="submit();"/>
                        </x:column>
                        
                         <x:column>
                            <f:facet name="header">
                            	<h:outputText value="Resetea Finalizar" />
                            </f:facet>
                            <x:selectBooleanCheckbox value="#{atributo.reseteaFinal}" style="vertical-align: center;"
	                            title="En esta tarea se va a tomar el valor del atributo en el cliente."
	                            disabled="#{!atributo.seleccionado}" onchange="submit();"/>
                        </x:column>
                        
                        <x:column>
                            <f:facet name="header">
                            	<h:outputText value="Impacta" />
                            </f:facet>
                            <x:selectBooleanCheckbox value="#{atributo.impacta}" style="vertical-align: center;"
	                            title="En esta tarea se va a grabar el valor del atributo en el cliente."
	                            disabled="#{!atributo.seleccionado || atributo.reseteaFinal}"/>
                        </x:column>
                        
                        <x:column>
                        	<f:facet name="header">
                            	<h:outputText value="Solo Lectura" />
                            </f:facet>
						<x:selectBooleanCheckbox id="chkSoloLectura" value="#{atributo.soloLectura}" 
								title="En esta tarea solo se vera el valor, no se podra editar."
								disabled="#{!atributo.seleccionado || atributo.reseteaFinal}" style="vertical-align: center;"/>
                		</x:column>
                		
                		<x:column>
                            <f:facet name="header">
                            	<h:outputText value="Visible" />
                            </f:facet>
                            <x:selectBooleanCheckbox value="#{atributo.visible}" style="vertical-align: center;"
	                            title="En esta tarea va a ser visible en el formulario dinamico."
	                            disabled="#{!atributo.seleccionado}"/>
                        </x:column>
                        
                        <x:column>
                            <f:facet name="header">
                            	<h:outputText value="Resetea valor" />
                            </f:facet>
                            <x:panelGrid columns="2">
	                            <x:selectBooleanCheckbox value="#{atributo.resetea}" style="vertical-align: center;"
		                            title="En esta tarea va a ser reseteado al valor designado."
		                            disabled="#{!atributo.seleccionado || atributo.reseteaFinal}" onchange="submit();"/>
	                            <h:inputText value="#{atributo.valor}" style="vertical-align: center; width : 60px;"
		                            disabled="#{!atributo.seleccionado || atributo.reseteaFinal}" rendered="#{atributo.resetea}"/>
	                        </x:panelGrid>
                        </x:column>
                        
                        <c:if test="${!empty ProcesoBean.nombreFormExt}">                        
	                        <x:column>
	                            <f:facet name="header">
	                            	<h:outputText value="Formulario: #{ProcesoBean.nombreFormExt}" />
	                            </f:facet>
	                            <x:panelGrid columns="2">
		                           <x:selectBooleanCheckbox value="#{atributo.usaParamForm}" style="vertical-align: center;"
			                            title="En esta tarea se usa como parametro para el Formulario externo."
			                            disabled="#{!atributo.seleccionado}" onchange="submit();"/> 
		                            <h:inputText value="#{atributo.paramForm.alias}" style="vertical-align: center;"
			                            disabled="#{!atributo.seleccionado}" rendered="#{atributo.usaParamForm}"/>
		                        </x:panelGrid>
	                        </x:column>
                        </c:if>
                        
                        <c:if test="${!empty ProcesoBean.nombreDocCont}">                        
	                        <x:column>
	                            <f:facet name="header">
	                            	<h:outputText value="Documento: #{ProcesoBean.nombreDocCont}" />
	                            </f:facet>
	                            <x:panelGrid columns="2">
		                       		<x:selectBooleanCheckbox value="#{atributo.usaParamDoc}" style="vertical-align: center;"
			                            title="En esta tarea se usa como parametro para el Documento contracual."
			                            disabled="#{!atributo.seleccionado}" onchange="submit();"/> 
		                            <h:inputText value="#{atributo.paramDoc.alias}" style="vertical-align: center;"
			                            disabled="#{!atributo.seleccionado}" rendered="#{atributo.usaParamDoc}"/>
		                        </x:panelGrid>
	                        </x:column>
                        </c:if>
                	</x:dataTable>
				</c:if>
				
				<c:if test="${empty ProcesoBean.atributosSeleccionable}">
					<h:outputText value="No existen atributos cargados en el proceso." styleClass="texto"/>
				</c:if>

				<h:panelGrid columns="1" width="417">
					<f:verbatim>
						<hr align="center" width="350">
					</f:verbatim>
					<h:panelGrid columns="7" width="260">
						<f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim>
						<f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim>
						<f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim>
						<f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim>
						<f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim>
						<x:commandButton id="buttonAceptarAtributoPopup" value="Aceptar"
							action="#{ProcesoBean.insertarAtributosATarea}" styleClass="botones"/>
						<x:commandButton id="buttonVolverDomicilioPopup" value="Cancelar"
							action="" styleClass="botones"
							onclick="window.close();" />
					</h:panelGrid>
				</h:panelGrid>
			</h:panelGrid>
		</x:panelTabbedPane>
	</h:form></center>
	</body>
	</html>
</f:view>
