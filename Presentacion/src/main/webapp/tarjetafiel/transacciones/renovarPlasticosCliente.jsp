<%@include file="/inc/tags.jsp" %>
<%@ page pageEncoding="UTF-8" %>
<%-- <jsp:useBean id="ahora" class="java.util.Date" scope="request"/> --%>

<f:view>
<html lang="es">
<head>
	<meta http-equiv="content-type" content="text/html;charset=UTF-8" />
	<title><x:outputText value="Tarjeta Fiel - Renovación de Plasticos Cliente"/></title>
	<s:script language="javascript">
		function validar(input) {
			valor = input.value;
			if (isNaN(valor)){
				valor = valor.substring(0, valor.length -1);
			}
			input.value = valor;
		}
		
		function informarLotes(){
			plasticos = document.getElementById('RenovacionPlasticos:cTotalolHid').value;
	 		lotes = document.getElementById('RenovacionPlasticos:txtTamanoLote').value;
	 		if (plasticos == null){
	 			plasticos = lotes;
	 		}
	 		
	 		if (lotes == null || lotes == 0){
	 			if (plasticos != 0){
					lotes = plasticos;
				}else{
					lotes = 1;
				}
	 		}
			
			totalLotes = Math.ceil(plasticos / lotes);
			
			return confirm('Se generaran ' + totalLotes + ' lotes. Desea continuar?');
		}
	</s:script>
</head>

<jsp:include page="/inc/includes.jsp" />

<body class="hold-transition skin-blue sidebar-mini" onbeforeunload="ShowWait('RenovacionPlasticos');" 
		onload="if('${RenovacionPlasticosBean.popup.mostrar}'=='true') {viewDialog.show();} else {window.scrollTo(0,${ScrollBean.hiddenScrollY});}">
<h:form id="mainMenu" style="display: none">
  <jsp:include page="/inc/navigation_test.jsp" />
  <x:commandLink id="cerrarSesion" action="#{LoginBean.salir}"/>
</h:form>

<jsp:include page="/inc/header.jsp" />

<!-- Content Header (Page header) -->
<section class="content-header">
  <h1>
    ${RenovacionPlasticosBean.tituloCorto}
    <small>${RenovacionPlasticosBean.tituloLargo}</small>
  </h1>
</section>

<section class="content">

<div class="box box-default">
<div class="box-header"><h3></h3></div>

