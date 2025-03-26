<%@include file="/inc/tags.jsp" %>
<jsp:useBean id="ahora" class="java.util.Date" scope="request"/>

<f:view>
<html>
<head>
	<title><h:outputText value="#{CodigoAutorizacionBean.tituloLargo}"/></title>
	<s:script language="javascript">
		function recargar() {
			document.getElementById('amCodigoAutorizacionForm').submit();
		}
	</s:script>
</head>

<jsp:include page="/inc/includes.jsp" />

<body class="hold-transition skin-blue sidebar-mini" onbeforeunload="ShowWait('amCodigoAutorizacionForm');" onload="if('${CodigoAutorizacionBean.popup.mostrar}'=='true') {viewDialog.show();} else {window.scrollTo(0,${ScrollBean.hiddenScrollY});}">


<h:form id="mainMenu" style="display: none">
  <jsp:include page="/inc/navigation_test.jsp" />
  <x:commandLink id="cerrarSesion" action="#{LoginBean.salir}"/>
</h:form>

<jsp:include page="/inc/header.jsp" />

<!-- Content Header (Page header) -->
<section class="content-header">
  <h1>
    ${CodigoAutorizacionBean.tituloCorto}
    <small>${CodigoAutorizacionBean.tituloLargo}</small>
  </h1>
</section>

<section class="content">

<div class="box box-default">
<div class="box-header"><h3></h3></div>

