<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f" %>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h" %>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="x"%>

<f:view>
<html>
<head>
    <title><h:outputText value="Ejemplo de tabla"/></title>
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
                <f:verbatim>    
                
                    <x:dataTable id="data"
                            styleClass="standardTable"
                            headerClass="standardTable_Header"
                            footerClass="standardTable_Header"
                            rowClasses="standardTable_Row1,standardTable_Row2"
                            columnClasses="standardTable_Column,standardTable_ColumnCentered,standardTable_Column"
                            var="cliente"
                            value="#{tabla.tabla}"
                            preserveDataModel="true" >
                   
                        <h:column>
                            <f:facet name="header">
                                <h:outputText value="Nombre" />
                            </f:facet>
                            <h:inputText value="#{cliente.nombre}" />
                        </h:column>

                        <h:column>
                            <f:facet name="header">
                                <h:outputText value="Apellido" />
                            </f:facet>
                            <h:inputText value="#{cliente.apellido}" />
                        </h:column>
                        
                        <h:column>
                            <f:facet name="header">
                                <h:outputText value="Dirección" />
                            </f:facet>
                            <h:inputText value="#{cliente.direccion}" />
                        </h:column>
                                            
                        <h:column>
                            <f:facet name="header">
                                <h:outputText value="Teléfono" />
                            </f:facet>
                            <h:inputText value="#{cliente.telefono}" />
                        </h:column>

                </x:dataTable>
                
                </f:verbatim>
            </h:panelGroup>
        </f:facet>

        <%@include file="inc/page_footer.jsp" %>

    </x:panelLayout>
</center>    
</body>
</html>
</f:view>
