<%@include file="/inc/tags.jsp"%>

<f:view>
	<html>
	<head>
	<meta HTTP-EQUIV="Content-Type" CONTENT="text/html;CHARSET=iso-8859-1">
	<title><h:outputText value="Tarjeta Fiel - Administración de Cheques" /></title>
	<link rel="stylesheet" type="text/css"
		href="<%=request.getContextPath()%>/css/basic.css" />
	</head>

	<%@include file="/inc/head.inc"%>

	<body onbeforeunload="ShowWait('agregarAtributoTareaForm');">
	 	<script languaje="javascript">
			//Permite que se ingrese un número decimal de decimales definidos en un Input Text
			function soloDecimalesPrecisos(InputText, evt, cantDeci) {
				var charCode = (evt.which) ? evt.which : event.keyCode;
				var strValue = '';
				var canti = parseInt(cantDeci);
			 	var selectionStart = getSelectionStart(InputText);
			  	var selectionEnd = getSelectionEnd(InputText);
				strValue = InputText.value.substring(0, selectionStart) + String.fromCharCode(charCode) + InputText.value.substring(selectionEnd);
				if (!isDecimalConNDecimales(strValue, canti) && charCode > 31) {
					return false;
				}		
				return true;
			}

			// Retorna true si inputVal es decimal.
			function isDecimalConNDecimales(inputVal, numeroDecimales) {
				oneDecimal = false;
				cantidades = 0;
				inputStr = inputVal.toString();
				for (var i = 0; i < inputStr.length; i++) {
					var oneChar = inputStr.charAt(i);
				//	if (i == 0 && oneChar == "-") {
				//		continue;
				//	}
					if (oneDecimal) {
					    cantidades = cantidades + 1;
					    if (cantidades > parseInt(numeroDecimales)) {return false;}
					}
					if (oneChar == "." && !oneDecimal) {
						oneDecimal = true;
						continue;
					}
					if (oneChar < "0" || oneChar > "9") {
						return false;
					}
				}
				return true;
			}	 
		</script>
	<center>
	<h:form id="admCheque">
		<x:panelTabbedPane bgcolor="#dcdcdc">
			<h:panelGrid columns="1" align="center">
				<h:outputText value="Administración de Medios" style="FONT-SIZE: large;" styleClass="textoblue" />
				
				<%-- INCLUDE PARA LOS ERRORES --%>
				<h:panelGroup id="errores">
					<jsp:include page="/inc/error.jsp" />
				</h:panelGroup>
			<h:outputText id="outPlanCuenta" value="Cuenta Fondos: #{AdminChequeBean.planCuenta}" styleClass="texto"/>
			<s:fieldset legend="" rendered="#{!AdminChequeBean.esEfectivo}">
				<h:selectOneRadio id="selectOneRadio" binding="#{AdminChequeBean.selectAlta}">
					<f:selectItem itemValue="true" itemLabel="Alta" id="alta"/>
					<f:selectItem itemValue="false" itemLabel="Busqueda" id="busqueda"/>
				</h:selectOneRadio>
			</s:fieldset>
			<h:panelGrid id="panelPrincipalUno" columns="2" width="450" align="center">
				<h:outputText value="Medio: " styleClass="texto"/>
				<h:outputText value="#{AdminChequeBean.medioSeleccionado}" styleClass="texto" style="color:green;"/>
 				<h:outputText value="   " rendered="#{AdminChequeBean.mostrarBotonera}"/>
				<x:commandButton id="buttonAgregaMedio" value="Agregar" rendered="#{AdminChequeBean.mostrarBotonera}"
					action="#{AdminChequeBean.agregarMedio}" styleClass="botones"/>
			</h:panelGrid>
			<x:dataTable id="listadoMedios"
                            styleClass="standardTable"
                            headerClass="dataTable_Header"
                            footerClass="standardTable_Header"
							columnClasses="standardTable_ColumnCentered"
                            sortable="true" 
                            var="obj"
                            width="400px"
                            rowStyle="border-width: 0;"
                            value="#{AdminChequeBean.mediosList}">
                        
                        <x:column>
                            <f:facet name="header">
                                <h:outputText value="Numero"/>
                            </f:facet>
							<h:outputText id="outNumero" value="#{obj.cheque.numero}" styleClass="texto"/>
                        </x:column>
                        <x:column>
                            <f:facet name="header">
                                <h:outputText value="Beneficiario"/>
                            </f:facet>
							<h:outputText id="outBenef" value="#{obj.cheque.beneficiario}" styleClass="texto"/>
                        </x:column>
						<x:column>
                            <f:facet name="header">
                                <h:outputText value="Emisión"/>
                            </f:facet>
							<h:outputText id="outFechaEmision" value="#{obj.cheque.fechaEmision}" styleClass="texto"/>
                        </x:column>
                        <x:column>
                            <f:facet name="header">
                                <h:outputText value="Pago"/>
                            </f:facet>
							<h:outputText id="outFechaPago" value="#{obj.cheque.fechaPago}" styleClass="texto"/>
                        </x:column>
						<x:column>
                            <f:facet name="header">
                                <h:outputText value="Importe"/>
                            </f:facet>
							<h:outputText id="outImporte" value="#{obj.importe}" styleClass="texto"/>
                        </x:column>
						<h:column>
							<x:commandLink action="#{obj.eliminar}" id="eliminarMedioLink">
								<x:graphicImage value="/img/cat_act.gif" title="Eliminar Medio." border="0" id="botonImagenTres" />
							</x:commandLink>
						</h:column>
			</x:dataTable>
