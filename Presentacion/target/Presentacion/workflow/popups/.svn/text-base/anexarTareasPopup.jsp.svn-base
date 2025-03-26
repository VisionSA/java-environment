<%@include file="/inc/tags.jsp"%>

<f:view>
	<html>
	<head>
	<meta HTTP-EQUIV="Content-Type" CONTENT="text/html;CHARSET=iso-8859-1">
	<title><h:outputText value="Tarjeta Fiel - Anexar Tareas" /></title>
	<link rel="stylesheet" type="text/css"
		href="<%=request.getContextPath()%>/css/basic.css" />
	</head>

	<%@include file="/inc/head.inc"%>

	<body onbeforeunload="ShowWait('agregarTareaForm');">
	<center><h:form id="agregarTareaForm">
		<x:panelTabbedPane bgcolor="#dcdcdc">
			<h:panelGroup>
				<h:panelGrid columns="1" align="center">
						<h:outputText value="Anexar Tareas" style="font-size: large;" styleClass="textoblue"/>
				</h:panelGrid>
				
				<%-- INCLUDE PARA LOS ERRORES --%>
				<h:panelGroup id="errores">
					<jsp:include page="/inc/error.jsp" />
				</h:panelGroup>

				<f:verbatim>&nbsp;</f:verbatim>
				<f:verbatim>&nbsp;</f:verbatim>
				<f:verbatim>&nbsp;</f:verbatim>
				<f:verbatim>&nbsp;</f:verbatim>

				<h:panelGrid columns="2" id="panelDatos" align="center">
					<h:outputText id="idTarea" value="Tipo de Tarea: " styleClass="texto" />
					<h:selectOneMenu id="lstTarea" valueChangeListener="#{ProcesoBean.cambioTarea}" 
						value="#{ProcesoBean.tareaSeleccionada}" styleClass="lista" onchange="submit();"
						immediate="true" onfocus="encender(this);" onblur="apagar(this);"
						style=" width : 300px;"	binding="#{ProcesoBean.tareasHtml}">
						<f:selectItems value="#{ProcesoBean.tareas}"/>
					</h:selectOneMenu>
				</h:panelGrid>
				<h:panelGrid columns="8" id="panelPropiedades" align="center">
	                <h:outputText id="outTareaInicial" value="Tarea inicial: " styleClass="texto"/>
   		            <x:selectBooleanCheckbox id="chkInicio" value="#{ProcesoBean.tareaInicial}" forceId="true" onclick="cambiarCheckTarea(this);"
   		            			onchange="return alert('Si marca esta tarea como inicial, va a quedar como predeterminada');"/>

					<f:verbatim>&nbsp;&nbsp;</f:verbatim>
					<h:outputText id="outTareaFinal" value="Tarea final: " styleClass="texto"/>
   		            <x:selectBooleanCheckbox id="chkFin" value="#{ProcesoBean.tareaFinal}"
								forceId="true" onclick="cambiarCheckTarea(this);"/>

					<f:verbatim>&nbsp;&nbsp;</f:verbatim>
					<h:outputText id="outConcurrencia" value="Controla concurrencia: " styleClass="texto"/>
   		            <x:selectBooleanCheckbox id="chkConcu" value="#{ProcesoBean.tareaProceso.ctrlConcurrencia}"
   		            		disabled="#{ProcesoBean.tareaInicial}"/>
   		            
				</h:panelGrid>
				
				<c:if test="${!empty ProcesoBean.nombreJob}">
					<s:fieldset id="fielJob" legend="Llamada del Job Externo" >
						<h:outputText id="outJobExterno" value="#{ProcesoBean.nombreJob}" styleClass="texto"/>
					</s:fieldset>
				</c:if>
				
				<c:if test="${empty ProcesoBean.nombreJob}">
				<h:panelGrid columns="2" id="panelPickList" align="center">
					<s:fieldset id="fielFormu" legend="Formulario Externo" >
					<c:if test="${!empty ProcesoBean.nombreFormExt}">
						<h:outputText id="idOutNomForm" value="#{ProcesoBean.nombreFormExt}" styleClass="textoblue" />
			        </c:if>
		        	</s:fieldset>
		        	<s:fieldset id="fielDocu" legend="Documento Contractual" >
		        	<c:if test="${!empty ProcesoBean.nombreDocCont}">
		        		<h:outputText id="idOutNomDoc" value="#{ProcesoBean.nombreDocCont}" styleClass="textoblue" />
			        </c:if>
			        </s:fieldset>
				</h:panelGrid>
				</c:if>
				
				<c:if test="${empty ProcesoBean.nombreJob}">
					<h:panelGrid id="listaRoles" columns="1" align="center">
						<s:fieldset id="fielRoles" legend="Roles" >
					        	<s:selectManyPicklist id="selRol" size="10" valueChangeListener="#{ProcesoBean.selecRolTarea}"
					        		value="#{ProcesoBean.rolesTarea}">
									<x:selectItems id="rolASel" value="#{ProcesoBean.rolSel}" var="rol"
		            		         itemValue="#{rol.idRol}" itemLabel="#{rol.descripcion}" /> 
					        	</s:selectManyPicklist>
						</s:fieldset>
					</h:panelGrid>
		        </c:if>
				
				<h:panelGrid columns="1" width="417" id="panelBotonera">
					<f:verbatim>
						<hr align="center" width="600">
					</f:verbatim>
					<h:panelGrid columns="7" width="560" id="panelBotones">
						<f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim>
						<f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim>
						<f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim>
						<f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim>
						<f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim>
						<x:commandButton id="buttonAceptarAtributoPopup" value="Aceptar"
							action="#{ProcesoBean.insertarTarea}" styleClass="botones" 
							onclick="return confirm('¿Se va a guardar la tarea en el proceso?');"/>
						<x:commandButton id="buttonVolverDomicilioPopup" value="Cancelar"
							action="#{ProcesoBean.cancelarTarea}" styleClass="botones"
							onclick="window.close();" />
					</h:panelGrid>
				</h:panelGrid>
			</h:panelGroup>
		</x:panelTabbedPane>
	</h:form></center>
	</body>
	</html>
</f:view>
