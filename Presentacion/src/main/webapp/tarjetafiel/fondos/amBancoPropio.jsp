<%@include file="/inc/tags.jsp" %>
<jsp:useBean id="ahora" class="java.util.Date" scope="request"/>

<f:view>
<html lang="es">
<head>
	<title><h:outputText value="#{BancoPropioBean.tituloLargo}"/></title>
	<s:script language="javascript">
		function recargar() {
			document.getElementById('amBancoPropioForm').submit();
		}
		
		function setearSucursal(inputText){
		    document.getElementById('amBancoPropioForm:sucCbu').value = inputText.value;
		}
		
		
		function setearSucursalDesdeCbu(inputText){
		    document.getElementById('amBancoPropioForm:nroSuc').value = inputText.value;
		}
		
	    function setearCodBanco(){
	        //document.nombreFormu.nombreSelec.options[document.nombreFormu.nombreSelec.selectedIndex].text;
	        
	        //alert(document.amBancoPropioForm.lstBanco.options[document.amBancoPropioForm.lstBanco.selectedIndex].text);
	        
	       /* var comboValue;
	        var selIndex = document.amBancoPropioForm.lstBanco.selectedIndex;
            comboValue = document.amBancoPropioForm.lstBanco.options[selIndex].text;
            alert(comboValue);*/
             var selIndex= document.getElementById('amBancoPropioForm:lstBanco').selectedIndex;
             var comboValue =document.getElementById('amBancoPropioForm:lstBanco').options[selIndex].text;
             var hasta= comboValue.indexOf("-");
             var codigo=comboValue.substring(0,hasta);             
             document.getElementById('amBancoPropioForm:nroEntidadCbu').value= codigo.replace(/^\s+/g,'').replace(/\s+$/g,'')
            
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

<jsp:include page="/inc/includes.jsp" />

<body class="hold-transition skin-blue sidebar-mini" onbeforeunload="ShowWait('amBancoPropioForm');" onload="if('${BancoPropioBean.popup.mostrar}'=='true') {viewDialog.show();} else {window.scrollTo(0,${ScrollBean.hiddenScrollY});}">

<h:form id="mainMenu" style="display: none">
  <jsp:include page="/inc/navigation_test.jsp" />
  <x:commandLink id="cerrarSesion" action="#{LoginBean.salir}"/>
</h:form>

<jsp:include page="/inc/header.jsp" />

<!-- Content Header (Page header) -->
<section class="content-header">
  <h1>
    ${BancoPropioBean.tituloCorto}
    <small>${BancoPropioBean.tituloLargo}</small>
  </h1>
</section>

<section class="content">

<div class="box box-default">
<div class="box-header"><h3></h3></div>

<center>
	<secure:check/><h:form id="amBancoPropioForm">

	<%-- GRABA EL ESTADO DEL SCROLL(Dibujar solo si el popup no se muestra) --%>
	<h:panelGroup rendered="#{!BancoPropioBean.popup.mostrar}">
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
					<h:panelGrid columns="1" align="center" id="PanelPricipal">
					<%-- INCLUDE PARA LOS ERRORES --%>
					<h:panelGroup id="errores">
						<jsp:include page="/inc/error.jsp" />
					</h:panelGroup>

					<%-- POPUP PARA LOS MENSAJES DE EXITO Y FRACASO --%>
					<s:modalDialog dialogId="viewDialog"
								dialogVar="viewDialog"
								styleClass="viewDialog"
								dialogTitle="#{BancoPropioBean.tituloCorto}">
						<h:panelGrid columns="2" width="500">
							<x:graphicImage value="/img/#{BancoPropioBean.popup.nombreIcono}" />
							<h:outputText value="#{BancoPropioBean.popup.mensaje}" styleClass="texto"/>
						</h:panelGrid>
						<h:panelGrid columns="3" width="500">
							<x:commandButton action="#{BancoPropioBean.irANuevoCaja}" 
								onclick="clickLink('nuevoBancoPropio');dojo.widget.byId('viewDialog').hide();"
								value="Nuevo" styleClass="botones" title="Agregar nuevo."/>
							<x:commandButton action="#{BancoPropioBean.irAModificarBancoPropio}" 
								onclick="clickLink('modificarBancoPropio');dojo.widget.byId('viewDialog').hide();"
								value="Modificar" styleClass="botones" title="Seguir modificando."/>
							<x:commandButton action="#{BancoPropioBean.irAListarBancoPropio}" 
								onclick="clickLink('listarBancoPropio');dojo.widget.byId('viewDialog').hide();"
								value="Listar" styleClass="botones" title="Ir al listado."/>
						</h:panelGrid>
					</s:modalDialog>
					<x:commandLink id="nuevoBancoPropio" action="#{BancoPropioBean.irANuevoBancoPropio}" forceId="true" style="display: block;"/>
					<x:commandLink id="modificarBancoPropio" action="#{BancoPropioBean.irAModificarBancoPropio}" forceId="true" style="display: block;"/>
					<x:commandLink id="listarBancoPropio" action="#{BancoPropioBean.irAListarBancoPropio}" forceId="true" style="display: block;"/>
						
					<h:panelGrid columns="1" align="center" width="850" id="uno">
						<s:fieldset legend="Cuenta Bancaria ">
						<h:panelGrid id="panelPrincipalUno" columns="2" width="600" align="center">
							
							<h:outputText value="Sucursal Fiel" styleClass="texto"/>
							<h:selectOneMenu id="lstSucursal" value="#{BancoPropioBean.idSucursalSeleccionada}"
								styleClass="lista" immediate="true" onfocus="encender(this);"
								onblur="apagar(this);" style="width: 200px">
								<f:selectItems value="#{BancoPropioBean.sucursalItems}"/>
							</h:selectOneMenu>
							<h:outputText value="Banco" styleClass="texto"/>
							<h:selectOneMenu id="lstBanco" value="#{BancoPropioBean.idBancoSeleccionado}"
								styleClass="lista" immediate="true" onfocus="encender(this);"
								onblur="apagar(this);" onchange="setearCodBanco();" style="width: 200px">
								<f:selectItems value="#{BancoPropioBean.bancoItems}"/>
							</h:selectOneMenu>
							<h:outputText value="Tipo Cuenta" styleClass="texto"/>
							<h:selectOneMenu id="lstTipoCuenta" value="#{BancoPropioBean.idTipoCuentaSeleccionado}"
								styleClass="lista" immediate="true"   binding="#{BancoPropioBean.htmlMenuTip}" onfocus="encender(this);"
								onblur="apagar(this);" style="width: 200px">
								<f:selectItems value="#{BancoPropioBean.tipoCuentaItems}"/>
							</h:selectOneMenu>
						   <h:outputText value="Numero de Cuenta" styleClass="texto"/>
							<h:inputText id="nombreFiltro" value="#{BancoPropioBean.bancoPropio.numeroCuenta}"
								styleClass="bordeceldatext" maxlength="12" size="100"  onkeypress="return soloEnteros(this, event);" 
								style=" width : 92px;" onfocus="encender(this);" onblur="apagar(this);">
							</h:inputText>	
						  <h:outputText value="Numero de Sucursal" styleClass="texto"/>
							<h:inputText id="nroSuc" value="#{BancoPropioBean.bancoPropio.numeroSucursal}"
								styleClass="bordeceldatext" maxlength="4" size="66"  onkeypress="return soloEnteros(this, event);" 
								style=" width : 45px;" onfocus="encender(this);" onblur="apagar(this);">
							     <a4j:support event="onkeyup" reRender="sucCbu">
 							     </a4j:support>  
 							 </h:inputText>
        				
						   	<h:outputText value="Plaza" styleClass="texto"/>
							<h:inputText id="plaza" value="#{BancoPropioBean.bancoPropio.plaza}"
								styleClass="bordeceldatext" maxlength="10" size="100" onkeypress="return soloEnteros(this, event);" 
								style=" width : 75px;" onfocus="encender(this);" onblur="apagar(this);"/>
							<h:outputText value="Moneda" styleClass="texto"/>
							<h:selectOneMenu id="lstMoneda" value="#{BancoPropioBean.idMonedaSeleccionada}"
								styleClass="lista" immediate="true" onfocus="encender(this);"
								onblur="apagar(this);" style="width: 200px">
								<f:selectItems value="#{BancoPropioBean.monedaItems}"/>
							</h:selectOneMenu>
							<h:outputText value="" />
							<s:fieldset legend="CBU">
									<h:panelGrid columns="4" id="bloq1">
									<h:outputText value="1er Bloque:" styleClass="texto"/>
								    <h:inputText id="nroEntidadCbu" value="#{BancoPropioBean.nroEntidadCBU}" style=" width : 33px;" maxlength="3"/>
								     <h:outputText id="sucCbu" value="#{BancoPropioBean.bancoPropio.numeroSucursal}" style=" width : 50px;" />
									 <h:inputText id="dv1" value="#{BancoPropioBean.digitoVer1}"
										styleClass="bordeceldatext" maxlength="1" size="100"
										style=" width : 12px;" onfocus="encender(this);" onblur="apagar(this);"/>	
									</h:panelGrid>	
									<h:panelGrid columns="3">
									<h:outputText value="2do Bloque:" styleClass="texto" id="bloq2"/>
									<h:inputText id="nroCtaCbu" value="#{BancoPropioBean.nroCuentaCBU}"
									 styleClass="bordeceldatext" maxlength="13" size="100" 
										style=" width : 102px;" onfocus="encender(this);" onblur="apagar(this);"/>	
									 <h:inputText id="dv2" value="#{BancoPropioBean.digitoVer2}"
										styleClass="bordeceldatext" maxlength="1" size="100"
										style=" width : 12px;" onfocus="encender(this);" onblur="apagar(this);"/>	
									</h:panelGrid>	
								</s:fieldset>	
							
								
								
								
						    <h:outputText value=""  id="relleno2" />
						    <h:panelGrid columns="2">		
						      <h:outputText id="outhabilitada" value="Habilitada:" styleClass="texto"/>
						      <h:selectBooleanCheckbox id="boolChecHabilitada" value="#{BancoPropioBean.habilitada}" />
						    </h:panelGrid>	
						</h:panelGrid>
						</s:fieldset>	
					</h:panelGrid>
					<f:verbatim>
						<br>
					</f:verbatim>
					<f:verbatim><hr align="center" width="700"></f:verbatim>
					<h:panelGrid columns="10" width="727" id="panelBotonera">
						<f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim>
						<f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim>
						<f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim>
						<f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim>
						<f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim>
						<f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim>
						<f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim>
						<f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim>
						<x:commandButton id="buttonGrabar" value="Guardar" action="#{BancoPropioBean.grabar}" styleClass="botones"/>
						<x:commandButton id="buttonCancelar" value="Cancelar" action="#{BancoPropioBean.cancelar}" styleClass="botones" />
					</h:panelGrid>
					</h:panelGrid>
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
    document.getElementById("sideMenu").innerHTML += generateMenu(obj,"mainMenu__idJsp2_menu:A]\#{BancoPropioBean.inicializar}") + `</li>`;
}

</script>

<%@include file="/inc/scripts.jsp" %>


</body>
</html>
</f:view>
