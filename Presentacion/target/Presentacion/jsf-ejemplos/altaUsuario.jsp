<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f" %>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h" %>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="x"%>

<f:view>
<html>
<head>
    <title><h:outputText value="Alta de usuarios"/></title>
</head>

<%@include file="inc/head.inc" %>
<f:loadBundle basename="pruebas.example_messages" var="example_messages"/>

<body>
<center>
    <x:panelLayout id="page" layout="#{globalOptions.pageLayout}"
            styleClass="pageLayout"
            headerClass="pageHeader"
            navigationClass="pageNavigation"
            bodyClass="pageBody"
            footerClass="pageFooter" >

        <f:facet name="header">
            <f:subview id="header">
                <jsp:include page="inc/page_header.jsp" />
            </f:subview>
        </f:facet>
        
        <f:facet name="navigation">
            <f:subview id="menu" >
                <jsp:include page="inc/navigation.jsp" />
            </f:subview>
        </f:facet>

        <f:facet name="body">
            <h:panelGroup id="body">
	            <h:form id="form_usuario">
	              <h:panelGrid id="grid" columns="2">
	                <h:outputText id="outNombre" value="Nombre: "/>
	                <h:inputText id="inpNombre" value="#{UsuarioBean.nombre}" required="true"/>
	                <h:outputText id="outApellido" value="Apellido: "/>
	                <h:inputText id="inpApellido" value="#{UsuarioBean.apellido}" required="true"/>
	                <h:outputText id="outDireccion" value="Dirección: "/>
	                <h:inputText id="inpDireccion" value="#{UsuarioBean.direccion}" required="true"/>                                
	                <h:commandButton id="buttonGrabar" value="Grabar" action="#{UsuarioBean.grabar}"/>
					<h:commandButton id="buttonGrabar" value="Borrar" action="#{UsuarioBean.borrar}"/>	                
	              </h:panelGrid>
	            </h:form>
            </h:panelGroup>
        </f:facet>

        <%@include file="inc/page_footer.jsp" %>

    </x:panelLayout>
</center>    
</body>
</html>
</f:view>
