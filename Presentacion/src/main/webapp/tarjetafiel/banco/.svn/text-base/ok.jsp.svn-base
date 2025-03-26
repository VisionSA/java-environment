<%@include file="/inc/tags.jsp" %>

<f:view>
<html>
<head>
    <title><h:outputText value="Tarjeta Fiel - Banco :: Operación Cometida"/></title>
</head>

<%@include file="/inc/head.inc" %>

<body>
<center>
<h:form id="altaOk">
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
        	<h:panelGrid columns="1" align="center">
	            <h:panelGroup id="body">
	            	<h:graphicImage value="/img/nueva_entrada.gif"/>
	            	<h:outputText value=" #{BancoBean.ok}" styleClass="texto"/>
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
