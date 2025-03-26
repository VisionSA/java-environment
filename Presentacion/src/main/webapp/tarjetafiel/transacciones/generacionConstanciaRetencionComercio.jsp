<%@include file="/inc/tags.jsp"%>
<jsp:useBean id="ahora" class="java.util.Date" scope="request" />

<f:view>
<html lang="es">
	<head>
	<title><h:outputText
		value="Tarjeta Fiel - Generacion Constancia Retenciones" /></title>
<s:script language="javascript">
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
<jsp:include page="/inc/includes.jsp"/>
	<body class="hold-transition skin-blue sidebar-mini" onbeforeunload="ShowWait('generaConstanciaRetForm');" 
			onload="if('${ConstanciaRetComerciosBean.popup.mostrar}'=='true') {viewDialog.show();} else {window.scrollTo(0,${ScrollBean.hiddenScrollY});}">
<h:form id="mainMenu" style="display: none">
	<jsp:include page="/inc/navigation_test.jsp" />
	<x:commandLink id="cerrarSesion" action="#{LoginBean.salir}"/>
</h:form>

<jsp:include page="/inc/header.jsp" />

<!-- Content Header (Page header) -->
<section class="content-header">
  <h1>
    ${ConstanciaRetComerciosBean.tituloCorto}
    <small>${ConstanciaRetComerciosBean.tituloLargo}</small>
  </h1>
</section>

<section class="content">
	<div class="box box-default">
		<div class="box-header"><h3></h3>
		</div>


	<center>
	<h:form id="generaConstanciaRetForm">
	<%-- GRABA EL ESTADO DEL SCROLL(Dibujar solo si el popup no se muestra) --%>
	<h:panelGroup rendered="#{!ConstanciaRetComerciosBean.popup.mostrar}">
		<%@include file="/inc/scroll.inc" %>
	</h:panelGroup>

		<x:panelLayout id="page" layout="#{globalOptions.pageLayout}"
			styleClass="pageLayout" headerClass="pageHeader"
			navigationClass="pageNavigation" bodyClass="pageBody"
			footerClass="pageFooter">

		
			<f:facet name="body">
				<h:panelGroup id="body">
				
					<h:panelGrid columns="1" align="center">
						<%-- INCLUDE PARA LOS ERRORES --%>
						<h:panelGroup id="errores">
							<jsp:include page="/inc/error.jsp" />
						</h:panelGroup>

					<%-- POPUP PARA LOS MENSAJES DE EXITO Y FRACASO --%>
					<s:modalDialog dialogId="viewDialog"
								dialogVar="viewDialog"
								styleClass="viewDialog"
								dialogTitle="#{ConstanciaRetComerciosBean.tituloCorto}">
						<h:panelGrid columns="2" width="500">
							<x:graphicImage value="/img/#{ConstanciaRetComerciosBean.popup.nombreIcono}" />
							<h:outputText value="#{ConstanciaRetComerciosBean.popup.mensaje}" styleClass="texto"/>
						</h:panelGrid>
						<h:panelGrid columns="3" width="500">
							<x:commandButton action="#{ConstanciaRetComerciosBean.irAContinuar}" 
								onclick="clickLink('continuar');dojo.widget.byId('viewDialog').hide();"
								value="Continuar" styleClass="btn btn-primary btn-flat" title="Conuar generando."/>
							<x:commandButton action="#{ConstanciaRetComerciosBean.irAImprimirTodos}" 
								onclick="clickLink('imprimir');dojo.widget.byId('viewDialog').hide();"
								value="Imprimir Todos" styleClass="btn btn-primary btn-flat" title="Imprimir todos certificados generados."/>
							<x:commandButton action="#{ConstanciaRetComerciosBean.irASalir}" 
								onclick="clickLink('salir');dojo.widget.byId('viewDialog').hide();"
								value="Salir" styleClass="btn btn-primary btn-flat" title="Ir al inicio."/>
						</h:panelGrid>
					</s:modalDialog>
					<x:commandLink id="continuar" action="#{ConstanciaRetComerciosBean.irAContinuar}" forceId="true" style="display: block;"/>
					<x:commandLink id="imprimir" action="#{ConstanciaRetComerciosBean.irAImprimirTodos}" forceId="true" style="display: block;"/>
					<x:commandLink id="salir" action="#{ConstanciaRetComerciosBean.irASalir}" forceId="true" style="display: block;"/>


						<s:layoutingTitlePane id="filtroFecha"
							label=" Filtro periodo"
							containerNodeClass="contentTitlePane"
							labelNodeClass="labelTitlePane">
							<h:panelGrid columns="8" id="panelDivisorio" align="center">
								<h:outputText value="Mes:" styleClass="texto" />
								<f:verbatim>&nbsp;&nbsp;</f:verbatim>
								<h:selectOneMenu id="lstmes"  
									value="#{ConstanciaRetComerciosBean.idMesSeleccionado}"
									styleClass="lista" immediate="true" onfocus="encender(this);"
									onblur="apagar(this);" style="width: 200px"
									binding="#{ConstanciaRetComerciosBean.mes}"
									valueChangeListener="#{ConstanciaRetComerciosBean.cambiarLabelBoton}"
									onchange="submit();">
									<f:selectItems id="itemTipo" value="#{ConstanciaRetComerciosBean.mesItems}" />
								</h:selectOneMenu>
								<f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim>
                                <h:outputText value="Año:" styleClass="texto" />
                                <f:verbatim>&nbsp;&nbsp;</f:verbatim>
                                <h:selectOneMenu id="lstAnio"  
									value="#{ConstanciaRetComerciosBean.idAnioSeleccionado}"
									styleClass="lista" immediate="true" onfocus="encender(this);"
									onblur="apagar(this);" style="width: 200px"
									binding="#{ConstanciaRetComerciosBean.anioItem}"
									valueChangeListener="#{ConstanciaRetComerciosBean.cambiarLabelBoton}"
									onchange="submit();">
									<f:selectItems id="itemAnio"
										value="#{ConstanciaRetComerciosBean.anioItems}" />
								</h:selectOneMenu>
                                 
						 		<h:panelGrid columns="2" id="btnBuscarFiltro">
									<f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim>
                                    <x:commandButton id="buttonGenerar" value="#{ConstanciaRetComerciosBean.labelButton}" action="#{ConstanciaRetComerciosBean.ejecutarAccion}" styleClass="btn btn-primary btn-flat"/>
                      		    </h:panelGrid>
                  		</h:panelGrid>
						</s:layoutingTitlePane>
                              <h:outputText value="No se generaron retenciones para los peridos seleccionados" id="outMsgAdvertencia"
					    		styleClass="texto" style="color:green" rendered="#{ConstanciaRetComerciosBean.listaVacia}"/>
					
					</h:panelGrid>
				</h:panelGroup>
			</f:facet>
		</x:panelLayout>
	</h:form></center>
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
    document.getElementById("sideMenu").innerHTML += generateMenu(obj,"mainMenu__idJsp2_menu:A]\#{ConstanciaRetComerciosBean.inicializar}") + `</li>`;
}

</script>

<%@include file="/inc/scripts.jsp" %>
	</body>
	</html>
</f:view>
