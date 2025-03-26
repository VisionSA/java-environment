<%@include file="/inc/tags.jsp" %>

<f:view >
<html >
<head>
    <title><h:outputText value="Tarjeta Fiel - Detalle de Asientos"/></title>
  	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/basic.css" />
  	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/popup.css" />    
  	<s:script language="javascript">
		function recargar() {
			document.getElementById('asientosForm').submit();
		}
		function popup(pagina,popW,popH) {
			var w = 0, h = 0;
		   	w = screen.width;
		   	h = screen.height;

			var leftPos = (w-popW)/2, topPos = (h-popH)/2;
		    popupWindow=open(pagina,'','resizable=no,autohide=no,scrollbars=yes,width='+popW+',height='+popH+',top='+topPos+',left='+leftPos);
		    
		    if (popupWindow.opener == null){popupWindow.opener = self;}
		    
		};		
	</s:script>
</head>
<%@include file="/inc/head.inc"%>

<body onbeforeunload="ShowWait('detalleAsientoForm');" onload="balancear(); balancearHaber(); habilitarCajas();">
   <script languaje="javascript">
         var listaModificados = new Array();
         var numero = 0;
         var palabra;
         var suma = 0;
         var sumaHaber = 0;
  		 var cadenaIni = "detalleAsientoForm:listadoLot:";
  		 var cadenaFin = ":deb";
  		 var cadenaFinHaber = ":hab";
  		 
  		 
         function agregarAModificar(a)
  		 {
  		 	yaExiste=false;
  		 	for (i=0; i<listaModificados.length;i++) {
 		 	    if (listaModificados[i]==a) {
 		 	       yaExiste=true;  
 		 	    }
 		 	}
 		 	if (!yaExiste) {
 		 		listaModificados[numero]=a;
 		 		numero++;
 		 		if (numero>1) {
 		 		    document.getElementById('pp').value = document.getElementById('pp').value + ", " + a;
 		 		} else {
 		 		    document.getElementById('pp').value = a;
 		 		}
 		 	}
  		 }
  		  function balancear() {
  		    suma = 0;
  		 	for (i=0; document.getElementById(cadenaIni + i + cadenaFin)!=null;i++) {
 		 	    if (document.getElementById(cadenaIni + i + cadenaFin).value!="") {
 		 	    	suma = suma + parseFloat(document.getElementById(cadenaIni + i + cadenaFin).value);
 		 	    }    
 		 	}
  		    document.getElementById('detalleAsientoForm:totDebeLote').value = parseFloat(suma);
  		    cambiarColor();
  		 }
  		 function balancearHaber() {
  		    sumaHaber = 0;
  		 	for (i=0; document.getElementById(cadenaIni + i + cadenaFinHaber)!=null;i++) {
 		 	    if (document.getElementById(cadenaIni + i + cadenaFinHaber).value!="") {
 		 	    	sumaHaber = sumaHaber + parseFloat(document.getElementById(cadenaIni + i + cadenaFinHaber).value);
 		 	    }    
 		 	}
  		    document.getElementById('detalleAsientoForm:totHaberLote').value = parseFloat(sumaHaber);
  		    cambiarColor();
  		 }
  		 function cambiarColor() {
  		     if (document.getElementById('detalleAsientoForm:totDebeLote').value==document.getElementById('detalleAsientoForm:totHaberLote').value) {
				 document.getElementById('detalleAsientoForm:balanceoLote').bgColor = document.getElementById('detalleAsientoForm:botoneraLote').bgColor;
  		     } else {
  		         document.getElementById('detalleAsientoForm:balanceoLote').bgColor = "red";
  		     }
  		 }
  		 function habilitarCajas() {
  		 	for (i=0; document.getElementById(cadenaIni + i + cadenaFinHaber)!=null;i++) {
 		 	    bloquearCaja(document.getElementById(cadenaIni + i + cadenaFinHaber)); 
 		 	}
 		 	for (i=0; document.getElementById(cadenaIni + i + cadenaFin)!=null;i++) {
 		 	    bloquearCaja(document.getElementById(cadenaIni + i + cadenaFin)); 
 		 	}
  		 }
  		 function bloquearCaja(InputText) {
  		      if (InputText.value!=""&&InputText.value!=null) { 
  		      		valor = InputText.id.substring(InputText.id.length-3, InputText.id.length);
  		      		if (valor == "hab") {
  		      		    document.getElementById(InputText.id.substring(0,InputText.id.length-3) + "deb").disabled = true;
  		      		} 
  		      		if (valor == "deb") {
  		      		    document.getElementById(InputText.id.substring(0,InputText.id.length-3) + "hab").disabled = true;
  		      		} 
  		      } else {
  		            valor = InputText.id.substring(InputText.id.length-3, InputText.id.length);
  		      		if (valor == "hab") {
  		      		    document.getElementById(InputText.id.substring(0,InputText.id.length-3) + "deb").disabled = false;
  		      		} 
  		      		if (valor == "deb") {
  		      		    document.getElementById(InputText.id.substring(0,InputText.id.length-3) + "hab").disabled = false;
  		      		}
  		      }
  		 
  		 }
  		 		 //Permite que se ingrese un número decimal de decimales definidos en un Input Text
				function soloDecimalesPrecisos(InputText, evt, cantDeci) {
					var charCode = (evt.which) ? evt.which : event.keyCode;
					var strValue = '';
					var canti = parseInt(cantDeci);
				 	var selectionStart = getSelectionStart(InputText);
				  	var selectionEnd = getSelectionEnd(InputText);
					strValue = InputText.value.substring(0, selectionStart) + String.fromCharCode(charCode) + InputText.value.substring(selectionEnd);
					if (charCode == 13) {
					    alert("apretaste enter!");
					    otorgarFoco();
						return true;
					}
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
						if (i == 0 && oneChar == "-") {
							continue;
						}
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
<h:form id="detalleAsientoForm" >
	
	
	<x:panelTabbedPane bgcolor="#dcdcdc" align="center">
	<h:panelGroup id="detalleAsi" rendered="#{!AsientosBean.enLote}">
		<h:outputText value="Detalles del Asiento" style="FONT-SIZE: large;" styleClass="texto"/>
		<h:panelGroup id="listadoPanel">
			<f:verbatim>&nbsp;</f:verbatim>
			<f:verbatim>&nbsp;</f:verbatim>
                    <h:panelGrid id="detalleAsientos" columns="4">
                    	<h:outputText value="Número de Asiento:" styleClass="text"/>
                    	<h:outputText value="#{AsientosBean.asientoDetallado.idAsiento}" styleClass="textoblue"/>
                   		<h:outputText value="Fecha: " styleClass="text"/>
                   		<h:outputText value="#{AsientosBean.asientoDetallado.fechaCarga}" styleClass="textoblue"/>
                   		<h:outputText value="TipoAsiento: " styleClass="text"/>
                    	<h:outputText value="#{AsientosBean.asientoDetallado.idTipoAsiento}" styleClass="textoblue"/>
                   		<h:outputText value="Concepto: " styleClass="text"/>
                   		<h:outputText value="#{AsientosBean.asientoDetallado.concepto}" styleClass="textoblue"/>
                    </h:panelGrid>
			
				    <h:panelGrid id="panelDeLosDetalles" rendered="#{!AsientosBean.hayAsientosDetalles}">
                    <x:dataTable id="listado"
                            styleClass="standardTable"
                            headerClass="dataTable_Header"
                            footerClass="standardTable_Header"
                            rowClasses="standardTable_Row1,standardTable_Row2"
                            columnClasses="dataTable_columns,standardTable_ColumnCentered,standardTable_Column"
                            sortable="true"
                            var="obj" 
                            value="#{AsientosBean.asientosDetalles}"
                            preserveDataModel="false"
                            rows="100" style=" width : 859px; height : 52px;">
                   
                        <x:column>
                            <f:facet name="header">
                                <h:outputText value="Codigo" />
                            </f:facet>
                            <h:outputText value="#{obj.asientoDetalle.numeroImputa}" />
                        </x:column>

                        <x:column>
                            <f:facet name="header">
                                <h:outputText value="Titulo" />
                            </f:facet>
                            <h:outputText value="#{obj.denominacion}" />
                        </x:column>
                        
                        <x:column id="columnaDebe">
                            <f:facet name="header">
                                <h:outputText value="Debe" />
                            </f:facet>
                            <h:outputText value="#{obj.debe}" />
                        </x:column>
                        
                        <h:column id="columnaHaber" >
                            <f:facet name="header">
                                <h:outputText value="Haber" />
                            </f:facet>
                            <h:outputText value="#{obj.haber}" />
                        </h:column>     
                        
                        <x:column>
                            <f:facet name="header">
                                <h:outputText value="Leyenda" />
                            </f:facet>
                            <h:outputText value="#{obj.asientoDetalle.leyenda}" />
                        </x:column>
                        
                        <h:column>
                            <f:facet name="header">
                                <h:outputText value="Centro de Costo" />
                            </f:facet>
                            <h:selectOneMenu id="lstTDoc" value="#{obj.idCentroCostoSeleccionado}"
									styleClass="lista" immediate="true" onchange="agregarAModificar('#{obj.idAsienDetal}')" onfocus="encender(this);" onblur="apagar(this);" style="width: 200px">
									<f:selectItems value="#{AsientosBean.listaCentroDeCostos}" id="selectItemIdTipoDoc"/>
							</h:selectOneMenu>
                        </h:column>   
                        
                        <h:column>
							<x:commandLink action="#{AsientosBean.verLibroMayor}" id="libroMayorLink">
							<f:param id="idAsientoALibro" name="idAsientoALibro" value="#{obj.idAsienDetal}"/>
							<x:graphicImage value="/img/icon/OrderView.gif" title="Ver Libro Mayor." border="0" id="botonImagenLibro" />
							</x:commandLink>		
						</h:column>
                        
                        <h:column>
							<x:commandLink action="#{AsientosBean.eliminarDetalleAsiento}" id="eliminarDetalleAsientoLink">
							<f:param id="idAsienDetalElim" name="idAsienDetalElim" value="#{obj.idAsienDetal}" />
							<x:graphicImage value="/img/cat_act.gif" title="Elimina el Detalle de asientoContable." border="0" id="botonImagenDos" />
							</x:commandLink>
						</h:column>
                                            
                	</x:dataTable>

        		<%@include file="/inc/paginator.jsp"%>
        		<h:panelGrid id="balanceoAsiento" columns="4" align="center">
        			<h:outputText value="Total Debe: " styleClass="text"/>
        			<h:inputText id="totDebe" value="#{AsientosBean.balanceAsientoDebe}"  disabled="true"/>
        			<h:outputText value="Total Haber: " styleClass="text"/>
        			<h:inputText id="totHaber" value="#{AsientosBean.balanceAsientoHaber}"  disabled="true"/>
        		</h:panelGrid>
				</h:panelGrid>
			
			
			<h:panelGrid id="botonera" columns="8" width="567" >
				<f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim>
				<f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim>
				<f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim>
				<h:outputText value="Total Debe: " styleClass="text"/>
				
        		<f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim>
				<f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim>
				<f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim>
               	<x:commandButton id="buttonAceptar" value="Grabar Cambios" rendered="#{AsientosBean.enLote}"
               					 action="#{AsientosBean.grabarCambiosAsientosDetalles}" 
               					 styleClass="botones" actionListener="#{AsientosBean.recargarYCerrarPopup}"/>
				<x:commandButton id="buttonCancelar" value="Cancelar" 
				                 action="#{AsientosBean.cancelarDetalles}" 
				                 styleClass="botones" onclick="window.close();"/>
			</h:panelGrid>
			
			
		</h:panelGroup>
	</h:panelGroup>
	
	
	<h:panelGroup id="detalleLot" rendered="#{AsientosBean.enLote}" >
    	<x:inputHidden id="pp" forceId="true" value="#{AsientosBean.listaDeModicadosEnString}"/>
		<h:outputText value="Detalles del Lote" style="FONT-SIZE: large;" styleClass="texto"/>
		
		<h:panelGroup id="listadoPanelLote">
			<f:verbatim>&nbsp;</f:verbatim>
			<f:verbatim>&nbsp;</f:verbatim>
                    <h:panelGrid id="detalleLotes" columns="4">
                    	<h:outputText value="Número de Lote: " styleClass="text"/>
                    	<h:outputText value="#{AsientosBean.loteDetallado.idAsiento}" styleClass="textoblue"/>
                   		<h:outputText value="Fecha: " styleClass="text"/>
                   		<h:outputText value="#{AsientosBean.loteDetallado.fechaCarga}" styleClass="textoblue"/>
                   		<h:outputText value="TipoAsiento: " styleClass="text"/>
                    	<h:outputText value="#{AsientosBean.loteDetallado.idTipoAsiento}" styleClass="textoblue"/>
                   		<h:outputText value="Concepto: " styleClass="text"/>
                   		<h:outputText value="#{AsientosBean.loteDetallado.concepto}" styleClass="textoblue"/>
                    </h:panelGrid>
			
				    <h:panelGrid id="panelDeLosDetallesdelLote" rendered="#{!AsientosBean.hayLotesDetalles}">
                    <x:dataTable id="listadoLot"
                            styleClass="standardTable"
                            headerClass="dataTable_Header"
                            footerClass="standardTable_Header"
                            rowClasses="standardTable_Row1,standardTable_Row2"
                            columnClasses="dataTable_columns,standardTable_ColumnCentered,standardTable_Column"
                            sortable="true" 
                            var="obj" 
                            value="#{AsientosBean.lotesDetalles}"
                            preserveDataModel="false"
                            >
                   
                        <x:column width="10px">
                            <f:facet name="header">
                                <h:outputText value="Código" />
                            </f:facet>
                            <h:outputText value="#{obj.loteDetalle.numeroImputa}" />
                        </x:column>

                        <x:column width="25px">
                            <f:facet name="header">
                                <h:outputText value="Título" />
                            </f:facet>
                            <h:outputText value="#{obj.denominacion}"  />
                        </x:column>
                        
                        <x:column>
                            <f:facet name="header">
                                <h:outputText value="Debe" />
                            </f:facet>
                            <h:inputText value="#{obj.debe}" styleClass="bordeceldainferior" id="deb" maxlength="10" onchange="agregarAModificar('#{obj.idLoteDetal}')" onkeyup="balancear(); bloquearCaja(this);" onkeypress="return soloDecimalesPrecisos(this,event,2);"/>
                        </x:column>
                        
                        <h:column>
                            <f:facet name="header">
                                <h:outputText value="Haber" />
                            </f:facet>
                            <h:inputText value="#{obj.haber}" id="hab" styleClass="bordeceldainferior" onchange="agregarAModificar('#{obj.idLoteDetal}')" maxlength="10" onkeyup="balancearHaber(); bloquearCaja(this);"  onkeypress="return soloDecimalesPrecisos(this,event,2);" />
                        </h:column>     
                        
                        <x:column>
                            <f:facet name="header">
                                <h:outputText value="Leyenda" />
                            </f:facet>
                            <h:inputText value="#{obj.loteDetalle.leyenda}" onchange="agregarAModificar('#{obj.idLoteDetal}')"/>
                        </x:column>
                        
                        <h:column>
                            <f:facet name="header">
                                <h:outputText value="Centro de Costo" />
                            </f:facet>
                            <h:selectOneMenu id="lstTLot" value="#{obj.idCentroCostoSeleccionado}" onchange="agregarAModificar('#{obj.idLoteDetal}')"
									styleClass="lista" immediate="true" onfocus="encender(this);" onblur="apagar(this);" style="width: 200px">
									<f:selectItems value="#{AsientosBean.listaCentroDeCostos}" id="selectItemIdTipoDoc"/>
							</h:selectOneMenu>
                        </h:column>   
                        
                          
                        
                        <h:column>
							<x:commandLink action="#{AsientosBean.eliminarDetalleLote}" id="eliminarDetalleLoteLink">
							<f:param id="idLoteDetalElim" name="idLoteDetalElim" value="#{obj.idLoteDetal}" />
							<x:graphicImage value="/img/cat_act.gif" title="Elimina el Detalle de loteContable." border="0" id="botonImagenDos" />
							</x:commandLink>
						</h:column>
                                            
                	</x:dataTable>

        		<%@include file="/inc/paginator.jsp" %>
        		<h:panelGrid id="balanceoLote" columns="5" align="center" >
        		    <h:commandButton id="balanceLote" action="#{AsientosBean.calcularBalanceoLote}" rendered="false" value="Calcular balanceo" styleClass="botones" />
        			<h:outputText value="Total Debe: " styleClass="text"/>
        			<h:inputText id="totDebeLote" disabled="true"/>
        			<h:outputText value="Total Haber: " styleClass="text"/>
        			<h:inputText id="totHaberLote" disabled="true"/>
        		</h:panelGrid>
				</h:panelGrid>
			
			
			<h:panelGrid id="botoneraLote" columns="10" width="736" >
				<f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim>
				<f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim>
					<h:outputText value="Codigo :" styleClass="text"/>
        			<h:inputText id="codigo" value="#{AsientosBean.numeroImputaAAgregar}"/>
        		<x:commandButton id="agregaLote" value="Agregar Detalle" action="#{AsientosBean.agregarNuevoDetalle}" styleClass="botones"/>
        		
               	<x:commandButton id="buttonAceptarLote" value="Grabar Cambios" 
               					 action="#{AsientosBean.grabarCambiosLotesDetalles}" 
               					 styleClass="botones" actionListener="#{AsientosBean.recargarYCerrarPopup}">
               					 <f:param id="laLista" name="laLista" value="listaModificados" />
               	</x:commandButton>
				<x:commandButton id="buttonCancelarLote" value="Cancelar" 
				                 action="#{AsientosBean.cancelarDetalles}" 
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