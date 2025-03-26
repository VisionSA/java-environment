<%@include file="/inc/tags.jsp" %>
<jsp:useBean id="ahora" class="java.util.Date" scope="request"/>

<f:view>
<html>
<head>
    <title><h:outputText value="Tarjeta Fiel - Editar banco"/></title>
</head>

<%@include file="/inc/head.inc" %>

<body onbeforeunload="ShowWait('altaOperadores');">
<center>
<h:form id="altaOperadores">
    <x:panelLayout id="page" layout="#{globalOptions.pageLayout}"
            styleClass="pageLayout"
            headerClass="pageHeader"
            navigationClass="pageNavigation"
            bodyClass="pageBody"
            footerClass="pageFooter" >

        <f:facet name="header">
            <f:subview id="header">
                <jsp:include page="/inc/page_header.jsp" />
                <jsp:include page="/inc/navigation_test.jsp" />
            </f:subview>
        </f:facet>
        
        <f:facet name="body">
            <h:panelGroup id="body">
            	<jsp:include page="/inc/title_header.jsp" >
            		<jsp:param name="tituloLargo" value="BANCOS"/>
            		<jsp:param name="tituloCorto" value="Editar bancos"/>
            	</jsp:include>
            	            
	        	<h:panelGrid columns="1" align="center">
					<h:panelGrid columns="1">
	                	<h:message style="font-size: 10px;color: red" for="Descripcion"/>
	                	<h:message style="font-size: 10px;color: red" for="CodigoCompuesto"/>
	                	<h:message style="font-size: 10px;color: red" for="DescripcionSucursal"/>
					</h:panelGrid>        		        	
	              <h:panelGrid id="grid" columns="2" width="277">

	                <h:outputText id="outCodigo" value="Código: " styleClass="texto"/>
	                <h:outputText value="#{BancoBean.codigo}" style="font-style: italic" styleClass="texto"/>

	                <h:outputText id="outDescripcion" value="Descripción: " styleClass="texto"/>
	                <h:inputText id="Descripcion" value="#{BancoBean.descripcion}" required="true" 
	                			 style=" width : 250px;" styleClass="bordeceldatext"/>

	                <h:outputText id="outCodigoCompuesto" value="Código Compuesto: " styleClass="texto"/>
	                <h:inputText id="CodigoCompuesto" value="#{BancoBean.codigoCompuesto}" 
	                			 required="true" style=" width : 150px;" styleClass="bordeceldainferior"/>

	                <h:outputText id="outDescripcionSucursal" value="Sucursal: " styleClass="texto"/>
	                <h:inputText id="DescripcionSucursal" value="#{BancoBean.descripcionSucursal}" 
	                			 required="true" style=" width : 250px;" styleClass="bordeceldatext"/>
					
	            </h:panelGrid>
	            
					<f:verbatim><hr align="center" width="700"></f:verbatim>
					<h:panelGrid columns="10" width="637">
						<f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim>
						<f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim>
						<f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim>
						<f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim>
						<f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim>
						<f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim>
						<f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim>
						<h:commandButton id="buttonModificar" value="Modificar" action="#{BancoBean.editar}" styleClass="botones"/>
						<h:commandButton id="buttonCancelar" value="Cancelar" action="#{BancoBean.listar}" type="reset" styleClass="botones"/>
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