<%-- 
Ir haciendo visible la carga formateada segun la seleccion del medio y agregarle un boton de cargar que lo valla 
agregando a la lista
--%>
<%-- Modulo para alta/busqueda de cheques de terceros --%>
			<h:panelGrid columns="1" align="center">
				<s:fieldset legend="#{AdminChequeBean.leyendaNros}" rendered="#{AdminChequeBean.boolNroCheque}">
					<h:panelGrid columns="8">
						<h:outputText id="outBanco" value="Banco: " styleClass="texto"/>
		                <h:inputText id="inpBanco" value="#{AdminChequeBean.cheque.banco.idBanco}" 
	                			 	 size="3" maxlength="3" styleClass="bordeceldainferior" style="width: 40px"
	                			 	 onfocus="encender(this);" onblur="apagar(this);"
	                			 	 onkeypress="return soloEnteros(this,event);"/>
	
						<h:outputText id="outSucursal" value="Sucursal: " styleClass="texto"/>
		                <h:inputText id="inpSucursal" value="#{AdminChequeBean.cheque.sucursalBanco}" 
	                			 	 size="3" maxlength="3" styleClass="bordeceldainferior" style="width: 40px"
	                			 	 onfocus="encender(this);" onblur="apagar(this);"
	                			 	 onkeypress="return soloEnteros(this,event);"/>
	
						<h:outputText id="outCodPostal" value="CP: " styleClass="texto"/>
		                <h:inputText id="inpCodPostal" value="#{AdminChequeBean.cheque.codigoPostal}" 
	                			 	 size="4" maxlength="4" styleClass="bordeceldainferior" style="width: 40px"
	                			 	 onfocus="encender(this);" onblur="apagar(this);"
	                			 	 onkeypress="return soloEnteros(this,event);"/>
	
						<h:outputText id="outDV1" value="DV: " styleClass="texto"/>
		                <h:inputText id="inpDV1" value="#{AdminChequeBean.cheque.DV1}" 
	                			 	 size="1" maxlength="1" styleClass="bordeceldainferior" style="width: 15px"
	                			 	 onfocus="encender(this);" onblur="apagar(this);"
	                			 	 onkeypress="return soloEnteros(this,event);"/>
	
						<h:outputText id="outNumero" value="Numero: " styleClass="texto"/>
		                <h:inputText id="inpNumero" value="#{AdminChequeBean.cheque.numero}" 
	                			 	 size="8" maxlength="8" styleClass="bordeceldainferior" style="width: 70px"
	                			 	 onfocus="encender(this);" onblur="apagar(this);"
	                			 	 onkeypress="return soloEnteros(this,event);"/>
	
						<h:outputText id="outDV2" value="DV: " styleClass="texto"/>
		                <h:inputText id="inpDV2" value="#{AdminChequeBean.cheque.DV2}" 
	                			 	 size="1" maxlength="1" styleClass="bordeceldainferior" style="width: 15px"
	                			 	 onfocus="encender(this);" onblur="apagar(this);"
	                			 	 onkeypress="return soloEnteros(this,event);"/>
						<h:outputText value="    " />
						<h:outputText value="    " />
						<h:outputText value="    " />
						<h:outputText value="    " />
	
						<h:outputText id="outNroCuenta" value="Nro Cuenta: " styleClass="texto"/>
		                <h:inputText id="inpNroCuenta" value="#{AdminChequeBean.cheque.cuenta}" 
	                			 	 size="11" maxlength="11" styleClass="bordeceldainferior" style="width: 90px"
	                			 	 onfocus="encender(this);" onblur="apagar(this);"
	                			 	 onkeypress="return soloEnteros(this,event);"/>
	
						<h:outputText id="outDV3" value="DV: " styleClass="texto"/>
		                <h:inputText id="inpDV3" value="#{AdminChequeBean.cheque.DV3}" 
	                			 	 size="1" maxlength="1" styleClass="bordeceldainferior" style="width: 15px"
	                			 	 onfocus="encender(this);" onblur="apagar(this);"
	                			 	 onkeypress="return soloEnteros(this,event);"/>
						<h:outputText value="    " />
						<h:outputText value="    " />
						<x:commandButton id="buttonBuscarPopup2" value="#{AdminChequeBean.botonNros}"
							action="#{AdminChequeBean.buscarTercero}" styleClass="botones"/>
						<h:outputText value="    " />
					</h:panelGrid>
				</s:fieldset>
