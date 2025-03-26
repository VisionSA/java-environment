<%@include file="/inc/tags.jsp" %>
<jsp:useBean id="ahora" class="java.util.Date" scope="request"/>

<f:view>
<html>
<head>
   	<title><h:outputText value="#{ProveedorBean.tituloLargo}"/></title>
    <s:script language="javascript">
    	function recargar() {
    		document.getElementById('altaProveedoresForm').submit();
    	};
    	function popup(pagina,popW,popH) {
			var w = 0, h = 0;
		   	w = screen.width;
		   	h = screen.height;

			var leftPos = (w-popW)/2, topPos = (h-popH)/2;

		    popupWindow=open(pagina,'','resizable=no,scrollbars=yes,width='+popW+',height='+popH+',top='+topPos+',left='+leftPos);
		    if (popupWindow.opener == null){popupWindow.opener = self;}
		};
    </s:script>
</head>

<%@include file="/inc/head.inc" %>

<body  onbeforeunload="ShowWait('altaProveedoresForm');" onload="if('${ProveedorBean.popup.mostrar}'=='true') {viewDialog.show();} else {window.scrollTo(0,${ScrollBean.hiddenScrollY});}">
<center>
<h:form id="altaProveedoresForm">

	<%-- GRABA EL ESTADO DEL SCROLL(Dibujar solo si el popup no se muestra) --%> 
	<h:panelGroup rendered="#{!ProveedorBean.popup.mostrar}">
		<%@include file="/inc/scroll.inc" %>   	      
	</h:panelGroup>
	
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
            		<jsp:param name="tituloLargo" value="${.tituloLargo}"/>
            		<jsp:param name="tituloCorto" value="${.tituloCorto}"/>
            	</jsp:include>
            	
            	<h:panelGrid columns="1" align="center" id="PanelPricipalProveedores" width="900">
					
					
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
