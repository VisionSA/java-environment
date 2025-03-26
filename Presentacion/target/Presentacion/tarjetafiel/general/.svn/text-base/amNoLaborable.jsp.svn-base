<%@include file="/inc/tags.jsp" %>
<jsp:useBean id="ahora" class="java.util.Date" scope="request"/>

<f:view>
<html lang="es">
<head>
	<title><h:outputText value="#{NoLaborableBean.tituloLargo}"/></title>
	<s:script language="javascript">
		function recargar() {
			document.getElementById('amNoLaborableForm').submit();
		}
	</s:script>
</head>

<jsp:include page="/inc/includes.jsp" />

<body class="hold-transition skin-blue sidebar-mini" onbeforeunload="ShowWait('amNoLaborableForm');" onload="if('${NoLaborableBean.popup.mostrar}'=='true') {viewDialog.show();} else {window.scrollTo(0,${ScrollBean.hiddenScrollY});}">
	<h:form id="mainMenu" style="display: none">
  <jsp:include page="/inc/navigation_test.jsp" />
  <x:commandLink id="cerrarSesion" action="#{LoginBean.salir}"/>
</h:form>

<jsp:include page="/inc/header.jsp" />

<!-- Content Header (Page header) -->
<section class="content-header">
  <h1>
    ${NoLaborableBean.tituloCorto}
    <small>${NoLaborableBean.tituloLargo}</small>
  </h1>
</section>

<section class="content">

<div class="box box-default">
<div class="box-header"><h3></h3></div>

<center>
	<secure:check/>

	<h:form id="amNoLaborableForm">

	<%-- GRABA EL ESTADO DEL SCROLL(Dibujar solo si el popup no se muestra) --%>
	<h:panelGroup rendered="#{!NoLaborableBean.popup.mostrar}">
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
								dialogTitle="#{NoLaborableBean.tituloCorto}">
						<h:panelGrid columns="2" width="500">
							<x:graphicImage value="/img/#{NoLaborableBean.popup.nombreIcono}" />
							<h:outputText value="#{NoLaborableBean.popup.mensaje}" styleClass="texto"/>
						</h:panelGrid>
						<h:panelGrid columns="3" width="500">
							<x:commandButton action="#{NoLaborableBean.irANuevoNoLaborable}" 
								onclick="clickLink('nuevoNoLaborable');dojo.widget.byId('viewDialog').hide();"
								value="Nuevo" styleClass="btn btn-primary btn-flat pull-right" title="Agregar nueva."/>
							<x:commandButton action="#{NoLaborableBean.irAModificarNoLaborable}" 
								onclick="clickLink('modificarNoLaborable');dojo.widget.byId('viewDialog').hide();"
								value="Modificar" styleClass="btn btn-primary btn-flat pull-right" title="Seguir modificando."/>
							<x:commandButton action="#{NoLaborableBean.irAListarNoLaborable}" 
								onclick="clickLink('listarNoLaborable');dojo.widget.byId('viewDialog').hide();"
								value="Listar" styleClass="btn btn-primary btn-flat pull-right" title="Ir al listado."/>
						</h:panelGrid>
					</s:modalDialog>
					<x:commandLink id="nuevoNoLaborable" action="#{NoLaborableBean.irANuevoNoLaborable}" forceId="true" style="display: block;"/>
					<x:commandLink id="modificarNoLaborable" action="#{NoLaborableBean.irAModificarNoLaborable}" forceId="true" style="display: block;"/>
					<x:commandLink id="listarNoLaborable" action="#{NoLaborableBean.irAListarNoLaborable}" forceId="true" style="display: block;"/>
					
					<h:panelGrid id="uno" columns="1" width="650" align="center">
					<s:fieldset legend="Feriado">
						<h:panelGrid id="panelPrincipalUno" columns="6" width="601" align="center">
						
							<h:outputText value="Fecha:" styleClass="texto"/>
							<f:verbatim>
					                <div class="input-group date">
					                    <div class="input-group-addon">
					                        <i class="fa fa-calendar"></i>
					                    </div>
					                    <input type="text" class="form-control pull-right" id="fDesde" placeholder="DD/MM/AAAA" autocomplete="off">
					                </div>
							</f:verbatim>
					      
							<h:outputText value="Es Feriado:" styleClass="texto"/>
							<h:selectBooleanCheckbox value="#{NoLaborableBean.esFeriado}" />
						  
							<s:fieldset id="fldTipos" legend="Tipo No Laborable">
							<h:panelGrid columns="2" id="pnlTipo" width="200">
						    	<h:outputText value="Bancario:" styleClass="texto"/>
						    	<h:selectBooleanCheckbox value="#{NoLaborableBean.esBancario}" />
						    		<h:outputText value="Provincial:" styleClass="texto"/>
						    	<h:selectBooleanCheckbox value="#{NoLaborableBean.esNacional}" />
							<h:outputText value="Nacional:" styleClass="texto"/>
						    	<h:selectBooleanCheckbox value="#{NoLaborableBean.esProvincial}" />
						    </h:panelGrid>	
                            </s:fieldset>							
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
						<x:commandButton id="buttonGrabar" value="Guardar" action="#{NoLaborableBean.grabar}" styleClass="btn btn-primary btn-flat pull-right"/>
						<x:commandButton id="buttonCancelar" value="Cancelar" action="#{NoLaborableBean.cancelar}" styleClass="btn btn-primary btn-flat pull-right" />
					</h:panelGrid>
					</h:panelGrid>
				</h:panelGroup>
			</f:facet>
			
		</x:panelLayout>

	<h:inputText id="FechaDesde" value="#{NoLaborableBean.fecha}" style="display: none;">
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
    document.getElementById("sideMenu").innerHTML += generateMenu(obj,"mainMenu__idJsp2_menu:A]\#{NoLaborableBean.inicializar}") + `</li>`;
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
        document.getElementById("amNoLaborableForm:FechaDesde").value = $.datepicker.formatDate('dd/mm/yy', fd) + " 03:00"; 
    });

  });

</script>


</body>
</html>
</f:view>
