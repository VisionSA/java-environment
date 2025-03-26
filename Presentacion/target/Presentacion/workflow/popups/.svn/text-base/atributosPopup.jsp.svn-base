<%@include file="/inc/tags.jsp" %>

<f:view>
<html>
<head>
	<meta HTTP-EQUIV="Content-Type" CONTENT="text/html;CHARSET=iso-8859-1">
    <title><h:outputText value="Tarjeta Fiel - Agregar Atributos"/></title>
  	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/basic.css" />
</head>

<%@include file="/inc/head.inc" %>

<body onbeforeunload="ShowWait('agregarAtributosForm');">
<center>
<h:form id="agregarAtributosForm">
	<x:panelTabbedPane bgcolor="#dcdcdc">
	<h:panelGroup>
		<h:outputText value="Insertar Atributos de Proceso" style="FONT-SIZE: large;" styleClass="textoblue"/>
		
		<%-- INCLUDE PARA LOS ERRORES --%> 	
		<h:panelGroup id="errores">
			<jsp:include page="/inc/error.jsp" />
		</h:panelGroup>            		
		
		<f:verbatim>&nbsp;</f:verbatim>
		<f:verbatim>&nbsp;</f:verbatim>					
		<f:verbatim>&nbsp;</f:verbatim>
		<f:verbatim>&nbsp;</f:verbatim>
					
		<h:panelGrid columns="4" id="panelDatos">
					<h:outputText id="outNombre" value="Nombre: " styleClass="texto"/>
	                <h:inputText id="nombre" value="#{AtributoBean.atributo.nombre}" title="Nombre interno del atributo"
                			 	 size="50" maxlength="50" styleClass="bordeceldatext" style="width: 250px"
                			 	 onfocus="encender(this);" onblur="apagar(this);"/>

					<h:outputText id="outDescripcion" value="Descripción: " styleClass="texto"/>
	                <h:inputText id="descripcion" value="#{AtributoBean.atributo.descripcion}" title="Etiqueta a mostrar en el escritorio"
                			 	 size="50" maxlength="50" styleClass="bordeceldatext" style="width: 250px"
                			 	 onfocus="encender(this);" onblur="apagar(this);"/>

		                <h:outputText id="outipoAtributo" value="Tipo de atributo: " styleClass="texto"/>
						<h:selectOneMenu id="lstTAtributo" valueChangeListener="#{AtributoBean.cambioTipo}" 
								value="#{AtributoBean.tipoASeleccionado}" styleClass="lista" 
								immediate="true" onfocus="encender(this);" binding="#{AtributoBean.tiposHtml}"
	       					 	onblur="apagar(this);" style=" width : 250px;" onchange="submit();">
	        				<f:selectItems id="itemsTipoAtri" value="#{AtributoBean.tipoAtributos}"/>
	       				</h:selectOneMenu>

					<h:outputText id="outLongitud" value="Longitud: " styleClass="texto"/>
	                <x:inputText id="longitud" value="#{AtributoBean.atributo.longitud}" disabled="#{AtributoBean.boolLongitud}"
                			 	 size="50" maxlength="50" styleClass="bordeceldainferior" style="width: 100px"
                			 	 onfocus="encender(this);" onblur="apagar(this);" onchange="submit();"
                			 	 onkeypress="return soloEnteros(this,event);"/>
					<h:panelGrid columns="1">
	                	<h:panelGroup>
							<x:selectBooleanCheckbox id="chkRequerido" value="#{AtributoBean.atributo.requerido}" />
	                		<h:outputText value="Requerido" styleClass="texto"/>
						</h:panelGroup>
	
	                	<h:panelGroup>
							<x:selectBooleanCheckbox id="chkLocal" value="#{AtributoBean.atributo.local}"
									onchange="submit();" />
	                		<h:outputText value="Local" styleClass="texto"/>
						</h:panelGroup>
	
	                	<h:panelGroup>
							<x:selectBooleanCheckbox id="chkParametro" value="#{AtributoBean.esParametro}" onchange="submit();"/>
	                		<h:outputText value="Parametro" styleClass="texto" style="COLOR: blue;"/>
						</h:panelGroup>

						<h:panelGroup id="pnlGridBuscaValor" rendered="#{AtributoBean.esParametro && !AtributoBean.atributo.local}">
							<x:selectBooleanCheckbox id="chkBuscaValor" value="#{AtributoBean.esBuscaValor}"/>
	                		<h:outputText value="Valor desde Sql" styleClass="texto" style="COLOR: blue;width: 150px" />
						</h:panelGroup>

					</h:panelGrid>
					<f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim>
					
					<h:outputText id="outComentario" value="Comentario: " styleClass="texto"/>
					<x:inputTextarea id="textComentario" style="width : 250px; height : 70px;"
						value="#{AtributoBean.atributo.comentario}" title="Comentario que se muestra como ayuda en el componente"
						onfocus="encender(this);" onblur="apagar(this);" />
			</h:panelGrid>
			<h:panelGrid columns="3" id="panelQuerys" rendered="#{!AtributoBean.atributo.local}">
				<h:panelGroup>
					<x:selectBooleanCheckbox id="chkValor" value="#{AtributoBean.atributo.valor}" onchange="submit();"/>
                	<h:outputText id="outSqlValor" value="SQL Valor: " styleClass="texto" />
                	<x:commandButton action="#{AtributoBean.verificarSQLValor}" disabled="#{!AtributoBean.atributo.valor}"
                			value="Verificar" styleClass="botones"/>
	   	            <h:outputText value="#{AtributoBean.msjValor}" styleClass="texto" style="COLOR: blue;"/>
	   	     	</h:panelGroup>
	             
	            <f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim>
	             
	            <h:panelGroup>
	            	<x:selectBooleanCheckbox id="chkGraba" value="#{AtributoBean.atributo.graba}" onchange="submit();"/>
	                <h:outputText id="outSqlGraba" value="SQL Graba: " styleClass="texto" />
	                <x:commandButton action="#{AtributoBean.verificarSQLGraba}" disabled="#{!AtributoBean.atributo.graba}"
	                		value="Verificar" styleClass="botones"/>
	                <h:outputText value="#{AtributoBean.msjGraba}" styleClass="texto" style="COLOR: blue;"/>
	  			</h:panelGroup>
	             
	                <x:inputTextarea id="SqlValor" style="width : 300px; height : 70px;" value="#{AtributoBean.atributo.sqlValor}"
	                		onfocus="encender(this);" onblur="apagar(this);" disabled="#{!AtributoBean.atributo.valor}"/>
	   			 	
	   			 	<f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim>
	   			 
	                <x:inputTextarea id="SqlGraba" style="width : 300px; height : 70px;" value="#{AtributoBean.atributo.sqlGraba}"
	                		onfocus="encender(this);" onblur="apagar(this);" disabled="#{!AtributoBean.atributo.graba}"/>
	                
                <h:panelGroup>
                	<x:selectBooleanCheckbox id="chkLista" value="#{AtributoBean.atributo.lista}" onchange="submit();"/>
                	<h:outputText id="outSqlLista" value="SQL Lista: " styleClass="texto" />
                	<x:commandButton action="#{AtributoBean.verificarSQLLista}" disabled="#{!AtributoBean.atributo.lista}"
                			value="Verificar" styleClass="botones"/>
                	<h:outputText value="#{AtributoBean.msjLista}" styleClass="texto" style="COLOR: blue;"/>
               	</h:panelGroup>	
               	
               		<f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim>
               	
               	<h:panelGroup>
               		<x:selectBooleanCheckbox id="chkValida" value="#{AtributoBean.atributo.valida}" onchange="submit();"/>
               		<h:outputText id="outSqlValida" value="SQL Valida: " styleClass="texto" />
               		<x:commandButton action="#{AtributoBean.verificarSQLValida}" disabled="#{!AtributoBean.atributo.valida}"
               				value="Verificar" styleClass="botones"/>
               		<h:outputText value="#{AtributoBean.msjValida}" styleClass="texto" style="COLOR: blue;"/>
               	</h:panelGroup>	
               	
	                <x:inputTextarea id="SqlLista" style="width : 300px; height : 70px;" value="#{AtributoBean.atributo.sqlLista}"
	                		onfocus="encender(this);" onblur="apagar(this);" disabled="#{!AtributoBean.atributo.lista}"/>
	                
	                <f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim>
	                
	                <x:inputTextarea id="SqlValida" style="width : 300px; height : 70px;" value="#{AtributoBean.atributo.sqlValida}"
	                		onfocus="encender(this);" onblur="apagar(this);" disabled="#{!AtributoBean.atributo.valida}"/>
	                
    	        </h:panelGrid>
	   			<h:panelGrid columns="3" id="panelValDefe" >
                	<h:outputText id="outValDef" value="Valor por defecto: " styleClass="texto"/>
	                <h:inputText id="ValDef" value="#{AtributoBean.atributo.valorDefecto}" 
                			 	 size="50" maxlength="#{AtributoBean.atributo.longitud}" styleClass="bordeceldatext" style="width: 300px"
                			 	 onfocus="encender(this);" onblur="apagar(this);"/>
                	<f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim>
				</h:panelGrid>              		
				<h:panelGrid columns="1">
					<f:verbatim><hr align="center" width="600"></f:verbatim>
					<h:panelGrid columns="7" width="560">
						<f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim>
						<f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim>
						<f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim>
						<f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim>
						<f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim>
	                	<x:commandButton id="buttonAceptarAtributoPopup" value="Aceptar" 
	                					 action="#{AtributoBean.grabarAtributo}" 
	                					 styleClass="botones" onclick="return confirm('¿Confirma grabar el atributo en la base?');"
	                					 actionListener="#{AtributoBean.recargarYCerrarPopup}"/>
						<x:commandButton id="buttonVolverDomicilioPopup" value="Cancelar" 
										 action="#{AtributoBean.cancelar}" 
										 styleClass="botones" onclick="window.close();"/>
					</h:panelGrid>					
		</h:panelGrid>
	</h:panelGroup>
	</x:panelTabbedPane>
</h:form>
</center>
</body>
</html>
</f:view>