<%-- 
						<h:outputText id="outNumero1" value="Numero: " styleClass="texto"/>
		                <h:inputText id="inpNumero1" value="#{AdminChequeBean.cheque.numero}" 
	                			 	 size="8" maxlength="8" styleClass="bordeceldainferior" style="width: 70px"
	                			 	 onfocus="encender(this);" onblur="apagar(this);"
	                			 	 onkeypress="return soloEnteros(this,event);"/> --%>
				<s:fieldset legend="#{AdminChequeBean.leyendaNros}" rendered="#{AdminChequeBean.boolBusquedaPropio}">
					<h:panelGrid columns="6">
						<h:outputText id="outBanco1" value="Banco: " styleClass="texto"/>
						<h:outputText id="outDescBanco1" value="#{AdminChequeBean.cheque.bancoPropio.label}" styleClass="texto"/>
						<h:outputText value="    " />
						<h:outputText value="    " />
						<h:outputText value="    " />
						<x:commandButton id="buttonBuscarPopup" value="#{AdminChequeBean.botonNros}"
							action="#{AdminChequeBean.buscarPropio}" styleClass="botones"/>

						<h:outputText id="outBenef" value="Beneficiario:" styleClass="texto"/>
						<h:inputText id="benef" value="#{AdminChequeBean.beneficiario}"
							styleClass="bordeceldatext" style="width: 200px" 
							onfocus="encender(this);" onblur="apagar(this);"/>
						<h:outputText value="    " />
						<h:outputText value="Tipo:" styleClass="texto"/>
						<h:selectOneMenu id="lstTipo" value="#{AdminChequeBean.idTipoSeleccionada}"
							styleClass="lista" immediate="true" onfocus="encender(this);"
							onblur="apagar(this);" style="width: 130px" disabled="#{AdminChequeBean.tipoDesabilitado}">
							<f:selectItem itemValue="0" itemLabel="Seleccione" id="itemSeleccione"/>
							<f:selectItem itemValue="P" itemLabel="Propio" id="itemPropio"/>
							<f:selectItem itemValue="A" itemLabel="Acreditación" id="itemAcred"/>
						</h:selectOneMenu>
						<h:outputText value="    " />
					</h:panelGrid>
					<h:panelGrid columns="6">
						<h:outputText id="outNroCheque" value="Numero de Cheque" styleClass="texto"/>
						<h:outputText id="out00"  value=" "/>
						<h:outputText id="outNroDesde" value="Desde:" styleClass="texto"/>
						<h:inputText id="nroDesde" value="#{AdminChequeBean.nroChequeDesde}" align="right" 
							styleClass="bordeceldainferior" maxlength="10" size="10"
							style="width: 100px" onfocus="encender(this);" onblur="apagar(this);"
							onkeypress="return soloEnteros(this,event);"/>
						<h:outputText id="outNroHasta" value="Hasta:" styleClass="texto"/>
						<h:inputText id="nroHasta" value="#{AdminChequeBean.nroChequeHasta}" align="right"
							styleClass="bordeceldainferior" maxlength="10" size="10"
							style="width: 100px" onfocus="encender(this);" onblur="apagar(this);"
							onkeypress="return soloEnteros(this,event);"/>

						<h:outputText id="outImporte" value="Importe" styleClass="texto"/>
						<h:outputText id="out01"  value=" "/>
						<h:outputText id="outImpDesde" value="Desde:" styleClass="texto"/>
						<h:inputText id="impDesde" value="#{AdminChequeBean.importeDesde}" align="right" 
							styleClass="bordeceldainferior" maxlength="10" size="10"
							style="width: 100px" onfocus="encender(this);" onblur="apagar(this);"
							onkeypress="return soloDecimalesPrecisos(this,event,2);"/>
						<h:outputText id="outImpHasta" value="Hasta:" styleClass="texto"/>
						<h:inputText id="impHasta" value="#{AdminChequeBean.importeHasta}" align="right"
							styleClass="bordeceldainferior" maxlength="10" size="10"
							style="width: 100px" onfocus="encender(this);" onblur="apagar(this);"
							onkeypress="return soloDecimalesPrecisos(this,event,2);"/>

						<h:outputText id="outFechaEmision" value="Fecha Emision" styleClass="texto"/>
						<h:outputText id="out02"  value=" "/>
						<h:outputText id="outEmiDesde" value="Desde:" styleClass="texto"/>
						<h:panelGroup id="GRdesdeEmision">
					 		<x:inputCalendar id="desdeEmision" monthYearRowClass="yearMonthHeader" weekRowClass="weekHeader" popupButtonStyleClass="standard_bold"
		                		currentDayCellClass="currentDayCell" value="#{AdminChequeBean.fechaEmisionDesde}" renderAsPopup="true"
				                styleClass="bordeceldainferior" style="width: 90px" align="right" 
		        		        popupDateFormat="dd/MM/yyyy" helpText="DD/MM/YYYY" forceId="true"/>
						</h:panelGroup>
						<h:outputText id="outEmiHasta" value="Hasta:" styleClass="texto"/>
						<h:panelGroup id="GRhastaEmision">
					 		<x:inputCalendar id="hastaEmision" monthYearRowClass="yearMonthHeader" weekRowClass="weekHeader" popupButtonStyleClass="standard_bold"
		                		currentDayCellClass="currentDayCell" value="#{AdminChequeBean.fechaEmisionHasta}" renderAsPopup="true"
				                styleClass="bordeceldainferior" style="width: 90px" align="right"
		        		        popupDateFormat="dd/MM/yyyy" helpText="DD/MM/YYYY" forceId="true"/>
						</h:panelGroup>

						<h:outputText id="outFechaPago" value="Fecha Pago" styleClass="texto"/>
						<h:outputText id="out03"  value=" "/>
						<h:outputText id="outPagDesde" value="Desde:" styleClass="texto"/>
						<h:panelGroup id="GRdesdePago">
					 		<x:inputCalendar id="desdePago" monthYearRowClass="yearMonthHeader" weekRowClass="weekHeader" popupButtonStyleClass="standard_bold"
		                		currentDayCellClass="currentDayCell" value="#{AdminChequeBean.fechaPagoDesde}" renderAsPopup="true"
				                styleClass="bordeceldainferior" style="width: 90px" align="right"
		        		        popupDateFormat="dd/MM/yyyy" helpText="DD/MM/YYYY" forceId="true"/>
						</h:panelGroup>
						<h:outputText id="outPagHasta" value="Hasta:" styleClass="texto"/>
						<h:panelGroup id="GRhastaPago">
					 		<x:inputCalendar id="hastaPago" monthYearRowClass="yearMonthHeader" weekRowClass="weekHeader" popupButtonStyleClass="standard_bold"
		                		currentDayCellClass="currentDayCell" value="#{AdminChequeBean.fechaPagoHasta}" renderAsPopup="true"
				                styleClass="bordeceldainferior" style="width: 90px" align="right"
		        		        popupDateFormat="dd/MM/yyyy" helpText="DD/MM/YYYY" forceId="true"/>
						</h:panelGroup>
					</h:panelGrid>
					<x:dataTable id="listadoCheques" styleClass="standardTable"
                            headerClass="dataTable_Header" footerClass="standardTable_Header"
							columnClasses="standardTable_ColumnCentered" sortable="true" 
                            var="obj" width="400px" rowStyle="border-width: 0;"
                            value="#{AdminChequeBean.listadoCheques}">
                        <x:column>
                            <f:facet name="header">
                                <h:outputText value="Tipo"/>
                            </f:facet>
							<h:outputText id="outBusTipo" value="#{obj.cheque.tipo}" styleClass="texto"/>
                        </x:column>
                        <x:column>
                            <f:facet name="header">
                                <h:outputText value="Numero"/>
                            </f:facet>
							<h:outputText id="outBusNumero" value="#{obj.cheque.numero}" styleClass="texto"/>
                        </x:column>
                        <x:column>
                            <f:facet name="header">
                                <h:outputText value="Beneficiario"/>
                            </f:facet>
							<h:outputText id="outbusBenef" value="#{obj.cheque.beneficiario}" styleClass="texto"/>
                        </x:column>
						<x:column>
                            <f:facet name="header">
                                <h:outputText value="Emisión"/>
                            </f:facet>
							<h:outputText id="outBusEmision" value="#{obj.cheque.fechaEmision}" styleClass="texto"/>
                        </x:column>
                        <x:column>
                            <f:facet name="header">
                                <h:outputText value="Pago"/>
                            </f:facet>
							<h:outputText id="outBusPago" value="#{obj.cheque.fechaPago}" styleClass="texto"/>
                        </x:column>
						<x:column>
                            <f:facet name="header">
                                <h:outputText value="Importe"/>
                            </f:facet>
							<h:outputText id="outBusImporte" value="#{obj.cheque.importe}" styleClass="texto"/>
                        </x:column>
						<h:column>
							<x:commandButton id="buttonSeleccionaCheque" value="Seleccionar"
								action="#{obj.seleccionar}" styleClass="botones"/>
						</h:column>
					</x:dataTable>
				</s:fieldset>


				<s:fieldset legend="#{AdminChequeBean.leyendaNros}" rendered="#{AdminChequeBean.boolAltaAcrd}">
					<h:panelGrid id="panelBeneficiario" columns="5" width="600"
								align="center">

								<h:outputText value="Cuenta Destino:" styleClass="texto" />
								<h:selectOneMenu id="lstTipoConc"  
									value="#{AdminChequeBean.idTipoAccionSeleccionada}"
									styleClass="lista" immediate="true" onfocus="encender(this);"
									onblur="apagar(this);" style="width: 200px"
									binding="#{AdminChequeBean.tipoAccion}"
									valueChangeListener="#{AdminChequeBean.cambiarTipoAccion}"
									onchange="submit();">
									<f:selectItems id="itemTipo" value="#{AdminChequeBean.tipoBusquedaItems}" />
								</h:selectOneMenu>

								<h:outputText value="Cuenta Propia:" styleClass="texto" rendered="#{AdminChequeBean.boolCuenta}"/>
								<h:selectOneMenu id="lstCuentaPropia"  rendered="#{AdminChequeBean.boolCuenta}"
									value="#{AdminChequeBean.idCuentaPropiaSeleccionada}"
									styleClass="lista" immediate="true" onfocus="encender(this);"
									onblur="apagar(this);" style="width: 200px">
									<f:selectItems id="itemCuenta" value="#{AdminChequeBean.cuentaPropiaItems}" />
								</h:selectOneMenu>

								<h:outputText value="Codigo:" styleClass="texto"  rendered="#{AdminChequeBean.boolCodigo}" />
								<h:inputText id="idCodigo" rendered="#{AdminChequeBean.boolCodigo}"							
									value="#{AdminChequeBean.codigo}" onkeypress="return soloEnteros(this,event);"
									styleClass="bordeceldainferior" maxlength="5" size="5"
									style="width: 90px" onfocus="encender(this);"
									onblur="apagar(this);" />
									
								<h:panelGrid columns="2" rendered="#{AdminChequeBean.boolCbu}">
									<h:outputText value="CBU:" styleClass="texto" />
									<h:inputText id="CBUFiltro"					
										value="#{AdminChequeBean.cbu}" onkeypress="return soloEnteros(this,event);"
										styleClass="bordeceldainferior" maxlength="22" size="22"
										style="width: 175px" onfocus="encender(this);"
										onblur="apagar(this);" />
	
									<h:outputText value="Banco:" styleClass="texto" />
									<h:selectOneMenu id="lstBancos" 
										value="#{AdminChequeBean.idBancoSeleccionado}"
										styleClass="lista" immediate="true" onfocus="encender(this);"
										onblur="apagar(this);" style="width: 175px">
										<f:selectItems id="itemBanco" value="#{AdminChequeBean.bancoItems}" />
									</h:selectOneMenu>

									<h:outputText value="Nro. Cuenta:" styleClass="texto" />
									<h:inputText id="cuentaFiltro"					
										value="#{AdminChequeBean.nroCuenta}" onkeypress="return soloEnteros(this,event);"
										styleClass="bordeceldainferior" maxlength="14" size="14"
								 		style="width: 175px" onfocus="encender(this);"
										onblur="apagar(this);" />

									<h:outputText value="Tipo:" styleClass="texto" />
									<h:selectOneMenu id="lstTipoCta" 
										value="#{AdminChequeBean.idTipoCtaSeleccionado}"
										styleClass="lista" immediate="true" onfocus="encender(this);"
										onblur="apagar(this);" style="width: 175px">
										<f:selectItems id="itemTipoCta" value="#{AdminChequeBean.tipoCtaItems}" />
									</h:selectOneMenu>
								</h:panelGrid>
							
								<x:commandButton id="btnBuscarBeneficiario" value="Buscar"	
									action="#{AdminChequeBean.buscarBeneficiario}"
									title="Busca el Comercio/Proveedor seleccionado"
									image="/img/icon/srch_24.gif"/>
					</h:panelGrid>
				</s:fieldset>

				<s:fieldset legend="#{AdminChequeBean.leyendaNros}" rendered="#{AdminChequeBean.boolFormaPago}">
						<x:dataTable id="listadoFormasPago"
	                            styleClass="standardTable"
	                            headerClass="dataTable_Header"
	                            footerClass="standardTable_Header"
								columnClasses="standardTable_ColumnCentered"
	                            sortable="true" 
	                            var="obj"
	                            width="400px"
	                            rowStyle="border-width: 0;"
	                            value="#{AdminChequeBean.formaPagoList}">
	                        
	                        <x:column>
	                            <f:facet name="header">
	                                <h:outputText value="Banco"/>
	                            </f:facet>
								<h:outputText id="outTipo" value="#{obj.banco.label}" styleClass="texto"/>
	                        </x:column>
	                        <x:column>
	                            <f:facet name="header">
	                                <h:outputText value="CBU"/>
	                            </f:facet>
								<h:outputText id="outTipo" value="#{obj.cbu}" styleClass="texto"/>
	                        </x:column>
							<x:column>
	                            <f:facet name="header">
	                                <h:outputText value="Tipo"/>
	                            </f:facet>
								<h:outputText id="outTipo" value="#{obj.tipoCta.descripcionCorta}" styleClass="texto"/>
	                        </x:column>
	                        <x:column>
	                            <f:facet name="header">
	                                <h:outputText value="Cuenta"/>
	                            </f:facet>
								<h:outputText id="outTipo" value="#{obj.codCtaDeposito}" styleClass="texto"/>
	                        </x:column>
							<h:column>
								<x:commandLink action="#{AdminChequeBean.seleccionarFP}" id="eliminarMedioLink">
									<f:param id="idFP" name="idFP" value="#{obj.idProvFormaPago}"/>
									<x:graphicImage value="/img/cat_pad.gif" title="Seleccionar Medio." border="0" id="botonImagenTres" />
								</x:commandLink>
							</h:column>
					</x:dataTable>
				</s:fieldset>

				<s:fieldset legend="Detalle del Cheque" rendered="#{AdminChequeBean.mostrarDetalle}">
					<h:outputText id="outCuentaBanco" value="Cuenta Bancaria: #{AdminChequeBean.bancoPropio}" styleClass="texto"/>
					<h:panelGrid columns="3">
						<h:outputText id="outBeneficiario" value="Beneficiario: " styleClass="texto"/>
		                <h:inputText id="inpBeneficiario" value="#{AdminChequeBean.cheque.beneficiario}" 
	                			 	 size="50" maxlength="50" styleClass="bordeceldatext" style="width: 300px"
	                			 	 onfocus="encender(this);" onblur="apagar(this);" 
	                			 	 disabled="#{!AdminChequeBean.alta}"/>
						<h:outputText value="    " />
						<h:outputText id="outEmision" value="Fecha Emisión: " styleClass="texto"/>
		                <x:inputCalendar id="inpEmision" monthYearRowClass="yearMonthHeader" weekRowClass="weekHeader" popupButtonStyleClass="standard_bold"
									currentDayCellClass="currentDayCell" value="#{AdminChequeBean.cheque.fechaEmision}" renderAsPopup="true"
									styleClass="bordeceldainferior" style="width: 90px" align="right"
									popupDateFormat="dd/MM/yyyy" helpText="DD/MM/YYYY" forceId="true"
									disabled="#{!AdminChequeBean.alta}"/>               
						<h:outputText value="    " />
						<h:outputText id="outPago" value="Fecha Pago: " styleClass="texto"/>
		                <x:inputCalendar id="inpPago" monthYearRowClass="yearMonthHeader" weekRowClass="weekHeader" popupButtonStyleClass="standard_bold"
									currentDayCellClass="currentDayCell" value="#{AdminChequeBean.cheque.fechaPago}" renderAsPopup="true"
									styleClass="bordeceldainferior" style="width: 90px" align="right"
									popupDateFormat="dd/MM/yyyy" helpText="DD/MM/YYYY" forceId="true"
									disabled="#{!AdminChequeBean.alta}"/>
						<h:outputText value="    " />
						<h:outputText id="outNoOrden" value="No a la orden: " styleClass="texto"/>
						<h:selectBooleanCheckbox id="selNoOrden" value="#{AdminChequeBean.cheque.noALaOrden}" 
	                           		style="background-color: #dcdcdc;"
	                           		disabled="#{!AdminChequeBean.alta}"/>
						<h:outputText value="    " />
						<h:outputText id="outCruzado" value="Cruzado: " styleClass="texto"/>
						<h:selectBooleanCheckbox id="selCruzado" value="#{AdminChequeBean.cheque.cruzado}" 
	                           		style="background-color: #dcdcdc;"
	                           		disabled="#{!AdminChequeBean.alta}"/>
						<h:outputText value="    " />
					</h:panelGrid>
				</s:fieldset>

				<h:panelGrid columns="2" rendered="#{!AdminChequeBean.mostrarBotonera}">
					<c:if test="${AdminChequeBean.alta && !AdminChequeBean.boolBusquedaPropio}">
		                <h:outputText id="outTextImporte" value="Importe: " styleClass="texto"/>
						<h:inputText value="#{AdminChequeBean.cheque.importe}"  
	                            		onkeypress="return soloDecimalesPrecisos(this,event,2);" 
	                            		id="inpImporteMonto" styleClass="bordeceldainferior"  maxlength="20"/>
					</c:if>
					<c:if test="${!AdminChequeBean.alta && !AdminChequeBean.boolBusquedaPropio}">
		                <h:outputText id="outOutImporte" value="Importe: " styleClass="texto"/>
						<h:outputText value="#{AdminChequeBean.cheque.importe}"  id="outImporteMonto" styleClass="texto"/>
					</c:if>
					<x:commandButton id="buttonAceptarAgregarMedio" value="Cargar Medio" rendered="#{!AdminChequeBean.boolBusquedaPropio}"
							action="#{AdminChequeBean.cargarMedio}" styleClass="botones"/>
					<h:outputText value="    "  rendered="#{AdminChequeBean.boolBusquedaPropio}"/>
					<x:commandButton id="buttonCancelarAgregarMedio" value="Cancelar Cargar" 
							action="#{AdminChequeBean.cancelarCargarMedio}" styleClass="botones"/>
				</h:panelGrid>

				<h:panelGrid columns="1" width="417" rendered="#{AdminChequeBean.mostrarBotonera}">
					<f:verbatim>
						<hr align="center" width="350">
					</f:verbatim>
					<h:panelGrid columns="7" width="260">
						<f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim>
						<f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim>
						<f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim>
						<f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim>
						<f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim>
						<x:commandButton id="buttonAceptarAtributoPopup" value="Aceptar"
							action="#{AdminChequeBean.grabar}" styleClass="botones"
							actionListener="#{AdminChequeBean.recargarYCerrarPopup}"/>
						<x:commandButton id="buttonVolverDomicilioPopup" value="Cancelar"
							action="#{AdminChequeBean.cancelar}" styleClass="botones"
							onclick="window.close();" />
					</h:panelGrid>
				</h:panelGrid>
			</h:panelGrid>
		</h:panelGrid>
		</x:panelTabbedPane>
	</h:form>
	</center>
	</body>
	</html>
