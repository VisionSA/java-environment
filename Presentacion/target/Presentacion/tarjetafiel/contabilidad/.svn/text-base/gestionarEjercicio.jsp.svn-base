<%@include file="/inc/tags.jsp" %>
<jsp:useBean id="ahora" class="java.util.Date" scope="request"/>

<f:view>
<html lang="es">
<head>
	<title><h:outputText value="#{EjercicioBean.tituloLargo}"/></title>
	<s:script language="javascript">
		function recargar() {
			document.getElementById('amEjercicioForm').submit();
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

<body class="hold-transition skin-blue sidebar-mini" onbeforeunload="ShowWait('amEjercicioForm');" onload="if('${EjercicioBean.popup.mostrar}'=='true') {viewDialog.show();} else {window.scrollTo(0,${ScrollBean.hiddenScrollY});}">

	<h:form id="mainMenu" style="display: none">
  <jsp:include page="/inc/navigation_test.jsp" />
  <x:commandLink id="cerrarSesion" action="#{LoginBean.salir}"/>
</h:form>

<jsp:include page="/inc/header.jsp" />

<!-- Content Header (Page header) -->
<section class="content-header">
  <h1>
    ${EjercicioBean.tituloCorto}
    <small>${EjercicioBean.tituloLargo}</small>
  </h1>
</section>

<section class="content">

<div class="box box-default">
<div class="box-header"><h3></h3></div>
<center>
	<secure:check/>

	<h:form id="amEjercicioForm">

	<%-- GRABA EL ESTADO DEL SCROLL(Dibujar solo si el popup no se muestra) --%>
	<h:panelGroup rendered="#{!EjercicioBean.popup.mostrar}">
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
								dialogTitle="#{EjercicioBean.tituloCorto}">
						<h:panelGrid columns="2" width="500">
							<x:graphicImage value="/img/#{EjercicioBean.popup.nombreIcono}" />
							<h:outputText value="#{EjercicioBean.popup.mensaje}" styleClass="texto"/>
						</h:panelGrid>
						<h:panelGrid columns="3" width="500">
							<x:commandButton action="#{EjercicioBean.agregarEjercicio}" 
								onclick="clickLink('nuevoEjercicio');dojo.widget.byId('viewDialog').hide();"
								value="Nuevo" styleClass="btn btn-primary btn-flat" title="Agregar nueva."/>
							<x:commandButton action="#{EjercicioBean.modificarEjercicio}" 
								onclick="clickLink('modificarEjercicio');dojo.widget.byId('viewDialog').hide();"
								value="Modificar" styleClass="btn btn-primary btn-flat" title="Seguir modificando."/>
							<x:commandButton action="#{EjercicioBean.irAGestionarEjercicio}" 
								onclick="clickLink('listarEjercicio');dojo.widget.byId('viewDialog').hide();"
								value="Listar" styleClass="btn btn-primary btn-flat" title="Ir al listado."/>
						</h:panelGrid>
					</s:modalDialog>
					<x:commandLink id="nuevoEjercicio" action="#{EjercicioBean.agregarEjercicio}" forceId="true" style="display: block;"/>
					<x:commandLink id="modificarEjercicio" action="#{EjercicioBean.modificarEjercicio}" forceId="true" style="display: block;"/>
					<x:commandLink id="listarEjercicio" action="#{EjercicioBean.irAGestionarEjercicio}" forceId="true" style="display: block;"/>


                    <h:panelGrid columns="1" id="uno" width="850" align="center">
                        <h:panelGrid id="pan" width="850" align="center" rendered="#{EjercicioBean.panelAlta}">
						<s:fieldset id="fieldParaAlta" legend="Alta de Ejercicio">
							<h:panelGrid id="panelPrincipalParaAltas" columns="4" width="600" align="center">
								<h:outputText value="Sucursal:" styleClass="texto"/>
								 <h:selectOneMenu id="lstSucursales" value="#{EjercicioBean.idSucSeleccionada}" disabled="true"
			       					  styleClass="lista" onfocus="encender(this);" immediate="true"
			       					  onblur="apagar(this);" style=" width : 200px;" onchange="submit();">
			       					  <f:selectItems value="#{EjercicioBean.sucursalesParaAlta}" id="selectSucu" />
			       				</h:selectOneMenu>	
								<h:outputText value="Ejercicio Abierto: " rendered="#{EjercicioBean.alta}" />
								<h:selectBooleanCheckbox value="#{EjercicioBean.ejercicioAbierto}" rendered="#{EjercicioBean.alta}" style="width: 25px" id="estado" disabled="true"/>
							</h:panelGrid>

							<f:verbatim>&nbsp;</f:verbatim>

							<h:panelGrid id="paneldeFechas" columns="6" width="900" align="center">
								<h:outputText value="Fecha Inicio:" styleClass="texto"/>
								<f:verbatim>
						            <div class="input-group date">
						                <div class="input-group-addon">
						                    <i class="fa fa-calendar"></i>
						                </div>
						                <input type="text" class="form-control pull-right" id="fDesde">
						            </div>
								</f:verbatim>

								<h:outputText value="Fecha Cierre:" styleClass="texto"/>
								<f:verbatim>
							        <div class="input-group date">
							            <div class="input-group-addon">
							           	   <i class="fa fa-calendar"></i>
							            </div>
							            <input type="text" class="form-control pull-right" id="fHasta">
							        </div>
								</f:verbatim>	
			                	<h:outputText value="Fecha Período:" styleClass="texto"/>
								<f:verbatim>
							        <div class="input-group date">
							            <div class="input-group-addon">
							           	   <i class="fa fa-calendar"></i>
							            </div>
							            <input type="text" class="form-control pull-right" id="fPeriodo">
							        </div>
								</f:verbatim>	
			                	
							</h:panelGrid>

							<f:verbatim>&nbsp;</f:verbatim>


							<h:panelGrid id="panelDefaulValue" columns="2" align="center">
								<h:outputText value="Setear como ejercicio por defecto:" styleClass="texto"/>
								<h:selectBooleanCheckbox value="#{EjercicioBean.ejercicioDefault}" style="width: 25px" id="default"/>
							</h:panelGrid>

							<f:verbatim>&nbsp;</f:verbatim>
						
							<h:panelGrid id="panelDeObservaciones" columns="2" align="center">
								<h:outputText value="Observaciones:" styleClass="texto"/>
								<h:inputText id="obser" value="#{EjercicioBean.observaciones}" size="60" maxlength="50"/>
							</h:panelGrid>

							<f:verbatim>&nbsp;</f:verbatim>

							<h:panelGrid id="panlDeObservaciones" columns="3" align="center">
								<x:commandButton action="#{EjercicioBean.cerrarEjercicio}" onclick="return confirm('Esta acción cerrará el ejercicio. ¿Desea continuar?')" 
											rendered="#{EjercicioBean.modificacion}" id="botonSumbitCerrarEjercicio" value="Cerrar Ejercicio" styleClass="btn btn-primary btn-flat"/>
								<x:commandButton action="#{EjercicioBean.grabarEjercicio}" id="botonSumbitGrabarEjercicio" value="Grabar Ejercicio" styleClass="btn btn-primary btn-flat"/>
								<x:commandButton action="#{EjercicioBean.volverAlListado}" id="botonSumbitCancelar" value="Cancelar" styleClass="btn btn-primary btn-flat"/>
							</h:panelGrid>
						</s:fieldset>
						</h:panelGrid>
						<h:panelGrid id="dos" width="850" align="center" rendered="#{!EjercicioBean.panelAlta}" columns="1">
						<s:fieldset legend="Ejercicios Existentes ">
							<h:panelGrid id="controlDeSucursal" columns="2" align="center">
							     <h:outputText value="Seleccione Sucursal:"/>
							     <h:selectOneMenu id="lstSucParaEjercicios" value="#{EjercicioBean.idSucSeleccionadaParaFiel}" disabled="true" binding="#{EjercicioBean.idSucursalDeFielSeleccionada}" onchange="submit();"
			       					  styleClass="lista" onfocus="encender(this);" immediate="true"
			       					  onblur="apagar(this);" style=" width : 200px;" valueChangeListener="#{EjercicioBean.cambiarElListado}">
			       					  <f:selectItems value="#{EjercicioBean.sucursalesDeFiel}" id="selectSucujar" />
			       				</h:selectOneMenu>	
							
							</h:panelGrid>

							<f:verbatim>&nbsp;</f:verbatim>
							
							<h:dataTable value="#{EjercicioBean.ejercicios}" align="center"  title="Ejercicios Existentes"
									id="tablaEjercicios" styleClass="standardTable" headerClass="dataTable_Header"
									footerClass="standardTable_Header" rowClasses="standardTable_Row1,standardTable_Row2"
									columnClasses="standardTable_ColumnCentered,standardTable_ColumnCentered,"
									var="objetoEjercicio" style=" width : 600px;">
										
									<h:column>
										<f:facet name="header">
											<h:outputText value="Número" styleClass="texto" />
										</f:facet>
										<h:outputText value="#{objetoEjercicio.ejercicio.idEjercicio}"/>
									</h:column>
										
									<h:column>
										<f:facet name="header">
											<h:outputText value="Fecha Inicio" styleClass="texto"  />
										</f:facet>
										<h:outputText value="#{objetoEjercicio.ejercicio.fechaInicio}"/>
									</h:column>
									
									<h:column>
										<f:facet name="header">
											<h:outputText value="Fecha Cierre" styleClass="texto"  />
										</f:facet>
										<h:outputText value="#{objetoEjercicio.ejercicio.fechaCierre}"/>
									</h:column>
									
									<h:column>
										<f:facet name="header">
											<h:outputText value="Fecha Período" styleClass="texto"  />
										</f:facet>
										<h:outputText value="#{objetoEjercicio.ejercicio.fechaPeriodo}"/>
									</h:column>
									
                                    <h:column>
										<f:facet name="header">
											<h:outputText value="Estado" styleClass="texto" />
										</f:facet>
										<h:outputText value="#{objetoEjercicio.ejercicio.estado}"/>
									</h:column>

									<h:column>
										<x:commandLink id="editarEjercicioLink"  action="#{EjercicioBean.modificarEjercicio}" >
											<x:graphicImage value="/img/editar.gif" title="Editar un Ejercicio." border="0" id="botonImagenTresA" />
											<f:param id="idEjeEdi" name="idEjeEdi" value="#{objetoEjercicio.idEjer}"/>
										</x:commandLink>
									</h:column>
									
								</h:dataTable>
										
								
	                		</s:fieldset>	

	                		<f:verbatim>&nbsp;</f:verbatim>

	                		<h:panelGrid columns="2" id="ultimo" align="center">
	                		    <x:commandButton action="#{EjercicioBean.agregarEjercicio}" id="botonSumbitAltaDeEjercicio" value="Alta de Ejercicio" styleClass="btn btn-primary btn-flat"/>
								<x:commandButton action="#{EjercicioBean.volverAlMenu}" id="botonSumbitVolverMenu" value="Cancelar" styleClass="btn btn-primary btn-flat"/>  
	                		</h:panelGrid>
	                			
		            		</h:panelGrid>
					</h:panelGrid>





					
					</h:panelGrid>
				</h:panelGroup>
			</f:facet>
			
		</x:panelLayout>


	<h:inputText id="FechaDesde" value="#{EjercicioBean.fechaInicio}" style="display: none;">
        <f:convertDateTime type="both" pattern = "dd/MM/yyyy HH:mm"/>
    </h:inputText>
    <h:inputText id="FechaHasta" value="#{EjercicioBean.fechaCierre}" style="display: none;">
        <f:convertDateTime type="both" pattern = "dd/MM/yyyy HH:mm"/>
    </h:inputText>
    <h:inputText id="FechaPeriodo" value="#{EjercicioBean.fechaPeriodo}" style="display: none;">
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
    document.getElementById("sideMenu").innerHTML += generateMenu(obj,"mainMenu__idJsp2_menu:A]\#{EjercicioBean.irAGestionarEjercicio}") + `</li>`;
}

