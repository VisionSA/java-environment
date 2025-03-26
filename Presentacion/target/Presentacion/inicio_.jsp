<%@include file="/inc/tags.jsp" %>
<jsp:useBean id="ahora" class="java.util.Date" scope="request"/>

<f:view>
<html>
    
	<head>
		<title><h:outputText value="Tarjeta Fiel - Fase I"/></title>	
		<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
	</head>

<%@include file="/inc/head.inc" %>

<body style="body" onload="clickLink('linkOculto');">
<center>
    <x:panelLayout id="page" layout="#{globalOptions.pageLayout}"
            styleClass="pageLayout"
            headerClass="pageHeader"
            navigationClass="pageNavigation"
            bodyClass="pageBody"
            footerClass="pageFooter" border="0">

        <f:facet name="header">
            <f:subview id="header">
                <jsp:include page="/inc/page_header.jsp" />
            </f:subview>
        </f:facet>
        
        <f:facet name="navigation">
            <f:subview id="menu" >
                <jsp:include page="/inc/navigation.jsp" />
            </f:subview>
        </f:facet>

        <f:facet name="body">
            <h:panelGroup id="body">
            	<f:verbatim>
					<table width="652" border="0" cellspacing="0" cellpadding="0">
				    	<tr>
            				<td width="22"><img src="<%=request.getContextPath()%>/img/fiel/fondos_03.jpg" width="22" height="55"></td>
            				<td width="204" class="titulo">INICIO<br>
                    		<span class="subtitulo">Inicio del sistema</span></td>
            				<td width="48"><img src="<%=request.getContextPath()%>/img/fiel/fondos_05.jpg" width="48" height="55"></td>
            				<td width="378" align="right" valign="top" class="fecha">
            					<fmt:formatDate value="${ahora}" dateStyle="full" timeZone="GMT-3" type="both"/>
            				</td>
          			   	</tr>
                      	<tr>
            				<td height="10" colspan="4"></td>
          				</tr>
        			</table>
            	</f:verbatim>
   	        	<h:form id="inicio">

        		<f:verbatim>
	       			<p>&nbsp;</p>
   				</f:verbatim>

				<s:modalDialog dialogId="viewDialog"
							   dialogVar="viewDialog"
							   styleClass="viewDialog"
			                   dialogTitle="Gestionar Imputaciones">
			        <h:outputText value=" Holaaaaaaaaaaaaaaaa" styleClass="texto" />
			        <h:commandButton action="#" value="Ejemplo"/>
				</s:modalDialog>
				
				<x:commandLink action="#" value="Show" id="linkOculto" onclick="alert('click!!!');viewDialog.show();" forceId="true" />
					
	            </h:form>
            </h:panelGroup>
        </f:facet>

        <%@include file="/inc/page_footer.jsp" %>

    </x:panelLayout>
</center>
</body>
</html>
</f:view>
