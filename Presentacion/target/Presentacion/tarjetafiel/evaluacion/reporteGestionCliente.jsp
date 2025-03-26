<%@include file="/inc/tags.jsp" %>
<jsp:useBean id="ahora" class="java.util.Date" scope="request"/>

<f:view>
<html>
<head>
    <title><h:outputText value="Tarjeta Fiel - Reporte (Gestion Cliente)"/></title>
    <s:script language="javascript">
    	function recargar() {
    		document.getElementById('generalForm').submit();
    	}
    </s:script>    
</head>

<%@include file="/inc/head.inc" %>

<body onload="${GestionClienteBean.popupReport}">
<center>

<secure:check/>

<h:form id="saldoProveedoresForm">
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
            		<jsp:param name="tituloLargo" value="${GestionClienteBean.tituloLargo}"/>
            		<jsp:param name="tituloCorto" value="${GestionClienteBean.tituloCorto}"/>
            	</jsp:include>

			<h:panelGrid columns="1" align="center">
				<%-- INCLUDE PARA LOS ERRORES --%> 	
				<h:panelGroup id="errores">
					<jsp:include page="/inc/error.jsp" />
				</h:panelGroup>

			<s:layoutingTitlePane id="filtroProveedores" label=" Filtro Reporte" 
								      containerNodeClass="contentTitlePane" labelNodeClass="labelTitlePane" >
					<h:panelGrid id="filtroUno" columns="2" align="center" width="546">
					   <h:panelGroup>
							<h:outputText value="Desde:" styleClass="texto"/>
					 		<x:inputCalendar id="FechaDesde" monthYearRowClass="yearMonthHeader" weekRowClass="weekHeader" popupButtonStyleClass="standard_bold"
		                		currentDayCellClass="currentDayCell" value="#{GestionClienteBean.fechaDesde}" renderAsPopup="true"
				                styleClass="bordeceldainferior" style="width: 90px" 
		        		        popupDateFormat="dd/MM/yyyy" helpText="DD/MM/YYYY" forceId="true"/>

						<f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim>
                        <f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim> 

							<h:outputText value="Hasta:" styleClass="texto"/>
					 		<x:inputCalendar id="FechaHasta" monthYearRowClass="yearMonthHeader" weekRowClass="weekHeader" popupButtonStyleClass="standard_bold"
		                		currentDayCellClass="currentDayCell" value="#{GestionClienteBean.fechaHasta}" renderAsPopup="true"
				                styleClass="bordeceldainferior" style="width: 90px" 
		        		        popupDateFormat="dd/MM/yyyy" helpText="DD/MM/YYYY" forceId="true"/>
						</h:panelGroup>
						<f:verbatim>&nbsp;</f:verbatim>
                        <f:verbatim>&nbsp;</f:verbatim>
						
						<h:panelGroup>
							<h:commandButton actionListener="#{GestionClienteBean.generarReporte}" 
											 value="Generar Reporte" styleClass="botones" style=" width : 116px;" />
						</h:panelGroup>
					</h:panelGrid>
					
				</s:layoutingTitlePane>			        
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
