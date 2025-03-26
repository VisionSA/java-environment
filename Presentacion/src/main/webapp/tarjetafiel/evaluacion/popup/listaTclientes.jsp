<%@include file="/inc/tags.jsp" %>

<f:view>
<html>
<head>
    <title><h:outputText value="Tarjeta Fiel - Mostrar Cliente"/></title>
  	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/basic.css" />
  	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/popup.css" />
</head>

<%@include file="/inc/head.inc" %>

<body  onbeforeunload="ShowWait('listaTclienteForm');">
<center>
<h:form id="listaTclienteForm">
	<x:panelTabbedPane bgcolor="#dcdcdc">
	
		<h:panelGroup id="body">
        	    	            	
            <h:panelGrid columns="1" align="center" id="PanelPricipalTClientes" width="600">
            
            	<f:verbatim>
            	
            		<x:dataTable id="listado"
            			styleClass="standardTable"
	                    headerClass="standardTable_Header"
	                    footerClass="standardTable_Header"
	                    rowClasses="standardTable_Row1,standardTable_Row2"
	                    columnClasses="standardTable_Column,standardTable_Column"
	                    var="cliente"
	                    value="#{IndividuoEvaluacionBean.listaTcliente}"
	                    preserveDataModel="false"
	                    align="center" 
	                    style=" width : 600px;">
	                    
	                    	<h:column>
	                        	<f:facet name="header">
	                        		<h:outputText value="Empresa"/>
	                        	</f:facet>
	                            <h:outputText value="#{cliente.nombreEmpresa}" />
	                        </h:column>
	
	                        <h:column>
	                            <f:facet name="header">
	                            	<h:outputText value="Descripción"/>
	                            </f:facet>
	                            <h:panelGroup id="DESCRIPCION">
	                            	
	                            	<h:outputText value="C.U.I.T: " id="cuit"/>
	                            	<h:outputText value="#{cliente.cuit}" styleClass="textoblue" id="CUIT"/>
	                            	
		                         	<f:verbatim><br></f:verbatim>
		                         	
		                         	<h:outputText value=" Calle: " id="calle"/>
	                            	<h:outputText value="#{cliente.calle}" styleClass="textoblue" id="CALLE"/>
		                         	
		                         	<h:outputText value=" - Orientación: " id="orientacion"/>
	                            	<h:outputText value="#{cliente.orientacion}" styleClass="textoblue" id="ORIENTACION"/>
		                         	
		                         	<h:outputText value=" - nro: " id="nro"/>
	                            	<h:outputText value="#{cliente.nroCalle}" styleClass="textoblue" id="NRO"/>
		                         	
		                         	<f:verbatim><br></f:verbatim>
			                        
			                        <h:outputText value="Dpto.: " id="dpto"/>
	                            	<h:outputText value="#{cliente.dpto}" styleClass="textoblue" id="DPTO"/>
		                         	
		                         	<h:outputText value=" - Piso: " id="piso"/>
	                            	<h:outputText value="#{cliente.piso}" styleClass="textoblue" id="PISO"/>
		                         	
		                         	<h:outputText value=" - Barrio: " id="barrio"/>
	                            	<h:outputText value="#{cliente.barrio}" styleClass="textoblue" id="BARRIO"/>
		                         	
		                         	<h:outputText value=" - Código Postal: " id="cp"/>
	                            	<h:outputText value="#{cliente.codPostal}" styleClass="textoblue" id="CP"/>
		                         	
		                         	<f:verbatim><br></f:verbatim>
			                        
			                        <h:outputText value="Dirección Generica: " id="dg"/>
			                        <h:outputText value="#{cliente.dirGenerica}" styleClass="textoblue" id="DG"/>
	                            			                         	
		                         	<h:outputText value=" - Cp1: " id="cp1"/>
	                            	<h:outputText value="#{cliente.cp1}" styleClass="textoblue" id="CP1"/>
		                         	
		                         	<h:outputText value=" - Cp2: " id="cp2"/>
	                            	<h:outputText value="#{cliente.cp2}" styleClass="textoblue" id="CP2"/>
		                         	
		                         	<h:outputText value=" - Cp3: " id="cp3"/>
	                            	<h:outputText value="#{cliente.cp3}" styleClass="textoblue" id="CP3"/>
		                         	
		                         	<f:verbatim><br></f:verbatim>
			                        
		                         	<h:outputText value="Cargo: " id="cargo"/>
	                            	<h:outputText value="#{cliente.cargo}" styleClass="textoblue" id="CARGO"/>
		                         	
		                         	<f:verbatim><br></f:verbatim>
		                         	
		                         	<h:outputText value="CodBis: " id="codvis"/>
	                            	<h:outputText value="#{cliente.codBis}" styleClass="textoblue" id="CODBIS"/>
		                         	
		                         	<f:verbatim><br></f:verbatim>
		                         	
		                         	<h:outputText value="Teléfono: " id="tel"/>
	                            	<h:outputText value="#{cliente.telefono}" styleClass="textoblue" id="TEL"/>
		                         	
		                         	<h:outputText value=" - Fax: " id="fax"/>
	                            	<h:outputText value="#{cliente.fax}" styleClass="textoblue" id="FAX"/>
		                         	
		                         	<f:verbatim><br></f:verbatim>
		                         	
		                         	<h:outputText value="eMail: " id="mail"/>
	                            	<h:outputText value="#{cliente.mail}" styleClass="textoblue" id="MAIL"/>
		                         	
		                         	<f:verbatim><br></f:verbatim>
		                         	
		                         	<h:outputText value="Antiguedad: " id="antiguedad"/>
	                            	<h:outputText value="#{cliente.antiguedad}" styleClass="textoblue" id="ANTIGUEDAD"/>
		                         	
		                         	<h:outputText value=" - Sueldo: " id="sueldo"/>
	                            	<h:outputText value="$#{cliente.sueldo}" styleClass="textoblue" id="SUELDO"/>
		                         	
		                         	<f:verbatim><br></f:verbatim>
		                         	
		                         	<h:outputText value="Empleo Anterior: " id="empAnt"/>
	                            	<h:outputText value="#{cliente.empAnt}" styleClass="textoblue" id="EMPANT"/>
		                         	
		                         	<h:outputText value=" - Teléfono Empresa Anterior: " id="telEm"/>
	                            	<h:outputText value="#{cliente.empTelef}" styleClass="textoblue" id="TELEMP"/>
		                         	
		                         	<f:verbatim><br></f:verbatim>
		                         	
		                         	<h:outputText value="Referencias Comerciales: " id="ref"/>
	                            	<h:outputText value="#{cliente.refComerciales}" styleClass="textoblue" id="REF"/>
		                         	
		                         	<f:verbatim><br></f:verbatim>
		                         	
		                         	<h:outputText value="Origen otros ingresos: " id="ooi"/>
	                            	<h:outputText value="#{cliente.otroIngOrigen}" styleClass="textoblue" id="OOI"/>
		                         	
		                         	<h:outputText value=" - Monto otros ingresos: " id="moi"/>
	                            	<h:outputText value="$#{cliente.otroIngMonto}" styleClass="textoblue" id="MOI"/>
		                         	
		                         	<f:verbatim><br></f:verbatim>
		                         	
		                         	<h:outputText value="Sueldo Fecha: " id="sf"/>
	                            	<h:outputText value="#{cliente.sueldoFec}" styleClass="textoblue" id="SF"/>
		                         	
		                        </h:panelGroup>
	                        </h:column>
	                        
	                </x:dataTable>
	                
				</f:verbatim>
				
			</h:panelGrid>
			
		</h:panelGroup>

	</x:panelTabbedPane>
</h:form>    
</center>    
</body>
</html>
</f:view>