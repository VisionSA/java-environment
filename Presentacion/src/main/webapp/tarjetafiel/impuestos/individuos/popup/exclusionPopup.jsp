<%@include file="/inc/tags.jsp" %>

<f:view>
<html>
<head>
	<meta HTTP-EQUIV="Content-Type" CONTENT="text/html;CHARSET=iso-8859-1">
    <title><h:outputText value="Tarjeta Fiel - Agregar exclusión"/></title>
  	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/basic.css" />
</head>

<%@include file="/inc/head.inc" %>

<body onbeforeunload="ShowWait('agregarExclusion');">
<center>
<h:form id="agregarExclusion">
	<x:panelTabbedPane bgcolor="#dcdcdc">
	<h:panelGroup>
		<h:outputText value="Agregar Exclusión" style="FONT-SIZE: large;" styleClass="texto"/>
		
		<%-- INCLUDE PARA LOS ERRORES --%> 	
		<h:panelGroup id="errores">
			<jsp:include page="/inc/error.jsp" />
		</h:panelGroup>            		
		
		<f:verbatim>&nbsp;</f:verbatim>
		<f:verbatim>&nbsp;</f:verbatim>					
		<f:verbatim>&nbsp;</f:verbatim>
		<f:verbatim>&nbsp;</f:verbatim>
					
		<h:panelGroup>
              		<h:panelGrid columns="3">
              			<h:outputText id="outTipoImp" value="Aplicado al impuesto: " styleClass="texto"/>
              			<h:outputText id="outDescTipoImp" value="#{ExclusionBean.exclusion.tipoImpuesto.descripcion}" styleClass="texto"/>
              			<f:verbatim>&nbsp;</f:verbatim>
						<h:outputText id="outDescripcion" value="Descripción: " styleClass="texto"/>
		                <h:inputText id="descripcion" value="#{ExclusionBean.exclusion.descripcion}" 
	                			 	 size="100" maxlength="100" styleClass="bordeceldatext" style="width: 300px"
	                			 	 onfocus="encender(this);" onblur="apagar(this);"/>
	                	<f:verbatim>&nbsp;</f:verbatim>
	                			     
						<h:outputText value="Desde:" styleClass="texto"/>
				 		<x:inputCalendar id="FechaDesde" monthYearRowClass="yearMonthHeader" weekRowClass="weekHeader" 
				 			popupButtonStyleClass="standard_bold" currentDayCellClass="currentDayCell" 
				 			value="#{ExclusionBean.fechaDesde}" renderAsPopup="true" styleClass="bordeceldainferior" 
				 			style="width: 90px" popupDateFormat="dd/MM/yyyy" helpText="DD/MM/YYYY" forceId="true"/>
	                	<f:verbatim>&nbsp;</f:verbatim>
	                			     
						<h:outputText value="Hasta:" styleClass="texto"/>
				 		<x:inputCalendar id="FechaHasta" monthYearRowClass="yearMonthHeader" weekRowClass="weekHeader" 
				 			popupButtonStyleClass="standard_bold" currentDayCellClass="currentDayCell" 
				 			value="#{ExclusionBean.fechaHasta}" renderAsPopup="true" styleClass="bordeceldainferior" 
				 			style="width: 90px" popupDateFormat="dd/MM/yyyy" helpText="DD/MM/YYYY" forceId="true"/>
	                	<f:verbatim>&nbsp;</f:verbatim>

						<h:outputText id="outPorcentaje" value="Porcentaje: " styleClass="texto"/>
		                <h:inputText id="manzana" value="#{ExclusionBean.exclusion.porcentaje}" 
	                			 	 size="5" maxlength="5" styleClass="bordeceldainferior" style=" width : 50px;"
	                			 	 onfocus="encender(this);" onblur="apagar(this);" onkeypress="return soloDecimales(this,event);"/>
	                	<f:verbatim>&nbsp;</f:verbatim>	                			 	 

						<h:outputText id="outnroCertificado" value="Nro Certificado: " styleClass="texto"/>
		                <h:inputText id="nroCertificado" value="#{ExclusionBean.exclusion.nroCertificado}" 
	                			 	 size="20" maxlength="20" styleClass="bordeceldainferior" style=" width : 100px;"
	                			 	 onfocus="encender(this);" onblur="apagar(this);"
	                			 	 onkeypress="return soloEnteros(this,event);"/>				 			
	                	<f:verbatim>&nbsp;</f:verbatim>	                	
	                	
						<h:outputText id="outPeriodoFiscal" value="Periodo Fiscal: " styleClass="texto"/>
		                <h:inputText id="periodoFiscal" value="#{ExclusionBean.exclusion.periodoFiscal}" 
	                			 	 size="4" maxlength="4" styleClass="bordeceldatext" style=" width : 50px;"
	                			 	 onfocus="encender(this);" onblur="apagar(this);"/>				 			
	                	<f:verbatim>&nbsp;</f:verbatim>
	                	
						<h:outputText id="outresolucionGral" value="Resolución Gral: " styleClass="texto"/>
		                <h:inputText id="resolucionGral" value="#{ExclusionBean.exclusion.resolucionGral}" 
	                			 	 size="50" maxlength="50" styleClass="bordeceldatext" style=" width : 100px;"
	                			 	 onfocus="encender(this);" onblur="apagar(this);"/>				 			
	                	<f:verbatim>&nbsp;</f:verbatim>	                		                	
	                	
						<h:outputText id="outNroLegajo" value="Nro Legajo: " styleClass="texto"/>
		                <h:inputText id="nroLegajo" value="#{ExclusionBean.exclusion.nroLegajo}" 
	                			 	 size="20" maxlength="20" styleClass="bordeceldainferior" style=" width : 100px;"
	                			 	 onfocus="encender(this);" onblur="apagar(this);"
	                			 	 onkeypress="return soloEnteros(this,event);"/>				 			
	                	<f:verbatim>&nbsp;</f:verbatim>	                	
	                	
						<h:outputText id="outEstado" value="Estado: " styleClass="texto"/>
		                <h:inputText id="estado" value="#{ExclusionBean.exclusion.estado}" 
	                			 	 size="30" maxlength="30" styleClass="bordeceldatext" style=" width : 100px;"
	                			 	 onfocus="encender(this);" onblur="apagar(this);"/>				 			
	                	<f:verbatim>&nbsp;</f:verbatim>
	                	
						<h:outputText value="Fecha Emisión:" styleClass="texto"/>
				 		<x:inputCalendar id="FechaEmision" monthYearRowClass="yearMonthHeader" weekRowClass="weekHeader" 
				 			popupButtonStyleClass="standard_bold" currentDayCellClass="currentDayCell" 
				 			value="#{ExclusionBean.fechaEmision}" renderAsPopup="true" styleClass="bordeceldainferior" 
				 			style="width: 90px" popupDateFormat="dd/MM/yyyy" helpText="DD/MM/YYYY" forceId="true"/>
	                	<f:verbatim>&nbsp;</f:verbatim>	                			                	
              		</h:panelGrid>
              		
					<f:verbatim><hr align="center" width="600"></f:verbatim>
					<h:panelGrid columns="7" width="560">
						<f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim>
						<f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim>
						<f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim>
						<f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim>
						<f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim>
	                	<x:commandButton id="buttonAceptarDomicilioPopup" value="Aceptar" 
	                					 action="#{ExclusionBean.agregarExclusion}" 
	                					 styleClass="botones" 
	                					 actionListener="#{ExclusionBean.recargarYCerrarPopup}"/>
						<x:commandButton id="buttonVolverDomicilioPopup" value="Cancelar" 
										 action="#{ExclusionBean.cancelar}" 
										 styleClass="botones" onclick="window.close();"/>
					</h:panelGrid>					
		</h:panelGroup>
	</h:panelGroup>
	</x:panelTabbedPane>
</h:form>
</center>
</body>
</html>
</f:view>