</script>

<%@include file="/inc/scripts.jsp" %>



<script type="text/javascript">
$(function () {


    //Inicializo datepicker
    $("#fDesde").datepicker({
      autoclose: true,
    });

    $("#fHasta").datepicker({
      autoclose: true,
    });

    $("#fPeriodo").datepicker({
      autoclose: true,
    });

	//Seteo fehcas que trae el bean por defecto, en el datepicker
	var fd = document.getElementById("amEjercicioForm:FechaDesde").value.split(" ")[0].split("/");
	var year = fd[2];
	var month = fd[1];
	var date = fd[0];
	var fechaAux = new Date(year+"-"+month+"-"+date);
	fechaAux.setDate(fechaAux.getDate() + 1);
	$("#fDesde").datepicker("setDate", fechaAux);

	fd = document.getElementById("amEjercicioForm:FechaHasta").value.split(" ")[0].split("/");
	year = fd[2];
	month = fd[1];
	date = fd[0];
	fechaAux = new Date(year+"-"+month+"-"+date);
	fechaAux.setDate(fechaAux.getDate() + 1);
	$("#fHasta").datepicker("setDate", fechaAux);

	fd = document.getElementById("amEjercicioForm:FechaPeriodo").value.split(" ")[0].split("/");
	year = fd[2];
	month = fd[1];
	date = fd[0];
	fechaAux = new Date(year+"-"+month+"-"+date);
	fechaAux.setDate(fechaAux.getDate() + 1);
	$("#fPeriodo").datepicker("setDate", fechaAux);

    
    //Desabilito datepickers en caso de ser necesario
    var mod = ${EjercicioBean.modificacionDisabled};
    var modp = ${EjercicioBean.modificacionFechaPeriodo};

	if (mod) {
		$("#fDesde").datepicker({minDate:-1,maxDate:-2}).attr('disabled','disabled');  
		$("#fHasta").datepicker({minDate:-1,maxDate:-2}).attr('disabled','disabled');  
	}
	if (!modp) {
		$("#fPeriodo").datepicker({minDate:-1,maxDate:-2}).attr('disabled','disabled');  
	}


    //Muevo fechas de datepicker a input q pasa datos al bean
    $("#fDesde").change(function() {
        var fd = $(this).datepicker("getDate");
        document.getElementById("amEjercicioForm:FechaDesde").value = $.datepicker.formatDate('dd/mm/yy', fd) + " 03:00"; 
    });

    $("#fHasta").change(function() {
        var fh = $(this).datepicker("getDate");
		document.getElementById("amEjercicioForm:FechaHasta").value = $.datepicker.formatDate('dd/mm/yy', fh) + " 03:00"; 
    });

    $("#fPeriodo").change(function() {
        var fp = $(this).datepicker("getDate");
		document.getElementById("amEjercicioForm:FechaPeriodo").value = $.datepicker.formatDate('dd/mm/yy', fp) + " 03:00"; 
    });


  });
</script>



</body>
</html>
</f:view>