<center>
	<secure:check/>

	<h:form id="amCodigoAutorizacionForm">

	<%-- GRABA EL ESTADO DEL SCROLL(Dibujar solo si el popup no se muestra) --%>
	<h:panelGroup rendered="#{!CodigoAutorizacionBean.popup.mostrar}">
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

					<h:panelGrid columns="1" align="center" id="PanelPricipal" width="900">
						<%-- INCLUDE PARA LOS ERRORES --%>
						<h:panelGroup id="errores">
							<jsp:include page="/inc/error.jsp" />
						</h:panelGroup>

						<%-- POPUP PARA LOS MENSAJES DE EXITO Y FRACASO --%>
						<s:modalDialog dialogId="viewDialog"
									dialogVar="viewDialog"
									styleClass="viewDialog"
									dialogTitle="#{CodigoAutorizacionBean.tituloCorto}">
							<h:panelGrid columns="2" width="500">
								<x:graphicImage value="/img/#{CodigoAutorizacionBean.popup.nombreIcono}" />
								<h:outputText value="#{CodigoAutorizacionBean.popup.mensaje}" styleClass="texto"/>
							</h:panelGrid>
							<h:panelGrid columns="3" width="500">
								<x:commandButton action="#{CodigoAutorizacionBean.irANuevoCodigoAutorizacion}" 
									onclick="clickLink('nuevoCodigoAutorizacion');dojo.widget.byId('viewDialog').hide();"
									value="Nuevo" styleClass="btn btn-primary btn-flat" title="Agregar nueva."/>
								<x:commandButton action="#{CodigoAutorizacionBean.irAModificarCodigoAutorizacion}" 
									onclick="clickLink('modificarCodigoAutorizacion');dojo.widget.byId('viewDialog').hide();"
									value="Modificar" styleClass="btn btn-primary btn-flat" title="Seguir modificando."/>
								<x:commandButton action="#{CodigoAutorizacionBean.irAListarCodigoAutorizacion}" 
									onclick="clickLink('listarCodigoAutorizacion');dojo.widget.byId('viewDialog').hide();"
									value="Listar" styleClass="btn btn-primary btn-flat" title="Ir al listado."/>
							</h:panelGrid>
						</s:modalDialog>
						<x:commandLink id="nuevoCodigoAutorizacion" action="#{CodigoAutorizacionBean.irANuevoCodigoAutorizacion}" forceId="true" style="display: block;"/>
						<x:commandLink id="modificarCodigoAutorizacion" action="#{CodigoAutorizacionBean.irAModificarCodigoAutorizacion}" forceId="true" style="display: block;"/>
						<x:commandLink id="listarCodigoAutorizacion" action="#{CodigoAutorizacionBean.irAListarCodigoAutorizacion}" forceId="true" style="display: block;"/>

						<s:fieldset legend="Código Autorización" id="fieldPrincipalUno">
						<h:panelGrid id="panelPrincipalUno" columns="2" width="600" align="center">

						<f:verbatim>&nbsp;</f:verbatim>
						<f:verbatim>&nbsp;</f:verbatim>
						
							<h:outputText value="Código: " styleClass="texto" id="codigo"/>
							<h:inputText id="codigoFiltro" value="#{CodigoAutorizacionBean.codigoAutorizacion.codigo}" styleClass="bordeceldainferior" 
								maxlength="8" size="8" style="width: 150px" onfocus="encender(this);" onblur="apagar(this);"
								onkeypress="return soloEnteros(this,event);"/>

						<f:verbatim>&nbsp;</f:verbatim>
						<f:verbatim>&nbsp;</f:verbatim>
								
							<h:outputText value="Fecha:" styleClass="texto" id="fecha"/>
							<f:verbatim>
					                <div class="input-group date" style="width: 150px">
					                    <div class="input-group-addon">
					                        <i class="fa fa-calendar"></i>
					                    </div>
					                    <input type="text" class="form-control pull-right" id="fDesde" placeholder="DD/MM/AAAA" autocomplete="off">
					                </div>
							</f:verbatim>

						<f:verbatim>&nbsp;</f:verbatim>
						<f:verbatim>&nbsp;</f:verbatim>

						<h:outputText value="Origen:" styleClass="texto" id="origen"/>
							<h:selectOneMenu id="lstOrigenen" value="#{CodigoAutorizacionBean.idOrigenenSeleccionada}" styleClass="lista" 
								immediate="true" onfocus="encender(this);" onblur="apagar(this);" style="width: 150px">
								<f:selectItems value="#{CodigoAutorizacionBean.origenItems}"/>
							</h:selectOneMenu>

						<f:verbatim>&nbsp;</f:verbatim>
						<f:verbatim>&nbsp;</f:verbatim>
						</h:panelGrid>
						</s:fieldset>
					
						<f:verbatim><br></f:verbatim>
						
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
							<x:commandButton id="buttonGrabar" value="Guardar" action="#{CodigoAutorizacionBean.grabar}" styleClass="btn btn-primary btn-flat"/>
							<x:commandButton id="buttonCancelar" value="Cancelar" action="#{CodigoAutorizacionBean.cancelar}" styleClass="btn btn-primary btn-flat" />
						</h:panelGrid>
						
					</h:panelGrid>
				</h:panelGroup>
			</f:facet>

		</x:panelLayout>

	<h:inputText id="FechaDesde" value="#{CodigoAutorizacionBean.fecha}" style="display: none;">
        <f:convertDateTime type="both" pattern = "dd/MM/yyyy HH:mm"/>
    </h:inputText>


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
    document.getElementById("sideMenu").innerHTML += generateMenu(obj,"mainMenu__idJsp2_menu:A]\#{CodigoAutorizacionBean.inicializar}") + `</li>`;
}

</script>

<%@include file="/inc/scripts.jsp" %>

<script type="text/javascript">
$(function () {

    //Inicializo datepicker
    $("#fDesde").datepicker({
      autoclose: true,
      orientation: "bottom"
    });


    //Muevo fechas de datepicker a input q pasa datos al bean
    $("#fDesde").change(function() {
        var fd = $(this).datepicker("getDate");
        //la hora va en gtm 0. al pasar el server que está en gtm -3, le restará 3 hs.
        document.getElementById("amCodigoAutorizacionForm:FechaDesde").value = $.datepicker.formatDate('dd/mm/yy', fd) + " 03:00"; 
    });

  });

</script>


</body>
</html>
</f:view>