<center>

	<h:form id="RenovacionPlasticos">
		<%-- GRABA EL ESTADO DEL SCROLL(Dibujar solo si el popup no se muestra) --%>
		<h:panelGroup rendered="#{!RenovacionPlasticosBean.popup.mostrar}">
		<%@include file="/inc/scroll.inc" %>
		</h:panelGroup>
		
		<x:panelLayout id="page" layout="#{globalOptions.pageLayout}"
			styleClass="pageLayout"
			headerClass="pageHeader"
			navigationClass="pageNavigation"
			bodyClass="pageBody"
			footerClass="pageFooter" >
			
			<f:facet name="body">
				<h:panelGroup id="body">
					<x:panelGrid columns="1" align="center" id="PanelPricipal">
						<%-- INCLUDE PARA LOS ERRORES --%>
						<h:panelGroup id="errores">
							<jsp:include page="/inc/error.jsp" />
						</h:panelGroup>
						
						<%-- POPUP PARA LOS MENSAJES DE EXITO Y FRACASO --%>
						<s:modalDialog dialogId="viewDialog"
									dialogVar="viewDialog"
									styleClass="viewDialog"
									dialogTitle="#{RenovacionPlasticosBean.tituloCorto}">
							<x:panelGrid columns="2" width="500">
								<x:graphicImage value="/img/#{RenovacionPlasticosBean.popup.nombreIcono}" />
								<x:outputText value="#{RenovacionPlasticosBean.popup.mensaje}" styleClass="texto"/>
							</x:panelGrid>
							<x:panelGrid columns="3" width="500">
								<x:commandButton action="#{RenovacionPlasticosBean.irAContinuar}" 
									onclick="clickLink('continuar');dojo.widget.byId('viewDialog').hide();"
									value="Continuar" styleClass="botones" title="Continuar en la pantalla."/>
								<f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim>
								<%--<x:commandButton action="#{RenovacionPlasticosBean.irASalir}" 
									onclick="clickLink('salir');dojo.widget.byId('viewDialog').hide();"
									value="Salir" styleClass="botones" title="Salir."/>--%>						
							</x:panelGrid>
						</s:modalDialog>
						<x:commandLink id="continuar" action="#{RenovacionPlasticosBean.irAContinuar}" forceId="true" style="display: block;"/>					
						<x:panelGrid columns="2">
							<h:graphicImage alt="El cliente posee domicilio incorrecto" width="16" height="16" url="/img/warning_16x16.gif"/>
							<x:outputText value="Atencion: No se regeneran los plasticos para los clientes titulares que posea domicilio incorrecto" style="color:red; font-size:13" />
						</x:panelGrid>

						<f:verbatim>&nbsp;</f:verbatim>
						
						<x:panelGrid id="resumen" columns="3" width="910" align="center">
							<s:layoutingTitlePane id="cTit" label="Cantidad Titulares" containerNodeClass="contentTitlePaneHalf" labelNodeClass="labelTitlePaneHalf" >
								<x:panelGrid id="titulares" columns="1" >
									<x:outputText id="cATitol" value="#{RenovacionPlasticosBean.cantidadTitulares} (#{RenovacionPlasticosBean.cantidadErroneos} con datos erroneos)" styleClass="textoACenter"/>
								</x:panelGrid>							
							</s:layoutingTitlePane>
							
							<s:layoutingTitlePane id="tAdic" label="Cantidad Adicionales" containerNodeClass="contentTitlePaneHalf" labelNodeClass="labelTitlePaneHalf" >
								<x:panelGrid id="Adicionales" columns="1" >
									<x:outputText id="cAdicol" value="#{RenovacionPlasticosBean.cantidadAdicionales}" styleClass="textoACenter"/>
								</x:panelGrid>
							</s:layoutingTitlePane>
							
							<s:layoutingTitlePane id="tTotal" label="Total Plasticos" containerNodeClass="contentTitlePaneHalf" labelNodeClass="labelTitlePaneHalf" >
								<x:panelGrid id="Total" columns="2" >
									<h:outputText id="cTotalol"  value="#{RenovacionPlasticosBean.cantidadTotal}" styleClass="textoACenter"/>
									<x:inputHidden id="cTotalolHid" value="#{RenovacionPlasticosBean.cantidadTotal}"></x:inputHidden>
								</x:panelGrid>
							</s:layoutingTitlePane>
						</x:panelGrid>

						<f:verbatim>&nbsp;</f:verbatim>
						
						<x:panelGrid id="plasticos" columns="1" width="910" align="center">
							<s:layoutingTitlePane id="env" label="Plásticos a renovar" 
							containerNodeClass="contentTitlePane" labelNodeClass="labelTitlePane" >
								<x:panelGrid id="plasticosPG" columns="1" align="center">
									<x:dataTable value="#{RenovacionPlasticosBean.plasticosRenovar}" id="listado"
												 styleClass="table-bordered table-striped"
	                    		       			 headerClass="standardTable_Header"
	                           					 footerClass="standardTable_Header"
			                           			 rowClasses="standardTable_Row1,standardTable_Row2"
			                           			 sortable="true"
	        		                   			 columnClasses="tdB,tdA,tdB,tdA,tdB"							             
									             var="plastico" style=" width : 890px;"
									             rows="25" >
				                        <x:column>
		    		                        <f:facet name="header">
		            		                    <x:outputText value="Numero" styleClass="texto" />
		                    		        </f:facet>
			                                <x:outputText value="#{plastico.numero}" styleClass="numero" />
		    		                    </x:column>
				                       	<x:column >
		    		                    	 <h:graphicImage alt="El cliente posee domicilio incorrecto" width="16" height="16" url="/img/warning_16x16.gif"
		            		                    	rendered="#{plastico.clienteTransaccion.domicilioValido==0 && plastico.clienteTransaccion.idTitular==null?true:false}" title="El cliente posee domicilio incorrecto"/>
		    		                        <f:facet name="header">
		            		                    <x:outputText value="Cliente" styleClass="texto" />
		                    		        </f:facet>
				                            <x:outputText value="#{plastico.clienteTransaccion.individuo.apellido} , #{plastico.clienteTransaccion.individuo.nombres}" styleClass="texto" />  
		    		                    </x:column>
		    		                    <x:column >
		    		                        <f:facet name="header">
		            		                    <x:outputText value="Cuenta" styleClass="texto" />
		                    		        </f:facet>
				                            <x:outputText value="#{plastico.clienteTransaccion.idCliente}" styleClass="numero" />  
		    		                    </x:column>
		    		                    <x:column >
		    		                        <f:facet name="header">
		            		                   
		            		                    <x:outputText value="Titular/Adicional" styleClass="texto" />
		                    		        </f:facet>
				                            <x:outputText value="#{plastico.clienteTransaccion.idTitular==null?'Titular':'Adicional'}" styleClass="textoACenter" />  
		    		                    </x:column>
		    		                    <x:column >
		    		                        <f:facet name="header">
		            		                    <x:outputText value="Cuenta Titular" styleClass="texto" />
		                    		        </f:facet>
				                            <x:outputText value="#{plastico.clienteTransaccion.idTitular==null?'-':plastico.clienteTransaccion.idTitular}" styleClass="textoACenter" />  
		    		                    </x:column>
									</x:dataTable>
									<f:verbatim>
										<div align="center" style="width: 890px;">
										</f:verbatim>
										<%@include file="/inc/paginator.jsp" %>
										<f:verbatim>
										</div>
									</f:verbatim>
								</x:panelGrid>
							</s:layoutingTitlePane>
						</x:panelGrid>
						<f:verbatim>
							<div align="right" style="width: 100%">
						</f:verbatim>

						<f:verbatim>&nbsp;</f:verbatim>
						
						<x:panelGrid id="maximoLote" columns="5" align="right">
							<x:outputText value="Ingrese la cantidad maxima de plasticos por lote: "></x:outputText>						
							<f:verbatim>&nbsp;&nbsp;</f:verbatim>		
							<h:inputText id="txtTamanoLote" value="#{RenovacionPlasticosBean.tamanoLote}"
								styleClass="bordeceldainferior" maxlength="4" size="4" style="width: 35px"
								onkeyup="validar(this);" />
							<f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim>		
							<x:commandButton id="btnRenovarPlasticos" styleClass="btn btn-primary btn-flat"
		       				 value="Renovar Plasticos" onclick="javascript:return informarLotes();"
		       				 action="#{RenovacionPlasticosBean.renovarAction}" 
		       				 title="Arma el lote de plasticos a renovar"/>
		       			</x:panelGrid>	 
				</x:panelGrid>
			</h:panelGroup>
		</f:facet>
		</x:panelLayout>
	</h:form>
</center>
<div class="box-header"></div>
</div>
</section> 

</div>
<!-- ./Main content -->

<%@include file="/inc/footer.jsp" %>


<script type="text/javascript">

document.getElementById("sideMenu").innerHTML = `<li class="header">MENU</li>`;

for(var i = 1; i < mainMenu__idJsp2_menu.length-1; i++) {
    var obj = mainMenu__idJsp2_menu[i];
    document.getElementById("sideMenu").innerHTML += generateMenu(obj,"mainMenu__idJsp2_menu:A]\#{RenovacionPlasticosBean.inicializar}") + `</li>`;
}

</script>
<%@include file="/inc/scripts.jsp" %>
</body>
</html>
</f:view>
