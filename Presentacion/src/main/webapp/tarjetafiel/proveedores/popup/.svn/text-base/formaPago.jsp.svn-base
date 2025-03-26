<%@include file="/inc/tags.jsp" %>

<f:view>
<html>
<head>
    <title><h:outputText value="Tarjeta Fiel - Agregar relación con clientes"/></title>
  	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/basic.css" />
  	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/popup.css" />
  	<s:script language="javascript">
		function recargar() {
			document.getElementById('formaPagoForm').submit();
		}
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

<body onbeforeunload="ShowWait('formaPagoForm');">
<center>
<h:form id="formaPagoForm">
	<x:panelTabbedPane id="formaPagoTabbedPane" bgcolor="#dcdcdc">
		<h:panelGroup id="formaPagoPanelGroupPrincipal">
		<h:outputText value="Formas de Pago" style="FONT-SIZE: large;"/>
		
		<%-- INCLUDE PARA LOS ERRORES --%> 	
		<h:panelGroup id="errores">
			<jsp:include page="/inc/error.jsp" />
		</h:panelGroup>            		

		<h:panelGrid id="panelGridUno" columns="2">
			<h:outputText value="Forma pago:" styleClass="texto"/>
			<h:selectOneMenu id="lstFormaPago" styleClass="lista" 
							valueChangeListener="#{FormaDePagoBean.selecFormaPago}"
							value="#{FormaDePagoBean.formaPagoSeleccionada}" 
						     immediate="true" onfocus="encender(this);" onblur="apagar(this);"
						     onchange="submit();" binding="#{FormaDePagoBean.fpOneMenuHtml}">
	        	<f:selectItems value="#{FormaDePagoBean.listaDeFormasDePago}" id="formasDePago"/>
       		</h:selectOneMenu>
       	</h:panelGrid>
		
        
 

		<%--h:outputText value="#{FormaDePagoBean.sucursalFormaPago.nroCuentaFondos}" styleClass="textoblue"/--%>
		<h:panelGroup id="formaPagoPanelGroupSecundario">
			<h:panelGrid id="panelGridTres" columns="1">
	                
					<h:panelGroup id="opcionDos" rendered="#{FormaDePagoBean.esCheque}">
					    <h:outputText id="outChequeNoAlaOrden" value="Cheque no a la orden:" styleClass="texto"/>
						<h:selectOneMenu id="lstNoEsAlaOrden" value="#{FormaDePagoBean.formaPago.chequeNoAlaOrden}" 
		        					 styleClass="lista" immediate="true" onfocus="encender(this);" onblur="apagar(this);">
		        			<f:selectItem id="sip" itemValue="S" itemLabel="Si"/>
		        			<f:selectItem id="nop" itemValue="N" itemLabel="No"/>	        			
		        		</h:selectOneMenu>
				
		                <h:outputText id="outOrdenCheque" value="Orden Cheque: " styleClass="texto"/>
		                <h:inputText id="OrdenCheque" value="#{FormaDePagoBean.formaPago.ordenCheque}" 
		                			 styleClass="bordeceldatext" immediate="true" maxlength="40" size="40" 
		                			 style="width: 250px" onfocus="encender(this);" onblur="apagar(this);"/>
		                <f:verbatim>&nbsp;</f:verbatim>			 
		                
		                <h:outputText id="outChequeCruzado" value="Cheque Cruzado: " styleClass="texto"/>
						<h:selectOneMenu id="lstChequeCruzado" value="#{FormaDePagoBean.formaPago.esChequeCruzado}" 
		        					 styleClass="lista" immediate="true" onfocus="encender(this);" onblur="apagar(this);">
		        			<f:selectItem id="si" itemValue="S" itemLabel="Si"/>
		        			<f:selectItem id="no" itemValue="N" itemLabel="No"/>	        			
		        		</h:selectOneMenu>
		        	</h:panelGroup>
		
					

				<h:panelGroup id="opcionTresBanco" rendered="#{FormaDePagoBean.esAcreditacionBancaria}">
                    <h:panelGrid columns="2">
		                <h:outputText id="outCodigoBancario" value="Código Banco: " styleClass="texto"/>
		                <h:selectOneMenu id="lstBanco" value="#{FormaDePagoBean.bancoSeleccionado}" 
		        					 styleClass="lista" immediate="true" onfocus="encender(this);" onblur="apagar(this);">
							<f:selectItems value="#{FormaDePagoBean.listaDeBancos}" id="lBancos"/>
		        		</h:selectOneMenu>
                    </h:panelGrid>
                    <h:panelGrid columns="4">
                    <h:outputText id="outTipoCuenta" value="Tipo Cuenta: " styleClass="texto"/>
					<h:selectOneMenu id="lstTipoCuentaFP" value="#{FormaDePagoBean.tipoCuentaBancoSeleccionada}" 
	        					 styleClass="lista" immediate="true" onfocus="encender(this);" onblur="apagar(this);">
						<f:selectItems value="#{FormaDePagoBean.listaDeCuentasBancos}" id="cuentaBancos"/>
	        		</h:selectOneMenu>
                            <h:outputText id="outCbu" value="Nro. CBU: " styleClass="texto"/>
	                             <h:inputText id="Cbu" value="#{FormaDePagoBean.cbu}" 
	                			 size="22" maxlength="22" immediate="true" styleClass="bordeceldainferior" 
	                			 style="width: 220px" onfocus="encender(this);" onblur="apagar(this);"
	             			 onkeypress="return soloEnteros(this,event);"/>
                              <h:outputText id="outCuentaDeposito" value="Cuenta Deposito: " styleClass="texto"/>
	                           <h:inputText id="CuentaDeposito" value="#{FormaDePagoBean.formaPago.codCtaDeposito}" 
	                			 size="17" maxlength="17" immediate="true" styleClass="bordeceldatext" 
	                			 style=" width : 135px;" onfocus="encender(this);" onblur="apagar(this);"
	                			 onkeypress="return soloEnteros(this,event);"/>
            
			         </h:panelGrid> 
	            </h:panelGroup>
	        		
					<h:panelGroup id="opcionSeis" rendered="#{FormaDePagoBean.esCaja}">
	                       <h:panelGrid id="secun" columns="3" rendered="false">
			                     <h:outputText value="Numero de Cuenta Fondos : " styleClass="texto"/>
								 <h:inputText value="#{FormaDePagoBean.idPlanCuentaABuscar}" onkeypress="return soloEnteros(this, event);" maxlength="4" id="inputCuentaBusquedaLibro" title="Introduzca el nro. de  cuenta buscada." style=" width : 47px;"/>
								 <x:commandLink id="buscarCuentaLink"  action="#{FormaDePagoBean.buscarCuentaPopup}">
									<x:graphicImage value="/img/icon/srch_24.gif" title="Buscar Cuenta." border="0"/>								
								 </x:commandLink>
						    </h:panelGrid>	
						    <h:outputText value=""  id="relleno2" />
						    <h:panelGrid columns="2">		
						      <h:outputText id="outhabilitada" value="Habilitada:" styleClass="texto"/>
						      <h:selectBooleanCheckbox id="boolChecHabilitada" value="#{FormaDePagoBean.habilitada}" />
						    </h:panelGrid>	
                           
					</h:panelGroup>
                     <h:panelGrid columns="2" >
				                     <h:outputText value="Numero de Cuenta Fondos : " styleClass="texto"/>
									 <h:selectOneMenu id="lstctasFondo" value="#{FormaDePagoBean.idCuentaFondosSeleccionada}"
											styleClass="lista" immediate="true" onfocus="encender(this);"
											onblur="apagar(this);" style="width: 200px">
											<f:selectItems id="itemListadoCuentasFondo" value="#{FormaDePagoBean.cuentaFondosItems}"/>
									</h:selectOneMenu>
                   	</h:panelGrid>  					


			</h:panelGrid>
		</h:panelGroup>

				<h:panelGroup id="opcionSiete" >
				<f:verbatim><hr align="center" width="500"></f:verbatim>
				<h:panelGrid id="panelGridBotones"  columns="8" width="532">
					<f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim>
					<f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim>
					<f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim>
					<f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim>
					<f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim>
					<f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim>
	               	<x:commandButton id="buttonGrabarFormaPago" 
	               	                 value="Grabar" 
	               	                 action="#{FormaDePagoBean.agregarFormaDePago}" 
	               	                 styleClass="botones" actionListener="#{FormaDePagoBean.recargarYCerrarPopup}"/>
					<x:commandButton id="buttonCancelarFormaPago"
									 value="Cancelar" onclick="window.close();"
									 action="#{FormaDePagoBean.volver}"  
									 styleClass="botones"/>
				</h:panelGrid>					
				</h:panelGroup>
			</h:panelGroup>
	</x:panelTabbedPane>
</h:form>	
</center>
</body>
</html>
</f:view>