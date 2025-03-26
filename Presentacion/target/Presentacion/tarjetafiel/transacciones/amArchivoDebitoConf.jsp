<%@include file="/inc/tags.jsp" %>
<jsp:useBean id="ahora" class="java.util.Date" scope="request"/>

<f:view>
<html lang="es">
<head>
	<title><h:outputText value="#{ArchivoDebitoConfBean.tituloLargo}"/></title>
	<s:script language="javascript">
		function recargar() {
			document.getElementById('amArchivoDebitoConfForm').submit();
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

<jsp:include page="/inc/includes.jsp" />

<body class="hold-transition skin-blue sidebar-mini" onbeforeunload="ShowWait('amArchivoDebitoConfForm');" onload="if('${ArchivoDebitoConfBean.popup.mostrar}'=='true') {viewDialog.show();} else {window.scrollTo(0,${ScrollBean.hiddenScrollY});}">

<h:form id="mainMenu" style="display: none">
  <jsp:include page="/inc/navigation_test.jsp" />
  <x:commandLink id="cerrarSesion" action="#{LoginBean.salir}"/>
</h:form>

<jsp:include page="/inc/header.jsp" />

<!-- Content Header (Page header) -->
<section class="content-header">
  <h1>
    ${ArchivoDebitoConfBean.tituloCorto}
    <small>${ArchivoDebitoConfBean.tituloLargo}</small>
  </h1>
</section>

<section class="content">

<div class="box box-default">
<div class="box-header"><h3></h3></div>

<center>
	<secure:check/>

	<h:form id="amArchivoDebitoConfForm">

	<%-- GRABA EL ESTADO DEL SCROLL(Dibujar solo si el popup no se muestra) --%>
	<h:panelGroup rendered="#{!ArchivoDebitoConfBean.popup.mostrar}">
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
								dialogTitle="#{ArchivoDebitoConfBean.tituloCorto}">
						<h:panelGrid columns="2" width="500">
							<x:graphicImage value="/img/#{ArchivoDebitoConfBean.popup.nombreIcono}" />
							<h:outputText value="#{ArchivoDebitoConfBean.popup.mensaje}" styleClass="texto"/>
						</h:panelGrid>
						<h:panelGrid columns="3" width="500">
							<x:commandButton action="#{ArchivoDebitoConfBean.irANuevoArchivoDebitoConf}" 
								onclick="clickLink('nuevoArchivoDebitoConf');dojo.widget.byId('viewDialog').hide();"
								value="Nuevo" styleClass="btn btn-primary btn-flat" title="Agregar nueva."/>
							<x:commandButton action="#{ArchivoDebitoConfBean.irAModificarArchivoDebitoConf}" 
								onclick="clickLink('modificarArchivoDebitoConf');dojo.widget.byId('viewDialog').hide();"
								value="Modificar" styleClass="btn btn-primary btn-flat" title="Seguir modificando."/>
							<x:commandButton action="#{ArchivoDebitoConfBean.irAListarArchivoDebitoConf}" 
								onclick="clickLink('listarArchivoDebitoConf');dojo.widget.byId('viewDialog').hide();"
								value="Listar" styleClass="btn btn-primary btn-flat" title="Ir al listado."/>
						</h:panelGrid>
					</s:modalDialog>
					<x:commandLink id="nuevoArchivoDebitoConf" action="#{ArchivoDebitoConfBean.irANuevoArchivoDebitoConf}" forceId="true" style="display: block;"/>
					<x:commandLink id="modificarArchivoDebitoConf" action="#{ArchivoDebitoConfBean.irAModificarArchivoDebitoConf}" forceId="true" style="display: block;"/>
					<x:commandLink id="listarArchivoDebitoConf" action="#{ArchivoDebitoConfBean.irAListarArchivoDebitoConf}" forceId="true" style="display: block;"/>

                <h:panelGrid columns="3" align="center" id="prin" width="800px">
                    <s:fieldset id="fieldCabecera" legend="Cabecera del Archivo:">
						<h:panelGrid width="350px;" columns="4" cellspacing="20" columnClasses="standardTable_ColumnLeft,standardTable_ColumnCentered,standardTable_ColumnCentered,standardTable_ColumnCentered" id="panCabecera" align="center">
                             <f:verbatim>&nbsp;</f:verbatim>
                             <h:outputText value="Caracter de Inicio" />
							 <h:outputText value="Caracter de Fin" />
                             <h:outputText value="Long. del campo" />
                             <h:outputText value="Descripción: " />
                             <h:inputText value="#{ArchivoDebitoConfBean.archivoDebitoConf.descripcion}" id="inputDescripcion" style=" width : 68px;"/>	
                             <f:verbatim>&nbsp;</f:verbatim>
							 <f:verbatim>&nbsp;</f:verbatim>
                             
                             <h:outputText value="Código Comercio: " />
                             <h:inputText value="#{ArchivoDebitoConfBean.archivoDebitoConf.codComercio}" onkeypress="return soloEnteros(this,event);" id="inputCodigoComercio" style=" width : 68px;"/>
							 <h:inputText value="#{ArchivoDebitoConfBean.archivoDebitoConf.codComercioHasta}" onkeypress="return soloEnteros(this,event);" id="inputCodComercioHasta" style=" width : 68px;"/>	
                             <h:outputText value="#{ArchivoDebitoConfBean.archivoDebitoConf.longitudCodComercio}"/>
                             
						</h:panelGrid>                    
                    </s:fieldset>
					
				    <s:fieldset id="fieldCuerpo" legend="Cuerpo del Archivo:">
                         <h:panelGrid columns="4" width="400px;" cellspacing="20" columnClasses="standardTable_ColumnLeft,standardTable_ColumnCentered,standardTable_ColumnCentered" id="panCuerpo" align="center">
                             <f:verbatim>&nbsp;</f:verbatim>
                             <h:outputText value="Caracter de Inicio" />						     
							 <h:outputText value="Caracter de Fin" />
                             <h:outputText value="Long. del campo" />							 

                             <h:outputText value="Número Plástico: " />
                             <h:inputText value="#{ArchivoDebitoConfBean.archivoDebitoConf.numeroPlastico}" onkeypress="return soloEnteros(this,event);" id="inputNumeroPlastico" style=" width : 68px;"/>							 
							 <h:inputText value="#{ArchivoDebitoConfBean.archivoDebitoConf.numeroPlasticoHasta}" onkeypress="return soloEnteros(this,event);" id="inputNumeroPlasticoHasta" style=" width : 68px;"/>	
                             <h:outputText value="#{ArchivoDebitoConfBean.archivoDebitoConf.longitudNumeroPlastico}"/>
							                              
                             <%--<h:outputText value="Vencimiento Plástico: " />
                             <h:inputText value="#{ArchivoDebitoConfBean.archivoDebitoConf.vencimientoPlastico}" onkeypress="return soloEnteros(this,event);"  style=" width : 68px;" id="inputVencimientoPlastico"/>	
                             <h:outputText value="#{ArchivoDebitoConfBean.archivoDebitoConf.longitudVencimientoPlastico}"/>
                             <h:outputText value="Pin: " />
                             <h:inputText value="#{ArchivoDebitoConfBean.archivoDebitoConf.pin}"  onkeypress="return soloEnteros(this,event);" id="inputPin"  style=" width : 68px;"/>	
                             <h:outputText value="#{ArchivoDebitoConfBean.archivoDebitoConf.longitudPin}"/>--%>

                             <h:outputText value="Importe: " />
                             <h:inputText value="#{ArchivoDebitoConfBean.archivoDebitoConf.importe}" onkeypress="return soloEnteros(this,event);"  id="inputImporte"  style=" width : 68px;"/>							 
							 <h:inputText value="#{ArchivoDebitoConfBean.archivoDebitoConf.importeHasta}" onkeypress="return soloEnteros(this,event);"  id="inputImporteHasta"  style=" width : 68px;"/>
                             <h:outputText value="#{ArchivoDebitoConfBean.archivoDebitoConf.longitudImporte}"/>
                             <h:outputText value="Importe Decimales: " />
							 <h:inputText value="#{ArchivoDebitoConfBean.archivoDebitoConf.importeDecimal}" onkeypress="return soloEnteros(this,event);"  id="inputImporteDecimales"  style=" width : 68px;"/>	
							 <h:inputText value="#{ArchivoDebitoConfBean.archivoDebitoConf.importeDecimalHasta}" onkeypress="return soloEnteros(this,event);"  id="inputImporteDecimalesHasta"  style=" width : 68px;"/>							 
                             <h:outputText value="#{ArchivoDebitoConfBean.archivoDebitoConf.longitudImporteDecimales}"/>
                             <h:outputText value="Dia Presentación: " />
                             <h:inputText value="#{ArchivoDebitoConfBean.archivoDebitoConf.diaPresentacion}"  onkeypress="return soloEnteros(this,event);" id="inputDiaPresentacion"  style=" width : 68px;"/>							 
							 <h:inputText value="#{ArchivoDebitoConfBean.archivoDebitoConf.diaPresentacionHasta}"  onkeypress="return soloEnteros(this,event);" id="inputDiaPresentacionHasta"  style=" width : 68px;"/>
                             <h:outputText value="#{ArchivoDebitoConfBean.archivoDebitoConf.longitudDiaPresentacion}"/>
                             <h:outputText value="Mes Presentación: " />
                             <h:inputText value="#{ArchivoDebitoConfBean.archivoDebitoConf.mesPresentacion}"  onkeypress="return soloEnteros(this,event);" id="inputMesPresentacion"  style=" width : 68px;"/>							 
							 <h:inputText value="#{ArchivoDebitoConfBean.archivoDebitoConf.mesPresentacionHasta}"  onkeypress="return soloEnteros(this,event);" id="inputMesPresentacionHasta"  style=" width : 68px;"/>
                             <h:outputText value="#{ArchivoDebitoConfBean.archivoDebitoConf.longitudMesPresentacion}"/>
                             <h:outputText value="Año Presentación: " />
                             <h:inputText value="#{ArchivoDebitoConfBean.archivoDebitoConf.anioPresentacion}"  onkeypress="return soloEnteros(this,event);" id="inputAnioPresentacion"  style=" width : 68px;"/>							 
							 <h:inputText value="#{ArchivoDebitoConfBean.archivoDebitoConf.anioPresentacionHasta}"  onkeypress="return soloEnteros(this,event);" id="inputAnioPresentacionHasta"  style=" width : 68px;"/>
                             <h:outputText value="#{ArchivoDebitoConfBean.archivoDebitoConf.longitudAnioPresentacion}"/>
                             <h:outputText value="Lista Precio: " />
                             <h:inputText value="#{ArchivoDebitoConfBean.archivoDebitoConf.listaPrecio}" onkeypress="return soloEnteros(this,event);"  id="inputListaPrecio" style=" width : 68px;"/>							 
							 <h:outputText value=""/>	
                             <h:outputText value="#{ArchivoDebitoConfBean.archivoDebitoConf.longitudListaPrecio}"/>
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
						<x:commandButton id="buttonGrabar" value="Guardar" action="#{ArchivoDebitoConfBean.grabar}" styleClass="btn btn-primary btn-flat"/>
						<x:commandButton id="buttonCancelar" value="Cancelar" action="#{ArchivoDebitoConfBean.cancelar}" styleClass="btn btn-primary btn-flat" />
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
    document.getElementById("sideMenu").innerHTML += generateMenu(obj,"mainMenu__idJsp2_menu:A]\#{ArchivoDebitoConfBean.inicializar}") + `</li>`;
}

</script>
<%@include file="/inc/scripts.jsp" %>
</body>
</html>
</f:view>