</f:view>
<%--s:fieldset legend="Filtrar por:" style=" width : 200px;">
						<h:selectOneRadio value="#{AdminChequeBean.selectFiltro}" id="selectOneRadio" disabled="true">
							<f:selectItem itemValue="1" itemLabel="Alta" id="Busqueda"/>
							<f:selectItem itemValue="2" itemLabel="Busqueda" id="Composicion"/>
						</h:selectOneRadio>
					</s:fieldset>
		            <h:outputText id="outCuenta" value="Cuenta: " styleClass="texto"/>
					<h:selectOneMenu id="lstTipoCheque" value="#{AdminChequeBean.idCuentaSeleccionada}" 
	        					styleClass="lista" onfocus="encender(this);" onblur="apagar(this);" style=" width : 200px;"
						valueChangeListener="#{AdminChequeBean.cambioCuenta}" onchange="submit();">
						<f:selectItems id="itemsCuenta" value="#{AdminChequeBean.cuentaItems}"/>
					</h:selectOneMenu>
	
					<s:fieldset rendered="#{AdminChequeBean.boolNroCheque}">
						<h:panelGrid columns="8">
							<h:outputText id="outBanco" value="Banco: " styleClass="texto"/>
			                <h:inputText id="inpBanco" value="#{AdminChequeBean.cheque.banco.idBanco}" 
		                			 	 size="3" maxlength="3" styleClass="bordeceldainferior" style="width: 40px"
		                			 	 onfocus="encender(this);" onblur="apagar(this);"
		                			 	 onkeypress="return soloEnteros(this,event);"/>
		
							<h:outputText id="outSucursal" value="Sucursal: " styleClass="texto"/>
			                <h:inputText id="inpSucursal" value="#{AdminChequeBean.cheque.sucursalBanco}" 
		                			 	 size="3" maxlength="3" styleClass="bordeceldainferior" style="width: 40px"
		                			 	 onfocus="encender(this);" onblur="apagar(this);"
		                			 	 onkeypress="return soloEnteros(this,event);"/>
		
							<h:outputText id="outCodPostal" value="CP: " styleClass="texto"/>
			                <h:inputText id="inpCodPostal" value="#{AdminChequeBean.cheque.codigoPostal}" 
		                			 	 size="4" maxlength="4" styleClass="bordeceldainferior" style="width: 40px"
		                			 	 onfocus="encender(this);" onblur="apagar(this);"
		                			 	 onkeypress="return soloEnteros(this,event);"/>
		
							<h:outputText id="outDV1" value="DV: " styleClass="texto"/>
			                <h:inputText id="inpDV1" value="#{AdminChequeBean.cheque.DV1}" 
		                			 	 size="1" maxlength="1" styleClass="bordeceldainferior" style="width: 15px"
		                			 	 onfocus="encender(this);" onblur="apagar(this);"
		                			 	 onkeypress="return soloEnteros(this,event);"/>
		
							<h:outputText id="outNumero" value="Numero: " styleClass="texto"/>
			                <h:inputText id="inpNumero" value="#{AdminChequeBean.cheque.numero}" 
		                			 	 size="8" maxlength="8" styleClass="bordeceldainferior" style="width: 70px"
		                			 	 onfocus="encender(this);" onblur="apagar(this);"
		                			 	 onkeypress="return soloEnteros(this,event);"/>
		
							<h:outputText id="outDV2" value="DV: " styleClass="texto"/>
			                <h:inputText id="inpDV2" value="#{AdminChequeBean.cheque.DV2}" 
		                			 	 size="1" maxlength="1" styleClass="bordeceldainferior" style="width: 15px"
		                			 	 onfocus="encender(this);" onblur="apagar(this);"
		                			 	 onkeypress="return soloEnteros(this,event);"/>
							<h:outputText value="    " />
							<h:outputText value="    " />
							<h:outputText value="    " />
							<h:outputText value="    " />
		
							<h:outputText id="outNroCuenta" value="Nro Cuenta: " styleClass="texto"/>
			                <h:inputText id="inpNroCuenta" value="#{AdminChequeBean.cheque.bancoPropio.numeroCuenta}" 
		                			 	 size="11" maxlength="11" styleClass="bordeceldainferior" style="width: 90px"
		                			 	 onfocus="encender(this);" onblur="apagar(this);"
		                			 	 onkeypress="return soloEnteros(this,event);"/>
		
							<h:outputText id="outDV3" value="DV: " styleClass="texto"/>
			                <h:inputText id="inpDV3" value="#{AdminChequeBean.cheque.DV3}" 
		                			 	 size="1" maxlength="1" styleClass="bordeceldainferior" style="width: 15px"
		                			 	 onfocus="encender(this);" onblur="apagar(this);"
		                			 	 onkeypress="return soloEnteros(this,event);"/>
							<h:outputText value="    " />
							<h:outputText value="    " />
							<h:outputText value="    " />
							<h:outputText value="    " />
						</h:panelGrid>
					</s:fieldset--%>