<%@include file="/inc/tags.jsp" %>

<f:view>
<html>
<head>
    <title><h:outputText value="Tarjeta Fiel - Banco :: Falla alta banco"/></title>
</head>

<%@include file="/inc/head.inc" %>

<body>
<center>
<h:form id="altaFalla">
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
            <h:panelGrid id="body" columns="1" align="center">
            	<h:panelGroup>
	            	<h:graphicImage value="/img/alarma.gif"/>
	            	<h:outputText value=" El banco ya existe en la base de datos." styleClass="texto"/>
            	</h:panelGroup>
            </h:panelGrid>
        </f:facet>

        <%@include file="/inc/page_footer.jsp" %>

    </x:panelLayout>
</h:form>    
</center>    
</body>
</html>
</f:view>
