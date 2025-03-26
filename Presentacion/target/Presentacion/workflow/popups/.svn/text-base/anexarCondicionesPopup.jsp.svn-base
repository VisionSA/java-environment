<%@include file="/inc/tags.jsp" %>
<jsp:useBean id="ahora" class="java.util.Date" scope="request"/>

<f:view>
<html>
<head>
   	<title><h:outputText value="#{FlujosBean.tituloLargo}"/></title>
    <s:script language="javascript">
    	function recargar() {
    		document.getElementById('anexarCondicionesForm').submit();
    	};
    </s:script>
</head>

<%@include file="/inc/head.inc" %>

<body  onbeforeunload="ShowWait('anexarCondicionesForm');">
<center>
<h:form id="anexarCondicionesForm">

	<%-- GRABA EL ESTADO DEL SCROLL(Dibujar solo si el popup no se muestra) --%> 
	<h:panelGroup rendered="#{!FlujosBean.popup.mostrar}">
		<%@include file="/inc/scroll.inc" %>   	      
	</h:panelGroup>
	
    <x:panelLayout id="page" layout="#{globalOptions.pageLayout}" styleClass="pageLayout" headerClass="pageHeader" navigationClass="pageNavigation"
            bodyClass="pageBody" footerClass="pageFooter" >

        <f:facet name="body">
            <h:panelGroup id="body">
            	<h:panelGrid columns="1" align="center" id="PanelPricipal">
					<h:panelGrid columns="1" align="center">
						<h:outputText value="Anexar Condiciones" style="font-size: large;" styleClass="textoblue"/>
					</h:panelGrid>
					<h:panelGrid columns="3" align="center">
						<h:outputText value="#{FlujosBean.tituloTarea}" style="COLOR: #39a316; FONT-WEIGHT: bold;"/>
						<h:outputText value=">>>" styleClass="textoblue"/>
						<h:outputText value="#{FlujosBean.tituloTareaDestino}" style="COLOR: #39a316; FONT-WEIGHT: bold;"/>
					</h:panelGrid>
					
					<%-- INCLUDE PARA LOS ERRORES --%>
					<h:panelGroup id="errores">
						<jsp:include page="/inc/error.jsp" />
					</h:panelGroup>
	
					<f:verbatim>&nbsp;</f:verbatim>
					
					<h:panelGrid columns="3" id="panelGridUno" align="center">
						
						<h:outputText value="Tipo Condición:" id="tipoCondicion" styleClass="textoblue"/>
						
						<h:selectOneMenu id="lstCondiciones" value="#{FlujosBean.idTipoCondicionesSelect}" styleClass="lista"
							immediate="true" onfocus="encender(this);" onblur="apagar(this);" style=" width : 170px;" onchange="submit();">
							<f:selectItems value="#{FlujosBean.tipoCondicionesList}" />
						</h:selectOneMenu>
						
						<x:commandButton id="buttonNuevaCondicion" value="Agregar"
							action="#{FlujosBean.agregarNuevaCondicion}" styleClass="botones" immediate="true"/>
					</h:panelGrid>
					
					<f:verbatim>
						<br>
					</f:verbatim>
					
					<x:dataList var="condicion" value="#{FlujosBean.auxCondicionList}">
						<h:panelGroup id="panelGroupUno" rendered="#{condicion.opcionA}">
							<h:panelGrid columns="8" align="center" id="panelGridDos">
								
								<f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim>
								<h:outputText id="atributoUno" value="Atributo" styleClass="textoblue"/>
								<h:outputText id="operadorUno" value="Operador" styleClass="textoblue"/>
								<h:outputText id="valorUno" value="Valor" styleClass="textoblue"/>
								<f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim>
								<h:outputText id="conectorUno" value="Conector" styleClass="textoblue"/>
								<f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim>
								<f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim>
								
								<h:selectOneMenu id="lstParentesisApertura" value="#{condicion.parentesisInicial}" styleClass="lista" immediate="true" 
									onfocus="encender(this);" onblur="apagar(this);" style=" width : 60px;" onchange="submit();">
									<f:selectItems value="#{FlujosBean.parentesisInicioList}"/>
								</h:selectOneMenu>
								
								<h:selectOneMenu id="lstAtributo" value="#{condicion.idAtributoSelect}" styleClass="lista" immediate="true" 
									onfocus="encender(this);" onblur="apagar(this);" style=" width : 170px;" onchange="submit();">
									<f:selectItems value="#{FlujosBean.atributosList}"/>
								</h:selectOneMenu>
								
								<h:selectOneMenu value="#{condicion.idCondicionSelect}" styleClass="lista" immediate="true" 
									onfocus="encender(this);" onblur="apagar(this);" style=" width : 70px;" onchange="submit();">
									<f:selectItems value="#{FlujosBean.condicionList}"/>
								</h:selectOneMenu>
								
								<x:inputText id="valor" value="#{condicion.valor}" styleClass="bordeceldatext" style="width: 70px" 
									onfocus="encender(this);" onblur="apagar(this);"/>
								
								<h:selectOneMenu id="lstParenC" value="#{condicion.parentesisFinal}" styleClass="lista" immediate="true" 
									onfocus="encender(this);" onblur="apagar(this);" style=" width : 60px;" onchange="submit();">
									<f:selectItems value="#{FlujosBean.parentesisFinalList}"/>
								</h:selectOneMenu>
								
								<h:selectOneMenu id="lstConectorDos" value="#{condicion.idTipoUnionSelect}" styleClass="lista" immediate="true" onfocus="encender(this);" 
									onblur="apagar(this);"	style=" width : 70px;" onchange="submit();">
									<f:selectItems value="#{FlujosBean.tipoUnionList}"/>
								</h:selectOneMenu>
								
								<f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim>
								
								<h:column>
									<x:commandLink action="#{FlujosBean.eliminarCondicion}" id="eliminarCondicionDos">
										<f:param id="idCondicion" name="idCondicion" value="#{condicion.id}"/>
										<x:graphicImage value="/img/borrar.gif" title="Eliminar el condición." border="0"/>
									</x:commandLink>
								</h:column>
							</h:panelGrid>
						</h:panelGroup>
						
						<h:panelGroup id="panelGroupDos" rendered="#{condicion.opcionB}">
							<h:panelGrid columns="8" id="panelGridTres">
								<f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim>
								<h:outputText id="atributoDos" value="Atributo" styleClass="textoblue"/>
								<h:outputText id="operadorDos" value="Operador" styleClass="textoblue"/>
								<h:outputText id="atributoTres" value="Atributo" styleClass="textoblue"/>
								<f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim>
								<h:outputText id="conectorUno" value="Conector" styleClass="textoblue"/>
								<f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim>
								<f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim>
								
								<h:selectOneMenu id="lstParentesisAperturaDos" value="#{condicion.parentesisInicial}" styleClass="lista" immediate="true" 
									onfocus="encender(this);" onblur="apagar(this);" style=" width : 60px;" onchange="submit();">
									<f:selectItems value="#{FlujosBean.parentesisInicioList}"/>
								</h:selectOneMenu>
								
								<h:selectOneMenu id="lstAtributoDos" value="#{condicion.idAtributoSelect}" styleClass="lista" immediate="true" 
									onfocus="encender(this);" onblur="apagar(this);" style=" width : 170px;" onchange="submit();">
									<f:selectItems value="#{FlujosBean.atributosList}"/>
								</h:selectOneMenu>
								
								<h:selectOneMenu value="#{condicion.idCondicionSelect}" styleClass="lista" immediate="true" 
									onfocus="encender(this);" onblur="apagar(this);" style=" width : 70px;" onchange="submit();">
									<f:selectItems value="#{FlujosBean.condicionList}"/>
								</h:selectOneMenu>
								
								<h:selectOneMenu id="lstAtributoTres" value="#{condicion.idAtributoDosSelect}" styleClass="lista" immediate="true" 
									onfocus="encender(this);" onblur="apagar(this);" style=" width : 170px;" onchange="submit();">
									<f:selectItems value="#{FlujosBean.atributosList}"/>
								</h:selectOneMenu>
								
								<h:selectOneMenu id="lstParentesisCierreDos" value="#{condicion.parentesisFinal}" styleClass="lista" immediate="true" 
									onfocus="encender(this);" onblur="apagar(this);" style=" width : 60px;" onchange="submit();">
									<f:selectItems value="#{FlujosBean.parentesisFinalList}"/>
								</h:selectOneMenu>
								
								<h:selectOneMenu id="lstConectorDos" value="#{condicion.idTipoUnionSelect}" styleClass="lista" immediate="true" onfocus="encender(this);" 
									onblur="apagar(this);"	style=" width : 70px;" onchange="submit();">
									<f:selectItems value="#{FlujosBean.tipoUnionList}"/>
								</h:selectOneMenu>
								
								<f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim>
							
								<h:column>
									<x:commandLink action="#{FlujosBean.eliminarCondicion}" id="eliminarCondicionDos">
										<f:param id="idCondicion" name="idCondicion" value="#{condicion.id}"/>
										<x:graphicImage value="/img/borrar.gif" title="Eliminar el condición." border="0"/>
									</x:commandLink>
								</h:column>
							</h:panelGrid>
						</h:panelGroup>
					</x:dataList>
					
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
							<x:commandButton id="buttonGrabar" value="Grabar" action="#{FlujosBean.grabarCondicion}" styleClass="botones"/>
							<x:commandButton id="buttonCancelar" value="Cancelar" action="" styleClass="botones" onclick="window.close();" />
						</h:panelGrid>
					</h:panelGrid>
				</h:panelGrid>
           </h:panelGroup>
        </f:facet>
    </x:panelLayout>
</h:form>    
</center>    
</body>
</html>
</f:view>